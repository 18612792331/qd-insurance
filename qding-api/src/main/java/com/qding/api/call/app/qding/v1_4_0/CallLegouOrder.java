package com.qding.api.call.app.qding.v1_4_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.api.call.app.qding.v1_4_0.struct.coupon.Coupon;
import com.qding.api.call.app.qding.v1_4_0.struct.legou.order.request.*;
import com.qding.api.call.app.qding.v1_4_0.struct.legou.order.response.data.*;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.QDMemberRemote;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ProjectConnect;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberAddress;
import com.qding.member.rpc.IMemberAddressRPC;
import com.qding.member.rpc.IMemberAddressSelfdRPC;
import com.qding.member.rpc.request.member.MemberAddressCondition;
import com.qding.member.rpc.response.memberaddress.MemberAddressListResponse;
import com.qding.member.rpc.response.memberaddress.MemberAddressResponse;
import com.qding.member.rpc.response.selfaddress.SelfdAddressResponse;
import com.qding.oder.dto.OrderGeneratorDto;
import com.qding.oder.dto.SubOrderDto;
import com.qding.order.domain.OrderPromotion;
import com.qding.order.domain.OrderSub;
import com.qding.promotion.common.dto.PromotionCouponUserDto;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.CountBeforOrderRequest;
import com.qding.promotion.common.struct.request.GetOrderAvailableCouponListRequest;
import com.qding.promotion.common.struct.response.CountBeforOrderResponse;
import com.qding.promotion.common.struct.response.CountForOrderBestResponse;
import com.qding.promotion.common.struct.response.CouponListResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.qding.member.model.MemberAddressSelfd;

import java.util.*;

/**
 * 乐购订单
 *
 * @author JIAWENZHENG
 */
public class CallLegouOrder extends com.qding.api.call.app.qding.v1_3_0.CallLegouOrder {

    @Autowired
    private IMemberAddressRPC memberAddressService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IPromotionRemoteService promotionRemoteService;
    
    @Autowired
    private IMemberAddressSelfdRPC memberAddressSelfdService;


    @HTTP(alias = "confirmOrder")
    @ExplainAnnotation(explain = "确定订单")
    @Deprecated
    public Response<ConfirmOrderResponseData> confirmOrder(ConfirmOrderRequest request) {

        try {
            String version = request.getAppDevice().getQdVersion();
            ConfirmOrderResponseData data = fittingConfirmOrderResponseObj(request.getProjectId(), request.getMemberId(), request.getSkus(),
                    request.getOrderSourceType(), request.getCouponCodes(), request.getOrderPromotionIds(), version);

            Response<ConfirmOrderResponseData> response = new Response<>();
            response.setData(data);
            return response;

        } catch (Exception e) {
            return handleException(ConfirmOrderResponseData.class, e);
        }
    }


    //组装确认订单的响应对象
    public ConfirmOrderResponseData fittingConfirmOrderResponseObj(String projectId, String memberId, List<Sku> skus, Integer orderSourceType, String[] couponCodes, List<String> orderPromotionIds, String version) throws Exception {

        Project project = projectReadService.get(Long.parseLong(projectId));
        if (project == null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
        }

        OrderGeneratorDto o = new OrderGeneratorDto();
        o.setMid(memberId);
        o.setProjectId(project.getId());
        o.setProjectName(project.getName());
        o.setSourceType(orderSourceType);
        o.setProductNo(Constant.PRODUCT_NO_NG);
        o.setOrderPromotionIds(CollectionUtils.isNotEmpty(orderPromotionIds) ? orderPromotionIds : new ArrayList<String>());
        if (couponCodes != null) {
            o.setCouponCodeList(Arrays.asList(couponCodes));
        }
        List<SubOrderDto> subOrders = transforList(SubOrderDto.class, skus);
        o.setSubOrderlist(subOrders);

        //获取用户订单金额订单优惠信息
        CountBeforOrderRequest countBeforeOrderRequest = new CountBeforOrderRequest();
        countBeforeOrderRequest.setOrderGeneratorDto(o);
        CountBeforOrderResponse countBeforOrderResponse = promotionRemoteService.countBeforOrder(countBeforeOrderRequest);
        checkAndContinue(countBeforOrderResponse);
        ConfirmOrderResponseData data = transfor(ConfirmOrderResponseData.class, countBeforOrderResponse);

        GetOrderAvailableCouponListRequest orderCouponsRequest = new GetOrderAvailableCouponListRequest();
        orderCouponsRequest.setMid(memberId);
        orderCouponsRequest.setPromotionIds(orderPromotionIds);
        orderCouponsRequest.setProjectId(project.getId());
        //新乐购优惠券
        orderCouponsRequest.setProductNo(Constant.PRODUCT_NO_NG);
        List<OrderPromotion> orderPromotionList = countBeforOrderResponse.getOrderDetailDto().getPromotionList();
        List<String> orderPromotionIdList = new ArrayList<String>();
        for (int i = 0; i < orderPromotionList.size(); i++) {
            OrderPromotion orderPromotion = orderPromotionList.get(i);
            if (3 == orderPromotion.getType()) continue;
            orderPromotionIdList.add(orderPromotion.getPromotionId());
        }
        orderCouponsRequest.setPromotionIds(orderPromotionIdList);
        orderCouponsRequest.setOrderRealPrice(countBeforOrderResponse.getOrderDetailDto().getOrderBase().getTotalRealpay());
        List<OrderSub> orderSubList = countBeforOrderResponse.getOrderDetailDto().getSubOrderList();
        orderCouponsRequest.setSubOrderlist(transforList(SubOrderDto.class,orderSubList));
        orderCouponsRequest.setOrderPrice(countBeforOrderResponse.getOrderDetailDto().getOrderBase().getTotalPrice());
        CouponListResponse orderCouponsResponse = promotionRemoteService.getOrderAvailableCouponListV2(orderCouponsRequest);
        checkAndContinue(orderCouponsResponse);
        transfor(data, orderCouponsResponse);

        //计算所有可用优惠券的总价值
        Integer totalCouponsPrice = 0;
        Iterator<PromotionCouponUserDto> commonIterator = orderCouponsResponse.getCouponList().iterator();
        List<Coupon> couponList = Lists.newArrayList();
        while (commonIterator.hasNext()) {
            PromotionCouponUserDto next = commonIterator.next();
            totalCouponsPrice += next.getPrice();
            Coupon coupon = transfor(Coupon.class, next);
            if (QDStringUtil.isNotEmpty(next.getNote())) {
                coupon.setProductNoNames(new String[]{next.getNote()});
            }
            couponList.add(coupon);
        }

        List<Coupon> unavailableCoupons = Lists.newArrayList();
        //不可用优惠券(2.5版本新增)
        List<PromotionCouponUserDto> unavailableCouponList = orderCouponsResponse.getUnavailableCouponList();
        if (CollectionUtils.isNotEmpty(unavailableCouponList)) {
            unavailableCoupons = transforList(Coupon.class, unavailableCouponList);
        }
        for (Coupon coupon : unavailableCoupons) {
            String[] array = {coupon.getBatchName()};
            coupon.setProductNoNames(array);
        }

        data.getEntity().setCommonCoupons(couponList);
        data.getEntity().setUnavailableCommonCoupons(unavailableCoupons);
        data.getEntity().setTotalCouponsPrice(String.valueOf(totalCouponsPrice));
        //获取项目第一条地址
		List<ProjectConnect> projectConnects = projectReadService.getProjectConnectByProjectId(project.getId());
        if (projectConnects != null && !projectConnects.isEmpty()) {
            data.getEntity().setProjectConcat(transfor(ProjectConcat.class, projectConnects.get(0)));
        }
        //获取用户的默认快递地址
        MemberAddressResponse memberAddressResponse = memberAddressService.getBtypeDefault(memberId, 0);
        checkAndContinue(memberAddressResponse);
        MemberAddress memberAddress = memberAddressResponse.getMemberAddress();
        //新老版本都没有设置过默认地址时 仍需返回一个地址
        if (memberAddress == null) {
            MemberAddressCondition memberAddressCondition = new MemberAddressCondition();
            memberAddressCondition.setMemberId(memberId);
            memberAddressCondition.setBusinessType(0);
            if (version.compareTo("2.5.0") < 0) {
                memberAddressCondition.setVersion(1);//1:老版本<2.5.0时传1,否则不用处理
            }
            MemberAddressListResponse  memberAddressListResponse = memberAddressService.findMemberAddressByCondition(memberAddressCondition);
            checkAndContinue(memberAddressListResponse);
            if(CollectionUtils.isNotEmpty(memberAddressListResponse.getMemberAddressList())){
                memberAddress = memberAddressListResponse.getMemberAddressList().get(0);
            }
        }
        //2.5只返回v2 其他版本只返回v1 否则return null
        if (version.compareTo("2.5.0") >= 0) {
            //if (memberAddress.getVersion() != null && memberAddress.getVersion().intValue() == 2) {
            data.getEntity().setDeliveryAddress(transfor(Addresses.class, memberAddress));
            if (data.getEntity().getDeliveryAddress() != null
                    && (data.getEntity().getDeliveryAddress().getVersion() == null || data.getEntity().getDeliveryAddress().getVersion() == 1)
                    && data.getEntity().getDeliveryAddress().getDefaultFlag().intValue() == 1) {
                data.getEntity().getDeliveryAddress().setDefaultFlag(0);
            }
            //}
        } else {
            if (QDStringUtil.isNotNull(memberAddress) && (memberAddress.getVersion() == null || memberAddress.getVersion().intValue() == 1)) {
                data.getEntity().setDeliveryAddress(transfor(Addresses.class, memberAddress));
            }
        }

        return data;
    }
    
    
    /**
     * version 2.8.0 专用
     * @param projectId
     * @param memberId
     * @param skus
     * @param orderSourceType
     * @param couponCodes
     * @param orderPromotionIds
     * @param version
     * @return
     * @throws Exception
     */
    public ConfirmOrderResponseData fittingConfirmOrderResponseObjFor28(String projectId, String memberId, List<Sku> skus, Integer orderSourceType, String[] couponCodes, List<String> orderPromotionIds, String version) throws Exception {

        Project project = projectReadService.get(Long.parseLong(projectId));
        if (project == null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
        }

        OrderGeneratorDto o = new OrderGeneratorDto();
        o.setMid(memberId);
        o.setProjectId(project.getId());
        o.setProjectName(project.getName());
        o.setSourceType(orderSourceType);
        o.setProductNo(Constant.PRODUCT_NO_NG);
        o.setOrderPromotionIds(CollectionUtils.isNotEmpty(orderPromotionIds) ? orderPromotionIds : new ArrayList<String>());
        if (couponCodes != null) {
            o.setCouponCodeList(Arrays.asList(couponCodes));
        }
        List<SubOrderDto> subOrders = transforList(SubOrderDto.class, skus);
        o.setSubOrderlist(subOrders);

        //获取用户订单金额订单优惠信息
        CountBeforOrderRequest countBeforeOrderRequest = new CountBeforOrderRequest();
        countBeforeOrderRequest.setOrderGeneratorDto(o);
        CountBeforOrderResponse countBeforOrderResponse = promotionRemoteService.countBeforOrder(countBeforeOrderRequest);
        checkAndContinue(countBeforOrderResponse);
        ConfirmOrderResponseData data = transfor(ConfirmOrderResponseData.class, countBeforOrderResponse);

        GetOrderAvailableCouponListRequest orderCouponsRequest = new GetOrderAvailableCouponListRequest();
        orderCouponsRequest.setMid(memberId);
        orderCouponsRequest.setPromotionIds(orderPromotionIds);
        orderCouponsRequest.setProjectId(project.getId());
        //新乐购优惠券
        orderCouponsRequest.setProductNo(Constant.PRODUCT_NO_NG);
        List<OrderPromotion> orderPromotionList = countBeforOrderResponse.getOrderDetailDto().getPromotionList();
        List<String> orderPromotionIdList = new ArrayList<String>();
        for (int i = 0; i < orderPromotionList.size(); i++) {
            OrderPromotion orderPromotion = orderPromotionList.get(i);
            if (3 == orderPromotion.getType()) continue;
            orderPromotionIdList.add(orderPromotion.getPromotionId());
        }
        orderCouponsRequest.setPromotionIds(orderPromotionIdList);
        orderCouponsRequest.setOrderRealPrice(countBeforOrderResponse.getOrderDetailDto().getOrderBase().getTotalRealpay());
        List<OrderSub> orderSubList = countBeforOrderResponse.getOrderDetailDto().getSubOrderList();
        orderCouponsRequest.setSubOrderlist(transforList(SubOrderDto.class,orderSubList));
        orderCouponsRequest.setOrderPrice(countBeforOrderResponse.getOrderDetailDto().getOrderBase().getTotalPrice());
        CouponListResponse orderCouponsResponse = promotionRemoteService.getOrderAvailableCouponListV2(orderCouponsRequest);
        checkAndContinue(orderCouponsResponse);
        transfor(data, orderCouponsResponse);

        //计算所有可用优惠券的总价值
        Integer totalCouponsPrice = 0;
        Iterator<PromotionCouponUserDto> commonIterator = orderCouponsResponse.getCouponList().iterator();
        List<Coupon> couponList = Lists.newArrayList();
        while (commonIterator.hasNext()) {
            PromotionCouponUserDto next = commonIterator.next();
            totalCouponsPrice += next.getPrice();
            Coupon coupon = transfor(Coupon.class, next);
            if (QDStringUtil.isNotEmpty(next.getNote())) {
                coupon.setProductNoNames(new String[]{next.getNote()});
            }
            couponList.add(coupon);
        }

        List<Coupon> unavailableCoupons = Lists.newArrayList();
        //不可用优惠券(2.5版本新增)
        List<PromotionCouponUserDto> unavailableCouponList = orderCouponsResponse.getUnavailableCouponList();
        if (CollectionUtils.isNotEmpty(unavailableCouponList)) {
            unavailableCoupons = transforList(Coupon.class, unavailableCouponList);
        }
        for (Coupon coupon : unavailableCoupons) {
            String[] array = {coupon.getBatchName()};
            coupon.setProductNoNames(array);
        }

        data.getEntity().setCommonCoupons(couponList);
        data.getEntity().setUnavailableCommonCoupons(unavailableCoupons);
        data.getEntity().setTotalCouponsPrice(String.valueOf(totalCouponsPrice));
        //2016/11/17 2.8版本客户服务中心多地址，选择后需要为会员提供一个会员ID,社区ID，自提地址ID的关系
        SelfdAddressResponse selfdAddressResponse =memberAddressSelfdService.listMemberAddressSelf(projectId, memberId);
        checkAndContinue(selfdAddressResponse);
        MemberAddressSelfd selfdAddress= selfdAddressResponse.getSelfd();
        if(selfdAddress!=null){
        	//设置最近一次购物自提地址作为默认地址
        	ProjectConnect con=projectReadService.getProjectConnectBId(selfdAddress.getProjectConnectId().longValue());
        	data.getEntity().setProjectConcat(transfor(ProjectConcat.class, con));
        }else{
        	//当自提地址只有一个是才会默认选中，否则被选中
    		List<ProjectConnect> projectConnects = projectReadService.getProjectConnectByProjectId(project.getId());
            if (projectConnects != null && !projectConnects.isEmpty() && projectConnects.size()==1) {
                data.getEntity().setProjectConcat(transfor(ProjectConcat.class, projectConnects.get(0)));
            }
        }
        //获取用户的默认快递地址
        MemberAddressResponse memberAddressResponse  = memberAddressService.getBtypeDefault(memberId, 0);
        checkAndContinue(memberAddressResponse);
        MemberAddress memberAddress = memberAddressResponse.getMemberAddress();
        //新老版本都没有设置过默认地址时 仍需返回一个地址
        if (memberAddress == null) {
            MemberAddressCondition memberAddressCondition = new MemberAddressCondition();
            memberAddressCondition.setMemberId(memberId);
            memberAddressCondition.setBusinessType(0);
            MemberAddressListResponse  memberAddressListResponse = memberAddressService.findMemberAddressByCondition(memberAddressCondition);
            checkAndContinue(memberAddressListResponse);
            if(CollectionUtils.isNotEmpty(memberAddressListResponse.getMemberAddressList())){
                memberAddress = memberAddressListResponse.getMemberAddressList().get(0);
            }
        }

        data.getEntity().setDeliveryAddress(transfor(Addresses.class, memberAddress));
        if (data.getEntity().getDeliveryAddress() != null
                && (data.getEntity().getDeliveryAddress().getVersion() == null || data.getEntity().getDeliveryAddress().getVersion() == 1)
                && data.getEntity().getDeliveryAddress().getDefaultFlag().intValue() == 1) {
            data.getEntity().getDeliveryAddress().setDefaultFlag(0);
        }

        return data;
    }






}
