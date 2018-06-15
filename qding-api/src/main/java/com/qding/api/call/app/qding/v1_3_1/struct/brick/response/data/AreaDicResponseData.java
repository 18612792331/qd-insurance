package com.qding.api.call.app.qding.v1_3_1.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/1/6.
 */
public class AreaDicResponseData extends ResponseData {

    private static final long serialVersionUID = 8799696850676425102L;

    @ExplainAnnotation (explain = "省市区县json数组串")
    private String areaJsonStr;

    @ExplainAnnotation (explain = "缓存校验key")
    private String cacheKey;

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public String getAreaJsonStr() {
        return areaJsonStr;
    }

    public void setAreaJsonStr(String areaJsonStr) {
        this.areaJsonStr = areaJsonStr;
    }

    @Override
    public String toString() {
        return "AreaDicResponseData{" +
                "areaJsonStr='" + areaJsonStr + '\'' +
                ", cacheKey='" + cacheKey + '\'' +
                '}';
    }
}
