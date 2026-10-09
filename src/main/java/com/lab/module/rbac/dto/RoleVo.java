package com.lab.module.rbac.dto;


public class RoleVo {
    private String nanoId;
    private String name;
    private String code;
    private Integer roleType;
    private Integer status;
    private Integer priority;
    private String description;

    public RoleVo() {
    }

    public String getNanoId() {
        return this.nanoId;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getRoleType() {
        return this.roleType;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public String getDescription() {
        return this.description;
    }

    public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RoleVo)) {
            return false;
        }
        RoleVo other = (RoleVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$roleType = this.getRoleType();
        Integer other$roleType = other.getRoleType();
        if (this$roleType == null ? other$roleType != null : !((Object)this$roleType).equals(other$roleType)) {
            return false;
        }
        Integer this$status = this.getStatus();
        Integer other$status = other.getStatus();
        if (this$status == null ? other$status != null : !((Object)this$status).equals(other$status)) {
            return false;
        }
        Integer this$priority = this.getPriority();
        Integer other$priority = other.getPriority();
        if (this$priority == null ? other$priority != null : !((Object)this$priority).equals(other$priority)) {
            return false;
        }
        String this$nanoId = this.getNanoId();
        String other$nanoId = other.getNanoId();
        if (this$nanoId == null ? other$nanoId != null : !this$nanoId.equals(other$nanoId)) {
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
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        return !(this$description == null ? other$description != null : !this$description.equals(other$description));
    }

    protected boolean canEqual(Object other) {
        return other instanceof RoleVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $roleType = this.getRoleType();
        result = result * 59 + ($roleType == null ? 43 : ((Object)$roleType).hashCode());
        Integer $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : ((Object)$status).hashCode());
        Integer $priority = this.getPriority();
        result = result * 59 + ($priority == null ? 43 : ((Object)$priority).hashCode());
        String $nanoId = this.getNanoId();
        result = result * 59 + ($nanoId == null ? 43 : $nanoId.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    public String toString() {
        return "RoleVo(nanoId=" + this.getNanoId() + ", name=" + this.getName() + ", code=" + this.getCode() + ", roleType=" + this.getRoleType() + ", status=" + this.getStatus() + ", priority=" + this.getPriority() + ", description=" + this.getDescription() + ")";
    }
}

