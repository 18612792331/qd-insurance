package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;


import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.struct.ResponseData;

public class UserRegisterResponseData   extends ResponseData{

	
	private static final long serialVersionUID = 7966396769516576821L;

	public UserRegisterResponseData() {
	}
	
	/**
     * 用户信息（账户信息，会员信息）对象
     */
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
