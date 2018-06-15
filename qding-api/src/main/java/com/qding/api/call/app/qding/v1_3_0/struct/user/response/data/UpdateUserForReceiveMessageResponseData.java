package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.struct.ResponseData;

public class UpdateUserForReceiveMessageResponseData   extends ResponseData {

	
	private static final long serialVersionUID = -7210986448538704537L;

	public UpdateUserForReceiveMessageResponseData() {
		// TODO Auto-generated constructor stub
	}
	
	@ExplainAnnotation (explain = "收货地址")
	private Addresses  entity;

	public Addresses getEntity() {
		return entity;
	}

	public void setEntity(Addresses entity) {
		this.entity = entity;
	}

	public UpdateUserForReceiveMessageResponseData(Addresses entity) {
		super();
		this.entity = entity;
	}
	
    @Override
    public String toString() {
        return "UpdateUserForReceiveMessageResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }
	    

}
