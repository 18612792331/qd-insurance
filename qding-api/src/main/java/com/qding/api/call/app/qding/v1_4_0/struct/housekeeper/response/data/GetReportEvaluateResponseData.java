package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.response.data;

import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.ReportEvaluate;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/9/10.
 */
public class GetReportEvaluateResponseData   extends ResponseData {


    private static final long serialVersionUID = -8676032743829436478L;

    /**
     * 评价信息
     */
    private ReportEvaluate entity;


    public ReportEvaluate getEntity() {
        return entity;
    }

    public void setEntity(ReportEvaluate entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetReportEvaluateResponseData [toString()=" + super.toString()
                + "]";
    }




}
