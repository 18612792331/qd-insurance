package com.qding.api.call.app.qding.v1_3_0.struct.wallet;

import java.io.Serializable;
import java.math.BigDecimal;

public class AccountCollect implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2769358447127294372L;

    /**
     * 可用预存款
     */
    private String availableAmount;

    /**
    *  总存入预存款
    */
    private String depositAmount;

    /**
    * 总成支出预存款
    */
    private String expenditureAmount;

    
    public String getAvailableAmount() {
		return availableAmount;
	}



	public void setAvailableAmount(String availableAmount) {
		this.availableAmount = availableAmount;
	}



	public String getDepositAmount() {
		return depositAmount;
	}



	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}



	public String getExpenditureAmount() {
		return expenditureAmount;
	}



	public void setExpenditureAmount(String expenditureAmount) {
		this.expenditureAmount = expenditureAmount;
	}



	public AccountCollect(){
        
    }

}
