package com.lab.module.workstation.repository;

import com.lab.module.workstation.entity.RentalRequestStatus;
import com.lab.module.workstation.entity.WorkstationRentalRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkstationRentalRequestRepository
extends JpaRepository<WorkstationRentalRequest, Long> {
    public WorkstationRentalRequest findByNanoId(String var1);

    @Query(value="SELECT r FROM WorkstationRentalRequest r WHERE (:status IS NULL OR r.status = :status) AND (:mentorGroupId IS NULL OR r.requesterMentorGroupId = :mentorGroupId) AND (:labId IS NULL OR r.labId = :labId) ORDER BY r.sysCreateDatetime DESC")
    public List<WorkstationRentalRequest> findWithFilters(@Param(value="status") RentalRequestStatus var1, @Param(value="mentorGroupId") Long var2, @Param(value="labId") Long var3);
}

