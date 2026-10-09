package com.lab.module.workstation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class WorkstationVo {
    private String nanoId;
    private String code;
    private String labNanoId;
    private String status;
    private String monthStatus;
    private String type;
    private LocalDateTime occupiedAt;
    private String currentUserNanoId;
    private String currentUserName;
    private String description;
    private String occupantMentorGroupName;
    private String occupantMentorGroupNanoId;
    private String reservedStartMonth;
    private String reservedEndMonth;
    private Boolean rentable;
    private String unrentableReason;
    private Integer posX;
    private Integer posY;
    private Integer width;
    private Integer height;
    @JsonProperty(value="zIndex")
    private Integer zIndex;
    private Integer rotation;
    private String gradeName;
    private String degreeName;
    private String expectedReleaseMonth;

        public WorkstationVo() {
    }

        public String getNanoId() {
        return this.nanoId;
    }

        public String getCode() {
        return this.code;
    }

        public String getLabNanoId() {
        return this.labNanoId;
    }

        public String getStatus() {
        return this.status;
    }

        public String getMonthStatus() {
        return this.monthStatus;
    }

        public String getType() {
        return this.type;
    }

        public LocalDateTime getOccupiedAt() {
        return this.occupiedAt;
    }

        public String getCurrentUserNanoId() {
        return this.currentUserNanoId;
    }

        public String getCurrentUserName() {
        return this.currentUserName;
    }

        public String getDescription() {
        return this.description;
    }

        public String getOccupantMentorGroupName() {
        return this.occupantMentorGroupName;
    }

        public String getOccupantMentorGroupNanoId() {
        return this.occupantMentorGroupNanoId;
    }

        public String getReservedStartMonth() {
        return this.reservedStartMonth;
    }

        public String getReservedEndMonth() {
        return this.reservedEndMonth;
    }

        public Boolean getRentable() {
        return this.rentable;
    }

        public String getUnrentableReason() {
        return this.unrentableReason;
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

        public String getGradeName() {
        return this.gradeName;
    }

        public String getDegreeName() {
        return this.degreeName;
    }

        public String getExpectedReleaseMonth() {
        return this.expectedReleaseMonth;
    }

        public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }

        public void setCode(String code) {
        this.code = code;
    }

        public void setLabNanoId(String labNanoId) {
        this.labNanoId = labNanoId;
    }

        public void setStatus(String status) {
        this.status = status;
    }

        public void setMonthStatus(String monthStatus) {
        this.monthStatus = monthStatus;
    }

        public void setType(String type) {
        this.type = type;
    }

        public void setOccupiedAt(LocalDateTime occupiedAt) {
        this.occupiedAt = occupiedAt;
    }

        public void setCurrentUserNanoId(String currentUserNanoId) {
        this.currentUserNanoId = currentUserNanoId;
    }

        public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

        public void setDescription(String description) {
        this.description = description;
    }

        public void setOccupantMentorGroupName(String occupantMentorGroupName) {
        this.occupantMentorGroupName = occupantMentorGroupName;
    }

        public void setOccupantMentorGroupNanoId(String occupantMentorGroupNanoId) {
        this.occupantMentorGroupNanoId = occupantMentorGroupNanoId;
    }

        public void setReservedStartMonth(String reservedStartMonth) {
        this.reservedStartMonth = reservedStartMonth;
    }

        public void setReservedEndMonth(String reservedEndMonth) {
        this.reservedEndMonth = reservedEndMonth;
    }

        public void setRentable(Boolean rentable) {
        this.rentable = rentable;
    }

        public void setUnrentableReason(String unrentableReason) {
        this.unrentableReason = unrentableReason;
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

    @JsonProperty(value="zIndex")
        public void setZIndex(Integer zIndex) {
        this.zIndex = zIndex;
    }

        public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

        public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

        public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

        public void setExpectedReleaseMonth(String expectedReleaseMonth) {
        this.expectedReleaseMonth = expectedReleaseMonth;
    }

        public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WorkstationVo)) {
            return false;
        }
        WorkstationVo other = (WorkstationVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Boolean this$rentable = this.getRentable();
        Boolean other$rentable = other.getRentable();
        if (this$rentable == null ? other$rentable != null : !((Object)this$rentable).equals(other$rentable)) {
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
        String this$nanoId = this.getNanoId();
        String other$nanoId = other.getNanoId();
        if (this$nanoId == null ? other$nanoId != null : !this$nanoId.equals(other$nanoId)) {
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
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        String this$monthStatus = this.getMonthStatus();
        String other$monthStatus = other.getMonthStatus();
        if (this$monthStatus == null ? other$monthStatus != null : !this$monthStatus.equals(other$monthStatus)) {
            return false;
        }
        String this$type = this.getType();
        String other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        LocalDateTime this$occupiedAt = this.getOccupiedAt();
        LocalDateTime other$occupiedAt = other.getOccupiedAt();
        if (this$occupiedAt == null ? other$occupiedAt != null : !((Object)this$occupiedAt).equals(other$occupiedAt)) {
            return false;
        }
        String this$currentUserNanoId = this.getCurrentUserNanoId();
        String other$currentUserNanoId = other.getCurrentUserNanoId();
        if (this$currentUserNanoId == null ? other$currentUserNanoId != null : !this$currentUserNanoId.equals(other$currentUserNanoId)) {
            return false;
        }
        String this$currentUserName = this.getCurrentUserName();
        String other$currentUserName = other.getCurrentUserName();
        if (this$currentUserName == null ? other$currentUserName != null : !this$currentUserName.equals(other$currentUserName)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        String this$occupantMentorGroupName = this.getOccupantMentorGroupName();
        String other$occupantMentorGroupName = other.getOccupantMentorGroupName();
        if (this$occupantMentorGroupName == null ? other$occupantMentorGroupName != null : !this$occupantMentorGroupName.equals(other$occupantMentorGroupName)) {
            return false;
        }
        String this$reservedStartMonth = this.getReservedStartMonth();
        String other$reservedStartMonth = other.getReservedStartMonth();
        if (this$reservedStartMonth == null ? other$reservedStartMonth != null : !this$reservedStartMonth.equals(other$reservedStartMonth)) {
            return false;
        }
        String this$reservedEndMonth = this.getReservedEndMonth();
        String other$reservedEndMonth = other.getReservedEndMonth();
        if (this$reservedEndMonth == null ? other$reservedEndMonth != null : !this$reservedEndMonth.equals(other$reservedEndMonth)) {
            return false;
        }
        String this$unrentableReason = this.getUnrentableReason();
        String other$unrentableReason = other.getUnrentableReason();
        return !(this$unrentableReason == null ? other$unrentableReason != null : !this$unrentableReason.equals(other$unrentableReason));
    }

        protected boolean canEqual(Object other) {
        return other instanceof WorkstationVo;
    }

        public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Boolean $rentable = this.getRentable();
        result = result * 59 + ($rentable == null ? 43 : ((Object)$rentable).hashCode());
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
        String $nanoId = this.getNanoId();
        result = result * 59 + ($nanoId == null ? 43 : $nanoId.hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        String $labNanoId = this.getLabNanoId();
        result = result * 59 + ($labNanoId == null ? 43 : $labNanoId.hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        String $monthStatus = this.getMonthStatus();
        result = result * 59 + ($monthStatus == null ? 43 : $monthStatus.hashCode());
        String $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        LocalDateTime $occupiedAt = this.getOccupiedAt();
        result = result * 59 + ($occupiedAt == null ? 43 : ((Object)$occupiedAt).hashCode());
        String $currentUserNanoId = this.getCurrentUserNanoId();
        result = result * 59 + ($currentUserNanoId == null ? 43 : $currentUserNanoId.hashCode());
        String $currentUserName = this.getCurrentUserName();
        result = result * 59 + ($currentUserName == null ? 43 : $currentUserName.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        String $occupantMentorGroupName = this.getOccupantMentorGroupName();
        result = result * 59 + ($occupantMentorGroupName == null ? 43 : $occupantMentorGroupName.hashCode());
        String $reservedStartMonth = this.getReservedStartMonth();
        result = result * 59 + ($reservedStartMonth == null ? 43 : $reservedStartMonth.hashCode());
        String $reservedEndMonth = this.getReservedEndMonth();
        result = result * 59 + ($reservedEndMonth == null ? 43 : $reservedEndMonth.hashCode());
        String $unrentableReason = this.getUnrentableReason();
        result = result * 59 + ($unrentableReason == null ? 43 : $unrentableReason.hashCode());
        return result;
    }

        public String toString() {
        return "WorkstationVo(nanoId=" + this.getNanoId() + ", code=" + this.getCode() + ", labNanoId=" + this.getLabNanoId() + ", status=" + this.getStatus() + ", monthStatus=" + this.getMonthStatus() + ", type=" + this.getType() + ", occupiedAt=" + String.valueOf(this.getOccupiedAt()) + ", currentUserNanoId=" + this.getCurrentUserNanoId() + ", currentUserName=" + this.getCurrentUserName() + ", description=" + this.getDescription() + ", occupantMentorGroupName=" + this.getOccupantMentorGroupName() + ", reservedStartMonth=" + this.getReservedStartMonth() + ", reservedEndMonth=" + this.getReservedEndMonth() + ", rentable=" + this.getRentable() + ", unrentableReason=" + this.getUnrentableReason() + ", posX=" + this.getPosX() + ", posY=" + this.getPosY() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ", zIndex=" + this.getZIndex() + ", rotation=" + this.getRotation() + ")";
    }
}

