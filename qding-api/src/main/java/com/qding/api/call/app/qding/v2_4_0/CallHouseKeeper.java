package com.qding.api.call.app.qding.v2_4_0;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.cache.redis.LongForWheelCache;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.ReportReceipt;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data.GetReportReceiptResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.EmpLocationBean;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.HkLocationBean;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.JsonToHouseKeeperBean;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.JsonToWorkerBean;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.ReportTaskBean;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.TaskEvaluateBean;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.request.GetApplyAccessTimeConfigRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.request.GetHkLocationRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.request.GetReportReceiptRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.request.GetReportServiceItemsRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.request.GetWorkTasksRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.request.ReportEvaluateRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.response.data.GetApplyAccessTimeConfigResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.response.data.GetHkLocationResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.response.data.GetReportServiceItemsResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.response.data.GetWorkTasksResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.response.data.ReportEvaluateResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.wheel.LimitCondition;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.wheel.Marketing;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.wheel.MarketingInfo;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.wheel.ProjectInfo;
import com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.wheel.RegionInfo;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.ActivityMessage;
import com.qding.api.imessage.IntegralMessage;
import com.qding.api.imessage.IntegralMessageBeanT;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.struts.request.GetServiceItemListRequest;
import com.qding.brick.struts.response.GetServiceItemListResponse;
import com.qding.core.common.util.DateUtil;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.http.QDHttpClientService;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hk.domain.MatterApply;
import com.qding.hk.domain.MatterReply;
import com.qding.hk.rpc.model.matter.DispatchInfoDto;
import com.qding.hk.rpc.model.matter.MatterProcessInfoExt;
import com.qding.hk.rpc.model.matter.MatterRoleDto;
import com.qding.hk.rpc.model.matter.MatterSnapOverViewDto;
import com.qding.hk.rpc.request.matter.ApplyScoreRequest;
import com.qding.hk.rpc.request.matter.FindMatterReplyRequest;
import com.qding.hk.rpc.request.matter.GetMatterSnapOverviewRequest;
import com.qding.hk.rpc.response.matter.GetMatterSnapOverviewResponse;
import com.qding.hk.rpc.response.matter.MatterApplyResponse;
import com.qding.hk.rpc.response.matter.MatterReplyResultResponse;
import com.qding.hk.rpc.service.IMatterRpcService;
import com.qding.manager.common.ModelResult;
import com.qding.manager.domain.Puser;
import com.qding.manager.domain.PuserThirdpart;
import com.qding.manager.domain.Role;
import com.qding.manager.service.IPuserRPCService;
import com.qding.manager.strut.request.GetAppRoleListByPuserIdAndProjectIdRequest;
import com.qding.manager.strut.response.GetAppRoleListByPuserIdAndProjectIdResponse;
import com.qding.passport.service.IMemberLocationService;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.passport.struct.response.MemberLocationResponse;
import com.qding.property.domain.MatterType;
import com.qding.property.rpc.service.PropertyRpcService;
import com.qding.property.rpc.service.response.MatterTypeResponse;

/**
 * Created by qd on 2016/6/8.
 */
public class CallHouseKeeper extends com.qding.api.call.app.qding.v2_1_0.CallHouseKeeper {


    @Autowired
    private IMatterRpcService matterService;

    @Autowired
    private IntegralMessage integralMessage;
    
    @Autowired
    private ActivityMessage activityMessage;

    @Autowired
    private IProfileService profileAPI;

    @Autowired
    private PropertyRpcService propertyRpcService;

    @Autowired
    private IPuserRPCService puserRPCService;

    @Autowired
    private IMemberLocationService memberLocationService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private AppConfigRemote appConfigRemote;
    
    @Autowired
    private LongForWheelCache cache;


    // 定义一个缓冲的线程值 线程池的大小根据任务变化
    private ExecutorService threadPool1 = Executors.newCachedThreadPool();

    private ExecutorService threadPool2 = Executors.newCachedThreadPool();

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallHouseKeeper.class);


    @HTTP(alias = "getHkLocation")
    @ExplainAnnotation(explain = "获取管家地理位置")
    public Response<GetHkLocationResponseData> getHkLocation(GetHkLocationRequest request) {
        Response<GetHkLocationResponseData> response = new Response<GetHkLocationResponseData>();
        GetHkLocationResponseData data = new GetHkLocationResponseData();
        String reportId = request.getReportId();

        try {
            Long startTime = System.currentTimeMillis();
            MatterApplyResponse matterApplyResponse = matterService.searchMatterApplyByApplyId(reportId);
            checkAndContinue(matterApplyResponse);
            Long lastTime = System.currentTimeMillis();
            MatterApply matterApply = matterApplyResponse.getMatterApply();
            Long projectId = matterApply.getProjectId();
            System.out.print("matterService.searchMatterApplyByApplyId=========》"+(lastTime - startTime));
            Long startTime2 = System.currentTimeMillis();
            boolean isLongHu = isLongHuForFee(projectId);
            Long lastTime2 = System.currentTimeMillis();
            System.out.print("isLongHuForFee=========》"+(lastTime2 - startTime2));
            if (isLongHu) {
                fittingLongHuLocation(data, reportId);
            } else {
                fittingNoLongHuLocation(data, reportId,projectId);
            }

        } catch (Exception e) {
            return handleException(GetHkLocationResponseData.class, e);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;

    }


    @HTTP(alias = "getReportReceipts")
    @ExplainAnnotation(explain = "通过获取报事|工单回执列表")
    public Response<GetReportReceiptResponseData> getReportReceipts(GetReportReceiptRequest request) {

        try {
            Response<GetReportReceiptResponseData> response = new Response<GetReportReceiptResponseData>();
            GetReportReceiptResponseData data = new GetReportReceiptResponseData();
            FindMatterReplyRequest findMatterReplyRequest = new FindMatterReplyRequest();
            findMatterReplyRequest.setApplyId(request.getReportId());
            findMatterReplyRequest.setOrderId(request.getTaskId());
            MatterReplyResultResponse matterReplyResultResponse = matterService.findUserMatterReply(findMatterReplyRequest);
            checkAndContinue(matterReplyResultResponse);
            List<MatterReply> matterReplyList = matterReplyResultResponse.getMatterReplyList();
            List<ReportReceipt> list = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(matterReplyList)){
                for (MatterReply matterReply : matterReplyList) {
                    try {
                        ReportReceipt reportReceipt = transfor(ReportReceipt.class, matterReply);
                        String replyContent = matterReply.getContent();
                        if( QDStringUtil.isEmpty(replyContent) ) continue;
                        boolean isContains = replyContent.contains("fullContext");
                        if( isContains ) {
                            JSONObject contentJson = JSONObject.parseObject(replyContent);
                            replyContent = contentJson.getString("fullContext");
                        }
                        reportReceipt.setContent(replyContent);
                        list.add(reportReceipt);
                    }catch (Exception ex) {
                        continue;
                    }
                }

            }

            data.setList(list);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;

        } catch (Exception e) {
            return handleException(GetReportReceiptResponseData.class, e);
        }
    }

    @HTTP(alias = "getWorkTasks")
    @ExplainAnnotation(explain = "获取报事处理详情工单列表")
    public Response<GetWorkTasksResponseData> getWorkTasks(GetWorkTasksRequest request) {

        Response<GetWorkTasksResponseData> response = new Response<GetWorkTasksResponseData>();
        GetWorkTasksResponseData data = new GetWorkTasksResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());

        GetMatterSnapOverviewRequest getMatterSnapOverviewRequest = new GetMatterSnapOverviewRequest();
        getMatterSnapOverviewRequest.setApplyId(request.getReportId());
        GetMatterSnapOverviewResponse getMatterSnapOverviewResponse = matterService.getMatterSnapOverview(getMatterSnapOverviewRequest);
        try {
            checkAndContinue(getMatterSnapOverviewResponse);
            MatterSnapOverViewDto matterSnapOverViewDto = getMatterSnapOverviewResponse.getDto();
            List<DispatchInfoDto> dispatchInfoDtoList = matterSnapOverViewDto.getTaskList();
            List<ReportTaskBean> list = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(dispatchInfoDtoList)) {
                for (DispatchInfoDto dispatchInfoDto : dispatchInfoDtoList) {
                    MatterProcessInfoExt processInfo = dispatchInfoDto.getProcessInfo();
                    ReportTaskBean reportTaskBean = transfor(ReportTaskBean.class, processInfo);
                    ModelResult modelResult = puserRPCService.getPuserInfoByPuserId(processInfo.getProcessorId());

                    if (HttpStatus.OK.getStatusCode() == modelResult.getCode()) {
                        Puser puser = (Puser) modelResult.getEntity();
                        if (puser != null && QDStringUtil.isNotNull(puser.getMobile())) {
                            String mobile = puser.getMobile();
                            if (puser.getMobile().length() == 11) {
                                StringBuffer mobileBuffer = new StringBuffer(mobile);
                                mobile = mobileBuffer.replace(3, 7, "****").toString();
                            }
                            reportTaskBean.setMobile(mobile);
                        }
                    }

                    DecimalFormat df = new DecimalFormat("0.00");
                    //如果实际费用不为空，则使用实际费用，否则使用预计费用
                    if (QDStringUtil.isNotNull(processInfo.getCostTatal()) && processInfo.getCostTatal()>0){
                        reportTaskBean.setTotalPrice(df.format(BigDecimal.valueOf(processInfo.getCostTatal()).divide(new BigDecimal("100"))));
                        reportTaskBean.setPriceLable("费用合计");
                    } else {
                        reportTaskBean.setTotalPrice("0.00");
                        if (QDStringUtil.isNotNull(processInfo.getGuidePrice())){
                            reportTaskBean.setTotalPrice(df.format(BigDecimal.valueOf(processInfo.getGuidePrice()).divide(new BigDecimal("100"))));
                        }
                        reportTaskBean.setPriceLable("费用预计");
                    }

                    reportTaskBean.setReportId(request.getReportId());
                    reportTaskBean.setTaskId(dispatchInfoDto.getMatterOrderId());
                    List<MatterRoleDto> matterRoleDtoList = dispatchInfoDto.getRoles();
                    StringBuffer roles = new StringBuffer();
                    if (CollectionUtils.isNotEmpty(matterRoleDtoList)) {
                        for (MatterRoleDto matterRoleDto : matterRoleDtoList) {
                            roles.append(matterRoleDto.getRoleName() + " ");
                        }
                        reportTaskBean.setRole(roles.toString());
                    }
                    if (QDStringUtil.isNotEmpty(processInfo.getMatterType())) {
                        MatterTypeResponse matterTypeResponse = propertyRpcService.findMatterTypeById(processInfo.getMatterType());
                        if (HttpStatus.OK.getStatusCode() == matterTypeResponse.getReturnInfo().getCode() && QDStringUtil.isNotNull( matterTypeResponse.getMatterType())) {
                            MatterType matterType = matterTypeResponse.getMatterType();
                            reportTaskBean.setReportTypeName(matterType.getMatterType());
                        }
                    }

                    list.add(reportTaskBean);
                }

                data.setList(list);
                response.setCode(HttpStatus.OK.getStatusCode());
                response.setData(data);

            }
        } catch (ServiceException e) {
            return handleException(GetWorkTasksResponseData.class, e);
        }

        return response;

    }


    /**
     * 报事评价
     * @param request
     * @return
     */
    @HTTP(alias = "scoreReport")
    @ExplainAnnotation(explain = "报事评价")
    public Response<ReportEvaluateResponseData> scoreReport(ReportEvaluateRequest request) {

        Response<ReportEvaluateResponseData> response = new Response<ReportEvaluateResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        ReportEvaluateResponseData data = new ReportEvaluateResponseData();
        boolean integralFlag = false;
        
        MatterApplyResponse matterApplyResponse = matterService.searchMatterApplyByApplyId(request.getReportId());
        if(2!=matterApplyResponse.getMatterApply().getStatus()){
            response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            data.setMessage("当前报事还未处理完成");
            response.setData(data);
          return response;
        }
        //start------------------------------------------------------------抽奖活动
        //GetCurrentEvaluationNumResponse response1=null;
        /*GetCountByRoomIdResponse response1=null;
        if(activityMessage.getStatus().equals("0")){
        	 if (matterApplyResponse!=null && HttpStatus.OK.getStatusCode() == matterApplyResponse.getReturnInfo().getCode()) {
        		 //response1=matterService.getCurrentEvaluationNum(matterApplyResponse.getMatterApply().getUserId());
        		 if(StringUtils.isNotBlank(matterApplyResponse.getMatterApply().getRoomId())){
        			 response1=matterService.getCountByroomId(matterApplyResponse.getMatterApply().getUserId(), 
        					 matterApplyResponse.getMatterApply().getRoomId());
        		 }
            }
        }*/
        //end--------------------------------------------------------------
        
        if (QDStringUtil.isNotNull(request.getReportEvaluateEntity())) {
            ApplyScoreRequest scoreRequest = transfor(ApplyScoreRequest.class, request.getReportEvaluateEntity());
            scoreRequest.setApplyId(request.getReportId());
            scoreRequest.setScore(Constant.hkReportScoreMap.get(scoreRequest.getScore()));
            BaseResponse scoreResponse = matterService.matterApplyScore(scoreRequest);
            if (HttpStatus.OK.getStatusCode() == scoreResponse.getReturnInfo().getCode()) {
                integralFlag = true;
            }

        } else {
            List<TaskEvaluateBean> taskEvaluateBeanList = request.getTaskEvaluateBeanList();
            for (TaskEvaluateBean taskEvaluateBean : taskEvaluateBeanList) {
                ApplyScoreRequest scoreRequest = transfor(ApplyScoreRequest.class, taskEvaluateBean);
                scoreRequest.setApplyId(request.getReportId());
                scoreRequest.setScore(Constant.hkReportScoreMap.get(scoreRequest.getScore()));
                BaseResponse scoreResponse = matterService.matterApplyScore(scoreRequest);
                if (HttpStatus.OK.getStatusCode() != scoreResponse.getReturnInfo().getCode()) {
                    continue;
                }
            }
            integralFlag = true;
        }
        //TODO 为便于调试，integralFlag 强制为true， 调试完注释掉
//        integralFlag = true;
        
        if (integralFlag) {
            if (HttpStatus.OK.getStatusCode() == matterApplyResponse.getReturnInfo().getCode()) {
                String userId = matterApplyResponse.getMatterApply().getUserId();
                Long projectId = matterApplyResponse.getMatterApply().getProjectId();
                logger.info("Integral get matterApplyResponse result:" + JSON.toJSONString(matterApplyResponse));
                //加入积分规则
                IntegralMessageBeanT integralMessageBeanT = new IntegralMessageBeanT(userId, Constant.INTEGRAL_REPAIR_EVALUATE, userId, projectId, System.currentTimeMillis(), Constant.INCOME_OPT_TYPE, null, request.getReportId());
                integralMessage.assembleIntegralMessage(integralMessageBeanT);
                data.setToast(Constant.integralToastMap.get(Constant.INTEGRAL_REPAIR_EVALUATE));
                
                if(activityMessage.getStatus().equals("0")){
                    //龙文脉报事客户参与抽奖活动
                	/*Map<String,Object> param=new HashMap<String,Object>();
                	param.put("currentTime", System.currentTimeMillis());
                	param.put("accountId", userId);
                	param.put("projectId", projectId);
                	param.put("roomId", matterApplyResponse.getMatterApply().getRoomId());
                	param.put("reportId", request.getReportId());
                	param.put("reportTime",matterApplyResponse.getMatterApply().getCreateTime());
                	activityMessage.noticeSysConfig(param,1);
                	logger.info("报事参与抽奖活动---"+param);*/
                	//报事评价抽奖活动
                	doWheel(matterApplyResponse.getMatterApply());
                }
            } else {
                logger.error("Integral for scoreReport can't get MatterApplyResponse");
            }
        }
        response.setData(data);

        return response;

    }
    
    private void doWheel(MatterApply matterApply){
        
        List<Marketing> marketings = marketingConf();
        
        if(marketings == null){
            return;
        }
        
        for(Marketing marketing : marketings){
            boolean wheelable = wheelable(marketing,matterApply);
            if(wheelable){
              Map<String,Object> param = new HashMap<String,Object>();
              param.put("accountId", matterApply.getUserId());
              param.put("projectId", matterApply.getProjectId());
              param.put("reportId", matterApply.getId());
              param.put("wheelId", marketing.getMarketingInfo().getWheelId());
              logger.info("报事评价抽奖活动（"+marketing.getMarketingInfo().getName()+"）param ---> "+param);
              activityMessage.noticeSysConfig(param,2);
          }
        }
    }
    
    /**
     * 从配置文件读取活动抽奖配置
     */
    private List<Marketing> marketingConf (){
        
        String confJson = APIPropertiesClient.getValue("score.report.conf");
        
        if(StringUtils.isBlank(confJson)){
            return null;
        }
        
        List<Marketing> marketings = JSONArray.parseArray(confJson, Marketing.class);
        
        return marketings;
    }
    
    /**
     * 重庆龙湖报事评价抽奖活动，是否可参加校验
     */
    private boolean wheelable(Marketing marketing,MatterApply matterApply){
        
        logger.info("报事评价抽奖活动（"+marketing.getMarketingInfo().getName()+"）资格验证开始---> matter id :"+matterApply.getId());
        
        // 活动时间校验，报事时间、评价时间 都在 配置范围内
        MarketingInfo marketingInfo = marketing.getMarketingInfo();
        
        long scoreTime = System.currentTimeMillis();
        long reportTime = matterApply.getCreateTime();
        long startTime = DateUtil.strToDate(marketingInfo.getStartTime(), marketingInfo.getStartTimeFormat()).getTime();
        long endTime = DateUtil.strToDate(marketingInfo.getEndTime(), marketingInfo.getEndTimeFormat()).getTime();
        
        if(!(reportTime>=startTime && reportTime<=endTime && scoreTime>=startTime && scoreTime<=endTime)){
            logger.error("报事时间、评价时间 不在活动时间段内！");
            return false;
        }
        
        // 参与次数限制 （1-手机号次数限制）
        LimitCondition limitCondition = marketing.getLimitCondition();
        
        String cacheKey = null;
        switch (limitCondition.getLimitType()) {
            case Constant.LONGFOR_WHEEL_LIMIT_TYPE_PHONE:
                cacheKey = "longforWheel:" + marketingInfo.getId() + ":" + limitCondition.getLimitType() + ":" + matterApply.getPhone();
                break;
            default:
                break;
        }
        
//        TODO 方便调试，每次删除缓存，调试完注释掉
//        cache.delKey(cacheKey);
        Object cacheValue = cache.get(cacheKey);
        if(cacheValue!=null && limitCondition.getLimitNum() <= Integer.parseInt(cacheValue.toString())){
            logger.error("key["+cacheKey+"] times limited，no gift ！");
            return false;
        }
        
        Long projectId = matterApply.getProjectId();
        Project project = projectReadService.get(projectId);
        
        // 是否龙湖社区限制
        if(marketing.getMarketingInfo().getMustLongfor()!=null && marketing.getMarketingInfo().getMustLongfor() == 1){
            boolean isLongfor = projectReadService.isLongForProject(projectId);
            if(!isLongfor){
                logger.error("not LONGFOR project, no gift!");
                return false;
            }
        }
        
        boolean isConfProject = false;
        boolean isConfRegion = false;
        
        // 是否指定社区
        List<ProjectInfo> projectInfos = marketing.getProjects();
        if(projectInfos!=null && projectInfos.size()>0){
            for(ProjectInfo projectInfo : projectInfos){
                if(projectInfo.getProjectId().longValue() == projectId.longValue()){
                    isConfProject = true;
                    break;
                }
            }
        }
        // 是否指定地区
        List<RegionInfo> regionInfos = marketing.getRegions();
        if(regionInfos!=null && regionInfos.size()>0){
            for(RegionInfo regionInfo : regionInfos){
                if(regionInfo.getRegionId().longValue() == project.getRegionId()){
                    isConfRegion = true;
                    break;
                }
            }
        }
        
        if(!(isConfRegion || isConfProject)){
            logger.error("projectId["+projectId+"] not config region/project, no gift!");
            return false;
        }
        
        // 可以参加活动，参加次数增加
        cacheValue = cacheValue==null ? 1 : Integer.parseInt(cacheValue.toString())+1;
        cache.set(cacheKey, cacheValue, endTime);
        
        logger.info("报事评价抽奖活动（"+marketing.getMarketingInfo().getName()+"）资格验证  通过---> ");
        return true;
    }
    
    public static void main(String[] args) {
        String aaa = DateUtil.date2string(new Date(1491377114786l), "yyyy-MM-dd HH:mm:ss");
        System.out.println(aaa);
    }
    
    @HTTP(alias = "getApplyAccessTime")
    @ExplainAnnotation(explain = "访客通行申请边际时间点及当前系统时间")
    public Response<GetApplyAccessTimeConfigResponseData> getApplyAccessConfigTime(GetApplyAccessTimeConfigRequest request) {

        Response<GetApplyAccessTimeConfigResponseData> response = new Response<GetApplyAccessTimeConfigResponseData>();
        GetApplyAccessTimeConfigResponseData data = new GetApplyAccessTimeConfigResponseData();
        try {
            String borderValue = APIPropertiesClient.getValue("applyAccessTime");
            if (QDStringUtil.isNotNull(borderValue)) {
                Long sysTime = System.currentTimeMillis();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String time = format.format(sysTime) + " " + borderValue;
                SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = format2.parse(time);
                Long borderTime = date.getTime();
                Long difftime = borderTime - sysTime;
                data.setBorderTime(borderValue);
                data.setSysTime(sysTime);
                if (difftime <= 0) {
                    data.setIsShowLable(true);
                }
            }

            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
            return response;
        } catch (Exception e) {
            return handleException(GetApplyAccessTimeConfigResponseData.class, e);
        }
    }



    @HTTP(alias = "getReportServiceItems")
    @ExplainAnnotation(explain = "获取报事服务",desc = "直联模式")
    @Deprecated
    public Response<GetReportServiceItemsResponseData> getReportServiceItems (GetReportServiceItemsRequest request) {

        Response<GetReportServiceItemsResponseData> response = new  Response<GetReportServiceItemsResponseData>();

        try {
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
            Long projectId = Long.parseLong(request.getProjectId());
            String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());
            GetServiceItemListRequest serviceItemListRequest = new GetServiceItemListRequest();
            serviceItemListRequest.setCode(null);
            serviceItemListRequest.setProjectId(projectId);
            serviceItemListRequest.setIsFixDirect(1);
            serviceItemListRequest.setVersion(version);
            serviceItemListRequest.setType(brickSourceType);
            GetServiceItemListResponse serviceItemListResponse = appConfigRemote.getBindServiceItemList(serviceItemListRequest);
            checkAndContinue(serviceItemListResponse);
            List<ServiceItem> serviceItems = serviceItemListResponse.getServiceItemList();
            List<ProjectService> services = transforList(ProjectService.class, serviceItems);
            GetReportServiceItemsResponseData data = new GetReportServiceItemsResponseData();
            data.setServices(services);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;
        }catch ( Exception ex) {

            return handleException(GetReportServiceItemsResponseData.class, ex);
        }

    }



/***********************************************************   华丽的私有方法分割线  **********************************************************************************/

    /**
     * 组装非龙湖社区报事相关人员地理位置
     *
     * @param data
     * @param reportId
     * @param projectId
     * @throws ServiceException
     */
    private void fittingNoLongHuLocation(GetHkLocationResponseData data, String reportId, Long projectId) throws ServiceException {

        Long startTime4 = System.currentTimeMillis();
        List<EmpLocationBean> empLocationBeanList = Lists.newArrayList();
        List<HkLocationBean> hkLocationBeanList = Lists.newArrayList();
        //获取管家列表===》puserId
        List<String> hkPuserIdList = Lists.newArrayList();
        Long startTime = System.currentTimeMillis();
        ModelResult modelResult = puserRPCService.getPuserThirdPartListByProjectId(String.valueOf(projectId));
        Long lastTime = System.currentTimeMillis();
        System.out.print("puserRPCService.getPuserThirdPartListByProjectId=========》"+(lastTime - startTime));
        checkAndContinue(modelResult);
        try {
            List<PuserThirdpart> puserThirdparts =(List<PuserThirdpart>) modelResult.getData().get("entity");
            if (CollectionUtils.isNotEmpty(puserThirdparts)) {
                for (PuserThirdpart puserThirdpart : puserThirdparts) {
                    hkPuserIdList.add(puserThirdpart.getPuserId());
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        Project project = projectReadService.get(projectId);
        String  projectDesc = (QDStringUtil.isNotNull(project.getRegionName()) ? project.getRegionName() + " - " : "") + (QDStringUtil.isNotNull(project.getName()) ? project.getName() : "");

        GetMatterSnapOverviewRequest getMatterSnapOverviewRequest = new GetMatterSnapOverviewRequest();
        getMatterSnapOverviewRequest.setApplyId(reportId);
        //获取当前报事所负责处理的处理人列表====>puserId
        GetMatterSnapOverviewResponse getMatterSnapOverviewResponse = matterService.getMatterSnapOverview(getMatterSnapOverviewRequest);
        checkAndContinue(getMatterSnapOverviewResponse);
        MatterSnapOverViewDto matterSnapOverViewDto = getMatterSnapOverviewResponse.getDto();
        List<DispatchInfoDto> dispatchInfoDtoList = matterSnapOverViewDto.getTaskList();
        Set<String> empPuserIdList = new HashSet<>();
        Long startTime3 = System.currentTimeMillis();
        if (CollectionUtils.isNotEmpty(dispatchInfoDtoList) ) {

            List<Future<EmpLocationBean>> futureList = new ArrayList<Future<EmpLocationBean>>();
            for (DispatchInfoDto dispatchInfoDto : dispatchInfoDtoList) {
                if (QDStringUtil.isNotNull(dispatchInfoDto.getProcessInfo().getProcessorId()) && QDStringUtil.isNotEmpty(dispatchInfoDto.getMatterOrderId())) {
                    empPuserIdList.add(dispatchInfoDto.getProcessInfo().getProcessorId());

                    GetHkLocationThread hkLocationThread = new GetHkLocationThread(reportId,projectId,dispatchInfoDto.getProcessInfo().getProcessorId(),dispatchInfoDto.getMatterOrderId());
                    Future<EmpLocationBean> future =threadPool1.submit(hkLocationThread);
                    futureList.add(future);

                    //获取处理人地理位置信息
//                    HkLocationBean hkLocationBean = fittingLocation(reportId, projectId,dispatchInfoDto.getProcessInfo().getProcessorId());
                   /* if (QDStringUtil.isNotNull(hkLocationBean)) {
                        EmpLocationBean empLocationBean = transfor(EmpLocationBean.class, hkLocationBean);
                        if ( QDStringUtil.isNotNull(empLocationBean.getIsLocation()) && empLocationBean.getIsLocation() == 1) {
                            data.setIsLocation(1);
                        }
                        empLocationBean.setTaskId(dispatchInfoDto.getMatterOrderId());
                        empLocationBeanList.add(empLocationBean);
                        System.out.print("=============");
                    }*/
                }
            }


            for (Future<EmpLocationBean> ocationBeanFuture : futureList) {
                try{
                    EmpLocationBean  locationBean = ocationBeanFuture.get(2, TimeUnit.SECONDS);
                    if (QDStringUtil.isNotNull(locationBean)) {
                        EmpLocationBean empLocationBean = transfor(EmpLocationBean.class, locationBean);
                        if ( QDStringUtil.isNotNull(empLocationBean.getIsLocation()) && empLocationBean.getIsLocation() == 1) {
                            data.setIsLocation(1);
                        }
                        empLocationBean.setProjectDesc(projectDesc);
                        empLocationBeanList.add(empLocationBean);
                        System.out.print("=============");
                    }
                } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error(" getHkLocationBean from future is wrong !!",e);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    logger.error(" getHkLocationBean from future is wrong !!",e);
                } catch (TimeoutException e) {
                    logger.error(" getHkLocationBean from future is timeout !!",e);
                    e.printStackTrace();
                }
            }
        }

        Long lastTime3 = System.currentTimeMillis();
        System.out.print("获取处理人地理位置信息=========》"+(lastTime3 - startTime3));
        if (CollectionUtils.isNotEmpty(empPuserIdList)) {
            for (String puserId : empPuserIdList) {
                //过滤管家中在工单处理人列表中出现过的人
                if (hkPuserIdList.contains(puserId)) {
                    hkPuserIdList.remove(puserId);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(hkPuserIdList)) {
            List<Future<EmpLocationBean>> futureList = new ArrayList<Future<EmpLocationBean>>();
            for (String puserId : hkPuserIdList) {
                GetHkLocationThread hkLocationThread = new GetHkLocationThread(reportId, projectId,puserId,null);
                Future<EmpLocationBean> future =threadPool2.submit(hkLocationThread);
                futureList.add(future);
            }

            for (Future<EmpLocationBean> ocationBeanFuture : futureList) {
                try{
                    EmpLocationBean  locationBean = ocationBeanFuture.get(2, TimeUnit.SECONDS);
                    if (QDStringUtil.isNotNull(locationBean)) {
                        HkLocationBean hkLocationBean = transfor(HkLocationBean.class, locationBean);
                        if ( QDStringUtil.isNotNull(hkLocationBean.getIsLocation()) && hkLocationBean.getIsLocation() == 1) {
                            data.setIsLocation(1);
                        }
                        hkLocationBean.setProjectDesc(projectDesc);
                        hkLocationBeanList.add(hkLocationBean);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logger.error(" getHkLocationBean from future is wrong !!",e);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                    logger.error(" getHkLocationBean from future is wrong !!",e);
                } catch (TimeoutException e) {
                    logger.error(" getHkLocationBean from future is timeout !!",e);
                    e.printStackTrace();
                }
            }
        }
        Long lastTime4 = System.currentTimeMillis();
        System.out.print("获取管家地理位置信息=========》"+(lastTime4 - startTime4));
        data.setHkLocationList(hkLocationBeanList);
        data.setEmpLocationList(empLocationBeanList);

    }

    /**
     * 组装龙湖社区报事相关人员地理位置
     *
     * @param data
     * @param reportId
     * @throws UnsupportedEncodingException
     */
    private void fittingLongHuLocation(GetHkLocationResponseData data, String reportId) throws UnsupportedEncodingException {

        List<HkLocationBean> hkLocationList = Lists.newArrayList();
        List<EmpLocationBean> empLocationList = Lists.newArrayList();
        StringBuffer urlsb = new StringBuffer(APIPropertiesClient.getValue("hk_location_url"));
        String pr = String.format("%s", reportId);
        JSONObject pJson = new JSONObject();
        pJson.put("informId", pr);
        logger.info("url ============>" + urlsb.toString().trim() + "?body=" + URLEncoder.encode(pJson.toJSONString(), "utf-8"));
        JSONObject responseJson = QDHttpClientService.sendGetRequest4Json(urlsb.toString().trim() + "?body=" + URLEncoder.encode(pJson.toJSONString(), "utf-8"), "utf-8");

        if (QDStringUtil.isNotNull(responseJson) && 200 == responseJson.getInteger("code") &&
                QDStringUtil.isNotNull(responseJson.getJSONObject("data"))) {

            JSONObject informinfoJson = responseJson.getJSONObject("data").getJSONObject("informinfo");
            String applyId = informinfoJson.getString("informId");
            String buildingName = informinfoJson.getString("buildingName");
            String cityName = informinfoJson.getString("cityName");
            String projectName = informinfoJson.getString("projectName");
            String projectDesc = (QDStringUtil.isNotNull(cityName) ? cityName + " - " : "") + (QDStringUtil.isNotNull(projectName) ? projectName : "");

            if (informinfoJson.containsKey("workers")) {
                JSONArray workersJsonArray = informinfoJson.getJSONArray("workers");
                if (QDStringUtil.isNotNull(workersJsonArray) && workersJsonArray.size() > 0) {
                    for (Object worker : workersJsonArray) {
                        try {
                            JSONObject workerJson = (JSONObject) worker;
                            JsonToWorkerBean jsonToWorkBean = JSONObject.toJavaObject(workerJson, JsonToWorkerBean.class);
                            EmpLocationBean empLocationBean = transfor(EmpLocationBean.class, jsonToWorkBean);
                            if (empLocationBean.getIsLocation() == 1) {
                                data.setIsLocation(1);
                            }
                            empLocationBean.setProjectDesc(projectDesc);
                            empLocationBean.setReportId(applyId);
                            GetMemberRequest memberRequest = new GetMemberRequest();
                            memberRequest.setMemberId(empLocationBean.getMemberId());
                            GetMemberResponse getMemberResponse = profileAPI.getMemberById(memberRequest);
                            if (HttpStatus.OK.getStatusCode() == getMemberResponse.getReturnInfo().getCode()) {
                                empLocationBean.setHeadImage(QDStringUtil.isNotNull(getMemberResponse.getMemberInfo().getHeadImg()) ? getMemberResponse.getMemberInfo().getHeadImg() : "");
                            }
                            empLocationList.add(empLocationBean);

                        } catch (Exception ex) {
                            logger.error("getHkLocation error:", ex);
                            continue;
                        }
                    }
                }
            }

            if (informinfoJson.containsKey("keepers")) {
                JSONArray keepersJsonArray = informinfoJson.getJSONArray("keepers");
                if (QDStringUtil.isNotNull(keepersJsonArray) && keepersJsonArray.size() > 0) {
                    for (Object keeper : keepersJsonArray) {
                        try {
                            JSONObject keeperJson = (JSONObject) keeper;
                            JsonToHouseKeeperBean jsonToWorkBean = JSONObject.toJavaObject(keeperJson, JsonToHouseKeeperBean.class);
                            HkLocationBean hkLocationBean = transfor(HkLocationBean.class, jsonToWorkBean);
                            if (hkLocationBean.getIsLocation() == 1) {
                                data.setIsLocation(1);
                            }
                            hkLocationBean.setProjectDesc(projectDesc);
                            hkLocationBean.setReportId(reportId);
                            GetMemberRequest memberRequest = new GetMemberRequest();
                            memberRequest.setMemberId(hkLocationBean.getMemberId());
                            GetMemberResponse getMemberResponse = profileAPI.getMemberById(memberRequest);
                            if (HttpStatus.OK.getStatusCode() == getMemberResponse.getReturnInfo().getCode()) {
                                hkLocationBean.setHeadImage(QDStringUtil.isNotNull(getMemberResponse.getMemberInfo().getHeadImg()) ? getMemberResponse.getMemberInfo().getHeadImg() : "");
                            }
                            hkLocationList.add(hkLocationBean);
                        } catch (Exception ex) {
                            logger.error("getHkLocation error:", ex);
                            continue;
                        }
                    }
                }
            }
        }
        data.setEmpLocationList(empLocationList);
        data.setHkLocationList(hkLocationList);
    }


    class GetHkLocationThread implements Callable<EmpLocationBean> {

        private String reportId;
        private Long projectId;
        private String puserId;
        private String taskId;

        public GetHkLocationThread(String reportId, Long projectId, String puserId, String taskId) {
            this.reportId = reportId;
            this.projectId = projectId;
            this.puserId = puserId;
            this.taskId = taskId;
        }

        @Override
        public EmpLocationBean call() throws Exception {

            Long startTime = System.currentTimeMillis();
            MemberLocationResponse memberLocationResponse = memberLocationService.findMemberLocationByPuserId(puserId, true);
            Long lastTime = System.currentTimeMillis();
            System.out.print("emberLocationService.findMemberLocationByPuserId=========》"+(lastTime - startTime)+"\n");
            EmpLocationBean hkLocationBean =  new EmpLocationBean();
            hkLocationBean.setTaskId(taskId);
            String memberId ="";
            if (HttpStatus.OK.getStatusCode() == memberLocationResponse.getReturnInfo().getCode()
                    && QDStringUtil.isNotNull(memberLocationResponse.getMemberId())) {

                memberId = memberLocationResponse.getMemberId();
                hkLocationBean = transfor(EmpLocationBean.class, memberLocationResponse);
                if (QDStringUtil.isNotEmpty(hkLocationBean.getLatitude())) {
                    hkLocationBean.setIsLocation(1);
                }
            } else {
                Long startTime2 = System.currentTimeMillis();
                ModelResult modelResult = puserRPCService.getMidByPuserId(puserId);
                Long lastTime2 = System.currentTimeMillis();
                System.out.print("puserRPCService.getMidByPuserId =========》"+(lastTime2 - startTime2)+"\n");
                if (HttpStatus.OK.getStatusCode() == modelResult.getCode() && QDStringUtil.isNotNull( modelResult.getEntity())) {
                    memberId = String.valueOf(modelResult.getEntity());
                }
            }

            hkLocationBean.setUserId(memberLocationResponse.getUserId());
            hkLocationBean.setHeadImage(memberLocationResponse.getHeadImg());
            hkLocationBean.setReportId(reportId);
            hkLocationBean.setUserName(memberLocationResponse.getMemberName());
            String roleNames = getRoleNamesByPuserIdAndProjectId(projectId, puserId);
            hkLocationBean.setRole(roleNames);
            hkLocationBean.setMemberId(memberId);
            hkLocationBean.setpUserId(puserId);
            hkLocationBean.setVirtualMobile(memberLocationResponse.getMobile());
            Long startTime4 = System.currentTimeMillis();

            Long lastTime4 = System.currentTimeMillis();
            System.out.print("projectReadService.get =========》"+(lastTime4 - startTime4)+"\n");
            System.out.println("===============================================================>" + JSON.toJSONString(hkLocationBean));

            return hkLocationBean;
        }
    }

    /**
     * 获取指定puserId在指定社区所处的角色
     * @param projectId
     * @param puserId
     */
    private String getRoleNamesByPuserIdAndProjectId(Long projectId, String puserId) {

        StringBuffer roleSb = new StringBuffer("");
        GetAppRoleListByPuserIdAndProjectIdRequest appRoleListByPuserIdAndProjectIdRequest = new GetAppRoleListByPuserIdAndProjectIdRequest();
        appRoleListByPuserIdAndProjectIdRequest.setProjectId(projectId);
        appRoleListByPuserIdAndProjectIdRequest.setPuserId(puserId);
        GetAppRoleListByPuserIdAndProjectIdResponse appRoleListByPuserIdAndProjectIdResponse =  puserRPCService.getAppRoleListByPuserIdAndProjectId(appRoleListByPuserIdAndProjectIdRequest);
        if (HttpStatus.OK.getStatusCode() == appRoleListByPuserIdAndProjectIdResponse.getReturnInfo().getCode()) {
            List<Role> roleList = appRoleListByPuserIdAndProjectIdResponse.getRoleList();
            if (CollectionUtils.isNotEmpty(roleList)){
                int i = 0;
                for (Role role : roleList) {
                    roleSb.append(role.getName());
                    i++;
                    if (i<roleList.size()){
                        roleSb.append(",");
                    }
                }
            }
        }
        return roleSb.toString();
    }

}
