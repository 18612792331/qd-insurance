package com.qding.api.call.app.qding.v1_3_2.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.struct.ResponseData;

public class UserLoginResponseData extends ResponseData {


	private static final long serialVersionUID = 6186843540337420237L;

	public UserLoginResponseData() {
	}
	
	@ExplainAnnotation(explain = "用户信息（账户信息，会员信息）对象")
	private AccountMember entity;

	public UserLoginResponseData(AccountMember entity) {
		super();
		this.entity = entity;
	}

	public AccountMember getEntity() {
		return entity;
	}

	public void setEntity(AccountMember entity) {
		this.entity = entity;
	}

	@Override
    public String toString() {
        return "UserLoginResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }
}
