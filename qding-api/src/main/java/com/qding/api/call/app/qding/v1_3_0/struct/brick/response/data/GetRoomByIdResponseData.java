package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.Room;
import com.qding.api.struct.ResponseData;

/**
 * 根据房间id获取房间信息					
 * @author lichao
 *
 */
public class GetRoomByIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9084563141169244207L;

	private Room entity;
	
	public GetRoomByIdResponseData() {

	}

	public GetRoomByIdResponseData(Room entity) {
		super();
		this.entity = entity;
	}

	public Room getEntity() {
		return entity;
	}

	public void setEntity(Room entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "GetRoomByIdResponseData [entity=" + entity + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
