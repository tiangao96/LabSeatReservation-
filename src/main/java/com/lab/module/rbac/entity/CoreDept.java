package com.lab.module.rbac.entity;

import com.lab.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name="core_dept")
@SQLRestriction(value="is_deleted = 0")
public class CoreDept
extends BaseEntity {
    private String name;
    private String code;
    private String deptType;
    private String phone;
    private String email;
    private Integer status;
    private String description;
    private Long parentId;
    private Long leadId;
    private Integer level;
    private String path;
    private String dingtalkDeptId;
    private String wecomDeptId;
    private String feishuDeptId;

    public CoreDept() {
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getDeptType() {
        return this.deptType;
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

    public Long getParentId() {
        return this.parentId;
    }

    public Long getLeadId() {
        return this.leadId;
    }

    public Integer getLevel() {
        return this.level;
    }

    public String getPath() {
        return this.path;
    }

    public String getDingtalkDeptId() {
        return this.dingtalkDeptId;
    }

    public String getWecomDeptId() {
        return this.wecomDeptId;
    }

    public String getFeishuDeptId() {
        return this.feishuDeptId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType;
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

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDingtalkDeptId(String dingtalkDeptId) {
        this.dingtalkDeptId = dingtalkDeptId;
    }

    public void setWecomDeptId(String wecomDeptId) {
        this.wecomDeptId = wecomDeptId;
    }

    public void setFeishuDeptId(String feishuDeptId) {
        this.feishuDeptId = feishuDeptId;
    }

    public String toString() {
        return "CoreDept(name=" + this.getName() + ", code=" + this.getCode() + ", deptType=" + this.getDeptType() + ", phone=" + this.getPhone() + ", email=" + this.getEmail() + ", status=" + this.getStatus() + ", description=" + this.getDescription() + ", parentId=" + this.getParentId() + ", leadId=" + this.getLeadId() + ", level=" + this.getLevel() + ", path=" + this.getPath() + ", dingtalkDeptId=" + this.getDingtalkDeptId() + ", wecomDeptId=" + this.getWecomDeptId() + ", feishuDeptId=" + this.getFeishuDeptId() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CoreDept)) {
            return false;
        }
        CoreDept other = (CoreDept)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Integer this$status = this.getStatus();
        Integer other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)this$status).equals(other$status)) {
            return false;
        }
        Long this$parentId = this.getParentId();
        Long other$parentId = other.getParentId();
        if (this$parentId == null ? other$parentId != null : !((Object)this$parentId).equals(other$parentId)) {
            return false;
        }
        Long this$leadId = this.getLeadId();
        Long other$leadId = other.getLeadId();
        if (this$leadId == null ? other$leadId != null : !((Object)this$leadId).equals(other$leadId)) {
            return false;
        }
        Integer this$level = this.getLevel();
        Integer other$level = other.getLevel();
        if (this$level == null ? other$level != null : !((Object)this$level).equals(other$level)) {
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
        String this$deptType = this.getDeptType();
        String other$deptType = other.getDeptType();
        if (this$deptType == null ? other$deptType != null : !this$deptType.equals(other$deptType)) {
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
        String this$path = this.getPath();
        String other$path = other.getPath();
        if (this$path == null ? other$path != null : !this$path.equals(other$path)) {
            return false;
        }
        String this$dingtalkDeptId = this.getDingtalkDeptId();
        String other$dingtalkDeptId = other.getDingtalkDeptId();
        if (this$dingtalkDeptId == null ? other$dingtalkDeptId != null : !this$dingtalkDeptId.equals(other$dingtalkDeptId)) {
            return false;
        }
        String this$wecomDeptId = this.getWecomDeptId();
        String other$wecomDeptId = other.getWecomDeptId();
        if (this$wecomDeptId == null ? other$wecomDeptId != null : !this$wecomDeptId.equals(other$wecomDeptId)) {
            return false;
        }
        String this$feishuDeptId = this.getFeishuDeptId();
        String other$feishuDeptId = other.getFeishuDeptId();
        return !(this$feishuDeptId == null ? other$feishuDeptId != null : !this$feishuDeptId.equals(other$feishuDeptId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CoreDept;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
        Integer $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)$status).hashCode());
        Long $parentId = this.getParentId();
        result = result * 59 + ($parentId == null ? 43 : ((Object)$parentId).hashCode());
        Long $leadId = this.getLeadId();
        result = result * 59 + ($leadId == null ? 43 : ((Object)$leadId).hashCode());
        Integer $level = this.getLevel();
        result = result * 59 + ($level == null ? 43 : ((Object)$level).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        String $deptType = this.getDeptType();
        result = result * 59 + ($deptType == null ? 43 : $deptType.hashCode());
        String $phone = this.getPhone();
        result = result * 59 + ($phone == null ? 43 : $phone.hashCode());
        String $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        String $path = this.getPath();
        result = result * 59 + ($path == null ? 43 : $path.hashCode());
        String $dingtalkDeptId = this.getDingtalkDeptId();
        result = result * 59 + ($dingtalkDeptId == null ? 43 : $dingtalkDeptId.hashCode());
        String $wecomDeptId = this.getWecomDeptId();
        result = result * 59 + ($wecomDeptId == null ? 43 : $wecomDeptId.hashCode());
        String $feishuDeptId = this.getFeishuDeptId();
        result = result * 59 + ($feishuDeptId == null ? 43 : $feishuDeptId.hashCode());
        return result;
    }
}

