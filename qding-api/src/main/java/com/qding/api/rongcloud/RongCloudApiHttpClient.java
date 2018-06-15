package com.qding.api.rongcloud;


import com.qding.api.rongcloud.model.*;
import com.qding.api.rongcloud.model.response.UserBlackList;
import com.qding.api.rongcloud.model.response.UserToken;
import com.qding.api.rongcloud.util.GsonUtil;
import com.qding.api.rongcloud.util.HttpUtil;
import com.qding.framework.common.util.QDStringUtil;
import org.apache.log4j.Logger;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public final class RongCloudApiHttpClient {
    private static final Logger logger = Logger.getLogger(RongCloudApiHttpClient.class);

    private static final String RONGCLOUDURI = "http://api.cn.ronghub.com";

    private static final String UTF8 = "UTF-8";

    private static String APPKEY;

    private static String APPSECRET;

    private RongCloudApiHttpClient() {
    }


    // 获取token
    public static UserToken getToken(String userId, String userName, String portraitUri) throws Exception {

        HttpURLConnection conn = HttpUtil
                .CreatePostHttpConnection(APPKEY, APPSECRET, RONGCLOUDURI
                        + "/user/getToken." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
        if (QDStringUtil.isNotEmpty(userName)) {
            sb.append("&name=").append(URLEncoder.encode(userName, UTF8));
        }
        if (QDStringUtil.isNotEmpty(portraitUri)) {
            sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri, UTF8));
        }
        HttpUtil.setBodyParameter(sb, conn);

        String result = HttpUtil.returnResultStr(conn);

        logger.info("getToken APPKEY:" + APPKEY + " ,APPSECRET:" + APPSECRET + " ,RONGCLOUDURI:" + RONGCLOUDURI + " ,sb:" + sb
                + " ,result:" + result);

        if (QDStringUtil.isNotEmpty(result)) {
            return (UserToken) GsonUtil.fromJson(result, UserToken.class);
        }

        return null;
    }

    //检测在线状态
    public static SdkHttpResult checkOnline(String userId) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/user/checkOnline." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    //刷新用户信息
    public static SdkHttpResult refreshUser(String userId, String userName, String portraitUri) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET, RONGCLOUDURI + "/user/refresh." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
        if (QDStringUtil.isNotEmpty(userName)) {
            sb.append("&name=").append(URLEncoder.encode(userName, UTF8));
        }
        if (QDStringUtil.isNotEmpty(portraitUri)) {
            sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri, UTF8));
        }
        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }


    public static SdkHttpResult blockUser(String userId, int minute) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET, RONGCLOUDURI + "/user/block." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
        sb.append("&minute=").append(
                URLEncoder.encode(String.valueOf(minute), UTF8));

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }


    public static SdkHttpResult unblockUser(String userId) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET, RONGCLOUDURI + "/user/unblock." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }


    public static SdkHttpResult queryBlockUsers() throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/user/block/query." + FormatType.json);

        return HttpUtil.returnResult(conn);
    }

    // 添加黑名单
    public static boolean blackUser(String userId, List<String> blackUserIds)
            throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/user/blacklist/add." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
        if (blackUserIds != null) {
            for (String blackId : blackUserIds) {
                sb.append("&blackUserId=").append(
                        URLEncoder.encode(blackId, UTF8));
            }
        }

        HttpUtil.setBodyParameter(sb, conn);
        String result = HttpUtil.returnResultStr(conn);

        return result.indexOf("200") != -1;
    }

    //去除黑名单
    public static boolean unblackUser(String userId, List<String> blackUserIds)
            throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/user/blacklist/remove." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
        if (blackUserIds != null) {
            for (String blackId : blackUserIds) {
                sb.append("&blackUserId=").append(
                        URLEncoder.encode(blackId, UTF8));
            }
        }

        HttpUtil.setBodyParameter(sb, conn);

        String result = HttpUtil.returnResultStr(conn);

        return result.indexOf("200") != -1;
    }

    // 获取用户黑名单列表
    public static UserBlackList QueryblackUser(String userId) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/user/blacklist/query." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));

        HttpUtil.setBodyParameter(sb, conn);

        String result = HttpUtil.returnResultStr(conn);

        if (QDStringUtil.isNotEmpty(result)) {
            return (UserBlackList) GsonUtil.fromJson(result, UserBlackList.class);
        }
        return null;
    }

    // ����Ⱥ
    public static SdkHttpResult createGroup(List<String> userIds, String groupId, String groupName) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET, RONGCLOUDURI + "/group/create." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));
        if (userIds != null) {
            for (String id : userIds) {
                sb.append("&userId=").append(URLEncoder.encode(id, UTF8));
            }
        }
        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ����Ⱥ
    public static SdkHttpResult joinGroup(String userId, String groupId, String groupName)
            throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET, RONGCLOUDURI + "/group/join." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
        sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
        sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));
        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ��������Ⱥ
    public static SdkHttpResult joinGroupBatch(List<String> userIds, String groupId, String groupName) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET, RONGCLOUDURI + "/group/join." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));
        if (userIds != null) {
            for (String id : userIds) {
                sb.append("&userId=").append(URLEncoder.encode(id, UTF8));
            }
        }
        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // �˳�Ⱥ
    public static SdkHttpResult quitGroup(String userId, String groupId) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET, RONGCLOUDURI + "/group/quit." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
        sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // �����˳�Ⱥ
    public static SdkHttpResult quitGroupBatch(List<String> userIds, String groupId)
            throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET, RONGCLOUDURI + "/group/quit." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        if (userIds != null) {
            for (String id : userIds) {
                sb.append("&userId=").append(URLEncoder.encode(id, UTF8));
            }
        }

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ��ɢȺ
    public static SdkHttpResult dismissGroup(String userId, String groupId) throws Exception {

        HttpURLConnection conn = HttpUtil
                .CreatePostHttpConnection(APPKEY, APPSECRET, RONGCLOUDURI
                        + "/group/dismiss." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
        sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ͬ���û�Ⱥ��Ϣ
    public static SdkHttpResult syncGroup(String userId, List<GroupInfo> groups)
            throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET, RONGCLOUDURI + "/group/sync." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
        if (groups != null) {
            for (GroupInfo info : groups) {
                if (info != null) {
                    sb.append(
                            String.format("&group[%s]=",
                                    URLEncoder.encode(info.getId(), UTF8)))
                            .append(URLEncoder.encode(info.getName(), UTF8));
                }
            }
        }
        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ˢ��Ⱥ��Ϣ
    public static SdkHttpResult refreshGroupInfo(String groupId, String groupName) throws Exception {

        HttpURLConnection conn = HttpUtil
                .CreatePostHttpConnection(APPKEY, APPSECRET, RONGCLOUDURI
                        + "/group/refresh." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
        sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ˢ��Ⱥ��Ϣ
    public static SdkHttpResult refreshGroupInfo(GroupInfo group)
            throws Exception {

        HttpURLConnection conn = HttpUtil
                .CreatePostHttpConnection(APPKEY, APPSECRET, RONGCLOUDURI
                        + "/group/refresh." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("groupId=").append(URLEncoder.encode(group.getId(), UTF8));
        sb.append("&groupName=").append(
                URLEncoder.encode(group.getName(), UTF8));

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ������Ϣ
    public static SdkHttpResult publishMessage(String fromUserId, List<String> toUserIds, Message msg) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/message/private/publish." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
        if (toUserIds != null) {
            for (int i = 0; i < toUserIds.size(); i++) {
                sb.append("&toUserId=").append(
                        URLEncoder.encode(toUserIds.get(i), UTF8));
            }
        }
        sb.append("&objectName=")
                .append(URLEncoder.encode(msg.getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ������Ϣ
    public static SdkHttpResult publishMessage(
            String fromUserId, List<String> toUserIds, Message msg,
            String pushContent, String pushData)
            throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/message/publish." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
        if (toUserIds != null) {
            for (int i = 0; i < toUserIds.size(); i++) {
                sb.append("&toUserId=").append(
                        URLEncoder.encode(toUserIds.get(i), UTF8));
            }
        }
        sb.append("&objectName=")
                .append(URLEncoder.encode(msg.getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

        if (pushContent != null) {
            sb.append("&pushContent=").append(
                    URLEncoder.encode(pushContent, UTF8));
        }

        if (pushData != null) {
            sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
        }

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ����ϵͳ��Ϣ
    public static SdkHttpResult publishSystemMessage(String fromUserId, List<String> toUserIds,
                                                     Message msg, String pushContent, String pushData)
            throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/message/system/publish." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
        if (toUserIds != null) {
            for (int i = 0; i < toUserIds.size(); i++) {
                sb.append("&toUserId=").append(
                        URLEncoder.encode(toUserIds.get(i), UTF8));
            }
        }
        sb.append("&objectName=")
                .append(URLEncoder.encode(msg.getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

        if (pushContent != null) {
            sb.append("&pushContent=").append(
                    URLEncoder.encode(pushContent, UTF8));
        }

        if (pushData != null) {
            sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
        }

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ����Ⱥ��Ϣ
    public static SdkHttpResult publishGroupMessage(String fromUserId, List<String> toGroupIds,
                                                    Message msg, String pushContent, String pushData)
            throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/message/group/publish." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
        if (toGroupIds != null) {
            for (int i = 0; i < toGroupIds.size(); i++) {
                sb.append("&toGroupId=").append(
                        URLEncoder.encode(toGroupIds.get(i), UTF8));
            }
        }
        sb.append("&objectName=")
                .append(URLEncoder.encode(msg.getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

        if (pushContent != null) {
            sb.append("&pushContent=").append(
                    URLEncoder.encode(pushContent, UTF8));
        }

        if (pushData != null) {
            sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
        }

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ������������Ϣ
    public static SdkHttpResult publishChatroomMessage(String fromUserId, List<String> toChatroomIds,
                                                       Message msg) throws Exception {

        HttpURLConnection conn = HttpUtil
                .CreatePostHttpConnection(APPKEY, APPSECRET, RONGCLOUDURI
                        + "/message/chatroom/publish." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
        if (toChatroomIds != null) {
            for (int i = 0; i < toChatroomIds.size(); i++) {
                sb.append("&toChatroomId=").append(
                        URLEncoder.encode(toChatroomIds.get(i), UTF8));
            }
        }
        sb.append("&objectName=")
                .append(URLEncoder.encode(msg.getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    public static SdkHttpResult broadcastMessage(String fromUserId, Message msg, String pushContent, String pushData) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/message/broadcast." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
        sb.append("&objectName=")
                .append(URLEncoder.encode(msg.getType(), UTF8));
        sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));
        if (pushContent != null) {
            sb.append("&pushContent=").append(
                    URLEncoder.encode(pushContent, UTF8));
        }

        if (pushData != null) {
            sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
        }

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ����������
    public static SdkHttpResult createChatroom(List<ChatroomInfo> chatrooms) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/chatroom/create." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("1=1");
        if (chatrooms != null) {
            for (ChatroomInfo info : chatrooms) {
                if (info != null) {
                    sb.append(
                            String.format("&chatroom[%s]=",
                                    URLEncoder.encode(info.getId(), UTF8)))
                            .append(URLEncoder.encode(info.getName(), UTF8));
                }
            }
        }
        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ���������
    public static SdkHttpResult destroyChatroom(List<String> chatroomIds)
            throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/chatroom/destroy." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("1=1");
        if (chatroomIds != null) {
            for (String id : chatroomIds) {
                sb.append("&chatroomId=").append(URLEncoder.encode(id, UTF8));
            }
        }

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ��ѯ��������Ϣ
    public static SdkHttpResult queryChatroom(List<String> chatroomIds) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/chatroom/query." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("1=1");
        if (chatroomIds != null) {
            for (String id : chatroomIds) {
                sb.append("&chatroomId=").append(URLEncoder.encode(id, UTF8));
            }
        }

        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ��ȡ��Ϣ��ʷ��¼���ص�ַ
    public static SdkHttpResult getMessageHistoryUrl(String date) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/message/history." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("date=").append(URLEncoder.encode(date, UTF8));
        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }

    // ɾ����Ϣ��ʷ��¼
    public static SdkHttpResult deleteMessageHistory(String date) throws Exception {

        HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APPKEY,
                APPSECRET,
                RONGCLOUDURI + "/message/history/delete." + FormatType.json);

        StringBuilder sb = new StringBuilder();
        sb.append("date=").append(URLEncoder.encode(date, UTF8));
        HttpUtil.setBodyParameter(sb, conn);

        return HttpUtil.returnResult(conn);
    }


/*
    public static void main(String[] args) throws Exception{
        UserToken userToken = RongCloudApiHttpClient.getToken("000000004e43e5cb014e4868b47b0020",null,null);

        System.out.println(userToken);

        //添加黑名单
        List<String> list = new ArrayList<String>();
        list.add("456");
        list.add("789");
        boolean flag = RongCloudApiHttpClient.blackUser("123",list);
        System.out.println(flag);


        //从黑名单移除
        List<String> list1 = new ArrayList<String>();
        list1.add("456");
        list1.add("789");
        boolean flag1 = RongCloudApiHttpClient.unblackUser("123", list1);
        System.out.println(flag1);

        //获取黑名单列表
        UserBlackList blackList = RongCloudApiHttpClient.QueryblackUser("123");

        if(blackList!=null){
            System.out.println(blackList.getCode());
            if(blackList.getUsers()!=null && blackList.getUsers().length>0){
                for(String userId: blackList.getUsers()){
                    System.out.println(userId);
                }
            }
        }

    }
*/

    public static String getAPPKEY() {
        return APPKEY;
    }

    public static void setAPPKEY(String APPKEY) {
        RongCloudApiHttpClient.APPKEY = APPKEY;
    }

    public static String getAPPSECRET() {
        return APPSECRET;
    }

    public static void setAPPSECRET(String APPSECRET) {
        RongCloudApiHttpClient.APPSECRET = APPSECRET;
    }
}
