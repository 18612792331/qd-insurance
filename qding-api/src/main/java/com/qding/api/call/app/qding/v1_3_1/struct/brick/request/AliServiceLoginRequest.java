package com.qding.api.call.app.qding.v1_3_1.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/1/27.
 */
@Validate
public class AliServiceLoginRequest extends BaseRequest {

    private static final long serialVersionUID = 2398748885247725359L;

    @ExplainAnnotation(explain = "支付宝服务窗用户口令")
    @NotNullValidate
    private String authcode;

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public AliServiceLoginRequest(String authcode) {
        this.authcode = authcode;
    }

    public AliServiceLoginRequest() {
    }

    @Override
    public String toString() {
        return "AliServiceLoginRequest{" +
                "authcode='" + authcode + '\'' +
                '}';
    }
}
