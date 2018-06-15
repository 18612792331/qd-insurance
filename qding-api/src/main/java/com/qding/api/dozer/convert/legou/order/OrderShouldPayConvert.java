package com.qding.api.dozer.convert.legou.order;

import org.dozer.CustomConverter;

import com.qding.order.domain.OrderBase;
import com.qding.order.enums.PayStatusEnum;

/**
 * 订单应付 
 * @author lichao
 *
 */
public class OrderShouldPayConvert implements CustomConverter{

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		
		OrderBase orderBase = (OrderBase) sourceFieldValue;
		
		//如果已支付 ， 应付就是0, 否则应付等于totalRealPay
		if(PayStatusEnum.PAY_STATUS_PAYSUCESS.getValue() == orderBase.getPayStatus()) {
			
			return "0";
		}
		else {
			return orderBase.getTotalRealpay();
		}
	}

}
