package com.qding.api.call.app.qding.v1_3_0;

import java.util.*;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.*;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.request.*;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data.*;
import com.qding.api.call.service.BrickService;
import com.qding.api.process.security.UserToken;
import com.qding.api.util.QDMemberRemote;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.struts.request.ProjectRequest;
import com.qding.brick.struts.request.RegionReadRequest;
import com.qding.brick.struts.response.ProjectReadResponse;
import com.qding.brick.struts.response.RegionReadResponse;
import com.qding.member.model.MemberRoom;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.response.memberroom.GetMemberRoomResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.constant.Constant;
import com.qding.api.ip.IPUtil;
import com.qding.api.ip.TaoBaoCity;
import com.qding.api.struct.Response;
import com.qding.api.util.ArraysUtil;
import com.qding.brick.domain.biz.AppInfo;
import com.qding.brick.pojo.biz.Building;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ProjectConnect;
import com.qding.brick.pojo.biz.Region;
import com.qding.brick.pojo.biz.RegionGroup;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.pojo.biz.RoomOwner;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.BuildingRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RegionRemote;
import com.qding.brick.remote.biz.RoomOwnerRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.sysconfig.rpc.service.AppHomeConfigRpcService;
import com.qding.thrift.model.dictionary.Dictionary;

/**
 * 基础数据 version 1.3.0
 * @author lichao
 *
 */
public class CallBrick extends Callable{

	@Autowired
	private BuildingRemote buildingRemoteService;
	
	@Autowired
	private ProjectReadRemote projectReadService;
	
	@Autowired
	private RegionRemote regionRemoteService;
	
	@Autowired
	private RoomReadRemote roomReadRemoteService;
	
	@Autowired
	private RoomOwnerRemote roomOwnerRemoteService;

	@Autowired
	private SkipModeFitting skipMode;

	@Autowired
	private AppConfigRemote appConfigRemoteService;

	@Autowired
	private IMemberRoomRPC memberRoomAPI;

	@Autowired
	private BrickService brickService;
	
    @Autowired
    private AppHomeConfigRpcService appHomeConfigRpcService;

	private static Logger logger = Logger.getLogger(CallBrick.class);

	private List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Dictonary> getDictionary(String groupName) {
		try {
			List<Dictionary> d = DictionaryClient.findDictionaryByGroupNameOrderBySortAsc(groupName);
			return transforList(com.qding.api.call.app.qding.v1_3_0.struct.brick.Dictonary.class, d);
		} catch (TException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	private List<HKPurposeDictonary> getHKPurposeDictonary(String groupName) {
		try {
			List<Dictionary> d = DictionaryClient.findDictionaryByGroupNameOrderBySortAsc(groupName);
			List<HKPurposeDictonary> purposeDictonaries = new ArrayList<>(); 
			for(Dictionary dic : d) {
				HKPurposeDictonary purposeDictonary = transfor(HKPurposeDictonary.class, dic);
				purposeDictonary.setReleaseNum(Constant.getReleaseNum(purposeDictonary.getKey()));
				purposeDictonaries.add(purposeDictonary);
			}
			return purposeDictonaries;
		} catch (TException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	private List<HKIndentityDictonary> getHKIndentityDictonary(String groupName) {
		try {
			List<Dictionary> d = DictionaryClient.findDictionaryByGroupNameOrderBySortAsc(groupName);
			List<HKIndentityDictonary> indentityDictonaries = new ArrayList<>(); 
			for(Dictionary dic : d) {
				HKIndentityDictonary indentityDictonary = transfor(HKIndentityDictonary.class, dic);
				indentityDictonary.setValidatyTimeKey(Constant.getValidatyTimeKey(indentityDictonary.getKey()));
				indentityDictonaries.add(indentityDictonary);
			}
			return indentityDictonaries;
		} catch (TException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}


	/**
	 * 获取app报事差评术语字典配置
	 * @param request
	 * @return
	 */
	@HTTP(alias="getEvaluationLabelDics")
	public Response<GetEvaluationLabelDicResponseData> getEvaluationLabelDics (GetEvaluationLabelDicRequest request) {

		Response<GetEvaluationLabelDicResponseData> response = new Response<GetEvaluationLabelDicResponseData>();
		GetEvaluationLabelDicResponseData data = new GetEvaluationLabelDicResponseData();
		List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Dictonary> dicList = getDictionary("evaluation_label");
		if (CollectionUtils.isEmpty(dicList)){
			dicList = Lists.newArrayList();
		}
		data.setEvaluationLabel(dicList);
		response.setCode(HttpStatus.OK.getStatusCode());
		response.setData(data);
		return response;
	}

	@HTTP(alias="getAppDictonary")
	@ExplainAnnotation(explain = "app配通用字典配置")
	public Response<GetDictonaryResponseData> getAppDictonary(GetDictonaryRequest request) {
		Response<GetDictonaryResponseData> response = new Response<GetDictonaryResponseData>();
		try {

			GetDictonaryResponseData data = new GetDictonaryResponseData();

			data.setGender(getDictionary("gender"));
			data.setMobileOSType(getDictionary("mobile_os_type"));
			data.setRegSourceType(getDictionary("reg_source_type"));
			data.setHkPurepose(getHKPurposeDictonary("hk_purpose"));
			data.setHkIndentity(getHKIndentityDictonary("hk_indentity"));
			data.setPastReleaseTime(getDictionary("past_release_time"));
			data.setValidityTime(getDictionary("validity_time"));
			data.setSmsAction(getDictionary("sms_action"));
			data.setPaymentType(getDictionary("pay_type"));
			data.setOrderStatus(getDictionary("order_status"));
			data.setPaymentStatus(getDictionary("pay_status"));
			data.setOrderIsPayOnline(getDictionary("order_is_pay_online"));
			data.setWatchRole(getDictionary("watch_role"));
			data.setEvaluationLabel(getDictionary("evaluation_label"));
			String collectLogSwitch = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_SWITCH_9.getGroupName(),Constant.Dict_K_V_Enum.DICT_SWITCH_9.getDictKey());
			data.setCollectLogSwitch(QDStringUtil.isNotEmpty(collectLogSwitch) && "close".equals(collectLogSwitch.toLowerCase())?false : true);
			
			String newSellIndexURL = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_NEWSELL_INDEX_URL.getGroupName(),Constant.Dict_K_V_Enum.DICT_NEWSELL_INDEX_URL.getDictKey());
			data.setNewSellIndexURL(newSellIndexURL);
			
			try {
				Project project = brickService.getProjectById(Long.parseLong(request.getAppUser().getProjectId()));
				data.setEntranceCardTime(project.getGateCardTime());
				data.setAppIconIndex(appHomeConfigRpcService.getAppIconIndex(request.getAppDevice().getQdVersion()));
			} catch (Exception ex) {
				logger.error("class : callBrick,method:getAppDictonary get EntranceCardTime is error :",ex);
			}
			try {
				data.setAppIconIndex(appHomeConfigRpcService.getAppIconIndex(request.getAppDevice().getQdVersion()));
			} catch (Exception ex) {
				logger.error("class : callBrick,method:getAppDictonary get getAppIconIndex is error :",ex);
			}
			response.setData(data);
			
		} catch (Exception e) {
			
			return handleException(GetDictonaryResponseData.class, e);
			
		}

		return response;
	}
	
	/**
	 * 获取城市列表		
	 * @param request
	 * @return
	 */
	@HTTP(alias="getCityList")
	public Response<GetCityListResponseData> getCityList(GetCityListRequest request, UserToken userToken) {
		
		try {
			Response<GetCityListResponseData> response = new Response<GetCityListResponseData>();
			
			Integer brickSouceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());

			RegionReadRequest regionReadRequest = transfor(RegionReadRequest.class,request);

			regionReadRequest.setType(brickSouceType);

			String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());

			String memberId = "";
			RegionReadResponse regionReadResponse  = null;
			if(QDStringUtil.isNotNull(userToken)){ //通过token获取会员ID
				memberId = userToken.getMemberId();
			}
			if (Integer.parseInt(initVersion)>=280000){
				try{
					if(QDStringUtil.isNotEmpty(memberId)){
						GetMemberRoomResponse  memberRoomResponse = memberRoomAPI.listValidByMember(memberId);
						checkAndContinue(memberRoomResponse);
						Set<Long> bindRoomPIdSet = new HashSet<>();
						List<com.qding.member.model.MemberRoom> lsMemberRoom = memberRoomResponse.getList();
						for (MemberRoom memberRoom : lsMemberRoom) {
							bindRoomPIdSet.add(Long.parseLong(memberRoom.getProjectId()));
						}
						regionReadRequest.setNeedIncludeProjectIds(bindRoomPIdSet);
					}
					regionReadResponse =regionRemoteService.getRegionListByTypeV2(regionReadRequest);
				}catch (Exception ex) {
					logger.error("获取城市列表,(v-2.8)：",ex);
				}

			} else {
				regionReadResponse  = regionRemoteService.getRegionListByType(regionReadRequest);
			}
			checkAndContinue(regionReadResponse);

			List<Region> regionList = regionReadResponse.getRegionList();

			List<Entry<String, List<Region>>> groupByCodeAsList = ArraysUtil.mergerListsAsList(regionList, "code");
			
			List<CitySpell> list = new ArrayList<>();
			
			for(Entry<String, List<Region>> entry : groupByCodeAsList) {
				
				List<City> cityList = transforList(City.class, entry.getValue());
				
				String py = entry.getKey();
				
				list.add(new CitySpell(py, cityList));
				
			}

			GetCityListResponseData data = new GetCityListResponseData();

			if (QDStringUtil.isNotNull(regionReadResponse.getLocateRegion())){

				City locateCity = transfor(City.class,regionReadResponse.getLocateRegion());

				data.setLocateCity(locateCity);
			}

			data.setList(list);

			response.setData(data);
			
			return response;

		} catch (Exception e) {
			
			return handleException(GetCityListResponseData.class, e);
		}
		
	}
	
	
	/**
	 * 根据城市id获取社区列表					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getProjects")
	@Deprecated
	public Response<GetProjectByCityIdResponseData> getProjects(GetProjectByCityIdRequest request) {
		
		try {
			Response<GetProjectByCityIdResponseData> response = new Response<GetProjectByCityIdResponseData>();

			List<ProjectSpell> list = getProjectsGroupBySpell(request);
			
			GetProjectByCityIdResponseData data = new GetProjectByCityIdResponseData();

			data.setList(list);
			
			response.setData(data);
			
			return response;
			
		} catch (Exception e) {
			return handleException(GetProjectByCityIdResponseData.class, e);
		}
	}

	public List<ProjectSpell> getProjectsGroupBySpell(GetProjectByCityIdRequest request) throws ServiceException {

		String qdPlatform = request.getAppDevice().getQdPlatform();
		if (QDStringUtil.isNotEmpty(qdPlatform) && QDStringUtil.isNotNull(qdPlatform)) {
			qdPlatform = qdPlatform.toLowerCase();
		}

		String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
		List<ProjectSpell> list = new ArrayList<>();
		Integer brickSouceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
		ProjectRequest projectRequest = new ProjectRequest();
		if (Integer.parseInt(initVersion)>=250000 && Integer.parseInt(initVersion)<280000 &&("android".equalsIgnoreCase(qdPlatform)||"ios".equalsIgnoreCase(qdPlatform))){
			projectRequest.setIsIncludeVirtual(1);
		}
		projectRequest.setRegionId(Long.parseLong(request.getCityId()));
		projectRequest.setType(brickSouceType);
		if (QDStringUtil.isNotEmpty(request.getMemberId()) && Integer.parseInt(initVersion)>=280000 &&("android".equalsIgnoreCase(qdPlatform)||"ios".equalsIgnoreCase(qdPlatform))){
			try{
				GetMemberRoomResponse memberRoomResponse = memberRoomAPI.listValidByMember(request.getMemberId());
				checkAndContinue(memberRoomResponse);
				Set<Long> bindRoomPIdSet = new HashSet<>();
				List<com.qding.member.model.MemberRoom> lsMemberRoom = memberRoomResponse.getList();
				for (MemberRoom memberRoom : lsMemberRoom) {
					bindRoomPIdSet.add(Long.parseLong(memberRoom.getProjectId()));
				}
				projectRequest.setNeedIncludeProjectIds(bindRoomPIdSet);
				projectRequest.setRegionId(Long.parseLong(request.getCityId()));
			}catch (Exception ex) {
				logger.error("根据城市id获取社区列表，普通模式异常(v-2.8)：",ex);
			}
		}

		ProjectReadResponse projectReadResponse = projectReadService.getProjectsByRequest(projectRequest);
		if (HttpStatus.OK.getStatusCode() == projectReadResponse.getReturnInfo().getCode()) {
			List<Project> projects = projectReadResponse.getProjects();
			if (CollectionUtils.isNotEmpty(projects)) {
				Set<Long> projectIdSet = new HashSet<>();
				for (Project project : projects) {
					projectIdSet.add(project.getId());
				}
				Map<Long, List<RegionGroup>> groupMap = projectReadService.getGroupListByProjectId(projectIdSet);
				if (QDStringUtil.isNotNull(groupMap)){
					for (Project project : projects) {
						if (groupMap.containsKey(project.getId())){
							project.setRegionGroup(groupMap.get(project.getId()));
						}
					}
				}
			}

			//拼音分组
			List<Entry<String, List<Project>>> groupByCodeList = ArraysUtil.mergerListsAsList(projects, "code");

			for(Entry<String, List<Project>> entry : groupByCodeList) {
				List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Project> projectList =
						transforList(com.qding.api.call.app.qding.v1_3_0.struct.brick.Project.class, entry.getValue());
				String py = entry.getKey();
				list.add(new ProjectSpell(py, projectList));
			}
		}

		return list;
	}

	/**
	 * 根据社区id获取社区信息					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getProject")
	public Response<GetProjectByIdResponseData> getProject(GetProjectByIdRequest request) {
		
		try {
			Response<GetProjectByIdResponseData> response = new Response<GetProjectByIdResponseData>();
			
			Project project = projectReadService.get(Long.parseLong(request.getProjectId()));
			
			if(project == null) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
			}

			com.qding.api.call.app.qding.v1_3_0.struct.brick.Project p = 
					transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Project.class, project);
			
			List<RegionGroup> groups = projectReadService.getRegionListByProjectId(project.getId());
			
			p.setGroups(transforList(Group.class, groups));
			
			GetProjectByIdResponseData data = new GetProjectByIdResponseData();
			
			data.setEntity(p);
			
			response.setData(data);
			
			return response;
		} catch (Exception e) {
			
			return handleException(GetProjectByIdResponseData.class, e);

		}
	}
	
	/**
	 * 根据社区id和组团code获取楼栋列表					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getBuildings")
	public Response<GetBuildingByProjectIdAndCodeResponseData> getBuildings(GetBuildingByProjectIdAndCodeRequest request) {
		
		try {
			Response<GetBuildingByProjectIdAndCodeResponseData> response = new Response<GetBuildingByProjectIdAndCodeResponseData>();
			
			String groupCode = request.getGroupCode();
			
			Long projectId = Long.parseLong(request.getProjectId());
			
			List<Building> bulidList;
			
			if(QDStringUtil.isBlank(groupCode)) {
			
				bulidList = buildingRemoteService.getBulidList(projectId, null);
			} else {
			
				bulidList = buildingRemoteService.getBulidList(projectId, Integer.parseInt(request.getGroupCode()));
			}
			if(bulidList == null) {
				
				bulidList = new ArrayList<>();
			}

			List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Building> bs = new ArrayList<>();
			
			for(Building b : bulidList) {
				
				bs.add(getBuilding(b));
			
			}
			
			GetBuildingByProjectIdAndCodeResponseData data = new GetBuildingByProjectIdAndCodeResponseData();
			
			data.setList(bs);
			
			response.setData(data);
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetBuildingByProjectIdAndCodeResponseData.class, e);

		}
	}
	
	/**
	 * 根据楼栋id获取楼栋信息					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getBuilding")
	public Response<GetBuildingByIdResponseData> getBuilding(GetBuildingByIdRequest request) {
		
		try {
			Response<GetBuildingByIdResponseData> response = new Response<GetBuildingByIdResponseData>();
			
			Building building = buildingRemoteService.get(Long.parseLong(request.getBuildingId()));
			
			if(building == null) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "楼栋不存在");
			}

			com.qding.api.call.app.qding.v1_3_0.struct.brick.Building b = getBuilding(building);
			
			GetBuildingByIdResponseData data = new GetBuildingByIdResponseData();
			
			data.setEntity(b);
			
			response.setData(data);
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetBuildingByIdResponseData.class, e);

		}
	}
	
	private com.qding.api.call.app.qding.v1_3_0.struct.brick.Building getBuilding(Building building) throws ServiceException {
		
		Project project = projectReadService.get(building.getProjectId());
		
		if(project == null) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
		}

		com.qding.api.call.app.qding.v1_3_0.struct.brick.Building b = 
				transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Building.class, building);
		
		b.setCityId(String.valueOf(project.getRegionId()));
		
		b.setCityName(project.getRegionName());
		
		b.setGroupCode(building.getRegionCode() == null ? null : String.valueOf(building.getRegionCode()));
		
		b.setGroupName(building.getGroupName());
		
		return b;
	}

	/**
	 * 根据楼栋id获取房间列表					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getRooms")
	@Deprecated
	public Response<GetRoomByBuildingIdResponseData> getRooms(GetRoomByBuildingIdRequest request) {

		Response<GetRoomByBuildingIdResponseData> response = new Response<GetRoomByBuildingIdResponseData>();

//		List<Room> rooms = roomReadRemoteService.getRoomByBuildingId(Long.parseLong(request.getBuildingId()));

		List<Room> rooms = roomReadRemoteService.getRoomsMobileByBuildingId(Long.parseLong(request.getBuildingId()));

		if(rooms == null) {

			rooms = new ArrayList<>();
		}

//		List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Room> rs =
//				transforList(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, rooms);

		List<com.qding.api.call.app.qding.v1_3_0.struct.brick.Room> rs = new ArrayList<>();

		//TODO FIX BUG 下一版本删除
		for(Room room : rooms) {

			com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r =
					transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);

			List<String> ownerMobile = new ArrayList<>();

			String ownerMobileString = room.getMobile();

			if(QDStringUtil.isNotEmpty(ownerMobileString)) {

				String[] split = ownerMobileString.split("/");

				for(String m : split) {
					ownerMobile.add(m);
				}
			}

			r.setMobiles(ownerMobile.toArray(new String[]{}));

			rs.add(r);

		}

		GetRoomByBuildingIdResponseData data = new GetRoomByBuildingIdResponseData();

		data.setList(rs);

		response.setData(data);

		return response;
	}


	/**
	 * 根据房间id获取房间信息					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getRoom")
	public Response<GetRoomByIdResponseData> getRoom(GetRoomByIdRequest request) {
		
		try {
			Response<GetRoomByIdResponseData> response = new Response<GetRoomByIdResponseData>();
			
			Room room = roomReadRemoteService.get(Long.parseLong(request.getRoomId()));
			
			if(room == null) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
			}
			
			com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r = 
					transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);
			
			GetRoomByIdResponseData data = new GetRoomByIdResponseData();
			
			data.setEntity(r);
			
			response.setData(data);
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetRoomByIdResponseData.class, e);
		}
	}
	
	/**
	 * 获取项目的地址列表
	 * @param request
	 * @return
	 */
	@HTTP(alias="getProjectConcats")
	public Response<GetProjectConcatsResponseData> getProjectConcats(GetProjectConcatsRequest request) {
		
		Response<GetProjectConcatsResponseData> response = new Response<GetProjectConcatsResponseData>();
		
		List<ProjectConnect> concats = projectReadService.getProjectConnectByProjectId(Long.parseLong(request.getProjectId()));
		
		List<ProjectConcat> list = transforList(ProjectConcat.class, concats);
		
		GetProjectConcatsResponseData data = new GetProjectConcatsResponseData();
		
		data.setList(list);
		
		response.setData(data);
		
		return response;
	}


	
	/**
	 * APP版本验证
	 * @param request
	 * @return
	 */
	@HTTP(alias="checkAppVersion",checkVersion = false)
	public Response<AppVersionCheckResponseData> checkAppVersion(AppVersionCheckRequest request) {
		Response<AppVersionCheckResponseData> response = new Response<>();
		
		int type = 0;
		//1 IOS
		//2 android
		String qdPlatform = request.getAppDevice().getQdPlatform();

		if (QDStringUtil.isNotEmpty(qdPlatform) && QDStringUtil.isNotNull(qdPlatform)) {
			qdPlatform = qdPlatform.toLowerCase();
		}
		
		if("android".equalsIgnoreCase(qdPlatform)) {
			type = 2;
		
		} else if("ios".equalsIgnoreCase(qdPlatform)) {
			type = 1;

		} else if ("pad".equalsIgnoreCase(qdPlatform)) {
			type = 3;
		}

		AppVersionCheckResponseData data = new AppVersionCheckResponseData();
		
		AppInfo appInfo = appConfigRemoteService.getAppInfo(type, request.getAppDevice().getQdVersion());

		AppVersionCheck appVersionCheck = transfor(AppVersionCheck.class, appInfo);
		
		data.setEntity(appVersionCheck);
		
		response.setData(data);
		
		return response;
	}
	
	
	/**`
	 * 根据IP定位
	 * @param request
	 * @param httpServletRequest
	 * @return
	 */
	@HTTP(alias="location")
	public Response<LocationResponseData> location(LocationRequest request, HttpServletRequest httpServletRequest) {
		
		String ip = IPUtil.getIpAddress(httpServletRequest);
		
		TaoBaoCity city = IPUtil.getIpCity(ip);
		
		Location location = new Location(city.getCountry(), city.getCity(), city.getProvince(), ip);
		
		LocationResponseData data = new LocationResponseData(location);
		
		Response<LocationResponseData> response = new Response<LocationResponseData>();
		
		response.setData(data);
		
		return response;
	}
	
	/**
	 * 获取房屋业主手机号
	 * @param request
	 * @return
	 */
	@HTTP(alias="getRoomOwnerMobiles")
	public Response<GetRoomOwnerMobilesResponseData> getRoomOwnerMobiles(GetRoomOwnerMobilesRequest request) {
		
		/**
		 * 房间对应的手机号
		 */
		List<RoomOwner> roomOwners = roomOwnerRemoteService.getOnwerInfoByRoomId(Long.parseLong(request.getRoomId()));
		
		if(roomOwners == null) {
			
			roomOwners = new ArrayList<>();
		}

		List<String> mobiles = new ArrayList<>();
		
		for(RoomOwner ro : roomOwners) {
			
			String mobile = ro.getMobile();
			
			if(mobile != null) {
				
				String[] split = mobile.split("/");
			
				for(String m : split) {
					mobiles.add(m);
				}
			}
		}
			
		String[] ownerMobiles = mobiles.toArray(new String[]{});
		
		GetRoomOwnerMobilesResponseData data = new GetRoomOwnerMobilesResponseData(
				ownerMobiles);
		
		Response<GetRoomOwnerMobilesResponseData> response = new Response<>();
		
		response.setData(data);
		
		return response;
	}
}
