package com.qding.api.call.app.qding.v2_1_0;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v2_1_0.struct.task.EntranceGuardBean;
import com.qding.api.call.app.qding.v2_1_0.struct.task.request.GetEntranceGuardTaskRequest;
import com.qding.api.call.app.qding.v2_1_0.struct.task.request.ParticipatingEntranceGuardTaskRequest;
import com.qding.api.call.app.qding.v2_1_0.struct.task.response.data.GetEntranceGuardTaskResponseData;
import com.qding.api.call.app.qding.v2_1_0.struct.task.response.data.ParticipatingEntranceGuardTaskResponseData;
import com.qding.api.struct.Response;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.task.serivice.ITaskEntranceGuardRpc;
import com.qding.task.struct.request.GetEntranceGuardTaskByMidAndPidRequest;
import com.qding.task.struct.response.GetEntranceGuardTaskByMidAndPidResponseData;
import org.springframework.beans.factory.annotation.Autowired;

@ExplainAnnotation (explain = "会员任务")
public class CallMemberTask extends Callable {


    @Autowired
    private ITaskEntranceGuardRpc taskEntranceGuardRpc;

    @Autowired
    private SkipModeFitting skipMode;



    @HTTP(alias="getEntranceGuardTask",isRequireAuth = true)
    @ExplainAnnotation (explain = "门禁任务弹出框")
    @Deprecated
    public Response<GetEntranceGuardTaskResponseData> getEntranceGuardTask(GetEntranceGuardTaskRequest request) {
        try {
            Response<GetEntranceGuardTaskResponseData> response = new Response<GetEntranceGuardTaskResponseData>();
            response.setCode(HttpStatus.OK.getStatusCode());
            GetEntranceGuardTaskResponseData data = new GetEntranceGuardTaskResponseData();
            GetEntranceGuardTaskByMidAndPidResponseData entranceGuardTaskByMidAndPidResponseData = taskEntranceGuardRpc.getEntranceGuardTaskByMidAndPid(transfor(GetEntranceGuardTaskByMidAndPidRequest.class,request));
            com.qding.task.dto.EntranceGuardBean  entr = entranceGuardTaskByMidAndPidResponseData.getEntranceGuardInfo();
            EntranceGuardBean entranceGuardBean = new EntranceGuardBean();
            if (QDStringUtil.isNotNull(entr) && !"".equals(entr.getTmfId())) {
                 entranceGuardBean =transfor(EntranceGuardBean.class,entr);
                String skipModeTr = skipMode.fittingSkipUrl(request.getAppDevice().getQdVersion(),entr.getSkipUrl(),0,"");
                entranceGuardBean.setSkipModel(skipModeTr);
            } else {
                //如果没有可执行的活动直接返回 code:204
                response.setCode(HttpStatus.NO_CONTENT.getStatusCode());
            }
            data.setEntity(entranceGuardBean);
            response.setData(data);
            return  response;

        } catch (Exception e) {
            return handleException(GetEntranceGuardTaskResponseData.class, e);
        }
    }


    @HTTP(alias = "participatingEntranceGuardTask",isRequireAuth = true)
    @ExplainAnnotation (explain = "参加门禁任务")
    public Response<ParticipatingEntranceGuardTaskResponseData>  participatingEntranceGuardTask (ParticipatingEntranceGuardTaskRequest request) {

        Response<ParticipatingEntranceGuardTaskResponseData> response = new  Response<ParticipatingEntranceGuardTaskResponseData>();
        try {
            com.qding.task.struct.request.ParticipatingEntranceGuardTaskRequest participatingEntranceGuardTaskRequest = transfor(com.qding.task.struct.request.ParticipatingEntranceGuardTaskRequest.class,request);
            participatingEntranceGuardTaskRequest.setId(request.getTmfId());
            com.qding.task.struct.response.ParticipatingEntranceGuardTaskResponseData participatingEntranceGuardTaskResponseData = taskEntranceGuardRpc.participatingEntranceGuardTask(participatingEntranceGuardTaskRequest);
            checkAndContinue(participatingEntranceGuardTaskResponseData);
            response.setData(new ParticipatingEntranceGuardTaskResponseData());
            response.setCode(HttpStatus.OK.getStatusCode());
        } catch (ServiceException e) {
            return handleException(ParticipatingEntranceGuardTaskResponseData.class, e);
        }

        return response;

    }


}
