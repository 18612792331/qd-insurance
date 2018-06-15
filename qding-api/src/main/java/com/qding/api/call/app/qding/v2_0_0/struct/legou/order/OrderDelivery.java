package com.qding.api.call.app.qding.v2_0_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class OrderDelivery implements Serializable{

	private static final long serialVersionUID = 4273989469230360142L;

	@ExplainAnnotation(explain = "快递地址ID")
	private String deliveryId;

	@ExplainAnnotation(explain = "收件人名称")
	private String receiveUserName;

	@ExplainAnnotation(explain = "收件人手机")
    private String receiveUserPhone;

	@ExplainAnnotation(explain = "收件地址")
    private String deliveryAddress;

	@ExplainAnnotation(explain = "邮编")
    private String receivePostCode;

	@ExplainAnnotation(explain = "快递费用")
    private String expressPrice = "0";

    public OrderDelivery() {

    }

	public OrderDelivery(String deliveryId, String receiveUserName,
			String receiveUserPhone, String deliveryAddress,
			String receivePostCode, String expressPrice) {
		super();
		this.deliveryId = deliveryId;
		this.receiveUserName = receiveUserName;
		this.receiveUserPhone = receiveUserPhone;
		this.deliveryAddress = deliveryAddress;
		this.receivePostCode = receivePostCode;
		this.expressPrice = expressPrice;
	}

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getReceiveUserName() {
		return receiveUserName;
	}

	public void setReceiveUserName(String receiveUserName) {
		this.receiveUserName = receiveUserName;
	}

	public String getReceiveUserPhone() {
		return receiveUserPhone;
	}

	public void setReceiveUserPhone(String receiveUserPhone) {
		this.receiveUserPhone = receiveUserPhone;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getReceivePostCode() {
		return receivePostCode;
	}

	public void setReceivePostCode(String receivePostCode) {
		this.receivePostCode = receivePostCode;
	}

	public String getExpressPrice() {
		return expressPrice;
	}

	public void setExpressPrice(String expressPrice) {
		this.expressPrice = expressPrice;
	}
}
