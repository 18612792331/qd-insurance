package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.ConfirmOrder;
import com.qding.api.struct.ResponseData;

public class ConfirmOrderResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4132430180751390251L;

	private ConfirmOrder entity;
	
	public ConfirmOrderResponseData() {

	}

	public ConfirmOrderResponseData(ConfirmOrder entity) {
		super();
		this.entity = entity;
	}

	public ConfirmOrder getEntity() {
		return entity;
	}

	public void setEntity(ConfirmOrder entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "ConfirmOrderResponseData [entity=" + entity + ", toString()="
				+ super.toString() + "]";
	}

}
