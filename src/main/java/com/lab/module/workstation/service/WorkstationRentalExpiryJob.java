package com.lab.module.workstation.service;

import com.lab.module.workstation.repository.WorkstationOccupancyHistoryRepository;
import com.lab.module.workstation.service.WorkstationService;
import java.time.YearMonth;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WorkstationRentalExpiryJob {
    private static final Logger log = LoggerFactory.getLogger(WorkstationRentalExpiryJob.class);
    private final WorkstationOccupancyHistoryRepository historyRepository;
    private final WorkstationService workstationService;

    @Scheduled(cron="${app.workstation.expiry-cron:0 5 0 * * *}")
    public void autoReleaseExpiredRentals() {
        this.runOnce();
    }

    @EventListener(value={ApplicationReadyEvent.class})
    public void onStartup() {
        this.runOnce();
    }

    void runOnce() {
        String currentMonth = YearMonth.now().toString();
        List<Object[]> expired = this.historyRepository.findExpiredOpenRentalSegments(currentMonth);
        if (expired.isEmpty()) {
            log.info("\u5de5\u4f4d\u6708\u79df\u5230\u671f\u626b\u63cf\uff08{}\uff09\uff1a\u65e0\u903e\u671f\u5360\u7528", (Object)currentMonth);
            return;
        }
        int released = 0;
        int skipped = 0;
        int failed = 0;
        for (Object[] row : expired) {
            Long wsId = ((Number)row[0]).longValue();
            Long rentalId = ((Number)row[1]).longValue();
            try {
                if (this.workstationService.autoReleaseExpiredRental(wsId, rentalId)) {
                    ++released;
                    continue;
                }
                ++skipped;
            }
            catch (Exception e) {
                ++failed;
                log.error("\u5de5\u4f4d\u5230\u671f\u81ea\u52a8\u91ca\u653e\u5931\u8d25 wsId={} rentalRequestId={}", new Object[]{wsId, rentalId, e});
            }
        }
        log.info("\u5de5\u4f4d\u6708\u79df\u5230\u671f\u626b\u63cf\u5b8c\u6210\uff08{}\uff09\uff1a\u547d\u4e2d {}\uff0c\u91ca\u653e {}\uff0c\u8df3\u8fc7 {}\uff0c\u5931\u8d25 {}", new Object[]{currentMonth, expired.size(), released, skipped, failed});
    }

    public WorkstationRentalExpiryJob(WorkstationOccupancyHistoryRepository historyRepository, WorkstationService workstationService) {
        this.historyRepository = historyRepository;
        this.workstationService = workstationService;
    }
}

