package com.qding.api.call.app.qding.v2_0_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.ProjectIndex;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/12/15.
 */
public class GetProjectIndexResponseData  extends ResponseData {

    private static final long serialVersionUID = -2884008794120398607L;

    @ExplainAnnotation(explain = "社区首页")
    private ProjectIndex entity;

    public GetProjectIndexResponseData() {

    }

    public GetProjectIndexResponseData(ProjectIndex entity) {
        super();
        this.entity = entity;
    }

    public ProjectIndex getEntity() {
        return entity;
    }

    public void setEntity(ProjectIndex entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetProjectIndexResponseData [entity=" + entity +",toString="+ super.toString()+"]";
    }
}
