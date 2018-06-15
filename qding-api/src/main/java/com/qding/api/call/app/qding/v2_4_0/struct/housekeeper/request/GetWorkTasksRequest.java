package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2016/6/14.
 */
public class GetWorkTasksRequest extends BaseRequest {

    private static final long serialVersionUID = -9119846379218328824L;

    @ExplainAnnotation (explain = "报事ID")
    private String reportId;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    @Override
    public String toString() {
        return "GetWorkTasksRequest{" +
                "reportId='" + reportId + '\'' +
                '}';
    }
}
