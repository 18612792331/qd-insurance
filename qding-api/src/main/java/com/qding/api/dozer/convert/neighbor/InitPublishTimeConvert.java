package com.qding.api.dozer.convert.neighbor;

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

        String interval="";
        if (QDStringUtil.isNotNull(sourceFieldValue)){
            Long publishTime =  Long.valueOf(String.valueOf(sourceFieldValue));
            interval =  RelativeDateFormat.format(publishTime);
        }
        return interval;
    }
}
