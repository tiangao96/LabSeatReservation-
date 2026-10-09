package com.lab.module.workstation.service;

import com.lab.common.api.ResultCode;
import com.lab.common.entity.BaseEntity;
import com.lab.common.exception.BizException;
import com.lab.common.security.MentorGroupResolver;
import com.lab.common.security.PermissionService;
import com.lab.common.security.SecurityContextUtil;
import com.lab.common.security.UserIdResolver;
import com.lab.module.workstation.dto.MentorGroupStudentUsageVo;
import com.lab.module.workstation.dto.RentalItemVo;
import com.lab.module.workstation.dto.RentalRejectRequest;
import com.lab.module.workstation.dto.RentalRequestCreateRequest;
import com.lab.module.workstation.dto.RentalRequestVo;
import com.lab.module.workstation.dto.StudentUsageItemVo;
import com.lab.module.workstation.entity.Lab;
import com.lab.module.workstation.entity.RentalRequestStatus;
import com.lab.module.workstation.entity.OccupancySourceType;
import com.lab.module.workstation.entity.OccupancyType;
import com.lab.module.workstation.entity.Workstation;
import com.lab.module.workstation.entity.WorkstationOccupancyHistory;
import com.lab.module.workstation.entity.WorkstationRentalRequest;
import com.lab.module.workstation.entity.WorkstationRentalRequestItem;
import com.lab.module.workstation.entity.WorkstationStatus;
import com.lab.module.workstation.entity.WorkstationType;
import com.lab.module.workstation.repository.LabRepository;
import com.lab.module.workstation.repository.WorkstationOccupancyHistoryRepository;
import com.lab.module.workstation.repository.WorkstationRentalRequestItemRepository;
import com.lab.module.workstation.repository.WorkstationRentalRequestRepository;
import com.lab.module.workstation.repository.WorkstationRepository;
import com.lab.module.workstation.service.RentalCalculator;
import com.lab.module.workstation.service.WorkstationRentalRequestService;
import com.lab.module.workstation.service.WorkstationService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Exception performing whole class analysis ignored.
 */
@Service
public class WorkstationRentalRequestService {
    private final WorkstationRentalRequestRepository requestRepository;
    private final WorkstationRentalRequestItemRepository itemRepository;
    private final WorkstationOccupancyHistoryRepository historyRepository;
    private final WorkstationRepository workstationRepository;
    private final LabRepository labRepository;
    private final UserIdResolver userIdResolver;
    private final MentorGroupResolver mentorGroupResolver;
    private final PermissionService permissionService;
    private final WorkstationService workstationService;
    private final WorkstationOccupancyHistoryService historyService;

    @Transactional
    public String create(String labNanoId, RentalRequestCreateRequest req) {
        Lab lab = this.requireLab(labNanoId != null ? labNanoId : req.getLabNanoId());
        BigDecimal workstationArea = RentalCalculator.workstationArea((BigDecimal)lab.getArea(), (Integer)lab.getCapacity());
        String mgNanoId = req.getMentorGroupNanoId();
        Long mentorGroupId = null;
        if (mgNanoId != null && !mgNanoId.isBlank()) {
            mentorGroupId = this.mentorGroupResolver.resolveMentorGroupId(mgNanoId);
            if (mentorGroupId == null) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u7533\u8bf7\u5bfc\u5e08\u7ec4\u4e0d\u5b58\u5728: " + mgNanoId);
            }
        }
        RentalCalculator.parseMonth((String)req.getStartMonth());
        RentalCalculator.parseMonth((String)req.getEndMonth());
        if (req.getStartMonth().compareTo(req.getEndMonth()) > 0) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7ed3\u675f\u6708\u4e0d\u80fd\u65e9\u4e8e\u8d77\u59cb\u6708");
        }
        List<Workstation> workstations = this.validateWorkstations(req.getWorkstationNanoIds(), lab.getId(), req.getStartMonth());
        List<Long> wsIds = workstations.stream().map(BaseEntity::getId).toList();
        if (this.itemRepository.countApprovedConflicts(wsIds, req.getStartMonth(), req.getEndMonth()) > 0L) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u6240\u9009\u5de5\u4f4d\u5728\u6307\u5b9a\u6708\u4efd\u5185\u5df2\u6709\u5ba1\u6279\u901a\u8fc7\u7684\u5de5\u4f4d\u7533\u8bf7\uff0c\u5b58\u5728\u51b2\u7a81");
        }
        int monthCount = RentalCalculator.calcMonthCount((String)req.getStartMonth(), (String)req.getEndMonth(), (int)workstations.size());
        WorkstationRentalRequest r = new WorkstationRentalRequest();
        r.setRequesterMentorGroupId(mentorGroupId);
        r.setLabId(lab.getId());
        r.setStartMonth(req.getStartMonth());
        r.setEndMonth(req.getEndMonth());
        r.setWorkstationArea(workstationArea);
        r.setMonthCount(Integer.valueOf(monthCount));
        r.setWorkstationCount(Integer.valueOf(workstations.size()));
        r.setStatus(RentalRequestStatus.PENDING);
        r.setReason(req.getReason());
        r = (WorkstationRentalRequest)this.requestRepository.save(r);
        for (Workstation ws : workstations) {
            WorkstationRentalRequestItem item = new WorkstationRentalRequestItem();
            item.setRentalRequestId(r.getId());
            item.setWorkstationId(ws.getId());
            this.itemRepository.save(item);
        }
        if (this.permissionService.has("workstation:rent:approve")) {
            r.setStatus(RentalRequestStatus.APPROVED);
            r.setApproverUserId(SecurityContextUtil.currentUserId());
            r.setApprovedAt(LocalDateTime.now());
            this.requestRepository.save(r);
            for (Workstation ws : workstations) {
                this.workstationService.doReserve(ws, r.getId(), r.getRequesterMentorGroupId());
            }
        }
        return r.getNanoId();
    }

    @Transactional
    public void approve(String requestNanoId) {
        WorkstationRentalRequest r = this.requireRequest(requestNanoId);
        if (!r.getStatus().isPending()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7533\u8bf7\u4e0d\u5728\u5f85\u5ba1\u6279\u72b6\u6001: " + String.valueOf(r.getStatus()));
        }
        List<WorkstationRentalRequestItem> items = this.itemRepository.findByRentalRequestId(r.getId());
        List<Long> wsIds = items.stream().map(WorkstationRentalRequestItem::getWorkstationId).toList();
        if (this.itemRepository.countApprovedConflictsExcluding(r.getId(), wsIds, r.getStartMonth(), r.getEndMonth()) > 0L) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u6240\u9009\u5de5\u4f4d\u5728\u6307\u5b9a\u6708\u4efd\u5185\u5df2\u6709\u5176\u4ed6\u5ba1\u6279\u901a\u8fc7\u7684\u6708\u79df\u7533\u8bf7\uff0c\u5b58\u5728\u51b2\u7a81");
        }
        r.setStatus(RentalRequestStatus.APPROVED);
        r.setApproverUserId(SecurityContextUtil.currentUserId());
        r.setApprovedAt(LocalDateTime.now());
        this.requestRepository.save(r);
        WorkstationRentalRequestService.StudentInfo studentInfo = WorkstationRentalRequestService.parseStudentInfo(r.getReason());
        if (studentInfo != null) {
            for (WorkstationRentalRequestItem item : items) {
                Workstation ws = this.workstationRepository.findById(item.getWorkstationId()).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5de5\u4f4d\u4e0d\u5b58\u5728"));
                boolean occByStudent = ws.getCurrentUserId() != null || (ws.getManualOccupantName() != null && !ws.getManualOccupantName().isBlank());
                if (ws.getStatus() != WorkstationStatus.IDLE && !(ws.getStatus() == WorkstationStatus.PRE_RESERVED && !occByStudent)) {
                    throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u5f53\u524d\u4e0d\u53ef\u5360\u7528: " + ws.getCode());
                }
                ws.setStatus(WorkstationStatus.OCCUPIED);
                ws.setOccupantMentorGroupId(r.getRequesterMentorGroupId());
                if (studentInfo.name != null && !studentInfo.name.isBlank()) {
                    ws.setManualOccupantName(studentInfo.name);
                }
                this.workstationRepository.save(ws);
                this.historyService.transitionTo(ws.getId(), OccupancyType.OCCUPIED, OccupancySourceType.RENTAL, r.getId(), r.getRequesterMentorGroupId(), null, LocalDateTime.now());
            }
        } else {
            for (WorkstationRentalRequestItem item : items) {
                Workstation ws = this.workstationRepository.findById(item.getWorkstationId()).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5de5\u4f4d\u4e0d\u5b58\u5728"));
                this.workstationService.doReserve(ws, r.getId(), r.getRequesterMentorGroupId());
            }
        }
    }

    @Transactional
    public void reject(String requestNanoId, RentalRejectRequest req) {
        WorkstationRentalRequest r = this.requireRequest(requestNanoId);
        if (!r.getStatus().isPending()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7533\u8bf7\u4e0d\u5728\u5f85\u5ba1\u6279\u72b6\u6001: " + String.valueOf(r.getStatus()));
        }
        r.setStatus(RentalRequestStatus.REJECTED);
        r.setApproverUserId(SecurityContextUtil.currentUserId());
        r.setApprovedAt(LocalDateTime.now());
        r.setRejectReason(req == null ? null : req.getRejectReason());
        this.requestRepository.save(r);
    }

    @Transactional
    public void cancel(String requestNanoId) {
        WorkstationRentalRequest r = this.requireRequest(requestNanoId);
        if (r.getStatus() != RentalRequestStatus.PENDING && r.getStatus() != RentalRequestStatus.APPROVED) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7533\u8bf7\u4e0d\u53ef\u53d6\u6d88: " + String.valueOf(r.getStatus()));
        }
        if (r.getStatus() == RentalRequestStatus.PENDING) {
            boolean isSameGroup;
            Long userId = SecurityContextUtil.currentUserId();
            Long mentorGroupId = SecurityContextUtil.currentMentorGroupId();
            boolean isSubmitter = userId != null && userId.equals(r.getSysCreatorId());
            boolean bl = isSameGroup = mentorGroupId != null && mentorGroupId.equals(r.getRequesterMentorGroupId());
            if (!isSubmitter && !isSameGroup) {
                throw new BizException(ResultCode.NO_PERMISSION, "\u4ec5\u63d0\u4ea4\u4eba\u6216\u540c\u5bfc\u5e08\u7ec4\u6210\u5458\u53ef\u53d6\u6d88");
            }
        } else {
            if (!this.permissionService.has("workstation:rent:approve")) {
                throw new BizException(ResultCode.NO_PERMISSION, "\u5df2\u901a\u8fc7\u7684\u7533\u8bf7\u4ec5\u7ba1\u7406\u5458\u53ef\u53d6\u6d88");
            }
            List<WorkstationRentalRequestItem> items = this.itemRepository.findByRentalRequestId(r.getId());
            WorkstationRentalRequestService.StudentInfo studentInfo = WorkstationRentalRequestService.parseStudentInfo(r.getReason());
            if (studentInfo != null) {
                for (WorkstationRentalRequestItem item : items) {
                    Workstation ws = this.workstationRepository.findById(item.getWorkstationId()).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5de5\u4f4d\u4e0d\u5b58\u5728"));
                    WorkstationOccupancyHistoryService.OpenRentalInfo open = this.historyService.openRentalInfo(ws.getId());
                    if (open != null && r.getId().equals(open.rentalId())) {
                        this.historyService.closeCurrentAt(ws.getId(), LocalDateTime.now());
                    }
                    ws.setStatus(WorkstationStatus.IDLE);
                    ws.setOccupantMentorGroupId(null);
                    ws.setManualOccupantName(null);
                    this.workstationRepository.save(ws);
                }
            } else {
                for (WorkstationRentalRequestItem item : items) {
                    Workstation ws = this.workstationRepository.findById(item.getWorkstationId()).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5de5\u4f4d\u4e0d\u5b58\u5728"));
                    this.workstationService.cancelReserve(ws, r.getId());
                }
            }
        }
        r.setStatus(RentalRequestStatus.CANCELLED);
        this.requestRepository.save(r);
    }

    public List<RentalRequestVo> list(boolean mine, String status, String mentorGroupNanoId, String labNanoId) {
        RentalRequestStatus statusEnum = this.parseStatus(status);
        Long mentorGroupId = null;
        if (mine) {
            mentorGroupId = SecurityContextUtil.currentMentorGroupId();
        } else if (mentorGroupNanoId != null && !mentorGroupNanoId.isBlank() && (mentorGroupId = this.mentorGroupResolver.resolveMentorGroupId(mentorGroupNanoId)) == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5bfc\u5e08\u7ec4\u4e0d\u5b58\u5728: " + mentorGroupNanoId);
        }
        Long labId = null;
        if (labNanoId != null && !labNanoId.isBlank()) {
            Lab lab = this.labRepository.findByNanoId(labNanoId);
            if (lab == null) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u5b9e\u9a8c\u5ba4\u4e0d\u5b58\u5728: " + labNanoId);
            }
            labId = lab.getId();
        }
        List<WorkstationRentalRequest> list = this.requestRepository.findWithFilters(statusEnum, mentorGroupId, labId);
        return list.stream().map(this::toVo).toList();
    }

    public RentalRequestVo getByNanoId(String nanoId) {
        return this.toVo(this.requireRequest(nanoId));
    }

    public MentorGroupStudentUsageVo mentorGroupStudentUsage(String mentorGroupNanoId, String startMonth, String endMonth) {
        Long mentorGroupId = this.mentorGroupResolver.resolveMentorGroupId(mentorGroupNanoId);
        if (mentorGroupId == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5bfc\u5e08\u7ec4\u4e0d\u5b58\u5728: " + mentorGroupNanoId);
        }
        RentalCalculator.parseMonth((String)startMonth);
        RentalCalculator.parseMonth((String)endMonth);
        if (startMonth.compareTo(endMonth) > 0) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7ed3\u675f\u6708\u4e0d\u80fd\u65e9\u4e8e\u8d77\u59cb\u6708");
        }
        LocalDateTime winStart = YearMonth.parse(startMonth).atDay(1).atStartOfDay();
        LocalDateTime winEnd = YearMonth.parse(endMonth).atEndOfMonth().atTime(23, 59, 59);
        LocalDateTime now = LocalDateTime.now();
        List<WorkstationOccupancyHistory> segments = this.historyRepository.findOccupiedByMentorGroupAndMonthRange(mentorGroupId, startMonth, endMonth);
        LinkedHashMap<Long, LinkedHashMap<Long, ItemAgg>> agg = new LinkedHashMap<Long, LinkedHashMap<Long, ItemAgg>>();
        HashSet<Long> studentIds = new HashSet<Long>();
        for (WorkstationOccupancyHistory h : segments) {
            List<Object[]> rawWs;
            LocalDateTime realStart = h.getOccupiedAt();
            LocalDateTime realEnd;
            if (h.getReleasedAt() == null) {
                LocalDateTime rentalEnd = WorkstationRentalRequestService.resolveOpenRentalEnd(this.requestRepository, h);
                LocalDateTime monthCap = now.toLocalDate().withDayOfMonth(1).plusMonths(1).atStartOfDay().minusSeconds(1);
                realEnd = rentalEnd != null ? (rentalEnd.isAfter(monthCap) ? monthCap : rentalEnd) : now;
            } else {
                realEnd = h.getReleasedAt();
            }
            LocalDateTime effStart = realStart.isBefore(winStart) ? winStart : realStart;
            LocalDateTime winClippedEnd = realEnd.isAfter(winEnd) ? winEnd : realEnd;
            if (effStart.isAfter(winClippedEnd) || (rawWs = this.workstationRepository.findWithLabRaw(h.getWorkstationId())).isEmpty()) continue;
            Object[] r = rawWs.get(0);
            Long wsId = ((Number)r[0]).longValue();
            String wsNanoId = (String)r[1];
            String wsCode = (String)r[2];
            String labNanoId = r[4] == null ? null : (String)r[4];
            String labName = r[5] == null ? null : (String)r[5];
            BigDecimal area = WorkstationRentalRequestService.resolveRawArea((Object)r[6], (Object)r[7]);
            studentIds.add(h.getOccupantUserId());
            ItemAgg it = agg.computeIfAbsent(h.getOccupantUserId(), k -> new LinkedHashMap<Long, ItemAgg>()).computeIfAbsent(wsId, k -> new ItemAgg());
            YearMonth s = YearMonth.from(realStart);
            YearMonth e = YearMonth.from(realEnd);
            YearMonth ym = s;
            while (!ym.isAfter(e)) {
                it.months.add(ym);
                ym = ym.plusMonths(1L);
            }
            it.wsNanoId = wsNanoId;
            it.wsCode = wsCode;
            it.labNanoId = labNanoId;
            it.labName = labName;
            it.area = area;
        }
        ArrayList<StudentUsageItemVo> items = new ArrayList<StudentUsageItemVo>();
        int totalMonths = 0;
        BigDecimal totalArea = BigDecimal.ZERO;
        HashSet<Long> wsIds = new HashSet<Long>();
        for (Map.Entry<Long, LinkedHashMap<Long, ItemAgg>> se : agg.entrySet()) {
            for (Map.Entry<Long, ItemAgg> we : se.getValue().entrySet()) {
                ItemAgg a = we.getValue();
                StudentUsageItemVo vo = new StudentUsageItemVo();
                vo.setStudentUserNanoId(this.userIdResolver.resolveNanoId((Long)se.getKey()));
                vo.setStudentUserName(this.userIdResolver.resolveName((Long)se.getKey()));
                vo.setWorkstationNanoId(a.wsNanoId);
                vo.setWorkstationCode(a.wsCode);
                vo.setLabNanoId(a.labNanoId);
                vo.setLabName(a.labName);
                vo.setStartMonth(((YearMonth)a.months.first()).toString());
                vo.setEndMonth(((YearMonth)a.months.last()).toString());
                vo.setDurationMonths(Integer.valueOf(a.months.size()));
                vo.setWorkstationArea(a.area);
                BigDecimal areaMonths = a.area == null ? BigDecimal.ZERO : a.area.multiply(BigDecimal.valueOf(a.months.size()));
                vo.setAreaMonths(areaMonths);
                items.add(vo);
                totalMonths += a.months.size();
                totalArea = totalArea.add(areaMonths);
                wsIds.add((Long)we.getKey());
            }
        }
        MentorGroupStudentUsageVo vo = new MentorGroupStudentUsageVo();
        vo.setMentorGroupNanoId(mentorGroupNanoId);
        vo.setMentorGroupName(this.mentorGroupResolver.resolveName(mentorGroupId));
        vo.setStartMonth(startMonth);
        vo.setEndMonth(endMonth);
        vo.setDistinctStudentCount(Integer.valueOf(studentIds.size()));
        vo.setDistinctWorkstationCount(Integer.valueOf(wsIds.size()));
        vo.setTotalWorkstationMonths(Integer.valueOf(totalMonths));
        vo.setTotalAreaMonths(totalArea);
        vo.setItems(items);
        return vo;
    }

    private static BigDecimal resolveRawArea(Object areaObj, Object capacityObj) {
        Number cap;
        BigDecimal area;
        block3: {
            block2: {
                if (!(areaObj instanceof BigDecimal)) break block2;
                area = (BigDecimal)areaObj;
                if (!(capacityObj instanceof Number)) break block2;
                cap = (Number)capacityObj;
                if (area.signum() > 0 && cap.intValue() > 0) break block3;
            }
            return null;
        }
        return RentalCalculator.workstationArea((BigDecimal)area, (Integer)cap.intValue());
    }

    private RentalRequestStatus parseStatus(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        for (RentalRequestStatus s : RentalRequestStatus.values()) {
            if (!s.name().equalsIgnoreCase(raw)) continue;
            return s;
        }
        throw new BizException(ResultCode.BAD_REQUEST, "\u975e\u6cd5\u7533\u8bf7\u72b6\u6001: " + raw);
    }

    private WorkstationRentalRequest requireRequest(String nanoId) {
        WorkstationRentalRequest r = this.requestRepository.findByNanoId(nanoId);
        if (r == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u6708\u79df\u7533\u8bf7\u4e0d\u5b58\u5728");
        }
        return r;
    }

    private Lab requireLab(String nanoId) {
        if (nanoId == null || nanoId.isBlank()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5b9e\u9a8c\u5ba4\u4e0d\u80fd\u4e3a\u7a7a");
        }
        Lab lab = this.labRepository.findByNanoId(nanoId);
        if (lab == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5b9e\u9a8c\u5ba4\u4e0d\u5b58\u5728: " + nanoId);
        }
        return lab;
    }

    private List<Workstation> validateWorkstations(List<String> workstationNanoIds, Long labId, String startMonth) {
        if (workstationNanoIds == null || workstationNanoIds.isEmpty()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u5de5\u4f4d");
        }
        HashSet<String> seen = new HashSet<String>();
        ArrayList<Workstation> result = new ArrayList<Workstation>();
        for (String nanoId : workstationNanoIds) {
            if (!seen.add(nanoId)) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u91cd\u590d: " + nanoId);
            }
            Workstation ws = this.workstationRepository.findByNanoId(nanoId);
            if (ws == null) {
                throw new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5de5\u4f4d\u4e0d\u5b58\u5728: " + nanoId);
            }
            if (!labId.equals(ws.getLabId())) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u4e0d\u5c5e\u4e8e\u8be5\u5b9e\u9a8c\u5ba4: " + nanoId);
            }
            if (ws.getType() != WorkstationType.SEAT) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u975e\u5de5\u4f4d\u7c7b\u578b\u4e0d\u53ef\u6708\u79df: " + String.valueOf(ws.getType()));
            }
            String reason = WorkstationService.unrentableReason((Workstation)ws, (boolean)false, (String)startMonth);
            if (reason != null) {
                throw new BizException(ResultCode.BAD_REQUEST, reason + ": " + ws.getCode());
            }
            result.add(ws);
        }
        return result;
    }

    private RentalRequestVo toVo(WorkstationRentalRequest r) {
        RentalRequestVo vo = new RentalRequestVo();
        vo.setNanoId(r.getNanoId());
        vo.setStatus(r.getStatus().name());
        vo.setStartMonth(r.getStartMonth());
        vo.setEndMonth(r.getEndMonth());
        vo.setWorkstationArea(r.getWorkstationArea());
        vo.setMonthCount(r.getMonthCount());
        vo.setWorkstationCount(r.getWorkstationCount());
        vo.setReason(r.getReason());
        vo.setRejectReason(r.getRejectReason());
        vo.setApprovedAt(r.getApprovedAt());
        vo.setSysCreateDatetime(r.getSysCreateDatetime());
        vo.setRequesterMentorGroupNanoId(this.mentorGroupResolver.resolveNanoId(r.getRequesterMentorGroupId()));
        vo.setRequesterMentorGroupName(this.mentorGroupResolver.resolveName(r.getRequesterMentorGroupId()));
        this.labRepository.findById(r.getLabId()).ifPresent(lab -> {
            vo.setLabNanoId(lab.getNanoId());
            vo.setLabName(lab.getName());
        });
        if (r.getApproverUserId() != null) {
            vo.setApproverUserNanoId(this.userIdResolver.resolveNanoId(r.getApproverUserId()));
            vo.setApproverUserName(this.userIdResolver.resolveName(r.getApproverUserId()));
        }
        List<WorkstationRentalRequestItem> items = this.itemRepository.findByRentalRequestId(r.getId());
        ArrayList<RentalItemVo> itemVos = new ArrayList<RentalItemVo>(items.size());
        for (WorkstationRentalRequestItem item : items) {
            this.workstationRepository.findById(item.getWorkstationId()).ifPresent(ws -> {
                RentalItemVo iv = new RentalItemVo();
                iv.setWorkstationNanoId(ws.getNanoId());
                iv.setWorkstationCode(ws.getCode());
                iv.setEndMonth(item.getEndMonth() != null ? item.getEndMonth() : r.getEndMonth());
                itemVos.add(iv);
            });
        }
        vo.setWorkstationNanoIds(itemVos.stream().map(RentalItemVo::getWorkstationNanoId).collect(Collectors.toList()));
        vo.setWorkstations(itemVos);
        StudentInfo info = parseStudentInfo(r.getReason());
        if (info != null) {
            vo.setStudentName(info.name);
            vo.setStudentNo(info.no);
            vo.setGradeName(info.grade);
            vo.setMentorName(info.mentor);
        }
        return vo;
    }

    @Transactional
    public String createStudentApply(String labNanoId, String workstationNanoId, String studentName, String studentNo, String gradeName, String mentorName, Long mentorGroupId, String endMonth) {
        // 导师组为空也允许提交(管理员后台手动确认)
        Workstation ws = this.workstationRepository.findByNanoId(workstationNanoId);
        if (ws == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5de5\u4f4d\u4e0d\u5b58\u5728: " + workstationNanoId);
        }
        boolean hasOccupant = ws.getCurrentUserId() != null || (ws.getManualOccupantName() != null && !ws.getManualOccupantName().isBlank());
        boolean canApply = ws.getStatus() == WorkstationStatus.IDLE || (ws.getStatus() == WorkstationStatus.PRE_RESERVED && !hasOccupant);
        if (!canApply) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u8be5\u5de5\u4f4d\u5f53\u524d\u4e0d\u53ef\u7533\u8bf7(\u5df2\u88ab\u4f7f\u7528\u6216\u4e0d\u53ef\u7528): " + ws.getCode());
        }
        if (ws.getStatus() == WorkstationStatus.PRE_RESERVED && !hasOccupant && this.itemRepository.countApprovedByWorkstationAndMonth(ws.getId(), YearMonth.now().toString()) > 0L) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u8be5\u5de5\u4f4d\u5df2\u88ab\u8bfe\u9898\u7ec4\u6b63\u5f0f\u5206\u914d, \u4e0d\u53ef\u7533\u8bf7: " + ws.getCode());
        }
        List<WorkstationRentalRequestItem> exists = this.itemRepository.findByWorkstationIdIn(java.util.List.of(ws.getId()));
        for (WorkstationRentalRequestItem it : exists) {
            WorkstationRentalRequest rr = it.getRentalRequestId() == null ? null : this.requestRepository.findById(it.getRentalRequestId()).orElse(null);
            if (rr != null && rr.getStatus() != null && (rr.getStatus().isPending() || rr.getStatus() == RentalRequestStatus.APPROVED)) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u8be5\u5de5\u4f4d\u5df2\u6709\u5f85\u5ba1\u6279\u6216\u5df2\u901a\u8fc7\u7684\u7533\u8bf7: " + ws.getCode());
            }
        }
        RentalRequestCreateRequest r = new RentalRequestCreateRequest();
        r.setLabNanoId(labNanoId);
        r.setWorkstationNanoIds(List.of(workstationNanoId));
        r.setStartMonth(YearMonth.now().toString());
        String effEnd = endMonth;
        if (effEnd == null || effEnd.isBlank()) {
            effEnd = WorkstationRentalRequestService.defaultStudentEndMonth();
        }
        if (!effEnd.matches("\\d{4}-\\d{2}")) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7ed3\u675f\u6708\u4efd\u683c\u5f0f\u5e94\u4e3a YYYY-MM");
        }
        YearMonth em = YearMonth.parse(effEnd);
        if (em.isBefore(YearMonth.now())) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7ed3\u675f\u6708\u4efd\u4e0d\u80fd\u65e9\u4e8e\u5f53\u524d\u6708\u4efd");
        }
        r.setEndMonth(effEnd);
        r.setMentorGroupNanoId(this.mentorGroupResolver.resolveNanoId(mentorGroupId));
        r.setReason(WorkstationRentalRequestService.buildStudentReason(studentName, studentNo, gradeName, mentorName));
        return this.create(labNanoId, r);
    }

    private static String buildStudentReason(String studentName, String studentNo, String gradeName, String mentorName) {
        return "\u3010\u5b66\u751f\u7533\u8bf7\u3011\u59d3\u540d:" + studentName + ";\u5b66\u53f7:" + studentNo + ";\u5e74\u7ea7:" + gradeName + ";\u5bfc\u5e08:" + mentorName;
    }

    public static StudentInfo parseStudentInfo(String reason) {
        if (reason == null || !reason.startsWith("\u3010\u5b66\u751f\u7533\u8bf7\u3011")) {
            return null;
        }
        StudentInfo info = new StudentInfo();
        String body = reason.substring("\u3010\u5b66\u751f\u7533\u8bf7\u3011".length());
        String[] parts = body.split(";");
        for (String p : parts) {
            int i = p.indexOf(58);
            if (i < 0) {
                continue;
            }
            String k = p.substring(0, i).trim();
            String v = p.substring(i + 1).trim();
            if ("\u59d3\u540d".equals(k)) {
                info.name = v;
            } else if ("\u5b66\u53f7".equals(k)) {
                info.no = v;
            } else if ("\u5e74\u7ea7".equals(k)) {
                info.grade = v;
            } else if ("\u5bfc\u5e08".equals(k)) {
                info.mentor = v;
            }
        }
        return info;
    }

    private static String defaultStudentEndMonth() {
        YearMonth now = YearMonth.now();
        return YearMonth.of(now.getYear() + 1, 6).toString();
    }

    public static class StudentInfo {
        public String name;
        public String no;
        public String grade;
        public String mentor;

        public StudentInfo() {
        }
    }

    public WorkstationRentalRequestService(WorkstationRentalRequestRepository requestRepository, WorkstationRentalRequestItemRepository itemRepository, WorkstationOccupancyHistoryRepository historyRepository, WorkstationRepository workstationRepository, LabRepository labRepository, UserIdResolver userIdResolver, MentorGroupResolver mentorGroupResolver, PermissionService permissionService, WorkstationService workstationService, WorkstationOccupancyHistoryService historyService) {
        this.requestRepository = requestRepository;
        this.itemRepository = itemRepository;
        this.historyRepository = historyRepository;
        this.workstationRepository = workstationRepository;
        this.labRepository = labRepository;
        this.userIdResolver = userIdResolver;
        this.mentorGroupResolver = mentorGroupResolver;
        this.permissionService = permissionService;
        this.workstationService = workstationService;
        this.historyService = historyService;
    }

    private static LocalDateTime resolveOpenRentalEnd(WorkstationRentalRequestRepository repo, WorkstationOccupancyHistory h) {
        if (h.getSourceType() != OccupancySourceType.RENTAL || h.getSourceId() == null) {
            return null;
        }
        WorkstationRentalRequest r = repo.findById(h.getSourceId()).orElse(null);
        if (r == null || r.getEndMonth() == null || r.getEndMonth().isBlank()) {
            return null;
        }
        try {
            return YearMonth.parse(r.getEndMonth()).atEndOfMonth().atTime(23, 59, 59);
        }
        catch (Exception e) {
            return null;
        }
    }

    static class ItemAgg {
        TreeSet<YearMonth> months = new TreeSet<YearMonth>();
        String wsNanoId;
        String wsCode;
        String labNanoId;
        String labName;
        BigDecimal area;
    }
}

