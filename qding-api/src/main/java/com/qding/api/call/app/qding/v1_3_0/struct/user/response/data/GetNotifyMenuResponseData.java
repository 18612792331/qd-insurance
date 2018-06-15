package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.struct.ResponseData;

public class GetNotifyMenuResponseData extends ResponseData{

	
	private static final long serialVersionUID = -79115858052401738L;
	
	/**
     * 业态信息列表
     */
	private List<ProjectService> list;
	
	public GetNotifyMenuResponseData() {

	}

	public GetNotifyMenuResponseData(List<ProjectService> list) {
		super();
		this.list = list;
	}

	public List<ProjectService> getList() {
		return list;
	}

	public void setList(List<ProjectService> list) {
		this.list = list;
	}
	
	   
    @Override
    public String toString() {
        return "GetNotifyMenuResponseData [list="+list+",toString()=" + super.toString() + "]";
    }
}
