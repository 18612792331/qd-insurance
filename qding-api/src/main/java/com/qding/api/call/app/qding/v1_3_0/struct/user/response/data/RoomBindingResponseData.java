package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.user.ProjectRoom;
import com.qding.api.struct.ResponseData;

public class RoomBindingResponseData   extends ResponseData {

	
	private static final long serialVersionUID = -6541928675151896742L;

	/**
     * 访问信息对象
     */
	private ProjectRoom entity;
	
	public void setEntity(ProjectRoom entity) {
		this.entity = entity;
	}
	
	public ProjectRoom getEntity() {
		return entity;
	}
	
	public RoomBindingResponseData() {
	}

	public RoomBindingResponseData(ProjectRoom entity) {
		super();
		this.entity = entity;
	}

	@Override
    public String toString() {
        return "RoomBindingResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }
}
