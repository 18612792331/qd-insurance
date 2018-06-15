package com.qding.api.call.app.qding.v1_3_0.struct.coupon.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 查询我的优惠券(全部)					
 * @author lichao
 *
 */
@Validate
public class GetAllCouponsRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9162206036566619727L;
 
	@NotNullValidate
	private String memberId;
	
	@NotNullValidate
	private Integer status;
	
	public GetAllCouponsRequest() {

	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
