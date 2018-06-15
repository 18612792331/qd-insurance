package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

public class BindAuthResponseData extends ResponseData {

    private static final long serialVersionUID = -5337441266330777488L;

    @ExplainAnnotation(explain = "是否具有开卡权限，1-是，0-否")
    private Integer auth;

//    @ExplainAnnotation(explain = "是否有二维码，1-是，0-否")
//    private Integer isQR;

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

//    public Integer getIsQR() {
//        return isQR;
//    }
//
//    public void setIsQR(Integer isQR) {
//        this.isQR = isQR;
//    }

}
