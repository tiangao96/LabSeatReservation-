package com.lab.module.workstation.repository;

import com.lab.module.workstation.entity.UsageManualRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageManualRecordRepository extends JpaRepository<UsageManualRecord, Long> {
    UsageManualRecord findByNanoId(String nanoId);

    List<UsageManualRecord> findAllByIsDeleted(Integer isDeleted);
}
