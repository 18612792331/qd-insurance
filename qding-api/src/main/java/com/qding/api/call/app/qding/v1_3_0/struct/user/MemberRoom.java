package com.qding.api.call.app.qding.v1_3_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class MemberRoom implements Serializable {
	
	
	private static final long serialVersionUID = -5968631408521899687L;

	/**
     * 会员信息对象
     */
	private Member member;
	
	/**
     * 会员身份
     */
	@ExplainAnnotation(explain = "1：业主，2：业主亲戚，3：业主朋友，4：租客，5,：装修负责人，6：游客，8：物业人员，9:保姆，10：司机")
	private Short hkIndentity;

	/**
     *  有效起始时间
     */
	private Long validityStartAt;

	/**
     * 有效终止时间
     */
	private Long validityEndAt;

	public MemberRoom() {

	}
	

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	
	public Short getHkIndentity() {
		return hkIndentity;
	}


	public void setHkIndentity(Short hkIndentity) {
		this.hkIndentity = hkIndentity;
	}


	public Long getValidityStartAt() {
		return validityStartAt;
	}

	public void setValidityStartAt(Long validityStartAt) {
		this.validityStartAt = validityStartAt;
	}

	public Long getValidityEndAt() {
		return validityEndAt;
	}

	public void setValidityEndAt(Long validityEndAt) {
		this.validityEndAt = validityEndAt;
	}

}