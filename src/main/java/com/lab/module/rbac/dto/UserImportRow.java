package com.lab.module.rbac.dto;

import cn.idev.excel.annotation.ExcelProperty;

public class UserImportRow {
    @ExcelProperty(value={"\u7528\u6237\u540d"}, index=0)
    private String username;
    @ExcelProperty(value={"\u5bc6\u7801"}, index=1)
    private String password;
    @ExcelProperty(value={"\u59d3\u540d"}, index=2)
    private String name;
    @ExcelProperty(value={"\u90ae\u7bb1"}, index=3)
    private String email;
    @ExcelProperty(value={"\u624b\u673a\u53f7"}, index=4)
    private String mobile;
    @ExcelProperty(value={"\u90e8\u95e8\u540d\u79f0"}, index=5)
    private String deptName;
    @ExcelProperty(value={"\u5bfc\u5e08\u7ec4\u540d\u79f0"}, index=6)
    private String mentorGroupName;
    @ExcelProperty(value={"\u89d2\u8272\u7f16\u7801"}, index=7)
    private String roleCodes;

    public UserImportRow() {
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

    public String getDeptName() {
        return this.deptName;
    }

    public String getMentorGroupName() {
        return this.mentorGroupName;
    }

    public String getRoleCodes() {
        return this.roleCodes;
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

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setMentorGroupName(String mentorGroupName) {
        this.mentorGroupName = mentorGroupName;
    }

    public void setRoleCodes(String roleCodes) {
        this.roleCodes = roleCodes;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserImportRow)) {
            return false;
        }
        UserImportRow other = (UserImportRow)o;
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
        String this$deptName = this.getDeptName();
        String other$deptName = other.getDeptName();
        if (this$deptName == null ? other$deptName != null : !this$deptName.equals(other$deptName)) {
            return false;
        }
        String this$mentorGroupName = this.getMentorGroupName();
        String other$mentorGroupName = other.getMentorGroupName();
        if (this$mentorGroupName == null ? other$mentorGroupName != null : !this$mentorGroupName.equals(other$mentorGroupName)) {
            return false;
        }
        String this$roleCodes = this.getRoleCodes();
        String other$roleCodes = other.getRoleCodes();
        return !(this$roleCodes == null ? other$roleCodes != null : !this$roleCodes.equals(other$roleCodes));
    }

    protected boolean canEqual(Object other) {
        return other instanceof UserImportRow;
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
        String $deptName = this.getDeptName();
        result = result * 59 + ($deptName == null ? 43 : $deptName.hashCode());
        String $mentorGroupName = this.getMentorGroupName();
        result = result * 59 + ($mentorGroupName == null ? 43 : $mentorGroupName.hashCode());
        String $roleCodes = this.getRoleCodes();
        result = result * 59 + ($roleCodes == null ? 43 : $roleCodes.hashCode());
        return result;
    }

    public String toString() {
        return "UserImportRow(username=" + this.getUsername() + ", password=" + this.getPassword() + ", name=" + this.getName() + ", email=" + this.getEmail() + ", mobile=" + this.getMobile() + ", deptName=" + this.getDeptName() + ", mentorGroupName=" + this.getMentorGroupName() + ", roleCodes=" + this.getRoleCodes() + ")";
    }
}

