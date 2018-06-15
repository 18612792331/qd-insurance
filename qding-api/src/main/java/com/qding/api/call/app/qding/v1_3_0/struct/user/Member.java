package com.qding.api.call.app.qding.v1_3_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;

public class Member {

	public Member() {
		// TODO Auto-generated constructor stub
	}

	@ExplainAnnotation (explain = "会员ID")
	private String memberId;

	@ExplainAnnotation (explain = "会员手机号")
	private String memberMobile;

	@ExplainAnnotation (explain = "会员名称")
	private String memberName;

	@ExplainAnnotation (explain = "会员签名")
	private String memberSignature;

	@ExplainAnnotation (explain = "会员性别")
	private String memberGender;

	@ExplainAnnotation (explain = "会员头像")
	private String memberAvatar;

	@ExplainAnnotation (explain = "会员生日")
	private String memberBirthday;

	@ExplainAnnotation (explain = "会员昵称")
	private String memberNickName;

	@ExplainAnnotation (explain = "会员状态",desc = "1:正常，0:冻结")
	private String memberStatus;

	@ExplainAnnotation (explain = "家庭状况" ,desc = "3.0新增")
	private String homeSituation;

	@ExplainAnnotation (explain = "家庭状况索引" ,desc = "3.0新增")
	private Integer homeSituationIndex;

	@ExplainAnnotation (explain = "统一会员编号")
	private String memberNo;


	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the memberMobile
	 */
	public String getMemberMobile() {
		return memberMobile;
	}

	/**
	 * @param memberMobile the memberMobile to set
	 */
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the memberSignature
	 */
	public String getMemberSignature() {
		return memberSignature;
	}

	/**
	 * @param memberSignature the memberSignature to set
	 */
	public void setMemberSignature(String memberSignature) {
		this.memberSignature = memberSignature;
	}

	/**
	 * @return the memberGender
	 */
	public String getMemberGender() {
		return memberGender;
	}

	/**
	 * @param memberGender the memberGender to set
	 */
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	/**
	 * @return the memberAvatar
	 */
	public String getMemberAvatar() {
		return memberAvatar;
	}

	/**
	 * @param memberAvatar the memberAvatar to set
	 */
	public void setMemberAvatar(String memberAvatar) {
		this.memberAvatar = memberAvatar;
	}

	/**
	 * @return the memberBirthday
	 */
	public String getMemberBirthday() {
		return memberBirthday;
	}

	/**
	 * @param memberBirthday the memberBirthday to set
	 */
	public void setMemberBirthday(String memberBirthday) {
		this.memberBirthday = memberBirthday;
	}

	/**
	 * @return the memberNickName
	 */
	public String getMemberNickName() {
		return memberNickName;
	}

	/**
	 * @param memberNickName the memberNickName to set
	 */
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	/**
	 * @return the memberStatus
	 */
	public String getMemberStatus() {
		return memberStatus;
	}

	/**
	 * @param memberStatus the memberStatus to set
	 */
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getHomeSituation() {
		return homeSituation;
	}

	public void setHomeSituation(String homeSituation) {
		this.homeSituation = homeSituation;
	}


	public Integer getHomeSituationIndex() {
		return homeSituationIndex;
	}

	public void setHomeSituationIndex(Integer homeSituationIndex) {
		this.homeSituationIndex = homeSituationIndex;
	}
}
