package com.qding.api.call.app.qding.v3_0_0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.cache.redis.MemberIdAccountIdCache;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Account;
import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Member;
import com.qding.api.call.app.qding.v3_0_0.struct.user.*;
import com.qding.api.call.app.qding.v3_0_0.struct.user.request.CheckCloudAlarmRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.user.request.*;
import com.qding.api.call.app.qding.v3_0_0.struct.user.response.data.CheckCloudAlarmResponse;
import com.qding.api.call.app.qding.v3_0_0.struct.user.response.data.*;
import com.qding.api.call.service.MemberGiftService;
import com.qding.api.call.service.MemberService;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.IntegralMessage;
import com.qding.api.imessage.IntegralMessageBeanT;
import com.qding.api.ip.IPUtil;
import com.qding.api.ip.TaoBaoCity;
import com.qding.api.process.security.UserToken;
import com.qding.api.rongcloud.RongCloudApiHttpClient;
import com.qding.api.sms.SendSms;
import com.qding.api.sms.SmsAction;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.api.util.*;
import com.qding.api.verifycode.ImgVerify;
import com.qding.api.verifycode.SendCodeConfig;
import com.qding.api.verifycode.VerifyCode;
import com.qding.api.verifycode.VerifyCodeConfig;
import com.qding.api.verifycode.generator.DefaultGeneratorCode;
import com.qding.api.verifycode.send.SmsSendCannel;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.brick.struts.request.GetProjectGPSDistanceRequest;
import com.qding.brick.struts.response.GetProjectGPSDistanceResponse;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.AppDevice;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberRoom;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.response.memberroom.GetMemberRoomResponse;
import com.qding.member.rpc.response.memberroom.GetSelfRoomResponse;
import com.qding.passport.service.IPassportService;
import com.qding.passport.service.IProfileService;
import com.qding.passport.service.new_version.INewPassportService;
import com.qding.passport.struct.AccountInfo;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.*;
import com.qding.passport.struct.response.*;
import com.qding.popularize.remote.MemberPopularizeRemoteService;
import com.qding.popularize.struct.request.PopularizeLoginRequest;
import com.qding.promotion.common.domain.PromotionCoupon;
import com.qding.promotion.common.domain.PromotionGiftPackageCoupon;
import com.qding.promotion.common.domain.PromotionGiftPackageWare;
import com.qding.promotion.common.params.PromotionGiftPackageParams;
import com.qding.promotion.common.service.IPromotionGiftPackageRemoteService;
import com.qding.promotion.common.struct.request.GiftPackageReceiveDetailRequest;
import com.qding.promotion.common.struct.request.PromotionGiftPackageReceiveRequest;
import com.qding.promotion.common.struct.request.PromotionGiftPackageRequest;
import com.qding.promotion.common.struct.response.PromotionGiftPackageReceiveResponse;
import com.qding.promotion.common.struct.response.PromotionGiftPackageResponse;
import com.qding.sysconfig.dto.AppHomeConfigDto;
import com.qding.sysconfig.rpc.response.MemberTypeAppHomeConfigResponse;
import com.qding.sysconfig.rpc.service.AppHomeConfigRpcService;
import com.qding.task.domain.GiftPackagePublishInfo;
import com.qding.task.rpc.service.IGiftPackRpc;
import com.qding.task.rpc.struct.request.AddGiftViewRequest;
import com.qding.task.rpc.struct.request.ValidGiftIsShowRequest;
import com.qding.task.rpc.struct.request.ValidGiftPackRequest;
import com.qding.task.rpc.struct.response.ValidGiftIsShowResponse;
import com.qding.task.rpc.struct.response.ValidGiftPackResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.*;


/**
 * Created by qd on 2017/3/3.
 */
@ExplainAnnotation(explain = "用户信息模块")
public class CallUser extends com.qding.api.call.app.qding.v2_8_0.CallUser {

    private static final Logger logger = Logger.getLogger(CallUser.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private IProfileService profileAPI;

    @Autowired
    private IPassportService passportAPI;

    @Autowired
    private INewPassportService newPassportService;

    @Autowired
    private ImageUtil imageUtil;

    @Autowired
    private IntegralMessage integralMessage;

    @Autowired
    private IMemberRoomRPC memberRoomAPI;

    @Autowired
    private MemberPopularizeRemoteService irMemberPopularizeService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IGiftPackRpc giftPackRpc;

    @Autowired
    private IPromotionGiftPackageRemoteService promotionGiftPackageRemoteService;


    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private MemberIdAccountIdCache memberIdAccountIdCache;

    @Autowired
    private MemberGiftService memberGiftService;

    @Resource
    private AppHomeConfigRpcService appHomeConfigRpcService;



    /**
     * 手机号是否已注册
     *
     * @param request
     * @return
     */
    @ExplainAnnotation(explain = "检查手机")
    @HTTP(alias = "checkMobile", isNeadProject = true, isNeedSign = true)
    public Response<CheckMobileResponseData> checkMobile(CheckMobileRequest request) {
        try {

            Response<CheckMobileResponseData> response = new Response<CheckMobileResponseData>();
            CheckMobileResponseData data = new CheckMobileResponseData();

            Integer resultCode = HttpStatus.OK.getStatusCode();
            CheckUniqueMobileResponse checkResponse = passportAPI.checkUniqueMobile(
                    transfor(CheckUniqueMobileRequest.class, request));
            checkAndContinue(checkResponse);

            boolean appAuditSwitch = checkAppAuditSwitchIsOpen(request.getMobile());
            if (!appAuditSwitch) {//如果不是APP审核测试账号，或审核测试账号通道未打开
                AppDevice appDevice = transfor(AppDevice.class, request.getAppDevice());
                //手机，地理位置，设备ID验证机制
                CheckMobileResultDTO checkMobileResult = checkMobileForRegister(request.getMobile(), request.getAppUser().getProjectId(), request.getAppDevice().getDeviceId(), request.getLatitude(), request.getLongitude(), appDevice);
                resultCode = checkMobileResult.getCode();
                data.setMessage(checkMobileResult.getMessage());
            }
            response.setCode(resultCode);
            response.setData(data);

            return response;

        } catch (Exception e) {
            return handleException(CheckMobileResponseData.class, e);
        }
    }


    @ExplainAnnotation(explain = "发送语音|短信验证码(跟随系统验证码验证)")
    @HTTP(alias = "sendCode", isNeedSign = true)
    public Response<SendCodeResponseData> sendCode(SendCodeRequest request) {

        Response<SendCodeResponseData> response = new Response<SendCodeResponseData>();
        ImgVerify imgVerify = new ImgVerify(request.getSysCode().toLowerCase(), request.getSysVerifyKey());
        try {
            String mobile = request.getMobile();
            int action = request.getAction();
            int codeType = request.getCodeType();
            SmsAction smsAction = SmsAction.to(action);
            VerifyCode.sendCode(
                    new SendCodeConfig(
                            System.currentTimeMillis() + 100 * 1000,
                            smsAction,
                            new DefaultGeneratorCode(),
                            new RedisStoreDevice(),
                            new SmsSendCannel(mobile, smsAction),
                            10 * 60,
                            imgVerify,
                            codeType
                    )
            );
            response.setData(new SendCodeResponseData());
            return response;

        } catch (Exception e) {
            return handleException(SendCodeResponseData.class, e);
        }

    }


    @ExplainAnnotation(explain = "手机号登陆", desc = "3.0级以上版本")
    @HTTP(alias = "loginByMobile", isLogin = true, isNeedSign = true, isNeadProject = true)
    public Response<UserLoginResponseData> loginByMobile(UserLoginRequest request, HttpServletRequest httpServletRequest) {

        try {
            Response<UserLoginResponseData> response = new Response<UserLoginResponseData>();
            UserLoginResponseData data = new UserLoginResponseData();
            String ip = IPUtil.getIpAddress(httpServletRequest);

            if (QDStringUtil.isNotEmpty(request.getVerifyCode())) {
                VerifyCode.verifyCode(
                        new VerifyCodeConfig(
                                SmsAction.LOGIN,
                                request.getVerifyCode(),
                                request.getMobile(),
                                new RedisStoreDevice()
                        )
                );
                MemberDeviceRequest deviceRequest = transfor(MemberDeviceRequest.class, request);
                LoginResponse loginResponse = newPassportService.updateDeviceNo(deviceRequest);
                checkAndContinue(loginResponse);
                data = fittingUserInfo(loginResponse, data, request, ip);

            } else {

                boolean appAuditSwitch = checkAppAuditSwitchIsOpen(request.getMobile());
                LoginResponse loginResponse = null;
                LoginRequest loginRequest = transfor(LoginRequest.class, request);
                TaoBaoCity city = IPUtil.getIpCity(ip);
                loginRequest.setLoginIp(ip);
                loginRequest.setProvinceName(city.getProvince());
                loginRequest.setCityName(city.getCity());

                if (!appAuditSwitch) { //如果不是测试账号，或测试账号通道未打开
                    loginRequest.setDeviceNo(request.getAppDevice().getDeviceId());
                    loginResponse = newPassportService.loginByMobile(loginRequest);
                    //新加code=204:手机号不存在 ; code=400:密码错误 code=409:当前设备和用户不匹配需要语音验证
                    int rpcCode = 0;
                    int returnCode = loginResponse.getReturnInfo().getCode();
                    if (Constant.ResultCodeEnum.DEVICE_409.toInteger() == returnCode) {
                        rpcCode = Constant.ResultCodeEnum.DEVICE_409.toInteger();
                    }else {
                        if (HttpStatus.OK.getStatusCode() != loginResponse.getReturnInfo().getCode()) {
                            checkAndContinue(loginResponse);
                        } else {
                            data = fittingUserInfo(loginResponse, data, request, ip);
                        }
                    }
                    //手机，地理位置，设备ID验证机制
                    AppDevice appDevice = transfor(AppDevice.class, request.getAppDevice());
                    CheckMobileResultDTO checkMobileResult = checkMobileForLogin(request.getMobile(), request.getAppDevice().getDeviceId(), appDevice, rpcCode);
                    Integer resultCode = checkMobileResult.getCode();
                    if (HttpStatus.OK.getStatusCode() != resultCode) {
                        response.setCode(checkMobileResult.getCode());
                        AccountMember accountMember = new AccountMember();
                        accountMember.setImToken(" ");
                        data.setEntity(accountMember);
                        data.setMessage(checkMobileResult.getMessage());
                        response.setCode(resultCode);
                        response.setData(data);
                        return response;
                    }
                } else {

                    loginResponse = passportAPI.loginByMobile(loginRequest);
                    checkAndContinue(loginResponse);
                    data = fittingUserInfo(loginResponse, data, request, ip);
                }
            }

            //通知单点登录系统
            MemberDeviceRequest memberDeviceRequest = new MemberDeviceRequest();
            memberDeviceRequest.setMemberId(data.getEntity().getMember().getMemberId());
            memberDeviceRequest.setDeviceNo(request.getAppDevice().getDeviceId());
            newPassportService.sendLoginMessage(memberDeviceRequest);

            try {
                //缓存mid,accountId
                AccountMember accountMember = data.getEntity();
                String mid = accountMember.getMember().getMemberId();
                String accountId = accountMember.getUser().getAccountId();
                String mobile = accountMember.getMember().getMemberMobile();
                if (StringUtils.isNotEmpty(mid) && StringUtils.isNotEmpty(accountId)) {
                    String value = String.format("%s:%s", accountId, mobile);
                    memberIdAccountIdCache.set(mid, value);
                }
            } catch (Exception e) {
                logger.error("memberIdAccountIdCache.set exception: " + e.getMessage(), e);
            }

            response.setData(data);
            return response;

        } catch (Exception e) {
            return handleException(UserLoginResponseData.class, e);
        }
    }

    /**
     * 组装用户登陆信息
     *
     * @param loginResponse
     * @param data
     * @param request
     * @param ip
     * @return
     * @throws ServiceException
     */
    private UserLoginResponseData fittingUserInfo(LoginResponse loginResponse, UserLoginResponseData data, UserLoginRequest request, String ip) throws ServiceException {

        data = transfor(UserLoginResponseData.class, loginResponse);
        checkAndContinue(loginResponse);
        PopularizeLoginRequest popularizeLoginRequest = new PopularizeLoginRequest();
        MemberInfo member = loginResponse.getMemberInfo();
        popularizeLoginRequest.setMemberId(member.getId());
        popularizeLoginRequest.setProjectId(request.getAppUser().getProjectId());
        popularizeLoginRequest.setIp(ip);
        BaseResponse baseResponse = irMemberPopularizeService.login(popularizeLoginRequest);

        if (HttpStatus.OK.getStatusCode() == baseResponse.getReturnInfo().getCode()) {
            IntegralMessageBeanT integralMessageBeanT = new IntegralMessageBeanT(member.getId(), Constant.INTEGRAL_LOGIN, member.getId(), Long.parseLong(request.getAppUser().getProjectId()), System.currentTimeMillis(), Constant.INCOME_OPT_TYPE, null, member.getId());
            integralMessage.assembleIntegralMessage(integralMessageBeanT);
        }
        String version = request.getAppDevice().getQdVersion();
        String userToken = afterLogin(data.getEntity(), version);
        data.setBaseToken(userToken);
        data.getEntity().setImToken(loginResponse.getAccountInfo().getRongCloudToken());
        return data;
    }


    @ExplainAnnotation(explain = "用户注册")
    @HTTP(alias = "register", isNeedSign = true)
    public Response<UserRegisterResponseData> register(UserRegisterRequest request, HttpServletRequest httpServletRequest) {

        Response<UserRegisterResponseData> response = new Response<UserRegisterResponseData>();
        String code = request.getVerifyCode();//短信验证码
        String version = request.getAppDevice().getQdVersion();
        try {
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
            registerRequest.setDeviceNo(request.getAppDevice().getDeviceId());
            registerRequest.setRegIp(QDStringUtil.isNotEmpty(ip) ? ip : "none");
            RegisterResponse rpcResponse = newPassportService.register(registerRequest);
            checkAndContinue(rpcResponse);
            UserRegisterResponseData userRegisterResponse = transfor(UserRegisterResponseData.class, rpcResponse);
            String memberId = userRegisterResponse.getEntity().getMember().getMemberId();
            String qrcode = request.getQrcode();
            String roomId = "";

            if (QDStringUtil.isNotNull(memberId) && QDStringUtil.isNotEmpty(qrcode)) {
                GetSelfRoomResponse selfRoomResponse  = memberRoomAPI.qrbind(memberId, qrcode);
                checkAndContinue(selfRoomResponse);
                com.qding.member.model.MemberRoom rm = selfRoomResponse.getMemberRoom();
                roomId = rm.getRoomId();
            }

            AccountMember accountMember = userRegisterResponse.getEntity();
            afterLogin(accountMember, version);
            String imToken = saveImUserToken(accountMember.getUser().getAccountId());
            accountMember.setImToken(imToken);
            response.setData(userRegisterResponse);

            //如果是邀请注册
            if (QDStringUtil.isNotEmpty(qrcode) && QDStringUtil.isNotEmpty(roomId)) {
                passportAPI.addIntegralForInvite(qrcode, roomId);
            }
            return response;

        } catch (ServiceException e) {
            return handleException(UserRegisterResponseData.class, e);
        }
    }


    @HTTP(alias = "checkSuperiorLimitForBindRoomByMid", isRequireAuth = true, isNeadToken = true)
    @ExplainAnnotation(explain = "检查用户绑定房屋是否达到上限")
    public Response<CheckSuperiorLimitByMidResponseData> checkSuperiorLimitForBindRoomByMid(CheckSuperiorLimitByMidRequest request, UserToken userToken) {

        Response<CheckSuperiorLimitByMidResponseData> response = new Response<CheckSuperiorLimitByMidResponseData>();
        CheckSuperiorLimitByMidResponseData data = new CheckSuperiorLimitByMidResponseData();
        try {
            GetMemberRequest memberRequest = new GetMemberRequest();
            memberRequest.setMemberId(userToken.getMemberId());
            GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);
            checkAndContinue(memberResponse);
            MemberInfo memberInfo = memberResponse.getMemberInfo();
            int bindLimit = memberInfo.getBindLimit();
            GetMemberRoomResponse memberRoomResponse = memberRoomAPI.listValidByMember(userToken.getMemberId());
            checkAndContinue(memberRoomResponse);
            List<com.qding.member.model.MemberRoom> lsMemberRoom = memberRoomResponse.getList();
            int curCount = lsMemberRoom.size();
            if (bindLimit <= curCount) {
                data.setSuperiorLimitFlag(1);
            } else {
                data.setSuperiorLimitFlag(0);
            }
        } catch (ServiceException e) {
            return handleException(CheckSuperiorLimitByMidResponseData.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

    @HTTP(alias = "checkCloudAlarm", isNeadToken = true)
    @ExplainAnnotation(explain = "获取是否设置云报警")
    public Response<CheckCloudAlarmResponse> checkCloudAlarm(CheckCloudAlarmRequest request, UserToken userToken) {

        Response<CheckCloudAlarmResponse> response = new Response<CheckCloudAlarmResponse>();
        CheckCloudAlarmResponse data = new CheckCloudAlarmResponse();
        try {
            com.qding.passport.struct.request.CheckCloudAlarmRequest checkCloudAlarmRequest = new com.qding.passport.struct.request.CheckCloudAlarmRequest();
            checkCloudAlarmRequest.setMemberId(userToken.getMemberId());
            com.qding.passport.struct.response.CheckCloudAlarmResponse checkCloudAlarmResponse = newPassportService.checkCloudAlarm(checkCloudAlarmRequest);
            checkAndContinue(checkCloudAlarmResponse);
            data.setAlarmFlag(checkCloudAlarmResponse.isAlarm());
        } catch (ServiceException e) {
            return handleException(CheckCloudAlarmResponse.class, e);
        }

        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

    @HTTP(alias = "checkCloudIntercom", isNeadToken = true)
    @ExplainAnnotation(explain = "获取是否设置云对讲")
    public Response<CheckCloudIntercomResponse> checkCloudIntercom(CheckCloudIntercomRequest request, UserToken userToken) {

        Response<CheckCloudIntercomResponse> response = new Response<CheckCloudIntercomResponse>();
        CheckCloudIntercomResponse data = new CheckCloudIntercomResponse();
        Project project = checkCloudIntercom(userToken.getMemberId());
        data.setIntercomFlag(QDStringUtil.isNotNull(project)?true:false);
        data.setProjectId(QDStringUtil.isNotNull(project)?String.valueOf(project.getId()):"");
        data.setProjectName(QDStringUtil.isNotNull(project)?project.getName():"");
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);

        return response;
    }

    private Project checkCloudIntercom (String memberId){

        Project cloudIntercomProject = null;
        GetMemberRoomResponse  memberRoomResponse = memberRoomAPI.listValidByMember(memberId);
        Set<Long> bindRoomPIdSet = new HashSet<>();
        if (HttpStatus.OK.getStatusCode() == memberRoomResponse.getReturnInfo().getCode()) {
            List<com.qding.member.model.MemberRoom> lsMemberRoom = memberRoomResponse.getList();
            for (MemberRoom memberRoom : lsMemberRoom) {
                bindRoomPIdSet.add(Long.parseLong(memberRoom.getProjectId()));
            }
        }

        if (CollectionUtils.isNotEmpty(bindRoomPIdSet)) {
            List<Project> projectList = projectReadService.getAllProjectInfoByIdSet(bindRoomPIdSet);
            if (CollectionUtils.isNotEmpty(projectList)) {
                for (Project project : projectList) {
                    if (QDStringUtil.isNotNull(project) && project.getIsCloudInterphone() !=0) {
                        cloudIntercomProject = project;
                        break;
                    }
                }
            }
        }

        return  cloudIntercomProject;
    }


    @HTTP(alias = "getGiftRemind")
    @ExplainAnnotation(explain = "新手礼包提醒")
    public Response<GetGiftRemindResponseData> getGiftRemind(GetGiftRemindRequest request,UserToken userToken) {

        Response<GetGiftRemindResponseData> response = new Response<GetGiftRemindResponseData>();
        GetGiftRemindResponseData data = new GetGiftRemindResponseData();
        try {
            boolean canShow = memberGiftService.checkGiftPackage( QDStringUtil.isNotNull(userToken)&& QDStringUtil.isNotEmpty(userToken.getMemberId())?userToken.getMemberId():"", Long.parseLong(request.getAppUser().getProjectId()));
            GiftSkinDTO giftSkinDTO = new GiftSkinDTO("");
            giftSkinDTO.setSkipModel(skipMode.fittingSkipUrl(request.getAppDevice().getQdVersion(), APIPropertiesClient.getValue("gift_index") + "?entry=" + request.getSourceType(), 0, ""));
            data.setGiftSkinDTO(giftSkinDTO);
            data.setShowFlag(canShow?1:0);
            data.setMoreFlag( QDStringUtil.isNotNull(userToken)&& QDStringUtil.isNotEmpty(userToken.getMemberId())&&canShow?1:0);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(GetGiftRemindResponseData.class, e);
        }
    }


    @HTTP(alias = "getGiftList", isRequireAuth = true)
    @ExplainAnnotation(explain = "新手礼包列表")
    public Response<GetGiftListResponseData> getGiftList(GetGiftListRequest request, UserToken userToken) {

        Response<GetGiftListResponseData> response = new Response<GetGiftListResponseData>();
        GetGiftListResponseData data = new GetGiftListResponseData();
        List<GiftInfoDTO> unclaimedlist = Lists.newArrayList();
        List<GiftInfoDTO> receivedList = Lists.newArrayList();

        try {
            String roomId = "";
            String memberId = userToken.getMemberId();
            String projectId = request.getAppUser().getProjectId();
            List<MemberRoom>  rooms = memberService.getRoomsByMultiRole(memberId, projectId,Constant.GIFT_ROLE_LIST,true);//可领取礼包房屋ID （当前社区，当前人，拥有业主|亲人|租客身份的最早绑定的房屋）
            if (CollectionUtils.isNotEmpty(rooms)) {
                roomId = rooms.get(0).getRoomId();
            }
            // 用户信息
            MemberInfo memberInfo = memberService.getMemberByMid(memberId);
            checkEntity(memberInfo);

            // 礼包信息
            PromotionGiftPackageRequest promotionGiftPackageRequest = new PromotionGiftPackageRequest();
            promotionGiftPackageRequest.setProjectId(String.valueOf(request.getAppUser().getProjectId()));
            promotionGiftPackageRequest.setRoomId(roomId);
            promotionGiftPackageRequest.setMid(memberId);
            PromotionGiftPackageResponse promotionGiftPackageResponse = promotionGiftPackageRemoteService.getGiftPackageInfoCommon(promotionGiftPackageRequest);
            checkAndContinue(promotionGiftPackageResponse);

            //该社区下礼包详情
            List<PromotionGiftPackageParams> giftPackageParamsList = promotionGiftPackageResponse.getGiftPackageParamsList();
            if (CollectionUtils.isEmpty(giftPackageParamsList) && request.getSourceType() == 1) { //如果当前社区下有礼包
                data.setMessage("当前社区下没有可用的礼包");
                response.setData(data);
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                 return response;
            }

            //获取指定房间下未领取的礼包
            unclaimedlist = memberGiftService.getUnclaimedGiftPackageByProjectId(roomId, memberInfo, promotionGiftPackageResponse);
            if (request.getSourceType() == 2) {
                //获取已领取的礼包不分社区
                receivedList =memberGiftService.getReceivedGiftForAllProject(memberInfo, promotionGiftPackageResponse); //获取已领取的礼包不分社区
            }

            if (QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())) {
                AddGiftViewRequest addGiftViewRequest = new AddGiftViewRequest();
                addGiftViewRequest.setMid(userToken.getMemberId());
                addGiftViewRequest.setSourceType(request.getSourceType());
                giftPackRpc.addGiftView(addGiftViewRequest);
            }
        } catch (Exception e) {
            return handleException(GetGiftListResponseData.class, e);
        }
        data.setReceivedList(receivedList);
        data.setUnclaimedlist(unclaimedlist);
        data.setTotalCount(10);
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }

    @HTTP(alias = "getGiftPackTicket",isNeadToken = true,isRequireAuth = true,isNeadProject = true)
    @ExplainAnnotation(explain = "领取礼包券", desc = "领取礼包券")
    public Response<ResponseData> getGiftPackTicket(GetGiftPackTicketRequest request,UserToken userToken) {

        Response<ResponseData> response = new Response<ResponseData>();
        ResponseData data = new ResponseData();
        try {
            String memberId = request.getAppUser().getCurMemberId();
            String projectId = request.getAppUser().getProjectId();
            String roomId  = "";
            List<MemberRoom>  rooms = memberService.getRoomsByMultiRole(memberId, projectId,Constant.GIFT_ROLE_LIST,true);//可领取礼包房屋ID （当前社区，当前人，拥有业主|亲人|租客身份的最早绑定的房屋）
            if (CollectionUtils.isNotEmpty(rooms)) {
                roomId = rooms.get(0).getRoomId();
            }

            //实物礼包
            if (request.getReceiveType() == Constant.GIFT_TYPE_SKU ) {

                if (QDStringUtil.isEmpty(roomId)){
                    response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
                    data.setMessage("当前社区没有已业主或家庭成员或租客身份绑定的房屋");
                    response.setData(data);
                    return response;

                } else {
                    // 礼包信息
                    PromotionGiftPackageRequest promotionGiftPackageRequest = new PromotionGiftPackageRequest();
                    promotionGiftPackageRequest.setProjectId(String.valueOf(request.getAppUser().getProjectId()));
                    promotionGiftPackageRequest.setRoomId(roomId);
                    promotionGiftPackageRequest.setMid(memberId);
                    PromotionGiftPackageResponse promotionGiftPackageResponse = promotionGiftPackageRemoteService.getGiftPackageInfoCommon(promotionGiftPackageRequest);
                    checkAndContinue(promotionGiftPackageResponse);
                    PromotionGiftPackageReceiveResponse giftPackageReceiveResponse = promotionGiftPackageResponse.getReceiveInfo();
                    Integer roomReceivedCount = giftPackageReceiveResponse.getRoomReceivedCount();
                    Integer roomReceivedLimit = giftPackageReceiveResponse.getRoomReceiveLimit();
                    if (roomReceivedCount.intValue() >= roomReceivedLimit.intValue()) {
                        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
                        data.setMessage("当前房屋实物礼包领取次数已经达到上限！");
                        response.setData(data);
                        return response;
                    }
                }
            }

            // 用户信息
            MemberInfo memberInfo = memberService.getMemberByMid(memberId);
            checkEntity(memberInfo);

            if ( Constant.GIFT_TYPE_SKU != request.getReceiveType()) {

                // 资料是否完善
                boolean isComplete = false;
                if (StringUtils.isNotBlank(memberInfo.getHeadImg()) && StringUtils.isNotBlank(memberInfo.getName())
                        && memberInfo.getBirthday() != 0L && memberInfo.getFamilyStatus() != 0 && QDStringUtil.isNotEmpty(roomId)) {
                    isComplete = true;
                }
                if (!isComplete) {
                    response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
                    data.setMessage("请先完善个人资料");
                    response.setData(data);
                    return response;
                }
            }

            PromotionGiftPackageReceiveRequest receiveRequest = new PromotionGiftPackageReceiveRequest();
            receiveRequest.setMid(userToken.getMemberId());
            receiveRequest.setRoomId(roomId);
            receiveRequest.setGiftPackageId(request.getGiftId());
            receiveRequest.setMobile(memberInfo.getMobile());
            receiveRequest.setName(memberInfo.getName());
            receiveRequest.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
            receiveRequest.setReceiveType(request.getReceiveType());
            PromotionGiftPackageReceiveResponse receiveResponse = promotionGiftPackageRemoteService.receiveGiftPackage(receiveRequest);
            checkAndContinue(receiveResponse);

            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(ResponseData.class, e);
        }
    }

    @HTTP(alias = "getGiftDetail",isRequireAuth = true,isNeadToken = true)
    @ExplainAnnotation(explain = "新手礼包详情", desc = "超值优惠券")
    public Response<GetGiftDetailResponseData> getGiftDetail(GetGiftDetailRequest request,UserToken userToken) {

        Long now = System.currentTimeMillis();
        Response<GetGiftDetailResponseData> response = new Response<GetGiftDetailResponseData>();
        GetGiftDetailResponseData data = new GetGiftDetailResponseData();
        try {
            List<CouponDTO> list = Lists.newArrayList();
            PromotionGiftPackageRequest promotionGiftPackageRequest = new PromotionGiftPackageRequest();
            promotionGiftPackageRequest.setGiftPackageId(request.getGiftId());
            promotionGiftPackageRequest.setMid(userToken.getMemberId());
            PromotionGiftPackageResponse promotionGiftPackageResponse = promotionGiftPackageRemoteService.getGiftPackageInfoCommon(promotionGiftPackageRequest);

            if (promotionGiftPackageResponse != null && promotionGiftPackageResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()
                    && CollectionUtils.isNotEmpty(promotionGiftPackageResponse.getGiftPackageParamsList())) {
                PromotionGiftPackageReceiveResponse receiveResponse =  promotionGiftPackageResponse.getReceiveInfo();
                boolean isReceived =  receiveResponse.getCurrentCouponsReceived();

                // 用户信息
                MemberInfo memberInfo = memberService.getMemberByMid(userToken.getMemberId());
                checkEntity(memberInfo);

                for (PromotionGiftPackageParams giftPackage : promotionGiftPackageResponse.getGiftPackageParamsList()) {
                    if (giftPackage.getType() == Constant.GIFT_TYPE_QUAN_NEW) {
                        boolean isNewMember = false;
                        if (memberInfo.getCreateTime() > giftPackage.getValidStart()) {
                            isNewMember = true;
                        }
                        for (PromotionGiftPackageCoupon giftPackageCoupon : giftPackage.getGiftPackageCouponList()) {
                            if (giftPackageCoupon.getType() == (isNewMember?1:2)) {
                                PromotionCoupon promotionCoupon = giftPackageCoupon.getCoupon();
                                CouponDTO couponDTO  = new CouponDTO(String.valueOf(promotionCoupon.getPrice()),
                                        "", promotionCoupon.getValidStart(), promotionCoupon.getValidEnd(), isReceived?0:1);
                                couponDTO.setDesc(giftPackageCoupon.getIntroduction());
                                couponDTO.setExplain(giftPackageCoupon.getExplain());
                                couponDTO.setRule(giftPackageCoupon.getCoupon().getNote());
                                couponDTO.setReceiveAfter(giftPackageCoupon.getCoupon().getReceiveAfter());
                                couponDTO.setReceivePeriod(giftPackageCoupon.getCoupon().getReceivePeriod());
                                // 如果未领取，判断是否已过期
                                if (couponDTO != null && couponDTO.getValidEndTime() !=
                                        null && couponDTO.getStatus() == 1 && now.longValue() > couponDTO.getValidEndTime()) {
                                    couponDTO.setStatus(2); //1:未领取,0:已领取，2:已过期
                                }
                                list.add(couponDTO);
                            }
                        }
                    }
                }
            }
            data.setList(list);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(GetGiftDetailResponseData.class, e);
        }
    }

    @HTTP(alias = "getMemberCenter",isRequireAuth = true,isNeadToken = true,isNeadProject = true)
    @ExplainAnnotation(explain = "会员中心", desc = "新手礼包|会员专享任务")
    public Response<GetMemberCenterResponseData> getMemberCenter (GetMemberCenterRequest request,UserToken userToken) {

        Response<GetMemberCenterResponseData> response = new Response<GetMemberCenterResponseData>();
        GetMemberCenterResponseData data = new GetMemberCenterResponseData();
        try {

            String memberId = userToken.getMemberId();
            String projectId = request.getAppUser().getProjectId();
            String roomId  = "";
            List<MemberRoom>  rooms = memberService.getRoomsByMultiRole(memberId, projectId,Constant.GIFT_ROLE_LIST,true);//可领取礼包房屋ID （当前社区，当前人，拥有业主|亲人|租客身份的最早绑定的房屋）
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
            List<GiftInfoDTO> receivedList = Lists.newArrayList();
            //获取已领取的礼包不分社区
            receivedList =memberGiftService.getReceivedGiftForAllProject(memberInfo, promotionGiftPackageResponse); //获取已领取的礼包不分社区

            //该社区下礼包详情
            List<PromotionGiftPackageParams> giftPackageParamsList = promotionGiftPackageResponse.getGiftPackageParamsList();
            if (CollectionUtils.isNotEmpty(giftPackageParamsList)) { //如果当前社区下有礼包
                //获取指定房间下未领取的礼包
                unclaimedlist = memberGiftService.getUnclaimedGiftPackageByProjectId(roomId, memberInfo, promotionGiftPackageResponse);
            }

            GiftLableDTO giftLableDTO = new GiftLableDTO();
            if ( CollectionUtils.isNotEmpty(unclaimedlist)) { //有未领取的实物礼包或券礼包
                giftLableDTO.setShowRemind(1);
            }
            if (CollectionUtils.isNotEmpty(receivedList) ||  CollectionUtils.isNotEmpty(unclaimedlist)){
                giftLableDTO.setHasGift(1);
            }

            List<MemberSpecialActivityDTO> taskList = Lists.newArrayList();
            MemberTypeAppHomeConfigResponse memberTypeAppHomeConfigResponse = appHomeConfigRpcService.getMemberTypeAppHomeConfig(memberId);
           if (HttpStatus.OK.getStatusCode() == memberTypeAppHomeConfigResponse.getReturnInfo().getCode()){
               List<AppHomeConfigDto> appHomeConfigDtoList = memberTypeAppHomeConfigResponse.getAppHomeConfigs();
               if (CollectionUtils.isNotEmpty(appHomeConfigDtoList)){
                   for (AppHomeConfigDto appHomeConfigDto : appHomeConfigDtoList) {
                       MemberSpecialActivityDTO memberSpecialActivityDTO = new MemberSpecialActivityDTO();
                       memberSpecialActivityDTO.setSkipModel(skipMode.fittingSkipUrl(request.getAppDevice().getQdVersion(), appHomeConfigDto.getContentUrl(),0,""));
                       memberSpecialActivityDTO.setActivityImg(appHomeConfigDto.getCalendarImgUrl());
                       memberSpecialActivityDTO.setIsNew(0);
                       memberSpecialActivityDTO.setTitle(appHomeConfigDto.getDescription());
                       taskList.add(memberSpecialActivityDTO);
                   }
               }
           } else {
               logger.error("API :getMemberCenter invoker rpc method:getMemberTypeAppHomeConfig error!  code:"+memberTypeAppHomeConfigResponse.getReturnInfo().getCode()+", msg:"+memberTypeAppHomeConfigResponse.getReturnInfo().getMessage());
           }

            data.setEntity(giftLableDTO);
            data.setTaskList(taskList);
        } catch (Exception e) {
            return handleException(GetMemberCenterResponseData.class, e);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }


    @ExplainAnnotation(explain = "查询指定用户信息")
    @HTTP(alias = "selUserInfo", isRequireAuth = true, isNeadToken = true)
    public Response<GetUserInfoResponseData> selUserInfo(GetUserInfoRequest request, UserToken userToken) {

        Response<GetUserInfoResponseData> response = new Response<GetUserInfoResponseData>();
        try {
            Account user = null;
            GetMemberRequest memberRequest = new GetMemberRequest();
            if (QDStringUtil.isNotEmpty(request.getSelMemberId())) {
                memberRequest.setMemberId(request.getSelMemberId());

            } else {
                GetAccountRequest accountRequest = new GetAccountRequest();
                accountRequest.setAccountId(userToken.getAccountId());
                GetAccountResponse accountResponse = passportAPI.getAccountByAccountId(accountRequest);
                checkAndContinue(accountResponse);
                user = transfor(Account.class, accountResponse.getAccountInfo());
                memberRequest.setMemberId(userToken.getMemberId());
            }

            GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);
            checkAndContinue(memberResponse);
            MemberInfo memberInfo = memberResponse.getMemberInfo();
            Member member = transfor(Member.class, memberInfo);
            if (QDStringUtil.isNotEmpty(request.getSelMemberId())) {
                GetAccountResponse accountResponse = passportAPI.getAppAccountByMobile(memberInfo.getMobile());
                if (HttpStatus.OK.getStatusCode() == accountResponse.getReturnInfo().getCode()) {
                    AccountInfo accountInfo = accountResponse.getAccountInfo();
                    user = transfor(Account.class, accountInfo);
                }
            }

            //增加家庭状况的输出
            if (QDStringUtil.isNotNull(memberInfo.getFamilyStatus())) {
                String homeSituation = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_FAMILY_STATUS.getGroupName(), String.valueOf(memberInfo.getFamilyStatus()));
                member.setHomeSituation(homeSituation);
            }
            AccountMember userEntity = new AccountMember(user, member);
            String myInsureUrl = APIPropertiesClient.getUrlContent("my_insure_url");
        	logger.info("my_insure_url:" + myInsureUrl);
        	userEntity.setMyInsureUrl(myInsureUrl);
            resizeMemberAvatar(member);//会员头像压缩处理
            GetUserInfoResponseData data = new GetUserInfoResponseData(userEntity);
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(GetUserInfoResponseData.class, e);
        }

    }


    @ExplainAnnotation(explain = "修改用户信息")
    @HTTP(alias = "updateUserInfo", isRequireAuth = true, isNeadToken = true)
    public Response<UpdateUserInfoResponseData> updateUserInfo(UpdateUserInfoRequest request, UserToken userToken) {

        try {
            ModifyMemberRequest modifyMemberRequest = transfor(ModifyMemberRequest.class, request);
            String headImg = modifyMemberRequest.getHeadImg();
            modifyMemberRequest.setHeadImg(getHeadImg(headImg));

            Response<UpdateUserInfoResponseData> response = new Response<UpdateUserInfoResponseData>();
            UpdateUserInfoResponseData data = new UpdateUserInfoResponseData();
            //临时解决如果性别传入的是2 对应后台数据为 -1
            if( QDStringUtil.isNotEmpty(request.getMemberInfo().getMemberGender()) && "2".equals(request.getMemberInfo().getMemberGender())) {
                modifyMemberRequest.setGender(-1);
            }
            modifyMemberRequest.setSkipCheck(true);
            //修改信息
            ModifyMemberResponse updateMemberResponse = profileAPI.modifyMember(modifyMemberRequest);
            checkAndContinue(updateMemberResponse);
            GetAccountRequest accountRequest = new GetAccountRequest();
            accountRequest.setAccountId(userToken.getAccountId());
            GetAccountResponse accountResponse = passportAPI.getAccountByAccountId(accountRequest);
            checkAndContinue(accountResponse);
            AccountMember accountMember = transfor(AccountMember.class, accountResponse);

            String name;
            String headImage;
            Member member = accountMember.getMember();
            //优先获取member的数据
            if (QDStringUtil.isNotNull(member)) {
                name = member.getMemberName();
                headImage = member.getMemberAvatar();
            }
            //member为空就获取account信息
            else {
                Account user = accountMember.getUser();
                name = user.getAccountNickName();
                headImage = user.getAccountAvatar();
            }
            //这里向融云同步用户信息
            RongCloudApiHttpClient.refreshUser(userToken.getAccountId(), name, headImage);
            resizeMemberAvatar(member);
            //增加家庭状况的输出
            if (QDStringUtil.isNotNull(member.getHomeSituationIndex())) {
                String homeSituation = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_FAMILY_STATUS.getGroupName(), String.valueOf(member.getHomeSituationIndex()));
                accountMember.getMember().setHomeSituation(homeSituation);
            }

            data.setEntity(accountMember);
            response.setData(data);

            return response;

        } catch (Exception e) {
            return handleException(UpdateUserInfoResponseData.class, e);
        }
    }

    @ExplainAnnotation(explain = "物业费代扣状态维护")
    @HTTP(alias = "updateMemberPayStatus", isRequireAuth = true, isNeadToken = true)
    public Response<UpdateMemberPayStatusResponseData> updateMemberPayStatus(UpdateMemberPayStatusRequest request, UserToken userToken) {

        Response<UpdateMemberPayStatusResponseData> response = new Response<UpdateMemberPayStatusResponseData>();
        try {
            UpdataPayStatusRequest updataPayStatusRequest = new UpdataPayStatusRequest();
            updataPayStatusRequest.setMemberId(userToken.getMemberId());
            updataPayStatusRequest.setPayStatus(request.getPayStatus());
            UpdataPayStatusResponse updataPayStatusResponse = profileAPI.modifyPayStatus(updataPayStatusRequest);
            checkAndContinue(updataPayStatusResponse);
            //如果开通代扣成功，发送短信提醒
            if (request.getPayStatus().intValue() == 1) {
                GetMemberRequest memberRequest = new GetMemberRequest();
                memberRequest.setMemberId(userToken.getMemberId());
                GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);
                if (HttpStatus.OK.getStatusCode() == memberResponse.getReturnInfo().getCode()) {
                    MemberInfo memberInfo = memberResponse.getMemberInfo();
                    if (QDStringUtil.isNotNull(memberInfo) && QDStringUtil.isNotEmpty(memberInfo.getMobile())) {
                        Map<String, String> data = new HashMap<>();
                        data.put("nickName", memberInfo.getName());
                        SendSms sendSms = ApplicationContextUtil.getBeansOfType(SendSms.class);
                        sendSms.send(SmsAction.OPEN_PROPERTY_FEE_PAY, memberInfo.getMobile(), data);
                    }
                }
            }
            response.setCode(HttpStatus.OK.getStatusCode());
        } catch (ServiceException e) {
            return handleException(UpdateMemberPayStatusResponseData.class, e);
        }
        return response;
    }


    protected void resizeMemberAvatar(Member member) {
        if (member == null) {
            return;
        }
        String memberAvatar = member.getMemberAvatar();
        member.setMemberAvatar(imageUtil.get(memberAvatar, 200, 200));
    }

    /**
     * 获取当前手机号是否为APP审核测试账号，且是打开测试通道
     *
     * @param mobile
     * @return
     */
    public boolean checkAppAuditSwitchIsOpen(String mobile) {

        /*   #APP审核测试账号  APP_AUDIT_MOBILE=13810362874
             #放行审核测试账号开关 true：放行，false：关闭 APP_AUDIT_SWITCH=true*/
        String app_audit_switch = APIPropertiesClient.getValue("APP_AUDIT_SWITCH");
        if ("true".equals(app_audit_switch)) {
            String mobileStr = APIPropertiesClient.getValue("APP_AUDIT_MOBILE");
            if (QDStringUtil.isNotEmpty(mobileStr)) {
                String[] mobileArray = mobileStr.split(",");
                List<String> mobileList = Arrays.asList(mobileArray);
                if (mobileList.contains(mobile)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 检查手机(注册逻辑)
     *
     * @param mobile
     * @param projectId
     * @param deviceId
     * @param latitud
     * @param longitude
     * @return
     * @throws Exception
     */
    private CheckMobileResultDTO checkMobileForRegister(String mobile, String projectId, String deviceId, String latitud, String longitude, AppDevice appDevice) throws Exception {

        CheckMobileResultDTO checkMobileResultDTO = null;

        if (!QDStringUtil.isMobile(mobile)) {   //手机号码无效
            checkMobileResultDTO = new CheckMobileResultDTO(Constant.ResultCodeEnum.INVALID_MOBILE_205.toInteger(), Constant.CheckMobileMsgEnum.INVALID_MOBILE.msg);
        } else {
            if (QDStringUtil.isEmpty(deviceId)) { //设备号未填写
                checkMobileResultDTO = new CheckMobileResultDTO(HttpStatus.BAD_REQUEST.getStatusCode(), Constant.CheckMobileMsgEnum.NO_DEVICEID_REGISTER.msg);
            } else {
                MemberDeviceRequest memberDeviceRequest = new MemberDeviceRequest();
                memberDeviceRequest.setAppDevice(appDevice);
                memberDeviceRequest.setMobile(mobile);
                memberDeviceRequest.setDeviceNo(deviceId);
                memberDeviceRequest.setType("reg");
                BaseResponse response = newPassportService.checkDeviceNo(memberDeviceRequest);
                int resultCode = response.getReturnInfo().getCode();

                if (HttpStatus.BAD_REQUEST.getStatusCode() != resultCode) {
                    if (!checkDistance(projectId, latitud, longitude)) { //检查范围有效性，无效则语音
                        checkMobileResultDTO = new CheckMobileResultDTO(Constant.ResultCodeEnum.VOICE_412.toInteger(), "");
                    } else {
                        checkMobileResultDTO = new CheckMobileResultDTO(Constant.ResultCodeEnum.SMS_411.toInteger(), "");
                    }
                } else {
                    checkMobileResultDTO = new CheckMobileResultDTO(resultCode, response.getReturnInfo().getMessage());
                }
            }
        }
        return checkMobileResultDTO;
    }


    /**
     * 检查手机(登陆逻辑)
     *
     * @param mobile
     * @param deviceId
     * @return
     * @throws Exception
     */
    private CheckMobileResultDTO checkMobileForLogin(String mobile, String deviceId, AppDevice appDevice, int resultCode) throws Exception {

        CheckMobileResultDTO checkMobileResultDTO = null;
        BaseResponse response = null;

        if (resultCode == 0) {
            MemberDeviceRequest memberDeviceRequest = new MemberDeviceRequest();
            memberDeviceRequest.setAppDevice(appDevice);
            memberDeviceRequest.setMobile(mobile);
            memberDeviceRequest.setDeviceNo(deviceId);
            memberDeviceRequest.setType("login");
            response = newPassportService.checkDeviceNo(memberDeviceRequest);
            resultCode = response.getReturnInfo().getCode();
        }

        if (Constant.ResultCodeEnum.DEVICE_409.toInteger() == resultCode) {

            checkMobileResultDTO = new CheckMobileResultDTO(Constant.ResultCodeEnum.VOICE_412.toInteger(), Constant.CheckMobileMsgEnum.OUT_DISTANCE_VOICE_LGOIN.msg);

        } else if (HttpStatus.NOT_FOUND.getStatusCode() == resultCode) {

            checkMobileResultDTO = new CheckMobileResultDTO(HttpStatus.OK.getStatusCode(), "");
        } else {
            checkMobileResultDTO = new CheckMobileResultDTO(resultCode, response.getReturnInfo().getMessage());
        }
        return checkMobileResultDTO;
    }


    /**
     * 检查给定坐标是否在指定社区范围内
     *
     * @return 在范围:true 不在范围:false
     * @throws ServiceException
     * @throws TException
     */
    private boolean checkDistance(String projectId, String latitud, String longitude) throws ServiceException, TException {

        if (QDStringUtil.isNotEmpty(latitud) && QDStringUtil.isNotEmpty(longitude)) {
            GetProjectGPSDistanceRequest projectGPSDistanceRequest = new GetProjectGPSDistanceRequest();
            projectGPSDistanceRequest.setProjectId(Long.parseLong(projectId));
            projectGPSDistanceRequest.setLat(Double.parseDouble(latitud));
            projectGPSDistanceRequest.setLng(Double.parseDouble(longitude));
            GetProjectGPSDistanceResponse projectGPSDistanceResponse = projectReadService.getProjectGPSDistance(projectGPSDistanceRequest);
            checkAndContinue(projectGPSDistanceResponse);
            Double distance = projectGPSDistanceResponse.getDistance();
            String maxDistance = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_CHECK_DISTANCE.getGroupName(),Constant.Dict_K_V_Enum.DICT_CHECK_DISTANCE.getDictKey());
            if (QDStringUtil.isNotNull(distance) && QDStringUtil.isNotNull(maxDistance)) {
                logger.info("projectId is " + projectId + " , latitud is " + latitud + " , longitude is " + longitude + ", distance is :" + distance + " maxDistance is :" + maxDistance);
                if (Double.parseDouble(maxDistance) > distance) {  // 在阀值范围内，采用短信验证码
                    return true;
                }
            }
        }
        return false;
    }

}
