package com.qding.insurance.param;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.google.common.collect.Maps;
import com.qding.brick.common.PageUtil;

/**
 * Created by qd on 2016/12/29.
 */
public abstract class Params implements Serializable {
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    /**
     * 将 实体属性转换到 Map中
     * @return
     */
    public HashMap<String,Object> toParamsMap(){
        return toParamsMap(true);
    }

    public HashMap<String,Object> toParamsMap(boolean hasPage){
        HashMap<String,Object> paramsMap = Maps.newHashMap();
        Field[] fieldList = this.getClass().getDeclaredFields();

        for(Field field : fieldList){
            try {
                field.setAccessible(true);
                paramsMap.put(field.getName(), field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        fieldList = this.getClass().getSuperclass().getDeclaredFields();

        for(Field field : fieldList){
            try {
                field.setAccessible(true);
                paramsMap.put(field.getName(), field.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (hasPage)
            PageUtil.getPageMap(paramsMap);

        return paramsMap;
    }
}
