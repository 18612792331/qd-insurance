package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Order;
import com.qding.api.struct.ResponseData;

/**
 * 查看订单详情					
 * @author lichao
 *
 */
public class GetOrderResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3846164828888695717L;

	private Order entity;
	
	public GetOrderResponseData() {

	}

	public GetOrderResponseData(Order entity) {
		super();
		this.entity = entity;
	}

	public Order getEntity() {
		return entity;
	}

	public void setEntity(Order entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "GetOrderResponseData [entity=" + entity + ", toString()="
				+ super.toString() + "]";
	}
	
}
