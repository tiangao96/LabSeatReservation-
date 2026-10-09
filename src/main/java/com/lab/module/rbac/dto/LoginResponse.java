package com.lab.module.rbac.dto;

import com.lab.module.rbac.dto.LoginResponse;
import java.util.Set;

public class LoginResponse {
    private String nanoId;
    private String username;
    private String accessToken;
    private long expiresIn;
    private String refreshToken;
    private long refreshExpiresIn;
    private String tokenType;
    private Set<String> permissions;
    private Set<String> roles;

    LoginResponse(String nanoId, String username, String accessToken, long expiresIn, String refreshToken, long refreshExpiresIn, String tokenType, Set<String> permissions, Set<String> roles) {
        this.nanoId = nanoId;
        this.username = username;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.refreshExpiresIn = refreshExpiresIn;
        this.tokenType = tokenType;
        this.permissions = permissions;
        this.roles = roles;
    }

    public static LoginResponseBuilder builder() {
        return new LoginResponseBuilder();
    }

    public String getNanoId() {
        return this.nanoId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public long getRefreshExpiresIn() {
        return this.refreshExpiresIn;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public Set<String> getPermissions() {
        return this.permissions;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setNanoId(String nanoId) {
        this.nanoId = nanoId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setRefreshExpiresIn(long refreshExpiresIn) {
        this.refreshExpiresIn = refreshExpiresIn;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LoginResponse)) {
            return false;
        }
        LoginResponse other = (LoginResponse)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        if (this.getExpiresIn() != other.getExpiresIn()) {
            return false;
        }
        if (this.getRefreshExpiresIn() != other.getRefreshExpiresIn()) {
            return false;
        }
        String this$nanoId = this.getNanoId();
        String other$nanoId = other.getNanoId();
        if (this$nanoId == null ? other$nanoId != null : !this$nanoId.equals(other$nanoId)) {
            return false;
        }
        String this$username = this.getUsername();
        String other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) {
            return false;
        }
        String this$accessToken = this.getAccessToken();
        String other$accessToken = other.getAccessToken();
        if (this$accessToken == null ? other$accessToken != null : !this$accessToken.equals(other$accessToken)) {
            return false;
        }
        String this$refreshToken = this.getRefreshToken();
        String other$refreshToken = other.getRefreshToken();
        if (this$refreshToken == null ? other$refreshToken != null : !this$refreshToken.equals(other$refreshToken)) {
            return false;
        }
        String this$tokenType = this.getTokenType();
        String other$tokenType = other.getTokenType();
        if (this$tokenType == null ? other$tokenType != null : !this$tokenType.equals(other$tokenType)) {
            return false;
        }
        Set this$permissions = this.getPermissions();
        Set other$permissions = other.getPermissions();
        if (this$permissions == null ? other$permissions != null : !((Object)this$permissions).equals(other$permissions)) {
            return false;
        }
        Set this$roles = this.getRoles();
        Set other$roles = other.getRoles();
        return !(this$roles == null ? other$roles != null : !((Object)this$roles).equals(other$roles));
    }

    protected boolean canEqual(Object other) {
        return other instanceof LoginResponse;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        long $expiresIn = this.getExpiresIn();
        result = result * 59 + (int)($expiresIn >>> 32 ^ $expiresIn);
        long $refreshExpiresIn = this.getRefreshExpiresIn();
        result = result * 59 + (int)($refreshExpiresIn >>> 32 ^ $refreshExpiresIn);
        String $nanoId = this.getNanoId();
        result = result * 59 + ($nanoId == null ? 43 : $nanoId.hashCode());
        String $username = this.getUsername();
        result = result * 59 + ($username == null ? 43 : $username.hashCode());
        String $accessToken = this.getAccessToken();
        result = result * 59 + ($accessToken == null ? 43 : $accessToken.hashCode());
        String $refreshToken = this.getRefreshToken();
        result = result * 59 + ($refreshToken == null ? 43 : $refreshToken.hashCode());
        String $tokenType = this.getTokenType();
        result = result * 59 + ($tokenType == null ? 43 : $tokenType.hashCode());
        Set $permissions = this.getPermissions();
        result = result * 59 + ($permissions == null ? 43 : ((Object)$permissions).hashCode());
        Set $roles = this.getRoles();
        result = result * 59 + ($roles == null ? 43 : ((Object)$roles).hashCode());
        return result;
    }

    public String toString() {
        return "LoginResponse(nanoId=" + this.getNanoId() + ", username=" + this.getUsername() + ", accessToken=" + this.getAccessToken() + ", expiresIn=" + this.getExpiresIn() + ", refreshToken=" + this.getRefreshToken() + ", refreshExpiresIn=" + this.getRefreshExpiresIn() + ", tokenType=" + this.getTokenType() + ", permissions=" + String.valueOf(this.getPermissions()) + ", roles=" + String.valueOf(this.getRoles()) + ")";
    }

    public static class LoginResponseBuilder {
        private String nanoId;
        private String username;
        private String accessToken;
        private long expiresIn;
        private String refreshToken;
        private long refreshExpiresIn;
        private String tokenType;
        private Set<String> permissions;
        private Set<String> roles;

        LoginResponseBuilder() {
        }

        public LoginResponseBuilder nanoId(String nanoId) {
            this.nanoId = nanoId;
            return this;
        }

        public LoginResponseBuilder username(String username) {
            this.username = username;
            return this;
        }

        public LoginResponseBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public LoginResponseBuilder expiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        public LoginResponseBuilder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public LoginResponseBuilder refreshExpiresIn(long refreshExpiresIn) {
            this.refreshExpiresIn = refreshExpiresIn;
            return this;
        }

        public LoginResponseBuilder tokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }

        public LoginResponseBuilder permissions(Set<String> permissions) {
            this.permissions = permissions;
            return this;
        }

        public LoginResponseBuilder roles(Set<String> roles) {
            this.roles = roles;
            return this;
        }

        public LoginResponse build() {
            return new LoginResponse(this.nanoId, this.username, this.accessToken, this.expiresIn, this.refreshToken, this.refreshExpiresIn, this.tokenType, this.permissions, this.roles);
        }

        public String toString() {
            return "LoginResponse.LoginResponseBuilder(nanoId=" + this.nanoId + ", username=" + this.username + ", accessToken=" + this.accessToken + ", expiresIn=" + this.expiresIn + ", refreshToken=" + this.refreshToken + ", refreshExpiresIn=" + this.refreshExpiresIn + ", tokenType=" + this.tokenType + ", permissions=" + String.valueOf(this.permissions) + ", roles=" + String.valueOf(this.roles) + ")";
        }
    }
}

