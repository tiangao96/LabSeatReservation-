package com.lab.module.workstation.repository;

import com.lab.module.workstation.entity.Equipment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository
extends JpaRepository<Equipment, Long> {
    public Equipment findByNanoId(String var1);

    public List<Equipment> findByLabId(Long var1);

    public boolean existsByLabId(Long var1);
}

