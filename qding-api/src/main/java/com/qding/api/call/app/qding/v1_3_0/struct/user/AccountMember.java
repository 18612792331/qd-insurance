package com.qding.api.call.app.qding.v1_3_0.struct.user;


import com.qding.api.annotation.ExplainAnnotation;

public class AccountMember {

	public AccountMember() {
	}
	
	@ExplainAnnotation (explain = "账户信息对象")
	private Account user;
	
	@ExplainAnnotation (explain = "会员信息对象")
	private Member member;
	
	private String userToken;

	private String imToken;
	
	@ExplainAnnotation(explain = "我的保单")
    private String myInsureUrl;

	public String getImToken() {
		return imToken;
	}

	public void setImToken(String imToken) {
		this.imToken = imToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	public String getUserToken() {
		return userToken;
	}
	
	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public AccountMember(Account user, Member member) {
		super();
		this.user = user;
		this.member = member;
	}

	public String getMyInsureUrl() {
		return myInsureUrl;
	}

	public void setMyInsureUrl(String myInsureUrl) {
		this.myInsureUrl = myInsureUrl;
	}
}
