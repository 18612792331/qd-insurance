package com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.Report;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/1/16.
 */
public class GetMyReportByIdResponseData extends ResponseData {

    private static final long serialVersionUID = -5596291273783898903L;

    @ExplainAnnotation(explain = "报事信息")
    private Report entity;

    public Report getEntity() {
        return entity;
    }

    public void setEntity(Report entity) {
        this.entity = entity;
    }
}
