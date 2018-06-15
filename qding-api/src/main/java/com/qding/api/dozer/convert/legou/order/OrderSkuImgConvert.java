package com.qding.api.dozer.convert.legou.order;

import java.util.ArrayList;
import java.util.List;

import org.dozer.CustomConverter;

import com.qding.api.util.ApplicationContextUtil;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;

/**
 * 订单详情 图片转换
 * @author lichao
 *
 */
public class OrderSkuImgConvert implements CustomConverter{

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {
		
		Long skuId = Long.parseLong(sourceFieldValue.toString());
		
		ISolrSkuService skuService = ApplicationContextUtil.getBeansOfType(ISolrSkuService.class);
		
		LegouSkuRequest legouSkuRequest = new LegouSkuRequest();
		
		ArrayList<Long> skuIds = new ArrayList<>();
		
		skuIds.add(skuId);
		
		legouSkuRequest.setSortedSkuIds(skuIds);
		
		legouSkuRequest.setFindAllStatus(true);
		
		LegouSkuResponse skuResponse = skuService.queryLegouSku(legouSkuRequest);
		 
		List<LegouSkuDetailInfo> skus = skuResponse.getSkus();
		
		if(skus != null && !skus.isEmpty()) {
			
			return skus.get(0).getSkuImgUrl();
		}
		
		return new String[0];
	}

}
