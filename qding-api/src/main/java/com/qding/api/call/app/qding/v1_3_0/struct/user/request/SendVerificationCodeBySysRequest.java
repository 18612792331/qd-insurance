package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class SendVerificationCodeBySysRequest extends BaseRequest {


	private static final long serialVersionUID = 34250557111655454L;
	/**
     * 手机号
     */
	@NotNullValidate
	private String mobile;

	/**
     * 验证码类型
     */
	@NotNullValidate
	private int action;

	//系统验证码
	@NotNullValidate
	private String sysCode;

	//系统验证码唯一key
	@NotNullValidate
	private String sysVerifyKey;

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getSysVerifyKey() {
		return sysVerifyKey;
	}

	public void setSysVerifyKey(String sysVerifyKey) {
		this.sysVerifyKey = sysVerifyKey;
	}


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public SendVerificationCodeBySysRequest(String mobile, int action) {
		super();
		this.mobile = mobile;
		this.action = action;
	}


	public SendVerificationCodeBySysRequest(String mobile, int action, String sysCode, String sysVerifyKey) {
		this.mobile = mobile;
		this.action = action;
		this.sysCode = sysCode;
		this.sysVerifyKey = sysVerifyKey;
	}

	public SendVerificationCodeBySysRequest(){}
	
	@Override
    public String toString() {
        return "SendVerificationCodeRequest [mobile="+mobile+",action="+action+",toString()=" + super.toString() + "]";
    }
    
	
}
