package com.qding.api.dozer.convert.wallet;

import com.qding.api.constant.Constant;
import org.apache.thrift.TException;
import org.dozer.CustomConverter;

import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.util.QDStringUtil;

public class GetProductNameByNoConvert implements CustomConverter  {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
     
        if (QDStringUtil.isNotNull(sourceFieldValue)) {
            
            try {
                
                String productNo = sourceFieldValue.toString();
                
                String productName = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_PRODUCT_NO.getGroupName(), productNo);
          
                return productName;
                
            } catch (TException e) {
                
                return "";
            } 
        }else {
            return "";
        }
       
        
    }

}
