package com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/12/2.
 */
public class GetIntegralCountResponseData extends ResponseData {

    private static final long serialVersionUID = 4588116880800830323L;

    @ExplainAnnotation(explain = "积分数")
    private Long accountIntegral = 0L;

    public Long getAccountIntegral() {
        return accountIntegral;
    }

    public void setAccountIntegral(Long accountIntegral) {
        this.accountIntegral = accountIntegral;
    }

    @Override
    public String toString() {
        return "GetIntegralCountResponseData{" +
                "accountIntegral=" + accountIntegral +
                '}';
    }
}
