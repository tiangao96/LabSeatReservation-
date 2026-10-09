package com.lab.module.workstation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lab.module.workstation.entity.WorkstationType;
import jakarta.validation.constraints.NotBlank;

public class WorkstationCreateRequest {
    @NotBlank(message="\u5de5\u4f4d\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u5de5\u4f4d\u7f16\u53f7\u4e0d\u80fd\u4e3a\u7a7a") String code;
    @NotBlank(message="\u6240\u5c5e\u5b9e\u9a8c\u5ba4\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u6240\u5c5e\u5b9e\u9a8c\u5ba4\u4e0d\u80fd\u4e3a\u7a7a") String labNanoId;
    private WorkstationType type;
    private String description;
    private Integer posX;
    private Integer posY;
    private Integer width;
    private Integer height;
    @JsonProperty(value="zIndex")
    private Integer zIndex;
    private Integer rotation;

    public WorkstationCreateRequest() {
    }

    public String getCode() {
        return this.code;
    }

    public String getLabNanoId() {
        return this.labNanoId;
    }

    public WorkstationType getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
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

    public void setLabNanoId(String labNanoId) {
        this.labNanoId = labNanoId;
    }

    public void setType(WorkstationType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof WorkstationCreateRequest)) {
            return false;
        }
        WorkstationCreateRequest other = (WorkstationCreateRequest)o;
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
        WorkstationType this$type = this.getType();
        WorkstationType other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        return !(this$description == null ? other$description != null : !this$description.equals(other$description));
    }

    protected boolean canEqual(Object other) {
        return other instanceof WorkstationCreateRequest;
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
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        String $labNanoId = this.getLabNanoId();
        result = result * 59 + ($labNanoId == null ? 43 : $labNanoId.hashCode());
        WorkstationType $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    public String toString() {
        return "WorkstationCreateRequest(code=" + this.getCode() + ", labNanoId=" + this.getLabNanoId() + ", type=" + String.valueOf(this.getType()) + ", description=" + this.getDescription() + ", posX=" + this.getPosX() + ", posY=" + this.getPosY() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ", zIndex=" + this.getZIndex() + ", rotation=" + this.getRotation() + ")";
    }
}

