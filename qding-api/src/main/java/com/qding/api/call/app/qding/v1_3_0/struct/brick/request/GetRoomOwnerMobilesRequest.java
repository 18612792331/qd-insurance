package com.qding.api.call.app.qding.v1_3_0.struct.brick.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetRoomOwnerMobilesRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8313924356220607331L;

	@NotNullValidate
	private String roomId;
	
	public GetRoomOwnerMobilesRequest() {

	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
}
