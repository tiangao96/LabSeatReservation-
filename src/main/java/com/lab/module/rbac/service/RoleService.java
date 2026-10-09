package com.lab.module.rbac.service;

import com.lab.common.api.ResultCode;
import com.lab.common.entity.BaseEntity;
import com.lab.common.exception.BizException;
import com.lab.module.rbac.dto.RoleCreateRequest;
import com.lab.module.rbac.dto.RoleVo;
import com.lab.module.rbac.entity.CoreMenu;
import com.lab.module.rbac.entity.CorePermission;
import com.lab.module.rbac.entity.CoreRole;
import com.lab.module.rbac.entity.CoreRoleMenu;
import com.lab.module.rbac.entity.CoreRolePermission;
import com.lab.module.rbac.repository.CoreMenuRepository;
import com.lab.module.rbac.repository.CorePermissionRepository;
import com.lab.module.rbac.repository.CoreRoleMenuRepository;
import com.lab.module.rbac.repository.CoreRolePermissionRepository;
import com.lab.module.rbac.repository.CoreRoleRepository;
import com.lab.module.rbac.repository.CoreUserRoleRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    private final CoreRoleRepository roleRepository;
    private final CorePermissionRepository permissionRepository;
    private final CoreMenuRepository menuRepository;
    private final CoreRolePermissionRepository rolePermissionRepository;
    private final CoreRoleMenuRepository roleMenuRepository;
    private final CoreUserRoleRepository userRoleRepository;

    public List<RoleVo> list(String keyword) {
        return this.roleRepository.searchByKeyword(keyword, Sort.by(Sort.Direction.DESC, (String[])new String[]{"priority"})).stream().map(this::toVo).toList();
    }

    public RoleVo getByNanoId(String nanoId) {
        return this.toVo(this.requireRole(nanoId));
    }

    @Transactional
    public String create(RoleCreateRequest req) {
        if (this.roleRepository.findByCode(req.getCode()) != null) {
            throw new BizException(ResultCode.ROLE_EXISTS);
        }
        CoreRole r = new CoreRole();
        r.setName(req.getName());
        r.setCode(req.getCode());
        r.setRoleType(Integer.valueOf(req.getRoleType() != null ? req.getRoleType() : 1));
        r.setStatus(Integer.valueOf(req.getStatus() != null ? req.getStatus() : 1));
        r.setPriority(req.getPriority());
        r.setDescription(req.getDescription());
        r = (CoreRole)this.roleRepository.save(r);
        return r.getNanoId();
    }

    @Transactional
    public void update(String nanoId, RoleCreateRequest req) {
        CoreRole r = this.requireRole(nanoId);
        if (!r.getCode().equals(req.getCode())) {
            CoreRole other = this.roleRepository.findByCode(req.getCode());
            if (other != null && !other.getId().equals(r.getId())) {
                throw new BizException(ResultCode.ROLE_EXISTS);
            }
            r.setCode(req.getCode());
        }
        r.setName(req.getName());
        r.setRoleType(req.getRoleType());
        r.setStatus(req.getStatus());
        r.setPriority(req.getPriority());
        r.setDescription(req.getDescription());
        this.roleRepository.save(r);
    }

    @Transactional
    public void delete(String nanoId) {
        CoreRole r = this.requireRole(nanoId);
        if (this.userRoleRepository.existsByRoleId(r.getId())) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u89d2\u8272\u5df2\u5206\u914d\u7ed9\u7528\u6237\uff0c\u8bf7\u5148\u89e3\u9664");
        }
        this.rolePermissionRepository.deleteByRoleId(r.getId());
        this.roleMenuRepository.deleteByRoleId(r.getId());
        r.setIsDeleted(Integer.valueOf(1));
        this.roleRepository.save(r);
    }

    @Transactional
    public void assignPermissions(String roleNanoId, List<String> permissionNanoIds) {
        CoreRole role = this.requireRole(roleNanoId);
        this.rolePermissionRepository.deleteByRoleId(role.getId());
        if (permissionNanoIds == null || permissionNanoIds.isEmpty()) {
            return;
        }
        List<CorePermission> perms = this.permissionRepository.findAllByNanoIdIn(permissionNanoIds);
        for (CorePermission p : perms) {
            this.rolePermissionRepository.save(new CoreRolePermission(role.getId(), p.getId()));
        }
    }

    @Transactional
    public void assignMenus(String roleNanoId, List<String> menuNanoIds) {
        CoreRole role = this.requireRole(roleNanoId);
        this.roleMenuRepository.deleteByRoleId(role.getId());
        if (menuNanoIds == null || menuNanoIds.isEmpty()) {
            return;
        }
        List<CoreMenu> menus = this.menuRepository.findAllByNanoIdIn(menuNanoIds);
        for (CoreMenu m : menus) {
            this.roleMenuRepository.save(new CoreRoleMenu(role.getId(), m.getId()));
        }
    }

    public List<String> listPermissionNanoIds(String roleNanoId) {
        CoreRole role = this.requireRole(roleNanoId);
        List<Long> permIds = this.rolePermissionRepository.findPermissionIdsByRoleId(role.getId());
        if (permIds.isEmpty()) {
            return List.of();
        }
        return this.permissionRepository.findAllById(permIds).stream().map(BaseEntity::getNanoId).toList();
    }

    public List<String> listMenuNanoIds(String roleNanoId) {
        CoreRole role = this.requireRole(roleNanoId);
        List<Long> menuIds = this.roleMenuRepository.findMenuIdsByRoleId(role.getId());
        if (menuIds.isEmpty()) {
            return List.of();
        }
        return this.menuRepository.findAllById(menuIds).stream().map(BaseEntity::getNanoId).toList();
    }

    private CoreRole requireRole(String nanoId) {
        CoreRole r = this.roleRepository.findByNanoId(nanoId);
        if (r == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        return r;
    }

    private RoleVo toVo(CoreRole r) {
        RoleVo vo = new RoleVo();
        vo.setNanoId(r.getNanoId());
        vo.setName(r.getName());
        vo.setCode(r.getCode());
        vo.setRoleType(r.getRoleType());
        vo.setStatus(r.getStatus());
        vo.setPriority(r.getPriority());
        vo.setDescription(r.getDescription());
        return vo;
    }

    public RoleService(CoreRoleRepository roleRepository, CorePermissionRepository permissionRepository, CoreMenuRepository menuRepository, CoreRolePermissionRepository rolePermissionRepository, CoreRoleMenuRepository roleMenuRepository, CoreUserRoleRepository userRoleRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.menuRepository = menuRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.roleMenuRepository = roleMenuRepository;
        this.userRoleRepository = userRoleRepository;
    }
}

