package com.qding.api.call.app.qding.v3_0_0;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.qding.api.util.*;
import com.qding.member.rpc.IMemberHkRoomRelationRPC;
import com.qding.member.rpc.response.relation.HkRoomRelationResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.PropertyChargesOrder;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.FrequentlyAskedQuestionDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.ProjectService;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.ProjectServiceList;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.RobotServiceItem;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request.FixSegRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request.GetCustomServiceSkipRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request.GetHKIndexRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request.GetReplaceCustListRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request.GetReplaceFeeRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request.GetReportServiceItemsRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request.GetRobotIndexRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request.ReplaceFeeCreateOrderRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data.FeeCreateOrderResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data.FixSegResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data.GetCustomServiceSkipResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data.GetHKIndexResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data.GetReplaceCustListResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data.GetReplaceFeeResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data.GetReportServiceItemsResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data.GetRobotIndexResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.project.request.HouseDeliverRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.project.request.MyBrokerRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.project.request.MyHouseKeeperRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.project.response.data.HouseDeliverResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.project.response.data.MyBrokerResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.project.response.data.MyHouseKeeperResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.user.CustomerSkipDTO;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.api.verifycode.store.StoreDevice;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.brick.struts.request.GetServiceItemListRequest;
import com.qding.brick.struts.response.GetServiceItemListResponse;
import com.qding.brick.struts.response.ProjectBrokerResponse;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.knowledge.domain.QuestionConf;
import com.qding.knowledge.service.IKnowledgeRemote;
import com.qding.knowledge.struct.request.FAQRequest;
import com.qding.knowledge.struct.response.FAQResponse;
import com.qding.knowledge.struct.response.FixSegResponse;
import com.qding.member.model.MemberHkRoomRelation;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.profee.domain.PropertyFeeOrder;
import com.qding.profee.rpc.response.fee.FeeCreateOrderResponse;
import com.qding.profee.rpc.response.fee.GetReplaceCustListResponse;
import com.qding.profee.rpc.response.fee.GetReplaceFeeResponse;
import com.qding.profee.rpc.service.IFeeRpcService;

/**
 * Created by qd on 2017/2/24.
 */
public class CallHouseKeeper extends com.qding.api.call.app.qding.v2_5_0.CallHouseKeeper {

    @Autowired
    private AppConfigRemote appConfigRemote;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private IKnowledgeRemote knowledgeRemote;

    @Autowired
    private IFeeRpcService feeService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private RoomReadRemote roomReadRemoteService;

    @Autowired
    private IMemberHkRoomRelationRPC hkRoomReletionService;
    
    @Autowired
    private IProfileService profileAPI;



    private static final Logger logger = Logger.getLogger(CallHouseKeeper.class);
    
    @HTTP(alias = "myHouseKeeper", isNeadProject = true, isNeedSign = false)
    @ExplainAnnotation(explain = "我的管家")
    public Response<MyHouseKeeperResponseData> myHouseKeeper(MyHouseKeeperRequest request, 
    		UserToken userToken) {
    	Response<MyHouseKeeperResponseData> response = new Response<MyHouseKeeperResponseData>();
    	response.setCode(HttpStatus.OK.getStatusCode());
    	MyHouseKeeperResponseData data=new MyHouseKeeperResponseData();
    	try{
    		String projectId = request.getAppUser().getProjectId();
            HkRoomRelationResponse hkRoomRelationResponse =  hkRoomReletionService.getEarliestRoomHkInfo(
    				userToken.getMemberId(),Long.parseLong(projectId));
            checkAndContinue(hkRoomRelationResponse);
    		if(hkRoomRelationResponse.getHkRoomRelation()!=null){
    			GetMemberRequest memberRequest = new GetMemberRequest();
    			memberRequest.setMemberId(hkRoomRelationResponse.getHkRoomRelation().getMemberId());
    			GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);
    			checkAndContinue(memberResponse);
    			MemberInfo memberInfo = memberResponse.getMemberInfo();
    			data.setName(memberInfo.getName());
    			data.setHeadImage(memberInfo.getHeadImg());
    			data.setMobile(memberInfo.getMobile());
    			data.setStatus("200");
    		}else{
    			data.setStatus("201");
    		}
    	}catch(Exception ex){
    		logger.error("我的管家",ex);
    		data.setStatus("202");
    		response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
    	}
    	response.setData(data);
    	return response;
    	
    }
    
    @HTTP(alias = "myBroker", isNeadProject = true, isNeedSign = false)
    @ExplainAnnotation(explain = "社区经纪人")
    public Response<MyBrokerResponseData> myBroker(MyBrokerRequest request, 
    		UserToken userToken) {
    	Response<MyBrokerResponseData> response = new Response<MyBrokerResponseData>();
    	response.setCode(HttpStatus.OK.getStatusCode());
    	MyBrokerResponseData data=new MyBrokerResponseData();
    	try{
    		String projectId = request.getAppUser().getProjectId();
    		ProjectBrokerResponse hkRoomRelationResponse =  projectReadService.getBrokerByProject(Long.parseLong(projectId));
            checkAndContinue(hkRoomRelationResponse);
    		if(hkRoomRelationResponse.getProjectBroker()!=null 
    				&& hkRoomRelationResponse.getProjectBroker().size()>0){
    			data.setName(hkRoomRelationResponse.getProjectBroker().get(0).getBrokerName());
    			data.setMobile(hkRoomRelationResponse.getProjectBroker().get(0).getBrokerMobile());
    			data.setStatus("200");
    		}else{
    			data.setStatus("404");
    		}
    	}catch(Exception ex){
    		logger.error("社区经纪人",ex);
    		data.setStatus("404");
    		response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
    	}
    	response.setData(data);
    	return response;
    	
    }
    
    

    @HTTP(alias = "index", isNeadProject = true)
    @ExplainAnnotation(explain = "管家首页")
    public Response<GetHKIndexResponseData> index(GetHKIndexRequest request) {

        try {
            Long projectId = Long.parseLong(request.getAppUser().getProjectId());
            Response<GetHKIndexResponseData> response = new Response<GetHKIndexResponseData>();

            String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
            StoreDevice storeDevice = new RedisStoreDevice();
            String key = "hkIndex:v:p:%s:%s";
            String cacheKey = String.format(key,initVersion,request.getAppUser().getProjectId());
            String cachString = storeDevice.getCode(cacheKey);
            if (QDStringUtil.isNotEmpty(cachString)) {
                logger.info("app redis cache === 从缓存获取管家首页.... ......");
                response = JSON.parseObject(cachString,new TypeReference<Response<GetHKIndexResponseData>>(){});
                return response;
            }

            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());

            /**
             * 社区首页业态
             */
            String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());

            /**
             * @param projectId 项目ID (必传)
             * @param code 分类编码  (可选,不传)
             * @param version 版本号 (如果是APP，此参数必传 ,44)
             * @param type  1 微信 2APP3支付宝4PAD  (必传)
             * @return
             */
            List<ServiceItem> serviceItems = appConfigRemote.getBindServiceItemListbyProjectId_20(projectId, null, version, brickSourceType);
            List<ProjectService> services = Lists.newArrayList();
            List<ProjectServiceList> list = Lists.newArrayList();
            List<String> serviceTypeList = Lists.newArrayList();

            Project project = projectReadService.get(projectId);
            if (CollectionUtils.isNotEmpty(serviceItems)) {
                for (ServiceItem serviceItem : serviceItems) {
                    try {
                        ProjectService projectService = transfor(ProjectService.class, serviceItem);
                        //填充身份角色3.2
                        fillRoomRole(serviceItem, projectService);
                        
                        projectService.setImageUrl(serviceItem.getHkImgUrl());
                        projectService.setServiceId(String.valueOf(serviceItem.getId()));
                        String skipStr = "";
                        if (QDStringUtil.isNull(serviceItem.getSkipNo())) {
                            logger.error("get project service error :" + JSON.toJSONString(serviceItem));
                            continue;
                        }

                        SkipBean skipBean = new SkipBean();
                        skipBean.setIds(String.valueOf(serviceItem.getId()));
                        skipBean.setSkipNo(Integer.parseInt(serviceItem.getSkipNo()));
                        skipBean.setProjectName(project.getName());
                        skipBean.setProjectId(String.valueOf(project.getId()));
                        skipBean.setIdentity(QDStringUtil.isNotNull(serviceItem.getAccessPrivilege()) ? serviceItem.getAccessPrivilege()+"" : Constant.DETAL_IDENTITY );
                        skipBean.setPcode( serviceItem.getPrivilegeType()==0?1:3);

                        if (Integer.parseInt(serviceItem.getSkipNo()) == Constant.SkipNo.URL_7000.toInteger()) {
                            skipBean.setSkipUrl(serviceItem.getContant());
                            skipStr = skipMode.fittingSkipUrl(request.getAppDevice().getQdVersion(), skipBean);
                        } else {
                            skipStr = skipMode.fittingSkipModelBySkipBean(request.getAppDevice().getQdVersion(),skipBean);
                        }
                        projectService.setSkipModel(skipStr);
                        String serviceType = String.valueOf(projectService.getServiceType());
                        if (!serviceTypeList.contains(serviceType)) {
                            serviceTypeList.add(serviceType);
                        }
                        services.add(projectService);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        logger.info("version3.0 - class: callHouseKeeper,method:index is error", ex);
                        continue;

                    }
                }
            }

            for (String type : serviceTypeList) {
                ProjectServiceList projectServiceList = new ProjectServiceList();
                List<ProjectService> servicesGroup = Lists.newArrayList();
                String title = "";
                for (ProjectService service : services) {
                    if (service.getServiceType() != null && service.getServiceType().equals(type)) {
                        servicesGroup.add(service);
                        title = service.getServiceTypeName();
                    }
                }
                projectServiceList.setServices(servicesGroup);
                projectServiceList.setTitle(title);
                list.add(projectServiceList);
            }

            List<String> phones = fittingHouseKeeperPhone(project);
            response.setData(new GetHKIndexResponseData(list,phones));
            logger.info("app redis cache ===未从缓存获取管家首页.... ......");
            try {
                String expirat =  DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_APPCACHE_1.getGroupName(),Constant.Dict_K_V_Enum.DICT_APPCACHE_1.getDictKey());
                storeDevice.setCode(cacheKey, EntitySerialUtil.serial(response),Integer.parseInt(expirat));
            } catch (TException e) {
                e.printStackTrace();
                logger.error("app redis cache === "+JSON.toJSONString(request)+" : 缓存数据失败");
            }

            return response;

        } catch (Exception e) {
            return handleException(GetHKIndexResponseData.class, e);
        }
    }

	private void fillRoomRole(ServiceItem serviceItem,
			ProjectService projectService) {
		//3.2新增
		projectService.setTagName(serviceItem.getTagName());
		projectService.setPermType(serviceItem.getPrivilegeType());
		if(serviceItem.getAccessPrivilege()!=null){
			//业主，家庭成员，租客，装修负责人，朋友，保姆，司机
			String s=Integer.toBinaryString(serviceItem.getAccessPrivilege());
			List<String> bindRoomRole=new ArrayList<String>();
			String init="0000000";
			int length=s.length();
			if(length!=7 && length<7){
				s=init.substring(0,7-length)+s;
			}
			length=s.length();
			//ONWER(1, "业主"), RELATIVE(2, "家庭成员"), FRIENDS(3, "业主朋友"), RENTER(4, "租客"), DECORATOR(5, "装修负责人"), VISTOR(6, "游客"),
			//NANNY(9,"保姆"), DRIVER(10,"司机"),FAMILY(11,"家庭成员");
			if(length>0 && s.charAt(0)=='1') bindRoomRole.add("1");
			if(length>1 && s.charAt(1)=='1') bindRoomRole.add("2");
			if(length>2 && s.charAt(2)=='1') bindRoomRole.add("4");
			if(length>3 && s.charAt(3)=='1') bindRoomRole.add("5");
			if(length>4 && s.charAt(4)=='1') bindRoomRole.add("3"); 
			if(length>5 && s.charAt(5)=='1') bindRoomRole.add("9");
			if(length>6 && s.charAt(6)=='1') bindRoomRole.add("10");
			projectService.setBindRoomRole(bindRoomRole);
		}
	}

    @HTTP(alias = "getRobotIndex", isNeadProject = true)
    @ExplainAnnotation(explain = "机器人聊天页")
    public Response<GetRobotIndexResponseData> getRobotIndex(GetRobotIndexRequest request) {

        Response<GetRobotIndexResponseData> response = new Response<GetRobotIndexResponseData>();
        GetRobotIndexResponseData data = new GetRobotIndexResponseData();
        List<RobotServiceItem> list = Lists.newArrayList();
        FrequentlyAskedQuestionDTO entity = new FrequentlyAskedQuestionDTO();
        try {
            FAQRequest faqRequest = new FAQRequest();
            faqRequest.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
            FAQResponse faqResponse = knowledgeRemote.getFAQ(faqRequest);
            checkAndContinue(faqResponse);
            List<QuestionConf> faqList = faqResponse.getList();
            if (CollectionUtils.isNotEmpty(faqList)) {
                for (QuestionConf questionConf : faqList) {
                    RobotServiceItem serviceItem = new RobotServiceItem();
                    serviceItem.setTitle(questionConf.getName());
                    String skipStr = "";
                    if (Integer.parseInt(questionConf.getSkipNo()) == Constant.SkipNo.URL_7000.toInteger()) {
                        skipStr = skipMode.fittingSkipUrl(request.getAppDevice().getQdVersion(), questionConf.getSkipUrl(), 0, "", Integer.parseInt(questionConf.getSkipPcode()));
                    } else {
                        skipStr = skipMode.fittingNoParameterSkipModel(request.getAppDevice().getQdVersion(), Integer.parseInt(questionConf.getSkipNo()));
                    }
                    serviceItem.setSkipModel(skipStr);
                    list.add(serviceItem);
                }
            }
            String url = APIPropertiesClient.getValue("robotquestion");
            entity.setSkipModel(skipMode.fittingSkipUrl(request.getAppDevice().getQdVersion(),url, 0, "",0));

            List<String> contentList = Lists.newArrayList();
            contentList.add(Constant.RobotIndexContnetEnum.ROBOT_INDEX_CONTENT_1.content);
            contentList.add(Constant.RobotIndexContnetEnum.ROBOT_INDEX_CONTENT_2.content);
            entity.setList(contentList);

        } catch (ServiceException ex) {
            return handleException(GetRobotIndexResponseData.class, ex);
        }
        data.setEntity(entity);
        data.setList(list);
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }


    @HTTP(alias = "fixSeg")
    @ExplainAnnotation(explain = "机器人语音解词纠错")
    public Response<FixSegResponseData> fixSeg (FixSegRequest request) {

        Response<FixSegResponseData> response = new Response<FixSegResponseData>();
        FixSegResponseData data  = new FixSegResponseData();

        try {
            com.qding.knowledge.struct.request.FixSegRequest fixSegRequest = transfor(com.qding.knowledge.struct.request.FixSegRequest.class,request);
            FixSegResponse fixSegResponse = knowledgeRemote.fixSeg(fixSegRequest);
            checkAndContinue(fixSegResponse);
            transfor(data,fixSegResponse);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
        } catch (ServiceException ex) {
            return handleException(FixSegResponseData.class, ex);
        }
        return response;
    }


    @HTTP(alias = "getCustomServiceSkip", isNeadProject = true,isRequireAuth = true,isNeadToken = true)
    @ExplainAnnotation(explain = "获取客服服务跳转信息" )
    public Response<GetCustomServiceSkipResponseData> getCustomServiceSkip (GetCustomServiceSkipRequest request,UserToken userToken) {

        Response<GetCustomServiceSkipResponseData> response = new  Response<GetCustomServiceSkipResponseData>();
        GetCustomServiceSkipResponseData data = new GetCustomServiceSkipResponseData();
        String version = request.getAppDevice().getQdVersion();
        Project project = projectReadService.get(Long.parseLong(request.getAppUser().getProjectId()));
        int isSupportAssistant = project.getIsSupportAssistant();//是否开通语音助手
        int isSupportChat = project.getIsSupportChat();//是否开通小丁管家
        CustomerSkipDTO customerSkipDTO = getCustomerSkip(isSupportAssistant,isSupportChat,version,userToken.getMemberId(),request.getAppUser().getProjectId());
        transfor(data,customerSkipDTO);
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());

        return  response;

    }

    /**
     * 客服体系过滤
     * @param isSupportAssistant
     * @param isSupportChat
     * @param version
     * @param memberId
     * @param projectId
     * @return
     */
    private CustomerSkipDTO getCustomerSkip (int isSupportAssistant, int isSupportChat, String version,String memberId,String projectId){

        CustomerSkipDTO customerSkip = new CustomerSkipDTO();
        if (isSupportAssistant != 1 ) { //没有开通了管家功能（语音助手）
            //跳智能机器人
            customerSkip = fittingSkipModel (version,3,null);
            logger.info("no opening service(isSupportAssistant), skip robot");
            return customerSkip;
        }

        if (isSupportChat != 1) { //没有开通小丁管家
            //美洽客服
            customerSkip = fittingSkipModel (version,2,null);
            logger.info("no opening service(isSupportChat), skip mq or robot");
            return customerSkip;
        }
        HkRoomRelationResponse hkRoomRelationResponse =  hkRoomReletionService.getEarliestRoomHkInfo(memberId,Long.parseLong(projectId));
        if (hkRoomRelationResponse.getReturnInfo().getCode()==HttpStatus.OK.getStatusCode() && QDStringUtil.isNull(hkRoomRelationResponse.getHkRoomRelation())) { //如果没有匹配到管家
            //跳转美洽客服（弹框提醒）| 智能机器人
            customerSkip = fittingSkipModel (version,2,null);
            if (customerSkip.getSkipNo().intValue() ==  Constant.SkipNo.MEIQI_4206.toInteger()) {
                customerSkip.setMsg("您的小丁管家不在线，现在由在线人工客服继续为您服务");
            }
            logger.info("no match hk, skip mq or robot");
            return customerSkip;
        }

        if (!isWorkingTime(1)) { //不在管家工作时间段
            //跳转美洽客服
            customerSkip = fittingSkipModel (version,2,hkRoomRelationResponse.getHkRoomRelation());
            logger.info("match hk ,but no match woringTime, skip mq or robot");
        } else {
            customerSkip = fittingSkipModel (version,1,hkRoomRelationResponse.getHkRoomRelation());
            logger.info(" match hk and woringTime, skip hk service");
        }
        return customerSkip;
    }

    /**
     * 客服体系跳转组装
     * @param version
     * @param customerServiceType
     * @return
     */
    private CustomerSkipDTO fittingSkipModel (String version,Integer customerServiceType,MemberHkRoomRelation roomHk){

        CustomerSkipDTO customerSkip = new CustomerSkipDTO();
        if (customerServiceType == 1) { //管家
            String roomDesc = "";
            Room room = roomReadRemoteService.getRoom(roomHk.getRoomId());
            if(room != null){
                roomDesc = room.getProjectName()+"-"+room.getGroupName()+"-"+room.getBuildingName()+"-"+room.getCode();
                try {
                    roomDesc = URLEncoder.encode(roomDesc, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    logger.error(e);  
                }
            }
            String skipUrl = APIPropertiesClient.getSkipUrl("hk_service");
            String hkMid = roomHk.getMemberId();//管家会员ID
            customerSkip.setSkipModel(skipMode.fittingSkipUrl(version,skipUrl+hkMid+"?roomDesc="+roomDesc,0,""));
            logger.info(" skip hk service ... ");

        } else if (customerServiceType == 3) { //机器人
            customerSkip.setSkipModel(skipMode.fittingNoParameterSkipModel(version, Constant.SkipNo.ROBOT_4208.toInteger()));
            customerSkip.setSkipNo(Constant.SkipNo.ROBOT_4208.toInteger());
            logger.info(" skip robot ... ");
        } else { //美洽
            //验证美洽客服是否在工作时间内
            boolean mqWorkingTimeStatus = isWorkingTime(2);
            if (mqWorkingTimeStatus) {
                //跳美洽
                String skipStr = skipMode.fittingSkipModelByOnlyId(version, Constant.SkipNo.MEIQI_4206.toInteger(),".");
                customerSkip.setSkipModel(skipStr.replace("robot","api"));
                customerSkip.setSkipNo(Constant.SkipNo.MEIQI_4206.toInteger());
                if (QDStringUtil.isNotNull(roomHk)) {
                    customerSkip.setMsg("您的小丁管家不在线，现在由在线人工客服继续为您服务");
                }
                logger.info(" skip mq ... ");
            } else {
                //跳智能机器人
                customerSkip.setSkipModel(skipMode.fittingNoParameterSkipModel(version, Constant.SkipNo.ROBOT_4208.toInteger()));
                customerSkip.setSkipNo(Constant.SkipNo.ROBOT_4208.toInteger());
                logger.info(" skip robot ... ");
            }
        }

        return customerSkip;
    }

    /**
     * 客服体系验证是否在工作时间内
     * @param customerServiceType
     * @return
     */
    private boolean isWorkingTime ( int customerServiceType ) {

        String dictKey =  customerServiceType == 1 ? "hk_working_time" : "kf_working_time";
        try {
            String workingTimeStr = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_WORKING_TIME.getGroupName(),dictKey);
            String[] workingTimeArray = workingTimeStr.split("-");
            return DateUtil.isTimeRang(workingTimeArray[0],workingTimeArray[1]);
        } catch (TException e) {
            e.printStackTrace();
        }
        return false;
    }




    @HTTP(alias = "getReportServiceItems", isNeadProject = true)
    @ExplainAnnotation(explain = "获取报事服务", desc = "直联模式")
    public Response<GetReportServiceItemsResponseData> getReportServiceItems(GetReportServiceItemsRequest request) {

        Response<GetReportServiceItemsResponseData> response = new Response<GetReportServiceItemsResponseData>();

        try {
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
            Long projectId = Long.parseLong(request.getAppUser().getProjectId());
            String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());
            GetServiceItemListRequest serviceItemListRequest = new GetServiceItemListRequest();
            serviceItemListRequest.setCode(null);
            serviceItemListRequest.setProjectId(projectId);
            serviceItemListRequest.setIsFixDirect(1);
            serviceItemListRequest.setVersion(version);
            serviceItemListRequest.setType(brickSourceType);
            GetServiceItemListResponse serviceItemListResponse = appConfigRemote.getBindServiceItemList(serviceItemListRequest);
            checkAndContinue(serviceItemListResponse);
            List<ServiceItem> serviceItems = serviceItemListResponse.getServiceItemList();
            List<ProjectService> services = Lists.newArrayList();
            for (ServiceItem serviceItem : serviceItems) {
                try {
                    ProjectService projectService = transfor(ProjectService.class, serviceItem);
                    projectService.setSkipModel(skipMode.fittingNoParameterSkipModel(request.getAppDevice().getQdVersion(), Integer.parseInt(serviceItem.getSkipNo())));
                    services.add(projectService);
                }catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }

            }
            GetReportServiceItemsResponseData data = new GetReportServiceItemsResponseData();
            data.setServices(services);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;
        } catch (Exception ex) {

            return handleException(GetReportServiceItemsResponseData.class, ex);
        }

    }

    @HTTP(alias = "getReplaceCustList", isNeadProject = true, isNeadToken = true)
    @ExplainAnnotation(explain = "获取代缴的客户列表")
    public Response<GetReplaceCustListResponseData> getReplaceCustList(GetReplaceCustListRequest request, UserToken userToken) {

        Response<GetReplaceCustListResponseData> response = new Response<GetReplaceCustListResponseData>();
        GetReplaceCustListResponseData data = new GetReplaceCustListResponseData();
        try {
            com.qding.profee.rpc.request.fee.GetReplaceCustListRequest replaceCustListRequest =
                    transfor(com.qding.profee.rpc.request.fee.GetReplaceCustListRequest.class, request);
            replaceCustListRequest.setMemberId(userToken.getMemberId());
            replaceCustListRequest.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
            GetReplaceCustListResponse replaceCustListResponse = feeService.getReplaceCustList(replaceCustListRequest);
            checkAndContinue(replaceCustListResponse);
            transfor(data, replaceCustListResponse);

        } catch (ServiceException ex) {
            return handleException(GetReplaceCustListResponseData.class, ex);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }


    @HTTP(alias = "getReplaceFee", isNeadProject = true, isNeadToken = true)
    @ExplainAnnotation(explain = "获取代缴账单列表")
    public Response<GetReplaceFeeResponseData> getReplaceFee (GetReplaceFeeRequest request,UserToken userToken) {

        Response<GetReplaceFeeResponseData> response = new Response<GetReplaceFeeResponseData>();
        GetReplaceFeeResponseData data = new GetReplaceFeeResponseData();
        try {
            com.qding.profee.rpc.request.fee.GetReplaceFeeRequest replaceFeeRequest =
                    transfor(com.qding.profee.rpc.request.fee.GetReplaceFeeRequest.class,request);
            replaceFeeRequest.setMemberId(userToken.getMemberId());
            replaceFeeRequest.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
            GetReplaceFeeResponse replaceFeeResponse = feeService.getReplaceFees(replaceFeeRequest);
            checkAndContinue(replaceFeeResponse);
            transfor(data, replaceFeeResponse);

        } catch (ServiceException ex) {
            return handleException(GetReplaceFeeResponseData.class, ex);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }

    @HTTP(alias = "createReplaceFeeOrder", isNeadProject = true, isNeadToken = true)
    @ExplainAnnotation(explain = "创建代缴订单")
    public Response<FeeCreateOrderResponseData> createReplaceFeeOrder (ReplaceFeeCreateOrderRequest request,UserToken userToken) {


        Response<FeeCreateOrderResponseData> response = new Response<FeeCreateOrderResponseData>();
        FeeCreateOrderResponseData data = new FeeCreateOrderResponseData();
        try {
            String projectId = request.getAppUser().getProjectId();
            Project project = projectReadService.get(Long.parseLong(projectId));
            com.qding.profee.rpc.request.fee.ReplaceFeeCreateOrderRequest  replaceFeeCreateOrderRequest =
                    transfor(com.qding.profee.rpc.request.fee.ReplaceFeeCreateOrderRequest.class,request);

            Room roomInfo = roomReadRemoteService.get(Long.parseLong(request.getRoomId()));
            boolean isLongHu = isLongHuForFee(roomInfo.getProjectId());

            replaceFeeCreateOrderRequest.setIsLongHu(isLongHu);
            replaceFeeCreateOrderRequest.setMemberId(userToken.getMemberId());
            replaceFeeCreateOrderRequest.setProjectId(projectId);
            replaceFeeCreateOrderRequest.setProjectName(project.getName());
            replaceFeeCreateOrderRequest.setRegionId(String.valueOf(project.getRegionId()));
            replaceFeeCreateOrderRequest.setRegionName(project.getRegionName());
            replaceFeeCreateOrderRequest.setRoomCode(roomInfo.getCode());
            replaceFeeCreateOrderRequest.setRoomName(project.getRegionName()+roomInfo.getProjectName()+roomInfo.getBuildingName()+roomInfo.getName());
            replaceFeeCreateOrderRequest.setSourceType(0);
            FeeCreateOrderResponse createOrderResponse = feeService.createReplaceFeeOrder(replaceFeeCreateOrderRequest);
            checkAndContinue(createOrderResponse);

            PropertyFeeOrder propertyFeeOrder = createOrderResponse.getPropertyFeeOrder();
            PropertyChargesOrder entity = new PropertyChargesOrder();
            entity.setFeeOwnersCustid(propertyFeeOrder.getFeeOwnersCustid());
            entity.setOrderCode(propertyFeeOrder.getOrderCode());
            entity.setTotalPrice(propertyFeeOrder.getTotalRealpay());
            data.setEntity(entity);
            transfor(data, createOrderResponse);

        } catch (ServiceException ex) {
            return handleException(FeeCreateOrderResponseData.class, ex);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        return response;
    }
}
