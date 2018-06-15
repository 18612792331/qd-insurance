package com.qding.api.call.app.qding.v4_0_0.struct.chatRecord.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v4_0_0.struct.project.ProjectIndex;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/2/23.
 */
public class GetProjectIndexResponseData extends ResponseData {

    private static final long serialVersionUID = 5490879723819004114L;

    @ExplainAnnotation(explain = "社区首页")
    private ProjectIndex entity;


    public ProjectIndex getEntity() {
		return entity;
	}

	public void setEntity(ProjectIndex entity) {
		this.entity = entity;
	}



	@Override
    public String toString() {
        return "GetProjectIndexResponseData{" +
                "entity=" + entity +
                '}';
    }
}
