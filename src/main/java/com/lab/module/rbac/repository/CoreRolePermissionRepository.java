package com.lab.module.rbac.repository;

import com.lab.module.rbac.entity.CoreRolePermission;
import com.lab.module.rbac.entity.id.CoreRolePermissionId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CoreRolePermissionRepository
extends JpaRepository<CoreRolePermission, CoreRolePermissionId> {
    @Query(value="SELECT rp.permissionId FROM CoreRolePermission rp WHERE rp.id.roleId = :roleId")
    public List<Long> findPermissionIdsByRoleId(@Param(value="roleId") Long var1);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM CoreRolePermission rp WHERE rp.id.roleId = :roleId")
    public int deleteByRoleId(@Param(value="roleId") Long var1);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM CoreRolePermission rp WHERE rp.permissionId = :permissionId")
    public int deleteByPermissionId(@Param(value="permissionId") Long var1);
}

