package com.lab.module.workstation.dto;


public class SeatCandidateVo {
    private String nanoId;
    private String name;

    public SeatCandidateVo() {
    }

    public String getNanoId() {
        return this.nanoId;
    }

    public String getName() {
        return this.name;
    }

    public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SeatCandidateVo)) {
            return false;
        }
        SeatCandidateVo other = (SeatCandidateVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$nanoId = this.getNanoId();
        String other$nanoId = other.getNanoId();
        if (this$nanoId == null ? other$nanoId != null : !this$nanoId.equals(other$nanoId)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        return !(this$name == null ? other$name != null : !this$name.equals(other$name));
    }

    protected boolean canEqual(Object other) {
        return other instanceof SeatCandidateVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $nanoId = this.getNanoId();
        result = result * 59 + ($nanoId == null ? 43 : $nanoId.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "SeatCandidateVo(nanoId=" + this.getNanoId() + ", name=" + this.getName() + ")";
    }
}

