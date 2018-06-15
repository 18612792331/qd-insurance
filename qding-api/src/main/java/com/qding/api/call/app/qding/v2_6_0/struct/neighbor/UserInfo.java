package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 用户
 *
 * @author lichao
 */
@XStreamAlias(value = "userInfo")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -742786027207933950L;

    @ExplainAnnotation(explain = "用户ID")
    private String userId;

    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

    @ExplainAnnotation(explain = "用户头像")
    private String userHeadImageUrl;

    @ExplainAnnotation(explain = "用户姓名")
    private String userName;

    @ExplainAnnotation(explain = "签名")
    private String userSign;

    @ExplainAnnotation(explain = "性别")
    private Integer userSex;

    @ExplainAnnotation(explain = "群组状态：是否可能发言 1可以发言  0禁言（不可以在群组发言）")
    private Integer isSay;

    @ExplainAnnotation(explain = "用户状态：是否冻结 1是 0否")
    private Integer isFreeze;

    public UserInfo() {

    }

    public UserInfo(String userId, String userHeadImageUrl, String userName,
                    String userSign, Integer userSex) {
        super();
        this.userId = userId;
        this.userHeadImageUrl = userHeadImageUrl;
        this.userName = userName;
        this.userSign = userSign;
        this.userSex = userSex;
    }

    public UserInfo(String userId, String memberId, String userHeadImageUrl, String userName, String userSign, Integer userSex, Integer isSay, Integer isFreeze) {
        this.userId = userId;
        this.memberId = memberId;
        this.userHeadImageUrl = userHeadImageUrl;
        this.userName = userName;
        this.userSign = userSign;
        this.userSex = userSex;
        this.isSay = isSay;
        this.isFreeze = isFreeze;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public Integer getUserSex() {
        return userSex;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserHeadImageUrl() {
        return userHeadImageUrl;
    }

    public void setUserHeadImageUrl(String userHeadImageUrl) {
        this.userHeadImageUrl = userHeadImageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUserSign() {
        return userSign;
    }

    public Integer getIsSay() {
        return isSay;
    }

    public void setIsSay(Integer isSay) {
        this.isSay = isSay;
    }

    public Integer getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Integer isFreeze) {
        this.isFreeze = isFreeze;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", userHeadImageUrl='" + userHeadImageUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", userSign='" + userSign + '\'' +
                ", userSex=" + userSex +
                ", isSay=" + isSay +
                ", isFreeze=" + isFreeze +
                '}';
    }
}
