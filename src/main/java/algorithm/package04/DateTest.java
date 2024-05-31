package algorithm.package04;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {
    public static void main(String[] args) throws ParseException {


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = sdf.parse("2024-02-26 07:00:00");
        System.out.println(dt);

        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
/*// 日期减1年
        rightNow.add(Calendar.YEAR, -1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        System.out.println("日期减1年:" + reStr);
// 日期加3个月
        rightNow.add(Calendar.MONTH, 3);
        Date dt2 = rightNow.getTime();
        String reStr2 = sdf.format(dt2);
        System.out.println("日期加3个月:" + reStr2);*/
// 日期加10天
        rightNow.add(Calendar.DAY_OF_YEAR, 273);
        Date dt3 = rightNow.getTime();
        String reStr3 = sdf.format(dt3);
        System.out.println("日期加280天:" + reStr3);


    }
}
