package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Room;
import com.qding.api.struct.ResponseData;

/**
 * 根据楼栋id获取房间列表					
 * @author lichao
 *
 */
public class GetRoomByBuildingIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3822177699987546757L;

	@ExplainAnnotation (explain = "房间列表")
	private List<Room> list;

	@ExplainAnnotation (explain = "总计路数")
	private int totalCount;
	
	public GetRoomByBuildingIdResponseData() {
	}

	public GetRoomByBuildingIdResponseData(List<Room> list) {
		super();
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String toString() {
		return "GetRoomByBuildingIdResponseData [list=" + list
				+ ", toString()=" + super.toString() + "]";
	}

	public List<Room> getList() {
		return list;
	}

	public void setList(List<Room> list) {
		this.list = list;
	}
}
