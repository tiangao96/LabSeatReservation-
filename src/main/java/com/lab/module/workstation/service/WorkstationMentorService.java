package com.lab.module.workstation.service;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import com.lab.common.security.MentorGroupResolver;
import com.lab.module.rbac.entity.CoreMentorGroup;
import com.lab.module.rbac.entity.CoreUser;
import com.lab.module.rbac.repository.CoreMentorGroupRepository;
import com.lab.module.rbac.repository.CoreUserRepository;
import com.lab.module.workstation.entity.Workstation;
import com.lab.module.workstation.entity.WorkstationStatus;
import com.lab.module.workstation.repository.WorkstationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class WorkstationMentorService {
    private final WorkstationRepository workstationRepository;
    private final MentorGroupResolver mentorGroupResolver;
    private final CoreUserRepository coreUserRepository;
    private final CoreMentorGroupRepository mentorGroupRepository;

    public WorkstationMentorService(WorkstationRepository workstationRepository, MentorGroupResolver mentorGroupResolver, CoreUserRepository coreUserRepository, CoreMentorGroupRepository mentorGroupRepository) {
        this.workstationRepository = workstationRepository;
        this.mentorGroupResolver = mentorGroupResolver;
        this.coreUserRepository = coreUserRepository;
        this.mentorGroupRepository = mentorGroupRepository;
    }

    @Transactional
    public void setMentorGroup(String nanoId, String mentorGroupNanoId, String mentorGroupName) {
        Workstation ws = this.workstationRepository.findByNanoId(nanoId);
        if (ws == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        boolean hasId = mentorGroupNanoId != null && !mentorGroupNanoId.isBlank();
        boolean hasName = mentorGroupName != null && !mentorGroupName.isBlank();
        if (!hasId && !hasName) {
            ws.setOccupantMentorGroupId(null);
            this.workstationRepository.save(ws);
            this.workstationRepository.updateMentorGroupName(ws.getId(), null);
        } else if (hasId) {
            Long groupId = this.mentorGroupResolver.resolveMentorGroupId(mentorGroupNanoId);
            if (groupId == null) {
                throw new BizException(ResultCode.BAD_REQUEST, "\u5bfc\u5e08\u7ec4\u4e0d\u5b58\u5728");
            }
            ws.setOccupantMentorGroupId(groupId);
            this.workstationRepository.save(ws);
            this.workstationRepository.updateMentorGroupName(ws.getId(), null);
        } else {
            String name = mentorGroupName.trim();
            List<CoreMentorGroup> groups = this.mentorGroupRepository.findAllByNameIn(Collections.singletonList(name));
            if (!groups.isEmpty()) {
                ws.setOccupantMentorGroupId(groups.get(0).getId());
                this.workstationRepository.save(ws);
                this.workstationRepository.updateMentorGroupName(ws.getId(), null);
            } else {
                ws.setOccupantMentorGroupId(null);
                this.workstationRepository.save(ws);
                this.workstationRepository.updateMentorGroupName(ws.getId(), name);
            }
        }
    }

    @Transactional
    public void setOccupantName(String nanoId, String name) {
        Workstation ws = this.workstationRepository.findByNanoId(nanoId);
        if (ws == null) {
            throw new BizException(ResultCode.RESOURCE_NOT_FOUND);
        }
        if (ws.getCurrentUserId() == null) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u8be5\u5de5\u4f4d\u5f53\u524d\u6ca1\u6709\u5360\u7528\u5b66\u751f");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5b66\u751f\u59d3\u540d\u4e0d\u80fd\u4e3a\u7a7a");
        }
        CoreUser user = this.coreUserRepository.findById(ws.getCurrentUserId()).orElseThrow(() -> new BizException(ResultCode.RESOURCE_NOT_FOUND, "\u5360\u7528\u5b66\u751f\u4e0d\u5b58\u5728"));
        user.setName(name.trim());
        this.coreUserRepository.save(user);
    }
}
