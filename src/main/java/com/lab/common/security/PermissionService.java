package com.lab.common.security;

import com.lab.common.security.SecurityContextUtil;
import com.lab.common.security.UserPrincipal;
import org.springframework.stereotype.Component;

@Component(value="perm")
public class PermissionService {
    public boolean has(String code) {
        UserPrincipal up = SecurityContextUtil.current();
        return up != null && up.hasPermission(code);
    }

    public boolean hasAny(String ... codes) {
        UserPrincipal up = SecurityContextUtil.current();
        if (up == null || codes == null) {
            return false;
        }
        for (String c : codes) {
            if (!up.hasPermission(c)) continue;
            return true;
        }
        return false;
    }

    public boolean hasRole(String roleCode) {
        UserPrincipal up = SecurityContextUtil.current();
        return up != null && up.hasRole(roleCode);
    }
}

