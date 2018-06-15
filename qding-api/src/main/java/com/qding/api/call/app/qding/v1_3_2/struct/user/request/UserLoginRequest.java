package com.qding.api.call.app.qding.v1_3_2.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class UserLoginRequest extends BaseRequest  {


	/**
	 *
	 */
	private static final long serialVersionUID = 5146878071254793768L;

	/**
     * 合作ID
     */
	@NotNullValidate
	private String mobile;
	
	/**
     * 密码
     */
	@NotNullValidate
	private String password;
	
	/**
     * 来源类型
     */
	@NotNullValidate
	private Integer sourceType;

	/**
	 * 社区ID
	 */
	@NotNullValidate
	private String projectId;


	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectId() {
		return projectId;
	}



	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserLoginRequest() {
	}

	public UserLoginRequest(String mobile, String password, Integer sourceType) {
		super();
		this.mobile = mobile;
		this.password = password;
		this.sourceType = sourceType;
	}

}
