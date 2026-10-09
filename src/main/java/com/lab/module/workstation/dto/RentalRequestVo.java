package com.lab.module.workstation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 工位申请 VO。
 * 学生公开申请时 studentName/studentNo/gradeName/mentorName 有值。
 */
public class RentalRequestVo {
    private String nanoId;
    private String requesterMentorGroupNanoId;
    private String requesterMentorGroupName;
    private String labNanoId;
    private String labName;
    private String startMonth;
    private String endMonth;
    private BigDecimal workstationArea;
    private Integer monthCount;
    private Integer workstationCount;
    private String status;
    private String reason;
    private String approverUserNanoId;
    private String approverUserName;
    private String rejectReason;
    private LocalDateTime approvedAt;
    private LocalDateTime sysCreateDatetime;
    private List<String> workstationNanoIds;
    private List<RentalItemVo> workstations;
    /** 学生申请: 姓名 */
    private String studentName;
    /** 学生申请: 学号 */
    private String studentNo;
    /** 学生申请: 年级号 */
    private String gradeName;
    /** 学生申请: 导师姓名 */
    private String mentorName;

    public RentalRequestVo() {
    }

    public String getNanoId() {
        return this.nanoId;
    }

    public String getRequesterMentorGroupNanoId() {
        return this.requesterMentorGroupNanoId;
    }

    public String getRequesterMentorGroupName() {
        return this.requesterMentorGroupName;
    }

    public String getLabNanoId() {
        return this.labNanoId;
    }

    public String getLabName() {
        return this.labName;
    }

    public String getStartMonth() {
        return this.startMonth;
    }

    public String getEndMonth() {
        return this.endMonth;
    }

    public BigDecimal getWorkstationArea() {
        return this.workstationArea;
    }

    public Integer getMonthCount() {
        return this.monthCount;
    }

    public Integer getWorkstationCount() {
        return this.workstationCount;
    }

    public String getStatus() {
        return this.status;
    }

    public String getReason() {
        return this.reason;
    }

    public String getApproverUserNanoId() {
        return this.approverUserNanoId;
    }

    public String getApproverUserName() {
        return this.approverUserName;
    }

    public String getRejectReason() {
        return this.rejectReason;
    }

    public LocalDateTime getApprovedAt() {
        return this.approvedAt;
    }

    public LocalDateTime getSysCreateDatetime() {
        return this.sysCreateDatetime;
    }

    public List<String> getWorkstationNanoIds() {
        return this.workstationNanoIds;
    }

    public List<RentalItemVo> getWorkstations() {
        return this.workstations;
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

    public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }

    public void setRequesterMentorGroupNanoId(String requesterMentorGroupNanoId) {
        this.requesterMentorGroupNanoId = requesterMentorGroupNanoId;
    }

    public void setRequesterMentorGroupName(String requesterMentorGroupName) {
        this.requesterMentorGroupName = requesterMentorGroupName;
    }

    public void setLabNanoId(String labNanoId) {
        this.labNanoId = labNanoId;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public void setWorkstationArea(BigDecimal workstationArea) {
        this.workstationArea = workstationArea;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public void setWorkstationCount(Integer workstationCount) {
        this.workstationCount = workstationCount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setApproverUserNanoId(String approverUserNanoId) {
        this.approverUserNanoId = approverUserNanoId;
    }

    public void setApproverUserName(String approverUserName) {
        this.approverUserName = approverUserName;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
    }

    public void setSysCreateDatetime(LocalDateTime sysCreateDatetime) {
        this.sysCreateDatetime = sysCreateDatetime;
    }

    public void setWorkstationNanoIds(List<String> workstationNanoIds) {
        this.workstationNanoIds = workstationNanoIds;
    }

    public void setWorkstations(List<RentalItemVo> workstations) {
        this.workstations = workstations;
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
