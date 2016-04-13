package com.hgsoft.yfzx.common.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能描述：格式化日期字符串；天数差等；日期的格式化，日期相加减，特定月份的第一天，最后一天等；
 * @Author: 吴锡霖
 * @Version: 1.0 add
 * @File: DateUtil.java
 * @Date: 2015/2/12
 * @Time: 17:10
 */
public class DateUtil {

    public static Date date = null;
    public static DateFormat dateFormat = null;
    public static Calendar calendar = null;

    public static SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
    public static SimpleDateFormat dayTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 功能描述：获得所在星期的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateByWeek(Date date) {

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int today = now.get(Calendar.DAY_OF_WEEK);
        int first_day_of_week = now.get(Calendar.DATE) + 2 - today; // 星期一
        now.set(now.DATE, first_day_of_week);
        return now.getTime();
    }

    /**
     * 功能描述：获得所在星期的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDateByWeek(Date date) {

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int today = now.get(Calendar.DAY_OF_WEEK);
        int first_day_of_week = now.get(Calendar.DATE) + 2 - today; // 星期一
        int last_day_of_week = first_day_of_week + 6; // 星期日
        now.set(now.DATE, last_day_of_week);
        return now.getTime();
    }

    /**
     * 功能描述：获得所在月份的最后一天
     * 当前月份所在的时间
     *
     * @param
     * @return 月份的最后一天
     */
    public static Date getLastDateByMonth(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + 1);
        now.set(Calendar.DATE, 1);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
        now.set(Calendar.HOUR, 11);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        return now.getTime();
    }

    /**
     * 功能描述：获得所在月份的第一天
     * 当前月份所在的时间
     *
     * @param
     * @return 月份的第一天
     */
    public static Date getFirstDateByMonth(Date date) {

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, 0);
        now.set(Calendar.HOUR, 12);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }

    /**
     * 功能描述：格式化日期
     *
     * @param dateStr String 字符型日期,输入格式类似为“2016-4-7”
     * @param format  String 格式 类似为“yyyy/MM/dd”
     * @return Date 日期
     */
    public static Date parseDate(String dateStr, String format) {
        try {
            dateFormat = new SimpleDateFormat(format);
            String dt = dateStr.replaceAll("-", "/");
            if ((!dt.equals("")) && (dt.length() < format.length())) {
                dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
                        "0");
            }
            date = (Date) dateFormat.parse(dt);
        } catch (Exception e) {
        }
        return date;
    }

    /**
     * 功能描述：格式化日期，格式：yyyy/MM/dd
     *
     * @param dateStr String 字符型日期：YYYY-MM-DD 格式
     * @return Date
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy/MM/dd");
    }

    /**
     * 功能描述：格式化输出日期
     *
     * @param date   Date 日期
     * @param format String 格式
     * @return 返回字符型日期
     */
    public static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                dateFormat = new SimpleDateFormat(format);
                result = dateFormat.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 功能描述：
     *
     * @param date Date 日期
     * @return
     */
    public static String format(Date date) {
        return format(date, "yyyy/MM/dd");
    }

    /**
     * 功能描述：返回年份
     *
     * @param date Date 日期
     * @return 返回年份
     */
    public static int getYear(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 功能描述：返回月份
     *
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日份
     *
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分钟
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 功能描述：返回字符型日期
     *
     * @param date 日期
     * @return 返回字符型日期 yyyy/MM/dd 格式
     */
    public static String getDate(Date date) {
        return format(date, "yyyy/MM/dd");
    }

    /**
     * 功能描述：返回字符型时间
     *
     * @param date Date 日期
     * @return 返回字符型时间 HH:mm:ss 格式
     */
    public static String getTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    /**
     * 功能描述：返回字符型日期时间
     *
     * @param date Date 日期
     * @return 返回字符型日期时间 yyyy/MM/dd HH:mm:ss 格式
     */
    public static String getDateTime(Date date) {
        return format(date, "yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 功能描述：日期相加
     *
     * @param date Date 日期
     * @param day  int 天数
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int day) {
        calendar = Calendar.getInstance();
        long millis = getMillis(date) + ((long) day) * 24 * 3600 * 1000;
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    /**
     * 功能描述：日期相减
     *
     * @param date  Date 日期
     * @param date1 Date 日期
     * @return 返回相减后的日期
     */
    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    /**
     * 功能描述：取得指定月份的第一天
     *
     * @param strdate String 字符型日期
     * @return String yyyy-MM-dd 格式
     */
    public static String getMonthBegin(String strdate) {
        date = parseDate(strdate);
        return format(date, "yyyy-MM") + "-01";
    }

    /**
     * 功能描述：取得指定月份的最后一天
     *
     * @param strdate String 字符型日期
     * @return String 日期字符串 yyyy-MM-dd格式
     */
    public static String getMonthEnd(String strdate) {
        date = parseDate(getMonthBegin(strdate));
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime());
    }

    /**
     * 功能描述：常用的格式化日期
     *
     * @param date Date 日期
     * @return String 日期字符串 yyyy-MM-dd格式
     */
    public static String formatDate(Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    /**
     * 功能描述：以指定的格式来格式化日期
     *
     * @param date   Date 日期
     * @param format String 格式
     * @return String 日期字符串
     */
    public static String formatDateByFormat(Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 功能描述：时间字符串生成timestamp
     *
     * @param day
     * @return
     */
    public static Timestamp dayToTimestamp(String day) {
        return Timestamp.valueOf(day + " 00:00:00.0");
    }

    /**
     * 功能描述：当前的timestamp
     *
     * @return
     */
    public static Timestamp nowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 功能描述：返回今天，不含时间部分
     *
     * @return
     */
    public static String nowDay() {
        return dayFormat.format(new Date());
    }

    /**
     * 功能描述：返回今天，含时间部分
     *
     * @return
     */
    public static String nowDayTime() {
        return dayTimeFormat.format(new Date());
    }

    /**
     * 功能描述：根据时间字符串获得timestamp
     *
     * @param daytime
     * @return
     */
    public static Timestamp dayTimeToTimestamp(String daytime) {
        return Timestamp.valueOf(daytime);
    }

    /**
     * 功能描述：两个日期的天数差
     *
     * @param oldTime 格式为包含“2015-10-11 ......”的日期,时间会被忽略
     * @param nowTime 格式为包含“2015-10-11 ......”的日期 时间会被忽略
     * @return
     * @throws ParseException
     */
    public static int getBetweenDate(String oldTime, String nowTime) throws ParseException {
        //把传入的日期中的"-"符号去掉放入一个字符串数组strdata[]中
        String[] strOldData = oldTime.split("-");
        //循环把数组中的值保存为一个字符串对象
        oldTime = strOldData[0];
        for (int i = 1; i < strOldData.length; i++) {
            oldTime = oldTime + strOldData[i];
        }
        //把传入的日期中的"-"符号去掉放入一个字符串数组strdata[]中
        String[] strNowData = nowTime.split("-");
        //循环把数组中的值保存为一个字符串对象
        nowTime = strNowData[0];
        for (int i = 1; i < strNowData.length; i++) {
            nowTime = nowTime + strNowData[i];
        }

        //设定日期格式
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        //把之前的字符串对象转为设定好格式的日期对象
        Date date1 = format.parse(oldTime);

        Date date2 = format.parse(nowTime);
        //把传入的日期跟当前系统日期相减，得出2个日期之间相差天数
        int differ = Math.abs((int) ((date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000)));
        //返回相差天数
        return differ;
    }

    /**
     * test   测试
     *
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        String a = "2015-05";


        System.out.println(getBetweenDate("2015-10-11 12:12:12", "2015-10-12 1213:12:13")+"\n\n");
        Date now = new Date();
        System.out.println(addDate(now, 15).toString());
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(monthFormat.parse(a));
        //Calendar.MONDAY;
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        System.out.println(calendar.getFirstDayOfWeek());
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(dayFormat.format(calendar.getTime()));

        System.out.println("First day of week is : "
                + new SimpleDateFormat("yyyy-MM-dd")
                .format(getFirstDateByWeek(new Date())));

        System.out.println("Last day of week is : "
                + new SimpleDateFormat("yyyy-MM-dd")
                .format(getLastDateByWeek(new Date())));

        System.out.println("First day of month is : "
                + new SimpleDateFormat("yyyy-MM-dd")
                .format(getFirstDateByMonth(new Date())));

        System.out.println("Last day of month is : "
                + new SimpleDateFormat("yyyy-MM-dd")
                .format(getLastDateByMonth(new Date())));

    }

}
