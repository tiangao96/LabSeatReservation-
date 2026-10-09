package com.lab.module.workstation.controller;

import com.lab.common.api.Result;
import com.lab.common.audit.AuditLog;
import com.lab.module.workstation.dto.RentalRejectRequest;
import com.lab.module.workstation.dto.RentalRequestVo;
import com.lab.module.workstation.service.WorkstationRentalRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lab.module.workstation.entity.WorkstationStatus;
import java.time.YearMonth;
import com.lab.common.api.ResultCode;
import com.lab.common.security.MentorGroupResolver;
import com.lab.module.rbac.entity.CoreMentorGroup;
import com.lab.module.rbac.repository.CoreMentorGroupRepository;
import com.lab.module.workstation.dto.RentalRequestCreateRequest;
import com.lab.module.workstation.entity.Lab;
import com.lab.module.workstation.entity.RentalRequestStatus;
import com.lab.module.workstation.entity.Workstation;
import com.lab.module.workstation.entity.WorkstationRentalRequest;
import com.lab.module.workstation.entity.WorkstationRentalRequestItem;
import com.lab.module.workstation.repository.LabRepository;
import com.lab.module.workstation.repository.WorkstationRepository;
import com.lab.module.workstation.repository.WorkstationRentalRequestItemRepository;
import com.lab.module.workstation.repository.WorkstationRentalRequestRepository;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

@Tag(name="\u5de5\u4f4d\u7533\u8bf7")
@RestController
@RequestMapping(value={"/api/workstation-rental-requests"})
public class WorkstationRentalRequestController {
    private final WorkstationRentalRequestService rentalRequestService;

    @GetMapping
    @PreAuthorize(value="@perm.hasAny('workstation:rent:apply','workstation:rent:approve')")
    public Result<List<RentalRequestVo>> list(@RequestParam(name="mine", defaultValue="false") boolean mine, @RequestParam(name="status", required=false) String status, @RequestParam(name="mentorGroupNanoId", required=false) String mentorGroupNanoId, @RequestParam(name="labNanoId", required=false) String labNanoId) {
        return Result.ok(this.rentalRequestService.list(mine, status, mentorGroupNanoId, labNanoId));
    }

    @GetMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.hasAny('workstation:rent:apply','workstation:rent:approve')")
    public Result<RentalRequestVo> get(@PathVariable(name="nanoId") String nanoId) {
        return Result.ok(this.rentalRequestService.getByNanoId(nanoId));
    }

    @PutMapping(value={"/{nanoId}/approve"})
    @PreAuthorize(value="@perm.has('workstation:rent:approve')")
    @AuditLog(module="workstation", action="approveRent", description="\u5ba1\u6279\u901a\u8fc7\u5de5\u4f4d\u7533\u8bf7")
    public Result<Void> approve(@PathVariable(name="nanoId") String nanoId) {
        this.rentalRequestService.approve(nanoId);
        return Result.ok();
    }

    @PutMapping(value={"/{nanoId}/reject"})
    @PreAuthorize(value="@perm.has('workstation:rent:approve')")
    @AuditLog(module="workstation", action="rejectRent", description="\u5ba1\u6279\u62d2\u7edd\u5de5\u4f4d\u7533\u8bf7")
    public Result<Void> reject(@PathVariable(name="nanoId") String nanoId, @RequestBody(required=false) @Valid RentalRejectRequest req) {
        this.rentalRequestService.reject(nanoId, req == null ? new RentalRejectRequest() : req);
        return Result.ok();
    }

    @PutMapping(value={"/{nanoId}/cancel"})
    @PreAuthorize(value="@perm.hasAny('workstation:rent:apply','workstation:rent:approve')")
    @AuditLog(module="workstation", action="cancelRent", description="\u53d6\u6d88\u5de5\u4f4d\u7533\u8bf7")
    public Result<Void> cancel(@PathVariable(name="nanoId") String nanoId) {
        this.rentalRequestService.cancel(nanoId);
        return Result.ok();
    }

    public WorkstationRentalRequestController(WorkstationRentalRequestService rentalRequestService) {
        this.rentalRequestService = rentalRequestService;
    }
}
