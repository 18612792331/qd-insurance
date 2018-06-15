package com.qding.api.call.app.qding.v1_3_0;

import com.google.common.collect.Lists;
import com.qding.api.cache.memcache.MemberStatusCache;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.CalculateOrderPrice;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.request.CalculateOrderPriceRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.response.data.CalculateOrderPriceResponseData;
import com.qding.api.call.service.OrderService;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.legou.struct.request.GetOrderFreightRequest;
import com.qding.oder.dto.OrderDetailDto;
import com.qding.oder.dto.OrderGeneratorDto;
import com.qding.oder.dto.SubOrderDto;
import com.qding.order.domain.OrderBase;
import com.qding.promotion.common.struct.request.*;
import com.qding.promotion.common.struct.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.BindCoupon;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.request.BindCouponsRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.request.GetAllCouponsRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.request.GetOrderCouponsRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.request.GetUserCouponsRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.response.data.BindCouponsResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.response.data.GetAllCouponsResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.response.data.GetOrderCouponsResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.response.data.GetUserCouponsResponseData;
import com.qding.api.struct.Response;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.promotion.common.service.IPromotionRemoteService;

import java.math.BigDecimal;
import java.util.List;

/**
 * 千丁劵
 * @author lichao
 *
 */
public class CallCoupon extends Callable{

	@Autowired
	private IProfileService profileAPI;
	
	@Autowired
	private IPromotionRemoteService promotionRemoteService;

	@Autowired
	private MemberStatusCache memberStatusCache;

	@Autowired
	private OrderService orderService;

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallCoupon.class);


	/**
	 * 用户绑定优惠券
	 * @param request
	 * @return
	 */
	@HTTP(alias="bindCoupons",isNeadToken = true, isRequireAuth =true)
	public Response<BindCouponsResponseData> bindCoupons(BindCouponsRequest request, UserToken userToken) {
		
		try {
			Response<BindCouponsResponseData> response = new Response<BindCouponsResponseData>();

			Boolean  canUseFlag = memberStatusCache.getMemberIsHalt(request.getMemberId());

			if (canUseFlag || (!userToken.getMemberId().equals(request.getMemberId()))) {
				BindCouponsResponseData data = new BindCouponsResponseData();
				data.setMessage("当前请求异常");
				response.setData(data);
				response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
				return response;
			}
			
			GetMemberRequest getMemberRequest = transfor(GetMemberRequest.class, request);
			
			GetMemberResponse getMemberResponse = profileAPI.getMemberById(getMemberRequest);
			
			checkAndContinue(getMemberResponse);
			
			MemberInfo member = getMemberResponse.getMemberInfo();

			BindCouponRequest bindCouponRequest = transfor(BindCouponRequest.class, request);

			bindCouponRequest.setUserName(member.getName());
			
			BindCouponResponse bindCouponResponse = promotionRemoteService.bindCoupon2User(bindCouponRequest);
			
			checkAndContinue(bindCouponResponse);
			
			BindCoupon bindCoupon = transfor(BindCoupon.class, bindCouponResponse);
			
			response.setData(new BindCouponsResponseData(bindCoupon));
			
			return response;
			
		} catch (Exception e) {

			return handleException(BindCouponsResponseData.class, e);
		}
	}

	/**
	 * 获取用户所有的优惠券
	 * @param request
	 * @return
	 */
	@HTTP(alias="getAllCoupons")
	@Deprecated
	public Response<GetAllCouponsResponseData> getAllCoupons(GetAllCouponsRequest request) {
		
		try {
			Response<GetAllCouponsResponseData> response = new Response<GetAllCouponsResponseData>();
			
			GetAllCouponUserByMidRequest getAllCouponUserByMidRequest = transfor(GetAllCouponUserByMidRequest.class, request);
			
			GetAllCouponUserByMidResponse getAllCouponUserByMidResponse = promotionRemoteService.getAllCouponUserByMid(getAllCouponUserByMidRequest);
			
			checkAndContinue(getAllCouponUserByMidResponse);
			
			GetAllCouponsResponseData data = transfor(GetAllCouponsResponseData.class, getAllCouponUserByMidResponse);
			
			response.setData(data);
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetAllCouponsResponseData.class, e);
		}
	}
	
	/**
	 * 获取用户某个项目下某个业态的所有可用优惠券(不包含未生效的)
	 * @param request
	 * @return
	 */
	@HTTP(alias="getOrderCoupons")
	@Deprecated
	public Response<GetOrderCouponsResponseData> getOrderCoupons(GetOrderCouponsRequest request) {
		
		try {
			Response<GetOrderCouponsResponseData> response = new Response<GetOrderCouponsResponseData>();
			
			GetOrderAvailableCouponListRequest orderCouponsRequest = transfor(GetOrderAvailableCouponListRequest.class, request);
			
			GetOrderAvailableCouponListResponse orderCouponsResponse = promotionRemoteService.getOrderAvailableCouponList(orderCouponsRequest);
			
			checkAndContinue(orderCouponsResponse);
			
			GetOrderCouponsResponseData data = transfor(GetOrderCouponsResponseData.class, orderCouponsResponse);
			
			response.setData(data);
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetOrderCouponsResponseData.class, e);
		}
	}
	
	
	/**
	 * 获取用户所有可用优惠券(包含未生效的)
	 * @param request
	 * @return
	 */
	@HTTP(alias="getUserCoupons")
	@Deprecated
	public Response<GetUserCouponsResponseData> getUserCoupons(GetUserCouponsRequest request) {
		
		try {
			Response<GetUserCouponsResponseData> response = new Response<GetUserCouponsResponseData>();
			
			GetUserCouponListRequest getAvailableCouponsRequest = transfor(GetUserCouponListRequest.class, request);
			
			GetUserCouponListResponse couponListResponse = promotionRemoteService.getUserCouponList(getAvailableCouponsRequest);
			
			checkAndContinue(couponListResponse);
			
			GetUserCouponsResponseData data = transfor(GetUserCouponsResponseData.class, couponListResponse);
			
			response.setData(data);
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetUserCouponsResponseData.class, e);
		}
	}


	/**
	 * 订单计价器
	 * @param request
	 * @return
	 */
	@HTTP(alias="calculateOrderPrice")
	public Response<CalculateOrderPriceResponseData> calculateOrderPrice (CalculateOrderPriceRequest request) {

		try {
			CountBeforOrderRequest countBeforeOrderRequest = new CountBeforOrderRequest();
			OrderGeneratorDto o = request.getOrderGeneratorDto();
			countBeforeOrderRequest.setOrderGeneratorDto(o);
			CountBeforOrderResponse countBeforOrderResponse = promotionRemoteService.countBeforOrder(countBeforeOrderRequest);
			checkAndContinue(countBeforOrderResponse);
			OrderDetailDto orderDetailDto = countBeforOrderResponse.getOrderDetailDto();
			if (QDStringUtil.isNull(orderDetailDto)){
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "orderDetailDto is null");
			}
			OrderBase orderBase = orderDetailDto.getOrderBase();
			String shoudPay = orderBase.getTotalRealpay();
			String orderFreight = "0.00";
			if(Constant.PRODUCT_NO_NG.equals(request.getOrderGeneratorDto().getProductNo())) {

				OrderGeneratorDto orderGeneratorDto = request.getOrderGeneratorDto();
				List<SubOrderDto> subOrderlist =orderGeneratorDto.getSubOrderlist();
				List<GetOrderFreightRequest.OrderSkuInfo> skuInfoList = Lists.newArrayList();
				GetOrderFreightRequest orderFreightRequest = new GetOrderFreightRequest();
				for (SubOrderDto subOrderDto : subOrderlist) {
					GetOrderFreightRequest.OrderSkuInfo orderSkuInfo = orderFreightRequest.new OrderSkuInfo();
					orderSkuInfo.setSkuId(subOrderDto.getWareSkuId());
					orderSkuInfo.setNums(subOrderDto.getWareCount());
					skuInfoList.add(orderSkuInfo);
				}
				orderFreight = orderService.getOrderFreight(skuInfoList,shoudPay);
			}

			CalculateOrderPrice entity = new CalculateOrderPrice();
			entity.setTotalDiscountPrice(orderBase.getTotalDiscount());
			entity.setTotalPrice(orderBase.getTotalPrice());
			entity.setPromotionList(orderDetailDto.getPromotionList());
			entity.setIsShowNotice(orderDetailDto.getIsShowNotice());
			entity.setNotice(orderDetailDto.getNotice());
			if(!("0.00").equals(orderFreight)) {
				BigDecimal shoudPayDecimal = new BigDecimal(shoudPay);
				BigDecimal orderFreightDecimal = new BigDecimal(orderFreight);
				shoudPayDecimal = shoudPayDecimal.add(orderFreightDecimal);
				//应付价
				shoudPay = shoudPayDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).toString();
			}
			entity.setShouldPay(shoudPay);
			entity.setExpressPrice(orderFreight);
			CalculateOrderPriceResponseData data = new CalculateOrderPriceResponseData();
			data.setEntity(entity);

			Response<CalculateOrderPriceResponseData> response = new  Response<CalculateOrderPriceResponseData>();
			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());

			return response;
		} catch (ServiceException e) {
			return handleException(CalculateOrderPriceResponseData.class, e);
		}

	}

}
