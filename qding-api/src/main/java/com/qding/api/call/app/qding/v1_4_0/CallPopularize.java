package com.qding.api.call.app.qding.v1_4_0;


import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.PopularizeQuickMarkDto;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.request.GetPopularizeQuickMarkRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data.GetPopularizeQuickMarkResponseData;
import com.qding.api.call.app.qding.v1_4_0.struct.popularize.request.*;
import com.qding.api.call.app.qding.v1_4_0.struct.popularize.response.data.*;
import com.qding.api.struct.Response;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.popularize.remote.MemberPopularizeRemoteService;
import com.qding.popularize.struct.request.AddPopularizeAccountRequest;
import com.qding.popularize.struct.request.GetPopularizeAccountByMIdRequest;
import com.qding.popularize.struct.response.AddPopularizeAccountResponse;
import com.qding.popularize.struct.response.GetPopularizeAccountByMIdResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by jiawenzheng on 2015/7/28.
 */
public class CallPopularize extends  com.qding.api.call.app.qding.v1_3_2.CallPopularize {

	@Autowired
	private MemberPopularizeRemoteService irMemberPopularizeService;

	@Autowired
	private IProfileService profileAPI;

	/**
	 * 提交推广员申请
	 * @param request
	 * @return
	 */
    @HTTP(alias = "submitPopularizeApply")
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

}
