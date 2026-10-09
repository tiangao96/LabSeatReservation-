package com.lab.module.workstation.controller;

import com.lab.common.api.Result;
import com.lab.common.api.ResultCode;
import com.lab.common.entity.BaseEntity;
import com.lab.common.security.MentorGroupResolver;
import com.lab.module.rbac.entity.CoreMentorGroup;
import com.lab.module.rbac.entity.CoreUser;
import com.lab.module.rbac.repository.CoreMentorGroupRepository;
import com.lab.module.rbac.repository.CoreUserRepository;
import com.lab.module.workstation.dto.StudentApplyRequest;
import com.lab.module.workstation.entity.Lab;
import com.lab.module.workstation.entity.RentalRequestStatus;
import com.lab.module.workstation.entity.Workstation;
import com.lab.module.workstation.entity.WorkstationStatus;
import com.lab.module.workstation.entity.WorkstationRentalRequest;
import com.lab.module.workstation.entity.WorkstationRentalRequestItem;
import com.lab.module.workstation.repository.LabRepository;
import com.lab.module.workstation.repository.WorkstationRentalRequestItemRepository;
import com.lab.module.workstation.repository.WorkstationRentalRequestRepository;
import com.lab.module.workstation.repository.WorkstationRepository;
import com.lab.module.workstation.service.WorkstationRentalRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生工位申请公开接口(免登录)。
 * 路径 /api/public/** 已在 SecurityConfig 放行。
 */
@Tag(name = "学生工位申请(公开)")
@RestController
@RequestMapping(value = {"/api/public"})
public class StudentApplyController {
    private final WorkstationRentalRequestService rentalRequestService;
    private final LabRepository labRepository;
    private final CoreMentorGroupRepository mentorGroupRepository;
    private final CoreUserRepository userRepository;
    private final WorkstationRepository workstationRepository;
    private final WorkstationRentalRequestRepository requestRepository;
    private final WorkstationRentalRequestItemRepository itemRepository;
    private final MentorGroupResolver mentorGroupResolver;

    @PostMapping(value = {"/apply"})
    public Result<String> apply(@RequestBody @Valid StudentApplyRequest req) {
        Long mentorGroupId = this.resolveMentorGroupId(req);
        // 导师匹配不到也允许提交(导师组留空), 由管理员在后台手动确认
        String nanoId = this.rentalRequestService.createStudentApply(req.getLabNanoId(), req.getWorkstationNanoId(), req.getStudentName(), req.getStudentNo(), req.getGradeName(), req.getMentorName(), mentorGroupId, req.getEndMonth());
        return Result.ok(nanoId);
    }

    private Long resolveMentorGroupId(StudentApplyRequest req) {
        String nanoId = req.getMentorGroupNanoId();
        if (nanoId != null && !nanoId.isBlank()) {
            Long id = this.mentorGroupResolver.resolveMentorGroupId(nanoId);
            if (id != null) {
                return id;
            }
        }
        String mentorName = req.getMentorName();
        if (mentorName == null || mentorName.isBlank()) {
            return null;
        }
        String name = mentorName.trim();
        List<CoreUser> users = this.userRepository.findByName(name);
        for (CoreUser u : users) {
            if (u.getMentorGroupId() != null) {
                return u.getMentorGroupId();
            }
        }
        for (CoreMentorGroup g : this.mentorGroupRepository.findAll()) {
            String gn = g.getName();
            if (gn != null && (gn.contains(name) || name.contains(gn.replace("\u7ec4", "")))) {
                return g.getId();
            }
        }
        return null;
    }

    @GetMapping(value = {"/labs"})
    public Result<List<Map<String, Object>>> labs() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Lab lab : this.labRepository.findAll()) {
            Map<String, Object> m = new LinkedHashMap<String, Object>();
            m.put("nanoId", lab.getNanoId());
            m.put("name", lab.getName());
            m.put("code", lab.getCode());
            m.put("area", lab.getArea());
            m.put("capacity", lab.getCapacity());
            list.add(m);
        }
        return Result.ok(list);
    }

    @GetMapping(value = {"/mentor-groups"})
    public Result<List<Map<String, Object>>> mentorGroups() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (CoreMentorGroup g : this.mentorGroupRepository.findAll()) {
            Map<String, Object> m = new LinkedHashMap<String, Object>();
            m.put("nanoId", g.getNanoId());
            m.put("name", g.getName());
            m.put("level", g.getLevel());
            list.add(m);
        }
        return Result.ok(list);
    }

    @GetMapping(value = {"/labs/{labNanoId}/workstations"})
    public Result<List<Map<String, Object>>> workstations(@PathVariable(name = "labNanoId") String labNanoId) {
        Lab lab = this.labRepository.findByNanoId(labNanoId);
        if (lab == null) {
            return Result.fail(ResultCode.BAD_REQUEST, "\u5b9e\u9a8c\u5ba4\u4e0d\u5b58\u5728: " + labNanoId);
        }
        List<Workstation> wss = this.workstationRepository.findByLabId(lab.getId());
        List<Long> wsIds = wss.stream().map(BaseEntity::getId).toList();
        Map<Long, String> occupantNameByWs = this.occupantNameByWorkstationIds(wsIds);
        Map<Long, WorkstationRentalRequest> pendingByWs = this.pendingRequestByWorkstationIds(wsIds);
        String nowYm = YearMonth.now().toString();
        List<Long> reservedIds = wsIds.isEmpty() ? new ArrayList<Long>() : this.itemRepository.findApprovedReservedWorkstationIds(wsIds, nowYm);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Workstation ws : wss) {
            Map<String, Object> m = new LinkedHashMap<String, Object>();
            WorkstationStatus st = ws.getStatus() == null ? WorkstationStatus.IDLE : ws.getStatus();
            String manual = ws.getManualOccupantName();
            boolean hasManual = manual != null && !manual.isBlank();
            boolean hasUser = ws.getCurrentUserId() != null;
            String approvedName = occupantNameByWs.get(ws.getId());
            WorkstationRentalRequest pending = pendingByWs.get(ws.getId());
            String outStatus = st.name();
            String occupantName = null;
            if (pending != null) {
                outStatus = WorkstationStatus.PRE_RESERVED.name();
                WorkstationRentalRequestService.StudentInfo pi = WorkstationRentalRequestService.parseStudentInfo(pending.getReason());
                occupantName = pi == null ? null : pi.name;
            } else if (st == WorkstationStatus.IDLE || st == WorkstationStatus.PRE_RESERVED) {
                if (hasUser || hasManual || approvedName != null || reservedIds.contains(ws.getId())) {
                    outStatus = WorkstationStatus.PRE_RESERVED.name();
                    occupantName = this.realOccupantName(ws, approvedName);
                } else {
                    outStatus = WorkstationStatus.IDLE.name();
                }
            } else {
                occupantName = this.realOccupantName(ws, approvedName);
            }
            m.put("nanoId", ws.getNanoId());
            m.put("code", ws.getCode());
            m.put("type", ws.getType() == null ? null : ws.getType().name());
            m.put("status", outStatus);
            m.put("description", ws.getDescription());
            m.put("posX", ws.getPosX());
            m.put("posY", ws.getPosY());
            m.put("width", ws.getWidth());
            m.put("height", ws.getHeight());
            m.put("zIndex", ws.getZIndex());
            m.put("rotation", ws.getRotation());
            m.put("occupantName", occupantName);
            m.put("manualOccupantName", ws.getManualOccupantName());
            m.put("occupantMentorGroupName", ws.getOccupantMentorGroupId() == null ? null : this.mentorGroupResolver.resolveName(ws.getOccupantMentorGroupId()));
            list.add(m);
        }
        return Result.ok(list);
    }

    @GetMapping(value = {"/applications"})
    public Result<List<Map<String, Object>>> applications(@RequestParam(name = "studentNo") String studentNo, @RequestParam(name = "studentName", required = false) String studentName) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (WorkstationRentalRequest r : this.requestRepository.findAll()) {
            WorkstationRentalRequestService.StudentInfo info = WorkstationRentalRequestService.parseStudentInfo(r.getReason());
            boolean matchNo = studentNo.equals(r.getNanoId());
            boolean matchStu = info != null && studentNo.equals(info.no);
            if (!matchNo && !matchStu) {
                continue;
            }
            if (studentName != null && !studentName.isBlank() && (info == null || !studentName.equals(info.name))) {
                continue;
            }
            Map<String, Object> m = new LinkedHashMap<String, Object>();
            m.put("nanoId", r.getNanoId());
            m.put("status", r.getStatus() == null ? null : r.getStatus().name());
            m.put("studentName", info == null ? "" : info.name);
            m.put("studentNo", info == null ? "" : info.no);
            m.put("gradeName", info == null ? "" : info.grade);
            m.put("mentorName", info == null ? "" : info.mentor);
            m.put("startMonth", r.getStartMonth());
            m.put("endMonth", r.getEndMonth());
            m.put("rejectReason", r.getRejectReason());
            m.put("approvedAt", r.getApprovedAt());
            m.put("sysCreateDatetime", r.getSysCreateDatetime());
            this.labRepository.findById(r.getLabId()).ifPresent(lab -> {
                m.put("labName", lab.getName());
            });
            List<WorkstationRentalRequestItem> items = this.itemRepository.findByRentalRequestId(r.getId());
            if (!items.isEmpty()) {
                this.workstationRepository.findById(items.get(0).getWorkstationId()).ifPresent(ws -> {
                    m.put("workstationCode", ws.getCode());
                });
            }
            list.add(m);
        }
        return Result.ok(list);
    }

    /**
     * 真实占用者名: 与管理员工位视图口径一致。
     * 优先级: 系统学生账号(currentUserId) > 手动占用名(manual_occupant_name) > 已通过的学生申请解析名。
     * IDLE 但存在手动占用名的工位视为已有人(不允许再申请), 同样返回该名字。
     */
    private String realOccupantName(Workstation ws, String approvedName) {
        WorkstationStatus st = ws.getStatus();
        String manual = ws.getManualOccupantName();
        if (st == WorkstationStatus.OCCUPIED || st == WorkstationStatus.PRE_RESERVED) {
            if (ws.getCurrentUserId() != null) {
                String n = this.userRepository.findById(ws.getCurrentUserId()).map(CoreUser::getName).orElse(null);
                if (n != null && !n.isBlank()) {
                    return n;
                }
            }
            if (manual != null && !manual.isBlank()) {
                return manual;
            }
            return approvedName;
        }
        if (st == WorkstationStatus.IDLE && manual != null && !manual.isBlank()) {
            return manual;
        }
        return null;
    }

    private Map<Long, WorkstationRentalRequest> pendingRequestByWorkstationIds(Collection<Long> wsIds) {
        Map<Long, WorkstationRentalRequest> map = new LinkedHashMap<Long, WorkstationRentalRequest>();
        if (wsIds == null || wsIds.isEmpty()) {
            return map;
        }
        List<WorkstationRentalRequestItem> items = this.itemRepository.findByWorkstationIdIn(wsIds);
        if (items.isEmpty()) {
            return map;
        }
        List<Long> requestIds = items.stream().map(WorkstationRentalRequestItem::getRentalRequestId).distinct().collect(Collectors.toList());
        Map<Long, WorkstationRentalRequest> reqById = this.requestRepository.findAllById(requestIds).stream().collect(Collectors.toMap(BaseEntity::getId, r -> r, (a, b) -> a));
        for (WorkstationRentalRequestItem item : items) {
            WorkstationRentalRequest r = reqById.get(item.getRentalRequestId());
            if (r != null && r.getStatus() == RentalRequestStatus.PENDING && !map.containsKey(item.getWorkstationId())) {
                map.put(item.getWorkstationId(), r);
            }
        }
        return map;
    }

    private Map<Long, String> occupantNameByWorkstationIds(Collection<Long> wsIds) {
        Map<Long, String> map = new LinkedHashMap<Long, String>();
        if (wsIds == null || wsIds.isEmpty()) {
            return map;
        }
        List<WorkstationRentalRequestItem> items = this.itemRepository.findByWorkstationIdIn(wsIds);
        if (items.isEmpty()) {
            return map;
        }
        List<Long> requestIds = items.stream().map(WorkstationRentalRequestItem::getRentalRequestId).distinct().collect(Collectors.toList());
        Map<Long, WorkstationRentalRequest> reqById = this.requestRepository.findAllById(requestIds).stream().collect(Collectors.toMap(BaseEntity::getId, r -> r, (a, b) -> a));
        Map<Long, WorkstationRentalRequest> approvedByWs = new LinkedHashMap<Long, WorkstationRentalRequest>();
        for (WorkstationRentalRequestItem item : items) {
            WorkstationRentalRequest r = reqById.get(item.getRentalRequestId());
            if (r == null || r.getStatus() != RentalRequestStatus.APPROVED) {
                continue;
            }
            approvedByWs.put(item.getWorkstationId(), r);
        }
        for (Map.Entry<Long, WorkstationRentalRequest> e : approvedByWs.entrySet()) {
            WorkstationRentalRequestService.StudentInfo info = WorkstationRentalRequestService.parseStudentInfo(e.getValue().getReason());
            if (info != null && info.name != null && !info.name.isBlank()) {
                map.put(e.getKey(), info.name);
            }
        }
        return map;
    }

    public StudentApplyController(WorkstationRentalRequestService rentalRequestService, LabRepository labRepository, CoreMentorGroupRepository mentorGroupRepository, CoreUserRepository userRepository, WorkstationRepository workstationRepository, WorkstationRentalRequestRepository requestRepository, WorkstationRentalRequestItemRepository itemRepository, MentorGroupResolver mentorGroupResolver) {
        this.rentalRequestService = rentalRequestService;
        this.labRepository = labRepository;
        this.mentorGroupRepository = mentorGroupRepository;
        this.userRepository = userRepository;
        this.workstationRepository = workstationRepository;
        this.requestRepository = requestRepository;
        this.itemRepository = itemRepository;
        this.mentorGroupResolver = mentorGroupResolver;
    }
}
