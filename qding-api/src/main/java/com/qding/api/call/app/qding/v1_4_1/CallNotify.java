package com.qding.api.call.app.qding.v1_4_1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_4_1.struct.notify.*;
import com.qding.api.call.app.qding.v1_4_1.struct.notify.SkipBean;
import com.qding.api.call.app.qding.v1_4_1.struct.notify.request.*;
import com.qding.api.call.app.qding.v1_4_1.struct.notify.response.data.*;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.*;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hotcycle.service.IHotCycleRemoteService;
import com.qding.hotcycle.struct.request.GetNotifyByUserIdRequest;
import com.qding.hotcycle.struct.response.GetNotifyByUserIdResponse;
import com.qding.message.constant.MsgConstant;
import com.qding.message.domain.MsgMessage;
import com.qding.message.service.IMessageService;
import com.qding.message.struct.request.MsgAppMessageConfigRequest;
import com.qding.message.struct.request.MsgInfoRequest;
import com.qding.message.struct.response.GetMsgAppMessageConfigResponse;
import com.qding.message.struct.response.MsgAppMessageConfigResponse;
import com.qding.message.struct.response.MsgInfoResponse;
import com.qding.message.util.Page;
import com.qding.neighbor.rpc.ITopicRpc;
import com.qding.neighbor.rpc.request.GetNotifyByMIdRequest;
import com.qding.neighbor.rpc.response.data.GetNotifyByMIdResponse;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.request.GetMemberByAccountIdRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.qding.message.constant.MsgConstant.APP_PUSH_MESSAGE_MARKETING_PUSH;

/**
 * Created by qd on 2015/10/23.
 */
public class CallNotify extends com.qding.api.call.app.qding.v1_3_0.CallNotify {

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IHotCycleRemoteService hotCycleService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private ITopicRpc topicRpc;

    @Autowired
    private IProfileService profileAPI;

    private static final Logger logger = Logger.getLogger("CallNotify");

    /**
     * 获取消息通知提醒
     */
    @HTTP(alias = "getNotifyRemind")
    public Response<GetNotifyRemindResponseData> getNotifyRemind(GetNotifyRemindRequest request) {

        Response<GetNotifyRemindResponseData> response = new Response<GetNotifyRemindResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetNotifyRemindResponseData data = new GetNotifyRemindResponseData();
        List<NotifyRemind> list = Lists.newArrayList();
        String version = request.getAppDevice().getQdVersion();
        String initVersion = skipMode.initVersion(version);

        try {
            if (Integer.parseInt(initVersion) >= 260000) {

                GetMemberByAccountIdRequest memberByAccountIdRequest = new GetMemberByAccountIdRequest();
                memberByAccountIdRequest.setAccountId(request.getUserId());
                GetMemberResponse memberResponse = profileAPI.getMemberByAccountId(memberByAccountIdRequest);
                if (HttpStatus.OK.getStatusCode() == memberResponse.getReturnInfo().getCode()) {
                    String mid = memberResponse.getMemberInfo().getId();
                    NotifyRemind notifyRemind = getNewNeighborRemind(mid);
                    list.add(notifyRemind);
                }

            } else {
                GetNotifyByUserIdRequest notifyByUserIdRequest = transfor(
                        com.qding.hotcycle.struct.request.GetNotifyByUserIdRequest.class,
                        request
                );
                notifyByUserIdRequest.setReset(0);
                GetNotifyByUserIdResponse getNotifyByUserIdResponse = hotCycleService.getNotifyByUserId(notifyByUserIdRequest);
                checkAndContinue(getNotifyByUserIdResponse);
                NotifyRemind notifyRemind = new NotifyRemind();
                notifyRemind.setContent("邻聚消息");
                notifyRemind.setNoticeNum(getNotifyByUserIdResponse.getNoticeNum());
                notifyRemind.setQueryType(1);//1:邻居
                list.add(notifyRemind);
            }
            data.setList(list);
            response.setData(data);
        } catch (Exception ex) {
            return handleException(GetNotifyRemindResponseData.class, ex);
        }
        return response;

    }


    private NotifyRemind getNewNeighborRemind(String memberId) {

        GetNotifyByMIdRequest notifyByMIdRequest = new GetNotifyByMIdRequest();
        notifyByMIdRequest.setMemberId(memberId);
        notifyByMIdRequest.setReset(0);
        GetNotifyByMIdResponse notifyByMidResponse = topicRpc.getNotifyByMId(notifyByMIdRequest);
        notifyByMidResponse.getNoticeNum();
        NotifyRemind notifyRemind = new NotifyRemind();
        notifyRemind.setQueryType(1);//
        notifyRemind.setNoticeNum(notifyByMidResponse.getNoticeNum());

        return notifyRemind;
    }


    /**
     * 系统通知列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getSysNotifies")
    public Response<GeSysNotifiesResponseData> geSysNotifies(GeSysNotifiesRequest request) {

        Response<GeSysNotifiesResponseData> response = new Response<GeSysNotifiesResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GeSysNotifiesResponseData data = new GeSysNotifiesResponseData();
        MsgInfoRequest msgRequest = transfor(MsgInfoRequest.class, request);
        StringBuffer sb = new StringBuffer();
        sb.append(Constant.FAMILY_PAY_NOTICE_SKIP_NO);
        sb.append(",");
        sb.append(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_INTEGRAL_CHANGE);
        sb.append(",");
        sb.append(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_INTEGRAL_NOTICE);
        sb.append(",");
        sb.append(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_NEIGHBOR);
        sb.append(",");
        sb.append(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_NEIGHBOR_DETAIL);
        sb.append(",");
        sb.append(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM);
        sb.append(",");
        sb.append(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_H5);
        sb.append(",");
        sb.append(MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_TOURISM_SERVICE);
        sb.append(",");
        sb.append(MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_TOURISM_LINE);
        sb.append(",");
        sb.append(MsgConstant.APP_PUSH_MESSAGE_PRECISION_MARKETING);
        sb.append(",");
        sb.append(MsgConstant.APP_PUSH_MESSAGE_MARKETING_PUSH);
        sb.append(",");
        sb.append(MsgConstant.APP_PUSH_MESSAGE_ROBOT_REMINDER_4);
        msgRequest.setType(sb.toString());
        msgRequest.setAppType(MsgConstant.APP_TYPE_QDING);
        msgRequest.setMid(request.getMemberId());
        MsgInfoResponse msgResponse = messageService.getMsgList(msgRequest);
        try {
            checkAndContinue(msgResponse);
            Page<MsgMessage> page = msgResponse.getPageList();
            if (CollectionUtils.isNotEmpty(page.getList())) {
                List<SysNotify> sysNotifyList = Lists.newArrayList();
                String version = request.getAppDevice().getQdVersion().trim();
                Map<String, String> skipConfigMap = skipMode.selSkipTemplateMap(version);

                for (MsgMessage m : page.getList()) {
                    try {
                        SysNotify sysNotify = new SysNotify();
                        sysNotify.setCreateTime(m.getCreateTime());
                        sysNotify.setContent(m.getContent());
                        if (m.getType() == Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_INTEGRAL_NOTICE) ||
                                m.getType() == Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_INTEGRAL_CHANGE)) {
                            sysNotify.setTitle("积分变动");
                            if (m.getType() == Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_INTEGRAL_NOTICE)) {
                                JSONObject json = JSON.parseObject(m.getDetail());
                                String goodId = json.getString("goodId");
                                if (QDStringUtil.isNotEmpty(goodId)) {
                                    sysNotify.setSkipModel(skipMode.fittingSkipUrl(skipConfigMap, APIPropertiesClient.getValue("integral.conver.detail") + goodId, 1, 0, ""));
                                }
                            } else {
                                sysNotify.setSkipModel(skipMode.fittingSkipUrl(skipConfigMap, APIPropertiesClient.getValue("integral.conver.home"), 1, 0, ""));
                            }
                        } else if (m.getType() == Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_NEIGHBOR_DETAIL)) {
                            sysNotify.setTitle(m.getTitle());
                            if (QDStringUtil.isNotEmpty(m.getDetail())) {
                                JSONObject detailJson = JSON.parseObject(m.getDetail());
                                if (QDStringUtil.isNotNull(detailJson)) {
                                    String id = String.valueOf(detailJson.get("id"));
                                    if (QDStringUtil.isNotEmpty(id))
                                        sysNotify.setSkipModel(skipMode.fittingSkipModelByOnlyId("2.6.0", 3014, id));
                                }
                            }
                        } else if (m.getType() == Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM)) {
                            sysNotify.setTitle(m.getTitle());
                            if (QDStringUtil.isNotEmpty(m.getDetail())) {
                                JSONObject detailJson = JSON.parseObject(m.getDetail());
                                if (QDStringUtil.isNotNull(detailJson)) {
                                    com.qding.api.util.SkipBean skipBean = new com.qding.api.util.SkipBean();
                                    String curVersion = "2.6.0";
                                    if (QDStringUtil.isNotNull(detailJson.getString("cid"))) {
                                        skipBean.setProjectId(detailJson.getString("cid"));
                                        skipBean.setProjectName(detailJson.getString("cname"));
                                        curVersion = "2.7.0";
                                    }
                                    skipBean.setPcode(9);
                                    skipBean.setSkipNo(4201);
                                    sysNotify.setSkipModel(skipMode.fittingSkipModelBySkipBean(curVersion, skipBean));
                                }
                            }

                        } else if (m.getType() == Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_H5)) {
                            sysNotify.setTitle(m.getTitle());
                            if (QDStringUtil.isNotEmpty(m.getDetail())) {
                                JSONObject detailJson = JSON.parseObject(m.getDetail());
                                if (QDStringUtil.isNotNull(detailJson)) {
                                    String url = String.valueOf(detailJson.get("url"));
                                    String curTime = String.valueOf(detailJson.get("curTime"));
                                    if (QDStringUtil.isNotEmpty(url))
                                        sysNotify.setSkipModel(skipMode.fittingSkipUrl("2.7.0", url + "?pushid=" + curTime, 0, ""));
                                }
                            }
                        } else if (m.getType() == Long.parseLong(MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_TOURISM_SERVICE)
                                || m.getType() == Long.parseLong(MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_TOURISM_LINE)
                                || m.getType() == Long.parseLong(MsgConstant.APP_PUSH_MESSAGE_MARKETING_PUSH)
                                || m.getType() == Long.parseLong(MsgConstant.APP_PUSH_MESSAGE_ROBOT_REMINDER_4)) {
                            sysNotify.setTitle(m.getTitle());
                            if (QDStringUtil.isNotEmpty(m.getDetail())) {
                                JSONObject detailJson = JSON.parseObject(m.getDetail());
                                if (QDStringUtil.isNotNull(detailJson)) {
                                    String url = String.valueOf(detailJson.get("url"));
                                    if (QDStringUtil.isNotEmpty(url))
                                        sysNotify.setSkipModel(skipMode.fittingSkipUrl(skipConfigMap, url, 0, 0,""));
                                }
                            }
                        } else if (m.getType() == Long.parseLong( MsgConstant.APP_PUSH_MESSAGE_PRECISION_MARKETING)){
                            try {
                                JSONObject detailJson = JSON.parseObject(m.getDetail().replace("\"{" ,"{").replace("}\"" ,"}"));
                                String jumpDetail = detailJson.getString("jumpDetail");
                                if (QDStringUtil.isNotEmpty(jumpDetail)) {
                                    JSONObject skipJson = JSON.parseObject(jumpDetail);
                                    Integer skipNo = skipJson.getInteger("skNo");
                                    String value =  skipJson.getString("value");
                                    String msgVersion =  skipJson.getString("version");
                                    sysNotify.setTitle(m.getTitle());
                                    if (Constant.SkipNo.URL_7000.toInteger().intValue() == Integer.valueOf(skipNo)) {
                                        sysNotify.setSkipModel(skipMode.fittingSkipUrl(skipConfigMap, value, 0, 0,""));
                                    } else {
                                        com.qding.api.util.SkipBean skipBean = new com.qding.api.util.SkipBean();
                                        skipBean.setSkipNo(skipNo);
                                        skipBean.setIds(value);
                                        sysNotify.setSkipModel(skipMode.fittingSkipModelBySkipBean(msgVersion, skipBean));
                                    }
                                }
                            } catch (Exception ex) {
                                logger.error("method: getSysNotifies,get SkipNo:"+MsgConstant.APP_PUSH_MESSAGE_PRECISION_MARKETING+" error: ", ex);
                            }

                        } else {
                            sysNotify.setTitle(QDStringUtil.isNotEmpty(m.getTitle()) ? m.getTitle() : "");
                        }
                        sysNotifyList.add(sysNotify);

                    } catch (Exception e) {
                        logger.error("getSysNotifies", e);
                    }
                }
                data.setList(sysNotifyList);
                data.setTotalCount(page.getTotalRow());
                data.setRecordCount(sysNotifyList.size());
            }

            response.setData(data);
        } catch (Exception e) {
            return handleException(GeSysNotifiesResponseData.class, e);
        }
        return response;

    }


    /**
     * 获取用业态消息列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getBusinessNotifies")
    public Response<GetBusinessNotifiesResponseData> getBusinessNotifies(GeBusinessNotifiesRequest request) {

        try {
            Response<GetBusinessNotifiesResponseData> response = new Response<>();

            MsgInfoRequest msgRequest = transfor(MsgInfoRequest.class, request);
            //乐购消息
            msgRequest.setType("4,5,6,5053,5008");

            msgRequest.setMid(request.getMemberId());

            List<BusinessNotify> businessNotify = new ArrayList<>();

            MsgInfoResponse msgResponse = messageService.getMsgList(msgRequest);

            checkAndContinue(msgResponse);

            Page<MsgMessage> page = msgResponse.getPageList();

            for (MsgMessage m : page.getList()) {
                try {

                    JSONObject json = JSON.parseObject(m.getDetail());
                    String title = QDStringUtil.isNotNull(json.getString("title")) ? json.getString("title") : m.getTitle();
                    SkipBean entity = new SkipBean();
                    if (5053L == m.getType() || 5008L == m.getType()) { //洗衣业态,绑定房屋业态消息
                        entity.setSubType("NOTIFY_XY");
                        entity.setServiceType("3");//不做任何跳转
                        if (5008L == m.getType()) {
                            title = "房间绑定提醒";
                        }

                    } else {
                        entity.setSubType("NOTIFY_NG");
                        entity.setServiceType("1");
                    }
                    businessNotify.add(new BusinessNotify(
                            m.getId().toString(),
                            title,
                            m.getCreateTime(),
                            QDStringUtil.isNotNull(json.getString("content")) ? json.getString("content") : m.getContent(),
                            json.getString("orderId"),
                            entity
                    ));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            GetBusinessNotifiesResponseData data = new GetBusinessNotifiesResponseData();
            data.setList(businessNotify);
            data.setRecordCount(businessNotify.size());
            data.setTotalCount(page.getTotalRow());
            response.setData(data);

            return response;
        } catch (Exception e) {

            return handleException(GetBusinessNotifiesResponseData.class, e);
        }
    }


    /**
     * 消息设置（订单消息，系统消息，免打扰时间段）
     *
     * @param request
     * @return
     */
    @HTTP(alias = "notifySettings")
    public Response<NotifySettingsResponseData> notifySettings(NotifySettingsRequest request) {

        try {
            Response<NotifySettingsResponseData> response = new Response<NotifySettingsResponseData>();

            MsgAppMessageConfigResponse msgAppMessageConfigResponse = messageService.notifySettings(transfor(MsgAppMessageConfigRequest.class, request));

            checkAndContinue(msgAppMessageConfigResponse);

            NotifySettingsResponseData data = new NotifySettingsResponseData();

            response.setData(data);

            response.setCode(HttpStatus.OK.getStatusCode());

            return response;

        } catch (Exception e) {
            return handleException(NotifySettingsResponseData.class, e);
        }

    }

    /**
     * 获取当前用户消息设置
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getNotifySettings")
    public Response<GetNotifySettingsResponseData> GetNotifySettings(GetNotifySettingsRequest request) {

        try {
            Response<GetNotifySettingsResponseData> response = new Response<GetNotifySettingsResponseData>();

            GetMsgAppMessageConfigResponse getMsgAppMessageConfigResponse = messageService.getNotifySettings(request.getMemberId());

            checkAndContinue(getMsgAppMessageConfigResponse);

            GetNotifySettingsResponseData data = transfor(GetNotifySettingsResponseData.class, getMsgAppMessageConfigResponse);

            response.setData(data);

            response.setCode(HttpStatus.OK.getStatusCode());

            return response;

        } catch (Exception e) {

            return handleException(GetNotifySettingsResponseData.class, e);
        }
    }

}
