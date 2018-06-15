package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/9/10.
 */
@Validate
public class GetReportEvaluateRequest  extends BaseRequest {


    private static final long serialVersionUID = -1701626412784392268L;


    @NotNullValidate
    private String reportId;

    public GetReportEvaluateRequest() {
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    @Override
    public String toString() {
        return "GetReportEvaluateRequest [reportId=" + reportId
                + ", toString()=" + super.toString() + "]";
    }

}
