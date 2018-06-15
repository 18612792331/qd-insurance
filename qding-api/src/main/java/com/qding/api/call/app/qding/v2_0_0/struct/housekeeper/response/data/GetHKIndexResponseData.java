package com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.response.data;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.brick.ProjectServiceList;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetHKIndexResponseData extends ResponseData {

    private static final long serialVersionUID = 8505693784159846717L;

    @ExplainAnnotation(explain = "管家首页服务列表")
    private List<ProjectServiceList> projectServiceList;

    @ExplainAnnotation(explain = "管家电话")
    private List<String> phones = Lists.newArrayList();

    public GetHKIndexResponseData() {

    }

    public GetHKIndexResponseData(List<ProjectServiceList> projectServiceList, List<String> phones) {
        this.projectServiceList = projectServiceList;
        this.phones = phones;
    }

    public List<ProjectServiceList> getProjectServiceList() {
        return projectServiceList;
    }

    public void setProjectServiceList(List<ProjectServiceList> projectServiceList) {
        this.projectServiceList = projectServiceList;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "GetHKIndexResponseData{" +
                "projectServiceList=" + projectServiceList +
                '}';
    }
}
