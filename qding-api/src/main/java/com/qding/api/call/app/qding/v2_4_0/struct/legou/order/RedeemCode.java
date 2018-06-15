package com.qding.api.call.app.qding.v2_4_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/8/12.
 */
public class RedeemCode implements Serializable {

    private static final long serialVersionUID = -2332145894626795504L;

    @ExplainAnnotation(explain = "兑换码")
    private String redeemCode;

    @ExplainAnnotation(explain = "密码")
    private String passWord;

    @ExplainAnnotation(explain = "条形码")
    private String imgCodeUrl;

    public String getRedeemCode() {
        return redeemCode;
    }

    public void setRedeemCode(String redeemCode) {
        this.redeemCode = redeemCode;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getImgCodeUrl() {
        return imgCodeUrl;
    }

    public void setImgCodeUrl(String imgCodeUrl) {
        this.imgCodeUrl = imgCodeUrl;
    }
}
