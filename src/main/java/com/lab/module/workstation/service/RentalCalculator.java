package com.lab.module.workstation.service;

import com.lab.common.api.ResultCode;
import com.lab.common.exception.BizException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.regex.Pattern;

/*
 * Exception performing whole class analysis ignored.
 */
public final class RentalCalculator {
    private static final Pattern MONTH_PATTERN = Pattern.compile("^(\\d{4})-(0[1-9]|1[0-2])$");

    private RentalCalculator() {
    }

    public static int calcMonthCount(String startMonth, String endMonth, int wsCount) {
        int[] start = RentalCalculator.parseMonth((String)startMonth);
        int[] end = RentalCalculator.parseMonth((String)endMonth);
        int monthCount = (end[0] - start[0]) * 12 + (end[1] - start[1]) + 1;
        if (monthCount <= 0) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u7ed3\u675f\u6708\u4e0d\u80fd\u65e9\u4e8e\u8d77\u59cb\u6708");
        }
        if (wsCount <= 0) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5de5\u4f4d\u6570\u987b\u5927\u4e8e 0");
        }
        return monthCount;
    }

    public static int calcMonthSpan(LocalDateTime from, LocalDateTime to) {
        YearMonth s = YearMonth.from(from);
        YearMonth e = YearMonth.from(to);
        return (e.getYear() - s.getYear()) * 12 + (e.getMonthValue() - s.getMonthValue()) + 1;
    }

    public static BigDecimal workstationArea(BigDecimal labArea, Integer capacity) {
        if (labArea == null || labArea.signum() <= 0 || capacity == null || capacity <= 0) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u5b9e\u9a8c\u5ba4\u672a\u914d\u7f6e\u9762\u79ef\u6216\u5bb9\u91cf\uff0c\u65e0\u6cd5\u8ba1\u7b97\u5355\u4e2a\u5de5\u4f4d\u9762\u79ef");
        }
        return labArea.divide(BigDecimal.valueOf(capacity.intValue()), 2, RoundingMode.CEILING);
    }

    public static int[] parseMonth(String month) {
        if (month == null || !MONTH_PATTERN.matcher(month).matches()) {
            throw new BizException(ResultCode.BAD_REQUEST, "\u6708\u4efd\u683c\u5f0f\u975e\u6cd5\uff0c\u5e94\u4e3a YYYY-MM: " + month);
        }
        String[] parts = month.split("-");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
    }
}

