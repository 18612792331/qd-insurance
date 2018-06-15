package com.qding.api.call.app.qding.v1_3_2.struct.popularize;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/31.
 */
public class PopularizeUserPassDto extends PopularizeUser implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8499163546792626578L;

	/**
     * 推广员ID
     */
    private String promoterId;

    /**
     * 注册日期
     */
    private Long registerAt;

    /**
     * 银行卡号
     */
    private String bankCardNum;

    /**
     * 开户行
     */
    private String bankBranch;

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public String getPromoterId() {
        return promoterId;
    }

    public void setPromoterId(String promoterId) {
        this.promoterId = promoterId;
    }

    public Long getRegisterAt() {
        return registerAt;
    }

    public void setRegisterAt(Long registerAt) {
        this.registerAt = registerAt;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }
}
