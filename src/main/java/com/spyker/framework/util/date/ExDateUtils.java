package com.spyker.framework.util.date;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

@Slf4j
public final class ExDateUtils extends DateUtils {

    public static Date parseDate(Object str) {

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
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(Format.YYYY_MM_DD.format);
    }

    public static String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static String parseDateToStr(final String format, final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String getTime() {
        return dateTimeNow(Format.YYYY_MM_DD_HH_MM_SS.format);
    }

    public static String dateTimeNow() {
        return dateTimeNow(Format.YYYYMMDDHHMMSS.format);
    }

    public static String dateTime(final Date date) {
        return parseDateToStr(Format.YYYY_MM_DD.format, date);
    }

    public static Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /** 日期路径 即年/月/日 如2018/08/08 */
    public static String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, Format.YYYY_MM_DD_.format);
    }

    /** 日期路径 即年/月/日 如20180808 */
    public static String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, Format.YMD.format);
    }

    /**
     * 日期型字符串转化为日期 格式
     *
     * <p>
     *
     * <p>/** 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
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

    public static Date addYears(Date date, int amount) {
        return org.apache.commons.lang3.time.DateUtils.addYears(date, amount);
    }

    public static Date addHours(Date date, int amount) {
        return org.apache.commons.lang3.time.DateUtils.addHours(date, amount);
    }

    public static Date addMinutes(Date date, int amount) {

        return org.apache.commons.lang3.time.DateUtils.addMinutes(date, amount);
    }

    public static Date addSeconds(Date date, int amount) {

        return org.apache.commons.lang3.time.DateUtils.addSeconds(date, amount);
    }

    public static String getCurrentMonth() {
        return ExDateUtils.date2String(getCurrentDate(), ExDateUtils.Format.YYYY_MM);
    }

    public static Date getCurrentDate() {

        return new Date();
    }

    public static String getNextYear(Date date) {
        Date nextYear = org.apache.commons.lang3.time.DateUtils.addYears(date, 1);

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

    public static Date addMonths(Date date, int amount) {
        return org.apache.commons.lang3.time.DateUtils.addMonths(date, amount);
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

    public static Date addDays(Date date, int amount) {
        return org.apache.commons.lang3.time.DateUtils.addDays(date, amount);
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

    /**
     * 是否跨天
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isDaySpan(Date start, Date end) {
        Date startYmd = clearHms(start);
        String startHm = date2String(start, Format.HM);
        String endHm = date2String(end, Format.HM);

        Date startYMDHM =
                ExDateUtils.format2Date(
                        ExDateUtils.date2String(startYmd, ExDateUtils.Format.YYYY_MM_DD)
                                + " "
                                + startHm,
                        ExDateUtils.Format.YYYY_MM_DD_HH_MM);
        Date endYMDHM =
                ExDateUtils.format2Date(
                        ExDateUtils.date2String(startYmd, ExDateUtils.Format.YYYY_MM_DD)
                                + " "
                                + endHm,
                        ExDateUtils.Format.YYYY_MM_DD_HH_MM);

        return startYMDHM.after(endYMDHM);
    }

    public static Date format2Date(String dateValue, Format format) {

        if (null == dateValue || dateValue.trim().length() < 1) {
            return null;
        }

        try {
            SimpleDateFormat dd = new SimpleDateFormat(format.getFormat());
            return dd.parse(dateValue);
        } catch (Exception e) {

            log.error("format2Date error", e.getMessage());

            return null;
        }
    }

    public enum Format {
        YMD("yyyyMMdd"),
        YMDE("yyyyMMdd E"),
        HM("HH:mm"),
        HMS("HH:mm:ss"),
        YYYY_P_MM_P_DD("yyyy.MM.dd"),
        YYYY_P_MM_P_DD_HHMM("yyyy.MM.dd HH:mm"),
        YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
        YYYYMMDDHHMM("yyyyMMddHHmm"),
        YYYY_MM_DD("yyyy-MM-dd"),
        YYYY_Y_MM_M_DD_D("yyyy年MM月dd日"),
        YYYYMM("yyyyMM"),
        YYYY_MM("yyyy-MM"),
        YYYY_MM_("yyyy/MM"),
        YYYY_MM_DD_("yyyy/MM/dd"),
        YYYY_MM_CN("yyyy年MM月"),
        MM_DD_CN("MM月dd日"),
        MM_DD_HH_MM_CN("MM月dd日 HH:mm"),
        YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
        YYYY_MM_DD_CN("yyyy年MM月dd日"),
        YYYY_MM_DD_HH_MM_CN("yyyy年MM月dd日 HH:mm"),
        YYYY_MM_DD_HH_MM_SS_CN("yyyy年MM月dd日 HH:mm:ss");

        private final String format;

        Format(String format) {
            this.format = format;
        }

        public String getFormat() {
            return format;
        }
    }

    public static void main(String[] args) {

        System.out.println(parseDate("2023年11月11日"));
        System.out.println(Format.YYYYMMDDHHMMSS.format);
    }
}