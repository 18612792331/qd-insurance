package com.qding.insurance.rpc.response;

import java.util.List;

import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.insurance.vo.RoomInsureStatusInfo;

public class RoomInsureStatusInfoResponse extends BaseResponse {

	private static final long serialVersionUID = -5976337420684328241L;
	// 房屋投保状态
	private List<RoomInsureStatusInfo> roomInsureStatusInfoList;

	public List<RoomInsureStatusInfo> getRoomInsureStatusInfoList() {
		return roomInsureStatusInfoList;
	}

	public void setRoomInsureStatusInfoList(List<RoomInsureStatusInfo> roomInsureStatusInfoList) {
		this.roomInsureStatusInfoList = roomInsureStatusInfoList;
	}

	

}
