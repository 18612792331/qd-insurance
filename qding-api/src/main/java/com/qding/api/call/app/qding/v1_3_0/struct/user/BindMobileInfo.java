package com.qding.api.call.app.qding.v1_3_0.struct.user;

public class BindMobileInfo {

    /**
     * 账户信息对象
     */
	private Account user;
	
	/**
     * 会员信息对象
     */
	private Member member;

	/**
	 * 返回token
	 */
	private String userToken;

	/**
	 * @return the user
	 */
	public Account getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Account user) {
		this.user = user;
	}

	/**
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * @param member the member to set
	 */
	public void setMember(Member member) {
		this.member = member;
	}


	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
}
