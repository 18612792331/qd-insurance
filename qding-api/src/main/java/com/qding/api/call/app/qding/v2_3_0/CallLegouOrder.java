package com.qding.api.call.app.qding.v2_3_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v1_4_0.struct.legou.order.ConfirmOrder;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.LogisticsInfo;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderBase;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.*;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.request.ConfirmOrderRequest;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.request.GetOrderRequest;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.request.GetPromotionInfoForCartRequest;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.response.data.ConfirmOrderResponseData;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.response.data.GetOrderResponseData;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.response.data.GetPromotionInfoForCartResponseData;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.PropertySelfOrderGroup;
import com.qding.api.call.service.PromotionService;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.ProjectConnect;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.legou.domain.OrderGoods;
import com.qding.legou.struct.response.GetOrderDetailByCodeResponse;
import com.qding.logistics.platform.client.remote.LogisticsPlatformOrderRpcService;
import com.qding.logistics.platform.client.remote.struct.bean.LogisticsOrderDeliveryBean;
import com.qding.logistics.platform.client.remote.struct.request.GetOrderLogisticsDataRequest;
import com.qding.logistics.platform.client.remote.struct.response.GetOrderLogisticsDataResponse;
import com.qding.order.domain.OrderReceiver;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by qd on 2016/5/17.
 */
@ExplainAnnotation(explain = "乐购订单")
public class CallLegouOrder extends com.qding.api.call.app.qding.v2_0_0.CallLegouOrder {

    @Autowired
    private ISolrSkuService skuService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private LogisticsPlatformOrderRpcService logisticsPlatformOrder;

    @Autowired
    private PromotionService promotionService;

    private static final Logger logger = Logger.getLogger("CallLegouOrder");

    @HTTP(alias = "getPromotionInfoForCart")
    @ExplainAnnotation(explain = "获取当前购物车中获取所优惠信息", desc = "该接口和2.3版本之前的confirmOrder接口功能相同，后期强升会过度掉")
    public Response<GetPromotionInfoForCartResponseData> getPromotionInfoForCart(GetPromotionInfoForCartRequest request) {
        Response<GetPromotionInfoForCartResponseData> response = new Response<GetPromotionInfoForCartResponseData>();
        GetPromotionInfoForCartResponseData data = new GetPromotionInfoForCartResponseData();
        if (CollectionUtils.isEmpty(request.getSkus())) {
            ConfirmOrder entity = new ConfirmOrder();
            entity.setShouldPay("0");
            data.setEntity(entity);
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
            return  response;
        }
        try {
            String version = request.getAppDevice().getQdVersion();
            com.qding.api.call.app.qding.v1_4_0.struct.legou.order.response.data.ConfirmOrderResponseData tempObj = fittingConfirmOrderResponseObj(request.getProjectId(), request.getMemberId(), request.getSkus(),
                    request.getOrderSourceType(), request.getCouponCodes(), request.getOrderPromotionIds(), version);
            data = transfor(GetPromotionInfoForCartResponseData.class, tempObj);
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());

        } catch (Exception e) {
            return handleException(GetPromotionInfoForCartResponseData.class, e);
        }
        return response;
    }


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
            //批量获取商品促销名称
            Map<Long,String[]> promotionNameNameMap = promotionService.getGoodActivityInfoBySkuids(skuIds,Long.parseLong(request.getProjectId()));

            for (LegouSkuDetailInfo legouSkuDetailInfo : LegouSkuDetailInfoList) {
                BuyGoodInfoBean buyGoodInfoBean = transfor(BuyGoodInfoBean.class, legouSkuDetailInfo);
                if (QDStringUtil.isNotNull(promotionNameNameMap) && promotionNameNameMap.containsKey(Long.parseLong(legouSkuDetailInfo.getSkuId()))) {
                    buyGoodInfoBean.setActivityInfo(promotionNameNameMap.get(Long.parseLong(legouSkuDetailInfo.getSkuId())));
                }

                buyGoodInfoBean.setBuyNum(buyNumMap.get(buyGoodInfoBean.getSkuId()));
                buyGoodInfoBeanList.add(buyGoodInfoBean);
                //判断是否含有周先生商品
                if (QDStringUtil.isNotEmpty(legouSkuDetailInfo.getMarkingCode()) && "zxs".equals(legouSkuDetailInfo.getMarkingCode().toLowerCase())) {
                    containZXS = 1;
                }
            }
        }

        try {
            com.qding.api.call.app.qding.v1_4_0.struct.legou.order.response.data.ConfirmOrderResponseData tempObj = fittingConfirmOrderResponseObj(request.getProjectId(), request.getMemberId(), skuList,
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
     * 获取订单详情
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getOrder", isRequireAuth = true)
    @ExplainAnnotation(explain = "获取订单详情")
    @Deprecated
    public Response<GetOrderResponseData> getOrder(GetOrderRequest request) {

        try {
            Response<GetOrderResponseData> response = new Response<GetOrderResponseData>();
            GetOrderDetailByCodeResponse getOrderResponse = checkOrderAuth(request.getMemberId(), request.getOrderCode());

            // TODO: 2016/7/20 由于APP在针对充值pos支付时调用了乐购订单详情出现异常，这里做兼容操作，后期需要维护
            if (request.getOrderCode().startsWith("CZ")) {
                GetOrderResponseData data = new GetOrderResponseData();
                Order order = new Order();
                OrderBase orderBase = new OrderBase();
                orderBase.setPaymentStatus(getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPayStatus());
                order.setOrderBase(orderBase);
                data.setEntity(order);
                response.setCode(HttpStatus.OK.getStatusCode());
                response.setData(data);
                return response;
            }
            List<OrderGoods> orderGoodList = getOrderResponse.getLegouOrderDetailDto().getOrderGoodsList();
            List<BuyGoodInfoBean> buyGoodInfoBeanList = Lists.newArrayList();
            for (OrderGoods orderGood : orderGoodList) {
                BuyGoodInfoBean buyGoodInfoBean = transfor(BuyGoodInfoBean.class, orderGood);
                buyGoodInfoBeanList.add(buyGoodInfoBean);

            }

            Map<Integer, Addresses> deliveryAddresses = new HashMap<>();
            OrderReceiver orderReceiver = null;
            if (QDStringUtil.isNotNull(getOrderResponse.getLegouOrderDetailDto().getOrderReceiver())) {
                orderReceiver = getOrderResponse.getLegouOrderDetailDto().getOrderReceiver();
                Addresses addresses = transfor(Addresses.class, orderReceiver);
                deliveryAddresses.put(Constant.DeliveryType.Logistics.getValue(), addresses);
            }

            if (QDStringUtil.isNotNull(getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPaddress())) {
                String paddRess = getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPaddress();
                Addresses addresses = new Addresses();
                addresses.setAddress(paddRess);
                deliveryAddresses.put(Constant.DeliveryType.PropertySelf.getValue(), addresses);
            }

            Map<Integer, Map<Long, List<BuyGoodInfoBean>>> goodGroupByDeliveryTypeMap = handleGoodsForGroupMap(buyGoodInfoBeanList);
            List<OrderGroup> groupList = getOrderGroupForShow(goodGroupByDeliveryTypeMap, deliveryAddresses);
            GetOrderResponseData data = transfor(GetOrderResponseData.class, getOrderResponse);
            if (getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPayAt() != null &&
                    getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPayAt() > 0) {
                data.getEntity().setPayRemind("请于下单后30分钟内完成支付");
            }
            for (OrderGroup orderGroup : groupList) {
                if (Constant.DeliveryType.Logistics.getValue() == orderGroup.getDeliveryType()) {
                    OrderGroup logistics_dis = orderGroup;
                    data.getEntity().setLogisticsDis(logistics_dis);

                } else if (Constant.DeliveryType.PropertySelf.getValue() == orderGroup.getDeliveryType()) {
                    PropertySelfOrderGroup property_self = transfor(PropertySelfOrderGroup.class, orderGroup);
                    data.getEntity().setPropertySelf(property_self);
                }
            }

            try {
                Integer legouStatus = getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getLegouStatus();
                data.getEntity().setLegouStatus(legouStatus);
            } catch (Exception e) {
                logger.error("get legouStatus for getOrder  is error ", e);
            }
            // TODO: 2016/8/2 将物流单号和物流公司信息由平台订单获取转移到订单平台物流系统获取 
//            OrderExt orderExt = getOrderResponse.getLegouOrderDetailDto().getOrderExt();
            GetOrderLogisticsDataRequest orderLogisticsDataRequest = new GetOrderLogisticsDataRequest();
            orderLogisticsDataRequest.setOrderCode(request.getOrderCode());
            GetOrderLogisticsDataResponse getOrderLogisticsDataResponse = logisticsPlatformOrder.getOrderLogisticsData(orderLogisticsDataRequest);
            if (HttpStatus.OK.getStatusCode() == getOrderLogisticsDataResponse.getReturnInfo().getCode()) {
                //物流信息 查询备用字段
                LogisticsInfo logisticsInfo = new LogisticsInfo();
                if (QDStringUtil.isNotNull(getOrderLogisticsDataResponse) && getOrderLogisticsDataResponse.getList().size() > 0) {
                    LogisticsOrderDeliveryBean logisticsOrderDeliveryBean = getOrderLogisticsDataResponse.getList().get(0);
                    logisticsInfo.setCompanyCode(logisticsOrderDeliveryBean.getLogisticsCompany());
                    logisticsInfo.setLogisticsCode(logisticsOrderDeliveryBean.getLogisticsCode());
                }
                data.getEntity().setLogisticsInfo(logisticsInfo);
            }

            if (QDStringUtil.isNotNull(orderReceiver)) {
                Addresses addresses = transfor(Addresses.class, orderReceiver);
                data.getEntity().setDeliveryAddress(addresses);
            }
            //获取项目第一条地址(物业自提地址)
            if (QDStringUtil.isNotNull(getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getProjectId())) {
                List<ProjectConnect> projectConnects = projectReadService.getProjectConnectByProjectId(getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getProjectId());
                if (projectConnects != null && !projectConnects.isEmpty()) {
                    data.getEntity().setProjectConcat(transfor(ProjectConcat.class, projectConnects.get(0)));
                }
            }
            response.setData(data);
            return response;

        } catch (Exception e) {

            return handleException(GetOrderResponseData.class, e);
        }
    }


    /**
     * 对商品进行分组（先按照收货方式分组，再按照供货商分组）
     *
     * @param
     * @param skuList
     * @return
     */
    public Map<Integer, Map<Long, List<BuyGoodInfoBean>>> handleGoodsForGroupMap(List<BuyGoodInfoBean> skuList) {

        Map<Integer, Map<Long, List<BuyGoodInfoBean>>> goodGroupByDeliveryTypeMap = new HashMap();
        Map<Long, List<BuyGoodInfoBean>> goodGroupByProviderIdMap = null;

        for (BuyGoodInfoBean buyGoodInfoBean : skuList) {

            if (buyGoodInfoBean != null) {
                Integer deliveryType = buyGoodInfoBean.getDeliveryType();
                if (goodGroupByDeliveryTypeMap.containsKey(deliveryType)) {
                    goodGroupByProviderIdMap = goodGroupByDeliveryTypeMap.get(deliveryType);
                } else {
                    goodGroupByProviderIdMap = new HashMap();
                }
                Long providerId = buyGoodInfoBean.getProviderId();
                List<BuyGoodInfoBean> buyGoodInfoBeanList = null;
                if (goodGroupByProviderIdMap.containsKey(providerId)) {
                    buyGoodInfoBeanList = goodGroupByProviderIdMap.get(providerId);
                } else {
                    buyGoodInfoBeanList = Lists.newArrayList();
                }

                buyGoodInfoBeanList.add(buyGoodInfoBean);
                goodGroupByProviderIdMap.put(providerId, buyGoodInfoBeanList);
                goodGroupByDeliveryTypeMap.put(deliveryType, goodGroupByProviderIdMap);

            }
        }
        return goodGroupByDeliveryTypeMap;
    }

    /**
     * 组装可对外展示的订单分组情况
     *
     * @param goodGroupByDeliveryTypeMap
     * @return
     */
    public List<OrderGroup> getOrderGroupForShow(Map<Integer, Map<Long, List<BuyGoodInfoBean>>> goodGroupByDeliveryTypeMap, Map<Integer, Addresses> deliveryAddresses) {

        List<OrderGroup> groupList = Lists.newArrayList();
        if (QDStringUtil.isNotNull(goodGroupByDeliveryTypeMap) && goodGroupByDeliveryTypeMap.size() > 0) {

            for (Map.Entry<Integer, Map<Long, List<BuyGoodInfoBean>>> goodGroupByDeliveryTypeEntry : goodGroupByDeliveryTypeMap.entrySet()) {

                Integer deliveryType = goodGroupByDeliveryTypeEntry.getKey();
                Map<Long, List<BuyGoodInfoBean>> goodGroupByProviderIdMap = goodGroupByDeliveryTypeEntry.getValue();
                List<OrdersGroupBySupplierBean> supplierGroupList = Lists.newArrayList();

                if (QDStringUtil.isNull(goodGroupByProviderIdMap) || goodGroupByProviderIdMap.size() <= 0) continue;
                for (Map.Entry<Long, List<BuyGoodInfoBean>> goodGroupByProviderIdEntry : goodGroupByProviderIdMap.entrySet()) {
                    List<BuyGoodInfoBean> goodInfoBeanList = goodGroupByProviderIdEntry.getValue();
                    OrdersGroupBySupplierBean ordersGroupBySupplierBean = new OrdersGroupBySupplierBean();
                    ordersGroupBySupplierBean.setGoodInfoBeanList(goodInfoBeanList);
                    ordersGroupBySupplierBean.setSupplierName(goodInfoBeanList.get(0).getProviderName());
                    supplierGroupList.add(ordersGroupBySupplierBean);
                }

                OrderGroup orderGroup = new OrderGroup();
                orderGroup.setDeliveryType(deliveryType);
                Addresses deliveryAddress = null;

                if (QDStringUtil.isNotNull(deliveryAddresses) && deliveryAddresses.size() > 0) {
                    deliveryAddress = deliveryAddresses.get(deliveryType);
                }
                orderGroup.setDeliveryAddress(deliveryAddress);
                orderGroup.setList(supplierGroupList);
                groupList.add(orderGroup);
            }
        }
        return groupList;
    }


    public String getAddress(Addresses address) {
        if (address == null) {
            return null;
        }
        if (address.getVersion() != null && 2 == address.getVersion()) {
            StringBuffer str = new StringBuffer();
            if (StringUtils.isNotEmpty(address.getProvinceName())) {
                str.append(address.getProvinceName() + "-");
            }
            if (StringUtils.isNotEmpty(address.getCityName())) {
                str.append(address.getCityName() + "-");
            }
            if (StringUtils.isNotEmpty(address.getAreaName())) {
                str.append(address.getAreaName() + "-");
            }
            if (StringUtils.isNotEmpty(address.getStreet())) {
                str.append(address.getStreet() + "-");
            }
            if (StringUtils.isNotEmpty(address.getProjectName())) {
                str.append(address.getProjectName() + "-");
            }
            address.setIsSetting(1);
            if (StringUtils.isNotEmpty(address.getAddress())) {
                str.append(address.getAddress());
                return str.toString();
            } else {
                if (StringUtils.isNotEmpty(address.getRoomName())) {
                    str.append(address.getRoomName());
                }
                return str.toString();
            }

        } else {
            if (address.getDefaultFlag().intValue() == 1) {
                address.setDefaultFlag(0);
            }
            return address.getAddress();
        }
    }

}
