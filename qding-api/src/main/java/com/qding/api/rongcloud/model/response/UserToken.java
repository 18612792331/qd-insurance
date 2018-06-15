package com.qding.api.rongcloud.model.response;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/27.
 */
public class UserToken implements Serializable {


    private static final long serialVersionUID = 5785033737220393039L;

    private Integer code;

    private String userId;

    private String token;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "code=" + code +
                ", userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
