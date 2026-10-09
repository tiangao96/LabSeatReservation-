package com.lab.module.workstation.dto;

import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class UsageManualRecordRequest {
    private String teacherName;
    @NotBlank(message = "\u623f\u95f4\u53f7\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message = "\u623f\u95f4\u53f7\u4e0d\u80fd\u4e3a\u7a7a") String labCode;
    private Integer quantity;
    @NotBlank(message = "\u8d77\u59cb\u6708\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message = "\u8d77\u59cb\u6708\u4e0d\u80fd\u4e3a\u7a7a") String startMonth;
    @NotBlank(message = "\u7ed3\u675f\u6708\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message = "\u7ed3\u675f\u6708\u4e0d\u80fd\u4e3a\u7a7a") String endMonth;
    private BigDecimal area;
    private String remark;
    public UsageManualRecordRequest() {
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
    public String toString() {
        return "UsageManualRecordRequest(teacherName=" + this.getTeacherName() + ", labCode=" + this.getLabCode() + ", quantity=" + this.getQuantity() + ", startMonth=" + this.getStartMonth() + ", endMonth=" + this.getEndMonth() + ", area=" + String.valueOf(this.getArea()) + ", remark=" + this.getRemark() + ")";
    }
}
