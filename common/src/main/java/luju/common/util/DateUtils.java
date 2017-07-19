package luju.common.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    public static int getNowWeek() {
        Calendar cal = Calendar.getInstance();

        cal.setTime(DateTime.now().toDate());

        int week = cal.get(Calendar.DAY_OF_WEEK);

        if (week == 1) {
            week = 7;
        } else {
            week = week - 1;
        }

        return week;
    }

    /**
     * 日期字符串格式YYYYMMDD
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date dataFromStringToDate(String date) {
        String newDate = date.substring(0,4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Date(sdf.parse(newDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取查询日期的月份查询
     *
     * @param date 日期字符串
     * @return 查询的月份
     */
    public static String formatQueryDate_YYYY_MM(String date) {
        String newDate = date.substring(0,4) + "-" + date.substring(5, 7);

        return newDate;
    }

    /**
     * 日期字符串格式yyyy-MM-dd
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date convertStringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        try {
            return new Date(sdf.parse(date).getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 日期字符串格式yyyy-MM-dd - yyyy-MM-dd
     *
     * @param dateRange 日期范围字符串
     * @return 日期
     */
    public static Date convertStringToStartDate(String dateRange) {
        String startDateString = dateRange.substring(0, 10);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return new Date(sdf.parse(startDateString).getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期字符串格式yyyy-MM-dd - yyyy-MM-dd
     *
     * @param dateRange 日期范围字符串
     * @return 日期
     */
    public static Date convertStringToEndDate(String dateRange) {
        String startDateString = dateRange.substring(12, 23);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return new Date(sdf.parse(startDateString).getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int nowYear() {
        DateTime time = new DateTime();

        return time.year().get();
    }

    public static String timeSplitMs(Timestamp ts) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (null != ts) {
                return sdf.format(ts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获得当前时间的YYYYMMDD字符串表示
     *
     * @return 返回日期字符串
     */
    public static String nowYYYYmmdd() {
        return DateTime.now().toString("yyyyMMdd");
    }

    /**
     * 把中文格式例如2017年05月19日转换为Date
     *
     * @param date 字符串日期
     * @return 返回日期
     */
    public static Date convertZHCNStringFormat(String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy年MM月dd日");

        return new Date(DateTime.parse(date, formatter).getMillis());
    }

    /**
     * 获得今天的YYYY-MM-DD格式的日期
     */
    public static String getNowDayDateFormatYYYY_MM_DD() {
        return DateTime.now().toString("yyyy-MM-dd");
    }

    /**
     * 获取当前天的后一天日期
     */
    public static String getNowNextDayDateFormatYYYY_MM_DD() {
        DateTime now = new DateTime();
        DateTime nextDay = now.plusDays(1);

        return nextDay.toString("yyyy-MM-dd");
    }

    /**
     * 获取下一个月的今天
     */
    public static String getNextMonthNowDayDateFormatYYYY_MM_DD() {
        DateTime now = new DateTime();
        DateTime nextMonthDay = now.plusMonths(1);

        return nextMonthDay.toString("yyyy-MM-dd");
    }

    /**
     * 获取下一个月的今天日期
     */
    public static Date getNextMonthDateTodayFormatYYYY_MM_DD() {
        DateTime now = new DateTime();
        DateTime nextMonthDay = now.plusMonths(1);

        return new Date(nextMonthDay.getMillis());
    }
}
