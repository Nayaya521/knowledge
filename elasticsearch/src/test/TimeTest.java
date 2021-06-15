import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/*@SpringBootTest
@RunWith(SpringRunner.class)*/
public class TimeTest {
    @Test
    public  void dealDateFormat() throws ParseException {
        //此格式只有  jdk 1.7才支持  yyyy-MM-dd'T'HH:mm:ss.SSSXXX
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");  //yyyy-MM-dd'T'HH:mm:ss.SSSZ
        Date date = df.parse("2021-05-12T07:39:39.000+00:00");
        SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        Date date1 =  df1.parse(date.toString());
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df2.format(date1));
    }
    @Test
    public  void TimeStampTest() throws ParseException{
       // System.out.println(System.currentTimeMillis());
        System.out.println(UUID.randomUUID().toString());
    }
}
