package com.lab.module.rbac.controller;

import com.lab.common.api.Result;
import com.lab.common.audit.AuditLog;
import com.lab.module.rbac.dto.AssignMenusRequest;
import com.lab.module.rbac.dto.AssignPermissionsRequest;
import com.lab.module.rbac.dto.RoleCreateRequest;
import com.lab.module.rbac.dto.RoleVo;
import com.lab.module.rbac.service.RoleService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="\u89d2\u8272")
@RestController
@RequestMapping(value={"/api/roles"})
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    @PreAuthorize(value="@perm.has('system:role:list')")
    public Result<List<RoleVo>> list(@RequestParam(required=false) String keyword) {
        return Result.ok(this.roleService.list(keyword));
    }

    @GetMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:role:view')")
    public Result<RoleVo> get(@PathVariable String nanoId) {
        return Result.ok(this.roleService.getByNanoId(nanoId));
    }

    @PostMapping
    @PreAuthorize(value="@perm.has('system:role:create')")
    @AuditLog(module="role", action="create", description="\u65b0\u589e\u89d2\u8272")
    public Result<String> create(@RequestBody @Valid RoleCreateRequest req) {
        return Result.ok(this.roleService.create(req));
    }

    @PutMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:role:update')")
    @AuditLog(module="role", action="update", description="\u4fee\u6539\u89d2\u8272")
    public Result<Void> update(@PathVariable String nanoId, @RequestBody @Valid RoleCreateRequest req) {
        this.roleService.update(nanoId, req);
        return Result.ok();
    }

    @DeleteMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:role:update')")
    @AuditLog(module="role", action="delete", description="\u5220\u9664\u89d2\u8272")
    public Result<Void> delete(@PathVariable String nanoId) {
        this.roleService.delete(nanoId);
        return Result.ok();
    }

    @GetMapping(value={"/{nanoId}/permissions"})
    @PreAuthorize(value="@perm.has('system:role:view')")
    public Result<List<String>> listPermissions(@PathVariable String nanoId) {
        return Result.ok(this.roleService.listPermissionNanoIds(nanoId));
    }

    @PutMapping(value={"/{nanoId}/permissions"})
    @PreAuthorize(value="@perm.has('system:role:assign')")
    @AuditLog(module="role", action="assign", description="\u5206\u914d\u89d2\u8272\u6743\u9650")
    public Result<Void> assignPermissions(@PathVariable String nanoId, @RequestBody @Valid AssignPermissionsRequest req) {
        this.roleService.assignPermissions(nanoId, req.getPermissionNanoIds());
        return Result.ok();
    }

    @GetMapping(value={"/{nanoId}/menus"})
    @PreAuthorize(value="@perm.has('system:role:view')")
    public Result<List<String>> listMenus(@PathVariable String nanoId) {
        return Result.ok(this.roleService.listMenuNanoIds(nanoId));
    }

    @PutMapping(value={"/{nanoId}/menus"})
    @PreAuthorize(value="@perm.has('system:role:assign')")
    @AuditLog(module="role", action="assign", description="\u5206\u914d\u89d2\u8272\u83dc\u5355")
    public Result<Void> assignMenus(@PathVariable String nanoId, @RequestBody @Valid AssignMenusRequest req) {
        this.roleService.assignMenus(nanoId, req.getMenuNanoIds());
        return Result.ok();
    }

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
}

