package com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.response.data;

import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/11/13.
 */
public class GetHkUserInfoResponseData extends ResponseData {

    private String mobile;

    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "GetHkUserInfoResponseData [account=" + account +",mobile="+mobile+"," +
                ",super.toString() ]";
    }
}
