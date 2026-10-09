package com.lab.module.rbac.dto;


public class ChangeDeptRequest {
    private String deptNanoId;

    public ChangeDeptRequest() {
    }

    public String getDeptNanoId() {
        return this.deptNanoId;
    }

    public void setDeptNanoId(String deptNanoId) {
        this.deptNanoId = deptNanoId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ChangeDeptRequest)) {
            return false;
        }
        ChangeDeptRequest other = (ChangeDeptRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$deptNanoId = this.getDeptNanoId();
        String other$deptNanoId = other.getDeptNanoId();
        return !(this$deptNanoId == null ? other$deptNanoId != null : !this$deptNanoId.equals(other$deptNanoId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ChangeDeptRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $deptNanoId = this.getDeptNanoId();
        result = result * 59 + ($deptNanoId == null ? 43 : $deptNanoId.hashCode());
        return result;
    }

    public String toString() {
        return "ChangeDeptRequest(deptNanoId=" + this.getDeptNanoId() + ")";
    }
}

