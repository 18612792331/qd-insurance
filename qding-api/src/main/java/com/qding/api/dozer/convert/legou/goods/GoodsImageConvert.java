package com.qding.api.dozer.convert.legou.goods;

import com.google.common.collect.Lists;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.util.ImageUtil;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import org.dozer.CustomConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 压缩乐购商品缩略图
 * @author lichao
 *
 */
public class GoodsImageConvert implements CustomConverter{

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {

		String[] imgs = (String[])sourceFieldValue;
		if (QDStringUtil.isNotNull(imgs)){
			List<String> img_reduce = Lists.newArrayList();
			for (String imgUlr : imgs) {
				img_reduce.add(ImageUtil.LgImg(imgUlr,1));
			}
			return (String[])img_reduce.toArray(new String[img_reduce.size()]);
		}
		return null;
		
	}

}
