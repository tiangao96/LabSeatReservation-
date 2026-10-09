package com.lab.module.rbac.entity;

import com.lab.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name="core_permission")
@SQLRestriction(value="is_deleted = 0")
public class CorePermission
extends BaseEntity {
    private Long menuId;
    private String name;
    private String code;
    private Integer permissionType;
    private String apiPath;
    private Integer httpMethod;
    private String description;
    private Integer isActive;

    public CorePermission() {
    }

    public Long getMenuId() {
        return this.menuId;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getPermissionType() {
        return this.permissionType;
    }

    public String getApiPath() {
        return this.apiPath;
    }

    public Integer getHttpMethod() {
        return this.httpMethod;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getIsActive() {
        return this.isActive;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }

    public void setHttpMethod(Integer httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String toString() {
        return "CorePermission(menuId=" + this.getMenuId() + ", name=" + this.getName() + ", code=" + this.getCode() + ", permissionType=" + this.getPermissionType() + ", apiPath=" + this.getApiPath() + ", httpMethod=" + this.getHttpMethod() + ", description=" + this.getDescription() + ", isActive=" + this.getIsActive() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CorePermission)) {
            return false;
        }
        CorePermission other = (CorePermission)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Long this$menuId = this.getMenuId();
        Long other$menuId = other.getMenuId();
        if (this$menuId == null ? other$menuId != null : !((Object)this$menuId).equals(other$menuId)) {
            return false;
        }
        Integer this$permissionType = this.getPermissionType();
        Integer other$permissionType = other.getPermissionType();
        if (this$permissionType == null ? other$permissionType != null : !((Object)this$permissionType).equals(other$permissionType)) {
            return false;
        }
        Integer this$httpMethod = this.getHttpMethod();
        Integer other$httpMethod = other.getHttpMethod();
        if (this$httpMethod == null ? other$httpMethod != null : !((Object)this$httpMethod).equals(other$httpMethod)) {
            return false;
        }
        Integer this$isActive = this.getIsActive();
        Integer other$isActive = other.getIsActive();
        if (this$isActive == null ? other$isActive != null : !((Object)this$isActive).equals(other$isActive)) {
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
        String this$apiPath = this.getApiPath();
        String other$apiPath = other.getApiPath();
        if (this$apiPath == null ? other$apiPath != null : !this$apiPath.equals(other$apiPath)) {
            return false;
        }
        String this$description = this.getDescription();
        String other$description = other.getDescription();
        return !(this$description == null ? other$description != null : !this$description.equals(other$description));
    }

    protected boolean canEqual(Object other) {
        return other instanceof CorePermission;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = super.hashCode();
        Long $menuId = this.getMenuId();
        result = result * 59 + ($menuId == null ? 43 : ((Object)$menuId).hashCode());
        Integer $permissionType = this.getPermissionType();
        result = result * 59 + ($permissionType == null ? 43 : ((Object)$permissionType).hashCode());
        Integer $httpMethod = this.getHttpMethod();
        result = result * 59 + ($httpMethod == null ? 43 : ((Object)$httpMethod).hashCode());
        Integer $isActive = this.getIsActive();
        result = result * 59 + ($isActive == null ? 43 : ((Object)$isActive).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        String $apiPath = this.getApiPath();
        result = result * 59 + ($apiPath == null ? 43 : $apiPath.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }
}

