package com.lab.common.security;

import com.lab.common.security.UserPrincipal;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal
implements UserDetails {
    private final Long id;
    private final String nanoId;
    private final String username;
    private final Long deptId;
    private final Long mentorGroupId;
    private final Set<String> permissions;
    private final Set<String> roles;
    private final String jti;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.permissions == null || this.permissions.isEmpty()) {
            return Collections.emptySet();
        }
        return this.permissions.stream().map(code -> (GrantedAuthority & Serializable)() -> code).toList();
    }

    public String getPassword() {
        return null;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean hasPermission(String code) {
        return this.permissions != null && this.permissions.contains(code);
    }

    public boolean hasRole(String roleCode) {
        return this.roles != null && this.roles.contains(roleCode);
    }

    UserPrincipal(Long id, String nanoId, String username, Long deptId, Long mentorGroupId, Set<String> permissions, Set<String> roles, String jti) {
        this.id = id;
        this.nanoId = nanoId;
        this.username = username;
        this.deptId = deptId;
        this.mentorGroupId = mentorGroupId;
        this.permissions = permissions;
        this.roles = roles;
        this.jti = jti;
    }

    public static UserPrincipalBuilder builder() {
        return new UserPrincipalBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getNanoId() {
        return this.nanoId;
    }

    public String getUsername() {
        return this.username;
    }

    public Long getDeptId() {
        return this.deptId;
    }

    public Long getMentorGroupId() {
        return this.mentorGroupId;
    }

    public Set<String> getPermissions() {
        return this.permissions;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public String getJti() {
        return this.jti;
    }

    public static class UserPrincipalBuilder {
        private Long id;
        private String nanoId;
        private String username;
        private Long deptId;
        private Long mentorGroupId;
        private Set<String> permissions;
        private Set<String> roles;
        private String jti;

        UserPrincipalBuilder() {
        }

        public UserPrincipalBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserPrincipalBuilder nanoId(String nanoId) {
            this.nanoId = nanoId;
            return this;
        }

        public UserPrincipalBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserPrincipalBuilder deptId(Long deptId) {
            this.deptId = deptId;
            return this;
        }

        public UserPrincipalBuilder mentorGroupId(Long mentorGroupId) {
            this.mentorGroupId = mentorGroupId;
            return this;
        }

        public UserPrincipalBuilder permissions(Set<String> permissions) {
            this.permissions = permissions;
            return this;
        }

        public UserPrincipalBuilder roles(Set<String> roles) {
            this.roles = roles;
            return this;
        }

        public UserPrincipalBuilder jti(String jti) {
            this.jti = jti;
            return this;
        }

        public UserPrincipal build() {
            return new UserPrincipal(this.id, this.nanoId, this.username, this.deptId, this.mentorGroupId, this.permissions, this.roles, this.jti);
        }

        public String toString() {
            return "UserPrincipal.UserPrincipalBuilder(id=" + this.id + ", nanoId=" + this.nanoId + ", username=" + this.username + ", deptId=" + this.deptId + ", mentorGroupId=" + this.mentorGroupId + ", permissions=" + String.valueOf(this.permissions) + ", roles=" + String.valueOf(this.roles) + ", jti=" + this.jti + ")";
        }
    }
}

