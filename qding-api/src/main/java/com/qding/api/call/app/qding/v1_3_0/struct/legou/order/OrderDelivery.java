package com.qding.api.call.app.qding.v1_3_0.struct.legou.order;

import java.io.Serializable;

public class OrderDelivery implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4273989469230360142L;
	
	private String deliveryId;
	private String receiveUserName;
    private String receiveUserPhone;
    private String deliveryAddress;
    private String receivePostCode;
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
