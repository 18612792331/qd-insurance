package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class UpdateUserPassWordRequest  extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1530889334040941123L;
	
	public UpdateUserPassWordRequest (){
		
	}
	
	/**
     * 账户ID
     */
	@NotNullValidate
	private String accountId;
	
	/**
     * 原密码
     */
	@NotNullValidate
	private String oldPwd;
	
	/**
     * 新密码
     */
	@NotNullValidate
	private String newPwd;

	
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

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public UpdateUserPassWordRequest(String accountId, String oldPwd,
			String newPwd) {
		super();
		this.accountId = accountId;
		this.oldPwd = oldPwd;
		this.newPwd = newPwd;
	}

	
	@Override
    public String toString() {
        return "UpdateUserPassWordRequest [accountId="+accountId+",oldPwd="+oldPwd+",newPwd="+newPwd+",toString()=" + super.toString() + "]";
    }
	
}
