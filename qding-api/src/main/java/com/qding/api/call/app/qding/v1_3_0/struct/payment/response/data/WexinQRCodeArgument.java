package com.qding.api.call.app.qding.v1_3_0.struct.payment.response.data;

import java.io.Serializable;

public class WexinQRCodeArgument implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 6967306290117743122L;

	private String qrCodeUrl;

	public WexinQRCodeArgument() {

	}

	public WexinQRCodeArgument(String qrCodeUrl) {
		super();
		this.qrCodeUrl = qrCodeUrl;
	}


	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	
	
}
