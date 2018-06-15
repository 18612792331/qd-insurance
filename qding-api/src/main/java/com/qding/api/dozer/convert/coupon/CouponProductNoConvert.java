package com.qding.api.dozer.convert.coupon;

import com.qding.framework.common.util.QDStringUtil;
import org.apache.thrift.TException;
import org.dozer.CustomConverter;

import com.qding.api.constant.Constant;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.dictionary.client.DictionaryClient;

/**
 * 优惠券批次名称
 * @author lichao
 *
 */
public class CouponProductNoConvert implements CustomConverter{

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {

		String[] productNoNames = null;
		if (QDStringUtil.isNotNull(sourceFieldValue)) {
			DictionaryClient dictionaryClient = ApplicationContextUtil.getBeansOfType(DictionaryClient.class);
			String[] productNos = (String[]) sourceFieldValue;
			productNoNames = new String[productNos.length];
			for(int i = 0; i < productNos.length; i ++) {

				try {
					productNoNames[i] = dictionaryClient.findDictValueByGroupNameAndDictKey(Constant.GROUP_NAME_PRODUCT_NO, productNos[i]);
				} catch (TException e) {
					e.printStackTrace();
				}

			}
		}

		return productNoNames;
	}

}
