package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetUserInfoRequest  extends BaseRequest  {


	private static final long serialVersionUID = 7727175593446863707L;

	public GetUserInfoRequest() {
	}
	
	/**
     * 账户ID
     */
	@NotNullValidate
	private String accountId;
	
	/**
     * 会员ID
     */
	@NotNullValidate
	private String memberId;
	
	


	public GetUserInfoRequest(String accountId, String memberId) {
		super();
		this.accountId = accountId;
		this.memberId = memberId;
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Override
    public String toString() {
        return "GetUserInfoRequest [accountId="+accountId+",memberId="+memberId+",toString()=" + super.toString() + "]";
    }

}
