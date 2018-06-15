package com.qding.api.call.app.qding.v1_3_2.struct.popularize;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/28.
 */
public class BankDto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7549722204696923501L;
	
	

	/**
     * 银行ID
     */
    private String bankId;

    /**
     * 银行名称
     */
    private String bankName;


    /**
     * 银行搜索条件
     */
    private String bankQueryKeyWord;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankQueryKeyWord() {
        return bankQueryKeyWord;
    }

    public void setBankQueryKeyWord(String bankQueryKeyWord) {
        this.bankQueryKeyWord = bankQueryKeyWord;
    }

    public BankDto(String bankId, String bankName, String bankQueryKeyWord) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankQueryKeyWord = bankQueryKeyWord;
    }

    public BankDto(){

    }
}
