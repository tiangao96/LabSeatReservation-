package com.lab.module.rbac.dto;

import jakarta.validation.constraints.NotBlank;

public class MentorGroupCreateRequest {
    @NotBlank(message="\u5bfc\u5e08\u7ec4\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u5bfc\u5e08\u7ec4\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a") String name;
    private String code;
    private String phone;
    private String email;
    private Integer status;
    private String description;
    private String parentNanoId;
    private String leadNanoId;
    private Integer sort;

    public MentorGroupCreateRequest() {
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

    public String getParentNanoId() {
        return this.parentNanoId;
    }

    public String getLeadNanoId() {
        return this.leadNanoId;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParentNanoId(String parentNanoId) {
        this.parentNanoId = parentNanoId;
    }

    public void setLeadNanoId(String leadNanoId) {
        this.leadNanoId = leadNanoId;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MentorGroupCreateRequest)) {
            return false;
        }
        MentorGroupCreateRequest other = (MentorGroupCreateRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$status = this.getStatus();
        Integer other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)this$status).equals(other$status)) {
            return false;
        }
        Integer this$sort = this.getSort();
        Integer other$sort = other.getSort();
        if (this$sort == null ? other$sort != null : !((Object)this$sort).equals(other$sort)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$code = this.getCode();
        String other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) {
            return false;
        }
        String this$phone = this.getPhone();
        String other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) {
            return false;
        }
        String this$email = this.getEmail();
        String other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) {
            return false;
        }
        String this$parentNanoId = this.getParentNanoId();
        String other$parentNanoId = other.getParentNanoId();
        if (this$parentNanoId == null ? other$parentNanoId != null : !this$parentNanoId.equals(other$parentNanoId)) {
            return false;
        }
        String this$leadNanoId = this.getLeadNanoId();
        String other$leadNanoId = other.getLeadNanoId();
        return !(this$leadNanoId == null ? other$leadNanoId != null : !this$leadNanoId.equals(other$leadNanoId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MentorGroupCreateRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)$status).hashCode());
        Integer $sort = this.getSort();
        result = result * 59 + ($sort == null ? 43 : ((Object)$sort).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        String $phone = this.getPhone();
        result = result * 59 + ($phone == null ? 43 : $phone.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        String $parentNanoId = this.getParentNanoId();
        result = result * 59 + ($parentNanoId == null ? 43 : $parentNanoId.hashCode());
        String $leadNanoId = this.getLeadNanoId();
        result = result * 59 + ($leadNanoId == null ? 43 : $leadNanoId.hashCode());
        return result;
    }

    public String toString() {
        return "MentorGroupCreateRequest(name=" + this.getName() + ", code=" + this.getCode() + ", phone=" + this.getPhone() + ", email=" + this.getEmail() + ", status=" + this.getStatus() + ", description=" + this.getDescription() + ", parentNanoId=" + this.getParentNanoId() + ", leadNanoId=" + this.getLeadNanoId() + ", sort=" + this.getSort() + ")";
    }
}

