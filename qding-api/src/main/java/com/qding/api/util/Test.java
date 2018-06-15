package com.qding.api.util;


import com.ning.http.util.Base64;
import com.qding.framework.common.util.QDStringUtil;
import com.qiniu.util.UrlSafeBase64;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by qd on 2016/5/25.
 */
public class Test {

    private static String checkUrlForGoodDetail (String url){

        if (url.contains("shopping/details/")){
            int index = url.lastIndexOf("/");
            System.out.println(index+"========"+( url.length()-1));
            if (index < url.length()-1) {
                String skuId = url.substring(index+1,url.length());
                return  skuId;
            }

        }

        return "";
    }

    public static void main(String[] args) {

        Calendar effectiveStart = getStartCalendar();
        Calendar effectiveEnd = null;

        int purposeDateType =1;

        //今天
        if(purposeDateType == 1) {
            effectiveEnd = getEndCalendar();
        }
        //明天
        else if(purposeDateType == 2) {

            effectiveEnd = getEndCalendar();
            addDay(effectiveStart, 1);
            addDay(effectiveEnd, 1);
        }
        //后天
        else if(purposeDateType == 3) {

            effectiveEnd = getEndCalendar();
            addDay(effectiveStart, 2);
            addDay(effectiveEnd, 2);
        }

        System.out.println(effectiveStart.getTime());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startDate=sdf.format(effectiveStart.getTime());
        String endDate=sdf.format(effectiveEnd.getTime());
        System.out.println(startDate);
        System.out.println(endDate);
//        System.out.println(checkUrlForGoodDetail("http://qam.iqdnet.com/shopping/details/8474"));
      /*  try {
            String respons = URLEncoder.encode("|");
            System.out.println(respons);
        } catch (Exception e) {
            e.printStackTrace();
        }

        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(df.format(BigDecimal.valueOf(Long.parseLong("100011")).divide(new BigDecimal("100"))));
        String url="http://7xjyap.com2.z0.glb.qiniucdn.com/image-2b4a0642-53e1-4cd9-8bf1-3443df0efb64.jpg";
        System.out.println((UrlSafeBase64.encodeToString(url)));*/
      /*  String ImageURL = "http://developer.qiniu.com/resource/logo-2.jpg";
        String code;
        code = new String(Base64.encode(ImageURL.getBytes(),1));
        System.out.println(code);*/

/*        //修改前
        Set<String> sSet=Sets.newHashSet("1","2","3");
        //修改后
        Set<String> dSet=Sets.newHashSet("2","3","4","5");
        Sets.SetView<String> diffSetHandle=Sets.difference(dSet, sSet);
        Iterator iter=diffSetHandle.iterator();
        while(iter.hasNext()){
            System.out.println("Set的不同元素："+iter.next().toString());
        }*/
    }


    private static Calendar getStartCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c;
    }

    private static Calendar getEndCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c;
    }

    private static void addDay(Calendar c, int day) {
        c.add(Calendar.DATE, day);
    }
}
