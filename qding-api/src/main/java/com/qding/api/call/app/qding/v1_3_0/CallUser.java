package com.qding.api.call.app.qding.v1_3_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v1_3_0.struct.user.*;
import com.qding.api.call.app.qding.v1_3_0.struct.user.request.*;
import com.qding.api.call.app.qding.v1_3_0.struct.user.request.BindMobileRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.user.request.ForgetPassWordRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.user.response.data.*;
import com.qding.api.call.app.qding.v1_3_0.struct.user.response.data.ForgetPassWordResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.ip.IPUtil;
import com.qding.api.ip.TaoBaoCity;
import com.qding.api.process.GlobalInstance;
import com.qding.api.process.security.UserToken;
import com.qding.api.rongcloud.RongCloudApiHttpClient;
import com.qding.api.smart.validate.AccountMemberValidate;
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
import com.qding.api.verifycode.store.StoreDevice;
import com.qding.api.weixin.WXOAuth2;
import com.qding.api.weixin.json.WXOAuthAccessToken;
import com.qding.brick.pojo.biz.*;
import com.qding.brick.remote.biz.*;
import com.qding.brick.struts.request.GetOwnerRoomsRequest;
import com.qding.brick.struts.request.UpdateOwnerMobileRequest;
import com.qding.brick.struts.response.GetOwnerRoomsResponse;
import com.qding.brick.struts.response.OwnerMobileResponse;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hotcycle.service.IGCMemberRemoteService;
import com.qding.hotcycle.struct.request.OptGroupMemberRequest;
import com.qding.member.common.constant.MemberRole;
import com.qding.member.model.MemberAddress;
import com.qding.member.model.MemberProject;
import com.qding.member.model.RoomBindQRcode;
import com.qding.member.model.RoomBindQRcodeLog;
import com.qding.member.model.dto.MemberBindRoomDTO;
import com.qding.member.rpc.*;
import com.qding.member.rpc.request.member.BindRoom;
import com.qding.member.rpc.request.member.MemberAddressCondition;
import com.qding.member.rpc.request.member.MemberRoomSyncRequest;
import com.qding.member.rpc.response.invitation.InvitationPageResponse;
import com.qding.member.rpc.response.invitation.InvitationResponse;
import com.qding.member.rpc.response.memberaddress.MemberAddressListResponse;
import com.qding.member.rpc.response.memberaddress.MemberAddressResponse;
import com.qding.member.rpc.response.memberroom.GetMemberRoomResponse;
import com.qding.member.rpc.response.memberroom.GetSelfRoomResponse;
import com.qding.member.rpc.response.project.MemberProjectResponse;
import com.qding.member.rpc.response.qrcode.QRCodeRoomBindResponse;
import com.qding.member.rpc.response.roombindapply.MemberBindRoomDTOsResponse;
import com.qding.member.rpc.response.roombindapply.RoomBindQRcodeResponse;
import com.qding.message.constant.MsgConstant;
import com.qding.message.service.IMessageService;
import com.qding.message.struct.request.MsgSendByMidRequest;
import com.qding.message.struct.response.MsgSendResponse;
import com.qding.passport.service.IPassportService;
import com.qding.passport.service.IProfileService;
import com.qding.passport.service.new_version.INewPassportService;
import com.qding.passport.struct.AccountInfo;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.*;
import com.qding.passport.struct.response.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CallUser extends Callable {
    private static final Logger logger = Logger.getLogger(CallUser.class);

    public CallUser() {
    }

    @Autowired
    private IPassportService passportAPI;

    @Autowired
    private IProfileService profileAPI;

    @Autowired
    private IMemberRoomRPC memberRoomAPI;

    @Autowired
    private IInvitationRPC invitationAPI;

    @Autowired
    private RoomReadRemote roomReadRemoteService;

    @Autowired
    private RoomOwnerRemote roomOwnerRemoteService;

    @Autowired
    private IMemberSuggestionRPC suggestionAPI;

    @Autowired
    private IMemberAddressRPC memberAddressService;

    @Autowired
    private ImageUtil imageUtil;

    @Autowired
    private IRoomBindApplyRPC roomBindApplyService;

    @Autowired
    private IRoomBindQRcodeRPC roomBindQRcodeService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private RegionRemote regionRemoteService;

    @Autowired
    private AppConfigRemote appConfigRemote;

    @Autowired
    private IMemberProjectRPC memberProjectService;

    @Autowired
    private ProjectReadRemote projectReadRemote;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private INewPassportService newPassportService;

    @Autowired
    private IGCMemberRemoteService igcMemberRemoteService;



    /**
     * 手机号是否已注册
     *
     * @param request
     * @return
     */
    @HTTP(alias = "checkMobileIsRegister")
    public Response<CheckMobileIsRegisterResponseData> checkMobileIsRegister(CheckMobileIsRegisterRequest request, UserToken userToken) {
        try {

            Response<CheckMobileIsRegisterResponseData> response = new Response<CheckMobileIsRegisterResponseData>();

            CheckUniqueMobileResponse checkResponse = passportAPI.checkUniqueMobile(
                    transfor(CheckUniqueMobileRequest.class, request));

            checkAndContinue(checkResponse);

            response.setData(new CheckMobileIsRegisterResponseData());

            return response;

        } catch (Exception e) {

            return handleException(CheckMobileIsRegisterResponseData.class, e);
        }
    }


    @HTTP(alias = "checkInvitationCodeIsActivate")
    public Response<GetInvitationCodeIsActivateResponseData> getInvitationCodeIsActivate(GetInvitationCodeIsActivateRequest request) {

        Response<GetInvitationCodeIsActivateResponseData> response = new Response<GetInvitationCodeIsActivateResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());

        try {
            GetInvitationCodeIsActivateResponseData data = new GetInvitationCodeIsActivateResponseData();

            String code = request.getQrcode();

            if (code.startsWith("HOUSE_KEEPER_")) {
                String id = code.replace("HOUSE_KEEPER_", "");
                QRCodeRoomBindResponse  qrCodeRoomBindResponse = roomBindQRcodeService.findById(id);
                checkAndContinue(qrCodeRoomBindResponse);
                RoomBindQRcode roomBindQRcode = qrCodeRoomBindResponse.getQrcode();
                if (roomBindQRcode != null && QDStringUtil.isNotEmpty(roomBindQRcode.getId())) {
                    //获取room
                    Room room = roomReadRemoteService.get(Long.parseLong(roomBindQRcode.getRoomId()));
                    com.qding.api.call.app.qding.v1_3_0.struct.brick.Room roomInfo =
                            transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);

                    Invitation invitation = new Invitation();
                    invitation.setQrcode(request.getQrcode());
                    invitation.setDescription("千丁房屋管家邀请您绑定房间");
                    invitation.setHkIndentity((short) roomBindQRcode.getRole());
                    invitation.setCreateTime(roomBindQRcode.getCreateTime());
                    invitation.setValidityEndAt(0L);
                    invitation.setValidityStartAt(0L);
                    invitation.setRoom(roomInfo);
                    data.setEntity(invitation);

                } else {
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "无效二维码");
                }
            } else if (code.startsWith("APPLY_GROUP_")) {

                String gcRoomId = code.replace("APPLY_GROUP_", "");
                String accountId = request.getAccountId();
                if (QDStringUtil.isEmpty(accountId)) {
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "无效二维码");
                }
                OptGroupMemberRequest optGroupMemberRequest = new OptGroupMemberRequest();
                optGroupMemberRequest.setGcRoomId(gcRoomId);
                optGroupMemberRequest.setUserId(accountId);
                optGroupMemberRequest.setOptAt("邀请码");

                BaseResponse baseResponse = igcMemberRemoteService.joinGroupApply(optGroupMemberRequest);
                checkAndContinue(baseResponse);

            } else {
                InvitationResponse invitationResponse = invitationAPI.checkInvitationByCode(code);
                checkAndContinue(invitationResponse);

                com.qding.member.model.Invitation invitation = invitationResponse.getInvitation();
                String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
                Short role = invitation.getRole();
                if (Integer.parseInt(initVersion) < 220000 && (9 == role || 10 == role)) {
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "请升级APP到最新版本才能以" + (role == 9 ? "保姆" : "司机") + "身份加入千丁哦");
                }

                if (invitation != null) {

                    Room room = roomReadRemoteService.get(Long.parseLong(invitation.getRoomId()));

                    Invitation inv = transfor(Invitation.class, invitation);

                    com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r =
                            transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);

                    inv.setRoom(r);

                    data.setEntity(inv);
                }
            }

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GetInvitationCodeIsActivateResponseData.class, e);
        }
    }

    /**
     * 发送短信验证码
     *
     * @param request
     * @return
     */
    @HTTP(alias = "sendVerificationCode")
    public Response<SendVerificationCodeResponseData> sendVerificationCode(SendVerificationCodeRequest request) {

        String platform = request.getAppDevice().getQdPlatform();

        Response<SendVerificationCodeResponseData> response = new Response<SendVerificationCodeResponseData>();

        if ("ios".equals(platform.toLowerCase()) || "android".equals(platform.toLowerCase()) || request.getAction() != 1) {
            String mobile = request.getMobile();

            int action = request.getAction();

            try {
                SmsAction smsAction = SmsAction.to(action);

                VerifyCode.sendCode(
                        new SendCodeConfig(
                                System.currentTimeMillis() + 100 * 1000,
                                smsAction,
                                new DefaultGeneratorCode(),
                                new RedisStoreDevice(),
                                new SmsSendCannel(mobile, smsAction),
                                10 * 60,
                                1
                        )
                );

                response.setData(new SendVerificationCodeResponseData());

                return response;

            } catch (Exception e) {

                return handleException(SendVerificationCodeResponseData.class, e);
            }
        } else {
            SendVerificationCodeResponseData data = new SendVerificationCodeResponseData();
            data.setMessage("验证码发送成功 ");
            response.setData(data);
            return response;
        }

    }


    /**
     * 发送短信验证码(跟随系统验证码验证)
     *
     * @param request
     * @return
     */
    @HTTP(alias = "sendVerificationCodeBySys")
    public Response<SendVerificationCodeResponseData> sendVerificationCodeBySys(SendVerificationCodeBySysRequest request) {

        String mobile = request.getMobile();

        int action = request.getAction();

        ImgVerify imgVerify = new ImgVerify(request.getSysCode().toLowerCase(), request.getSysVerifyKey());

        try {
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
                            1
                    )
            );

            Response<SendVerificationCodeResponseData> response = new Response<SendVerificationCodeResponseData>();

            response.setData(new SendVerificationCodeResponseData());

            return response;

        } catch (Exception e) {

            return handleException(SendVerificationCodeResponseData.class, e);
        }

    }


    /**
     * 验证码是否有效
     *
     * @param request
     * @return
     */
    @HTTP(alias = "checkVerificationCodeIsEnable")
    public Response<GetVerificationCodeIsEnableResponseData> verificationCodeIsEnable(GetVerificationCodeIsEnableRequest request) {

        String mobile = request.getMobile();

        String code = request.getVerifyCode();

        Integer action = request.getAction();

        try {
            SmsAction smsAction = SmsAction.to(action);

            if (smsAction == null) {

                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "无效的action");

            }

            VerifyCode.verifyCode(
                    new VerifyCodeConfig(
                            smsAction,
                            code,
                            mobile,
                            new RedisStoreDevice()
                    )
            );

            Response<GetVerificationCodeIsEnableResponseData> response = new Response<GetVerificationCodeIsEnableResponseData>();

            response.setData(new GetVerificationCodeIsEnableResponseData());

            return response;

        } catch (Exception e) {

            return handleException(GetVerificationCodeIsEnableResponseData.class, e);
        }

    }


    /**
     * 修改密码
     *
     * @param request
     * @return
     */
    @HTTP(alias = "updatePassWord", isRequireAuth = true, isNeedReLoginWhenExpire = true)
    public Response<UpdateUserPassWordResponseData> updatePassWord(UpdateUserPassWordRequest request) {

        try {
            UpdataPassWordResponse passWordResponse = profileAPI.updatePassWord(
                    transfor(UpdataPassWordRequest.class, request));

            checkAndContinue(passWordResponse);

            Response<UpdateUserPassWordResponseData> response = new Response<UpdateUserPassWordResponseData>();

            response.setData(new UpdateUserPassWordResponseData());

            return response;

        } catch (Exception e) {

            return handleException(UpdateUserPassWordResponseData.class, e);
        }
    }

    /**
     * 忘记密码
     *
     * @param request
     * @return
     */
    @HTTP(alias = "forgetPassWord")
    public Response<ForgetPassWordResponseData> forgetPassWord(ForgetPassWordRequest request) {

        Response<ForgetPassWordResponseData> response = new Response<ForgetPassWordResponseData>();

        String mobile = request.getMobile();

        String code = request.getVerifyCode();

        try {

            VerifyCode.verifyCode(
                    new VerifyCodeConfig(
                            SmsAction.FORGET_PWD,
                            code,
                            mobile,
                            new RedisStoreDevice()
                    )
            );

            com.qding.passport.struct.response.ForgetPassWordResponseData forgetResponse =
                    profileAPI.updatePassWordForForget(
                            transfor(
                                    com.qding.passport.struct.request.ForgetPassWordRequest.class, request
                            )
                    );

            checkAndContinue(forgetResponse);

            response.setData(new ForgetPassWordResponseData());

            return response;

        } catch (ServiceException e) {

            return handleException(ForgetPassWordResponseData.class, e);
        }
    }


    /**
     * 提建议
     *
     * @param request
     * @return
     */
    @HTTP(alias = "addSuggest")
    public Response<AddSuggestResponseData> addSuggest(AddSuggestRequest request) {

        try {
            Response<AddSuggestResponseData> response = new Response<AddSuggestResponseData>();

            String memberId = request.getMemberId();

            String suggestion = request.getSuggestion();

            if (QDStringUtil.isEmpty(request.getSuggestion()) || "".equals(suggestion.trim())) {

                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());

                AddSuggestResponseData data = new AddSuggestResponseData();

                data.setMessage("建议内容为必填项且不能为空格");

                response.setData(data);

            } else {
                BaseResponse  baseResponse = suggestionAPI.suggest(memberId, suggestion);
                checkAndContinue(baseResponse);
                response.setData(new AddSuggestResponseData());
            }


            return response;

        } catch (Exception e) {

            return handleException(AddSuggestResponseData.class, e);
        }
    }


    /**
     * 用户注册
     *
     * @param request
     * @return
     */
    @HTTP(alias = "register")
    @Deprecated
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
            registerRequest.setRegIp(QDStringUtil.isNotEmpty(ip)?ip:"none");
            RegisterResponse rpcResponse = passportAPI.register(registerRequest);

            checkAndContinue(rpcResponse);

            UserRegisterResponseData userRegisterResponse = transfor(UserRegisterResponseData.class, rpcResponse);

            String memberId = userRegisterResponse.getEntity().getMember().getMemberId();

            String qrcode = request.getQrcode();

            String roomId = "";

            if (QDStringUtil.isNotNull(memberId) && QDStringUtil.isNotEmpty(qrcode)) {

                GetSelfRoomResponse selfRoomResponse = memberRoomAPI.qrbind(memberId, qrcode);

                checkAndContinue(selfRoomResponse);

                com.qding.member.model.MemberRoom rm = selfRoomResponse.getMemberRoom();

                roomId = rm.getRoomId();

            }

            AccountMember accountMember = userRegisterResponse.getEntity();

            afterLogin(accountMember,version);

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

    @Autowired
    private WXOAuth2 wxoAuth2;

    /**
     * 第三方帐户登录
     *
     * @param request
     * @return
     */
    @HTTP(alias = "thirdLogin", isLogin = true)
    public Response<ThirdLoginResponseData> thirdLogin(ThirdLoginRequest request, HttpServletRequest httpServletRequest) {

        Response<ThirdLoginResponseData> response = new Response<ThirdLoginResponseData>();
        ThirdLoginResponseData data = new ThirdLoginResponseData();

        try {
            String version = request.getAppDevice().getQdVersion();
            UnionLoginRequest unionLoginRequest = transfor(UnionLoginRequest.class, request);
            Integer sourceType = request.getSourceType();
            String partnerId = request.getPartnerId();
            WXUser wxUser = null;

            //如果是微信登录
            if (sourceType == Constant.REGISTER_SOURCE_WEIXIN) {

                WXOAuthAccessToken accessToken = wxoAuth2.getWXOAuthAccessToken(partnerId);
                unionLoginRequest.setPartnerId(accessToken.getUnionid());
                //公众号ID 应用密匙
                wxUser = wxoAuth2.getWXUser(accessToken.getAccess_token(), accessToken.getOpenid());
            }

            String ip = IPUtil.getIpAddress(httpServletRequest);

            TaoBaoCity city = IPUtil.getIpCity(ip);

            unionLoginRequest.setLoginIp(ip);

            unionLoginRequest.setProvinceName(city.getProvince());

            unionLoginRequest.setCityName(city.getCity());

            unionLoginRequest.setAppDevice(QDStringUtil.isNotNull(request.getAppDevice()) ? request.getAppDevice() : null);

            unionLoginRequest.setRegIp(QDStringUtil.isNotEmpty(ip)?ip:"none");

            UnionLoginResponse unionLoginResponse = passportAPI.unionLogin(unionLoginRequest);

            checkAndContinue(unionLoginResponse);

            data = transfor(ThirdLoginResponseData.class, unionLoginResponse);

            AccountMember accountMember = data.getEntity();

            //如果当前还没有绑定手机号不生成token
            if (QDStringUtil.isNotNull(unionLoginResponse.getMemberInfo())) {
                String userToken = afterLogin(accountMember,version);
                data.setBaseToken(userToken);
            }

            data.setWxUser(wxUser);
            data.getEntity().setImToken(unionLoginResponse.getAccountInfo().getRongCloudToken());
            if ( QDStringUtil.isNotEmpty(request.getAppDevice().getDeviceId()) && QDStringUtil.isNotNull(data.getEntity().getMember())) {
                //通知单点登录系统
                MemberDeviceRequest memberDeviceRequest = new MemberDeviceRequest();
                memberDeviceRequest.setMemberId(data.getEntity().getMember().getMemberId());
                memberDeviceRequest.setDeviceNo(request.getAppDevice().getDeviceId());
                newPassportService.sendLoginMessage(memberDeviceRequest);
            }
            response.setData(data);

            return response;

        } catch (ServiceException e) {

            return handleException(ThirdLoginResponseData.class, e);
        }

    }

    /**
     * 绑定手机号
     *
     * @param request
     * @return
     */
    @HTTP(alias = "bindMobile")
    public Response<BindMobileResponseData> bindMobile(BindMobileRequest request) {

        Response<BindMobileResponseData> response;

        try {

            String code = request.getVerifyCode();

            VerifyCode.verifyCode(
                    new VerifyCodeConfig(
                            SmsAction.BIND_MOBILE,
                            code,
                            request.getMobile(),
                            new RedisStoreDevice()
                    )
            );

            response = new Response<BindMobileResponseData>();

            BindMobileResponse bindMobileResponse = passportAPI.bindMobile(transfor(com.qding.passport.struct.request.BindMobileRequest.class, request));

            checkAndContinue(bindMobileResponse);

            BindMobileResponseData data = transfor(BindMobileResponseData.class, request);

            autoBindOwnerRoom(data.getEntity().getMember());
            //如果当前还没有绑定手机号不生成token
            if (QDStringUtil.isNotNull(data.getEntity().getMember())) {
                AccountMember accountMember = new AccountMember();
                accountMember.setMember(data.getEntity().getMember());
                accountMember.setUser(data.getEntity().getUser());
                String userToken = afterLogin(accountMember,request.getAppDevice().getQdVersion());
                data.setBaseToken(userToken);
                data.getEntity().setUserToken(userToken);
                if ( QDStringUtil.isNotEmpty(request.getAppDevice().getDeviceId())) {
                    //通知单点登录系统
                    MemberDeviceRequest memberDeviceRequest = new MemberDeviceRequest();
                    memberDeviceRequest.setMemberId(data.getEntity().getMember().getMemberId());
                    memberDeviceRequest.setDeviceNo(request.getAppDevice().getDeviceId());
                    newPassportService.sendLoginMessage(memberDeviceRequest);
                }
            }
            response.setData(data);

        } catch (ServiceException e) {

            return handleException(BindMobileResponseData.class, e);
        }

        return response;
    }

    /**
     * 第三方帐户是否绑定
     *
     * @param request
     * @return
     */
    @HTTP(alias = "thirdBinding")
    public Response<ThirdBindingResponseData> thirdBinding(ThirdBindingRequest request, HttpServletRequest httpServletRequest) {

        CheckUnionLoginResponse checkResponse = passportAPI.checkUnionLogin(
                transfor(CheckUnionLoginRequest.class, request));

        Response<ThirdBindingResponseData> response = new Response<ThirdBindingResponseData>();

        if (HttpStatus.OK.getStatusCode() == checkResponse.getReturnInfo().getCode()) {

            try {
                UnionLoginRequest unionLoginRequest = transfor(UnionLoginRequest.class, request);
                String ip = IPUtil.getIpAddress(httpServletRequest);
                unionLoginRequest.setRegIp(QDStringUtil.isNotEmpty(ip)?ip:"none");
                UnionLoginResponse unionLoginResponse = passportAPI.unionLogin(unionLoginRequest);

                checkAndContinue(unionLoginResponse);

                AccountInfo accountInfo = unionLoginResponse.getAccountInfo();

                String imToken ="";
                if (QDStringUtil.isNotNull(accountInfo))
                    imToken = saveImUserToken(accountInfo.getId());//保存IM token到数据库

                ThirdBindingResponseData data = transfor(ThirdBindingResponseData.class, unionLoginResponse);
                data.getEntity().setImToken(imToken);
                response.setData(data);

                return response;

            } catch (ServiceException e) {

                return handleException(ThirdBindingResponseData.class, e);
            }

        } else {

            ThirdBindingResponseData thResponse = new ThirdBindingResponseData();

            thResponse.setMessage("未绑定第三方账户");

            response.setData(thResponse);

            response.setCode(HttpStatus.OK.getStatusCode());

            return response;
        }

    }


    /**
     * 通过手机号登录
     *
     * @param request
     * @return
     */
    @HTTP(alias = "loginByMobile", isLogin = true,checkVersion = false)
    public Response<UserLoginResponseData> loginByMobile(UserLoginRequest request, HttpServletRequest httpServletRequest) {

        try {
            String version = request.getAppDevice().getQdVersion();

            Response<UserLoginResponseData> response = new Response<UserLoginResponseData>();

            LoginRequest loginRequest = transfor(LoginRequest.class, request);

            String ip = IPUtil.getIpAddress(httpServletRequest);

            TaoBaoCity city = IPUtil.getIpCity(ip);

            loginRequest.setLoginIp(ip);

            loginRequest.setProvinceName(city.getProvince());

            loginRequest.setCityName(city.getCity());

            loginRequest.setAppDevice(QDStringUtil.isNotNull(request.getAppDevice()) ? request.getAppDevice() : null);

            LoginResponse loginResponse = passportAPI.loginByMobile(loginRequest);

            checkAndContinue(loginResponse);

            UserLoginResponseData data = transfor(UserLoginResponseData.class, loginResponse);

            String userToken = afterLogin(data.getEntity(),version);

            data.setBaseToken(userToken);

            response.setData(data);

            return response;

        } catch (ServiceException e) {

            return handleException(UserLoginResponseData.class, e);
        }
    }


    /**
     * 修改用户信息
     *
     * @param request
     * @return
     */
    @HTTP(alias = "updateUserInfo", isRequireAuth = true)
    @Deprecated
    public Response<UpdateUserInfoResponseData> updateUserInfo(UpdateUserInfoRequest request) {

        try {

            ModifyMemberRequest modifyMemberRequest = transfor(ModifyMemberRequest.class, request);

            String headImg = modifyMemberRequest.getHeadImg();

            modifyMemberRequest.setHeadImg(getHeadImg(headImg));

            Response<UpdateUserInfoResponseData> response = new Response<UpdateUserInfoResponseData>();

            //临时解决如果性别传入的是2 对应后台数据为 -1
            if( QDStringUtil.isNotEmpty(request.getMemberInfo().getMemberGender()) && "2".equals(request.getMemberInfo().getMemberGender())) {
                modifyMemberRequest.setGender(-1);
            }

            modifyMemberRequest.setSkipCheck(true);
            //修改信息
            ModifyMemberResponse updateMemberResponse = profileAPI.modifyMember(modifyMemberRequest);

            checkAndContinue(updateMemberResponse);

            UpdateUserInfoResponseData data = transfor(UpdateUserInfoResponseData.class, request);

            Member member = data.getEntity().getMember();

            GetAccountResponse accountResponse = passportAPI.getAccountByAccountId(transfor(GetAccountRequest.class, request));

            checkAndContinue(accountResponse);

            AccountInfo user = accountResponse.getAccountInfo();

            String name;
            String headImage;

            //优先获取member的数据
            if (member != null) {
                name = member.getMemberName();
                headImage = member.getMemberAvatar();
            }
            //member为空就获取account信息
            else {
                name = user.getNickName();
                headImage = user.getHeadImg();
            }

            //这里向融云同步用户信息
            RongCloudApiHttpClient.refreshUser(String.valueOf(request.getAccountId()), name, headImage);

            resizeMemberAvatar(member);

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(UpdateUserInfoResponseData.class, e);
        }
    }


    public String getHeadImg (String headImg){
        String headImgStr = "";
        if (QDStringUtil.isNotEmpty(headImg)) { //修改头像 提取七牛key
            boolean isPubliscQiniu = headImg.startsWith("https://img1.qdingnet.com");
            if (!isPubliscQiniu) {
                Pattern matches = Pattern.compile("http://.*(qiniu:qding.*)\\?");
                Matcher matcher = matches.matcher(headImg);
                if (matcher.find()) {
                    headImgStr =matcher.group(1);
                }
            } else {
                String[] userImag = headImg.split("\\?"); //如果是七牛共有图片
                headImgStr=userImag[0];
            }
        }
        return headImgStr;
    }


    /**
     * 修改手机号
     *
     * @param request
     * @return
     */
    @HTTP(alias = "updateMobile", isRequireAuth = true, isNeedReLoginWhenExpire = true)
    public Response<UpdateMobileResponseData> updateMobile(UpdateMobileRequest request) {

        try {

            Response<UpdateMobileResponseData> response = new Response<UpdateMobileResponseData>();

            String mobile = request.getNewMobile();

            String code = request.getVerifyCode();

            VerifyCode.verifyCode(
                    new VerifyCodeConfig(
                            SmsAction.UPDATE_MOBILE,
                            code,
                            mobile,
                            new RedisStoreDevice()
                    )
            );

            ModifyMemberMobileResponse memberResponse = profileAPI.modifyMemberMobile(transfor(ModifyMemberMobileRequest.class, request));

            checkAndContinue(memberResponse);

            UpdateMobileResponseData data = transfor(UpdateMobileResponseData.class, request);

            Member member = data.getEntity().getMember();

            resizeMemberAvatar(member);

            response.setData(data);

            return response;

        } catch (ServiceException e) {

            return handleException(UpdateMobileResponseData.class, e);
        }

    }


    /**
     * 绑定房间
     *
     * @param request
     * @return
     */
    @HTTP(alias = "roomBinding", isRequireAuth = true, isNeadToken = true)
    public Response<RoomBindingResponseData> roomBinding(RoomBindingRequest request,UserToken token) {

        Response<RoomBindingResponseData> response = new Response<RoomBindingResponseData>();
        String mobile = request.getMobile();
        String memberId = request.getMemberId();
        RoomBindingResponseData data = new RoomBindingResponseData();
        String platform = request.getAppDevice().getQdPlatform();
        String qrcode = request.getQrcode();
        String roomId = "";
        try {
            if (QDStringUtil.isNotEmpty(qrcode)) {

                ProjectRoom pr = null;
                com.qding.member.model.MemberRoom rm = null;

                if (qrcode.startsWith("HOUSE_KEEPER_")) {

                    RoomBindQRcodeLog roomBindQRcodeLog = transfor(RoomBindQRcodeLog.class, request);
                    roomBindQRcodeLog.setQrcodeId(qrcode.replace("HOUSE_KEEPER_", ""));
                    RoomBindQRcodeResponse roomBindQRcodeResponse = roomBindQRcodeService.scanQRcode(roomBindQRcodeLog);
                    checkAndContinue(roomBindQRcodeResponse);
                    rm = roomBindQRcodeResponse.getMemberRoom();

                } else {
                    GetSelfRoomResponse selfRoomResponse = memberRoomAPI.qrbind(memberId, qrcode);
                    checkAndContinue(selfRoomResponse);
                    rm = selfRoomResponse.getMemberRoom();
                }

                pr = transfor(ProjectRoom.class, rm);
                roomId = rm.getRoomId();
                Room room = roomReadRemoteService.get(Long.parseLong(roomId));
                com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r =
                        transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);
                pr.setRoom(r);
                pr.setAuditStatus(1);//这里绑定的都为非身份证申请绑定所以返回为1：审核通过
                response.setData(new RoomBindingResponseData(pr));

            } else {
                   try{
                       if( !request.getMemberId().equals(token.getMemberId()) ){
                        logger.info("mid:"+token.getMemberId()+",传入会员和当前登陆实际会员ID不同，可能为作弊用户");
                        data.setMessage("当前用户状态异常，请退出重新登录");
                        response.setData(data);
                        response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                        return  response;
                    }
                    OwnerMobileResponse ownerMobileResponse = roomOwnerRemoteService.getOwnerMobileByRoomId(Long.parseLong(request.getRoomId()));
                    checkAndContinue(ownerMobileResponse);
                    Set<String> mobileSet = ownerMobileResponse.getMobiles();
                    if(CollectionUtils.isNotEmpty(mobileSet) && !mobileSet.contains(request.getMobile())){
                        logger.info("moible:"+request.getMobile()+",不在该房屋所属业主手机号的范围内，可能为作弊用户");
                        data.setMessage("会员数据与实际不符");
                        response.setData(data);
                        response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                        return  response;
                    }
                }catch (Exception ex) {
                    return handleException(RoomBindingResponseData.class, ex);
                }

                if (request.getHideMobile() == 1) {
                    //v2.7增加处理 用于H5手机号加密模式下的手动绑定房间时完善手机号操作
                    UpdateOwnerMobileRequest updateOwnerMobileRequest = new UpdateOwnerMobileRequest();
                    updateOwnerMobileRequest.setMobile(request.getMobile());
                    updateOwnerMobileRequest.setRoomId(Long.parseLong(request.getRoomId()));
                    BaseResponse baseResponse = roomOwnerRemoteService.updateOwnerMobileByRoomId(updateOwnerMobileRequest);
                    checkAndContinue(baseResponse);
                } else {
                    String verifyCode = request.getVerifyCode();

                    if (QDStringUtil.isEmpty(verifyCode))
                        throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "验证码不能为空");

                    VerifyCode.verifyCode(
                            new VerifyCodeConfig(
                                    SmsAction.BIND_ROOM,
                                    verifyCode,
                                    mobile,
                                    new RedisStoreDevice()
                            )
                    );
                }

                roomId = request.getRoomId();

                Integer hkIndentity = request.getHkIndentity();//身份

                Room room = roomReadRemoteService.get(Long.parseLong(roomId));


                String projectId = String.valueOf(room.getProjectId());

                MemberRole  memberRole = MemberRole.fromCode(hkIndentity);

                GetSelfRoomResponse  roomResponse = memberRoomAPI.bind(
                        memberId,
                        roomId,
                        projectId,
                        memberRole
                );

                checkAndContinue(roomResponse);

                GetMemberRoomResponse memberRoomResponse = memberRoomAPI.listValidGuest(memberId, roomId);

                checkAndContinue(memberRoomResponse);

                List<com.qding.member.model.MemberRoom> lsMemberRoom =  memberRoomResponse.getList();

                if (lsMemberRoom != null && lsMemberRoom.size() > 0) {

                    com.qding.member.model.MemberRoom rm = lsMemberRoom.get(0);

                    ProjectRoom pr = transfor(ProjectRoom.class, rm);

                    com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r =
                            transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);

                    pr.setRoom(r);

                    pr.setAuditStatus(1);//这里绑定的都为非身份证申请绑定所以返回为1：审核通过

                    response.setData(new RoomBindingResponseData(pr));
                }

            }

            return response;

        } catch (Exception e) {

            return handleException(RoomBindingResponseData.class, e);
        }
    }


    /**
     * 解除房间绑定
     *
     * @param request
     * @return
     */
    @HTTP(alias = "roomUnbinding", isRequireAuth = true)
    public Response<RoomUnbindingResponseData> roomUnbinding(RoomUnbindingRequest request) {

        Response<RoomUnbindingResponseData> response = new Response<RoomUnbindingResponseData>();

        RoomUnbindingResponseData data = new RoomUnbindingResponseData();

        response.setCode(HttpStatus.OK.getStatusCode());

        try {
            String memberId = request.getMemberId();

            String roomId = request.getRoomId();

            String bindId = request.getBindId();

            //如果不是审核失败 则为解绑房屋操作
            if (QDStringUtil.isNull(bindId) || QDStringUtil.isEmpty(bindId)) {

                BaseResponse baseResponse = memberRoomAPI.unbind(memberId, roomId);

                checkAndContinue(baseResponse);

                data.setMessage("解除房间绑定成功");

            } else { //如果是审核失败的房屋则进行申请删除操作

                BaseResponse baseResponse = roomBindApplyService.deleteByApplyId(bindId);
                if (baseResponse.getReturnInfo().getCode()!=HttpStatus.OK.getStatusCode()) {
                    response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                    data.setMessage("删除房屋绑定申请失败");
                } else {
                    data.setMessage("删除房屋绑定申请成功");
                }
            }

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(RoomUnbindingResponseData.class, e);
        }
    }


    /**
     * 根据用户id获取用户信息
     *
     * @param request
     * @return
     */
    @HTTP(alias = "selUserInfo",checkVersion = false)
    public Response<GetUserInfoResponseData> getUserInfo(GetUserInfoRequest request) {

        try {
            Response<GetUserInfoResponseData> response = new Response<GetUserInfoResponseData>();

            GetAccountResponse accountResponse = passportAPI.getAccountByAccountId(transfor(GetAccountRequest.class, request));

            checkAndContinue(accountResponse);

            GetMemberResponse memberResponse = profileAPI.getMemberById(transfor(GetMemberRequest.class, request));

            checkAndContinue(memberResponse);

            Member member = transfor(Member.class, memberResponse.getMemberInfo());

            Account user = transfor(Account.class, accountResponse.getAccountInfo());

            AccountMember userEntity = new AccountMember(user, member);

            resizeMemberAvatar(member);

            GetUserInfoResponseData data = new GetUserInfoResponseData(userEntity);

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GetUserInfoResponseData.class, e);
        }

    }

    private Calendar getStartCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c;
    }

    private Calendar getEndCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return c;
    }

    private void addMonth(Calendar c, int month) {
        c.add(Calendar.MONTH, month);
    }

    /**
     * 邀请用户绑定房屋
     *
     * @param request
     * @return
     */
    @HTTP(alias = "inviteUser", isRequireAuth = true)
    public Response<InviteUserResponseData> inviteUser(InviteUserRequest request) {

        try {
            Response<InviteUserResponseData> response = new Response<InviteUserResponseData>();

            Calendar effectiveStart = getStartCalendar();

            Calendar effectiveEnd;

            Integer validityTime = request.getValidityTime();

            com.qding.member.model.Invitation requestInvitation = transfor(com.qding.member.model.Invitation.class, request);

            if (validityTime == -1) {
                requestInvitation.setValidityPeriod((short) -1);
            } else {

                //1个月
                if (validityTime == 1) {
                    effectiveEnd = getEndCalendar();
                    addMonth(effectiveEnd, 1);
                }
                //三个月
                else if (validityTime == 3) {

                    effectiveEnd = getEndCalendar();
                    addMonth(effectiveEnd, 3);
                }
                //六个月
                else if (validityTime == 6) {

                    effectiveEnd = getEndCalendar();
                    addMonth(effectiveEnd, 6);
                }
                //12个月
                else if (validityTime == 12) {

                    effectiveEnd = getEndCalendar();
                    addMonth(effectiveEnd, 12);
                } else {
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "无效的validityTime");
                }
                requestInvitation.setValidityPeriod((short) 1);
                requestInvitation.setValidityStartAt(effectiveStart.getTime().getTime());
                requestInvitation.setValidityEndAt(effectiveEnd.getTime().getTime());
            }

            Room room = roomReadRemoteService.get(Long.parseLong(request.getRoomId()));
            
            /** 
             * bugfix: 生成邀请码时，app传入用户所在社区，应该是房屋所在社区
             * APP修复需要发包，这里应急处理一下，强制设置projectId为房屋所在社区
             */
            requestInvitation.setProjectId(room.getProjectId()+"");
            
            InvitationResponse invitationResponse = invitationAPI.invitation(requestInvitation);

            checkAndContinue(invitationResponse);

            com.qding.member.model.Invitation inv = invitationResponse.getInvitation();

            Invitation entity = transfor(Invitation.class, inv);
            String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
            if (Integer.parseInt(initVersion) >= 230000) {
                String skipUrlTranslator = APIPropertiesClient.getSkipUrl("translator");
                String longUrl = skipUrlTranslator + "?skip={'entity':{'skno':'2402','id':'" + inv.getQrcode() + "','source':'api','pcode':'0'}}";
                String shortUrl = CreateShortUrlUtil.ServiceUrlConvertShortUrl(longUrl, 24 * 60 * 60L);
                entity.setQrcode(shortUrl);
            }

            com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r =
                    transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);

            entity.setRoom(r);

            InviteUserResponseData data = new InviteUserResponseData(entity);

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(InviteUserResponseData.class, e);
        }
    }


    /**
     * 邀请历史列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "inviteUserHistory", isRequireAuth = true)
    public Response<InviteUserHistoryResponseData> inviteUserHistory(InviteUserHistoryRequest request) {

        try {
            Response<InviteUserHistoryResponseData> response = new Response<InviteUserHistoryResponseData>();

            InvitationPageResponse invitationPageResponse = invitationAPI.listPage(request.getMemberId(), request.getPageNo(), request.getPageSize());

            checkAndContinue(invitationPageResponse);

            Integer total = invitationPageResponse.getTotal();

            List<com.qding.member.model.Invitation> lsInv = invitationPageResponse.getList();

            List<Invitation> list = new ArrayList<Invitation>(lsInv.size());

            for (com.qding.member.model.Invitation invitation : lsInv) {

                Room room = roomReadRemoteService.get(Long.parseLong(invitation.getRoomId()));

                Invitation inv = transfor(Invitation.class, invitation);

                com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r =
                        transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);

                inv.setRoom(r);

                list.add(inv);
            }

            InviteUserHistoryResponseData data = new InviteUserHistoryResponseData();

            data.setList(list);

            data.setTotalCount(total);

            data.setRecordCount(list.size());

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(InviteUserHistoryResponseData.class, e);
        }

    }


    /**
     * 获取用户有效房间列表
     *
     * @param request
     */
    @HTTP(alias = "getRoomsByMemberId", isRequireAuth = true)
    public Response<GetMemberRoomsResponseData> getRoomsByMemberId(GetMemberRoomsRequest request) {

        Response<GetMemberRoomsResponseData> response = new Response<GetMemberRoomsResponseData>();

        try {
            GetMemberRoomResponse memberRoomResponse = memberRoomAPI.listValidByMember(request.getMemberId());

            checkAndContinue(memberRoomResponse);

            List<com.qding.member.model.MemberRoom> lsMemberRoom = memberRoomResponse.getList();

            List<ProjectRoom> list = Lists.newArrayList();

            Map<String, com.qding.member.model.MemberRoom> mrh = new HashMap<String, com.qding.member.model.MemberRoom>();

            String hkIndentityStr = request.getHkIndentitys();

            List<Short> fitter = Lists.newArrayList();

            if (QDStringUtil.isNotEmpty(hkIndentityStr)) {

                String[] hkIndentityArray = hkIndentityStr.split(",");

                for (int i = 0; i < hkIndentityArray.length; i++) {

                    fitter.add(Short.parseShort(hkIndentityArray[i]));
                }
            }

            for (int i = 0; i < lsMemberRoom.size(); i++) {

                com.qding.member.model.MemberRoom memberRoom = lsMemberRoom.get(i);

                if (fitter.contains(memberRoom.getRole())) {
                    continue;
                }

                mrh.put(memberRoom.getRoomId(), memberRoom);
            }

            if (mrh.size() > 0) {

                Set<Long> projectIds = new HashSet<>();

                for (int i = 0; i < lsMemberRoom.size(); i++) {

                    com.qding.member.model.MemberRoom memberRoom = lsMemberRoom.get(i);

                    if (fitter.contains(memberRoom.getRole())) {
                        continue;
                    }
                    mrh.put(memberRoom.getRoomId(), memberRoom);

                    projectIds.add(Long.parseLong(memberRoom.getProjectId()));
                }

                Map<Long, String> projectAddrMap = new HashMap<>();
                Map<String, String> groupAddrMap = new HashMap<>();

                List<Project> projectList = projectReadService.getAllProjectInfoByIdSet(projectIds);

                if (CollectionUtils.isNotEmpty(projectList)) {

                    for (Project project : projectList) {

                        projectAddrMap.put(project.getId(), project.getAddress());

                        List<RegionGroup> regionGroup = project.getRegionGroup();

                        if (CollectionUtils.isNotEmpty(regionGroup)) {

                            for (RegionGroup group : regionGroup) {

                                groupAddrMap.put(group.getRegionName(), group.getAddress());
                            }
                        }
                    }
                }

                Map<String, Object> roomParams = new HashMap<>();

                roomParams.put("idSet", mrh.keySet().toArray());

                List<Room> gets = roomReadRemoteService.gets(roomParams);

                for (Room room : gets) {

                    com.qding.member.model.MemberRoom  memberRoom =  mrh.get(String.valueOf(room.getId()));
                    //获取房间对象
                    ProjectRoom pr = transfor(ProjectRoom.class,memberRoom);

                    com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r =
                            transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);
                    r.setRoomAddr(fittingRoomAddr(projectAddrMap, groupAddrMap, r));
                    try {
                       String roleName = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_HK_INDENTITY.getGroupName(),String.valueOf(memberRoom.getRole()));
                        pr.setRoleName(roleName);
                    } catch (TException e) {
                       logger.error("get dic error , groupName:"+Constant.Dict_K_V_Enum.DICT_HK_INDENTITY.getGroupName()+",dicKey :"+memberRoom.getRole());
                    }
                    pr.setHkIndentity(memberRoom.getRole());
                    pr.setRoom(r);
                    pr.setAuditStatus(1);//因为当前接口提供的是有效房屋列表，所以都为审核通过的房屋
                    list.add(pr);
                }
            }

            GetMemberRoomsResponseData data = new GetMemberRoomsResponseData();

            data.setList(list);

            response.setCode(HttpStatus.OK.getStatusCode());

            response.setData(data);

            return response;

        } catch (ServiceException e) {

            return handleException(GetMemberRoomsResponseData.class, e);
        }

    }


    private static String fittingRoomAddr(Map<Long, String> projectAddrMap, Map<String, String> groupAddrMap, com.qding.api.call.app.qding.v1_3_0.struct.brick.Room room) {

        StringBuffer roomAddrBuf = new StringBuffer();

        if (groupAddrMap.containsKey(room.getGroupName())) {
            roomAddrBuf.append(QDStringUtil.isNotNull(groupAddrMap.get(room.getGroupName())) ? groupAddrMap.get(room.getGroupName()) : "");
        } else if (projectAddrMap.containsKey(room.getProjectId())) {
            roomAddrBuf.append(QDStringUtil.isNotNull(projectAddrMap.get(room.getProjectId())) ? projectAddrMap.get(room.getProjectId()) : "");
        }
        roomAddrBuf.append(QDStringUtil.isNotNull(room.getProjectName()) ? room.getProjectName() + "-" : "");
        roomAddrBuf.append(QDStringUtil.isNotNull(room.getBuildingName()) ? room.getBuildingName() + "-" : "");
        roomAddrBuf.append(QDStringUtil.isNotNull(room.getName()) ? room.getName() : "");

        return roomAddrBuf.toString();
    }

    /**
     * 获取用户在项目下的房间列表
     *
     * @param request
     */
    @HTTP(alias = "getProjectRooms")
    public Response<GetProjectRoomsResponseData> getProjectRooms(GetProjectRoomsRequest request) {

        try {

            MemberBindRoomDTOsResponse memberBindRoomDTOsResponse = roomBindApplyService.findByMemberIdAndProjectId(request.getMemberId(), request.getProjectId());


            List<ProjectRoom> prs = fittingRoomList(request, memberBindRoomDTOsResponse.getList());

            Response<GetProjectRoomsResponseData> response = new Response<GetProjectRoomsResponseData>();
            GetProjectRoomsResponseData data = new GetProjectRoomsResponseData();
            data.setList(prs);
            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GetProjectRoomsResponseData.class, e);
        }
    }

    private List<ProjectRoom> fittingRoomList(GetProjectRoomsRequest request, List<MemberBindRoomDTO> list) {

        Map<String, MemberBindRoomDTO> mrh = new HashMap<String, MemberBindRoomDTO>();
        for (int i = 0; i < list.size(); i++) {
            MemberBindRoomDTO memberBindRoomDTO = list.get(i);
            mrh.put(memberBindRoomDTO.getRoomId(), memberBindRoomDTO);
        }
        List<ProjectRoom> prs = new ArrayList<>();
        if (mrh.size() > 0) {
            Map<String, Object> roomParams = new HashMap<>();
            roomParams.put("idSet", mrh.keySet().toArray());
            roomParams.put("projectId", request.getProjectId());
            List<Room> gets = roomReadRemoteService.gets(roomParams);
            Project project = projectReadService.get(Long.parseLong(request.getProjectId()));
            List<RegionGroup> regionGroupList = projectReadService.getAllRegionListByProjectId(Long.parseLong(request.getProjectId()));

            for (Room room : gets) {
                //获取房间对象
                ProjectRoom pr = transfor(ProjectRoom.class, mrh.get(String.valueOf(room.getId())));
                com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r =
                        transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);

                boolean flag = false;
                if (CollectionUtils.isNotEmpty(regionGroupList) && QDStringUtil.isNotNull(room.getGroupName())) {
                    for (RegionGroup regionGroup : regionGroupList) {
                        if (room.getGroupName().equals(regionGroup.getRegionName())) {
                            r.setRoomAddr(QDStringUtil.isNotNull(regionGroup.getAddress()) ? regionGroup.getAddress() + "-" : "" + r.getRoomAddr());
                            flag = true;
                            break;
                        }
                    }
                }

                if (!flag && QDStringUtil.isNotNull(project.getAddress())) {
                    r.setRoomAddr(project.getAddress() + "-" + r.getRoomAddr());
                }
                pr.setRoom(r);
                prs.add(pr);
            }
        }
        return prs;
    }

    /**
     * 获取房屋下所有成员
     *
     * @param request
     * @return
     */
    @HTTP(alias = "selAllMemberByRoom", isRequireAuth = true)
    public Response<GetAllMemberByRoomResponseData> getAllMemberByRoom(GetAllMemberByRoomRequest request) {

        try {
            Response<GetAllMemberByRoomResponseData> response = new Response<GetAllMemberByRoomResponseData>();

            String memberId = request.getMemberId();

            String roomId = request.getRoomId();

            Room rpcRoom = roomReadRemoteService.get(Long.parseLong(roomId));

            com.qding.api.call.app.qding.v1_3_0.struct.brick.Room room =
                    transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, rpcRoom);

            GetMemberRoomResponse memberRoomResponse = memberRoomAPI.listValidGuest(memberId, roomId);
            checkAndContinue(memberRoomResponse);
            List<com.qding.member.model.MemberRoom> lsMemberRoom = memberRoomResponse.getList();

            List<MemberRoom> memberList = new ArrayList<MemberRoom>(lsMemberRoom.size());

            for (com.qding.member.model.MemberRoom mr : lsMemberRoom) {

                try {
                    MemberRoom memberRoom = transfor(MemberRoom.class, mr);

                    GetMemberRequest memeberRequest = new GetMemberRequest();

                    memeberRequest.setMemberId(mr.getMemberId());

                    GetMemberResponse memeberResponse = profileAPI.getMemberById(memeberRequest);

                    checkAndContinue(memeberResponse);

                    MemberInfo memberInfo = memeberResponse.getMemberInfo();

                    Member member = transfor(Member.class, memberInfo);

                    if (QDStringUtil.isNotNull(member.getMemberMobile()) && member.getMemberMobile().length() == 11) {

                        StringBuffer mobileBuffer = new StringBuffer(member.getMemberMobile());
                        mobileBuffer.replace(3, 7, "****");
                        member.setMemberMobile(mobileBuffer.toString());
                    }

                    resizeMemberAvatar(member);

                    memberRoom.setMember(member);

                    memberList.add(memberRoom);
                } catch (Exception ex) {
                     ex.printStackTrace();
                     continue;
                }

            }

            GetAllMemberByRoomResponseData data = new GetAllMemberByRoomResponseData();

            data.setList(memberList);

            data.setRoom(room);

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GetAllMemberByRoomResponseData.class, e);
        }
    }


    /**
     * 删除房屋成员
     *
     * @param request
     * @return
     */
    @HTTP(alias = "deleteMemberByRoom", isRequireAuth = true)
    public Response<DeleteMemberByRoomResponseData> deleteMemberByRoom(DeleteMemberByRoomRequest request) {

        try {

            String ownMemberId = request.getMemberId();

            String roomId = request.getRoomId();

            String guestMemberId = request.getGuestMemberId();

            BaseResponse baseResponse = memberRoomAPI.deleteGuestRoom(ownMemberId, guestMemberId, roomId);

            checkAndContinue(baseResponse);

            Response<DeleteMemberByRoomResponseData> response = new Response<DeleteMemberByRoomResponseData>();

            response.setData(new DeleteMemberByRoomResponseData());

            return response;

        } catch (Exception e) {

            return handleException(DeleteMemberByRoomResponseData.class, e);
        }
    }


    /**
     * 获取收件人列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "selUserForReceiveMessage", isRequireAuth = true)
    @Deprecated
    public Response<GetUserForReceiveMessageResponseData> getUserForReceiveMessage(GetUserForReceiveMessageReuqest request) {

        try {

            Response<GetUserForReceiveMessageResponseData> response = new Response<GetUserForReceiveMessageResponseData>();
            MemberAddressCondition memberAddressCondition = new MemberAddressCondition();
            memberAddressCondition.setMemberId(request.getMemberId());
            memberAddressCondition.setBusinessType(request.getAddressBusinessType());
            if (request.getAppDevice().getQdVersion().compareTo("2.5.0") < 0) {
                memberAddressCondition.setVersion(1);//1:老版本<2.5.0时传1,否则不用处理
            }
            MemberAddressListResponse memberAddressListResponse =  memberAddressService.findMemberAddressByCondition(memberAddressCondition);
            List<MemberAddress> ng_mas = memberAddressListResponse.getMemberAddressList();
            List<Addresses> addresses = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(ng_mas)) {
                addresses = transforList(Addresses.class, ng_mas);
            }
            GetUserForReceiveMessageResponseData data = new GetUserForReceiveMessageResponseData(addresses);
            response.setData(data);

            return response;

        } catch (Exception e) {
            return handleException(GetUserForReceiveMessageResponseData.class, e);
        }
    }


    /**
     * 更新收件人
     *
     * @param request
     * @return
     */
    @HTTP(alias = "updateUserForReceiveMessage", isRequireAuth = true)
    @Deprecated
    public Response<UpdateUserForReceiveMessageResponseData> updateUserForReceiveMessage(UpdateUserForReceiveMessageReuqest request) {

        try {

            Response<UpdateUserForReceiveMessageResponseData> response = new Response<UpdateUserForReceiveMessageResponseData>();

            MemberAddress memberAddress = transfor(MemberAddress.class, request);
            memberAddress.setVersion(1);

            MemberAddressResponse memberAddressResponse = memberAddressService.update(memberAddress);

            checkAndContinue(memberAddressResponse);

            if (1 == request.getDefaultFlag()) {

                MemberAddressResponse memberAddressResponse2 = memberAddressService.defaultMa(request.getMemberId(), request.getId());

                checkAndContinue(memberAddressResponse2);
            }

            MemberAddress mas = memberAddressResponse.getMemberAddress();

            Addresses addresses = transfor(Addresses.class, mas);

            UpdateUserForReceiveMessageResponseData data = new UpdateUserForReceiveMessageResponseData();

            data.setEntity(addresses);

            response.setData(data);

            return response;

        } catch (ServiceException e) {

            return handleException(UpdateUserForReceiveMessageResponseData.class, e);

        }

    }


    /**
     * 添加收件人
     *
     * @param request
     * @return
     */
    @HTTP(alias = "addUserForReceiveMessage", isRequireAuth = true)
    @Deprecated
    public Response<AddUserForReceiveMessageResponseData> addUserForReceiveMessage(AddUserForReceiveMessageReuqest request) {

        try {

            MemberAddress memberAddress = transfor(MemberAddress.class, request);
            memberAddress.setVersion(1);
            //如果有房间ID获取房间信息拼装地址
            if (QDStringUtil.isNotEmpty(request.getRoomId())) {

                Room room = roomReadRemoteService.get(Long.parseLong(request.getRoomId()));
                Project project = projectReadService.get(room.getProjectId());
                List<RegionGroup> regionGroupList = projectReadService.getAllRegionListByProjectId(room.getProjectId());
                Region region = regionRemoteService.getDetailById(project.getRegionId());
                StringBuffer adr = new StringBuffer();
                boolean flag = false;
                if (CollectionUtils.isNotEmpty(regionGroupList) && QDStringUtil.isNotNull(room.getGroupName())) {
                    for (RegionGroup regionGroup : regionGroupList) {
                        if (room.getGroupName().equals(regionGroup.getRegionName())) {
                            adr.append(regionGroup.getAddress() + "-");
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag && QDStringUtil.isNotNull(project.getAddress())) {
                    adr.append(project.getAddress() + "-");
                    flag = true;
                }
                if (!flag) {
                    adr.append(QDStringUtil.isNotEmpty(region.getProvince()) ? region.getProvince() + "-" : "");
                    adr.append(QDStringUtil.isNotEmpty(project.getRegionName()) ? project.getRegionName() + "-" : "");
                }

                adr.append(QDStringUtil.isNotEmpty(room.getProjectName()) ? room.getProjectName() : "");
                adr.append(QDStringUtil.isNotEmpty(room.getGroupName()) ? room.getGroupName() : "");
                adr.append(QDStringUtil.isNotEmpty(room.getBuildingName()) ? room.getBuildingName() : "");
                adr.append(QDStringUtil.isNotEmpty(room.getFloor()) ? room.getFloor() : "");
                adr.append(QDStringUtil.isNotEmpty(room.getName()) ? room.getName() : "");

                memberAddress.setAddress(adr.toString());

            }

            MemberAddressResponse memberAddressResponse = memberAddressService.save(memberAddress);

            checkAndContinue(memberAddressResponse);

            MemberAddress mas =memberAddressResponse.getMemberAddress();

            Addresses addresses = transfor(Addresses.class, mas);

            if (1 == request.getDefaultFlag()) {

                MemberAddressResponse setDefaultResponse = memberAddressService.defaultMa(mas.getMemberId(), mas.getId());

                checkAndContinue(setDefaultResponse);
            }

            Response<AddUserForReceiveMessageResponseData> response = new Response<AddUserForReceiveMessageResponseData>();

            AddUserForReceiveMessageResponseData data = new AddUserForReceiveMessageResponseData();

            data.setEntity(addresses);

            response.setData(data);

            return response;

        } catch (ServiceException e) {

            return handleException(AddUserForReceiveMessageResponseData.class, e);
        }
    }

    /**
     * 删除收货地址
     *
     * @param request
     * @return
     */
    @HTTP(alias = "deleteDeliveryAddress", isRequireAuth = true)
    public Response<DelReceiveMessageResponseData> deleteDeliveryAddress(DelReceiveMessageRequest request) {

        Response<DelReceiveMessageResponseData> response = new Response<DelReceiveMessageResponseData>();
        try {

            MemberAddressResponse memberAddressResponse = memberAddressService.deleteMa(request.getMemberId(), request.getId());

            checkAndContinue(memberAddressResponse);

            response.setData(new DelReceiveMessageResponseData());

            return response;

        } catch (ServiceException e) {

            return handleException(DelReceiveMessageResponseData.class, e);
        }

    }

    /**
     * 设置默认收货地址
     *
     * @param request
     * @return
     */
    @HTTP(alias = "setDefaultDeliveryAddress", isRequireAuth = true)
    public Response<DefaultReceiveMessageResonseData> defaultReceiveMessage(DefaultReceiveMessageRequest request) {

        Response<DefaultReceiveMessageResonseData> response = new Response<DefaultReceiveMessageResonseData>();

        try {

            MemberAddressResponse memberAddressResponse = memberAddressService.defaultMa(request.getMemberId(), request.getId());

            checkAndContinue(memberAddressResponse);

            response.setData(new DefaultReceiveMessageResonseData());

            return response;

        } catch (ServiceException e) {

            return handleException(DefaultReceiveMessageResonseData.class, e);
        }

    }


    /**
     * 获取我的订单菜单
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getOrderMenu")
    public Response<GetOrderMenuResponseData> getOrderMenu(GetOrderMenuRequest request) {

        try {
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());

            String serviceItemOrder = Constant.transforServiceByAppDevice(Constant.serviceItemOrderMap, request.getAppDevice());
            String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());

            List<ServiceItem> serviceItems = appConfigRemote.getBindServiceItemListbyProjectId(Long.parseLong(request.getProjectId()),
                    serviceItemOrder,
                    version,
                    brickSourceType
            );

            List<ProjectService> orderMenu = transforList(ProjectService.class, serviceItems);

            Response<GetOrderMenuResponseData> response = new Response<>();

            response.setData(new GetOrderMenuResponseData(orderMenu));

            return response;
        } catch (Exception e) {

            return handleException(GetOrderMenuResponseData.class, e);
        }

    }

    /**
     * 获取我的通知消息菜单
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getNotifyMenu")
    public Response<GetNotifyMenuResponseData> getNotifyMenu(GetNotifyMenuRequest request) {

        try {
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());

            String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());

            List<ServiceItem> serviceItems = appConfigRemote.getBindServiceItemListbyProjectId(Long.parseLong(request.getProjectId()),
                    Constant.SERVICE_ITEM_NOTIFY,
                    version,
                    brickSourceType
            );

            List<ProjectService> notifyMenu = transforList(ProjectService.class, serviceItems);

            Response<GetNotifyMenuResponseData> response = new Response<>();

            response.setData(new GetNotifyMenuResponseData(notifyMenu));

            return response;
        } catch (Exception e) {

            return handleException(GetNotifyMenuResponseData.class, e);
        }

    }


    @HTTP(alias = "authorize")
    public Response<AuthorizeResponseData> authorize(HttpServletResponse httpServletResponse, AuthorizeRequest request) {

        try {

            String version = request.getAppDevice().getQdVersion();

            GetAccountResponse accountResponse = passportAPI.getAccountByAccountId(new GetAccountRequest(request.getAccountId()));

            checkAndContinue(accountResponse);

            Member member = new Member();

            if(accountResponse.getMemberInfo()!=null || QDStringUtil.isNotEmpty(accountResponse.getMemberInfo().getId())){
                member = transfor(Member.class, accountResponse.getMemberInfo());
            }

            Account user = transfor(Account.class, accountResponse.getAccountInfo());

            String userToken = getUserToken(user, member,version);

            Response<AuthorizeResponseData> response = new Response<>();

            response.setData(new AuthorizeResponseData(userToken));

            return response;

        } catch (Exception e) {

            return handleException(AuthorizeResponseData.class, e);
        }
    }

    /**
     * 智能绑定房屋
     *
     * @param request
     * @return
     */
    @HTTP(alias = "smartBindOwnerRoom", isRequireAuth = true)
    public Response<ResponseData> smartBindOwnerRoom(SmartBindOwnerRoomRequest request) {

        try {

            GetMemberRequest getMemberRequest = transfor(GetMemberRequest.class, request);

            GetMemberResponse getMemberResponse = profileAPI.getMemberById(getMemberRequest);

            checkAndContinue(getMemberResponse);

            MemberInfo memberInfo = getMemberResponse.getMemberInfo();

            autoBindOwnerRoom(memberInfo.getId(), memberInfo.getMobile());

            Response<ResponseData> response = new Response<>();

            response.setData(new ResponseData());

            return response;

        } catch (Exception e) {

            return handleException(ResponseData.class, e);
        }

    }




    /**
     * 登录后的动作
     * 1 裁剪头像
     * 2 自动绑定房屋
     * 3 生成用户Token
     *
     * @param accountMember
     * @throws ServiceException
     */
    protected String afterLogin(AccountMember accountMember,String version ) throws ServiceException {

        Member member = accountMember.getMember();
        Account user = accountMember.getUser();

        if(QDStringUtil.isNotNull(member) && Integer.parseInt(member.getMemberStatus()) == 0){
            throw  new ServiceException(HttpStatus.GONE.getStatusCode(),"当前会员已停用");
        }

        if ( QDStringUtil.isNotNull(user)) {

            AccountMemberValidate.validateAccount(user);

        }
        if ( QDStringUtil.isNotNull(member)) {

            AccountMemberValidate.validateMember(member);
        }

        resizeMemberAvatar(member);
        logger.info("自动绑定房屋------------------start");
        autoBindOwnerRoom(member);
        logger.info("自动绑定房屋------------------end");
        String userToken = getUserToken(user, member,version);

        accountMember.setUserToken(userToken);

        cleanMsgVerifyCache (member.getMemberId(),member.getMemberMobile());

        return userToken;

    }


    /**
     * 登陆成功 清除短息+语音验证请求次数，如之前状态为冻结，则从冻结列表中清除
     * @param memberId
     * @param mobile
     */
    private void cleanMsgVerifyCache (String memberId,String mobile){

        StoreDevice storeDevice = new RedisStoreDevice();
        String check_msg_verify_key = String.format(Constant.CHECK_MSG_VERIFY_FREQUENCY_KEY, mobile);
        String freez_key = Constant.FREEZ_EMEMBER_READ_KEY.toString();
        storeDevice.delKey(check_msg_verify_key);
        storeDevice.srem(freez_key,memberId);
    }


    /**
     * 针对新注册用户为其生成IM使用的令牌并存储
     *
     * @param accountId
     */
    public String saveImUserToken(String accountId) {

        String imtoken = "";
        try {
            com.qding.api.rongcloud.model.response.UserToken rongcloudToken =
                    RongCloudApiHttpClient.getToken(accountId, null, null);
            if (200 == rongcloudToken.getCode()) {
                SaveImUserTokenRequest request = new SaveImUserTokenRequest();
                request.setAccountId(accountId);
                request.setRongcloudToken(rongcloudToken.getToken());
                SaveImUserTokenResponse imUserTokenResponse = passportAPI.saveImUserToken(request);
                if (imUserTokenResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()){
                    imtoken = rongcloudToken.getToken();
                }
            }
        } catch (Exception e) {

        }
        return  imtoken;
    }


    protected String getUserToken(Account user, Member member ,String version) {

		Integer curVersion =0;
		if (QDStringUtil.isNotEmpty(version)){
			curVersion = Integer.parseInt(skipMode.initVersion(version));
		}

        String token = GlobalInstance.getUserTokenCallableSecurity().generatorToken(new UserToken(
                user.getAccountId(),
                member.getMemberId(),
                user.getAccountNickName(),
                user.getAccountSourceType()),curVersion);
        return token;

    }

    protected void resizeMemberAvatar(Member member) {

        if (member == null) {
            return;
        }

        String memberAvatar = member.getMemberAvatar();

        member.setMemberAvatar(imageUtil.get(memberAvatar, 200, 200));
    }

    /**
     * 自动绑定房屋
     *
     * @param member
     */
    protected void autoBindOwnerRoom(Member member) {
        try {
            if (member == null) {
                return;
            }
            MemberProjectResponse memberProjectResponse = memberProjectService.getMemberProject(member.getMemberId());
            MemberProject mp = memberProjectResponse.getMemberProject();
            if (QDStringUtil.isNull(mp) || QDStringUtil.isEmpty(mp.getProjectId()))
                return;
        /*判断是否为隐藏号码方式*/
            Project project = projectReadRemote.get(Long.parseLong(mp.getProjectId()));
            /* 自动绑定涉及场景4-7
             * 100(4): 获取绑定提醒后主动选择房屋
			 * 101(5): 获取绑定提醒后主动选择房屋 && 扫描邀请码
			 * 110(6): 获取绑定提醒后主动选择房屋 && 上传资料完成验证
			 * 111(7): 获取绑定提醒后主动选择房屋 && 上传资料完成验证 && 扫描邀请码
			* */
            if (project != null && project.getBindType() >= 4 && project.getBindType() <= 7) {/*隐藏手机号方式绑定类型为4-7*/
                //判断是否满足隐藏手机号自动绑定逻辑(隐藏手机号+匹配条数) 不满足时发push
                GetOwnerRoomsRequest getOwnerRoomsRequest = new GetOwnerRoomsRequest();
                getOwnerRoomsRequest.setMobile(member.getMemberMobile());
                getOwnerRoomsRequest.setProjectId(project.getId());
                //查询隐藏号码方式下的有效房屋列表
                GetOwnerRoomsResponse getOwnerRoomsResponse = roomOwnerRemoteService.getOwnerRooms(getOwnerRoomsRequest);
                checkAndContinue(getOwnerRoomsResponse);
                boolean isConflict = getOwnerRoomsResponse.isConflict();
                List<Room> list = getOwnerRoomsResponse.getRooms();
                /*isConflict:true push
                 *自动绑定 false&&rooms.size=>0
				 *isConflict: (false&&rooms==null) 不处理
				 */
                if (isConflict == false && CollectionUtils.isNotEmpty(list)) {
                    autoBindOwnerRoom(member.getMemberId(), member.getMemberMobile(), list);
                } else if (isConflict == true) {
                    List<String> mids = new ArrayList<>();
                    mids.add(member.getMemberId());
                    String tid = "bindRoomNotify"; //7000跳H5 无参数模板
                    Map<String, String> tdata = new HashMap<>();
                    tdata.put("curTime", String.valueOf(System.currentTimeMillis() / 1000));
                    tdata.put("type", MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_H5);
                    tdata.put("url", APIPropertiesClient.getUrlContent("skip_url_btn_h5") + "/house/match");//skip_url_btn_h5=http://qam.iqdnet.com
                    tdata.put("cityName", project.getRegionName());
                    tdata.put("projectName", project.getName());
                    MsgSendByMidRequest msgSendByMidRequest = new MsgSendByMidRequest(mids, tid, tdata, MsgConstant.APP_TYPE_QDING);
                    msgSendByMidRequest.setMessageType(MsgConstant.APP_PUSH_MESSAGE_TYPE_SYS);
                    msgSendByMidRequest.setPush(true);
                    msgSendByMidRequest.setVersion(MsgConstant.APP_PUSH_VERSION_2_0);
                    MsgSendResponse msgSendResponse = messageService.sendMessageByMids(msgSendByMidRequest);
                    logger.info("messageService.sendMessageByMids params:" + msgSendByMidRequest.toString() + " ,result:" + msgSendResponse.toString());
                }
            } else { /*全号码方式绑定类型为1-3*/
                autoBindOwnerRoom(member.getMemberId(), member.getMemberMobile());
            }
        } catch (Exception e) {
            logger.error("autoBindOwnerRoom error: ", e);
        }

    }

    /**
     * 自动绑定房屋-全号码方式
     *
     * @param memberId
     * @param mobile
     */
    protected void autoBindOwnerRoom(String memberId, String mobile) {

        //同步房屋数据
        try {
        	logger.info("全号码自动绑定房屋----------------------"+memberId);
            if (QDStringUtil.isEmpty(mobile)) {
                return;
            }

            List<Room> allRooms = roomOwnerRemoteService.getRoomsByMobile(mobile);
            List<Room> deleteRooms = roomOwnerRemoteService.getDeleteRoomByMobile(mobile);

            MemberRoomSyncRequest request = new MemberRoomSyncRequest();
            Set<BindRoom> bindRooms = new HashSet<>();
            if (allRooms != null) {
                for (Room room : allRooms) {

                    bindRooms.add(new BindRoom(
                            String.valueOf(room.getId()),
                            String.valueOf(room.getProjectId()), MemberRole.ONWER
                    ));
                }
            }
            Set<String> unBindRoomIds = new HashSet<>();
            if (deleteRooms != null) {
                for (Room room : deleteRooms) {
                    unBindRoomIds.add(String.valueOf(room.getId()));
                }
            }

            request.setMemberId(memberId);
            request.setBindRooms(bindRooms);
            request.setUnBindRoomIds(unBindRoomIds);

            BaseResponse baseResponse = memberRoomAPI.syncMemberRoom(request);
            checkAndContinue(baseResponse);

        } catch (Exception e) {
        	logger.error("全号码自动绑定房屋",e);
            //skip
        }
    }

    /**
     * 自动绑定房屋--隐藏手机号码的方式
     */
    protected void autoBindOwnerRoom(String memberId, String mobile, List<Room> allRooms) {
    	
        //同步房屋数据
        try {
        	logger.info("隐藏手机号码自动绑定房屋-----------------------"+memberId);
            List<Room> deleteRooms = roomOwnerRemoteService.getDeleteRoomByMobile(mobile);

            MemberRoomSyncRequest request = new MemberRoomSyncRequest();
            Set<BindRoom> bindRooms = new HashSet<>();
            if (allRooms != null) {
                for (Room room : allRooms) {

                    bindRooms.add(new BindRoom(
                            String.valueOf(room.getId()),
                            String.valueOf(room.getProjectId()), MemberRole.ONWER
                    ));
                }
            }
            Set<String> unBindRoomIds = new HashSet<>();
            if (deleteRooms != null) {
                for (Room room : deleteRooms) {
                    unBindRoomIds.add(String.valueOf(room.getId()));
                }
            }

            request.setMemberId(memberId);
            request.setBindRooms(bindRooms);
            request.setUnBindRoomIds(unBindRoomIds);

            BaseResponse baseResponse = memberRoomAPI.syncMemberRoom(request);
            checkAndContinue(baseResponse);

        } catch (Exception e) {
            logger.error("隐藏手机号码自动绑定房屋",e);
            //skip
        }
    }
}
