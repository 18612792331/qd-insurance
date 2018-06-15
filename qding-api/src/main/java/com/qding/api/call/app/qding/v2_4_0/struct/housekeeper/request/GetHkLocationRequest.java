package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/6/8.
 */
public class GetHkLocationRequest extends BaseRequest {

    private static final long serialVersionUID = -4366007044921974379L;

    @NotNullValidate
    @ExplainAnnotation(explain = "报事ID")
    private String reportId;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    @Override
    public String toString() {
        return "GetHkLocationRequest{" +
                "reportId='" + reportId + '\'' +
                '}';
    }
}
