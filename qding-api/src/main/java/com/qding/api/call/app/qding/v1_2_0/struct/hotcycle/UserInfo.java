package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 用户
 * @author lichao
 *
 */
@XStreamAlias(value="userInfo")
public class UserInfo implements Serializable{

	private static final long serialVersionUID = -742786027207933950L;

	@ExplainAnnotation(explain = "用户ID")
	private String userId;

	@ExplainAnnotation(explain = "会员ID")
	private String memberId;
	
	@ExplainAnnotation(explain = "用户头像")
	private String userHeadImageUrl;

	@ExplainAnnotation(explain = "用户姓名")
	private String userName;
	
	@ExplainAnnotation(explain = "签名")
	private String userSign;
	
	@ExplainAnnotation(explain = "性别")
	private Integer userSex;
	
	public UserInfo() {

	}

	public UserInfo(String userId, String userHeadImageUrl, String userName,
			String userSign, Integer userSex) {
		super();
		this.userId = userId;
		this.userHeadImageUrl = userHeadImageUrl;
		this.userName = userName;
		this.userSign = userSign;
		this.userSex = userSex;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	
	public Integer getUserSex() {
		return userSex;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserHeadImageUrl() {
		return userHeadImageUrl;
	}

	public void setUserHeadImageUrl(String userHeadImageUrl) {
		this.userHeadImageUrl = userHeadImageUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserSign(String userSign) {
		this.userSign = userSign;
	}
	
	public String getUserSign() {
		return userSign;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userHeadImageUrl="
				+ userHeadImageUrl + ", userName=" + userName + ", userSign="
				+ userSign + ", toString()=" + super.toString() + "]";
	}
	
}
