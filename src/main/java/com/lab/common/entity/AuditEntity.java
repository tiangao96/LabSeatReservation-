package com.lab.common.entity;

import com.lab.common.jpa.AuditListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value={AuditListener.class})
public abstract class AuditEntity {
    private Integer sort;
    @Column(name="is_deleted")
    private Integer isDeleted = 0;
    @Column(name="sys_create_datetime")
    private LocalDateTime sysCreateDatetime;
    @Column(name="sys_update_datetime")
    private LocalDateTime sysUpdateDatetime;
    @Column(name="sys_creator_id")
    private Long sysCreatorId;
    @Column(name="sys_modifier_id")
    private Long sysModifierId;
    @Column(name="sys_dept_id")
    private Long sysDeptId;

    public AuditEntity() {
    }

    public Integer getSort() {
        return this.sort;
    }

    public Integer getIsDeleted() {
        return this.isDeleted;
    }

    public LocalDateTime getSysCreateDatetime() {
        return this.sysCreateDatetime;
    }

    public LocalDateTime getSysUpdateDatetime() {
        return this.sysUpdateDatetime;
    }

    public Long getSysCreatorId() {
        return this.sysCreatorId;
    }

    public Long getSysModifierId() {
        return this.sysModifierId;
    }

    public Long getSysDeptId() {
        return this.sysDeptId;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setSysCreateDatetime(LocalDateTime sysCreateDatetime) {
        this.sysCreateDatetime = sysCreateDatetime;
    }

    public void setSysUpdateDatetime(LocalDateTime sysUpdateDatetime) {
        this.sysUpdateDatetime = sysUpdateDatetime;
    }

    public void setSysCreatorId(Long sysCreatorId) {
        this.sysCreatorId = sysCreatorId;
    }

    public void setSysModifierId(Long sysModifierId) {
        this.sysModifierId = sysModifierId;
    }

    public void setSysDeptId(Long sysDeptId) {
        this.sysDeptId = sysDeptId;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AuditEntity)) {
            return false;
        }
        AuditEntity other = (AuditEntity)o;
        if (!other.canEqual((Object)this)) {
            return false;
        }
        Integer this$sort = this.getSort();
        Integer other$sort = other.getSort();
        if (this$sort == null ? other$sort != null : !((Object)this$sort).equals(other$sort)) {
            return false;
        }
        Integer this$isDeleted = this.getIsDeleted();
        Integer other$isDeleted = other.getIsDeleted();
        if (this$isDeleted == null ? other$isDeleted != null : !((Object)this$isDeleted).equals(other$isDeleted)) {
            return false;
        }
        Long this$sysCreatorId = this.getSysCreatorId();
        Long other$sysCreatorId = other.getSysCreatorId();
        if (this$sysCreatorId == null ? other$sysCreatorId != null : !((Object)this$sysCreatorId).equals(other$sysCreatorId)) {
            return false;
        }
        Long this$sysModifierId = this.getSysModifierId();
        Long other$sysModifierId = other.getSysModifierId();
        if (this$sysModifierId == null ? other$sysModifierId != null : !((Object)this$sysModifierId).equals(other$sysModifierId)) {
            return false;
        }
        Long this$sysDeptId = this.getSysDeptId();
        Long other$sysDeptId = other.getSysDeptId();
        if (this$sysDeptId == null ? other$sysDeptId != null : !((Object)this$sysDeptId).equals(other$sysDeptId)) {
            return false;
        }
        LocalDateTime this$sysCreateDatetime = this.getSysCreateDatetime();
        LocalDateTime other$sysCreateDatetime = other.getSysCreateDatetime();
        if (this$sysCreateDatetime == null ? other$sysCreateDatetime != null : !((Object)this$sysCreateDatetime).equals(other$sysCreateDatetime)) {
            return false;
        }
        LocalDateTime this$sysUpdateDatetime = this.getSysUpdateDatetime();
        LocalDateTime other$sysUpdateDatetime = other.getSysUpdateDatetime();
        return !(this$sysUpdateDatetime == null ? other$sysUpdateDatetime != null : !((Object)this$sysUpdateDatetime).equals(other$sysUpdateDatetime));
    }

    protected boolean canEqual(Object other) {
        return other instanceof AuditEntity;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        Integer $sort = this.getSort();
        result = result * 59 + ($sort == null ? 43 : ((Object)$sort).hashCode());
        Integer $isDeleted = this.getIsDeleted();
        result = result * 59 + ($isDeleted == null ? 43 : ((Object)$isDeleted).hashCode());
        Long $sysCreatorId = this.getSysCreatorId();
        result = result * 59 + ($sysCreatorId == null ? 43 : ((Object)$sysCreatorId).hashCode());
        Long $sysModifierId = this.getSysModifierId();
        result = result * 59 + ($sysModifierId == null ? 43 : ((Object)$sysModifierId).hashCode());
        Long $sysDeptId = this.getSysDeptId();
        result = result * 59 + ($sysDeptId == null ? 43 : ((Object)$sysDeptId).hashCode());
        LocalDateTime $sysCreateDatetime = this.getSysCreateDatetime();
        result = result * 59 + ($sysCreateDatetime == null ? 43 : ((Object)$sysCreateDatetime).hashCode());
        LocalDateTime $sysUpdateDatetime = this.getSysUpdateDatetime();
        result = result * 59 + ($sysUpdateDatetime == null ? 43 : ((Object)$sysUpdateDatetime).hashCode());
        return result;
    }

    public String toString() {
        return "AuditEntity(sort=" + this.getSort() + ", isDeleted=" + this.getIsDeleted() + ", sysCreateDatetime=" + String.valueOf(this.getSysCreateDatetime()) + ", sysUpdateDatetime=" + String.valueOf(this.getSysUpdateDatetime()) + ", sysCreatorId=" + this.getSysCreatorId() + ", sysModifierId=" + this.getSysModifierId() + ", sysDeptId=" + this.getSysDeptId() + ")";
    }
}

