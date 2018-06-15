package com.qding.api.call.app.qding.v2_4_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.Cart;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * 我的购物车列表					
 * @author lichao
 *
 */
public class GetCartResponseData extends ResponseData{


	private static final long serialVersionUID = -8498892271283371274L;

	@ExplainAnnotation(explain = "购物有效车列表")
	private List<Cart> effectiveList;

	@ExplainAnnotation(explain = "购物无效车列表")
	private List<Cart> invalidList;
	
	public GetCartResponseData() {

	}

	public List<Cart> getEffectiveList() {
		return effectiveList;
	}

	public void setEffectiveList(List<Cart> effectiveList) {
		this.effectiveList = effectiveList;
	}

	public List<Cart> getInvalidList() {
		return invalidList;
	}

	public void setInvalidList(List<Cart> invalidList) {
		this.invalidList = invalidList;
	}
}
