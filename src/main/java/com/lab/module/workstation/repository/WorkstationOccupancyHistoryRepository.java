package com.lab.module.workstation.repository;

import com.lab.module.workstation.entity.WorkstationOccupancyHistory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkstationOccupancyHistoryRepository
extends JpaRepository<WorkstationOccupancyHistory, Long> {
    public Optional<WorkstationOccupancyHistory> findFirstByWorkstationIdAndReleasedAtIsNullOrderByOccupiedAtDesc(Long var1);

    public List<WorkstationOccupancyHistory> findByWorkstationIdOrderByOccupiedAtDesc(Long var1);

    @Query(value="SELECT COUNT(h) FROM WorkstationOccupancyHistory h WHERE h.releasedAt IS NULL")
    public long countOpenSegments();

    public List<WorkstationOccupancyHistory> findAllByReleasedAtIsNullAndOccupantUserIdIsNotNull();

    @Query(value="SELECT * FROM workstation_occupancy_history h WHERE h.occupant_mentor_group_id = :mentorGroupId AND h.occupancy_type = 'OCCUPIED' AND h.occupant_user_id IS NOT NULL AND h.is_deleted = 0 AND DATE_FORMAT(h.occupied_at, '%Y-%m') <= :endMonth AND (h.released_at IS NULL OR DATE_FORMAT(h.released_at, '%Y-%m') >= :startMonth) ORDER BY h.occupant_user_id, h.occupied_at", nativeQuery=true)
    public List<WorkstationOccupancyHistory> findOccupiedByMentorGroupAndMonthRange(@Param(value="mentorGroupId") Long var1, @Param(value="startMonth") String var2, @Param(value="endMonth") String var3);

    @Query(value="SELECT DISTINCT h.workstation_id, h.source_id FROM workstation_occupancy_history h INNER JOIN workstation_rental_request r ON h.source_id = r.id WHERE h.released_at IS NULL AND h.source_type = 'RENTAL' AND h.is_deleted = 0 AND r.is_deleted = 0 AND r.status = 'APPROVED' AND r.end_month < :month", nativeQuery=true)
    public List<Object[]> findExpiredOpenRentalSegments(@Param(value="month") String var1);
}

