package com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MaxValueValidate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 我的订单列表					
 * @author JIAWENZHENG
 *
 */
@Validate
public class GetOrdersRequest extends BaseRequest{

	private static final long serialVersionUID = 2880799893255522410L;

	@NotNullValidate
	private String memberId;

	@ExplainAnnotation(explain = "状态类型",desc = "0:全部,1:待付款，2:已付款(暂不实现)，3:待评价")
	private Integer type;

	@NotNullValidate
	@ExplainAnnotation(explain = "业态类型")
	private String businessType;

	@MinValueValidate(value="1")
	@ExplainAnnotation(explain = "当前查询页码")
	private Integer pageNo = 1;

	@MaxValueValidate(value="20")
	@ExplainAnnotation(explain = "每页查询数")
	private Integer pageSize = 10;

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public GetOrdersRequest() {

	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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


	@Override
	public String toString() {
		return "GetOrdersRequest [memberId=" + memberId
				+ ", type=" + type + ", pageNo=" + pageNo
				+ ",pageSize="+pageSize+", toString()=" + super.toString() + "]";
	}

}
