package com.qding.api.call.app.qding.v1_3_2.struct.popularize;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/31.
 */
public class PopularizeUserApplyDto extends PopularizeUser implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7605651184265919002L;

	/**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String bankCardNum;

    /**
     * 银行ID
     */
    private Long bankId;

    /**
     * 开户行
     */
    private String bankBranch = "";



    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public PopularizeUserApplyDto(){

    }
}
