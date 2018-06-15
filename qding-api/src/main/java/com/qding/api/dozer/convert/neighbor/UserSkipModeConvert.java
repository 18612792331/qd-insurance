package com.qding.api.dozer.convert.neighbor;

import com.qding.api.constant.Constant;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.util.QDStringUtil;
import org.dozer.CustomConverter;

/**
 * Created by qd on 2015/12/17.
 */
public class UserSkipModeConvert implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

         String skipModeStr = "";
        if (QDStringUtil.isNotNull(sourceFieldValue)) {
            SkipModeFitting skipMode =  ApplicationContextUtil.getBeansOfType(SkipModeFitting.class);
            skipModeStr = skipMode.fittingSkipModelByOnlyId("2.6.0", Constant.SkipNo.LJSY_3015.toInteger(),String.valueOf(sourceFieldValue));
        }
        return skipModeStr;
    }
}
