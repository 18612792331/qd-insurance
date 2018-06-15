package com.qding.api.call.app.qding.v1_3_0.struct.notify.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class BindNotifyTokenRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8901038413340834571L;

	@NotNullValidate
	private String accountId;
	
	@NotNullValidate
	private String projectId;
	
	@NotNullValidate
	private String deviceUniqueToken;

	/**
	 * 设备类型 (0,1,2) null->mqtt
	 *  OS_HUAWEI = 1;
	 *  OS_MI = 2;
	 *  OS_OTHER = 0;
	 */
	private String deviceType = "";
	
	public BindNotifyTokenRequest() {

	}

	public BindNotifyTokenRequest(String accountId, String projectId,
			String deviceUniqueToken) {
		super();
		this.accountId = accountId;
		this.projectId = projectId;
		this.deviceUniqueToken = deviceUniqueToken;
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

	public String getDeviceUniqueToken() {
		return deviceUniqueToken;
	}

	public void setDeviceUniqueToken(String deviceUniqueToken) {
		this.deviceUniqueToken = deviceUniqueToken;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
}
