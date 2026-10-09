package com.lab.module.workstation.dto;


public class RentalItemVo {
    private String workstationNanoId;
    private String workstationCode;
    private String endMonth;

    public RentalItemVo() {
    }

    public String getWorkstationNanoId() {
        return this.workstationNanoId;
    }

    public String getWorkstationCode() {
        return this.workstationCode;
    }

    public String getEndMonth() {
        return this.endMonth;
    }

    public void setWorkstationNanoId(String workstationNanoId) {
        this.workstationNanoId = workstationNanoId;
    }

    public void setWorkstationCode(String workstationCode) {
        this.workstationCode = workstationCode;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RentalItemVo)) {
            return false;
        }
        RentalItemVo other = (RentalItemVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$workstationNanoId = this.getWorkstationNanoId();
        String other$workstationNanoId = other.getWorkstationNanoId();
        if (this$workstationNanoId == null ? other$workstationNanoId != null : !this$workstationNanoId.equals(other$workstationNanoId)) {
            return false;
        }
        String this$workstationCode = this.getWorkstationCode();
        String other$workstationCode = other.getWorkstationCode();
        if (this$workstationCode == null ? other$workstationCode != null : !this$workstationCode.equals(other$workstationCode)) {
            return false;
        }
        String this$endMonth = this.getEndMonth();
        String other$endMonth = other.getEndMonth();
        return !(this$endMonth == null ? other$endMonth != null : !this$endMonth.equals(other$endMonth));
    }

    protected boolean canEqual(Object other) {
        return other instanceof RentalItemVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $workstationNanoId = this.getWorkstationNanoId();
        result = result * 59 + ($workstationNanoId == null ? 43 : $workstationNanoId.hashCode());
        String $workstationCode = this.getWorkstationCode();
        result = result * 59 + ($workstationCode == null ? 43 : $workstationCode.hashCode());
        String $endMonth = this.getEndMonth();
        result = result * 59 + ($endMonth == null ? 43 : $endMonth.hashCode());
        return result;
    }

    public String toString() {
        return "RentalItemVo(workstationNanoId=" + this.getWorkstationNanoId() + ", workstationCode=" + this.getWorkstationCode() + ", endMonth=" + this.getEndMonth() + ")";
    }
}

