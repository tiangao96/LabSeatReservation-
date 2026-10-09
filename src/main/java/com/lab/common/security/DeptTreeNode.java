package com.lab.common.security;

import java.util.ArrayList;
import java.util.List;

public class DeptTreeNode {
    private String nanoId;
    private String name;
    private String parentNanoId;
    private Integer level;
    private List<DeptTreeNode> children = new ArrayList();

    public DeptTreeNode() {
    }

    public String getNanoId() {
        return this.nanoId;
    }

    public String getName() {
        return this.name;
    }

    public String getParentNanoId() {
        return this.parentNanoId;
    }

    public Integer getLevel() {
        return this.level;
    }

    public List<DeptTreeNode> getChildren() {
        return this.children;
    }

    public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentNanoId(String parentNanoId) {
        this.parentNanoId = parentNanoId;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setChildren(List<DeptTreeNode> children) {
        this.children = children;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof DeptTreeNode)) {
            return false;
        }
        DeptTreeNode other = (DeptTreeNode)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$level = this.getLevel();
        Integer other$level = other.getLevel();
        if (this$level == null ? other$level != null : !((Object)this$level).equals(other$level)) {
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
        String this$parentNanoId = this.getParentNanoId();
        String other$parentNanoId = other.getParentNanoId();
        if (this$parentNanoId == null ? other$parentNanoId != null : !this$parentNanoId.equals(other$parentNanoId)) {
            return false;
        }
        List this$children = this.getChildren();
        List other$children = other.getChildren();
        return !(this$children == null ? other$children != null : !((Object)this$children).equals(other$children));
    }

    protected boolean canEqual(Object other) {
        return other instanceof DeptTreeNode;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $level = this.getLevel();
        result = result * 59 + ($level == null ? 43 : ((Object)$level).hashCode());
        String $nanoId = this.getNanoId();
        result = result * 59 + ($nanoId == null ? 43 : $nanoId.hashCode());
        String $name = this.getName();
        result = result * 59 + ($name == null ? 43 : $name.hashCode());
        String $parentNanoId = this.getParentNanoId();
        result = result * 59 + ($parentNanoId == null ? 43 : $parentNanoId.hashCode());
        List $children = this.getChildren();
        result = result * 59 + ($children == null ? 43 : ((Object)$children).hashCode());
        return result;
    }

    public String toString() {
        return "DeptTreeNode(nanoId=" + this.getNanoId() + ", name=" + this.getName() + ", parentNanoId=" + this.getParentNanoId() + ", level=" + this.getLevel() + ", children=" + String.valueOf(this.getChildren()) + ")";
    }
}

