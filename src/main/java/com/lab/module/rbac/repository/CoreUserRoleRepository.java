package com.lab.module.rbac.repository;

import com.lab.module.rbac.entity.CoreUserRole;
import com.lab.module.rbac.entity.id.CoreUserRoleId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CoreUserRoleRepository
extends JpaRepository<CoreUserRole, CoreUserRoleId> {
    @Query(value="SELECT ur.roleId FROM CoreUserRole ur WHERE ur.id.userId = :userId")
    public List<Long> findRoleIdsByUserId(@Param(value="userId") Long var1);

    public boolean existsByUserId(Long var1);

    public boolean existsByRoleId(Long var1);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM CoreUserRole ur WHERE ur.userId = :userId")
    public int deleteByUserId(@Param(value="userId") Long var1);
}

