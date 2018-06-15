package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.user.Invitation;
import com.qding.api.struct.ResponseData;

public class InviteUserResponseData   extends ResponseData {

	
	private static final long serialVersionUID = 4429414187875734822L;

	public InviteUserResponseData() {
	
	}
	
	/**
     * 邀请信息对象
     */
	private Invitation entity;

	public Invitation getEntity() {
		return entity;
	}

	public void setEntity(Invitation entity) {
		this.entity = entity;
	}

	public InviteUserResponseData(Invitation entity) {
		super();
		this.entity = entity;
	}

	@Override
    public String toString() {
        return "InviteUserResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }

}
