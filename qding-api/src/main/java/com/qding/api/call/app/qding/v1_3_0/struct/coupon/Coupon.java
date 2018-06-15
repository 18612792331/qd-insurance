package com.qding.api.call.app.qding.v1_3_0.struct.coupon;

import java.io.Serializable;

public class Coupon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3258142233519609181L;

	private String code;
	
    private Integer status;
    
    private String price;
    
    private Long startTime;
    
    private Long endTime;
    
    private String batchId;
    
    private String batchName;
    
    private String[] productNoNames;
    
    //1：代表地区券 2：代表通用券
  	private Integer couponTemplateType;
  	
    public Coupon() {

    }

    public Integer getCouponTemplateType() {
		return couponTemplateType;
	}

	public void setCouponTemplateType(Integer couponTemplateType) {
		this.couponTemplateType = couponTemplateType;
	}

	public String[] getProductNoNames() {
		return productNoNames;
	}
    
    public void setProductNoNames(String[] productNoNames) {
		this.productNoNames = productNoNames;
	}
    
	public String getBatchId() {
		return batchId;
	}


	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}


	public String getBatchName() {
		return batchName;
	}


	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

}
