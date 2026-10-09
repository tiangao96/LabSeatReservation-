package com.lab.module.workstation.dto;

import com.lab.module.workstation.dto.StudentUsageItemVo;
import java.math.BigDecimal;
import java.util.List;

public class MentorGroupStudentUsageVo {
    private String mentorGroupNanoId;
    private String mentorGroupName;
    private String startMonth;
    private String endMonth;
    private Integer distinctStudentCount;
    private Integer distinctWorkstationCount;
    private Integer totalWorkstationMonths;
    private BigDecimal totalAreaMonths;
    private List<StudentUsageItemVo> items;

    public MentorGroupStudentUsageVo() {
    }

    public String getMentorGroupNanoId() {
        return this.mentorGroupNanoId;
    }

    public String getMentorGroupName() {
        return this.mentorGroupName;
    }

    public String getStartMonth() {
        return this.startMonth;
    }

    public String getEndMonth() {
        return this.endMonth;
    }

    public Integer getDistinctStudentCount() {
        return this.distinctStudentCount;
    }

    public Integer getDistinctWorkstationCount() {
        return this.distinctWorkstationCount;
    }

    public Integer getTotalWorkstationMonths() {
        return this.totalWorkstationMonths;
    }

    public BigDecimal getTotalAreaMonths() {
        return this.totalAreaMonths;
    }

    public List<StudentUsageItemVo> getItems() {
        return this.items;
    }

    public void setMentorGroupNanoId(String mentorGroupNanoId) {
        this.mentorGroupNanoId = mentorGroupNanoId;
    }

    public void setMentorGroupName(String mentorGroupName) {
        this.mentorGroupName = mentorGroupName;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public void setDistinctStudentCount(Integer distinctStudentCount) {
        this.distinctStudentCount = distinctStudentCount;
    }

    public void setDistinctWorkstationCount(Integer distinctWorkstationCount) {
        this.distinctWorkstationCount = distinctWorkstationCount;
    }

    public void setTotalWorkstationMonths(Integer totalWorkstationMonths) {
        this.totalWorkstationMonths = totalWorkstationMonths;
    }

    public void setTotalAreaMonths(BigDecimal totalAreaMonths) {
        this.totalAreaMonths = totalAreaMonths;
    }

    public void setItems(List<StudentUsageItemVo> items) {
        this.items = items;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MentorGroupStudentUsageVo)) {
            return false;
        }
        MentorGroupStudentUsageVo other = (MentorGroupStudentUsageVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$distinctStudentCount = this.getDistinctStudentCount();
        Integer other$distinctStudentCount = other.getDistinctStudentCount();
        if (this$distinctStudentCount == null ? other$distinctStudentCount != null : !((Object)this$distinctStudentCount).equals(other$distinctStudentCount)) {
            return false;
        }
        Integer this$distinctWorkstationCount = this.getDistinctWorkstationCount();
        Integer other$distinctWorkstationCount = other.getDistinctWorkstationCount();
        if (this$distinctWorkstationCount == null ? other$distinctWorkstationCount != null : !((Object)this$distinctWorkstationCount).equals(other$distinctWorkstationCount)) {
            return false;
        }
        Integer this$totalWorkstationMonths = this.getTotalWorkstationMonths();
        Integer other$totalWorkstationMonths = other.getTotalWorkstationMonths();
        if (this$totalWorkstationMonths == null ? other$totalWorkstationMonths != null : !((Object)this$totalWorkstationMonths).equals(other$totalWorkstationMonths)) {
            return false;
        }
        String this$mentorGroupNanoId = this.getMentorGroupNanoId();
        String other$mentorGroupNanoId = other.getMentorGroupNanoId();
        if (this$mentorGroupNanoId == null ? other$mentorGroupNanoId != null : !this$mentorGroupNanoId.equals(other$mentorGroupNanoId)) {
            return false;
        }
        String this$mentorGroupName = this.getMentorGroupName();
        String other$mentorGroupName = other.getMentorGroupName();
        if (this$mentorGroupName == null ? other$mentorGroupName != null : !this$mentorGroupName.equals(other$mentorGroupName)) {
            return false;
        }
        String this$startMonth = this.getStartMonth();
        String other$startMonth = other.getStartMonth();
        if (this$startMonth == null ? other$startMonth != null : !this$startMonth.equals(other$startMonth)) {
            return false;
        }
        String this$endMonth = this.getEndMonth();
        String other$endMonth = other.getEndMonth();
        if (this$endMonth == null ? other$endMonth != null : !this$endMonth.equals(other$endMonth)) {
            return false;
        }
        BigDecimal this$totalAreaMonths = this.getTotalAreaMonths();
        BigDecimal other$totalAreaMonths = other.getTotalAreaMonths();
        if (this$totalAreaMonths == null ? other$totalAreaMonths != null : !((Object)this$totalAreaMonths).equals(other$totalAreaMonths)) {
            return false;
        }
        List this$items = this.getItems();
        List other$items = other.getItems();
        return !(this$items == null ? other$items != null : !((Object)this$items).equals(other$items));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MentorGroupStudentUsageVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $distinctStudentCount = this.getDistinctStudentCount();
        result = result * 59 + ($distinctStudentCount == null ? 43 : ((Object)$distinctStudentCount).hashCode());
        Integer $distinctWorkstationCount = this.getDistinctWorkstationCount();
        result = result * 59 + ($distinctWorkstationCount == null ? 43 : ((Object)$distinctWorkstationCount).hashCode());
        Integer $totalWorkstationMonths = this.getTotalWorkstationMonths();
        result = result * 59 + ($totalWorkstationMonths == null ? 43 : ((Object)$totalWorkstationMonths).hashCode());
        String $mentorGroupNanoId = this.getMentorGroupNanoId();
        result = result * 59 + ($mentorGroupNanoId == null ? 43 : $mentorGroupNanoId.hashCode());
        String $mentorGroupName = this.getMentorGroupName();
        result = result * 59 + ($mentorGroupName == null ? 43 : $mentorGroupName.hashCode());
        String $startMonth = this.getStartMonth();
        result = result * 59 + ($startMonth == null ? 43 : $startMonth.hashCode());
        String $endMonth = this.getEndMonth();
        result = result * 59 + ($endMonth == null ? 43 : $endMonth.hashCode());
        BigDecimal $totalAreaMonths = this.getTotalAreaMonths();
        result = result * 59 + ($totalAreaMonths == null ? 43 : ((Object)$totalAreaMonths).hashCode());
        List $items = this.getItems();
        result = result * 59 + ($items == null ? 43 : ((Object)$items).hashCode());
        return result;
    }

    public String toString() {
        return "MentorGroupStudentUsageVo(mentorGroupNanoId=" + this.getMentorGroupNanoId() + ", mentorGroupName=" + this.getMentorGroupName() + ", startMonth=" + this.getStartMonth() + ", endMonth=" + this.getEndMonth() + ", distinctStudentCount=" + this.getDistinctStudentCount() + ", distinctWorkstationCount=" + this.getDistinctWorkstationCount() + ", totalWorkstationMonths=" + this.getTotalWorkstationMonths() + ", totalAreaMonths=" + String.valueOf(this.getTotalAreaMonths()) + ", items=" + String.valueOf(this.getItems()) + ")";
    }
}

