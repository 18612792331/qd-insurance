package com.qding.api.call.app.qding.v3_0_0.struct.user.response.data;


import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.struct.ResponseData;

public class UserRegisterResponseData extends ResponseData{

	
	private static final long serialVersionUID = 7966396769516576821L;

	public UserRegisterResponseData() {
	}
	
	@ExplainAnnotation (explain = "用户信息")
	private AccountMember entity;

	public UserRegisterResponseData(AccountMember entity) {
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
        return "UserRegisterResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }
}
