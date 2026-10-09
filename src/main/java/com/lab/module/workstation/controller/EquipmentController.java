package com.lab.module.workstation.controller;

import com.lab.common.api.Result;
import com.lab.common.audit.AuditLog;
import com.lab.module.workstation.dto.EquipmentCreateRequest;
import com.lab.module.workstation.dto.EquipmentVo;
import com.lab.module.workstation.service.EquipmentService;
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

@Tag(name="\u8bbe\u5907")
@RestController
@RequestMapping(value={"/api/equipment"})
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping
    @PreAuthorize(value="@perm.has('workstation:view')")
    public Result<List<EquipmentVo>> list(@RequestParam String labNanoId) {
        return Result.ok(this.equipmentService.listByLab(labNanoId));
    }

    @GetMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('workstation:view')")
    public Result<EquipmentVo> get(@PathVariable String nanoId) {
        return Result.ok(this.equipmentService.getByNanoId(nanoId));
    }

    @PostMapping
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="equipment", action="create", description="\u65b0\u589e\u8bbe\u5907")
    public Result<String> create(@RequestBody @Valid EquipmentCreateRequest req) {
        return Result.ok(this.equipmentService.create(req));
    }

    @PutMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="equipment", action="update", description="\u4fee\u6539\u8bbe\u5907")
    public Result<Void> update(@PathVariable String nanoId, @RequestBody @Valid EquipmentCreateRequest req) {
        this.equipmentService.update(nanoId, req);
        return Result.ok();
    }

    @DeleteMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="equipment", action="delete", description="\u5220\u9664\u8bbe\u5907")
    public Result<Void> delete(@PathVariable String nanoId) {
        this.equipmentService.delete(nanoId);
        return Result.ok();
    }

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
}

