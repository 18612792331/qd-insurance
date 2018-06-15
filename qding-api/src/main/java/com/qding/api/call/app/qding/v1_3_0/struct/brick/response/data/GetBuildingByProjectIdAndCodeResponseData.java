package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.Building;
import com.qding.api.struct.ResponseData;

/**
 * 根据社区id和组团code获取楼栋列表					
 * @author lichao
 *
 */
public class GetBuildingByProjectIdAndCodeResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6126400822733272476L;

	private List<Building> list;
	
	public GetBuildingByProjectIdAndCodeResponseData() {
	}

	public GetBuildingByProjectIdAndCodeResponseData(List<Building> list) {
		super();
		this.list = list;
	}

	public List<Building> getList() {
		return list;
	}

	public void setList(List<Building> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetBuildingByProjectIdAndCodeRequest [list=" + list
				+ ", toString()=" + super.toString() + "]";
	}
}
