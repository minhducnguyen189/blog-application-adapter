package com.application.adapter.Utilities;

import com.application.adapter.Constants.Constant;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String getRecentDate() {
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern(Constant.DATE_TIME_FORMAT);
        ZoneId zoneId = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDateTime localDateTime = LocalDateTime.now(zoneId);
        return localDateTime.format(formatter);
    }
}
