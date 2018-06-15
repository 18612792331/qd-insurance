package com.qding.api.call.app.qding.v3_2_0;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v3_2_0.struct.task.SignInPrizeInfo;
import com.qding.api.call.app.qding.v3_2_0.struct.task.request.SaveEntranceGuardActivityRecordRequest;
import com.qding.api.call.app.qding.v3_2_0.struct.task.request.GetSignInInfoRequest;
import com.qding.api.call.app.qding.v3_2_0.struct.task.request.SaveSignInLogRequest;
import com.qding.api.call.app.qding.v3_2_0.struct.task.response.data.SaveEntranceGuardActivityRecordResponseData;
import com.qding.api.call.app.qding.v3_2_0.struct.task.response.data.GetSignInInfoResponseData;
import com.qding.api.call.app.qding.v3_2_0.struct.task.response.data.SaveSignInLogResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.IntegralMessage;
import com.qding.api.imessage.IntegralMessageBeanT;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.DateUtil;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.integral.remote.ActivityGoodsRemoteService;
import com.qding.integral.remote.request.ExchangeGoodsRequest;
import com.qding.integral.remote.response.ExchangeGoodsResponse;
import com.qding.marketing.service.MkRemoteService;
import com.qding.marketing.struct.request.GuarkTaskLimitRequest;
import com.qding.marketing.struct.response.GuardTaskLimitResponse;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.task.dto.SignInInfoDto;
import com.qding.task.dto.SignInPrizeInfoDto;
import com.qding.task.serivice.ISignInRpcService;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by jinhaishan on 17/7/24.
 */
public class CallMemberTask extends com.qding.api.call.app.qding.v3_0_0.CallMemberTask {


    private static final Logger logger = Logger.getLogger("CallMemberTask");


    @Autowired
    private ISignInRpcService signInRpcService;

    @Autowired
    private IntegralMessage integralMessage;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IProfileService profileAPI;

    @Resource
    private ActivityGoodsRemoteService activityGoodsRemoteService;


    @Autowired
    private MkRemoteService mkRemoteService;


    @HTTP(alias = "saveSignInLog", isNeadToken = true, isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "签到")
    public Response<SaveSignInLogResponseData> saveSignInLog(SaveSignInLogRequest request, UserToken userToken) {
        try {
            Project project = projectReadService.get(Long.parseLong(request.getAppUser().getProjectId()));
            GetMemberRequest memberRequest = new GetMemberRequest();
            memberRequest.setMemberId(userToken.getMemberId());
            GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);
            checkAndContinue(memberResponse);
            MemberInfo memberInfo = memberResponse.getMemberInfo();
            String signInPoint = "0";
            BaseResponse baseResponse = signInRpcService.saveSignInLog(userToken.getMemberId(), project.getRegionId(), project.getId(), memberInfo.getName(), memberInfo.getMobile());
            if (baseResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                signInPoint = getFromDictionnary("INTEGRAL", "signInPoint");
                //积分埋点
                IntegralMessageBeanT integralMessageBeanT = new IntegralMessageBeanT(userToken.getMemberId(), Constant.INTEGRAL_SIGN_IN, userToken.getMemberId(), Long.parseLong(request.getAppUser().getProjectId()), System.currentTimeMillis(), Constant.INCOME_OPT_TYPE, null, String.valueOf(DateUtil.getDayBegin().getTime()));
                integralMessage.assembleIntegralMessage(integralMessageBeanT);
            }
            SignInInfoDto signInInfoDto = signInRpcService.getSignInInfoDto(userToken.getMemberId());
            List<SignInPrizeInfoDto> signInPrizeInfoDtos = signInRpcService.getSignInPrizeInfoDto();
            List<SignInPrizeInfo> signInPrizeInfos= getSignInPrizeInfos(userToken.getMemberId(), signInInfoDto, signInPrizeInfoDtos);
            Response<SaveSignInLogResponseData> response = new Response<SaveSignInLogResponseData>();
            response.setCode(HttpStatus.OK.getStatusCode());
            SaveSignInLogResponseData data = new SaveSignInLogResponseData();
            data.setSignInPrizeInfos(signInPrizeInfos);
            data.setSignInPoint(signInPoint);
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(SaveSignInLogResponseData.class, e);
        }
    }

    private String getFromDictionnary(String groupName, String dictKey) {
        try {
            String data = DictionaryClient.findDictValueByGroupNameAndDictKey(groupName, dictKey);
            return data;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return null;
    }

    private Set<Long> getRedeemedGoodsIds(String memberId, List<Long> goodsIds) {
        if (CollectionUtils.isEmpty(goodsIds)) {
            return Collections.emptySet();
        }
        ExchangeGoodsRequest exchangeGoodsRequest = new ExchangeGoodsRequest();
        exchangeGoodsRequest.setIds(goodsIds);
        exchangeGoodsRequest.setMid(memberId);
        ExchangeGoodsResponse exchangeGoodsResponse = activityGoodsRemoteService.exchangeGoodsDetail(exchangeGoodsRequest);
        return Sets.newHashSet(exchangeGoodsResponse.getExchangeList());
    }

    @HTTP(alias = "getSignInInfo", isNeadToken = true, isRequireAuth = true)
    @ExplainAnnotation(explain = "获取签到信息")
    public Response<GetSignInInfoResponseData> getSignInInfo(GetSignInInfoRequest request, UserToken userToken) {
        try {
            SignInInfoDto signInInfoDto = signInRpcService.getSignInInfoDto(userToken.getMemberId());
            List<SignInPrizeInfoDto> signInPrizeInfoDtos = signInRpcService.getSignInPrizeInfoDto();
            List<SignInPrizeInfo> signInPrizeInfos = getSignInPrizeInfos(userToken.getMemberId(), signInInfoDto, signInPrizeInfoDtos);
            Response<GetSignInInfoResponseData> response = new Response<GetSignInInfoResponseData>();
            response.setCode(HttpStatus.OK.getStatusCode());
            GetSignInInfoResponseData data = new GetSignInInfoResponseData();
            data.setSignInPrizeInfos(signInPrizeInfos);
            if (signInInfoDto != null) {
                data.setSignInDays(signInInfoDto.getSignInDays());
                data.setSignInCount(signInInfoDto.getSignInCount());
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return handleException(GetSignInInfoResponseData.class, e);
        }
    }

    /**
     * 获取签到奖品信息
     * @param memberId
     * @param signInInfoDto
     * @param signInPrizeInfoDtos
     * @return
     */
    private List<SignInPrizeInfo> getSignInPrizeInfos(String memberId, SignInInfoDto signInInfoDto, List<SignInPrizeInfoDto> signInPrizeInfoDtos) {
        List<Long> goodsIds = Lists.newArrayList();
        List<SignInPrizeInfo> signInPrizeInfos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(signInPrizeInfoDtos)) {
            BeanCopier copier = BeanCopier.create(SignInPrizeInfoDto.class, SignInPrizeInfo.class, false);
            for (SignInPrizeInfoDto signInPrizeInfoDto : signInPrizeInfoDtos) {
                SignInPrizeInfo signInPrizeInfo = new SignInPrizeInfo();
                copier.copy(signInPrizeInfoDto, signInPrizeInfo, null);
                signInPrizeInfos.add(signInPrizeInfo);
                goodsIds.add(signInPrizeInfoDto.getGoodsId());
            }
        }
        Set<Long> redeemedIds = getRedeemedGoodsIds(memberId, goodsIds);
        for (SignInPrizeInfo signInPrizeInfo : signInPrizeInfos) {
            if (redeemedIds.contains(signInPrizeInfo.getGoodsId())) {
                signInPrizeInfo.setStatus("2");
            } else if (signInInfoDto != null && signInInfoDto.getSignInCount() >= signInPrizeInfo.getMinSignInCount()) {
                signInPrizeInfo.setStatus("1");
            } else {
                signInPrizeInfo.setStatus("0");
            }
        }
        return signInPrizeInfos;
    }

    @HTTP(alias = "saveEntranceGuardActivityRecord", isNeadToken = true, isRequireAuth = true)
    @ExplainAnnotation(explain = "收集门禁营销活动弹出记录")
    public Response<SaveEntranceGuardActivityRecordResponseData> saveEntranceGuardActivityRecord(SaveEntranceGuardActivityRecordRequest request, UserToken userToken) {

        Response<SaveEntranceGuardActivityRecordResponseData> response = new Response<SaveEntranceGuardActivityRecordResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            GuarkTaskLimitRequest taskLimitRequest = new GuarkTaskLimitRequest();
            taskLimitRequest.setMemberId(userToken.getMemberId());
            taskLimitRequest.setActivityId(request.getActivityId());
            GuardTaskLimitResponse taskLimitResponse = mkRemoteService.recordCountforGuarkPointStage(taskLimitRequest);
            checkAndContinue(taskLimitResponse);
        } catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            return handleException(SaveEntranceGuardActivityRecordResponseData.class, e);
        }

        return response;
    }
}
