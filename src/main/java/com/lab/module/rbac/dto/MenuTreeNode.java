package com.lab.module.rbac.dto;

import java.util.ArrayList;
import java.util.List;

public class MenuTreeNode {
    private String nanoId;
    private String name;
    private String title;
    private String path;
    private String component;
    private String icon;
    private Integer order;
    private String badge;
    private String badgeType;
    private String badgeVariants;
    private List<MenuTreeNode> children = new ArrayList();

        public MenuTreeNode() {
    }

        public String getNanoId() {
        return this.nanoId;
    }

        public String getName() {
        return this.name;
    }

        public String getTitle() {
        return this.title;
    }

        public String getPath() {
        return this.path;
    }

        public String getComponent() {
        return this.component;
    }

        public String getIcon() {
        return this.icon;
    }

        public Integer getOrder() {
        return this.order;
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

        public List<MenuTreeNode> getChildren() {
        return this.children;
    }

        public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }

        public void setName(String name) {
        this.name = name;
    }

        public void setTitle(String title) {
        this.title = title;
    }

        public void setPath(String path) {
        this.path = path;
    }

        public void setComponent(String component) {
        this.component = component;
    }

        public void setIcon(String icon) {
        this.icon = icon;
    }

        public void setOrder(Integer order) {
        this.order = order;
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

        public void setChildren(List<MenuTreeNode> children) {
        this.children = children;
    }

        public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MenuTreeNode)) {
            return false;
        }
        MenuTreeNode other = (MenuTreeNode)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$order = this.getOrder();
        Integer other$order = other.getOrder();
        if (this$order == null ? other$order != null : !((Object)this$order).equals(other$order)) {
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
        String this$title = this.getTitle();
        String other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) {
            return false;
        }
        String this$path = this.getPath();
        String other$path = other.getPath();
        if (this$path == null ? other$path != null : !this$path.equals(other$path)) {
            return false;
        }
        String this$component = this.getComponent();
        String other$component = other.getComponent();
        if (this$component == null ? other$component != null : !this$component.equals(other$component)) {
            return false;
        }
        String this$icon = this.getIcon();
        String other$icon = other.getIcon();
        if (this$icon == null ? other$icon != null : !this$icon.equals(other$icon)) {
            return false;
        }
        List this$children = this.getChildren();
        List other$children = other.getChildren();
        return !(this$children == null ? other$children != null : !((Object)this$children).equals(other$children));
    }

        protected boolean canEqual(Object other) {
        return other instanceof MenuTreeNode;
    }

        public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $order = this.getOrder();
        result = result * 59 + ($order == null ? 43 : ((Object)$order).hashCode());
        String $nanoId = this.getNanoId();
        result = result * 59 + ($nanoId == null ? 43 : $nanoId.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $title = this.getTitle();
        result = result * 59 + ($title == null ? 43 : $title.hashCode());
        String $path = this.getPath();
        result = result * 59 + ($path == null ? 43 : $path.hashCode());
        String $component = this.getComponent();
        result = result * 59 + ($component == null ? 43 : $component.hashCode());
        String $icon = this.getIcon();
        result = result * 59 + ($icon == null ? 43 : $icon.hashCode());
        List $children = this.getChildren();
        result = result * 59 + ($children == null ? 43 : ((Object)$children).hashCode());
        return result;
    }

        public String toString() {
        return "MenuTreeNode(nanoId=" + this.getNanoId() + ", name=" + this.getName() + ", title=" + this.getTitle() + ", path=" + this.getPath() + ", component=" + this.getComponent() + ", icon=" + this.getIcon() + ", order=" + this.getOrder() + ", children=" + String.valueOf(this.getChildren()) + ")";
    }
}

