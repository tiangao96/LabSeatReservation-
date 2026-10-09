package com.lab.module.workstation.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * 学生公开申请工位请求(免登录)。
 */
public class StudentApplyRequest {
    @NotBlank(message = "实验室不能为空")
    private String labNanoId;
    @NotBlank(message = "请选择工位")
    private String workstationNanoId;
    @NotBlank(message = "姓名不能为空")
    private String studentName;
    @NotBlank(message = "学号不能为空")
    private String studentNo;
    @NotBlank(message = "年级不能为空")
    private String gradeName;
    @NotBlank(message = "导师不能为空")
    private String mentorName;
    /** 可选:不传时按导师姓名自动匹配导师组 */
    private String mentorGroupNanoId;
    /** 可选:预计使用到 YYYY-MM, 不传默认次年6月 */
    private String endMonth;

    public StudentApplyRequest() {
    }

    public String getLabNanoId() {
        return this.labNanoId;
    }

    public String getWorkstationNanoId() {
        return this.workstationNanoId;
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

    public String getMentorGroupNanoId() {
        return this.mentorGroupNanoId;
    }

    public String getEndMonth() {
        return this.endMonth;
    }

    public void setLabNanoId(String labNanoId) {
        this.labNanoId = labNanoId;
    }

    public void setWorkstationNanoId(String workstationNanoId) {
        this.workstationNanoId = workstationNanoId;
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

    public void setMentorGroupNanoId(String mentorGroupNanoId) {
        this.mentorGroupNanoId = mentorGroupNanoId;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }
}
