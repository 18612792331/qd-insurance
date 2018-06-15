package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.struct.ResponseData;

public class GetProjectConcatsResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8625395025104499494L;

	private List<ProjectConcat> list;
	
	public GetProjectConcatsResponseData() {

	}

	public GetProjectConcatsResponseData(List<ProjectConcat> list) {
		super();
		this.list = list;
	}

	public List<ProjectConcat> getList() {
		return list;
	}

	public void setList(List<ProjectConcat> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetProjectConcatsResponseData [list=" + list + ", toString()="
				+ super.toString() + "]";
	}
	
}
