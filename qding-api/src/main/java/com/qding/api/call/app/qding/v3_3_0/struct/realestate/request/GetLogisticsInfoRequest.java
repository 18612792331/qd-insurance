package com.qding.api.call.app.qding.v3_3_0.struct.realestate.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Create by jinhaishan on 17/12/7
 **/
public class GetLogisticsInfoRequest extends BaseRequest {

    private static final long serialVersionUID = 2447162025294067675L;
    @ExplainAnnotation(explain = "快递单号")
    private String packageNumber;

    @ExplainAnnotation(explain = "快递公司")
    private String packageName;

    public String getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return "GetLogisticsInfoRequest{" +
                "packageNumber='" + packageNumber + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
