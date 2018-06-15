package com.qding.api.call.app.qding.v2_3_0.struct.watch.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

public class CheckIMEIRequest extends BaseRequest {

    private static final long serialVersionUID = 4162413891394174146L;

    @ExplainAnnotation(explain = "手表设备码")
    private String imei;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        return "ScanWatchCodeRequest{" +
                "imei='" + imei + '\'' +
                '}';
    }
}
