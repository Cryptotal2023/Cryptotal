package com.cryptotal.service.core.util;

import com.cryptotal.service.core.security.AuthUserDetail;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Utils {
    public static int getUserId() {
        AuthUserDetail authUserDetail = (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("[Utils] " + authUserDetail);

        return authUserDetail.getId();
    }

    public static LocalDateTime convertStringToLocalDateTime(String timestampString) {

        long timestamp = Long.parseLong(timestampString);
        Instant instant = Instant.ofEpochMilli(timestamp);

        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
