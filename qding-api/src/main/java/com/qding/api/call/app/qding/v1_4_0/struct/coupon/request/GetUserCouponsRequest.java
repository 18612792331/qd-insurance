package com.qding.api.call.app.qding.v1_4_0.struct.coupon.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * 获取用户所有可用优惠券(包含未生效的)		
 * @author lichao
 *
 */
@Validate
public class GetUserCouponsRequest extends BaseRequest{

	private static final long serialVersionUID = -9162206036566619727L;
 
	@NotNullValidate
	private String memberId;

	@MinValueValidate(value="1")
	private Integer pageNo;

	@RangeValueValidate(max="20", min="1")
	private Integer pageSize;
	
	public GetUserCouponsRequest() {

	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	@Override
	public String toString() {
		return "GetOrderCouponsRequest [memberId=" + memberId
				+ ", toString()=" + super.toString() + "]";
	}
}
