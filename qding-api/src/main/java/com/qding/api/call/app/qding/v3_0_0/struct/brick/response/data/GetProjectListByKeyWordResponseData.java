package com.qding.api.call.app.qding.v3_0_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Project;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetProjectListByKeyWordResponseData extends ResponseData{

    private static final long serialVersionUID = 2611595122892670271L;

    @ExplainAnnotation(explain = "社区列表")
    private List<Project> list;

    public List<Project> getList() {
        return list;
    }

    public void setList(List<Project> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetProjectListByKeyWordResponseData{" +
                "list=" + list +
                '}';
    }
}
