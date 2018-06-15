package com.qding.api.call.app.qding.v1_4_1.struct.housekeeper;

import java.io.Serializable;

/**
 * Created by qd on 2015/11/13.
 */
public class Puser implements Serializable {

    private static final long serialVersionUID = 2330771727583951382L;

    private String id;
    private Integer status;
    private String account;
    private String name;
    private String mobile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
