package com.qding.api.call.app.qding.v1_3_0.struct.payment;

import java.io.Serializable;

public class WeixinPayArgument implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1488152896307703037L;

	private String appId;

	private String signPackage;

	private String partnerId;

	private String sign;

	private String nonceString;

	private String prepayId;

	private String timestamp;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public WeixinPayArgument() {

	}

	public WeixinPayArgument(String appId,String signPackage, String partnerId, String sign,
			String nonceString, String prepayId, String timestamp) {
		super();
		this.appId = appId;
		this.signPackage = signPackage;
		this.partnerId = partnerId;
		this.sign = sign;
		this.nonceString = nonceString;
		this.prepayId = prepayId;
		this.timestamp = timestamp;
	}

	public String getSignPackage() {
		return signPackage;
	}

	public void setSignPackage(String signPackage) {
		this.signPackage = signPackage;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNonceString() {
		return nonceString;
	}

	public void setNonceString(String nonceString) {
		this.nonceString = nonceString;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}	
}
