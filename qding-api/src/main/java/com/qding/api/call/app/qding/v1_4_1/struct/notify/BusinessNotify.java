package com.qding.api.call.app.qding.v1_4_1.struct.notify;

import java.io.Serializable;

public class BusinessNotify implements Serializable{

	private static final long serialVersionUID = -3789023608341933868L;

	private String id;

	private String title;

	private Long createTime;

	private String content;

	private String orderId;

	private SkipBean entity;

	public SkipBean getEntity() {
		return entity;
	}

	public void setEntity(SkipBean entity) {
		this.entity = entity;
	}

	public BusinessNotify() {

	}

	public BusinessNotify(String id, String title, Long createTime, String content, String orderId, SkipBean entity) {
		this.id = id;
		this.title = title;
		this.createTime = createTime;
		this.content = content;
		this.orderId = orderId;
		this.entity = entity;
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


}
