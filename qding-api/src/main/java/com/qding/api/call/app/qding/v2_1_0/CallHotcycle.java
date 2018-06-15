package com.qding.api.call.app.qding.v2_1_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_1_0.struct.hotcycle.ProjectGcRoomDTO;
import com.qding.api.call.app.qding.v2_1_0.struct.hotcycle.request.ShareHotcycleHomePageRequest;
import com.qding.api.call.app.qding.v2_1_0.struct.hotcycle.response.data.ShareHotcycleHomePageResponseData;
import com.qding.api.struct.Response;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hotcycle.service.IGCRoomRemoteService;
import com.qding.hotcycle.struct.request.*;
import com.qding.hotcycle.struct.response.*;
import com.qding.neighbor.domain.ProjectGcRoomInfo;
import com.qding.neighbor.rpc.IGcRoomRpc;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class CallHotcycle extends com.qding.api.call.app.qding.v1_4_1.CallHotcycle {


    @Autowired
    private IGcRoomRpc gcRoomRpc;


    /**
     * app2.1 分享至邻聚群组 - 选择群组接口
     *
     * @param request
     * @return
     */
    @HTTP(alias = "shareHotcycleHomePage")
    @ExplainAnnotation(explain = "分享至邻聚群组-首页")
    public Response<ShareHotcycleHomePageResponseData> shareHotcycleHomePage(ShareHotcycleHomePageRequest request) {

        try {

            Response<ShareHotcycleHomePageResponseData> response = new Response<ShareHotcycleHomePageResponseData>();
            ShareHotcycleHomePageResponseData data = new ShareHotcycleHomePageResponseData();

            response.setCode(HttpStatus.OK.getStatusCode());

            com.qding.neighbor.rpc.request.GetGcRoomGroupByProjectRequest getGcRoomGroupByProjectRequest
                    = transfor(com.qding.neighbor.rpc.request.GetGcRoomGroupByProjectRequest.class, request);
            com.qding.neighbor.rpc.response.data.GetGcRoomGroupByProjectResponse getGcRoomGroupByProjectResponse
                    = gcRoomRpc.homePageGroupByProject(getGcRoomGroupByProjectRequest);
            if (getGcRoomGroupByProjectResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                List<ProjectGcRoomInfo>  projectGcRoomInfos = getGcRoomGroupByProjectResponse.getProjectGcRoomInfoList();
                List<ProjectGcRoomDTO> list = Lists.newArrayList();
                for (ProjectGcRoomInfo projectGcRoomInfo : projectGcRoomInfos) {
                    if(QDStringUtil.isEmpty(projectGcRoomInfo.getProjectId()) ||QDStringUtil.isEmpty(projectGcRoomInfo.getProjectName()) || CollectionUtils.isEmpty(projectGcRoomInfo.getGroupList())){
                        continue;
                    }
                    ProjectGcRoomDTO projectGcRoomDTO = transfor(ProjectGcRoomDTO.class,projectGcRoomInfo);
                    list.add(projectGcRoomDTO);
                }
                data.setList(list);
                data.setTotalCount(getGcRoomGroupByProjectResponse.getTotalCount());
                response.setData(data);
            } else {
                response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
                return response;
            }

            return response;

        } catch (Exception ex) {

            return handleException(ShareHotcycleHomePageResponseData.class, ex);
        }

    }

}
