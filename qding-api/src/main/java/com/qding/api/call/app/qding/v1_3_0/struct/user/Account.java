package com.qding.api.call.app.qding.v1_3_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;

public class Account {

	public Account() {
	}

	@ExplainAnnotation(explain = "账号ID")
	private String accountId;

	@ExplainAnnotation (explain = "账号昵称")
	private String accountNickName;

	@ExplainAnnotation (explain = "用户头像")
	private String accountAvatar;

	@ExplainAnnotation (explain = "账号来源类型")
	private Integer accountSourceType;

	@ExplainAnnotation (explain = "账号类型")
	private Integer accountType;

	@ExplainAnnotation (explain = "账号状态")
	private Integer status;
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountNickName() {
		return accountNickName;
	}

	public void setAccountNickName(String accountNickName) {
		this.accountNickName = accountNickName;
	}

	public String getAccountAvatar() {
		return accountAvatar;
	}

	public void setAccountAvatar(String accountAvatar) {
		this.accountAvatar = accountAvatar;
	}

	public Integer getAccountSourceType() {
		return accountSourceType;
	}
	
	public void setAccountSourceType(Integer accountSourceType) {
		this.accountSourceType = accountSourceType;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
