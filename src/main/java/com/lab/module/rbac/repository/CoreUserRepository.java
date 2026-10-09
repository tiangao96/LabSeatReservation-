package com.lab.module.rbac.repository;

import com.lab.module.rbac.entity.CoreUser;
import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoreUserRepository
extends JpaRepository<CoreUser, Long> {
    public java.util.List<CoreUser> findByName(String name);

    public CoreUser findByUsername(String var1);

    public List<CoreUser> findByUsernameIn(Collection<String> var1);

    public CoreUser findByNanoId(String var1);

    public Page<CoreUser> findByUsernameContainingIgnoreCaseOrNameContainingIgnoreCase(String var1, String var2, Pageable var3);

    @Query(value="SELECT u FROM CoreUser u WHERE (:deptId IS NULL OR u.deptId = :deptId) AND (:kw IS NULL OR :kw = '' OR LOWER(u.username) LIKE LOWER(CONCAT('%', :kw, '%')) OR LOWER(u.name) LIKE LOWER(CONCAT('%', :kw, '%')))")
    public Page<CoreUser> searchByDeptAndKeyword(@Param(value="deptId") Long var1, @Param(value="kw") String var2, Pageable var3);

    public boolean existsByDeptId(Long var1);

    public List<CoreUser> findByDeptId(Long var1);

    @Query(value="SELECT u FROM CoreUser u WHERE (:mentorGroupId IS NULL OR u.mentorGroupId = :mentorGroupId) AND (:kw IS NULL OR :kw = '' OR LOWER(u.username) LIKE LOWER(CONCAT('%', :kw, '%')) OR LOWER(u.name) LIKE LOWER(CONCAT('%', :kw, '%')))")
    public Page<CoreUser> searchByMentorGroupAndKeyword(@Param(value="mentorGroupId") Long var1, @Param(value="kw") String var2, Pageable var3);

    public boolean existsByMentorGroupId(Long var1);

    public List<CoreUser> findByMentorGroupId(Long var1);
}

