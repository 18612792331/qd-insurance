package com.qding.api.call.app.qding.v2_5_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.request.*;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.response.data.*;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.*;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.QDMemberRemote;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.GateVisitorType;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.pojo.biz.RoomOwner;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomOwnerRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.brick.struts.request.ProjectGateTypeRequest;
import com.qding.brick.struts.response.ProjectGateTypeResponse;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.DateUtil;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hk.rpc.service.IPastReleaseRpcService;
import com.qding.member.model.MemberRoom;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.response.memberroom.GetSelfRoomResponse;
import com.qding.profee.domain.PropertyFeeOrder;
import com.qding.profee.dto.FeeOrderDto;
import com.qding.profee.rpc.model.fee.FeeModel;
import com.qding.profee.rpc.request.fee.FeeRequest;
import com.qding.profee.rpc.request.fee.GetFeeOrderListRequest;
import com.qding.profee.rpc.response.fee.FeeResponse;
import com.qding.profee.rpc.response.fee.GetFeeOrderListResponse;
import com.qding.profee.rpc.response.fee.SumFeeResponse;
import com.qding.profee.rpc.response.propertyFee.PropertyFeeOrderDetailResponse;
import com.qding.profee.rpc.service.IFeeRpcService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 管家
 */
public class CallHouseKeeper extends com.qding.api.call.app.qding.v2_4_0.CallHouseKeeper {

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

    @Autowired
    IPastReleaseRpcService pastReleaseRpcService;

    @Autowired
    private SkipModeFitting skipMode;

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallHouseKeeper.class);

    /**
     * 物业缴费信息首页
     *
     * @return
     */
    @HTTP(alias = "getPropertyBillIndex")
    @ExplainAnnotation(explain = "物业缴费信息首页")
    public Response<GetPropertyBillsIndexResponseData> getPropertyBillIndex(GetPropertyBillsIndexRequest request) {

        try {
            Response<GetPropertyBillsIndexResponseData> response = new Response<GetPropertyBillsIndexResponseData>();
            Room room = roomReadService.get(Long.parseLong(request.getRoomId()));
            if (room == null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
            }

            boolean isBind = false;
            if (QDStringUtil.isNotNull(request.getMemberId())) {
                GetSelfRoomResponse selfRoomResponse  = memberRoomAPI.getVaildSelfRoom(request.getMemberId(), request.getRoomId());
                checkAndContinue(selfRoomResponse);
                MemberRoom rm = selfRoomResponse.getMemberRoom();
                //如果和当前房屋是绑定关系，则显示业主等信息
                if (QDStringUtil.isNotNull(rm)) {
                    isBind = true;
                }
            }

            boolean isLongHu = isLongHuForFee(room.getProjectId());
            logger.info("callHousekeeper isLongHu return : projectId" + room.getProjectId() + "  , isLongHu:" + isLongHu);
            SumFeeResponse sumFeeResponse = null;
            if (isLongHu) {
                // CODE mid projectId
                /*我的账单 getSumFeeV2*/
                sumFeeResponse = feeService.getSumFeeV2(String.valueOf(room.getId()), request.getMemberId(), room.getProjectId());
            } else {
                // roomId mid projectId
                sumFeeResponse = feeService.getNotLongForSumFeeV2(room.getId(), request.getMemberId(), room.getProjectId());

            }
            checkAndContinue(sumFeeResponse);

            //未交
            Float debts = sumFeeResponse.getSumFee().getDebts();
            GetPropertyBillsIndexResponseData data = new GetPropertyBillsIndexResponseData();

            if(!request.getAppDevice().getQdDevice().toLowerCase().equals("h5")  ){
                //西扬返回是否能缴费 1：能缴费 0：不能缴费
                data.setCanPayFee( sumFeeResponse.getCanPayFee()==1?0:1);
            } else {
                //西扬返回是否能缴费 1：能缴费 0：不能缴费
                data.setCanPayFee( sumFeeResponse.getCanPayFee());
            }
            data.setDiscountAmount(sumFeeResponse.getDiscountAmount());
            data.setRemindMsg(sumFeeResponse.getRemindMsg());
            data.setShouldPay(sumFeeResponse.getShouldPay());
            data.setSumDebt(String.valueOf(debts));
            data.setBind(isBind);

            List<FeeModel> feeModelList = sumFeeResponse.getFeeModelList();
            if (CollectionUtils.isNotEmpty(feeModelList)) {
                List<PropertyBills> list = transforList(PropertyBills.class, feeModelList);
                data.setList(transForGroup2(list));
                data.setRecordCount(feeModelList.size());
                data.setTotalCount(feeModelList.size());
            }

            if (isBind == true) {
                List<RoomOwner> roomOwners = roomOwnerRemoteService.getOnwerInfoByRoomId(Long.parseLong(request.getRoomId()));
                List<OwnerInfo> ownerInfos = Lists.newArrayList();
                if (roomOwners != null) {
                    ownerInfos = transforList(OwnerInfo.class, roomOwners);
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
     * 物业缴费信息-房屋账单
     *
     * @return
     */
    @HTTP(alias = "getFeeByMonth")
    @ExplainAnnotation(explain = "房屋账单")
    public Response<GetPropertyFeeByMonthResponseData> getFeeByMonth(GetPropertyFeeByMonthRequest request) {
        try {
            Response<GetPropertyFeeByMonthResponseData> response = new Response<GetPropertyFeeByMonthResponseData>();
            GetPropertyFeeByMonthResponseData data = new GetPropertyFeeByMonthResponseData();
            Room room = roomReadService.get(Long.parseLong(request.getRoomId()));
            if (room == null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
            }
            boolean isBind = false;
            if (QDStringUtil.isNotNull(request.getMemberId())) {
                GetSelfRoomResponse selfRoomResponse = memberRoomAPI.getVaildSelfRoom(request.getMemberId(), request.getRoomId());
                checkAndContinue(selfRoomResponse);
                MemberRoom rm =selfRoomResponse.getMemberRoom();
                //如果和当前房屋是绑定关系，则显示业主等信息
                if (QDStringUtil.isNotNull(rm)) {
                    isBind = true;
                }
            }
            boolean isLongHu = isLongHuForFee(room.getProjectId());
            logger.info("callHousekeeper isLongHu return : projectId" + room.getProjectId() + "  , isLongHu:" + isLongHu);
            SumFeeResponse sumFeeResponse = null;
            if (isBind) {
                FeeRequest feeRequest = transfor(FeeRequest.class, request);
                feeRequest.setCode(room.getCode());
                Integer pageNo = request.getPageNo();
                Integer pageSize = request.getPageSize();
                feeRequest.setPageNo(pageNo);
                feeRequest.setPageSize(pageSize);
                feeRequest.setLongHu(isLongHu);
                if (request.getFeeStatus() != 0 && request.getFeeStatus() != 1) {
                    throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "错误的feeStatus");
                }

                FeeResponse feeByMonth;
                /*房屋账单 getFeeByMonth*/
                feeByMonth = feeService.getFeeByMonth(feeRequest);
                checkAndContinue(feeByMonth);
                FeeResponse feeByMonthPage = getFeeCachePage(feeByMonth, request.getFeeStatus());
                List<PropertyBills> list = transforList(PropertyBills.class, feeByMonthPage.getFeeModelList());
                List<PropertyBillsByMonthInfo> resultList = Lists.newArrayList();
                List<PropertyBillsInfo> propertyBillsInfoList = transforGroup(list);
                for (PropertyBillsInfo info : propertyBillsInfoList) {
                    PropertyBillsByMonthInfo res = new PropertyBillsByMonthInfo();
                    res.setYear(info.getYear());
                    List<PropertyBillsByMonth> propertyBillsByMonthList = Lists.newArrayList();
                    String curMonth = null;
                    PropertyBillsByMonth propertyBillsByMonth = null;
                    for (PropertyBills bill : info.getBillsList()) {
                        //按月汇总应缴、未缴金额
                        curMonth = bill.getFeeDueDateStrMonth();
                        BigDecimal dueAmount = new BigDecimal(bill.getDueAmount()).setScale(2);
                        BigDecimal debtsAmount = new BigDecimal(bill.getDebtsAmount()).setScale(2);

                        propertyBillsByMonth = new PropertyBillsByMonth();
                        propertyBillsByMonth.setFeeDueDateStrMonth(curMonth);
                        propertyBillsByMonth.setFeeDueDate(bill.getFeeDueDate());
                        String ldueAmount=String.valueOf(dueAmount);
                        String ldebtsAmount=String.valueOf(debtsAmount);
                        if (StringUtils.isNotEmpty(bill.getLateFeeAmount()) && bill.getLateFeeAmount().compareTo("0.0") > 0) {
                            ldueAmount+= "(含违约金:" + new BigDecimal(bill.getLateFeeAmount() ).setScale(2) + ")";
                            ldebtsAmount+="(含违约金:" + new BigDecimal(bill.getLateFeeAmount() ).setScale(2) + ")";
                        }
                        propertyBillsByMonth.setDueAmount(ldueAmount);
                        propertyBillsByMonth.setDebtsAmount(ldebtsAmount);
                        if (debtsAmount.compareTo(new BigDecimal("0")) > 0) {//当月未缴费用合计>0
                            propertyBillsByMonth.setFeeStatus("未缴");
                        } else {
                            propertyBillsByMonth.setFeeStatus("已缴");
                        }
                        propertyBillsByMonthList.add(propertyBillsByMonth);
                    }
                    res.setList(propertyBillsByMonthList);
                    resultList.add(res);
                }
                data.setList(resultList);
                data.setTotalCount(feeByMonth.getTotalCount());
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(GetPropertyFeeByMonthResponseData.class, e);
        }
    }


    /**
     * APP缴费记录
     *
     * @return
     */
    @HTTP(alias = "getFeeOrderList")
    @ExplainAnnotation(explain = "APP缴费记录")
    public Response<GetFeeOrderListResponseData> getFeeOrderList(com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.request.GetFeeOrderListRequest request) {
        try {
            Response<GetFeeOrderListResponseData> response = new Response<GetFeeOrderListResponseData>();
            GetFeeOrderListResponseData data = new GetFeeOrderListResponseData();
            Room room = roomReadService.get(Long.parseLong(request.getRoomId()));
            if (room == null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
            }

            boolean isBind = false;
            if (QDStringUtil.isNotNull(request.getMemberId())) {
                GetSelfRoomResponse selfRoomResponse = memberRoomAPI.getVaildSelfRoom(request.getMemberId(), request.getRoomId());
                checkAndContinue(selfRoomResponse);
                MemberRoom rm = selfRoomResponse.getMemberRoom();
                //如果和当前房屋是绑定关系，则显示业主等信息
                if (QDStringUtil.isNotNull(rm)) {
                    isBind = true;
                }
            }

            boolean isLongHu = isLongHuForFee(room.getProjectId());
            logger.info("callHousekeeper isLongHu return : projectId" + room.getProjectId() + "  , isLongHu:" + isLongHu);
            SumFeeResponse sumFeeResponse = null;
            if (isLongHu) {
                // CODE mid projectId
                /*我的账单 getSumFeeV2*/
                sumFeeResponse = feeService.getSumFeeV2(String.valueOf(room.getId()), request.getMemberId(), room.getProjectId());
            } else {
                // roomId mid projectId
                sumFeeResponse = feeService.getNotLongForSumFeeV2(room.getId(), request.getMemberId(), room.getProjectId());

            }
            checkAndContinue(sumFeeResponse);

            data.setCanPayFee(sumFeeResponse.getCanPayFee() == 1 ? 0 : 1);
            data.setRemindMsg(sumFeeResponse.getRemindMsg());

            GetFeeOrderListRequest getFeeOrderListRequest = new GetFeeOrderListRequest();
            getFeeOrderListRequest.setMemberId(request.getMemberId());
            getFeeOrderListRequest.setRoomId(room.getId());
            getFeeOrderListRequest.setPage(request.getPageNo());
            getFeeOrderListRequest.setSize(request.getPageSize());
            GetFeeOrderListResponse getFeeOrderListResponse = feeService.getFeeOrderList(getFeeOrderListRequest);
            checkAndContinue(getFeeOrderListResponse);
            List<FeeOrderDto> orderDtoList = getFeeOrderListResponse.getResultPage().getItems();
            List<PropertyFeeOrderInfo> list = Lists.newArrayList();
            for (FeeOrderDto dto : orderDtoList) {
                PropertyFeeOrderInfo res = new PropertyFeeOrderInfo();
                PropertyFeeOrder propertyFeeOrder = dto.getFeeOrder();
                res.setId(propertyFeeOrder.getId());
                res.setOrderCode(propertyFeeOrder.getOrderCode());
                res.setCreateAt(String.valueOf(propertyFeeOrder.getCreateAt()));
                if (1 == propertyFeeOrder.getIsdel()) {
                    //200:已取消
                    res.setPayStatus("200");
                } else {
                    res.setPayStatus(String.valueOf(propertyFeeOrder.getPayStatus()));
                }
                res.setTimeSpan(dto.getTimeSpan());
                res.setTotalPrice(propertyFeeOrder.getTotalPrice());
                res.setTotalDiscount(propertyFeeOrder.getTotalDiscount());
                res.setTotalRealpay(propertyFeeOrder.getTotalRealpay());
                // 2017/3/23 物业缴费优化新增
                res.setFeeOwnersName(propertyFeeOrder.getFeeOwnersName());
                res.setIsReplacement(propertyFeeOrder.getIsReplacement());
                list.add(res);
            }
            data.setList(list);
            data.setBind(isBind);
            data.setTotalCount(list.size());
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(GetFeeOrderListResponseData.class, e);
        }
    }


    @HTTP(alias = "getRoomPropertyBills")
    @ExplainAnnotation(explain = "物业费缴费详情(账单明细)")
    public Response<GetRoomPropertyBillsResponseData> getRoomPropertyBills(GetRoomPropertyBillsRequest request) {
        try {
            Response<GetRoomPropertyBillsResponseData> response = new Response<GetRoomPropertyBillsResponseData>();
            Room room = roomReadService.get(Long.parseLong(request.getRoomId()));
            if (room == null) {
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
            }

            FeeRequest feeRequest = transfor(FeeRequest.class, request);
            feeRequest.setCode(room.getCode());
            //为避免时区问题，客户端传过来日期字符串，由服务端转成时间戳
            if (QDStringUtil.isNotEmpty(request.getDate())) {
                feeRequest.setDate(DateFormat.getDateInstance().parse(request.getDate()).getTime());
            } else {
                //兼容老版本
                feeRequest.setDate(request.getMonth());
            }
            boolean isLongHu = isLongHuForFee(room.getProjectId());
            feeRequest.setLongHu(isLongHu);
            feeRequest.setMonthStr(request.getMonthStr());
            FeeResponse roomBillsResponse = feeService.getOweFeeByMonth(feeRequest);
            checkAndContinue(roomBillsResponse);
            GetRoomPropertyBillsResponseData data = new GetRoomPropertyBillsResponseData();// transfor(GetRoomPropertyBillsResponseData.class, roomBillsResponse);
            List<PropertyBills> list = Lists.newArrayList();
            if (QDStringUtil.isNotNull(roomBillsResponse.getFeeModelList()) && roomBillsResponse.getFeeModelList().size() > 0) {
                Iterator<FeeModel> iterator = roomBillsResponse.getFeeModelList().iterator();
                BigDecimal unpaidNum = new BigDecimal("0");
                BigDecimal paidNum = new BigDecimal("0");
                int i = 0;
                String ym = null;
                while (iterator.hasNext()) {
                    FeeModel fee = iterator.next();

                    if (i == 0) {
                        if(StringUtils.isNotBlank(fee.getFeeDueDateStr())&& "全年".equals(fee.getFeeDueDate())) {
                            ym = "全年";
                        }else{
                            String y = fee.getFeeDueDateStr().substring(0, 4);
                            String m = fee.getFeeDueDateStr().substring(5, 7);
                            ym = y + "年" + m + "月";
                        }
                        i = 1;
                    }
                    PropertyBills propertyBills = transfor(PropertyBills.class, fee);
                    //0:待缴 | 1：已缴
                    if (request.getPayStatus().equals("0")) {
                        if ("未缴".equals(fee.getFeeStatus())) {
                            list.add(propertyBills);
                        }
                    } else {
                        if ("已缴".equals(fee.getFeeStatus())) {
                            list.add(propertyBills);
                        }
                    }

                    if ("未缴".equals(fee.getFeeStatus())) {
                        if (!data.isUnpaid()) {
                            data.setUnpaid(true);
                        }
                        unpaidNum =unpaidNum.add(new BigDecimal(String.valueOf( fee.getDebtsAmount())));
                    } else if ("已缴".equals(fee.getFeeStatus())) {
                        paidNum = paidNum.add(new BigDecimal(String.valueOf(fee.getDueAmount())));
                    }
                }
                data.setUnpaidPrice(unpaidNum);
                BigDecimal totalCount = unpaidNum.add(paidNum);
                data.setPaidPrice(paidNum);
                data.setTotalPrice(totalCount);
                data.setList(list);
                data.setYm(ym);
            }
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(GetRoomPropertyBillsResponseData.class, e);
        }
    }


    @HTTP(alias = "getOrderDetail")
    @ExplainAnnotation(explain = "查询物业订单详情")
    public Response<GetOrderDetailResponseData> getOrderDetail(GetOrderDetailRequest request) {
        try {
            Response<GetOrderDetailResponseData> response = new Response<GetOrderDetailResponseData>();
            GetOrderDetailResponseData data = new GetOrderDetailResponseData();
            boolean isBind = false;
            if (QDStringUtil.isNotNull(request.getMemberId())) {
                GetSelfRoomResponse selfRoomResponse = memberRoomAPI.getVaildSelfRoom(request.getMemberId(), request.getRoomId());
                checkAndContinue(selfRoomResponse);
                MemberRoom rm = selfRoomResponse.getMemberRoom();
                //如果和当前房屋是绑定关系，则显示业主等信息
                if (QDStringUtil.isNotNull(rm)) {
                    isBind = true;
                }
            }
            PropertyFeeOrderDetailResponse propertyFeeOrderDetailResponse = feeService.getOrderDetailByCode(request.getOrderCode());
            checkAndContinue(propertyFeeOrderDetailResponse);
            List<PropertyFeeOrderDetailInfo> infoList = transforList(PropertyFeeOrderDetailInfo.class, propertyFeeOrderDetailResponse.getFeeOrderDetailList());
            List<PropertyFeeOrderDetailByMonthInfo> detailList = transforFeeDetailGroup(infoList);
            data.setList(detailList);
            //合计金额
            PropertyFeeOrder propertyFeeOrder = propertyFeeOrderDetailResponse.getFeeOrder();
            data.setTotalPrice(propertyFeeOrder.getTotalPrice());
            data.setTotalDiscount(propertyFeeOrder.getTotalDiscount());
            data.setTotalRealpay(propertyFeeOrder.getTotalRealpay());
            data.setPayStatus(String.valueOf(propertyFeeOrder.getPayStatus()));
            // 2017/3/23 物业缴费优化增加
            data.setIsReplacement(propertyFeeOrder.getIsReplacement());
            data.setFeeOwnersName(propertyFeeOrder.getFeeOwnersName());
            data.setFeeOwnersType(propertyFeeOrder.getFeeOwnersType());
            if (propertyFeeOrder.getPayAt() != null) {
                data.setPayeeAt(String.valueOf(propertyFeeOrder.getPayAt()));//付款时间
            }
            data.setReceiptId(propertyFeeOrder.getReceiptId());//收据号
            data.setTimeSpan(propertyFeeOrderDetailResponse.getTimeSpan());
            response.setData(data);
            data.setBind(isBind);
            return response;
        } catch (Exception e) {
            return handleException(GetOrderDetailResponseData.class, e);
        }
    }


    @ExplainAnnotation (explain = "删除访客通行预约记录",desc = "2.8版本新增")
    @HTTP(alias = "delAccessControlHistory",isRequireAuth = true,isNeadProject = true)
    public Response<DelAccessControlHistoryResponseData> delAccessControlHistory(DelAccessControlHistoryRequest request) {
        Response<DelAccessControlHistoryResponseData> response = new  Response<DelAccessControlHistoryResponseData>();
        DelAccessControlHistoryResponseData data = new DelAccessControlHistoryResponseData();
        try{
        	BaseResponse delResult=pastReleaseRpcService.delPastRelease(request.getAppUser().getProjectId(),
            		request.getAccountId(), request.getId());
            data.setMessage(delResult.getReturnInfo().getMessage());
            response.setCode(delResult.getReturnInfo().getCode());
            response.setData(data);
            return response;
        }catch(Exception ex){
        	return handleException(DelAccessControlHistoryResponseData.class, ex);
        }

    }

    @ExplainAnnotation (explain = "获取指定社区来访目的字典配置信息",desc = "2.8版本新增")
    @HTTP(alias = "getPurposeOfVisitDicsByProjectId",isNeadProject = true)
    public Response<GetPurposeOfVisitDicsByProjectIdResponseData> getPurposeOfVisitDicsByProjectId (GetPurposeOfVisitDicsByProjectIdRequest request){

        Response<GetPurposeOfVisitDicsByProjectIdResponseData> response = new  Response<GetPurposeOfVisitDicsByProjectIdResponseData>();
        GetPurposeOfVisitDicsByProjectIdResponseData data = new GetPurposeOfVisitDicsByProjectIdResponseData();
        String projectId = request.getAppUser().getProjectId();
        ProjectGateTypeRequest request1=new ProjectGateTypeRequest();
        request1.setProjectId(Long.parseLong(projectId));
        ProjectGateTypeResponse response1=projectService.getProjectGateType(request1);
        try {
			checkAndContinue(response1);
			List<PurposeDic> list=new ArrayList<PurposeDic>();
            if(CollectionUtils.isNotEmpty(response1.getGateVisitorTypes())){
                for(GateVisitorType visitor:response1.getGateVisitorTypes()){
                    PurposeDic dic=new PurposeDic();
                    dic.setPurposeName(visitor.getName());
                    dic.setPurposeType(Integer.parseInt(visitor.getCode()));
                    dic.setValidTimes(visitor.getValidTimes());
                    list.add(dic);
                }
            } else{
                logger.info("当前社区"+request.getAppUser().getProjectId()+"是否配置了访客通行!");
            }
			data.setList(list);
			response.setData(data);
		} catch(Exception ex){
        	return handleException(GetPurposeOfVisitDicsByProjectIdResponseData.class, ex);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        return  response;

    }

/********************************************************************内部方法********************************************************************************/



    /**
     * 分组List并排序
     * @param list
     * @return
     */
    public static void propertyBilsSort(List<PropertyBills> list) {

        Collections.sort(list,new Comparator<PropertyBills>(){
            public int compare(PropertyBills arg0, PropertyBills arg1) {
                return getTimeForBils(arg0).compareTo(getTimeForBils(arg1));
            }
            private Long getTimeForBils(PropertyBills obj) {
                Long times ;
                if (StringUtils.isNotEmpty(obj.getFeeDueDateStr())) {
                    times = DateUtil.getDate(obj.getFeeDueDateStr(),"yyyy-MM-dd HH:mm:ss").getTime();
                } else {
                    long time = obj.getFeeDueDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(new Date(time));
                    times = DateUtil.getDate(str,"yyyy-MM-dd HH:mm:ss").getTime();
                }
                return times;
            }
        });
    }


    public List<PropertyBillsInfo> transforGroup(List<PropertyBills> list) {

        List<PropertyBillsInfo> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            String year;
            if (StringUtils.isNotEmpty(list.get(0).getFeeDueDateStr())) {
                year = list.get(0).getFeeDueDateStr().substring(0, 4);
            } else {
                long time = list.get(0).getFeeDueDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = sdf.format(new Date(time));
                year = str.substring(0, 4);
            }
            year += "年";
            PropertyBillsInfo info = new PropertyBillsInfo();
            List<PropertyBills> billsList = Lists.newArrayList();
            for (PropertyBills entity : list) {
              /*  if (StringUtils.isNotEmpty(entity.getLateFeeAmount()) && entity.getLateFeeAmount().compareTo("0.0") > 0) {
                    entity.setDueAmount(entity.getDueAmount() + "(含违约金:" + entity.getLateFeeAmount() + ")");
                    entity.setDebtsAmount(entity.getDebtsAmount() + "(含违约金:" + entity.getLateFeeAmount() + ")");
                }*/

                String str = null;
                if (StringUtils.isNotEmpty(entity.getFeeDueDateStr())) {
                    str = entity.getFeeDueDateStr();
                } else {
                    long time = entity.getFeeDueDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    str = sdf.format(new Date(time));
                }
                String y = str.substring(0, 4) + "年";
                if (year.equals(y)) {
                    info.setYear(year);
                    billsList.add(entity);
                } else {
                    info.setBillsList(billsList);
                    result.add(info);
                    year = y;
                    //下次初始化
                    info = new PropertyBillsInfo();
                    billsList = Lists.newArrayList();
                    info.setYear(year);
                    billsList.add(entity);
                }
            }
            info.setBillsList(billsList);
            result.add(info);
        }
        return result;
    }


    public   List<PropertyBillsInfo> transForGroup2(List<PropertyBills> list) {

        List<PropertyBillsInfo> billsList = Lists.newArrayList();
        HashMap<String,List<PropertyBills>> billsMap = new HashMap<>();
        SortedSet<Integer> yearSet = new TreeSet();
        if (CollectionUtils.isNotEmpty(list)) {
            propertyBilsSort(list);
            for (PropertyBills propertyBills : list) {

                if (StringUtils.isNotEmpty(propertyBills.getLateFeeAmount()) && propertyBills.getLateFeeAmount().compareTo("0.0") > 0) {
                    propertyBills.setDueAmount(propertyBills.getDueAmount() + "(含违约金:" + propertyBills.getLateFeeAmount() + ")");
                    propertyBills.setDebtsAmount(propertyBills.getDebtsAmount() + "(含违约金:" + propertyBills.getLateFeeAmount() + ")");
                }

                String year;
                if (StringUtils.isNotEmpty(propertyBills.getFeeDueDateStr())) {
                    year = propertyBills.getFeeDueDateStr().substring(0, 4);
                } else {
                    long time = propertyBills.getFeeDueDate();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String str = sdf.format(new Date(time));
                    year = str.substring(0, 4);
                }
                List<PropertyBills>  billList = Lists.newArrayList();
                if (billsMap.containsKey(year)){
                    billList =  billsMap.get(year);
                    billList.add(propertyBills);
                } else {
                    billList.add(propertyBills);
                }
                billsMap.put(year,billList);
                yearSet.add(Integer.parseInt(year));
            }
            Object[] yearArray = yearSet.toArray();
            for (int i=yearArray.length-1;i>=0;i--) {
                PropertyBillsInfo propertyBillsInfo = new PropertyBillsInfo();
                propertyBillsInfo.setYear(String.valueOf(yearArray[i]));
                propertyBillsInfo.setBillsList(billsMap.get(String.valueOf(yearArray[i])));
                billsList.add(propertyBillsInfo);
            }
        }
        return  billsList;
    }


    public List<PropertyFeeOrderDetailByMonthInfo> transforFeeDetailGroup(List<PropertyFeeOrderDetailInfo> list) {
        List<PropertyFeeOrderDetailByMonthInfo> result = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(list)) {
            String year = list.get(0).getFeeDate().substring(0, 4);
            year += "年";
            PropertyFeeOrderDetailByMonthInfo info = new PropertyFeeOrderDetailByMonthInfo();
            List<PropertyFeeOrderDetailInfo> billsList = Lists.newArrayList();
            for (PropertyFeeOrderDetailInfo entity : list) {

                String str = entity.getFeeDate();
                String y = str.substring(0, 4) + "年";
                String m = str.substring(5, 7) + "月";
                if (m.startsWith("0")) {
                    m = m.substring(1);
                }
                if (year.equals(y)) {
                    info.setYear(year);
                    entity.setMonth(m);
                    billsList.add(entity);
                } else {
                    info.setList(billsList);
                    result.add(info);
                    year = y;
                    //下次初始化
                    info = new PropertyFeeOrderDetailByMonthInfo();
                    billsList = Lists.newArrayList();
                    info.setYear(year);
                    entity.setMonth(m);
                    billsList.add(entity);
                }
            }
            info.setList(billsList);
            result.add(info);
        }
        return result;
    }


}
