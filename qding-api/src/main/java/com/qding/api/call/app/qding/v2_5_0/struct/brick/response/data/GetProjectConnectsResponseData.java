package com.qding.api.call.app.qding.v2_5_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.brick.ProjectConnect;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/11/22.
 */
public class GetProjectConnectsResponseData extends ResponseData {

    @ExplainAnnotation (explain = "社区物业自提地址列表")
    private List<ProjectConnect> list;

    public List<ProjectConnect> getList() {
        return list;
    }

    public void setList(List<ProjectConnect> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetProjectConnectsResponseData{" +
                "list=" + list +
                '}';
    }
}
