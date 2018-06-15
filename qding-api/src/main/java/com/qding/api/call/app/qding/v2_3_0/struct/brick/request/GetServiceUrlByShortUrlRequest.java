package com.qding.api.call.app.qding.v2_3_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2016/5/25.
 */
public class GetServiceUrlByShortUrlRequest extends BaseRequest {

    private static final long serialVersionUID = -7293578484500775732L;

    @ExplainAnnotation(explain = "待转换长连接地址")
    private String convertUrl;


    public String getConvertUrl() {
        return convertUrl;
    }

    public void setConvertUrl(String convertUrl) {
        this.convertUrl = convertUrl;
    }

    public GetServiceUrlByShortUrlRequest() {
    }

    public GetServiceUrlByShortUrlRequest(String convertUrl) {
        this.convertUrl = convertUrl;
    }
}
