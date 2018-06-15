package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 获取用户是否已注册 
 * @author Administrator
 *
 */
@Validate
public class CheckMobileIsRegisterRequest  extends BaseRequest  {

	private static final long serialVersionUID = -755473816284005509L;
	
	/**
	 * 手机号
	 */
	@NotNullValidate
	private String mobile;
	
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

	public CheckMobileIsRegisterRequest(String mobile,Integer sourceType) {
		super();
		this.mobile = mobile;
		this.sourceType = sourceType;
	}
	
	public CheckMobileIsRegisterRequest(){
		
	}
	
	
	@Override
    public String toString() {
        return "CheckMobileIsRegisterRequest [sourceType="+sourceType+",mobile=" + mobile+", toString()=" + super.toString() + "]";
    }
	
}
