package com.qding.api.dozer.convert.legou.order;

import java.util.ArrayList;
import java.util.List;

import org.dozer.CustomConverter;

import com.qding.order.domain.OrderPromotion;

/**
 * 确认订单中的订单优惠活动
 * @author lichao
 *
 */
public class ConfirmOrderOrderPromotionConvert implements CustomConverter{

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		
		List<OrderPromotion> list = (List<OrderPromotion>) sourceFieldValue;
		
		if(list == null) {
			return null;
		}
		
		List<com.qding.api.call.app.qding.v1_3_0.struct.legou.order.OrderPromotion> ps = new ArrayList<>();
		
		for(OrderPromotion p : list) {
			if(p.getType() == 2) {
				
				ps.add( new com.qding.api.call.app.qding.v1_3_0.struct.legou.order.OrderPromotion(
						
						p.getPromotionId(),
						p.getPromotionName(),
						p.getDiscount()));
			}
		}
		
		return ps;
	}

}
