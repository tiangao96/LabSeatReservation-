package com.lab.module.rbac.service;

import com.lab.module.rbac.entity.CoreRole;
import com.lab.module.rbac.repository.CorePermissionRepository;
import com.lab.module.rbac.repository.CoreRoleMenuRepository;
import com.lab.module.rbac.repository.CoreRoleRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class PermissionLoader {
    private final CoreRoleRepository roleRepository;
    private final CorePermissionRepository permissionRepository;
    private final CoreRoleMenuRepository roleMenuRepository;

    public Set<String> loadPermissionCodes(Long userId) {
        return new HashSet<String>(this.permissionRepository.findPermissionCodesByUserId(userId));
    }

    public Set<String> loadRoleCodes(Long userId) {
        return new HashSet<String>(this.roleRepository.findRoleCodesByUserId(userId));
    }

    public List<CoreRole> loadRoles(Long userId) {
        return this.roleRepository.findRolesByUserId(userId);
    }

    public List<Long> loadMenuIds(Long userId) {
        return this.roleMenuRepository.findMenuIdsByUserId(userId);
    }

    public PermissionLoader(CoreRoleRepository roleRepository, CorePermissionRepository permissionRepository, CoreRoleMenuRepository roleMenuRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.roleMenuRepository = roleMenuRepository;
    }
}

