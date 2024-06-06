package com.spyker.framework.util.date;

import jakarta.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Slf4j
public final class ExDateUtils extends DateUtils {

    /** wvp内部统一时间格式 "yyyy-MM-dd HH:mm:ss" */
    public static final String YMD_LINE_HMS = Format.YMD_LINE_HMS.getFormat();

    /** wvp内部统一时间格式 "yyyyMMddHHmmss"; */
    public static final String YMDHMS = Format.YMDHMS.getFormat();

    /** 日期格式 yyyy-MM-dd */
    public static final String YMD_LINE = Format.YMD_LINE.getFormat();

    public static final String zoneStr = "Asia/Shanghai";
    public static final DateTimeFormatter YMD_LINE_HMS_FORMATTER =
            DateTimeFormatter.ofPattern(YMD_LINE_HMS, Locale.getDefault())
                    .withZone(ZoneId.of(zoneStr));

    public static final DateTimeFormatter YMD_LINE_Formatter =
            DateTimeFormatter.ofPattern(YMD_LINE, Locale.getDefault()).withZone(ZoneId.of(zoneStr));

    public static final DateTimeFormatter YMDHMS_FORMATTER =
            DateTimeFormatter.ofPattern(YMDHMS, Locale.getDefault()).withZone(ZoneId.of(zoneStr));

    /** 兼容不规范的iso8601时间格式 "yyyy-M-d'T'H:m:s" */
    private static final String ISO8601_COMPATIBLE_PATTERN =
            Format.ISO8601_COMPATIBLE_PATTERN.getFormat();

    public static final DateTimeFormatter formatterCompatibleISO8601 =
            DateTimeFormatter.ofPattern(ISO8601_COMPATIBLE_PATTERN, Locale.getDefault())
                    .withZone(ZoneId.of(zoneStr));

    /** 用以输出标准的iso8601时间格式 "yyyy-MM-dd'T'HH:mm:ss" */
    private static final String ISO8601_PATTERN = Format.ISO8601_PATTERN.getFormat();

    public static final DateTimeFormatter formatterISO8601 =
            DateTimeFormatter.ofPattern(ISO8601_PATTERN, Locale.getDefault())
                    .withZone(ZoneId.of(zoneStr));

    /** iso8601时间格式带时区，例如：2024-02-21T11:10:36+08:00 "yyyy-MM-dd'T'HH:mm:ssXXX" */
    private static final String ISO8601_ZONE_PATTERN = Format.ISO8601_ZONE_PATTERN.getFormat();

    public static final DateTimeFormatter formatterZoneISO8601 =
            DateTimeFormatter.ofPattern(ISO8601_ZONE_PATTERN, Locale.getDefault())
                    .withZone(ZoneId.of(zoneStr));

    /** 兼容的时间格式 iso8601时间格式带毫秒 yyyy-MM-dd'T'HH:mm:ss.SSS */
    private static final String ISO8601_MILLISECOND_PATTERN =
            Format.ISO8601_MILLISECOND_PATTERN.getFormat();

    public static final DateTimeFormatter formatterMillisecondISO8601 =
            DateTimeFormatter.ofPattern(ISO8601_MILLISECOND_PATTERN, Locale.getDefault())
                    .withZone(ZoneId.of(zoneStr));

    /**
     * 格式校验
     *
     * @param timeStr 时间字符串
     * @param dateTimeFormatter 待校验的格式
     * @return
     */
    public static boolean verification(String timeStr, DateTimeFormatter dateTimeFormatter) {
        try {
            LocalDate.parse(timeStr, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    public static Date parse(Object str) {

        if (str == null) {
            return null;
        }
        try {

            List<String> parsePatternsList = new ArrayList<>();

            for (Format format : Format.values()) {
                parsePatternsList.add(format.getFormat());
            }

            String[] parsePatterns =
                    parsePatternsList.toArray(new String[parsePatternsList.size()]);

            return parseDate(str.toString(), parsePatterns);
        } catch (Exception e) {
            log.error("error-->{}", e.getMessage());

            return null;
        }
    }

    public static String dateTimeNow(final String format) {
        return format(new Date(), format);
    }

    public static String dateTimeNow() {
        return dateTimeNow(Format.YMDHMS.format);
    }

    public static Date parse(@NotNull final String value, @NotNull final String format) {

        if (StringUtils.isNotBlank(value)) {

            try {
                return parse(value, format);
            } catch (Exception e) {
                log.error("error-->{}", e.getMessage());
                return null;
            }
        }

        return null;
    }

    /** 日期路径 即年/月/日 如2018/08/08 */
    public static String datePath() {
        return format(getCurrentDate(), Format.YMD_PATH.format);
    }

    public static String format(final Date date, final String format) {
        return DateFormatUtils.format(date, format);
    }

    public static Date getCurrentDate() {

        return new Date();
    }

    public static int dayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static int weekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static int hourOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int minutesOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MINUTE);
    }

    public static int weekOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    public static int monthOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int secOfMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.SECOND);
    }

    public static Date clearSec(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date startOfWeek(Date date) {
        int dayOfWeek = dayOfWeek(date);

        if (0 == dayOfWeek) {
            return addDays(date, -7 + 1);
        }

        return addDays(date, dayOfWeek * -1 + 1);
    }

    public static int dayOfWeek(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static Date endOfWeek(Date date) {
        int dayOfWeek = dayOfWeek(date);

        if (0 == dayOfWeek) {
            return date;
        }

        return addDays(date, 7 - dayOfWeek);
    }

    public static float minutesBetween(Date start, Date end) {

        return (end.getTime() - start.getTime()) / (1000 * 60);
    }

    public static float hourBetween(Date start, Date end) {

        start = clearMs(start);
        end = clearMs(end);

        return (end.getTime() - start.getTime()) / (1000 * 3600);
    }

    public static Date clearMs(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static int daysBetween(Date start, Date end) {

        start = clearHms(start);
        end = clearHms(end);

        Long days = (end.getTime() - start.getTime()) / (1000 * 3600 * 24);

        return days.intValue();
    }

    public static Date clearHms(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static int dayNumberOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getActualMaximum(Calendar.DATE);
    }

    public static int dayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Date endOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    public static Date startOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date nowMonthEnd() {

        return endOfMonth(getCurrentDate());
    }

    public static Date endOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addMonths(date, 1));

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return addDays(calendar.getTime(), -1);
    }

    public static Date nowMonthStart() {

        return startOfMonth(getCurrentDate());
    }

    public static Date startOfMonth(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date nowYearStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static long getCurrentDateValue() {
        return new Date().getTime();
    }

    public static Date calcQingMingJie(Date date) {

        // 清明节日期的计算 [Y*D+C]-L Y=年数后2位，D=0.2422，L=闰年数，21世纪C=4.81，20世纪=5.59。

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        String year = calendar.get(Calendar.YEAR) + "";

        // 21世纪C=4.81，20世纪=5.59
        double c = 4.81;
        if (year.startsWith("1")) {
            c = 5.59;
        }
        double d = 0.242;

        int y = Integer.parseInt(year.substring(2, 4));

        Double result = (y * d + c) - (y / 4);

        int days = result.intValue();

        calendar.set(Calendar.MONTH, 4 - 1);
        calendar.set(Calendar.DAY_OF_MONTH, days);

        return calendar.getTime();
    }

    /**
     * 计算两个日期相差的月份数
     *
     * @param start 日期1
     * @param end 日期2
     * @return 相差的月份数
     */
    public static int monthsBetween(Date start, Date end) {

        Period between = Period.between(date2LocalDate(start), date2LocalDate(end));

        return ((Long) between.toTotalMonths()).intValue();
    }

    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    public static Date format2Date(String dateValue, Format format) {

        if (null == dateValue || dateValue.trim().length() < 1) {
            return null;
        }

        try {
            return parse(dateValue, format.getFormat());
        } catch (Exception e) {

            log.error("format2Date error", e.getMessage());

            return null;
        }
    }

    public enum Format {

        /** 大写的SSS是毫秒 */
        YM("yyyyMM"),

        YM_CN("yyyy年MM月"),

        YM_POINT("yyyy.MM"),

        YM_LINE("yyyy-MM"),

        YMD("yyyyMMdd"),

        YMD_CN("yyyy年MM月dd日"),

        YMD_P("yyyy.MM.dd"),

        YMD_LINE("yyyy-MM-dd"),

        YMD_LINE_HMS("yyyy-MM-dd HH:mm:ss"),

        YMD_LINE_HMSS("yyyy-MM-dd HH:mm:ss.SSS"),

        YMD_PATH("yyyy/MM/dd"),

        YMDHMS("yyyyMMddHHmmss"),

        HMS("HH:mm:ss"),

        YMDHMSS("yyyyMMddHHmmssSSS"),

        ISO8601_COMPATIBLE_PATTERN("yyyy-M-d'T'H:m:s"),

        ISO8601_PATTERN("yyyy-MM-dd'T'HH:mm:ss"),

        ISO8601_ZONE_PATTERN("yyyy-MM-dd'T'HH:mm:ssXXX"),

        ISO8601_MILLISECOND_PATTERN("yyyy-MM-dd'T'HH:mm:ss.SSS"),
        ;

        private final String format;

        Format(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }
}