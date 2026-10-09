package com.lab.module.rbac.controller;

import com.lab.common.api.PageResult;
import com.lab.common.api.Result;
import com.lab.common.audit.AuditLog;
import com.lab.common.security.SecurityContextUtil;
import com.lab.module.rbac.dto.AssignRolesRequest;
import com.lab.module.rbac.dto.AvatarVo;
import com.lab.module.rbac.dto.ChangeDeptRequest;
import com.lab.module.rbac.dto.ChangeMentorGroupRequest;
import com.lab.module.rbac.dto.UserCreateRequest;
import com.lab.module.rbac.dto.UserImportResultVo;
import com.lab.module.rbac.dto.UserUpdateRequest;
import com.lab.module.rbac.dto.UserVo;
import com.lab.module.rbac.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
import org.springframework.web.multipart.MultipartFile;

@Tag(name="\u7528\u6237")
@RestController
@RequestMapping(value={"/api/users"})
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize(value="@perm.has('system:user:list')")
    public Result<PageResult<UserVo>> page(@RequestParam(defaultValue="1") long current, @RequestParam(defaultValue="10") long size, @RequestParam(required=false) String keyword, @RequestParam(required=false) String deptNanoId, @RequestParam(required=false) String mentorGroupNanoId) {
        return Result.ok(this.userService.page(current, size, keyword, deptNanoId, mentorGroupNanoId));
    }

    @GetMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:user:view')")
    public Result<UserVo> get(@PathVariable String nanoId) {
        return Result.ok(this.userService.getByNanoId(nanoId));
    }

    @PostMapping
    @PreAuthorize(value="@perm.has('system:user:create')")
    @AuditLog(module="user", action="create", description="\u65b0\u589e\u7528\u6237")
    public Result<String> create(@RequestBody @Valid UserCreateRequest req) {
        return Result.ok(this.userService.create(req));
    }

    @PutMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:user:update')")
    @AuditLog(module="user", action="update", description="\u4fee\u6539\u7528\u6237")
    public Result<Void> update(@PathVariable String nanoId, @RequestBody @Valid UserUpdateRequest req) {
        this.userService.update(nanoId, req);
        return Result.ok();
    }

    @DeleteMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('system:user:delete')")
    @AuditLog(module="user", action="delete", description="\u5220\u9664\u7528\u6237")
    public Result<Void> delete(@PathVariable String nanoId) {
        this.userService.delete(nanoId);
        return Result.ok();
    }

    @PutMapping(value={"/{nanoId}/roles"})
    @PreAuthorize(value="@perm.has('system:user:assign')")
    @AuditLog(module="user", action="assign", description="\u5206\u914d\u7528\u6237\u89d2\u8272")
    public Result<Void> assignRoles(@PathVariable String nanoId, @RequestBody @Valid AssignRolesRequest req) {
        this.userService.assignRoles(nanoId, req.getRoleNanoIds());
        return Result.ok();
    }

    @PutMapping(value={"/{nanoId}/dept"})
    @PreAuthorize(value="@perm.has('system:user:update')")
    @AuditLog(module="user", action="update", description="\u4fee\u6539\u7528\u6237\u90e8\u95e8")
    public Result<Void> changeDept(@PathVariable String nanoId, @RequestBody @Valid ChangeDeptRequest req) {
        this.userService.changeDept(nanoId, req.getDeptNanoId());
        return Result.ok();
    }

    @PutMapping(value={"/{nanoId}/mentor-group"})
    @PreAuthorize(value="@perm.has('system:user:update')")
    @AuditLog(module="user", action="update", description="\u4fee\u6539\u7528\u6237\u5bfc\u5e08\u7ec4")
    public Result<Void> changeMentorGroup(@PathVariable String nanoId, @RequestBody @Valid ChangeMentorGroupRequest req) {
        this.userService.changeMentorGroup(nanoId, req.getMentorGroupNanoId());
        return Result.ok();
    }

    @PostMapping(value={"/import"})
    @PreAuthorize(value="@perm.has('system:user:import')")
    @AuditLog(module="user", action="import", description="\u6279\u91cf\u5bfc\u5165\u7528\u6237", logArgs=false)
    public Result<UserImportResultVo> importUsers(@RequestParam(value="file") MultipartFile file) {
        return Result.ok(this.userService.importUsers(file));
    }

    @GetMapping(value={"/import-template"})
    @PreAuthorize(value="@perm.has('system:user:import')")
    public void downloadImportTemplate(HttpServletResponse response) {
        this.userService.downloadTemplate(response);
    }

    @PostMapping(value={"/me/avatar"})
    @AuditLog(module="user", action="update", description="\u66f4\u6362\u5934\u50cf", logArgs=false)
    public Result<AvatarVo> uploadOwnAvatar(@RequestParam(value="file", required=false) MultipartFile file) {
        return Result.ok(this.userService.uploadAvatar(SecurityContextUtil.currentUserId(), file));
    }

    @PostMapping(value={"/{nanoId}/avatar"})
    @PreAuthorize(value="@perm.has('system:user:update')")
    @AuditLog(module="user", action="update", description="\u66f4\u6362\u5934\u50cf", logArgs=false)
    public Result<AvatarVo> uploadAvatar(@PathVariable String nanoId, @RequestParam(value="file", required=false) MultipartFile file) {
        return Result.ok(this.userService.uploadAvatar(nanoId, file));
    }

    @DeleteMapping(value={"/me/avatar"})
    @AuditLog(module="user", action="update", description="\u5220\u9664\u5934\u50cf", logArgs=false)
    public Result<Void> removeOwnAvatar() {
        this.userService.removeAvatar(SecurityContextUtil.currentUserId());
        return Result.ok();
    }

    @DeleteMapping(value={"/{nanoId}/avatar"})
    @PreAuthorize(value="@perm.has('system:user:update')")
    @AuditLog(module="user", action="update", description="\u5220\u9664\u5934\u50cf", logArgs=false)
    public Result<Void> removeAvatar(@PathVariable String nanoId) {
        this.userService.removeAvatar(nanoId);
        return Result.ok();
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}

