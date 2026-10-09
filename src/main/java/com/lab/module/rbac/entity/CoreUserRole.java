package com.lab.module.rbac.entity;

import com.lab.common.entity.AuditEntity;
import com.lab.module.rbac.entity.id.CoreUserRoleId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name="core_user_role")
@SQLRestriction(value="is_deleted = 0")
@IdClass(value=CoreUserRoleId.class)
public class CoreUserRole
extends AuditEntity {
    @Id
    private Long userId;
    @Id
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

    public String toString() {
        return "CoreUserRole(userId=" + this.getUserId() + ", roleId=" + this.getRoleId() + ")";
    }

    public CoreUserRole() {
    }

    public CoreUserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CoreUserRole)) {
            return false;
        }
        CoreUserRole other = (CoreUserRole)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (!super.equals(o)) {
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
        return other instanceof CoreUserRole;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
        Long $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : ((Object)$userId).hashCode());
        Long $roleId = this.getRoleId();
        result = result * 59 + ($roleId == null ? 43 : ((Object)$roleId).hashCode());
        return result;
    }
}

