package com.qding.api.call.app.qding.v2_0_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.ProjectAround;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/7/6.
 */
public class GetProjectAroundInfoByIdResponseData extends ResponseData {

    private static final long serialVersionUID = 4297798293061145170L;

    @ExplainAnnotation (explain = "周边商户信息列表")
    private ProjectAround  entity;

    public ProjectAround getEntity() {
        return entity;
    }

    public void setEntity(ProjectAround entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetProjectAroundInfoByIdResponseData{" +
                "entity=" + entity +
                '}';
    }
}
