package com.qding.api.call.app.qding.v1_3_2.struct.popularize.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by jiawenzheng on 2015/7/28.
 */
@Validate
public class UserInviteRegisterRequest extends BaseRequest {

	private static final long serialVersionUID = -5268668163553485709L;


    @NotNullValidate
    @ExplainAnnotation (explain = "手机号")
    private String mobile;

    @NotNullValidate
    @ExplainAnnotation (explain = "密码")
    private String password;

    @NotNullValidate
    @ExplainAnnotation (explain = "来源类型")
    private String sourceType;

    @NotNullValidate
    @ExplainAnnotation (explain = "验证码")
    private String verifyCode;

    @NotNullValidate
    @ExplainAnnotation (explain = "邀请码")
    private String inviteCode;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

}
