package com.lab.module.rbac.repository;

import com.lab.module.rbac.entity.CoreMentorGroup;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoreMentorGroupRepository
extends JpaRepository<CoreMentorGroup, Long> {
    public CoreMentorGroup findByNanoId(String var1);

    public boolean existsByParentId(Long var1);

    public List<CoreMentorGroup> findAllByNameIn(Collection<String> var1);
}

