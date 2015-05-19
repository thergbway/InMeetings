package com.inmeetings.business.utils;

import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Stateless
public class TimeUtils {
    public LocalDateTime convertToSystemDefaultDateTime(Timestamp timestamp) {
        return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
    }
}
