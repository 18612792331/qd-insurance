package com.qding.api.util;

import com.google.common.collect.Maps;
import com.qding.framework.common.api.struct.request.BaseRequest;

import java.util.Map;

/**
 * Created by jinhaishan on 17/4/19.
 */
public class ThreadLocalUtil {

    private static ThreadLocalUtil threadLocalUtil = new ThreadLocalUtil();

    private ThreadLocalUtil() {
    }

    private static final String BASEREQUEST_KEY = "baseRequest";

    private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>()
    {
        @Override
        protected Map<String, Object> initialValue() {
            return Maps.newHashMap();
        }
    };


    public static ThreadLocalUtil getInstance()
    {
        return threadLocalUtil;
    }

    public void put(String key, Object value)
    {
        threadLocal.get().put(key, value);
    }

    public Object get(String key)
    {
       return threadLocal.get().get(key);
    }

    public void remove()
    {
        threadLocal.get().clear();
    }

    public void putBaseRequest(BaseRequest baseRequest)
    {
        this.put(BASEREQUEST_KEY, baseRequest);
    }

    public BaseRequest getBaseRequest()
    {
        return (BaseRequest) this.get(BASEREQUEST_KEY);
    }

}
