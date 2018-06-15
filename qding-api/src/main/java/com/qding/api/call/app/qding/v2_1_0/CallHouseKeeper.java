package com.qding.api.call.app.qding.v2_1_0;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.PropertyBills;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.OwnerInfo;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.PropertyChargesOrder;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.request.PropertyChargesOrderRequest;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.response.data.PropertyChargesOrderResponseData;
import com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.request.GetPropertyBillsIndexRequest;
import com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.response.data.GetPropertyBillsIndexResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.QDMemberRemote;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.pojo.biz.RoomOwner;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomOwnerRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.response.memberroom.GetSelfRoomResponse;
import com.qding.profee.rpc.model.fee.FeeModel;
import com.qding.profee.rpc.request.fee.FeeCreateOrderRequest;
import com.qding.profee.rpc.request.fee.FeeRequest;
import com.qding.profee.rpc.response.fee.FeeCreateOrderResponse;
import com.qding.profee.rpc.response.fee.FeeResponse;
import com.qding.profee.rpc.response.fee.SumFeeResponse;
import com.qding.profee.rpc.service.IFeeRpcService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

/**
 * 管家
 */
public class CallHouseKeeper extends com.qding.api.call.app.qding.v2_0_0.CallHouseKeeper {

    @Autowired
    private RoomReadRemote roomReadService;

    @Autowired
    private IFeeRpcService feeService;

    @Autowired
    private RoomOwnerRemote roomOwnerRemoteService;

    @Autowired
    private IMemberRoomRPC memberRoomAPI;

    @Autowired
    private ProjectReadRemote projectService;

    private org.apache.log4j.Logger logger =  org.apache.log4j.Logger.getLogger(CallHouseKeeper.class);

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

            boolean isBind = false;
            if(QDStringUtil.isNotNull(request.getMemberId())){
                GetSelfRoomResponse selfRoomResponse = memberRoomAPI.getVaildSelfRoom(request.getMemberId(), request.getRoomId());
                checkAndContinue(selfRoomResponse);
                com.qding.member.model.MemberRoom rm = selfRoomResponse.getMemberRoom();
                //如果和当前房屋是绑定关系，则显示业主等信息
                if (QDStringUtil.isNotNull(rm)) {
                    isBind = true;
                }
            }

            boolean isLongHu =  isLongHuForFee(room.getProjectId());
            logger.info("callHousekeeper isLongHu return : projectId"+room.getProjectId()+"  , isLongHu:"+isLongHu);
            SumFeeResponse sumFeeResponse = null ;
            if (isLongHu) {
                // CODE mid projectId
                sumFeeResponse = feeService.getSumFeeV2(String.valueOf(room.getId()),request.getMemberId(),room.getProjectId());
            } else {
                // roomId mid projectId
                sumFeeResponse = feeService.getNotLongForSumFeeV2(room.getId(),request.getMemberId(),room.getProjectId());

            }
            checkAndContinue(sumFeeResponse);

            //未交
            Float debts = sumFeeResponse.getSumFee().getDebts();
            GetPropertyBillsIndexResponseData data = new GetPropertyBillsIndexResponseData();

            //西扬返回是否能缴费 1：能缴费 0：不能缴费，我这里是0：能缴费，1：不能缴费
            data.setCanPayFee( sumFeeResponse.getCanPayFee() == 1 ? 0 : 1 );
            data.setDiscountAmount(sumFeeResponse.getDiscountAmount());
            data.setRemindMsg(sumFeeResponse.getRemindMsg());
            data.setShouldPay( sumFeeResponse.getShouldPay());
            data.setSumDebt(String.valueOf(debts));
            data.setBind(isBind);

            if (isBind){
                FeeRequest feeRequest = transfor(FeeRequest.class, request);
                feeRequest.setCode(room.getCode());

                Integer pageNo = request.getPageNo();
                Integer pageSize = request.getPageSize();

                feeRequest.setPageNo(pageNo);
                feeRequest.setPageSize(pageSize);
                feeRequest.setLongHu(isLongHu);

                if (request.getFeeStatus() != 0 && request.getFeeStatus() != 1 ) {
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "错误的feeStatus");
                }

                if(request.getFeeStatus() == 1){//如果是点击去缴费的查询
                    List<FeeModel> feeModelList =  sumFeeResponse.getFeeModelList();
                    if (CollectionUtils.isNotEmpty(feeModelList)){
                        data.setList(transforList(PropertyBills.class, feeModelList));
                        data.setRecordCount(feeModelList.size());
                        data.setTotalCount(feeModelList.size());
                    }

                }else{//如果是首页的查询
                    FeeResponse feeByMonth;
                    //查询分页接口，特别耗时，谨慎调用
                    feeByMonth = feeService.getFeeByMonth(feeRequest);
                    checkAndContinue(feeByMonth);
                    FeeResponse feeByMonthPage =  getFeeCachePage ( feeByMonth,  request.getFeeStatus());
                    data.setList(transforList(PropertyBills.class, feeByMonthPage.getFeeModelList()));
                    data.setRecordCount(feeByMonthPage.getFeeModelList().size());
                    data.setTotalCount(feeByMonth.getTotalCount());
                }

                List<RoomOwner> roomOwners = roomOwnerRemoteService.getOnwerInfoByRoomId(Long.parseLong(request.getRoomId()));
                List<OwnerInfo> ownerInfos = Lists.newArrayList();
                if(roomOwners != null){
                    ownerInfos = transforList(OwnerInfo.class,roomOwners);
                }
                data.setOwnerInfos(ownerInfos);
            }

            response.setData(data);
            return response;

        } catch (Exception e) {
            return handleException(GetPropertyBillsIndexResponseData.class, e);
        }
    }


    /**
     * 生成物业费订单
     * @param request
     * @return
     */
    @HTTP(alias = "propertyChargesOrder")
    public Response<PropertyChargesOrderResponseData> propertyChargesOrder (PropertyChargesOrderRequest request) {


        Response<PropertyChargesOrderResponseData> response = new Response<PropertyChargesOrderResponseData>();

        response.setCode(HttpStatus.OK.getStatusCode());

        PropertyChargesOrderResponseData data = new PropertyChargesOrderResponseData();

        Room room = roomReadService.get(Long.parseLong(request.getRoomId()));

        boolean isLongHu = isLongHuForFee(room.getProjectId());

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
            logger.info("propertyChargesOrder============> orderSourceType:"+orderSourceType+",feeCreateOrderRequest:"+ JSON.toJSONString(feeCreateOrderRequest)+" ,AppDevice"+ JSON.toJSONString( request.getAppDevice()));

        } catch (ServiceException e) {
            e.printStackTrace();
            feeCreateOrderRequest.setSourceType(0);
        }

        try {
            FeeCreateOrderResponse feeCreateOrderResponse =  feeService.createFeeOrderV2(feeCreateOrderRequest);
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


}
