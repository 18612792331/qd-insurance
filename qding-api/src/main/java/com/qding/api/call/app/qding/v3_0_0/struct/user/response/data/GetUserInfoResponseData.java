package com.qding.api.call.app.qding.v3_0_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/3.
 */
public class GetUserInfoResponseData extends ResponseData {

    private static final long serialVersionUID = -2404605769587141132L;

    @ExplainAnnotation (explain = "用户信息（账户和会员信息）")
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
    public GetUserInfoResponseData() {
        super();
    }

    @Override
    public String toString() {
        return "GetUserInfoResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }
}
