package com.qding.api.dozer.convert.wallet;

import org.dozer.CustomConverter;

import com.qding.framework.common.util.QDStringUtil;
import com.qding.useraccount.common.PredepositBusinessTypeEnum;

public class GetBuinessTypeNameByTypeConvert implements CustomConverter  {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
     
        if (QDStringUtil.isNotNull(sourceFieldValue)) {
            
            PredepositBusinessTypeEnum bussinessType = (PredepositBusinessTypeEnum) sourceFieldValue;
            
            String bussinessTypeName = bussinessType.description;
             
            return bussinessTypeName; 
            
        } else {
            
            return "";
        }
       
    }

}
