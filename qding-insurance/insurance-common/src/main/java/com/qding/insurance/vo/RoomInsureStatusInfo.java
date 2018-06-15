package com.qding.insurance.vo;

import java.io.Serializable;

//房屋投保状态 → 一个房屋可能上多份保险,每个单证对应一个状态
public class RoomInsureStatusInfo implements Serializable{

	private static final long serialVersionUID = -5591784728276089606L;
	private String id;
	private Integer status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
