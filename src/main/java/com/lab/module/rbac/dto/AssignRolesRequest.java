package com.lab.module.rbac.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class AssignRolesRequest {
    @NotNull(message="\u89d2\u8272\u5217\u8868\u4e0d\u80fd\u4e3a null")
    private @NotNull(message="\u89d2\u8272\u5217\u8868\u4e0d\u80fd\u4e3a null") List<String> roleNanoIds;

    public AssignRolesRequest() {
    }

    public List<String> getRoleNanoIds() {
        return this.roleNanoIds;
    }

    public void setRoleNanoIds(List<String> roleNanoIds) {
        this.roleNanoIds = roleNanoIds;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AssignRolesRequest)) {
            return false;
        }
        AssignRolesRequest other = (AssignRolesRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        List this$roleNanoIds = this.getRoleNanoIds();
        List other$roleNanoIds = other.getRoleNanoIds();
        return !(this$roleNanoIds == null ? other$roleNanoIds != null : !((Object)this$roleNanoIds).equals(other$roleNanoIds));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AssignRolesRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        List $roleNanoIds = this.getRoleNanoIds();
        result = result * 59 + ($roleNanoIds == null ? 43 : ((Object)$roleNanoIds).hashCode());
        return result;
    }

    public String toString() {
        return "AssignRolesRequest(roleNanoIds=" + String.valueOf(this.getRoleNanoIds()) + ")";
    }
}

