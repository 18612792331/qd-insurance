package com.qding.insurance.rpc.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by zhangxiaojun on 18/6/7.
 */
public class InsuranceOrderInsureRequest extends BaseRequest {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3943032553490656322L;

	//商品ID
	private String wareId;
	//投保人MID
	private String memberId;
	//投保人姓名
	private String memberName;
	//投保人证件号
	private String memberIdcard;
	//投保人手机号
	private String memberPhone;
	//被保险人姓名
	private String insurantName;
	//被保险人证件号
	private String insurantIdCard;
	//被保险人手机号
	private String insurantPhone;
	//被保险人与投保人的关系
	private String insurantRelation;
	//受益人姓名
	private String benefitName;
	//受益人证件号
	private String benefitIdCard;
	//受益人手机号
	private String benefitPhone;
	//城市ID
	private String cityId;
	//城市名称
	private String cityName;
	//被保险房屋的社区ID
	private String projectId;
	//被保险房屋的社区名称
	private String projectName;
	//被保险房屋的roomId
	private String roomId;
	//被保险房屋的地址
	private String roomAddress;
	//保险生效日期
	private String policyActAt;
	//货品ID
	private String skuId;
	//邮箱地址
	private String memberEmail;
	
	public String getWareId() {
		return wareId;
	}
	public void setWareId(String wareId) {
		this.wareId = wareId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberIdcard() {
		return memberIdcard;
	}
	public void setMemberIdcard(String memberIdcard) {
		this.memberIdcard = memberIdcard;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getInsurantName() {
		return insurantName;
	}
	public void setInsurantName(String insurantName) {
		this.insurantName = insurantName;
	}
	public String getInsurantIdCard() {
		return insurantIdCard;
	}
	public void setInsurantIdCard(String insurantIdCard) {
		this.insurantIdCard = insurantIdCard;
	}
	public String getInsurantPhone() {
		return insurantPhone;
	}
	public void setInsurantPhone(String insurantPhone) {
		this.insurantPhone = insurantPhone;
	}
	public String getInsurantRelation() {
		return insurantRelation;
	}
	public void setInsurantRelation(String insurantRelation) {
		this.insurantRelation = insurantRelation;
	}
	public String getBenefitName() {
		return benefitName;
	}
	public void setBenefitName(String benefitName) {
		this.benefitName = benefitName;
	}
	public String getBenefitIdCard() {
		return benefitIdCard;
	}
	public void setBenefitIdCard(String benefitIdCard) {
		this.benefitIdCard = benefitIdCard;
	}
	public String getBenefitPhone() {
		return benefitPhone;
	}
	public void setBenefitPhone(String benefitPhone) {
		this.benefitPhone = benefitPhone;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomAddress() {
		return roomAddress;
	}
	public void setRoomAddress(String roomAddress) {
		this.roomAddress = roomAddress;
	}
	public String getPolicyActAt() {
		return policyActAt;
	}
	public void setPolicyActAt(String policyActAt) {
		this.policyActAt = policyActAt;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	
	

}
