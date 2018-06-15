package com.qding.api.call.app.qding.v3_3_0.struct.realestate.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Create by jinhaishan on 17/12/7
 **/
public class GetPackageNoticeDetailRequest extends BaseRequest {

    private static final long serialVersionUID = 2447162025294067675L;
    @ExplainAnnotation(explain = "入住通知包裹ID")
    private Integer packageNoticeId;

    public Integer getPackageNoticeId() {
        return packageNoticeId;
    }

    public void setPackageNoticeId(Integer packageNoticeId) {
        this.packageNoticeId = packageNoticeId;
    }

    @Override
    public String toString() {
        return "GetPackageNoticeDetailRequest{" +
                "packageNoticeId='" + packageNoticeId + '\'' +
                '}';
    }
}
