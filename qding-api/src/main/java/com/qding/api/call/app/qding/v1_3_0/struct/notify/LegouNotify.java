package com.qding.api.call.app.qding.v1_3_0.struct.notify;

import java.io.Serializable;

public class LegouNotify implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3789023608341933868L;

	private String id;
	
	private String title;
	
	private Long createTime;
	
	private String content;
	
	private String orderId;
	
	private String totalPrice;
	
	private String goodsName;
	
	private String receiveAddress;
	
	public LegouNotify() {

	}

	public LegouNotify(String id, String title, Long createTime,
			String content, String orderId, String totalPrice,
			String goodsName, String receiveAddress) {
		super();
		this.id = id;
		this.title = title;
		this.createTime = createTime;
		this.content = content;
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.goodsName = goodsName;
		this.receiveAddress = receiveAddress;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	
}
