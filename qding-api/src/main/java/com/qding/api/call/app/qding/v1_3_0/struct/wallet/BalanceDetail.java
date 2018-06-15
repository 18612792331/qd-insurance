package com.qding.api.call.app.qding.v1_3_0.struct.wallet;

import java.io.Serializable;


public class BalanceDetail extends AccountDetail implements Serializable {

    
	private static final long serialVersionUID = -1628802637719244508L;
	
	
	public BalanceDetail(){
		
	}
	
	
	 public BalanceDetail(
				String businessTypeName, String payTypeName) {
			super();
			this.businessTypeName = businessTypeName;
			this.payTypeName = payTypeName;

		}
	
    /**
     * 业务类型
     */
    private String businessTypeName;
    
    /**
     *  支付方式
     */
    private String payTypeName;
    

    /**
     * @return Returns the businessTypeName.
     */
    public String getBusinessTypeName() {
        return businessTypeName;
    }

    /**
     * @param businessTypeName The businessTypeName to set.
     */
    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    /**
     * @return Returns the payTypeName.
     */
    public String getPayTypeName() {
        return payTypeName;
    }


    /**
     * @param payTypeName The payTypeName to set.
     */
    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

}
