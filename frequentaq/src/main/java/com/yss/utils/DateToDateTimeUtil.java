package com.yss.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToDateTimeUtil {
    public static Timestamp transfer() {
        Date date = new Date(); //获取当前时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp time = new Timestamp(date.getTime());
        return time;
    }
}
