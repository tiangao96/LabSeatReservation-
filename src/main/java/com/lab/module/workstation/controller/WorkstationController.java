package com.lab.module.workstation.controller;

import com.lab.common.api.Result;
import com.lab.common.api.ResultCode;
import com.lab.common.audit.AuditLog;
import com.lab.common.exception.BizException;
import com.lab.module.rbac.entity.CoreUser;
import com.lab.module.rbac.repository.CoreUserRepository;
import com.lab.module.workstation.entity.Workstation;
import com.lab.module.workstation.entity.WorkstationRentalRequest;
import com.lab.module.workstation.entity.WorkstationRentalRequestItem;
import com.lab.module.workstation.entity.WorkstationStatus;
import com.lab.module.workstation.repository.WorkstationRepository;
import com.lab.module.workstation.repository.WorkstationRentalRequestItemRepository;
import com.lab.module.workstation.repository.WorkstationRentalRequestRepository;
import com.lab.module.workstation.dto.MentorGroupStudentUsageVo;
import com.lab.module.workstation.dto.MentorGroupUpdateRequest;
import com.lab.module.workstation.dto.OccupancyHistoryVo;
import com.lab.module.workstation.dto.ReleaseRequest;
import com.lab.module.workstation.dto.RentalRequestCreateRequest;
import com.lab.module.workstation.dto.SeatCandidateVo;
import com.lab.module.workstation.dto.SeatRequest;
import com.lab.module.workstation.dto.WorkstationCreateRequest;
import com.lab.module.workstation.dto.WorkstationStatusChangeRequest;
import com.lab.module.workstation.dto.WorkstationVo;
import com.lab.module.workstation.service.WorkstationRentalRequestService;
import com.lab.module.workstation.service.WorkstationMentorService;
import com.lab.module.workstation.service.WorkstationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.YearMonth;
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

@Tag(name="\u5de5\u4f4d")
@RestController
@RequestMapping(value={"/api/workstations"})
public class WorkstationController {
    private final WorkstationService workstationService;
    private final WorkstationRentalRequestService rentalRequestService;
    private final WorkstationMentorService workstationMentorService;
    private final WorkstationRepository workstationRepository;
    private final CoreUserRepository coreUserRepository;
    private final WorkstationRentalRequestItemRepository rentalItemRepository;
    private final WorkstationRentalRequestRepository rentalRequestRepository;

    @GetMapping
    @PreAuthorize(value="@perm.has('workstation:view')")
    public Result<List<WorkstationVo>> list(@RequestParam("labNanoId") String labNanoId, @RequestParam(value="month", required=false) String month) {
        return Result.ok(this.workstationService.listByLab(labNanoId, month));
    }

    @GetMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('workstation:view')")
    public Result<WorkstationVo> get(@PathVariable("nanoId") String nanoId) {
        return Result.ok(this.workstationService.getByNanoId(nanoId));
    }

    @GetMapping(value={"/rent-candidates"})
    @PreAuthorize(value="@perm.hasAny('workstation:rent:apply','workstation:rent:approve')")
    public Result<List<WorkstationVo>> rentCandidates(@RequestParam("labNanoId") String labNanoId, @RequestParam("startMonth") String startMonth, @RequestParam("endMonth") String endMonth) {
        return Result.ok(this.workstationService.listRentCandidates(labNanoId, startMonth, endMonth));
    }

    @PostMapping
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="workstation", action="create", description="\u65b0\u589e\u5de5\u4f4d")
    public Result<String> create(@RequestBody @Valid WorkstationCreateRequest req) {
        return Result.ok(this.workstationService.create(req));
    }

    @PutMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="workstation", action="update", description="\u4fee\u6539\u5de5\u4f4d")
    public Result<Void> update(@PathVariable("nanoId") String nanoId, @RequestBody @Valid WorkstationCreateRequest req) {
        this.workstationService.update(nanoId, req);
        return Result.ok();
    }

    @DeleteMapping(value={"/{nanoId}"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="workstation", action="delete", description="\u5220\u9664\u5de5\u4f4d")
    public Result<Void> delete(@PathVariable("nanoId") String nanoId) {
        this.workstationService.delete(nanoId);
        return Result.ok();
    }

    @PostMapping(value={"/{labNanoId}/rent-request"})
    @PreAuthorize(value="@perm.hasAny('workstation:rent:apply','workstation:rent:approve')")
    @AuditLog(module="workstation", action="applyRent", description="\u7533\u8bf7\u5de5\u4f4d")
    public Result<String> applyRent(@PathVariable("labNanoId") String labNanoId, @RequestBody @Valid RentalRequestCreateRequest req) {
        return Result.ok(this.rentalRequestService.create(labNanoId, req));
    }

    @PutMapping(value={"/{nanoId}/release"})
    @PreAuthorize(value="@perm.has('workstation:release')")
    @AuditLog(module="workstation", action="release", description="\u91ca\u653e\u5de5\u4f4d")
    public Result<Void> release(@PathVariable("nanoId") String nanoId, @RequestBody(required=false) ReleaseRequest req) {
        this.workstationService.release(nanoId, req != null ? req.getEndMonth() : null);
        return Result.ok();
    }

    @PostMapping(value={"/{nanoId}/seat"})
    @PreAuthorize(value="@perm.has('workstation:seat')")
    @AuditLog(module="workstation", action="seat", description="\u5b66\u751f\u5360\u9886\u5de5\u4f4d")
    public Result<Void> seat(@PathVariable("nanoId") String nanoId, @RequestBody @Valid SeatRequest req) {
        this.workstationService.seat(nanoId, req.getUserNanoId(), req.getStartMonth());
        return Result.ok();
    }

    @GetMapping(value={"/{nanoId}/seat-candidates"})
    @PreAuthorize(value="@perm.has('workstation:seat')")
    public Result<List<SeatCandidateVo>> seatCandidates(@PathVariable("nanoId") String nanoId) {
        return Result.ok(this.workstationService.seatCandidates(nanoId));
    }

    @PutMapping(value={"/{nanoId}/status"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="workstation", action="changeStatus", description="\u5207\u6362\u5de5\u4f4d\u72b6\u6001")
    public Result<Void> changeStatus(@PathVariable("nanoId") String nanoId, @RequestBody @Valid WorkstationStatusChangeRequest req) {
        this.workstationService.changeStatus(nanoId, req);
        return Result.ok();
    }

    @PutMapping(value={"/{nanoId}/mentor-group"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="workstation", action="updateMentorGroup", description="\u4fee\u6539\u5de5\u4f4d\u5f52\u5c5e\u5bfc\u5e08")
    public Result<Void> updateMentorGroup(@PathVariable("nanoId") String nanoId, @RequestBody(required=false) MentorGroupUpdateRequest req) {
        this.workstationMentorService.setMentorGroup(nanoId, req != null ? req.getMentorGroupNanoId() : null, req != null ? req.getMentorGroupName() : null);
        return Result.ok();
    }

    @PutMapping(value={"/{nanoId}/occupant-name"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="workstation", action="updateOccupantName", description="\u4fee\u6539\u5de5\u4f4d\u5360\u7528\u5b66\u751f\u59d3\u540d")
    public Result<Void> updateOccupantName(@PathVariable("nanoId") String nanoId, @RequestParam("name") String name) {
        Workstation ws = this.workstationRepository.findByNanoId(nanoId);
        if (ws == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (ws.getCurrentUserId() == null) {
            String mn = (name == null || name.trim().isEmpty()) ? null : name.trim();
            ws.setManualOccupantName(mn);
            if (mn != null && !mn.isEmpty()) {
                // 手动填写使用人姓名(学生/导师) => 工位标为占用中
                if (ws.getStatus() == WorkstationStatus.IDLE || ws.getStatus() == WorkstationStatus.PRE_RESERVED) {
                    ws.setStatus(WorkstationStatus.OCCUPIED);
                    ws.setOccupiedAt(LocalDateTime.now());
                }
            } else if (ws.getStatus() == WorkstationStatus.OCCUPIED || ws.getStatus() == WorkstationStatus.PRE_RESERVED) {
                // 清空姓名 => 若仍绑定导师组则回到预占(位子仍归导师),否则回到空闲
                ws.setStatus(ws.getOccupantMentorGroupId() != null ? WorkstationStatus.PRE_RESERVED : WorkstationStatus.IDLE);
                ws.setOccupiedAt(null);
            }
            this.workstationRepository.save(ws);
            return Result.ok();
        }
        if (name == null || name.trim().isEmpty()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5b66\u751f\u59d3\u540d\u4e0d\u80fd\u4e3a\u7a7a");
        }
        CoreUser user = this.coreUserRepository.findById(ws.getCurrentUserId()).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5360\u7528\u5b66\u751f\u4e0d\u5b58\u5728"));
        user.setName(name.trim());
        this.coreUserRepository.save(user);
        return Result.ok();
    }

    @PutMapping(value={"/{nanoId}/reserved-end-month"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="workstation", action="updateReservedEndMonth", description="\u4fee\u6539\u5de5\u4f4d\u9884\u8ba1\u91ca\u653e\u6708\u4efd")
    public Result<Void> updateReservedEndMonth(@PathVariable("nanoId") String nanoId, @RequestParam("month") String month) {
        Workstation ws = this.workstationRepository.findByNanoId(nanoId);
        if (ws == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (month == null || !month.matches("\\d{4}-\\d{2}")) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u6708\u4efd\u683c\u5f0f\u5e94\u4e3a YYYY-MM");
        }
        String m = month.trim();
        // 1) workstation expected_release_month (manual occupied w/o rental also allowed)
        ws.setExpectedReleaseMonth(m);
        this.workstationRepository.save(ws);
        // 2) sync approved rental item end; single-workstation rental also syncs request end (auto-expire basis)
        Long rentalId = this.rentalItemRepository.findApprovedRentalRequestIdByWorkstationAndMonth(ws.getId(), YearMonth.now().toString());
        if (rentalId != null) {
            List<WorkstationRentalRequestItem> items = this.rentalItemRepository.findByRentalRequestId(rentalId);
            WorkstationRentalRequestItem target = null;
            for (WorkstationRentalRequestItem it : items) {
                if (it.getWorkstationId() != null && it.getWorkstationId().equals(ws.getId())) {
                    target = it;
                    break;
                }
            }
            if (target != null) {
                target.setEndMonth(m);
                this.rentalItemRepository.save(target);
                if (items.size() == 1) {
                    this.rentalRequestRepository.findById(rentalId).ifPresent(req -> {
                        req.setEndMonth(m);
                        this.rentalRequestRepository.save(req);
                    });
                }
            }
        }
        return Result.ok();
    }

    @PutMapping(value={"/{nanoId}/grade-info"})
    @PreAuthorize(value="@perm.has('workstation:manage')")
    @AuditLog(module="workstation", action="updateGradeInfo", description="\u4fee\u6539\u5de5\u4f4d\u5e74\u7ea7\u4e0e\u5b66\u5386")
    public Result<Void> updateGradeInfo(@PathVariable("nanoId") String nanoId, @RequestParam(value="gradeName", required=false) String gradeName, @RequestParam(value="degree", required=false) String degree) {
        this.workstationService.updateGradeInfo(nanoId, gradeName, degree);
        return Result.ok();
    }

    @GetMapping(value={"/mentor-group-student-usage"})
    @PreAuthorize(value="@perm.has('workstation:usage:view')")
    public Result<MentorGroupStudentUsageVo> mentorGroupStudentUsage(@RequestParam("mentorGroupNanoId") String mentorGroupNanoId, @RequestParam("startMonth") String startMonth, @RequestParam("endMonth") String endMonth) {
        return Result.ok(this.rentalRequestService.mentorGroupStudentUsage(mentorGroupNanoId, startMonth, endMonth));
    }

    @GetMapping(value={"/{nanoId}/occupancy-history"})
    @PreAuthorize(value="@perm.has('workstation:history:view')")
    public Result<List<OccupancyHistoryVo>> occupancyHistory(@PathVariable("nanoId") String nanoId) {
        return Result.ok(this.workstationService.occupancyHistory(nanoId));
    }

        public WorkstationController(WorkstationService workstationService, WorkstationRentalRequestService rentalRequestService, WorkstationMentorService workstationMentorService, WorkstationRepository workstationRepository, CoreUserRepository coreUserRepository, WorkstationRentalRequestItemRepository rentalItemRepository, WorkstationRentalRequestRepository rentalRequestRepository) {
        this.workstationService = workstationService;
        this.rentalRequestService = rentalRequestService;
        this.workstationMentorService = workstationMentorService;
        this.workstationRepository = workstationRepository;
        this.coreUserRepository = coreUserRepository;
        this.rentalItemRepository = rentalItemRepository;
        this.rentalRequestRepository = rentalRequestRepository;
    }
}

