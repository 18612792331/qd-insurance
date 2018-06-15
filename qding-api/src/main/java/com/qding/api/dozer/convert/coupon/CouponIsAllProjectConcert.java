package com.qding.api.dozer.convert.coupon;

import com.qding.framework.common.util.QDStringUtil;
import org.dozer.CustomConverter;

/**
 * Created by qd on 2015/9/23.
 */
public class CouponIsAllProjectConcert  implements CustomConverter {


    @Override
    public Object convert(Object existingDestinationFieldValue,
                          Object sourceFieldValue, Class<?> destinationClass,
                          Class<?> sourceClass) {

        // 1:全社区通用  0：不是全场通用
        Integer isAllProject = QDStringUtil.isNotNull(sourceFieldValue) &&  QDStringUtil.isNotEmpty(String.valueOf(sourceFieldValue)) ? 0 : 1;

        return isAllProject;
    }

}