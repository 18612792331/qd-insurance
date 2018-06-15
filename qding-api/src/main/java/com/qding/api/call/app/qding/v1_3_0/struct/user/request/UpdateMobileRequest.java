package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.api.smart.validate.rule.AccountValidate;
import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class UpdateMobileRequest  extends BaseRequest  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3406589030114700673L;

	public UpdateMobileRequest() {
	}
	
	/**
     * 会员ID
     */
	@MemberValidate
	private String memberId;
	
	/**
     * 账户ID
     */
	@AccountValidate
	private String accountId;
	
	/**
     * 新手机号
     */
	@NotNullValidate
	private String newMobile;
	
	/**
     * 原手机号
     */
	@NotNullValidate
	private String oldMobile;
	
	/**
     * 验证码
     */
	@NotNullValidate (name = "短信验证码",message = "短信验证码为必填项")
	private String verifyCode;

	
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

	public String getNewMobile() {
		return newMobile;
	}

	public void setNewMobile(String newMobile) {
		this.newMobile = newMobile;
	}

	public String getOldMobile() {
		return oldMobile;
	}

	public void setOldMobile(String oldMobile) {
		this.oldMobile = oldMobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}


	@Override
    public String toString() {
        return "UpdateMobileRequest [memberId="+memberId+",accountId="+accountId+",newMobile="+newMobile+","
                + "oldMobile="+oldMobile+",verifyCode="+verifyCode+",toString()=" + super.toString() + "]";
    }

}
