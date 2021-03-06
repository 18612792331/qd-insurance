package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.user.ProjectRoom;
import com.qding.api.struct.ResponseData;

public class GetProjectRoomsResponseData extends ResponseData{

	private static final long serialVersionUID = -6193052733382986976L;
	
	/**
     * 房屋信息列表
     */
	private List<ProjectRoom> list;

	public GetProjectRoomsResponseData(List<ProjectRoom> list) {
		super();
		this.list = list;
	}
	
	public GetProjectRoomsResponseData() {

	}

	public List<ProjectRoom> getList() {
		return list;
	}

	public void setList(List<ProjectRoom> list) {
		this.list = list;
	}
	
	@Override
    public String toString() {
        return "GetProjectRoomsResponseData [list="+list+",toString()=" + super.toString() + "]";
    }
	
}
