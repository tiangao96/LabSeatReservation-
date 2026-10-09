package com.lab.module.rbac.repository;

import com.lab.module.rbac.entity.CorePermission;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CorePermissionRepository
extends JpaRepository<CorePermission, Long> {
    public List<CorePermission> findAllByNanoIdIn(Collection<String> var1);

    public CorePermission findByNanoId(String var1);

    public CorePermission findByMenuIdAndCode(Long var1, String var2);

    @Query(value="SELECT DISTINCT p.code FROM core_user_role ur JOIN core_role_permission rp ON rp.role_id = ur.role_id JOIN core_permission p ON p.id = rp.permission_id WHERE ur.user_id = :userId AND ur.is_deleted = 0   AND p.is_deleted = 0 AND p.is_active = 1", nativeQuery=true)
    public List<String> findPermissionCodesByUserId(@Param(value="userId") Long var1);
}

