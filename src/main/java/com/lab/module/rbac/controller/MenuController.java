package com.lab.module.rbac.controller;

import com.lab.common.api.Result;
import com.lab.common.audit.AuditLog;
import com.lab.common.security.SecurityContextUtil;
import com.lab.module.rbac.dto.MenuCreateRequest;
import com.lab.module.rbac.dto.MenuTreeNode;
import com.lab.module.rbac.dto.MenuVo;
import com.lab.module.rbac.service.MenuService;
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

@Tag(name="\u83dc\u5355")
@RestController
@RequestMapping(value={"/api/menus"})
public class MenuController {
    private final MenuService menuService;

    @GetMapping(value={"/tree"})
    public Result<List<MenuTreeNode>> tree() {
        Long userId = SecurityContextUtil.currentUserId();
        return Result.ok(this.menuService.currentUserMenuTree(userId));
    }

    @GetMapping
    @PreAuthorize(value="@perm.has('system:menu:list')")
    public Result<List<MenuVo>> list() {
        return Result.ok(this.menuService.list());
    }

    @GetMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:menu:view')")
    public Result<MenuVo> get(@PathVariable String nanoId) {
        return Result.ok(this.menuService.getByNanoId(nanoId));
    }

    @PostMapping
    @PreAuthorize(value="@perm.has('system:menu:create')")
    @AuditLog(module="menu", action="create", description="\u65b0\u589e\u83dc\u5355")
    public Result<String> create(@RequestBody @Valid MenuCreateRequest req) {
        return Result.ok(this.menuService.create(req));
    }

    @PutMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:menu:update')")
    @AuditLog(module="menu", action="update", description="\u4fee\u6539\u83dc\u5355")
    public Result<Void> update(@PathVariable String nanoId, @RequestBody @Valid MenuCreateRequest req) {
        this.menuService.update(nanoId, req);
        return Result.ok();
    }

    @DeleteMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:menu:delete')")
    @AuditLog(module="menu", action="delete", description="\u5220\u9664\u83dc\u5355")
    public Result<Void> delete(@PathVariable String nanoId) {
        this.menuService.delete(nanoId);
        return Result.ok();
    }

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
}

