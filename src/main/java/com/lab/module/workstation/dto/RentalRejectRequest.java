package com.lab.module.workstation.dto;


public class RentalRejectRequest {
    private String rejectReason;

    public RentalRejectRequest() {
    }

    public String getRejectReason() {
        return this.rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RentalRejectRequest)) {
            return false;
        }
        RentalRejectRequest other = (RentalRejectRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$rejectReason = this.getRejectReason();
        String other$rejectReason = other.getRejectReason();
        return !(this$rejectReason == null ? other$rejectReason != null : !this$rejectReason.equals(other$rejectReason));
    }

    protected boolean canEqual(Object other) {
        return other instanceof RentalRejectRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $rejectReason = this.getRejectReason();
        result = result * 59 + ($rejectReason == null ? 43 : $rejectReason.hashCode());
        return result;
    }

    public String toString() {
        return "RentalRejectRequest(rejectReason=" + this.getRejectReason() + ")";
    }
}

