package com.qding.api.call.app.qding.v2_0_0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.ProductOrderBean;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BusinessBean;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.LogisticsBean;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.LogisticsInfo;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderBean;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderGood;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request.ConfirmReceiptGoodsRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request.GetBusinessListRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request.GetOrderGoodsRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request.GetOrderRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request.GetOrderStatusRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request.GetOrdersRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request.LogisticsInfoRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.response.data.GetBusinessListResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.response.data.GetOrderGoodsResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.response.data.GetOrderResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.response.data.GetOrderStatusResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.response.data.GetOrdersResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.response.data.LogisticsInfoResponseData;
import com.qding.api.call.service.OrderService;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.ListUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.service.order.IGetProductOrderService;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.insurance.rpc.IInsuranceOrderListRpcService;
import com.qding.integral.remote.IntegralRemoteService;
import com.qding.integral.remote.domain.PlatformIntegralGoods;
import com.qding.integral.remote.request.GetIntegralGoodsDetailRequest;
import com.qding.integral.remote.response.GetIntegralGoodsDetailResponse;
import com.qding.legou.dto.AfterSalesDetailDto;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.GetAfterSalesDetailsByOrderCodeRequest;
import com.qding.legou.struct.request.GetOrderDetailByCodeRequest;
import com.qding.legou.struct.response.GetAfterSalesDetailsByOrderCodeResponse;
import com.qding.legou.struct.response.GetOrderDetailByCodeResponse;
import com.qding.logistics.platform.client.remote.LogisticsPlatformOrderRpcService;
import com.qding.logistics.platform.client.remote.struct.bean.LogisticsLogDto;
import com.qding.logistics.platform.client.remote.struct.bean.LogisticsOrderDeliveryBean;
import com.qding.logistics.platform.client.remote.struct.request.GetOrderLogisticsDataRequest;
import com.qding.logistics.platform.client.remote.struct.request.OrderSignRequest;
import com.qding.logistics.platform.client.remote.struct.response.GetExpressResponse;
import com.qding.logistics.platform.client.remote.struct.response.GetOrderLogisticsDataResponse;
import com.qding.newsell.service.IAfterSalesRemoteService;
import com.qding.oder.dto.OrderDetailDto;
import com.qding.order.domain.OrderBase;
import com.qding.order.domain.OrderSub;
import com.qding.order.enums.OrderStatusEnum;
import com.qding.order.service.IRemoteOrderService;
import com.qding.order.struct.request.FindMemberOrderListRequest;
import com.qding.order.struct.request.FindMemberProductListRequest;
import com.qding.order.struct.response.FindMemberOrderListResponse;
import com.qding.order.struct.response.FindMemberProductListResponse;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.SkuRequest;
import com.qding.solr.struct.response.SkuResponse;
import com.qding.solr.struct.sku.SkuDetailInfo;

/**
 * 订单
 *
 * @author JIAWENZHENG
 */
public class CallLegouOrder extends com.qding.api.call.app.qding.v1_4_0.CallLegouOrder {

    @Autowired
    private IRemoteOrderService orderService;

    @Autowired
    private OrderService apiOrderService;

    @Autowired
    private ISolrSkuService solrSku;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private ILegouRemoteService legouRemoteService;

    @Autowired
    private LogisticsPlatformOrderRpcService logisticsPlatformOrder;

    @Autowired
    private IntegralRemoteService integralRemoteService;

    @Autowired
    private IAfterSalesRemoteService afterSalesRemoteService;

    // 定义一个缓冲的线程值 线程池的大小根据任务变化
    private ExecutorService threadPool = Executors.newCachedThreadPool();

    private static final Logger logger = Logger.getLogger("CallLegouOrder");

    /**
     * 获取当前用户下过订单的业态
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getBusinessListByMemberId", isRequireAuth = true)
    @ExplainAnnotation(explain = "获取当前用户下过订单的业态")
    public Response<GetBusinessListResponseData> getBusinessListByMemberId(GetBusinessListRequest request) {

        try {
            Response<GetBusinessListResponseData> response = new Response<GetBusinessListResponseData>();

            FindMemberProductListRequest findMemberProductListRequest = transfor(FindMemberProductListRequest.class, request);

            FindMemberProductListResponse findMemberProductListResponse = orderService.findMemberProductList(findMemberProductListRequest);

            List<String> productList = findMemberProductListResponse.getProductList();

            List<BusinessBean> businessBeanList = Lists.newArrayList();

            if (productList.size() > 0) {

                Map<String, String> productInfoMap = DictionaryClient.getDictValueMapByGroupNameAndDictKeyList("product_no", productList);

                if (QDStringUtil.isNotNull(productInfoMap) && productInfoMap.size() > 0) {

                    for (String key : productInfoMap.keySet()) {
                        BusinessBean bb = new BusinessBean();
                        bb.setBusinessName(productInfoMap.get(key));
                        bb.setBusinessType(key);
                        businessBeanList.add(bb);
                    }
                }
            }
            GetBusinessListResponseData data = new GetBusinessListResponseData();

            data.setList(businessBeanList);

            response.setData(data);

            response.setCode(HttpStatus.OK.getStatusCode());

            return response;

        } catch (Exception e) {

            return handleException(GetBusinessListResponseData.class, e);
        }

    }

    /**
     * 获取订单列表，新增多业态订单
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getOrders", isRequireAuth = true)
    @ExplainAnnotation(explain = "获取订单列表，新增多业态订单")
    public Response<GetOrdersResponseData> getOrders(GetOrdersRequest request) {

        try {

            String version = request.getAppDevice().getQdVersion();
            Response<GetOrdersResponseData> response = new Response<GetOrdersResponseData>();
            GetOrdersResponseData data = new GetOrdersResponseData();
            FindMemberOrderListRequest findMemberOrderListRequest = transfor(FindMemberOrderListRequest.class, request);
            findMemberOrderListRequest.setDataType(request.getType());
            String businessType = "";
            if (!"ALL".equals(request.getBusinessType())) {
                businessType = request.getBusinessType();
            }

            //如果是商城或选择全部
            if (Constant.PRODUCT_NO_NG.equals(request.getBusinessType()) || "ALL".equals(request.getBusinessType())) {
                data.setIsShowRedeemCode(1);
            }
            findMemberOrderListRequest.setProductNo(businessType);
            findMemberOrderListRequest.setPage(request.getPageNo());
            findMemberOrderListRequest.setSize(request.getPageSize());
            FindMemberOrderListResponse findMemberOrderListResponse = orderService.findMemberOrderList(findMemberOrderListRequest);
            checkAndContinue(findMemberOrderListResponse);
            List<OrderDetailDto> orderDetailDtoList = findMemberOrderListResponse.getOrderPage().getItems();
            List<OrderBean> list = Lists.newArrayList();
            Map<String, List<String>> orderProductMap = null;

            if (QDStringUtil.isNotNull(orderDetailDtoList) && orderDetailDtoList.size() > 0) {
                Map<String, String> skipConfigMap = null;
                if (QDStringUtil.isNotEmpty(version)) {
                    version = version.trim();
                    skipConfigMap = skipMode.selSkipTemplateMap(version);
                } else {
                    logger.info("project index can't get version");
                }

                orderProductMap = new HashMap<String, List<String>>();
                for (OrderDetailDto orderDetailDto : orderDetailDtoList) {
                    try {
                        OrderBean orderBean = getOrderDetail(skipConfigMap, orderProductMap, orderDetailDto,version);
                        list.add(orderBean);
                    } catch (Exception e) {
                        logger.error(" method:getOrders is error ==>", e);
                    }
                }

            }
            JSONArray jsonArray = new JSONArray();
            if (QDStringUtil.isNotNull(orderProductMap)) {
                for (String key : orderProductMap.keySet()) {
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("product_no", key);
                    List<String> orderCodes = orderProductMap.get(key);
                    jsonObj.put("orderCodes", ListUtil.listForStringToString(orderCodes));
                    jsonArray.add(jsonObj);
                }
            }
            data.setList(list);
            //遍历map 组合json
            data.setOrderParametere(QDStringUtil.isNotNull(jsonArray) ? jsonArray.toJSONString() : "");
            data.setTotalCount(findMemberOrderListResponse.getOrderPage().getTotalCount());
            response.setData(data);
            return response;

        } catch (Exception e) {
            return handleException(GetOrdersResponseData.class, e);
        }
    }

    private OrderBean getOrderDetail(Map<String, String> skipConfigMap, Map<String, List<String>> orderProductMap, OrderDetailDto orderDetailDto,String version) throws TException {

        OrderBean orderBean = new OrderBean();
        OrderBase orderBase = orderDetailDto.getOrderBase();
        String productNo = orderBase.getMainProductNo();//为了兼容老版本，如果主业态编号为空，则获取ProductNo
        if (QDStringUtil.isEmpty(productNo)) {
            productNo = orderBase.getProductNo();
        }
        List<String> productList = null;
        if (Constant.ProductNoForOrderStatusList.contains(productNo)) {

            if (orderProductMap.containsKey(productNo)) {
                productList = orderProductMap.get(productNo);
                productList.add(orderBase.getCode());
            } else {
                productList = Lists.newArrayList();
                productList.add(orderBase.getCode());
            }
            String tempUrl = APIPropertiesClient.getSkipUrl(productNo);
            if (QDStringUtil.isNotEmpty(tempUrl)) {
                tempUrl = tempUrl + orderBase.getCode();
                String skipModeStr = skipMode.fittingSkipUrl(skipConfigMap, tempUrl, 1, 0,"");
                orderBean.setSkipModel(skipModeStr);
            }
            orderProductMap.put(productNo, productList);
        } else {
            String skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.DDXQ_4008.toInteger(), orderBase.getCode());
            orderBean.setSkipModel(skipModeStr);
            orderBean.setOrderStatus(orderBase.getOrderStatus());

            if (Constant.PRODUCT_NO_NG.equals(orderBase.getProductNo())) {
                if (Constant.orderStatusMap.containsKey(orderBase.getOrderStatus().intValue())) {
                    orderBean.setOrderStatusName( Constant.orderStatusMap.get(orderBase.getOrderStatus().intValue()));
                } else {
                    if (orderBase.getPayAt() != null && orderBase.getPayAt() > 0) {
                        orderBean.setOrderStatusName("已支付");
                    } else {
                        orderBean.setOrderStatusName("未支付");
                    }
                }
                if (Constant.PAYMENT_STATUS_108.intValue() == orderBase.getPayStatus().intValue()) {
                    orderBean.setOrderStatusName("已退款");
                }
            }
        }

        Integer orderSourceType = QDStringUtil.isNull(orderDetailDto.getSourceType())?0:orderDetailDto.getSourceType();
        orderBean.setOrderSourceType(orderSourceType);
        //组装乐购订单按钮
        List<BtnSkip> btnSkipList = fittingLgOrderSkipBtn(skipConfigMap, orderSourceType, orderBase);
        orderBean.setBtnSkipList(btnSkipList);

        //如果是乐购或洗车业态则直接通过solr获取商品图片等信息
        if (Constant.GetGoodsDetailFromSolrList.contains(orderBase.getProductNo())) {

            List<OrderSub> subOrderList = orderDetailDto.getSubOrderList();
            List<Long> skuIds = Lists.newArrayList();
            for (OrderSub orderSub : subOrderList) {
                skuIds.add(orderSub.getWareSkuId());
            }
            SkuRequest skuRequest = new SkuRequest();
            skuRequest.setSortedSkuIds(skuIds);
            SkuResponse skuResponse = solrSku.querySku(skuRequest);
            List<SkuDetailInfo> skuDetailInfoList = skuResponse.getSkus();
            List<OrderGood> orderGoods = Lists.newArrayList();
            //类如洗衣，通用业态等，如果订单下没有商品，则显示默认图片
            if (CollectionUtils.isEmpty(skuDetailInfoList)) {
                OrderGood orderGood = new OrderGood();
                orderGood.setSkuImgUrl(Constant.defaultPicForOrderListMap.get(orderBase.getProductNo()));
                orderGoods.add(orderGood);
            } else {
                for (SkuDetailInfo skuDetailInfo : skuDetailInfoList) {
                    if (Constant.MRX_PSF_MAKING_CODE.equals(skuDetailInfo.getMarkingCode()) || Constant.NEWSE_PSF_MAKING_CODE.equals(skuDetailInfo.getMarkingCode())) {
                        continue;
                    }
                    OrderGood orderGood = new OrderGood();
                    orderGood.setGoodsDesc(skuDetailInfo.getDescription());
                    orderGood.setGoodsName(skuDetailInfo.getSkuName());
                    orderGood.setSkuImgUrl(QDStringUtil.isNotNull(skuDetailInfo.getSkuImgUrl()) && skuDetailInfo.getSkuImgUrl().length > 0 ? skuDetailInfo.getSkuImgUrl() : Constant.defaultPicForOrderListMap.get(orderBase.getProductNo()));
                    orderGood.setWareId(skuDetailInfo.getWareId());
                    orderGood.setMarkingName(skuDetailInfo.getMarkingName());
                    orderGood.setMarkingCode(skuDetailInfo.getMarkingCode());
                    orderGoods.add(orderGood);
                }
            }
            orderBean.setOrderGoods(orderGoods);
        } else if (Constant.PRODUCT_NO_JF.equals(orderBase.getProductNo())) {
            //积分订单商品
            List<OrderGood> orderGoods = Lists.newArrayList();
            List<OrderSub> subOrderList = orderDetailDto.getSubOrderList();
            List<Long> skuIds = Lists.newArrayList();
            for (OrderSub orderSub : subOrderList) {
                skuIds.add(orderSub.getWareSkuId());
            }
            GetIntegralGoodsDetailRequest getIntegralGoodsDetailRequest = new GetIntegralGoodsDetailRequest();
            getIntegralGoodsDetailRequest.setSkuIds(skuIds);
            GetIntegralGoodsDetailResponse getIntegralGoodsDetailResponse = integralRemoteService.getIntegralGoodsDetail(getIntegralGoodsDetailRequest);
            List<PlatformIntegralGoods> platformIntegralGoodses = getIntegralGoodsDetailResponse.getPlatformIntegralGoodses();
            for (PlatformIntegralGoods detail : platformIntegralGoodses) {
                OrderGood orderGood = new OrderGood();
                orderGood.setGoodsDesc(detail.getGoodsDesc());
                orderGood.setGoodsName(detail.getGoodsName());
                orderGood.setSkuImgUrl(detail.getSkuImgUrl());
                orderGood.setWareId(detail.getWareId());
                orderGoods.add(orderGood);
            }
            orderBean.setOrderGoods(orderGoods);
        }
        orderBean.setOrderCode(orderBase.getCode());
        orderBean.setShouldPay(QDStringUtil.isNotEmpty(orderBase.getTotalRealpay()) ? orderBase.getTotalRealpay() : "0");
        orderBean.setBusinessType(QDStringUtil.isNotEmpty(orderBase.getMainProductNo())?orderBase.getMainProductNo():orderBase.getProductNo());
        orderBean.setBusinessName(DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_PRODUCT_NO.getGroupName(), orderBase.getProductNo()));

        //2.2 新增字段
        orderBean.setPaymentType(orderBase.getPayType());
        orderBean.setTotalRealPay(QDStringUtil.isNotEmpty(orderBase.getTotalRealpay()) ? orderBase.getTotalRealpay() : "0");
        orderBean.setTotalDiscount(QDStringUtil.isNotEmpty(orderBase.getTotalDiscount()) ? orderBase.getTotalDiscount() : "0");
        orderBean.setPaymentStatus(orderBase.getPayStatus());
        orderBean.setCreateTime(QDStringUtil.isNotNull(orderBase.getGorderAt()) ? orderBase.getGorderAt() : 0);
        orderBean.setSourceType(orderBase.getSourceType());
        orderBean.setTotalPrice(orderBase.getTotalPrice());

        return orderBean;
    }


    //组装乐购订单列表按钮
    private  List<BtnSkip>   fittingLgOrderSkipBtn(Map<String, String> skipConfigMap, Integer orderSourceType, OrderBase orderBase) throws TException {

        List<BtnSkip> btnSkipList = Lists.newArrayList();
        if (Constant.PRODUCT_NO_NG.equals(orderBase.getProductNo())) {
            if (Constant.ORDER_PAY_OVER_STATUS.intValue() == orderBase.getPayStatus().intValue() &&
                    Constant.ORDER_CANCEL_STATUS == orderBase.getOrderStatus().intValue()) {
                BtnSkip btnSkip = new BtnSkip();
                btnSkip.setBtnName("去支付");
                btnSkip.setBtnType(1);
                btnSkip.setBtnColor(Constant.ORDER_BTN_COLOR);
                btnSkip.setSkipModel("");
                btnSkipList.add(btnSkip);
            }

            // 如果是点滴订单且（是在已发货或订单完成状态）
            if (1==orderSourceType && ((OrderStatusEnum.ORDER_STATUS_COMPLETE.getValue() == orderBase.getOrderStatus().intValue())
                    || (orderBase.getDeliveryStatus() != null && Constant.DELIVER_STATUS_FOR_AFTERSALE_STATUS_LIST.contains(orderBase.getDeliveryStatus())))) {
                try {
                    GetAfterSalesDetailsByOrderCodeRequest afterSalesDetailsByOrderCodeRequest = new GetAfterSalesDetailsByOrderCodeRequest();
                    afterSalesDetailsByOrderCodeRequest.setOrderCode(orderBase.getCode());
                    GetAfterSalesDetailsByOrderCodeResponse afterSalesDetailsByOrderCodeResponse = afterSalesRemoteService.getAfterSalesDetailsByOrderCode(afterSalesDetailsByOrderCodeRequest);
                    checkAndContinue(afterSalesDetailsByOrderCodeResponse);
                    List<AfterSalesDetailDto> afterSalesDetailDtoList = afterSalesDetailsByOrderCodeResponse.getAfterSalesDetailList();
                    if(CollectionUtils.isNotEmpty(afterSalesDetailDtoList)) {
                        BtnSkip btnSkip = new BtnSkip();
                        btnSkip.setBtnName("查看售后");
                        btnSkip.setBtnType(0);
                        String skipModeStr = skipMode.fittingSkipUrl(skipConfigMap,APIPropertiesClient.getUrlContent("after_sales_detail_url")+orderBase.getCode(), 1,0,"");
                        btnSkip.setSkipModel(skipModeStr);
                        btnSkip.setBtnColor(Constant.ORDER_BTN_COLOR);
                        btnSkipList.add(btnSkip);
                    }
                } catch (ServiceException e) {
                    logger.error("invoker newsell getAfterSalesDetailsByOrderCode error:",e);
                }

                BtnSkip btnSkip = new BtnSkip();
                btnSkip.setBtnName("售后申请");
                btnSkip.setBtnType(0);
                String skipModeStr = skipMode.fittingSkipUrl(skipConfigMap,APIPropertiesClient.getUrlContent("after_sales_apply_url")+orderBase.getCode(), 1,0,"");
                btnSkip.setSkipModel(skipModeStr);
                btnSkip.setBtnColor(Constant.ORDER_BTN_COLOR);
                btnSkipList.add(btnSkip);
            } else {
                if (OrderStatusEnum.ORDER_STATUS_COMPLETE.getValue() == orderBase.getOrderStatus().intValue() &&
                        0 == orderBase.getEvaluateStatus().intValue()) {
                    BtnSkip btnSkip = new BtnSkip();
                    btnSkip.setBtnName("去评价");
                    btnSkip.setBtnType(0);
                    String skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.DDQKJ_4009.toInteger(), orderBase.getCode());
                    btnSkip.setSkipModel(skipModeStr);
                    btnSkip.setBtnColor(Constant.ORDER_BTN_COLOR);
                    btnSkipList.add(btnSkip);
                }
            }

            if (orderBase.getDeliveryStatus() != null && (Constant.ORDER_ARRIVED_STATUS.intValue() == orderBase.getDeliveryStatus().intValue() ||
                    Constant.ORDER_DELIVERYED_STATUS.intValue() == orderBase.getDeliveryStatus().intValue())) {
                BtnSkip btnSkip = new BtnSkip();
                btnSkip.setBtnName("去签收");
                btnSkip.setBtnType(2);
                btnSkip.setBtnColor(Constant.ORDER_BTN_COLOR);
                btnSkipList.add(btnSkip);
            }

            if ( btnSkipList.size()<3){
                String status = DictionaryClient.getClient().findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_ONLINE_CUSTOMER_SERVICE.getGroupName(), Constant.Dict_K_V_Enum.DICT_ONLINE_CUSTOMER_SERVICE.getDictKey());
                if ("open".equals(status)){
                    BtnSkip  serverBtn = new BtnSkip();
                    serverBtn.setBtnName("在线客服");
                    serverBtn.setBtnType(3);
                    serverBtn.setSkipModel("");
                    btnSkipList.add(serverBtn);
                }
            }



         /* BtnSkip btnSkip = new BtnSkip();
            btnSkip.setBtnName("查看详情");
            btnSkip.setBtnType(0);
            btnSkip.setSkipModel( skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.DDXQ_4008.toInteger(), orderBase.getCode()));
            btnSkipList.add(btnSkip);*/

        }
        return  btnSkipList;
    }

    /**
     * 批量获取多业态订单状态
     *
     * @param request
     * @return
     */
        @HTTP(alias = "getOrderStatus", isRequireAuth = true)
    @ExplainAnnotation(explain = "批量获取多业态订单状态")
    public Response<GetOrderStatusResponseData> getOrderStatus(GetOrderStatusRequest request) {

        Response<GetOrderStatusResponseData> response = new Response<GetOrderStatusResponseData>();
        GetOrderStatusResponseData data = new GetOrderStatusResponseData();

        String orderParametere = request.getOrderParametere();

        List<ProductOrderBean> allProductOrderBeanList = new ArrayList<ProductOrderBean>();

        if (QDStringUtil.isNotEmpty(orderParametere)) {

            JSONArray jsonArray = JSON.parseArray(orderParametere);
            int threadSize = jsonArray.size();
            List<Future<List<ProductOrderBean>>> futureList = new ArrayList<Future<List<ProductOrderBean>>>();
            for (int i = 0; i < threadSize; i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                OrderStatusInfoThread orderStatusThread = new OrderStatusInfoThread(json);
                Future<List<ProductOrderBean>> future = threadPool.submit(orderStatusThread);
                futureList.add(future);
            }

            for (Future<List<ProductOrderBean>> future : futureList) {
                try {
                    List<ProductOrderBean> productOrderBeanList1 = future.get(2, TimeUnit.SECONDS);
                    allProductOrderBeanList.addAll(productOrderBeanList1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logger.error(" getOrderStatus from future is wrong !!", e);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    logger.error(" getOrderStatus from future is wrong !!", e);
                } catch (TimeoutException e) {
                    logger.error(" getOrderStatus from future is timeout !!", e);
                    e.printStackTrace();
                }
            }
            logger.info(" [getOrderStatus] the master thread  is over!!");

        }
        String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
        if (Integer.parseInt(initVersion) < 320000) {
            //进行版本兼容
            for (ProductOrderBean productOrderBean : allProductOrderBeanList) {
                List<BtnSkip> btnSkipList = productOrderBean.getBtnSkipList();
                List<BtnSkip> delBtnSkipList = Lists.newArrayList();
                for (BtnSkip btnSkip : btnSkipList) {
                    if (btnSkip.getBtnName().equals("查看详情") ||btnSkip.getBtnName().equals("在线客服")) {
                        delBtnSkipList.add(btnSkip);
                    }
                }
                btnSkipList.removeAll(delBtnSkipList);
            }
        }

        data.setProductOrderList(allProductOrderBeanList);
        response.setData(data);
        return response;

    }

    //洗衣订单状态获取RPC
    @Autowired
    private IGetProductOrderService washRpcSerivce;

    //保洁订单状态获取RPC
    @Autowired
    private IGetProductOrderService cleanRpcSerivce;

    //旅游订单状态获取RPC
    @Autowired
    private IGetProductOrderService travelRpcSerivce;

    //洗车订单状态获取RPC
    @Autowired
    private IGetProductOrderService washCarRpcSerivce;

    //通用业态订单状态获取RPC
    @Autowired
    private IGetProductOrderService cbRpcSerivce;

    //阶梯团购订单状态获取RPC
    @Autowired
    private IGetProductOrderService tgRpcSerivce;

    //积分订单订单状态获取RPC
    @Autowired
    private IGetProductOrderService integralRpcSerivce;

    //分享团购状态获取RPC
    @Autowired
    private IGetProductOrderService sgRpcSerivce;

    //保洁状态获取RPC
    @Autowired
    private IGetProductOrderService newcleanerRpcService;

    //新旅游状态获取RPC
    @Autowired
    private IGetProductOrderService newTravelRpcService;

    //保姆状态获取RPC
    @Autowired
    private IGetProductOrderService touismServiceRpcService;

    //保姆状态获取RPC
    @Autowired
    private IGetProductOrderService babysitterRpcService;

    //车保养业态状态获取RPC
    @Autowired
    private IGetProductOrderService mnRpcService;

    //聚合业态状态获取RPC
    @Autowired
    private IGetProductOrderService csRpcService;

    //物业服务获取RPC
    @Autowired
    private IGetProductOrderService psRpcService;

    //虚拟上传获取RPC
    @Autowired
    private IGetProductOrderService vmRpcService;

    //永辉超市
    @Autowired
    private IGetProductOrderService msRpcService;
    
    //保险订单
    @Autowired
    private IInsuranceOrderListRpcService insuranceSkuService;

    class OrderStatusInfoThread implements Callable<List<ProductOrderBean>> {

        private JSONObject subThreadjson;

        OrderStatusInfoThread(JSONObject subThreadjson) {
            this.subThreadjson = subThreadjson;
        }

        @Override
        public List<ProductOrderBean> call() throws Exception {

            List<ProductOrderBean> productOrderList = new ArrayList<ProductOrderBean>();
            try {
                String productNo = subThreadjson.getString("product_no");
                String orderCodes = subThreadjson.getString("orderCodes");
                String[] orderCodeArray = orderCodes.split(",");
                List<String> orderCodList = Lists.newArrayList();
                for (int i = 0; i < orderCodeArray.length; i++) {
                    orderCodList.add(orderCodeArray[i]);
                }
                productOrderList = getProductOrderList(productNo, orderCodList);

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return productOrderList;
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

            GetOrderDetailByCodeResponse getOrderResponse = checkOrderAuth(request.getMemberId(), request.getOrderCode());

            GetOrderResponseData data = transfor(GetOrderResponseData.class, getOrderResponse);

            try {
                Integer legouStatus = getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getLegouStatus();
                data.getEntity().setLegouStatus(legouStatus);
            } catch (Exception e) {
                logger.error("get legouStatus for getOrder  is error ", e);
            }
//			OrderExt orderExt = getOrderResponse.getLegouOrderDetailDto().getOrderExt();
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
            //判断前端需要显示的收货地址 1: 配送上门 2: 物业自提
            String receiveAddr = getReceiveAddr(getOrderResponse);
            data.getEntity().setReceiveAddr(receiveAddr);
            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GetOrderResponseData.class, e);
        }
    }

    /**
     * 2.0.0 新需求
     * 判断前端需要显示的收货地址 1: 配送上门 2: 物业自提
     * 如果所有的都是物业自提，则显示物业自提地址
     * 如果有一个商品的配送方式是送货上门，则显示业主家庭地址
     *
     * @param getOrderResponse
     * @return
     */
    public String getReceiveAddr(GetOrderDetailByCodeResponse getOrderResponse) {

        int deliveryType = 2;
        for (OrderSub orderSub : getOrderResponse.getLegouOrderDetailDto().getSubOrderList()) {
            if (orderSub.getDeliveryType() != null && 1 == orderSub.getDeliveryType()) {
                deliveryType = 1;
                break;
            }
        }

        String receiveAddr = "";
        if (deliveryType == 2) {
            receiveAddr = getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPaddress();
        } else {
            receiveAddr = getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getReceiverAddress();
        }
        return receiveAddr;
    }

    /**
     * 获取指定订单信息，只包含订单商品
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getOrderGoods", isRequireAuth = true)
    @ExplainAnnotation(explain = "获取指定订单信息，只包含订单商品")
    public Response<GetOrderGoodsResponseData> getOrderGoods(GetOrderGoodsRequest request) {

        try {
            Response<GetOrderGoodsResponseData> response = new Response<GetOrderGoodsResponseData>();
            OrderDetailDto orderDetailDto =  apiOrderService.getOrderDetailByCode(request.getOrderCode());
            Map<String, List<String>> orderProductMap = new HashMap<>();
            Map<String, String> skipConfigMap = null;
            String version = request.getAppDevice().getQdVersion();
            if (QDStringUtil.isNotEmpty(version)) {
                version = version.trim();
                skipConfigMap = skipMode.selSkipTemplateMap(version);
            } else {
                logger.info("project index can't get version");
            }
            OrderBean orderBean = getOrderDetail(skipConfigMap, orderProductMap, orderDetailDto,version);
            GetOrderGoodsResponseData data = new GetOrderGoodsResponseData();
            data.setEntity(orderBean);
            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GetOrderGoodsResponseData.class, e);
        }
    }

    /**
     * 获取物流跟单追踪
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getLogisticsInfo", isRequireAuth = true)
    @ExplainAnnotation(explain = "获取物流跟单追踪")
    public Response<LogisticsInfoResponseData> getLogisticsInfo(LogisticsInfoRequest request) {

        Response<LogisticsInfoResponseData> response = new Response<LogisticsInfoResponseData>();
        response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
        response.setData(new LogisticsInfoResponseData());
        LogisticsInfoResponseData data = new LogisticsInfoResponseData();
        List<LogisticsBean> logisticsBeanList = Lists.newArrayList();

        try {
            GetExpressResponse expressResponse = logisticsPlatformOrder.getExpress(request.getOrderCode());
            if (HttpStatus.OK.getStatusCode() == expressResponse.getReturnInfo().getCode() &&
                    CollectionUtils.isNotEmpty(expressResponse.getLogs())) {
                List<LogisticsLogDto> logisticsLogList = expressResponse.getLogs();
                logisticsBeanList = transforList(LogisticsBean.class, logisticsLogList);
                Integer delveryType = expressResponse.getDeliveryType();
                if (delveryType.intValue() == 0) { //快递配送
                    data = transfor(LogisticsInfoResponseData.class, expressResponse.getDeliverExpressInfo());
                } else { //自行配送
                    data = transfor(LogisticsInfoResponseData.class, expressResponse.getDeliverOwnerInfo());
                }
                data.setDeliveryType(delveryType);
            }
            data.setList(logisticsBeanList);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(LogisticsInfoResponseData.class, e);
        }
    }

    /**
     * 确认收货商品（签收功能）
     *
     * @param request
     * @return
     */
    @HTTP(alias = "confirmReceiptGoods", isRequireAuth = true)
    @ExplainAnnotation(explain = "确认收货商品（签收功能）")
    public Response<ResponseData> confirmReceiptGoods(ConfirmReceiptGoodsRequest request) {

        try {
            Response<ResponseData> response = new Response<>();
            GetOrderDetailByCodeRequest orderDetailByCodeRequest = new GetOrderDetailByCodeRequest();
            orderDetailByCodeRequest.setOrderCode(request.getOrderCode());
            GetOrderDetailByCodeResponse orderDetailByCodeResponse = legouRemoteService.getOrderDetailByCode(orderDetailByCodeRequest);
            checkAndContinue(orderDetailByCodeResponse);
            String mid = orderDetailByCodeResponse.getLegouOrderDetailDto().getLegouOrder().getMid();
            String name = orderDetailByCodeResponse.getLegouOrderDetailDto().getLegouOrder().getReceiverName();
            OrderSignRequest orderSignRequest = new OrderSignRequest();
            orderSignRequest.setOptId(mid);
            orderSignRequest.setOptName(name);
            orderSignRequest.setOrderCodes(Lists.newArrayList(request.getOrderCode()));
            BaseResponse baseResponse = logisticsPlatformOrder.orderSign(orderSignRequest);
            checkAndContinue(baseResponse);

            response.setData(new ResponseData());

            return response;

        } catch (Exception e) {

            return handleException(ResponseData.class, e);
        }
    }

    public List<ProductOrderBean> getProductOrderList(String productNo, List<String> orderCodList) {
        List<ProductOrderBean> productOrderList = Lists.newArrayList();
        switch (productNo) {

            //洗衣业态订单
            case Constant.PRODUCT_NO_XY:
                List<com.qding.framework.common.service.order.ProductOrderBean> washList = washRpcSerivce.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, washList);
                break;

            //保洁业态订单
            case Constant.PRODUCT_NO_BJ:

                List<com.qding.framework.common.service.order.ProductOrderBean> cleanList = cleanRpcSerivce.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, cleanList);
                break;

            //旅游业态订单
            case Constant.PRODUCT_NO_LY:

                List<com.qding.framework.common.service.order.ProductOrderBean> travelList = travelRpcSerivce.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, travelList);
                break;

            //洗车业态订单
            case Constant.PRODUCT_NO_XC:

                List<com.qding.framework.common.service.order.ProductOrderBean> washCarlList = washCarRpcSerivce.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, washCarlList);
                break;

            //阶梯团购业态订单
            case Constant.PRODUCT_NO_TG:
                List<com.qding.framework.common.service.order.ProductOrderBean> tglList = tgRpcSerivce.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, tglList);
                break;

            //通用业态订单
            case Constant.PRODUCT_NO_CB:
                List<com.qding.framework.common.service.order.ProductOrderBean> cbList = cbRpcSerivce.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, cbList);
                break;

            //分享团购订单
            case Constant.PRODUCT_NO_SG:
                List<com.qding.framework.common.service.order.ProductOrderBean> sgList = sgRpcSerivce.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, sgList);
                break;

            //积分订单
            case Constant.PRODUCT_NO_JF:
                List<com.qding.framework.common.service.order.ProductOrderBean> integralList = integralRpcSerivce.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, integralList);
                break;

            //最新保洁
            case Constant.PRODUCT_NO_NC :
                List<com.qding.framework.common.service.order.ProductOrderBean> newClearList = newcleanerRpcService.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, newClearList);
                break;

            //旅游线路
            case Constant.PRODUCT_NO_TL:
                List<com.qding.framework.common.service.order.ProductOrderBean> newTravelList = newTravelRpcService.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, newTravelList);
                break;

            //保姆
            case Constant.PRODUCT_NO_BM:
                List<com.qding.framework.common.service.order.ProductOrderBean> babysitterList = babysitterRpcService.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, babysitterList);
                break;
            //旅游服务
            case Constant.PRODUCT_NO_TS:
                List<com.qding.framework.common.service.order.ProductOrderBean> touismList = touismServiceRpcService.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, touismList);
                break;

            //聚合业态
            case Constant.PRODUCT_NO_CS:
                List<com.qding.framework.common.service.order.ProductOrderBean> csList = csRpcService.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, csList);
                break;

            //车保养
            case Constant.PRODUCT_NO_MN:
                List<com.qding.framework.common.service.order.ProductOrderBean> mnList = mnRpcService.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, mnList);
                break;

            //物业服务
            case Constant.PRODUCT_NO_PS:
                List<com.qding.framework.common.service.order.ProductOrderBean> psList = psRpcService.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, psList);
                break;

            //虚拟商城
            case Constant.PRODUCT_NO_VM:
                List<com.qding.framework.common.service.order.ProductOrderBean> vmList = vmRpcService.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, vmList);
                break;

            //永辉超市
            case Constant.PRODUCT_NO_MS:
                List<com.qding.framework.common.service.order.ProductOrderBean> msList = msRpcService.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, msList);
                break;
               
              //保险
            case Constant.PRODUCT_NO_BX:
                List<com.qding.framework.common.service.order.ProductOrderBean> bxList = insuranceSkuService.getProductOrder(orderCodList);
                productOrderList = transforList(ProductOrderBean.class, bxList);
                break;
                
            default:
                break;

        }
        return productOrderList;
    }

}
