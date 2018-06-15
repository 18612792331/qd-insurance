package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class ThirdLoginRequest  extends BaseRequest  {

	
	private static final long serialVersionUID = -5721035255705533461L;

	public ThirdLoginRequest() {
	}
	
	/**
     * 合作ID
     */
	@NotNullValidate
	private String partnerId;
	
	/**
     * 来源类型
     */
	@NotNullValidate
	private Integer sourceType;
	
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	
	public Integer getSourceType() {
		return sourceType;
	}
	
	@Override
    public String toString() {
        return "ThirdLoginRequest [partnerId="+partnerId+",sourceType="+sourceType+",toString()=" + super.toString() + "]";
    }


}
