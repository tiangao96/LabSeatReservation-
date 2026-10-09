package com.lab.module.rbac.service;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.module.rbac.dto.PermissionCreateRequest;
import com.lab.module.rbac.dto.PermissionVo;
import com.lab.module.rbac.entity.CoreMenu;
import com.lab.module.rbac.entity.CorePermission;
import com.lab.module.rbac.repository.CoreMenuRepository;
import com.lab.module.rbac.repository.CorePermissionRepository;
import com.lab.module.rbac.repository.CoreRolePermissionRepository;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissionService {
    private final CorePermissionRepository permissionRepository;
    private final CoreMenuRepository menuRepository;
    private final CoreRolePermissionRepository rolePermissionRepository;

    public List<PermissionVo> list() {
        return this.permissionRepository.findAll(Sort.by(Sort.Direction.ASC, (String[])new String[]{"sort"})).stream().map(this::toVo).toList();
    }

    public PermissionVo getByNanoId(String nanoId) {
        CorePermission p = this.requirePermission(nanoId);
        PermissionVo v = this.toVo(p);
        if (p.getMenuId() != null) {
            this.menuRepository.findById(p.getMenuId()).ifPresent(m -> v.setMenuNanoId(m.getNanoId()));
        }
        return v;
    }

    @Transactional
    public String create(PermissionCreateRequest req) {
        Long menuId = this.resolveMenuId(req.getMenuNanoId());
        this.checkUnique(menuId, req.getCode(), null);
        CorePermission p = new CorePermission();
        p.setName(req.getName());
        p.setCode(req.getCode());
        p.setMenuId(menuId);
        p.setIsActive(Integer.valueOf(req.getIsActive() != null ? req.getIsActive() : 1));
        this.applyFields(p, req);
        p = (CorePermission)this.permissionRepository.save(p);
        return p.getNanoId();
    }

    @Transactional
    public void update(String nanoId, PermissionCreateRequest req) {
        CorePermission p = this.requirePermission(nanoId);
        Long menuId = this.resolveMenuId(req.getMenuNanoId());
        this.checkUnique(menuId, req.getCode(), p.getId());
        p.setName(req.getName());
        p.setCode(req.getCode());
        p.setMenuId(menuId);
        if (req.getIsActive() != null) {
            p.setIsActive(req.getIsActive());
        }
        this.applyFields(p, req);
        this.permissionRepository.save(p);
    }

    @Transactional
    public void delete(String nanoId) {
        CorePermission p = this.requirePermission(nanoId);
        this.rolePermissionRepository.deleteByPermissionId(p.getId());
        p.setIsDeleted(Integer.valueOf(1));
        this.permissionRepository.save(p);
    }

    private CorePermission requirePermission(String nanoId) {
        CorePermission p = this.permissionRepository.findByNanoId(nanoId);
        if (p == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        return p;
    }

    private Long resolveMenuId(String menuNanoId) {
        if (menuNanoId == null || menuNanoId.isBlank()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5173\u8054\u83dc\u5355\u4e0d\u80fd\u4e3a\u7a7a");
        }
        CoreMenu m = this.menuRepository.findByNanoId(menuNanoId);
        if (m == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u83dc\u5355\u4e0d\u5b58\u5728: " + menuNanoId);
        }
        return m.getId();
    }

    private void checkUnique(Long menuId, String code, Long excludeId) {
        CorePermission exist = this.permissionRepository.findByMenuIdAndCode(menuId, code);
        if (exist != null && !exist.getId().equals(excludeId)) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u6743\u9650\u7f16\u7801\u5728\u8be5\u83dc\u5355\u4e0b\u5df2\u5b58\u5728: " + code);
        }
    }

    private void applyFields(CorePermission p, PermissionCreateRequest req) {
        p.setPermissionType(Integer.valueOf(req.getPermissionType() != null ? req.getPermissionType() : 1));
        p.setApiPath(req.getApiPath());
        p.setHttpMethod(req.getHttpMethod());
        p.setDescription(req.getDescription());
        p.setSort(req.getSort());
    }

    private PermissionVo toVo(CorePermission p) {
        PermissionVo vo = new PermissionVo();
        vo.setNanoId(p.getNanoId());
        vo.setName(p.getName());
        vo.setCode(p.getCode());
        vo.setPermissionType(p.getPermissionType());
        vo.setApiPath(p.getApiPath());
        vo.setHttpMethod(p.getHttpMethod());
        vo.setDescription(p.getDescription());
        vo.setIsActive(p.getIsActive());
        vo.setSort(p.getSort());
        return vo;
    }

    public PermissionService(CorePermissionRepository permissionRepository, CoreMenuRepository menuRepository, CoreRolePermissionRepository rolePermissionRepository) {
        this.permissionRepository = permissionRepository;
        this.menuRepository = menuRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }
}

