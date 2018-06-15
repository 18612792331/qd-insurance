package com.qding.api.verifycode;

import com.qding.api.verifycode.store.StoreDevice;

public class ImgCodeConfig {

	private String code;

	private String identity;

	private StoreDevice store;

	private boolean isExpireAt = true;

	public boolean isExpireAt() {
		return isExpireAt;
	}

	public void setExpireAt(boolean isExpireAt) {
		this.isExpireAt = isExpireAt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public StoreDevice getStore() {
		return store;
	}

	public void setStore(StoreDevice store) {
		this.store = store;
	}

	public ImgCodeConfig( String code, String identity, StoreDevice store, boolean isExpireAt) {
		this.code = code;
		this.identity = identity;
		this.store = store;
		this.isExpireAt = isExpireAt;
	}
}
