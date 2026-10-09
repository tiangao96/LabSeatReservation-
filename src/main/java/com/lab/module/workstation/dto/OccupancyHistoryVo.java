package com.lab.module.workstation.dto;

import java.time.LocalDateTime;

public class OccupancyHistoryVo {
    private String nanoId;
    private String occupancyType;
    private String sourceType;
    private String sourceNanoId;
    private String occupantUserNanoId;
    private String occupantUserName;
    private String mentorGroupName;
    private LocalDateTime occupiedAt;
    private LocalDateTime releasedAt;
    private Integer durationMonths;

    public OccupancyHistoryVo() {
    }

    public String getNanoId() {
        return this.nanoId;
    }

    public String getOccupancyType() {
        return this.occupancyType;
    }

    public String getSourceType() {
        return this.sourceType;
    }

    public String getSourceNanoId() {
        return this.sourceNanoId;
    }

    public String getOccupantUserNanoId() {
        return this.occupantUserNanoId;
    }

    public String getOccupantUserName() {
        return this.occupantUserName;
    }

    public String getMentorGroupName() {
        return this.mentorGroupName;
    }

    public LocalDateTime getOccupiedAt() {
        return this.occupiedAt;
    }

    public LocalDateTime getReleasedAt() {
        return this.releasedAt;
    }

    public Integer getDurationMonths() {
        return this.durationMonths;
    }

    public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }

    public void setOccupancyType(String occupancyType) {
        this.occupancyType = occupancyType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public void setSourceNanoId(String sourceNanoId) {
        this.sourceNanoId = sourceNanoId;
    }

    public void setOccupantUserNanoId(String occupantUserNanoId) {
        this.occupantUserNanoId = occupantUserNanoId;
    }

    public void setOccupantUserName(String occupantUserName) {
        this.occupantUserName = occupantUserName;
    }

    public void setMentorGroupName(String mentorGroupName) {
        this.mentorGroupName = mentorGroupName;
    }

    public void setOccupiedAt(LocalDateTime occupiedAt) {
        this.occupiedAt = occupiedAt;
    }

    public void setReleasedAt(LocalDateTime releasedAt) {
        this.releasedAt = releasedAt;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof OccupancyHistoryVo)) {
            return false;
        }
        OccupancyHistoryVo other = (OccupancyHistoryVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$durationMonths = this.getDurationMonths();
        Integer other$durationMonths = other.getDurationMonths();
        if (this$durationMonths == null ? other$durationMonths != null : !((Object)this$durationMonths).equals(other$durationMonths)) {
            return false;
        }
        String this$nanoId = this.getNanoId();
        String other$nanoId = other.getNanoId();
        if (this$nanoId == null ? other$nanoId != null : !this$nanoId.equals(other$nanoId)) {
            return false;
        }
        String this$occupancyType = this.getOccupancyType();
        String other$occupancyType = other.getOccupancyType();
        if (this$occupancyType == null ? other$occupancyType != null : !this$occupancyType.equals(other$occupancyType)) {
            return false;
        }
        String this$sourceType = this.getSourceType();
        String other$sourceType = other.getSourceType();
        if (this$sourceType == null ? other$sourceType != null : !this$sourceType.equals(other$sourceType)) {
            return false;
        }
        String this$sourceNanoId = this.getSourceNanoId();
        String other$sourceNanoId = other.getSourceNanoId();
        if (this$sourceNanoId == null ? other$sourceNanoId != null : !this$sourceNanoId.equals(other$sourceNanoId)) {
            return false;
        }
        String this$occupantUserNanoId = this.getOccupantUserNanoId();
        String other$occupantUserNanoId = other.getOccupantUserNanoId();
        if (this$occupantUserNanoId == null ? other$occupantUserNanoId != null : !this$occupantUserNanoId.equals(other$occupantUserNanoId)) {
            return false;
        }
        String this$occupantUserName = this.getOccupantUserName();
        String other$occupantUserName = other.getOccupantUserName();
        if (this$occupantUserName == null ? other$occupantUserName != null : !this$occupantUserName.equals(other$occupantUserName)) {
            return false;
        }
        String this$mentorGroupName = this.getMentorGroupName();
        String other$mentorGroupName = other.getMentorGroupName();
        if (this$mentorGroupName == null ? other$mentorGroupName != null : !this$mentorGroupName.equals(other$mentorGroupName)) {
            return false;
        }
        LocalDateTime this$occupiedAt = this.getOccupiedAt();
        LocalDateTime other$occupiedAt = other.getOccupiedAt();
        if (this$occupiedAt == null ? other$occupiedAt != null : !((Object)this$occupiedAt).equals(other$occupiedAt)) {
            return false;
        }
        LocalDateTime this$releasedAt = this.getReleasedAt();
        LocalDateTime other$releasedAt = other.getReleasedAt();
        return !(this$releasedAt == null ? other$releasedAt != null : !((Object)this$releasedAt).equals(other$releasedAt));
    }

    protected boolean canEqual(Object other) {
        return other instanceof OccupancyHistoryVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $durationMonths = this.getDurationMonths();
        result = result * 59 + ($durationMonths == null ? 43 : ((Object)$durationMonths).hashCode());
        String $nanoId = this.getNanoId();
        result = result * 59 + ($nanoId == null ? 43 : $nanoId.hashCode());
        String $occupancyType = this.getOccupancyType();
        result = result * 59 + ($occupancyType == null ? 43 : $occupancyType.hashCode());
        String $sourceType = this.getSourceType();
        result = result * 59 + ($sourceType == null ? 43 : $sourceType.hashCode());
        String $sourceNanoId = this.getSourceNanoId();
        result = result * 59 + ($sourceNanoId == null ? 43 : $sourceNanoId.hashCode());
        String $occupantUserNanoId = this.getOccupantUserNanoId();
        result = result * 59 + ($occupantUserNanoId == null ? 43 : $occupantUserNanoId.hashCode());
        String $occupantUserName = this.getOccupantUserName();
        result = result * 59 + ($occupantUserName == null ? 43 : $occupantUserName.hashCode());
        String $mentorGroupName = this.getMentorGroupName();
        result = result * 59 + ($mentorGroupName == null ? 43 : $mentorGroupName.hashCode());
        LocalDateTime $occupiedAt = this.getOccupiedAt();
        result = result * 59 + ($occupiedAt == null ? 43 : ((Object)$occupiedAt).hashCode());
        LocalDateTime $releasedAt = this.getReleasedAt();
        result = result * 59 + ($releasedAt == null ? 43 : ((Object)$releasedAt).hashCode());
        return result;
    }

    public String toString() {
        return "OccupancyHistoryVo(nanoId=" + this.getNanoId() + ", occupancyType=" + this.getOccupancyType() + ", sourceType=" + this.getSourceType() + ", sourceNanoId=" + this.getSourceNanoId() + ", occupantUserNanoId=" + this.getOccupantUserNanoId() + ", occupantUserName=" + this.getOccupantUserName() + ", mentorGroupName=" + this.getMentorGroupName() + ", occupiedAt=" + String.valueOf(this.getOccupiedAt()) + ", releasedAt=" + String.valueOf(this.getReleasedAt()) + ", durationMonths=" + this.getDurationMonths() + ")";
    }
}

