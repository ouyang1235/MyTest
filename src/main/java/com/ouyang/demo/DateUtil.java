package com.ouyang.demo;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * @PakeageName:com.hd.common.utils
 * @ClassName:DateUtil
 * @date:2021/01/28 19:24
 * @author: Superlee
 * @Description: 日期工具类
 */
public class DateUtil {
    private static final String DEFAULT_FORMAT = "yyyyMMdd";

    /**
     * 获取时间戳
     *
     * @return
     */
    public static Integer getTime(Date date) {
        if (date == null)
            return null;
        Long time = date.getTime() / 1000;
        return time.intValue();
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Integer getCurrentDay() {
        Date date = new Date();
        Long time = date.getTime() / 1000;
        return time.intValue();
    }

    /**
     * 获取当前时间戳 毫秒
     *
     * @return
     */
    public static Long getCurrentDayMicro() {
        Date date = new Date();

        return date.getTime();
    }

    /**
     * 获取某个时间往后X天后时间戳
     *
     * @return
     */
    public static Integer getNextXDay(Integer dateTime, Integer nextTime) {
        Date date = new Date(dateTime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +nextTime);
        date = calendar.getTime();
        Long time = date.getTime() / 1000;
        return dateTime + time.intValue();
    }

    /**
     * 获取date的前(后) var天 日期
     *
     * @return
     */
    public static String getCustomizeDay(Date date, int var) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, var);
        date = calendar.getTime();
        return formatDate(date);
    }

    /**
     * 获取date的前(后) var天 日期
     *
     * @return
     */
    public static Date getCustomizeDayForDate(Date date, int var) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, var);
        date = calendar.getTime();
        return date;
    }


    /**
     * 时间格式化 格式化日期为字符串
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");// 格式化一下时间
        String defaultStartDate = dateFmt.format(date); // 格式化当前
        return defaultStartDate;
    }

    /**
     * 时间格式化 格式化字符串为时间戳
     *
     * @param str
     * @return
     */
    public static Integer parseDate(String str) {
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化一下时间
        Date date = null;
        try {
            date = dateFmt.parse(str);
        } catch (ParseException e) {
//            log.error("时间格式化异常 {}", e);
        }
        return getTime(date);
    }


    /**
     * 时间格式化 格式化字符串为时间戳 ms
     *
     * @param str
     * @return
     */
    public static Long parseMicroTimestamp(String str) {
        try {
            SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化一下时间
            Date date = dateFmt.parse(str);
            return date.getTime();
        } catch (ParseException e) {
//            log.error("时间格式化异常 {}", e);
        }

        return null;
    }

    public static Integer parseDatetimeStr(String str, String format) {
        SimpleDateFormat dateFmt = new SimpleDateFormat(format);// 格式化一下时间
        Date date = null;
        try {
            date = dateFmt.parse(str);
        } catch (ParseException e) {
//            log.error("时间格式化异常 {}", e);
        }
        return getTime(date);
    }


    public static Date parseToDate(String dateStr, String format) {
        Date date = null;
        try {
            if (dateStr!=null) {
                date = new SimpleDateFormat(format).parse(dateStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getYearStart() {
        Calendar calendar = Calendar.getInstance();
        Date currYearFirst = calendar.getTime();
        return new SimpleDateFormat("yyyy").format(currYearFirst) + "-01-01 00:00:00";

    }

    /**
     * 获取某个月天数
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String getYearEnd() {
        Calendar calendar = Calendar.getInstance();
        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat("yyyy").format(currYearLast) + "-12-31 23:59:59";

    }
    /**
     * 获取本月开始日期
     *
     * @return String
     **/
    public static String getMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time) + " 00:00:00";
    }

    /**
     * 获取本月最后一天
     *
     * @return String
     **/
    public static String getMonthEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time) + " 23:59:59";
    }

    /**
     * 获取本周的第一天
     *
     * @return String
     **/
    public static String getWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, 0);
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time) + " 00:00:00";
    }

    /**
     * 获取本周的最后一天
     *
     * @return String
     **/
    public static String getWeekEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));
        cal.add(Calendar.DAY_OF_WEEK, 1);
        Date time = cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(time) + " 23:59:59";
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static LocalDateTime getCurrentLocalDateTime() {

        return LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
    }

    /**
     * LocalDateTime时间转String时间
     *
     * @param localDateTime
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return df.format(localDateTime);
    }

    /**
     * LocalDate时间转String时间
     *
     * @param localDate
     * @return
     */
    public static String localDateToString(LocalDate localDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return df.format(localDate);
    }

    /**
     * LocalDateTime时间转时间戳
     *
     * @param localDateTime
     * @return
     */
    public static Integer localDateTimeToTimestamp(LocalDateTime localDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return parseDate(df.format(localDateTime));
    }

    /**
     * String时间转LocalDateTime时间
     *
     * @param time
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String time) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return LocalDateTime.parse(time, df);
    }

    /**
     * LocalDate 转为 Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
    }

    /**
     * LocalDateTime 转为 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return localDateTime==null?null:Date.from(localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant());
    }

    public static void main(String[] args) {
        Date date = localDateTimeToDate(null);
    }

    /**
     * 时间戳转LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestampToLocalDateTime(Integer timestamp) {
        long microtime = Long.valueOf(timestamp) * 1000;
        return Instant.ofEpochMilli(microtime).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    /**
     * 时间戳转时间字符串
     *
     * @param timestamp
     * @return
     */
    public static String timestampToString(Integer timestamp) {
        long microtime = Long.valueOf(timestamp) * 1000;
        LocalDateTime localDateTime = Instant.ofEpochMilli(microtime).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();

        return localDateTimeToString(localDateTime);
    }

    /**
     * 时间戳转LocalDate
     *
     * @param microtime
     * @return
     */
    public static LocalDate timestampToLocalDate(Long microtime) {
        return Instant.ofEpochMilli(microtime).atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    /**
     * 字符时间转LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate stringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * 字符时间转LocalTime
     *
     * @param time
     * @return
     */
    public static LocalTime stringToLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * 时间戳转LocalTime
     *
     * @param microtime
     * @return
     */
    public static LocalTime timestampToLocalTime(Long microtime) {
        return Instant.ofEpochMilli(microtime).atZone(ZoneOffset.ofHours(8)).toLocalTime();
    }

    /**
     * 获取N天前/后开始时间 - int
     *
     * @param days
     * @return
     */
    public static Integer getTimestampMin(Integer days) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().plusDays(days), LocalTime.MIN);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return parseDate(df.format(localDateTime));
    }


    /**
     * 获取N天前/后开始日期 - String
     *
     * @param days
     * @return
     */
    public static String getPlusDateString(Integer days) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().plusDays(days), LocalTime.MIN);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");

        return df.format(localDateTime);
    }

    /**
     * 获取N天前/后结束时间 - int
     *
     * @param days
     * @return
     */
    public static Integer getTimestampMax(Integer days) {
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().plusDays(days), LocalTime.MAX);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return parseDate(df.format(localDateTime));
    }

    /**
     * 时间戳转String Date
     *
     * @param timestamp
     * @return
     */
    public static String timestampToStringDate(Long timestamp,String format) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        DateTimeFormatter df = null;
        if (format!=null){
            df = DateTimeFormatter.ofPattern(format);
        }else{
            df = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
        }
        return df.format(localDateTime);
    }

    /**
     * 时间戳转换为指定格式yyyyMMddHHmmssSSS
     * @param time
     * @return
     */
    public static String transFormat(Long time){
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = sd.format(time);
        String timeStr = null;
        try {
            timeStr = sdf.format(sd.parse(format));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStr;
    }

    /**
     * 匹配存在的开始回放时间戳
     * @param set
     * @param timestampStart
     * @return
     */
    public static String matchesStartTime(Set<String> set, String timestampStart){
        Object[] timestampArr = set.toArray();
        Object timestampObj = timestampArr[0];
        long timeStart = Long.valueOf(timestampStart).longValue();
        long arrStart = Long.valueOf(String.valueOf(timestampObj)).longValue();
        Long realStartTime = arrStart;
        Long minDiff = Math.abs(arrStart - timeStart);
        for (int i = 0; i < timestampArr.length; i++){
            long timestamp = Long.valueOf(String.valueOf(timestampArr[i])).longValue();
            if (timeStart > timestamp){
                long diff = Math.abs(timestamp - timeStart);
                if (minDiff > diff || minDiff == diff){
                    minDiff = diff;
                    realStartTime = timestamp;
                }
            }
        }
        return String.valueOf(realStartTime);
    }

    /**
     * 匹配开始角标
     *
     * @param set
     * @param timestampStart
     * @return
     */
    public static long matchesStart(Set<String> set, String timestampStart) {
        Object[] timestampArr = set.toArray();
        long timeStart = Long.parseLong(timestampStart);
        for (int i = 0; i < timestampArr.length; i++) {
            long timestamp = Long.parseLong(String.valueOf(timestampArr[i]));
            if (timestamp > timeStart) {
                if (i == 0) {
                    return 0;
                } else {
                    return i - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 匹配结束角标
     *
     * @param set
     * @param timestampEnd
     * @return
     */
    public static long matchesEnd(Set<String> set, String timestampEnd) {
        Object[] timestampArr = set.toArray();
        int size = timestampArr.length;
        long timeEnd = Long.parseLong(timestampEnd);
        for (int i = size - 1; i >= 0; i--) {
            long timestamp = Long.parseLong(String.valueOf(timestampArr[i]));
            if (timestamp < timeEnd) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 匹配存在的结束回放时间戳
     * @param set
     * @param timestampEnd
     * @return
     */
    public static String matchEndTime(Set<String> set, String timestampEnd){
        Object[] timestampArr = set.toArray();
        Object timestampObj = timestampArr[timestampArr.length-1];
        long timeEnd = Long.valueOf(timestampEnd).longValue();
        long arrEnd = Long.valueOf(String.valueOf(timestampObj)).longValue();
        Long realEndTime = arrEnd;
        Long minDiff = Math.abs(arrEnd - timeEnd);
        for (int i = 0; i < timestampArr.length; i++){
            long timestamp = Long.valueOf(String.valueOf(timestampArr[i])).longValue();
            if (timestamp > timeEnd || timestamp == timeEnd){
                long diff = Math.abs(timestamp - timeEnd);
                if (minDiff > diff || minDiff == diff){
                    minDiff = diff;
                    realEndTime = timestamp;
                }
            }
        }
        return String.valueOf(realEndTime);
    }

}
