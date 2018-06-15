package com.qding.api.call.app.qding.v2_5_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.brick.Project;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/8/22.
 */
public class GetProjectListByDistrictIdResponseData extends ResponseData {

    private static final long serialVersionUID = 3184405191292631988L;

    @ExplainAnnotation (explain = "社区列表")
    private List<Project> list;

    public List<Project> getList() {
        return list;
    }

    public void setList(List<Project> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetProjectListByDistrictIdResponseData{" +
                "list=" + list +
                '}';
    }
}
