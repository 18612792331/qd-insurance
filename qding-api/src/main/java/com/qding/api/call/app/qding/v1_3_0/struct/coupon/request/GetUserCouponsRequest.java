package com.qding.api.call.app.qding.v1_3_0.struct.coupon.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 获取用户所有可用优惠券(包含未生效的)		
 * @author lichao
 *
 */
@Validate
public class GetUserCouponsRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9162206036566619727L;
 
	@NotNullValidate
	private String memberId;
	
	public GetUserCouponsRequest() {

	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
