package com.qding.api.verifycode;

import com.qding.api.sms.SmsAction;
import com.qding.api.verifycode.store.StoreDevice;


public class VerifyCodeConfig {

	private SmsAction action;
	
	private String code;
	
	private String identity;
	
	private StoreDevice store;

	private String activityKey;

	private String activityName;
	

	public VerifyCodeConfig(SmsAction action, String code, String identity,
			StoreDevice store) {
		super();
		this.action = action;
		this.code = code.toLowerCase();
		this.identity = identity;
		this.store = store;
	}

	public VerifyCodeConfig(SmsAction action, String code, String identity,
							StoreDevice store,String activityKey,String activityName) {
		super();
		this.action = action;
		this.code = code.toLowerCase();
		this.identity = identity;
		this.store = store;
		this.activityKey =activityKey;
		this.activityName = activityName;
	}
	public String getActivityKey() {
		return activityKey;
	}

	public void setActivityKey(String activityKey) {
		this.activityKey = activityKey;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public SmsAction getAction() {
		return action;
	}

	public void setAction(SmsAction action) {
		this.action = action;
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
}
