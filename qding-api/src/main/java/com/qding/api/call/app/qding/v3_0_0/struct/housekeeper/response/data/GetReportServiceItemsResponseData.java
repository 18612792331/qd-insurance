package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.ProjectService;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/7/25.
 */
public class GetReportServiceItemsResponseData extends ResponseData {

    private static final long serialVersionUID = -175468724447771370L;

    @ExplainAnnotation(explain = "报事服务列表")
    private List<ProjectService> list;

    public List<ProjectService> getServices() {
        return list;
    }

    public void setServices(List<ProjectService> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetReportServiceItemsResponseData{" +
                "list=" + list +
                '}';
    }
}
