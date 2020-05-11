package com.qxt.bysj.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {


    /**
     * 用于计算两个日期之间的间隔 （原时间差工具类跨年计算错误）
     * @param first 第一个日期(取最近的时间)
     * @param second 第二个日期（较远的时间）
     * @return 返回天数差
     *
     */
    public static int  longOfTwoDate(Date first, Date second) throws ParseException {
        /**
         * 原有的时间在转化成String然后再次解析为Date
         * 目的: 清除时间原本自带的小时毫秒便于计算
         */
        //格式化时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //将时间转化成String类型
        String fDate = format.format(first);
        String sDate = format.format(second);
        //解析String为Date
        Date date1 = format.parse(fDate);
        Date date2 = format.parse(sDate);
        //两日期相减，所得毫秒数 除以1000得秒 除以3600得小时 除24得天数得到两个日期的间隔
        int day = (int) ((date1.getTime() - date2.getTime()) / (1000*3600*24));
        //返回天数
        return day;
    }
}
