package com.lab.module.rbac.dto;

import jakarta.validation.constraints.NotBlank;

public class PermissionCreateRequest {
    @NotBlank(message="\u6743\u9650\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u6743\u9650\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a") String name;
    @NotBlank(message="\u6743\u9650\u7f16\u7801\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u6743\u9650\u7f16\u7801\u4e0d\u80fd\u4e3a\u7a7a") String code;
    private String menuNanoId;
    private Integer permissionType;
    private String apiPath;
    private Integer httpMethod;
    private String description;
    private Integer isActive;
    private Integer sort;

    public PermissionCreateRequest() {
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getMenuNanoId() {
        return this.menuNanoId;
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

    public Integer getSort() {
        return this.sort;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMenuNanoId(String menuNanoId) {
        this.menuNanoId = menuNanoId;
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

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PermissionCreateRequest)) {
            return false;
        }
        PermissionCreateRequest other = (PermissionCreateRequest)o;
        if (!other.canEqual((Object)this)) {
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
        String this$menuNanoId = this.getMenuNanoId();
        String other$menuNanoId = other.getMenuNanoId();
        if (this$menuNanoId == null ? other$menuNanoId != null : !this$menuNanoId.equals(other$menuNanoId)) {
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
        return other instanceof PermissionCreateRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $permissionType = this.getPermissionType();
        result = result * 59 + ($permissionType == null ? 43 : ((Object)$permissionType).hashCode());
        Integer $httpMethod = this.getHttpMethod();
        result = result * 59 + ($httpMethod == null ? 43 : ((Object)$httpMethod).hashCode());
        Integer $isActive = this.getIsActive();
        result = result * 59 + ($isActive == null ? 43 : ((Object)$isActive).hashCode());
        Integer $sort = this.getSort();
        result = result * 59 + ($sort == null ? 43 : ((Object)$sort).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        String $menuNanoId = this.getMenuNanoId();
        result = result * 59 + ($menuNanoId == null ? 43 : $menuNanoId.hashCode());
        String $apiPath = this.getApiPath();
        result = result * 59 + ($apiPath == null ? 43 : $apiPath.hashCode());
        String $description = this.getDescription();
        result = result * 59 + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    public String toString() {
        return "PermissionCreateRequest(name=" + this.getName() + ", code=" + this.getCode() + ", menuNanoId=" + this.getMenuNanoId() + ", permissionType=" + this.getPermissionType() + ", apiPath=" + this.getApiPath() + ", httpMethod=" + this.getHttpMethod() + ", description=" + this.getDescription() + ", isActive=" + this.getIsActive() + ", sort=" + this.getSort() + ")";
    }
}

