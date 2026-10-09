package com.lab.module.rbac.repository;

import com.lab.module.rbac.entity.CoreDept;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoreDeptRepository
extends JpaRepository<CoreDept, Long> {
    public CoreDept findByNanoId(String var1);

    public boolean existsByParentId(Long var1);

    public List<CoreDept> findAllByNameIn(Collection<String> var1);
}

