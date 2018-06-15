package com.qding.api.util;

import java.util.List;

/**
 * Created by qd on 2016/1/6.
 */
public class ListUtil {

    /**
     * list转字符串
     * @param stringList
     * @return
     */
    public static String listForStringToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }


    /**
     * list转字符串
     * @param stringList
     * @return
     */
    public static String listForLongToString(List<Long> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (Long string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }


}
