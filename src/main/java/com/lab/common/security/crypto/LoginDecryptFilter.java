package com.lab.common.security.crypto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lab.common.api.Result;
import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.common.security.crypto.RewritableBodyRequestWrapper;
import com.lab.common.security.crypto.RsaCryptoProperties;
import com.lab.common.security.crypto.RsaCryptoUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class LoginDecryptFilter
extends OncePerRequestFilter {
    private static final String LOGIN_PATH = "/api/auth/login";
    private final RsaCryptoProperties props;
    private final RsaCryptoUtil rsaCryptoUtil;
    private final ObjectMapper objectMapper;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String plain;
        ObjectNode root;
        if (!this.props.isEnabled()) {
            chain.doFilter((ServletRequest)request, (ServletResponse)response);
            return;
        }
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (!"POST".equalsIgnoreCase(request.getMethod()) || !LOGIN_PATH.equals(path)) {
            chain.doFilter((ServletRequest)request, (ServletResponse)response);
            return;
        }
        byte[] rawBody = request.getInputStream().readAllBytes();
        if (rawBody.length == 0) {
            chain.doFilter((ServletRequest)new RewritableBodyRequestWrapper(request, rawBody), (ServletResponse)response);
            return;
        }
        try {
            root = (ObjectNode)this.objectMapper.readTree(rawBody);
        }
        catch (Exception e) {
            chain.doFilter((ServletRequest)new RewritableBodyRequestWrapper(request, rawBody), (ServletResponse)response);
            return;
        }
        JsonNode pwdNode = root.get("password");
        if (pwdNode == null || pwdNode.isNull() || pwdNode.asText().isBlank()) {
            chain.doFilter((ServletRequest)new RewritableBodyRequestWrapper(request, rawBody), (ServletResponse)response);
            return;
        }
        try {
            plain = this.rsaCryptoUtil.decrypt(pwdNode.asText());
        }
        catch (BizException e) {
            this.writeJson(response, Result.fail((ResultCode)e.getCode(), (String)e.getMessage()), 400);
            return;
        }
        root.put("password", plain);
        byte[] newBody = this.objectMapper.writeValueAsBytes((Object)root);
        chain.doFilter((ServletRequest)new RewritableBodyRequestWrapper(request, newBody), (ServletResponse)response);
    }

    private void writeJson(HttpServletResponse response, Result<?> result, int status) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(this.objectMapper.writeValueAsString(result));
    }

    public LoginDecryptFilter(RsaCryptoProperties props, RsaCryptoUtil rsaCryptoUtil, ObjectMapper objectMapper) {
        this.props = props;
        this.rsaCryptoUtil = rsaCryptoUtil;
        this.objectMapper = objectMapper;
    }
}

