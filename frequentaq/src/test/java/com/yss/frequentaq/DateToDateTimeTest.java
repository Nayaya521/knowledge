package com.yss.frequentaq;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class DateToDateTimeTest {
    @Test
    public void transfer() {
        java.util.Date date = new java.util.Date();
        Timestamp createDate = new Timestamp(date.getTime());
        System.out.println(createDate);
    }
    @Test
    public void  transfer2(){

        Date date = new Date(); //获取当前时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// Date类型
        System.out.println(formatter.format(date));
// Timestamp类型
        Timestamp tdate = new Timestamp(date.getTime());
        System.out.println(formatter.format(tdate));

      /*  Date date = new Date(); //获取当前时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp time = new Timestamp(date.getTime());
        System.out.println();*/
    }
    @Test
    public void testsplit(){
        String[] split = "2.3.4".split("\\.");
        for (String s : split) {
            System.out.println(s);
        }
    }
    @Test
    public void testTime(){
        Date date = new Date(); //获取当前时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(date);
        System.out.println(format);
    }
}
