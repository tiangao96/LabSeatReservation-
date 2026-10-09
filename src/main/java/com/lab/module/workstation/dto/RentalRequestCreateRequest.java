package com.lab.module.workstation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 工位申请请求。
 * 导师组月租申请: mentorGroupNanoId 必填, 学生字段可空。
 * 学生公开申请: studentName/studentNo/gradeName/mentorName 必填, 走公开通道。
 */
public class RentalRequestCreateRequest {
    private String labNanoId;
    @NotEmpty(message = "至少选择一个工位")
    private List<String> workstationNanoIds;
    @NotBlank(message = "起始月不能为空")
    private String startMonth;
    @NotBlank(message = "结束月不能为空")
    private String endMonth;
    @NotBlank(message = "申请导师组不能为空")
    private String mentorGroupNanoId;
    private String reason;
    /** 学生申请: 姓名 */
    private String studentName;
    /** 学生申请: 学号 */
    private String studentNo;
    /** 学生申请: 年级号(如 2023级) */
    private String gradeName;
    /** 学生申请: 导师姓名 */
    private String mentorName;

    public RentalRequestCreateRequest() {
    }

    public String getLabNanoId() {
        return this.labNanoId;
    }

    public List<String> getWorkstationNanoIds() {
        return this.workstationNanoIds;
    }

    public String getStartMonth() {
        return this.startMonth;
    }

    public String getEndMonth() {
        return this.endMonth;
    }

    public String getMentorGroupNanoId() {
        return this.mentorGroupNanoId;
    }

    public String getReason() {
        return this.reason;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public String getStudentNo() {
        return this.studentNo;
    }

    public String getGradeName() {
        return this.gradeName;
    }

    public String getMentorName() {
        return this.mentorName;
    }

    public void setLabNanoId(String labNanoId) {
        this.labNanoId = labNanoId;
    }

    public void setWorkstationNanoIds(List<String> workstationNanoIds) {
        this.workstationNanoIds = workstationNanoIds;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public void setMentorGroupNanoId(String mentorGroupNanoId) {
        this.mentorGroupNanoId = mentorGroupNanoId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }
}
