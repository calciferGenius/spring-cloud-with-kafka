package com.sn.saba_source.utils;

import com.sn.saba_source.enums.DateFieldEnums;
import com.sn.saba_source.enums.DateFormatEnums;
import com.sn.saba_source.exception.JodaDateUtilsException;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.util.Date;

public class JodaDateUtils {

    public static Timestamp now() {
        return new Timestamp(LocalDateTime.now().toDateTime().getMillis());
    }

    public static Timestamp convertStringToTimestamp(String date){ return Timestamp.valueOf(date);}

    public static Timestamp convertStringToTimestamp(String date, DateFormatEnums dateFormatEnums) throws JodaDateUtilsException {
        if(dateFormatEnums == null)
            throw new JodaDateUtilsException("Unable to convert string to Timestamp. Date format is null");
        return new Timestamp(dateFormatEnums.getFormatter().parseDateTime(date).getMillis());
    }

    public static Timestamp convertDateToTimeStamp(Date date){ return new Timestamp(date.getTime());}

    public static String nowAsStandardFormat() {
        return LocalDateTime.fromDateFields(new Date(LocalDateTime.now().toDateTime().getMillis())).toString(DateFormatEnums.STANDARD_FORMAT.getFormatter());
    }

    public static String formatToStandard(Date date) {
        return LocalDateTime.fromDateFields(date).toString(DateFormatEnums.STANDARD_FORMAT.getFormatter());
    }

    public static String fmtyyyyMMddHHmmss(Date date) {
        return LocalDateTime.fromDateFields(date).toString(DateFormatEnums.yyyyMMddHHmmss.getFormatter());
    }

    public static Date parseDate(String dateText, DateFormatEnums dateFormatEnums) throws JodaDateUtilsException {
        if(dateFormatEnums == null)
            throw new JodaDateUtilsException("Unable to parse date(String). Date format is null");
        return dateFormatEnums.getFormatter().parseDateTime(dateText).toDate();
    }

    public static String parseDate(Date date, DateFormatEnums dateFormatEnums) throws JodaDateUtilsException {
        if(dateFormatEnums == null)
            throw new JodaDateUtilsException("Unable to parse date(Date). Date format is null");
        return LocalDateTime.fromDateFields(date).toString(dateFormatEnums.getFormatter());
    }

    public static String parseDate(Date date, DateTimeFormatter dateTimeFormatter) throws JodaDateUtilsException {
        if(dateTimeFormatter == null)
            throw new JodaDateUtilsException("Unable to parse date(Date). Date time formater is null");
        return LocalDateTime.fromDateFields(date).toString(dateTimeFormatter);
    }

    public static String parseDate(String dateText, DateFormatEnums fromFormat, DateFormatEnums toFormat) throws JodaDateUtilsException {
        if(fromFormat == null || toFormat == null)
            throw new JodaDateUtilsException("Unable to parse date(String). Date format is null. From: " + fromFormat.name() + " To: " + toFormat.name());
        return parseDate(fromFormat.getFormatter().parseDateTime(dateText).toDate(), toFormat);
    }

    public static Date clearDate(Date date){
        return new DateTime(date).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate();
    }

    public static Date startOfHour(Date date){
        return new DateTime(date).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate();
    }

    public static Date endOfHour(Date date){
        return new DateTime(date).withMinuteOfHour(59).withSecondOfMinute(59).toDate();
    }

    public static Date startOfMin(Date date){
        return new DateTime(date).withSecondOfMinute(0).withMillisOfSecond(0).toDate();
    }

    public static Date setDate230000(Date date){
        return new DateTime(date).withHourOfDay(23).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate();
    }

    public static Date setDate235959(Date date){
        return new DateTime(date).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).toDate();
    }

    public static Date getRawToday(){
        return new Date();
    }

    public static Date getToday() {
        return clearDate(getRawToday());
    }

    public static Date getToday(Date date) {
        return clearDate(date);
    }

    public static Date getTomorrow() {
        return addDays(getToday(), 1);
    }

    public static Date getTomorrow(Date date) {
        return addDays(date, 1);
    }

    public static long getDiffMillis(Date start, Date end) {
        return (end.getTime() - start.getTime());
    }

    public static Date addTodayField(DateFieldEnums dateFieldEnums, int value){
        return addField(getRawToday(), dateFieldEnums, value);
    }

    public static Date addField(Date date, DateFieldEnums dateFieldEnums, int value){
        switch (dateFieldEnums){
            case YEAR:
                return addYears(date, value);
            case MONTH:
                return addMonths(date, value);
            case DAY:
                return addDays(date, value);
            case HOUR:
                return addHours(date, value);
            case MINUTE:
                return addMinutes(date, value);
            case SECOND:
                return addSeconds(date, value);
        }
        return date;
    }

    public static Date addSeconds(Date date, int seconds) {
        return new Date(LocalDateTime.fromDateFields(date).plusSeconds(seconds).toDateTime().getMillis());
    }

    public static Date addMinutes(Date date, int minutes) {
        return new Date(LocalDateTime.fromDateFields(date).plusMinutes(minutes).toDateTime().getMillis());
    }

    public static Date addHours(Date date, int hours) {
        return new Date(LocalDateTime.fromDateFields(date).plusHours(hours).toDateTime().getMillis());
    }

    public static Date addDays(Date date, int days){
        return new Date(LocalDateTime.fromDateFields(date).plusDays(days).toDateTime().getMillis());
    }

    public static Date addMonths(Date date, int months){
        return new Date(LocalDateTime.fromDateFields(date).plusMonths(months).toDateTime().getMillis());
    }

    public static Date addYears(Date date, int years){
        return new Date(LocalDateTime.fromDateFields(date).plusYears(years).toDateTime().getMillis());
    }

    public static Date subtractTodayField(DateFieldEnums dateFieldEnums, int value){
        return subtractField(getRawToday(), dateFieldEnums, value);
    }

    public static Date subtractField(Date date, DateFieldEnums dateFieldEnums, int value){
        switch (dateFieldEnums){
            case YEAR:
                return subtractYears(date, value);
            case MONTH:
                return subtractMonths(date, value);
            case DAY:
                return subtractDays(date, value);
            case HOUR:
                return subtractHours(date, value);
            case MINUTE:
                return subtractMinutes(date, value);
            case SECOND:
                return subtractSeconds(date, value);
        }
        return date;
    }

    public static Date subtractSeconds(Date date, int seconds) {
        return addSeconds(date, seconds * -1);
    }

    public static Date subtractMinutes(Date date, int minutes) {
        return addMinutes(date, minutes * -1);
    }

    public static Date subtractHours(Date date, int hours) {
        return addHours(date, hours * -1);
    }

    public static Date subtractDays(Date date, int days){
        return addDays(date, days * -1);
    }

    public static Date subtractMonths(Date date, int months){
        return addMonths(date, months * -1);
    }

    public static Date subtractYears(Date date, int years){
        return addYears(date, years * -1);
    }

    public static boolean isInRange(Date start, Date end, Date toCheck){
        Interval interval = new Interval(new DateTime(start), new DateTime(end));
        return interval.contains(new DateTime(toCheck));
    }
    public static void main(String[] args) throws JodaDateUtilsException {

        Date start = parseDate("07/18/2018 03:15:00 PM", DateFormatEnums.MM_DD_YYYY_AMPM);
        System.out.println(start);
    }


}