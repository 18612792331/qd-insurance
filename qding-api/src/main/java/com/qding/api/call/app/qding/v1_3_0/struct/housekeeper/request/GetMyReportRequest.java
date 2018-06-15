package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * 我的报事查询					
 * @author lichao
 *
 */
@Validate
public class GetMyReportRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2072974733783160675L;

	@NotNullValidate
	private String accountId;
	@NotNullValidate
	private String projectId;
	
	@RangeValueValidate(max="20", min="1")
	private Integer pageSize = 10;
	
	@MinValueValidate(value="1")
	private Integer pageNo = 1;

	public GetMyReportRequest() {

	}

	public GetMyReportRequest(String accountId, String projectId, Integer pageSize,
			Integer pageNo) {
		super();
		this.accountId = accountId;
		this.projectId = projectId;
		this.pageSize = pageSize;
		this.pageNo = pageNo;
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

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public String toString() {
		return "GetMyReportRequest [accountId=" + accountId + ", projectId="
				+ projectId + ", pageSize=" + pageSize + ", pageNo=" + pageNo
				+ ", toString()=" + super.toString() + "]";
	}
	
}
