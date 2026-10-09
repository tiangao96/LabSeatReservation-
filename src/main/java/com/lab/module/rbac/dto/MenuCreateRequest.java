package com.lab.module.rbac.dto;

import jakarta.validation.constraints.NotBlank;

public class MenuCreateRequest {
    @NotBlank(message="\u83dc\u5355\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u83dc\u5355\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a") String name;
    @NotBlank(message="\u8def\u7531\u8def\u5f84\u4e0d\u80fd\u4e3a\u7a7a")
    private @NotBlank(message="\u8def\u7531\u8def\u5f84\u4e0d\u80fd\u4e3a\u7a7a") String path;
    private String parentNanoId;
    private Long applicationId;
    private Integer isSystem;
    private String title;
    private String authCode;
    private String type;
    private String component;
    private String redirect;
    private String activePath;
    private String query;
    private Integer noBasicLayout;
    private String icon;
    private String activeIcon;
    private Integer order;
    private Integer hideInMenu;
    private Integer hideChildrenInMenu;
    private Integer hideInBreadcrumb;
    private Integer hideInTab;
    private Integer affixTab;
    private Integer affixTabOrder;
    private Integer keepAlive;
    private Integer maxNumOfOpenTab;
    private Integer fullPathKey;
    private String link;
    private String iframeSrc;
    private Integer openInNewWindow;
    private String badge;
    private String badgeType;
    private String badgeVariants;

    public MenuCreateRequest() {
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public String getParentNanoId() {
        return this.parentNanoId;
    }

    public Long getApplicationId() {
        return this.applicationId;
    }

    public Integer getIsSystem() {
        return this.isSystem;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public String getType() {
        return this.type;
    }

    public String getComponent() {
        return this.component;
    }

    public String getRedirect() {
        return this.redirect;
    }

    public String getActivePath() {
        return this.activePath;
    }

    public String getQuery() {
        return this.query;
    }

    public Integer getNoBasicLayout() {
        return this.noBasicLayout;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getActiveIcon() {
        return this.activeIcon;
    }

    public Integer getOrder() {
        return this.order;
    }

    public Integer getHideInMenu() {
        return this.hideInMenu;
    }

    public Integer getHideChildrenInMenu() {
        return this.hideChildrenInMenu;
    }

    public Integer getHideInBreadcrumb() {
        return this.hideInBreadcrumb;
    }

    public Integer getHideInTab() {
        return this.hideInTab;
    }

    public Integer getAffixTab() {
        return this.affixTab;
    }

    public Integer getAffixTabOrder() {
        return this.affixTabOrder;
    }

    public Integer getKeepAlive() {
        return this.keepAlive;
    }

    public Integer getMaxNumOfOpenTab() {
        return this.maxNumOfOpenTab;
    }

    public Integer getFullPathKey() {
        return this.fullPathKey;
    }

    public String getLink() {
        return this.link;
    }

    public String getIframeSrc() {
        return this.iframeSrc;
    }

    public Integer getOpenInNewWindow() {
        return this.openInNewWindow;
    }

    public String getBadge() {
        return this.badge;
    }

    public String getBadgeType() {
        return this.badgeType;
    }

    public String getBadgeVariants() {
        return this.badgeVariants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setParentNanoId(String parentNanoId) {
        this.parentNanoId = parentNanoId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public void setIsSystem(Integer isSystem) {
        this.isSystem = isSystem;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public void setActivePath(String activePath) {
        this.activePath = activePath;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setNoBasicLayout(Integer noBasicLayout) {
        this.noBasicLayout = noBasicLayout;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setActiveIcon(String activeIcon) {
        this.activeIcon = activeIcon;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setHideInMenu(Integer hideInMenu) {
        this.hideInMenu = hideInMenu;
    }

    public void setHideChildrenInMenu(Integer hideChildrenInMenu) {
        this.hideChildrenInMenu = hideChildrenInMenu;
    }

    public void setHideInBreadcrumb(Integer hideInBreadcrumb) {
        this.hideInBreadcrumb = hideInBreadcrumb;
    }

    public void setHideInTab(Integer hideInTab) {
        this.hideInTab = hideInTab;
    }

    public void setAffixTab(Integer affixTab) {
        this.affixTab = affixTab;
    }

    public void setAffixTabOrder(Integer affixTabOrder) {
        this.affixTabOrder = affixTabOrder;
    }

    public void setKeepAlive(Integer keepAlive) {
        this.keepAlive = keepAlive;
    }

    public void setMaxNumOfOpenTab(Integer maxNumOfOpenTab) {
        this.maxNumOfOpenTab = maxNumOfOpenTab;
    }

    public void setFullPathKey(Integer fullPathKey) {
        this.fullPathKey = fullPathKey;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setIframeSrc(String iframeSrc) {
        this.iframeSrc = iframeSrc;
    }

    public void setOpenInNewWindow(Integer openInNewWindow) {
        this.openInNewWindow = openInNewWindow;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public void setBadgeType(String badgeType) {
        this.badgeType = badgeType;
    }

    public void setBadgeVariants(String badgeVariants) {
        this.badgeVariants = badgeVariants;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MenuCreateRequest)) {
            return false;
        }
        MenuCreateRequest other = (MenuCreateRequest)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Long this$applicationId = this.getApplicationId();
        Long other$applicationId = other.getApplicationId();
        if (this$applicationId == null ? other$applicationId != null : !((Object)this$applicationId).equals(other$applicationId)) {
            return false;
        }
        Integer this$isSystem = this.getIsSystem();
        Integer other$isSystem = other.getIsSystem();
        if (this$isSystem == null ? other$isSystem != null : !((Object)this$isSystem).equals(other$isSystem)) {
            return false;
        }
        Integer this$noBasicLayout = this.getNoBasicLayout();
        Integer other$noBasicLayout = other.getNoBasicLayout();
        if (this$noBasicLayout == null ? other$noBasicLayout != null : !((Object)this$noBasicLayout).equals(other$noBasicLayout)) {
            return false;
        }
        Integer this$order = this.getOrder();
        Integer other$order = other.getOrder();
        if (this$order == null ? other$order != null : !((Object)this$order).equals(other$order)) {
            return false;
        }
        Integer this$hideInMenu = this.getHideInMenu();
        Integer other$hideInMenu = other.getHideInMenu();
        if (this$hideInMenu == null ? other$hideInMenu != null : !((Object)this$hideInMenu).equals(other$hideInMenu)) {
            return false;
        }
        Integer this$hideChildrenInMenu = this.getHideChildrenInMenu();
        Integer other$hideChildrenInMenu = other.getHideChildrenInMenu();
        if (this$hideChildrenInMenu == null ? other$hideChildrenInMenu != null : !((Object)this$hideChildrenInMenu).equals(other$hideChildrenInMenu)) {
            return false;
        }
        Integer this$hideInBreadcrumb = this.getHideInBreadcrumb();
        Integer other$hideInBreadcrumb = other.getHideInBreadcrumb();
        if (this$hideInBreadcrumb == null ? other$hideInBreadcrumb != null : !((Object)this$hideInBreadcrumb).equals(other$hideInBreadcrumb)) {
            return false;
        }
        Integer this$hideInTab = this.getHideInTab();
        Integer other$hideInTab = other.getHideInTab();
        if (this$hideInTab == null ? other$hideInTab != null : !((Object)this$hideInTab).equals(other$hideInTab)) {
            return false;
        }
        Integer this$affixTab = this.getAffixTab();
        Integer other$affixTab = other.getAffixTab();
        if (this$affixTab == null ? other$affixTab != null : !((Object)this$affixTab).equals(other$affixTab)) {
            return false;
        }
        Integer this$affixTabOrder = this.getAffixTabOrder();
        Integer other$affixTabOrder = other.getAffixTabOrder();
        if (this$affixTabOrder == null ? other$affixTabOrder != null : !((Object)this$affixTabOrder).equals(other$affixTabOrder)) {
            return false;
        }
        Integer this$keepAlive = this.getKeepAlive();
        Integer other$keepAlive = other.getKeepAlive();
        if (this$keepAlive == null ? other$keepAlive != null : !((Object)this$keepAlive).equals(other$keepAlive)) {
            return false;
        }
        Integer this$maxNumOfOpenTab = this.getMaxNumOfOpenTab();
        Integer other$maxNumOfOpenTab = other.getMaxNumOfOpenTab();
        if (this$maxNumOfOpenTab == null ? other$maxNumOfOpenTab != null : !((Object)this$maxNumOfOpenTab).equals(other$maxNumOfOpenTab)) {
            return false;
        }
        Integer this$fullPathKey = this.getFullPathKey();
        Integer other$fullPathKey = other.getFullPathKey();
        if (this$fullPathKey == null ? other$fullPathKey != null : !((Object)this$fullPathKey).equals(other$fullPathKey)) {
            return false;
        }
        Integer this$openInNewWindow = this.getOpenInNewWindow();
        Integer other$openInNewWindow = other.getOpenInNewWindow();
        if (this$openInNewWindow == null ? other$openInNewWindow != null : !((Object)this$openInNewWindow).equals(other$openInNewWindow)) {
            return false;
        }
        String this$name = this.getName();
        String other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
            return false;
        }
        String this$path = this.getPath();
        String other$path = other.getPath();
        if (this$path == null ? other$path != null : !this$path.equals(other$path)) {
            return false;
        }
        String this$parentNanoId = this.getParentNanoId();
        String other$parentNanoId = other.getParentNanoId();
        if (this$parentNanoId == null ? other$parentNanoId != null : !this$parentNanoId.equals(other$parentNanoId)) {
            return false;
        }
        String this$title = this.getTitle();
        String other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) {
            return false;
        }
        String this$authCode = this.getAuthCode();
        String other$authCode = other.getAuthCode();
        if (this$authCode == null ? other$authCode != null : !this$authCode.equals(other$authCode)) {
            return false;
        }
        String this$type = this.getType();
        String other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) {
            return false;
        }
        String this$component = this.getComponent();
        String other$component = other.getComponent();
        if (this$component == null ? other$component != null : !this$component.equals(other$component)) {
            return false;
        }
        String this$redirect = this.getRedirect();
        String other$redirect = other.getRedirect();
        if (this$redirect == null ? other$redirect != null : !this$redirect.equals(other$redirect)) {
            return false;
        }
        String this$activePath = this.getActivePath();
        String other$activePath = other.getActivePath();
        if (this$activePath == null ? other$activePath != null : !this$activePath.equals(other$activePath)) {
            return false;
        }
        String this$query = this.getQuery();
        String other$query = other.getQuery();
        if (this$query == null ? other$query != null : !this$query.equals(other$query)) {
            return false;
        }
        String this$icon = this.getIcon();
        String other$icon = other.getIcon();
        if (this$icon == null ? other$icon != null : !this$icon.equals(other$icon)) {
            return false;
        }
        String this$activeIcon = this.getActiveIcon();
        String other$activeIcon = other.getActiveIcon();
        if (this$activeIcon == null ? other$activeIcon != null : !this$activeIcon.equals(other$activeIcon)) {
            return false;
        }
        String this$link = this.getLink();
        String other$link = other.getLink();
        if (this$link == null ? other$link != null : !this$link.equals(other$link)) {
            return false;
        }
        String this$iframeSrc = this.getIframeSrc();
        String other$iframeSrc = other.getIframeSrc();
        if (this$iframeSrc == null ? other$iframeSrc != null : !this$iframeSrc.equals(other$iframeSrc)) {
            return false;
        }
        String this$badge = this.getBadge();
        String other$badge = other.getBadge();
        if (this$badge == null ? other$badge != null : !this$badge.equals(other$badge)) {
            return false;
        }
        String this$badgeType = this.getBadgeType();
        String other$badgeType = other.getBadgeType();
        if (this$badgeType == null ? other$badgeType != null : !this$badgeType.equals(other$badgeType)) {
            return false;
        }
        String this$badgeVariants = this.getBadgeVariants();
        String other$badgeVariants = other.getBadgeVariants();
        return !(this$badgeVariants == null ? other$badgeVariants != null : !this$badgeVariants.equals(other$badgeVariants));
    }

    protected boolean canEqual(Object other) {
        return other instanceof MenuCreateRequest;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Long $applicationId = this.getApplicationId();
        result = result * 59 + ($applicationId == null ? 43 : ((Object)$applicationId).hashCode());
        Integer $isSystem = this.getIsSystem();
        result = result * 59 + ($isSystem == null ? 43 : ((Object)$isSystem).hashCode());
        Integer $noBasicLayout = this.getNoBasicLayout();
        result = result * 59 + ($noBasicLayout == null ? 43 : ((Object)$noBasicLayout).hashCode());
        Integer $order = this.getOrder();
        result = result * 59 + ($order == null ? 43 : ((Object)$order).hashCode());
        Integer $hideInMenu = this.getHideInMenu();
        result = result * 59 + ($hideInMenu == null ? 43 : ((Object)$hideInMenu).hashCode());
        Integer $hideChildrenInMenu = this.getHideChildrenInMenu();
        result = result * 59 + ($hideChildrenInMenu == null ? 43 : ((Object)$hideChildrenInMenu).hashCode());
        Integer $hideInBreadcrumb = this.getHideInBreadcrumb();
        result = result * 59 + ($hideInBreadcrumb == null ? 43 : ((Object)$hideInBreadcrumb).hashCode());
        Integer $hideInTab = this.getHideInTab();
        result = result * 59 + ($hideInTab == null ? 43 : ((Object)$hideInTab).hashCode());
        Integer $affixTab = this.getAffixTab();
        result = result * 59 + ($affixTab == null ? 43 : ((Object)$affixTab).hashCode());
        Integer $affixTabOrder = this.getAffixTabOrder();
        result = result * 59 + ($affixTabOrder == null ? 43 : ((Object)$affixTabOrder).hashCode());
        Integer $keepAlive = this.getKeepAlive();
        result = result * 59 + ($keepAlive == null ? 43 : ((Object)$keepAlive).hashCode());
        Integer $maxNumOfOpenTab = this.getMaxNumOfOpenTab();
        result = result * 59 + ($maxNumOfOpenTab == null ? 43 : ((Object)$maxNumOfOpenTab).hashCode());
        Integer $fullPathKey = this.getFullPathKey();
        result = result * 59 + ($fullPathKey == null ? 43 : ((Object)$fullPathKey).hashCode());
        Integer $openInNewWindow = this.getOpenInNewWindow();
        result = result * 59 + ($openInNewWindow == null ? 43 : ((Object)$openInNewWindow).hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $path = this.getPath();
        result = result * 59 + ($path == null ? 43 : $path.hashCode());
        String $parentNanoId = this.getParentNanoId();
        result = result * 59 + ($parentNanoId == null ? 43 : $parentNanoId.hashCode());
        String $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        String $authCode = this.getAuthCode();
        result = result * 59 + ($authCode == null ? 43 : $authCode.hashCode());
        String $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        String $component = this.getComponent();
        result = result * 59 + ($component == null ? 43 : $component.hashCode());
        String $redirect = this.getRedirect();
        result = result * 59 + ($redirect == null ? 43 : $redirect.hashCode());
        String $activePath = this.getActivePath();
        result = result * 59 + ($activePath == null ? 43 : $activePath.hashCode());
        String $query = this.getQuery();
        result = result * 59 + ($query == null ? 43 : $query.hashCode());
        String $icon = this.getIcon();
        result = result * 59 + ($icon == null ? 43 : $icon.hashCode());
        String $activeIcon = this.getActiveIcon();
        result = result * 59 + ($activeIcon == null ? 43 : $activeIcon.hashCode());
        String $link = this.getLink();
        result = result * 59 + ($link == null ? 43 : $link.hashCode());
        String $iframeSrc = this.getIframeSrc();
        result = result * 59 + ($iframeSrc == null ? 43 : $iframeSrc.hashCode());
        String $badge = this.getBadge();
        result = result * 59 + ($badge == null ? 43 : $badge.hashCode());
        String $badgeType = this.getBadgeType();
        result = result * 59 + ($badgeType == null ? 43 : $badgeType.hashCode());
        String $badgeVariants = this.getBadgeVariants();
        result = result * 59 + ($badgeVariants == null ? 43 : $badgeVariants.hashCode());
        return result;
    }

    public String toString() {
        return "MenuCreateRequest(name=" + this.getName() + ", path=" + this.getPath() + ", parentNanoId=" + this.getParentNanoId() + ", applicationId=" + this.getApplicationId() + ", isSystem=" + this.getIsSystem() + ", title=" + this.getTitle() + ", authCode=" + this.getAuthCode() + ", type=" + this.getType() + ", component=" + this.getComponent() + ", redirect=" + this.getRedirect() + ", activePath=" + this.getActivePath() + ", query=" + this.getQuery() + ", noBasicLayout=" + this.getNoBasicLayout() + ", icon=" + this.getIcon() + ", activeIcon=" + this.getActiveIcon() + ", order=" + this.getOrder() + ", hideInMenu=" + this.getHideInMenu() + ", hideChildrenInMenu=" + this.getHideChildrenInMenu() + ", hideInBreadcrumb=" + this.getHideInBreadcrumb() + ", hideInTab=" + this.getHideInTab() + ", affixTab=" + this.getAffixTab() + ", affixTabOrder=" + this.getAffixTabOrder() + ", keepAlive=" + this.getKeepAlive() + ", maxNumOfOpenTab=" + this.getMaxNumOfOpenTab() + ", fullPathKey=" + this.getFullPathKey() + ", link=" + this.getLink() + ", iframeSrc=" + this.getIframeSrc() + ", openInNewWindow=" + this.getOpenInNewWindow() + ", badge=" + this.getBadge() + ", badgeType=" + this.getBadgeType() + ", badgeVariants=" + this.getBadgeVariants() + ")";
    }
}

