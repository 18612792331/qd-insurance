package com.qding.api.call.app.qding.v1_4_0;


import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Dictonary;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.AccountCollect;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.AccountWalletHome;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.GiftBean;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.request.AccountWalletHomePageRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.request.GetIntegralCountRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data.AccountWalletHomePageResonseData;
import com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data.GetIntegralCountResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.user.GiftInfoDTO;
import com.qding.api.call.service.MemberGiftService;
import com.qding.api.call.service.MemberService;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberRoom;
import com.qding.passport.struct.MemberInfo;
import com.qding.promotion.common.dto.PromotionCouponUserDto;
import com.qding.promotion.common.params.PromotionGiftPackageParams;
import com.qding.promotion.common.service.IPromotionGiftPackageRemoteService;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.GetUserCouponListRequest;
import com.qding.promotion.common.struct.request.PromotionGiftPackageRequest;
import com.qding.promotion.common.struct.response.CouponListResponse;
import com.qding.promotion.common.struct.response.CouponPageListResponse;
import com.qding.promotion.common.struct.response.PromotionGiftPackageResponse;
import com.qding.thrift.model.dictionary.Dictionary;
import com.qding.useraccount.service.IUserIntegralRemoteService;
import com.qding.useraccount.service.IUserPredepositRemoteService;
import com.qding.useraccount.struct.request.IntegralInfoRequest;
import com.qding.useraccount.struct.request.PredepositInfoRequest;
import com.qding.useraccount.struct.response.IntegralInfoResponse;
import com.qding.useraccount.struct.response.PredepositInfoResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.qding.message.xinge.org.json.XMLTokener.entity;

/**
 * 我的钱包 1.4.0
 * @author JIAWENZHENG
 *
 */
public class CallWallet extends com.qding.api.call.app.qding.v1_3_1.CallWallet{

	@Autowired
	private IPromotionRemoteService promotionRemoteService;

	@Autowired
	private IUserPredepositRemoteService userPredepositAPI;

	@Autowired
	private IUserIntegralRemoteService userIntegralRemoteService;

	@Autowired
	private MemberGiftService memberGiftService;

	@Autowired
	private SkipModeFitting skipMode;

	@Autowired
	private IPromotionGiftPackageRemoteService promotionGiftPackageRemoteService;

	@Autowired
	private MemberService memberService;



	/**
	 * 获取指定用户剩余积分总数
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getIntegralCount", isRequireAuth=true)
	public Response<GetIntegralCountResponseData> getIntegralCount (GetIntegralCountRequest request) {

		Response<GetIntegralCountResponseData> response = new Response<GetIntegralCountResponseData>();
		GetIntegralCountResponseData data = new GetIntegralCountResponseData();
		response.setCode(HttpStatus.OK.getStatusCode());

		try {
			IntegralInfoRequest integralInfoRequest = new IntegralInfoRequest();
			integralInfoRequest.setMid(request.getMemberId());
			IntegralInfoResponse integralInfoResponse = userIntegralRemoteService.get(integralInfoRequest);
			checkAndContinue(integralInfoResponse);
			BigDecimal accountIntegral =  integralInfoResponse.getAvailableNum();
			data.setAccountIntegral(accountIntegral.longValue());
		} catch (ServiceException e) {
			return handleException(GetIntegralCountResponseData.class, e);
		}
		response.setData(data);

		return response;
	}


	/**
	 * 获取我得钱包首页信息
	 * @param request
	 * @return
	 */
	@HTTP(alias = "accountWalletHomePage", isRequireAuth=true,isNeadToken = true)
	public Response<AccountWalletHomePageResonseData> accountWalletHomePage(AccountWalletHomePageRequest request, UserToken userToken) {

		try {

			Response<AccountWalletHomePageResonseData> response = new Response<AccountWalletHomePageResonseData>();

			PredepositInfoRequest infoRequest = new PredepositInfoRequest();

			infoRequest.setMid(request.getMemberId());

			PredepositInfoResponse predepositInfoResponse = userPredepositAPI.get(infoRequest);

			checkAndContinue(predepositInfoResponse);

			AccountCollect account = transfor(AccountCollect.class, predepositInfoResponse);

			AccountWalletHomePageResonseData data = new AccountWalletHomePageResonseData();

			AccountWalletHome entity = new AccountWalletHome();

			entity.setAccount(account);

			entity.setAccountQdTicket(this.GetCouponMoneyForUser(request.getMemberId()));

			entity.setMemberId(request.getMemberId());
			IntegralInfoRequest integralInfoRequest = new IntegralInfoRequest();
			integralInfoRequest.setMid(request.getMemberId());

			IntegralInfoResponse integralInfoResponse = userIntegralRemoteService.get(integralInfoRequest);

			checkAndContinue(integralInfoResponse);

			BigDecimal accountIntegral =  integralInfoResponse.getAvailableNum();

			entity.setAccountIntegral(accountIntegral.longValue());

			entity.setWalletStatus(getWalletStatus(request.getMemberId()));

			List<Dictonary>  dictonaryList = getDictionary("my_wallet_sogan");

			if (QDStringUtil.isNotNull(dictonaryList) && dictonaryList.size()>0) {

				Dictonary dictonary = dictonaryList.get(0);

				String soganStr = dictonary.getValue();

				if (QDStringUtil.isNotNull(soganStr) && QDStringUtil.isNotEmpty(soganStr)) {

					String[] soganStrArray = soganStr.split("\\|\\|");

					String[] slogan ={QDStringUtil.isNotNull(soganStrArray)?soganStrArray[0]:""};

					entity.setSlogan(slogan);

					if (soganStrArray.length>1) {

						String sloganDetail = soganStrArray[1];

						entity.setSloganDetail(sloganDetail);
					}
				}
			}

			//获取我的 会员中心跳转和提醒信息
			String memberId = userToken.getMemberId();
			String projectId = request.getAppUser().getProjectId();

			String roomId  = "";
			List<MemberRoom>  rooms = memberService.getRoomsByMultiRole(memberId, projectId, Constant.GIFT_ROLE_LIST,true);//可领取礼包房屋ID （当前社区，当前人，拥有业主|亲人|租客身份的最早绑定的房屋）
			if (CollectionUtils.isNotEmpty(rooms)) {
				roomId = rooms.get(0).getRoomId();
			}

			// 礼包信息
			PromotionGiftPackageRequest promotionGiftPackageRequest = new PromotionGiftPackageRequest();
			promotionGiftPackageRequest.setProjectId(String.valueOf(request.getAppUser().getProjectId()));
			promotionGiftPackageRequest.setRoomId(roomId);
			promotionGiftPackageRequest.setMid(memberId);
			PromotionGiftPackageResponse promotionGiftPackageResponse = promotionGiftPackageRemoteService.getGiftPackageInfoCommon(promotionGiftPackageRequest);
			checkAndContinue(promotionGiftPackageResponse);

			// 用户信息
			MemberInfo memberInfo = memberService.getMemberByMid(memberId);
			checkEntity(memberInfo);

			List<GiftInfoDTO> unclaimedlist = Lists.newArrayList();

			//该社区下礼包详情
			List<PromotionGiftPackageParams> giftPackageParamsList = promotionGiftPackageResponse.getGiftPackageParamsList();
			if (CollectionUtils.isNotEmpty(giftPackageParamsList)) { //如果当前社区下有礼包
				//获取指定房间下未领取的礼包
				unclaimedlist = memberGiftService.getUnclaimedGiftPackageByProjectId(roomId, memberInfo, promotionGiftPackageResponse);
			}

			GiftBean giftBean = new GiftBean();
			if ( CollectionUtils.isNotEmpty(unclaimedlist)) { //有未领取的实物礼包或券礼包
				giftBean.setShowFlag(1);
			}

			String url = APIPropertiesClient.getValue("member_center_url");
			giftBean.setSkipModel(skipMode.fittingSkipUrl(request.getAppDevice().getQdVersion(),url,0,""));
			entity.setGiftEntity(giftBean);
			data.setEntity(entity);
			response.setData(data);
			return response;
		} catch (Exception e) {
			return handleException(AccountWalletHomePageResonseData.class, e);
		}
	}


	/**
	 * 千丁劵余额计算
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	private int GetCouponMoneyForUser(String memberId) throws Exception{

		GetUserCouponListRequest request = new GetUserCouponListRequest();

		request.setMid(memberId);

		CouponPageListResponse  getUserCouponListResponse = promotionRemoteService.getUserCouponListV2(request);

		checkAndContinue(getUserCouponListResponse);

		//计算所有优惠券的总价值
		Integer totalCouponsPrice = 0;

		Iterator<PromotionCouponUserDto> commonIterator = getUserCouponListResponse.getResultPage().getItems().iterator();

		while(commonIterator.hasNext()) {

			PromotionCouponUserDto next = commonIterator.next();

			totalCouponsPrice += next.getPrice();
		}

		return totalCouponsPrice;
	}

}
