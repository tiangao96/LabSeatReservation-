package com.lab.module.rbac.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class UserCreateRequest {
    @NotBlank(message="\u7528\u6237\u540d\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u7528\u6237\u540d\u4e0d\u80fd\u4e3a\u7a7a") String username;
    @NotBlank(message="\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u5bc6\u7801\u4e0d\u80fd\u4e3a\u7a7a") String password;
    private String name;
    private String email;
    private String mobile;
    private String deptNanoId;
    private String mentorGroupNanoId;
    private List<String> roleNanoIds;

    public UserCreateRequest() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMobile() {
        return this.mobile;
    }

    public String getDeptNanoId() {
        return this.deptNanoId;
    }

    public String getMentorGroupNanoId() {
        return this.mentorGroupNanoId;
    }

    public List<String> getRoleNanoIds() {
        return this.roleNanoIds;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setDeptNanoId(String deptNanoId) {
        this.deptNanoId = deptNanoId;
    }

    public void setMentorGroupNanoId(String mentorGroupNanoId) {
        this.mentorGroupNanoId = mentorGroupNanoId;
    }

    public void setRoleNanoIds(List<String> roleNanoIds) {
        this.roleNanoIds = roleNanoIds;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserCreateRequest)) {
            return false;
        }
        UserCreateRequest other = (UserCreateRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$username = this.getUsername();
        String other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) {
            return false;
        }
        String this$password = this.getPassword();
        String other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$mobile = this.getMobile();
        String other$mobile = other.getMobile();
        if (this$mobile == null ? other$mobile != null : !this$mobile.equals(other$mobile)) {
            return false;
        }
        String this$deptNanoId = this.getDeptNanoId();
        String other$deptNanoId = other.getDeptNanoId();
        if (this$deptNanoId == null ? other$deptNanoId != null : !this$deptNanoId.equals(other$deptNanoId)) {
            return false;
        }
        String this$mentorGroupNanoId = this.getMentorGroupNanoId();
        String other$mentorGroupNanoId = other.getMentorGroupNanoId();
        if (this$mentorGroupNanoId == null ? other$mentorGroupNanoId != null : !this$mentorGroupNanoId.equals(other$mentorGroupNanoId)) {
            return false;
        }
        List this$roleNanoIds = this.getRoleNanoIds();
        List other$roleNanoIds = other.getRoleNanoIds();
        return !(this$roleNanoIds == null ? other$roleNanoIds != null : !((Object)this$roleNanoIds).equals(other$roleNanoIds));
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserCreateRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        String $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $mobile = this.getMobile();
        result = result * 59 + ($mobile == null ? 43 : $mobile.hashCode());
        String $deptNanoId = this.getDeptNanoId();
        result = result * 59 + ($deptNanoId == null ? 43 : $deptNanoId.hashCode());
        String $mentorGroupNanoId = this.getMentorGroupNanoId();
        result = result * 59 + ($mentorGroupNanoId == null ? 43 : $mentorGroupNanoId.hashCode());
        List $roleNanoIds = this.getRoleNanoIds();
        result = result * 59 + ($roleNanoIds == null ? 43 : ((Object)$roleNanoIds).hashCode());
        return result;
    }

    public String toString() {
        return "UserCreateRequest(username=" + this.getUsername() + ", password=" + this.getPassword() + ", name=" + this.getName() + ", email=" + this.getEmail() + ", mobile=" + this.getMobile() + ", deptNanoId=" + this.getDeptNanoId() + ", mentorGroupNanoId=" + this.getMentorGroupNanoId() + ", roleNanoIds=" + String.valueOf(this.getRoleNanoIds()) + ")";
    }
}

