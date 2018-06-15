package com.qding.api.dozer.convert.coupon;

import org.dozer.CustomConverter;

/**
 * 优惠券批次名称
 * @author lichao
 *
 */
public class CouponsBatchNameConvert implements CustomConverter{

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		
		return "100%抵扣";
	}

}
