package com.qding.api.call.app.qding.v2_5_0;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.MakeOrderRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.MakeOrderResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.BuyGoodInfoBean;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrderGroup;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.LogisticsInfo;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.RedeemCode;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.OrderGrouponInfoDto;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.request.GetOrderRequest;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.response.data.GetOrderResponseData;
import com.qding.api.call.service.OrderService;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.HttpMethod;
import com.qding.api.util.QDMemberRemote;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ProjectConnect;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.pojo.ware.WareMarkingConf;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.brick.remote.ware.WareRemoteService;
import com.qding.brick.struts.request.GetProjectConnectRequest;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.legou.domain.OrderGoods;
import com.qding.legou.dto.LegouGoodsThirdCodeDetailDto;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.CreateGorderRequest;
import com.qding.legou.struct.response.CreateGorderResponse;
import com.qding.legou.struct.response.GetOrderDetailByCodeResponse;
import com.qding.member.model.MemberAddress;
import com.qding.member.rpc.IMemberAddressRPC;
import com.qding.member.rpc.IMemberAddressSelfdRPC;
import com.qding.member.rpc.response.memberaddress.MemberAddressResponse;
import com.qding.oder.dto.OrderGeneratorDto;
import com.qding.oder.dto.OrderGrouponDto;
import com.qding.oder.dto.OrderGrouponShowDto;
import com.qding.oder.dto.SubOrderDto;
import com.qding.order.domain.OrderReceiver;
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
 * Created by qd on 2016/8/25.
 */
public class CallLegouOrder extends com.qding.api.call.app.qding.v2_4_0.CallLegouOrder {


    @Autowired
    private IMemberAddressRPC memberAddressService;

    @Autowired
    private IMemberAddressSelfdRPC memberAddressSelfdService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private ILegouRemoteService legouRemoteService;

    @Autowired
    private WareRemoteService wareRemoteService;

    @Autowired
    private RoomReadRemote roomReadRemoteService;

    @Autowired
    private ISolrSkuService solrSku;

    @Autowired
    private OrderService apiOrderService;

    private static final Logger logger = Logger.getLogger("CallLegouOrder");


    @ExplainAnnotation(explain = "下单（生成订单）")
    @HTTP(alias = "makeOrder",isNeadProject = true,isNeadToken = true,isRequireAuth = true,supportMethod = HttpMethod.POST)
    public Response<MakeOrderResponseData> makeOrder(MakeOrderRequest request, UserToken userToken) {

        try {
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
            //周先生配送 0:物业自取，1：物流配送（上门）
            if (QDStringUtil.isNotNull(request.getZxsAddressType()) && request.getZxsAddressType().intValue() == 1) {
                deliveryAddressId = request.getZxsAddressId();
            }
            //每日鲜配送 0:物业自取，1：物流配送（上门）
            if (QDStringUtil.isNotNull(request.getMrxAddressId()) && request.getMrxAddressType().intValue() == 1) {

                deliveryAddressId = request.getMrxAddressId();
                //针对上门配送服务提供配送费用，作为商品进入订单
                LegouSkuDetailInfo logisticsFee = getLogisticsFee(Long.parseLong(projectId));
                Sku logisticsFeeSku = new Sku();
                logisticsFeeSku.setSkuId(logisticsFee.getSkuId());
                logisticsFeeSku.setBuyNum(1);
            }
            MemberAddressResponse memberAddressResponse = memberAddressService.get(deliveryAddressId);
            checkAndContinue(memberAddressResponse);
            MemberAddress memberAddress = memberAddressResponse.getMemberAddress();
            if (memberAddress == null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "收货地址不存在");
            }

            Project project = projectReadService.get(Long.parseLong(projectId));
            if (project == null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
            }

            CreateGorderRequest createGOrderRequest = new CreateGorderRequest();
            OrderGeneratorDto orderGenerator = new OrderGeneratorDto();

            ProjectConnect projectAddress = fittingProjectAddr(memberId, projectId, projectAddressId,QDStringUtil.isNotNull(request.getMrxAddressId()));
            if (QDStringUtil.isNotNull(projectAddress)) {
                orderGenerator.setPaddressId(projectAddress.getId());
                orderGenerator.setPaddress(projectAddress.getAddress());
            }

            orderGenerator.setMid(memberId);
            orderGenerator.setProjectId(project.getId());
            orderGenerator.setProjectName(project.getName());
            orderGenerator.setPublicsId(QDStringUtil.isEmpty(request.getPublicsId()) ? null : Long.parseLong(request.getPublicsId()));
            orderGenerator.setSourceType(orderSourceType);
            orderGenerator.setInvoiceTitle(invoiceTitle);
            fittingMemberDeliveryAddr( memberAddress,  orderGenerator);
            orderGenerator.setIsPayOnline(request.getIsPayOnline());
            orderGenerator.setIsAnonymity(request.getIsAnonymity());//2.0.0版本新增是否匿名
            orderGenerator.setOrderPromotionIds(CollectionUtils.isNotEmpty(request.getOrderPromotionIds()) ? request.getOrderPromotionIds() : new ArrayList<String>());
            if (QDStringUtil.isNotNull(request.getZxsAddressType())) {
                orderGenerator.setReceiveType(request.getZxsAddressType()); //
            }

            String[] couponCodes = request.getCouponCodes();
            if (couponCodes != null) {
                orderGenerator.setCouponCodeList(Arrays.asList(couponCodes));
            }
            List<SubOrderDto> subOrders = transforList(SubOrderDto.class, skus);
            orderGenerator.setSubOrderlist(subOrders);
            orderGenerator.setRemarks(QDStringUtil.isNotEmpty(request.getRemarks()) ? request.getRemarks() : "");//订单备注 2.7
            orderGenerator.setSpm(QDStringUtil.isNotEmpty(request.getSpm()) ? request.getSpm() : ""); //2.8新增请求来源
            orderGenerator.setHkMid(QDStringUtil.isNotEmpty(request.getHkMid()) ? request.getHkMid() : "");
            createGOrderRequest.setOrderDto(orderGenerator);
            CreateGorderResponse createGorderResponse = legouRemoteService.createGorder(createGOrderRequest);
            checkAndContinue(createGorderResponse);
            MakeOrderResponseData data = transfor(MakeOrderResponseData.class, createGorderResponse);
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(MakeOrderResponseData.class, e);
        }
    }

    /**
     *
     * @param memberId
     * @param projectId
     * @param projectAddressId
     * @return
     * @throws ServiceException
     */
    public ProjectConnect fittingProjectAddr(String memberId, String projectId, String projectAddressId,boolean isMrx) throws ServiceException {

        ProjectConnect projectAddress = null;
        if (QDStringUtil.isNotEmpty(projectAddressId)) {
             projectAddress = projectReadService.getProjectConnectBId(Long.parseLong(projectAddressId));
            if (projectAddress == null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目地址不存在");
            }
            //app2.8维护用户最近一次自提点
            BaseResponse baseResponse = memberAddressSelfdService.handleMemberAddressSelf(projectId, memberId, Integer.parseInt(projectAddressId));
            checkAndContinue(baseResponse);
            logger.info("维护用户最近一次自提点 结果= projectId=" + projectId + " memberId=" + memberId + " projectAddressId=" + projectAddressId);
        } else {
            //app2.8默认生辰该订单传递一个自提点，望梅组必须传递该参数
            List<ProjectConnect> projectConnects = isMrx? getProjectConnectList(projectId) : getProjectConnectListWithoutMRX(projectId);
            if (projectConnects != null && !projectConnects.isEmpty()) {
                projectAddress = projectConnects.get(0);
            }
        }
        return projectAddress;
    }


    public   OrderGeneratorDto fittingMemberDeliveryAddr(MemberAddress memberAddress, OrderGeneratorDto orderGenerator) {

        orderGenerator.setReceiverId(memberAddress.getId());
        orderGenerator.setReceiverName(memberAddress.getName());
        orderGenerator.setReceiverPhone(memberAddress.getMobile());
        if (memberAddress.getVersion() != null && 2 == memberAddress.getVersion()) {
            StringBuffer str = new StringBuffer();
            if (StringUtils.isNotEmpty(memberAddress.getProvinceName())) {
                str.append(memberAddress.getProvinceName() + "-");
            }
            if (StringUtils.isNotEmpty(memberAddress.getCityName())) {
                str.append(memberAddress.getCityName() + "-");
            }
            if (StringUtils.isNotEmpty(memberAddress.getAreaName())) {
                str.append(memberAddress.getAreaName() + "-");
            }
            if (StringUtils.isNotEmpty(memberAddress.getRoomAddress())) {
                str.append(memberAddress.getRoomAddress() + "-");
            }
            if (StringUtils.isNotEmpty(memberAddress.getProjectName())) {
                str.append(memberAddress.getProjectName() + "-");
            }
            if (StringUtils.isNotEmpty(memberAddress.getAddress())) {
                str.append(memberAddress.getAddress());
            } else {
                if (StringUtils.isNotEmpty(memberAddress.getRoomId())) {
                    Room room = roomReadRemoteService.get(Long.parseLong(memberAddress.getRoomId()));
                    str.append(QDStringUtil.isNotNull(room.getGroupName()) ? (room.getGroupName() + "-") : "");
                    str.append(QDStringUtil.isNotNull(room.getBuildingName()) ? (room.getBuildingName() + "-") : "");
                    str.append(room.getName());
                }
            }
            orderGenerator.setReceiverAddress(str.toString());
        } else {
            orderGenerator.setReceiverAddress(memberAddress.getAddress()); //用户物流配送收货地址
        }

        return orderGenerator;
    }


    /**
     * 获取订单详情
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getOrder", isRequireAuth = true,isNeadToken = true)
    @ExplainAnnotation(explain = "获取订单详情")
    public Response<GetOrderResponseData> getOrder(GetOrderRequest request,UserToken userToken) {

        try {
            Response<GetOrderResponseData> response = new Response<GetOrderResponseData>();
            String orderCode = request.getOrderCode();
            GetOrderDetailByCodeResponse getOrderResponse = checkOrderAuth(userToken.getMemberId(), orderCode);

            List<OrderGoods> orderGoodList = getOrderResponse.getLegouOrderDetailDto().getOrderGoodsList();
            List<BuyGoodInfoBean> buyGoodInfoBeanList = Lists.newArrayList();

            for (OrderGoods orderGood : orderGoodList) {

                BuyGoodInfoBean buyGoodInfoBean = transfor(BuyGoodInfoBean.class, orderGood);
                LegouSkuRequest skuRequest = new LegouSkuRequest();
                ArrayList<Long> sortedSkuIds = new ArrayList<Long>();
                sortedSkuIds.add(orderGood.getWareSkuId());
                skuRequest.setSortedSkuIds(sortedSkuIds);
                LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
                if (skuResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                    List<LegouSkuDetailInfo> skuInfoList = skuResponse.getSkus();
                    if (CollectionUtils.isNotEmpty(skuInfoList)) {
                        LegouSkuDetailInfo skuDetailInfo = skuInfoList.get(0);
                        buyGoodInfoBean.setIsSdnrtr(skuDetailInfo.getIsSdnrtr());
                    }
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

            Map<Integer, Addresses> deliveryAddresses = new HashMap<>();
            OrderReceiver orderReceiver = null;
            //物流配送分组
            if (QDStringUtil.isNotNull(getOrderResponse.getLegouOrderDetailDto().getOrderReceiver())) {
                orderReceiver = getOrderResponse.getLegouOrderDetailDto().getOrderReceiver();
                Addresses addresses = transfor(Addresses.class, orderReceiver);
                deliveryAddresses.put(Constant.DeliveryType.Logistics.getValue(), addresses);
            }

            //物业自提分组
            if (QDStringUtil.isNotNull(getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPaddress())) {
                String paddRess = getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPaddress();
                Addresses addresses = new Addresses();
                addresses.setAddress(paddRess);
                deliveryAddresses.put(Constant.DeliveryType.PropertySelf.getValue(), addresses);
            }

            Map<Integer, Map<Long, List<BuyGoodInfoBean>>> goodGroupByDeliveryTypeMap = handleGoodsForGroupMap_25(buyGoodInfoBeanList);
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
                String remarks = getOrderResponse.getLegouOrderDetailDto().getOrderBase().getRemarks();
                data.getEntity().setRemarks(remarks);
                data.getEntity().setLegouStatus(legouStatus);
            } catch (Exception e) {
                logger.error("get legouStatus for getOrder  is error ", e);
            }
            //获取包裹信息物流信息
            List<LogisticsInfo> logisticsInfoList = apiOrderService.getLogisticsInfosForPackage(orderCode, getOrderResponse);
            data.getEntity().setLogisticsInfoList(logisticsInfoList);
            if (QDStringUtil.isNotNull(orderReceiver)) {
                Addresses addresses = transfor(Addresses.class, orderReceiver);
                data.getEntity().setDeliveryAddress(addresses);
            }

            //物业联系地址
            Long paddressId = getOrderResponse.getLegouOrderDetailDto().getOrderBase().getPaddressId();
            Long projectId = getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getProjectId();
            ProjectConnect projectConnect = gettProjectConcat(paddressId,projectId);
            if (QDStringUtil.isNotNull(projectConnect)) {
                data.getEntity().setProjectConcat(transfor(ProjectConcat.class, projectConnect));
            }

            //周先生配送最终方式（用于订单底层周先生送货地址的展示判断）
            if (getOrderResponse.getLegouOrderDetailDto().getReceiveType() != null) {//为空时表示不是周先生配送
                if (getOrderResponse.getLegouOrderDetailDto().getReceiveType() == 1) {
                    data.getEntity().setZxsDeliveryType(1);//快递配送（上门）
                } else if (getOrderResponse.getLegouOrderDetailDto().getReceiveType() == 0) {
                    data.getEntity().setZxsDeliveryType(2);//自提
                }
            }
            try {
                if (StringUtils.isNotEmpty(getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getOriginalCode())) {
                    data.getEntity().setIsSplit(1);
                } else {
                    data.getEntity().setIsSplit(0);
                    if (105 == getOrderResponse.getLegouOrderDetailDto().getLegouOrder().getPayStatus().intValue()) {//状态为已支付时返回app 已拆单状态(用于处理app显示问题)
                        data.getEntity().setIsSplit(1);
                    }
                }
            } catch (Exception e) {
                data.getEntity().setIsSplit(0);
            }

            //地址详情
            String deliveryAddress = fittingDeliveryAddressForOrder(data.getEntity().getDeliveryAddress());
            data.getEntity().getDeliveryAddress().setAddressStr(deliveryAddress);
            //组装订单中含阶梯团购结构
            data.getEntity().setOrderGrouponInfoDtos(fittingGrouponForOrder(getOrderResponse.getLegouOrderDetailDto().getGrouponList()));
            response.setData(data);
            return response;

        } catch (Exception e) {
            return handleException(GetOrderResponseData.class, e);
        }
    }


    /**
     * 获取每日鲜配送费相关数据
     * @return
     */
    public LegouSkuDetailInfo getLogisticsFee(Long projectId) {
        LegouSkuDetailInfo logisticsFeeSku = null;
        LegouSkuRequest request = new LegouSkuRequest();
        request.setProjectId(projectId);
        request.setMarkingCode(Constant.MRX_PSF_MAKING_CODE);
        request.setSortType(1);
        LegouSkuResponse querySku = solrSku.queryLegouSku(request);
        if (querySku.getReturnInfo().getCode() == 200 && querySku.getTotal() > 0) {
            logisticsFeeSku = querySku.getSkus().get(0);
        }
        return logisticsFeeSku;
    }


    /**
     * 组装物流配送收货地址
     * @param deliveryAddr
     * @return
     */
    public String fittingDeliveryAddressForOrder ( Addresses deliveryAddr){

        if (deliveryAddr.getVersion() != null && 2 == deliveryAddr.getVersion()) {
            StringBuffer str = new StringBuffer();
            if (StringUtils.isNotEmpty(deliveryAddr.getProvinceName())) {
                str.append(deliveryAddr.getProvinceName() + "-");
            }
            if (StringUtils.isNotEmpty(deliveryAddr.getCityName())) {
                str.append(deliveryAddr.getCityName() + "-");
            }
            if (StringUtils.isNotEmpty(deliveryAddr.getAreaName())) {
                str.append(deliveryAddr.getAreaName() + "-");
            }
            if (StringUtils.isNotEmpty(deliveryAddr.getStreet())) {
                str.append(deliveryAddr.getStreet() + "-");
            }
            if (StringUtils.isNotEmpty(deliveryAddr.getProjectName())) {
                str.append(deliveryAddr.getProjectName() + "-");
            }
            if (StringUtils.isNotEmpty(deliveryAddr.getAddress())) {
                str.append(deliveryAddr.getAddress());
            } else {
                if (StringUtils.isNotEmpty(deliveryAddr.getRoomName())) {
                    str.append(deliveryAddr.getRoomName());
                }
            }
            return str.toString();
        } else {
            return deliveryAddr.getAddress();
        }
    }


    /**
     * 获取物业联系地址 ,有指定的获取指定，没有指定的获取默认第一个
     * @return
     */
    public ProjectConnect gettProjectConcat(Long paddressId,Long projectId ) {

        ProjectConnect projectAddress = null;
        if (paddressId != null) {
            projectAddress = projectReadService.getProjectConnectBId(paddressId);
        } else {
            //获取项目第一条地址(物业自提地址)
            List<ProjectConnect> projectConnects = projectReadService.getProjectConnectByProjectId(projectId);
            if (projectConnects != null && !projectConnects.isEmpty()) {
                projectAddress = projectConnects.get(0);
            }
        }
        return  projectAddress;
    }



    /**
     * 组装订单中含阶梯团购结构
     * @param orderGrouponList
     * @return
     */
    public   List<OrderGrouponInfoDto> fittingGrouponForOrder( List<OrderGrouponDto>  orderGrouponList) {

        List<OrderGrouponInfoDto> orderGrouponInfoDtos  = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(orderGrouponList)){
            orderGrouponInfoDtos  = Lists.transform(orderGrouponList, new Function<OrderGrouponDto, OrderGrouponInfoDto>() {
                @Override
                public OrderGrouponInfoDto apply(OrderGrouponDto input) {
                    OrderGrouponInfoDto orderGrouponInfoDto = new OrderGrouponInfoDto();
                    orderGrouponInfoDto.setCouponAmount(input.getCouponAmount());
                    orderGrouponInfoDto.setDiscount(input.getDiscount());
                    orderGrouponInfoDto.setGrouponStatusName(input.getGrouponStatusName());
                    orderGrouponInfoDto.setGrouponStatus(input.getGrouponStatus());
                    orderGrouponInfoDto.setIntegralAmount(input.getIntegralAmount());
                    orderGrouponInfoDto.setTotalDiscount(input.getTotalDiscount());
                    orderGrouponInfoDto.setTotalRealpay(input.getTotalRealpay());
                    return orderGrouponInfoDto;
                }
            });
        }
        return orderGrouponInfoDtos;
    }
    
    
    public   List<OrderGrouponInfoDto> fittingGrouponForOrderNew(OrderGrouponShowDto input) {
    	List<OrderGrouponInfoDto> orderGrouponInfoDtos  = Lists.newArrayList();
    	if(input!=null){
    		OrderGrouponInfoDto orderGrouponInfoDto = new OrderGrouponInfoDto();
        	orderGrouponInfoDto.setCouponAmount(input.getCouponAmount());
        	orderGrouponInfoDto.setDiscount(input.getDiscount());
        	orderGrouponInfoDto.setGrouponStatusName(input.getGrouponStatusName());
        	orderGrouponInfoDto.setGrouponStatus(input.getGrouponStatus());
        	if(StringUtils.isNotBlank(input.getIntegralAmount())){
        		String integralAmount=input.getIntegralAmount();
        		BigDecimal d=new BigDecimal(integralAmount);
        		BigDecimal result=d.multiply(new BigDecimal(100));
        		orderGrouponInfoDto.setIntegralAmount(result.intValue()+"");	
        	}
        	orderGrouponInfoDto.setTotalDiscount(input.getPredepositAmount());
//        	orderGrouponInfoDto.setTotalRealpay(input.getOrderTotalPrice());
        	orderGrouponInfoDto.setTotalRealpay(input.getGrouponPrice());
        	orderGrouponInfoDtos.add(orderGrouponInfoDto);
    	}
    	return orderGrouponInfoDtos;
    }

    /**
     * 对商品进行分组（先按照收货方式分组，再按照供货商分组）
     *
     * @param
     * @param skuList
     * @return
     */
    public Map<Integer, Map<Long, List<BuyGoodInfoBean>>> handleGoodsForGroupMap_25(List<BuyGoodInfoBean> skuList) {

        Map<Integer, Map<Long, List<BuyGoodInfoBean>>> goodGroupByDeliveryTypeMap = new HashMap();
        Map<Long, List<BuyGoodInfoBean>> goodGroupByProviderIdMap = null;

        for (BuyGoodInfoBean buyGoodInfoBean : skuList) {

            if (buyGoodInfoBean != null) {
                Integer groupTo = buyGoodInfoBean.getGroupTo();
                if (goodGroupByDeliveryTypeMap.containsKey(groupTo)) {
                    goodGroupByProviderIdMap = goodGroupByDeliveryTypeMap.get(groupTo);
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
                goodGroupByDeliveryTypeMap.put(groupTo, goodGroupByProviderIdMap);

            }
        }
        return goodGroupByDeliveryTypeMap;
    }



    /**
     * 获取指定社区下的自提地址列表
     * @param projectId
     * @return
     */
    public List<ProjectConnect> getProjectConnectList (String projectId){
        List<ProjectConnect> projectConnects = projectReadService.getProjectConnectByProjectId(Long.parseLong(projectId));
        return  projectConnects;
    }

    /**
     * 获取指定社区下除每日鲜地址外的自提地址列表
     * @param projectId
     * @return
     */
    public List<ProjectConnect> getProjectConnectListWithoutMRX(String projectId){
        GetProjectConnectRequest projectConnectRequest = new GetProjectConnectRequest();
        projectConnectRequest.setProjectId(Long.parseLong(projectId));
        projectConnectRequest.setExcludeConnectTypeSet(Sets.newHashSet(5));
        List<ProjectConnect> projectConnects = projectReadService.getProjectConnectByRequest (projectConnectRequest);
        return  projectConnects;
    }

}
