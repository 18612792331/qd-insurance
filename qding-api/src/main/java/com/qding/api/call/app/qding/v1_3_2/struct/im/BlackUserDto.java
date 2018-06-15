package com.qding.api.call.app.qding.v1_3_2.struct.im;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/28.
 */
public class BlackUserDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6817397340447227974L;

	/**
     * 用户ID
     */
    private String userId;

    /**
     * 用户头像
     */
    private String userHeadImageUrl;

    /**
     * 用户名称
     */
    private String userName;

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

    public BlackUserDto(String userId, String userHeadImageUrl, String userName) {
        this.userId = userId;
        this.userHeadImageUrl = userHeadImageUrl;
        this.userName = userName;
    }

    public BlackUserDto(){

    }
}
