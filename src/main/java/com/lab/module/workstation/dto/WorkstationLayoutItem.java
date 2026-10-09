package com.lab.module.workstation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class WorkstationLayoutItem {
    @NotBlank(message="\u5de5\u4f4d\u6807\u8bc6\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u5de5\u4f4d\u6807\u8bc6\u4e0d\u80fd\u4e3a\u7a7a") String workstationNanoId;
    private Integer posX;
    private Integer posY;
    private Integer width;
    private Integer height;
    @JsonProperty(value="zIndex")
    private Integer zIndex;
    private Integer rotation;

    public WorkstationLayoutItem() {
    }

    public String getWorkstationNanoId() {
        return this.workstationNanoId;
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

    public void setWorkstationNanoId(String workstationNanoId) {
        this.workstationNanoId = workstationNanoId;
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

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WorkstationLayoutItem)) {
            return false;
        }
        WorkstationLayoutItem other = (WorkstationLayoutItem)o;
        if (!other.canEqual((Object)this)) {
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
        String this$workstationNanoId = this.getWorkstationNanoId();
        String other$workstationNanoId = other.getWorkstationNanoId();
        return !(this$workstationNanoId == null ? other$workstationNanoId != null : !this$workstationNanoId.equals(other$workstationNanoId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof WorkstationLayoutItem;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
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
        String $workstationNanoId = this.getWorkstationNanoId();
        result = result * 59 + ($workstationNanoId == null ? 43 : $workstationNanoId.hashCode());
        return result;
    }

    public String toString() {
        return "WorkstationLayoutItem(workstationNanoId=" + this.getWorkstationNanoId() + ", posX=" + this.getPosX() + ", posY=" + this.getPosY() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ", zIndex=" + this.getZIndex() + ", rotation=" + this.getRotation() + ")";
    }
}

