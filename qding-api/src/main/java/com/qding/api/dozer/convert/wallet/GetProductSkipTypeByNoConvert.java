package com.qding.api.dozer.convert.wallet;

import org.dozer.CustomConverter;

import com.qding.api.constant.Constant;
import com.qding.framework.common.util.QDStringUtil;

public class GetProductSkipTypeByNoConvert implements CustomConverter  {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
     
    	  
    	if (QDStringUtil.isNull(sourceFieldValue)) return "";
    		
    	  String productNo = sourceFieldValue.toString();
          
          String skipType = Constant.getproductSkipType(productNo);
    
          return skipType;
        
    }

}
