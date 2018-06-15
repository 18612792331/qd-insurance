package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * 根据用户id和社区id查询门禁列表					
 * @author lichao
 *
 */
@Validate
public class GetAccessControlsRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7401681593258710145L;
	//TODO
//	@RangeValueValidate(max="20", min="1")
	private Integer pageSize = 10;
	
	@MinValueValidate(value="1")
	private Integer pageNo = 1;
	
	@NotNullValidate
	private String accountId;
	
	@NotNullValidate
	private String projectId;
	
	public GetAccessControlsRequest() {
	}

	public GetAccessControlsRequest(Integer pageSize, Integer pageNo, String accountId,
			String projectId) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.accountId = accountId;
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

	@Override
	public String toString() {
		return "GetAccessControlsRequest [pageSize=" + pageSize + ", pageNo="
				+ pageNo + ", accountId=" + accountId + ", projectId=" + projectId
				+ ", toString()=" + super.toString() + "]";
	}
	 
}
