package com.lab.module.rbac.repository;

import com.lab.module.rbac.entity.CoreRole;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoreRoleRepository
extends JpaRepository<CoreRole, Long> {
    public CoreRole findByNanoId(String var1);

    public CoreRole findByCode(String var1);

    public List<CoreRole> findAllByNanoIdIn(Collection<String> var1);

    public List<CoreRole> findAllByCodeIn(Collection<String> var1);

    @Query(value="SELECT r FROM CoreRole r WHERE (:kw IS NULL OR :kw = '' OR LOWER(r.name) LIKE LOWER(CONCAT('%', :kw, '%')) OR LOWER(r.code) LIKE LOWER(CONCAT('%', :kw, '%')))")
    public List<CoreRole> searchByKeyword(@Param(value="kw") String var1, Sort var2);

    @Query(value="SELECT r.code FROM core_role r JOIN core_user_role ur ON ur.role_id = r.id WHERE ur.user_id = :userId AND ur.is_deleted = 0 AND r.is_deleted = 0", nativeQuery=true)
    public List<String> findRoleCodesByUserId(@Param(value="userId") Long var1);

    @Query(value="SELECT r FROM CoreRole r WHERE r.id IN (SELECT ur.roleId FROM CoreUserRole ur WHERE ur.id.userId = :userId)")
    public List<CoreRole> findRolesByUserId(@Param(value="userId") Long var1);
}

