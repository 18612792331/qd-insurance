package com.qding.api.call.app.qding.v2_4_1;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_4_1.struct.task.DialogInfo;
import com.qding.api.call.app.qding.v2_4_1.struct.task.EntranceGuardBean;
import com.qding.api.call.app.qding.v2_4_1.struct.task.TaskBtnSkip;
import com.qding.api.call.app.qding.v2_4_1.struct.task.request.GetEntranceGuardTaskRequest;
import com.qding.api.call.app.qding.v2_4_1.struct.task.response.data.GetEntranceGuardTaskResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.task.dto.Button;
import com.qding.task.dto.DialogBox;
import com.qding.task.dto.EntranceGuardV2Bean;
import com.qding.task.serivice.ITaskEntranceGuardRpc;
import com.qding.task.struct.request.GetEntranceGuardTaskByMidAndPidV2Request;
import com.qding.task.struct.response.GetEntranceGuardTaskByMidAndPidV2ResponseData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@ExplainAnnotation (explain = "会员任务")
public class CallMemberTask extends com.qding.api.call.app.qding.v2_1_0.CallMemberTask {


    @Autowired
    private ITaskEntranceGuardRpc taskEntranceGuardRpc;

    @Autowired
    private SkipModeFitting skipMode;

    private static final Logger logger = Logger.getLogger("CallMemberTask");


    @HTTP(alias="getEntranceGuardTask",isRequireAuth = false)
    @ExplainAnnotation (explain = "门禁任务弹出框")
    @Deprecated
    public Response<GetEntranceGuardTaskResponseData> getEntranceGuardTask(GetEntranceGuardTaskRequest request) {
        try {

            String version = request.getAppDevice().getQdVersion();
            Map<String,String> skipConfigMap = null;
            if (QDStringUtil.isNotEmpty(version)){
                version = version.trim();
                skipConfigMap = skipMode.selSkipTemplateMap(version);
            } else {
                logger.info("project index can't get version");
            }

            Response<GetEntranceGuardTaskResponseData> response = new Response<GetEntranceGuardTaskResponseData>();
            response.setCode(HttpStatus.OK.getStatusCode());
            GetEntranceGuardTaskResponseData data = new GetEntranceGuardTaskResponseData();
            EntranceGuardBean entity = new EntranceGuardBean();
            GetEntranceGuardTaskByMidAndPidV2ResponseData entranceGuardTaskByMidAndPidResponseData = taskEntranceGuardRpc.getEntranceGuardTaskByMidAndPidV2(transfor(GetEntranceGuardTaskByMidAndPidV2Request.class,request));

            if (HttpStatus.OK.getStatusCode() != entranceGuardTaskByMidAndPidResponseData.getReturnInfo().getCode()
                    || QDStringUtil.isNull(entranceGuardTaskByMidAndPidResponseData.getEntranceGuardInfo())
                    || entranceGuardTaskByMidAndPidResponseData.getEntranceGuardInfo().getShowDialogBox().intValue()==2){

                entity.setShowDialog(2);
                data.setEntity(entity);
                response.setData(data);
                return  response;
            }

            EntranceGuardV2Bean entr = entranceGuardTaskByMidAndPidResponseData.getEntranceGuardInfo();
            entity = transfor(EntranceGuardBean.class,entr);
            entity.setShowDialog(entr.getShowDialogBox());
            String skipModeStr = "";
            //如果显示对话框
            if ( 1 == entr.getShowDialogBox()){

                DialogBox dialogBox = entr.getDialogBox();
                DialogInfo dialogEntiy = transfor(DialogInfo.class,dialogBox);
                List<TaskBtnSkip> taskBtnSkipList = Lists.newArrayList();
                entity.setDialogEntity(dialogEntiy);
                List<Button> btnList = dialogBox.getButtonList();
                for (Button buttonDto : btnList) {
                    skipModeStr ="";
                    TaskBtnSkip taskBtnSkip = transfor(TaskBtnSkip.class,buttonDto);
                    Integer skipNo = Integer.parseInt(buttonDto.getEventSubType());
                    if (skipNo.intValue() == Constant.SkipNo.LJHD_3003.toInteger().intValue() || skipNo == Constant.SkipNo.SPLB_5000.toInteger().intValue()
                            || skipNo.intValue() == Constant.SkipNo.SPXQ_5004.toInteger().intValue() || skipNo.intValue() ==Constant.SkipNo.SPPL_5003.toInteger().intValue() ||
                            skipNo.intValue() ==Constant.SkipNo.TOPICXQ_3014.toInteger().intValue() ||   skipNo.intValue() ==Constant.SkipNo.LJTHEME_3017.toInteger().intValue() ){//商品活动查询
                        skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap,skipNo, buttonDto.getHandle());
                    } else if( skipNo.intValue() == Constant.SkipNo.URL_7000.toInteger().intValue() ) {
                        skipModeStr =  skipMode.fittingSkipUrl(skipConfigMap, buttonDto.getHandle(),1,0,"");
                        //这里需要转换一下，原积分明细code:4016跳原生，现对外转换为7000跳html5
                    } else if ( skipNo.intValue() == Constant.SkipNo.JFMX_4106.toInteger().intValue()){
                        skipModeStr =  skipMode.fittingSkipUrl(skipConfigMap, APIPropertiesClient.getUrl("integral.record.url"),1,0,"");
                    }else {
                        skipModeStr = skipMode.fittingNoParameterSkipModel(skipConfigMap,skipNo);
                    }
                    taskBtnSkip.setSkipModel(skipModeStr);
                    taskBtnSkipList.add(taskBtnSkip);
                }
                dialogEntiy.setTaskBtnSkipList(taskBtnSkipList);

            } else { //如果不显示对话框

                switch (entr.getOptType()){
                    case 1: //url连接
                        skipModeStr =  skipMode.fittingSkipUrl(skipConfigMap, entr.getOptHandle(),1,0,"");
                        break;
                    case 3: //商品
                        if (QDStringUtil.isNotEmpty(entr.getOptHandle())) {

                            String skuIds = entr.getOptHandle();
                            String[] skuIdArray = skuIds.split(",");
                            if( skuIdArray.length==1) {
                                skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.SPXQ_5004.toInteger(), skuIds.toString());
                            }  else {
                                skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.SPLB_5000.toInteger(), skuIds.toString());
                            }
                        }
                        break;
                }
                entity.setSkipModel(skipModeStr);
            }
            data.setEntity(entity);
            response.setData(data);
            return  response;

        } catch (Exception e) {
            return handleException(GetEntranceGuardTaskResponseData.class, e);
        }
    }

}
