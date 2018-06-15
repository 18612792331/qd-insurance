package com.qding.api.call.app.qding.v1_3_2.struct.popularize.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by Administrator on 2015/8/3.
 */
@Validate
public class GetPopularizeAccountByCodeRequest    extends BaseRequest {

    @NotNullValidate
    private String code;

    public GetPopularizeAccountByCodeRequest() {
    }

    public GetPopularizeAccountByCodeRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return "GetPopularizeAccountByCodeRequest [code=" + this.code + "]";
    }
}
