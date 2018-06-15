package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.struct.ResponseData;

public class GetUserInfoResponseData   extends ResponseData {

	
	
	private static final long serialVersionUID = -7432670854634682055L;

	public GetUserInfoResponseData() {
	}
	
	/**
     * 用户信息（账户和会员信息）对象
     */
	private AccountMember entity;

	public AccountMember getEntity() {
		return entity;
	}

	public void setEntity(AccountMember entity) {
		this.entity = entity;
	}

	public GetUserInfoResponseData(AccountMember entity) {
		super();
		this.entity = entity;
	}
	
	@Override
    public String toString() {
        return "GetUserInfoResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }


}
