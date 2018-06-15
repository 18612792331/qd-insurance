package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

public class InsuranceOrderDetailResponseData extends ResponseData{

	private static final long serialVersionUID = -1374314141757999180L;
	
	@ExplainAnnotation (explain = "订单状态")
	private String status;
	
	@ExplainAnnotation (explain = "主图地址")
	private String image;
	
	@ExplainAnnotation (explain = "商品名称")
	private String wareName;
	
	@ExplainAnnotation (explain = "总价")
	private String orderMoney;
	
	@ExplainAnnotation (explain = "投保人姓名")
	private MemberAndInsurantInfo memberAndInsurantInfo;
	
	@ExplainAnnotation (explain = "保单id(保障详情跳转)")
	private String policyId;
	
	@ExplainAnnotation (explain = "电子保单url(电子保单跳转)")
	private String policyUrl;
	
	
	@ExplainAnnotation (explain = "供方")
	private String insuranceCompany;
	
	@ExplainAnnotation (explain = "下单时间")
	private String createAt;
	
	@ExplainAnnotation (explain = "系统订单编号")
	private String orderNo;
	
	@ExplainAnnotation (explain = "实付金额")
	private String paidMoney;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWareName() {
		return wareName;
	}

	public void setWareName(String wareName) {
		this.wareName = wareName;
	}

	public String getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(String orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(String paidMoney) {
		this.paidMoney = paidMoney;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyUrl() {
		return policyUrl;
	}

	public void setPolicyUrl(String policyUrl) {
		this.policyUrl = policyUrl;
	}

	public MemberAndInsurantInfo getMemberAndInsurantInfo() {
		return memberAndInsurantInfo;
	}

	public void setMemberAndInsurantInfo(MemberAndInsurantInfo memberAndInsurantInfo) {
		this.memberAndInsurantInfo = memberAndInsurantInfo;
	}
	
	
	
	
	
	

}