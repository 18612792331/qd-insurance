package com.qding.api.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import net.sf.cglib.beans.BeanCopier;

import java.util.concurrent.ConcurrentMap;

/**
 * Create by jinhaishan on 17/10/20
 * 用于实体对象转换，只支持同名属性的值拷贝，不同名属性请手动处理
 **/
public class ConvertUtil {

    private static ConcurrentMap<String, BeanCopier> beanCopierMap = Maps.newConcurrentMap();

    public static <T> T copy(Object source, Class<T> target) {
        Preconditions.checkNotNull(source);
        BeanCopier beanCopier = getBeanCopier(source.getClass(), target);
        T instance = null;
        try {
            instance = target.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        beanCopier.copy(source, instance, null);
        return instance;
    }

    public static <T> void copy(Object source, T target) {
        Preconditions.checkNotNull(source);
        BeanCopier beanCopier = getBeanCopier(source.getClass(), target.getClass());
        beanCopier.copy(source, target, null);
    }

    private static <T> BeanCopier getBeanCopier(Class<?> sourceClass, Class<T> targetClass) {
        String key = sourceClass.getName() + "@" + targetClass.getName();
        BeanCopier beanCopier = beanCopierMap.get(key);
        if(beanCopier == null)
        {
            beanCopier = BeanCopier.create(sourceClass, targetClass, false);
            beanCopierMap.putIfAbsent(key, beanCopier);
        }
        return beanCopier;
    }

}
