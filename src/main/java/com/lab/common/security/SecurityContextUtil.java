package com.lab.common.security;

import com.lab.common.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/*
 * Exception performing whole class analysis ignored.
 */
public final class SecurityContextUtil {
    private SecurityContextUtil() {
    }

    public static UserPrincipal current() {
        Object object;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && (object = auth.getPrincipal()) instanceof UserPrincipal) {
            UserPrincipal up = (UserPrincipal)object;
            return up;
        }
        return null;
    }

    public static Long currentUserId() {
        UserPrincipal up = SecurityContextUtil.current();
        return up != null ? up.getId() : null;
    }

    public static Long currentDeptId() {
        UserPrincipal up = SecurityContextUtil.current();
        return up != null ? up.getDeptId() : null;
    }

    public static Long currentMentorGroupId() {
        UserPrincipal up = SecurityContextUtil.current();
        return up != null ? up.getMentorGroupId() : null;
    }
}

