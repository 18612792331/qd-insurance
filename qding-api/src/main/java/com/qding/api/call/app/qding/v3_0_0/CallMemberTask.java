package com.qding.api.call.app.qding.v3_0_0;

import com.alibaba.fastjson.JSON;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v3_0_0.struct.task.EntranceGuardActivity;
import com.qding.api.call.app.qding.v3_0_0.struct.task.MaketingActivity;
import com.qding.api.call.app.qding.v3_0_0.struct.task.DialogInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.task.request.GetEntranceGuardTaskRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.task.response.data.GetEntranceGuardTaskResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.AppDevice;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.marketing.domain.GuardTaskActivity;
import com.qding.marketing.service.MkRemoteService;
import com.qding.marketing.struct.request.GuardTaskActivityRequest;
import com.qding.marketing.struct.response.GuardTaskActivtyResponse;
import com.qding.sysconfig.rpc.request.AppHomeConfigRequest;
import com.qding.sysconfig.rpc.response.AppHomSurpriseResponse;
import com.qding.sysconfig.rpc.service.AppHomeConfigRpcService;
import com.qding.task.dto.DialogBox;
import com.qding.task.dto.EntranceGuardV3Bean;
import com.qding.task.serivice.ITaskEntranceGuardRpc;
import com.qding.task.struct.request.GetEntranceGuardTaskByMidAndPidV3Request;
import com.qding.task.struct.response.GetEntranceGuardTaskByMidAndPidV3ResponseData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by qd on 2017/3/4.
 */
public class CallMemberTask extends com.qding.api.call.app.qding.v2_4_1.CallMemberTask {

    @Autowired
    private ITaskEntranceGuardRpc taskEntranceGuardRpc;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private AppHomeConfigRpcService appHomeConfigRpcService;

    @Autowired
    private AppConfigRemote appConfigRemote;

    @Autowired
    private MkRemoteService mkRemoteService;

    private static final Logger logger = Logger.getLogger("CallMemberTask");


    @HTTP(alias = "getEntranceGuardTask",  isNeadProject = true)
    @ExplainAnnotation(explain = "门禁任务弹出框")
    public Response<GetEntranceGuardTaskResponseData> getEntranceGuardTask(GetEntranceGuardTaskRequest request, UserToken userToken) {
        try {

            String version = request.getAppDevice().getQdVersion();
            String projectId =  request.getAppUser().getProjectId();
            Map<String, String> skipConfigMap = null;
            if (QDStringUtil.isNotEmpty(version)) {
                version = version.trim();
                skipConfigMap = skipMode.selSkipTemplateMap(version);
            } else {
                logger.info("project index can't get version");
            }

            Response<GetEntranceGuardTaskResponseData> response = new Response<GetEntranceGuardTaskResponseData>();
            GetEntranceGuardTaskResponseData data = new GetEntranceGuardTaskResponseData();
            response.setCode(HttpStatus.OK.getStatusCode());
            DialogInfo dialogEntity =null;
            EntranceGuardActivity entranceGuardActivity = null;
            if (QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())) {
                 dialogEntity = fittingStartUpActivity ( projectId,userToken.getMemberId(),skipConfigMap,version);
                 entranceGuardActivity = fittingGuardActivity(Long.parseLong(projectId),userToken.getMemberId(), skipConfigMap);
                logger.warn("class:CallMemberTask,method:getEntranceGuardTask can't get userToken");
            }

            MaketingActivity maketingActivity = fittingMarketingActivity(request.getAppDevice(), Long.parseLong(projectId), skipConfigMap);
            data.setDialogEntity(dialogEntity);
            data.setEntranceGuard(entranceGuardActivity);
            data.setMarketing(maketingActivity);
            response.setData(data);
            return response;

        } catch (Exception e) {
            return handleException(GetEntranceGuardTaskResponseData.class, e);
        }
    }

    /**
     * 获取闪屏活动
     * @param projectId
     * @param memberId
     * @param skipConfigMap
     * @return
     */
    private DialogInfo fittingStartUpActivity (String  projectId, String memberId,Map<String, String> skipConfigMap,String version){

        DialogInfo dialogEntity = null;
        try {
            GetEntranceGuardTaskByMidAndPidV3Request entranceGuardTaskByMidAndPidV3Request = new GetEntranceGuardTaskByMidAndPidV3Request();
            entranceGuardTaskByMidAndPidV3Request.setMemberId(memberId);
            entranceGuardTaskByMidAndPidV3Request.setProjectId(projectId);
            GetEntranceGuardTaskByMidAndPidV3ResponseData entranceGuardTaskByMidAndPidResponseData = taskEntranceGuardRpc.getEntranceGuardTaskByMidAndPidV3(entranceGuardTaskByMidAndPidV3Request);

            if (HttpStatus.OK.getStatusCode() != entranceGuardTaskByMidAndPidResponseData.getReturnInfo().getCode()
                    || QDStringUtil.isNull(entranceGuardTaskByMidAndPidResponseData.getEntranceGuardInfo())) {
                return dialogEntity;
            }

            EntranceGuardV3Bean entr = entranceGuardTaskByMidAndPidResponseData.getEntranceGuardInfo();
            DialogBox dialogBox = entr.getDialogBox();
            dialogEntity = transfor(DialogInfo.class, dialogBox);
            if (dialogBox != null) {
                Integer optType = dialogBox.getOptType();
                if (optType == 1) { //url
                    String skipModeStr = skipMode.fittingSkipUrl(skipConfigMap, dialogBox.getUrl(), 0, 0, "");
                    dialogEntity.setSkipModel(skipModeStr);
                    dialogEntity.setType(2);
                    dialogEntity.setImgUrl(dialogBox.getImgUrl());
                }

                if (QDStringUtil.isNotNull(dialogBox.getNoticeType())) {
                    if (optType == 4 ) {
                        String noticId = dialogBox.getUrl();
                        String noticeUrl = APIPropertiesClient.getSkipUrl(Constant.SkipType.NOTICE_DETAIL.toString());
                        noticeUrl = noticeUrl + noticId;
                        String skipModeStr = skipMode.fittingSkipUrl(skipConfigMap, noticeUrl, 0, 0, "");
                        dialogEntity.setSkipModel(skipModeStr);
                    }
                    dialogEntity.setType(1);
                    dialogEntity.setNoticeType(dialogBox.getNoticeType());
                    String initVersion = skipMode.initVersion(version);
                    if (Integer.parseInt(initVersion) >= 320000) {
                        dialogEntity.setImgUrl(APIPropertiesClient.getValue("notice_"+dialogBox.getNoticeType()));
                    }
                }
            }

            String timeoutInterval = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_TIMEOUTINTERVAL.getGroupName(),Constant.Dict_K_V_Enum.DICT_TIMEOUTINTERVAL.getDictKey());
            if (QDStringUtil.isNotEmpty(timeoutInterval) && dialogEntity != null) {
                dialogEntity.setTimeoutInterval(Long.parseLong(timeoutInterval));
            }
        }catch (Exception ex) {
            logger.error("fittingStartUpActivity is error", ex);
            ex.printStackTrace();
        }
       return  dialogEntity;

    }

    /**
     * 获取门禁活动信息
     *
     * @param projectId
     * @throws ServiceException
     */
    private EntranceGuardActivity fittingGuardActivity(Long projectId,String memberId, Map<String, String> skipConfigMap) {

        EntranceGuardActivity activity = null;
        try {
            GuardTaskActivityRequest guardTaskActivityRequest = new GuardTaskActivityRequest();
            guardTaskActivityRequest.setProjectId(projectId);
            guardTaskActivityRequest.setMemberId(memberId);
            logger.info("method:fittingGuardActivity invoker rpc getActivityByGuardPoint request "+ JSON.toJSONString(guardTaskActivityRequest));
            GuardTaskActivtyResponse taskActivtyResponse = mkRemoteService.getActivityByGuardPoint(guardTaskActivityRequest);
            checkAndContinue(taskActivtyResponse);
            GuardTaskActivity guardTaskActivity = taskActivtyResponse.getGuardTaskActivity();
            if (QDStringUtil.isNotNull(guardTaskActivity)) {
                activity = new EntranceGuardActivity();
                StringBuffer url = new StringBuffer(APIPropertiesClient.getValue("qding.web.global.url"));
                url.append(guardTaskActivity.getFrontLink());
                activity.setSkipModel(skipMode.fittingSkipUrl(skipConfigMap, url.toString(), 1, 0, ""));
                activity.setImgUrl(guardTaskActivity.getShowImg());
                activity.setActivityId(guardTaskActivity.getActivityId());
            }
        } catch (Exception ex) {
            logger.error("fittingGuardActivity is error", ex);
            ex.printStackTrace();
        }

        return activity;
    }


    /**
     * 获取聚合活动信息
     *
     * @return
     */
    private MaketingActivity fittingMarketingActivity(AppDevice appDevice, Long projectId, Map<String, String> skipConfigMap) {

        MaketingActivity maketingActivity = new MaketingActivity();
        try {
            String version = appConfigRemote.getCurVersion(appDevice.getQdVersion());
            Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, appDevice);
            AppHomeConfigRequest homeConfigRequest = new AppHomeConfigRequest();
            homeConfigRequest.setProjectId(projectId);
            homeConfigRequest.setType(salePlatform);
            homeConfigRequest.setVersion(version);
            AppHomSurpriseResponse appHomSurpriseResponse = appHomeConfigRpcService.getAppHomSurpriseByRequest(homeConfigRequest);
            checkAndContinue(appHomSurpriseResponse);
            maketingActivity.setImgUrl(appHomSurpriseResponse.getImageUrl());
            maketingActivity.setSkipModel(skipMode.fittingSkipUrl(skipConfigMap, appHomSurpriseResponse.getUrl(), 1, 0, ""));
        } catch (Exception ex) {
            logger.error("fittingMarketingActivity is error", ex);
            ex.printStackTrace();
        }
        return maketingActivity;
    }
}
