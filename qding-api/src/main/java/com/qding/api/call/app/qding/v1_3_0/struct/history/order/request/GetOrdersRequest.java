package com.qding.api.call.app.qding.v1_3_0.struct.history.order.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MaxValueValidate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetOrdersRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5174591444600453992L;

	@NotNullValidate
	private String accountId;
	
	@NotNullValidate
	private String projectId;
	
	@MinValueValidate(value="1")
	private Integer pageNo = 1;
	
	@MaxValueValidate(value="20")
	private Integer pageSize = 10;
	
	public GetOrdersRequest() {

	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
