package com.qding.api.call.common.struct.dataAnalysis.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class StartupAppRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6352525370396899522L;

	private String accountId;
	
	private String memberId;
	
	private String mobile;
	
	private String projectId;
	
	private String projectName;
	
	public StartupAppRequest() {

	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
