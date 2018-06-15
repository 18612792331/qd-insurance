package com.qding.api.call.app.qding.v1_4_0.struct.coupon.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * 查询我的优惠券(全部)					
 * @author lichao
 *
 */
@Validate
public class GetAllCouponsRequest extends BaseRequest{

	private static final long serialVersionUID = -9162206036566619727L;
 
	@NotNullValidate
	private String memberId;

	@NotNullValidate
	@ExplainAnnotation (explain = "1:已使用，2：未使用 ，3：已过期")
	private Integer status;

	@MinValueValidate(value="1")
	private Integer pageNo;

	@RangeValueValidate(max="20", min="1")
	private Integer pageSize;


	public GetAllCouponsRequest() {

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GetAllCouponsRequest [pageNo="+pageNo+", pageSize="+pageSize+",memberId=" + memberId + ", status=" + status
				+ ", toString()=" + super.toString() + "]";
	}
}
