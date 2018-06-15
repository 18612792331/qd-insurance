package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.ReportTaskBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/6/14.
 */
public class GetWorkTasksResponseData extends ResponseData{

    private static final long serialVersionUID = -6338845506937444493L;

    @ExplainAnnotation (explain = "报事工单列表")
    private List<ReportTaskBean> list;

    public List<ReportTaskBean> getList() {
        return list;
    }

    public void setList(List<ReportTaskBean> list) {
        this.list = list;
    }
}
