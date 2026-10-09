package com.lab.module.workstation.repository;

import com.lab.module.workstation.entity.WorkstationRentalRequestItem;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkstationRentalRequestItemRepository
extends JpaRepository<WorkstationRentalRequestItem, Long> {
    public List<WorkstationRentalRequestItem> findByRentalRequestId(Long var1);

    @Query(value="SELECT COUNT(*) FROM workstation_rental_request_item ri INNER JOIN workstation_rental_request r ON ri.rental_request_id = r.id WHERE ri.workstation_id IN (:workstationIds) AND r.status = 'APPROVED' AND r.is_deleted = 0 AND ri.is_deleted = 0 AND r.start_month <= :endMonth AND COALESCE(ri.end_month, r.end_month) >= :startMonth", nativeQuery=true)
    public long countApprovedConflicts(@Param(value="workstationIds") Collection<Long> var1, @Param(value="startMonth") String var2, @Param(value="endMonth") String var3);

    @Query(value="SELECT COUNT(*) FROM workstation_rental_request_item ri INNER JOIN workstation_rental_request r ON ri.rental_request_id = r.id WHERE ri.rental_request_id <> :excludeRequestId AND ri.workstation_id IN (:workstationIds) AND r.status = 'APPROVED' AND r.is_deleted = 0 AND ri.is_deleted = 0 AND r.start_month <= :endMonth AND COALESCE(ri.end_month, r.end_month) >= :startMonth", nativeQuery=true)
    public long countApprovedConflictsExcluding(@Param(value="excludeRequestId") Long var1, @Param(value="workstationIds") Collection<Long> var2, @Param(value="startMonth") String var3, @Param(value="endMonth") String var4);

    @Query(value="SELECT COUNT(*) FROM workstation_rental_request_item ri INNER JOIN workstation_rental_request r ON ri.rental_request_id = r.id WHERE ri.workstation_id = :workstationId AND r.status = 'APPROVED' AND r.is_deleted = 0 AND ri.is_deleted = 0 AND r.start_month <= :month AND COALESCE(ri.end_month, r.end_month) >= :month", nativeQuery=true)
    public long countApprovedByWorkstationAndMonth(@Param(value="workstationId") Long var1, @Param(value="month") String var2);

    @Query(value="SELECT DISTINCT ri.workstation_id FROM workstation_rental_request_item ri INNER JOIN workstation_rental_request r ON ri.rental_request_id = r.id WHERE ri.workstation_id IN (:workstationIds) AND r.status = 'APPROVED' AND r.is_deleted = 0 AND ri.is_deleted = 0 AND r.start_month <= :month AND COALESCE(ri.end_month, r.end_month) >= :month", nativeQuery=true)
    public List<Long> findApprovedReservedWorkstationIds(@Param(value="workstationIds") Collection<Long> var1, @Param(value="month") String var2);

    @Query(value="SELECT ri.workstation_id, r.requester_mentor_group_id, r.start_month, COALESCE(ri.end_month, r.end_month) FROM workstation_rental_request_item ri INNER JOIN workstation_rental_request r ON ri.rental_request_id = r.id WHERE ri.workstation_id IN (:workstationIds) AND r.status = 'APPROVED' AND r.is_deleted = 0 AND ri.is_deleted = 0 AND r.start_month <= :month AND COALESCE(ri.end_month, r.end_month) >= :month", nativeQuery=true)
    public List<Object[]> findApprovedRentalInfoByWorkstationIds(@Param(value="workstationIds") Collection<Long> var1, @Param(value="month") String var2);

    @Query(value="SELECT r.id FROM workstation_rental_request_item ri INNER JOIN workstation_rental_request r ON ri.rental_request_id = r.id WHERE ri.workstation_id = :workstationId AND r.status = 'APPROVED' AND r.is_deleted = 0 AND ri.is_deleted = 0 AND r.start_month <= :month AND COALESCE(ri.end_month, r.end_month) >= :month LIMIT 1", nativeQuery=true)
    public Long findApprovedRentalRequestIdByWorkstationAndMonth(@Param(value="workstationId") Long var1, @Param(value="month") String var2);

    @Query(value="SELECT DISTINCT ri.workstation_id FROM workstation_rental_request_item ri INNER JOIN workstation_rental_request r ON ri.rental_request_id = r.id WHERE ri.workstation_id IN (:workstationIds) AND r.status = 'APPROVED' AND r.is_deleted = 0 AND ri.is_deleted = 0 AND r.start_month <= :endMonth AND COALESCE(ri.end_month, r.end_month) >= :startMonth", nativeQuery=true)
    public List<Long> findConflictingWorkstationIds(@Param(value="workstationIds") Collection<Long> var1, @Param(value="startMonth") String var2, @Param(value="endMonth") String var3);

    @Query(value="SELECT ri.rental_request_id, r.start_month, r.requester_mentor_group_id FROM workstation_rental_request_item ri INNER JOIN workstation_rental_request r ON ri.rental_request_id = r.id WHERE ri.workstation_id = :workstationId AND r.status = 'APPROVED' AND r.is_deleted = 0 AND ri.is_deleted = 0 AND COALESCE(ri.end_month, r.end_month) >= :month ORDER BY r.start_month ASC LIMIT 1", nativeQuery=true)
    public List<Object[]> findActiveApprovedRentalByWorkstation(@Param(value="workstationId") Long var1, @Param(value="month") String var2);

    @Query(value="SELECT ri.rental_request_id, r.start_month, r.requester_mentor_group_id FROM workstation_rental_request_item ri INNER JOIN workstation_rental_request r ON ri.rental_request_id = r.id WHERE ri.workstation_id = :workstationId AND ri.rental_request_id <> :excludeRequestId AND r.status = 'APPROVED' AND r.is_deleted = 0 AND ri.is_deleted = 0 AND COALESCE(ri.end_month, r.end_month) >= :month ORDER BY r.start_month ASC LIMIT 1", nativeQuery=true)
    public List<Object[]> findNextApprovedRentalByWorkstation(@Param(value="workstationId") Long var1, @Param(value="month") String var2, @Param(value="excludeRequestId") Long var3);

    /**
     * 按工位 ID 集合查询所有关联的租赁申请明细。
     */
    public List<WorkstationRentalRequestItem> findByWorkstationIdIn(Collection<Long> workstationIds);
}

