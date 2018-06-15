package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class SmartBindOwnerRoomRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5199071583802494921L;

	@NotNullValidate
	private String memberId;
	
	public SmartBindOwnerRoomRequest() {

	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
}
