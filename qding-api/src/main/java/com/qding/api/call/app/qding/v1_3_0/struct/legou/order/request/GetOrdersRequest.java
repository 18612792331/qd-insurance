package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MaxValueValidate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 我的订单列表					
 * @author lichao
 *
 */
@Validate
public class GetOrdersRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2880799893255522410L;

	@NotNullValidate
	private String memberId;
	
	@NotNullValidate
	private Integer type;
	
	@MinValueValidate(value="1")
	private Integer pageNo = 1;
	
	@MaxValueValidate(value="20")
	private Integer pageSize = 10;

	public GetOrdersRequest() {

	}

	public GetOrdersRequest(String memberId, Integer type,
			Integer pageNo, Integer pageSize) {
		super();
		this.memberId = memberId;
		this.type = type;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
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

}
