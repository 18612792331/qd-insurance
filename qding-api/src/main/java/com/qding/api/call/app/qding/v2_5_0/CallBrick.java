 package com.qding.api.call.app.qding.v2_5_0;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.qding.api.call.app.qding.v2_5_0.struct.brick.request.*;
import com.qding.api.call.app.qding.v2_5_0.struct.brick.response.data.*;
import com.qding.api.constant.Constant;
import com.qding.brick.struts.request.GetProjectConnectRequest;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.response.memberroom.GetMemberRoomResponse;
import com.qding.sysconfig.domain.DomainConfig;
import com.qding.sysconfig.rpc.service.DomainConfigRpcService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.City;
import com.qding.api.call.app.qding.v1_3_1.struct.brick.request.AreaDicRequest;
import com.qding.api.call.app.qding.v1_3_1.struct.brick.response.data.AreaDicResponseData;
import com.qding.api.struct.Response;
import com.qding.api.util.QDMemberRemote;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.District;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ProjectConnect;
import com.qding.brick.pojo.biz.Province;
import com.qding.brick.pojo.biz.Region;
import com.qding.brick.pojo.biz.RegionGroup;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RegionRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.brick.struts.request.ProjectRequest;
import com.qding.brick.struts.request.RegionRequest;
import com.qding.brick.struts.request.RoomRequest;
import com.qding.brick.struts.response.AreaResponse;
import com.qding.brick.struts.response.ProjectReadResponse;
import com.qding.brick.struts.response.RegionResponse;
import com.qding.brick.struts.response.RoomReadResponse;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberRoom;
import com.qding.sysconfig.domain.AppPatchConfig;
import com.qding.sysconfig.rpc.service.SysConfigRpcService;

/**
 * Created by qd on 2016/8/22.
 */
public class CallBrick extends  com.qding.api.call.app.qding.v2_3_0.CallBrick {

    @Autowired
    private AppConfigRemote appConfigRemoteService;

    @Autowired
    private RegionRemote regionRemoteService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private SysConfigRpcService sysConfigRpcService;

    @Autowired
    private SkipModeFitting skipMode;
    
    @Autowired
    private RoomReadRemote roomReadRemoteService;
    
    @Autowired
    private IMemberRoomRPC memberRoomAPI;

    @Autowired
    private DomainConfigRpcService domainConfigRpcService;



    @HTTP(alias = "getProvinceList")
    @ExplainAnnotation(explain = "获取省份列表")
    public Response<GetProvinceListResponseData> getProvinceList (GetProvinceListRequest request) {

        Response<GetProvinceListResponseData> response = new Response<GetProvinceListResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetProvinceListResponseData data = new GetProvinceListResponseData();
        try {
            RegionResponse regionResponse =  regionRemoteService.getProvinceList();
            checkAndContinue(regionResponse);
            List<Province> provinceList = regionResponse.getProvinceList();
            List<com.qding.api.call.app.qding.v2_5_0.struct.brick.Province> list = transforList(com.qding.api.call.app.qding.v2_5_0.struct.brick.Province.class,provinceList);
            data.setList(list);
            response.setData(data);
        } catch (ServiceException e) {
            return handleException(GetProvinceListResponseData.class, e);
        }

        return response;
    }



    @HTTP(alias = "getRegionListByProvinceId")
    @ExplainAnnotation(explain = "通过省ID获取城市信息列表")
    public Response<GetRegionListByProvinceIdResponseData> getRegionListByProvinceId (GetRegionListByProvinceIdRequest request) {


        Response<GetRegionListByProvinceIdResponseData> response = new Response<GetRegionListByProvinceIdResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetRegionListByProvinceIdResponseData data = new GetRegionListByProvinceIdResponseData();
        try {
            RegionRequest regionRequest = transfor(RegionRequest.class,request);
            RegionResponse regionResponse =  regionRemoteService.getRegionListByProvinceId(regionRequest);
            checkAndContinue(regionResponse);
            List<Region> regionList = regionResponse.getRegionList();
            List<City> list = transforList(City.class,regionList);
            data.setList(list);
            response.setData(data);
        } catch (ServiceException e) {
            return handleException(GetRegionListByProvinceIdResponseData.class, e);
        }
        return response;

    }


    @HTTP(alias = "getDistrictListByRegionId")
    @ExplainAnnotation(explain = "通过市ID获取区县信息列表")
    public Response<GetDistrictListByRegionIdResponseData> getDistrictListByRegionId (GetDistrictListByRegionIdRequest request) {


        Response<GetDistrictListByRegionIdResponseData> response = new Response<GetDistrictListByRegionIdResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetDistrictListByRegionIdResponseData data = new GetDistrictListByRegionIdResponseData();
        try {
            RegionRequest regionRequest = transfor(RegionRequest.class,request);
            RegionResponse regionResponse =  regionRemoteService.getDistirctListByRegionId(regionRequest);
            checkAndContinue(regionResponse);
            List<District> regionList = regionResponse.getDistrictList();
            List<com.qding.api.call.app.qding.v2_5_0.struct.brick.District> list = transforList(com.qding.api.call.app.qding.v2_5_0.struct.brick.District.class,regionList);
            data.setList(list);
            response.setData(data);
        } catch (ServiceException e) {
            return handleException(GetDistrictListByRegionIdResponseData.class, e);
        }
        return response;

    }



    @HTTP(alias = "getProjectListByDistrictId")
    @ExplainAnnotation(explain = "获取社区列表")
    @Deprecated
    public Response<GetProjectListByDistrictIdResponseData> getProjectListByDistrictId (GetProjectListByDistrictIdRequest request) {
        Response<GetProjectListByDistrictIdResponseData> response = new Response<GetProjectListByDistrictIdResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetProjectListByDistrictIdResponseData data = new GetProjectListByDistrictIdResponseData();
        try {
            ProjectRequest regionRequest = transfor(ProjectRequest.class,request);
            ProjectReadResponse projectReadResponse =  projectReadService.getProjectsByRequest(regionRequest);
            checkAndContinue(projectReadResponse);
            List<Project> projectList = projectReadResponse.getProjects();
            List<com.qding.api.call.app.qding.v2_5_0.struct.brick.Project> list =
                    transforList(com.qding.api.call.app.qding.v2_5_0.struct.brick.Project.class, projectList);
            data.setList(list);
            response.setData(data);
        } catch (ServiceException e) {
            return handleException(GetProjectListByDistrictIdResponseData.class, e);
        }
        return response;

    }

    @HTTP(alias="getAreaDic")
    @ExplainAnnotation(explain = "获取省市区县字典")

    public Response<AreaDicResponseData> getAreaDic(AreaDicRequest request){

        Response<AreaDicResponseData> response = new Response<AreaDicResponseData>();
        AreaDicResponseData data = new AreaDicResponseData();

        try {
            String key = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_APPCACHE_3.getGroupName(),Constant.Dict_K_V_Enum.DICT_APPCACHE_3.getDictKey());
            if (QDStringUtil.isNotEmpty(request.getCacheKey()) && key.equals(request.getCacheKey())){
                data.setMessage("");
                response.setData(data);
                response.setCode(HttpStatus.NOT_MODIFIED.getStatusCode());
            } else {
                AreaResponse areaResponse = appConfigRemoteService.getAllAreaInfo();
                checkAndContinue(areaResponse);
                String areaJson = areaResponse.getAreaInfo();
                data.setAreaJsonStr(areaJson);
                data.setCacheKey( key);
                response.setCode(HttpStatus.OK.getStatusCode());
                response.setData(data);
            }

        } catch (Exception e) {
            return handleException(AreaDicResponseData.class, e);
        }

        return response;
    }



    @HTTP(alias="getServicePackInfo")
    @ExplainAnnotation(explain = "获取android补丁包")
    public Response<GetServicePackResponse> getServicePackInfo(GetServicePackRequest request) {

        Response<GetServicePackResponse> response = new  Response<GetServicePackResponse>();
        GetServicePackResponse data = new GetServicePackResponse();
        String platForm = request.getAppDevice().getQdPlatform().toLowerCase();

        if(("ios".equals(platForm) && request.getAppType()==1) ||
                ("android".equals(platForm) && request.getAppType()==2)){

            response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            data.setMessage("当前设备类型与请求参数类型不匹配!");
            response.setData(data);
            return response;
        }

        try {
            Integer versionCode = Integer.parseInt(skipMode.initVersion(request.getVersionCode()));
            com.qding.sysconfig.rpc.response.GetServicePackResponse servicePackResponse = sysConfigRpcService.getServicePack(versionCode,request.getAppType());
            checkAndContinue(servicePackResponse);
            AppPatchConfig appPatchConfig = servicePackResponse.getAppPatchConfig();
            if (QDStringUtil.isNotNull(appPatchConfig)){
                data.setPatchCode(appPatchConfig.getPatchCode());
                data.setVersionCode(request.getVersionCode());
                data.setPatchUrl(appPatchConfig.getPatchUrl());
            }

            if(request.getAppType()==1){ //android
                String switchV = "";
                try {
                    switchV = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_SWITCH_6.getGroupName(),Constant.Dict_K_V_Enum.DICT_SWITCH_6.getDictKey());
                    data.setIsClearForAndroid("open".equals(switchV)?1:0);
                } catch (TException e) {
                    e.printStackTrace();
                }

            }
            if(request.getAppType() == 2 && QDStringUtil.isNotNull(appPatchConfig) && QDStringUtil.isNotNull(appPatchConfig.getVersionCode())){//ios
                data.setIsHotfixOpenForIos(1);
            }
        } catch (ServiceException e) {
            return handleException(GetServicePackResponse.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }


    @HTTP(alias="getGroupAddress")
    @ExplainAnnotation(explain = "获取组团地址列表",desc = "2.8新增")
    public Response<GetGroupAddressResponseData> getGroupAddress(GetGroupAddressRequest request){
        Response<GetGroupAddressResponseData> response = new Response<GetGroupAddressResponseData>();
        try{
        	 GetGroupAddressResponseData data = new GetGroupAddressResponseData();
             List<RegionGroup> listGroup=projectReadService.getRegionListByProjectId(Long.parseLong(request.getProjectId()));
             List<com.qding.api.call.app.qding.v2_5_0.struct.brick.GroupAddress> list =
                     transforList(com.qding.api.call.app.qding.v2_5_0.struct.brick.GroupAddress.class, listGroup);
             data.setList(list);
             response.setCode(HttpStatus.OK.getStatusCode());
             response.setData(data);
             return response;
        }catch(Exception ex){
        	 return handleException(GetGroupAddressResponseData.class, ex);
        }
        
    }
    
    @HTTP(alias="getRoomBygroupName")
    @ExplainAnnotation(explain = "根据组团获取房间地址列表",desc = "2.8新增")
    public Response<GetRoomAddressResponseData> getRoomByGroupName(GetRoomAddressRequest request){
        Response<GetRoomAddressResponseData> response = new Response<GetRoomAddressResponseData>();
        try{
        	GetRoomAddressResponseData data = new GetRoomAddressResponseData();
            GetMemberRoomResponse memberRoomResponse =memberRoomAPI.listVaild(request.getMemeberId(), request.getProjectId());
            checkAndContinue(memberRoomResponse);
        	List<MemberRoom> list=memberRoomResponse.getList();
        	if(list!=null && !list.isEmpty()){
        		RoomRequest requestbrick = new RoomRequest();
        		Set<Long> ids = new HashSet<Long>();
        		for(MemberRoom room:list){
        			ids.add(Long.parseLong(room.getRoomId()));
        		}
        		Set<String> groupNames =new HashSet<String>();
        		groupNames.add(request.getGroupName());
        		requestbrick.setIdSet(ids);
        		requestbrick.setGroupNameSet(groupNames);
        		requestbrick.setPage(1);
        		requestbrick.setSize(list.size());
        		RoomReadResponse roomResponse=roomReadRemoteService.getRoomsByRequest(requestbrick);
        		checkAndContinue(roomResponse);
        		List<com.qding.api.call.app.qding.v2_5_0.struct.brick.RoomAddress> listroom =
        				transforList(com.qding.api.call.app.qding.v2_5_0.struct.brick.RoomAddress.class, roomResponse.getRooms());
        		data.setList(listroom);
        	}
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;
        }catch(Exception ex){
        	 return handleException(GetRoomAddressResponseData.class, ex);
        }
        
    }
    

    @HTTP(alias="getProjectConnects")
    @ExplainAnnotation(explain = "获取多客服中心地址列表（物业地址)",desc = "2.8新增")
    public Response<GetProjectConnectsResponseData> getProjectConnects(GetProjectConnectsRequest request){
        Response<GetProjectConnectsResponseData> response = new Response<GetProjectConnectsResponseData>();
        GetProjectConnectsResponseData data = new GetProjectConnectsResponseData();
        Long projectId=Long.parseLong(request.getAppUser().getProjectId());
        try{
            GetProjectConnectRequest projectConnectRequest = new GetProjectConnectRequest();
            projectConnectRequest.setProjectId(projectId);
            projectConnectRequest.setExcludeConnectTypeSet(Sets.newHashSet(5));
        	List<ProjectConnect> projectConnects = projectReadService.getProjectConnectByRequest (projectConnectRequest);
            List<com.qding.api.call.app.qding.v2_5_0.struct.brick.ProjectConnect> list=
            		new ArrayList<com.qding.api.call.app.qding.v2_5_0.struct.brick.ProjectConnect>();
            if(projectConnects!=null && projectConnects.size()>0){
            	for(ProjectConnect conn:projectConnects){
                	com.qding.api.call.app.qding.v2_5_0.struct.brick.ProjectConnect apicon=new com.qding.api.call.app.qding.v2_5_0.struct.brick.ProjectConnect();
                	apicon.setId(String.valueOf(conn.getId()));
                	apicon.setAddress(conn.getAddress());
                	list.add(apicon);
                }
            }
            data.setList(list);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
        }catch(Exception ex){
       	 	return handleException(GetProjectConnectsResponseData.class, ex);
        }
        return response;
    }




    @HTTP(alias="getDomainList")
    @ExplainAnnotation(explain = "域名白名单列表",desc = "2.8新增")
    public Response<GetUrlWhiteListResponseData> getDomainList (GetUrlWhiteListRequest request) {

        Response<GetUrlWhiteListResponseData> response = new  Response<GetUrlWhiteListResponseData>();
        GetUrlWhiteListResponseData  data = new GetUrlWhiteListResponseData();
        List<DomainConfig> list = domainConfigRpcService.getDomainList();
        Set<String> domainSet = new HashSet<>();
        if(CollectionUtils.isNotEmpty(list)){
            for (DomainConfig domainConfig : list) {
                domainSet.add(domainConfig.getDomain().toLowerCase());
                if(QDStringUtil.isNotEmpty(domainConfig.getSubDomain())){
                    domainSet.add(domainConfig.getSubDomain().toLowerCase());
                }
            }

        }
        data.setList(Lists.<String>newArrayList(domainSet));
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }

}
