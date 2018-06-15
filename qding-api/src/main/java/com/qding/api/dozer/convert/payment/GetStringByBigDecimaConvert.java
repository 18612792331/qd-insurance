package com.qding.api.dozer.convert.payment;

import java.math.BigDecimal;

import org.dozer.CustomConverter;

public class GetStringByBigDecimaConvert implements CustomConverter  {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
     
        if (sourceFieldValue != null) {
            
        	BigDecimal d = (BigDecimal) sourceFieldValue;
        	
        	return String.valueOf(d.doubleValue());
        	
        } else {
            
            return "0";
        }
       
    }

}
