package com.lab.module.workstation.entity;

import com.lab.common.entity.BaseEntity;
import com.lab.module.workstation.entity.OccupancySourceType;
import com.lab.module.workstation.entity.OccupancyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name="workstation_occupancy_history")
@SQLRestriction(value="is_deleted = 0")
public class WorkstationOccupancyHistory
extends BaseEntity {
    @Column(nullable=false)
    private Long workstationId;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false, length=20)
    private OccupancyType occupancyType;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false, length=20)
    private OccupancySourceType sourceType;
    private Long sourceId;
    private Long occupantUserId;
    private Long occupantMentorGroupId;
    @Column(nullable=false)
    private LocalDateTime occupiedAt;
    private LocalDateTime releasedAt;
    private Integer durationMonths;

    public WorkstationOccupancyHistory() {
    }

    public Long getWorkstationId() {
        return this.workstationId;
    }

    public OccupancyType getOccupancyType() {
        return this.occupancyType;
    }

    public OccupancySourceType getSourceType() {
        return this.sourceType;
    }

    public Long getSourceId() {
        return this.sourceId;
    }

    public Long getOccupantUserId() {
        return this.occupantUserId;
    }

    public Long getOccupantMentorGroupId() {
        return this.occupantMentorGroupId;
    }

    public LocalDateTime getOccupiedAt() {
        return this.occupiedAt;
    }

    public LocalDateTime getReleasedAt() {
        return this.releasedAt;
    }

    public Integer getDurationMonths() {
        return this.durationMonths;
    }

    public void setWorkstationId(Long workstationId) {
        this.workstationId = workstationId;
    }

    public void setOccupancyType(OccupancyType occupancyType) {
        this.occupancyType = occupancyType;
    }

    public void setSourceType(OccupancySourceType sourceType) {
        this.sourceType = sourceType;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public void setOccupantUserId(Long occupantUserId) {
        this.occupantUserId = occupantUserId;
    }

    public void setOccupantMentorGroupId(Long occupantMentorGroupId) {
        this.occupantMentorGroupId = occupantMentorGroupId;
    }

    public void setOccupiedAt(LocalDateTime occupiedAt) {
        this.occupiedAt = occupiedAt;
    }

    public void setReleasedAt(LocalDateTime releasedAt) {
        this.releasedAt = releasedAt;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public String toString() {
        return "WorkstationOccupancyHistory(workstationId=" + this.getWorkstationId() + ", occupancyType=" + String.valueOf(this.getOccupancyType()) + ", sourceType=" + String.valueOf(this.getSourceType()) + ", sourceId=" + this.getSourceId() + ", occupantUserId=" + this.getOccupantUserId() + ", occupantMentorGroupId=" + this.getOccupantMentorGroupId() + ", occupiedAt=" + String.valueOf(this.getOccupiedAt()) + ", releasedAt=" + String.valueOf(this.getReleasedAt()) + ", durationMonths=" + this.getDurationMonths() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WorkstationOccupancyHistory)) {
            return false;
        }
        WorkstationOccupancyHistory other = (WorkstationOccupancyHistory)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Long this$workstationId = this.getWorkstationId();
        Long other$workstationId = other.getWorkstationId();
        if (this$workstationId == null ? other$workstationId != null : !((Object)this$workstationId).equals(other$workstationId)) {
            return false;
        }
        Long this$sourceId = this.getSourceId();
        Long other$sourceId = other.getSourceId();
        if (this$sourceId == null ? other$sourceId != null : !((Object)this$sourceId).equals(other$sourceId)) {
            return false;
        }
        Long this$occupantUserId = this.getOccupantUserId();
        Long other$occupantUserId = other.getOccupantUserId();
        if (this$occupantUserId == null ? other$occupantUserId != null : !((Object)this$occupantUserId).equals(other$occupantUserId)) {
            return false;
        }
        Long this$occupantMentorGroupId = this.getOccupantMentorGroupId();
        Long other$occupantMentorGroupId = other.getOccupantMentorGroupId();
        if (this$occupantMentorGroupId == null ? other$occupantMentorGroupId != null : !((Object)this$occupantMentorGroupId).equals(other$occupantMentorGroupId)) {
            return false;
        }
        Integer this$durationMonths = this.getDurationMonths();
        Integer other$durationMonths = other.getDurationMonths();
        if (this$durationMonths == null ? other$durationMonths != null : !((Object)this$durationMonths).equals(other$durationMonths)) {
            return false;
        }
        OccupancyType this$occupancyType = this.getOccupancyType();
        OccupancyType other$occupancyType = other.getOccupancyType();
        if (this$occupancyType == null ? other$occupancyType != null : !this$occupancyType.equals(other$occupancyType)) {
            return false;
        }
        OccupancySourceType this$sourceType = this.getSourceType();
        OccupancySourceType other$sourceType = other.getSourceType();
        if (this$sourceType == null ? other$sourceType != null : !this$sourceType.equals(other$sourceType)) {
            return false;
        }
        LocalDateTime this$occupiedAt = this.getOccupiedAt();
        LocalDateTime other$occupiedAt = other.getOccupiedAt();
        if (this$occupiedAt == null ? other$occupiedAt != null : !((Object)this$occupiedAt).equals(other$occupiedAt)) {
            return false;
        }
        LocalDateTime this$releasedAt = this.getReleasedAt();
        LocalDateTime other$releasedAt = other.getReleasedAt();
        return !(this$releasedAt == null ? other$releasedAt != null : !((Object)this$releasedAt).equals(other$releasedAt));
    }

    protected boolean canEqual(Object other) {
        return other instanceof WorkstationOccupancyHistory;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
        Long $workstationId = this.getWorkstationId();
        result = result * 59 + ($workstationId == null ? 43 : ((Object)$workstationId).hashCode());
        Long $sourceId = this.getSourceId();
        result = result * 59 + ($sourceId == null ? 43 : ((Object)$sourceId).hashCode());
        Long $occupantUserId = this.getOccupantUserId();
        result = result * 59 + ($occupantUserId == null ? 43 : ((Object)$occupantUserId).hashCode());
        Long $occupantMentorGroupId = this.getOccupantMentorGroupId();
        result = result * 59 + ($occupantMentorGroupId == null ? 43 : ((Object)$occupantMentorGroupId).hashCode());
        Integer $durationMonths = this.getDurationMonths();
        result = result * 59 + ($durationMonths == null ? 43 : ((Object)$durationMonths).hashCode());
        OccupancyType $occupancyType = this.getOccupancyType();
        result = result * 59 + ($occupancyType == null ? 43 : $occupancyType.hashCode());
        OccupancySourceType $sourceType = this.getSourceType();
        result = result * 59 + ($sourceType == null ? 43 : $sourceType.hashCode());
        LocalDateTime $occupiedAt = this.getOccupiedAt();
        result = result * 59 + ($occupiedAt == null ? 43 : ((Object)$occupiedAt).hashCode());
        LocalDateTime $releasedAt = this.getReleasedAt();
        result = result * 59 + ($releasedAt == null ? 43 : ((Object)$releasedAt).hashCode());
        return result;
    }
}

