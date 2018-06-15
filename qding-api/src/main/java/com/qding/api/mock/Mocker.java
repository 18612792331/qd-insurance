
package com.qding.api.mock;

import com.qding.api.process.print.AbstractProtocolPrint;
import com.qding.api.process.print.JsonProtocolPrint;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.api.util.ApplicationContextUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Mocker {

    public static String mock(Type returnType)
            throws Exception {

        AbstractProtocolPrint print = ApplicationContextUtil.getBeansOfType(JsonProtocolPrint.class);
        
        Response<ResponseData> responseInstance = (Response<ResponseData>) newInstance(Response.class);
        ResponseData data = (ResponseData) dataInstance(returnType);

        responseInstance.setData(data);
        fillFields(data);

        String ret = print.out(responseInstance);

        return ret;
    }

    private static Object newInstance(Class cls) {
        Object object = null;
        try {
            object = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }
    private static Object newInstance(String clsName) {
        Object object = null;
        try {
            object = Class.forName(clsName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return object;
    }

    /**
     * 泛型返回参数实例化
     */
    private static Object dataInstance(Type returnType) throws Exception {

        if (returnType == null) {
            return null;
        }

        Object data = null;

        if (returnType instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) returnType).getActualTypeArguments();
            Class type = (Class) types[0];
            data = newInstance(type);
        }

        return data;
    }

    private static void fillFields(Object data) throws Exception {

        Class clz = data.getClass();

        Field[] fields = clz.getDeclaredFields();

        for (Field field : fields) {

            if (java.lang.reflect.Modifier.isFinal(field.getModifiers())) {
                // System.out.println("字段："+field+"使用final修饰");
                continue;
            }

            field.setAccessible(true);
            Class fieldClz = field.getType();

            // 基础类型
            if (MockerConf.BASIC_CLASS.contains(fieldClz)) {
                field.set(data, genValueBasic(fieldClz));
            }
            // 数组
            else if (fieldClz.isArray()) {
                String clsName = fieldClz.toString().replace("class [L", "").replace(";", "");
                Object arrayInstance = arrayInstance(clsName);
                field.set(data, arrayInstance); 
                Object beanInstance = newInstance(clsName);
                Array.set(arrayInstance, 0, beanInstance);
                fillFields(beanInstance);
            }

            // 集合类
            else if (isCollection(fieldClz)) {
                Object collectionInstance = collectionInstance(fieldClz);
                field.set(data, collectionInstance);
                if (!fieldClz.equals(Map.class)) {

                    Type type = field.getGenericType();
                    if(type instanceof ParameterizedType)
                    {
                        Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                        Class type0 = (Class) types[0];
                        if(!MockerConf.BASIC_CLASS.contains(type0))
                        {
                            Object beanInstance = dataInstance(type);
                            pushToCollection(fieldClz, collectionInstance, beanInstance);
                            fillFields(beanInstance);
                        }
                    }
                }
            }

            // javaBean
            else {
                Object beanInstance = newInstance(fieldClz);
                field.set(data, beanInstance);
                fillFields(beanInstance);
            }
        }
    }

    private static Object genValueBasic(Class fieldClz) {

        Object obj = null;

        if (fieldClz.equals(String.class)) {
            obj = ValueGenerator.genString();
        }

        if (fieldClz.equals(Integer.class) || fieldClz.equals(int.class)) {
            obj = ValueGenerator.genInteger();
        }
        if (fieldClz.equals(Short.class) || fieldClz.equals(short.class)) {
            obj = ValueGenerator.genShort();
        }
        if (fieldClz.equals(Long.class) || fieldClz.equals(long.class)) {
            obj = ValueGenerator.genLong();
        }
        if (fieldClz.equals(Float.class) || fieldClz.equals(float.class)) {
            obj = ValueGenerator.genFloat();
        }
        if (fieldClz.equals(Double.class) || fieldClz.equals(double.class)) {
            obj = ValueGenerator.genDouble();
        }
        if (fieldClz.equals(Character.class) || fieldClz.equals(char.class)) {
            obj = ValueGenerator.genChar();
        }
        if (fieldClz.equals(Byte.class) || fieldClz.equals(byte.class)) {
            obj = ValueGenerator.genByte();
        }
        if (fieldClz.equals(Boolean.class) || fieldClz.equals(boolean.class)) {
            obj = ValueGenerator.genBoolean();
        }

        return obj;
    }

    private static boolean isCollection(Class cls) {

        if (MockerConf.MAP_CLASS.contains(cls) || MockerConf.SET_CLASS.contains(cls)
                || MockerConf.LIST_CLASS.contains(cls)) {
            return true;
        }

        return false;
    }

    private static void pushToCollection(Class collectionCls, Object collectionInstance, Object beanInstance)
            throws Exception {

        if (MockerConf.SET_CLASS.contains(collectionCls) || MockerConf.LIST_CLASS.contains(collectionCls)) {
            Method addMethod = collectionCls.getDeclaredMethod("add", Object.class);
            addMethod.invoke(collectionInstance, beanInstance);
        }
    }

    private static Object collectionInstance(Class cls) throws Exception {

        Object ret = null;
        if (cls.isInterface()) {
            if (cls.equals(List.class)) {
                ret = new ArrayList();
            }
            if (cls.equals(Set.class)) {
                ret = new HashSet();
            }
            if (cls.equals(Map.class)) {
                ret = new HashMap();
            }
        } else {
            ret = newInstance(cls);
        }

        return ret;
    }
    private static Object arrayInstance(String clsName) throws Exception {
        
        Object ret = Array.newInstance(Class.forName(clsName), 1);
            
        return ret;
    }

    public static void main(String[] args) throws Exception {
    }
}
