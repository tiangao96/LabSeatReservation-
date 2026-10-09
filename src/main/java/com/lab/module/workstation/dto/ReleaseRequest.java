package com.lab.module.workstation.dto;


public class ReleaseRequest {
    private String endMonth;

    public ReleaseRequest() {
    }

    public String getEndMonth() {
        return this.endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReleaseRequest)) {
            return false;
        }
        ReleaseRequest other = (ReleaseRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$endMonth = this.getEndMonth();
        String other$endMonth = other.getEndMonth();
        return !(this$endMonth == null ? other$endMonth != null : !this$endMonth.equals(other$endMonth));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ReleaseRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $endMonth = this.getEndMonth();
        result = result * 59 + ($endMonth == null ? 43 : $endMonth.hashCode());
        return result;
    }

    public String toString() {
        return "ReleaseRequest(endMonth=" + this.getEndMonth() + ")";
    }
}

