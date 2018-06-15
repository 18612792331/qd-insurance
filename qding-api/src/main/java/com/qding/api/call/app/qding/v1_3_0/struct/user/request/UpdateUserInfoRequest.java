package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.api.call.app.qding.v1_3_0.struct.user.Member;
import com.qding.api.smart.validate.rule.AccountValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class UpdateUserInfoRequest  extends BaseRequest  {

    
	private static final long serialVersionUID = 6238880682800223945L;

	public UpdateUserInfoRequest() {
	}
	
	
	/**
     * 账号ID
     */
	@AccountValidate
	private String accountId;
	
	/**
     * 会员信息
     */
	@NotNullValidate
	private Member memberInfo;
	

	public Member getMemberInfo() {
		return memberInfo;
	}


	public void setMemberInfo(Member memberInfo) {
		this.memberInfo = memberInfo;
	}


	public UpdateUserInfoRequest(String accountId, Member memberInfo) {
		super();
		this.accountId = accountId;
		this.memberInfo = memberInfo;
	}


	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}


	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
    public String toString() {
        return "UpdateUserInfoRequest [accountId="+accountId+",memberInfo="+memberInfo+",toString()=" + super.toString() + "]";
    }

}
