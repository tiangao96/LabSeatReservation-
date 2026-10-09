package com.lab.module.workstation.service;

import com.lab.common.api.ResultCode;
import com.lab.common.entity.BaseEntity;
import com.lab.common.exception.BizException;
import com.lab.common.security.MentorGroupResolver;
import com.lab.common.security.PermissionService;
import com.lab.common.security.SecurityContextUtil;
import com.lab.common.security.UserIdResolver;
import com.lab.module.workstation.dto.OccupancyHistoryVo;
import com.lab.module.workstation.dto.SeatCandidateVo;
import com.lab.module.workstation.dto.WorkstationCreateRequest;
import com.lab.module.workstation.dto.WorkstationStatusChangeRequest;
import com.lab.module.workstation.dto.WorkstationVo;
import com.lab.module.workstation.entity.Lab;
import com.lab.module.workstation.entity.OccupancySourceType;
import com.lab.module.workstation.entity.OccupancyType;
import com.lab.module.workstation.entity.Workstation;
import com.lab.module.workstation.entity.WorkstationRentalRequest;
import com.lab.module.workstation.entity.WorkstationStatus;
import com.lab.module.workstation.entity.WorkstationType;
import com.lab.module.workstation.repository.LabRepository;
import com.lab.module.workstation.repository.WorkstationRentalRequestItemRepository;
import com.lab.module.workstation.repository.WorkstationRentalRequestRepository;
import com.lab.module.workstation.repository.WorkstationRepository;
import com.lab.module.workstation.service.RentalCalculator;
import com.lab.module.workstation.service.WorkstationOccupancyHistoryService;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Exception performing whole class analysis ignored.
 */
@Service
public class WorkstationService {
    private final WorkstationRepository workstationRepository;
    private final LabRepository labRepository;
    private final PermissionService permissionService;
    private final UserIdResolver userIdResolver;
    private final MentorGroupResolver mentorGroupResolver;
    private final WorkstationRentalRequestItemRepository rentalItemRepository;
    private final WorkstationRentalRequestRepository rentalRequestRepository;
    private final WorkstationOccupancyHistoryService historyService;

    public List<WorkstationVo> listByLab(String labNanoId, String month) {
        Lab lab = this.requireLab(labNanoId);
        List<Workstation> workstations = this.workstationRepository.findByLabId(lab.getId());
        String currentMonth = YearMonth.now().toString();
        Map<Long, Object[]> rentalInfoByWsId = this.rentalItemRepository.findApprovedRentalInfoByWorkstationIds(workstations.stream().map(BaseEntity::getId).toList(), currentMonth).stream().collect(Collectors.toMap(row -> ((Number)row[0]).longValue(), row -> row, (a, b) -> a));
        if (month == null || month.isBlank()) {
            return workstations.stream().map(ws -> this.toVo(ws, lab.getNanoId(), ws.getStatus().name(), rentalInfoByWsId.get(ws.getId()))).toList();
        }
        RentalCalculator.parseMonth((String)month);
        Set<Long> reservedWsIds = this.rentalItemRepository.findApprovedReservedWorkstationIds(workstations.stream().map(BaseEntity::getId).toList(), month).stream().collect(Collectors.toSet());
        return workstations.stream().map(ws -> this.toVo(ws, lab.getNanoId(), this.monthStatus(ws, reservedWsIds), rentalInfoByWsId.get(ws.getId()))).toList();
    }

    private String monthStatus(Workstation ws, Set<Long> reservedWsIds) {
        if (reservedWsIds.contains(ws.getId())) {
            return ws.getStatus() == WorkstationStatus.OCCUPIED ? WorkstationStatus.OCCUPIED.name() : WorkstationStatus.PRE_RESERVED.name();
        }
        return ws.getStatus().name();
    }

    public List<WorkstationVo> listRentCandidates(String labNanoId, String startMonth, String endMonth) {
        Lab lab = this.requireLab(labNanoId);
        RentalCalculator.parseMonth((String)startMonth);
        RentalCalculator.parseMonth((String)endMonth);
        if (startMonth.compareTo(endMonth) > 0) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7ed3\u675f\u6708\u4e0d\u80fd\u65e9\u4e8e\u8d77\u59cb\u6708");
        }
        List<Workstation> seats = this.workstationRepository.findByLabId(lab.getId()).stream().filter(ws -> ws.getType() == null || ws.getType() == WorkstationType.SEAT).toList();
        if (seats.isEmpty()) {
            return List.of();
        }
        List<Long> wsIds = seats.stream().map(BaseEntity::getId).toList();
        Set<Long> conflictingWsIds = this.rentalItemRepository.findConflictingWorkstationIds(wsIds, startMonth, endMonth).stream().collect(Collectors.toSet());
        Map<Long, Object[]> rentalInfoByWsId = this.rentalItemRepository.findApprovedRentalInfoByWorkstationIds(wsIds, YearMonth.now().toString()).stream().collect(Collectors.toMap(row -> ((Number)row[0]).longValue(), row -> row, (a, b) -> a));
        return seats.stream().map(ws -> {
            WorkstationVo vo = this.toVo(ws, lab.getNanoId(), ws.getStatus().name(), rentalInfoByWsId.get(ws.getId()));
            String reason = WorkstationService.unrentableReason((Workstation)ws, (boolean)conflictingWsIds.contains(ws.getId()), (String)startMonth);
            vo.setRentable(Boolean.valueOf(reason == null));
            vo.setUnrentableReason(reason);
            return vo;
        }).toList();
    }

    static String unrentableReason(Workstation ws, boolean monthConflict, String startMonth) {
        if (ws.getStatus() == WorkstationStatus.MAINTENANCE || ws.getStatus() == WorkstationStatus.DISABLED) {
            return ws.getStatus() == WorkstationStatus.MAINTENANCE ? "\u5de5\u4f4d\u7ef4\u62a4\u4e2d" : "\u5de5\u4f4d\u5df2\u7981\u7528";
        }
        if (monthConflict) {
            return "\u6240\u9009\u6708\u4efd\u4e0e\u5df2\u901a\u8fc7\u7684\u6708\u79df\u51b2\u7a81";
        }
        if (ws.getStatus() == WorkstationStatus.OCCUPIED && startMonth.compareTo(YearMonth.now().toString()) <= 0) {
            return "\u5de5\u4f4d\u5f53\u524d\u88ab\u5360\u7528\uff0c\u8d77\u59cb\u6708\u987b\u665a\u4e8e\u5f53\u524d\u6708\uff08\u6216\u5148\u91ca\u653e\uff09";
        }
        return null;
    }

    public WorkstationVo getByNanoId(String nanoId) {
        Workstation ws = this.requireWorkstation(nanoId);
        Lab lab = (Lab)this.labRepository.findById(ws.getLabId()).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND));
        String currentMonth = YearMonth.now().toString();
        List<Object[]> infos = this.rentalItemRepository.findApprovedRentalInfoByWorkstationIds(List.of(ws.getId()), currentMonth);
        Object[] rentalInfo = infos.isEmpty() ? null : infos.get(0);
        return this.toVo(ws, lab.getNanoId(), ws.getStatus().name(), rentalInfo);
    }

    public List<OccupancyHistoryVo> occupancyHistory(String nanoId) {
        Workstation ws = this.requireWorkstation(nanoId);
        return this.historyService.listByWorkstation(ws.getId());
    }

    @Transactional
    public String create(WorkstationCreateRequest req) {
        Lab lab = this.requireLab(req.getLabNanoId());
        this.workstationRepository.findByLabIdAndCode(lab.getId(), req.getCode()).ifPresent(w -> {
            throw new BizException(ResultCode.BAD_REQUEST, "\u8be5\u5b9e\u9a8c\u5ba4\u5185\u5de5\u4f4d\u7f16\u53f7\u5df2\u5b58\u5728");
        });
        Workstation ws = new Workstation();
        ws.setCode(req.getCode());
        ws.setLabId(lab.getId());
        ws.setStatus(WorkstationStatus.IDLE);
        ws.setType(req.getType() != null ? req.getType() : WorkstationType.SEAT);
        this.applyLayout(ws, req);
        ws.setDescription(req.getDescription());
        ws = (Workstation)this.workstationRepository.save(ws);
        return ws.getNanoId();
    }

    @Transactional
    public void update(String nanoId, WorkstationCreateRequest req) {
        Workstation ws = this.requireWorkstation(nanoId);
        Lab lab = this.requireLab(req.getLabNanoId());
        this.workstationRepository.findByLabIdAndCode(lab.getId(), req.getCode()).ifPresent(other -> {
            if (!other.getId().equals(ws.getId())) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u8be5\u5b9e\u9a8c\u5ba4\u5185\u5de5\u4f4d\u7f16\u53f7\u5df2\u5b58\u5728");
            }
        });
        ws.setCode(req.getCode());
        ws.setLabId(lab.getId());
        ws.setType(req.getType() != null ? req.getType() : WorkstationType.SEAT);
        this.applyLayout(ws, req);
        ws.setDescription(req.getDescription());
        this.workstationRepository.save(ws);
    }

    @Transactional
    public void delete(String nanoId) {
        Workstation ws = this.requireWorkstation(nanoId);
        if (ws.getStatus() == WorkstationStatus.OCCUPIED) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5360\u7528\u4e2d\u5de5\u4f4d\u4e0d\u53ef\u5220\u9664\uff0c\u8bf7\u5148\u91ca\u653e");
        }
        if (ws.getStatus() == WorkstationStatus.PRE_RESERVED) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u9884\u5360\u4e2d\u5de5\u4f4d\u4e0d\u53ef\u5220\u9664\uff0c\u8bf7\u5148\u53d6\u6d88\u5de5\u4f4d\u7533\u8bf7");
        }
        ws.setIsDeleted(Integer.valueOf(1));
        this.workstationRepository.save(ws);
    }

    void doReserve(Workstation ws, Long rentalRequestId, Long occupantMentorGroupId) {
        if (ws.getStatus() == WorkstationStatus.PRE_RESERVED || ws.getStatus() == WorkstationStatus.OCCUPIED) {
            return;
        }
        if (!ws.getStatus().canReserve()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u5f53\u524d\u72b6\u6001\u4e0d\u53ef\u9884\u5360: " + String.valueOf(ws.getStatus()));
        }
        ws.setStatus(WorkstationStatus.PRE_RESERVED);
        ws.setOccupantMentorGroupId(occupantMentorGroupId);
        this.workstationRepository.save(ws);
        this.historyService.startSegment(ws.getId(), OccupancyType.PRE_RESERVED, OccupancySourceType.RENTAL, rentalRequestId, occupantMentorGroupId, null);
    }

    void doSeatOccupy(Workstation ws, Long userId, LocalDateTime occupyAt, Long rentalId) {
        if (ws.getType() != WorkstationType.SEAT) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u975e\u5de5\u4f4d\u7c7b\u578b\u4e0d\u53ef\u5360\u9886: " + String.valueOf(ws.getType()));
        }
        if (!ws.getStatus().canSeatOccupy()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u5f53\u524d\u72b6\u6001\u4e0d\u53ef\u88ab\u5b66\u751f\u5360\u9886\uff08\u987b\u5148\u9884\u5360\uff09: " + String.valueOf(ws.getStatus()));
        }
        ws.setStatus(WorkstationStatus.OCCUPIED);
        ws.setCurrentUserId(userId);
        ws.setOccupiedAt(occupyAt);
        ws.setManualOccupantName(null);
        this.workstationRepository.save(ws);
        this.historyService.transitionTo(ws.getId(), OccupancyType.OCCUPIED, OccupancySourceType.RENTAL, rentalId != null ? rentalId : this.currentRentalId(ws.getId()), ws.getOccupantMentorGroupId(), userId, occupyAt);
    }

    @Transactional
    public void seat(String nanoId, String userNanoId, String startMonth) {
        LocalDateTime occupyAt;
        String rentalStartMonth;
        Workstation ws = this.requireWorkstation(nanoId);
        Long userId = this.userIdResolver.resolveUserId(userNanoId);
        if (userId == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u88ab\u5360\u9886\u5b66\u751f\u4e0d\u5b58\u5728");
        }
        if (ws.getOccupantMentorGroupId() == null || this.userIdResolver.listByMentorGroup(ws.getOccupantMentorGroupId()).stream().noneMatch(u -> u.userId().equals(userId))) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u8be5\u5b66\u751f\u4e0d\u5c5e\u4e8e\u6b64\u5de5\u4f4d\u7684\u5360\u7528\u7ec4");
        }
        if (this.workstationRepository.existsByCurrentUserId(userId)) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u8be5\u5b66\u751f\u5df2\u5360\u7528\u5176\u4ed6\u5de5\u4f4d");
        }
        WorkstationOccupancyHistoryService.OpenRentalInfo openRental = this.historyService.openRentalInfo(ws.getId());
        String string = rentalStartMonth = openRental != null ? openRental.startMonth() : this.activeRentalStartMonth(ws.getId());
        if (startMonth == null || startMonth.isBlank()) {
            occupyAt = rentalStartMonth != null ? WorkstationService.monthStart((String)rentalStartMonth) : LocalDateTime.now();
        } else {
            RentalCalculator.parseMonth((String)startMonth);
            if (rentalStartMonth != null && startMonth.compareTo(rentalStartMonth) < 0) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u5360\u9886\u65e5\u671f\u4e0d\u5f97\u65e9\u4e8e\u5de5\u4f4d\u7533\u8bf7\u8d77\u59cb\u6708: " + rentalStartMonth);
            }
            occupyAt = WorkstationService.monthStart((String)startMonth);
        }
        this.doSeatOccupy(ws, userId, occupyAt, openRental != null ? openRental.rentalId() : null);
    }

    private String activeRentalStartMonth(Long wsId) {
        List<Object[]> rows = this.rentalItemRepository.findActiveApprovedRentalByWorkstation(wsId, YearMonth.now().toString());
        return rows.isEmpty() ? null : (String)(rows.get(0))[1];
    }

    private static LocalDateTime monthStart(String month) {
        int[] ym = RentalCalculator.parseMonth((String)month);
        return LocalDateTime.of(ym[0], ym[1], 1, 0, 0);
    }

    public List<SeatCandidateVo> seatCandidates(String nanoId) {
        Workstation ws = this.requireWorkstation(nanoId);
        if (ws.getStatus() != WorkstationStatus.PRE_RESERVED) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u5f53\u524d\u975e\u9884\u5360\u6001\uff0c\u65e0\u6cd5\u5360\u9886");
        }
        if (ws.getOccupantMentorGroupId() == null) {
            return List.of();
        }
        return this.userIdResolver.listByMentorGroup(ws.getOccupantMentorGroupId()).stream().filter(u -> !this.workstationRepository.existsByCurrentUserId(u.userId())).map(u -> {
            SeatCandidateVo vo = new SeatCandidateVo();
            vo.setNanoId(u.nanoId());
            vo.setName(u.name());
            return vo;
        }).toList();
    }

    @Transactional
    public boolean autoReleaseExpiredRental(Long workstationId, Long rentalRequestId) {
        Workstation ws = (Workstation)this.workstationRepository.findById(workstationId).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5de5\u4f4d\u4e0d\u5b58\u5728: " + workstationId));
        WorkstationOccupancyHistoryService.OpenRentalInfo open = this.historyService.openRentalInfo(ws.getId());
        if (open == null || !rentalRequestId.equals(open.rentalId())) {
            return false;
        }
        WorkstationRentalRequest rental = (WorkstationRentalRequest)this.rentalRequestRepository.findById(rentalRequestId).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u6708\u79df\u7533\u8bf7\u4e0d\u5b58\u5728: " + rentalRequestId));
        int[] ym = RentalCalculator.parseMonth((String)rental.getEndMonth());
        LocalDateTime releasedAt = LocalDateTime.of(ym[0], ym[1], 1, 0, 0).plusMonths(1L).minusSeconds(1L);
        this.historyService.closeCurrentAt(ws.getId(), releasedAt);
        this.chainNextRental(ws, YearMonth.now().toString(), rentalRequestId);
        return true;
    }

    void cancelReserve(Workstation ws, Long rentalRequestId) {
        WorkstationOccupancyHistoryService.OpenRentalInfo open = this.historyService.openRentalInfo(ws.getId());
        if (open == null || !rentalRequestId.equals(open.rentalId())) {
            return;
        }
        if (ws.getStatus() == WorkstationStatus.OCCUPIED) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u5df2\u88ab\u5b66\u751f\u5360\u9886\uff0c\u8bf7\u5148\u91ca\u653e: " + ws.getCode());
        }
        this.historyService.closeCurrent(ws.getId());
        this.chainNextRental(ws, YearMonth.now().toString(), rentalRequestId);
    }

    private void chainNextRental(Workstation ws, String fromMonth, Long excludeRentalId) {
        List<Object[]> rows = excludeRentalId != null ? this.rentalItemRepository.findNextApprovedRentalByWorkstation(ws.getId(), fromMonth, excludeRentalId) : this.rentalItemRepository.findActiveApprovedRentalByWorkstation(ws.getId(), fromMonth);
        ws.setCurrentUserId(null);
        ws.setOccupiedAt(null);
        if (rows.isEmpty()) {
            ws.setStatus(WorkstationStatus.IDLE);
            ws.setOccupantMentorGroupId(null);
            this.workstationRepository.save(ws);
            return;
        }
        Object[] row = rows.get(0);
        Long rentalId = ((Number)row[0]).longValue();
        Long groupId = row[2] == null ? null : Long.valueOf(((Number)row[2]).longValue());
        ws.setStatus(WorkstationStatus.PRE_RESERVED);
        ws.setOccupantMentorGroupId(groupId);
        this.workstationRepository.save(ws);
        this.historyService.startSegment(ws.getId(), OccupancyType.PRE_RESERVED, OccupancySourceType.RENTAL, rentalId, groupId, null);
    }

    @Transactional
    public void release(String nanoId, String endMonth) {
        LocalDateTime releasedAt;
        Workstation ws = this.requireWorkstation(nanoId);
        if (ws.getStatus() != WorkstationStatus.OCCUPIED && ws.getStatus() != WorkstationStatus.PRE_RESERVED) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u672a\u88ab\u5360\u7528");
        }
        Long userId = SecurityContextUtil.currentUserId();
        boolean canForce = this.permissionService.has("workstation:manage");
        if (!(canForce || userId != null && userId.equals(ws.getCurrentUserId()))) {
            throw new BizException(ResultCode.NO_PERMISSION, "\u4ec5\u5360\u7528\u8005\u672c\u4eba\u6216\u7ba1\u7406\u5458\u53ef\u91ca\u653e");
        }
        if (endMonth == null || endMonth.isBlank()) {
            releasedAt = LocalDateTime.now();
        } else {
            int[] ym = RentalCalculator.parseMonth((String)endMonth);
            releasedAt = LocalDateTime.of(ym[0], ym[1], 1, 0, 0).plusMonths(1L).minusSeconds(1L);
        }
        WorkstationOccupancyHistoryService.OpenRentalInfo openRental = this.historyService.openRentalInfo(ws.getId());
        this.historyService.closeCurrentAt(ws.getId(), releasedAt);
        if (openRental != null) {
            this.shrinkRentalItem(openRental.rentalId(), ws.getId(), YearMonth.from(releasedAt).toString());
        }
        this.chainNextRental(ws, YearMonth.from(releasedAt).toString(), openRental != null ? openRental.rentalId() : null);
    }

    @Transactional
    public void changeStatus(String nanoId, WorkstationStatusChangeRequest req) {
        Workstation ws = this.requireWorkstation(nanoId);
        WorkstationStatus current = ws.getStatus();
        WorkstationStatus target = this.parseStatus(req.getStatus());
        if (target == WorkstationStatus.OCCUPIED) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u8bf7\u4f7f\u7528\u5360\u7528\u63a5\u53e3\u8bbe\u7f6e\u5360\u7528\u72b6\u6001");
        }
        if (target == WorkstationStatus.PRE_RESERVED) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u8bf7\u901a\u8fc7\u6708\u79df\u5ba1\u6279\u8bbe\u7f6e\u9884\u5360\u72b6\u6001");
        }
        if (target == current) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u5df2\u662f\u8be5\u72b6\u6001: " + String.valueOf(current));
        }
        if (!current.canManageTo(target)) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u72b6\u6001\u6d41\u8f6c\u975e\u6cd5: " + String.valueOf(current) + " -> " + String.valueOf(target));
        }
        if (current == WorkstationStatus.OCCUPIED) {
            ws.setCurrentUserId(null);
            ws.setOccupiedAt(null);
        }
        if (current == WorkstationStatus.OCCUPIED || current == WorkstationStatus.PRE_RESERVED) {
            ws.setOccupantMentorGroupId(null);
        }
        ws.setStatus(target);
        this.workstationRepository.save(ws);
        if (current == WorkstationStatus.OCCUPIED || current == WorkstationStatus.PRE_RESERVED) {
            WorkstationOccupancyHistoryService.OpenRentalInfo openRental = this.historyService.openRentalInfo(ws.getId());
            this.historyService.closeCurrent(ws.getId());
            if (openRental != null) {
                this.shrinkRentalItem(openRental.rentalId(), ws.getId(), YearMonth.now().toString());
            }
        }
    }

    @Transactional
    public void setMentorGroup(String nanoId, String mentorGroupNanoId) {
        Workstation ws = this.requireWorkstation(nanoId);
        if (ws.getStatus() == WorkstationStatus.OCCUPIED) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u5df2\u88ab\u5b66\u751f\u5360\u7528\uff0c\u8bf7\u5148\u91ca\u653e\u518d\u4fee\u6539\u5bfc\u5e08");
        }
        if (mentorGroupNanoId == null || mentorGroupNanoId.isBlank()) {
            ws.setOccupantMentorGroupId(null);
        } else {
            Long groupId = this.mentorGroupResolver.resolveMentorGroupId(mentorGroupNanoId);
            if (groupId == null) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u5bfc\u5e08\u7ec4\u4e0d\u5b58\u5728");
            }
            ws.setOccupantMentorGroupId(groupId);
        }
        this.workstationRepository.save(ws);
    }

    private void shrinkRentalItem(Long rentalRequestId, Long workstationId, String endMonth) {
        this.rentalRequestRepository.findById(rentalRequestId).ifPresent(rental -> this.rentalItemRepository.findByRentalRequestId(rentalRequestId).stream().filter(i -> workstationId.equals(i.getWorkstationId())).filter(i -> endMonth.compareTo(i.getEndMonth() != null ? i.getEndMonth() : rental.getEndMonth()) < 0).findFirst().ifPresent(i -> {
            i.setEndMonth(endMonth);
            this.rentalItemRepository.save(i);
        }));
    }

    private WorkstationStatus parseStatus(String raw) {
        for (WorkstationStatus s : WorkstationStatus.values()) {
            if (!s.name().equalsIgnoreCase(raw)) continue;
            return s;
        }
        throw new BizException(ResultCode.BAD_REQUEST, "\u975e\u6cd5\u5de5\u4f4d\u72b6\u6001: " + raw);
    }

    private void applyLayout(Workstation ws, WorkstationCreateRequest req) {
        ws.setPosX(req.getPosX());
        ws.setPosY(req.getPosY());
        ws.setWidth(req.getWidth());
        ws.setHeight(req.getHeight());
        ws.setZIndex(req.getZIndex());
        ws.setRotation(req.getRotation());
    }

    private Long currentRentalId(Long wsId) {
        return this.rentalItemRepository.findApprovedRentalRequestIdByWorkstationAndMonth(wsId, YearMonth.now().toString());
    }

    private Workstation requireWorkstation(String nanoId) {
        Workstation ws = this.workstationRepository.findByNanoId(nanoId);
        if (ws == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        return ws;
    }

    private Lab requireLab(String nanoId) {
        Lab lab = this.labRepository.findByNanoId(nanoId);
        if (lab == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5b9e\u9a8c\u5ba4\u4e0d\u5b58\u5728");
        }
        return lab;
    }

    private WorkstationVo toVo(Workstation ws, String labNanoId) {
        return this.toVo(ws, labNanoId, ws.getStatus().name(), null);
    }

    private WorkstationVo toVo(Workstation ws, String labNanoId, String monthStatus) {
        return this.toVo(ws, labNanoId, monthStatus, null);
    }

    private WorkstationVo toVo(Workstation ws, String labNanoId, String monthStatus, Object[] rentalInfo) {
        WorkstationVo vo = new WorkstationVo();
        vo.setNanoId(ws.getNanoId());
        vo.setCode(ws.getCode());
        vo.setLabNanoId(labNanoId);
        vo.setStatus(ws.getStatus().name());
        vo.setMonthStatus(monthStatus);
        vo.setType(ws.getType() != null ? ws.getType().name() : null);
        vo.setOccupiedAt(ws.getOccupiedAt());
        vo.setCurrentUserNanoId(this.userIdResolver.resolveNanoId(ws.getCurrentUserId()));
        String currentUserName = this.userIdResolver.resolveName(ws.getCurrentUserId());
        if (currentUserName == null) {
            currentUserName = ws.getManualOccupantName();
        }
        vo.setCurrentUserName(currentUserName);
        String resolvedMgName = this.mentorGroupResolver.resolveName(ws.getOccupantMentorGroupId());
        if (resolvedMgName == null) {
            resolvedMgName = this.workstationRepository.findMentorGroupName(ws.getId());
        }
        vo.setOccupantMentorGroupName(resolvedMgName);
        vo.setOccupantMentorGroupNanoId(this.mentorGroupResolver.resolveNanoId(ws.getOccupantMentorGroupId()));
        vo.setGradeName(ws.getGradeName());
        vo.setDegreeName(ws.getDegree());
        String exp = ws.getExpectedReleaseMonth();
        if (exp == null) {
            exp = this.computeExpectedRelease(ws.getGradeName(), ws.getDegree());
        }
        vo.setExpectedReleaseMonth(exp);
        if (rentalInfo != null) {
            vo.setReservedStartMonth((String)rentalInfo[2]);
            vo.setReservedEndMonth((String)rentalInfo[3]);
        }
        vo.setDescription(ws.getDescription());
        vo.setPosX(ws.getPosX());
        vo.setPosY(ws.getPosY());
        vo.setWidth(ws.getWidth());
        vo.setHeight(ws.getHeight());
        vo.setZIndex(ws.getZIndex());
        vo.setRotation(ws.getRotation());
        return vo;
    }

    public void updateGradeInfo(String nanoId, String gradeName, String degree) {
        Workstation ws = this.workstationRepository.findByNanoId(nanoId);
        if (ws == null) {
            throw new com.lab.common.exception.BizException(com.lab.common.api.ResultCode.RESOURCE_NOT_FOUND);
        }
        String g = (gradeName == null || gradeName.trim().isEmpty()) ? null : gradeName.trim();
        String d = (degree == null || degree.trim().isEmpty()) ? null : degree.trim();
        ws.setGradeName(g);
        ws.setDegree(d);
        ws.setExpectedReleaseMonth(g == null ? null : this.computeExpectedRelease(g, d));
        this.workstationRepository.save(ws);
    }

    private String computeExpectedRelease(String gradeName, String degree) {
        if (gradeName == null) {
            return null;
        }
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("(20\\d{2}|\\d{2})").matcher(gradeName);
        if (!m.find()) {
            return null;
        }
        int year = Integer.parseInt(m.group(1));
        if (year < 100) {
            year += 2000;
        }
        if (year < 2000 || year > 2100) {
            return null;
        }
        int years = degree != null && degree.contains("\u672c\u79d1") ? 4 : 3;
        return (year + years) + "-06";
    }

        public WorkstationService(WorkstationRepository workstationRepository, LabRepository labRepository, PermissionService permissionService, UserIdResolver userIdResolver, MentorGroupResolver mentorGroupResolver, WorkstationRentalRequestItemRepository rentalItemRepository, WorkstationRentalRequestRepository rentalRequestRepository, WorkstationOccupancyHistoryService historyService) {
        this.workstationRepository = workstationRepository;
        this.labRepository = labRepository;
        this.permissionService = permissionService;
        this.userIdResolver = userIdResolver;
        this.mentorGroupResolver = mentorGroupResolver;
        this.rentalItemRepository = rentalItemRepository;
        this.rentalRequestRepository = rentalRequestRepository;
        this.historyService = historyService;
    }
}

