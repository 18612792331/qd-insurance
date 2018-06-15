package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * 添加到购物车					
 * @author lichao
 *
 */
public class AddCartResponseData extends ResponseData{


	private static final long serialVersionUID = 7753356867180265461L;

	@ExplainAnnotation(explain = "购物车品类数")
	private Integer cartCount=0;
 
	public AddCartResponseData() {

	}

	public Integer getCartCount() {
		return cartCount;
	}

	public void setCartCount(Integer cartCount) {
		this.cartCount = cartCount;
	}

	@Override
	public String toString() {
		return "AddCartResponseData{" +
				"cartCount=" + cartCount +
				'}';
	}
}
