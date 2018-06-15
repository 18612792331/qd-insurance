package com.qding.api.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by qd on 2017/4/27.
 */
public class EntitySerialUtil {


    public static String serial(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T deserial(String str, Class<T> clazz) {
        if(str == null || str.length() == 0) {
            return null;
        }
        return JSON.parseObject(str, clazz);
    }


}
