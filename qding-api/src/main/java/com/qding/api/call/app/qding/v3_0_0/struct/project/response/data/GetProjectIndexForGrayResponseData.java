package com.qding.api.call.app.qding.v3_0_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.project.ProjectIndexForGray;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/16.
 */
public class GetProjectIndexForGrayResponseData extends ResponseData {

    private static final long serialVersionUID = 5662979458412059235L;

    @ExplainAnnotation (explain = "社区首页")
    private ProjectIndexForGray entity;

    public ProjectIndexForGray getEntity() {
        return entity;
    }

    public void setEntity(ProjectIndexForGray entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetProjectIndexForGrayResponseData{" +
                "entity=" + entity +
                '}';
    }
}
