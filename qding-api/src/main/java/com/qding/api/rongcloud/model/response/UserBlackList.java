package com.qding.api.rongcloud.model.response;

import java.io.Serializable;

/**
 * 用户黑名单列表
 * Created by Administrator on 2015/7/27.
 */
public class UserBlackList implements Serializable {

    private static final long serialVersionUID = -6295294985925423960L;

    private Integer code;

    private String[] users;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String[] getUsers() {
        return users;
    }

    public void setUsers(String[] users) {
        this.users = users;
    }
}
