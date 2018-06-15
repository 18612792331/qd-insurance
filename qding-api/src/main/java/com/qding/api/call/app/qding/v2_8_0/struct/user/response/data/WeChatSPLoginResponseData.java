package com.qding.api.call.app.qding.v2_8_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/1/22.
 */
public class WeChatSPLoginResponseData extends ResponseData {

    private static final long serialVersionUID = -5548415185761903198L;

    @ExplainAnnotation(explain = "用户信息（账户信息，会员信息）对象")
    private AccountMember entity;

    public AccountMember getEntity() {
        return entity;
    }

    public void setEntity(AccountMember entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "WeChatSPLoginResponseData{" +
                "entity=" + entity +
                '}';
    }
}
