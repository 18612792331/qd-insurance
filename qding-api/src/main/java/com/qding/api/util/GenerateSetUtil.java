package com.qding.api.util;

import com.qding.api.call.app.qding.v3_0_1.struct.groupon.GrouponDetailDto;

import java.lang.reflect.Field;

/**
 * Created by jinhaishan on 17/3/3.
 */
public class GenerateSetUtil {

    public static void main(String[] args) {
        genertSet(GrouponDetailDto.class, "groupBuyingDetailDto", "promotionGrouponConfigDto");
    }

    public static void genertSet(Class clazz, String toName, String fromName)
    {
        for(; clazz != Object.class; clazz=clazz.getSuperclass())
        {
            Field[] fields =  clazz.getDeclaredFields();
            for(Field field: fields)
            {
                String fieldName = field.getName();
           System.out.println(toName +".set"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1, fieldName.length()) + "("+fromName +");");
//                System.out.println(toName +".set"+fieldName.substring(0,1).toUpperCase() + fieldName.substring(1, fieldName.length()) + "();");
            }
        }

    }
}
