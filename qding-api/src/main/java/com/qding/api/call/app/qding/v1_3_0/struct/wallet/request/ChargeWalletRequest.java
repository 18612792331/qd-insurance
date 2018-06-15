package com.qding.api.call.app.qding.v1_3_0.struct.wallet.request;

import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.smart.validate.rule.WalletStatusValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;


@Validate
public class ChargeWalletRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7114134113446709549L;

	@MemberValidate
	@WalletStatusValidate
	private String memberId;
	
	@NotNullValidate
	private String projectId;
	
	@NotNullValidate
	private Integer orderSourceType;
	
	@NotNullValidate
	private String skuId;
	
	private String publicsId;
	
	public ChargeWalletRequest() {

	}

	public String getPublicsId() {
		return publicsId;
	}


	public void setPublicsId(String publicsId) {
		this.publicsId = publicsId;
	}


	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getOrderSourceType() {
		return orderSourceType;
	}

	public void setOrderSourceType(Integer orderSourceType) {
		this.orderSourceType = orderSourceType;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	
}
