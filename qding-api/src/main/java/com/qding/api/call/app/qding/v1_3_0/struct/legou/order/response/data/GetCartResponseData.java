package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Cart;
import com.qding.api.struct.ResponseData;

/**
 * 我的购物车列表					
 * @author lichao
 *
 */
public class GetCartResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8498892271283371274L;

	private List<Cart> list;
	
	public GetCartResponseData() {

	}

	public GetCartResponseData(List<Cart> list) {
		super();
		this.list = list;
	}

	public List<Cart> getList() {
		return list;
	}

	public void setList(List<Cart> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetCartResponseData [list=" + list + ", toString()="
				+ super.toString() + "]";
	}
	
}
