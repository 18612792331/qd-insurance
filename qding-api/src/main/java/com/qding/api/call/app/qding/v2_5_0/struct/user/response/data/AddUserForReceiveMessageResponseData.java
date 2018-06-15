package com.qding.api.call.app.qding.v2_5_0.struct.user.response.data;

import com.qding.api.call.app.qding.v2_5_0.struct.user.Addresses;
import com.qding.api.struct.ResponseData;

public class AddUserForReceiveMessageResponseData extends ResponseData {

	
	private static final long serialVersionUID = -5985716698318380135L;
	
	/**
     * 地址信息对象
     */
	private Addresses entity;

	public AddUserForReceiveMessageResponseData() {
	}

	public Addresses getEntity() {
		return entity;
	}

	public void setEntity(Addresses entity) {
		this.entity = entity;
	}

	public AddUserForReceiveMessageResponseData(Addresses entity) {
		super();
		this.entity = entity;
	}

	@Override
    public String toString() {
        return "AddUserForReceiveMessageResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }

}
