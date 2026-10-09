package com.lab.module.rbac.controller;

import com.lab.common.api.Result;
import com.lab.common.audit.AuditLog;
import com.lab.module.rbac.dto.PermissionCreateRequest;
import com.lab.module.rbac.dto.PermissionVo;
import com.lab.module.rbac.service.PermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="\u6743\u9650")
@RestController
@RequestMapping(value={"/api/permissions"})
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    @PreAuthorize(value="@perm.has('system:permission:list')")
    public Result<List<PermissionVo>> list() {
        return Result.ok(this.permissionService.list());
    }

    @GetMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:permission:view')")
    public Result<PermissionVo> get(@PathVariable String nanoId) {
        return Result.ok(this.permissionService.getByNanoId(nanoId));
    }

    @PostMapping
    @PreAuthorize(value="@perm.has('system:permission:create')")
    @AuditLog(module="permission", action="create", description="\u65b0\u589e\u6743\u9650")
    public Result<String> create(@RequestBody @Valid PermissionCreateRequest req) {
        return Result.ok(this.permissionService.create(req));
    }

    @PutMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:permission:update')")
    @AuditLog(module="permission", action="update", description="\u4fee\u6539\u6743\u9650")
    public Result<Void> update(@PathVariable String nanoId, @RequestBody @Valid PermissionCreateRequest req) {
        this.permissionService.update(nanoId, req);
        return Result.ok();
    }

    @DeleteMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:permission:delete')")
    @AuditLog(module="permission", action="delete", description="\u5220\u9664\u6743\u9650")
    public Result<Void> delete(@PathVariable String nanoId) {
        this.permissionService.delete(nanoId);
        return Result.ok();
    }

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}

