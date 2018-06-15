package com.qding.api.call.app.qding.v3_0_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/3.
 */
public class UpdateUserInfoResponseData extends ResponseData {

    private static final long serialVersionUID = 6630655740738763767L;


    public UpdateUserInfoResponseData() {
    }

    @ExplainAnnotation (explain = "用户信息（账户信息，会员信息")
    private AccountMember entity;

    public UpdateUserInfoResponseData(AccountMember entity) {
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
        return "UpdateUserInfoResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }
}
