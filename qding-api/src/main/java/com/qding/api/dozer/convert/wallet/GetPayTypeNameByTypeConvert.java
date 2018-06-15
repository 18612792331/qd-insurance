package com.qding.api.dozer.convert.wallet;

import org.dozer.CustomConverter;

import com.qding.framework.common.util.QDStringUtil;
import com.qding.useraccount.common.PayTypeEnum;

public class GetPayTypeNameByTypeConvert implements CustomConverter  {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
     
        if (QDStringUtil.isNotNull(sourceFieldValue)) {
            
            PayTypeEnum payType = (PayTypeEnum) sourceFieldValue;
            
            String payTypeName = payType.description;
             
            return payTypeName;
            
        } else {
            
            return "";
        }
       
    }

}
