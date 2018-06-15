package com.qding.api.dozer.convert.housekeeper;


import com.qding.framework.common.util.QDStringUtil;
import org.dozer.CustomConverter;


/**
 * 隐藏手机号中间四位
 * @author jiawenzheng
 *
 */
public class MobileHiddenConvert implements CustomConverter{

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {

		System.out.println("=======================================================================================");
		String mobiles =  String.valueOf(sourceFieldValue);
		String[] mobile = mobiles.split("/");
		StringBuffer mobileStr = new StringBuffer("");
		for (int i=0;i<mobile.length;i++) {
			if (mobile[i].length()==11){
				StringBuffer mobileBuffer = new StringBuffer( mobile[i]);
				mobileStr.append(mobileBuffer.replace(3,7,"****").toString()+"/");
			}
		}
		if (QDStringUtil.isNotEmpty(mobileStr.toString())) {
			mobileStr = mobileStr.replace(mobileStr.length()-1,mobileStr.length(),"");
		} else {
			mobileStr.append(mobiles);
		}
		return mobileStr.toString();
	}

}
