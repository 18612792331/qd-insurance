package com.qding.api.call.app.qding.v3_0_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v3_0_0.struct.brick.HomeSituationDicDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.brick.request.*;
import com.qding.api.call.app.qding.v3_0_0.struct.brick.response.data.*;
import com.qding.api.call.service.BrickService;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.struts.request.ProjectNameLikeRequest;
import com.qding.brick.struts.response.ProjectNameLikeResponse;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.solr.service.ISolrRoomService;
import com.qding.solr.struct.basis.Room;
import com.qding.solr.struct.request.RoomReadRequest;
import com.qding.solr.struct.response.RoomResponse;
import com.qding.thrift.model.dictionary.Dictionary;
import org.apache.commons.collections.CollectionUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by qd on 2017/2/23.
 */
@ExplainAnnotation(explain = "基础服务")
public class CallBrick extends com.qding.api.call.app.qding.v2_8_0.CallBrick {

    @Autowired
    private ProjectReadRemote projectService;

    @Autowired
    private ISolrRoomService solrRoomService;

    @ExplainAnnotation (explain = "家庭状况字典")
    @HTTP(alias = "getHomeSituationDic")
    public Response<GetHomeSituationDicResponseData> getHomeSituationDic (GetHomeSituationDicRequest request) {

        Response<GetHomeSituationDicResponseData> response = new  Response<GetHomeSituationDicResponseData>();
        GetHomeSituationDicResponseData data = new GetHomeSituationDicResponseData();
        List<HomeSituationDicDTO> list = Lists.newArrayList();
        try {
            List<Dictionary> dictionaryList = DictionaryClient.findDictionaryByGroupNameOrderBySortAsc("family_status");
            if (CollectionUtils.isNotEmpty(dictionaryList)) {
                for (Dictionary dictionary : dictionaryList) {
                    HomeSituationDicDTO homeSituation = new HomeSituationDicDTO(Integer.parseInt(dictionary.getDictKey()),dictionary.getDictValue());
                    list.add(homeSituation);
                }
            }

        } catch (TException e) {
            e.printStackTrace();
        }
        data.setList(list);
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;

    }



    @ExplainAnnotation (explain = "获取当前系统时间")
    @HTTP(alias = "getSysTime")
    public Response<GetSysTimeResponseData> getSysTime (GetSysTimeRequest request) {

        Response<GetSysTimeResponseData> response = new Response<GetSysTimeResponseData>();
        GetSysTimeResponseData data = new GetSysTimeResponseData();
        data.setSysTime(System.currentTimeMillis());
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
         return  response;
    }


    @ExplainAnnotation (explain = "获取机器人心跳检测频率值")
    @HTTP(alias = "getRobotHeartbeatNum")
    public Response<GetRobotHeartbeatNumResponseData> getRobotHeartbeatNum (GetRobotHeartbeatNumRequest request) {

         Response<GetRobotHeartbeatNumResponseData>  response = new  Response<GetRobotHeartbeatNumResponseData>();
        GetRobotHeartbeatNumResponseData data = new GetRobotHeartbeatNumResponseData();
        String num = null;
        try {
            num = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_ROBOT.getGroupName(),Constant.Dict_K_V_Enum.DICT_ROBOT.getDictKey());
            data.setHeartBeatNum(QDStringUtil.isNotEmpty(num)?Integer.parseInt(num):180);
        } catch (TException e) {
            e.printStackTrace();
            data.setHeartBeatNum(180);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return  response;
    }

    @ExplainAnnotation (explain = "通过关键字模糊匹配社区列表")
    @HTTP(alias = "getProjectListByKeyWord")
    public Response<GetProjectListByKeyWordResponseData> getProjectListByKeyWord(GetProjectListByKeyWordRequest request) {

        Response<GetProjectListByKeyWordResponseData> response = new  Response<GetProjectListByKeyWordResponseData>();
        GetProjectListByKeyWordResponseData data = new GetProjectListByKeyWordResponseData();
        try {
            ProjectNameLikeRequest projectNameLikeRequest = new ProjectNameLikeRequest();
            projectNameLikeRequest.setCount(request.getPageSize());
            projectNameLikeRequest.setName(request.getKeyWord());
            ProjectNameLikeResponse projectNameLikeResponse = projectService.nameLike(projectNameLikeRequest);
            checkAndContinue(projectNameLikeResponse);
            List<Project> projectList = projectNameLikeResponse.getList();
            List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Project> list = transforList(com.qding.api.call.app.qding.v1_3_0.struct.brick.Project.class,projectList);
            data.setList(list);
        } catch (ServiceException e) {
            return handleException(GetProjectListByKeyWordResponseData.class, e);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return  response;
    }


    @ExplainAnnotation (explain = "通过物业和千丁房屋关联ID批量获取房间信息")
    @HTTP(alias = "getRoomByCmsIds")
    public Response<GetRoomByCmsIdResponseData>  getRoomByCmsIds (GetRoomByCmsIdRequest request){

        Response<GetRoomByCmsIdResponseData> response = new  Response<GetRoomByCmsIdResponseData>();
        GetRoomByCmsIdResponseData data = new GetRoomByCmsIdResponseData();
        RoomReadRequest roomReadRequest = new RoomReadRequest();
        Set<String> cmsIdSet = new HashSet<>();
        cmsIdSet.addAll(request.getCsmIds());
        roomReadRequest.setCsmRoomIdSet(cmsIdSet);
        try {
            RoomResponse rpcResponse = solrRoomService.getsRoomList(roomReadRequest);
            checkAndContinue(rpcResponse);
            List<Room>  roomList = rpcResponse.getBd();
            List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Room> list = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(roomList)) {
               list = transforList(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class,roomList);
            }
            data.setTotalCount(Integer.parseInt(String.valueOf(rpcResponse.getTotal())));
            data.setList(list);
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
        } catch (ServiceException e) {
            return handleException(GetRoomByCmsIdResponseData.class, e);
        }
        return  response;
    }


}
