package com.qding.api.dozer.convert.wallet;

import org.dozer.CustomConverter;

import com.qding.framework.common.util.QDStringUtil;
import com.qding.useraccount.common.AccountOptTypeEnum;

public class GetOptTypeNameByTypeConvert  implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
     
      if (QDStringUtil.isNotNull(sourceFieldValue)) {
          
           AccountOptTypeEnum optType = (AccountOptTypeEnum)sourceFieldValue;
          
          int payTypeValue = optType.value;
          
          return payTypeValue;
          
      } else {
          
          return "";
      }
    
    }

}
