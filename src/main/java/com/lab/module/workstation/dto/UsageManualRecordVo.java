package com.lab.module.workstation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UsageManualRecordVo {
    private String nanoId;
    private String teacherName;
    private String labCode;
    private Integer quantity;
    private String startMonth;
    private String endMonth;
    private BigDecimal area;
    private String remark;
    private LocalDateTime sysCreateDatetime;
    public UsageManualRecordVo() {
    }
    public String getNanoId() {
        return this.nanoId;
    }
    public String getTeacherName() {
        return this.teacherName;
    }
    public String getLabCode() {
        return this.labCode;
    }
    public Integer getQuantity() {
        return this.quantity;
    }
    public String getStartMonth() {
        return this.startMonth;
    }
    public String getEndMonth() {
        return this.endMonth;
    }
    public BigDecimal getArea() {
        return this.area;
    }
    public String getRemark() {
        return this.remark;
    }
    public LocalDateTime getSysCreateDatetime() {
        return this.sysCreateDatetime;
    }
    public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public void setLabCode(String labCode) {
        this.labCode = labCode;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }
    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }
    public void setArea(BigDecimal area) {
        this.area = area;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public void setSysCreateDatetime(LocalDateTime sysCreateDatetime) {
        this.sysCreateDatetime = sysCreateDatetime;
    }
    public String toString() {
        return "UsageManualRecordVo(nanoId=" + this.getNanoId() + ", teacherName=" + this.getTeacherName() + ", labCode=" + this.getLabCode() + ", quantity=" + this.getQuantity() + ", startMonth=" + this.getStartMonth() + ", endMonth=" + this.getEndMonth() + ", area=" + String.valueOf(this.getArea()) + ", remark=" + this.getRemark() + ", sysCreateDatetime=" + String.valueOf(this.getSysCreateDatetime()) + ")";
    }
}
