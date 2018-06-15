package com.qding.api.call.app.qding.v2_5_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_5_0.struct.user.request.GetUserForReceiveMessageReuqest;
import com.qding.api.call.app.qding.v2_5_0.struct.user.response.data.GetUserForReceiveMessageResponseData;
import com.qding.api.call.app.qding.v2_5_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v2_5_0.struct.user.request.AddUserForReceiveMessageReuqest;
import com.qding.api.call.app.qding.v2_5_0.struct.user.request.UpdateUserForReceiveMessageReuqest;
import com.qding.api.call.app.qding.v2_5_0.struct.user.response.data.AddUserForReceiveMessageResponseData;
import com.qding.api.call.app.qding.v2_5_0.struct.user.response.data.UpdateUserForReceiveMessageResponseData;
import com.qding.api.call.app.qding.v2_5_0.struct.user.request.GetUserForReceiveMessageByIdReuqest;
import com.qding.api.call.app.qding.v2_5_0.struct.user.response.data.GetUserForReceiveMessageByIdResponseData;
import com.qding.api.struct.Response;
import com.qding.api.util.QDMemberRemote;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.Region;
import com.qding.brick.pojo.biz.RegionGroup;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RegionRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberAddress;
import com.qding.member.rpc.IMemberAddressRPC;
import com.qding.member.rpc.request.member.MemberAddressCondition;
import com.qding.member.rpc.response.memberaddress.MemberAddressListResponse;
import com.qding.member.rpc.response.memberaddress.MemberAddressResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by qd on 2016/8/23.
 */
public class CallUser extends com.qding.api.call.app.qding.v1_3_2.CallUser {


    @Autowired
    private IMemberAddressRPC memberAddressService;

    @Autowired
    private RoomReadRemote roomReadRemoteService;

    @Autowired
    private RegionRemote regionRemoteService;

    @Autowired
    private ProjectReadRemote projectReadService;


    @ExplainAnnotation(explain = "新增收件人地址信息")
    @HTTP(alias = "addUserForReceiveMessage", isRequireAuth = false)
    @Deprecated
    public Response<AddUserForReceiveMessageResponseData> addUserForReceiveMessage(AddUserForReceiveMessageReuqest request) {

        try {

            MemberAddress memberAddress = transfor(MemberAddress.class, request);
            memberAddress.setVersion(2);
            //如果有房间ID获取房间信息拼装地址
            if (QDStringUtil.isNotEmpty(request.getRoomId())) {
                Room room = roomReadRemoteService.get(Long.parseLong(request.getRoomId()));
                memberAddress.setProjectName(room.getProjectName());
                StringBuffer adr = new StringBuffer();
                adr.append(QDStringUtil.isNotEmpty(room.getGroupName()) ? room.getGroupName() : "");
                adr.append(QDStringUtil.isNotEmpty(room.getBuildingName()) ? room.getBuildingName() : "");
                adr.append(QDStringUtil.isNotEmpty(room.getName()) ? room.getName() : "");

                memberAddress.setAddress(adr.toString());

            }
            MemberAddressResponse memberAddressResponse = memberAddressService.save(memberAddress);
            checkAndContinue(memberAddressResponse);
            MemberAddress mas = memberAddressResponse.getMemberAddress();
            Addresses addresses = transfor(Addresses.class, mas);
            setAddress(addresses);
            if (1 == request.getDefaultFlag()) {
                MemberAddressResponse  setDefaultResponse = memberAddressService.defaultMa(mas.getMemberId(), mas.getId());
                checkAndContinue(setDefaultResponse);
            }
            //版本号 1 为老版本（或则null），2为新版本
            if (2 == mas.getVersion()) {
                addresses.setIsSetting(1);
            }

            Response<AddUserForReceiveMessageResponseData> response = new Response<AddUserForReceiveMessageResponseData>();
            AddUserForReceiveMessageResponseData data = new AddUserForReceiveMessageResponseData();
            data.setEntity(addresses);
            response.setData(data);

            return response;

        } catch (ServiceException e) {
            return handleException(AddUserForReceiveMessageResponseData.class, e);
        }
    }

    @ExplainAnnotation(explain = "修改收件人地址信息")
    @HTTP(alias = "updateUserForReceiveMessage", isRequireAuth = true)
    @Deprecated
    public Response<UpdateUserForReceiveMessageResponseData> updateUserForReceiveMessage(UpdateUserForReceiveMessageReuqest request) {

        try {

            Response<UpdateUserForReceiveMessageResponseData> response = new Response<UpdateUserForReceiveMessageResponseData>();
            MemberAddress memberAddress = transfor(MemberAddress.class, request);
            memberAddress.setVersion(2);
            MemberAddressResponse  updateResponse = memberAddressService.update(memberAddress);
            checkAndContinue(updateResponse);
            if (1 == request.getDefaultFlag()) {
                MemberAddressResponse  setDefaultResponse = memberAddressService.defaultMa(request.getMemberId(), request.getId());
                checkAndContinue(setDefaultResponse);
            }

            MemberAddress mas = updateResponse.getMemberAddress();
            Addresses addresses = transfor(Addresses.class, mas);
            ////版本号 1 为老版本（或则null），2为新版本
            setAddress(addresses);
            //版本号 1 为老版本（或则null），2为新版本
            if (2 == mas.getVersion()) {
                addresses.setIsSetting(1);
            }
            UpdateUserForReceiveMessageResponseData data = new UpdateUserForReceiveMessageResponseData();
            data.setEntity(addresses);
            response.setData(data);

            return response;

        } catch (ServiceException e) {

            return handleException(UpdateUserForReceiveMessageResponseData.class, e);

        }

    }


    @ExplainAnnotation(explain = "根据id获取收件人地址信息")
    @HTTP(alias = "getUserForReceiveMessageById", isRequireAuth = false)
    public Response<GetUserForReceiveMessageByIdResponseData> getUserForReceiveMessageById(GetUserForReceiveMessageByIdReuqest request) {
        try {
            Response<GetUserForReceiveMessageByIdResponseData> response = new Response<GetUserForReceiveMessageByIdResponseData>();
            MemberAddressResponse  memberAddressResponse = memberAddressService.getMemberAddressById(request.getId());
            checkAndContinue(memberAddressResponse);
            GetUserForReceiveMessageByIdResponseData data = new GetUserForReceiveMessageByIdResponseData();
            Addresses addresses = transfor(Addresses.class, memberAddressResponse.getMemberAddress());
            setAddress(addresses);
            data.setEntity(addresses);
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(GetUserForReceiveMessageByIdResponseData.class, e);
        }
    }


    @ExplainAnnotation (explain = "获取收件人列表")
    @HTTP(alias = "selUserForReceiveMessage", isRequireAuth = true)
    @Deprecated
    public Response<GetUserForReceiveMessageResponseData> getUserForReceiveMessage(GetUserForReceiveMessageReuqest request) {

        try {

            Response<GetUserForReceiveMessageResponseData> response = new Response<GetUserForReceiveMessageResponseData>();
            MemberAddressCondition memberAddressCondition = new MemberAddressCondition();
            memberAddressCondition.setMemberId(request.getMemberId());
            memberAddressCondition.setBusinessType(request.getAddressBusinessType());
            memberAddressCondition.setProjectId(request.getProjectId());
            if (request.getAppDevice().getQdVersion().compareTo("2.5.0") < 0) {
                memberAddressCondition.setVersion(1);//1:老版本<2.5.0时传1,否则不用处理
            }
            MemberAddressListResponse memberAddressListResponse = memberAddressService.findMemberAddressByCondition(memberAddressCondition);
            checkAndContinue(memberAddressListResponse);
            List<Addresses> addresses = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(memberAddressListResponse.getMemberAddressList())) {
                addresses = transforList(Addresses.class, memberAddressListResponse.getMemberAddressList());
            }
            for (Addresses addr : addresses) {
                setAddress(addr);
            }
            GetUserForReceiveMessageResponseData data = new GetUserForReceiveMessageResponseData(addresses);
            response.setData(data);

            return response;

        } catch (Exception e) {
            return handleException(GetUserForReceiveMessageResponseData.class, e);
        }
    }
    
    public void setAddress(Addresses addresses){
        if (addresses.getVersion() != null && 2 == addresses.getVersion()) {
            StringBuffer str = new StringBuffer();
            if (StringUtils.isNotEmpty(addresses.getProvinceName())) {
                str.append(addresses.getProvinceName() + "-");
            }
            if (StringUtils.isNotEmpty(addresses.getCityName())) {
                str.append(addresses.getCityName() + "-");
            }
            if (StringUtils.isNotEmpty(addresses.getAreaName())) {
                str.append(addresses.getAreaName() + "-");
            }
            if (StringUtils.isNotEmpty(addresses.getGroupAddress())) {
            	//2.8新增组团街道
                str.append(addresses.getGroupAddress() + "-");
            }else{
            	 if (StringUtils.isNotEmpty(addresses.getStreet())) {
                     str.append(addresses.getStreet() + "-");
                 }
            }
            if (StringUtils.isNotEmpty(addresses.getProjectName())) {
                str.append(addresses.getProjectName() + "-");
            }
            if (StringUtils.isNotEmpty(addresses.getAddress())) {
                str.append(addresses.getAddress());
            } else {
                if (StringUtils.isNotEmpty(addresses.getRoomName())) {
                    str.append(addresses.getRoomName());
                }
            }
            addresses.setAddressStr(str.toString());
            addresses.setIsSetting(1);
        } else {
            addresses.setAddressStr(addresses.getAddress());
            if (addresses.getDefaultFlag().intValue() == 1) {
                addresses.setDefaultFlag(0);
            }
        }
    }

}
