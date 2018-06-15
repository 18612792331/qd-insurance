package com.qding.api.struct;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="data")
public class ResponseData implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2767300673516866302L;
	
	private String message;

	private String toast;

	private String baseToken;

	public ResponseData() {

	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToast() {
		return toast;
	}

	public void setToast(String toast) {
		this.toast = toast;
	}

	public String getBaseToken() {
		return baseToken;
	}

	public void setBaseToken(String baseToken) {
		this.baseToken = baseToken;
	}

	@Override
	public String toString() {
		return "ResponseData [message=" + message + ",toast= " + toast + " ]";
	}
	
}
