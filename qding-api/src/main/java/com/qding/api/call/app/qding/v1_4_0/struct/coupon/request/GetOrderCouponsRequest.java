package com.qding.api.call.app.qding.v1_4_0.struct.coupon.request;

import com.google.common.collect.Lists;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;

/**
 * 获取用户某个项目下某个业态的所有可用优惠券(不包含未生效的)		
 * @author lichao
 *
 */
@Validate
public class GetOrderCouponsRequest extends com.qding.api.call.app.qding.v1_3_0.struct.coupon.request.GetOrderCouponsRequest{


	private List<String> promotionIds = Lists.newArrayList();

	public List<String> getPromotionIds() {
		return promotionIds;
	}

	public void setPromotionIds(List<String> promotionIds) {
		this.promotionIds = promotionIds;
	}

	public GetOrderCouponsRequest(){

	}

	public GetOrderCouponsRequest(List<String> promotionIds) {
		super();
		this.promotionIds = promotionIds;
	}

	@Override
	public String toString() {
		return "GetOrderCouponsRequest [promotionIds=" + promotionIds
				+ ", toString()=" + super.toString() + "]";
	}


}
