package com.qding.api.call.app.qding.v3_0_0;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.PopularizeQuickMarkDto;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.request.GetPopularizeQuickMarkRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data.GetPopularizeQuickMarkResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.popularize.request.PopularizeBindMemberRequest;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.CreateShortUrlUtil;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.popularize.remote.MemberPopularizeRemoteService;
import com.qding.popularize.struct.bean.PopularizeAccount;
import com.qding.popularize.struct.request.GetPopularizeAccountByCodeRequest;
import com.qding.popularize.struct.request.GetPopularizeAccountByMIdRequest;
import com.qding.popularize.struct.request.PopularizeRegisterRequest;
import com.qding.popularize.struct.response.GetPopularizeAccountByCodeResponse;
import com.qding.popularize.struct.response.GetPopularizeAccountByMIdResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by qd on 2017/3/25.
 */
@ExplainAnnotation(explain = "推广员")
public class CallPopularize extends  com.qding.api.call.app.qding.v1_4_0.CallPopularize {

    @Autowired
    private MemberPopularizeRemoteService irMemberPopularizeService;


    @Autowired
    private IProfileService profileAPI;


    /**
     *获取推广码短链接
     * @param request
     * @return
     */
    @HTTP(alias = "getPopluarizeQRCode", isRequireAuth = true ,isNeadToken = true)
    public Response<GetPopularizeQuickMarkResponseData> getPopluarizeQRCode (GetPopularizeQuickMarkRequest request,UserToken token) {

        try {

            Response<GetPopularizeQuickMarkResponseData>  response = new Response<GetPopularizeQuickMarkResponseData> ();
            GetPopularizeQuickMarkResponseData data = new GetPopularizeQuickMarkResponseData();
            GetPopularizeAccountByMIdRequest req = new GetPopularizeAccountByMIdRequest();
            req.setMemberId(token.getMemberId());
            GetPopularizeAccountByMIdResponse popularizeAccountByMIdResponse =irMemberPopularizeService.getPopularizeAccountByMId(req);
            if(HttpStatus.OK.getStatusCode()== popularizeAccountByMIdResponse.getReturnInfo().getCode() ){
                //irMemberPopularizeService.agreement(req);
                PopularizeAccount account = popularizeAccountByMIdResponse.getAccount();
                Integer status = account.getStatus();
                if (Constant.POPULARIZE_PASS_AUDIT==status){
                    String skipUrlTranslator = APIPropertiesClient.getSkipUrl("translator");
                    String longUrl = skipUrlTranslator + "?skip={'entity':{'skno':7000,'url':'"+APIPropertiesClient.getValue("promoter_invite")+account.getCode()+"','pcode':1}}";
                    String shortUrl = CreateShortUrlUtil.ServiceUrlConvertShortUrl(longUrl,null);
                    if(shortUrl.equals("400")){
                        response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                    }else {
                        PopularizeQuickMarkDto entity = new PopularizeQuickMarkDto(shortUrl, null);
                        data.setEntity(entity);
                        response.setData(data);
                        response.setCode(HttpStatus.OK.getStatusCode());
                    }
                }else if(Constant.POPULARIZE_WAIT_AUDIT==status){
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "您的推广员资格正在审核中");
                }else if(Constant.POPULARIZE_NOT_PASS_AUDIT==status){
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "您的推广员资格没有审核通过");
                }else if(Constant.POPULARIZE_FREEZE_AUDIT==status){
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "您的推广员资格已冻结");
                }

            } else {
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            }

            return response;

        }catch (Exception e) {
            return handleException(GetPopularizeQuickMarkResponseData.class, e);
        }
    }


    /**
     * 推广员与用户绑定
     * @param request
     * @return
     */
    @HTTP(alias="bindMember", isRequireAuth = true ,isNeadToken = true)
    public Response<ResponseData> bindMember (PopularizeBindMemberRequest request,UserToken userToken) {

        Response<ResponseData> response = new Response<ResponseData>();

        try {
            GetMemberRequest getMemberRequest = new GetMemberRequest();
            getMemberRequest.setMemberId(userToken.getMemberId());
            GetMemberResponse getMemberResponse = profileAPI.getMemberById(getMemberRequest);
            if(getMemberResponse==null || getMemberResponse.getMemberInfo()==null){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "没有获取到用户信息");
            }
            //验证用户注册天数
            String day  = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_LAST_REG_DAY.getGroupName(),Constant.Dict_K_V_Enum.DICT_LAST_REG_DAY.getDictKey());
            if(QDStringUtil.isNotEmpty(day)){
                int dayNum = Integer.parseInt(day);
                long lastRegTime = System.currentTimeMillis()-(dayNum*(3600*1000*24));
                long regTime = getMemberResponse.getMemberInfo().getCreateTime();
                if(lastRegTime>regTime){
                    //如果15天之前注册的，不允许绑定推广
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "注册后"+dayNum+"日内可扫描推广码；您当前已超过有效期，无法关联推广员");
                }
            }
            PopularizeRegisterRequest popularizeRegisterRequest = new PopularizeRegisterRequest();
            popularizeRegisterRequest.setInviteCode(request.getInviteCode());
            popularizeRegisterRequest.setInvitedMemberId(getMemberResponse.getMemberInfo().getId());
            BaseResponse baseResponse = irMemberPopularizeService.register(popularizeRegisterRequest);
            ResponseData responseData = new ResponseData();
            if(baseResponse.getReturnInfo().getCode()==200){
                //获取推广员信息
                GetPopularizeAccountByCodeRequest r = new GetPopularizeAccountByCodeRequest();
                r.setCode(request.getInviteCode());
                GetPopularizeAccountByCodeResponse res = irMemberPopularizeService.getPopularizeAccountByCode(r);
                if(res!=null && res.getAccount()!=null){
                    responseData.setMessage("您已成功关联推广员"+res.getAccount().getName());
                }
            }else{
                responseData.setMessage(baseResponse.getReturnInfo().getMessage());
            }
            response.setCode(baseResponse.getReturnInfo().getCode());
            response.setData(responseData);
        } catch (Exception e) {
            return handleException(ResponseData.class, e);
        }
        return response;
    }




}
