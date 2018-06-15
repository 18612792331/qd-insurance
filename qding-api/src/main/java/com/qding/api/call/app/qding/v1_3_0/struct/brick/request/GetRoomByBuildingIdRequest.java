package com.qding.api.call.app.qding.v1_3_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * 根据楼栋id获取房间列表					
 * @author lichao
 *
 */
@Validate
public class GetRoomByBuildingIdRequest extends BaseRequest{

	private static final long serialVersionUID = 7859434334817858875L;

	@NotNullValidate
	@ExplainAnnotation(explain = "楼栋ID")
	private String buildingId;

	@ExplainAnnotation(explain = "房间号")
	private String roomNum = String.valueOf("");

	@ExplainAnnotation(explain = "每页查询数")
	private Integer pageSize = 0;

	@ExplainAnnotation(explain = "当前查询页码")
	private Integer pageNo = 1;


	public GetRoomByBuildingIdRequest() {
	}
	
	public GetRoomByBuildingIdRequest(String buildingId) {
		super();
		this.buildingId = buildingId;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public String toString() {
		return "GetRoomByBuildingIdRequest{" +
				"buildingId='" + buildingId + '\'' +
				", pageSize=" + pageSize +
				", pageNo=" + pageNo +
				'}';
	}
}
