package com.lab.module.rbac.service;

import com.lab.common.entity.BaseEntity;
import com.lab.common.security.DeptResolver;
import com.lab.common.security.DeptTreeNode;
import com.lab.module.rbac.entity.CoreDept;
import com.lab.module.rbac.repository.CoreDeptRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class CoreDeptDeptResolver
implements DeptResolver {
    private final CoreDeptRepository deptRepository;

    public String resolveNanoId(Long deptId) {
        if (deptId == null) {
            return null;
        }
        return this.deptRepository.findById(deptId).map(BaseEntity::getNanoId).orElse(null);
    }

    public String resolveName(Long deptId) {
        if (deptId == null) {
            return null;
        }
        return this.deptRepository.findById(deptId).map(CoreDept::getName).orElse(null);
    }

    public Long resolveDeptId(String nanoId) {
        if (nanoId == null || nanoId.isBlank()) {
            return null;
        }
        CoreDept d = this.deptRepository.findByNanoId(nanoId);
        return d != null ? d.getId() : null;
    }

    public List<DeptTreeNode> deptTree(int maxLevels) {
        List<CoreDept> depts = this.deptRepository.findAll(Sort.by(Sort.Direction.ASC, (String[])new String[]{"sort"}).and(Sort.by(Sort.Direction.ASC, (String[])new String[]{"id"})));
        LinkedHashMap<Long, DeptTreeNode> nodeMap = new LinkedHashMap<Long, DeptTreeNode>();
        LinkedHashMap<String, Integer> sortMap = new LinkedHashMap<String, Integer>();
        LinkedHashMap<Long, Long> parentIdMap = new LinkedHashMap<Long, Long>();
        LinkedHashMap<Long, Integer> depthMap = new LinkedHashMap<Long, Integer>();
        for (CoreDept d : depts) {
            DeptTreeNode node = new DeptTreeNode();
            node.setNanoId(d.getNanoId());
            node.setName(d.getName());
            nodeMap.put(d.getId(), node);
            parentIdMap.put(d.getId(), d.getParentId());
            sortMap.put(d.getNanoId(), d.getSort());
        }
        ArrayList<DeptTreeNode> roots = new ArrayList<DeptTreeNode>();
        for (CoreDept d : depts) {
            Long pid = (Long)parentIdMap.get(d.getId());
            DeptTreeNode node = (DeptTreeNode)nodeMap.get(d.getId());
            node.setLevel(Integer.valueOf(this.depthOf(d.getId(), parentIdMap, depthMap)));
            if (pid == null || !nodeMap.containsKey(pid)) {
                roots.add(node);
                continue;
            }
            DeptTreeNode parent = (DeptTreeNode)nodeMap.get(pid);
            node.setParentNanoId(parent.getNanoId());
            if (node.getLevel() > maxLevels) continue;
            parent.getChildren().add(node);
        }
        Comparator<DeptTreeNode> bySort = Comparator.comparingInt((DeptTreeNode n) -> sortMap.getOrDefault(n.getNanoId(), 0)).thenComparing(DeptTreeNode::getNanoId);
        this.sortTree(roots, bySort);
        return roots;
    }

    private int depthOf(Long id, Map<Long, Long> parentIdMap, Map<Long, Integer> depthMap) {
        Integer cached = depthMap.get(id);
        if (cached != null) {
            return cached;
        }
        depthMap.put(id, 1);
        Long pid = parentIdMap.get(id);
        int depth = pid == null || !parentIdMap.containsKey(pid) ? 1 : this.depthOf(pid, parentIdMap, depthMap) + 1;
        depthMap.put(id, depth);
        return depth;
    }

    private void sortTree(List<DeptTreeNode> nodes, Comparator<DeptTreeNode> cmp) {
        nodes.sort(cmp);
        for (DeptTreeNode n : nodes) {
            this.sortTree(n.getChildren(), cmp);
        }
    }

    public CoreDeptDeptResolver(CoreDeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }
}

