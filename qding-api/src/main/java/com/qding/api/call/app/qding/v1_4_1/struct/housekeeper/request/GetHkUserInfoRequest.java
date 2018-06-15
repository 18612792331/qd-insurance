package com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/11/13.
 */
@Validate
public class GetHkUserInfoRequest extends BaseRequest {

    @NotNullValidate
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "GetHkUserInfoRequest [account=" + account +",super.toString() ]";
    }
}
