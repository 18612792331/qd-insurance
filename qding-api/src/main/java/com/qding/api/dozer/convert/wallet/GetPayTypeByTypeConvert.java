package com.qding.api.dozer.convert.wallet;

import org.dozer.CustomConverter;

import com.qding.framework.common.util.QDStringUtil;
import com.qding.useraccount.common.PayTypeEnum;

public class GetPayTypeByTypeConvert implements CustomConverter  {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
     
        if (QDStringUtil.isNotNull(sourceFieldValue)) {
            
            PayTypeEnum payType = (PayTypeEnum) sourceFieldValue;
            
            String paymentType = payType.value;
             
            return Integer.parseInt(paymentType);
            
        } else {
            
            return 0;
        }
       
    }

}
