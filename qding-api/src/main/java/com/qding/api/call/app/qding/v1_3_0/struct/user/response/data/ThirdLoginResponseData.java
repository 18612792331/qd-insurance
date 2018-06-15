package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.call.app.qding.v1_3_0.struct.user.WXUser;
import com.qding.api.struct.ResponseData;

public class ThirdLoginResponseData extends ResponseData {

	
	private static final long serialVersionUID = -1551644594049599384L;

	public ThirdLoginResponseData() {
	}
	
	@ExplainAnnotation(explain = "用户信息（账户信息，会员信息）对象")
	private AccountMember entity;

	@ExplainAnnotation(explain = "获取微信用户信息")
	private WXUser wxUser;

	public ThirdLoginResponseData(AccountMember entity) {
		super();
		this.entity = entity;
	}

	public WXUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WXUser wxUser) {
		this.wxUser = wxUser;
	}

	public AccountMember getEntity() {
		return entity;
	}

	public void setEntity(AccountMember entity) {
		this.entity = entity;
	}

	@Override
    public String toString() {
        return "ThirdLoginResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }
	

}
