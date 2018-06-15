package com.qding.api.dozer.convert.user;

import com.qding.framework.common.util.QDStringUtil;
import org.dozer.CustomConverter;

public class SetAddressConvert implements CustomConverter {

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {

		int isSetting = 0 ;
		if (QDStringUtil.isNotNull(sourceFieldValue)) {
			Integer version = Integer.parseInt(sourceFieldValue.toString());
			if (QDStringUtil.isNull(version) && version.intValue() == 2 ) {
				isSetting = 1;
			}
		}
		return  isSetting;
	}

}
