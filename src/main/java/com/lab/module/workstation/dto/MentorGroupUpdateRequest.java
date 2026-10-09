package com.lab.module.workstation.dto;

public class MentorGroupUpdateRequest {
    private String mentorGroupNanoId;
    private String mentorGroupName;

    public MentorGroupUpdateRequest() {
    }

    public String getMentorGroupNanoId() {
        return this.mentorGroupNanoId;
    }

    public void setMentorGroupNanoId(String mentorGroupNanoId) {
        this.mentorGroupNanoId = mentorGroupNanoId;
    }

    public String getMentorGroupName() {
        return this.mentorGroupName;
    }

    public void setMentorGroupName(String mentorGroupName) {
        this.mentorGroupName = mentorGroupName;
    }
}
