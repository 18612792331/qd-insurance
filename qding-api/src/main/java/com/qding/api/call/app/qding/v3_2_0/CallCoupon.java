package com.qding.api.call.app.qding.v3_2_0;

import com.google.common.collect.Lists;
import com.qding.api.call.app.qding.v3_2_0.struct.coupon.CouponDto;
import com.qding.promotion.common.dto.PromotionCouponUserDto;
import com.qding.promotion.common.struct.response.MineCouponUserResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v3_2_0.struct.coupon.request.GetCouponsBymidRequest;
import com.qding.api.call.app.qding.v3_2_0.struct.coupon.request.GetReportDataRequest;
import com.qding.api.call.app.qding.v3_2_0.struct.coupon.response.data.GetCouponsBymidResponseData;
import com.qding.api.call.app.qding.v3_2_0.struct.coupon.response.data.GetReportDataResponseData;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.GetAllCouponUserByMidRequest;
import com.qding.promotion.common.struct.response.CouponPageListResponse;

import java.util.List;

public class CallCoupon extends com.qding.api.call.app.qding.v1_4_0.CallCoupon {

	@Autowired
	private IPromotionRemoteService promotionRemoteService;
	

	@HTTP(alias="getReportData",isRequireAuth = true ,isNeadToken = true)
	@ExplainAnnotation (explain = "我的优惠券列表个状态数据统计",desc="返回未用多少，已用多少，过期多少")
	public Response<GetReportDataResponseData> getReportData(GetReportDataRequest request,UserToken userToken) {
		try {
			Response<GetReportDataResponseData> response = new Response<GetReportDataResponseData>();
			GetReportDataResponseData data=new GetReportDataResponseData();
			MineCouponUserResponse mineCouponUserResponse = promotionRemoteService.getMineCouponInfo(userToken.getMemberId());
			checkAndContinue(mineCouponUserResponse);
			data.setOverDueTotal(mineCouponUserResponse.getExpiredCouponNum());
			data.setUsedTotal(mineCouponUserResponse.getUsedCouponNum());
			data.setUnusedTotal(mineCouponUserResponse.getUnuseCouponNum());
			response.setData(data);
			return response;
		} catch (Exception e) {
			return handleException(GetReportDataResponseData.class, e);
		}
		
	}


	@HTTP(alias="getCouponsBymid",isNeadToken = true ,isRequireAuth = true)
	@ExplainAnnotation (explain = "获取指定用户 全部|已使用|未使用|已过期优惠券列表")
	public Response<GetCouponsBymidResponseData> getCouponsBymid(GetCouponsBymidRequest request, UserToken userToken) {

		try {
			Response<GetCouponsBymidResponseData> response = new Response<GetCouponsBymidResponseData>();
			GetAllCouponUserByMidRequest getAllCouponUserByMidRequest = transfor(GetAllCouponUserByMidRequest.class, request);
			getAllCouponUserByMidRequest.setMid(userToken.getMemberId());
			CouponPageListResponse getAllCouponUserByMidResponse = promotionRemoteService.getAllCouponUserByMidV2(getAllCouponUserByMidRequest);
			checkAndContinue(getAllCouponUserByMidResponse);
			List<PromotionCouponUserDto> promotionCouponUserDtoList = getAllCouponUserByMidResponse.getResultPage().getItems();
			List<CouponDto> commonCoupons =  fittingCouponListV2(promotionCouponUserDtoList);
			GetCouponsBymidResponseData data =  new GetCouponsBymidResponseData();
			Integer totalCount =  getAllCouponUserByMidResponse.getResultPage().getTotalCount();
			data.setCommonCoupons(commonCoupons);
			data.setTotalCount(totalCount);
			response.setData(data);

			return response;
		} catch (Exception e) {
			return handleException(GetCouponsBymidResponseData.class, e);
		}
	}

	/**
	 * 组装优惠券
	 * @param couponUserDtoList
	 * @return
     */
	public List<CouponDto> fittingCouponListV2(List<PromotionCouponUserDto> couponUserDtoList){

		List<CouponDto> list = Lists.newArrayList();
		if (CollectionUtils.isNotEmpty(couponUserDtoList)) {
			for (PromotionCouponUserDto promotionCouponUserDto : couponUserDtoList) {
				CouponDto coupon = transfor(CouponDto.class,promotionCouponUserDto);
				coupon.setSkipModel(getSkipUrl(coupon.getCode()));
				list.add(coupon);
			}
		}
		return  list;
	}

}
