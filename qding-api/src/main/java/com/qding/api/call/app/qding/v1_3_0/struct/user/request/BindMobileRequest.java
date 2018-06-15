package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.api.smart.validate.rule.AccountValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class BindMobileRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8195597562180019423L;
	
	/**
	 * 会员账号ID
	 */
	@AccountValidate
	private String accountId ;
	
	/**
	 * 手机号
	 */
	@NotNullValidate
	private String mobile;
	
	/**
	 * 验证码
	 */
	@NotNullValidate
	private String verifyCode;

	/**
	 * 当前社区ID
	 */
	private String projectId = "";


	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	
	public String getMobile() {
		return mobile;
	}

	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getVerifyCode() {
		return verifyCode;
	}

	
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	
	@Override
    public String toString() {
        return "BindMobileRequest [projectId="+projectId+",accountId="+accountId+",mobile=" + mobile+",verifyCode="+verifyCode+ ", toString()=" + super.toString() + "]";
    }
	
}
