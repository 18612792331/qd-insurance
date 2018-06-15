package com.qding.api.call.app.qding.v2_5_0.struct.brick.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.brick.RoomAddress;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/11/22.
 */
public class GetRoomAddressResponseData extends ResponseData {

    private static final long serialVersionUID = -598202159884900510L;

    @ExplainAnnotation (explain = "room地址列表")
    private List<RoomAddress> list;
    
    public List<RoomAddress> getList() {
		return list;
	}

	public void setList(List<RoomAddress> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetRoomAddressResponseData [list=" + list + "]";
	}

	
    
}
