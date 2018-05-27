package com.yz.album.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {

    public String millToDate(long mill){

        Date date = new Date(mill);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    public long dateToMill(String time) throws ParseException {
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss|S" );
        Date date = sdf.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Long afterChangeTime = calendar.getTimeInMillis();
        return afterChangeTime;
    }

    //输入字符串转换为印尼时间
    public long changtoIndonesia(String dateTime) throws ParseException {

        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss：" );
        Date date = sdf.parse( dateTime );
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Long afterChangeTime = calendar.getTimeInMillis();

        return afterChangeTime;
    }

    //获取印尼时区当前时间
    public long getIndonesia() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Long nowaday = calendar.getTimeInMillis() + 28800000;//加了8小时
        return nowaday;
    }

    //判断是否同一天
    public static final int SECONDS_IN_DAY = 60 * 60 * 24;
    public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;
    public static boolean isSameDayOfMillis(final long ms1, final long ms2) {
        final long interval = ms1 - ms2;
        return interval < MILLIS_IN_DAY
                && interval > -1L * MILLIS_IN_DAY
                && toDay(ms1) == toDay(ms2);
    }
    private static long toDay(long millis) {
        return (millis + TimeZone.getDefault().getOffset(millis)) / MILLIS_IN_DAY;
    }
    //


    public  double getRemainTime(String time,Integer monthAmount) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));

        int month = getMonth();

        int year = getYear();

        int endmonth = month + monthAmount-1;

        String endday = getFirstDayOfMonth(year,endmonth);

        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endday+" " + "00:00:00"));

        double lastTime = calendar.getTimeInMillis();

        calendar.setTime(new Date());//这里得到现在的时间

        double remainingTime = lastTime - (calendar.getTimeInMillis());

        return remainingTime;
    }


    public long changeToMillis(String time,String format){

        long millTime = 0;
        if(format.equals("day")){
             millTime = Long.parseLong(time)*24*60*60*1000;
        }
        else if(format.equals("month")){
             millTime = Long.parseLong(time)*30*24*60*60*1000;
        }
        if(format.equals("ms")){
            Long.parseLong(time);
        }
        return millTime;
    }

    public  double getLastTime(String time) throws ParseException {

        String [] strs = time.split("[_]");
        long times = changeToMillis(strs[0],strs[1]);//获得的是有效时间毫秒

        double lastTime= 0;

        if(strs[1].equals("month")) {
            Calendar calendar = Calendar.getInstance();
            int monthAmount = Integer.parseInt(strs[0]);
            int month = getMonth();
            int year = getYear();
            int endmonth = month + monthAmount - 1;
            String endday = getFirstDayOfMonth(year, endmonth);
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endday + " " + "00:00:00"));
            lastTime = calendar.getTimeInMillis();
        }

        else if (strs[1].equals("day")){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            long NowTime = calendar.getTimeInMillis();
            long duration = times;
            lastTime = times + NowTime;
        }
        return  lastTime;
    }

    public  double getRemainTime(String time) throws ParseException {

        String [] strs = time.split("[_]");
        long times = changeToMillis(strs[0],strs[1]);//获得的是有效时间毫秒

        double remainingTime = 0;

        if(strs[1].equals("month")) {
           remainingTime = (double) times;
        }
        else if (strs[1].equals("day")){
            remainingTime = (double)times;
        }
        return  remainingTime;
    }

    public double millChangeFormat(double mill,String format){

        double ms = mill;

        if(format.equals("ss")){
            return ms/1000;
        }
        else if(format.equals("mm")){
            return ms/1000/60;
        }
        else if(format.equals("HH")){
            return ms/1000/60/60;
        }
        else if(format.equals(("dd"))){
            return ms/1000/60/60/24;
        }
        else if(format.equals("MM")){
            return ms/1000/60/60/24/30;
        }
        else if(format.equals("yyyy")){
            return ms/1000/60/60/24/30/12;
        }
        return ms;
    }

    public  String getLastDayOfMonth(int year,int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMaximum(Calendar.DATE));
        return  new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }

    public static String getFirstDayOfMonth(int year, int month) {//月份从零开始
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
        return   new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }

    int getYear()
    {
        Date date = new Date();
        String year = new SimpleDateFormat("yyyy").format(date);
        return Integer.parseInt(year);
    }

    int getMonth()
    {
        Date date = new Date();
        String month = new SimpleDateFormat("MM").format(date);
        return Integer.parseInt(month);
    }


    public static void main(String args[]) throws ParseException {
        String iiii= "2017-11-17 14:24:31.0";
        Date nowTime = new Date();
        String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(iiii);
        System.out.println(s);

        String try1 = "7_day";
        String[] s1 = try1.split("[_]");

        System.out.println(s1[0]);
        System.out.println(s1[1]);

        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTimeInMillis());
        TimeUtil timeUtil = new TimeUtil();
        int month = timeUtil.getMonth();
        Double a = timeUtil.getRemainTime("1_day");
        Double b = timeUtil.getRemainTime("1_month");
        Double c= timeUtil.getLastTime("1_day");
        Double d = timeUtil.getLastTime("1_month");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        Calendar can = Calendar.getInstance();

        can.setTimeInMillis(new Double(c).longValue());
        Date date = can.getTime();
        System.out.println(sdf.format(date));

        can.setTimeInMillis(new Double(d).longValue());
        date = can.getTime();
        System.out.println(sdf.format(date));

        System.out.println("a"+a);
        System.out.println("b"+b);
        System.out.println(timeUtil.millChangeFormat(a,"dd"));
        System.out.println(timeUtil.millChangeFormat(b,"dd"));
        System.out.println(c);
        System.out.println(d);

    }


}
