package com.qding.api.dozer.convert.neighbor;

import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.util.ImageUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.storage.qiniu.ImgServiceForAPPInPrivate;
import com.qding.framework.common.util.QDStringUtil;
import com.qiniu.common.QiniuException;
import org.dozer.CustomConverter;

/**
 * Created by qd on 2016/10/11.
 */
public class MemberImgConvert implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        String imgUrl="";
        if(QDStringUtil.isNotNull(sourceFieldValue)) {
            imgUrl = String.valueOf(sourceFieldValue);
            if(imgUrl.startsWith("https:") || QDStringUtil.isEmpty(imgUrl)) return imgUrl;
            if(imgUrl.startsWith("http:")) return imgUrl.replace("http:","https:");
            if (imgUrl.startsWith("qiniu:qding:")){
                ImgServiceForAPPInPrivate imgServiceForAPP =  ApplicationContextUtil.getBeansOfType(ImgServiceForAPPInPrivate.class);
                try {
                    String tmpUrl = imgServiceForAPP.GetDownloadThumbnailUrl(imgUrl,2, 150, 150);
                    if(tmpUrl.startsWith("http:")){
                        imgUrl = tmpUrl.replace("http:","https:");
                    }  if(tmpUrl.startsWith("https:")){
                        imgUrl = tmpUrl;
                    }else{
                        imgUrl="";
                    }
                    return imgUrl;
                } catch (QiniuException e) {
                    e.printStackTrace();
                }
            }
            return "https://"+imgUrl;
        }
        return imgUrl;
    }
}
