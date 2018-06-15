package com.qding.api.call.app.qding.v1_3_1.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/1/27.
 */
public class AliServiceLoginResponseData extends ResponseData {

    private static final long serialVersionUID = -2421170830633696822L;

    @ExplainAnnotation (explain = "账号ID")
    private String accountId;

    @ExplainAnnotation (explain = "会员ID")
    private String memberId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public AliServiceLoginResponseData(String accountId, String memberId) {
        this.accountId = accountId;
        this.memberId = memberId;
    }

    public AliServiceLoginResponseData() {
    }

    @Override
    public String toString() {
        return "AliServiceLoginResponseData{" +
                "accountId='" + accountId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
