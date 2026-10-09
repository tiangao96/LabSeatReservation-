package com.lab.module.rbac.service;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.module.rbac.dto.MenuCreateRequest;
import com.lab.module.rbac.dto.MenuTreeNode;
import com.lab.module.rbac.dto.MenuVo;
import com.lab.module.rbac.entity.CoreMenu;
import com.lab.module.rbac.repository.CoreMenuRepository;
import com.lab.module.rbac.repository.CoreRoleMenuRepository;
import com.lab.module.workstation.entity.RentalRequestStatus;
import com.lab.module.workstation.entity.WorkstationRentalRequest;
import com.lab.module.workstation.repository.WorkstationRentalRequestRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuService {
    private final CoreRoleMenuRepository roleMenuRepository;
    private final CoreMenuRepository menuRepository;
    private final WorkstationRentalRequestRepository rentalRequestRepository;

    public List<MenuTreeNode> currentUserMenuTree(Long userId) {
        List<Long> menuIds = this.roleMenuRepository.findMenuIdsByUserId(userId);
        if (menuIds.isEmpty()) {
            return List.of();
        }
        List<CoreMenu> menus = this.menuRepository.findAllById(menuIds);
        LinkedHashMap<Long, MenuTreeNode> nodeMap = new LinkedHashMap<Long, MenuTreeNode>();
        for (CoreMenu m : menus) {
            nodeMap.put(m.getId(), this.toNode(m));
        }
        ArrayList<MenuTreeNode> roots = new ArrayList<MenuTreeNode>();
        for (CoreMenu m : menus) {
            MenuTreeNode node = (MenuTreeNode)nodeMap.get(m.getId());
            Long pid = m.getParentId();
            if (pid == null || !nodeMap.containsKey(pid)) {
                roots.add(node);
                continue;
            }
            ((MenuTreeNode)nodeMap.get(pid)).getChildren().add(node);
        }
        Comparator<MenuTreeNode> byOrder = Comparator.comparingInt(n -> n.getOrder() == null ? 0 : n.getOrder());
        this.sortTree(roots, byOrder);
        return roots;
    }

    private void sortTree(List<MenuTreeNode> nodes, Comparator<MenuTreeNode> cmp) {
        nodes.sort(cmp);
        for (MenuTreeNode n : nodes) {
            this.sortTree(n.getChildren(), cmp);
        }
    }

    private MenuTreeNode toNode(CoreMenu m) {
        MenuTreeNode n = new MenuTreeNode();
        n.setNanoId(m.getNanoId());
        n.setName(m.getName());
        n.setTitle(m.getTitle());
        n.setPath(m.getPath());
        n.setComponent(m.getComponent());
        n.setIcon(m.getIcon());
        n.setOrder(m.getOrder());
        this.applyBadge(n, m);
        return n;
    }

    private void applyBadge(MenuTreeNode n, CoreMenu m) {
        if ("/lab/rental".equals(m.getPath())) {
            List<WorkstationRentalRequest> pending = this.rentalRequestRepository.findWithFilters(RentalRequestStatus.PENDING, null, null);
            int count = pending == null ? 0 : pending.size();
            if (count > 0) {
                n.setBadge(String.valueOf(count));
                n.setBadgeType("number");
                n.setBadgeVariants("destructive");
            }
        }
    }

    public List<MenuVo> list() {
        List<CoreMenu> menus = this.menuRepository.findAll(Sort.by(Sort.Direction.ASC, new String[]{"order"}).and(Sort.by(Sort.Direction.ASC, new String[]{"id"})));
        return this.buildTree(menus);
    }

    public MenuVo getByNanoId(String nanoId) {
        CoreMenu m = this.requireMenu(nanoId);
        MenuVo v = this.toVo(m);
        if (m.getParentId() != null) {
            this.menuRepository.findById(m.getParentId()).ifPresent(p -> v.setParentNanoId(p.getNanoId()));
        }
        return v;
    }

    @Transactional
    public String create(MenuCreateRequest req) {
        CoreMenu m = new CoreMenu();
        m.setName(req.getName());
        m.setPath(req.getPath());
        m.setParentId(this.resolveParentId(req.getParentNanoId()));
        m.setIsSystem(Integer.valueOf(req.getIsSystem() != null ? req.getIsSystem() : 0));
        this.applyFields(m, req);
        m = this.menuRepository.save(m);
        return m.getNanoId();
    }

    @Transactional
    public void update(String nanoId, MenuCreateRequest req) {
        CoreMenu m = this.requireMenu(nanoId);
        Long newParentId = this.resolveParentId(req.getParentNanoId());
        if (newParentId != null) {
            if (newParentId.equals(m.getId())) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u4e0d\u80fd\u5c06\u81ea\u8eab\u8bbe\u4e3a\u7236\u83dc\u5355");
            }
            if (this.isDescendant(newParentId, m.getId())) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u4e0d\u80fd\u5c06\u5b50\u83dc\u5355\u8bbe\u4e3a\u7236\u83dc\u5355\uff08\u4f1a\u5f62\u6210\u73af\uff09");
            }
        }
        m.setParentId(newParentId);
        m.setName(req.getName());
        m.setPath(req.getPath());
        if (req.getIsSystem() != null) {
            m.setIsSystem(req.getIsSystem());
        }
        this.applyFields(m, req);
        this.menuRepository.save(m);
    }

    @Transactional
    public void delete(String nanoId) {
        CoreMenu m = this.requireMenu(nanoId);
        if (m.getIsSystem() != null && m.getIsSystem() == 1) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7cfb\u7edf\u83dc\u5355\u4e0d\u53ef\u5220\u9664");
        }
        if (this.menuRepository.existsByParentId(m.getId())) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5b58\u5728\u5b50\u83dc\u5355\uff0c\u8bf7\u5148\u5220\u9664\u5b50\u83dc\u5355");
        }
        this.roleMenuRepository.deleteByMenuId(m.getId());
        m.setIsDeleted(Integer.valueOf(1));
        this.menuRepository.save(m);
    }

    private CoreMenu requireMenu(String nanoId) {
        CoreMenu m = this.menuRepository.findByNanoId(nanoId);
        if (m == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        return m;
    }

    private Long resolveParentId(String parentNanoId) {
        if (parentNanoId == null || parentNanoId.isBlank()) {
            return null;
        }
        CoreMenu parent = this.menuRepository.findByNanoId(parentNanoId);
        if (parent == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7236\u83dc\u5355\u4e0d\u5b58\u5728: " + parentNanoId);
        }
        return parent.getId();
    }

    private boolean isDescendant(Long candidateId, Long rootId) {
        HashSet<Long> visited = new HashSet<Long>();
        Long cur = candidateId;
        while (cur != null && visited.add(cur)) {
            if (cur.equals(rootId)) {
                return true;
            }
            CoreMenu m = this.menuRepository.findById(cur).orElse(null);
            if (m == null) {
                return false;
            }
            cur = m.getParentId();
        }
        return false;
    }

    private void applyFields(CoreMenu m, MenuCreateRequest req) {
        m.setApplicationId(req.getApplicationId());
        m.setTitle(req.getTitle());
        m.setAuthCode(req.getAuthCode());
        m.setType(req.getType());
        m.setComponent(req.getComponent());
        m.setRedirect(req.getRedirect());
        m.setActivePath(req.getActivePath());
        m.setQuery(req.getQuery());
        m.setNoBasicLayout(req.getNoBasicLayout());
        m.setIcon(req.getIcon());
        m.setActiveIcon(req.getActiveIcon());
        m.setOrder(req.getOrder());
        m.setHideInMenu(req.getHideInMenu());
        m.setHideChildrenInMenu(req.getHideChildrenInMenu());
        m.setHideInBreadcrumb(req.getHideInBreadcrumb());
        m.setHideInTab(req.getHideInTab());
        m.setAffixTab(req.getAffixTab());
        m.setAffixTabOrder(req.getAffixTabOrder());
        m.setKeepAlive(req.getKeepAlive());
        m.setMaxNumOfOpenTab(req.getMaxNumOfOpenTab());
        m.setFullPathKey(req.getFullPathKey());
        m.setLink(req.getLink());
        m.setIframeSrc(req.getIframeSrc());
        m.setOpenInNewWindow(req.getOpenInNewWindow());
        m.setBadge(req.getBadge());
        m.setBadgeType(req.getBadgeType());
        m.setBadgeVariants(req.getBadgeVariants());
    }

    private List<MenuVo> buildTree(List<CoreMenu> menus) {
        LinkedHashMap<Long, MenuVo> nodeMap = new LinkedHashMap<Long, MenuVo>();
        for (CoreMenu coreMenu : menus) {
            nodeMap.put(coreMenu.getId(), this.toVo(coreMenu));
        }
        ArrayList<MenuVo> roots = new ArrayList<MenuVo>();
        for (CoreMenu m : menus) {
            MenuVo node = (MenuVo)nodeMap.get(m.getId());
            Long pid = m.getParentId();
            if (pid == null || !nodeMap.containsKey(pid)) {
                roots.add(node);
                continue;
            }
            MenuVo parent = (MenuVo)nodeMap.get(pid);
            node.setParentNanoId(parent.getNanoId());
            parent.getChildren().add(node);
        }
        Comparator<MenuVo> comparator = Comparator.comparingInt(n -> n.getOrder() == null ? 0 : n.getOrder());
        this.sortVoTree(roots, comparator);
        return roots;
    }

    private void sortVoTree(List<MenuVo> nodes, Comparator<MenuVo> cmp) {
        nodes.sort(cmp);
        for (MenuVo n : nodes) {
            this.sortVoTree(n.getChildren(), cmp);
        }
    }

    private MenuVo toVo(CoreMenu m) {
        MenuVo v = new MenuVo();
        v.setNanoId(m.getNanoId());
        v.setApplicationId(m.getApplicationId());
        v.setIsSystem(m.getIsSystem());
        v.setName(m.getName());
        v.setTitle(m.getTitle());
        v.setAuthCode(m.getAuthCode());
        v.setPath(m.getPath());
        v.setType(m.getType());
        v.setComponent(m.getComponent());
        v.setRedirect(m.getRedirect());
        v.setActivePath(m.getActivePath());
        v.setQuery(m.getQuery());
        v.setNoBasicLayout(m.getNoBasicLayout());
        v.setIcon(m.getIcon());
        v.setActiveIcon(m.getActiveIcon());
        v.setOrder(m.getOrder());
        v.setHideInMenu(m.getHideInMenu());
        v.setHideChildrenInMenu(m.getHideChildrenInMenu());
        v.setHideInBreadcrumb(m.getHideInBreadcrumb());
        v.setHideInTab(m.getHideInTab());
        v.setAffixTab(m.getAffixTab());
        v.setAffixTabOrder(m.getAffixTabOrder());
        v.setKeepAlive(m.getKeepAlive());
        v.setMaxNumOfOpenTab(m.getMaxNumOfOpenTab());
        v.setFullPathKey(m.getFullPathKey());
        v.setLink(m.getLink());
        v.setIframeSrc(m.getIframeSrc());
        v.setOpenInNewWindow(m.getOpenInNewWindow());
        v.setBadge(m.getBadge());
        v.setBadgeType(m.getBadgeType());
        v.setBadgeVariants(m.getBadgeVariants());
        return v;
    }

        public MenuService(CoreRoleMenuRepository roleMenuRepository, CoreMenuRepository menuRepository, WorkstationRentalRequestRepository rentalRequestRepository) {
        this.roleMenuRepository = roleMenuRepository;
        this.menuRepository = menuRepository;
        this.rentalRequestRepository = rentalRequestRepository;
    }
}

