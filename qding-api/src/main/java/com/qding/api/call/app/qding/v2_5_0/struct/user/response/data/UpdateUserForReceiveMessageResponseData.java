package com.qding.api.call.app.qding.v2_5_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.user.Addresses;
import com.qding.api.struct.ResponseData;

public class UpdateUserForReceiveMessageResponseData extends ResponseData {

	
	private static final long serialVersionUID = -7210986448538704537L;

	public UpdateUserForReceiveMessageResponseData() {
	}
	
	@ExplainAnnotation (explain = "收货地址")
	private Addresses  entity;
	
	@ExplainAnnotation (explain = "如果true则业务处理失败，为组团特别说明")
	private boolean  isNeedGroupId=false;
	

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
	
    public boolean isNeedGroupId() {
		return isNeedGroupId;
	}

	public void setNeedGroupId(boolean isNeedGroupId) {
		this.isNeedGroupId = isNeedGroupId;
	}

	@Override
    public String toString() {
        return "UpdateUserForReceiveMessageResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }
	    

}
