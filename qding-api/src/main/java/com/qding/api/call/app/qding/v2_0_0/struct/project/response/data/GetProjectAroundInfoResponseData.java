package com.qding.api.call.app.qding.v2_0_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.ProjectAround;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/7/6.
 */
public class GetProjectAroundInfoResponseData extends ResponseData {

    private static final long serialVersionUID = 4297798293061145170L;

    @ExplainAnnotation (explain = "周边商户信息列表")
    private List<ProjectAround> projectAroundDataList;

    @ExplainAnnotation (explain = "总记录数")
    private int totalCount;

    public List<ProjectAround> getProjectAroundDataList() {
        return projectAroundDataList;
    }

    public void setProjectAroundDataList(List<ProjectAround> projectAroundDataList) {
        this.projectAroundDataList = projectAroundDataList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GetProjectAroundInfoResponseData{" +
                "projectAroundDataList=" + projectAroundDataList +
                ", totalCount=" + totalCount +
                '}';
    }
}
