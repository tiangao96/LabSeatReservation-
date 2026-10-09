package com.lab.module.rbac.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class AssignPermissionsRequest {
    @NotNull(message="\u6743\u9650\u5217\u8868\u4e0d\u80fd\u4e3a null\uff08\u53ef\u4e3a\u7a7a\u5217\u8868\u8868\u793a\u6e05\u7a7a\uff09")
    private @NotNull(message="\u6743\u9650\u5217\u8868\u4e0d\u80fd\u4e3a null\uff08\u53ef\u4e3a\u7a7a\u5217\u8868\u8868\u793a\u6e05\u7a7a\uff09") List<String> permissionNanoIds;

    public AssignPermissionsRequest() {
    }

    public List<String> getPermissionNanoIds() {
        return this.permissionNanoIds;
    }

    public void setPermissionNanoIds(List<String> permissionNanoIds) {
        this.permissionNanoIds = permissionNanoIds;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AssignPermissionsRequest)) {
            return false;
        }
        AssignPermissionsRequest other = (AssignPermissionsRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        List this$permissionNanoIds = this.getPermissionNanoIds();
        List other$permissionNanoIds = other.getPermissionNanoIds();
        return !(this$permissionNanoIds == null ? other$permissionNanoIds != null : !((Object)this$permissionNanoIds).equals(other$permissionNanoIds));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AssignPermissionsRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        List $permissionNanoIds = this.getPermissionNanoIds();
        result = result * 59 + ($permissionNanoIds == null ? 43 : ((Object)$permissionNanoIds).hashCode());
        return result;
    }

    public String toString() {
        return "AssignPermissionsRequest(permissionNanoIds=" + String.valueOf(this.getPermissionNanoIds()) + ")";
    }
}

