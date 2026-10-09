package com.lab.common.storage;

import java.io.File;
import java.util.Set;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="app.storage")
public class StorageProperties {
    private String uploadDir = "./uploads";
    private String urlPrefix = "/uploads";
    private long avatarMaxBytes = 0x500000L;
    private int avatarMaxDimension = 256;
    private Set<String> avatarAllowedContentTypes = Set.of("image/jpeg", "image/png", "image/gif");

    public String getUploadDirAbsolute() {
        return new File(this.uploadDir).getAbsolutePath();
    }

    public StorageProperties() {
    }

    public String getUploadDir() {
        return this.uploadDir;
    }

    public String getUrlPrefix() {
        return this.urlPrefix;
    }

    public long getAvatarMaxBytes() {
        return this.avatarMaxBytes;
    }

    public int getAvatarMaxDimension() {
        return this.avatarMaxDimension;
    }

    public Set<String> getAvatarAllowedContentTypes() {
        return this.avatarAllowedContentTypes;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public void setAvatarMaxBytes(long avatarMaxBytes) {
        this.avatarMaxBytes = avatarMaxBytes;
    }

    public void setAvatarMaxDimension(int avatarMaxDimension) {
        this.avatarMaxDimension = avatarMaxDimension;
    }

    public void setAvatarAllowedContentTypes(Set<String> avatarAllowedContentTypes) {
        this.avatarAllowedContentTypes = avatarAllowedContentTypes;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof StorageProperties)) {
            return false;
        }
        StorageProperties other = (StorageProperties)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getAvatarMaxBytes() != other.getAvatarMaxBytes()) {
            return false;
        }
        if (this.getAvatarMaxDimension() != other.getAvatarMaxDimension()) {
            return false;
        }
        String this$uploadDir = this.getUploadDir();
        String other$uploadDir = other.getUploadDir();
        if (this$uploadDir == null ? other$uploadDir != null : !this$uploadDir.equals(other$uploadDir)) {
            return false;
        }
        String this$urlPrefix = this.getUrlPrefix();
        String other$urlPrefix = other.getUrlPrefix();
        if (this$urlPrefix == null ? other$urlPrefix != null : !this$urlPrefix.equals(other$urlPrefix)) {
            return false;
        }
        Set this$avatarAllowedContentTypes = this.getAvatarAllowedContentTypes();
        Set other$avatarAllowedContentTypes = other.getAvatarAllowedContentTypes();
        return !(this$avatarAllowedContentTypes == null ? other$avatarAllowedContentTypes != null : !((Object)this$avatarAllowedContentTypes).equals(other$avatarAllowedContentTypes));
    }

    protected boolean canEqual(Object other) {
        return other instanceof StorageProperties;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $avatarMaxBytes = this.getAvatarMaxBytes();
        result = result * 59 + (int)($avatarMaxBytes >>> 32 ^ $avatarMaxBytes);
        result = result * 59 + this.getAvatarMaxDimension();
        String $uploadDir = this.getUploadDir();
        result = result * 59 + ($uploadDir == null ? 43 : $uploadDir.hashCode());
        String $urlPrefix = this.getUrlPrefix();
        result = result * 59 + ($urlPrefix == null ? 43 : $urlPrefix.hashCode());
        Set $avatarAllowedContentTypes = this.getAvatarAllowedContentTypes();
        result = result * 59 + ($avatarAllowedContentTypes == null ? 43 : ((Object)$avatarAllowedContentTypes).hashCode());
        return result;
    }

    public String toString() {
        return "StorageProperties(uploadDir=" + this.getUploadDir() + ", urlPrefix=" + this.getUrlPrefix() + ", avatarMaxBytes=" + this.getAvatarMaxBytes() + ", avatarMaxDimension=" + this.getAvatarMaxDimension() + ", avatarAllowedContentTypes=" + String.valueOf(this.getAvatarAllowedContentTypes()) + ")";
    }
}

