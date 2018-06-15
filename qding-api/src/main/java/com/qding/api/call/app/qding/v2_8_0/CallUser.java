package com.qding.api.call.app.qding.v2_8_0;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.call.app.qding.v2_8_0.struct.user.request.WeChatSPLoginRequest;
import com.qding.api.call.app.qding.v2_8_0.struct.user.response.data.WeChatSPLoginResponseData;
import com.qding.api.ip.IPUtil;
import com.qding.api.ip.TaoBaoCity;
import com.qding.member.rpc.IMemberAddressRPC;
import com.qding.member.rpc.request.member.MemberAddressCondition;
import com.qding.member.rpc.response.memberaddress.MemberAddressListResponse;
import com.qding.member.rpc.response.memberaddress.MemberAddressResponse;
import com.qding.passport.service.IPassportService;
import com.qding.passport.struct.request.UnionLoginRequest;
import com.qding.passport.struct.response.UnionLoginResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_5_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v2_5_0.struct.user.request.AddUserForReceiveMessageReuqest;
import com.qding.api.call.app.qding.v2_5_0.struct.user.request.GetUserForReceiveMessageReuqest;
import com.qding.api.call.app.qding.v2_5_0.struct.user.request.UpdateUserForReceiveMessageReuqest;
import com.qding.api.call.app.qding.v2_5_0.struct.user.response.data.AddUserForReceiveMessageResponseData;
import com.qding.api.call.app.qding.v2_5_0.struct.user.response.data.GetUserForReceiveMessageResponseData;
import com.qding.api.call.app.qding.v2_5_0.struct.user.response.data.UpdateUserForReceiveMessageResponseData;
import com.qding.api.struct.Response;
import com.qding.api.util.QDMemberRemote;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RegionRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.brick.struts.request.GetRegionGroupRequest;
import com.qding.brick.struts.response.GetRegionGroupResponse;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberAddress;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by qd on 2016/8/23.
 */
public class CallUser extends com.qding.api.call.app.qding.v2_5_0.CallUser {


    @Autowired
    private IMemberAddressRPC memberAddressService;

    @Autowired
    public RoomReadRemote roomReadRemoteService;

    @Autowired
    public ProjectReadRemote projectReadService;

    @Autowired
    private IPassportService passportAPI;

    @ExplainAnnotation(explain = "新增收件人地址信息")
    @HTTP(alias = "addUserForReceiveMessage", isRequireAuth = true)
    public Response<AddUserForReceiveMessageResponseData> addUserForReceiveMessage(AddUserForReceiveMessageReuqest request) {
        try {
            MemberAddress memberAddress = transfor(MemberAddress.class, request);
            memberAddress.setVersion(2);
            fillMemberAddressInfo(request, memberAddress);
            MemberAddressResponse memberAddressResponse = memberAddressService.save(memberAddress);
            checkAndContinue(memberAddressResponse);
            MemberAddress mas = memberAddressResponse.getMemberAddress();
            Addresses addresses = transfor(Addresses.class, mas);
            setAddress(addresses);
            if (1 == request.getDefaultFlag()) {
                MemberAddressResponse memberAddressResponse2 = memberAddressService.defaultMa(mas.getMemberId(), mas.getId());
                checkAndContinue(memberAddressResponse2);
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
    public Response<UpdateUserForReceiveMessageResponseData> updateUserForReceiveMessage(UpdateUserForReceiveMessageReuqest request) {
        try {
            Response<UpdateUserForReceiveMessageResponseData> response = new Response<UpdateUserForReceiveMessageResponseData>();
            MemberAddress memberAddress = transfor(MemberAddress.class, request);
            memberAddress.setVersion(2);
            
            AddUserForReceiveMessageReuqest req=new AddUserForReceiveMessageReuqest();
            req.setRoomId(request.getRoomId());
            req.setGroupId(request.getGroupId());
            req.setProjectId(request.getProjectId());
            fillMemberAddressInfo(req, memberAddress);
            //2.8.0补充
            if(StringUtils.isBlank(req.getGroupId())){
            	if(StringUtils.isNotBlank(request.getProjectId())){
            		com.qding.brick.pojo.biz.Project p=projectReadService.get(Long.parseLong(request.getProjectId()));
                	if(p.getIsGroupAddress()==1){
                		UpdateUserForReceiveMessageResponseData data=new UpdateUserForReceiveMessageResponseData(); 
                		data.setMessage("社区存在组团地址，必须填写组团");
                		data.setNeedGroupId(true);
                	    response.setData(data);
                	    return response;
                	}
            	}
            	memberAddress.setGroupId("");
            	memberAddress.setGroupName("");
            	memberAddress.setGroupAddress("");
            }
            MemberAddressResponse  updateResponse = memberAddressService.update(memberAddress);
            checkAndContinue(updateResponse);
            if (1 == request.getDefaultFlag()) {
                MemberAddressResponse setDefaultResponse = memberAddressService.defaultMa(request.getMemberId(), request.getId());
                checkAndContinue(setDefaultResponse);
            }
            MemberAddress mas = updateResponse.getMemberAddress();
            Addresses addresses = transfor(Addresses.class, mas);
            setAddress(addresses);
            //版本号 1 为老版本（或则null），2为新版本
            if(2==mas.getVersion()){
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
    
    @ExplainAnnotation (explain = "获取收件人列表")
    @HTTP(alias = "selUserForReceiveMessage")
    public Response<GetUserForReceiveMessageResponseData> getUserForReceiveMessage(GetUserForReceiveMessageReuqest request) {

        try {

            Response<GetUserForReceiveMessageResponseData> response = new Response<GetUserForReceiveMessageResponseData>();
            MemberAddressCondition memberAddressCondition = new MemberAddressCondition();
            memberAddressCondition.setMemberId(request.getMemberId());
            memberAddressCondition.setBusinessType(request.getAddressBusinessType());
            memberAddressCondition.setProjectId(request.getProjectId());
            MemberAddressListResponse memberAddressListResponse = memberAddressService.findMemberAddressByCondition(memberAddressCondition);
            checkAndContinue(memberAddressListResponse);
            memberAddressListResponse.getMemberAddressList();
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


    @ExplainAnnotation(explain = "微信小程序登陆|注册")
    @HTTP(alias = "weChatSpLongin", isLogin = true)
    public Response<WeChatSPLoginResponseData> weChatSpLongin(WeChatSPLoginRequest request, HttpServletRequest httpServletRequest) {

        Response<WeChatSPLoginResponseData> response = new Response<WeChatSPLoginResponseData>();
        WeChatSPLoginResponseData data = new WeChatSPLoginResponseData();

        try {
            String version = request.getAppDevice().getQdVersion();
            UnionLoginRequest unionLoginRequest = transfor(UnionLoginRequest.class, request);
            String unionId =  request.getUnionId();
            String ip = IPUtil.getIpAddress(httpServletRequest);
            TaoBaoCity city = IPUtil.getIpCity(ip);
            unionLoginRequest.setLoginIp(ip);
            unionLoginRequest.setProvinceName(city.getProvince());
            unionLoginRequest.setCityName(city.getCity());
            unionLoginRequest.setAppDevice(QDStringUtil.isNotNull(request.getAppDevice()) ? request.getAppDevice() : null);
            unionLoginRequest.setRegIp(QDStringUtil.isNotEmpty(ip)?ip:"none");
            unionLoginRequest.setPartnerId(unionId);
            UnionLoginResponse unionLoginResponse = passportAPI.unionLogin(unionLoginRequest);
            checkAndContinue(unionLoginResponse);
            data = transfor(WeChatSPLoginResponseData.class, unionLoginResponse);
            AccountMember accountMember = data.getEntity();
            String userToken = afterLogin(accountMember,version);
            data.getEntity().setImToken(unionLoginResponse.getAccountInfo().getRongCloudToken());
            response.setData(data);
            data.setBaseToken(userToken);

            return response;

        } catch (ServiceException e) {
            return handleException(WeChatSPLoginResponseData.class, e);
        }

    }
    
    public void fillMemberAddressInfo(AddUserForReceiveMessageReuqest request,
			MemberAddress memberAddress) throws ServiceException {
		//如果有房间ID获取房间信息拼装地址
		if (QDStringUtil.isNotEmpty(request.getRoomId())) {
		    Room room = roomReadRemoteService.get(Long.parseLong(request.getRoomId()));
		    memberAddress.setProjectName(room.getProjectName());
		    StringBuffer adr = new StringBuffer();
		    adr.append(QDStringUtil.isNotEmpty(room.getGroupName()) ? room.getGroupName()+"-" : "");
		    adr.append(QDStringUtil.isNotEmpty(room.getBuildingName()) ? room.getBuildingName()+"-" : "");
		    adr.append(QDStringUtil.isNotEmpty(room.getName()) ? room.getName() : "");
            String addr = adr.toString();
            if (QDStringUtil.isNotEmpty(addr)){
                String delStr =  adr.substring(addr.length()-1,addr.length());
                if (delStr.equals("-")){
                    addr = addr.substring(0,addr.length()-1);
                }
            }

		    memberAddress.setAddress(addr.toString());
		}
		//2.8新增组团信息
		if(QDStringUtil.isNotEmpty(request.getGroupId())){
			GetRegionGroupRequest request1=new GetRegionGroupRequest();
			Set<Long> regionGroupIds=new HashSet<Long>();
			regionGroupIds.add(Long.parseLong(request.getGroupId()));
			request1.setProjectId(Long.parseLong(request.getProjectId()));
			request1.setRegionGroupIds(regionGroupIds);
			GetRegionGroupResponse regionGroup=projectReadService.getRegionGroupByRequest(request1);
			checkAndContinue(regionGroup);
			if(regionGroup.getRegionGroups()!=null && regionGroup.getRegionGroups().size()>0){
				memberAddress.setGroupName(regionGroup.getRegionGroups().get(0).getRegionName());
				memberAddress.setGroupAddress(regionGroup.getRegionGroups().get(0).getAddress());
			}
			
		}
	}
    
    //注意这里面的拼接没有用来保存到数据库，仅仅用来展示了
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
            //优先使用组团街道地址-----------------------------------------
            if (StringUtils.isNotEmpty(addresses.getGroupAddress())) {
            	//2.8新增组团街道
                str.append(addresses.getGroupAddress() + "-");
            }else{
            	 if (StringUtils.isNotEmpty(addresses.getStreet())) {
                     str.append(addresses.getStreet() + "-");
                 }
            }
            //--------------------------------------------------------
            if (StringUtils.isNotEmpty(addresses.getProjectName())) {
                str.append(addresses.getProjectName() + "-");
            }
            if (StringUtils.isNotEmpty(addresses.getGroupName())) {
            	//2.8新增组团街道
                str.append(addresses.getGroupName() + "-");
            }
            if (StringUtils.isNotEmpty(addresses.getAddress())) {
            	//其他详细信息，比如房屋位置等
                str.append(addresses.getAddress());
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
