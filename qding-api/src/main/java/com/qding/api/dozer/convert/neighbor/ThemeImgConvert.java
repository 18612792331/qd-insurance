package com.qding.api.dozer.convert.neighbor;

import com.qding.framework.common.util.QDStringUtil;
import org.dozer.CustomConverter;

/**
 * Created by qd on 2016/10/11.
 */
public class ThemeImgConvert implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        String imgUrl="";
        if(QDStringUtil.isNotNull(sourceFieldValue)) {
            imgUrl = String.valueOf(sourceFieldValue);
            if(imgUrl.startsWith("https:")) return imgUrl;
            if(imgUrl.startsWith("http:")){
                imgUrl = imgUrl.replace("http:","https:");
                return imgUrl;
            }
            return "https://"+imgUrl;
        }
        return imgUrl;
    }
}
