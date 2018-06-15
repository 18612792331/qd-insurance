package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class ForgetPassWordRequest extends BaseRequest  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2096412420425613167L;

	public ForgetPassWordRequest() {
		// TODO Auto-generated constructor stub
	}
	
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
	 * 新密码
	 */
	@NotNullValidate
	private String newPwd;
	
	/**
	 * 来源类型
	 */
	@NotNullValidate
	private Integer sourceType;
	

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

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	 @Override
	    public String toString() {
	        return "ForgetPassWordRequest [verifyCode="+verifyCode+",mobile=" + mobile+",newPwd="+newPwd+" "
	                + ",sourceType="+sourceType+",toString()=" + super.toString() + "]";
	    }

}
