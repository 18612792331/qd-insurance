package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;


import com.qding.api.call.app.qding.v1_3_0.struct.user.Invitation;
import com.qding.api.struct.ResponseData;

public class GetInvitationCodeIsActivateResponseData   extends ResponseData{

	
	private static final long serialVersionUID = -6691072265796837919L;
	
	/**
     * 邀请信息对象
     */
	private Invitation entity;
	

	/**
	 * @return the entity
	 */
	public Invitation getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Invitation entity) {
		this.entity = entity;
	}

	public GetInvitationCodeIsActivateResponseData() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GetInvitationCodeIsActivateResponseData [toString()=" + super.toString()
				+ "]";
	}
}
