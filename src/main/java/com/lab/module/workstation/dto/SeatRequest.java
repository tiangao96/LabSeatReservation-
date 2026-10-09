package com.lab.module.workstation.dto;

import jakarta.validation.constraints.NotBlank;

public class SeatRequest {
    @NotBlank(message="\u88ab\u5360\u9886\u5b66\u751f\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u88ab\u5360\u9886\u5b66\u751f\u4e0d\u80fd\u4e3a\u7a7a") String userNanoId;
    private String startMonth;

    public SeatRequest() {
    }

    public String getUserNanoId() {
        return this.userNanoId;
    }

    public String getStartMonth() {
        return this.startMonth;
    }

    public void setUserNanoId(String userNanoId) {
        this.userNanoId = userNanoId;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SeatRequest)) {
            return false;
        }
        SeatRequest other = (SeatRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$userNanoId = this.getUserNanoId();
        String other$userNanoId = other.getUserNanoId();
        if (this$userNanoId == null ? other$userNanoId != null : !this$userNanoId.equals(other$userNanoId)) {
            return false;
        }
        String this$startMonth = this.getStartMonth();
        String other$startMonth = other.getStartMonth();
        return !(this$startMonth == null ? other$startMonth != null : !this$startMonth.equals(other$startMonth));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SeatRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $userNanoId = this.getUserNanoId();
        result = result * 59 + ($userNanoId == null ? 43 : $userNanoId.hashCode());
        String $startMonth = this.getStartMonth();
        result = result * 59 + ($startMonth == null ? 43 : $startMonth.hashCode());
        return result;
    }

    public String toString() {
        return "SeatRequest(userNanoId=" + this.getUserNanoId() + ", startMonth=" + this.getStartMonth() + ")";
    }
}

