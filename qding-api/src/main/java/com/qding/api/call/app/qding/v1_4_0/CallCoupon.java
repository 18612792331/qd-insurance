package com.qding.api.call.app.qding.v1_4_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_4_0.struct.coupon.Coupon;
import com.qding.api.call.app.qding.v1_4_0.struct.coupon.request.GetAllCouponsRequest;
import com.qding.api.call.app.qding.v1_4_0.struct.coupon.request.GetPromotionCouponByCodeRequest;
import com.qding.api.call.app.qding.v1_4_0.struct.coupon.request.GetUserCouponsRequest;
import com.qding.api.call.app.qding.v1_4_0.struct.coupon.request.GetOrderCouponsRequest;
import com.qding.api.call.app.qding.v1_4_0.struct.coupon.response.data.GetCouponListResponseData;
import com.qding.api.call.app.qding.v1_4_0.struct.coupon.response.data.GetPromotionCouponByCodeResponse;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.promotion.common.dto.PromotionCouponUserDto;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.GetAllCouponUserByMidRequest;
import com.qding.promotion.common.struct.request.GetOrderAvailableCouponListRequest;
import com.qding.promotion.common.struct.request.GetUserCouponListRequest;
import com.qding.promotion.common.struct.response.CouponListResponse;
import com.qding.promotion.common.struct.response.CouponPageListResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * 千丁劵
 * @author jiawenzheng
 *
 */
public class CallCoupon extends com.qding.api.call.app.qding.v1_3_0.CallCoupon {

	@Autowired
	private IPromotionRemoteService promotionRemoteService;


	@Autowired
	private SkipModeFitting skipMode;


	/**
	 * 获取下单时订单可用优惠券
	 * 某个项目下某个业态的所有可用优惠券
	 * @param request
	 * @return
	 */
	@HTTP(alias="getOrderCoupons")
	public Response<GetCouponListResponseData> getOrderCoupons(GetOrderCouponsRequest request) {
		
		try {
			Response<GetCouponListResponseData> response = new Response<GetCouponListResponseData>();
			GetOrderAvailableCouponListRequest orderCouponsRequest = transfor(GetOrderAvailableCouponListRequest.class, request);
			CouponListResponse orderCouponsResponse = promotionRemoteService.getOrderAvailableCouponListV2(orderCouponsRequest);
			checkAndContinue(orderCouponsResponse);

			List<Coupon> commonCoupons = fittingCouponList(orderCouponsResponse.getCouponList());
			List<Coupon> unavailableCouponList = fittingCouponList(orderCouponsResponse.getUnavailableCouponList());
			GetCouponListResponseData data = new GetCouponListResponseData();
			data.setCommonCoupons(commonCoupons);
			data.setUnavailableCouponList(unavailableCouponList);
			data.setTotalCount(commonCoupons.size());
			response.setData(data);
			
			return response;
			
		} catch (Exception e) {
			return handleException(GetCouponListResponseData.class, e);
		}
	}


	/**
	 * 获取用户所有可用优惠券(包含未生效的)
	 * @param request
	 * @return
	 */
	@HTTP(alias="getUserCoupons",isRequireAuth = true,isNeadToken = true)
	public Response<GetCouponListResponseData> getUserCoupons(GetUserCouponsRequest request, UserToken userToken) {

		try {
			Response<GetCouponListResponseData> response = new Response<GetCouponListResponseData>();
			GetUserCouponListRequest getAvailableCouponsRequest = transfor(GetUserCouponListRequest.class, request);
			getAvailableCouponsRequest.setMid(userToken.getMemberId());
			CouponPageListResponse couponListResponse = promotionRemoteService.getUserCouponListV2(getAvailableCouponsRequest);
			checkAndContinue(couponListResponse);

			List<PromotionCouponUserDto> promotionCouponUserDtoList =  couponListResponse.getResultPage().getItems();
			GetCouponListResponseData data = new GetCouponListResponseData();
			List<Coupon> commonCoupons = fittingCouponList(promotionCouponUserDtoList);
			Integer totalCount =  couponListResponse.getResultPage().getTotalCount();
			data.setCommonCoupons(commonCoupons);
			data.setTotalCount(totalCount);
			response.setData(data);
			return response;

		} catch (Exception e) {
			return handleException(GetCouponListResponseData.class, e);
		}
	}

	/**
	 * 获取用户所有的优惠券
	 * @param request
	 * @return
	 */
	@HTTP(alias="getAllCoupons")
	public Response<GetCouponListResponseData> getAllCoupons(GetAllCouponsRequest request) {

		try {
			Response<GetCouponListResponseData> response = new Response<GetCouponListResponseData>();
			GetAllCouponUserByMidRequest getAllCouponUserByMidRequest = transfor(GetAllCouponUserByMidRequest.class, request);
			CouponPageListResponse getAllCouponUserByMidResponse = promotionRemoteService.getAllCouponUserByMidV2(getAllCouponUserByMidRequest);
			checkAndContinue(getAllCouponUserByMidResponse);

			List<PromotionCouponUserDto> promotionCouponUserDtoList = getAllCouponUserByMidResponse.getResultPage().getItems();
			List<Coupon> commonCoupons =  fittingCouponList(promotionCouponUserDtoList);
			GetCouponListResponseData data =  new GetCouponListResponseData();
			Integer totalCount =  getAllCouponUserByMidResponse.getResultPage().getTotalCount();
			data.setCommonCoupons(commonCoupons);
			data.setTotalCount(totalCount);
			response.setData(data);

			return response;
		} catch (Exception e) {
			return handleException(GetCouponListResponseData.class, e);
		}
	}


	public List<Coupon> fittingCouponList(List<PromotionCouponUserDto> couponUserDtoList){

		List<Coupon> list = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(couponUserDtoList)) {
			for (PromotionCouponUserDto promotionCouponUserDto : couponUserDtoList) {
				Coupon coupon = transfor(Coupon.class,promotionCouponUserDto);
				if (QDStringUtil.isNotEmpty(promotionCouponUserDto.getNote())) {
					coupon.setProductNoNames(new String[]{promotionCouponUserDto.getNote()});
				}
				coupon.setSkipModel(getSkipUrl(coupon.getCode()));
				list.add(coupon);
			}
		}
		return  list;
	}

	public String getSkipUrl(String code){
		String url = APIPropertiesClient.getSkipUrl("coupon")+code;
		String skipUrl = skipMode.fittingSkipUrl("2.0.0",url,0,"");
		return  skipUrl;
	}

	/**
	 * 根据优惠券Code获取优惠券详情
	 * @param request
	 * @return
	 */
	@HTTP(alias="getCouponByCode")
	public Response<GetPromotionCouponByCodeResponse> getPromotionCouponByCode (GetPromotionCouponByCodeRequest request) {

		Response<GetPromotionCouponByCodeResponse> response = new Response<GetPromotionCouponByCodeResponse>();
		response.setCode(HttpStatus.OK.getStatusCode());
		GetPromotionCouponByCodeResponse data = new GetPromotionCouponByCodeResponse ();
		try {
			com.qding.promotion.common.struct.response.GetPromotionCouponByCodeResponse  getPromotionCouponByCodeResponse = promotionRemoteService.getPromotionCouponByCode(request.getCouponCode());
			checkAndContinue(getPromotionCouponByCodeResponse);
			Coupon entity = transfor(Coupon.class,getPromotionCouponByCodeResponse.getPromotionCouponUserDto());
			entity.setSkipModel(getSkipUrl(entity.getCode()));
			data.setEntity(entity);
			response.setData(data);

		} catch (ServiceException e) {
			return handleException(GetPromotionCouponByCodeResponse.class, e);
		}
		return  response;

	}


}
