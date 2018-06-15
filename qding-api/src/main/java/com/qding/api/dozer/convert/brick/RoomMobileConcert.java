package com.qding.api.dozer.convert.brick;

import com.google.common.collect.Lists;
import com.qding.framework.common.util.QDStringUtil;
import org.dozer.CustomConverter;

import java.util.List;

/**
 * Created by andy on 15-11-24.
 */
public class RoomMobileConcert  implements CustomConverter {


    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        List<String> list = Lists.newArrayList();

        if(QDStringUtil.isNotNull(sourceFieldValue) &&  QDStringUtil.isNotEmpty(String.valueOf(sourceFieldValue))){

            String mobileStr = String.valueOf(sourceFieldValue);
            String mobileArray[] = mobileStr.split("/");

            if (QDStringUtil.isNotNull(mobileArray) && mobileArray.length>0) {

                for (int i=0;i<mobileArray.length;i++) {
                    String mobile = mobileArray[i];
                    if (mobile.length()==11) {

                        StringBuffer mobileBuffer = new StringBuffer(mobile);
                        mobileBuffer.replace(3,7,"****");
                        mobileArray[i]=mobileBuffer.toString();
                    }
                }
            }
            return mobileArray;
        }

        return list.toArray(new String[]{});
    }
}
