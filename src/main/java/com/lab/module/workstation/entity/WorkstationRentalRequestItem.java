package com.lab.module.workstation.entity;

import com.lab.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name="workstation_rental_request_item")
@SQLRestriction(value="is_deleted = 0")
public class WorkstationRentalRequestItem
extends BaseEntity {
    @Column(nullable=false)
    private Long rentalRequestId;
    @Column(nullable=false)
    private Long workstationId;
    @Column(name="end_month")
    private String endMonth;

    public WorkstationRentalRequestItem() {
    }

    public Long getRentalRequestId() {
        return this.rentalRequestId;
    }

    public Long getWorkstationId() {
        return this.workstationId;
    }

    public String getEndMonth() {
        return this.endMonth;
    }

    public void setRentalRequestId(Long rentalRequestId) {
        this.rentalRequestId = rentalRequestId;
    }

    public void setWorkstationId(Long workstationId) {
        this.workstationId = workstationId;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String toString() {
        return "WorkstationRentalRequestItem(rentalRequestId=" + this.getRentalRequestId() + ", workstationId=" + this.getWorkstationId() + ", endMonth=" + this.getEndMonth() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WorkstationRentalRequestItem)) {
            return false;
        }
        WorkstationRentalRequestItem other = (WorkstationRentalRequestItem)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Long this$rentalRequestId = this.getRentalRequestId();
        Long other$rentalRequestId = other.getRentalRequestId();
        if (this$rentalRequestId == null ? other$rentalRequestId != null : !((Object)this$rentalRequestId).equals(other$rentalRequestId)) {
            return false;
        }
        Long this$workstationId = this.getWorkstationId();
        Long other$workstationId = other.getWorkstationId();
        if (this$workstationId == null ? other$workstationId != null : !((Object)this$workstationId).equals(other$workstationId)) {
            return false;
        }
        String this$endMonth = this.getEndMonth();
        String other$endMonth = other.getEndMonth();
        return !(this$endMonth == null ? other$endMonth != null : !this$endMonth.equals(other$endMonth));
    }

    protected boolean canEqual(Object other) {
        return other instanceof WorkstationRentalRequestItem;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
        Long $rentalRequestId = this.getRentalRequestId();
        result = result * 59 + ($rentalRequestId == null ? 43 : ((Object)$rentalRequestId).hashCode());
        Long $workstationId = this.getWorkstationId();
        result = result * 59 + ($workstationId == null ? 43 : ((Object)$workstationId).hashCode());
        String $endMonth = this.getEndMonth();
        result = result * 59 + ($endMonth == null ? 43 : $endMonth.hashCode());
        return result;
    }
}

