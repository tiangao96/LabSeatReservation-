package com.lab.module.workstation.service;

import com.lab.module.rbac.entity.CoreUser;
import com.lab.module.rbac.repository.CoreUserRepository;
import com.lab.module.workstation.entity.Workstation;
import com.lab.module.workstation.entity.WorkstationStatus;
import com.lab.module.workstation.repository.WorkstationRepository;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class StudentGraduationReleaseJob {
    private static final Logger log = LoggerFactory.getLogger(StudentGraduationReleaseJob.class);
    private static final Pattern GRADE_SUFFIX = Pattern.compile("(\\d{2})$");

    private final JdbcTemplate jdbcTemplate;
    private final WorkstationRepository workstationRepository;
    private final CoreUserRepository userRepository;

    @Scheduled(cron = "${app.workstation.graduation-cron-june:0 5 0 30 6 *}")
    public void scheduledScanJune() {
        runOnce();
    }

    @Scheduled(cron = "${app.workstation.graduation-cron-feb:0 5 0 28 2 *}")
    public void scheduledScanFeb() {
        runOnce();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        runOnce();
    }

    @Transactional
    public void runOnce() {
        YearMonth now = YearMonth.now();
        String sql = "SELECT h.workstation_id, h.occupant_user_id FROM workstation_occupancy_history h "
                + "WHERE h.released_at IS NULL AND h.occupant_user_id IS NOT NULL AND h.is_deleted = 0";
        List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(sql);
        if (rows.isEmpty()) {
            log.info("毕业工位扫描({}): 无开放占用段", now);
            return;
        }
        int released = 0;
        int skipped = 0;
        int failed = 0;
        for (Map<String, Object> row : rows) {
            Number wsIdNum = (Number) row.get("workstation_id");
            Number userIdNum = (Number) row.get("occupant_user_id");
            if (wsIdNum == null || userIdNum == null) {
                skipped++;
                continue;
            }
            Long wsId = wsIdNum.longValue();
            Long userId = userIdNum.longValue();
            try {
                CoreUser u = this.userRepository.findById(userId).orElse(null);
                YearMonth grad = graduationMonth(u);
                if (grad == null || now.isBefore(grad)) {
                    skipped++;
                    continue;
                }
                Workstation ws = this.workstationRepository.findById(wsId).orElse(null);
                if (ws == null || (ws.getStatus() != WorkstationStatus.OCCUPIED && ws.getStatus() != WorkstationStatus.PRE_RESERVED)) {
                    skipped++;
                    continue;
                }
                releaseDirect(ws, wsId);
                released++;
                log.info("毕业释放: {} (类型{}, 毕业{}) -> 工位 {}", u != null ? u.getName() : userId, u != null ? u.getUserType() : "?", grad, ws.getCode());
            } catch (Exception e) {
                failed++;
                log.error("毕业释放失败 workstationId={} userId={}", wsId, userId, e);
            }
        }
        log.info("毕业工位扫描完成({}): 命中 {}，释放 {}，跳过 {}，失败 {}", now, rows.size(), released, skipped, failed);
    }

    /** 直接释放: 关闭占用段 + 工位回 IDLE + 清占用学生 (保留导师组, 空位仍属该导师) */
    private void releaseDirect(Workstation ws, Long wsId) {
        LocalDateTime releasedAt = LocalDateTime.now();
        String sql = "UPDATE workstation_occupancy_history SET released_at = ? WHERE workstation_id = ? AND released_at IS NULL AND is_deleted = 0";
        this.jdbcTemplate.update(sql, releasedAt, wsId);
        ws.setStatus(WorkstationStatus.IDLE);
        ws.setCurrentUserId(null);
        ws.setOccupiedAt(null);
        this.workstationRepository.save(ws);
    }

    static YearMonth graduationMonth(CoreUser u) {
        if (u == null || u.getUserType() == null) {
            return null;
        }
        int type = u.getUserType().intValue();
        int years;
        if (type == 1) {
            years = 4;      // 本科
        } else if (type == 2) {
            years = 3;      // 硕士
        } else {
            return null;    // 博士等不自动推算
        }
        Integer grade = parseGrade(u.getUsername());
        if (grade == null) {
            return null;
        }
        int enroll = 2000 + grade;
        return YearMonth.of(enroll + years, 6);
    }

    static Integer parseGrade(String username) {
        if (username == null || username.isBlank()) {
            return null;
        }
        Matcher m = GRADE_SUFFIX.matcher(username);
        if (!m.find()) {
            return null;
        }
        try {
            int g = Integer.parseInt(m.group(1));
            if (g >= 10 && g <= 99) {
                return Integer.valueOf(g);
            }
        } catch (NumberFormatException ignored) {
        }
        return null;
    }

    public StudentGraduationReleaseJob(DataSource dataSource,
                                       WorkstationRepository workstationRepository,
                                       CoreUserRepository userRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.workstationRepository = workstationRepository;
        this.userRepository = userRepository;
    }
}
