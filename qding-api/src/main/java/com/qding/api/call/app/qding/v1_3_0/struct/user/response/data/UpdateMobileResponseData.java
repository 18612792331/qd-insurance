package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.struct.ResponseData;

public class UpdateMobileResponseData extends ResponseData {

	
	private static final long serialVersionUID = 83869813066571411L;

	/**
     * 用户信息（账户信息，会员信息）对象
     */
	private AccountMember entity;
	
	
	public UpdateMobileResponseData() {
	
	}

	public AccountMember getEntity() {
		return entity;
	}
	
	public void setEntity(AccountMember entity) {
		this.entity = entity;
	}

	public UpdateMobileResponseData(AccountMember entity) {
		super();
		this.entity = entity;
	}
	
	@Override
    public String toString() {
        return "UpdateMobileResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }
    
}
