package com.qding.api.call.app.qding.v1_4_0;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.BlueToothLog;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.PropertyChargesOrder;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.request.*;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.response.data.*;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.OwnerInfo;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.ReportEvaluate;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.IntegralMessage;
import com.qding.api.imessage.IntegralMessageBeanT;
import com.qding.api.imessage.bluetooth.BlueToothMessage;
import com.qding.api.struct.Response;
import com.qding.api.util.DateUtil;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.pojo.biz.RoomOwner;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomOwnerRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.fuwuchuang.service.IFuwuchuangService;
import com.qding.hk.rpc.request.matter.ApplyScoreRequest;
import com.qding.hk.rpc.response.bluetooth.FindBlueToothLogResponse;
import com.qding.hk.rpc.response.matter.MatterApplyResponse;
import com.qding.hk.rpc.service.IBlueToothLogRpcService;
import com.qding.profee.rpc.model.fee.FeeModel;
import com.qding.profee.rpc.request.fee.FeeCreateOrderRequest;
import com.qding.profee.rpc.request.fee.FeeRequest;
import com.qding.profee.rpc.response.fee.FeeCreateOrderResponse;
import com.qding.profee.rpc.response.fee.FeeResponse;
import com.qding.profee.rpc.response.fee.SumFeeResponse;
import com.qding.profee.rpc.service.IFeeRpcService;
import com.qding.hk.rpc.service.IMatterRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 管家 version 1.4.0
 * @author jiawenzheng
 *
 */
public class CallHouseKeeper extends com.qding.api.call.app.qding.v1_3_0.CallHouseKeeper{


    @Autowired
    private IntegralMessage integralMessage;

    @Autowired
    private IMatterRpcService matterService;


    @Autowired
    private RoomReadRemote roomReadService;

    @Autowired
    private IFeeRpcService feeService;

    @Autowired
    private RoomOwnerRemote roomOwnerRemoteService;

    @Autowired
    private ProjectReadRemote projectService;

    @Autowired
    private IBlueToothLogRpcService blueToothLogRpcService;

    @Autowired
    private IFuwuchuangService iFuwuchuangService;

    @Autowired
    private BlueToothMessage blueToothMessage;

    private org.apache.log4j.Logger logger =  org.apache.log4j.Logger.getLogger(CallHouseKeeper.class);

    /**
     * 报事评价
     * @param request
     * @return
     */
    @HTTP(alias="scoreReport")
    @Deprecated
    public Response<ReportEvaluateResponseData> scoreReport (ReportEvaluateRequest request) {


        Response<ReportEvaluateResponseData> response = new Response<ReportEvaluateResponseData>();

        response.setCode(HttpStatus.OK.getStatusCode());

        ReportEvaluateResponseData data = new ReportEvaluateResponseData();

        try{
            MatterApplyResponse matterApplyResponse = matterService.searchMatterApplyByApplyId(request.getReportId());
            checkAndContinue(matterApplyResponse);
            if(2!=matterApplyResponse.getMatterApply().getStatus()){
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                data.setMessage("当前报事还未处理完成");
                response.setData(data);
                return response;
            }

            ApplyScoreRequest scoreRequest = transfor(ApplyScoreRequest.class, request);
            scoreRequest.setScore(Constant.hkReportScoreMap.get(scoreRequest.getScore()));
            BaseResponse scoreResponse = matterService.matterApplyScore(scoreRequest);
            checkAndContinue(scoreResponse);
            String userId = matterApplyResponse.getMatterApply().getUserId();
            Long projectId = matterApplyResponse.getMatterApply().getProjectId();
            logger.info("Integral get matterApplyResponse result:"+ JSON.toJSONString(matterApplyResponse));
            //加入积分规则
            IntegralMessageBeanT integralMessageBeanT = new IntegralMessageBeanT(userId, Constant.INTEGRAL_REPAIR_EVALUATE, userId, projectId, System.currentTimeMillis(), Constant.INCOME_OPT_TYPE, null,request.getReportId());
            integralMessage.assembleIntegralMessage(integralMessageBeanT);
            data.setToast(Constant.integralToastMap.get(Constant.INTEGRAL_REPAIR_EVALUATE));

        }catch (Exception e){
            return handleException(ReportEvaluateResponseData.class, e);
        }

        response.setData(data);

        return response;

    }

    /**
     * 报事评价详情
     * @param request
     * @return
     */
    @HTTP(alias="getReportEvaluate")
    @Deprecated
    public Response<GetReportEvaluateResponseData>  getReportEvaluate (GetReportEvaluateRequest request) {

        Response<GetReportEvaluateResponseData> response = new Response<GetReportEvaluateResponseData>();

        response.setCode(HttpStatus.OK.getStatusCode());

        GetReportEvaluateResponseData data = new GetReportEvaluateResponseData();

        ReportEvaluate entity = new ReportEvaluate();

        data.setEntity(entity);

        response.setData(data);

        return  response;

    }


    /**
     * 物业缴费信息首页
     * @return
     */
    @HTTP(alias="getPropertyBillIndex")
    @Deprecated
    public Response<GetPropertyBillsIndexResponseData> getPropertyBillIndex(GetPropertyBillsIndexRequest request) {

        try {
            Response<GetPropertyBillsIndexResponseData> response = new Response<GetPropertyBillsIndexResponseData>();

            Room room = roomReadService.get(Long.parseLong(request.getRoomId()));

            if(room == null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
            }

            boolean isLongHu =  isLongHuForFee(room.getProjectId());
            logger.info("callHousekeeper isLongHu return : projectId"+room.getProjectId()+"  , isLongHu:"+isLongHu);
            SumFeeResponse sumFeeResponse = null ;

            if (isLongHu) {
                 sumFeeResponse = feeService.getSumFee(room.getCode());
            } else {
                 sumFeeResponse = feeService.getNotLongForSumFee(room.getId());
            }

            checkAndContinue(sumFeeResponse);

            //未交
            Float debts = sumFeeResponse.getSumFee().getDebts();
            //预付
            Float prePay = sumFeeResponse.getSumFee().getPrePay();

            FeeRequest feeRequest = transfor(FeeRequest.class, request);
            feeRequest.setCode(room.getCode());

            FeeResponse feeByMonth;

            Integer pageNo = request.getPageNo();
            Integer pageSize = request.getPageSize();

            feeRequest.setPageNo(pageNo);
            feeRequest.setPageSize(pageSize);
            feeRequest.setLongHu(isLongHu);
            feeByMonth = feeService.getFeeByMonth(feeRequest);
            checkAndContinue(feeByMonth);
            FeeResponse feeByMonthPage =  getFeeCachePage ( feeByMonth,  request.getFeeStatus());

            if (request.getFeeStatus() != 0 && request.getFeeStatus() != 1 ) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "错误的feeStatus");
            }

            GetPropertyBillsIndexResponseData data = transfor(GetPropertyBillsIndexResponseData.class, feeByMonthPage);
            data.setRecordCount(feeByMonthPage.getFeeModelList().size());
            data.setTotalCount(feeByMonth.getFeeModelList().size());
            data.setSumDebt(String.valueOf(debts));
            data.setSumPrePay(String.valueOf(prePay));

            List<RoomOwner> roomOwners = roomOwnerRemoteService.getOnwerInfoByRoomId(Long.parseLong(request.getRoomId()));

            List<OwnerInfo> ownerInfos = Lists.newArrayList();

            if(roomOwners != null){

                 ownerInfos = transforList(OwnerInfo.class,roomOwners);
             }

            data.setOwnerInfos(ownerInfos);

            boolean canFee = canFree(room.getId(),isLongHu);

            data.setCanPayFee( canFee ? 1 : 0 );

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GetPropertyBillsIndexResponseData.class, e);
        }
    }


    public FeeResponse getFeeCachePage (FeeResponse feeByMonth, int  feedStatus){

        List<FeeModel> unPayFees = new ArrayList<>();
        Iterator<FeeModel> iterator = feeByMonth.getFeeModelList().iterator();
        while(iterator.hasNext()) {
            FeeModel fee = iterator.next();
            if ((feedStatus==0)||(feedStatus==1 && "未缴".equals(fee.getFeeStatus()))) {
                unPayFees.add(fee);
            }
        }

        FeeResponse feeByMonthPage = new FeeResponse();
        feeByMonthPage.setFeeModelList(unPayFees);

        return feeByMonthPage;

    }


    //判断能否缴费
    public boolean canFree (Long roomId,boolean isLongHu){

        Room room = roomReadService.get(roomId);
        if (QDStringUtil.isNotNull(room)) {
            try {
                if (isLongHu){ //龙湖每月最后两天和每个月的前两天关闭不可用
                    String currentDate =  new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
                    List<String> dateList = DateUtil.getDateList();
                    boolean dateFlag = dateList.contains(currentDate);
                    if (dateFlag) return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }


    /**
     * 生成物业费订单
     * @param request
     * @return
     */
    @HTTP(alias = "propertyChargesOrder")
    @Deprecated
    public Response<PropertyChargesOrderResponseData> propertyChargesOrder (PropertyChargesOrderRequest request) {


        Response<PropertyChargesOrderResponseData> response = new Response<PropertyChargesOrderResponseData>();

        response.setCode(HttpStatus.OK.getStatusCode());

        PropertyChargesOrderResponseData data = new PropertyChargesOrderResponseData();

        Room room = roomReadService.get(Long.parseLong(request.getRoomId()));

        boolean isLongHu = false;

        if (QDStringUtil.isNotNull(room)) {

            try {
                isLongHu = isLongHuForFee(room.getProjectId());
                if (isLongHu){ //龙湖每月最后两天和每个月的前两天关闭不可用
                    String currentDate =  new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
                    List<String> dateList = DateUtil.getDateList();
                    boolean dateFlag = dateList.contains(currentDate);
                    if (dateFlag){
                        response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                        data.setMessage("物业账单清算日，暂不提供物业缴费服务");
                        response.setData(data);

                        return  response;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        FeeCreateOrderRequest feeCreateOrderRequest = new FeeCreateOrderRequest();

        Project project = projectService.get(room.getProjectId());

        feeCreateOrderRequest.setLongHu(isLongHu);
        feeCreateOrderRequest.setMemberId(request.getMemberId());
        feeCreateOrderRequest.setProjectId(String.valueOf(room.getProjectId()));
        feeCreateOrderRequest.setProjectName(room.getProjectName());
        feeCreateOrderRequest.setRegionId(String.valueOf(project.getRegionId()));
        feeCreateOrderRequest.setRegionName(project.getRegionName());
        feeCreateOrderRequest.setRoomCode(room.getCode());
        feeCreateOrderRequest.setRoomId(String.valueOf(room.getId()));
        feeCreateOrderRequest.setRoomName(project.getRegionName()+room.getProjectName()+room.getBuildingName()+room.getName());

        try {
            Integer orderSourceType = Constant.transforPlatformByAppDevice(Constant.feeOrderReleaseSourceMap, request.getAppDevice());

            if( QDStringUtil.isNull(orderSourceType) ) {
                orderSourceType =0;
            }
            feeCreateOrderRequest.setSourceType(orderSourceType);
            logger.info("propertyChargesOrder============> orderSourceType:"+orderSourceType+",feeCreateOrderRequest:"+JSON.toJSONString(feeCreateOrderRequest)+" ,AppDevice"+ JSON.toJSONString( request.getAppDevice()));

        } catch (ServiceException e) {
            e.printStackTrace();
            feeCreateOrderRequest.setSourceType(0);
        }

        try {
            FeeCreateOrderResponse feeCreateOrderResponse =  feeService.createFeeOrder(feeCreateOrderRequest);
            checkAndContinue(feeCreateOrderResponse);
            PropertyChargesOrder entity = transfor(PropertyChargesOrder.class,feeCreateOrderResponse.getPropertyFeeOrder());
            entity.setTotalPrice(feeCreateOrderResponse.getPropertyFeeOrder().getTotalRealpay());
            data.setEntity(entity);
            response.setData(data);

        } catch (ServiceException e) {

            return handleException(PropertyChargesOrderResponseData.class, e);
        }
        return  response;
    }


    /**
     * 添加蓝牙门岗日志
     * @param request
     * @return
     */
    @HTTP(alias = "addBlueToothLog")
     public Response<AddBlueToothLogResponseData>  addBlueToothLog (AddBlueToothLogRequest request) {

        Response<AddBlueToothLogResponseData>  response = new Response<AddBlueToothLogResponseData>();

        try {
            response.setCode(HttpStatus.OK.getStatusCode());

            com.qding.hk.rpc.request.bluetooth.AddBlueToothLogRequest addBlueToothLogRequest = new com.qding.hk.rpc.request.bluetooth.AddBlueToothLogRequest();

            com.qding.hk.domain.BlueToothLog blueToothLog  = new com.qding.hk.domain.BlueToothLog();
            blueToothLog.setAccountId(request.getAccountId());
            blueToothLog.setGateLocation(request.getGateLocation());
            blueToothLog.setMemberId(request.getMemberId());
            blueToothLog.setMobile(request.getMobile());
            blueToothLog.setName(request.getName());
            blueToothLog.setPassMode(request.getPassMode());
            blueToothLog.setProjectId(request.getProjectId());
            blueToothLog.setProjectName(request.getProjectName());
            blueToothLog.setMessage(request.getMessage());
            blueToothLog.setCode(request.getCode());
            blueToothLog.setDeviceName(request.getAppDevice().getQdDevice());
            blueToothLog.setPlatform(request.getAppDevice().getQdPlatform());
            blueToothLog.setVersion(request.getAppDevice().getQdVersion());
            blueToothLog.setStep(request.getStep());//接口调用步骤
            blueToothLog.setRelationId(request.getRelationId());//关联ID
            blueToothLog.setPlatformVersion(request.getAppDevice().getOSVersion());
            blueToothLog.setCallTtime(request.getCallTime());
            blueToothLog.setNetWork(request.getNetWork());
            addBlueToothLogRequest.setBlueToothLog(blueToothLog);

            blueToothMessage.addBlueToothLog(addBlueToothLogRequest);

            response.setCode(HttpStatus.OK.getStatusCode());

            response.setData( new AddBlueToothLogResponseData());

        }catch (Exception e) {

            return handleException(AddBlueToothLogResponseData.class, e);
        }

        return  response;
     }



    @ExplainAnnotation (explain = "批量添加蓝牙门岗日志")
    @HTTP(alias = "addBatchBlueToothLog")
    public Response<AddBlueToothLogResponseData>  addBatchBlueToothLog (AddBatchBlueToothLogRequest request) {

        Response<AddBlueToothLogResponseData>  response = new Response<AddBlueToothLogResponseData>();

        try {
            response.setCode(HttpStatus.OK.getStatusCode());

            List<com.qding.hk.domain.BlueToothLog> blueToothLogList = transforList(com.qding.hk.domain.BlueToothLog.class,request.getList());

            for (com.qding.hk.domain.BlueToothLog blueToothLog :blueToothLogList){
                blueToothLog.setVersion(request.getAppDevice().getQdVersion());
                blueToothLog.setPlatform(request.getAppDevice().getQdPlatform());
                blueToothLog.setDeviceName(request.getAppDevice().getQdDevice());
                blueToothLog.setCreateTime(blueToothLog.getCreateTime());
                blueToothLog.setPlatformVersion(request.getAppDevice().getOSVersion());
            }

            com.qding.hk.rpc.request.bluetooth.AddBlueToothLogRequest addBlueToothLogRequest = new com.qding.hk.rpc.request.bluetooth.AddBlueToothLogRequest();

            addBlueToothLogRequest.setBlueToothLogList(blueToothLogList);

            blueToothMessage.addBlueToothLog(addBlueToothLogRequest);

            response.setData( new AddBlueToothLogResponseData());

        }catch (Exception e) {

            return handleException(AddBlueToothLogResponseData.class, e);
        }

        return  response;
    }


    /**
     * 查询门岗蓝牙日志
     * @param reqeust
     * @return
     */
    @HTTP(alias = "findBlueToothLog")
    public Response<FindBlueToothLogResponseData> findBlueToothLog(FindBlueToothLogRequest reqeust){

        Response<FindBlueToothLogResponseData> response = new  Response<FindBlueToothLogResponseData>();

       try {
           response.setCode(HttpStatus.OK.getStatusCode());

           FindBlueToothLogResponse findBlueToothLogResponse = blueToothLogRpcService.findBlueToothLogBySZ(reqeust.getAccountId(),reqeust.getPageSize(),reqeust.getPageNo());

           checkAndContinue( findBlueToothLogResponse);

           List<BlueToothLog> list = transforList(BlueToothLog.class,findBlueToothLogResponse.getLogList());

           FindBlueToothLogResponseData data = new FindBlueToothLogResponseData();

           data.setList(list);

           data.setHaveNextPage(list.size()<reqeust.getPageSize()?false:true);

           response.setData(data);

       } catch ( Exception ex) {

           return handleException(FindBlueToothLogResponseData.class, ex);
       }

        return  response;
    }

    /**
     * 支付宝服务窗口（水电煤气缴费支付宝服务窗口）
     * @param request
     * @return
     */
    @HTTP(alias = "alipayServeWindow")
    public Response<AlipayServeWindowResponseData>   alipayServeWindow (AlipayServeWindowRequest request){

        Response<AlipayServeWindowResponseData>  response = new Response<AlipayServeWindowResponseData>();

        response.setCode(HttpStatus.OK.getStatusCode());

        AlipayServeWindowResponseData data = new AlipayServeWindowResponseData();

        String url  = iFuwuchuangService.ppBillWegPayment(request.getSubBizType());

        if (QDStringUtil.isNull(url) || QDStringUtil.isEmpty(url)) {

            response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            data.setMessage("获取服务窗口失败");

        } else {
            data.setUrl(url);
        }
        response.setData(data);

        return response;

    }

}
