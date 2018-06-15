package com.qding.api.call.app.qding.v1_3_0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.qding.api.call.service.PromotionService;
import com.qding.api.process.security.UserToken;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.logistics.platform.client.remote.LogisticsPlatformOrderRpcService;
import com.qding.logistics.platform.client.remote.struct.request.OrderSignRequest;
import com.qding.member.rpc.IMemberAddressRPC;
import com.qding.member.rpc.response.memberaddress.MemberAddressResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Sku;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.AddCartRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.CancelOrderRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.ConfirmOrderRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.ConfirmReceiptGoodsRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.DeleteCartRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.DeleteOrderRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.GetCartRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.GetOrderRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.GetOrdersRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.GetSubOrdersRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.IncrCartNumRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.MakeOrderRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.AddCartResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.CancelOrderResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.ConfirmOrderResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.DeleteCartResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.DeleteOrderResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.GetCartResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.GetOrderResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.GetOrdersResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.GetSubOrderResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.IncrCartNumResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data.MakeOrderResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.api.util.HttpMethod;
import com.qding.api.util.QDMemberRemote;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ProjectConnect;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.ware.WareRemoteService;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.legou.domain.Cart;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.CancleOrderRequest;
import com.qding.legou.struct.request.CreateGorderRequest;
import com.qding.legou.struct.request.DeleteCartGoodsRequest;
import com.qding.legou.struct.request.GetOrderDetailByCodeRequest;
import com.qding.legou.struct.request.GetOrderResultPageRequest;
import com.qding.legou.struct.request.GetOrderSubResultPageRequest;
import com.qding.legou.struct.request.GetUserCartRequest;
import com.qding.legou.struct.request.UpdateCartGoodsRequest;
import com.qding.legou.struct.response.AddCartResponse;
import com.qding.legou.struct.response.CancleOrderResponse;
import com.qding.legou.struct.response.CreateGorderResponse;
import com.qding.legou.struct.response.DeleteCartGoodsResponse;
import com.qding.legou.struct.response.DeleteOrderResponse;
import com.qding.legou.struct.response.GetOrderDetailByCodeResponse;
import com.qding.legou.struct.response.GetOrderResultPageResponse;
import com.qding.legou.struct.response.GetOrderSubResultPageResponse;
import com.qding.legou.struct.response.GetUserCartResponse;
import com.qding.legou.struct.response.UpdateCartGoodsResponse;
import com.qding.member.model.MemberAddress;
import com.qding.oder.dto.OrderGeneratorDto;
import com.qding.oder.dto.SubOrderDto;
import com.qding.promotion.common.dto.PromotionCouponUserDto;
import com.qding.promotion.common.dto.TemplateWithCouponUserDto;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.CountBeforOrderRequest;
import com.qding.promotion.common.struct.request.GetOrderAvailableCouponListRequest;
import com.qding.promotion.common.struct.response.CountBeforOrderResponse;
import com.qding.promotion.common.struct.response.GetOrderAvailableCouponListResponse;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;

/**
 * 乐购订单
 *
 * @author lichao
 */
public class CallLegouOrder extends Callable {

    @Autowired
    private ILegouRemoteService legouRemoteService;

    @Autowired
    private IMemberAddressRPC memberAddressService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private LogisticsPlatformOrderRpcService logisticsPlatformOrder;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ISolrSkuService skuService;

    @Autowired
    private IPromotionRemoteService promotionRemoteService;


    /**
     * 添加商品到购物车
     *
     * @param request
     * @return
     */
    @HTTP(alias = "addCart")
    public Response<AddCartResponseData> addCart(AddCartRequest request) {

        try {
            Response<AddCartResponseData> response = new Response<AddCartResponseData>();

            com.qding.legou.struct.request.AddCartRequest addCartRequest =
                    transfor(com.qding.legou.struct.request.AddCartRequest.class, request);

            AddCartResponse addCartResponse = legouRemoteService.addCart(addCartRequest);
            checkAndContinue(addCartResponse);
            AddCartResponseData cartResponseData = new AddCartResponseData();

            if (QDStringUtil.isNotEmpty(request.getMemberId()) && QDStringUtil.isNotEmpty(request.getProjectId())) {
                GetUserCartRequest getCartRequest = new GetUserCartRequest();
                getCartRequest.setMid(request.getMemberId());
                getCartRequest.setProjectId(Long.parseLong(request.getProjectId()));
                GetUserCartResponse getUserCartResponse = legouRemoteService.getUserCart(getCartRequest);
                List<Cart> catList = getUserCartResponse.getCatList();
                int count = 0;
                if (CollectionUtils.isNotEmpty(catList)) {
                    count = catList.size();
                }
                cartResponseData.setCartCount(count);
            }

            response.setData(cartResponseData);

            return response;

        } catch (Exception e) {

            return handleException(AddCartResponseData.class, e);
        }
    }


    /**
     * 从购物车中删除多个商品
     *
     * @param request
     * @return
     */
    @HTTP(alias = "deleteCart")
    public Response<DeleteCartResponseData> deleteCart(DeleteCartRequest request) {

        try {
            Response<DeleteCartResponseData> response = new Response<DeleteCartResponseData>();

            DeleteCartGoodsRequest deleteCartRequest = transfor(DeleteCartGoodsRequest.class, request);

            DeleteCartGoodsResponse deleteCartResponse = legouRemoteService.deleteCartGoods(deleteCartRequest);

            checkAndContinue(deleteCartResponse);

            response.setData(new DeleteCartResponseData());

            return response;

        } catch (Exception e) {

            return handleException(DeleteCartResponseData.class, e);

        }
    }

    /**
     * 修改购物车商品数量
     *
     * @param request
     * @return
     */
    @HTTP(alias = "incrmentCartNum")
    public Response<IncrCartNumResponseData> incrmentCartNum(IncrCartNumRequest request) {

        try {
            Response<IncrCartNumResponseData> response = new Response<IncrCartNumResponseData>();

            UpdateCartGoodsRequest updateCartGoodsRequest = transfor(UpdateCartGoodsRequest.class, request);

            UpdateCartGoodsResponse updateCartGoodsResponse = legouRemoteService.updateCartGoods(updateCartGoodsRequest);

            checkAndContinue(updateCartGoodsResponse);

            response.setData(new IncrCartNumResponseData());

            return response;

        } catch (Exception e) {

            return handleException(IncrCartNumResponseData.class, e);
        }
    }

    public GetOrderDetailByCodeResponse checkOrderAuth(String memberId, String orderCode)
            throws ServiceException {

        GetOrderDetailByCodeRequest getOrderRequest = new GetOrderDetailByCodeRequest();

        getOrderRequest.setOrderCode(orderCode);

        GetOrderDetailByCodeResponse getOrderResposne = legouRemoteService.getOrderDetailByCode(getOrderRequest);

        checkAndContinue(getOrderResposne);

        if (!memberId.equals(getOrderResposne.getLegouOrderDetailDto().getOrderBase().getMid())) {

            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "此用户没有此订单权限");
        }

        return getOrderResposne;
    }


    /**
     * 获取购物车列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getCarts")
    @Deprecated
    public Response<GetCartResponseData> getCarts(GetCartRequest request) {

        try {

            Response<GetCartResponseData> response = new Response<GetCartResponseData>();

            GetUserCartRequest getCartRequest = transfor(GetUserCartRequest.class, request);

            GetUserCartResponse getUserCartResponse = legouRemoteService.getUserCart(getCartRequest);

            checkAndContinue(getUserCartResponse);

            //商品详细信息 从商品接口中获取
            List<Cart> catList = getUserCartResponse.getCatList();
            List<com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Cart> cartList = new ArrayList<>();

            for (Cart c : catList) {

                LegouSkuRequest legouSkuRequest = new LegouSkuRequest();

                ArrayList<Long> skuIds = new ArrayList<>();

                skuIds.add(c.getWareSkuId());

                legouSkuRequest.setSortedSkuIds(skuIds);

                legouSkuRequest.setFindAllStatus(true);

                LegouSkuResponse skuResponse = skuService.queryLegouSku(legouSkuRequest);

                List<LegouSkuDetailInfo> skus = skuResponse.getSkus();

                if (skus != null && !skus.isEmpty()) {

                    LegouSkuDetailInfo sku = skus.get(0);
                    cartList.add(
                            new com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Cart(
                                    sku.getWareId().toString(),
                                    c.getWareSkuId().toString(),
                                    sku.getName(),
                                    sku.getSkuImgUrl(),
                                    sku.getPrice(),
                                    sku.getMarketPrice(),
                                    c.getCount(),
                                    sku.getDeliveryType(),
                                    promotionService.getGoodActivityInfo(Long.parseLong(sku.getSkuId()), Long.parseLong(request.getProjectId()))
                            )
                    );
                }
            }

            GetCartResponseData data = new GetCartResponseData();

            data.setList(cartList);

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
    @HTTP(alias = "getOrder")
    @Deprecated
    public Response<GetOrderResponseData> getOrder(GetOrderRequest request) {

        try {
            Response<GetOrderResponseData> response = new Response<GetOrderResponseData>();

            GetOrderDetailByCodeResponse getOrderResponse = checkOrderAuth(request.getMemberId(), request.getOrderCode());

            GetOrderResponseData data = transfor(GetOrderResponseData.class, getOrderResponse);

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GetOrderResponseData.class, e);
        }
    }


    /**
     * 获取订单列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getOrders")
    @Deprecated
    public Response<GetOrdersResponseData> getOrders(GetOrdersRequest request) {

        try {
            Response<GetOrdersResponseData> response = new Response<GetOrdersResponseData>();

            GetOrderResultPageRequest getOrdersRequest = transfor(GetOrderResultPageRequest.class, request);

            Integer type = request.getType();

            if (type == Constant.ORDER_LIST_CANCEL) {

                getOrdersRequest.setOrderStatusList(Arrays.asList(
                        Constant.ORDER_STATUS_201,
                        Constant.ORDER_STATUS_202,
                        Constant.ORDER_STATUS_203,
                        Constant.ORDER_STATUS_204
                ));

            } else if (type == Constant.ORDER_LIST_WAIT_PAY) {

                getOrdersRequest.setPayStatusList(Arrays.asList(
                        Constant.PAYMENT_STATUS_101,
                        Constant.PAYMENT_STATUS_102,
                        Constant.PAYMENT_STATUS_103,
                        Constant.PAYMENT_STATUS_104));

                getOrdersRequest.setOrderStatusList(Arrays.asList(
                        Constant.ORDER_STATUS_101
                ));

            }

            GetOrderResultPageResponse getOrdersResponse = legouRemoteService.getOrderResultPage(getOrdersRequest);

            checkAndContinue(getOrdersResponse);

            GetOrdersResponseData data = transfor(GetOrdersResponseData.class, getOrdersResponse);

            data.setRecordCount(data.getList().size());

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GetOrdersResponseData.class, e);
        }
    }


    /**
     * 获取子订单列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getSubOrders")
    public Response<GetSubOrderResponseData> getSubOrders(GetSubOrdersRequest request) {

        GetOrderSubResultPageRequest getSubOrderRequest = transfor(GetOrderSubResultPageRequest.class, request);

        Integer type = request.getType();

        if (type == Constant.ORDER_LIST_WAIT_DELIVERY) {

            getSubOrderRequest.setProductStatus(Arrays.asList(Constant.LEGOU_PRODUCT_STATUS_100));

            getSubOrderRequest.setPayStatusList(Arrays.asList(
                    Constant.PAYMENT_STATUS_104,
                    Constant.PAYMENT_STATUS_105,
                    Constant.PAYMENT_STATUS_106,
                    Constant.PAYMENT_STATUS_107));

        } else if (type == Constant.ORDER_LIST_WAIT_SIGN) {

            getSubOrderRequest.setProductStatus(
                    Arrays.asList(Constant.LEGOU_PRODUCT_STATUS_101, Constant.LEGOU_PRODUCT_STATUS_102));

        } else if (type == Constant.ORDER_LIST_REFUND) {

            getSubOrderRequest.setOrderStatus(Constant.LEGOU_SUB_ORDER_STATUS_102);

        }

        GetOrderSubResultPageResponse subResultPageResponse = legouRemoteService.getOrderSubResultPage(getSubOrderRequest);

        GetSubOrderResponseData data = transfor(GetSubOrderResponseData.class, subResultPageResponse);

        Response<GetSubOrderResponseData> response = new Response<>();

        data.setRecordCount(data.getList().size());

        response.setData(data);

        return response;
    }


    /**
     * 确定订单
     *
     * @param request
     * @return
     */
    @HTTP(alias = "confirmOrder")
    @Deprecated
    public Response<ConfirmOrderResponseData> confirmOrder(ConfirmOrderRequest request) {

        try {
            //会员ID
            String memberId = request.getMemberId();
            //小区ID
            String projectId = request.getProjectId();
            //订单详情
            List<Sku> skus = request.getSkus();

            Project project = projectReadService.get(Long.parseLong(projectId));

            if (project == null) {

                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
            }

            OrderGeneratorDto o = new OrderGeneratorDto();

            o.setMid(memberId);
            o.setProjectId(project.getId());
            o.setProjectName(project.getName());
            o.setSourceType(request.getOrderSourceType());
            o.setProductNo(Constant.PRODUCT_NO_NG);
            String[] couponCodes = request.getCouponCodes();

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

            //获取用户的可用优惠券 (区分社区和业态)
            GetOrderAvailableCouponListRequest orderCouponsRequest = transfor(GetOrderAvailableCouponListRequest.class, request);

            orderCouponsRequest.setMid(memberId);
            orderCouponsRequest.setProjectId(project.getId());
            //新乐购优惠券
            orderCouponsRequest.setProductNo(Constant.PRODUCT_NO_NG);
            orderCouponsRequest.setOrderRealPrice(QDStringUtil.isNotNull(countBeforOrderResponse.getOrderDetailDto().getOrderBase().getTotalRealpay()) ? countBeforOrderResponse.getOrderDetailDto().getOrderBase().getTotalRealpay() : null);
            orderCouponsRequest.setSubOrderlist(subOrders);

            GetOrderAvailableCouponListResponse orderCouponsResponse = promotionRemoteService.getOrderAvailableCouponList(orderCouponsRequest);

            checkAndContinue(orderCouponsResponse);

            transfor(data, orderCouponsResponse);

            //计算所有优惠券的总价值
            Integer totalCouponsPrice = 0;
            Iterator<PromotionCouponUserDto> commonIterator = orderCouponsResponse.getCommonList().iterator();
            while (commonIterator.hasNext()) {
                PromotionCouponUserDto next = commonIterator.next();
                totalCouponsPrice += next.getPrice();
            }
            Iterator<TemplateWithCouponUserDto> specialBatchIterator = orderCouponsResponse.getSpecialList().iterator();
            while (specialBatchIterator.hasNext()) {
                TemplateWithCouponUserDto nextBatch = specialBatchIterator.next();
                Iterator<PromotionCouponUserDto> specialIterator = nextBatch.getCouponUserDtoList().iterator();
                while (specialIterator.hasNext()) {
                    PromotionCouponUserDto next = specialIterator.next();
                    totalCouponsPrice += next.getPrice();
                }
            }
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

            data.getEntity().setDeliveryAddress(transfor(Addresses.class, memberAddress));

            Response<ConfirmOrderResponseData> response = new Response<>();

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(ConfirmOrderResponseData.class, e);
        }
    }

    /**
     * 下单
     *
     * @param request
     * @return
     */
    @HTTP(alias = "makeOrder",isRequireAuth = true,isNeadToken = true,isNeadProject = true,supportMethod = HttpMethod.POST)
    @Deprecated
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

            MemberAddressResponse  memberAddressResponse = memberAddressService.get(deliveryAddressId);

            checkAndContinue(memberAddressResponse);

            MemberAddress ma = memberAddressResponse.getMemberAddress();

            if (ma == null) {

                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "收货地址不存在");
            }

            Project project = projectReadService.get(Long.parseLong(projectId));

            if (project == null) {

                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
            }

            CreateGorderRequest createGOrderRequest = new CreateGorderRequest();

            OrderGeneratorDto o = new OrderGeneratorDto();

            if (QDStringUtil.isNotEmpty(projectAddressId)) {
                ProjectConnect projectAddress = projectReadService.getProjectConnectBId(Long.parseLong(projectAddressId));

                if (projectAddress == null) {

                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目地址不存在");
                }
                o.setPaddressId(projectAddress.getId());
                o.setPaddress(projectAddress.getAddress());
            }
            o.setMid(memberId);
            o.setProjectId(project.getId());
            o.setProjectName(project.getName());
            o.setPublicsId(QDStringUtil.isEmpty(request.getPublicsId()) ? null : Long.parseLong(request.getPublicsId()));
            o.setSourceType(orderSourceType);
            o.setInvoiceTitle(invoiceTitle);
            o.setReceiverId(ma.getId());
            o.setReceiverName(ma.getName());
            o.setReceiverPhone(ma.getMobile());
            o.setReceiverAddress(ma.getAddress());
            o.setIsPayOnline(request.getIsPayOnline());
            o.setIsAnonymity(request.getIsAnonymity());//2.0.0版本新增是否匿名
            o.setOrderPromotionIds(CollectionUtils.isNotEmpty(request.getOrderPromotionIds()) ? request.getOrderPromotionIds() : new ArrayList<String>());
            String[] couponCodes = request.getCouponCodes();
            if (couponCodes != null) {
                o.setCouponCodeList(Arrays.asList(couponCodes));
            }
            List<SubOrderDto> subOrders = transforList(SubOrderDto.class, skus);

            o.setSubOrderlist(subOrders);

            createGOrderRequest.setOrderDto(o);

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
     * 取消订单
     *
     * @param request
     * @return
     */
    @HTTP(alias = "cancelOrder")
    public Response<CancelOrderResponseData> cancelOrder(CancelOrderRequest request) {

        try {
            Response<CancelOrderResponseData> response = new Response<CancelOrderResponseData>();

            checkOrderAuth(request.getMemberId(), request.getOrderCode());

            CancleOrderRequest cancelOrderRequest = transfor(CancleOrderRequest.class, request);

            CancleOrderResponse cancleOrderResponse = legouRemoteService.cancleOrder(cancelOrderRequest);

            checkAndContinue(cancleOrderResponse);

            response.setData(new CancelOrderResponseData());

            return response;

        } catch (Exception e) {

            return handleException(CancelOrderResponseData.class, e);
        }
    }

    /**
     * 删除订单
     *
     * @param request
     * @return
     */
    @HTTP(alias = "deleteOrder")
    public Response<DeleteOrderResponseData> deleteOrder(DeleteOrderRequest request) {

        try {
            Response<DeleteOrderResponseData> response = new Response<DeleteOrderResponseData>();

            checkOrderAuth(request.getMemberId(), request.getOrderCode());

            com.qding.legou.struct.request.DeleteOrderRequest deleteOrderRequest =
                    transfor(com.qding.legou.struct.request.DeleteOrderRequest.class, request);

            DeleteOrderResponse deleteOrderResposne = legouRemoteService.deleteOrder(deleteOrderRequest);

            checkAndContinue(deleteOrderResposne);

            response.setData(new DeleteOrderResponseData());

            return response;

        } catch (Exception e) {

            return handleException(DeleteOrderResponseData.class, e);
        }
    }

    /**
     * 确认收货商品（签收功能）
     *
     * @param request
     * @return
     */
    @HTTP(alias = "confirmReceiptGoods")
    @Deprecated
    public Response<ResponseData> confirmReceiptGoods(ConfirmReceiptGoodsRequest request) {

        try {
            Response<ResponseData> response = new Response<>();

            GetOrderDetailByCodeRequest orderDetailByCodeRequest = new GetOrderDetailByCodeRequest();
            orderDetailByCodeRequest.setOrderCode(request.getSubOrderCode());
            GetOrderDetailByCodeResponse orderDetailByCodeResponse = legouRemoteService.getOrderDetailByCode(orderDetailByCodeRequest);
            checkAndContinue(orderDetailByCodeResponse);
            String mid = orderDetailByCodeResponse.getLegouOrderDetailDto().getLegouOrder().getMid();
            String name = orderDetailByCodeResponse.getLegouOrderDetailDto().getLegouOrder().getReceiverName();
            OrderSignRequest orderSignRequest = new OrderSignRequest();
            orderSignRequest.setOptId(mid);
            orderSignRequest.setOptName(name);
            BaseResponse baseResponse = logisticsPlatformOrder.orderSign(orderSignRequest);
            checkAndContinue(baseResponse);

			/*ReceiveGoodsConfirmResponse confirmResponse = legouRemoteService.receiveGoodsConfirm(transfor(
                    ReceiveGoodsConfirmRequest.class, request
				));
			
			checkAndContinue(confirmResponse);*/

            response.setData(new ResponseData());

            return response;

        } catch (Exception e) {

            return handleException(ResponseData.class, e);
        }
    }
}
