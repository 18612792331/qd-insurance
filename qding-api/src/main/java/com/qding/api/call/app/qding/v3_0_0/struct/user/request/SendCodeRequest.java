package com.qding.api.call.app.qding.v3_0_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/3/8.
 */
@Validate
public class SendCodeRequest extends BaseRequest {

    private static final long serialVersionUID = -8501792380841586908L;

    @ExplainAnnotation (explain = "手机号")
    @NotNullValidate
    private String mobile;

    @ExplainAnnotation (explain = "验证码类型")
    @NotNullValidate
    private Integer action;

    @ExplainAnnotation (explain = "短信验证类型",desc = "1:短信验证码 2:语音验证码")
    @NotNullValidate
    private Integer codeType;

    //系统验证码
    @NotNullValidate
    private String sysCode;

    //系统验证码唯一key
    @NotNullValidate
    private String sysVerifyKey;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getSysVerifyKey() {
        return sysVerifyKey;
    }

    public void setSysVerifyKey(String sysVerifyKey) {
        this.sysVerifyKey = sysVerifyKey;
    }
}
