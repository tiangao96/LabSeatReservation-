package com.lab.module.rbac.dto;


public class AvatarVo {
    private String avatarUrl;

    public AvatarVo() {
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AvatarVo)) {
            return false;
        }
        AvatarVo other = (AvatarVo)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        String this$avatarUrl = this.getAvatarUrl();
        String other$avatarUrl = other.getAvatarUrl();
        return !(this$avatarUrl == null ? other$avatarUrl != null : !this$avatarUrl.equals(other$avatarUrl));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AvatarVo;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        String $avatarUrl = this.getAvatarUrl();
        result = result * 59 + ($avatarUrl == null ? 43 : $avatarUrl.hashCode());
        return result;
    }

    public String toString() {
        return "AvatarVo(avatarUrl=" + this.getAvatarUrl() + ")";
    }
}

