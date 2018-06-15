package com.qding.api.call.app.qding.v3_1_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.BuyGoodInfoBean;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrderGroup;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrdersGroupBySupplierBean;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.Cart;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.LogisticsInfo;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.RedeemCode;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.request.GetOrderRequest;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.response.data.GetOrderResponseData;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.PropertySelfOrderGroup;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.MrxOrderGroup;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.SettlingChargeBean;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.SkuSettlingCharge;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.request.*;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.response.data.ConfirmOrderResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.response.data.GetCartResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.response.data.GetGoodsSettlingChargeResponseDate;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.MakeOrderResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.response.data.GetOrderPayStatusResponseData;
import com.qding.api.call.service.MemberService;
import com.qding.api.call.service.OrderService;
import com.qding.api.call.service.PromotionService;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.HttpMethod;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ProjectConnect;
import com.qding.brick.pojo.ware.WareMarkingConf;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.ware.WareRemoteService;
import com.qding.framework.common.api.struct.ReturnInfo;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.legou.domain.OrderGoods;
import com.qding.legou.dto.LegouGoodsThirdCodeDetailDto;
import com.qding.legou.dto.SkuStockParamDto;
import com.qding.legou.dto.ThirdSkuProvinceStockDto;
import com.qding.legou.dto.ThirdSkuStockParamDto;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.BatchGetThirdSkuProvinceStockRequest;
import com.qding.legou.struct.request.CreateGorderRequest;
import com.qding.legou.struct.request.GetUserCartRequest;
import com.qding.legou.struct.response.BatchGetThirdSkuProvinceStockResponse;
import com.qding.legou.struct.response.CreateGorderResponse;
import com.qding.legou.struct.response.GetOrderDetailByCodeResponse;
import com.qding.legou.struct.response.GetUserCartResponse;
import com.qding.member.model.MemberAddress;
import com.qding.member.rpc.IMemberAddressRPC;
import com.qding.member.rpc.response.memberaddress.MemberAddressResponse;
import com.qding.newsell.constant.ThirdpartChannelEnum;
import com.qding.oder.dto.OrderGeneratorDto;
import com.qding.oder.dto.SubOrderDto;
import com.qding.order.domain.OrderInvoice;
import com.qding.order.domain.OrderReceiver;
import com.qding.order.service.IRemoteOrderService;
import com.qding.order.struct.response.GetOrderPayStatusResponse;
import com.qding.promotion.common.service.IPromotionGrouponRpcService;
import com.qding.promotion.common.struct.request.CheckGrouponSkuRequest;
import com.qding.promotion.common.struct.response.base.BaseCheckResponse;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by qd on 2017/3/27.
 */
public class CallLegouOrder extends com.qding.api.call.app.qding.v3_0_0.CallLegouOrder {

    @Autowired
    private ISolrSkuService skuService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private ILegouRemoteService legouRemoteService;

    @Autowired
    private WareRemoteService wareRemoteService;

    @Autowired
    private IMemberAddressRPC memberAddressService;

    @Autowired
    private IRemoteOrderService orderService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private OrderService apiOrderService;
    
    @Autowired
    IPromotionGrouponRpcService promotionGrouponRpcService;

    @Autowired
    private MemberService memberService;


    private static final Logger logger = Logger.getLogger("CallLegouOrder");


    @HTTP(alias = "getOrderPayStatus", isNeadToken = true, isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "获取订单支付状态")
    public Response<GetOrderPayStatusResponseData> getOrderPayStatus(GetOrderPayStatusRequest request) {

        Response<GetOrderPayStatusResponseData> response = new Response<GetOrderPayStatusResponseData>();
        GetOrderPayStatusResponseData data = new GetOrderPayStatusResponseData();
        try {
            GetOrderPayStatusResponse orderPayStatusResponse = orderService.getOrderPayStatus(request.getOrderId());
            checkAndContinue(orderPayStatusResponse);
            Integer payStatus = orderPayStatusResponse.getPayStatus();
            data.setPayStatus(0);
            if (Constant.ORDER_PAY_END_STATUS == payStatus) { //如果已支付
                data.setPayStatus(1);
            }
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
        } catch (ServiceException e) {
            return handleException(GetOrderPayStatusResponseData.class, e);
        }

        return response;

    }


    @HTTP(alias = "getCarts", isRequireAuth = true, isNeedSign = false,  isNeadToken = true, isNeadProject = true)
    @ExplainAnnotation(explain = "获取购物车列表")
    public Response<GetCartResponseData> getCarts(GetCartRequest request, UserToken userToken) {

        Response<GetCartResponseData> response = new Response<GetCartResponseData>();
        GetCartResponseData data = new GetCartResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        String memberId = userToken.getMemberId();
        Long projectId = Long.parseLong(request.getAppUser().getProjectId());

        try {
            GetUserCartRequest getCartRequest = new GetUserCartRequest();
            getCartRequest.setProjectId(projectId);
            getCartRequest.setMid(memberId);
            GetUserCartResponse getUserCartResponse = legouRemoteService.getUserCart(getCartRequest);
            checkAndContinue(getUserCartResponse);

            if (QDStringUtil.isNotNull(getUserCartResponse) && CollectionUtils.isNotEmpty(getUserCartResponse.getCatList())) {
                List<com.qding.legou.domain.Cart> catList = getUserCartResponse.getCatList();
                Map<Long, com.qding.legou.domain.Cart> cartMap = new HashMap<>();
                ArrayList<Long> skuIds = new ArrayList<>();
                for (com.qding.legou.domain.Cart cartEntity : catList) {
                    Long skuId = cartEntity.getWareSkuId();
                    skuIds.add(skuId);
                    cartMap.put(skuId, cartEntity);
                }

                //批量获取商品信息
                List<LegouSkuDetailInfo> skus = batchGetSkuInfo(skuIds, true, true);
                //重新遍历存储真实存在的商品ID
                List<Long> wareSkuIds = Lists.newArrayList();
                List<ThirdSkuStockParamDto> thirdSkuBnList =Lists.newArrayList();
                for (LegouSkuDetailInfo sku : skus) {
                    if(sku.getSourceType() == ThirdpartChannelEnum.DD.getChannel().intValue() && QDStringUtil.isNotEmpty(sku.getThirdSkuBn())) {
                        ThirdSkuStockParamDto thirdSkuStockParamDto = new ThirdSkuStockParamDto();
                        com.qding.legou.domain.Cart cart = cartMap.get(Long.parseLong(sku.getSkuId()));
                        thirdSkuStockParamDto.setNum(cart.getCount());
                        thirdSkuStockParamDto.setSkuBn(sku.getThirdSkuBn());
                        thirdSkuBnList.add(thirdSkuStockParamDto);
                    }
                    wareSkuIds.add(Long.parseLong(sku.getSkuId()));
                }

                //获取第三方货品实时库存
                batchGetThirdSkuStock(memberId, projectId, skus, thirdSkuBnList);
                //批量获取商品限购信息
                Map<Long, Map<String, Object>> limitMap = promotionService.batchGetSkuLimitListByWareIds(memberId, wareSkuIds,projectId);
                //批量获取商品促销信息
                Map<Long, List<GoodsPromotion>> goodsPromotionMap = promotionService.batchGetGoodsPromotions(wareSkuIds, projectId);
                //批量获取商品最优促销信息
                Map<Long,GoodsPromotion> optimizePromotionMap = promotionService.batchGetGoodsOptimizePromotion (wareSkuIds,projectId);
                //组装最终列表
                data = fittingCartObj( cartMap, skus, limitMap, goodsPromotionMap,optimizePromotionMap);
            }
            response.setData(data);
            //3.2检查阶梯团购是否在购物车
            checkCart(response,request);
            return response;

        } catch (Exception e) {
            return handleException(GetCartResponseData.class, e);
        }
    }

    /**
     * 获取第三方货品实时库存
     * @param memberId
     * @param projectId
     * @param skus
     * @param thirdSkuBnList
     * @throws Exception
     */
    private void batchGetThirdSkuStock(String memberId, Long projectId, List<LegouSkuDetailInfo> skus, List<ThirdSkuStockParamDto> thirdSkuBnList) {
        //获取第三方商品库存
        if(CollectionUtils.isNotEmpty(thirdSkuBnList)) {

            try {
                Long provinceId = null;
                Long countyId = null;
                Long cityId = null;
                List<MemberAddress> memberAddressList = memberService.getMemberAddressList(memberId,String.valueOf(projectId));
                if(CollectionUtils.isNotEmpty(memberAddressList)) {
                    MemberAddress memberAddress = memberAddressList.get(0);
                    provinceId = Long.parseLong(String.valueOf(memberAddress.getProvinceId()));
                    countyId = Long.parseLong(String.valueOf(memberAddress.getAreaId()));
                    cityId = Long.parseLong(String.valueOf(memberAddress.getCityId()));
                } else {
                    Project project = projectReadService.get(projectId);
                    provinceId = project.getProvinceId();
                    countyId = project.getDistrictId();
                    cityId  =project.getRegionId();
                }

                if( QDStringUtil.isNotNull(provinceId) && QDStringUtil.isNotNull(countyId) && QDStringUtil.isNotNull(cityId)) {
                    BatchGetThirdSkuProvinceStockRequest batchGetThirdSkuProvinceStockRequest = new BatchGetThirdSkuProvinceStockRequest();
                    batchGetThirdSkuProvinceStockRequest.setThirdSkuBns(thirdSkuBnList);
                    batchGetThirdSkuProvinceStockRequest.setProvinceId(provinceId);
                    batchGetThirdSkuProvinceStockRequest.setCountyId(countyId);
                    batchGetThirdSkuProvinceStockRequest.setCityId(cityId);
                    BatchGetThirdSkuProvinceStockResponse batchGetThirdSkuProvinceStockResponse = legouRemoteService.bathGetThirdSkuProvinceStock(batchGetThirdSkuProvinceStockRequest);
                    ReturnInfo returnInfo =  batchGetThirdSkuProvinceStockResponse.getReturnInfo();
                    if(HttpStatus.OK.getStatusCode() == returnInfo.getCode()) {
                        List<ThirdSkuProvinceStockDto>  thirdSkuProvinceStockDtoList = batchGetThirdSkuProvinceStockResponse.getList();
                        if(CollectionUtils.isNotEmpty(thirdSkuProvinceStockDtoList)) {
                            Map<String,ThirdSkuProvinceStockDto> thirdSkuProvinceStockMap = new HashMap<>();
                            for (ThirdSkuProvinceStockDto thirdSkuProvinceStockDto : thirdSkuProvinceStockDtoList) {
                                thirdSkuProvinceStockMap.put(thirdSkuProvinceStockDto.getSkunBn(),thirdSkuProvinceStockDto);
                            }

                            for (LegouSkuDetailInfo sku : skus) {
                                if(thirdSkuProvinceStockMap.containsKey(sku.getThirdSkuBn())) {
                                    ThirdSkuProvinceStockDto thirdSkuProvinceStock = thirdSkuProvinceStockMap.get(sku.getThirdSkuBn());
                                    sku.setCountStock(Long.parseLong(String.valueOf(thirdSkuProvinceStock.getStock())));
                                }
                            }
                        }
                    } else {
                        logger.error("API method:getCarts invoker  rpc method: bathGetThirdSkuProvinceStock error :" + returnInfo.getMessage());
                    }

                }
            } catch (Exception e) {
                logger.error("API method:getCarts invoker  rpc method: bathGetThirdSkuProvinceStock error :" ,e);
            }
        }
    }

    /**
     * @Description: 检查购物车是否包含阶梯团购，如果是讲商品移动到无效列表
     * @param response
     * @param request
     */
    private void checkCart(Response<GetCartResponseData> response,GetCartRequest request){
    	List<Long> skus=new ArrayList<Long>();
    	if(response.getCode()==HttpStatus.OK.getStatusCode()){
    		List<Cart> effectiveList=response.getData().getEffectiveList();
    		if(effectiveList!=null && effectiveList.size()>0){
    			for(Cart cart:effectiveList){
    				skus.add(Long.parseLong(cart.getSkuId()));
    			}
    		}
    		
    	}
    	if(skus.size()>0){
    		CheckGrouponSkuRequest reques=new CheckGrouponSkuRequest();
    		reques.setSkuIdList(skus);
    		reques.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
    		BaseCheckResponse<Long> res=promotionGrouponRpcService.isGrouponSku(reques);
    		List<Cart> invalid=new ArrayList<Cart>();
    		if(res.getReturnInfo().getCode()==HttpStatus.OK.getStatusCode()){
    			for(Cart cart: response.getData().getEffectiveList()){
    				boolean result=res.getResultMap().get(Long.parseLong(cart.getSkuId()));
    				if(result){
    					cart.setStatusTag("阶梯团购");
    					invalid.add(cart);
    				}
    				logger.info("阶梯团购-----skuId="+cart.getSkuId()+" result="+result);
    			}
    			response.getData().getEffectiveList().removeAll(invalid);
    			response.getData().getInvalidList().addAll(invalid);
    		}
    		
    	}
    	
    	
    }
    
    
    @HTTP(alias = "getGoodsSettlingCharge", isNeadToken = true, isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "选购商品结算计价")
    public Response<GetGoodsSettlingChargeResponseDate> getGoodsSettlingCharge(GetGoodsSettlingChargeRequest request, UserToken userToken) {

        Response<GetGoodsSettlingChargeResponseDate> response = new Response<GetGoodsSettlingChargeResponseDate>();
        GetGoodsSettlingChargeResponseDate data = new GetGoodsSettlingChargeResponseDate();
        List<SkuSettlingCharge> qSkuList = request.getSkus();
        List<Sku> mrxSkuList = Lists.newArrayList();
        List<Sku> commonSkuList = Lists.newArrayList();

        try {
            if (CollectionUtils.isNotEmpty(qSkuList)) {
                for (SkuSettlingCharge skuSettlingCharge : qSkuList) {
                    if (1 == skuSettlingCharge.getChargeType()) {
                        mrxSkuList.add(skuSettlingCharge);
                    } else {
                        commonSkuList.add(skuSettlingCharge);
                    }
                }
            }
            List<SettlingChargeBean> list = Lists.newArrayList();
            com.qding.api.call.app.qding.v1_4_0.struct.legou.order.response.data.ConfirmOrderResponseData confirmOrderResponse = null;
            if (CollectionUtils.isNotEmpty(mrxSkuList)) {
                confirmOrderResponse = fittingConfirmOrderResponseObjFor30(request.getAppUser().getProjectId(), userToken.getMemberId(), mrxSkuList,
                        request.getOrderSourceType(), request.getCouponCodes(), request.getOrderPromotionIds(), request.getIsUserChooseCoupon(), true,null);
                SettlingChargeBean settlingCharge = transfor(SettlingChargeBean.class, confirmOrderResponse.getEntity());
                settlingCharge.setChargeType(1);
                list.add(settlingCharge);
            }

            if (CollectionUtils.isNotEmpty(commonSkuList)) {
                confirmOrderResponse = fittingConfirmOrderResponseObjFor30(request.getAppUser().getProjectId(), userToken.getMemberId(), commonSkuList,
                        request.getOrderSourceType(), request.getCouponCodes(), request.getOrderPromotionIds(), request.getIsUserChooseCoupon(), false,null);
                SettlingChargeBean settlingCharge = transfor(SettlingChargeBean.class, confirmOrderResponse.getEntity());
                settlingCharge.setChargeType(2);
                list.add(settlingCharge);
            }
            data.setList(list);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);

        } catch (Exception ex) {

            return handleException(GetGoodsSettlingChargeResponseDate.class, ex);
        }
        return response;
    }


    @HTTP(alias = "confirmOrder", isNeadToken = true, isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "确定订单")
    public Response<ConfirmOrderResponseData> confirmOrder(ConfirmOrderRequest request, UserToken userToken) {

        Response<ConfirmOrderResponseData> response = new Response<ConfirmOrderResponseData>();
        List<Sku> skuList = request.getSkus();
        List<BuyGoodInfoBean> buyGoodInfoBeanList = Lists.newArrayList();
        ArrayList<Long> skuIds = new ArrayList<>();
        Long projectId = Long.parseLong(request.getAppUser().getProjectId());
        String memberId = userToken.getMemberId();

        if (CollectionUtils.isNotEmpty(skuList)) {
            try {
                List<LegouSkuDetailInfo> LegouSkuDetailInfoList = Lists.newArrayList();
                Map<String, Integer> buyNumMap = new HashMap<>();
                for (Sku sku : skuList) {
                    skuIds.add(Long.parseLong(sku.getSkuId()));
                    buyNumMap.put(sku.getSkuId(), sku.getBuyNum());
                }

                //批量获取购买商品详情列表
                LegouSkuDetailInfoList = batchGetSkuInfo(skuIds, true, false);
                //批量获取商品促销活动名称
                Map<Long,String[]> promotionNameNameMap = promotionService.getGoodActivityInfoBySkuids(skuIds,projectId);

                for (LegouSkuDetailInfo legouSkuDetailInfo : LegouSkuDetailInfoList) {
                    try {
                        List<BuyGoodInfoBean> goodInfoList = groupGoodsByPromotion(legouSkuDetailInfo, buyNumMap.get(legouSkuDetailInfo.getSkuId()),promotionNameNameMap);
                        buyGoodInfoBeanList.addAll(goodInfoList);

                    } catch (ServiceException e) {
                        logger.error(e);
                    }
                }

                //获取该订单的优惠券信息和促销信息并组装成可用模型
                com.qding.api.call.app.qding.v1_4_0.struct.legou.order.response.data.ConfirmOrderResponseData confirmOrderResponse
                        = fittingConfirmOrderResponseObjFor30(request.getAppUser().getProjectId(), memberId, skuList,
                        request.getOrderSourceType(), request.getCouponCodes(), request.getOrderPromotionIds(), request.getIsUserChooseCoupon(), request.getShowType() == 1 ? true : false,request.getAddrId());
                ConfirmOrderResponseData data = transfor(ConfirmOrderResponseData.class, confirmOrderResponse);
                if (data.getEntity() == null) {
                    response.setData(data);
                    response.setCode(HttpStatus.OK.getStatusCode());
                    return response;
                }

                if (request.getShowType() == 1) { // 每日鲜 订单展示 （同一供货商无需分组）

                    MrxOrderGroup mrxOrder = new MrxOrderGroup();
                    OrdersGroupBySupplierBean ordersGroupBySupplierBean = new OrdersGroupBySupplierBean();
                    ordersGroupBySupplierBean.setGoodInfoBeanList(buyGoodInfoBeanList);
                    List<OrdersGroupBySupplierBean> supplierBeanList = Lists.newArrayList();
                    supplierBeanList.add(ordersGroupBySupplierBean);
                    mrxOrder.setList(supplierBeanList);
                    com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat addresses = null;

                    Project project = projectReadService.get(projectId);
                    int supportDistribute = project.getIsSupportDistribute();
                    mrxOrder.setIsSupportDistribute(supportDistribute);//是否开启配送上门
                    if (supportDistribute == 1) { //解决为空时,Integer类型默认值0问题
                        mrxOrder.setDeliveryType(Constant.DeliveryType.Logistics.getValue());
                    } else {
                        mrxOrder.setDeliveryType(Constant.DeliveryType.PropertySelf.getValue());
                    }

                    //有限获取每日鲜自提地址
                    List<ProjectConnect> projectConnectList = projectReadService.getProjectConnectByProjectId(Long.parseLong(request.getAppUser().getProjectId()));
                    if (CollectionUtils.isNotEmpty(projectConnectList)) {
                        for (ProjectConnect projectConnect : projectConnectList) {
                            if (projectConnect.getConnectType() == 5) { //获取每日鲜自提地址
                                mrxOrder.setDeliveryType(Constant.DeliveryType.PropertySelf.getValue());
                                addresses = transfor(ProjectConcat.class,projectConnect);
                                data.getEntity().setIsMoreProjectAddr(0);//如果有每日鲜自提地址就不需要再对外展示物业自提地址列表
                                break;
                            }
                        }
                    }

                    //每日鲜自提地址不存在 默认使用获取物业自提地址
                    if (QDStringUtil.isNull(addresses)) {
                        ProjectConcat projectConnect = confirmOrderResponse.getEntity().getProjectConcat();//物业自提地址
                        if (QDStringUtil.isNotNull(projectConnect)) {
                            addresses = projectConnect;
                            mrxOrder.setDeliveryType(Constant.DeliveryType.PropertySelf.getValue());
                        } else  {
                            logger.info("can't get project address ,please check brick data ");
                        }
                    }

                    List<MemberAddress> memberAllAddressList = memberService.getMemberAddressList(memberId, null);
                    if (CollectionUtils.isNotEmpty(memberAllAddressList)) {
                        //获取当前社区下用户配送地址列表 用于获取联系人信息
                        List<MemberAddress> memberAddressList = memberService.getMemberAddressList(memberId, String.valueOf(projectId));
                        if (CollectionUtils.isNotEmpty(memberAddressList)) {
                            if (memberAddressList.size() > 1) {
                                //多个配送地址可显示列表
                                mrxOrder.setIsHaveDeliveryAddress(3);
                            } else {
                                //匹配到一个配送地址
                                mrxOrder.setIsHaveDeliveryAddress(2);
                            }

                            //获取用户的默认快递地址
                            MemberAddressResponse memberAddressResponse = memberAddressService.getBtypeDefault(memberId, 0);
                            checkAndContinue(memberAddressResponse);
                            Addresses memberAddr  = null;
                            MemberAddress memberAddress = memberAddressResponse.getMemberAddress();
                            if (QDStringUtil.isNotNull(memberAddress) && memberAddress.getProjectId().equals(String.valueOf(projectId))) {
                                memberAddr  = transfor(Addresses.class,memberAddress);
                            }

                           if (QDStringUtil.isNull(memberAddr)) {
                               memberAddr  = transfor(Addresses.class, memberAddressList.get(0));
                           }

                            String addressStr = getAddress(memberAddr);
                            memberAddr.setAddressStr(addressStr);
                            data.getEntity().setDeliveryAddress(memberAddr);

                        } else {
                            mrxOrder.setIsHaveDeliveryAddress(1);
                        }
                    } else {
                        mrxOrder.setIsHaveDeliveryAddress(0);
                    }

                    mrxOrder.setProjectConcat(addresses);
                    data.setMrxOrder(mrxOrder);
                    data.setShowType(1);

                    //每日鲜，将配送费计入订单总金额计算中
                    LegouSkuDetailInfo logisticsFeeSku = getLogisticsFee(projectId);
                    if (logisticsFeeSku != null) {
                        data.getEntity().setExpressPrice(logisticsFeeSku.getPrice());
                    }

                } else { //通用商品订单

                    //自提地址和物流地址信息获取
                    if (data.getEntity().getDeliveryAddress() != null) {
                        String addressStr = getAddress(data.getEntity().getDeliveryAddress());
                        data.getEntity().getDeliveryAddress().setAddressStr(addressStr);
                    }
                    if (data.getPropertySelf() != null && data.getPropertySelf().getDeliveryAddress() != null) {
                        String addressStr = getAddress(data.getPropertySelf().getDeliveryAddress());
                        data.getPropertySelf().getDeliveryAddress().setAddressStr(addressStr);
                    }

                    Map<Integer, Addresses> deliveryAddressesMap = new HashMap<>();
                    deliveryAddressesMap.put(Constant.DeliveryType.Logistics.getValue(), QDStringUtil.isNotNull(confirmOrderResponse.getEntity().getDeliveryAddress()) ? confirmOrderResponse.getEntity().getDeliveryAddress() : null);
                    //获取物业自提地址，这里讲物业联系对象临时转化为收货对象
                    if (QDStringUtil.isNotNull(confirmOrderResponse.getEntity().getProjectConcat())) {
                        ProjectConcat projectContat = confirmOrderResponse.getEntity().getProjectConcat();
                        Addresses addresses = transfor(Addresses.class, projectContat);
                        String addressStr = getAddress(addresses);
                        addresses.setAddressStr(addressStr);
                        deliveryAddressesMap.put(Constant.DeliveryType.PropertySelf.getValue(), addresses);
                    }

                    Map<Integer, Map<Long, List<BuyGoodInfoBean>>> goodGroupByDeliveryTypeMap = handleGoodsForGroupMap(buyGoodInfoBeanList);
                    List<OrderGroup> groupList = getOrderGroupForShow(goodGroupByDeliveryTypeMap, deliveryAddressesMap);
                    for (OrderGroup orderGroup : groupList) {
                        if (Constant.DeliveryType.Logistics.getValue() == orderGroup.getDeliveryType()) {//快递
                            OrderGroup logistics_dis = orderGroup;
                            data.setLogisticsDis(logistics_dis);

                        } else if (Constant.DeliveryType.PropertySelf.getValue() == orderGroup.getDeliveryType()) {//物业自提
                            PropertySelfOrderGroup property_self = transfor(PropertySelfOrderGroup.class, orderGroup);
                            data.setPropertySelf(property_self);
                        }
                    }
              /*      GetOrderFreightRequest orderFreightRequest = new GetOrderFreightRequest();
                    orderFreightRequest.setMid(userToken.getMemberId());
                    orderFreightRequest.setReceiveAddress(request.get);
                    List<GetOrderFreightRequest.OrderSkuInfo> skuInfoList = Lists.newArrayList();
                    orderFreightRequest.setSkuInfoList();
                    legouRemoteService.getOrderFreight()
                    data.getEntity().setExpressPrice(logisticsFeeSku.getPrice());*/
                }
                response.setData(data);
                response.setCode(HttpStatus.OK.getStatusCode());

            } catch (Exception e) {
                logger.error(e);
                return handleException(ConfirmOrderResponseData.class, e);
            }
        }
        return response;

    }


    @ExplainAnnotation(explain = "下单（生成订单）")
    @HTTP(alias = "makeOrder", isNeadProject = true, isNeadToken = true, isRequireAuth = true, supportMethod = HttpMethod.POST)
    public Response<MakeOrderResponseData> makeOrder(MakeOrderRequest request, UserToken userToken) {

        try {
            MakeOrderResponseData data = new MakeOrderResponseData();

            Response<MakeOrderResponseData> response = new Response<MakeOrderResponseData>();
            //会员ID
            String memberId = userToken.getMemberId();
            //小区ID
            String projectId = request.getAppUser().getProjectId();
            //发票抬头
            String invoiceTitle = request.getInvoiceTitle();
            //收货地址
            String deliveryAddressId = request.getDeliveryAddressId();
            //小区地址
            String projectAddressId = request.getProjectAddressId();
            //订单来源
            Integer orderSourceType = request.getOrderSourceType();
            //订单详情
            List<Sku> skus = request.getSkus();

            OrderGeneratorDto orderGenerator = new OrderGeneratorDto();

            //每日鲜配送 1:配送上门,2:定点自提
    /*        if (request.getIsMrx() == 1 && request.getMrxDeliveryType()==1) {

                deliveryAddressId = request.getDeliveryAddressId();
                //针对上门配送服务提供配送费用，作为商品进入订单
                LegouSkuDetailInfo logisticsFee = getLogisticsFee(Long.parseLong(projectId));
                if (QDStringUtil.isNotNull(logisticsFee)) {
                    Sku logisticsFeeSku = new Sku();
                    logisticsFeeSku.setSkuId(logisticsFee.getSkuId());
                    logisticsFeeSku.setBuyNum(1);
                    skus.add(logisticsFeeSku);
                }

            }*/
            MemberAddress memberAddress = null;
            //如果用户有传入收货地址
            if (QDStringUtil.isNotEmpty(deliveryAddressId)) {
                MemberAddressResponse  memberAddressResponse = memberAddressService.get(deliveryAddressId);
                checkAndContinue(memberAddressResponse);
                memberAddress = memberAddressResponse.getMemberAddress();
                if (memberAddress == null) {
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "收货地址不存在");
                }

            } else {
                //获取用户默认收货地址
                memberAddress = memberService.getMemberDefaultAddress(memberId);
            }
            //组装物流配送收货地址
            orderGenerator = fittingMemberDeliveryAddr(memberAddress, orderGenerator);

            Project project = projectReadService.get(Long.parseLong(projectId));
            if (project == null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
            }

            //获取自提地址
            ProjectConnect projectAddress = fittingProjectAddr(memberId, projectId, projectAddressId,request.getIsMrx()==1);
            if (QDStringUtil.isNotNull(projectAddress)) {
                orderGenerator.setPaddressId(projectAddress.getId());
                orderGenerator.setPaddress(projectAddress.getAddress());
            }

            orderGenerator.setHkMid(QDStringUtil.isNotEmpty(request.getHkMid()) ? request.getHkMid() : "");
            orderGenerator.setMid(memberId);
            orderGenerator.setProjectId(project.getId());
            orderGenerator.setProjectName(project.getName());
            orderGenerator.setPublicsId(QDStringUtil.isEmpty(request.getPublicsId()) ? null : Long.parseLong(request.getPublicsId()));
            orderGenerator.setSourceType(orderSourceType);
            orderGenerator.setIsPayOnline(request.getIsPayOnline());
            orderGenerator.setIsAnonymity(request.getIsAnonymity());//2.0.0版本新增是否匿名
            orderGenerator.setOrderPromotionIds(CollectionUtils.isNotEmpty(request.getOrderPromotionIds()) ? request.getOrderPromotionIds() : new ArrayList<String>());
            boolean isMrx = false;
            if (request.getIsMrx() == 1) {
                //每日鲜配送 1:配送上门,2:定点自提
                orderGenerator.setReceiveType( request.getMrxDeliveryType()==1 ? 1 : 0);   //RPC接口参数 1:配送上门,0:定点自提
                isMrx =true;
            }

            //组装发票信息
            boolean isOk = fittingInvoice ( orderGenerator ,  request, isMrx );
            if (!isOk) {
                data.setMessage("请填写纳税人识别号!");
                response.setData(data);
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                return  response;
            }

            String[] couponCodes = request.getCouponCodes();
            if (couponCodes != null) {
                orderGenerator.setCouponCodeList(Arrays.asList(couponCodes));
            }
            List<SubOrderDto> subOrders = transforList(SubOrderDto.class, skus);
            orderGenerator.setSubOrderlist(subOrders);
            orderGenerator.setRemarks(QDStringUtil.isNotEmpty(request.getRemarks()) ? request.getRemarks() : "");//订单备注 2.7
            orderGenerator.setSpm(QDStringUtil.isNotEmpty(request.getSpm()) ? request.getSpm() : ""); //2.8新增请求来源
            orderGenerator.setReceiverId(memberAddress.getId());

            CreateGorderRequest createGOrderRequest = new CreateGorderRequest();
            createGOrderRequest.setOrderDto(orderGenerator);
            CreateGorderResponse createGorderResponse = legouRemoteService.createGorder(createGOrderRequest);

            if (createGorderResponse.getReturnInfo().getCode() ==600) {
                data.setMessage(createGorderResponse.getReturnInfo().getMessage());
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            }  else {
                checkAndContinue(createGorderResponse);
                data = transfor(MakeOrderResponseData.class, createGorderResponse);
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(MakeOrderResponseData.class, e);
        }
    }

    private boolean fittingInvoice (OrderGeneratorDto orderGenerator , MakeOrderRequest request,boolean isMrx ){

        String invoiceTitle = request.getInvoiceTitle();
        if (QDStringUtil.isNotEmpty(invoiceTitle)) {
            String content = "";
            Integer invoiceType ;
            if (isMrx) {
                content = "以订单实际内容为准";
                invoiceType=2;//电子发票

            } else {
                content ="明细";
                invoiceType = QDStringUtil.isNotNull(request.getInvoiceType())?Integer.valueOf(request.getInvoiceType()):1;
            }

            if (QDStringUtil.isNotNull(request.getObjType()) && request.getObjType()== 2) { //如果是单位
                if (QDStringUtil.isEmpty(request.getInvoiceTaxId())) {
                    return  false;
                }
            }

            //注意这里的区别 廖望梅RPC接口返回的InvoiceMediaType 为发票类型  ，invoiceType 为发票内容 （只能做兼容处理）
            orderGenerator.setInvoiceType(content);
            orderGenerator.setInvoiceMediaType(invoiceType);
            orderGenerator.setInvoicePeopleType(request.getObjType());
            orderGenerator.setInvoiceTitle(invoiceTitle);
            orderGenerator.setInvoiceReceiverEmail(request.getInvoiceReceiverEmail());
            orderGenerator.setInvoiceTaxId(request.getInvoiceTaxId());
        }
        return  true;

    }


    /**
     * 获取订单详情
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getOrder", isRequireAuth = true, isNeadToken = true)
    @ExplainAnnotation(explain = "获取订单详情")
    public Response<GetOrderResponseData> getOrder(GetOrderRequest request, UserToken userToken) {

        try {
            Response<GetOrderResponseData> response = new Response<GetOrderResponseData>();
            String orderCode = request.getOrderCode();
            GetOrderDetailByCodeResponse getOrderResponse = checkOrderAuth(userToken.getMemberId(), orderCode);
            GetOrderResponseData data = transfor(GetOrderResponseData.class, getOrderResponse);
            if ( QDStringUtil.isNotNull(data.getEntity()) &&  QDStringUtil.isNotNull(data.getEntity().getOrderInvoice())) {
                //注意这里的区别 廖望梅RPC接口返回的InvoiceMediaType 为发票类型  ，invoiceType 为发票内容 （只能做兼容处理）
                OrderInvoice orderInvoice = getOrderResponse.getLegouOrderDetailDto().getOrderInvoice();
                data.getEntity().getOrderInvoice().setInvoiceType(QDStringUtil.isNotNull(orderInvoice.getInvoiceMediaType())?orderInvoice.getInvoiceMediaType()+"":"1");
                data.getEntity().getOrderInvoice().setInvoiceContent(orderInvoice.getInvoiceType());
                data.getEntity().getOrderInvoice().setObjType(orderInvoice.getInvoicePeopleType());
            }
            List<OrderGoods> orderGoodList = getOrderResponse.getLegouOrderDetailDto().getOrderGoodsList();
            List<BuyGoodInfoBean> buyGoodInfoBeanList = Lists.newArrayList();
            ArrayList<Long> sortedSkuIds = new ArrayList<Long>();
            for (OrderGoods orderGoods : orderGoodList) {
                sortedSkuIds.add(orderGoods.getWareSkuId());
            }

            List<LegouSkuDetailInfo> skuInfoList = batchGetSkuInfo(sortedSkuIds, false, false);
            HashMap<String, LegouSkuDetailInfo> skuDetailMap = new HashMap<>();
            if (CollectionUtils.isNotEmpty(skuInfoList)) {
                for (LegouSkuDetailInfo legouSkuDetailInfo : skuInfoList) {
                    if (Constant.NEWSE_PSF_MAKING_CODE.equals(legouSkuDetailInfo.getMarkingCode())) {
                        data.getEntity().setExpressPrice(legouSkuDetailInfo.getPrice());//每日鲜配送费
                        continue;
                    }
                    skuDetailMap.put(legouSkuDetailInfo.getSkuId(), legouSkuDetailInfo);
                }
            }

            //解析商品详情信息
            for (OrderGoods orderGood : orderGoodList) {
                if (Constant.NEWSE_PSF_MAKING_CODE.equals(orderGood.getMarkingCode()) || Constant.MRX_PSF_MAKING_CODE.equals(orderGood.getMarkingCode())) {
                    if(Constant.NEWSE_PSF_MAKING_CODE.equals(orderGood.getMarkingCode()))
                    data.getEntity().setExpressPrice(orderGood.getTotalPrice());//新零售运费
                    continue;
                }
                BuyGoodInfoBean buyGoodInfoBean = transfor(BuyGoodInfoBean.class, orderGood);
                if (skuDetailMap.containsKey(String.valueOf(orderGood.getWareSkuId()))) {
                    LegouSkuDetailInfo skuDetailInfo = skuDetailMap.get(String.valueOf(orderGood.getWareSkuId()));
                    buyGoodInfoBean.setIsSdnrtr(skuDetailInfo.getIsSdnrtr());
                }
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

            //订单支付提醒
            if ((QDStringUtil.isNull(getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPayAt()) ||
                    getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPayAt() <= 0) &&
                    !(201 <= getOrderResponse.getLegouOrderDetailDto().getOrderBase().getOrderStatus().intValue() && 204 >= getOrderResponse.getLegouOrderDetailDto().getOrderBase().getOrderStatus().intValue())) {
                data.getEntity().setPayRemind("请于下单后30分钟内完成支付");
            }

            //获取订单状态
            try {
                Integer legouStatus = getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getLegouStatus();
                String remarks = getOrderResponse.getLegouOrderDetailDto().getOrderBase().getRemarks();
                data.getEntity().setRemarks(remarks);
                data.getEntity().setLegouStatus(legouStatus);
            } catch (Exception e) {
                logger.error("get legouStatus for getOrder  is error ", e);
            }

            Map<Integer, Addresses> deliveryAddresses = new HashMap<>();
            OrderReceiver orderReceiver = null;

            //物流配送地址信息
            if (QDStringUtil.isNotNull(getOrderResponse.getLegouOrderDetailDto().getOrderReceiver())) {
                orderReceiver = getOrderResponse.getLegouOrderDetailDto().getOrderReceiver();
                Addresses addresses = transfor(Addresses.class, orderReceiver);
                deliveryAddresses.put(Constant.DeliveryType.Logistics.getValue(), addresses);
            }

            //物业自提地址信息|每日鲜自提地址
            if (QDStringUtil.isNotNull(getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPaddress())) {
                String paddRess = getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPaddress();
                Addresses addresses = new Addresses();
                addresses.setAddress(paddRess);
                deliveryAddresses.put(Constant.DeliveryType.PropertySelf.getValue(), addresses);
            }

            int orderType = getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getOrderType();
            logger.info("get orderType :" + orderType);
            if (orderType == 1) {   // 判断是否是每日鲜订单
                //提货码
                String takeCode = getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getTakeCode();
                logger.info("get taskCode :" + takeCode);
                data.getEntity().setTakeCode(takeCode);
                OrderGroup mrxOrder = new OrderGroup();
                OrdersGroupBySupplierBean ordersGroupBySupplierBean = new OrdersGroupBySupplierBean();
                ordersGroupBySupplierBean.setGoodInfoBeanList(buyGoodInfoBeanList);
                List<OrdersGroupBySupplierBean> supplierBeanList = Lists.newArrayList();
                supplierBeanList.add(ordersGroupBySupplierBean);
                mrxOrder.setList(supplierBeanList);

                Addresses addresses = null;
                //1:配送上门,0:定点自提
                Integer receiveType = getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getReceiveType();
                if (receiveType == 0) {
                    addresses = deliveryAddresses.get(Constant.DeliveryType.PropertySelf.getValue());
                    mrxOrder.setDeliveryType(Constant.DeliveryType.PropertySelf.getValue());
                    data.getEntity().getOrderBase().setGoodsPrice(data.getEntity().getOrderBase().getTotalPrice());
                } else {
                    addresses = deliveryAddresses.get(Constant.DeliveryType.Logistics.getValue());
                    mrxOrder.setDeliveryType(Constant.DeliveryType.Logistics.getValue());
                    String expressPrice = data.getEntity().getExpressPrice();
                    BigDecimal totalPrice_ = new BigDecimal(data.getEntity().getOrderBase().getTotalPrice());
                    if (QDStringUtil.isNotEmpty(expressPrice) && !"0".equals(expressPrice)) {
                        BigDecimal expressPrice_ = new BigDecimal(expressPrice);
                        BigDecimal goodsPrice_ = totalPrice_.subtract(expressPrice_);
                        data.getEntity().getOrderBase().setGoodsPrice(goodsPrice_.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                    } else {
                        data.getEntity().getOrderBase().setGoodsPrice(totalPrice_.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                    }
                }

                mrxOrder.setDeliveryAddress(addresses);
                data.getEntity().setMrxOrder(mrxOrder);
                data.getEntity().setShowType(1);


            } else {

                //对商品进行分组（先按照收货方式分组，再按照供货商分组）
                Map<Integer, Map<Long, List<BuyGoodInfoBean>>> goodGroupByDeliveryTypeMap = handleGoodsForGroupMap_25(buyGoodInfoBeanList);
                List<OrderGroup> groupList = getOrderGroupForShow(goodGroupByDeliveryTypeMap, deliveryAddresses);
                for (OrderGroup orderGroup : groupList) {
                    if (Constant.DeliveryType.Logistics.getValue() == orderGroup.getDeliveryType()) {
                        OrderGroup logistics_dis = orderGroup;
                        data.getEntity().setLogisticsDis(logistics_dis);
                    } else if (Constant.DeliveryType.PropertySelf.getValue() == orderGroup.getDeliveryType()) {
                        OrderGroup property_self = orderGroup;
                        data.getEntity().setPropertySelf(property_self);
                    }
                }
                data.getEntity().setShowType(2);
            }

            //是否已经拆单
            try {
                if (StringUtils.isNotEmpty(getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getOriginalCode())) {
                    data.getEntity().setIsSplit(1);
                } else {
                    //状态为已支付时返回app 已拆单状态(用于处理app显示问题)
                    if (105 == getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getPayStatus().intValue()) {
                        data.getEntity().setIsSplit(1);
                    } else {
                        data.getEntity().setIsSplit(0);
                    }
                }
            } catch (Exception e) {
                data.getEntity().setIsSplit(0);
            }

            //物业联系地址
            Long paddressId = getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPaddressId();
            Long projectId = getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getProjectId();
            ProjectConnect projectConnect = gettProjectConcat(paddressId, projectId);
            if (QDStringUtil.isNotNull(projectConnect)) {
                data.getEntity().setProjectConcat(transfor(ProjectConcat.class, projectConnect));
            }

            //获取包裹信息物流信息
            List<LogisticsInfo> logisticsInfoList = apiOrderService.getLogisticsInfosForPackage(orderCode, getOrderResponse);
            data.getEntity().setLogisticsInfoList(logisticsInfoList);
            if (QDStringUtil.isNotNull(orderReceiver)) {
                Addresses addresses = transfor(Addresses.class, orderReceiver);
                data.getEntity().setDeliveryAddress(addresses);
            }

            //收货地址详情
            String deliveryAddress = fittingDeliveryAddressForOrder(data.getEntity().getDeliveryAddress());
            data.getEntity().getDeliveryAddress().setAddressStr(deliveryAddress);

            //组装订单中含阶梯团购结构
            //data.getEntity().setOrderGrouponInfoDtos(fittingGrouponForOrder(getOrderResponse.getLegouOrderDetailDto().getGrouponList()));
            data.getEntity().setOrderGrouponInfoDtos(fittingGrouponForOrderNew(getOrderResponse.getLegouOrderDetailDto().getGrouponShowDto()));

            //获取支付类型描述
            if(Constant.PAYMENT_STATUS_101.intValue() == getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getPayStatus().intValue()) {
                data.getEntity().getOrderBase().setPaymentTypeName("未支付");
            } else {
                Constant.PayTypeEnum payTypeEnum = Constant.PayTypeEnum.getByCode( getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPayType());
                if(QDStringUtil.isNotNull(payTypeEnum)){
                    data.getEntity().getOrderBase().setPaymentTypeName(payTypeEnum.getDescription());
                }
            }
            response.setData(data);
            return response;

        } catch (Exception e) {
            logger.error(e);
            return handleException(GetOrderResponseData.class, e);
        }

    }


    /**
     * 批量获取商品信息
     *
     * @param skuIds
     * @return
     * @throws Exception
     */
    public List<LegouSkuDetailInfo> batchGetSkuInfo(List<Long> skuIds, boolean isFindAllStatus, boolean isFindSkuStock) throws Exception {

        LegouSkuRequest legouSkuRequest = new LegouSkuRequest();
        legouSkuRequest.setSortedSkuIds(skuIds);
        legouSkuRequest.setFindAllStatus(isFindAllStatus);
        legouSkuRequest.setFindSkuStock(isFindSkuStock);
        LegouSkuResponse skuResponse = skuService.queryLegouSku(legouSkuRequest);
        checkAndContinue(skuResponse);
        List<LegouSkuDetailInfo> skus = skuResponse.getSkus();

        return skus;
    }



    /**
     * 组装指定商品购物车信息
     *
     * @param limitMap
     */
    private GetCartResponseData fittingCartObj( Map<Long, com.qding.legou.domain.Cart> cartMap, List<LegouSkuDetailInfo> skus, Map<Long, Map<String, Object>> limitMap, Map<Long, List<GoodsPromotion>> goodsPromotionMap, Map<Long,GoodsPromotion> optimizePromotionMap) {

        GetCartResponseData data = new GetCartResponseData();
        //有效
        List<Cart> effectiveList = new ArrayList<>();
        //无效
        List<Cart> invalidList = new ArrayList<>();
        //每日鲜
        List<Cart> mrxList = new ArrayList<>();

        for (LegouSkuDetailInfo sku : skus) {
            Integer limitStrategy = 0;
            Long skuId = Long.parseLong(sku.getSkuId());
            Integer limitCount = -1;
            Integer availableCount = -1;
            String discountWarePrice = "";
            if (QDStringUtil.isNotNull(limitMap) && limitMap.containsKey(skuId)) {
                limitCount = Integer.parseInt(limitMap.get(skuId).get("limitCount").toString()); //限购数量
                availableCount = Integer.parseInt(limitMap.get(skuId).get("availableCount").toString());//可购买数量
            }

            if (QDStringUtil.isNotNull(optimizePromotionMap) && optimizePromotionMap.containsKey(skuId)) {
                GoodsPromotion goodsPromotion  = optimizePromotionMap.get(skuId);//最优活动
                limitStrategy = goodsPromotion.getLimitStrategy();
                discountWarePrice = QDStringUtil.isNotNull(goodsPromotion)&&QDStringUtil.isNotNull(goodsPromotion.getPromotionPrice())?goodsPromotion.getPromotionPrice():"";
            }

            if (availableCount == 0) {
                discountWarePrice = "";
                limitCount = -1;
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
                    QDStringUtil.isNotNull(goodsPromotionMap) &&
                            goodsPromotionMap.containsKey(Long.parseLong(sku.getSkuId())) ? goodsPromotionMap.get(Long.parseLong(sku.getSkuId())) : Lists.<GoodsPromotion>newArrayList(),
                    sku.getSpecName(),
                    sku.getSpecValue(),
                    availableCount,
                    limitCount,
                    limitStrategy
            );
            
            //如果货品是上加状态，且库存足够 或 限购策略为超出不支持原价购买且限购剩余数不为0
            if (sku.getIsDel() != 0 || (limitStrategy == 0 && availableCount.intValue() == 0)) {
                if(sku.getIsDel()!=0){
                	cart.setStatusTag("失效");
                }else{
                	cart.setStatusTag("限购");
                }
            	invalidList.add(cart);
            } else {
                if ((1 == sku.getStatus().intValue() && 1 <= sku.getCountStock().intValue())) {
                    String markingCode = sku.getMarkingCode();
                    if (Constant.MRX_MAKING_CODE.equals(markingCode.trim())) {
                        mrxList.add(cart);
                    } else {
                        effectiveList.add(cart);
                    }
                } else {
                	cart.setStatusTag("售罄");
                    invalidList.add(cart);
                }
            }
        }

        data.setEffectiveList(effectiveList);
        data.setInvalidList(invalidList);
        
        data.setMrxList(mrxList);
        return data;
    }


}
