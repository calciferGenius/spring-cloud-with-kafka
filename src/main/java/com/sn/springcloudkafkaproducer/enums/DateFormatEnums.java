package com.sn.springcloudkafkaproducer.enums;/**
 * Created by pc-001 on 7/12/18.
 */

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

/**
 * Created by Kier pc-001 on 7/12/18.
 *
 * If needed new format just add here in ENUMS
 */

public enum DateFormatEnums {



    STANDARD_FORMAT(100, "Standard format", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")),
    STANDARD_FORMAT_WITH_SPACE(100, "Standard format", DateTimeFormat.forPattern("yyyy-MM-dd%20HH:mm:ss")),
    STANDARD_FORMAT_WO_SECS(101, "Standard format with no seconds", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm")),
    STANDARD_FORMAT_W_MILLIS(102, "Standard format with Milliseconds", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS")),
    STANDARD_FORMAT_DATE_ONLY(103, "Standard format with only date", DateTimeFormat.forPattern("yyy-MM-dd")),
    STANDARD_FORMAT_DATE_REPLACE_COLON_WITH_DOT(104, "Standard format use dot instead of colon", DateTimeFormat.forPattern("yyyy-MM-dd HH.mm.ss")),
    STANDARD_FORMAT_UTC(105, "Standard format using UTC +00:00 timezone", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss+00:00").withZoneUTC()),
    STANDARD_FORMAT_UTC_ONLY(106, "Standard format using UTC timezone", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss").withZoneUTC()),
    STANDARD_FORMAT_T(107, "Standard format with 'T'", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss")),

    yyyy_MM_dd_HH_mm_ss_Z(200, "Standard format with Z", DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss Z")),

    yyyyMMddHHmmss(300, "Standard format with no separation", DateTimeFormat.forPattern("yyyyMMddHHmmss")),
    yyyyMMdd(301, "Year month and date format with no separation", DateTimeFormat.forPattern("yyyyMMdd")),
    yyyyMMddHHmm(301, "Year month and date format with no separation", DateTimeFormat.forPattern("yyyyMMddHHmm")),
    yyMMdd(302, "Last 2 digit of year month and date format with no separation", DateTimeFormat.forPattern("yyMMdd")),
    yyyyMMddHH(303, "Year month date and hour format with no separation", DateTimeFormat.forPattern("yyyyMMddHH")),
    yyyyMMddHHmmssSSS(304, "Standard format with no separation", DateTimeFormat.forPattern("yyyyMMddHHmmssSSS")),
    HHmmss(400, "Standard time format", DateTimeFormat.forPattern("HH:mm:ss")),
    HH(401, "Hour only", DateTimeFormat.forPattern("HH")),

    MM_DD_YYYY_AMPM(500, "Date format with slash, AM/PM and English locale", DateTimeFormat.forPattern("MM/dd/yyyy hh:mm:ss a").withLocale(Locale.ENGLISH)),
    MM_DD_YYYY_HH_mm_ss(501, "Standard format using slash separation", DateTimeFormat.forPattern("MM/dd/yyyy hh:mm:ss")),

    ;

    int code;
    String name;
    DateTimeFormatter formatter;

    DateFormatEnums(int code, String name, DateTimeFormatter formatter){
        this.code = code;
        this.name = name;
        this.formatter = formatter;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
