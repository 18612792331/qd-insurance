package com.qding.api.call.app.qding.v1_3_2.struct.popularize;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/31.
 */
public class PopularizeUser implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4661536802472319655L;

	/**
     * 用户会员ID
     */
    private String memberId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 身份证号
     */
    private String identityCard;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }
}
