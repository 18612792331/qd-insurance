package com.qding.api.call.app.qding.v1_3_0.struct.brick.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 根据楼栋id获取楼栋信息					
 * @author lichao
 *
 */
@Validate
public class GetBuildingByIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 469780697995697970L;

	@NotNullValidate
	private String buildingId;
	
	public GetBuildingByIdRequest() {
	}

	public GetBuildingByIdRequest(String buildingId) {
		super();
		this.buildingId = buildingId;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	@Override
	public String toString() {
		return "GetBuildingByIdRequest [buildingId=" + buildingId
				+ ", toString()=" + super.toString() + "]";
	}
}
