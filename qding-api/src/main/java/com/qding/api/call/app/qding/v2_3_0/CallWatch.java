package com.qding.api.call.app.qding.v2_3_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.*;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.request.*;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.response.data.*;
import com.qding.api.ip.IPUtil;
import com.qding.api.rongcloud.RongCloudApiHttpClient;
import com.qding.api.rongcloud.model.response.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.passport.service.IPassportService;
import com.qding.passport.service.IWatchService;
import com.qding.passport.struct.AccountInfo;
import com.qding.passport.struct.request.SaveImUserTokenRequest;
import com.qding.passport.struct.response.SaveImUserTokenResponse;
import com.qding.passport.struct.watch.OtherPhone;
import com.qding.passport.struct.watch.request.ModifyWatchInfoRequest;
import com.qding.passport.struct.watch.response.*;
import com.qding.thrift.model.dictionary.Dictionary;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@ExplainAnnotation(explain = "儿童手表")
public class CallWatch extends Callable {

    private static Logger logger = Logger.getLogger(CallWatch.class);

    @Autowired
    private IWatchService watchService;

    @Autowired
    private IPassportService passportService;

    @HTTP(alias = "checkIMEI")
    @ExplainAnnotation(explain = "扫码验证手表串号是否有效")
    public Response<CheckIMEIResponseData> checkIMEI(CheckIMEIRequest request) {
        try {
            Response<CheckIMEIResponseData> response = new Response<CheckIMEIResponseData>();
            CheckIMEIResponseData data = new CheckIMEIResponseData();
            BaseResponse baseResponse = watchService.checkIMEI(request.getImei());
            if (baseResponse != null && baseResponse.getReturnInfo() != null &&
                    baseResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                response.setCode(HttpStatus.OK.getStatusCode());
                WatchBindResponse watchBindResponse = watchService.getWatchBindByIMEI(request.getImei());
                if (watchBindResponse != null && watchBindResponse.getReturnInfo() != null &&
                        watchBindResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                    response.setCode(HttpStatus.OK.getStatusCode());
                    WatchInfo watchInfo = transfor(WatchInfo.class, watchBindResponse.getWatchInfo());
                    if (watchInfo != null) {
                        WatchAccountInfo accountInfo = watchInfo.getAccountInfo();
                        data.setWatchAccountInfo(accountInfo);
                    }
                    response.setData(data);
                }
            } else {
                response.setCode(baseResponse.getReturnInfo().getCode());
                data.setMessage(baseResponse.getReturnInfo().getMessage());
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(CheckIMEIResponseData.class, e);
        }
    }

    @HTTP(alias = "bindWatch")
    @ExplainAnnotation(explain = "手表绑定")
    public Response<BindWatchResponseData> bindWatch(BindWatchRequest request, HttpServletRequest httpServletRequest) {
        try {
            Response<BindWatchResponseData> response = new Response<BindWatchResponseData>();
            BindWatchResponseData data = new BindWatchResponseData();

            if (StringUtils.isEmpty(request.getNickName())) {
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                data.setMessage("请设置手表使用人昵称");
                response.setData(data);
                return response;
            }

            com.qding.passport.struct.watch.request.BindWatchRequest bindWatchRequest =
                    transfor(com.qding.passport.struct.watch.request.BindWatchRequest.class, request);
            String ip = IPUtil.getIpAddress(httpServletRequest);
            bindWatchRequest.setRegIp(QDStringUtil.isNotEmpty(ip)?ip:"none");
            BaseResponse baseResponse = watchService.bindWatch(bindWatchRequest);
            if (baseResponse != null && baseResponse.getReturnInfo() != null &&
                    baseResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                response.setCode(HttpStatus.OK.getStatusCode());
                data.setMessage(baseResponse.getReturnInfo().getMessage());
                checkAndContinue(baseResponse);
                //根据手表串号获取手表信息和手表绑定信息
                WatchBindResponse watchBindResponse = watchService.getWatchBindByIMEI(request.getImei());
                if (watchBindResponse != null && watchBindResponse.getWatchInfo() != null) {
                    AccountInfo accountInfo = watchBindResponse.getWatchInfo().getAccountInfo();
                    if (accountInfo != null && StringUtils.isEmpty(accountInfo.getRongCloudToken())) {
                        UserToken userToken = RongCloudApiHttpClient.getToken(accountInfo.getId(), accountInfo.getNickName(), accountInfo.getHeadImg());
                        if (QDStringUtil.isNotNull(userToken) && 200 == userToken.getCode()) {
                            String tokenStr = userToken.getToken();
                            SaveImUserTokenRequest saveImUserTokenRequest = new SaveImUserTokenRequest();
                            saveImUserTokenRequest.setAccountId(accountInfo.getId());
                            saveImUserTokenRequest.setRongcloudToken(tokenStr);
                            SaveImUserTokenResponse saveImUserTokenResponse = passportService.saveImUserToken(saveImUserTokenRequest);
                            checkAndContinue(saveImUserTokenResponse);
                        } else {
                            response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                            data.setMessage("服务错误没有获取到用户令牌");
                        }
                    }
                    //最后一步验证心跳
                    baseResponse = watchService.checkHeartBeatByIMEI(request.getImei());
                    if (baseResponse != null && baseResponse.getReturnInfo() != null &&
                            baseResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                        response.setCode(HttpStatus.OK.getStatusCode());
                    } else {
                        response.setCode(baseResponse.getReturnInfo().getCode());
                        data.setMessage(baseResponse.getReturnInfo().getMessage());
                    }
                }
            } else {
                response.setCode(baseResponse.getReturnInfo().getCode());
                data.setMessage(baseResponse.getReturnInfo().getMessage());
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(BindWatchResponseData.class, e);
        }
    }


    @HTTP(alias = "setupWatchPhone")
    @ExplainAnnotation(explain = "设置手表电话")
    public Response<WatchPhoneResponseData> setupWatchPhone(WatchPhoneRequest request) {
        try {
            Response<WatchPhoneResponseData> response = new Response<WatchPhoneResponseData>();
            WatchPhoneResponseData data = new WatchPhoneResponseData();
            if (request.getType().equals("1")) {
                //1:设置
                com.qding.passport.struct.watch.request.WatchPhoneRequest watchPhoneRequest =
                        transfor(com.qding.passport.struct.watch.request.WatchPhoneRequest.class, request);
                BaseResponse baseResponse = watchService.setupWatchPhone(watchPhoneRequest);
                if (baseResponse != null && baseResponse.getReturnInfo() != null &&
                        baseResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                    response.setCode(HttpStatus.OK.getStatusCode());
                } else {
                    response.setCode(baseResponse.getReturnInfo().getCode());
                    data.setMessage(baseResponse.getReturnInfo().getMessage());
                }
            } else if (request.getType().equals("2")) {
                //2:修改
                //修改手表电话
                BaseResponse baseResponse;
                if (StringUtils.isNotEmpty(request.getWatchPhone())) {
                    baseResponse = watchService.updateWatchPhone(request.getImei(), request.getWatchPhone());
                    if (baseResponse != null && baseResponse.getReturnInfo() != null &&
                            baseResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                        response.setCode(HttpStatus.OK.getStatusCode());
                    } else {
                        response.setCode(baseResponse.getReturnInfo().getCode());
                        data.setMessage(baseResponse.getReturnInfo().getMessage());
                    }
                    checkAndContinue(baseResponse);
                }

                //修改手表亲情电话
                if (request.getOtherPhoneList() != null && request.getOtherPhoneList().size() > 0) {
                    List<OtherPhone> otherPhoneList = transforList(OtherPhone.class, request.getOtherPhoneList());
                    baseResponse = watchService.updateWatchOtherPhone(request.getImei(), otherPhoneList, request.getMemberId());
                    if (baseResponse != null && baseResponse.getReturnInfo() != null &&
                            baseResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                        response.setCode(HttpStatus.OK.getStatusCode());
                    } else {
                        response.setCode(baseResponse.getReturnInfo().getCode());
                        data.setMessage(baseResponse.getReturnInfo().getMessage());
                    }
                }
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(WatchPhoneResponseData.class, e);
        }

    }

    @HTTP(alias = "getWatchDetail")
    @ExplainAnnotation(explain = "根据手机串号和memberId获取电话详情")
    public Response<GetWatchDetailResponseData> getWatchDetail(GetWatchDetailRequest request) {
        try {
            Response<GetWatchDetailResponseData> response = new Response<GetWatchDetailResponseData>();
            GetWatchDetailResponseData data = new GetWatchDetailResponseData();
            WatchDetailResponse watchDetailResponse = watchService.getWatchDetail(request.getImei(), request.getMemberId());
            if (watchDetailResponse != null && watchDetailResponse.getReturnInfo() != null &&
                    watchDetailResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                response.setCode(HttpStatus.OK.getStatusCode());
                WatchDetailInfo watchDetailInfo = transfor(WatchDetailInfo.class, watchDetailResponse.getWatchDetailInfo());
                watchDetailInfo.getCurrentWatchBindInfo().setCreateTime(formatDate(
                        watchDetailResponse.getWatchDetailInfo().getCurrentWatchBindInfo().getCreateTime()));
                for (WatchBindInfo info : watchDetailInfo.getOtherWatchBindList()) {
                    String roleDesc = DictionaryClient.findDictValueByGroupNameAndDictKey
                            ("watch_role", info.getRole().toString());
                    info.setRoleDesc(roleDesc);
                    //格式化后显示
                    info.setCreateTime(formatDate(Long.parseLong(info.getCreateTime())));
                }
                String roleDesc = DictionaryClient.findDictValueByGroupNameAndDictKey
                        ("watch_role", watchDetailInfo.getCurrentWatchBindInfo().getRole().toString());
                watchDetailInfo.getCurrentWatchBindInfo().setRoleDesc(roleDesc);
                data.setWatchDetailInfo(watchDetailInfo);
                response.setData(data);
            } else {
                response.setCode(watchDetailResponse.getReturnInfo().getCode());
                data.setMessage(watchDetailResponse.getReturnInfo().getMessage());
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(GetWatchDetailResponseData.class, e);
        }
    }

    @HTTP(alias = "getWatchInfoListByMemberId")
    @ExplainAnnotation(explain = "根据memberId获取绑定的手表列表")
    public Response<GetWatchInfoListByMemberIdResponseData> getWatchInfoListByMemberId(GetWatchInfoListByMemberIdRequest request) {
        try {
            Response<GetWatchInfoListByMemberIdResponseData> response = new Response<GetWatchInfoListByMemberIdResponseData>();
            GetWatchInfoListByMemberIdResponseData data = new GetWatchInfoListByMemberIdResponseData();
            WatchInfoResponse watchInfoResponse = watchService.getWatchInfoListByMemberId(request.getMemberId());
            if (watchInfoResponse != null && watchInfoResponse.getReturnInfo() != null &&
                    watchInfoResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                response.setCode(HttpStatus.OK.getStatusCode());
                List<WatchInfo> watchInfoList = transforList(WatchInfo.class, watchInfoResponse.getWatchInfoList());
                data.setList(watchInfoList);
                response.setData(data);
            } else {
                response.setCode(watchInfoResponse.getReturnInfo().getCode());
                data.setMessage(watchInfoResponse.getReturnInfo().getMessage());
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(GetWatchInfoListByMemberIdResponseData.class, e);
        }
    }

    @HTTP(alias = "modifyWatchAccountInfo")
    @ExplainAnnotation(explain = "修改手机账号信息(修改头像，修改昵称)")
    public Response<ResponseData> modifyWatchAccountInfo(ModifyWatchAccountInfoRequest request) {
        try {
            Response<ResponseData> response = new Response<ResponseData>();
            ResponseData data = new ResponseData();
            ModifyWatchInfoRequest modifyWatchInfoRequest = transfor(ModifyWatchInfoRequest.class, request);
            BaseResponse baseResponse = watchService.modifyWatchAccountInfo(modifyWatchInfoRequest);
            if (baseResponse != null && baseResponse.getReturnInfo() != null &&
                    baseResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                response.setCode(HttpStatus.OK.getStatusCode());
                //根据手表串号获取手表信息和手表绑定信息
                WatchBindResponse watchBindResponse = watchService.getWatchBindByIMEI(request.getImei());
                if (watchBindResponse != null && watchBindResponse.getWatchInfo() != null) {
                    AccountInfo accountInfo = watchBindResponse.getWatchInfo().getAccountInfo();
                    if (accountInfo != null) {
                        //刷新融云用户信息
                        RongCloudApiHttpClient.refreshUser(accountInfo.getId(), request.getNickName(), request.getHeadImage());
                    }
                }
            } else {
                response.setCode(baseResponse.getReturnInfo().getCode());
                data.setMessage(baseResponse.getReturnInfo().getMessage());
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(ResponseData.class, e);
        }
    }

    @HTTP(alias = "getWatchPhoneByIMEI")
    @ExplainAnnotation(explain = "根据手表串号获取手表电话信息")
    public Response<GetWatchInfoResponseData> getWatchPhoneByIMEI(GetWatchPhoneByIMEIRequest request) {
        try {
            Response<GetWatchInfoResponseData> response = new Response<GetWatchInfoResponseData>();
            GetWatchInfoResponseData data = new GetWatchInfoResponseData();
            WatchPhoneResponse watchPhoneResponse = watchService.getWatchPhoneByIMEI(request.getImei());
            if (watchPhoneResponse != null && watchPhoneResponse.getReturnInfo() != null &&
                    watchPhoneResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                response.setCode(HttpStatus.OK.getStatusCode());
                WatchPhoneInfo entity = transfor(WatchPhoneInfo.class, watchPhoneResponse.getWatchPhone());
                data.setEntity(entity);
            } else {
                response.setCode(watchPhoneResponse.getReturnInfo().getCode());
                data.setMessage(watchPhoneResponse.getReturnInfo().getMessage());
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(GetWatchInfoResponseData.class, e);
        }
    }

    @HTTP(alias = "getWatchLocationByIMEI")
    @ExplainAnnotation(explain = "根据手表串号获取手表位置信息")
    public Response<GetWatchLocationByIMEIResponseData> getWatchLocationByIMEI(GetWatchLocationByIMEIRequest request) {
        try {
            Response<GetWatchLocationByIMEIResponseData> response = new Response<GetWatchLocationByIMEIResponseData>();
            GetWatchLocationByIMEIResponseData data = new GetWatchLocationByIMEIResponseData();
            WatchLocationResponse watchLocationResponse = watchService.getWatchLocationByIMEI(request.getImei());
            /*WatchLocationResponse watchLocationResponse = new WatchLocationResponse();
            watchLocationResponse.setReturnInfo(new ReturnInfo(HttpStatus.OK));
            Address address = new Address();
            address.setRegion("北京市");
            address.setCounty("朝阳区");
            address.setStreet("管庄地区");
            address.setStreet_number("东十里堡村");
            address.setCity("北京市");
            address.setCountry("中国");
            Location location = new Location();
            location.setAddressDescription("北京市朝阳区杨闸环岛管庄地区东十里堡村北 辰福第南区西南");
            location.setAccuracy("800");
            location.setAddress(address);
            location.setLatitude("39.9243300");
            location.setLongitude("116.5995000");
            watchLocationResponse.setLocation(location);*/
            if (watchLocationResponse != null && watchLocationResponse.getReturnInfo() != null &&
                    watchLocationResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                response.setCode(HttpStatus.OK.getStatusCode());
                WatchLocation entity = transfor(WatchLocation.class, watchLocationResponse.getLocation());
                data.setEntity(entity);
            } else {
                /*response.setCode(watchLocationResponse.getReturnInfo().getCode());
                data.setMessage(watchLocationResponse.getReturnInfo().getMessage());
                if (StringUtils.isEmpty(data.getMessage())) {
                    data.setMessage("系统没有检测到手表信号，无法在地图上准确定位手表的位置");
                }*/
                response.setCode(HttpStatus.OK.getStatusCode());
                WatchLocation entity = new WatchLocation();
                data.setEntity(entity);
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(GetWatchLocationByIMEIResponseData.class, e);
        }
    }

    @HTTP(alias = "modifyRoleByIMEIAndMemberId")
    @ExplainAnnotation(explain = "根据手表串号和memberId修改绑定角色") //已经加到手表绑定接口中
    public Response<ResponseData> modifyRoleByIMEIAndMemberId(ModifyRoleByIMEIAndMemberIdRequest request) {
        try {
            Response<ResponseData> response = new Response<ResponseData>();
            ResponseData data = new ResponseData();
            BaseResponse baseResponse = watchService.modifyRoleByIMEIAndMemberId
                    (request.getImei(), request.getMemberId(), Integer.parseInt(request.getRole()));
            if (baseResponse != null && baseResponse.getReturnInfo() != null &&
                    baseResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                response.setCode(HttpStatus.OK.getStatusCode());
            } else {
                response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(ResponseData.class, e);
        }
    }


    @HTTP(alias = "relationType")
    @ExplainAnnotation(explain = "获取您与手表使用者的关系类型")
    public Response<GetWatchRelationTypeResponseData> getWatchRelationType(BaseRequest request) {
        try {
            Response<GetWatchRelationTypeResponseData> response = new Response<GetWatchRelationTypeResponseData>();
            GetWatchRelationTypeResponseData data = new GetWatchRelationTypeResponseData();
            List<WatchRelationType> list = Lists.newArrayList();
            List<Dictionary> listDictionary = DictionaryClient.findDictionaryByGroupNameOrderBySortAsc("watch_role");
            for (Dictionary dic : listDictionary) {
                WatchRelationType relationType = new WatchRelationType(dic.getDictKey(), dic.getDictValue());
                list.add(relationType);
            }
            data.setList(list);
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(GetWatchRelationTypeResponseData.class, e);
        }
    }


    @HTTP(alias = "unbindingWatchRelations")
    @ExplainAnnotation(explain = "解绑手表亲情号码")
    public Response<UnbindingRelationsResponseData> unbindingWatchRelations (UnbindingRelationsRequest request) {
        try {
            Response<UnbindingRelationsResponseData> response = new Response<UnbindingRelationsResponseData>();
            UnbindingRelationsResponseData data = new UnbindingRelationsResponseData();
            BaseResponse baseResponse = watchService.unbindWatch(request.getImei(),request.getUserId(),request.getMemberId());
            checkAndContinue(baseResponse);
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(UnbindingRelationsResponseData.class, e);
        }
    }

    private String formatDate(Long createTime) {
        try {
            if (createTime != null) {
                Date date = new Date(createTime);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String result = sdf.format(date);
                return result;
            } else {
                return "";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
    }


}
