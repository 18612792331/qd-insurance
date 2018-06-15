package com.qding.api.call.app.qding.v3_1_0.struct.user;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

public class RoomDto implements Serializable{
	
	@ExplainAnnotation(explain = "房间id")
	private String roomId;
	
	@ExplainAnnotation(explain = "房屋描述、形如东区 - 6栋 - 5单元1803")
	private String roomDes;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomDes() {
		return roomDes;
	}

	public void setRoomDes(String roomDes) {
		this.roomDes = roomDes;
	}
	
	
	
}
