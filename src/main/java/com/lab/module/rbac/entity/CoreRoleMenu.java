package com.lab.module.rbac.entity;

import com.lab.module.rbac.entity.id.CoreRoleMenuId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="core_role_menu")
@IdClass(value=CoreRoleMenuId.class)
public class CoreRoleMenu {
    @Id
    private Long roleId;
    @Id
    private Long menuId;

    public Long getRoleId() {
        return this.roleId;
    }

    public Long getMenuId() {
        return this.menuId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CoreRoleMenu)) {
            return false;
        }
        CoreRoleMenu other = (CoreRoleMenu)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Long this$roleId = this.getRoleId();
        Long other$roleId = other.getRoleId();
        if (this$roleId == null ? other$roleId != null : !((Object)this$roleId).equals(other$roleId)) {
            return false;
        }
        Long this$menuId = this.getMenuId();
        Long other$menuId = other.getMenuId();
        return !(this$menuId == null ? other$menuId != null : !((Object)this$menuId).equals(other$menuId));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CoreRoleMenu;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $roleId = this.getRoleId();
        result = result * 59 + ($roleId == null ? 43 : ((Object)$roleId).hashCode());
        Long $menuId = this.getMenuId();
        result = result * 59 + ($menuId == null ? 43 : ((Object)$menuId).hashCode());
        return result;
    }

    public String toString() {
        return "CoreRoleMenu(roleId=" + this.getRoleId() + ", menuId=" + this.getMenuId() + ")";
    }

    public CoreRoleMenu() {
    }

    public CoreRoleMenu(Long roleId, Long menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }
}

