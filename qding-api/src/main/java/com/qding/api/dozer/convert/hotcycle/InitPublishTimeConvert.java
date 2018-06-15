package com.qding.api.dozer.convert.hotcycle;

import com.qding.api.util.RelativeDateFormat;
import com.qding.framework.common.util.QDStringUtil;
import org.dozer.CustomConverter;

/**
 * Created by qd on 2015/12/17.
 */
public class InitPublishTimeConvert implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        Long publishTime =  Long.valueOf(String.valueOf(sourceFieldValue));
        String interval="";
        if (QDStringUtil.isNotNull(publishTime)){
            interval =  RelativeDateFormat.format(publishTime);
        }
        return interval;
    }
}
