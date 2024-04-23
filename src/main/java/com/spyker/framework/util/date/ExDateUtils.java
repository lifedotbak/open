package com.spyker.framework.util.date;

import jakarta.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.*;

@Slf4j
public final class ExDateUtils extends DateUtils {

    /** wvp内部统一时间格式 */
    public static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** wvp内部统一时间格式 */
    public static final String URL_PATTERN = "yyyyMMddHHmmss";

    /** 日期格式 */
    public static final String date_PATTERN = "yyyy-MM-dd";

    public static final String zoneStr = "Asia/Shanghai";
    public static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(PATTERN, Locale.getDefault()).withZone(ZoneId.of(zoneStr));
    public static final DateTimeFormatter DateFormatter =
            DateTimeFormatter.ofPattern(date_PATTERN, Locale.getDefault())
                    .withZone(ZoneId.of(zoneStr));
    public static final DateTimeFormatter urlFormatter =
            DateTimeFormatter.ofPattern(URL_PATTERN, Locale.getDefault())
                    .withZone(ZoneId.of(zoneStr));

    /** 兼容不规范的iso8601时间格式 */
    private static final String ISO8601_COMPATIBLE_PATTERN = "yyyy-M-d'T'H:m:s";

    public static final DateTimeFormatter formatterCompatibleISO8601 =
            DateTimeFormatter.ofPattern(ISO8601_COMPATIBLE_PATTERN, Locale.getDefault())
                    .withZone(ZoneId.of(zoneStr));

    /** 用以输出标准的iso8601时间格式 */
    private static final String ISO8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    public static final DateTimeFormatter formatterISO8601 =
            DateTimeFormatter.ofPattern(ISO8601_PATTERN, Locale.getDefault())
                    .withZone(ZoneId.of(zoneStr));

    /** iso8601时间格式带时区，例如：2024-02-21T11:10:36+08:00 */
    private static final String ISO8601_ZONE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";

    public static final DateTimeFormatter formatterZoneISO8601 =
            DateTimeFormatter.ofPattern(ISO8601_ZONE_PATTERN, Locale.getDefault())
                    .withZone(ZoneId.of(zoneStr));

    /** 兼容的时间格式 iso8601时间格式带毫秒 */
    private static final String ISO8601_MILLISECOND_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static final DateTimeFormatter formatterMillisecondISO8601 =
            DateTimeFormatter.ofPattern(ISO8601_MILLISECOND_PATTERN, Locale.getDefault())
                    .withZone(ZoneId.of(zoneStr));

    public static String yyyy_MM_dd_HH_mm_ssToISO8601(String formatTime) {

        return formatterISO8601.format(formatter.parse(formatTime));
    }

    public static String ISO8601Toyyyy_MM_dd_HH_mm_ss(String formatTime) {
        // 三种日期格式都尝试，为了兼容不同厂家的日期格式
        if (verification(formatTime, formatterCompatibleISO8601)) {
            return formatter.format(formatterCompatibleISO8601.parse(formatTime));
        } else if (verification(formatTime, formatterZoneISO8601)) {
            return formatter.format(formatterZoneISO8601.parse(formatTime));
        } else if (verification(formatTime, formatterMillisecondISO8601)) {
            return formatter.format(formatterMillisecondISO8601.parse(formatTime));
        }
        return formatter.format(formatterISO8601.parse(formatTime));
    }

    public static String urlToyyyy_MM_dd_HH_mm_ss(String formatTime) {
        return formatter.format(urlFormatter.parse(formatTime));
    }

    /**
     * yyyy_MM_dd_HH_mm_ss 转时间戳
     *
     * @param formatTime
     * @return
     */
    public static long yyyy_MM_dd_HH_mm_ssToTimestamp(String formatTime) {
        TemporalAccessor temporalAccessor = formatter.parse(formatTime);
        Instant instant = Instant.from(temporalAccessor);
        return instant.getEpochSecond();
    }

    /** 时间戳 转 yyyy_MM_dd_HH_mm_ss */
    public static String timestampTo_yyyy_MM_dd_HH_mm_ss(long timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        return formatter.format(LocalDateTime.ofInstant(instant, ZoneId.of(zoneStr)));
    }

    /**
     * yyyy_MM_dd_HH_mm_ss 转时间戳（毫秒）
     *
     * @param formatTime
     * @return
     */
    public static long yyyy_MM_dd_HH_mm_ssToTimestampMs(String formatTime) {
        TemporalAccessor temporalAccessor = formatter.parse(formatTime);
        Instant instant = Instant.from(temporalAccessor);
        return instant.toEpochMilli();
    }

    /** 时间戳（毫秒） 转 yyyy_MM_dd_HH_mm_ss */
    public static String timestampMsTo_yyyy_MM_dd_HH_mm_ss(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return formatter.format(LocalDateTime.ofInstant(instant, ZoneId.of(zoneStr)));
    }

    /** 时间戳 转 yyyy_MM_dd */
    public static String timestampTo_yyyy_MM_dd(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return DateFormatter.format(LocalDateTime.ofInstant(instant, ZoneId.of(zoneStr)));
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNow() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        return formatter.format(nowDateTime);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowForUrl() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        return urlFormatter.format(nowDateTime);
    }

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

    public static String getNowForISO8601() {
        LocalDateTime nowDateTime = LocalDateTime.now();
        return formatterISO8601.format(nowDateTime);
    }

    public static long getDifferenceForNow(String keepaliveTime) {
        if (ObjectUtils.isEmpty(keepaliveTime)) {
            return 0;
        }
        Instant beforeInstant = Instant.from(formatter.parse(keepaliveTime));
        return ChronoUnit.MILLIS.between(beforeInstant, Instant.now());
    }

    public static long getDifference(String startTime, String endTime) {
        if (ObjectUtils.isEmpty(startTime) || ObjectUtils.isEmpty(endTime)) {
            return 0;
        }
        Instant startInstant = Instant.from(formatter.parse(startTime));
        Instant endInstant = Instant.from(formatter.parse(endTime));
        return ChronoUnit.MILLIS.between(endInstant, startInstant);
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

    public static String format(final Date date, final String format) {
        return DateFormatUtils.format(date, format);
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(Format.YMD_LINE.format);
    }

    public static String dateTimeNow(final String format) {
        return format(new Date(), format);
    }

    public static String dateTimeNow() {
        return dateTimeNow(Format.YMDHMS.format);
    }

    /** 日期路径 即年/月/日 如2018/08/08 */
    public static String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, Format.YMD_PATH.format);
    }

    /** 计算相差天数 */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        return Math.abs((int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24)));
    }

    /**
     * 计算时间差
     *
     * @param endDate 最后时间
     * @param startTime 开始时间
     * @return 时间差（天/小时/分钟）
     */
    public static String timeDistance(Date endDate, Date startTime) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startTime.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /** 增加 LocalDateTime ==> Date */
    public static Date toDate(LocalDateTime temporalAccessor) {
        ZonedDateTime zdt = temporalAccessor.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /** 增加 LocalDate ==> Date */
    public static Date toDate(LocalDate temporalAccessor) {
        LocalDateTime localDateTime = LocalDateTime.of(temporalAccessor, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    public static String date2String(long date, Format format) {
        return date2String(new Date(date), format);
    }

    public static String date2String(Date date, Format format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format.getFormat());
        return sdf.format(date);
    }

    public static Date getCurrentDate() {

        return new Date();
    }

    public static String getNextYear(Date date) {
        Date nextYear = addYears(date, 1);

        return getYear(nextYear);
    }

    public static String getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);

        return year + "";
    }

    public static Date getPreviousMonth(Date date) {
        return addMonths(date, -1);
    }

    public static Date getNextMonth(Date date) {
        return addMonths(date, 1);
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

    public static boolean isDayStart(Date date) {

        String dateValue = date2String(date, Format.HMS);

        return "00:00:00".equals(dateValue);
    }

    public static boolean isDayEnd(Date date) {

        String dateValue = date2String(date, Format.HMS);

        return "23:59:59".equals(dateValue);
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

    public static void main(String[] args) {

        System.out.println(parse("2023年11月11日"));

        Date date = format2Date("20231111222201121", Format.YMDHMSS);
        System.out.println(date.getTime());
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

        YMDHMS("yyyyMMddHHmmSS"),

        HMS("HH:mm:ss"),

        YMDHMSS("yyyyMMddHHmmssSSS"),
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