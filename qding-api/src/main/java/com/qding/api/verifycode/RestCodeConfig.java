package com.qding.api.verifycode;

import com.qding.api.sms.SmsAction;
import com.qding.api.verifycode.send.SendChannel;
import com.qding.api.verifycode.store.StoreDevice;


public class RestCodeConfig {

	private int codeExpireAt;

	private long expireAt;

	private SmsAction action;

	private String code;

	private StoreDevice store;

	private SendChannel sendChannel;

	public RestCodeConfig(long expireAt, SmsAction action, String code,
						  StoreDevice store, SendChannel sendChannel, int codeExpireAt) {
		super();
		this.expireAt = expireAt;
		this.action = action;
		this.code = code;
		this.store = store;
		this.sendChannel = sendChannel;
		this.codeExpireAt = codeExpireAt;
	}

	public void setAction(SmsAction action) {
		this.action = action;
	}
	
	public SmsAction getAction() {
		return action;
	}
	
	public long getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(long expireAt) {
		this.expireAt = expireAt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public StoreDevice getStore() {
		return store;
	}

	public void setStore(StoreDevice store) {
		this.store = store;
	}

	public SendChannel getSendChannel() {
		return sendChannel;
	}

	public void setSendChannel(SendChannel sendChannel) {
		this.sendChannel = sendChannel;
	}

	public int getCodeExpireAt() {
		return codeExpireAt;
	}

	public void setCodeExpireAt(int codeExpireAt) {
		this.codeExpireAt = codeExpireAt;
	}
}
