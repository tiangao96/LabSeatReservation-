package com.lab.module.rbac.service;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.module.rbac.dto.MentorGroupCreateRequest;
import com.lab.module.rbac.dto.MentorGroupVo;
import com.lab.module.rbac.entity.CoreMentorGroup;
import com.lab.module.rbac.entity.CoreUser;
import com.lab.module.rbac.repository.CoreMentorGroupRepository;
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
public class MentorGroupService {
    private static final int MAX_LEVEL = 3;
    private final CoreMentorGroupRepository mentorGroupRepository;
    private final CoreUserRepository userRepository;

    public List<MentorGroupVo> list() {
        List<CoreMentorGroup> groups = this.mentorGroupRepository.findAll(Sort.by(Sort.Direction.ASC, (String[])new String[]{"sort"}).and(Sort.by(Sort.Direction.ASC, (String[])new String[]{"id"})));
        return this.buildTree(groups);
    }

    public MentorGroupVo getByNanoId(String nanoId) {
        CoreMentorGroup g = this.requireGroup(nanoId);
        MentorGroupVo v = this.toVo(g);
        if (g.getParentId() != null) {
            this.mentorGroupRepository.findById(g.getParentId()).ifPresent(p -> v.setParentNanoId(p.getNanoId()));
        }
        return v;
    }

    @Transactional
    public String create(MentorGroupCreateRequest req) {
        CoreMentorGroup g = new CoreMentorGroup();
        g.setName(req.getName());
        Long parentId = this.resolveParentId(req.getParentNanoId());
        g.setParentId(parentId);
        g.setLevel(Integer.valueOf(this.computeLevel(parentId)));
        g.setLeadId(this.resolveLeadId(req.getLeadNanoId()));
        this.applyFields(g, req);
        g = (CoreMentorGroup)this.mentorGroupRepository.save(g);
        return g.getNanoId();
    }

    @Transactional
    public void update(String nanoId, MentorGroupCreateRequest req) {
        CoreMentorGroup g = this.requireGroup(nanoId);
        Long newParentId = this.resolveParentId(req.getParentNanoId());
        if (newParentId != null) {
            if (newParentId.equals(g.getId())) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u4e0d\u80fd\u5c06\u81ea\u8eab\u8bbe\u4e3a\u7236\u8282\u70b9");
            }
            if (this.isDescendant(newParentId, g.getId())) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u4e0d\u80fd\u5c06\u5b50\u8282\u70b9\u8bbe\u4e3a\u7236\u8282\u70b9\uff08\u4f1a\u5f62\u6210\u73af\uff09");
            }
        }
        g.setParentId(newParentId);
        g.setLevel(Integer.valueOf(this.computeLevel(newParentId)));
        g.setLeadId(this.resolveLeadId(req.getLeadNanoId()));
        g.setName(req.getName());
        this.applyFields(g, req);
        this.mentorGroupRepository.save(g);
    }

    @Transactional
    public void delete(String nanoId) {
        CoreMentorGroup g = this.requireGroup(nanoId);
        if (this.mentorGroupRepository.existsByParentId(g.getId())) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5b58\u5728\u5b50\u8282\u70b9\uff0c\u8bf7\u5148\u5220\u9664\u5b50\u8282\u70b9");
        }
        if (this.userRepository.existsByMentorGroupId(g.getId())) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5bfc\u5e08\u7ec4\u4e0b\u5b58\u5728\u7528\u6237\uff0c\u8bf7\u5148\u8fc1\u79fb\u7528\u6237");
        }
        g.setIsDeleted(Integer.valueOf(1));
        this.mentorGroupRepository.save(g);
    }

    private CoreMentorGroup requireGroup(String nanoId) {
        CoreMentorGroup g = this.mentorGroupRepository.findByNanoId(nanoId);
        if (g == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        return g;
    }

    private int computeLevel(Long parentId) {
        int level = 1;
        if (parentId != null) {
            CoreMentorGroup p = (CoreMentorGroup)this.mentorGroupRepository.findById(parentId).orElseThrow(() -> new BizException(ResultCode.BAD_REQUEST, "\u7236\u8282\u70b9\u4e0d\u5b58\u5728"));
            int n = level = p.getLevel() == null ? 2 : p.getLevel() + 1;
        }
        if (level > 3) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5bfc\u5e08\u7ec4\u4ec5\u652f\u6301 3 \u5c42\u6811\uff08\u5bfc\u5e08\u7ec4\u4e3a\u7b2c 3 \u5c42\u53f6\u5b50\uff0c\u4e0d\u80fd\u518d\u6dfb\u52a0\u4e0b\u7ea7\uff09");
        }
        return level;
    }

    private Long resolveParentId(String parentNanoId) {
        if (parentNanoId == null || parentNanoId.isBlank()) {
            return null;
        }
        CoreMentorGroup p = this.mentorGroupRepository.findByNanoId(parentNanoId);
        if (p == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7236\u8282\u70b9\u4e0d\u5b58\u5728: " + parentNanoId);
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
            CoreMentorGroup g = this.mentorGroupRepository.findById(cur).orElse(null);
            if (g == null) {
                return false;
            }
            cur = g.getParentId();
        }
        return false;
    }

    private void applyFields(CoreMentorGroup g, MentorGroupCreateRequest req) {
        g.setCode(req.getCode());
        g.setPhone(req.getPhone());
        g.setEmail(req.getEmail());
        g.setStatus(req.getStatus());
        g.setDescription(req.getDescription());
        g.setSort(req.getSort());
    }

    private List<MentorGroupVo> buildTree(List<CoreMentorGroup> groups) {
        LinkedHashMap<Long, MentorGroupVo> nodeMap = new LinkedHashMap<Long, MentorGroupVo>();
        for (CoreMentorGroup coreMentorGroup : groups) {
            nodeMap.put(coreMentorGroup.getId(), this.toVo(coreMentorGroup));
        }
        ArrayList<MentorGroupVo> roots = new ArrayList<MentorGroupVo>();
        for (CoreMentorGroup g : groups) {
            MentorGroupVo node = (MentorGroupVo)nodeMap.get(g.getId());
            Long pid = g.getParentId();
            if (pid == null || !nodeMap.containsKey(pid)) {
                roots.add(node);
                continue;
            }
            MentorGroupVo parent = (MentorGroupVo)nodeMap.get(pid);
            node.setParentNanoId(parent.getNanoId());
            parent.getChildren().add(node);
        }
        Comparator<MentorGroupVo> comparator = Comparator.comparingInt(n -> n.getSort() == null ? 0 : n.getSort());
        this.sortTree(roots, comparator);
        return roots;
    }

    private void sortTree(List<MentorGroupVo> nodes, Comparator<MentorGroupVo> cmp) {
        nodes.sort(cmp);
        for (MentorGroupVo n : nodes) {
            this.sortTree(n.getChildren(), cmp);
        }
    }

    private MentorGroupVo toVo(CoreMentorGroup g) {
        MentorGroupVo v = new MentorGroupVo();
        v.setNanoId(g.getNanoId());
        v.setName(g.getName());
        v.setCode(g.getCode());
        v.setPhone(g.getPhone());
        v.setEmail(g.getEmail());
        v.setStatus(g.getStatus());
        v.setDescription(g.getDescription());
        v.setLevel(g.getLevel());
        v.setPath(g.getPath());
        v.setSort(g.getSort());
        if (g.getLeadId() != null) {
            this.userRepository.findById(g.getLeadId()).ifPresent(u -> v.setLeadNanoId(u.getNanoId()));
        }
        return v;
    }

    public MentorGroupService(CoreMentorGroupRepository mentorGroupRepository, CoreUserRepository userRepository) {
        this.mentorGroupRepository = mentorGroupRepository;
        this.userRepository = userRepository;
    }
}

