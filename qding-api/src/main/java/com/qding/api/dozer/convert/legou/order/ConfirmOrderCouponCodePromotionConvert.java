package com.qding.api.dozer.convert.legou.order;

import java.util.List;

import org.dozer.CustomConverter;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.CouponsPromotion;
import com.qding.order.domain.OrderPromotion;

/**
 * 确认订单中的千丁券优惠
 * @author lichao
 *
 */
public class ConfirmOrderCouponCodePromotionConvert implements CustomConverter{

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		
		List<OrderPromotion> list = (List<OrderPromotion>) sourceFieldValue;
		
		if(list == null) {
			return null;
		}
		
		for(OrderPromotion p : list) {
			if(p.getType() == 3) {	
					return new CouponsPromotion(
						p.getPromotionId(),
						p.getPromotionName(),
						p.getDiscount(),
						p.getTotalCouponPrice());
			}
		}
		
		return null;
	}

}
