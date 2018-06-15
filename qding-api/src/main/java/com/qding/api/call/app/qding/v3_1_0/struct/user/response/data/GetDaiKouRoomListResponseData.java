package com.qding.api.call.app.qding.v3_1_0.struct.user.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_0.struct.user.RoomDto;
import com.qding.api.struct.ResponseData;


public class GetDaiKouRoomListResponseData extends ResponseData {

    private static final long serialVersionUID = 2409085836086098452L;
   
    @ExplainAnnotation(explain = "代扣物业费房屋列表")
	private List<RoomDto> list;

	public List<RoomDto> getList() {
		return list;
	}

	public void setList(List<RoomDto> list) {
		this.list = list;
	}
    
    
    
    

}
