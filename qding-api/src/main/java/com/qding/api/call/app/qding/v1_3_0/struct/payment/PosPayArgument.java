package com.qding.api.call.app.qding.v1_3_0.struct.payment;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class PosPayArgument implements Serializable{

	private static final long serialVersionUID = 6967306290117743122L;

	@ExplainAnnotation (explain = "二维码")
	private String qrCodeUrl;

	@ExplainAnnotation (explain = "条形码",desc = "目前只针对pos支付有")
	private String barCodeUrl;

	@ExplainAnnotation (explain = "流水号",desc = "目前只针对pos支付有")
	private String tradeNo;
	
	public PosPayArgument() {

	}

	public PosPayArgument(String qrCodeUrl) {
		super();
		this.qrCodeUrl = qrCodeUrl;
	}

	public PosPayArgument(String qrCodeUrl, String barCodeUrl, String tradeNo) {
		super();
		this.qrCodeUrl = qrCodeUrl;
		this.barCodeUrl = barCodeUrl;
		this.tradeNo = tradeNo;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	public String getBarCodeUrl() {
		return barCodeUrl;
	}

	public void setBarCodeUrl(String barCodeUrl) {
		this.barCodeUrl = barCodeUrl;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
}
