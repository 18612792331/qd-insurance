package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetVerificationCodeIsEnableRequest  extends BaseRequest {

	
	private static final long serialVersionUID = 2677298208779623848L;

	/**
     * 手机号
     */
	@NotNullValidate
	private String mobile;
	
	/**
     * 验证码类型
     */
	@NotNullValidate
	private Integer action;
	
	/**
     * 验证码
     */
	@NotNullValidate
	private String verifyCode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public GetVerificationCodeIsEnableRequest(String mobile, Integer action,
			String verifyCode) {
		super();
		this.mobile = mobile;
		this.action = action;
		this.verifyCode = verifyCode;
	}
	
	public GetVerificationCodeIsEnableRequest (){
		
	}
	
	@Override
    public String toString() {
        return "GetUserInfoRequest [mobile="+mobile+",action="+action+",verifyCode="+verifyCode+",toString()=" + super.toString() + "]";
    }

}
