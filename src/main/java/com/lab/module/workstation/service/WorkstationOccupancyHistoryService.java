package com.lab.module.workstation.service;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.common.security.MentorGroupResolver;
import com.lab.common.security.UserIdResolver;
import com.lab.module.workstation.dto.OccupancyHistoryVo;
import com.lab.module.workstation.entity.OccupancySourceType;
import com.lab.module.workstation.entity.OccupancyType;
import com.lab.module.workstation.entity.WorkstationOccupancyHistory;
import com.lab.module.workstation.repository.WorkstationOccupancyHistoryRepository;
import com.lab.module.workstation.repository.WorkstationRentalRequestRepository;
import com.lab.module.workstation.service.RentalCalculator;
import com.lab.module.workstation.service.WorkstationOccupancyHistoryService;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WorkstationOccupancyHistoryService {
    private static final Logger log = LoggerFactory.getLogger(WorkstationOccupancyHistoryService.class);
    private final WorkstationOccupancyHistoryRepository historyRepository;
    private final WorkstationRentalRequestRepository rentalRequestRepository;
    private final UserIdResolver userIdResolver;
    private final MentorGroupResolver mentorGroupResolver;

    @Transactional(propagation=Propagation.MANDATORY)
    public void startSegment(Long wsId, OccupancyType occType, OccupancySourceType src, Long sourceId, Long occupantMentorGroupId, Long occupantUserId) {
        WorkstationOccupancyHistory h = new WorkstationOccupancyHistory();
        h.setWorkstationId(wsId);
        h.setOccupancyType(occType);
        h.setSourceType(src);
        h.setSourceId(sourceId);
        h.setOccupantMentorGroupId(occupantMentorGroupId);
        h.setOccupantUserId(occupantUserId);
        h.setOccupiedAt(LocalDateTime.now());
        this.historyRepository.save(h);
    }

    @Transactional(propagation=Propagation.MANDATORY)
    public void closeCurrent(Long wsId) {
        this.closeCurrentAt(wsId, LocalDateTime.now());
    }

    @Transactional(propagation=Propagation.MANDATORY)
    public void closeCurrentAt(Long wsId, LocalDateTime endAt) {
        this.historyRepository.findFirstByWorkstationIdAndReleasedAtIsNullOrderByOccupiedAtDesc(wsId).ifPresent(h -> this.closeSegmentAt(h, endAt));
    }

    @Transactional(propagation=Propagation.MANDATORY)
    public void transitionTo(Long wsId, OccupancyType newType, OccupancySourceType src, Long sourceId, Long occupantMentorGroupId, Long occupantUserId, LocalDateTime occurredAt) {
        this.historyRepository.findFirstByWorkstationIdAndReleasedAtIsNullOrderByOccupiedAtDesc(wsId).ifPresent(h -> {
            LocalDateTime closeAt = occurredAt.isBefore(h.getOccupiedAt()) ? h.getOccupiedAt() : occurredAt;
            this.closeSegmentAt(h, closeAt);
        });
        WorkstationOccupancyHistory h2 = new WorkstationOccupancyHistory();
        h2.setWorkstationId(wsId);
        h2.setOccupancyType(newType);
        h2.setSourceType(src);
        h2.setSourceId(sourceId);
        h2.setOccupantMentorGroupId(occupantMentorGroupId);
        h2.setOccupantUserId(occupantUserId);
        h2.setOccupiedAt(occurredAt);
        this.historyRepository.save(h2);
    }

    @Transactional(readOnly=true)
    public List<OccupancyHistoryVo> listByWorkstation(Long wsId) {
        return this.historyRepository.findByWorkstationIdOrderByOccupiedAtDesc(wsId).stream().map(this::toVo).toList();
    }

    @Transactional(readOnly=true)
    public OpenRentalInfo openRentalInfo(Long wsId) {
        return this.historyRepository.findFirstByWorkstationIdAndReleasedAtIsNullOrderByOccupiedAtDesc(wsId).filter(h -> h.getSourceType() == OccupancySourceType.RENTAL && h.getSourceId() != null).flatMap(h -> this.rentalRequestRepository.findById(h.getSourceId()).map(r -> new OpenRentalInfo(r.getId(), r.getStartMonth()))).orElse(null);
    }

    private void closeSegmentAt(WorkstationOccupancyHistory h, LocalDateTime endAt) {
        LocalDateTime effectiveEnd;
        LocalDateTime localDateTime = effectiveEnd = endAt.isBefore(h.getOccupiedAt()) ? h.getOccupiedAt() : endAt;
        if (YearMonth.from(effectiveEnd).isBefore(YearMonth.from(h.getOccupiedAt()))) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7ed3\u675f\u6708\u4e0d\u80fd\u65e9\u4e8e\u5360\u7528\u5f00\u59cb\u6708: " + String.valueOf(YearMonth.from(h.getOccupiedAt())));
        }
        h.setReleasedAt(effectiveEnd);
        h.setDurationMonths(Integer.valueOf(RentalCalculator.calcMonthSpan((LocalDateTime)h.getOccupiedAt(), (LocalDateTime)effectiveEnd)));
        this.historyRepository.save(h);
    }

    private OccupancyHistoryVo toVo(WorkstationOccupancyHistory h) {
        OccupancyHistoryVo vo = new OccupancyHistoryVo();
        vo.setNanoId(h.getNanoId());
        vo.setOccupancyType(h.getOccupancyType().name());
        vo.setSourceType(h.getSourceType().name());
        vo.setOccupiedAt(h.getOccupiedAt());
        vo.setReleasedAt(h.getReleasedAt());
        vo.setDurationMonths(h.getDurationMonths());
        if (h.getOccupantUserId() != null) {
            vo.setOccupantUserNanoId(this.userIdResolver.resolveNanoId(h.getOccupantUserId()));
            vo.setOccupantUserName(this.userIdResolver.resolveName(h.getOccupantUserId()));
        }
        vo.setMentorGroupName(this.mentorGroupResolver.resolveName(h.getOccupantMentorGroupId()));
        if (h.getSourceType() == OccupancySourceType.RENTAL && h.getSourceId() != null) {
            this.rentalRequestRepository.findById(h.getSourceId()).ifPresent(r -> vo.setSourceNanoId(r.getNanoId()));
        }
        return vo;
    }

    public WorkstationOccupancyHistoryService(WorkstationOccupancyHistoryRepository historyRepository, WorkstationRentalRequestRepository rentalRequestRepository, UserIdResolver userIdResolver, MentorGroupResolver mentorGroupResolver) {
        this.historyRepository = historyRepository;
        this.rentalRequestRepository = rentalRequestRepository;
        this.userIdResolver = userIdResolver;
        this.mentorGroupResolver = mentorGroupResolver;
    }

    public record OpenRentalInfo(Long rentalId, String startMonth) {
    }
}

