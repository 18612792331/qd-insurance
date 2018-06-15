package com.qding.api.dozer.convert.housekeeper;

import com.qding.framework.common.util.QDStringUtil;
import org.dozer.CustomConverter;

/**
 * Created by qd on 2016/6/17.
 */
public class LongitudeConvert implements CustomConverter {
    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        if (QDStringUtil.isNotNull(sourceFieldValue)) {
            String[] lbsArray = String.valueOf(sourceFieldValue).split(",");
            if(lbsArray.length==2) {
                return lbsArray[1];
            }
        }
        return "";
    }
}
