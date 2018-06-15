package com.qding.api.call.app.qding.v1_3_2;

import java.util.List;

import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.UserInfo;
import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.call.app.qding.v1_3_2.struct.im.BlackUserDto;
import com.qding.api.call.app.qding.v1_3_2.struct.im.request.AddBlackUserRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.im.request.GetBlackUserRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.im.request.GetImUserTokenRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.im.request.UnBlackUserRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.im.response.data.AddBlackUserResponse;
import com.qding.api.call.app.qding.v1_3_2.struct.im.response.data.GetBlackUserResponse;
import com.qding.api.call.app.qding.v1_3_2.struct.im.response.data.GetImUserTokenResponseData;
import com.qding.api.call.app.qding.v1_3_2.struct.im.response.data.UnBlackUserResponse;
import com.qding.api.constant.Constant;
import com.qding.api.rongcloud.RongCloudApiHttpClient;
import com.qding.api.rongcloud.model.response.UserBlackList;
import com.qding.api.rongcloud.model.response.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.ImageUtil;
import com.qding.brick.pojo.contract.Supplier;
import com.qding.brick.remote.contract.SupplierRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.manager.common.ModelResult;
import com.qding.manager.domain.Puser;
import com.qding.manager.service.IPuserRPCService;
import com.qding.passport.service.IPassportService;
import com.qding.passport.struct.AccountInfo;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetAccountRequest;
import com.qding.passport.struct.request.SaveImUserTokenRequest;
import com.qding.passport.struct.response.GetAccountResponse;
import com.qding.passport.struct.response.GetImUserTokenResponse;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jiawenzheng on 2015/7/27.
 */
public class CallIM extends Callable {


    @Autowired
    private IPassportService passportAPI;

	@Autowired
	private ImageUtil imageUtil;

	@Autowired
	private IPuserRPCService puserRPCService;

	/**
	 * 获取融云用户token
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getImUserToken")
    public Response<GetImUserTokenResponseData> getImUserToken (GetImUserTokenRequest request) {

        Response<GetImUserTokenResponseData> response = new Response<GetImUserTokenResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetImUserTokenResponseData data = new GetImUserTokenResponseData();
        try {
			com.qding.passport.struct.request.GetImUserTokenRequest getImUserTokenRequest = new com.qding.passport.struct.request.GetImUserTokenRequest();
			getImUserTokenRequest.setAccountId(request.getUserId());
			GetImUserTokenResponse imUserTokenResponse = passportAPI.getImUserToken(getImUserTokenRequest);
			
			if(HttpStatus.NO_CONTENT.getStatusCode()==imUserTokenResponse.getReturnInfo().getCode()){
				
				UserToken userToken = RongCloudApiHttpClient.getToken(request.getUserId(), null, null);
				
				if(QDStringUtil.isNotNull(userToken) && 200==userToken.getCode()){
					
					String tokenStr= userToken.getToken();
					data.setToken(tokenStr);
					
					SaveImUserTokenRequest saveImUserTokenRequest = new SaveImUserTokenRequest();
					saveImUserTokenRequest.setAccountId(request.getUserId());
					saveImUserTokenRequest.setRongcloudToken(tokenStr);
					passportAPI.saveImUserToken(saveImUserTokenRequest);
					
				} else {
					response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
					data.setMessage("服务错误没有获取到用户令牌");
				}
				
			} else {
				checkAndContinue(imUserTokenResponse);
				data = new GetImUserTokenResponseData();
				data.setToken(imUserTokenResponse.getRongcloudToken());
			} 
			
			response.setData(data);
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetImUserTokenResponseData.class, e);
		}

    }

	/**
	 * 融云聊天加入黑名单
	 * @param request
	 * @return
	 */
	@HTTP(alias = "addBlackUser")
    public Response<AddBlackUserResponse> addBlackUser(AddBlackUserRequest request) {
    	
    	 Response<AddBlackUserResponse> response = new  Response<AddBlackUserResponse>();
    	 AddBlackUserResponse data = new AddBlackUserResponse();
		try {
			
	    	 response.setCode(HttpStatus.OK.getStatusCode());
	    	 data.setMessage("黑名单添加成功");
	    	 
	    	String userId = request.getUserId();
	    	List<String> blackUserIds = request.getBlackUserIds();
	    	
	    	boolean flag = RongCloudApiHttpClient.blackUser(userId, blackUserIds);
			if(!flag) {
	    		 response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
	        	 data.setMessage("黑名单添加失败");
	    	} 
			 response.setData(data);
	    	 return response;
	    	
		} catch (Exception e) {
			 response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
        	 data.setMessage("服务异常黑名单添加失败");
        	 response.setData(data);
	    	 return response;
		}
    
    }

	/**
	 * 融云聊天解除黑名单
	 * @param request
	 * @return
	 */
	@HTTP(alias = "unblackUser")
    public Response<UnBlackUserResponse> unblackUser(UnBlackUserRequest request) {
    	
   	 Response<UnBlackUserResponse> response = new  Response<UnBlackUserResponse>();
        UnBlackUserResponse data = new UnBlackUserResponse();
		try {
			
	    	 response.setCode(HttpStatus.OK.getStatusCode());
	    	 data.setMessage("黑名单去除成功");
	    	 
	    	String userId = request.getUserId();
	    	List<String> blackUserIds = request.getBlackUserIds();
	    	
	    	boolean flag = RongCloudApiHttpClient.unblackUser(userId, blackUserIds);
			if(!flag) {
	    		 response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
	        	 data.setMessage("黑名单去除失败");
	    	} 
			 response.setData(data);
	    	 return response;
	    	
		} catch (Exception e) {
			 response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
	       	 data.setMessage("服务异常黑名单去除失败");
	       	 response.setData(data);
	    	 return response;
		}
   
   }

	/**
	 * 融云聊天获取黑名单
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getBlackUser")
    public Response<GetBlackUserResponse> getBlackUser(GetBlackUserRequest request) {
    	
    	String accountId= request.getUserId();
    	Response<GetBlackUserResponse>  response = new Response<GetBlackUserResponse> ();
    	GetBlackUserResponse data = new GetBlackUserResponse();
    	
    	try {
			UserBlackList userblack = RongCloudApiHttpClient.QueryblackUser(accountId);
			
			if (QDStringUtil.isNotNull(userblack)) {
				 String[] userblacks= userblack.getUsers();
				GetAccountRequest accountRequest = new GetAccountRequest();
				List<BlackUserDto> blackUserDtoList = Lists.newArrayList();
				 
				 for(String accountIdTmp :userblacks){
					 try {
						 if (accountIdTmp.startsWith(Constant.SUPPER_PREFIX)) {
							 String puserId= accountIdTmp.replace(Constant.SUPPER_PREFIX,"");
							 ModelResult modelResult = puserRPCService.getPuserInfoByPuserId(puserId);
							 if (HttpStatus.OK.getStatusCode() == modelResult.getCode()) {
								 Puser puser = (Puser) modelResult.getEntity();
								 BlackUserDto blackUserDto = new BlackUserDto(accountIdTmp,imageUtil.get(Constant.DEFAULT_SUPPLIER_HEAD_IMG,150,150) ,puser.getName());
								 blackUserDtoList.add(blackUserDto);
							 }

						 } else {
							 accountRequest.setAccountId(accountIdTmp);
							 GetAccountResponse accountResponse = passportAPI.getAccountByAccountId(accountRequest);
							 AccountInfo user = accountResponse.getAccountInfo();
							 MemberInfo memberInfo = accountResponse.getMemberInfo();
							 String name;
							 String headImg;
							 //优先获取member的数据
							 if(memberInfo != null) {
								 name = memberInfo.getName();
								 headImg = memberInfo.getHeadImg();
							 }else {//member为空就获取account信息
								 name = user.getNickName();
								 headImg = user.getHeadImg();
							 }
							 String img = imageUtil.get(headImg,150,150);
							 BlackUserDto blackUserDto = new BlackUserDto(accountIdTmp,img,name);
							 blackUserDtoList.add(blackUserDto);
						 }

					 } catch (Exception ex) {
						 ex.printStackTrace();
					 }

				 }

				 data.setUsers(blackUserDtoList);
				 response.setCode(HttpStatus.OK.getStatusCode());
			} else {
				response.setCode(HttpStatus.OK.getStatusCode());
				data.setMessage("没有黑名单数据");
			}
			
		} catch (Exception e) {
			response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
			data.setMessage("查询异常");
			
		} 
    		response.setData(data);
			return response;
    	
    }


}
