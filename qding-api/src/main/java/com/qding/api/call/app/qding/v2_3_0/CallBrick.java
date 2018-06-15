package com.qding.api.call.app.qding.v2_3_0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.City;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Group;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Project;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectSpell;
import com.qding.api.call.app.qding.v2_3_0.struct.brick.ProjectsByGroup;
import com.qding.api.call.app.qding.v2_3_0.struct.brick.request.*;
import com.qding.api.call.app.qding.v2_3_0.struct.brick.response.data.CheckValidBymobileAndProjectIdResponseData;
import com.qding.api.call.app.qding.v2_3_0.struct.brick.response.data.GetProjectByCityIdResponseData;
import com.qding.api.call.app.qding.v2_3_0.struct.brick.response.data.GetRoomsByKeyWordResponseData;
import com.qding.api.call.app.qding.v2_3_0.struct.brick.response.data.GetServiceUrlByShortUrlResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.QDMemberRemote;
import com.qding.api.util.SkipModeFitting;
import com.qding.api.verifycode.VerifyCode;
import com.qding.brick.pojo.biz.Region;
import com.qding.brick.pojo.biz.RegionGroup;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.brick.struts.entity.ProjectDistance;
import com.qding.brick.struts.request.AppLocateRequest;
import com.qding.brick.struts.request.RoomRequest;
import com.qding.brick.struts.response.AppLocateResponse;
import com.qding.brick.struts.response.RoomReadResponse;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.http.QDHttpClientService;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberRoom;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.response.memberroom.GetMemberRoomResponse;
import com.qding.sysconfig.rpc.service.DemoProjectRpcService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

@ExplainAnnotation(explain = "基础数据")
public class CallBrick extends com.qding.api.call.app.qding.v2_0_0.CallBrick {


    @Autowired
    private AppConfigRemote appConfigRemoteService;

    @Autowired
    private RoomReadRemote roomReadRemoteService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private DemoProjectRpcService demoProjectRpcService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IMemberRoomRPC memberRoomAPI;

    private static Logger logger = Logger.getLogger(CallBrick.class);

    @HTTP(alias = "getProjects")
    @ExplainAnnotation(explain = "根据城市id获取社区列表")
    public Response<GetProjectByCityIdResponseData> getProjects(GetProjectByCityIdRequest request, UserToken userToken) {

        try {

            Response<GetProjectByCityIdResponseData> response = new Response<GetProjectByCityIdResponseData>();
            GetProjectByCityIdResponseData data = new GetProjectByCityIdResponseData();
            List<ProjectsByGroup> projectsByGroupList = new ArrayList<>();

            String memberId = QDStringUtil.isNotNull(userToken) &&QDStringUtil.isNotEmpty(userToken.getMemberId())?userToken.getMemberId():"";

            //如果没有提供经纬度，则直接按照拼音首字母排序
            if (QDStringUtil.isEmpty(request.getLatitude()) || QDStringUtil.isEmpty(request.getLongitude())) {
                com.qding.api.call.app.qding.v1_3_0.struct.brick.request.GetProjectByCityIdRequest projectByCityRequest = transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.request.GetProjectByCityIdRequest.class, request);
                projectByCityRequest.setMemberId(memberId);
                List<ProjectSpell> projectSpellList = getProjectsGroupBySpell(projectByCityRequest);
                projectsByGroupList = transforList(ProjectsByGroup.class, projectSpellList);
                data.setShowType(1);
            } else {
                AppLocateRequest appLocateRequest = transfor(AppLocateRequest.class, request);
                Integer brickSouceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
                appLocateRequest.setType(brickSouceType);
                if(request.getLocationType()!=null){
                    appLocateRequest.setLocationType(request.getLocationType());
                }
                String qdPlatform = request.getAppDevice().getQdPlatform();
                if (QDStringUtil.isNotEmpty(qdPlatform) && QDStringUtil.isNotNull(qdPlatform)) {
                    qdPlatform = qdPlatform.toLowerCase();
                }
                String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
                if (Integer.parseInt(initVersion)>=250000 && Integer.parseInt(initVersion)<280000  && ("android".equalsIgnoreCase(qdPlatform) || "ios".equalsIgnoreCase(qdPlatform))){
                    appLocateRequest.setIsIncludeVirtual(1);//1:包含虚拟社区 0：不包含
                }

                if (QDStringUtil.isNotEmpty(memberId)
                        && Integer.parseInt(initVersion)>=280000 && ("android".equalsIgnoreCase(qdPlatform)||"ios".equalsIgnoreCase(qdPlatform))){
                    //通过会员拿到绑定的房屋列表,通过房屋列表获取所在的社区列表
                    try{
                        GetMemberRoomResponse  memberRoomResponse = memberRoomAPI.listValidByMember(memberId);
                        checkAndContinue(memberRoomResponse);
                        Set<Long> bindRoomPIdSet = new HashSet<>();
                        List<com.qding.member.model.MemberRoom> lsMemberRoom = memberRoomResponse.getList();
                        for (MemberRoom memberRoom : lsMemberRoom) {
                            bindRoomPIdSet.add(Long.parseLong(memberRoom.getProjectId()));
                        }
                        appLocateRequest.setNeedIncludeProjectIds(bindRoomPIdSet);
                        appLocateRequest.setCityId(Long.parseLong(request.getCityId()));
                    }catch (Exception ex) {
                        logger.error("根据城市id获取社区列表，GPS模式异常(v-2.8)：",ex);
                    }
                }

                AppLocateResponse appLocateResponse = appConfigRemoteService.getDistanceByGPS(appLocateRequest);
                checkAndContinue(appLocateResponse);
                data.setStatus(appLocateResponse.getStatus());
                Region region = appLocateResponse.getRegion();
                if (QDStringUtil.isNotNull(region)) {
                    City city = new City(String.valueOf(region.getId()), region.getName());
                    data.setCity(city);
                }

                List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Project> projectList = Lists.newArrayList();
                if (CollectionUtils.isNotEmpty(appLocateResponse.getProjectDistanceList())) {
                    List<ProjectDistance> projectDistanceList  =  appLocateResponse.getProjectDistanceList();
                    projectList =transforList(com.qding.api.call.app.qding.v1_3_0.struct.brick.Project.class, projectDistanceList);
                    Set<Long> projectIdSet = new HashSet<>();
                    for (Project project : projectList) {
                        projectIdSet.add(Long.parseLong(project.getId()));
                    }
                    Map<Long, List<RegionGroup>> groupMap = projectReadService.getGroupListByProjectId(projectIdSet);
                    if (QDStringUtil.isNotNull(groupMap)){
                        for (Project project : projectList) {
                            if (groupMap.containsKey(Long.parseLong(project.getId()))){
                                project.setGroups(transforList(Group.class, groupMap.get(Long.parseLong(project.getId()))));
                            }
                        }
                    }

                }
                projectsByGroupList.add(new ProjectsByGroup("gps", projectList));

                ProjectDistance projectDistance = appLocateResponse.getLocateProject();
                if (QDStringUtil.isNotNull(projectDistance)) {
                    com.qding.api.call.app.qding.v1_3_0.struct.brick.Project locateProject = transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Project.class, projectDistance);
                    data.setLocateProject(locateProject);
                }
                data.setShowType(2);
            }
            data.setList(projectsByGroupList);
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
            return response;

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
            return handleException(GetProjectByCityIdResponseData.class, e);

        }
    }

    @HTTP(alias = "getServiceUrlByShortUrl")
    @ExplainAnnotation(explain = "短地址转服务地址")
    public Response<GetServiceUrlByShortUrlResponseData> getServiceUrlByShortUrl(GetServiceUrlByShortUrlRequest request) {

        Response<GetServiceUrlByShortUrlResponseData> response = new Response<GetServiceUrlByShortUrlResponseData>();
        GetServiceUrlByShortUrlResponseData data = new GetServiceUrlByShortUrlResponseData();
        response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
        data.setMessage("二维码已失效");
        response.setData(data);
        try {
            String convertUrl = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.URL_CONVERT_DICTNAME.toString(), Constant.URL_CONVERT_DICTNAME.toString());
            StringBuffer urlsb = new StringBuffer(convertUrl + "/get?h=%s");
            String responseStr = QDHttpClientService.sendGetRequest(String.format(urlsb.toString(), request.getConvertUrl()), "utf-8");
            if (QDStringUtil.isNotNull(responseStr)) {
                responseStr = URLDecoder.decode(responseStr, "UTF-8");
                JSONObject jsonObject = JSON.parseObject(responseStr.toString());
                if (jsonObject.containsKey("url")) {
                    String serviceUrl = jsonObject.get("url").toString();
                    serviceUrl = serviceUrl.replace("'","\"");
                    String[] urlArray = serviceUrl.split("skip=");
                    String parameStr = URLEncoder.encode(urlArray[1],"UTF-8");
                    serviceUrl = urlArray[0]+"skip="+parameStr;
                    data.setServiceUrl(serviceUrl);
                    data.setMessage("");
                    response.setData(data);
                    response.setCode(HttpStatus.OK.getStatusCode());
                }
            }

        } catch (Exception e) {
            return handleException(GetServiceUrlByShortUrlResponseData.class, e);
        }

        return response;
    }

    @HTTP(alias = "delMsgVerifyCache")
    @Deprecated
    @ExplainAnnotation(explain = "删除短信缓存验证数据")
    public void delMsgVerifyCache (DelMsgVerifyCacheRequest request) {

        VerifyCode.delVerifyMsgCache(request.getMobile(),request.getType());
    }


    @HTTP(alias = "getRoomByKeyWord")
    @ExplainAnnotation(explain = "根据房间号关键字查询房间")
    public Response<GetRoomsByKeyWordResponseData> getRoomByKeyWord (GetRoomsByKeyWordRequest request) {

        Response<GetRoomsByKeyWordResponseData> response = new Response<GetRoomsByKeyWordResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetRoomsByKeyWordResponseData data = new GetRoomsByKeyWordResponseData();
        List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Room> list = Lists.newArrayList();
        try {
            RoomRequest roomRequest = new RoomRequest();
            Set<Long> buildingSet = new HashSet<>();
            buildingSet.add(Long.parseLong(request.getBuildingId()));
            Set<String> codeSet = new HashSet<>();
            codeSet.add(request.getKeyWord());
            roomRequest.setBuildingIdSet(buildingSet);
            roomRequest.setNameSet(codeSet);
            RoomReadResponse roomReadResponse = roomReadRemoteService.getRoomsByRequest(roomRequest);
            checkAndContinue(roomReadResponse);

            List< com.qding.brick.pojo.biz.Room> rooms = roomReadResponse.getRooms();
            if (CollectionUtils.isNotEmpty(rooms)) {
                list = transforList(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, rooms);
            }
            data.setTotalCount(roomReadResponse.getTotalCount());
            data.setList(list);
            response.setData(data);
            return response;

         } catch (Exception e) {
            return handleException(GetRoomsByKeyWordResponseData.class, e);
        }
    }


    @HTTP(alias = "checkValidBymobileAndProjectId")
    @ExplainAnnotation(explain = "验证当前手机号是否有权限进入演示社区")
    public Response<CheckValidBymobileAndProjectIdResponseData> checkValidBymobileAndProjectId(CheckValidBymobileAndProjectIdRequest request){

        Response<CheckValidBymobileAndProjectIdResponseData> response = new Response<CheckValidBymobileAndProjectIdResponseData>();
        CheckValidBymobileAndProjectIdResponseData data = new CheckValidBymobileAndProjectIdResponseData();
        try {
            boolean flag = demoProjectRpcService.checkValidBymobileAndProjectId(request.getMobile().trim(),request.getProjectId());
            data.setHavePower(flag?1:0);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);

        }catch (Exception ex){
            return handleException(CheckValidBymobileAndProjectIdResponseData.class, ex);
        }
        return response;

    }


/**************************************************************华丽的内部方法分割线**************************************************************************/

//    public static String ServiceUrlConvertShortUrl(String url, Long ExpressTime) {
//
//        String shortUrl = "400";
//        try {
//            String convertUrl  = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.URL_CONVERT_DICTNAME.toString(), Constant.URL_CONVERT_DICTNAME.toString());
//            StringBuffer urlsb = new StringBuffer(convertUrl + "/new?u=%s&t=%s");
//            url = URLEncoder.encode(url, "utf-8");
//            String responseStr = QDHttpClientService.sendGetRequest(String.format(urlsb.toString(), url, ExpressTime), "utf-8");
//            if (QDStringUtil.isNotNull(responseStr)) {
//                JSONObject jsonObject = JSON.parseObject(responseStr.toString());
//                logger.info("邀请码短连接参数输入："+responseStr);
//                if (jsonObject.containsKey("hash")) {
//                    shortUrl = jsonObject.get("hash").toString();
//                    logger.info("邀请码短连接产出："+convertUrl+"/"+shortUrl);
//                    return  convertUrl+"/"+shortUrl;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return shortUrl;
//    }

}
