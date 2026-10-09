package com.lab.common.audit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.common.audit.AuditLog;
import com.lab.common.audit.AuditLogEntry;
import com.lab.common.audit.service.AuditLogService;
import com.lab.common.security.SecurityContextUtil;
import com.lab.common.security.UserPrincipal;
import com.lab.common.util.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuditLogAspect {
    private static final Logger log = LoggerFactory.getLogger(AuditLogAspect.class);
    private final AuditLogService auditLogService;
    private final ObjectMapper objectMapper;
    private static final Set<Class<?>> EXCLUDE_TYPES = Set.of(HttpServletRequest.class, HttpServletResponse.class, OutputStream.class, Writer.class, BindingResult.class, Model.class);
    private static final Pattern SENSITIVE = Pattern.compile("(\"(?:password|oldPassword|newPassword|token|refreshToken|accessToken|secret)\"\\s*:\\s*)\"[^\"]*\"", 2);
    private static final int PARAMS_MAX = 2000;

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Around(value="@annotation(auditLog)")
    public Object around(ProceedingJoinPoint pjp, AuditLog auditLog) throws Throwable {
        Object object;
        long start = System.nanoTime();
        Throwable thrown = null;
        Object result = null;
        try {
            object = result = pjp.proceed();
        }
        catch (Throwable t) {
            try {
                thrown = t;
                throw t;
            }
            catch (Throwable throwable) {
                try {
                    long durationMs = (System.nanoTime() - start) / 1000000L;
                    this.auditLogService.record(this.buildEntry(pjp, auditLog, durationMs, thrown, result));
                    throw throwable;
                }
                catch (Exception e) {
                    log.warn("audit aspect capture failed: module={}, action={}, err={}", new Object[]{auditLog.module(), auditLog.action(), e.toString()});
                }
                throw throwable;
            }
        }
        try {
            long durationMs = (System.nanoTime() - start) / 1000000L;
            this.auditLogService.record(this.buildEntry(pjp, auditLog, durationMs, thrown, result));
            return object;
        }
        catch (Exception e) {
            log.warn("audit aspect capture failed: module={}, action={}, err={}", new Object[]{auditLog.module(), auditLog.action(), e.toString()});
        }
        return object;
    }

    private AuditLogEntry buildEntry(ProceedingJoinPoint pjp, AuditLog ann, long durationMs, Throwable thrown, Object result) {
        UserPrincipal principal = SecurityContextUtil.current();
        Object[] args = pjp.getArgs();
        Long userId = principal != null ? principal.getId() : null;
        Long deptId = principal != null ? principal.getDeptId() : null;
        String username = principal != null ? principal.getUsername() : this.extractUsername(args);
        HttpServletRequest req = this.currentRequest();
        String httpMethod = req != null ? req.getMethod() : null;
        String requestUri = req != null ? req.getRequestURI() : null;
        String ip = IpUtils.getClientIp((HttpServletRequest)req);
        String userAgent = req != null ? req.getHeader("User-Agent") : null;
        String targetId = this.extractPathVar(pjp, args);
        String params = ann.logArgs() ? this.serializeArgs(args) : null;
        boolean success = thrown == null;
        String errorMsg = thrown != null ? thrown.getClass().getSimpleName() + ": " + thrown.getMessage() : null;
        return AuditLogEntry.builder().userId(userId).username(username).deptId(deptId).module(ann.module()).action(ann.action()).description(ann.description()).targetId(targetId).httpMethod(httpMethod).requestUri(requestUri).ip(ip).userAgent(this.truncate(userAgent, 255)).params(params).success(success).errorMsg(success ? null : this.truncate(errorMsg, 2000)).durationMs(durationMs).build();
    }

    private String extractUsername(Object[] args) {
        if (args == null) {
            return null;
        }
        for (Object arg : args) {
            if (arg == null) continue;
            try {
                String s;
                Method m = arg.getClass().getMethod("getUsername", new Class[0]);
                Object v = m.invoke(arg, new Object[0]);
                if (!(v instanceof String) || (s = (String)v).isBlank()) continue;
                return s;
            }
            catch (NoSuchMethodException m) {
            }
            catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private String extractPathVar(ProceedingJoinPoint pjp, Object[] args) {
        MethodSignature ms = (MethodSignature)pjp.getSignature();
        Annotation[][] paramAnnos = ms.getMethod().getParameterAnnotations();
        for (int i = 0; i < paramAnnos.length; ++i) {
            for (Annotation a : paramAnnos[i]) {
                Object object;
                if (!(a instanceof PathVariable) || i >= args.length || !((object = args[i]) instanceof String)) continue;
                String s = (String)object;
                return s;
            }
        }
        return null;
    }

    private String serializeArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }
        HashSet<Integer> skip = new HashSet<Integer>();
        block2: for (int i = 0; i < args.length; ++i) {
            if (args[i] == null) {
                skip.add(i);
                continue;
            }
            for (Class t : EXCLUDE_TYPES) {
                if (!t.isInstance(args[i])) continue;
                skip.add(i);
                continue block2;
            }
        }
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (int i = 0; i < args.length; ++i) {
            if (skip.contains(i)) continue;
            if (!first) {
                sb.append(",");
            }
            first = false;
            try {
                sb.append(this.objectMapper.writeValueAsString(args[i]));
                continue;
            }
            catch (Exception e) {
                sb.append("\"<serialize-error:").append(args[i].getClass().getSimpleName()).append(">\"");
            }
        }
        sb.append("]");
        String json = this.mask(sb.toString());
        return this.truncate(json, 2000);
    }

    private String mask(String json) {
        if (json == null) {
            return null;
        }
        Matcher m = SENSITIVE.matcher(json);
        return m.replaceAll("$1\"***\"");
    }

    private String truncate(String s, int max) {
        if (s == null || s.length() <= max) {
            return s;
        }
        return s.substring(0, max);
    }

    private HttpServletRequest currentRequest() {
        RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
        if (attrs instanceof ServletRequestAttributes) {
            ServletRequestAttributes sra = (ServletRequestAttributes)attrs;
            return sra.getRequest();
        }
        return null;
    }

    public AuditLogAspect(AuditLogService auditLogService, ObjectMapper objectMapper) {
        this.auditLogService = auditLogService;
        this.objectMapper = objectMapper;
    }
}

