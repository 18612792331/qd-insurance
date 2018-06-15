package com.qding.api.call.app.qding.v3_0_0.struct.user;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/16.
 */
public class CheckMobileResultDTO  implements Serializable{

    private static final long serialVersionUID = 5529848213886346198L;

    private Integer code;

    private String message;

    public CheckMobileResultDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CheckMobileResultDTO(){

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
