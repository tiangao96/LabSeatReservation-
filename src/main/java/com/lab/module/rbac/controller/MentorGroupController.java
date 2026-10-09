package com.lab.module.rbac.controller;

import com.lab.common.api.Result;
import com.lab.common.audit.AuditLog;
import com.lab.module.rbac.dto.MentorGroupCreateRequest;
import com.lab.module.rbac.dto.MentorGroupVo;
import com.lab.module.rbac.service.MentorGroupService;
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

@Tag(name="\u5bfc\u5e08\u7ec4")
@RestController
@RequestMapping(value={"/api/mentor-groups"})
public class MentorGroupController {
    private final MentorGroupService mentorGroupService;

    @GetMapping
    @PreAuthorize(value="@perm.has('system:mentor-group:list')")
    public Result<List<MentorGroupVo>> list() {
        return Result.ok(this.mentorGroupService.list());
    }

    @GetMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:mentor-group:view')")
    public Result<MentorGroupVo> get(@PathVariable String nanoId) {
        return Result.ok(this.mentorGroupService.getByNanoId(nanoId));
    }

    @PostMapping
    @PreAuthorize(value="@perm.has('system:mentor-group:create')")
    @AuditLog(module="mentor-group", action="create", description="\u65b0\u589e\u5bfc\u5e08\u7ec4")
    public Result<String> create(@RequestBody @Valid MentorGroupCreateRequest req) {
        return Result.ok(this.mentorGroupService.create(req));
    }

    @PutMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:mentor-group:update')")
    @AuditLog(module="mentor-group", action="update", description="\u4fee\u6539\u5bfc\u5e08\u7ec4")
    public Result<Void> update(@PathVariable String nanoId, @RequestBody @Valid MentorGroupCreateRequest req) {
        this.mentorGroupService.update(nanoId, req);
        return Result.ok();
    }

    @DeleteMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:mentor-group:delete')")
    @AuditLog(module="mentor-group", action="delete", description="\u5220\u9664\u5bfc\u5e08\u7ec4")
    public Result<Void> delete(@PathVariable String nanoId) {
        this.mentorGroupService.delete(nanoId);
        return Result.ok();
    }

    public MentorGroupController(MentorGroupService mentorGroupService) {
        this.mentorGroupService = mentorGroupService;
    }
}

