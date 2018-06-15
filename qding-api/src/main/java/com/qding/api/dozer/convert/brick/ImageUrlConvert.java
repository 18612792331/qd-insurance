package com.qding.api.dozer.convert.brick;

import com.google.common.collect.Lists;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.util.ImageUtil;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.util.QDStringUtil;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 获取最终图片路径
 * @author jiawenzheng
 *
 */
public class ImageUrlConvert implements CustomConverter{

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {

		ImageUtil imageUtil = ApplicationContextUtil.getBeansOfType(ImageUtil.class);
		String img = (String)sourceFieldValue;
		if (QDStringUtil.isNotEmpty(img)){
			return imageUtil.get(img);
		}
		return null;
	}

}
