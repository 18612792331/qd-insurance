package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.AfterCreateOrder;
import com.qding.api.struct.ResponseData;

/**
 * 生成订单					
 * @author lichao
 *
 */
public class MakeOrderResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3265682809311311837L;
 
    private AfterCreateOrder entity;
    
    public MakeOrderResponseData() {
    	
	}

	public MakeOrderResponseData(AfterCreateOrder entity) {
		super();
		this.entity = entity;
	}

	public AfterCreateOrder getEntity() {
		return entity;
	}

	public void setEntity(AfterCreateOrder entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "MakeOrderResponseData [entity=" + entity + ", toString()="
				+ super.toString() + "]";
	}

}
