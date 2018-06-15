package com.qding.api.call.app.qding.v1_3_0.struct.wallet;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;


public class AccountWalletHome implements Serializable{

   
    private static final long serialVersionUID = 7187709412211504176L;
    
    
    @ExplainAnnotation (explain = "会员ID")
    private String memberId;
    
    @ExplainAnnotation (explain = "账户金额汇总")
    private AccountCollect account;
    
    @ExplainAnnotation (explain = "积分")
    private Long accountIntegral = 0L;
    
    @ExplainAnnotation (explain = "千丁券总金额")
    private int accountQdTicket;
    
    @ExplainAnnotation (explain = "宣传语")
    private String[] slogan ={};

    @ExplainAnnotation (explain = "详情")
    private String sloganDetail ="";
    
    @ExplainAnnotation (explain = "钱包状态信息")
    private WalletStatus walletStatus;

    @ExplainAnnotation (explain = "礼包提醒跳转")
    private GiftBean giftEntity;

    public GiftBean getGiftEntity() {
        return giftEntity;
    }

    public void setGiftEntity(GiftBean giftEntity) {
        this.giftEntity = giftEntity;
    }

    public WalletStatus getWalletStatus() {
		return walletStatus;
	}
    
    public void setWalletStatus(WalletStatus walletStatus) {
		this.walletStatus = walletStatus;
	}
    
    /**
     * @return Returns the slogan.
     */
    public String[] getSlogan() {
        return slogan;
    }

    /**
     * @param slogan The slogan to set.
     */
    public void setSlogan(String[] slogan) {
        this.slogan = slogan;
    }

    /**
     * @return Returns the memberId.
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId The memberId to set.
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * @return the account
     */
    public AccountCollect getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(AccountCollect account) {
        this.account = account;
    }

    /**
     * @return Returns the accountIntegral.
     */
    public Long getAccountIntegral() {
        return accountIntegral;
    }

    /**
     * @param accountIntegral The accountIntegral to set.
     */
    public void setAccountIntegral(Long	 accountIntegral) {
        this.accountIntegral = accountIntegral;
    }

    /**
     * @return Returns the accountQdTicket.
     */
    public int getAccountQdTicket() {
        return accountQdTicket;
    }

    /**
     * @param accountQdTicket The accountQdTicket to set.
     */
    public void setAccountQdTicket(int accountQdTicket) {
        this.accountQdTicket = accountQdTicket;
    }

    public String getSloganDetail() {
        return sloganDetail;
    }

    public void setSloganDetail(String sloganDetail) {
        this.sloganDetail = sloganDetail;
    }
}
