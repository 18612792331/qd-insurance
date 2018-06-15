package com.qding.api.dozer.convert.housekeeper;

import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.util.QDStringUtil;
import org.dozer.CustomConverter;

/**
 * Created by qd on 2017/2/25.
 */
public class HkServiceItemSkipModelConvert  implements CustomConverter {


    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        String skipNo = String.valueOf(sourceFieldValue);
        if(QDStringUtil.isNotEmpty(skipNo)){
            SkipModeFitting skipMode =  ApplicationContextUtil.getBeansOfType(SkipModeFitting.class);
            return  skipMode.fittingNoParameterSkipModel("3.0.0",Integer.parseInt(skipNo));

        }
        return "";
    }
}
