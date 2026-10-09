package com.lab.module.workstation.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public class EquipmentCreateRequest {
    @NotBlank(message="\u8bbe\u5907\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u8bbe\u5907\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a") String name;
    private String code;
    @NotBlank(message="\u6240\u5c5e\u5b9e\u9a8c\u5ba4\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u6240\u5c5e\u5b9e\u9a8c\u5ba4\u4e0d\u80fd\u4e3a\u7a7a") String labNanoId;
    private String category;
    private String model;
    private String serialNumber;
    private String status;
    private LocalDate purchasedAt;
    private String description;

    public EquipmentCreateRequest() {
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getLabNanoId() {
        return this.labNanoId;
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

    public void setLabNanoId(String labNanoId) {
        this.labNanoId = labNanoId;
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

    public void setPurchasedAt(LocalDate purchasedAt) {
        this.purchasedAt = purchasedAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EquipmentCreateRequest)) {
            return false;
        }
        EquipmentCreateRequest other = (EquipmentCreateRequest)o;
        if (!other.canEqual((Object)this)) {
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
        String this$labNanoId = this.getLabNanoId();
        String other$labNanoId = other.getLabNanoId();
        if (this$labNanoId == null ? other$labNanoId != null : !this$labNanoId.equals(other$labNanoId)) {
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
        return other instanceof EquipmentCreateRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        String $labNanoId = this.getLabNanoId();
        result = result * 59 + ($labNanoId == null ? 43 : $labNanoId.hashCode());
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

    public String toString() {
        return "EquipmentCreateRequest(name=" + this.getName() + ", code=" + this.getCode() + ", labNanoId=" + this.getLabNanoId() + ", category=" + this.getCategory() + ", model=" + this.getModel() + ", serialNumber=" + this.getSerialNumber() + ", status=" + this.getStatus() + ", purchasedAt=" + String.valueOf(this.getPurchasedAt()) + ", description=" + this.getDescription() + ")";
    }
}

