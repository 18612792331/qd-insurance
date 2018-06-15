package com.qding.api.verifycode.send;

import com.qding.framework.common.exception.ServiceException;

public abstract class SendChannel {

	protected String identity;

	public abstract void send(String code) throws ServiceException;

	public abstract void send(String code,String activityName) throws ServiceException;
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public String getIdentity() {
		return identity;
	}

	public abstract void sendVoicVerfyCode (String mobile,String code)  throws ServiceException;
	
}
