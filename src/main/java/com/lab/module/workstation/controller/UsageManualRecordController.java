package com.lab.module.workstation.controller;

import com.lab.common.api.Result;
import com.lab.common.audit.AuditLog;
import com.lab.module.workstation.dto.UsageManualRecordRequest;
import com.lab.module.workstation.dto.UsageManualRecordVo;
import com.lab.module.workstation.service.UsageManualRecordService;
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

@Tag(name = "\u5de5\u4f4d\u7528\u65f6\u7edf\u8ba1-\u624b\u52a8\u8865\u5145")
@RestController
@RequestMapping(value = {"/api/usage-manual-records"})
public class UsageManualRecordController {
    private final UsageManualRecordService service;

    @GetMapping
    @PreAuthorize(value = "@perm.hasAny('workstation:usage:view')")
    public Result<List<UsageManualRecordVo>> list() {
        return Result.ok(this.service.list());
    }

    @PostMapping
    @PreAuthorize(value = "@perm.hasAny('workstation:usage:view')")
    @AuditLog(module = "workstation", action = "createUsageManualRecord", description = "\u65b0\u589e\u5de5\u4f4d\u7528\u65f6\u624b\u52a8\u8865\u5145\u8bb0\u5f55")
    public Result<UsageManualRecordVo> create(@RequestBody @Valid UsageManualRecordRequest req) {
        return Result.ok(this.service.create(req));
    }

    @PutMapping(value = {"/{nanoId}"})
    @PreAuthorize(value = "@perm.hasAny('workstation:usage:view')")
    @AuditLog(module = "workstation", action = "updateUsageManualRecord", description = "\u4fee\u6539\u5de5\u4f4d\u7528\u65f6\u624b\u52a8\u8865\u5145\u8bb0\u5f55")
    public Result<UsageManualRecordVo> update(@PathVariable("nanoId") String nanoId, @RequestBody @Valid UsageManualRecordRequest req) {
        return Result.ok(this.service.update(nanoId, req));
    }

    @DeleteMapping(value = {"/{nanoId}"})
    @PreAuthorize(value = "@perm.hasAny('workstation:usage:view')")
    @AuditLog(module = "workstation", action = "deleteUsageManualRecord", description = "\u5220\u9664\u5de5\u4f4d\u7528\u65f6\u624b\u52a8\u8865\u5145\u8bb0\u5f55")
    public Result<Void> delete(@PathVariable("nanoId") String nanoId) {
        this.service.delete(nanoId);
        return Result.ok();
    }
    public UsageManualRecordController(UsageManualRecordService service) {
        this.service = service;
    }
}
