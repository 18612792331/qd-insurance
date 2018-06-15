package com.qding.api.verifycode;

public class StoreCode {
	
	private String code;
	
	private Long expireAt;

	public StoreCode() {

	}
	
	public StoreCode(String code, long expireAt) {
		super();
		this.code = code;
		this.expireAt = expireAt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Long expireAt) {
		this.expireAt = expireAt;
	}
}