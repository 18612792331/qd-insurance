package com.qding.api.call.app.qding.v3_2_0.struct.coupon.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * 查询我的优惠券
 * @author lichao
 *
 */
@Validate
public class GetCouponsBymidRequest extends BaseRequest{

	private static final long serialVersionUID = -9162206036566619727L;

	@NotNullValidate
	@ExplainAnnotation (explain = "0：全部，1:已使用，2：未使用 ，3：已过期")
	private Integer status;

	@MinValueValidate(value="1")
	private Integer pageNo;

	@RangeValueValidate(max="20", min="1")
	private Integer pageSize;


	public GetCouponsBymidRequest() {

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GetAllCouponsRequest [pageNo="+pageNo+", pageSize="+pageSize+", status=" + status
				+ ", toString()=" + super.toString() + "]";
	}
}
