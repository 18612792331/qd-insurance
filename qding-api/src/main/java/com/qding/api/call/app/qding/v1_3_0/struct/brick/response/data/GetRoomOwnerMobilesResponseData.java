package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import com.qding.api.struct.ResponseData;

public class GetRoomOwnerMobilesResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7682038993692830574L;

	private String[] list;
	
	public GetRoomOwnerMobilesResponseData() {

	}
	
	public GetRoomOwnerMobilesResponseData(String[] list) {
		super();
		this.list = list;
	}

	public String[] getList() {
		return list;
	}
	
	public void setList(String[] list) {
		this.list = list;
	}
	
}
