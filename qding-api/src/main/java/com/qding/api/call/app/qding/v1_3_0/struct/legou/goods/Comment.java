package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods;

import java.io.Serializable;

public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5645101009553002093L;
	
	private String key = "取货地址";
	
	private String value;
	
	public Comment(){
		
	}

	public Comment(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
