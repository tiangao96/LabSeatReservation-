package com.lab.module.rbac.dto;


public class ChangeMentorGroupRequest {
    private String mentorGroupNanoId;

    public ChangeMentorGroupRequest() {
    }

    public String getMentorGroupNanoId() {
        return this.mentorGroupNanoId;
    }

    public void setMentorGroupNanoId(String mentorGroupNanoId) {
        this.mentorGroupNanoId = mentorGroupNanoId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ChangeMentorGroupRequest)) {
            return false;
        }
        ChangeMentorGroupRequest other = (ChangeMentorGroupRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$mentorGroupNanoId = this.getMentorGroupNanoId();
        String other$mentorGroupNanoId = other.getMentorGroupNanoId();
        return !(this$mentorGroupNanoId == null ? other$mentorGroupNanoId != null : !this$mentorGroupNanoId.equals(other$mentorGroupNanoId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof ChangeMentorGroupRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $mentorGroupNanoId = this.getMentorGroupNanoId();
        result = result * 59 + ($mentorGroupNanoId == null ? 43 : $mentorGroupNanoId.hashCode());
        return result;
    }

    public String toString() {
        return "ChangeMentorGroupRequest(mentorGroupNanoId=" + this.getMentorGroupNanoId() + ")";
    }
}

