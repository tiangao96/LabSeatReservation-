package com.lab.module.workstation.entity;

import com.lab.common.entity.BaseEntity;
import com.lab.module.workstation.entity.RentalRequestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name="workstation_rental_request")
@SQLRestriction(value="is_deleted = 0")
public class WorkstationRentalRequest
extends BaseEntity {
    @Column(nullable=false)
    private Long requesterMentorGroupId;
    @Column(nullable=false)
    private Long labId;
    @Column(nullable=false, length=7)
    private String startMonth;
    @Column(nullable=false, length=7)
    private String endMonth;
    @Column(precision=10, scale=2)
    private BigDecimal workstationArea;
    @Column(nullable=false)
    private Integer monthCount;
    @Column(nullable=false)
    private Integer workstationCount;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false, length=20)
    private RentalRequestStatus status = RentalRequestStatus.PENDING;
    @Column(length=500)
    private String reason;
    private Long approverUserId;
    @Column(length=500)
    private String rejectReason;
    private LocalDateTime approvedAt;

    public WorkstationRentalRequest() {
    }

    public Long getRequesterMentorGroupId() {
        return this.requesterMentorGroupId;
    }

    public Long getLabId() {
        return this.labId;
    }

    public String getStartMonth() {
        return this.startMonth;
    }

    public String getEndMonth() {
        return this.endMonth;
    }

    public BigDecimal getWorkstationArea() {
        return this.workstationArea;
    }

    public Integer getMonthCount() {
        return this.monthCount;
    }

    public Integer getWorkstationCount() {
        return this.workstationCount;
    }

    public RentalRequestStatus getStatus() {
        return this.status;
    }

    public String getReason() {
        return this.reason;
    }

    public Long getApproverUserId() {
        return this.approverUserId;
    }

    public String getRejectReason() {
        return this.rejectReason;
    }

    public LocalDateTime getApprovedAt() {
        return this.approvedAt;
    }

    public void setRequesterMentorGroupId(Long requesterMentorGroupId) {
        this.requesterMentorGroupId = requesterMentorGroupId;
    }

    public void setLabId(Long labId) {
        this.labId = labId;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public void setWorkstationArea(BigDecimal workstationArea) {
        this.workstationArea = workstationArea;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public void setWorkstationCount(Integer workstationCount) {
        this.workstationCount = workstationCount;
    }

    public void setStatus(RentalRequestStatus status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setApproverUserId(Long approverUserId) {
        this.approverUserId = approverUserId;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
    }

    public String toString() {
        return "WorkstationRentalRequest(requesterMentorGroupId=" + this.getRequesterMentorGroupId() + ", labId=" + this.getLabId() + ", startMonth=" + this.getStartMonth() + ", endMonth=" + this.getEndMonth() + ", workstationArea=" + String.valueOf(this.getWorkstationArea()) + ", monthCount=" + this.getMonthCount() + ", workstationCount=" + this.getWorkstationCount() + ", status=" + String.valueOf(this.getStatus()) + ", reason=" + this.getReason() + ", approverUserId=" + this.getApproverUserId() + ", rejectReason=" + this.getRejectReason() + ", approvedAt=" + String.valueOf(this.getApprovedAt()) + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WorkstationRentalRequest)) {
            return false;
        }
        WorkstationRentalRequest other = (WorkstationRentalRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Long this$requesterMentorGroupId = this.getRequesterMentorGroupId();
        Long other$requesterMentorGroupId = other.getRequesterMentorGroupId();
        if (this$requesterMentorGroupId == null ? other$requesterMentorGroupId != null : !((Object)this$requesterMentorGroupId).equals(other$requesterMentorGroupId)) {
            return false;
        }
        Long this$labId = this.getLabId();
        Long other$labId = other.getLabId();
        if (this$labId == null ? other$labId != null : !((Object)this$labId).equals(other$labId)) {
            return false;
        }
        Integer this$monthCount = this.getMonthCount();
        Integer other$monthCount = other.getMonthCount();
        if (this$monthCount == null ? other$monthCount != null : !((Object)this$monthCount).equals(other$monthCount)) {
            return false;
        }
        Integer this$workstationCount = this.getWorkstationCount();
        Integer other$workstationCount = other.getWorkstationCount();
        if (this$workstationCount == null ? other$workstationCount != null : !((Object)this$workstationCount).equals(other$workstationCount)) {
            return false;
        }
        Long this$approverUserId = this.getApproverUserId();
        Long other$approverUserId = other.getApproverUserId();
        if (this$approverUserId == null ? other$approverUserId != null : !((Object)this$approverUserId).equals(other$approverUserId)) {
            return false;
        }
        String this$startMonth = this.getStartMonth();
        String other$startMonth = other.getStartMonth();
        if (this$startMonth == null ? other$startMonth != null : !this$startMonth.equals(other$startMonth)) {
            return false;
        }
        String this$endMonth = this.getEndMonth();
        String other$endMonth = other.getEndMonth();
        if (this$endMonth == null ? other$endMonth != null : !this$endMonth.equals(other$endMonth)) {
            return false;
        }
        BigDecimal this$workstationArea = this.getWorkstationArea();
        BigDecimal other$workstationArea = other.getWorkstationArea();
        if (this$workstationArea == null ? other$workstationArea != null : !((Object)this$workstationArea).equals(other$workstationArea)) {
            return false;
        }
        RentalRequestStatus this$status = this.getStatus();
        RentalRequestStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        String this$reason = this.getReason();
        String other$reason = other.getReason();
        if (this$reason == null ? other$reason != null : !this$reason.equals(other$reason)) {
            return false;
        }
        String this$rejectReason = this.getRejectReason();
        String other$rejectReason = other.getRejectReason();
        if (this$rejectReason == null ? other$rejectReason != null : !this$rejectReason.equals(other$rejectReason)) {
            return false;
        }
        LocalDateTime this$approvedAt = this.getApprovedAt();
        LocalDateTime other$approvedAt = other.getApprovedAt();
        return !(this$approvedAt == null ? other$approvedAt != null : !((Object)this$approvedAt).equals(other$approvedAt));
    }

    protected boolean canEqual(Object other) {
        return other instanceof WorkstationRentalRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
        Long $requesterMentorGroupId = this.getRequesterMentorGroupId();
        result = result * 59 + ($requesterMentorGroupId == null ? 43 : ((Object)$requesterMentorGroupId).hashCode());
        Long $labId = this.getLabId();
        result = result * 59 + ($labId == null ? 43 : ((Object)$labId).hashCode());
        Integer $monthCount = this.getMonthCount();
        result = result * 59 + ($monthCount == null ? 43 : ((Object)$monthCount).hashCode());
        Integer $workstationCount = this.getWorkstationCount();
        result = result * 59 + ($workstationCount == null ? 43 : ((Object)$workstationCount).hashCode());
        Long $approverUserId = this.getApproverUserId();
        result = result * 59 + ($approverUserId == null ? 43 : ((Object)$approverUserId).hashCode());
        String $startMonth = this.getStartMonth();
        result = result * 59 + ($startMonth == null ? 43 : $startMonth.hashCode());
        String $endMonth = this.getEndMonth();
        result = result * 59 + ($endMonth == null ? 43 : $endMonth.hashCode());
        BigDecimal $workstationArea = this.getWorkstationArea();
        result = result * 59 + ($workstationArea == null ? 43 : ((Object)$workstationArea).hashCode());
        RentalRequestStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        String $reason = this.getReason();
        result = result * 59 + ($reason == null ? 43 : $reason.hashCode());
        String $rejectReason = this.getRejectReason();
        result = result * 59 + ($rejectReason == null ? 43 : $rejectReason.hashCode());
        LocalDateTime $approvedAt = this.getApprovedAt();
        result = result * 59 + ($approvedAt == null ? 43 : ((Object)$approvedAt).hashCode());
        return result;
    }
}

