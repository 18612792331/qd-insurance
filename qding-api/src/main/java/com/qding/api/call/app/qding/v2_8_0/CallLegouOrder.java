package com.qding.api.call.app.qding.v2_8_0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qding.api.call.service.PromotionService;
import com.qding.framework.common.exception.ServiceException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.BuyGoodInfoBean;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrderGroup;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.request.ConfirmOrderRequest;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.response.data.ConfirmOrderResponseData;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.PropertySelfOrderGroup;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;

/**
 * Created by qd on 2016/5/17.
 */
@ExplainAnnotation(explain = "乐购订单")
public class CallLegouOrder extends com.qding.api.call.app.qding.v2_5_0.CallLegouOrder {

    @Autowired
    private ISolrSkuService skuService;

    @Autowired
    private PromotionService promotionService;


    @HTTP(alias = "confirmOrder")
    @ExplainAnnotation(explain = "确定订单")
    @Deprecated
    public Response<ConfirmOrderResponseData> confirmOrder(ConfirmOrderRequest request) {

        String version = request.getAppDevice().getQdVersion();
        Integer containZXS = 0;
        Response<ConfirmOrderResponseData> response = new Response<ConfirmOrderResponseData>();
        List<Sku> skuList = request.getSkus();
        List<BuyGoodInfoBean> buyGoodInfoBeanList = Lists.newArrayList();
        ArrayList<Long> skuIds = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(skuList)) {
            List<LegouSkuDetailInfo> LegouSkuDetailInfoList = Lists.newArrayList();
            Map<String, Integer> buyNumMap = new HashMap<>();
            for (Sku sku : skuList) {
                skuIds.add(Long.parseLong(sku.getSkuId()));
                buyNumMap.put(sku.getSkuId(), sku.getBuyNum());
            }
            LegouSkuRequest legouSkuRequest = new LegouSkuRequest();
            legouSkuRequest.setSortedSkuIds(skuIds);
            legouSkuRequest.setFindAllStatus(true);
            LegouSkuResponse skuResponse = skuService.queryLegouSku(legouSkuRequest);
            LegouSkuDetailInfoList = skuResponse.getSkus();
            //批量获取商品促销活动名称
            Map<Long,String[]> promotionNameNameMap = promotionService.getGoodActivityInfoBySkuids(skuIds,Long.parseLong(request.getProjectId()));
            for (LegouSkuDetailInfo legouSkuDetailInfo : LegouSkuDetailInfoList) {
                BuyGoodInfoBean buyGoodInfoBean = transfor(BuyGoodInfoBean.class, legouSkuDetailInfo);
                try {
                    List<BuyGoodInfoBean> goodInfoList =  groupGoodsByPromotion(legouSkuDetailInfo,buyNumMap.get(buyGoodInfoBean.getSkuId()),promotionNameNameMap);
                    buyGoodInfoBeanList.addAll(goodInfoList);
                    //判断是否含有周先生商品
                    if (QDStringUtil.isNotEmpty(legouSkuDetailInfo.getMarkingCode()) && "zxs".equals(legouSkuDetailInfo.getMarkingCode().toLowerCase())) {
                        containZXS = 1;
                    }
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
        	com.qding.api.call.app.qding.v1_4_0.struct.legou.order.response.data.ConfirmOrderResponseData tempObj 
        	= fittingConfirmOrderResponseObjFor28(request.getProjectId(), request.getMemberId(), skuList,
                    request.getOrderSourceType(), request.getCouponCodes(), request.getOrderPromotionIds(), version);
            ConfirmOrderResponseData data = transfor(ConfirmOrderResponseData.class, tempObj);

            if (data.getEntity().getDeliveryAddress() != null) {
                String addressStr = getAddress(data.getEntity().getDeliveryAddress());
                data.getEntity().getDeliveryAddress().setAddressStr(addressStr);
            }
            if (data.getPropertySelf() != null && data.getPropertySelf().getDeliveryAddress() != null) {
                String addressStr = getAddress(data.getPropertySelf().getDeliveryAddress());
                data.getPropertySelf().getDeliveryAddress().setAddressStr(addressStr);
            }

            Map<Integer, Addresses> deliveryAddresses = new HashMap<>();
            deliveryAddresses.put(Constant.DeliveryType.Logistics.getValue(), QDStringUtil.isNotNull(tempObj.getEntity().getDeliveryAddress()) ? tempObj.getEntity().getDeliveryAddress() : null);

            //获取物业自提地址，这里讲物业联系对象临时转化为收货对象
            if (QDStringUtil.isNotNull(tempObj.getEntity().getProjectConcat())) {
                ProjectConcat projectContat = tempObj.getEntity().getProjectConcat();
                Addresses addresses = transfor(Addresses.class, projectContat);
                String addressStr = getAddress(addresses);
                addresses.setAddressStr(addressStr);
                deliveryAddresses.put(Constant.DeliveryType.PropertySelf.getValue(), addresses);
            }

            Map<Integer, Map<Long, List<BuyGoodInfoBean>>> goodGroupByDeliveryTypeMap = handleGoodsForGroupMap(buyGoodInfoBeanList);
            List<OrderGroup> groupList = getOrderGroupForShow(goodGroupByDeliveryTypeMap, deliveryAddresses);

            for (OrderGroup orderGroup : groupList) {
                if (Constant.DeliveryType.Logistics.getValue() == orderGroup.getDeliveryType()) {//快递
                    OrderGroup logistics_dis = orderGroup;
                    data.setLogisticsDis(logistics_dis);

                } else if (Constant.DeliveryType.PropertySelf.getValue() == orderGroup.getDeliveryType()) {//物业自提
                    PropertySelfOrderGroup property_self = transfor(PropertySelfOrderGroup.class, orderGroup);
                    //如果包含周先生商品，则需要给外部提供提示文案和标识
                    if (containZXS.intValue() == 1) {
                        property_self.setRemindContent(Constant.ZXS_REMIND_CONTENT);
                        property_self.setContainZxs(containZXS);
                    }
                    data.setPropertySelf(property_self);
                }
            }
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
        } catch (Exception e) {
            return handleException(ConfirmOrderResponseData.class, e);
        }
        return response;
    }


    /**
     * APP 3.0 改造方法
     * @param legouSkuDetailInfo
     * @param buyCount
     * @return
     * @throws ServiceException
     */
    public  List<BuyGoodInfoBean> groupGoodsByPromotion ( LegouSkuDetailInfo legouSkuDetailInfo,
    		Integer buyCount,	Map<Long,String[]> promotionNameMap) throws ServiceException {

        List<BuyGoodInfoBean> list = Lists.newArrayList();
        BuyGoodInfoBean buyGoodInfoBean = transfor(BuyGoodInfoBean.class, legouSkuDetailInfo);
        buyGoodInfoBean.setBuyNum(buyCount);
        Long skuId = Long.parseLong(legouSkuDetailInfo.getSkuId());
        if (QDStringUtil.isNotNull(promotionNameMap) && promotionNameMap.containsKey(skuId)) {
            buyGoodInfoBean.setActivityInfo(promotionNameMap.get(skuId));
        }
        list.add(buyGoodInfoBean);
        return list;
    }
    

}
