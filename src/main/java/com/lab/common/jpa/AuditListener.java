package com.lab.common.jpa;

import com.lab.common.entity.AuditEntity;
import com.lab.common.entity.BaseEntity;
import com.lab.common.security.SecurityContextUtil;
import com.lab.common.util.NanoId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

public class AuditListener {
    @PrePersist
    void prePersist(Object o) {
        LocalDateTime now = LocalDateTime.now();
        Long uid = SecurityContextUtil.currentUserId();
        Long deptId = SecurityContextUtil.currentDeptId();
        if (o instanceof BaseEntity) {
            BaseEntity b = (BaseEntity)o;
            if (b.getNanoId() == null) {
                b.setNanoId(NanoId.next());
            }
            this.fillAudit(b, now, uid, deptId);
            if (b.getIsDeleted() == null) {
                b.setIsDeleted(Integer.valueOf(0));
            }
        } else if (o instanceof AuditEntity) {
            AuditEntity a = (AuditEntity)o;
            this.fillAudit(a, now, uid, deptId);
            if (a.getIsDeleted() == null) {
                a.setIsDeleted(Integer.valueOf(0));
            }
        }
    }

    @PreUpdate
    void preUpdate(Object o) {
        LocalDateTime now = LocalDateTime.now();
        Long uid = SecurityContextUtil.currentUserId();
        if (o instanceof BaseEntity) {
            BaseEntity b = (BaseEntity)o;
            b.setSysUpdateDatetime(now);
            b.setSysModifierId(uid);
        } else if (o instanceof AuditEntity) {
            AuditEntity a = (AuditEntity)o;
            a.setSysUpdateDatetime(now);
            a.setSysModifierId(uid);
        }
    }

    private void fillAudit(BaseEntity b, LocalDateTime now, Long uid, Long deptId) {
        b.setSysCreateDatetime(now);
        b.setSysUpdateDatetime(now);
        b.setSysCreatorId(uid);
        b.setSysModifierId(uid);
        b.setSysDeptId(deptId);
    }

    private void fillAudit(AuditEntity a, LocalDateTime now, Long uid, Long deptId) {
        a.setSysCreateDatetime(now);
        a.setSysUpdateDatetime(now);
        a.setSysCreatorId(uid);
        a.setSysModifierId(uid);
        a.setSysDeptId(deptId);
    }
}

