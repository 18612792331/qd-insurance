package com.qding.api.call.app.qding.v1_3_2;


import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.*;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.request.GetPopularizeAccountByCodeRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.request.GetPopularizeQuickMarkRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.request.SubmitPopularizeApplyRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.request.UserInviteRegisterRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data.GetPopularizeQuickMarkResponseData;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data.SubmitPopularizeApplyResponseData;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data.UserInviteRegisterResponseData;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.request.*;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data.*;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.IntegralMessage;
import com.qding.api.ip.IPUtil;
import com.qding.api.sms.SmsAction;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.ArraysUtil;
import com.qding.api.verifycode.VerifyCode;
import com.qding.api.verifycode.VerifyCodeConfig;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.brick.pojo.biz.BankInfo;
import com.qding.brick.remote.biz.UtilRemote;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.passport.service.IPassportService;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberByAccountIdRequest;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.request.RegisterRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.passport.struct.response.RegisterResponse;
import com.qding.popularize.remote.MemberPopularizeRemoteService;
import com.qding.popularize.struct.bean.*;
import com.qding.popularize.struct.bean.PopularizeAccountBank;
import com.qding.popularize.struct.request.*;
import com.qding.popularize.struct.response.AddPopularizeAccountResponse;
import com.qding.popularize.struct.response.GetPopularizeAccountBankResponse;
import com.qding.popularize.struct.response.GetPopularizeAccountByCodeResponse;
import com.qding.popularize.struct.response.GetPopularizeAccountByMIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jiawenzheng on 2015/7/28.
 */
public class CallPopularize extends Callable {


	@Autowired
	private IPassportService passportAPI;

	@Autowired
	private IProfileService profileAPI;

	@Autowired
	private MemberPopularizeRemoteService irMemberPopularizeService;

	/**
	 * 推广员注册
	 * @param request
	 * @return
	 */
    @HTTP(alias="register")
    public Response<UserInviteRegisterResponseData> register (UserInviteRegisterRequest request, HttpServletRequest httpServletRequest) {

        Response<UserInviteRegisterResponseData> response = new Response<UserInviteRegisterResponseData>();

		try {

			String code = request.getVerifyCode();

			VerifyCode.verifyCode(
					new VerifyCodeConfig(
							SmsAction.REGISTER,
							code,
							request.getMobile(),
							new RedisStoreDevice()
					)
			);

			String ip = IPUtil.getIpAddress(httpServletRequest);
			RegisterRequest registerRequest = transfor(RegisterRequest.class, request);
			registerRequest.setRegIp(QDStringUtil.isNotEmpty(ip)?ip:"none");
			registerRequest.setInviteCode(registerRequest.getInviteCode());
			RegisterResponse rpcResponse = passportAPI.register(registerRequest);
			checkAndContinue(rpcResponse);
			response.setData(new UserInviteRegisterResponseData());

		/*	if (HttpStatus.OK.getStatusCode()==rpcResponse.getReturnInfo().getCode()) {

				GetMemberByAccountIdRequest memberByAccountIdRequest = new GetMemberByAccountIdRequest();
				memberByAccountIdRequest.setAccountId(rpcResponse.getAccountId());
				GetMemberResponse memberResponse = profileAPI.getMemberByAccountId(memberByAccountIdRequest);

				PopularizeRegisterRequest popularizeRegisterRequest = new PopularizeRegisterRequest();
				popularizeRegisterRequest.setInviteCode(request.getInviteCode());
				popularizeRegisterRequest.setInvitedMemberId(memberResponse.getMemberInfo().getId());
				BaseResponse baseResponse = irMemberPopularizeService.register(popularizeRegisterRequest);


			}*/

		} catch (Exception e) {

			return handleException(UserInviteRegisterResponseData.class, e);
		}

		return response;
    }


	/**
	 * 扫描二维码，通过状态返回相应跳转链接
	 * @return
	 */
	@HTTP(alias="scanPopularizeQuickMark")
	public void scanPopularizeQuickMark(GetPopularizeAccountByCodeRequest request,HttpServletResponse response){

		try{

			GetPopularizeAccountByCodeResponse popularizeAccountByCodeResponse = irMemberPopularizeService.getPopularizeAccountByCode(transfor(com.qding.popularize.struct.request.GetPopularizeAccountByCodeRequest.class,request));
			Integer status = popularizeAccountByCodeResponse.getAccount().getStatus();
			String url = APIPropertiesClient.getPopularizeUrl(status,request.getCode());
			response.sendRedirect(url);
		}catch (Exception ex) {

		}
		
	}

    @Autowired
    private UtilRemote utilRemote;

	/**
	 * 获取银行列表，可通过银行名称关键字查询
	 * @param request
	 * @return
	 */
    @HTTP(alias = "getBankList")
    public Response<GetBankNameByKeyWordResponseData> getBankNameByKeyWord (GetBankNameByKeyWordRequest request){

        Response<GetBankNameByKeyWordResponseData> response = new  Response<GetBankNameByKeyWordResponseData>();

        GetBankNameByKeyWordResponseData  bankNameByKeyWordResponse = new GetBankNameByKeyWordResponseData();

        List<BankInfo> bankInfoList = utilRemote.getBankList("");

		List<Entry<String, List<BankInfo>>> groupByCodeAsList = ArraysUtil.mergerListsAsList(bankInfoList, "letter");

		List<BankSpellDto> list = new ArrayList<>();

		for(Entry<String, List<BankInfo>> entry : groupByCodeAsList) {

			List<BankDto> bankList = transforList(BankDto.class, entry.getValue());

			String py = entry.getKey();

			list.add(new BankSpellDto(py, bankList));

		}

        bankNameByKeyWordResponse.setList(list);
        bankNameByKeyWordResponse.setTotalCount(bankInfoList.size());
        response.setData(bankNameByKeyWordResponse);
        response.setCode(HttpStatus.OK.getStatusCode());

        return response;

    }


	/**
	 * 提交推广员申请
	 * @param request
	 * @return
	 */
    @HTTP(alias = "submitPopularizeApply")
	@Deprecated
    public Response<SubmitPopularizeApplyResponseData> submitPopularizeApplyInfo (SubmitPopularizeApplyRequest request) {

		try{
			Response<SubmitPopularizeApplyResponseData> response = new Response<SubmitPopularizeApplyResponseData>();
			SubmitPopularizeApplyResponseData data = new SubmitPopularizeApplyResponseData();
			BaseResponse addPopularizeAccountResponse = irMemberPopularizeService.addPopularizeAccount(transfor(AddPopularizeAccountRequest.class, request));
			checkAndContinue(addPopularizeAccountResponse);
			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());
			return response;
		}catch (Exception e){
			return handleException(SubmitPopularizeApplyResponseData.class, e);
		}

    }

    /**
	 * 获取推广员申请状态
     * @param request
     * @return
     */
    @HTTP(alias = "getPopularizeApplyStatus")
    public Response<GetPopularizeApplyStatusResponseData> getPopularizeApplyStatus (GetPopularizeApplyStatusRequest request) {

    	try {

			Response<GetPopularizeApplyStatusResponseData> response = new Response<GetPopularizeApplyStatusResponseData>();

			GetPopularizeAccountByMIdResponse popularizeAccountByMIdResponse = irMemberPopularizeService.getPopularizeAccountByMId(transfor(GetPopularizeAccountByMIdRequest.class, request));
			if(400==popularizeAccountByMIdResponse.getReturnInfo().getCode()){
				GetPopularizeApplyStatusResponseData data = new GetPopularizeApplyStatusResponseData();
				PopularizeApplyStatusDto entity = new PopularizeApplyStatusDto(Constant.POPULARIZE_NO_APPLY);
				data.setEntity(entity);
				response.setData(data);
				response.setCode(HttpStatus.OK.getStatusCode());

			} else {
				checkAndContinue(popularizeAccountByMIdResponse);
				if(HttpStatus.OK.getStatusCode()==popularizeAccountByMIdResponse.getReturnInfo().getCode()){
					Integer status = popularizeAccountByMIdResponse.getAccount().getStatus();
					GetPopularizeApplyStatusResponseData data = new GetPopularizeApplyStatusResponseData();
					PopularizeApplyStatusDto entity = new PopularizeApplyStatusDto(status);
					data.setEntity(entity);
					response.setData(data);
					response.setCode(HttpStatus.OK.getStatusCode());
				} else {
					response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
				}
			}


			return response;

		}catch (Exception e) {
			return handleException(GetPopularizeApplyStatusResponseData.class, e);

		}
    }

	/**
	 *获取推广二维码
	 * @param request
	 * @return
	 */
    @HTTP(alias = "getPopularizeQuickMark")
    public Response<GetPopularizeQuickMarkResponseData> getPopularizeQuickMark (GetPopularizeQuickMarkRequest request) {

		try {

			Response<GetPopularizeQuickMarkResponseData>  response = new Response<GetPopularizeQuickMarkResponseData> ();
			GetPopularizeQuickMarkResponseData data = new GetPopularizeQuickMarkResponseData();
			GetPopularizeAccountByMIdResponse popularizeAccountByMIdResponse =irMemberPopularizeService.getPopularizeAccountByMId(transfor(GetPopularizeAccountByMIdRequest.class, request));
			if(HttpStatus.OK.getStatusCode()== popularizeAccountByMIdResponse.getReturnInfo().getCode() ){

				PopularizeAccount account = popularizeAccountByMIdResponse.getAccount();
				String qrCodeUrl = account.getQrCodeUrl();
				Integer status = account.getStatus();
				String skipUrl = APIPropertiesClient.getPopularizeUrl(status,account.getCode());

				PopularizeQuickMarkDto entity = new PopularizeQuickMarkDto (qrCodeUrl,skipUrl);
				data.setEntity(entity);
				response.setData(data);
				response.setCode(HttpStatus.OK.getStatusCode());
			} else {
				response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
			}

			return response;

		}catch (Exception e) {
			return handleException(GetPopularizeQuickMarkResponseData.class, e);

		}
    }

	/**
	 * 获取推广二维码信息
	 * @param request
	 * @return
	 */
    @HTTP(alias = "getPopularizeQuickMarkInfo")
    public Response<GetPopularizeQuickMarkInfoResponseData> getPopularizeQuickMarkInfo (GetPopularizeQuickMarkInfoRequest request) {

    	try {

			Response<GetPopularizeQuickMarkInfoResponseData> response = new Response<GetPopularizeQuickMarkInfoResponseData>();
			GetPopularizeAccountByMIdResponse popularizeAccountByMIdResponse = irMemberPopularizeService.getPopularizeAccountByMId(transfor(GetPopularizeAccountByMIdRequest.class, request));
			if(HttpStatus.OK.getStatusCode()==popularizeAccountByMIdResponse.getReturnInfo().getCode()){

				GetPopularizeQuickMarkInfoResponseData data =transfor(GetPopularizeQuickMarkInfoResponseData.class,popularizeAccountByMIdResponse);
				String identityCard = data.getEntity().getIdentityCard();
				if (identityCard.length()==18){
					String replactStr = identityCard.substring(6,14);
					identityCard = identityCard.replace(replactStr,"********");
					data.getEntity().setIdentityCard(identityCard);
				}
				GetPopularizeAccountBankRequest popularizeAccountBankRequest = new GetPopularizeAccountBankRequest();
				popularizeAccountBankRequest.setPopularizeAccountId(popularizeAccountByMIdResponse.getAccount().getId());
				GetPopularizeAccountBankResponse popularizeAccountBankResponse = irMemberPopularizeService.getPopularizeAccountBanks(popularizeAccountBankRequest);
				//获取所有银行信息  目前只取第一条
				List<PopularizeAccountBank> popularizeAccountBank =popularizeAccountBankResponse.getBanks();
				String bankCardNo = popularizeAccountBank.get(0).getBankCardNo();

				if(bankCardNo.length()==16 ||bankCardNo.length()==19 ){
					String replactStr = bankCardNo.substring(bankCardNo.length()-4,bankCardNo.length());
					bankCardNo = bankCardNo.replace(replactStr,"*********");
				}
				data.getEntity().setBankCardNum(bankCardNo);
				data.getEntity().setBankBranch(QDStringUtil.isNotNull(popularizeAccountBank.get(0).getBankBranch())?popularizeAccountBank.get(0).getBankBranch():"");

				PopularizeStatistics popularizeStatistics = new PopularizeStatistics();
				popularizeStatistics.setPopularizeOrderCount(10);
				popularizeStatistics.setPopularizeRefundCount(10);
				popularizeStatistics.setPopularizeUserCount(10);
				data.setStatistics(popularizeStatistics);

				response.setData(data);
				response.setCode(HttpStatus.OK.getStatusCode());
			}else {
				response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
			}

			return response;
		}catch (Exception e) {
			return handleException(GetPopularizeQuickMarkInfoResponseData.class, e);

		}
    }

	/**
	 * 获取推广员申请信息
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getPopularizeApplyInfo")
	public Response<GetPopularizeApplyInfoResponseData> getPopularizeUserInfoResponseInfo (GetPopularizeApplyInfoResponseRequest request) {

		Response<GetPopularizeApplyInfoResponseData> response = new Response<GetPopularizeApplyInfoResponseData>();

		GetPopularizeAccountBankRequest popularizeAccountBankRequest = new GetPopularizeAccountBankRequest();
		GetPopularizeAccountByMIdResponse popularizeAccountByMIdResponse = irMemberPopularizeService.getPopularizeAccountByMId(transfor(GetPopularizeAccountByMIdRequest.class, request));

		if(HttpStatus.OK.getStatusCode()==popularizeAccountByMIdResponse.getReturnInfo().getCode()){

			popularizeAccountBankRequest.setPopularizeAccountId(popularizeAccountByMIdResponse.getAccount().getId());
			GetPopularizeAccountBankResponse popularizeAccountBankResponse = irMemberPopularizeService.getPopularizeAccountBanks(popularizeAccountBankRequest);

			//获取所有银行信息  目前只取第一条
			List<PopularizeAccountBank> popularizeAccountBank =popularizeAccountBankResponse.getBanks();
			BankInfo bankInfo = utilRemote.getBank(Long.parseLong(popularizeAccountBank.get(0).getBankId()));
			PopularizeAccount popularizeAccount = popularizeAccountByMIdResponse.getAccount();
			GetPopularizeApplyInfoResponseData data = new GetPopularizeApplyInfoResponseData();
			PopularizeUserApplyDto apply = new PopularizeUserApplyDto();
			String identityCard = popularizeAccount.getIdNumber();
			if (identityCard.length()==18){
				String replactStr = identityCard.substring(6,14);
				identityCard = identityCard.replace(replactStr,"********");
			}
			apply.setIdentityCard(identityCard);
			apply.setMemberId(popularizeAccount.getMemberId());
			apply.setUserName(popularizeAccount.getName());
			String bankCardNo = popularizeAccountBank.get(0).getBankCardNo();
			if(bankCardNo.length()==16 ||bankCardNo.length()==19 ){
				String replactStr = bankCardNo.substring(bankCardNo.length()-4,bankCardNo.length());
				bankCardNo = bankCardNo.replace(replactStr,"****");
			}
			apply.setBankCardNum(bankCardNo);
			apply.setBankName(bankInfo.getName());
			apply.setBankId(Long.parseLong(popularizeAccountBank.get(0).getBankId()));
			apply.setBankBranch(QDStringUtil.isNotNull(popularizeAccountBank.get(0).getBankBranch())?popularizeAccountBank.get(0).getBankBranch():"");
			data.setEntity(apply);

			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());
		} else {
			response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());

		}

		return response;
	}


	/**
	 * 获取统计信息
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getPopularizeStatistics")
	public Response<GetPopularizeStatisticsResponseData>  getPopularizeStatistics (GetPopularizeStatisticsRequest request){

		Response<GetPopularizeStatisticsResponseData>  response = new Response<GetPopularizeStatisticsResponseData>();

		response.setCode(HttpStatus.OK.getStatusCode());

		GetPopularizeStatisticsResponseData data = new GetPopularizeStatisticsResponseData();

		List<StatisticsItemsBean> list = Lists.newArrayList();

		StatisticsItemsBean statisticsItemsBean = new StatisticsItemsBean();

		statisticsItemsBean.setDate("2015-09-10");

		statisticsItemsBean.setCount(10);

		list.add(statisticsItemsBean);

		data.setList(list);

		data.setTotalCount(10);

		response.setData(data);

		return  response;
	}

	/**
	 * 每日推广订单统计（业态）
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getPopularizeStatisticsByBusiness")
	public Response<GetPopularizeStatisticsForBusinessResponseData> getPopularizeStatisticsByBusiness(GetPopularizeStatisticsForBusinessRequest request){

		Response<GetPopularizeStatisticsForBusinessResponseData> response = new Response<GetPopularizeStatisticsForBusinessResponseData>();

		response.setCode(HttpStatus.OK.getStatusCode());

		GetPopularizeStatisticsForBusinessResponseData data = new GetPopularizeStatisticsForBusinessResponseData();

		List<StatisticsItemsForBusiness> list = Lists.newArrayList();

		StatisticsItemsForBusiness statisticsItemsForBusiness = new StatisticsItemsForBusiness();

		statisticsItemsForBusiness.setBusinessName("乐购");

		statisticsItemsForBusiness.setCount(25);

		list.add(statisticsItemsForBusiness);

		data.setList(list);

		data.setTotalCount(1);

		response.setData(data);

		return response;
	}

}
