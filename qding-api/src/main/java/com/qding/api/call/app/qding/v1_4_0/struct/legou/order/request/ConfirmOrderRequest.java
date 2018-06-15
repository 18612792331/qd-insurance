package com.qding.api.call.app.qding.v1_4_0.struct.legou.order.request;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;

@Validate
public class ConfirmOrderRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2505352101786583877L;
	
	@MemberValidate
	private String memberId;
	
	@NotNullValidate
	private String projectId;
	
	@NotNullValidate
	private List<Sku> skus;
	
	@NotNullValidate
	private Integer orderSourceType;
	
	private String[] couponCodes;

	//是否匿名购买 匿名：0 不匿名：1
	private Integer isAnonymity = 1;

	//订单促销ID
	private List<String> orderPromotionIds;

	public ConfirmOrderRequest() {

	}

	public ConfirmOrderRequest(String memberId, String projectId,
							   List<Sku> skus, Integer orderSourceType, String[] couponCodes) {
		super();
		this.memberId = memberId;
		this.projectId = projectId;
		this.skus = skus;
		this.orderSourceType = orderSourceType;
		this.couponCodes = couponCodes;
	}


	public Integer getIsAnonymity() {
		return isAnonymity;
	}

	public void setIsAnonymity(Integer isAnonymity) {
		this.isAnonymity = isAnonymity;
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


	public List<Sku> getSkus() {
		return skus;
	}


	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}


	public Integer getOrderSourceType() {
		return orderSourceType;
	}


	public void setOrderSourceType(Integer orderSourceType) {
		this.orderSourceType = orderSourceType;
	}


	public String[] getCouponCodes() {
		return couponCodes;
	}


	public void setCouponCodes(String[] couponCodes) {
		this.couponCodes = couponCodes;
	}


	public List<String> getOrderPromotionIds() {
		return orderPromotionIds;
	}

	public void setOrderPromotionIds(List<String> orderPromotionIds) {
		this.orderPromotionIds = orderPromotionIds;
	}
}
