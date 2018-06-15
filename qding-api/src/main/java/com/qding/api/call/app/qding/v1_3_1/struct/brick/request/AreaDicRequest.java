package com.qding.api.call.app.qding.v1_3_1.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2016/1/6.
 */
public class AreaDicRequest extends BaseRequest {

    private static final long serialVersionUID = -90960485649560297L;

    @ExplainAnnotation (explain = "缓存校验key")
    private String cacheKey;

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
