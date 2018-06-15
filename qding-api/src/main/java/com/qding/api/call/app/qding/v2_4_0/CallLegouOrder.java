package com.qding.api.call.app.qding.v2_4_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderBase;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderGood;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.BuyGoodInfoBean;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrderGroup;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.LogisticsInfo;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.Order;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.RedeemCode;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.RedeemCodeOrderBean;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.request.GetCartRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.request.GetOrderRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.request.GetRedeemCodeOrdersRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.response.data.GetCartResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.response.data.GetOrderResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.response.data.GetRedeemCodeOrdersResponseData;
import com.qding.api.call.service.OrderService;
import com.qding.api.call.service.PromotionService;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.ProjectConnect;
import com.qding.brick.pojo.ware.WareMarkingConf;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.ware.WareRemoteService;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.basemodel.ResultPage;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.legou.domain.Cart;
import com.qding.legou.domain.OrderGoods;
import com.qding.legou.dto.LegouGoodsThirdCodeDetailDto;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.GetMemberThirdCodesPageRequest;
import com.qding.legou.struct.request.GetUserCartRequest;
import com.qding.legou.struct.response.GetMemberThirdCodePageResponse;
import com.qding.legou.struct.response.GetOrderDetailByCodeResponse;
import com.qding.legou.struct.response.GetUserCartResponse;
import com.qding.logistics.platform.client.remote.LogisticsPlatformOrderRpcService;
import com.qding.order.domain.OrderReceiver;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.request.SkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.response.SkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import com.qding.solr.struct.sku.SkuDetailInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * Created by qd on 2016/6/16.
 */
public class CallLegouOrder extends com.qding.api.call.app.qding.v2_3_0.CallLegouOrder {

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private ISolrSkuService skuService;

    @Autowired
    private ILegouRemoteService legouRemoteService;

    @Autowired
    private WareRemoteService wareRemoteService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private LogisticsPlatformOrderRpcService logisticsPlatformOrder;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private OrderService apiOrderService;

    private static final Logger logger = Logger.getLogger("CallLegouOrder");


    @HTTP(alias = "getCarts")
    @ExplainAnnotation(explain = "获取购物车列表")
    public Response<GetCartResponseData> getCarts(GetCartRequest request) {

        try {
            Long startTime1 = System.currentTimeMillis();
            Response<GetCartResponseData> response = new Response<GetCartResponseData>();
            GetUserCartRequest getCartRequest = transfor(GetUserCartRequest.class, request);
            //有效
            List<com.qding.api.call.app.qding.v2_4_0.struct.legou.order.Cart> effectiveList = new ArrayList<>();
            //无效
            List<com.qding.api.call.app.qding.v2_4_0.struct.legou.order.Cart> invalidList = new ArrayList<>();

            GetUserCartResponse getUserCartResponse = legouRemoteService.getUserCart(getCartRequest);
            checkAndContinue(getUserCartResponse);
            Long endTime1 = System.currentTimeMillis();
            logger.info("getCarts 获取购物车信息列表时间：" + (endTime1 -startTime1));

            if (QDStringUtil.isNotNull(getUserCartResponse) && CollectionUtils.isNotEmpty(getUserCartResponse.getCatList())){

                //商品详细信息 从商品接口中获取
                List<Cart> cartList = getUserCartResponse.getCatList();

                Map<Long,Cart> cartMap = new HashMap<>();
                ArrayList<Long> skuIds = new ArrayList<>();
                for (Cart cartObj : cartList) {
                    Long skuId = cartObj.getWareSkuId();
                    skuIds.add(skuId);
                    cartMap.put(skuId,cartObj);
                }
                LegouSkuRequest legouSkuRequest = new LegouSkuRequest();
                legouSkuRequest.setSortedSkuIds(skuIds);
                legouSkuRequest.setFindAllStatus(true);
                legouSkuRequest.setFindSkuStock(true);

                Long startTime3 = System.currentTimeMillis();
                LegouSkuResponse skuResponse = skuService.queryLegouSku(legouSkuRequest);
                List<LegouSkuDetailInfo> skus = skuResponse.getSkus();
                List<Long> wareSkuIds = Lists.newArrayList();

                Long endTime3 = System.currentTimeMillis();
                logger.info("getCarts solr获取商品信息时间：" + (endTime3 -startTime3));
                for (LegouSkuDetailInfo sku : skus) {
                    wareSkuIds.add(Long.parseLong(sku.getSkuId()));
                }

                Long startTime4 = System.currentTimeMillis();
                //批量获取商品限购信息
                Map<Long, Map<String, Object>> limitMap = promotionService.batchGetSkuLimitListByWareIds(QDStringUtil.isNotNull(request.getMemberId()) ? request.getMemberId() : "",wareSkuIds,(Long.parseLong(request.getProjectId())));
                Long endTime4 = System.currentTimeMillis();
                logger.info("getCarts获取商品限购信息时间时间：" + (endTime4 -startTime4));

                Long startTime5 = System.currentTimeMillis();
                //批量获取商品促销信息列表
                Map<Long,List<GoodsPromotion>>  goodsPromotionMap = promotionService.batchGetGoodsPromotions(wareSkuIds, Long.parseLong(request.getProjectId()));
                Long endTime5 = System.currentTimeMillis();
                logger.info("getCarts 获取商品促销信息时间时间：" + (endTime5 -startTime5));


                //批量获取商品最优促销信息
                Long startTime6 = System.currentTimeMillis();
                Map<Long,GoodsPromotion> optimizePromotionMap = promotionService.batchGetGoodsOptimizePromotion (wareSkuIds, Long.parseLong(request.getProjectId()));
                Long endTime6 = System.currentTimeMillis();
                logger.info("getCarts 获取商品最优促销信息时间时间：" + (endTime6 -startTime6));

                Long startTime7 = System.currentTimeMillis();
                for (LegouSkuDetailInfo sku : skus) {
                    Integer limitStrategy = 0 ;
                    Long skuId = Long.parseLong(sku.getSkuId());
                    Integer limitCount = -1;
                    Integer availableCount = -1;
                    String discountWarePrice = "";
                    if (QDStringUtil.isNotNull(limitMap) && limitMap.containsKey(skuId)) {
                        limitCount = Integer.parseInt(limitMap.get(skuId).get("limitCount").toString()); //限购数量
                        availableCount = Integer.parseInt(limitMap.get(skuId).get("availableCount").toString());//可购买数量
                    }

                    //3.0针对限购商品超出限购数量是否可以继续购买的判断
                    String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
                    if (Integer.parseInt(initVersion)>=300000) {
                        if (QDStringUtil.isNotNull(optimizePromotionMap) && optimizePromotionMap.containsKey(skuId)) {
                            GoodsPromotion goodsPromotion = optimizePromotionMap.get(skuId);
                            limitStrategy = goodsPromotion.getLimitStrategy();
                            discountWarePrice = QDStringUtil.isNotEmpty(goodsPromotion.getPromotionPrice()) ?goodsPromotion.getPromotionPrice() :"";
                        }

                        if (availableCount == 0) {
                            discountWarePrice = "";
                            limitCount= -1;
                        }
                    }

                    com.qding.api.call.app.qding.v2_4_0.struct.legou.order.Cart cart = new com.qding.api.call.app.qding.v2_4_0.struct.legou.order.Cart(
                            QDStringUtil.isNotNull(sku.getMarkingCode()) ? sku.getMarkingCode() : "",
                            QDStringUtil.isNotNull(sku.getMarkingName()) ? sku.getMarkingName() : "",
                            sku.getWareId().toString(),
                            sku.getSkuId(),
                            sku.getName(),
                            sku.getSkuImgUrl(),
                            sku.getPrice(),
                            sku.getMarketPrice(),
                            discountWarePrice, //张杰对接最优促销价格
                            cartMap.get(skuId).getCount(),
                            sku.getDeliveryType(),
                            QDStringUtil.isNotNull(goodsPromotionMap)&&
                                    goodsPromotionMap.containsKey(sku.getSkuId())?goodsPromotionMap.get(sku.getSkuId()):Lists.<GoodsPromotion>newArrayList(),
                            sku.getSpecName(),
                            sku.getSpecValue(),
                            availableCount,
                            limitCount,
                            limitStrategy
                    );

                    //如果货品是上加状态，且库存足够 或 限购策略为超出不支持原价购买且限购剩余数不为0
                    if (sku.getIsDel()!=0 || (limitStrategy==0 && availableCount.intValue() == 0)) {
                        invalidList.add(cart);
                    } else {
                        if ((1 == sku.getStatus().intValue() && 1 <= sku.getCountStock().intValue())) {
                            String markingCode = sku.getMarkingCode();
                            if (Constant.MRX_MAKING_CODE.equals(markingCode)) {
                                invalidList.add(cart); //3.0版本每日鲜进入失效列表
                            } else {
                                effectiveList.add(cart);
                            }
                        } else {
                            invalidList.add(cart);
                        }
                    }
                }
                Long endTime7 = System.currentTimeMillis();
                logger.info("getCarts 组装购物车列表时间：" + (endTime7-startTime7));
            }
            GetCartResponseData data = new GetCartResponseData();
            data.setEffectiveList(effectiveList);
            data.setInvalidList(invalidList);
            response.setData(data);
            return response;

        } catch (Exception e) {
            return handleException(GetCartResponseData.class, e);
        }
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
            String orderCode = request.getOrderCode();
            GetOrderDetailByCodeResponse getOrderResponse = checkOrderAuth(request.getMemberId(), orderCode);

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
                List<RedeemCode> redeemCodeList = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(orderGood.getThirdCodeDetails())) {
                    List<LegouGoodsThirdCodeDetailDto> legouGoodsThirdCodeDetailList = new ArrayList<>(orderGood.getThirdCodeDetails());
                    redeemCodeList = transforList(RedeemCode.class, legouGoodsThirdCodeDetailList);
                }
                if (QDStringUtil.isNotEmpty(orderGood.getMarkingCode())) {
                    WareMarkingConf wareMarkingConf = wareRemoteService.getMarkingConf(orderGood.getMarkingCode(), true);
                    buyGoodInfoBean.setMarkingName(QDStringUtil.isNotNull(wareMarkingConf) ? wareMarkingConf.getName() : "");
                }
                buyGoodInfoBean.setRedeemCodeList(redeemCodeList);
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
            if ((QDStringUtil.isNull(getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPayAt()) ||
                    getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPayAt() <= 0) &&
                    !(201 <= getOrderResponse.getLegouOrderDetailDto().getOrderBase().getOrderStatus().intValue() && 204 >= getOrderResponse.getLegouOrderDetailDto().getOrderBase().getOrderStatus().intValue())) {
                data.getEntity().setPayRemind("请于下单后30分钟内完成支付");
            }
            for (OrderGroup orderGroup : groupList) {
                if (Constant.DeliveryType.Logistics.getValue() == orderGroup.getDeliveryType()) {
                    OrderGroup logistics_dis = orderGroup;
                    data.getEntity().setLogisticsDis(logistics_dis);

                } else if (Constant.DeliveryType.PropertySelf.getValue() == orderGroup.getDeliveryType()) {
                    OrderGroup property_self = orderGroup;
                    data.getEntity().setPropertySelf(property_self);
                }
            }

            try {
                Integer legouStatus = getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getLegouStatus();
                data.getEntity().setLegouStatus(legouStatus);
            } catch (Exception e) {
                logger.error("get legouStatus for getOrder  is error ", e);
            }

            List<LogisticsInfo> logisticsInfoList = apiOrderService.getLogisticsInfosForPackage(orderCode, getOrderResponse);
            data.getEntity().setLogisticsInfoList(logisticsInfoList);
            if (QDStringUtil.isNotNull(orderReceiver)) {
                Addresses addresses = transfor(Addresses.class, orderReceiver);
                data.getEntity().setDeliveryAddress(addresses);
            }
            //获取项目第一条地址(物业自提地址)
            if (QDStringUtil.isNotNull(getOrderResponse.getLegouOrderDetailDto().getLegouOrder())
                    && QDStringUtil.isNotNull(getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getProjectId())) {
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


/*
    @HTTP(alias = "getRedeemCodeOrders", isRequireAuth = false)
    @ExplainAnnotation(explain = "获取兑换码列表")
    public Response<GetRedeemCodeOrdersResponseData> getRedeemCodeOrders(GetRedeemCodeOrdersRequest request) {

        Response<GetRedeemCodeOrdersResponseData> response = new Response<GetRedeemCodeOrdersResponseData>();
        GetRedeemCodeOrdersResponseData data = new GetRedeemCodeOrdersResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {

            Set<Long> skuIds = new HashSet<>();
            List<RedeemCodeOrderBean> list = Lists.newArrayList();
            //目前只支持用户在点击全部或商城业态时才会去做查询
            if ("ALL".equals(request.getBusinessType()) || Constant.PRODUCT_NO_NG.equals(request.getBusinessType())) {

                String version = request.getAppDevice().getQdVersion();
                Map<String, String> skipConfigMap = null;
                if (QDStringUtil.isNotEmpty(version)) {
                    version = version.trim();
                    skipConfigMap = skipMode.selSkipTemplateMap(version);
                } else {
                    logger.info("project index can't get version");
                }

                GetMemberThirdCodesPageRequest thirdCodesPageRequest = transfor(GetMemberThirdCodesPageRequest.class, request);
                GetMemberThirdCodePageResponse memberThirdCodePageResponse = legouRemoteService.getMemberThirdCodes(thirdCodesPageRequest);
                checkAndContinue(memberThirdCodePageResponse);
                ResultPage<LegouGoodsThirdCodeDetailDto> resultPage = memberThirdCodePageResponse.getResultPage();
                List<LegouGoodsThirdCodeDetailDto> legouGoodsThirdCodeDetailList = resultPage.getItems();

                if (CollectionUtils.isNotEmpty(legouGoodsThirdCodeDetailList)) {

                    data.setTotalCount(memberThirdCodePageResponse.getResultPage().getTotalCount());
                    for (LegouGoodsThirdCodeDetailDto legouGoodsThirdCodeDetailDto : legouGoodsThirdCodeDetailList) {
                        skuIds.add(legouGoodsThirdCodeDetailDto.getSkuId());
                    }
                    Map<Long, OrderGood> orderGoodMap = getOrderGoodMap(new ArrayList(skuIds));
                    if (QDStringUtil.isNotNull(orderGoodMap)) {
                        for (LegouGoodsThirdCodeDetailDto legouGoodsThirdCodeDetailDto : legouGoodsThirdCodeDetailList) {
                            String skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.DDXQ_4008.toInteger(), legouGoodsThirdCodeDetailDto.getOrderCode());
                            List<BtnSkip> btnSkipList = Lists.newArrayList();
                            BtnSkip btnSkip = new BtnSkip();
                            btnSkip.setBtnName("查看详情");
                            btnSkip.setBtnType(0);
                            btnSkip.setSkipModel(skipModeStr);
                            btnSkipList.add(btnSkip);

                            RedeemCode redeemCode = new RedeemCode();
                            redeemCode.setPassWord(legouGoodsThirdCodeDetailDto.getThirdPassword());
                            redeemCode.setRedeemCode(legouGoodsThirdCodeDetailDto.getThirdCode());

                            RedeemCodeOrderBean redeemCodeOrderBean = new RedeemCodeOrderBean();
                            redeemCodeOrderBean.setBtnSkipList(btnSkipList);
                            redeemCodeOrderBean.setBusinessName(DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_PRODUCT_NO.getGroupName(), memberThirdCodePageResponse.getProductNo()));
                            redeemCodeOrderBean.setRedeemCode(redeemCode);
                            redeemCodeOrderBean.setOrderGood(orderGoodMap.get(legouGoodsThirdCodeDetailDto.getSkuId()));
                            list.add(redeemCodeOrderBean);
                        }
                    }
                }
            }
            data.setList(list);
            response.setData(data);

        } catch (Exception e) {
            return handleException(GetRedeemCodeOrdersResponseData.class, e);
        }
        return response;
    }
*/

    /***************************************************************
     * 华丽的私有方法分割线
     ********************************************************************/

    private Map<Long, OrderGood> getOrderGoodMap(List<Long> skuIds) {

        SkuRequest skuRequest = new SkuRequest();
        skuRequest.setSortedSkuIds(skuIds);
        SkuResponse skuResponse = skuService.querySku(skuRequest);
        Map<Long, OrderGood> orderGoodMap = new HashMap<>();
        List<SkuDetailInfo> skuDetailInfoList = skuResponse.getSkus();
        if (CollectionUtils.isNotEmpty(skuDetailInfoList)) {
            for (SkuDetailInfo skuDetailInfo : skuDetailInfoList) {
                OrderGood orderGood = new OrderGood();
                orderGood.setGoodsDesc(skuDetailInfo.getDescription());
                orderGood.setGoodsName(skuDetailInfo.getSkuName());
                orderGood.setSkuImgUrl(QDStringUtil.isNotNull(skuDetailInfo.getSkuImgUrl()) && skuDetailInfo.getSkuImgUrl().length > 0 ? skuDetailInfo.getSkuImgUrl() : Constant.defaultPicForOrderListMap.get(Constant.PRODUCT_NO_NG.toString()));
                orderGood.setWareId(skuDetailInfo.getWareId());
                orderGood.setMarkingName(skuDetailInfo.getMarkingName());
                orderGood.setMarkingCode(skuDetailInfo.getMarkingCode());
                orderGoodMap.put(Long.parseLong(skuDetailInfo.getSkuId()), orderGood);
            }
        }
        return orderGoodMap;
    }









}
