package com.lab.module.rbac.service;

import com.lab.common.entity.BaseEntity;
import com.lab.common.security.UserBrief;
import com.lab.common.security.UserIdResolver;
import com.lab.module.rbac.entity.CoreUser;
import com.lab.module.rbac.repository.CoreUserRepository;
import java.util.List;
import org.springframework.stereotype.Component;

/*
 * Exception performing whole class analysis ignored.
 */
@Component
public class CoreUserUserIdResolver
implements UserIdResolver {
    private final CoreUserRepository userRepository;

    public String resolveNanoId(Long userId) {
        if (userId == null) {
            return null;
        }
        return this.userRepository.findById(userId).map(BaseEntity::getNanoId).orElse(null);
    }

    public String resolveName(Long userId) {
        if (userId == null) {
            return null;
        }
        return this.userRepository.findById(userId).map(CoreUserUserIdResolver::displayName).orElse(null);
    }

    public Long resolveUserId(String nanoId) {
        if (nanoId == null || nanoId.isBlank()) {
            return null;
        }
        CoreUser u = this.userRepository.findByNanoId(nanoId);
        return u != null ? u.getId() : null;
    }

    public List<UserBrief> listByDept(Long deptId) {
        if (deptId == null) {
            return List.of();
        }
        return this.userRepository.findByDeptId(deptId).stream().map(u -> new UserBrief(u.getId(), u.getNanoId(), CoreUserUserIdResolver.displayName((CoreUser)u))).toList();
    }

    public List<UserBrief> listByMentorGroup(Long mentorGroupId) {
        if (mentorGroupId == null) {
            return List.of();
        }
        return this.userRepository.findByMentorGroupId(mentorGroupId).stream().map(u -> new UserBrief(u.getId(), u.getNanoId(), CoreUserUserIdResolver.displayName((CoreUser)u))).toList();
    }

    private static String displayName(CoreUser u) {
        String name = u.getName();
        return name != null && !name.isBlank() ? name : u.getUsername();
    }

    public CoreUserUserIdResolver(CoreUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

