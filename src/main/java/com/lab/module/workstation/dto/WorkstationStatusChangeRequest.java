package com.lab.module.workstation.dto;

import jakarta.validation.constraints.NotBlank;

public class WorkstationStatusChangeRequest {
    @NotBlank(message="\u76ee\u6807\u72b6\u6001\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u76ee\u6807\u72b6\u6001\u4e0d\u80fd\u4e3a\u7a7a") String status;

    public WorkstationStatusChangeRequest() {
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WorkstationStatusChangeRequest)) {
            return false;
        }
        WorkstationStatusChangeRequest other = (WorkstationStatusChangeRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$status = this.getStatus();
        String other$status = other.getStatus();
        return !(this$status == null ? other$status != null : !this$status.equals(other$status));
    }

    protected boolean canEqual(Object other) {
        return other instanceof WorkstationStatusChangeRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        return result;
    }

    public String toString() {
        return "WorkstationStatusChangeRequest(status=" + this.getStatus() + ")";
    }
}

