package com.qding.api.call.app.qding.v3_0_0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v2_0_0.struct.project.BoardImg;
import com.qding.api.call.app.qding.v2_0_0.struct.project.GrouponActivity;
import com.qding.api.call.app.qding.v2_0_0.struct.project.NoticeBoard;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.call.app.qding.v2_8_0.struct.project.BannerBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.ProjectService;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefNewsInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.project.*;
import com.qding.api.call.app.qding.v3_0_0.struct.project.request.AppNoticeUserDefineRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.project.request.CollectClickRateRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.project.request.GetActivityCalendarRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.project.request.GetProjectIndexRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.project.response.data.*;
import com.qding.api.call.service.BrickService;
import com.qding.api.call.service.PromotionService;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.*;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.api.verifycode.store.StoreDevice;
import com.qding.brick.dto.SellBest;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.ware.KeywordConfRemote;
import com.qding.brick.remote.ware.WareRemoteService;
import com.qding.business.service.IRemoteOrderService;
import com.qding.business.struct.bean.ProductDetailBean;
import com.qding.business.struct.request.GetProductByIdRequest;
import com.qding.business.struct.response.GetProductByIdResponse;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.AppDevice;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.groupon.remote.ProductRemoteService;
import com.qding.groupon.struct.bean.ProductBean;
import com.qding.groupon.struct.bean.ProductSalePolicyBean;
import com.qding.groupon.struct.request.GetProductRequest;
import com.qding.groupon.struct.response.GetProductResponse;
import com.qding.hk.rpc.response.AppNoticeUserDefineResponse;
import com.qding.hk.rpc.service.INoticeRpcService;
import com.qding.marketing.domain.ActivityDetail;
import com.qding.marketing.service.MkRemoteService;
import com.qding.marketing.struct.request.ActivityRequest;
import com.qding.marketing.struct.response.ActivityResponse;
import com.qding.message.constant.MsgConstant;
import com.qding.message.domain.MsgMessage;
import com.qding.message.service.IMessageService;
import com.qding.message.struct.request.MsgInfoRequest;
import com.qding.message.struct.response.MsgInfoResponse;
import com.qding.message.util.Page;
import com.qding.neighbor.v3.dto.ActivityBaseDto;
import com.qding.neighbor.v3.dto.NewsDto;
import com.qding.neighbor.v3.dto.PageResultDto;
import com.qding.neighbor.v3.dto.TopicRecommendDto;
import com.qding.neighbor.v3.enums.EnumModuleType;
import com.qding.neighbor.v3.rpc.ITopicRpcV3;
import com.qding.neighbor.v3.rpc.requst.GetActivityBaseDtoRequest;
import com.qding.neighbor.v3.rpc.requst.GetNewsDtoListRequest;
import com.qding.neighbor.v3.rpc.requst.TopicRecommendRequest;
import com.qding.neighbor.v3.rpc.response.data.GetActivityBaseDtoResponseData;
import com.qding.promotion.common.dto.PromotionDto;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.GetPromotionDetailRequest;
import com.qding.promotion.common.struct.response.GetPromotionDetailResponse;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import com.qding.sysconfig.domain.AppHomeCalendar;
import com.qding.sysconfig.dto.AppHomeConfigDto;
import com.qding.sysconfig.dto.AppHomeSectionDto;
import com.qding.sysconfig.rpc.model.TracfficModel;
import com.qding.sysconfig.rpc.model.WeatherModel;
import com.qding.sysconfig.rpc.request.AppHomeCalendarRequest;
import com.qding.sysconfig.rpc.request.AppHomeConfigRequest;
import com.qding.sysconfig.rpc.response.AppHomeCalendarResponse;
import com.qding.sysconfig.rpc.response.AppHomeConfigResponse;
import com.qding.sysconfig.rpc.response.TracfficResponse;
import com.qding.sysconfig.rpc.response.WeatherResponse;
import com.qding.sysconfig.rpc.service.AppHomeConfigRpcService;
import com.qding.sysconfig.rpc.service.ThridApiRpcService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by qd on 2017/2/22.
 */
@ExplainAnnotation(explain = "社区服务")
public class CallProject extends com.qding.api.call.app.qding.v2_8_0.CallProject {

    @Autowired
    private INoticeRpcService noticeService;

    @Autowired
    private AppHomeConfigRpcService appHomeConfigRpcService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private ProductRemoteService grouponRpcService;

    @Autowired
    private ISolrSkuService solrSku;

    @Autowired
    private ThridApiRpcService thridApiRpcService;

    @Autowired
    private IRemoteOrderService businessOrderService;

    @Autowired
    private IPromotionRemoteService promotionRemoteService;

    @Autowired
    private AppConfigRemote appConfigRemote;

    @Autowired
    private MkRemoteService mkRemoteService;

    @Autowired
    private WareRemoteService wareRemoteService;

    @Autowired
    private ISolrSkuService skuService;

    @Autowired
    private ITopicRpcV3 topicRpcV3;

    @Autowired
    private KeywordConfRemote keywordConfRemote;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private BrickService brickService;


    private static final Logger logger = Logger.getLogger("CallProject");



    @HTTP(alias = "projectIndexForGray", isNeadProject = true)
    @ExplainAnnotation(explain = "社区首页", desc = "灰度使用")
    public Response<GetProjectIndexForGrayResponseData> projectIndexForGray(com.qding.api.call.app.qding.v2_8_0.struct.project.request.GetProjectIndexRequest request, UserToken userToken) {

        Response<GetProjectIndexForGrayResponseData> response = new Response<GetProjectIndexForGrayResponseData>();
        GetProjectIndexForGrayResponseData data = new GetProjectIndexForGrayResponseData();
        String projectId = request.getAppUser().getProjectId();
        response.setCode(HttpStatus.OK.getStatusCode());
        String memberId="";
        if (QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())){
            memberId = userToken.getMemberId();
        }
        String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
        StoreDevice storeDevice = new RedisStoreDevice();
        String key = "pindexGray:v:p:m:%s:%s:%s";
        String cacheKey = String.format(key,initVersion,request.getAppUser().getProjectId(),memberId);
        String cachString = storeDevice.getCode(cacheKey);
        if (QDStringUtil.isNotEmpty(cachString)) {
            logger.info("从缓存获取登陆后首页灰度信息.... ......");
            response = JSON.parseObject(cachString,new TypeReference<Response<GetProjectIndexForGrayResponseData>>(){});
            return response;
        }

        try {
            String version = request.getAppDevice().getQdVersion();
            Map<String, String> skipConfigMap = null;
            if (QDStringUtil.isNotEmpty(version)) {
                version = version.trim();
                skipConfigMap = skipMode.selSkipTemplateMap(version);
            } else {
                logger.info("project index can't get version");
            }

            //其他板块从父类方法获取
            com.qding.api.call.app.qding.v2_0_0.struct.project.ProjectIndex sourceIndex = fittingIndex(projectId, QDStringUtil.isNotNull(userToken) ? userToken.getMemberId() : "", request.getAppDevice(), skipConfigMap);
            ProjectIndexForGray entity = transfor(ProjectIndexForGray.class, sourceIndex);

            Project project = null;
            //管家电话
            try {
                 project = projectReadService.get(Long.parseLong(projectId));
                Long startTime = System.currentTimeMillis();
                entity.setPhones(fittingHouseKeeperPhone(project));
                Long endTime = System.currentTimeMillis();
                logger.info("==========> get houseKeeperPhone total time:" + (endTime - startTime));
            } catch (Exception e) {
                logger.error("fittingHouseKeeperPhone fail ", e);
            }

            try {
                //常用工具组装
                Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
                if (QDStringUtil.isNotNull(brickSourceType)) {
                    List<ServiceItem> serviceItems = appConfigRemote.getIndexPageServiceItem(Long.parseLong(request.getAppUser().getProjectId()), brickSourceType, request.getAppDevice().getQdVersion());
                    List<ProjectService> services = Lists.newArrayList();
                    if (CollectionUtils.isNotEmpty(serviceItems)) {
                        Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(version);
                        for (ServiceItem serviceItem : serviceItems) {
                            ProjectService projectService = transfor(ProjectService.class, serviceItem);
                            projectService.setImageUrl(serviceItem.getImgUrlColor());
                            if (QDStringUtil.isNull(serviceItem.getSkipNo())) {
                                logger.error("get project service error :" + JSON.toJSONString(serviceItem));
                                continue;
                            }
                            //组装服务跳转
                            String skipStr = fittingServiceItemSkip( skipModelMap, serviceItem , project);
                            projectService.setSkipModel(skipStr);
                            services.add(projectService);
                        }
                    }
                    entity.setProjectServices(services);
                }
            } catch (Exception ex) {
                logger.error("get ProjectService fail ", ex);
            }

            //首页Banner
            try {
                BannerBoard bannerBoard = fittingBanner(skipConfigMap, Long.parseLong(projectId), version, QDStringUtil.isNotNull(userToken) ? userToken.getMemberId() : "");
                entity.setBannerBoard(bannerBoard);
            } catch (Exception e) {
                logger.error("fittingBannerBoard fail ", e);
            }

            String keyWord = "";
            //获取搜索框关键字
            try {
                keyWord = keywordConfRemote.getByProjectId(Long.parseLong(projectId));
                if (QDStringUtil.isEmpty(keyWord)) {
                    keyWord = "搜索商品或服务";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            entity.setKeyWord(keyWord);
            data.setEntity(entity);
            response.setData(data);

        } catch (Exception e) {
            return handleException(GetProjectIndexForGrayResponseData.class, e);
        }
        try {
            logger.info("缓存未含有获取登陆后首页灰度信息.... ......");
            String expirat =  DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_APPCACHE_1.getGroupName(),Constant.Dict_K_V_Enum.DICT_APPCACHE_1.getDictKey());
            storeDevice.setCode(cacheKey,EntitySerialUtil.serial(response),Integer.parseInt(expirat));
        } catch (TException e) {
            e.printStackTrace();
            logger.error(JSON.toJSONString(request)+" : 缓存数据失败");
        }

        return response;
    }


    @HTTP(alias = "index", isNeadProject = true, isNeedSign = false)
    @ExplainAnnotation(explain = "社区首页")
    public Response<GetProjectIndexResponseData> getProjectIndex(GetProjectIndexRequest request, UserToken userToken) {

        ProjectIndex projectIndex = new ProjectIndex();
        Response<GetProjectIndexResponseData> response = new Response<GetProjectIndexResponseData>();
        GetProjectIndexResponseData data = new GetProjectIndexResponseData();
        String version = request.getAppDevice().getQdVersion();

        String memberId="";
        if (QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())){
            memberId = userToken.getMemberId();
        }
        String initVersion = skipMode.initVersion(version);
        Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(version);
        StoreDevice storeDevice = new RedisStoreDevice();
        String key = "projectIndex:v:p:m:%s:%s:%s";
        String cacheKey = String.format(key,initVersion,request.getAppUser().getProjectId(),memberId);
        String cachString = storeDevice.getCode(cacheKey);
        if (QDStringUtil.isNotEmpty(cachString)) {
            try {
                response = JSON.parseObject(cachString,new TypeReference<Response<GetProjectIndexResponseData>>(){});
                response.getData().getEntity().setRemindBoard(fittingRemindBoard(skipModelMap, userToken, request.getLastFreshTime()));
                return response;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {

            String dbVersion = appConfigRemote.getCurVersion(version);
            Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
            AppHomeConfigRequest appHomeConfigRequest = new AppHomeConfigRequest();
            appHomeConfigRequest.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
            appHomeConfigRequest.setType(salePlatform);
            appHomeConfigRequest.setVersion(dbVersion);
            AppHomeConfigResponse appHomeConfigResponse = appHomeConfigRpcService.getAppHomeConfigByRequest(appHomeConfigRequest);
            checkAndContinue(appHomeConfigResponse);
            List<AppHomeSectionDto> sectionDtoList = appHomeConfigResponse.getAppHomeSectionDtos();
            Project project = projectReadService.get(Long.parseLong(request.getAppUser().getProjectId()));

            for (AppHomeSectionDto appHomeSectionDto : sectionDtoList) {

                String sectionCode = appHomeSectionDto.getCode(); //板块编码值
                String sectionName = appHomeSectionDto.getName();//板块名称
                List<AppHomeConfigDto> appHomeConfigs = appHomeSectionDto.getAppHomeConfigs();//板块具体配置项

                if (CollectionUtils.isEmpty(appHomeConfigs) && !Constant.noConfigBoardList.contains(sectionCode))
                    continue;//如果当前板块没有配置信息则跳过

                if (Constant.AH_CD.equals(sectionCode) || Constant.AH_PA.equals(sectionCode)) { //如果是日历板块 或 整合营销

                    CalendarBoard calendarBoard = fittingCalendarBoard(project, version, skipModelMap, appHomeSectionDto, appHomeConfigs);
                    projectIndex.setCalendarBoard(calendarBoard);

                } else if (Constant.AH_GL.equals(sectionCode)) {//品质生活板块

                    QualityLifeBoard qualityLifeBoard = figgingQualityLiftBoard(project, skipModelMap, appHomeSectionDto, appHomeConfigs);
                    projectIndex.setQualityLifeBoard(qualityLifeBoard);

                } else if (Constant.AH_GS.equals(sectionCode)) { // 福利铺

                    WelfareBoard welfareBoard = new WelfareBoard();
                    welfareBoard.setTitle(sectionName);
                    List<WelfareGoodsDTO> welfareGoodsList = Lists.newArrayList();
                    GrouponActivity grouponActivity = null;
                    for (AppHomeConfigDto appHomeConfig : appHomeConfigs) {
                        Integer configType = appHomeConfig.getConfigType();
                        // 1.阶梯团购
                        if (configType == 1) {
                            grouponActivity = getGrouponActivityBoard(project, skipModelMap, appHomeSectionDto, appHomeConfig);
                            grouponActivity.setTitle("阶梯团购");
                            welfareBoard.setGrouponActivity(grouponActivity);
                        } else {
                            WelfareGoodsDTO welfareGoods = fittingWelfareBoard(request, project, skipModelMap, appHomeSectionDto, appHomeConfig);
                            if (QDStringUtil.isNotNull(welfareGoods)) {
                                if (appHomeConfig.getConfigType() == 7 || appHomeConfig.getConfigType() == 8) {
                                    welfareGoods.setUiType(1);
                                } else {
                                    welfareGoods.setUiType(2);
                                }
                                welfareGoodsList.add(welfareGoods);
                            }
                        }
                    }
                    if (CollectionUtils.isNotEmpty(welfareGoodsList) || QDStringUtil.isNotNull(grouponActivity)) {
                        welfareBoard.setSectionCode(sectionCode);
                        welfareBoard.setSortIndex(appHomeSectionDto.getIndex());
                        welfareBoard.setWelfareGoodsList(welfareGoodsList);
                        projectIndex.setWelfareBoard(welfareBoard);
                    }

                } else if (Constant.AH_SF.equals(sectionCode)) {  //居家服务
                    LifeServicesBoard lifeServicesBoard = fittingLifeServicesBoard(project, skipModelMap, appHomeSectionDto, appHomeConfigs);
                    projectIndex.setLifeServicesBoard(lifeServicesBoard);

                } else if (Constant.AH_NT.equals(sectionCode)) { //公告
                    NoticeBoard noticeBoard = null;
                    try {
                        Long startTime = System.currentTimeMillis();
                        noticeBoard = fittingNoticeBoard(skipModelMap, project, memberId,request.getAppDevice().getQdVersion());
                        if (QDStringUtil.isNotNull(noticeBoard)) {
                            noticeBoard.setSortIndex(appHomeSectionDto.getIndex());
                            noticeBoard.setSectionCode(sectionCode);
                            projectIndex.setNoticeBoard(noticeBoard);
                            Long endTime = System.currentTimeMillis();
                            logger.info("==========> get notice total time:" + (endTime - startTime));
                        }
                    } catch (Exception e) {
                        logger.error("fittingNoticeBoard fail ", e);
                    }
                } else if (Constant.AH_PT.equals(sectionCode) && !brickService.isAnnualMettingProject(request.getAppUser().getProjectId())) { //社区动态

                    ProjectLifeBoard projectLifeBoard = new ProjectLifeBoard();
                    List<BriefNewsInfo> newsList = fittingNewsInfosForProjectLifeBoard(project, skipModelMap, appHomeSectionDto);
                    List<ProjectLifeDTO> topicList = fittingTopicsForProjectLifeBoard(project, skipModelMap, appHomeSectionDto);
                    if (CollectionUtils.isNotEmpty(newsList) || CollectionUtils.isNotEmpty(topicList)) {
                        projectLifeBoard.setNewsList(newsList);
                        projectLifeBoard.setTopicList(topicList);
                        projectLifeBoard.setSortIndex(appHomeSectionDto.getIndex());
                        projectLifeBoard.setSectionCode(sectionCode);
                        projectLifeBoard.setSkipModel(skipMode.fittingNoParameterSkipModel(skipModelMap,
                                Constant.SkipNo.LJGC_3012.toInteger()));
                        projectIndex.setProjectLifeBoard(projectLifeBoard);
                    }

                } else if (Constant.AH_RT.equals(sectionCode)) { //推荐工具
                    //常用工具组装
                    ProjectServicesBoard projectServicesBoard = fittingProjectServiceBoard(project, request.getAppDevice(), skipModelMap, appHomeSectionDto);
                    projectIndex.setProjectServices(projectServicesBoard);

                } else if (Constant.AH_HS.equals(sectionCode) && !brickService.isAnnualMettingProject(request.getAppUser().getProjectId())) {
                    CityWideRecommendBoard cityWideRecommendBoard = fittingCityWideRecommendBoard(project, skipModelMap, appHomeSectionDto);
                    projectIndex.setCityWideRecommendBoard(cityWideRecommendBoard);
                }
            }
            String keyWord = "";
            //获取搜索框关键字
            try {
                keyWord = keywordConfRemote.getByProjectId(project.getId());
                if (QDStringUtil.isEmpty(keyWord)) {
                    keyWord = "搜索商品或服务";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            projectIndex.setKeyWord(keyWord);

            //管家电话
            try {
                Long startTime = System.currentTimeMillis();
                projectIndex.setPhones(fittingHouseKeeperPhone(project));
                Long endTime = System.currentTimeMillis();
                logger.info("==========> get houseKeeperPhone total time:" + (endTime - startTime));
            } catch (Exception e) {
                logger.error("fittingHouseKeeperPhone fail ", e);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
            return handleException(GetProjectIndexResponseData.class, e);
        }

        //消息浮层板块移动到外层拼装不作为排序
        projectIndex.setRemindBoard(fittingRemindBoard(skipModelMap, userToken, request.getLastFreshTime()));

        data.setEntity(projectIndex);
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());

        logger.info("app redis cache ===缓存未含有获取登陆后首页信息.... ......");

        try {
            String expirat =  DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_APPCACHE_1.getGroupName(),Constant.Dict_K_V_Enum.DICT_APPCACHE_1.getDictKey());
            storeDevice.setCode(cacheKey,EntitySerialUtil.serial(response),Integer.parseInt(expirat));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("app redis cache ==="+JSON.toJSONString(request)+" : 缓存数据失败");
        }

        return response;
    }


    @HTTP(alias = "getActivityCalendar", isNeadProject = true)
    @ExplainAnnotation(explain = "日历首页")
    public Response<GetActivityCalendarResponseData> getActivityCalendar(GetActivityCalendarRequest request) {

        Response<GetActivityCalendarResponseData> response = new Response<GetActivityCalendarResponseData>();
        GetActivityCalendarResponseData data = new GetActivityCalendarResponseData();

        //天气情况  暂时屏蔽无需求
       /* Project project = projectReadService.get(Long.parseLong(request.getAppUser().getProjectId()));
        String regionId = String.valueOf(project.getRegionId()); //城市ID
        List<WeatherDTO> weatherList = getWeatherByCityId(regionId,true);
        data.setWeatherList(weatherList);*/

        //日历部分
        DateDetailDTO curDate = transfor(DateDetailDTO.class, Lunar.getCurDay());//当天日历
        List<DateDTO> dateDtoList = Lunar.getDatesForMonth(request.getYear(), request.getMonth()); //整月日历信息
        Long startTime = DateUtil.getFirstDayOfMonth(request.getYear(), request.getMonth());
        Long endTime = DateUtil.getLastDayOfMonth(request.getYear(), request.getMonth());
        //获取整月活动(已分好组)
        Map<String, List<ActivityCalendarDTO>> activitysByCalendarGroup = null;
        try {
            activitysByCalendarGroup = getActivityListByGroup(request.getAppDevice().getQdVersion(), startTime, endTime, request.getAppUser().getProjectId());
        } catch (Exception ex) {
            logger.error("getActivityListByGroup is error", ex);
            ex.printStackTrace();
        }

        //获取整月民俗(已分好组)
        Map<String, CustomsDTO> customsByCalendarGroup = null;
        try {
            customsByCalendarGroup = getCustomsListByGroup(startTime, endTime);
        } catch (Exception ex) {
            logger.error("customsByCalendarGroup is error", ex);
            ex.printStackTrace();
        }

        List<DateDetailDTO> dateList = Lists.newArrayList();
        for (DateDTO dateDTO : dateDtoList) {
            DateDetailDTO dateDetail = transfor(DateDetailDTO.class, dateDTO);
            if (QDStringUtil.isNotNull(activitysByCalendarGroup)) {
                if (activitysByCalendarGroup.containsKey(dateDTO.gethDate())) {
                    dateDetail.setCurDayActivityList(activitysByCalendarGroup.get(dateDTO.gethDate()));
                }
            }
            if (QDStringUtil.isNotNull(customsByCalendarGroup)) {
                if (customsByCalendarGroup.containsKey(dateDTO.gethDate())) {
                    CustomsDTO customs = customsByCalendarGroup.get(dateDTO.gethDate());
                    dateDetail.setCalendarImg(customs.getCalendarImg()); //后台配置
                    dateDetail.setDateDesc(customs.getDateDesc());//描述
                    dateDetail.setDateTitle(customs.getDateTitle());
                }
            }

            if (dateDTO.gethDate().equals(curDate.gethDate())) { //如果遍历的日期和当天日期相同则复制它的数据（主要是活动数据）
                curDate = transfor(DateDetailDTO.class, dateDetail);
            }
            dateList.add(dateDetail);
        }

        data.setDateList(dateList);
        data.setCurDate(curDate);
        data.setTitle("千丁日历");

        response.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);

        return response;

    }


    @ExplainAnnotation(explain = "会员公告喜好")
    @HTTP(alias = "appNoticeUserDefine", isNeadToken = true)
    public Response<AppNoticeUserDefineResponseData> appNoticeUserDefine(AppNoticeUserDefineRequest request) {

        Response<AppNoticeUserDefineResponseData> response = new Response<AppNoticeUserDefineResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        AppNoticeUserDefineResponseData data = new AppNoticeUserDefineResponseData();
        com.qding.hk.rpc.request.AppNoticeUserDefineRequest noticeUserDefineRequest = transfor(com.qding.hk.rpc.request.AppNoticeUserDefineRequest.class, request);
        AppNoticeUserDefineResponse appNoticeUserDefineResponse = noticeService.appNotifyUserDefine(noticeUserDefineRequest);
        try {
            checkAndContinue(appNoticeUserDefineResponse);
        } catch (ServiceException e) {
            return handleException(AppNoticeUserDefineResponseData.class, e);
        }
        response.setData(data);
        return response;
    }

    @ExplainAnnotation(explain = "首页点击量日志输出")
    @HTTP(alias = "collectClickRate")
    public Response<CollectClickRateResponseData> collectClickRate(CollectClickRateRequest request) {

        Response<CollectClickRateResponseData> response = new Response<CollectClickRateResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }


    /*************************************
     * 私有工具方法
     ********************************************/

    /**
     * 消息浮层板块组装
     *
     * @param userToken
     * @param lastFreshTime
     * @return
     */
    private RemindBoard fittingRemindBoard(Map<String, String> skipModelMap, UserToken userToken, Long lastFreshTime) {

        RemindBoard remindBoard = new RemindBoard();
        if (QDStringUtil.isNotNull(userToken)) {
            try {
                String memberId = userToken.getMemberId();
                MsgInfoRequest msgInfoRequest = new MsgInfoRequest();
                msgInfoRequest.setMid(memberId);
                Long beginForDay = DateUtil.getDayBeginTimestamp();
                if (QDStringUtil.isNull(lastFreshTime) || beginForDay > lastFreshTime) {
                    msgInfoRequest.setTimeStamp(beginForDay);
                } else {
                    msgInfoRequest.setTimeStamp(lastFreshTime);
                }
                String msgTypeStr = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_NOTIFY_TYPES.getGroupName(),Constant.Dict_K_V_Enum.DICT_NOTIFY_TYPES.getDictKey());
                msgInfoRequest.setType(msgTypeStr);
                msgInfoRequest.setPageNo(1);
                msgInfoRequest.setPageSize(1);
                msgInfoRequest.setAppType(MsgConstant.APP_TYPE_QDING);
                logger.info(" project  index get notice remind rpc msgInfoRequest :" + JSON.toJSONString(msgInfoRequest));
                MsgInfoResponse msgInfoResponse = messageService.getMsgList(msgInfoRequest);
                logger.info(" project  index get notice remind rpc MsgInfoResponse :" + JSON.toJSONString(msgInfoResponse));
                Page<MsgMessage> page = msgInfoResponse.getPageList();
                List<MsgMessage> msgList = page.getList();
                if (CollectionUtils.isNotEmpty(msgList)) {
                    MsgMessage msgMessage = msgList.get(0);
                    Long msgType = msgMessage.getType();
                    try {
                        String imgUrl = DictionaryClient.findDictValueByGroupNameAndDictKey("notify_img", Constant.APP_NOTIFY_MEIQIA.toString().equals(String.valueOf(msgType))  ? "notify_img_2" : "notify_img_3");
                        String userName = DictionaryClient.findDictValueByGroupNameAndDictKey("notify_user", Constant.APP_NOTIFY_MEIQIA.toString().equals(String.valueOf(msgType)) ? "notify_user_2" : "notify_user_3");
                        remindBoard.setSysNotify(msgMessage.getTitle());
                        remindBoard.setImg(imgUrl);
                        remindBoard.setName(userName);
                        remindBoard.setTotalCount(page.getTotalRow());

                        String skipStr = "";
                        if (Long.parseLong(Constant.APP_NOTIFY_MEIQIA) == msgMessage.getType()) { //美洽
                            skipStr = skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.MEIQI_4206.toInteger(), msgMessage.getContent());
                            remindBoard.setName("");

                        } else if (Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM) == msgMessage.getType()) {
                            JSONObject detailJson = JSON.parseObject(msgMessage.getDetail());
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
                                skipStr = skipMode.fittingSkipModelBySkipBean(curVersion, skipBean);
                            }

                        } else if (Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_H5) == msgMessage.getType()) {
                            JSONObject detailJson = JSON.parseObject(msgMessage.getDetail());
                            if (QDStringUtil.isNotNull(detailJson)) {
                                String url = String.valueOf(detailJson.get("url"));
                                String curTime = String.valueOf(detailJson.get("curTime"));
                                if (QDStringUtil.isNotEmpty(url))
                                    skipStr = skipMode.fittingSkipUrl("2.7.0", url + "?pushid=" + curTime, 0, "");
                            }
                        } else if (Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_V3) == msgMessage.getType()) {
                            JSONObject detailJson = JSON.parseObject(msgMessage.getDetail());
                            if (QDStringUtil.isNotNull(detailJson)) {
                                String id = String.valueOf(detailJson.get("id"));
                                skipStr = skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.BINDMEMBERLIST_4207.toInteger(), id);
                            }

                        } else { //乐购相关
                            JSONObject detailJson = JSON.parseObject(msgMessage.getDetail());
                            skipStr = skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.DDXQ_4008.toInteger(), detailJson.getString("orderId"));
                        }

                        remindBoard.setSkipModel(skipStr);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        logger.error("projectIndex get msg remind  error", ex);
                    }
                }
            } catch (TException e) {
                e.printStackTrace();
            }
        }
        return remindBoard;
    }


    private ProductDetailBean getBusinessProductInfo(String productId) {

        ProductDetailBean businessProductDetailBean = null;
        GetProductByIdRequest productByIdRequest = new GetProductByIdRequest();
        productByIdRequest.setProductId(productId);
        GetProductByIdResponse productByIdResponse = businessOrderService.getProductById(productByIdRequest);
        if (HttpStatus.OK.getStatusCode() == productByIdResponse.getReturnInfo().getCode()) {
            businessProductDetailBean = productByIdResponse.getEntity();
        }
        return businessProductDetailBean;
    }


    /**
     * 获取指定城市的天气情况，当天或多天
     *
     * @param regionId   城市
     * @param isMultiple 多天：true  当天：false
     */
    private List<WeatherDTO> getWeatherByCityId(String regionId, Boolean isMultiple) {

        List<WeatherDTO> weatherList = Lists.newArrayList();
        WeatherResponse weatherResponse = thridApiRpcService.getWeatherByCityId(String.valueOf(regionId));
        if (HttpStatus.OK.getStatusCode() == weatherResponse.getReturnInfo().getCode()) {
            List<WeatherModel> weatherModelList = weatherResponse.getWeatherModelList();
            if (CollectionUtils.isNotEmpty(weatherModelList)) {
                for (WeatherModel weatherModel : weatherModelList) {
                    WeatherDTO weatherInfo = transfor(WeatherDTO.class, weatherModel);
                    transfor(weatherInfo, weatherResponse.getAirModel());
                    weatherList.add(weatherInfo);
                    if (!isMultiple) break; //如果只获取当天 拿到直接退出
                }
            }
        }
        return weatherList;
    }


    /**
     * 获取营销指定时间段活动列表
     *
     * @param activityRequest
     * @return
     */
    private List<ActivityDetail> getActivityList(ActivityRequest activityRequest) {

        List<ActivityDetail> activityDetailList = Lists.newArrayList();
        ActivityResponse activityResponse = mkRemoteService.getActivity(activityRequest);
        if (HttpStatus.OK.getStatusCode() == activityResponse.getReturnInfo().getCode()) {
            if (CollectionUtils.isNotEmpty(activityResponse.getActivityList())) {
                return activityResponse.getActivityList();
            }
        }
        return activityDetailList;
    }

    /**
     * 获取邻聚指定时间段活动列表
     *
     * @param activityBaseDtoRequest
     * @return
     */
    private List<ActivityBaseDto> getNeighborActivityList(GetActivityBaseDtoRequest activityBaseDtoRequest) {

        List<ActivityBaseDto> activityBaseDtoList = Lists.newArrayList();
        GetActivityBaseDtoResponseData activityBaseDtoResponseData = topicRpcV3.getActivityBaseDtoListByTime(activityBaseDtoRequest);
        if (HttpStatus.OK.getStatusCode() == activityBaseDtoResponseData.getReturnInfo().getCode()) {
            if (CollectionUtils.isNotEmpty(activityBaseDtoResponseData.getActivityBaseDtoList())) {
                return activityBaseDtoResponseData.getActivityBaseDtoList();
            }
        }
        return activityBaseDtoList;
    }


    /**
     * 获取指定年份，指定月份所有活动,并对活动按照日期进行分组
     *
     * @param projectId
     * @return
     */
    private Map<String, List<ActivityCalendarDTO>> getActivityListByGroup(String version, Long startTime, Long endTime, String projectId) {

    	Map<String, List<ActivityCalendarDTO>> groupByCodeAsList = null;
        List<ActivityCalendarDTO> activityCalendarList = Lists.newArrayList();
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setActivityStartTime(startTime);
        activityRequest.setActivityEndTime(endTime);
        activityRequest.setProjectId(Long.parseLong(projectId));
        List<ActivityDetail> marketActivityList = getActivityList(activityRequest);

        GetActivityBaseDtoRequest activityBaseDtoRequest = new GetActivityBaseDtoRequest();
        activityBaseDtoRequest.setStartTime(startTime);
        activityBaseDtoRequest.setEndTime(endTime);
        activityBaseDtoRequest.setProjectId(projectId);
        List<ActivityBaseDto> neighborActivityList = getNeighborActivityList(activityBaseDtoRequest);

        //遍历营销活动列表
        for (ActivityDetail activityDetail : marketActivityList) {

            Long activitySTime = DateUtil.parseDate(DateUtil.timeStampToStr(activityDetail.getPublishTime())).getTime();//获取起始时间(这里使用发布时间)
            Long activityETime = DateUtil.parseDate(DateUtil.timeStampToStr(activityDetail.getEndTime())).getTime();//活动终止时间
            
            Long dayCount = DateUtil.getTwoDay(activityETime, activitySTime);//活动举办的天数（时间差）
            String startDate = DateUtil.timeStampToStr(activitySTime);

            String skipStr = skipMode.fittingSkipUrl(version,  APIPropertiesClient.getUrlContent("skip_url_btn_h5")+activityDetail.getUrl(), 0, null, 1);
            for (long i = 0; i <= dayCount; i++) {
                String middleDate = DateUtil.getDateByNum(startDate, Integer.parseInt(String.valueOf(i)));
                long tmpTime = DateUtil.parseDate(middleDate).getTime();
                if (tmpTime > endTime) break;
                ActivityCalendarDTO activityCoverInfo = new ActivityCalendarDTO();
                activityCoverInfo.setActivityName(activityDetail.getName());
                activityCoverInfo.setSkipModel(skipStr);
                activityCoverInfo.setActivityIco("");
                activityCoverInfo.setActivityTime(middleDate);
                activityCoverInfo.setActivityId(QDStringUtil.isNotNull(activityDetail.getActivityId())?String.valueOf(activityDetail.getActivityId()):"");
                activityCalendarList.add(activityCoverInfo);
            }
        }

        //遍历邻聚活动列表
        for (ActivityBaseDto neighborActivity : neighborActivityList) {
            
        	Long activitySTime = DateUtil.parseDate(DateUtil.timeStampToStr(neighborActivity.getStartTime())).getTime();//获取起始时间(这里使用发布时间)
            Long activityETime = DateUtil.parseDate(DateUtil.timeStampToStr(neighborActivity.getEndTime())).getTime();//活动终止时间
           
            Long dayCount = DateUtil.getTwoDay(activityETime, activitySTime);//活动举办的天数（时间差）
            String startDate = DateUtil.timeStampToStr(activitySTime);
            String skipStr = skipMode.fittingSkipModelByOnlyId(version, Constant.SkipNo.P_ACTIVITY_DETAIL_3028.toInteger(), neighborActivity.getId());
            for (long i = 0; i <= dayCount; i++) {
                if (neighborActivity.getEndTime() < System.currentTimeMillis()) continue; //如果当前活动已经结束则不再显示
                String middleDate = DateUtil.getDateByNum(startDate, Integer.parseInt(String.valueOf(i)));
                long tmpTime = DateUtil.parseDate(middleDate).getTime();
                if (tmpTime > endTime) break;
                ActivityCalendarDTO activityCoverInfo = new ActivityCalendarDTO();
                activityCoverInfo.setActivityName(neighborActivity.getTitle());
                activityCoverInfo.setActivityIco("");
                activityCoverInfo.setSkipModel(skipStr);
                activityCoverInfo.setActivityTime(middleDate);
                activityCoverInfo.setActivityId(neighborActivity.getId());
                activityCalendarList.add(activityCoverInfo);
            }
        }

        //按照活动时间进行分组
        if (CollectionUtils.isNotEmpty(activityCalendarList)) {
            groupByCodeAsList = ArraysUtil.mergerLists(activityCalendarList, "activityTime");
        }

        return groupByCodeAsList;
    }

    /**
     * 日历民俗信息分组
     *
     * @param startTime
     * @param endTime
     * @return
     */
    private Map<String, CustomsDTO> getCustomsListByGroup(Long startTime, Long endTime) {

        Map<String, CustomsDTO> groupByCodeMap = new HashMap<>();
        AppHomeCalendarRequest appHomeCalendarRequest = new AppHomeCalendarRequest();
        appHomeCalendarRequest.setEndTime(endTime);
        appHomeCalendarRequest.setStartTime(startTime);
        AppHomeCalendarResponse appHomeCalendarResponse = appHomeConfigRpcService.getAppHomeCalendarByRequest(appHomeCalendarRequest);
        List<AppHomeCalendar> appHomeCalendarList = appHomeCalendarResponse.getAppHomeCalendarList();
        if (CollectionUtils.isNotEmpty(appHomeCalendarList)) {
            for (AppHomeCalendar appHomeCalendar : appHomeCalendarList) {
                Long calendarSTime = appHomeCalendar.getStartTime();//获取起始时间
                Long calendarETime = appHomeCalendar.getEndTime();//获取终止时间
                Long dayCount = DateUtil.getTwoDay(calendarETime, calendarSTime);//节气民俗持续的天数（时间差）
                String startDate = DateUtil.timeStampToStr(calendarSTime);
                for (long i = 0; i <= dayCount; i++) {
                    String middleDate = DateUtil.getDateByNum(startDate, Integer.parseInt(String.valueOf(i)));
                    CustomsDTO customs = new CustomsDTO();
                    customs.setDateDesc(appHomeCalendar.getContent());
                    customs.setDateTitle(appHomeCalendar.getName());
                    customs.setCalendarImg(appHomeCalendar.getImgUrl());
                    customs.setShowTime(middleDate);
                    groupByCodeMap.put(middleDate, customs);
                }
            }

        }
        return groupByCodeMap;
    }


    private String fittingSkipUrl(AppHomeConfigDto appHomeConfig, Map<String, String> skipModelMap) {

        Integer configType = appHomeConfig.getConfigType();
        String skipModeStr = "";

        if (configType == 0) { //单个通用业态商品详情
            String url = APIPropertiesClient.getSkipUrl("good_detail_CB");
            skipModeStr = skipMode.fittingSkipUrl(skipModelMap, url + appHomeConfig.getProductId().toString(), 1, appHomeConfig.getIsShare(), appHomeConfig.getProductId().toString());

        } else if (configType == 3) { //跳转商城单个商品
            skipModeStr = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                    Constant.SkipNo.SPXQ_5004.toInteger(), appHomeConfig.getProductId());

        } else if (configType == 4) { //通用业态商品列表
            String url = APIPropertiesClient.getSkipUrl("good_list_CB_v3");
            url += appHomeConfig.getId() + "?type=" + appHomeConfig.getExtendId();
            skipModeStr = skipMode.fittingSkipUrl(skipModelMap, url, 1, appHomeConfig.getIsShare(), String.valueOf(appHomeConfig.getId()));

        } else if (configType == 5) { //跳转商城商品列表
            if (CollectionUtils.isNotEmpty(appHomeConfig.getProducts())) {
                skipModeStr = skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.SPLB_5000.toInteger(),
                        ListUtil.listForStringToString(appHomeConfig.getProducts()));
            }

        } else if (configType == 1 || configType == 2 || configType == 6 || configType == 7 || configType == 8) { //1:阶梯团购 2: 秒杀 //6.旅游：url+线路id  //7 活动：url+活动id  //8:指定url链接
            skipModeStr = skipMode.fittingSkipUrl( skipModelMap, appHomeConfig.getContentUrl(), 1,appHomeConfig.getIsShare(),
                    appHomeConfig.getShareTitle(), appHomeConfig.getShareDescription(), appHomeConfig.getShareImgUrl(), appHomeConfig.getContentUrl(), "",appHomeConfig.getProductId());

        }
        return skipModeStr;
    }


    /********************************************内部方法************************************************/


    /**
     * 同城热卖板块
     *
     * @param project
     * @param skipModelMap
     * @param appHomeSectionDto
     * @return
     */
    private CityWideRecommendBoard fittingCityWideRecommendBoard(Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto) {

        CityWideRecommendBoard cityWideRecommendBoard = new CityWideRecommendBoard();
        try {
            List<SellBest> sellBestList = wareRemoteService.getSellBestByCity("NG", project.getId(), 4);
            if (CollectionUtils.isNotEmpty(sellBestList)) {
                List<Long> skuIds = Lists.newArrayList();
                Map<String, Integer> sellMap = new HashMap<>();
                for (SellBest sellBest : sellBestList) {
                    skuIds.add(sellBest.getSkuId());
                    sellMap.put(String.valueOf(sellBest.getSkuId()), Integer.parseInt(String.valueOf(sellBest.getSellNum())));
                }
                LegouSkuRequest legouSkuRequest = new LegouSkuRequest();
                legouSkuRequest.setSortedSkuIds(skuIds);
                legouSkuRequest.setFindAllStatus(true);
                LegouSkuResponse skuResponse = skuService.queryLegouSku(legouSkuRequest);
                if (HttpStatus.OK.getStatusCode() == skuResponse.getReturnInfo().getCode()) {
                    List<RecommendGood> recommendGoodList = Lists.newArrayList();
                    List<LegouSkuDetailInfo> skuInfoList = skuResponse.getSkus();
                    if (CollectionUtils.isNotEmpty(skuInfoList)) {
                        for (LegouSkuDetailInfo legouSkuDetailInfo : skuInfoList) {
                            try {
                                RecommendGood recommendGood = new RecommendGood();
                                String skuId = legouSkuDetailInfo.getSkuId();
                                recommendGood.setSellCount(sellMap.containsKey(skuId) ? sellMap.get(skuId) : 0);
                                recommendGood.setSkuId(skuId);
                                recommendGood.setGoodsImg(QDStringUtil.isNotNull(legouSkuDetailInfo.getSkuImgUrl())
                                        ? legouSkuDetailInfo.getSkuImgUrl()[0] : "");
                                recommendGood.setGoodsName(legouSkuDetailInfo.getSkuName());
                                recommendGood.setPrice(legouSkuDetailInfo.getPrice());
                                recommendGood.setOriginalPrice(legouSkuDetailInfo.getMarketPrice());
                                String skipModeStr = skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.SPXQ_5004.toInteger(), skuId);
                                recommendGood.setSkipModel(skipModeStr);
                                recommendGoodList.add(recommendGood);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                outIndexLog(appHomeSectionDto, project, ex, JSON.toJSONString(legouSkuDetailInfo));
                                continue;
                            }

                        }
                        cityWideRecommendBoard.setSectionCode(appHomeSectionDto.getCode());
                        cityWideRecommendBoard.setRecommendList(recommendGoodList);
                        cityWideRecommendBoard.setTitle(appHomeSectionDto.getName());
                        cityWideRecommendBoard.setTotalCount(null);
                        cityWideRecommendBoard.setSortIndex(appHomeSectionDto.getIndex());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }
        return cityWideRecommendBoard;
    }


    /**
     * 社区服务工具板块组装
     *
     * @param project
     * @param appDevice
     * @param skipModelMap
     * @param appHomeSectionDto
     * @return
     */
    private ProjectServicesBoard fittingProjectServiceBoard(Project project, AppDevice appDevice, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto) {

        ProjectServicesBoard projectServicesBoard = new ProjectServicesBoard();
        try {
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, appDevice);
            if (QDStringUtil.isNotNull(brickSourceType)) {
                List<ServiceItem> serviceItems = null;
                serviceItems = appConfigRemote.getIndexPageServiceItem(project.getId(), brickSourceType, appDevice.getQdVersion());
                if (CollectionUtils.isNotEmpty(serviceItems)) {
                    List<ProjectService> services = Lists.newArrayList();
                    for (int i = 0; i < serviceItems.size(); i++) {
                        ServiceItem serviceItem = serviceItems.get(i);
                        try {
                            ProjectService projectService = transfor(ProjectService.class, serviceItem);
                            projectService.setImageUrl(serviceItem.getImgUrlColor());
                            projectService.setServiceId(String.valueOf(serviceItem.getId()));
                            if (QDStringUtil.isNull(serviceItem.getSkipNo())) {
                                logger.error("get project service error :" + JSON.toJSONString(serviceItem));
                                continue;
                            }
                            //组装服务跳转
                            String skipStr = fittingServiceItemSkip( skipModelMap, serviceItem , project);
                            projectService.setSkipModel(skipStr);
                            services.add(projectService);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            outIndexLog(appHomeSectionDto, project, ex, JSON.toJSONString(serviceItem));
                            continue;
                        }
                    }

                    ProjectService tmpItem = new ProjectService();
                    tmpItem.setImageUrl(APIPropertiesClient.getValue("index_tools_more_img"));
                    tmpItem.setName("全部");
                    tmpItem.setSkipModel(skipMode.fittingNoParameterSkipModel(skipModelMap, Constant.SkipNo.HK_SERVICE_2000.toInteger()));
                    services.add(tmpItem);

                    projectServicesBoard.setProjectServices(services);
                    projectServicesBoard.setSectionCode(appHomeSectionDto.getCode());
                    projectServicesBoard.setSortIndex(appHomeSectionDto.getIndex());

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }
        return projectServicesBoard;
    }


    /**
     * 话题推荐位组装
     *
     * @param project
     * @param skipModelMap
     * @param appHomeSectionDto
     * @return
     */
    private List<ProjectLifeDTO> fittingTopicsForProjectLifeBoard(Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto) {

        List<ProjectLifeDTO> topicList = Lists.newArrayList();
        try {
            TopicRecommendRequest recommendRequest = new TopicRecommendRequest();
            recommendRequest.setProjectId(project.getId());
            recommendRequest.setCityId(project.getRegionId());
            recommendRequest.setModuleType(EnumModuleType.INDEX);
            PageResultDto<TopicRecommendDto> topicRecommendPageResult = topicRpcV3.getTopicRecommend(recommendRequest);
            List<TopicRecommendDto> rpcIndexRecommendList = topicRecommendPageResult.getList();
            if (CollectionUtils.isNotEmpty(rpcIndexRecommendList)) {
                for (TopicRecommendDto topicRecommendDto : rpcIndexRecommendList) {
                    try {
                        ProjectLifeDTO topicDto = transfor(ProjectLifeDTO.class, topicRecommendDto);
                        if (QDStringUtil.isNotEmpty(topicRecommendDto.getUrl())) { //如果是url
                            topicDto.setSkipModel(skipMode.fittingSkipUrl(skipModelMap, topicRecommendDto.getUrl(), 1, 0, appHomeSectionDto.getCode()));
                        } else {
                            Integer subTopicTopic = topicDto.getTopicType();
                            Integer skipNo = null;
                            topicDto.setSkipModel(getTopicDetailSkipStr(skipModelMap, subTopicTopic, skipNo, topicRecommendDto.getTopicId()));
                        }
                        topicList.add(topicDto);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        outIndexLog(appHomeSectionDto, project, ex, JSON.toJSONString(topicRecommendDto));
                        continue;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }

        return topicList;
    }

    /**
     * 社区新闻模块
     *
     * @param project
     * @param skipModelMap
     * @return
     */
    private List<BriefNewsInfo> fittingNewsInfosForProjectLifeBoard(Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto) {

        List<BriefNewsInfo> newsList = Lists.newArrayList();
        try {
            GetNewsDtoListRequest newsDtoListRequest = new GetNewsDtoListRequest();
            newsDtoListRequest.setPageSize(2);
            newsDtoListRequest.setLastPublishTime(System.currentTimeMillis());
            newsDtoListRequest.setProjectId(project.getId());
            newsDtoListRequest.setCityId(project.getRegionId());
            newsDtoListRequest.setBigThanTime(DateTime.now().minusDays(7).getMillis());
            PageResultDto<NewsDto> newsPageResult = topicRpcV3.getNewsDtoList(newsDtoListRequest);
            List<NewsDto> rpcNewsList = newsPageResult.getList();
            if (CollectionUtils.isNotEmpty(rpcNewsList)) {
                for (NewsDto newsDto : rpcNewsList) {
                    try {
                        BriefNewsInfo briefNewsInfo = transfor(BriefNewsInfo.class, newsDto);
                        briefNewsInfo.setSkipModel(skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.P_NEWS_DETAIL_3022.toInteger(), newsDto.getId()));
                        newsList.add(briefNewsInfo);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        outIndexLog(appHomeSectionDto, project, ex, JSON.toJSONString(newsDto));
                        continue;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }
        return newsList;
    }

    /**
     * 居家服务板块
     *
     * @param project
     * @param skipModelMap
     * @param appHomeSectionDto
     * @param appHomeConfigs
     * @return
     */
    private LifeServicesBoard fittingLifeServicesBoard(Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto, List<AppHomeConfigDto> appHomeConfigs) {

        LifeServicesBoard lifeServicesBoard = new LifeServicesBoard();
        lifeServicesBoard.setTitle(appHomeSectionDto.getName());
        List<BoardImg> lifeServiceList = Lists.newArrayList();
        for (AppHomeConfigDto appHomeConfig : appHomeConfigs) {
            try {
                BoardImg lifeService = new BoardImg();
                lifeService.setImgTitle(appHomeConfig.getName());
                lifeService.setImgDesc(appHomeConfig.getPromotionTagContent());
                lifeService.setImageUrl(appHomeConfig.getImgUrl());
                lifeService.setSkipModel(fittingSkipUrl(appHomeConfig, skipModelMap));
                lifeServiceList.add(lifeService);
            } catch (Exception ex) {
                ex.printStackTrace();
                outIndexLog(appHomeSectionDto, project, ex, JSON.toJSONString(appHomeConfig));
                continue;
            }

        }

        if (CollectionUtils.isNotEmpty(lifeServiceList)) {
            lifeServicesBoard.setSectionCode(appHomeSectionDto.getCode());
            lifeServicesBoard.setSortIndex(appHomeSectionDto.getIndex());
            lifeServicesBoard.setLifeServicesList(lifeServiceList);
        }

        return lifeServicesBoard;
    }

    private WelfareGoodsDTO fittingWelfareBoard(GetProjectIndexRequest request, Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto, AppHomeConfigDto appHomeConfig) {

        WelfareGoodsDTO welfareGoods = new WelfareGoodsDTO();
        try {

            Integer isShow = appHomeConfig.getIsDisplayButton(); //0:否 1:是
            if (isShow == 1) {
                welfareGoods.setBtnName( appHomeConfig.getButtonName());
            }
            welfareGoods.setTitle(appHomeConfig.getTagName());
            welfareGoods.setGoodsImg(appHomeConfig.getImgUrl());
            Integer configType = appHomeConfig.getConfigType();
            if (configType == 3 || configType == 5 || configType == 2) {  //3.商城单个商品,5.商城商品列表 2:秒杀

                LegouSkuRequest skuRequest = transfor(LegouSkuRequest.class, request);
                skuRequest.setFindSellNum(true);
                skuRequest.setFindSkuStock(true);
                ArrayList<Long> sortedSkuIds = new ArrayList<Long>();
                sortedSkuIds.add(Long.parseLong(appHomeConfig.getProductId()));
                skuRequest.setSortedSkuIds(sortedSkuIds);
                skuRequest.setProjectId(project.getId());
                LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
                List<LegouSkuDetailInfo> legouSkuDetailInfoList = skuResponse.getSkus();

                if (CollectionUtils.isNotEmpty(legouSkuDetailInfoList)) {
                    LegouSkuDetailInfo legouSkuDetailInfo = legouSkuDetailInfoList.get(0);
                    welfareGoods.setSellCount(Integer.parseInt(String.valueOf(legouSkuDetailInfo.getCountWareSellNum())));//购买数
                    welfareGoods.setGoodsName(legouSkuDetailInfo.getSkuName());//商品名称
                    welfareGoods.setPrice(legouSkuDetailInfo.getPrice());//现价
                    welfareGoods.setOriginalPrice(legouSkuDetailInfo.getMarketPrice());//市场价
                    welfareGoods.setSurplusCount(Integer.parseInt(String.valueOf(legouSkuDetailInfo.getCountStock())));//剩余库存
                    welfareGoods.setUnlimitedStock(legouSkuDetailInfo.getUnlimitedStock()); //是否是无限或有限库存
                }
                welfareGoods.setType(1);

                if (configType == 2) { //秒杀

                    GetPromotionDetailRequest promotionDetailRequest = new GetPromotionDetailRequest();
                    promotionDetailRequest.setPromotionId(appHomeConfig.getExtendId());//秒杀的促销ID
                    GetPromotionDetailResponse promotionDetailResponse = promotionRemoteService.getPromotionDetail(promotionDetailRequest);
                    GoodsPromotion goodsPromotion = promotionService.getGoodsOptimizePromotion(project.getId(),Long.parseLong(appHomeConfig.getProductId()));
                    if (QDStringUtil.isNotNull(goodsPromotion)) {
                        String discountWarePrice = goodsPromotion.getPromotionPrice();
                        welfareGoods.setOriginalPrice(welfareGoods.getPrice());//市场价存储现价
                        welfareGoods.setPrice(discountWarePrice);//现价存储促销价
                    }

                    if (HttpStatus.OK.getStatusCode() == promotionDetailResponse.getReturnInfo().getCode()) {
                        PromotionDto promotionDto = promotionDetailResponse.getPromotionDto();
                        Integer status = promotionDetailResponse.getStatus();
                        welfareGoods.setGoodsName(appHomeConfig.getName());
                        welfareGoods.setStatus(status);
                        if (status == -1) { //未开始
                            Long nextStartTime = promotionDetailResponse.getCurrentValidTime().getValidStart();
                            if (nextStartTime != 0) {
                                Long curTime = System.currentTimeMillis();
                                welfareGoods.setPromotionStartTime(nextStartTime - curTime);//秒杀开始时间 获取
                            }
                        }
                        if (QDStringUtil.isEmpty(promotionDto.getStockRealtimeCounts())) {
                            welfareGoods.setSurplusCount(99999);
                            welfareGoods.setUnlimitedStock(1); //无限库存
                        } else {
                            String wardIdStr = promotionDto.getWareIds();
                            String stockStr =promotionDto.getStockRealtimeCounts();
                            String[] wardIdArray = wardIdStr.split(",");
                            String[] stockArray = stockStr.split(",");
                            for (int i = 0; i <wardIdArray.length ; i++) {
                                if (wardIdArray[i].equals(appHomeConfig.getProductId()) ) {
                                    welfareGoods.setSurplusCount(Integer.parseInt(stockArray[i]));
                                }
                            }
                            welfareGoods.setUnlimitedStock(0); //有限库存
                        }
                    }
                    welfareGoods.setType(2);
                }


            } else if (configType == 0 || configType == 4) {  //0.通用业态单个商品 4.通用业态商品列表
                ProductDetailBean businessProductDetailBean = getBusinessProductInfo(appHomeConfig.getProductId());
                if (QDStringUtil.isNotNull(businessProductDetailBean)) {
                    welfareGoods.setSellCount(Integer.valueOf(String.valueOf(businessProductDetailBean.getSoldProductNum())));//购买数
                    welfareGoods.setGoodsName(businessProductDetailBean.getTitle());//商品名称
                    Integer priceType = businessProductDetailBean.getPriceType();
                    BigDecimal amount;
                    if (priceType == 0) { //如果是固定价
                        amount = new BigDecimal(businessProductDetailBean.getPrice());
                        amount = amount.divide(new BigDecimal(100));//分转元处理
                        welfareGoods.setPrice(String.valueOf(amount));
                    } else {
                        String priceRangeStr = businessProductDetailBean.getPriceRange();
                        if (QDStringUtil.isNotEmpty(priceRangeStr)) {
                            String[] rangeArray = priceRangeStr.split(",");
                            StringBuffer sb = new StringBuffer();
                            for (int i = 0; i < rangeArray.length; i++) {
                                if (i==1) {
                                    sb.append("-");
                                }
                                amount = new BigDecimal(rangeArray[i]);
                                amount = amount.divide(new BigDecimal(100));
                                sb.append(amount);
                            }
                            welfareGoods.setPrice(sb.toString());
                        }
                    }

                }
                welfareGoods.setType(3);

            } else if (configType == 7 || configType == 8) { //url+id
                welfareGoods.setTitle(appHomeConfig.getTagName());
                welfareGoods.setGoodsImg(appHomeConfig.getImgUrl());
                welfareGoods.setGoodsName(appHomeConfig.getName());
                welfareGoods.setDesc(appHomeConfig.getDescription());
                welfareGoods.setType(4);
            }
            welfareGoods.setSkipModel(fittingSkipUrl(appHomeConfig, skipModelMap));

        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }
        return welfareGoods;
    }

    /**
     * 阶梯团购板块组装
     *
     * @param skipModelMap
     * @param appHomeConfig
     * @return
     */
    private GrouponActivity getGrouponActivityBoard(Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto, AppHomeConfigDto appHomeConfig) {

        GrouponActivity grouponActivity = new GrouponActivity();//阶梯团购

        try {
            GetProductRequest productRequest = new GetProductRequest();
            productRequest.setProductId(appHomeConfig.getProductId());
            GetProductResponse grouponResponse = grouponRpcService.getProduct(productRequest);
            if (HttpStatus.OK.getStatusCode() == grouponResponse.getReturnInfo().getCode()) {
                ProductBean productBean = grouponResponse.getProduct();
                grouponActivity = transfor(GrouponActivity.class, productBean);
                grouponActivity.setNum(productBean.getSaleNum());
                List<ProductSalePolicyBean> lists = productBean.getProductSalePolicyList();
                grouponActivity = initStage(grouponActivity, productBean.getSaleNum(), lists);
                String url = APIPropertiesClient.getUrl("groupon") + "/" + productBean.getId();
                grouponActivity.setUrl(url);
                appHomeConfig.setContentUrl(url);
                List<String> imagez = grouponActivity.getImagez();
                String imgUrl = imagez.get(0) + "?imageMogr2/gravity/Center/crop/400x400";
                if (QDStringUtil.isNotNull(productBean.getFirstImg())) {
                    imgUrl = productBean.getFirstImg();
                }
                imagez.set(0, imgUrl);
                grouponActivity.setSkipModel(fittingSkipUrl(appHomeConfig, skipModelMap));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }
        return grouponActivity;
    }

    /**
     * 品质生活板块
     *
     * @param skipModelMap
     * @param appHomeSectionDto
     * @param appHomeConfigs
     */
    private QualityLifeBoard figgingQualityLiftBoard(Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto,
                                                     List<AppHomeConfigDto> appHomeConfigs) {

        QualityLifeBoard qualityLifeBoard = new QualityLifeBoard();

        try {
            qualityLifeBoard.setTitle(appHomeSectionDto.getName());
            List<QualityLifeDTO> qualityLifeList = Lists.newArrayList();

            for (AppHomeConfigDto appHomeConfig : appHomeConfigs) {
                QualityLifeDTO qualityLifeDTO = new QualityLifeDTO();
                qualityLifeDTO.setImg(appHomeConfig.getImgUrl());
                qualityLifeDTO.setTagName(appHomeConfig.getTagName());
                qualityLifeDTO.setTitle(appHomeConfig.getName());
                qualityLifeDTO.setDesc(appHomeConfig.getDescription());
                Integer configType = appHomeConfig.getConfigType();
                if (configType == 0 || configType == 4) { // 0.通用业态单个商品 4.通用业态商品列表

                } else if (configType == 6) { //6.旅游
                    qualityLifeDTO.setEndTime(appHomeConfig.getEndTime()); //获取旅游产品结束时间
                }
                qualityLifeDTO.setSkipModel(fittingSkipUrl(appHomeConfig, skipModelMap));
                qualityLifeList.add(qualityLifeDTO);
            }
            qualityLifeBoard.setSortIndex(appHomeSectionDto.getIndex());
            qualityLifeBoard.setQualityLifeList(qualityLifeList);
            qualityLifeBoard.setSectionCode(appHomeSectionDto.getCode());

        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }
        return qualityLifeBoard;

    }


    /**
     * 组装日历 和 整合营销板块
     *
     * @param skipModelMap
     * @param appHomeSectionDto
     * @param appHomeConfigs
     * @return
     */
    private CalendarBoard fittingCalendarBoard(Project project, String version, Map<String, String> skipModelMap,
                                               AppHomeSectionDto appHomeSectionDto, List<AppHomeConfigDto> appHomeConfigs) {

        CalendarBoard calendarBoard = new CalendarBoard();
        try {
            List<ActivityCalendarDTO> activityList = Lists.newArrayList();
            AppHomeConfigDto appHomeConfigDto = appHomeConfigs.get(0);
            CalendarBackgroundDTO calendarBackground = new CalendarBackgroundDTO();
            calendarBackground.setMaxImgUrl(appHomeConfigDto.getImgUrl());
            calendarBoard.setCalendarFontColor(appHomeConfigDto.getCalendarFontColor());//日历字体颜色
            calendarBoard.setDateInfo(Lunar.getCurDay());

            if (Constant.AH_CD.equals(appHomeSectionDto.getCode())) {
                calendarBoard.setCalendarType(1);
                WeatherDTO weatherInfo = new WeatherDTO();
                List<WeatherDTO> weatherList = getWeatherByCityId(String.valueOf(project.getRegionId()), false);
                if (CollectionUtils.isNotEmpty(weatherList)) {
                    weatherInfo = weatherList.get(0);
                }

                TracfficResponse tracfficResponse = thridApiRpcService.getTracfficRestrictionByCityId(String.valueOf(project.getRegionId()), System.currentTimeMillis());
                if (HttpStatus.OK.getStatusCode() == tracfficResponse.getReturnInfo().getCode()) {
                    TracfficModel tracfficModel = tracfficResponse.getTracfficModel();
                    if (QDStringUtil.isNotNull(tracfficModel)) {
                        weatherInfo.setLimitLine("--".equals(tracfficModel.getNum()) ? "" : tracfficModel.getNum());
                    }
                }
                calendarBoard.setWeatherInfo(weatherInfo);

                try {
                    ActivityRequest activityRequest = new ActivityRequest();
                    activityRequest.setCreateAt(System.currentTimeMillis());
                    activityRequest.setProjectId(project.getId());
                    List<ActivityDetail> activityDetailList = getActivityList(activityRequest);
                    if (CollectionUtils.isNotEmpty(activityDetailList)) {
                        for (ActivityDetail activityDetail : activityDetailList) {
                            ActivityCalendarDTO activityInfo = new ActivityCalendarDTO();
                            activityInfo.setActivityName(activityDetail.getName());
                            activityInfo.setActivityTime(String.valueOf(activityDetail.getStartTime()));
                            activityInfo.setActivityId(QDStringUtil.isNotNull(activityDetail.getActivityId())?String.valueOf(activityDetail.getActivityId()):"");
                            activityInfo.setSkipModel(skipMode.fittingSkipUrl(skipModelMap, APIPropertiesClient.getUrlContent("skip_url_btn_h5")+activityDetail.getUrl(), 1, 0, null));
                            activityList.add(activityInfo);
                        }
                    }

                    GetActivityBaseDtoRequest activityBaseDtoRequest = new GetActivityBaseDtoRequest();
                    activityBaseDtoRequest.setStartTime(DateUtil.getDayBegin().getTime());
                    activityBaseDtoRequest.setEndTime(DateUtil.getDayEnd().getTime());
                    activityBaseDtoRequest.setProjectId(String.valueOf(project.getId()));
                    List<ActivityBaseDto> neighborActivityList = getNeighborActivityList(activityBaseDtoRequest);
                    if ( CollectionUtils.isNotEmpty(neighborActivityList)) {
                        for (ActivityBaseDto neighborActivity : neighborActivityList) {
                            if (neighborActivity.getEndTime() < System.currentTimeMillis()) continue; //如果当前活动已经结束则不再显示
                            ActivityCalendarDTO activityInfo = new ActivityCalendarDTO();
                            activityInfo.setActivityTime(String.valueOf(neighborActivity.getPublishTime()));
                            activityInfo.setActivityName(neighborActivity.getTitle());
                            activityInfo.setActivityId(QDStringUtil.isNotNull(neighborActivity.getId())?String.valueOf(neighborActivity.getId()):"");
                            activityInfo.setSkipModel(skipMode.fittingSkipModelByOnlyId(version, Constant.SkipNo.P_ACTIVITY_DETAIL_3028.toInteger(), neighborActivity.getId()));
                            activityList.add(activityInfo);
                        }
                    }
                    getActivityListBySort(activityList);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.error("get Activity list error :", ex);
                }

            } else {

                calendarBackground.setMinImgUrl(appHomeConfigDto.getCalendarImgUrl());
                calendarBoard.setCalendarType(2);
                ActivityCalendarDTO activityInfo = new ActivityCalendarDTO();

                int isShare = appHomeConfigDto.getIsShare();
                if (1==isShare) {
                    activityInfo.setSkipModel(skipMode.fittingSkipUrl(skipModelMap, appHomeConfigDto.getContentUrl(), 0, 1,
                            appHomeConfigDto.getShareTitle(), appHomeConfigDto.getShareDescription(), appHomeConfigDto.getShareImgUrl(),
                            appHomeConfigDto.getContentUrl(), version, String.valueOf(appHomeSectionDto.getId())));
                } else {
                    activityInfo.setSkipModel(skipMode.fittingSkipUrl(skipModelMap,  appHomeConfigDto.getContentUrl(), 0, 0,""));
                }

                activityInfo.setBtnName("进入活动");
                activityInfo.setTitle(appHomeConfigDto.getName());
                activityInfo.setActivityName(appHomeConfigDto.getName());
                activityInfo.setActivityId(String.valueOf(appHomeConfigDto.getId()));
                activityList.add(activityInfo);

            }
            calendarBoard.setSectionCode(appHomeSectionDto.getCode());
            calendarBoard.setSortIndex(appHomeSectionDto.getIndex());
            calendarBoard.setActivityList(activityList);
            calendarBoard.setCalendarBackground(calendarBackground);
            String calendarIndexUrl = APIPropertiesClient.getValue("calendar_index_url");
            calendarBoard.setSkipModel(skipMode.fittingSkipUrl(skipModelMap, calendarIndexUrl, 0, 0, ""));

        } catch (Exception ex) {
            outIndexLog(appHomeSectionDto, project, ex, "");
            ex.printStackTrace();
        }

        return calendarBoard;
    }



    /**
     * 日历活动按照开始时间排序
     * @param list
     * @return
     */
    public static void getActivityListBySort(List<ActivityCalendarDTO> list) {

        if (list.size()>=2) {
            Collections.sort(list,new Comparator<ActivityCalendarDTO>(){
                public int compare(ActivityCalendarDTO arg0, ActivityCalendarDTO arg1) {
                    return arg1.getActivityTime().compareTo(arg0.getActivityTime());
                }

            });
        }
    }


    /**
     * 首页板块异常日志输出
     *
     * @param appHomeSectionDto
     * @param project
     * @param ex
     */
    private void outIndexLog(AppHomeSectionDto appHomeSectionDto, Project project, Exception ex, String desc) {

        logger.error("class:CallProject - version:3.0 - Method:index fitting section  " +
                "" + appHomeSectionDto.getCode() + " , sectionName is " + appHomeSectionDto.getName() + " , projectId is " + project.getId() + " ," +
                " projectName" + project.getName() + " is error , desc :" + desc, ex);
    }


    /**
     * 组装帖子详情跳转模型
     *
     * @param skipModelMap
     * @param subTopicTopic
     * @param skipNo
     * @param topicId
     * @return
     */
    private String getTopicDetailSkipStr(Map<String, String> skipModelMap, Integer subTopicTopic, Integer skipNo, String topicId) {

        switch (subTopicTopic) {
            case 1:
                skipNo = Constant.SkipNo.P_TOPIC_DETAIL_3031.toInteger(); //社区-邻里互动帖子详情或跟帖详情
                break;
            case 2:
                skipNo = Constant.SkipNo.P_TOPIC_DETAIL_3031.toInteger(); //社区-邻里互动帖子详情或跟帖详情
                break;
            case 3:
                skipNo = Constant.SkipNo.P_VOTE_DETAIL_3033.toInteger(); //社区-投票详情
                break;
            case 4:
                skipNo = Constant.SkipNo.P_ACTIVITY_DETAIL_3028.toInteger(); //社区-社区活动详情
                break;
            case 5:
                skipNo = Constant.SkipNo.P_INTERACT_DETAIL_3025.toInteger(); //社区-邻里互动帖子详情或跟帖详情
                break;
            case 6:
                skipNo = Constant.SkipNo.P_NEWS_DETAIL_3022.toInteger(); //社区-社区新闻详情
                break;
            case 7:
                skipNo = Constant.SkipNo.P_ENCYCLOPEDIA_DETAIL_3032.toInteger();//社区-生活百科详情
                break;
        }
        return skipMode.fittingSkipModelByOnlyId(skipModelMap, skipNo, topicId);
    }


    /**
     * 管家电话
     *
     * @param
     * @throws ServiceException
     */
    private   List<String> fittingHouseKeeperPhone(Project project) throws ServiceException {
        if (project == null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
        }
        com.qding.api.call.app.qding.v1_3_0.struct.brick.Project p =
                transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Project.class, project);
        Set<String> phoneSet = new HashSet<String>();

        List<ProjectConcat> projectConcats = p.getConcats();
        for (ProjectConcat projectConcat : projectConcats) {
            if ("1".equals(projectConcat.getType())) {
                String[] phoneStrings = projectConcat.getPhones();
                for (String phone : phoneStrings) {
                    phoneSet.add(phone);
                }
            }
        }
        List<String> phoneList = new ArrayList<String>();
        if (phoneSet.size() > 0) {
            phoneList.addAll(phoneSet);
        }
        return  phoneList;
    }


    /**
     * 组装服务跳转
     * @param skipModelMap
     * @param serviceItem
     * @param project
     * @return
     */
    public String fittingServiceItemSkip( Map<String, String> skipModelMap,ServiceItem serviceItem ,Project project){

        String skipStr = "";
        SkipBean skipBean = new SkipBean();
        skipBean.setProjectName(project.getName());
        skipBean.setProjectId(String.valueOf(project.getId()));
        skipBean.setIds(String.valueOf(serviceItem.getId()));
        skipBean.setSkipNo(Integer.parseInt(serviceItem.getSkipNo()));
        StringBuffer identity = new StringBuffer(Constant.DETAL_IDENTITY);
        if (QDStringUtil.isNotNull(serviceItem.getAccessPrivilege())){
            identity.setLength(0);
            identity.append(serviceItem.getAccessPrivilege());
        }
        skipBean.setIdentity(identity.toString());
        skipBean.setPcode( serviceItem.getPrivilegeType()==0?1:3);
        if (Integer.parseInt(serviceItem.getSkipNo()) == Constant.SkipNo.URL_7000.toInteger()) {
            skipBean.setSkipUrl(serviceItem.getContant());
            skipStr = skipMode.fittingSkipUrl(skipModelMap, skipBean);
        } else {
            skipStr = skipMode.fittingSkipModelBySkipBean(skipModelMap,skipBean);
        }
        return skipStr;
    }

}
