package com.lab.module.workstation.entity;

import com.lab.common.entity.BaseEntity;
import com.lab.module.workstation.entity.WorkstationStatus;
import com.lab.module.workstation.entity.WorkstationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name="workstation")
@SQLRestriction(value="is_deleted = 0")
public class Workstation
extends BaseEntity {
    @Column(nullable=false)
    private String code;
    @Column(nullable=false)
    private Long labId;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false, length=20)
    private WorkstationStatus status = WorkstationStatus.IDLE;
    @Enumerated(value=EnumType.STRING)
    @Column(nullable=false, length=20)
    private WorkstationType type = WorkstationType.SEAT;
    private Long occupantMentorGroupId;
    private String occupantMentorGroupName;
    private Long currentUserId;
    private LocalDateTime occupiedAt;
    private String description;
    private String manualOccupantName;
    private String gradeName;
    private String degree;
    private String expectedReleaseMonth;
    @Column(name="pos_x")
    private Integer posX;
    @Column(name="pos_y")
    private Integer posY;
    private Integer width;
    private Integer height;
    private Integer zIndex;
    private Integer rotation;
    public Workstation() {
    }
    public String getCode() {
        return this.code;
    }
    public Long getLabId() {
        return this.labId;
    }
    public WorkstationStatus getStatus() {
        return this.status;
    }
    public WorkstationType getType() {
        return this.type;
    }
    public Long getOccupantMentorGroupId() {
        return this.occupantMentorGroupId;
    }
    public String getOccupantMentorGroupName() {
        return this.occupantMentorGroupName;
    }
    public Long getCurrentUserId() {
        return this.currentUserId;
    }
    public LocalDateTime getOccupiedAt() {
        return this.occupiedAt;
    }
    public String getDescription() {
        return this.description;
    }
    public String getManualOccupantName() {
        return this.manualOccupantName;
    }
    public String getGradeName() {
        return this.gradeName;
    }
    public String getDegree() {
        return this.degree;
    }
    public String getExpectedReleaseMonth() {
        return this.expectedReleaseMonth;
    }
    public Integer getPosX() {
        return this.posX;
    }
    public Integer getPosY() {
        return this.posY;
    }
    public Integer getWidth() {
        return this.width;
    }
    public Integer getHeight() {
        return this.height;
    }
    public Integer getZIndex() {
        return this.zIndex;
    }
    public Integer getRotation() {
        return this.rotation;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setLabId(Long labId) {
        this.labId = labId;
    }
    public void setStatus(WorkstationStatus status) {
        this.status = status;
    }
    public void setType(WorkstationType type) {
        this.type = type;
    }
    public void setOccupantMentorGroupId(Long occupantMentorGroupId) {
        this.occupantMentorGroupId = occupantMentorGroupId;
    }

    public void setOccupantMentorGroupName(String occupantMentorGroupName) {
        this.occupantMentorGroupName = occupantMentorGroupName;
    }
    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }
    public void setOccupiedAt(LocalDateTime occupiedAt) {
        this.occupiedAt = occupiedAt;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setManualOccupantName(String manualOccupantName) {
        this.manualOccupantName = manualOccupantName;
    }
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public void setExpectedReleaseMonth(String expectedReleaseMonth) {
        this.expectedReleaseMonth = expectedReleaseMonth;
    }
    public void setPosX(Integer posX) {
        this.posX = posX;
    }
    public void setPosY(Integer posY) {
        this.posY = posY;
    }
    public void setWidth(Integer width) {
        this.width = width;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public void setZIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }
    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }
    public String toString() {
        return "Workstation(code=" + this.getCode() + ", labId=" + this.getLabId() + ", status=" + String.valueOf(this.getStatus()) + ", type=" + String.valueOf(this.getType()) + ", occupantMentorGroupId=" + this.getOccupantMentorGroupId() + ", currentUserId=" + this.getCurrentUserId() + ", occupiedAt=" + String.valueOf(this.getOccupiedAt()) + ", description=" + this.getDescription() + ", posX=" + this.getPosX() + ", posY=" + this.getPosY() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ", zIndex=" + this.getZIndex() + ", rotation=" + this.getRotation() + ")";
    }
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Workstation)) {
            return false;
        }
        Workstation other = (Workstation)o;
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
        Long this$occupantMentorGroupId = this.getOccupantMentorGroupId();
        Long other$occupantMentorGroupId = other.getOccupantMentorGroupId();
        if (this$occupantMentorGroupId == null ? other$occupantMentorGroupId != null : !((Object)this$occupantMentorGroupId).equals(other$occupantMentorGroupId)) {
            return false;
        }
        Long this$currentUserId = this.getCurrentUserId();
        Long other$currentUserId = other.getCurrentUserId();
        if (this$currentUserId == null ? other$currentUserId != null : !((Object)this$currentUserId).equals(other$currentUserId)) {
            return false;
        }
        Integer this$posX = this.getPosX();
        Integer other$posX = other.getPosX();
        if (this$posX == null ? other$posX != null : !((Object)this$posX).equals(other$posX)) {
            return false;
        }
        Integer this$posY = this.getPosY();
        Integer other$posY = other.getPosY();
        if (this$posY == null ? other$posY != null : !((Object)this$posY).equals(other$posY)) {
            return false;
        }
        Integer this$width = this.getWidth();
        Integer other$width = other.getWidth();
        if (this$width == null ? other$width != null : !((Object)this$width).equals(other$width)) {
            return false;
        }
        Integer this$height = this.getHeight();
        Integer other$height = other.getHeight();
        if (this$height == null ? other$height != null : !((Object)this$height).equals(other$height)) {
            return false;
        }
        Integer this$zIndex = this.getZIndex();
        Integer other$zIndex = other.getZIndex();
        if (this$zIndex == null ? other$zIndex != null : !((Object)this$zIndex).equals(other$zIndex)) {
            return false;
        }
        Integer this$rotation = this.getRotation();
        Integer other$rotation = other.getRotation();
        if (this$rotation == null ? other$rotation != null : !((Object)this$rotation).equals(other$rotation)) {
            return false;
        }
        String this$code = this.getCode();
        String other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) {
            return false;
        }
        WorkstationStatus this$status = this.getStatus();
        WorkstationStatus other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        WorkstationType this$type = this.getType();
        WorkstationType other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        LocalDateTime this$occupiedAt = this.getOccupiedAt();
        LocalDateTime other$occupiedAt = other.getOccupiedAt();
        if (this$occupiedAt == null ? other$occupiedAt != null : !((Object)this$occupiedAt).equals(other$occupiedAt)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        return !(this$description == null ? other$description != null : !this$description.equals(other$description));
    }
    protected boolean canEqual(Object other) {
        return other instanceof Workstation;
    }
    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
        Long $labId = this.getLabId();
        result = result * 59 + ($labId == null ? 43 : ((Object)$labId).hashCode());
        Long $occupantMentorGroupId = this.getOccupantMentorGroupId();
        result = result * 59 + ($occupantMentorGroupId == null ? 43 : ((Object)$occupantMentorGroupId).hashCode());
        Long $currentUserId = this.getCurrentUserId();
        result = result * 59 + ($currentUserId == null ? 43 : ((Object)$currentUserId).hashCode());
        Integer $posX = this.getPosX();
        result = result * 59 + ($posX == null ? 43 : ((Object)$posX).hashCode());
        Integer $posY = this.getPosY();
        result = result * 59 + ($posY == null ? 43 : ((Object)$posY).hashCode());
        Integer $width = this.getWidth();
        result = result * 59 + ($width == null ? 43 : ((Object)$width).hashCode());
        Integer $height = this.getHeight();
        result = result * 59 + ($height == null ? 43 : ((Object)$height).hashCode());
        Integer $zIndex = this.getZIndex();
        result = result * 59 + ($zIndex == null ? 43 : ((Object)$zIndex).hashCode());
        Integer $rotation = this.getRotation();
        result = result * 59 + ($rotation == null ? 43 : ((Object)$rotation).hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        WorkstationStatus $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        WorkstationType $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        LocalDateTime $occupiedAt = this.getOccupiedAt();
        result = result * 59 + ($occupiedAt == null ? 43 : ((Object)$occupiedAt).hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }
}

