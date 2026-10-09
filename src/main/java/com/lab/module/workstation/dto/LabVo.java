package com.lab.module.workstation.dto;

import java.math.BigDecimal;

public class LabVo {
    private String nanoId;
    private String name;
    private String code;
    private String location;
    private Integer capacity;
    private String status;
    private String description;
    private String deptNanoId;
    private String deptName;
    private BigDecimal area;
    private Integer canvasWidth;
    private Integer canvasHeight;

    public LabVo() {
    }

    public String getNanoId() {
        return this.nanoId;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getLocation() {
        return this.location;
    }

    public Integer getCapacity() {
        return this.capacity;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDeptNanoId() {
        return this.deptNanoId;
    }

    public String getDeptName() {
        return this.deptName;
    }

    public BigDecimal getArea() {
        return this.area;
    }

    public Integer getCanvasWidth() {
        return this.canvasWidth;
    }

    public Integer getCanvasHeight() {
        return this.canvasHeight;
    }

    public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeptNanoId(String deptNanoId) {
        this.deptNanoId = deptNanoId;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void setCanvasWidth(Integer canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public void setCanvasHeight(Integer canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LabVo)) {
            return false;
        }
        LabVo other = (LabVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$capacity = this.getCapacity();
        Integer other$capacity = other.getCapacity();
        if (this$capacity == null ? other$capacity != null : !((Object)this$capacity).equals(other$capacity)) {
            return false;
        }
        Integer this$canvasWidth = this.getCanvasWidth();
        Integer other$canvasWidth = other.getCanvasWidth();
        if (this$canvasWidth == null ? other$canvasWidth != null : !((Object)this$canvasWidth).equals(other$canvasWidth)) {
            return false;
        }
        Integer this$canvasHeight = this.getCanvasHeight();
        Integer other$canvasHeight = other.getCanvasHeight();
        if (this$canvasHeight == null ? other$canvasHeight != null : !((Object)this$canvasHeight).equals(other$canvasHeight)) {
            return false;
        }
        String this$nanoId = this.getNanoId();
        String other$nanoId = other.getNanoId();
        if (this$nanoId == null ? other$nanoId != null : !this$nanoId.equals(other$nanoId)) {
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
        String this$location = this.getLocation();
        String other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        String this$deptNanoId = this.getDeptNanoId();
        String other$deptNanoId = other.getDeptNanoId();
        if (this$deptNanoId == null ? other$deptNanoId != null : !this$deptNanoId.equals(other$deptNanoId)) {
            return false;
        }
        String this$deptName = this.getDeptName();
        String other$deptName = other.getDeptName();
        if (this$deptName == null ? other$deptName != null : !this$deptName.equals(other$deptName)) {
            return false;
        }
        BigDecimal this$area = this.getArea();
        BigDecimal other$area = other.getArea();
        return !(this$area == null ? other$area != null : !((Object)this$area).equals(other$area));
    }

    protected boolean canEqual(Object other) {
        return other instanceof LabVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $capacity = this.getCapacity();
        result = result * 59 + ($capacity == null ? 43 : ((Object)$capacity).hashCode());
        Integer $canvasWidth = this.getCanvasWidth();
        result = result * 59 + ($canvasWidth == null ? 43 : ((Object)$canvasWidth).hashCode());
        Integer $canvasHeight = this.getCanvasHeight();
        result = result * 59 + ($canvasHeight == null ? 43 : ((Object)$canvasHeight).hashCode());
        String $nanoId = this.getNanoId();
        result = result * 59 + ($nanoId == null ? 43 : $nanoId.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        String $location = this.getLocation();
        result = result * 59 + ($location == null ? 43 : $location.hashCode());
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        String $deptNanoId = this.getDeptNanoId();
        result = result * 59 + ($deptNanoId == null ? 43 : $deptNanoId.hashCode());
        String $deptName = this.getDeptName();
        result = result * 59 + ($deptName == null ? 43 : $deptName.hashCode());
        BigDecimal $area = this.getArea();
        result = result * 59 + ($area == null ? 43 : ((Object)$area).hashCode());
        return result;
    }

    public String toString() {
        return "LabVo(nanoId=" + this.getNanoId() + ", name=" + this.getName() + ", code=" + this.getCode() + ", location=" + this.getLocation() + ", capacity=" + this.getCapacity() + ", status=" + this.getStatus() + ", description=" + this.getDescription() + ", deptNanoId=" + this.getDeptNanoId() + ", deptName=" + this.getDeptName() + ", area=" + String.valueOf(this.getArea()) + ", canvasWidth=" + this.getCanvasWidth() + ", canvasHeight=" + this.getCanvasHeight() + ")";
    }
}

