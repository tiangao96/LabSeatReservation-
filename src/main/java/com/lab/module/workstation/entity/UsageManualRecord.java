package com.lab.module.workstation.entity;

import com.lab.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "usage_manual_record")
@SQLRestriction(value = "is_deleted = 0")
public class UsageManualRecord extends BaseEntity {
    @Column(length = 100)
    private String teacherName;
    @Column(nullable = false, length = 50)
    private String labCode;
    private Integer quantity = 1;
    @Column(nullable = false, length = 7)
    private String startMonth;
    @Column(nullable = false, length = 7)
    private String endMonth;
    @Column(precision = 10, scale = 2)
    private BigDecimal area;
    @Column(length = 500)
    private String remark;
    public UsageManualRecord() {
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
        return "UsageManualRecord(teacherName=" + this.getTeacherName() + ", labCode=" + this.getLabCode() + ", quantity=" + this.getQuantity() + ", startMonth=" + this.getStartMonth() + ", endMonth=" + this.getEndMonth() + ", area=" + String.valueOf(this.getArea()) + ", remark=" + this.getRemark() + ")";
    }
}
