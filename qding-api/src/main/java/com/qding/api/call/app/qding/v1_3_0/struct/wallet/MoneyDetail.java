package com.qding.api.call.app.qding.v1_3_0.struct.wallet;

import com.qding.api.constant.Constant;

import java.io.Serializable;

public class MoneyDetail extends AccountDetail implements Serializable{

	
	private static final long serialVersionUID = -5813561442748828133L;



    /**
     * 跳转类型
     */
    private String skipType;
    
    /**
     * 跳转路径
     */
    private String orderUrl = "";

	/**
	 * 是否开通路径跳转
	 */
	private String skipStatus = Constant.SKIP_OK;  //0:不开通 1：开通

	public String getSkipStatus() {
		return skipStatus;
	}

	public void setSkipStatus(String skipStatus) {
		this.skipStatus = skipStatus;
	}

	public String getOrderUrl() {
		return orderUrl;
	}


	public void setOrderUrl(String orderUrl) {
		this.orderUrl = orderUrl;
	}


	public String getSkipType() {
		return skipType;
	}


	public void setSkipType(String skipType) {
		this.skipType = skipType;
	}



	public MoneyDetail(){
        
    }
	
}
