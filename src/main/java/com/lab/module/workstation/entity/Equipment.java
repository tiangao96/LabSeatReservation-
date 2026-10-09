package com.lab.module.workstation.entity;

import com.lab.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDate;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name="equipment")
@SQLRestriction(value="is_deleted = 0")
public class Equipment
extends BaseEntity {
    @Column(nullable=false)
    private String name;
    private String code;
    private Long labId;
    private String category;
    private String model;
    private String serialNumber;
    @Column(nullable=false, length=20)
    private String status = "NORMAL";
    private Long custodianId;
    private LocalDate purchasedAt;
    private String description;

    public Equipment() {
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public Long getLabId() {
        return this.labId;
    }

    public String getCategory() {
        return this.category;
    }

    public String getModel() {
        return this.model;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public String getStatus() {
        return this.status;
    }

    public Long getCustodianId() {
        return this.custodianId;
    }

    public LocalDate getPurchasedAt() {
        return this.purchasedAt;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLabId(Long labId) {
        this.labId = labId;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustodianId(Long custodianId) {
        this.custodianId = custodianId;
    }

    public void setPurchasedAt(LocalDate purchasedAt) {
        this.purchasedAt = purchasedAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "Equipment(name=" + this.getName() + ", code=" + this.getCode() + ", labId=" + this.getLabId() + ", category=" + this.getCategory() + ", model=" + this.getModel() + ", serialNumber=" + this.getSerialNumber() + ", status=" + this.getStatus() + ", custodianId=" + this.getCustodianId() + ", purchasedAt=" + String.valueOf(this.getPurchasedAt()) + ", description=" + this.getDescription() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Equipment)) {
            return false;
        }
        Equipment other = (Equipment)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Long this$labId = this.getLabId();
        Long other$labId = other.getLabId();
        if (this$labId == null ? other$labId != null : !((Object)this$labId).equals(other$labId)) {
            return false;
        }
        Long this$custodianId = this.getCustodianId();
        Long other$custodianId = other.getCustodianId();
        if (this$custodianId == null ? other$custodianId != null : !((Object)this$custodianId).equals(other$custodianId)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$code = this.getCode();
        String other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) {
            return false;
        }
        String this$category = this.getCategory();
        String other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category)) {
            return false;
        }
        String this$model = this.getModel();
        String other$model = other.getModel();
        if (this$model == null ? other$model != null : !this$model.equals(other$model)) {
            return false;
        }
        String this$serialNumber = this.getSerialNumber();
        String other$serialNumber = other.getSerialNumber();
        if (this$serialNumber == null ? other$serialNumber != null : !this$serialNumber.equals(other$serialNumber)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        LocalDate this$purchasedAt = this.getPurchasedAt();
        LocalDate other$purchasedAt = other.getPurchasedAt();
        if (this$purchasedAt == null ? other$purchasedAt != null : !((Object)this$purchasedAt).equals(other$purchasedAt)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        return !(this$description == null ? other$description != null : !this$description.equals(other$description));
    }

    protected boolean canEqual(Object other) {
        return other instanceof Equipment;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
        Long $labId = this.getLabId();
        result = result * 59 + ($labId == null ? 43 : ((Object)$labId).hashCode());
        Long $custodianId = this.getCustodianId();
        result = result * 59 + ($custodianId == null ? 43 : ((Object)$custodianId).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        String $category = this.getCategory();
        result = result * 59 + ($category == null ? 43 : $category.hashCode());
        String $model = this.getModel();
        result = result * 59 + ($model == null ? 43 : $model.hashCode());
        String $serialNumber = this.getSerialNumber();
        result = result * 59 + ($serialNumber == null ? 43 : $serialNumber.hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        LocalDate $purchasedAt = this.getPurchasedAt();
        result = result * 59 + ($purchasedAt == null ? 43 : ((Object)$purchasedAt).hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }
}

