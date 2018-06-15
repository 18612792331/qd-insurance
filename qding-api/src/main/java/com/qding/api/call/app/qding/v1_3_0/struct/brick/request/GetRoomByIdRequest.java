package com.qding.api.call.app.qding.v1_3_0.struct.brick.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 根据房间id获取房间信息					
 * @author lichao
 *
 */
@Validate
public class GetRoomByIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3375850667417889421L;

	@NotNullValidate
	private String roomId;
	
	public GetRoomByIdRequest() {

	}

	public GetRoomByIdRequest(String roomId) {
		super();
		this.roomId = roomId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	@Override
	public String toString() {
		return "GetRoomByIdRequest [roomId=" + roomId + ", toString()="
				+ super.toString() + "]";
	}
	
}
