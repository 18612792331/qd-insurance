package com.qding.api.call.app.qding.v2_3_0.struct.watch;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/5/16.
 */
public class WatchBindInfo implements Serializable {

    private static final long serialVersionUID = -1041190254054857750L;

    @ExplainAnnotation(explain = "绑定人角色")
    private Integer role;

    @ExplainAnnotation(explain = "绑定人角色描述")
    private String roleDesc;

    @ExplainAnnotation(explain = "绑定时间")
    private String createTime = "";//2016-06-01 15:29:33

    @ExplainAnnotation(explain = "绑定人手机号")
    private String mobile = "";


    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "WatchBindInfo{" +
                "role=" + role +
                ", roleDesc='" + roleDesc + '\'' +
                ", createTime='" + createTime + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
