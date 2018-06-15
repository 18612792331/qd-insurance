package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetInvitationCodeIsActivateRequest  extends BaseRequest {

	private static final long serialVersionUID = 2554046250569694437L;
	
	/**
	 * 邀请码
	 */
	@NotNullValidate
	private String qrcode;

	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public GetInvitationCodeIsActivateRequest(){
		
	}

	/**
	 * @return the qrcode
	 */
	public String getQrcode() {
		return qrcode;
	}

	/**
	 * @param qrcode the qrcode to set
	 */
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}


    @Override
    public String toString() {
        return "GetInvitationCodeIsActivateRequest [accountId="+accountId+", qrcode="+qrcode+",toString()=" + super.toString() + "]";
    }

	
}
