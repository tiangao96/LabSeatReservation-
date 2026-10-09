package com.lab.common.audit.controller;

import com.lab.common.api.PageResult;
import com.lab.common.api.Result;
import com.lab.common.audit.dto.AuditLogQueryRequest;
import com.lab.common.audit.dto.AuditLogVo;
import com.lab.common.audit.entity.AuditLog;
import com.lab.common.audit.service.AuditLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="\u64cd\u4f5c\u5ba1\u8ba1\u65e5\u5fd7")
@RestController
@RequestMapping(value={"/api/audit-logs"})
public class AuditLogController {
    private final AuditLogService auditLogService;

    @GetMapping
    @PreAuthorize(value="@perm.has('system:audit:list')")
    public Result<PageResult<AuditLogVo>> page(@RequestParam(defaultValue="1") long current, @RequestParam(defaultValue="20") long size, @ModelAttribute AuditLogQueryRequest query) {
        Page<AuditLog> p = this.auditLogService.page(current, size, query.getKeyword(), query.getModule(), query.getAction(), query.getResult(), query.getStartTime(), query.getEndTime());
        return Result.ok(PageResult.of(p.map(this::toVo)));
    }

    @GetMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:audit:view')")
    public Result<AuditLogVo> get(@PathVariable String nanoId) {
        return Result.ok(this.toVo(this.auditLogService.getByNanoId(nanoId)));
    }

    private AuditLogVo toVo(AuditLog a) {
        AuditLogVo vo = new AuditLogVo();
        vo.setNanoId(a.getNanoId());
        vo.setUserId(a.getUserId());
        vo.setUsername(a.getUsername());
        vo.setDeptId(a.getDeptId());
        vo.setModule(a.getModule());
        vo.setAction(a.getAction());
        vo.setDescription(a.getDescription());
        vo.setTargetId(a.getTargetId());
        vo.setHttpMethod(a.getHttpMethod());
        vo.setRequestUri(a.getRequestUri());
        vo.setIp(a.getIp());
        vo.setUserAgent(a.getUserAgent());
        vo.setParams(a.getParams());
        vo.setResult(a.getResult());
        vo.setErrorMsg(a.getErrorMsg());
        vo.setDurationMs(a.getDurationMs());
        vo.setCreateTime(a.getCreateTime());
        return vo;
    }

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }
}

