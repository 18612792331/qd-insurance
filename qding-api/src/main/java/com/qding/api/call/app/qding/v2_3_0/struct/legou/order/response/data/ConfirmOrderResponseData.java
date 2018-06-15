package com.qding.api.call.app.qding.v2_3_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_0.struct.legou.order.ConfirmOrder;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrderGroup;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.PropertySelfOrderGroup;
import com.qding.api.struct.ResponseData;

public class ConfirmOrderResponseData extends ResponseData{

	private static final long serialVersionUID = 4132430180751390251L;

	@ExplainAnnotation(explain = "订单确认页")
	private ConfirmOrder entity;

	@ExplainAnnotation(explain = "物流配送组")
	private OrderGroup logisticsDis;

	@ExplainAnnotation(explain = "物业自提组")
	private PropertySelfOrderGroup propertySelf;


	public ConfirmOrderResponseData() {

	}

	public OrderGroup getLogisticsDis() {
		return logisticsDis;
	}

	public void setLogisticsDis(OrderGroup logisticsDis) {
		this.logisticsDis = logisticsDis;
	}

	public PropertySelfOrderGroup getPropertySelf() {
		return propertySelf;
	}

	public void setPropertySelf(PropertySelfOrderGroup propertySelf) {
		this.propertySelf = propertySelf;
	}

	public ConfirmOrder getEntity() {
		return entity;
	}

	public void setEntity(ConfirmOrder entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "ConfirmOrderResponseData{" +
				"entity=" + entity +
				", logisticsDis=" + logisticsDis +
				", propertySelf=" + propertySelf +
				'}';
	}
}
