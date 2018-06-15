package com.qding.api.call.app.qding.v1_3_0.struct.coupon.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 用户绑定优惠券					
 * @author lichao
 *
 */
@Validate
public class BindCouponsRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4690913675654016373L;

	@NotNullValidate
	private String memberId;
	
	@NotNullValidate
	private String couponsCode;
	
	public BindCouponsRequest() {

	}

	public BindCouponsRequest(String memberId, String couponsCode) {
		super();
		this.memberId = memberId;
		this.couponsCode = couponsCode;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setCouponsCode(String couponsCode) {
		this.couponsCode = couponsCode;
	}
	
	public String getCouponsCode() {
		return couponsCode;
	}

	@Override
	public String toString() {
		return "BindCouponsRequest [memberId=" + memberId + ", couponsCode="
				+ couponsCode + ", toString()=" + super.toString() + "]";
	}

	
}
