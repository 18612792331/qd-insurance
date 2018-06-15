package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Room;
import com.qding.api.call.app.qding.v1_3_0.struct.user.MemberRoom;
import com.qding.api.struct.ResponseData;

public class GetAllMemberByRoomResponseData   extends ResponseData{

	
	private static final long serialVersionUID = -3129327149991004472L;

	public GetAllMemberByRoomResponseData() {
	}
	
	@ExplainAnnotation (explain = "房间绑定用户信息列表")
	private List<MemberRoom> list;

	@ExplainAnnotation (explain = "房间信息")
	private Room room;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<MemberRoom> getList() {
		return list;
	}

	public void setList(List<MemberRoom> list) {
		this.list = list;
	}
	
	@Override
    public String toString() {
        return "GetAllMemberByRoomResponseData [list="+list+",toString()=" + super.toString() + "]";
    }

}
