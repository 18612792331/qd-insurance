package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 用户
 * @author lichao
 *
 */
@XStreamAlias(value="user")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -742786027207933950L;

	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 用户头像
	 */
	private String userHeadImageUrl;

	/**
	 *用户姓名
	 */
	private String userName;
	
	/**
	 *签名 
	 */
	private String userSign;

	/**
	 * 性别
	 */
	private Integer userSex;

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public User() {

	}


	public User(String userId, String userHeadImageUrl, String userName,
			String userSign) {
		super();
		this.userId = userId;
		this.userHeadImageUrl = userHeadImageUrl;
		this.userName = userName;
		this.userSign = userSign;
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
				+ userSign + ", userSex="+userSex+",toString()=" + super.toString() + "]";
	}
	
}
