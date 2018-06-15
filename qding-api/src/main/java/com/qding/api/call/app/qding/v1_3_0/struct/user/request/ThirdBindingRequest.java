package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class ThirdBindingRequest  extends BaseRequest  {

	
	private static final long serialVersionUID = -5034634325467097495L;

	public ThirdBindingRequest() {
	}
	
	/**
     * 合作ID
     */
	@NotNullValidate
	private String partnerId;
	
	/**
     * 来源
     */
	@NotNullValidate
	private Integer sourceType;
	
	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	
	
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public ThirdBindingRequest(String partnerId, Integer sourceType) {
		super();
		this.partnerId = partnerId;
		this.sourceType = sourceType;
	}

	@Override
    public String toString() {
        return "ThirdBindingRequest [partnerId="+partnerId+",sourceType="+sourceType+",toString()=" + super.toString() + "]";
    }

}
