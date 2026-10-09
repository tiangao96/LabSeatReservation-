package com.lab.module.rbac.entity.id;

import java.io.Serializable;

public class CoreUserRoleId
implements Serializable {
    private Long userId;
    private Long roleId;

    public Long getUserId() {
        return this.userId;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CoreUserRoleId)) {
            return false;
        }
        CoreUserRoleId other = (CoreUserRoleId)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Long this$userId = this.getUserId();
        Long other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !((Object)this$userId).equals(other$userId)) {
            return false;
        }
        Long this$roleId = this.getRoleId();
        Long other$roleId = other.getRoleId();
        return !(this$roleId == null ? other$roleId != null : !((Object)this$roleId).equals(other$roleId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CoreUserRoleId;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Long $roleId = this.getRoleId();
        result = result * 59 + ($roleId == null ? 43 : ((Object)$roleId).hashCode());
        return result;
    }

    public String toString() {
        return "CoreUserRoleId(userId=" + this.getUserId() + ", roleId=" + this.getRoleId() + ")";
    }

    public CoreUserRoleId() {
    }

    public CoreUserRoleId(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}

