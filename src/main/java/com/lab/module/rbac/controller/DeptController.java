package com.lab.module.rbac.controller;

import com.lab.common.api.Result;
import com.lab.common.audit.AuditLog;
import com.lab.module.rbac.dto.DeptCreateRequest;
import com.lab.module.rbac.dto.DeptVo;
import com.lab.module.rbac.service.DeptService;
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

@Tag(name="\u90e8\u95e8")
@RestController
@RequestMapping(value={"/api/depts"})
public class DeptController {
    private final DeptService deptService;

    @GetMapping
    @PreAuthorize(value="@perm.has('system:dept:list')")
    public Result<List<DeptVo>> list() {
        return Result.ok(this.deptService.list());
    }

    @GetMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:dept:view')")
    public Result<DeptVo> get(@PathVariable String nanoId) {
        return Result.ok(this.deptService.getByNanoId(nanoId));
    }

    @PostMapping
    @PreAuthorize(value="@perm.has('system:dept:create')")
    @AuditLog(module="dept", action="create", description="\u65b0\u589e\u90e8\u95e8")
    public Result<String> create(@RequestBody @Valid DeptCreateRequest req) {
        return Result.ok(this.deptService.create(req));
    }

    @PutMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:dept:update')")
    @AuditLog(module="dept", action="update", description="\u4fee\u6539\u90e8\u95e8")
    public Result<Void> update(@PathVariable String nanoId, @RequestBody @Valid DeptCreateRequest req) {
        this.deptService.update(nanoId, req);
        return Result.ok();
    }

    @DeleteMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:dept:delete')")
    @AuditLog(module="dept", action="delete", description="\u5220\u9664\u90e8\u95e8")
    public Result<Void> delete(@PathVariable String nanoId) {
        this.deptService.delete(nanoId);
        return Result.ok();
    }

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }
}

