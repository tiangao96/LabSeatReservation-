package com.lab.module.rbac.dto;

import java.util.Set;

public class MeVo {
    private String nanoId;
    private String username;
    private String name;
    private Long deptId;
    private Long mentorGroupId;
    private Integer isActive;
    private String avatarUrl;
    private Set<String> permissions;
    private Set<String> roles;

    public MeVo() {
    }

    public String getNanoId() {
        return this.nanoId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getName() {
        return this.name;
    }

    public Long getDeptId() {
        return this.deptId;
    }

    public Long getMentorGroupId() {
        return this.mentorGroupId;
    }

    public Integer getIsActive() {
        return this.isActive;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public Set<String> getPermissions() {
        return this.permissions;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setMentorGroupId(Long mentorGroupId) {
        this.mentorGroupId = mentorGroupId;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MeVo)) {
            return false;
        }
        MeVo other = (MeVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Long this$deptId = this.getDeptId();
        Long other$deptId = other.getDeptId();
        if (this$deptId == null ? other$deptId != null : !((Object)this$deptId).equals(other$deptId)) {
            return false;
        }
        Long this$mentorGroupId = this.getMentorGroupId();
        Long other$mentorGroupId = other.getMentorGroupId();
        if (this$mentorGroupId == null ? other$mentorGroupId != null : !((Object)this$mentorGroupId).equals(other$mentorGroupId)) {
            return false;
        }
        Integer this$isActive = this.getIsActive();
        Integer other$isActive = other.getIsActive();
        if (this$isActive == null ? other$isActive != null : !((Object)this$isActive).equals(other$isActive)) {
            return false;
        }
        String this$nanoId = this.getNanoId();
        String other$nanoId = other.getNanoId();
        if (this$nanoId == null ? other$nanoId != null : !this$nanoId.equals(other$nanoId)) {
            return false;
        }
        String this$username = this.getUsername();
        String other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$avatarUrl = this.getAvatarUrl();
        String other$avatarUrl = other.getAvatarUrl();
        if (this$avatarUrl == null ? other$avatarUrl != null : !this$avatarUrl.equals(other$avatarUrl)) {
            return false;
        }
        Set this$permissions = this.getPermissions();
        Set other$permissions = other.getPermissions();
        if (this$permissions == null ? other$permissions != null : !((Object)this$permissions).equals(other$permissions)) {
            return false;
        }
        Set this$roles = this.getRoles();
        Set other$roles = other.getRoles();
        return !(this$roles == null ? other$roles != null : !((Object)this$roles).equals(other$roles));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MeVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $deptId = this.getDeptId();
        result = result * 59 + ($deptId == null ? 43 : ((Object)$deptId).hashCode());
        Long $mentorGroupId = this.getMentorGroupId();
        result = result * 59 + ($mentorGroupId == null ? 43 : ((Object)$mentorGroupId).hashCode());
        Integer $isActive = this.getIsActive();
        result = result * 59 + ($isActive == null ? 43 : ((Object)$isActive).hashCode());
        String $nanoId = this.getNanoId();
        result = result * 59 + ($nanoId == null ? 43 : $nanoId.hashCode());
        String $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $avatarUrl = this.getAvatarUrl();
        result = result * 59 + ($avatarUrl == null ? 43 : $avatarUrl.hashCode());
        Set $permissions = this.getPermissions();
        result = result * 59 + ($permissions == null ? 43 : ((Object)$permissions).hashCode());
        Set $roles = this.getRoles();
        result = result * 59 + ($roles == null ? 43 : ((Object)$roles).hashCode());
        return result;
    }

    public String toString() {
        return "MeVo(nanoId=" + this.getNanoId() + ", username=" + this.getUsername() + ", name=" + this.getName() + ", deptId=" + this.getDeptId() + ", mentorGroupId=" + this.getMentorGroupId() + ", isActive=" + this.getIsActive() + ", avatarUrl=" + this.getAvatarUrl() + ", permissions=" + String.valueOf(this.getPermissions()) + ", roles=" + String.valueOf(this.getRoles()) + ")";
    }
}

