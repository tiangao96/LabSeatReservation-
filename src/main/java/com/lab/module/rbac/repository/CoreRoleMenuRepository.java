package com.lab.module.rbac.repository;

import com.lab.module.rbac.entity.CoreRoleMenu;
import com.lab.module.rbac.entity.id.CoreRoleMenuId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CoreRoleMenuRepository
extends JpaRepository<CoreRoleMenu, CoreRoleMenuId> {
    @Query(value="SELECT DISTINCT rm.menu_id FROM core_user_role ur JOIN core_role_menu rm ON rm.role_id = ur.role_id WHERE ur.user_id = :userId AND ur.is_deleted = 0", nativeQuery=true)
    public List<Long> findMenuIdsByUserId(@Param(value="userId") Long var1);

    @Query(value="SELECT rm.menuId FROM CoreRoleMenu rm WHERE rm.id.roleId = :roleId")
    public List<Long> findMenuIdsByRoleId(@Param(value="roleId") Long var1);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM CoreRoleMenu rm WHERE rm.id.roleId = :roleId")
    public int deleteByRoleId(@Param(value="roleId") Long var1);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM CoreRoleMenu rm WHERE rm.menuId = :menuId")
    public int deleteByMenuId(@Param(value="menuId") Long var1);
}

