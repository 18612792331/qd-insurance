package com.qding.api.call.app.qding.v2_5_0.struct.user.response.data;

import com.qding.api.call.app.qding.v2_5_0.struct.user.Addresses;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetUserForReceiveMessageResponseData extends ResponseData {

	
	private static final long serialVersionUID = -6501286591991040682L;

	public GetUserForReceiveMessageResponseData() {
	}
	
	/**
     * 地址信息列表
     */
	private List<Addresses> list;

	public List<Addresses> getList() {
		return list;
	}

	public void setList(List<Addresses> list) {
		this.list = list;
	}

	public GetUserForReceiveMessageResponseData(List<Addresses> list) {
		super();
		this.list = list;
	}
	
	@Override
    public String toString() {
        return "GetUserForReceiveMessageResponseData [list="+list+",toString()=" + super.toString() + "]";
    }
	

}
