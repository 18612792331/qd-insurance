package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */
@Validate
public class ReportGroupRequest  extends BaseRequest {

    private static final long serialVersionUID = 6451241466532617772L;

    @NotNullValidate
    private  String gcRoomId;

    @NotNullValidate
    private String userId;

    @NotNullValidate
    private String reportType;

    @NotNullValidate
    private String optAt;

    public String getOptAt() {
        return optAt;
    }

    public void setOptAt(String optAt) {
        this.optAt = optAt;
    }

    public String getGcRoomId() {
        return gcRoomId;
    }

    public void setGcRoomId(String gcRoomId) {
        this.gcRoomId = gcRoomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    @Override
    public String toString() {
        return "ReportGroupRequest [optAt="+optAt+",gcRoomId=" + gcRoomId +",userId="+userId+",reportType="+reportType +
                " ,super.toString() ]";
    }
}
