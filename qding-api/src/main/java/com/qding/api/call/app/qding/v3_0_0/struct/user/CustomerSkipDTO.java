package com.qding.api.call.app.qding.v3_0_0.struct.user;

import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

/**
 * Created by qd on 2017/5/17.
 */
public class CustomerSkipDTO extends SkipUrl {

    private Integer skipNo;

    private String msg;

    public Integer getSkipNo() {
        return skipNo;
    }

    public void setSkipNo(Integer skipNo) {
        this.skipNo = skipNo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
