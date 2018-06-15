package com.qding.api.util;

import com.google.common.collect.Lists;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by qd on 2015/10/26.
 */
public class DateUtil {

    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 格式化日期
     * <p>
     * 日期格式yyyy-MM-dd
     *
     * @return
     */
    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 将字符串日期时间转换成java.util.Date类型
     * <p>
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     *
     * @param datetime
     * @return
     */
    public static Date parseDatetime(String datetime) throws ParseException {
        return datetimeFormat.parse(datetime);
    }

    public static List<String> getDateList(){

        List<String> dateList = Lists.newArrayList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        dateList.add(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        cal.set(Calendar.DAY_OF_MONTH, 2);
        dateList.add(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        cal.roll(Calendar.DAY_OF_MONTH, -2);
        dateList.add(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
        cal.roll(Calendar.DAY_OF_MONTH, -1);
        dateList.add(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));

        return dateList;
    }


    public static String Date2Str(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(date);
        return  str;
    }

    public static Timestamp getDayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 001);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getDayEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        return new Timestamp(cal.getTimeInMillis());
    }


    public static Long getDayBeginTimestamp() {
        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60
                * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND)
                * 1000);
        return date2.getTime();
    }



    public static Long getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY ,0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 001);
        return  cal.getTime().getTime();
    }

    //需要注意的是：月份是从0开始的，比如说如果输入5的话，实际上显示的是4月份的最后一天，千万不要搞错了哦
    public static Long getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY ,23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        // new   SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime())
        return   cal.getTime().getTime();
    }


    /**
     * 得到二个日期间的间隔天数
     */
    public static Long getTwoDay(Long endtime,Long startTime) {
        long day = 0;
        if (endtime<startTime) return -1L;
        day = (endtime - startTime)/ (24 * 60 * 60 * 1000);
        if ((endtime - startTime)%(24 * 60 * 60 * 1000)>0) day++;
        return day;
    }


    public static String getDateByNum(String dateStr,int num) {

        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date =sdf.parse(dateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DAY_OF_MONTH, num);

            DateFormat fmtDateTime = new SimpleDateFormat("yyyy-MM-dd");
            String dates = fmtDateTime.format(c.getTime());

            return dates;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String timeStampToStr(Long  timeStamp){

        Date date = new Date();
        date.setTime(timeStamp);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(date);
        return  str;
    }

    public static Date parseDate(String sDate) {

        SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date retDate  = formatter.parse(sDate);
            return retDate;
        } catch (Exception ex) {
        }
      return  null;
    }


    public static Date parseDateTime(String sDate) {

        SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date retDate  = formatter.parse(sDate);
            return retDate;
        } catch (Exception ex) {
        }
        return  null;
    }
    
    public static Date addDay(Date from, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(from);
        cal.add(Calendar.DAY_OF_MONTH, days);
        
        return cal.getTime();
    }

    public static boolean isTimeRang(String star,String end){

        SimpleDateFormat localTime=new SimpleDateFormat("HH:mm:ss");
        try{
            Date sdate = localTime.parse(star);
            Date edate=localTime.parse(end);
            Date curDate=localTime.parse(localTime.format(new Date( System.currentTimeMillis())));
            long time =curDate.getTime();
            if(time>=sdate.getTime()&& time<=edate.getTime()){
               return  true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断某个日期是否在某个日期范围
     *
     * @param beginDate 日期范围开始
     * @param endDate 日期范围结束
     * @param src 需要判断的日期
     * @return
     */
    public static boolean between(Date beginDate, Date endDate, Date src) {
        return beginDate.before(src) && endDate.after(src);
    }



    public static void main(String args[]){

//        System.out.println(getDayBegin().getTime());
//        System.out.println(getDayEnd().getTime());
//        System.out.println(getFirstDayOfMonth(2017,2));
//        System.out.println(getLastDayOfMonth(2017,2));
//        System.out.println(timeStampToStr(1469289600000L));
        
        System.out.println(getDayBegin().getTime());

    }
}
