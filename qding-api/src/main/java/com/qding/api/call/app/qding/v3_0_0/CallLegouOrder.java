package com.qding.api.call.app.qding.v3_0_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v1_4_0.struct.coupon.Coupon;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.BuyGoodInfoBean;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrderGroup;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.response.data.ConfirmOrderResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.PropertySelfOrderGroup;
import com.qding.api.call.app.qding.v3_0_0.struct.order.legou.request.ConfirmOrderRequest;
import com.qding.api.call.service.MemberService;
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
import com.qding.member.model.MemberAddressSelfd;
import com.qding.member.rpc.IMemberAddressRPC;
import com.qding.member.rpc.IMemberAddressSelfdRPC;
import com.qding.member.rpc.request.member.MemberAddressCondition;
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
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * Created by qd on 2017/3/27.
 */
public class CallLegouOrder extends com.qding.api.call.app.qding.v2_8_0.CallLegouOrder {

    @Autowired
    private ISolrSkuService skuService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IMemberAddressRPC memberAddressService;

    @Autowired
    private IPromotionRemoteService promotionRemoteService;

    @Autowired
    private IMemberAddressSelfdRPC memberAddressSelfdService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private MemberService memberService;


    @HTTP(alias = "confirmOrder", isNeadToken = true, isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "确定订单")
    public Response<ConfirmOrderResponseData> confirmOrder(ConfirmOrderRequest request, UserToken userToken) {

        Integer containZXS = 0;
        Response<ConfirmOrderResponseData> response = new Response<ConfirmOrderResponseData>();
        List<Sku> skuList = request.getSkus();
        List<BuyGoodInfoBean> buyGoodInfoBeanList = Lists.newArrayList();
        ArrayList<Long> skuIds = new ArrayList<>();
        Long projectId = Long.parseLong(request.getAppUser().getProjectId());

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

            List<Long> wareSkuIds = Lists.newArrayList();
            for (LegouSkuDetailInfo legouSkuDetailInfo : LegouSkuDetailInfoList) {
                wareSkuIds.add(Long.parseLong(legouSkuDetailInfo.getSkuId()));
            }

            //批量获取商品促销名称
            Map<Long,String[]> promotionNameNameMap = promotionService.getGoodActivityInfoBySkuids(wareSkuIds,projectId);
            Map<Long, Map<String, Object>> limitMap = null;

            if (QDStringUtil.isNotNull(projectId) && QDStringUtil.isNotEmpty( userToken.getMemberId())) {
                //批量获取商品限购信息
                try {
                    limitMap = promotionService.batchGetSkuLimitListByWareIds( userToken.getMemberId(), wareSkuIds,projectId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //批量获取商品最优促销信息
            Map<Long,GoodsPromotion> optimizePromotionMap = promotionService.batchGetGoodsOptimizePromotion (wareSkuIds,projectId);
            for (LegouSkuDetailInfo legouSkuDetailInfo : LegouSkuDetailInfoList) {
                BuyGoodInfoBean buyGoodInfoBean = transfor(BuyGoodInfoBean.class, legouSkuDetailInfo);
                try {
                    List<BuyGoodInfoBean> goodInfoList = groupGoodsByPromotion(legouSkuDetailInfo, buyNumMap.get(buyGoodInfoBean.getSkuId()), projectId, userToken.getMemberId(), promotionNameNameMap ,limitMap,  optimizePromotionMap);
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
                    = fittingConfirmOrderResponseObjFor30(request.getAppUser().getProjectId(), userToken.getMemberId(), skuList,
                    request.getOrderSourceType(), request.getCouponCodes(), request.getOrderPromotionIds(), request.getIsUserChooseCoupon(),false,request.getAddrId());
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
     *
     * @param legouSkuDetailInfo
     * @param buyCount
     * @param projectId
     * @param memberId
     * @return
     * @throws ServiceException
     */
    public List<BuyGoodInfoBean> groupGoodsByPromotion(LegouSkuDetailInfo legouSkuDetailInfo, Integer buyCount, Long projectId, String memberId,  Map<Long,String[]> promotionNameNameMap , Map<Long, Map<String, Object>> limitMap, Map<Long,GoodsPromotion> optimizePromotionMap ) throws ServiceException {

        List<BuyGoodInfoBean> list = Lists.newArrayList();
        BuyGoodInfoBean buyGoodInfoBean = transfor(BuyGoodInfoBean.class, legouSkuDetailInfo);
        buyGoodInfoBean.setBuyNum(buyCount);
        Long skuId = Long.parseLong(legouSkuDetailInfo.getSkuId());
        if (QDStringUtil.isNotNull(promotionNameNameMap) && promotionNameNameMap.containsKey(skuId)) {
            buyGoodInfoBean.setActivityInfo(promotionNameNameMap.get(skuId));
        }

        //获取限购情况 (只限于3.0版本有针对限购情况下同个商品的分组)
        if (QDStringUtil.isNotNull(projectId) && QDStringUtil.isNotEmpty(memberId) ) {

            Integer limitCount = -1;
            Integer availableCount = -1;
            Integer diffCount = 0;//同一个商品实际购买数比促销可购买数多出的差值
            if (QDStringUtil.isNotNull(limitMap) && limitMap.containsKey(skuId)) {
                limitCount = Integer.parseInt(limitMap.get(skuId).get("limitCount").toString()); //限购数量
                availableCount = Integer.parseInt(limitMap.get(skuId).get("availableCount").toString());//可购买数量
                String discountWarePrice = "";
                if (availableCount > 0) {
                    if (QDStringUtil.isNotNull(optimizePromotionMap) && optimizePromotionMap.containsKey(skuId) ){
                        GoodsPromotion goodsPromotion = optimizePromotionMap.get(skuId);
                        discountWarePrice = goodsPromotion.getPromotionPrice();
                    }
                }

                diffCount = buyCount - availableCount;
                Integer originalCount = buyCount;
                if (diffCount > 0) { //原价购买
                    buyGoodInfoBean.setBuyNum(diffCount);
                    list.add(buyGoodInfoBean);//同个原价商品组
                    originalCount = availableCount;
                }
                if (availableCount > 0) { //如果限购有剩余可购买数
                    BuyGoodInfoBean buyGoodInfoBean2 = transfor(BuyGoodInfoBean.class, legouSkuDetailInfo);
                    buyGoodInfoBean2.setBuyNum(originalCount);//限购可购买数量
                    buyGoodInfoBean2.setPrice(discountWarePrice); //放置促销价格
                    list.add(buyGoodInfoBean2);//同个促销价商品组
                }

                return list;
            }
        }
        list.add(buyGoodInfoBean);
        return list;
    }


    /**
     *  /**
     * version 3.0.0 专用
     * 获取该订单的优惠券信息和促销信息并组装成可用模型
     * @param projectId
     * @param memberId
     * @param skus
     * @param orderSourceType
     * @param couponCodes
     * @param orderPromotionIds
     * @param isUserChooseCoupon
     * @param isMrx  是否是每日鲜订单 1：是
     * @return
     * @throws Exception
     */
    public com.qding.api.call.app.qding.v1_4_0.struct.legou.order.response.data.ConfirmOrderResponseData 
    fittingConfirmOrderResponseObjFor30(String projectId, String memberId, List<Sku> skus, Integer orderSourceType, String[] couponCodes, List<String> orderPromotionIds, Integer isUserChooseCoupon,boolean isMrx,String memberAddrId) throws Exception {

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
        o.setOrderPromotionIds(CollectionUtils.isNotEmpty(orderPromotionIds)
        		? orderPromotionIds : new ArrayList<String>());
        if (couponCodes != null) {
            o.setCouponCodeList(Arrays.asList(couponCodes));
        }
        List<SubOrderDto> subOrders = transforList(SubOrderDto.class, skus);
        o.setSubOrderlist(subOrders);

        com.qding.api.call.app.qding.v1_4_0.struct.legou.order.response.data.ConfirmOrderResponseData data
                = new com.qding.api.call.app.qding.v1_4_0.struct.legou.order.response.data.ConfirmOrderResponseData();
        CountBeforOrderRequest countBeforeOrderRequest = new CountBeforOrderRequest();

        //可用优惠券
        List<PromotionCouponUserDto> promotionCouponList = Lists.newArrayList();
        //不可用优惠券
        List<PromotionCouponUserDto> unavailableCouponList = Lists.newArrayList();

        if (isUserChooseCoupon == 2) { //未选券
            countBeforeOrderRequest.setOrderGeneratorDto(o);
            CountBeforOrderResponse countBeforOrderResponse = promotionRemoteService.countBeforOrder(countBeforeOrderRequest);
             data = transfor(com.qding.api.call.app.qding.v1_4_0.struct.legou.order.response.data.ConfirmOrderResponseData.class, countBeforOrderResponse);

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
            //可用优惠券
            promotionCouponList = orderCouponsResponse.getCouponList();
            //不可用优惠券
            unavailableCouponList = orderCouponsResponse.getUnavailableCouponList();
            
            //3.3新增
            countBeforeOrderRequest.setOrderGeneratorDto(o);
            countBeforeOrderRequest.setIsUserChooseCoupon(isUserChooseCoupon);
            CountForOrderBestResponse countBeforOrderResponse1 = 
            		promotionRemoteService.countForOrderBest(countBeforeOrderRequest);
            checkAndContinue(countBeforOrderResponse1);
            if (countBeforOrderResponse1.getOrderDetailDto() == null) {
                return data;
            }
            //ConfirmOrderGoodsPromotionConvert 商品促销在这里完成
            //阶梯团购促销，目前放入商品促销属性中，方便app端显示
            if(countBeforOrderResponse1.getOrderDetailDto().getPromotionList()!=null){
            	 for(OrderPromotion p : countBeforOrderResponse1.getOrderDetailDto().getPromotionList()) {
         			if(p.getType() == 6) {
         				List<com.qding.api.call.app.qding.v1_3_0.struct.legou.order.GoodsPromotion> gp = new ArrayList<>();
         				com.qding.api.call.app.qding.v1_3_0.struct.legou.order.GoodsPromotion info=new 
         				com.qding.api.call.app.qding.v1_3_0.struct.legou.order.GoodsPromotion();
         				info.setPromotionId(p.getPromotionId());
         				info.setPromotionName(p.getPromotionName());
         				info.setDiscount(p.getDiscount());
         				gp.add(info);
         				data.getEntity().setGoodsPromotiones(gp);
         				break;
         			}
         		}
            }
            //3.3 结束
             
        } else { //首次进入默认最优|用户自身手动选券
            countBeforeOrderRequest.setOrderGeneratorDto(o);
            countBeforeOrderRequest.setIsUserChooseCoupon(isUserChooseCoupon);
            CountForOrderBestResponse countBeforOrderResponse = 
            		promotionRemoteService.countForOrderBest(countBeforeOrderRequest);
            checkAndContinue(countBeforOrderResponse);
            if (countBeforOrderResponse.getOrderDetailDto() == null) {
                return data;
            }
            data = transfor(com.qding.api.call.app.qding.v1_4_0.struct.legou.order.
            		response.data.ConfirmOrderResponseData.class, countBeforOrderResponse);
            //ConfirmOrderGoodsPromotionConvert 商品促销在这里完成
            //阶梯团购促销，目前放入商品促销属性中，方便app端显示
            if(countBeforOrderResponse.getOrderDetailDto().getPromotionList()!=null){
            	 for(OrderPromotion p : countBeforOrderResponse.getOrderDetailDto().getPromotionList()) {
         			if(p.getType() == 6) {
         				List<com.qding.api.call.app.qding.v1_3_0.struct.legou.order.GoodsPromotion> gp = new ArrayList<>();
         				com.qding.api.call.app.qding.v1_3_0.struct.legou.order.GoodsPromotion info=new 
         				com.qding.api.call.app.qding.v1_3_0.struct.legou.order.GoodsPromotion();
         				info.setPromotionId(p.getPromotionId());
         				info.setPromotionName(p.getPromotionName());
         				info.setDiscount(p.getDiscount());
         				gp.add(info);
         				data.getEntity().setGoodsPromotiones(gp);
         				break;
         			}
         		}
            }
            
            //可用优惠券
            promotionCouponList = countBeforOrderResponse.getCouponList();
            //不可用优惠券
             unavailableCouponList = countBeforOrderResponse.getUnavailableCouponList();
            data.getEntity().setIsCanUseCoupon(countBeforOrderResponse.getCanUseCoupon());
        }

        //计算所有可用优惠券的总价值
        Integer totalCouponsPrice = 0;
        if (CollectionUtils.isNotEmpty(promotionCouponList)) {
            Iterator<PromotionCouponUserDto> commonIterator = promotionCouponList.iterator();
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
            data.getEntity().setCommonCoupons(couponList);
        }

        if (CollectionUtils.isNotEmpty(unavailableCouponList)) {
            List<Coupon> unavailableCoupons = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(unavailableCouponList)) {
                unavailableCoupons = transforList(Coupon.class, unavailableCouponList);
            }
            for (Coupon coupon : unavailableCoupons) {
                String[] array = {coupon.getBatchName()};
                coupon.setProductNoNames(array);
            }
            data.getEntity().setUnavailableCommonCoupons(unavailableCoupons);
        }

        data.getEntity().setTotalCouponsPrice(String.valueOf(totalCouponsPrice));

        if (isMrx) { //如果是每日鲜
            List<ProjectConnect> projectConnects =getProjectConnectList( projectId);   //如果当前社区匹配到多个物业自提地址则返回自提列表
            if (CollectionUtils.isNotEmpty(projectConnects)) {
                if (projectConnects.size()>1) {
                    data.getEntity().setIsMoreProjectAddr(1);
                }
                data.getEntity().setProjectConcat(transfor(ProjectConcat.class, projectConnects.get(0)));
            }

        } else {

            MemberAddress memberAddress = null;
            if(QDStringUtil.isNotEmpty(memberAddrId)) {
                memberAddress = memberService.getMembverAddressByAddrId(memberAddrId);
            }
            //获取当前会员在社区的默认物业地址
            ProjectConnect projectAddr = getProjectAddr(memberId, projectId);
            if (QDStringUtil.isNotNull(projectAddr))
                data.getEntity().setProjectConcat(transfor(ProjectConcat.class, projectAddr));

            if(QDStringUtil.isNull(memberAddress)) {
                //获取用户默认配送收货地址
                memberAddress = memberService.getMemberDefaultAddress(memberId);
            }
            if (QDStringUtil.isNotNull(memberAddress)) {
                data.getEntity().setDeliveryAddress(transfor(Addresses.class, memberAddress));
            }
            if (data.getEntity().getDeliveryAddress() != null
                    && (data.getEntity().getDeliveryAddress().getVersion() == null || data.getEntity().getDeliveryAddress().getVersion() == 1)
                    && data.getEntity().getDeliveryAddress().getDefaultFlag().intValue() == 1) {
                data.getEntity().getDeliveryAddress().setDefaultFlag(0);
            }
        }

        return data;
    }




    /**
     * 获取用户，指定社区默认物业地址
     *
     * @param memberId
     * @return
     * @throws Exception
     */
    private ProjectConnect getProjectAddr(String memberId, String projectId) throws Exception {

        ProjectConnect projectAddr = null; //getLastTimeProjectAddrByMid (memberId,projectId);
        if ( QDStringUtil.isNull(projectAddr)){
            //当自提地址只有一个是才会默认选中，否则不选中
            List<ProjectConnect> projectConnects =getProjectConnectListWithoutMRX ( projectId);
            if (CollectionUtils.isNotEmpty(projectConnects)) {
                projectAddr = projectConnects.get(0);
            }
        }
        return projectAddr;
    }


}
