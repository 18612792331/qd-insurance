package com.qding.api.dozer.convert.legou.order;

import com.google.common.collect.Lists;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.service.order.BtnSkipModel;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import org.apache.commons.collections.CollectionUtils;
import org.dozer.CustomConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情 图片转换
 * @author lichao
 *
 */
public class OrderBtnSkipModelConvert implements CustomConverter{

	@Override
	public Object convert(Object existingDestinationFieldValue,
			Object sourceFieldValue, Class<?> destinationClass,
			Class<?> sourceClass) {

		List<BtnSkip> btnSkipModeList = Lists.newArrayList();
		if ( QDStringUtil.isNull(sourceFieldValue)){
			return  btnSkipModeList;
		}
		List<BtnSkipModel> btnSkipList =(List<BtnSkipModel>)sourceFieldValue;
		SkipModeFitting skipMode =  ApplicationContextUtil.getBeansOfType(SkipModeFitting.class);

		if (CollectionUtils.isNotEmpty(btnSkipList)) {
			for (BtnSkipModel btnSkipModel : btnSkipList) {
				BtnSkip btnSkip = new BtnSkip();
				btnSkip.setBtnName(btnSkipModel.getBtnName());
				//如果是去支付
				if ("1".equals(String.valueOf(btnSkipModel.getBtnType()))){
					btnSkip.setBtnType(1);
					btnSkip.setSkipModel("");
				} else { //其他
					btnSkip.setBtnType(QDStringUtil.isNotNull(btnSkipModel.getBtnType())?btnSkipModel.getBtnType():0);
					btnSkip.setSkipModel(skipMode.fittingSkipUrl("2.5.0", APIPropertiesClient.getValue("skip_url_btn_h5")+btnSkipModel.getSkipUrl(),0,""));
				}
				btnSkip.setBtnColor(btnSkipModel.getBtnColor());
				btnSkipModeList.add(btnSkip);
			}
		}
		return  btnSkipModeList;
	}

}
