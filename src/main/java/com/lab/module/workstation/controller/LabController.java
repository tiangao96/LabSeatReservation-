package com.lab.module.workstation.controller;

import com.lab.common.api.Result;
import com.lab.common.audit.AuditLog;
import com.lab.common.security.DeptTreeNode;
import com.lab.module.workstation.dto.LabCreateRequest;
import com.lab.module.workstation.dto.LabLayoutRequest;
import com.lab.module.workstation.dto.LabVo;
import com.lab.module.workstation.service.LabService;
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

@Tag(name="\u5b9e\u9a8c\u5ba4")
@RestController
@RequestMapping(value={"/api/labs"})
public class LabController {
    private final LabService labService;

    @GetMapping(value={"/dept-tree"})
    @PreAuthorize(value="@perm.has('workstation:view')")
    public Result<List<DeptTreeNode>> deptTree() {
        return Result.ok(this.labService.deptTree(2));
    }

    @GetMapping
    @PreAuthorize(value="@perm.has('workstation:view')")
    public Result<List<LabVo>> list() {
        return Result.ok(this.labService.list());
    }

    @GetMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('workstation:view')")
    public Result<LabVo> get(@PathVariable String nanoId) {
        return Result.ok(this.labService.getByNanoId(nanoId));
    }

    @PostMapping
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="lab", action="create", description="\u65b0\u589e\u5b9e\u9a8c\u5ba4")
    public Result<String> create(@RequestBody @Valid LabCreateRequest req) {
        return Result.ok(this.labService.create(req));
    }

    @PutMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="lab", action="update", description="\u4fee\u6539\u5b9e\u9a8c\u5ba4")
    public Result<Void> update(@PathVariable String nanoId, @RequestBody @Valid LabCreateRequest req) {
        this.labService.update(nanoId, req);
        return Result.ok();
    }

    @DeleteMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="lab", action="delete", description="\u5220\u9664\u5b9e\u9a8c\u5ba4")
    public Result<Void> delete(@PathVariable String nanoId) {
        this.labService.delete(nanoId);
        return Result.ok();
    }

    @PutMapping(value={"/{nanoId}/layout"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="lab", action="updateLayout", description="\u4fdd\u5b58\u5b9e\u9a8c\u5ba4\u5e03\u5c40")
    public Result<Void> updateLayout(@PathVariable String nanoId, @RequestBody @Valid LabLayoutRequest req) {
        this.labService.updateLayout(nanoId, req);
        return Result.ok();
    }

    public LabController(LabService labService) {
        this.labService = labService;
    }
}

