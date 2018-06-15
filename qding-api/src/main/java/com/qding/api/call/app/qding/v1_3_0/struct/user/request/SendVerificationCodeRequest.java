package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class SendVerificationCodeRequest  extends BaseRequest {


	private static final long serialVersionUID = 1530889334040941123L;

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

	public SendVerificationCodeRequest(String mobile, int action) {
		super();
		this.mobile = mobile;
		this.action = action;
	}
	
	public SendVerificationCodeRequest (){}
	
	@Override
    public String toString() {
        return "SendVerificationCodeRequest [mobile="+mobile+",action="+action+",toString()=" + super.toString() + "]";
    }
    
	
}
