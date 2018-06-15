package com.qding.api.call.app.qding.v2_3_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/5/25.
 */
public class GetServiceUrlByShortUrlResponseData extends ResponseData {

    private static final long serialVersionUID = 1044842413266021551L;

    @ExplainAnnotation(explain = "服务地址")
    private String serviceUrl;

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public GetServiceUrlByShortUrlResponseData() {
    }

    @Override
    public String toString() {
        return "ShortToLongForUrlResponseData{" +
                "serviceUrl='" + serviceUrl + '\'' +
                '}';
    }
}
