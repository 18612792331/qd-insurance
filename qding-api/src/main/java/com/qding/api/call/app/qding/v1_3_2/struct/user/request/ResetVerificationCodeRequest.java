package com.qding.api.call.app.qding.v1_3_2.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/9/17.
 */

@Validate
public class ResetVerificationCodeRequest  extends BaseRequest {


    private static final long serialVersionUID = 4988072124800745373L;

    /**
     * 手机号
     */
    @NotNullValidate
    private String mobile;

    /**
     * 验证码类型
     */
    @NotNullValidate
    private int action;


    /**
     * 验证码
     */
    @NotNullValidate
    private String verifyCode;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
