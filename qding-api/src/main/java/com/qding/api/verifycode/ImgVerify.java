package com.qding.api.verifycode;

import java.io.Serializable;

/**
 * Created by qd on 2016/4/7.
 */
public class ImgVerify implements Serializable {


    private static final long serialVersionUID = 672116886383279729L;
    //系统验证码
    private String sysCode;

    //系统验证码唯一key
    private String sysVerifyKey;

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

    public ImgVerify(String sysCode, String sysVerifyKey) {
        this.sysCode = sysCode;
        this.sysVerifyKey = sysVerifyKey;
    }

    public ImgVerify() {
    }
}
