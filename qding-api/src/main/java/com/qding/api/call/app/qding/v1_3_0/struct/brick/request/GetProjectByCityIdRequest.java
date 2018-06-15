package com.qding.api.call.app.qding.v1_3_0.struct.brick.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 根据城市id获取社区列表					
 * @author lichao
 *
 */
@Validate
public class GetProjectByCityIdRequest extends BaseRequest{

	private static final long serialVersionUID = 8783719634823278835L;

	@NotNullValidate
	private String cityId;

	private String memberId;
	
	public GetProjectByCityIdRequest() {

	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
}
