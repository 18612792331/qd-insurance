package com.qding.api.call.app.qding.v2_0_0;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.LogisticsBean;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request.ConfirmReceiptGoodsRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.platform.order.PlatformOrder;
import com.qding.api.call.app.qding.v2_0_0.struct.platform.order.request.*;
import com.qding.api.call.app.qding.v2_0_0.struct.platform.order.response.data.*;
import com.qding.api.call.app.qding.v2_0_0.struct.platform.order.LogisticsForSubCode;
import com.qding.api.call.service.OrderService;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.legou.struct.request.GetOrderDetailByCodeRequest;
import com.qding.logistics.platform.client.remote.LogisticsPlatformOrderRpcService;
import com.qding.logistics.platform.client.remote.struct.bean.LogisticsLogDto;
import com.qding.logistics.platform.client.remote.struct.bean.LogisticsOrderDeliveryBean;
import com.qding.logistics.platform.client.remote.struct.request.OrderSignRequest;
import com.qding.logistics.platform.client.remote.struct.response.GetExpressResponse;
import com.qding.oder.dto.OrderDetailDto;
import com.qding.order.service.IRemoteOrderService;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.promotion.query.request.GetSkuPromotionInfoRequest;
import com.qding.promotion.query.request.GetWarePromotionInfoRequest;
import com.qding.promotion.query.service.IPromotionQueryService;
import com.qding.sysconfig.domain.ClauseConfig;
import com.qding.sysconfig.domain.IndexRecommendContentGoods;
import com.qding.sysconfig.dto.IndexRecommendDTO;
import com.qding.sysconfig.rpc.response.AppHomeConfigProductsResponse;
import com.qding.sysconfig.rpc.service.AppHomeConfigRpcService;
import com.qding.sysconfig.rpc.service.ClauseConfigRpcService;
import com.qding.sysconfig.rpc.service.IndexRecommendRpcService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qd on 2016/3/17.
 */
public class CallPlateFormOrder extends com.qding.api.call.app.qding.v1_3_0.CallPlateFormOrder {

    @Autowired
    private IRemoteOrderService orderService;

    @Autowired
    private IndexRecommendRpcService indexRecommendRpcService;

    @Autowired
    private ClauseConfigRpcService clauseConfigRpcService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private AppHomeConfigRpcService appHomeConfigRpcService;

    @Autowired
    private IProfileService profileAPI;

    @Autowired
    private LogisticsPlatformOrderRpcService logisticsPlatformOrder;

    @Autowired
    private OrderService apiOrderService;

    @Autowired
    private IPromotionQueryService promotionQueryService;

    private static Logger logger = Logger.getLogger(CallPlateFormOrder.class);

    @HTTP(alias = "getPlatformOrderDetail")
    @ExplainAnnotation(explain = "获取平台订单详情")
    public Response<GetPlatformOrderDetailResponseData> getPlatformOrderDetail(GetPlatformOrderDetailRequest request) {

        Response<GetPlatformOrderDetailResponseData> response = new Response<GetPlatformOrderDetailResponseData>();
        GetPlatformOrderDetailResponseData data = new GetPlatformOrderDetailResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            OrderDetailDto orderDetailDto  = apiOrderService.getOrderDetailByCode(request.getOrderId());
            PlatformOrder entity = transfor(PlatformOrder.class,orderDetailDto);
            Constant.PayTypeEnum payTypeEnum = Constant.PayTypeEnum.getByCode(orderDetailDto.getOrderBase().getPayType());
            if(QDStringUtil.isNotNull(payTypeEnum)){
                entity.getOrderBase().setPaymentTypeName(payTypeEnum.getDescription());
            }
            data.setEntity(entity);
            response.setData(data);
        } catch (ServiceException e) {
            return handleException(GetPlatformOrderDetailResponseData.class, e);
        }
        return response;
    }


    @HTTP(alias = "getGoodsIdList")
    @ExplainAnnotation(explain = "根据推荐活动详情ID获取通用业态商品列表")
    public Response<GetSkuIdListResponseData> getGoodsIdList(GetSkuIdListRequest request) {

        Response<GetSkuIdListResponseData> response = new Response<GetSkuIdListResponseData>();
        GetSkuIdListResponseData data = new GetSkuIdListResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        List<String> skuIds = Lists.newArrayList();

        try {
            String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
            if (Integer.parseInt(initVersion) >= 300000) {
                logger.info("method : getGoodsIdList request :" + JSON.toJSONString(request.getIndexId()));
                AppHomeConfigProductsResponse appHomeConfigProductsResponse = appHomeConfigRpcService.getAppHomeConfigProducts(Long.parseLong(request.getIndexId()));
                logger.info("method : getGoodsIdList response :" + JSON.toJSONString(appHomeConfigProductsResponse));
                checkAndContinue(appHomeConfigProductsResponse);
                 skuIds = appHomeConfigProductsResponse.getProducts();
            } else {
                IndexRecommendDTO indexRecommendDTO = indexRecommendRpcService.getIndexRecommendByContentId(request.getIndexId());
                if (CollectionUtils.isNotEmpty(indexRecommendDTO.getSkuIdList())) {
                    List<IndexRecommendContentGoods> indexRecommendContentGoodsList = indexRecommendDTO.getSkuIdList();
                    for (IndexRecommendContentGoods indexRecommendContentGoods : indexRecommendContentGoodsList) {
                        skuIds.add(indexRecommendContentGoods.getSkuId());
                    }
                }
            }

            data.setSkuIds(skuIds);
            response.setData(data);

        } catch (Exception e) {
            return handleException(GetSkuIdListResponseData.class, e);
        }
        return response;

    }

    @HTTP(alias = "getClauseConfigByProductNo")
    @ExplainAnnotation(explain = "通过业态编号获取条款信息")
    public Response<GetClauseConfigByProductNoResponseData> getClauseConfigByProductNo (GetClauseConfigByProductNoRequest request) {

        Response<GetClauseConfigByProductNoResponseData> response = new  Response<GetClauseConfigByProductNoResponseData>();
        GetClauseConfigByProductNoResponseData data = new GetClauseConfigByProductNoResponseData();
        List< com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.ClauseConfig> clauseConfigList = Lists.newArrayList();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            List<ClauseConfig> clauseConfigs = clauseConfigRpcService.selClauseConfigByProductNo(request.getProductNo().trim());
            if (QDStringUtil.isNotNull(clauseConfigs) && clauseConfigs.size()>0){
                clauseConfigList = transforList(com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.ClauseConfig.class,clauseConfigs);

            }
        }catch (Exception ex) {
            logger.error("获取业态"+request.getProductNo()+"条款错误：",ex);
        }

        data.setList(clauseConfigList);
        response.setData(data);
        return response;
    }


    /**
     * 确认收货商品（签收功能）
     *
     * @param request
     * @return
     */
    @HTTP(alias = "signPackage", isRequireAuth = true)
    @ExplainAnnotation(explain = "确认收货平台商品（签收功能）")
    public Response<ResponseData> signPackage(ConfirmReceiptGoodsRequest request, UserToken userToken) {

        try {
            Response<ResponseData> response = new Response<>();
            GetMemberRequest  memberRequest = new GetMemberRequest();
            memberRequest.setMemberId(userToken.getMemberId());
            GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);
            checkAndContinue(memberResponse);
            MemberInfo memberInfo = memberResponse.getMemberInfo();
            OrderSignRequest orderSignRequest = new OrderSignRequest();
            orderSignRequest.setOptId(userToken.getMemberId());
            orderSignRequest.setOptName(memberInfo.getName());
            orderSignRequest.setOrderCodes(Lists.newArrayList(request.getOrderCode()));
            BaseResponse baseResponse = logisticsPlatformOrder.orderSign(orderSignRequest);
            checkAndContinue(baseResponse);
            response.setData(new ResponseData());
            return response;

        } catch (Exception e) {
            return handleException(ResponseData.class, e);
        }
    }



    /**
     * 获取物流跟单追踪
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getLogisticsInfoByPlateFormOrderCode",isRequireAuth = true)
    @ExplainAnnotation(explain = "获取物流跟单追踪,全平台订单维度")
    public Response<LogisticsInfoByPlateFormOrderCodeResponseData> getLogisticsInfoByPlateFormOrderCode(LogisticsInfoByPlateFormOrderCodeRequest request) {

        Response<LogisticsInfoByPlateFormOrderCodeResponseData> response = new Response<LogisticsInfoByPlateFormOrderCodeResponseData>();
        LogisticsInfoByPlateFormOrderCodeResponseData data = new LogisticsInfoByPlateFormOrderCodeResponseData();
        List<LogisticsForSubCode> list = Lists.newArrayList();
        try {
            GetOrderDetailByCodeRequest getOrderRequest = new GetOrderDetailByCodeRequest();
            getOrderRequest.setOrderCode(request.getOrderCode());
            List<LogisticsOrderDeliveryBean> logisticsOrderList =  apiOrderService.getOrderLogisticsData(request.getOrderCode());
            if (CollectionUtils.isNotEmpty(logisticsOrderList)) {
                for (LogisticsOrderDeliveryBean logisticsOrderDeliveryBean : logisticsOrderList) {
                    String subOrderCode = logisticsOrderDeliveryBean.getOrderSubCode();
                    GetExpressResponse expressResponse = logisticsPlatformOrder.getExpress(subOrderCode);
                    if (HttpStatus.OK.getStatusCode() == expressResponse.getReturnInfo().getCode() &&
                            CollectionUtils.isNotEmpty(expressResponse.getLogs())) {
                        List<LogisticsBean> logisticsBeanList = Lists.newArrayList();
                        LogisticsForSubCode logisticsSubCode = new LogisticsForSubCode();
                        List<LogisticsLogDto> logisticsLogList = expressResponse.getLogs();
                        logisticsBeanList = transforList(LogisticsBean.class, logisticsLogList);
                        if (expressResponse.getDeliveryType().intValue() == 0) { //快递配送
                            logisticsSubCode.setLogisticsName(expressResponse.getDeliverExpressInfo().getLogisticsCompany());
                            logisticsSubCode.setLogisticsCode(expressResponse.getDeliverExpressInfo().getLogisticsCode());

                        } else { //自行配送
                            logisticsSubCode.setLogisticsName(expressResponse.getDeliverOwnerInfo().getDeliveryName());
                            logisticsSubCode.setLogisticsCode(expressResponse.getDeliverOwnerInfo().getDeliveryMobile());
                        }
                        logisticsSubCode.setList(logisticsBeanList);
                        logisticsSubCode.setSubOrderCode(subOrderCode);
                        list.add(logisticsSubCode);
                    }
                }

            }
            data.setList(list);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
        } catch (Exception e) {
            return handleException(LogisticsInfoByPlateFormOrderCodeResponseData.class, e);
        }
        return response;

    }


    @HTTP(alias = "queryWarePromotionInfo", isRequireAuth = true,isNeadToken = true,isNeadProject = true)
    @ExplainAnnotation(explain = "查询促销信息，列表展示用")
    public Response<WarePromotionInfoResponse> queryWarePromotionInfo(final QueryWarePromotionInfoRequest request, final UserToken userToken) {
        try {
            Response<WarePromotionInfoResponse> response = new Response<WarePromotionInfoResponse>();
            WarePromotionInfoResponse warePromotionInfoResponse = new WarePromotionInfoResponse();
            GetWarePromotionInfoRequest getWarePromotionInfoRequest = new GetWarePromotionInfoRequest();
            getWarePromotionInfoRequest.setMid(userToken.getMemberId());
            getWarePromotionInfoRequest.setProductNo(request.getProductNo());
            getWarePromotionInfoRequest.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
            getWarePromotionInfoRequest.setWareIdList(request.getWareIdList());
            com.qding.promotion.query.response.WarePromotionInfoResponse warePromotionInfoResponseInterface = promotionQueryService.getWarePromotionInfo(getWarePromotionInfoRequest);
            checkAndContinue(warePromotionInfoResponseInterface);
            transfor(warePromotionInfoResponse,warePromotionInfoResponseInterface);
            response.setData(warePromotionInfoResponse);
            response.setCode(HttpStatus.OK.getStatusCode());
            return response;
        } catch (Exception e) {
            return handleException(WarePromotionInfoResponse.class, e);
        }
    }

    @HTTP(alias = "getSkuPromotionInfo", isRequireAuth = true, isNeadToken = true,isNeadProject = true)
    @ExplainAnnotation(explain = "根据skuId查询促销标签和促销价格")
    public Response<SkuPromotionInfoResponse> getSkuPromotionInfo(final GetSkuPromotionsInfoRequest request, final UserToken userToken) {
        try {
            Response<SkuPromotionInfoResponse> response = new Response<SkuPromotionInfoResponse>();
            SkuPromotionInfoResponse skuPromotionInfoResponse = new SkuPromotionInfoResponse();
            GetSkuPromotionInfoRequest getSkuPromotionInfoRequest = new GetSkuPromotionInfoRequest();
            getSkuPromotionInfoRequest.setMid(userToken.getMemberId());
            getSkuPromotionInfoRequest.setProductNo(request.getProductNo());
            getSkuPromotionInfoRequest.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
            getSkuPromotionInfoRequest.setSkuId(request.getSkuId());
            com.qding.promotion.query.response.SkuPromotionInfoResponse skuPromotionInfoResponseInterface = promotionQueryService.getSkuPromotionInfo(getSkuPromotionInfoRequest);
            checkAndContinue(skuPromotionInfoResponseInterface);
            transfor(skuPromotionInfoResponse,skuPromotionInfoResponseInterface);
            response.setData(skuPromotionInfoResponse);
            response.setCode(HttpStatus.OK.getStatusCode());
            return response;
        } catch (Exception e) {
            return handleException(SkuPromotionInfoResponse.class, e);
        }
    }


}
