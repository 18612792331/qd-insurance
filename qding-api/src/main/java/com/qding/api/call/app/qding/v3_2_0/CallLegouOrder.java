package com.qding.api.call.app.qding.v3_2_0;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qding.api.call.service.MemberService;
import com.qding.api.call.service.OrderService;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.GetOrderFreightRequest;
import com.qding.legou.struct.response.GetOrderFreightResponse;
import com.qding.member.rpc.IMemberAddressRPC;
import com.qding.member.rpc.response.memberaddress.MemberAddressResponse;
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
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrdersGroupBySupplierBean;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.PropertySelfOrderGroup;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.MrxOrderGroup;
import com.qding.api.call.app.qding.v3_2_0.struct.legou.order.request.ConfirmOrderRequest;
import com.qding.api.call.app.qding.v3_2_0.struct.legou.order.response.data.ConfirmOrderResponseData;
import com.qding.api.call.service.PromotionService;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ProjectConnect;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberAddress;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;

/**
 * Created by qd on 2017/2/23.
 */
@ExplainAnnotation(explain = "乐购")
public class CallLegouOrder extends com.qding.api.call.app.qding.v3_1_1.CallLegouOrder {


    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private PromotionService promotionService;
    
    @Autowired
    private IMemberAddressRPC memberAddressService;

    @Autowired
    private ILegouRemoteService legouRemoteService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberService memberService;

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallLegouOrder.class);
    
    @HTTP(alias = "confirmOrder", isNeadToken = true, isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "确定订单",desc="优化了优惠卷对象体，去掉已废弃属性")
    public Response<ConfirmOrderResponseData> confirmOrder(ConfirmOrderRequest request, UserToken userToken) {

        Response<ConfirmOrderResponseData> response = new Response<ConfirmOrderResponseData>();
        List<Sku> skuList = request.getSkus();
        List<BuyGoodInfoBean> buyGoodInfoBeanList = Lists.newArrayList();
        ArrayList<Long> skuIds = new ArrayList<>();
        Long projectId = Long.parseLong(request.getAppUser().getProjectId());
        String memberId = userToken.getMemberId();
        Project project = null;
        if (CollectionUtils.isNotEmpty(skuList)) {
            try {
                project = projectReadService.get(projectId);
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
                com.qding.api.call.app.qding.v1_4_0.struct.legou.order.
                response.data.ConfirmOrderResponseData confirmOrderResponse
                        = fittingConfirmOrderResponseObjFor30(request.getAppUser().getProjectId(), memberId, skuList,
                        request.getOrderSourceType(), request.getCouponCodes(), request.getOrderPromotionIds(), 
                        request.getIsUserChooseCoupon(), request.getShowType() == 1 ? true : false,request.getAddrId());

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

                    GetOrderFreightRequest orderFreightRequest = new GetOrderFreightRequest();
                    List<GetOrderFreightRequest.OrderSkuInfo> orderSkuInfoList = Lists.newArrayList();

                    for (Sku sku : skuList) {
                        GetOrderFreightRequest.OrderSkuInfo orderSkuInfo = orderFreightRequest.new OrderSkuInfo();
                        orderSkuInfo.setNums(sku.getBuyNum());
                        orderSkuInfo.setSkuId(Long.parseLong(sku.getSkuId()));
                        orderSkuInfoList.add(orderSkuInfo);
                    }
                    String orderFreight =orderService.getOrderFreight(orderSkuInfoList,data.getEntity().getShouldPay());
                    BigDecimal shoudPayDecimal = new BigDecimal(data.getEntity().getShouldPay());
                    if(!("0.00").equals(orderFreight)) {
                        BigDecimal orderFreightDecimal = new BigDecimal(orderFreight);
                        shoudPayDecimal = shoudPayDecimal.add(orderFreightDecimal);
                    }

                    //应付价
                    data.getEntity().setShouldPay( shoudPayDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
                    //运费
                    data.getEntity().setExpressPrice(orderFreight);
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


    /**
     * 确认订单时获取运费
     * @param cityId
     * @param areaId
     * @param addr
     * @param ordersGroupBySupplierBeans
     * @return
     */
    private String getOrderFreight(Long cityId,Long areaId,String addr, List<OrdersGroupBySupplierBean> ordersGroupBySupplierBeans){

        String orderFreight = "0.00";
        try {
            GetOrderFreightRequest orderFreightRequest = new GetOrderFreightRequest();
            List<GetOrderFreightRequest.OrderSkuInfo> skuInfoList = Lists.newArrayList();
            for (OrdersGroupBySupplierBean ordersGroupBySupplierBean : ordersGroupBySupplierBeans) {
                List<BuyGoodInfoBean> buyGoodInfoBeans = ordersGroupBySupplierBean.getGoodInfoBeanList();
                for (BuyGoodInfoBean buyGoodInfoBean : buyGoodInfoBeans) {
                    GetOrderFreightRequest.OrderSkuInfo orderSkuInfo = orderFreightRequest.new OrderSkuInfo();
                    orderSkuInfo.setNums(buyGoodInfoBean.getBuyNum());
                    orderSkuInfo.setSkuId( Long.parseLong(buyGoodInfoBean.getSkuId()));
                    skuInfoList.add(orderSkuInfo);
                }
            }
            orderFreightRequest.setSkuInfoList(skuInfoList);
            orderFreightRequest.setReceiveAddress(addr);
            orderFreightRequest.setCityId(cityId);
            orderFreightRequest.setAreaId(areaId);
            GetOrderFreightResponse orderFreightResponse = legouRemoteService.getOrderFreight(orderFreightRequest);
            checkAndContinue(orderFreightResponse);
            orderFreight = orderFreightResponse.getTotalFreight();
        } catch (Exception ex) {
              logger.error("确认订单接口获取取费失败:",ex);
        }
        return orderFreight;
    }

    
     

}
