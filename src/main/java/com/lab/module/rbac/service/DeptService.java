package com.lab.module.rbac.service;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.module.rbac.dto.DeptCreateRequest;
import com.lab.module.rbac.dto.DeptVo;
import com.lab.module.rbac.entity.CoreDept;
import com.lab.module.rbac.entity.CoreUser;
import com.lab.module.rbac.repository.CoreDeptRepository;
import com.lab.module.rbac.repository.CoreUserRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptService {
    private final CoreDeptRepository deptRepository;
    private final CoreUserRepository userRepository;

    public List<DeptVo> list() {
        List<CoreDept> depts = this.deptRepository.findAll(Sort.by(Sort.Direction.ASC, (String[])new String[]{"sort"}).and(Sort.by(Sort.Direction.ASC, (String[])new String[]{"id"})));
        return this.buildTree(depts);
    }

    public DeptVo getByNanoId(String nanoId) {
        CoreDept d = this.requireDept(nanoId);
        DeptVo v = this.toVo(d);
        if (d.getParentId() != null) {
            this.deptRepository.findById(d.getParentId()).ifPresent(p -> v.setParentNanoId(p.getNanoId()));
        }
        return v;
    }

    @Transactional
    public String create(DeptCreateRequest req) {
        CoreDept d = new CoreDept();
        d.setName(req.getName());
        d.setParentId(this.resolveParentId(req.getParentNanoId()));
        d.setLeadId(this.resolveLeadId(req.getLeadNanoId()));
        this.applyFields(d, req);
        d = (CoreDept)this.deptRepository.save(d);
        return d.getNanoId();
    }

    @Transactional
    public void update(String nanoId, DeptCreateRequest req) {
        CoreDept d = this.requireDept(nanoId);
        Long newParentId = this.resolveParentId(req.getParentNanoId());
        if (newParentId != null) {
            if (newParentId.equals(d.getId())) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u4e0d\u80fd\u5c06\u81ea\u8eab\u8bbe\u4e3a\u7236\u90e8\u95e8");
            }
            if (this.isDescendant(newParentId, d.getId())) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u4e0d\u80fd\u5c06\u5b50\u90e8\u95e8\u8bbe\u4e3a\u7236\u90e8\u95e8\uff08\u4f1a\u5f62\u6210\u73af\uff09");
            }
        }
        d.setParentId(newParentId);
        d.setLeadId(this.resolveLeadId(req.getLeadNanoId()));
        d.setName(req.getName());
        this.applyFields(d, req);
        this.deptRepository.save(d);
    }

    @Transactional
    public void delete(String nanoId) {
        CoreDept d = this.requireDept(nanoId);
        if (this.deptRepository.existsByParentId(d.getId())) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5b58\u5728\u5b50\u90e8\u95e8\uff0c\u8bf7\u5148\u5220\u9664\u5b50\u90e8\u95e8");
        }
        if (this.userRepository.existsByDeptId(d.getId())) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u90e8\u95e8\u4e0b\u5b58\u5728\u7528\u6237\uff0c\u8bf7\u5148\u8fc1\u79fb\u7528\u6237");
        }
        d.setIsDeleted(Integer.valueOf(1));
        this.deptRepository.save(d);
    }

    private CoreDept requireDept(String nanoId) {
        CoreDept d = this.deptRepository.findByNanoId(nanoId);
        if (d == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        return d;
    }

    private Long resolveParentId(String parentNanoId) {
        if (parentNanoId == null || parentNanoId.isBlank()) {
            return null;
        }
        CoreDept p = this.deptRepository.findByNanoId(parentNanoId);
        if (p == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7236\u90e8\u95e8\u4e0d\u5b58\u5728: " + parentNanoId);
        }
        return p.getId();
    }

    private Long resolveLeadId(String leadNanoId) {
        if (leadNanoId == null || leadNanoId.isBlank()) {
            return null;
        }
        CoreUser u = this.userRepository.findByNanoId(leadNanoId);
        if (u == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u8d1f\u8d23\u4eba\u7528\u6237\u4e0d\u5b58\u5728: " + leadNanoId);
        }
        return u.getId();
    }

    private boolean isDescendant(Long candidateId, Long rootId) {
        HashSet<Long> visited = new HashSet<Long>();
        Long cur = candidateId;
        while (cur != null && visited.add(cur)) {
            if (cur.equals(rootId)) {
                return true;
            }
            CoreDept d = this.deptRepository.findById(cur).orElse(null);
            if (d == null) {
                return false;
            }
            cur = d.getParentId();
        }
        return false;
    }

    private void applyFields(CoreDept d, DeptCreateRequest req) {
        d.setCode(req.getCode());
        d.setDeptType(req.getDeptType());
        d.setPhone(req.getPhone());
        d.setEmail(req.getEmail());
        d.setStatus(req.getStatus());
        d.setDescription(req.getDescription());
        d.setLevel(req.getLevel());
        d.setPath(req.getPath());
        d.setSort(req.getSort());
    }

    private List<DeptVo> buildTree(List<CoreDept> depts) {
        LinkedHashMap<Long, DeptVo> nodeMap = new LinkedHashMap<Long, DeptVo>();
        for (CoreDept coreDept : depts) {
            nodeMap.put(coreDept.getId(), this.toVo(coreDept));
        }
        ArrayList<DeptVo> roots = new ArrayList<DeptVo>();
        for (CoreDept d : depts) {
            DeptVo node = (DeptVo)nodeMap.get(d.getId());
            Long pid = d.getParentId();
            if (pid == null || !nodeMap.containsKey(pid)) {
                roots.add(node);
                continue;
            }
            DeptVo parent = (DeptVo)nodeMap.get(pid);
            node.setParentNanoId(parent.getNanoId());
            parent.getChildren().add(node);
        }
        Comparator<DeptVo> comparator = Comparator.comparingInt(n -> n.getSort() == null ? 0 : n.getSort());
        this.sortTree(roots, comparator);
        return roots;
    }

    private void sortTree(List<DeptVo> nodes, Comparator<DeptVo> cmp) {
        nodes.sort(cmp);
        for (DeptVo n : nodes) {
            this.sortTree(n.getChildren(), cmp);
        }
    }

    private DeptVo toVo(CoreDept d) {
        DeptVo v = new DeptVo();
        v.setNanoId(d.getNanoId());
        v.setName(d.getName());
        v.setCode(d.getCode());
        v.setDeptType(d.getDeptType());
        v.setPhone(d.getPhone());
        v.setEmail(d.getEmail());
        v.setStatus(d.getStatus());
        v.setDescription(d.getDescription());
        v.setLevel(d.getLevel());
        v.setPath(d.getPath());
        v.setSort(d.getSort());
        if (d.getLeadId() != null) {
            this.userRepository.findById(d.getLeadId()).ifPresent(u -> v.setLeadNanoId(u.getNanoId()));
        }
        return v;
    }

    public DeptService(CoreDeptRepository deptRepository, CoreUserRepository userRepository) {
        this.deptRepository = deptRepository;
        this.userRepository = userRepository;
    }
}

