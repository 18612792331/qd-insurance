package com.qding.api.call.app.qding.v1_4_1;

import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.PropertyBills;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.OwnerInfo;
import com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.request.*;
import com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.response.data.*;
import com.qding.api.constant.Constant;
import com.qding.api.sms.SmsAction;
import com.qding.api.struct.Response;
import com.qding.api.util.DateUtil;
import com.qding.api.util.QDMemberRemote;
import com.qding.api.verifycode.ImgCodeConfig;
import com.qding.api.verifycode.VerifyCode;
import com.qding.api.verifycode.VerifyCodeConfig;
import com.qding.api.verifycode.store.RedisStoreDevice;
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
import com.qding.member.rpc.IRoomBindQRcodeRPC;
import com.qding.member.rpc.response.memberroom.GetSelfRoomResponse;
import com.qding.member.rpc.response.qrcode.QRCodeResponse;
import com.qding.profee.rpc.model.fee.FeeModel;
import com.qding.profee.rpc.request.fee.FeeRequest;
import com.qding.profee.rpc.response.fee.FeeResponse;
import com.qding.profee.rpc.response.fee.SumFeeResponse;
import com.qding.profee.rpc.service.IFeeRpcService;
import com.qding.manager.common.ModelResult;
import com.qding.manager.domain.AppMenu;
import com.qding.manager.domain.Puser;
import com.qding.manager.dto.PuserProjectDto;
import com.qding.manager.service.IPuserRPCService;
import com.qding.member.model.RoomBindQRcode;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by qd on 2015/10/23.
 */
public class CallHouseKeeper extends  com.qding.api.call.app.qding.v1_4_0.CallHouseKeeper {

    @Autowired
    private RoomReadRemote roomReadService;

    @Autowired
    private IFeeRpcService feeService;

    @Autowired
    private RoomOwnerRemote roomOwnerRemoteService;

    @Autowired
    private IMemberRoomRPC memberRoomAPI;

    @Autowired
    private IPuserRPCService puserRPCService;

    @Autowired
    private RoomReadRemote roomReadRemoteService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IRoomBindQRcodeRPC roomBindQRcodeService;

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

                GetSelfRoomResponse selfRoomResponse  = memberRoomAPI.getVaildSelfRoom(request.getMemberId(), request.getRoomId());
                checkAndContinue(selfRoomResponse);
                com.qding.member.model.MemberRoom rm =  selfRoomResponse.getMemberRoom();

                //如果和当前房屋是绑定关系，则显示业主等信息
                if (QDStringUtil.isNotNull(rm)) {
                    isBind = true;
                }
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

            GetPropertyBillsIndexResponseData data = new GetPropertyBillsIndexResponseData();

            isLongHu = isLongHuForFee(room.getProjectId());
            boolean dateFlag = false;
            if (isLongHu){ //龙湖每月最后两天和每个月的前两天关闭不可用
                String currentDate =  new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
                List<String> dateList = DateUtil.getDateList();
                dateFlag = dateList.contains(currentDate);
            }
            //// TODO: 2016/3/1  临时设置非android的设备访问时添加 ”月末月初暂停缴费“ 话术，2.1版本需要重构
            data.setSumDebt(String.valueOf(debts));
            if (dateFlag) {
                if (!Constant.QD_PLATFORM_ANDROID.equals(request.getAppDevice().getQdPlatform().toLowerCase())){
                    data.setSumDebt(String.valueOf(debts)+"月末月初暂停缴费");
                }
            }
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

                    data.setList(transforList(PropertyBills.class, feeModelList));

                    data.setRecordCount(CollectionUtils.isNotEmpty(feeModelList)?feeModelList.size():0);

                    data.setTotalCount(CollectionUtils.isNotEmpty(feeModelList)?feeModelList.size():0);
                }else{//如果是首页的查询
                    FeeResponse feeByMonth;

                    //查询分页接口，特别耗时，谨慎调用
                    feeByMonth = feeService.getFeeByMonth(feeRequest);

                    checkAndContinue(feeByMonth);

                    FeeResponse feeByMonthPage =  getFeeCachePage ( feeByMonth,  request.getFeeStatus());

                    data.setList(transforList(PropertyBills.class, feeByMonthPage.getFeeModelList()));

                    data.setRecordCount(CollectionUtils.isNotEmpty(feeByMonthPage.getFeeModelList())?feeByMonthPage.getFeeModelList().size():0);

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
     * 管家登陆
     * @param request
     * @return
     */
    @HTTP(alias = "housekeeperLogin")
    public Response<HousekeeperLoginResponseData> housekeeperLogin(HousekeeperLoginRequest request){

      try {

          Response<HousekeeperLoginResponseData> response = new  Response<HousekeeperLoginResponseData>();

          response.setCode(HttpStatus.OK.getStatusCode());
/*
          VerifyCode.imageVerifyCode(
                  new ImgCodeConfig(
                          request.getVerifyCode(),
                          request.getVerifyKey(),
                          new RedisStoreDevice(),
                          true));*/

          ModelResult modelResult = puserRPCService.login(request.getAccount(),request.getPassWord());

          checkAndContinue(modelResult);

          //如果是物业系统账号直接登录
          boolean isWY = (Boolean)modelResult.getData().get("flag");

          List<AppMenu> appMenuList = modelResult.getList();

          boolean flag = false;

          if(isWY){//如果是物业系统账号可直接登录
              flag = true;
          } else { //如果不是物业系统账号，则判断appMenuList中含有绑定房屋管理的权限，则可登录
              if(appMenuList.size()>0){
                  for (AppMenu appMenu : appMenuList){
                      if("FW&AUTHEN".equals(appMenu.getFlag())) {
                          flag = true;
                          break;
                      }
                  }
              }
          }

          HousekeeperLoginResponseData data = new HousekeeperLoginResponseData();

          if(flag){

              Puser user = (Puser) modelResult.getEntity();

              PuserProjectDto puserProjectDto = puserRPCService.getProjectIds(user.getOldId());

              List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Project> projectLists = Lists.newArrayList();

              if ( QDStringUtil.isNotNull(puserProjectDto.getProjectIdSet()) ){

                  List<Long>  projectIdList = puserProjectDto.getProjectIdSet();

                  Set<Long> projectSet = new HashSet(projectIdList);

                  List<Project> projects = projectReadService.getByIds(projectSet);

                  projectLists = transforList(com.qding.api.call.app.qding.v1_3_0.struct.brick.Project.class,projects);
              }
              data.setEntity(transfor(com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.Puser.class,user));

              data.setList(projectLists);

              response.setData(data);
          } else {
              response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());

              data.setMessage("该账户不符合房屋管家权限");

              response.setData(data);
          }

          return response;


      } catch (Exception e){

          return handleException(HousekeeperLoginResponseData.class, e);
      }

    }

    /**
     * 管家忘记密码
     * @param request
     * @return
     */
    @HTTP(alias = "housekeeperForgetPassword")
    public Response<HousekeeperForgetPasswordResponseData> housekeeperForgetPassword (HousekeeperForgetPasswordRequest request) {

       try{
           Response<HousekeeperForgetPasswordResponseData> response = new  Response<HousekeeperForgetPasswordResponseData>();

           response.setCode(HttpStatus.OK.getStatusCode());

           String code = request.getVerifyCode();

           VerifyCode.verifyCode(
                   new VerifyCodeConfig(
                           SmsAction.FORGET_PWD,
                           code.toLowerCase(),
                           request.getMobile(),
                           new RedisStoreDevice()
                   )
           );

           ModelResult modelResult = puserRPCService.changePassword(request.getAccount(),request.getPassWord());

           checkAndContinue(modelResult);

           HousekeeperForgetPasswordResponseData data = new HousekeeperForgetPasswordResponseData();

           response.setData(data);

           return response;

       } catch (Exception e) {

           return handleException(HousekeeperForgetPasswordResponseData.class, e);
       }
    }

    /**
     * 获取管家账号和手机
     * @param request
     * @return
     */
    @HTTP(alias = "getHkUserInfo")
    public Response<GetHkUserInfoResponseData> getHkUserInfo (GetHkUserInfoRequest request) {

        Response<GetHkUserInfoResponseData> response = new Response<GetHkUserInfoResponseData>();

        try{
            ModelResult modelResult =  puserRPCService.getPuserInfoByAccount(request.getAccount());

            checkAndContinue(modelResult);

            Puser user = (Puser) modelResult.getEntity();

            GetHkUserInfoResponseData data = new GetHkUserInfoResponseData();

            data.setMobile( user.getMobile());

            data.setAccount(user.getAccount());

            response.setData(data);

            return  response;

        }catch (Exception e){

            return handleException(GetHkUserInfoResponseData.class, e);
        }

    }

    /**
     * 生成可绑定房屋的二维码
     * @param request
     * @return
     */
    @HTTP(alias = "housekeeperBindRoomQuickmark")
    public Response<HousekeeperBindRoomQuickmarkResponseData> housekeeperBindRoomQuickmark (HousekeeperBindRoomQuickmarkRequest request) {

        Response<HousekeeperBindRoomQuickmarkResponseData> response = new  Response<HousekeeperBindRoomQuickmarkResponseData>();

        response.setCode(HttpStatus.OK.getStatusCode());

        try {
            Room room = roomReadRemoteService.get(Long.parseLong(request.getRoomId()));

            String fullRoom = (QDStringUtil.isNotNull(room.getGroupName())?room.getGroupName():"") + (QDStringUtil.isNotNull(room.getBuildingName())?room.getBuildingName():"")+
                    (QDStringUtil.isNotNull(room.getName())? room.getName():"");

            RoomBindQRcode roomBindQRcode = transfor(RoomBindQRcode.class,request);
            roomBindQRcode.setRole(request.getHkIndentity());
            roomBindQRcode.setRoomCode(room.getCode());
            roomBindQRcode.setRoomName(fullRoom);
            roomBindQRcode.setProjectId(String.valueOf(room.getProjectId()));
            roomBindQRcode.setProjectName(room.getProjectName());

            QRCodeResponse qrCodeResponse = roomBindQRcodeService.createQRcode(roomBindQRcode);
            checkAndContinue(qrCodeResponse);
            HousekeeperBindRoomQuickmarkResponseData data = new HousekeeperBindRoomQuickmarkResponseData();
            data.setQuickMarkUrl(qrCodeResponse.getQrcode());
            response.setData(data);
        } catch (Exception  e) {
            return handleException(HousekeeperBindRoomQuickmarkResponseData.class, e);
        }
        return response;
    }

}
