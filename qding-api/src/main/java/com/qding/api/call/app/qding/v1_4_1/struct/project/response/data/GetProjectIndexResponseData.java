package com.qding.api.call.app.qding.v1_4_1.struct.project.response.data;

import com.qding.api.call.app.qding.v1_4_1.struct.project.ProjectIndex;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/10/30.
 */
public class GetProjectIndexResponseData extends ResponseData {

    private static final long serialVersionUID = -2836564270040337555L;

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
}
