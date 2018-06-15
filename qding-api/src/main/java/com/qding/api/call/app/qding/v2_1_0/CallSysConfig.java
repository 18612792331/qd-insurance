package com.qding.api.call.app.qding.v2_1_0;


import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.AppSkinEntity;
import com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.request.GetAppSkinRequest;
import com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.response.data.GetAppSkinResponseData;
import com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.AccessControAnimationBean;
import com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.request.GetAccessControAnimationRequest;
import com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.response.data.GetAccessControAnimationResponseData;
import com.qding.api.struct.Response;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.sysconfig.dto.AppSkinDto;
import com.qding.sysconfig.rpc.response.GetAppSkinResponse;
import com.qding.sysconfig.rpc.service.SysConfigRpcService;
import com.qding.task.domain.TaskEntranceGuardTopic;
import com.qding.task.serivice.ITaskEntranceGuardRpc;
import com.qding.task.struct.response.GetEntranceGuardTopicResponseData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class CallSysConfig extends Callable {

    @Autowired
    private SysConfigRpcService sysConfigRpcService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private ITaskEntranceGuardRpc taskEntranceGuardRpc;

    @Autowired
    private SkipModeFitting skipMode;

    @HTTP(alias = "getAppSkin")
    @ExplainAnnotation(explain = "app换肤")
    public Response<GetAppSkinResponseData> getAppSkin(GetAppSkinRequest request) {
        try {
            Response<GetAppSkinResponseData> response = new Response<GetAppSkinResponseData>();
            GetAppSkinResponseData data = new GetAppSkinResponseData();

            response.setCode(HttpStatus.OK.getStatusCode());
            GetAppSkinResponse getAppSkinResponse = sysConfigRpcService.getAppSkinResponse();
            if (getAppSkinResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                AppSkinDto appSkinDto = getAppSkinResponse.getAppSkinDto();
                com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.AppSkinDto dto =
                        transfor(com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.AppSkinDto.class, appSkinDto);
                if (dto == null) {
                    //boss后台保证已发布的皮肤只有一套，没有时返回null 返回app空的dto id为空字符串
                    dto = new com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.AppSkinDto();
                    dto.setId("");
                }
                AppSkinEntity entity = new AppSkinEntity();
                entity.setId(dto.getId());
                entity.setName(dto.getName());

                if (dto.getTopImageList() != null) {
                    //遍历顶部图片
                    for (Map.Entry<Integer, String> entry : dto.getTopImageList().entrySet()) {
                        if (entry.getKey().equals(1)) {
                            //头部区域背景图
                            entity.setHeadBg(entry.getValue());
                        } else if (entry.getKey().equals(2)) {
                            //切换社区箭头图标
                            entity.setProjectSelectArrawIcon(entry.getValue());
                        } else if (entry.getKey().equals(3)) {
                            //消息中心图标
                            entity.setMessageIcon(entry.getValue());
                        } else if (entry.getKey().equals(4)) {
                            //头部4个推荐位背景图
                            entity.setRecommendBg(entry.getValue());
                        }
                    }
                }

                if (dto.getTopColorList() != null) {
                    //遍历顶部色值
                    for (Map.Entry<Integer, String> entry : dto.getTopColorList().entrySet()) {
                        if (entry.getKey().equals(1)) {
                            //社区名称色值
                            entity.setMainColor(entry.getValue());
                        } else if (entry.getKey().equals(2)) {
                            //消息数字颜色
                            entity.setMessageColor(entry.getValue());
                        }
                    }
                }

                if (dto.getBottomColorList() != null) {
                    //遍历底部色值
                    for (Map.Entry<Integer, String> entry : dto.getBottomColorList().entrySet()) {
                        if (entry.getKey().equals(1)) {
                            //底部导航栏背景色
                            entity.setFootBg(entry.getValue());
                        }
                    }
                }

                if (dto.getBottomImageList() != null) {
                    //遍历底部图片
                    for (Map.Entry<Integer, String> entry : dto.getBottomImageList().entrySet()) {
                        if (entry.getKey().equals(1)) {
                            //首页图标
                            entity.setHomeIcon(entry.getValue());
                        } else if (entry.getKey().equals(2)) {
                            //管家图标
                            entity.setManagerIcon(entry.getValue());
                        } else if (entry.getKey().equals(3)) {
                            //邻聚图标
                            entity.setSocialIcon(entry.getValue());
                        } else if (entry.getKey().equals(4)) {
                            //我的图标
                            entity.setMineIcon(entry.getValue());
                        } else  if (entry.getKey().equals(5)) {
                            //扫码/报事/发照片图标 (支持报事)
                            entity.setMoreIcon(entry.getValue());
                        } else if (entry.getKey().equals(6)) {
                            if ( QDStringUtil.isNotEmpty(request.getProjectId())) {
                                Project project = projectReadService.get(Long.parseLong(request.getProjectId()));
                                if (QDStringUtil.isNotNull(project) && 0 == project.getGateType()) {
                                    //扫码/报事/发照片图标 (不支持报事)
                                    entity.setMoreIcon(entry.getValue());
                                }
                            }
                        }
                    }
                }

                data.setAppSkinEntity(entity);
            } else {
                response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
                return response;
            }
            response.setData(data);
            return response;
        } catch (Exception ex) {
            return handleException(GetAppSkinResponseData.class, ex);
        }
    }

    @HTTP(alias = "getAccessControAnimation")
    @ExplainAnnotation(explain = "获取门禁动画")
    public Response<GetAccessControAnimationResponseData> getAccessControAnimation (GetAccessControAnimationRequest request) {

        Response<GetAccessControAnimationResponseData>  response = new Response<GetAccessControAnimationResponseData>();
        GetAccessControAnimationResponseData data = new GetAccessControAnimationResponseData();
        AccessControAnimationBean entity = new AccessControAnimationBean();

        try {
            GetEntranceGuardTopicResponseData entranceGuardTopicResponseData = taskEntranceGuardRpc.getEntranceGuardTopic();
            checkAndContinue(entranceGuardTopicResponseData);
            if (QDStringUtil.isNotNull(entranceGuardTopicResponseData.getTaskEntranceGuardTopic())) {
                TaskEntranceGuardTopic taskEntranceGuardTopic = entranceGuardTopicResponseData.getTaskEntranceGuardTopic();
                entity.setCachCode(String.valueOf(taskEntranceGuardTopic.getUpdateAt()));
                entity.setZipUrl(taskEntranceGuardTopic.getZipurl());
                String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
                if (Integer.parseInt(initVersion)>=300000) {
                    entity.setStatus(0);
                } else {
                    entity.setStatus(taskEntranceGuardTopic.getStatus()==1?0:1);//rpc 0:可用 1:不可用
                }
                data.setEntity(entity);
            } else {
                entity.setStatus(0);
                data.setEntity(entity);
            }
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
        } catch (ServiceException ex) {
            return handleException(GetAccessControAnimationResponseData.class, ex);
        }
        return response;

    }
}
