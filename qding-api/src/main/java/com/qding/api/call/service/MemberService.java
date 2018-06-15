package com.qding.api.call.service;

import com.google.common.collect.Lists;
import com.qding.api.call.Callable;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberAddress;
import com.qding.member.model.MemberRoom;
import com.qding.member.rpc.IMemberAddressRPC;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.request.member.MemberAddressCondition;
import com.qding.member.rpc.response.memberaddress.MemberAddressListResponse;
import com.qding.member.rpc.response.memberaddress.MemberAddressResponse;
import com.qding.member.rpc.response.memberroom.GetMemberRoomResponse;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;



/**
 * Created by qd on 2017/8/16.
 */
public class MemberService extends Callable {

    private static final Logger logger = Logger.getLogger(MemberService.class);

    @Autowired
    private IProfileService profileAPI;

    @Autowired
    private RoomReadRemote roomReadRemoteClient;

    @Autowired
    private IMemberRoomRPC memberRoomAPI;

    @Autowired
    private IMemberAddressRPC memberAddressService;


    /**
     * RPC获取指定ID的会员信息
     * @param memberId
     * @return
     */
    public MemberInfo getMemberByMid (String memberId){
        MemberInfo memberInfo = null;
        try {
            GetMemberRequest getMemberRequest = new GetMemberRequest();
            getMemberRequest.setMemberId(memberId);
            GetMemberResponse getMemberResponse = profileAPI.getMemberById(getMemberRequest);
            checkAndContinue(getMemberResponse);
            memberInfo = getMemberResponse.getMemberInfo();
        } catch (ServiceException e) {
            logger.error("class:MemberService,method:getMemberByMid is error:",e);
        }
        return memberInfo;
    }


    /**
     * 获取指定社区下指定人指定的多个身份有效房屋列表
     * @param memberId
     * @param projectId
     * @return
     */
    public   List<MemberRoom> getRoomsByMultiRole(String memberId, String projectId,List<Integer> roleList,Boolean bySort) {

        List<MemberRoom> vaildRoomList = Lists.newArrayList();
        GetMemberRoomResponse memberRoomResponse = memberRoomAPI.listVaild(memberId, projectId);
        List<MemberRoom> memberRoomList =memberRoomResponse.getList();
        if (memberRoomList != null) {
            for (MemberRoom memberRoom : memberRoomList) {
                //过滤虚拟房间 都为false(虚拟房间)视为没有绑定房间
                boolean isReturnScore = roomReadRemoteClient.returnScore(Long.valueOf(memberRoom.getRoomId()));
                if (isReturnScore == true) {
                    Integer role = memberRoom.getRole().intValue();
                    if (roleList.contains(role)) {
                        vaildRoomList.add(memberRoom);
                    }
                }
            }
            //排序处理
            if (bySort && CollectionUtils.isNotEmpty(vaildRoomList) && vaildRoomList.size()>1) {
                Collections.sort(vaildRoomList, new Comparator<MemberRoom>() {
                    @Override
                    public int compare(MemberRoom o1, MemberRoom o2) {
                        Long diffTime = o1.getCreateAt()-o2.getCreateAt();
                        if (diffTime>=0) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                });
            }
        }
        return vaildRoomList;
    }


    /**
     * 获取用户收货地址列表 （可获取指定社区下）
     * @param memberId
     * @param projectId
     * @return
     * @throws Exception
     */
    public  List<MemberAddress> getMemberAddressList(String memberId, String projectId) throws  Exception{

        MemberAddressCondition memberAddressCondition = new MemberAddressCondition();
        memberAddressCondition.setMemberId(memberId);
        memberAddressCondition.setBusinessType(0);
        if (QDStringUtil.isNotEmpty(projectId)) {
            memberAddressCondition.setProjectId(projectId);
        }
        MemberAddressListResponse memberAddressListResponse = memberAddressService.findMemberAddressByCondition(memberAddressCondition);
        checkAndContinue(memberAddressListResponse);
        return memberAddressListResponse.getMemberAddressList();
    }



    /**
     * 获取用户默认收货地址
     *
     * @param memberId
     * @return
     * @throws Exception
     */
    public MemberAddress getMemberDefaultAddress(String memberId) throws Exception {

        //获取用户的默认快递地址
        MemberAddressResponse memberAddressResponse = memberAddressService.getBtypeDefault(memberId, 0);
        checkAndContinue(memberAddressResponse);
        MemberAddress memberAddress = memberAddressResponse.getMemberAddress();
        //新老版本都没有设置过默认地址时 仍需返回一个地址
        if (memberAddress == null) {
            MemberAddressCondition memberAddressCondition = new MemberAddressCondition();
            memberAddressCondition.setMemberId(memberId);
            memberAddressCondition.setBusinessType(0);
            List<MemberAddress> ng_mas = getMemberAddressList( memberId, null);
            if (CollectionUtils.isNotEmpty(ng_mas)) {
                memberAddress = ng_mas.get(0);
            }
        }
        return memberAddress;
    }



    /**
     * 获取用户指定ID收货地址
     * @param addrId
     * @return
     * @throws Exception
     */
    public MemberAddress getMembverAddressByAddrId(String addrId) throws Exception {

        MemberAddressResponse memberAddressResponse = memberAddressService.getMemberAddressById(addrId);
        checkAndContinue(memberAddressResponse);
        MemberAddress memberAddress = memberAddressResponse.getMemberAddress();
        return memberAddress;
    }



}
