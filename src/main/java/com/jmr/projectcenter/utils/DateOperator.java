package com.jmr.projectcenter.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DateOperator {
    private static String DATE_PATTERN = "yyyy-MM-dd";
    private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);

    /**
     * 获取某个时间段内所有日期
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getDayBetweenDates(String begin, String end) throws ParseException {
        Date dBegin = sdf.parse(begin);
        Date dEnd = sdf.parse(end);
        List<String> dateList = new ArrayList<>();
        dateList.add(begin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            dateList.add(sdf.format(calBegin.getTime()));
        }
        return dateList;
    }

    /**
     * 获取过去第past天的日期
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        return sdf.format(calendar.getTime());
    }

    public static List<String> getIntervalDates(int interval) {
        ArrayList<String> pastDaysList = new ArrayList<>();
        for (int i = 0; i <interval; i++) {
            pastDaysList.add(getPastDate(i));
        }
        return pastDaysList;
    }

    public static String parseDate(Date date) {
        return sdf.format(date);
    }

    public static Date parseString(String date) throws ParseException { return  sdf.parse(date); }

    public static Integer getGapDays(Date date, Date start) {
        long gap = (date.getTime() - start.getTime()) / (1000L*3600L*24L);
        return Integer.parseInt(String.valueOf(gap));
    }


    public static void main(String[] args) throws ParseException {
        Integer gap = getGapDays(new Date(), parseString("2020-04-11"));
        System.out.println(gap);
    }
}
