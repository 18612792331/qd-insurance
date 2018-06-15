package com.qding.api.call.app.qding.v2_8_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_6_0.VerifyRule;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefTheme;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicDetail;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.GetNeighborIndexRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.GetNeighborIndexResponseData;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.NeighborBanner;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.ThemeTagInfo;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.request.*;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.request.GetNearbyTopicsByProjectIdRequest;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.request.GetNearbyTopicsByThemeIdRequest;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.request.GetTopicsByBannerIdRequest;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.response.data.*;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.response.data.GetNearbyTopicsByProjectIdResponseData;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.response.data.GetNearbyTopicsByThemeIdResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.SkipModeFitting;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.neighbor.domain.Banner;
import com.qding.neighbor.domain.Theme;
import com.qding.neighbor.dto.ThemeDetailDTO;
import com.qding.neighbor.dto.TopicDetailRpcDTO;
import com.qding.neighbor.rpc.IBannerRpc;
import com.qding.neighbor.rpc.IThemeRpc;
import com.qding.neighbor.rpc.ITopicRpc;
import com.qding.neighbor.rpc.request.*;
import com.qding.neighbor.rpc.response.data.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by qd on 2016/11/22.
 */
@ExplainAnnotation(explain = "邻聚2.0")
public class CallNeighbor extends com.qding.api.call.app.qding.v2_6_0.CallNeighbor {


    @Autowired
    private ITopicRpc topicRpc;

    @Autowired
    private IBannerRpc bannerRpc;

    @Autowired
    private IThemeRpc themeRpc;

    @Autowired
    private SkipModeFitting skipMode;

    private static Logger logger = Logger.getLogger(CallNeighbor.class);


    @HTTP(alias = "getNeighborIndex",isNeadProject = true)
    @ExplainAnnotation(explain = "邻聚首页广场")
    @Deprecated
    public Response<GetNeighborIndexResponseData> getNeighborIndex(GetNeighborIndexRequest request,UserToken userToken) {

        String projectId = request.getAppUser().getProjectId();
        Response<GetNeighborIndexResponseData> response = new Response<GetNeighborIndexResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetNeighborIndexResponseData data = new GetNeighborIndexResponseData();
        String version = request.getAppDevice().getQdVersion();

        //主题列表
        try {
            List<BriefTheme> themeList = Lists.newArrayList();
            Integer totalCount = 0;
            Map<String, Object> themeMap = getThemes( projectId, 1, request.getShowThemeSize());
            if (QDStringUtil.isNotNull(themeMap)) {
                themeList = (List<BriefTheme>) themeMap.get("list");
                totalCount = (Integer) themeMap.get("totalCount");
            }
            data.setThemeCount(totalCount);
            data.setThemeList(themeList);

        } catch (Exception ex) {
            logger.error("method : getNeighborIndex get themes  error:", ex);
            return handleException(GetNeighborIndexResponseData.class, ex);

        }

        //获取banner列表
        GetBannerByProjectIdResponse bannerByProjectIdResponse = bannerRpc.getBannerByProjectId(projectId);
        if (bannerByProjectIdResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()){

            Map<String, String> skipConfigMap = null;
            if (QDStringUtil.isNotEmpty(version)) {
                version = version.trim();
                skipConfigMap = skipMode.selSkipTemplateMap(version);
            } else {
                logger.info("project index can't get version");
            }

            List<Banner> bannerList = bannerByProjectIdResponse.getList();
            List<NeighborBanner> neighborBannerList = Lists.newArrayList();
            if(CollectionUtils.isNotEmpty(bannerList)){
                for (Banner banner : bannerList) {
                    try {
                        String skipStr ="";
                        if(banner.getSkipType()==1){ //活动
                            GetThemeInfoByTagIdRequest themeInfoByTagIdRequest = new GetThemeInfoByTagIdRequest();
                            themeInfoByTagIdRequest.setTagId(banner.getTagId());
                            GetThemeInfoByTagIdResponse themeInfoByTagIdResponse = topicRpc.getThemeInfoByTagId(themeInfoByTagIdRequest);
                            if(HttpStatus.OK.getStatusCode() == themeInfoByTagIdResponse.getReturnInfo().getCode()) {
                                Theme theme = themeInfoByTagIdResponse.getTheme();
                                String ids =  banner.getTagId()+","+theme.getId();
                                skipStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.LJBANNER_3019.toInteger(),ids);
                            } else{
                                logger.error("获取指定banner:"+banner.getTagId()+"的skipModel模型异常:"+themeInfoByTagIdResponse.getReturnInfo().getMessage());
                            }

                        }else if(banner.getSkipType() ==2){ //url
                            skipStr = skipMode.fittingSkipUrl(skipConfigMap,banner.getUrl(),1,0,banner.getId());
                        }else{ //帖子列表
                            skipStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.LJBANNER_3020.toInteger(), banner.getId());
                        }
                        NeighborBanner  neighborBanner= new NeighborBanner();
                        neighborBanner.setBannerImg(banner.getImg());
                        neighborBanner.setBannerId(banner.getId());
                        neighborBanner.setSkipModel(skipStr);
                        neighborBanner.setBannerName(banner.getName());
                        neighborBannerList.add(neighborBanner);
                    } catch (Exception ex) {
                        logger.error("获取banner:"+banner.getTagId()+"的skipModel模型异常:",ex);
                    }

                }
                data.setBannerList(neighborBannerList);

            }
        }


        if(QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId()))  {

            try {
                Integer permissionType = getVistorPermissionType(userToken.getMemberId(), request.getAppUser().getProjectId());
                String memberId = userToken.getMemberId();
                com.qding.neighbor.rpc.request.GetNearbyTopicsByProjectIdRequest topicsByProjectIdRequest = transfor(com.qding.neighbor.rpc.request.GetNearbyTopicsByProjectIdRequest.class, request);
                topicsByProjectIdRequest.setPageNo(1);
                topicsByProjectIdRequest.setPageSize(request.getShowTopicSize());
                topicsByProjectIdRequest.setType(permissionType);
                topicsByProjectIdRequest.setMemberId(memberId);
                topicsByProjectIdRequest.setProjectId(request.getAppUser().getProjectId());
                com.qding.neighbor.rpc.response.data.GetNearbyTopicsByProjectIdResponseData nearbyTopicsByProjectIdResponse = topicRpc.getNearbyTopicsByProjectId(topicsByProjectIdRequest);
                checkAndContinue(nearbyTopicsByProjectIdResponse);
                List<TopicDetailRpcDTO> topicDetailRpcDTOList = nearbyTopicsByProjectIdResponse.getList();
                List<TopicDetail> list = getTopicList(version, topicDetailRpcDTOList);
                data.setTotalCount(nearbyTopicsByProjectIdResponse.getTotalCount());
                data.setTopicList(list);

            } catch (ServiceException e) {
                return handleException(GetNeighborIndexResponseData.class, e);
            }

          /*  try {
                Integer permissionType = getVistorPermissionType(userToken.getMemberId(), request.getAppUser().getProjectId());
                //3、热门话题列表
                GetHotTopicsByProjectIdRequest hotTopicsByProjectIdRequest = transfor(GetHotTopicsByProjectIdRequest.class, request);
                hotTopicsByProjectIdRequest.setPageNo(1);
                hotTopicsByProjectIdRequest.setPageSize(request.getShowTopicSize());
                hotTopicsByProjectIdRequest.setType(permissionType);
                hotTopicsByProjectIdRequest.setProjectId(projectId);
                hotTopicsByProjectIdRequest.setMemberId(userToken.getMemberId());
                GetHotTopicsByProjectIdResponseData hotTopicsByProjectIdResponseData = topicRpc.getHotTopicsByProjectId(hotTopicsByProjectIdRequest);
                checkAndContinue(hotTopicsByProjectIdResponseData);
                List<TopicDetailRpcDTO> topicDetailRpcDTOList = hotTopicsByProjectIdResponseData.getList();
                List<TopicDetail> list = getTopicList(version, topicDetailRpcDTOList);
                if(CollectionUtils.isNotEmpty(list)) {
//                    data.setLastRefreshTime(topicDetailRpcDTOList.get(0).getCreateTime());
                    data.setLastRefreshTime(System.currentTimeMillis());
                }

                data.setNewTopicCount(hotTopicsByProjectIdResponseData.getNewTopicCount());
                data.setTotalCount(hotTopicsByProjectIdResponseData.getTotalCount());
                data.setTopicList(list);
            } catch (ServiceException e) {
                return handleException(GetNeighborIndexResponseData.class, e);
            }*/
        }

        response.setData(data);
        return response;
    }


    @ExplainAnnotation(explain = "通过话题ID集合获取对应的话题列表")
    @HTTP(alias = "getTopicsByTopicIdList")
    public Response<GetTopicsByTopicIdListResponseData> getTopicsByTopicIdList (GetTopicsByTopicIdListRequest request,UserToken userToken) {

        Response<GetTopicsByTopicIdListResponseData> response = new Response<GetTopicsByTopicIdListResponseData>();
        GetTopicsByTopicIdListResponseData data = new GetTopicsByTopicIdListResponseData();

        try {
            String memberId = "";
            if(QDStringUtil.isNotNull(userToken)){
                memberId = userToken.getMemberId();
            }
            GetTopicsByIdListRequest topicsByIdListRequest = transfor(GetTopicsByIdListRequest.class,request);
            topicsByIdListRequest.setMemberId(memberId);
            GetTopicsByIdListResponseData  topicsByIdListResponse = topicRpc.getTopicsByIdList(topicsByIdListRequest);
            checkAndContinue(topicsByIdListResponse);
            List<TopicDetailRpcDTO> topicDetailRpcDTOList = topicsByIdListResponse.getList();
            List<TopicDetail> list = getTopicList(request.getAppDevice().getQdVersion(), topicDetailRpcDTOList);
            data.setTopicList(list);
        } catch (ServiceException e) {
            return handleException(GetTopicsByTopicIdListResponseData.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }


    @ExplainAnnotation(explain = "通过BannerID获取对应的话题列表",desc = "banner跳转为话题列表专用")
    @HTTP(alias = "getTopicsByBannerId",isNeadToken = true)
    public Response<GetTopicsByBannerIdResponseData> getTopicsByTopicIdList (GetTopicsByBannerIdRequest request,UserToken userToken) {

        Response<GetTopicsByBannerIdResponseData> response = new Response<GetTopicsByBannerIdResponseData>();
        GetTopicsByBannerIdResponseData data = new GetTopicsByBannerIdResponseData();

        try {
            String memberId = "";
            if(QDStringUtil.isNotNull(userToken)){
                memberId = userToken.getMemberId();
            }
            com.qding.neighbor.rpc.request.GetTopicsByBannerIdRequest topicsByBannerIdRequest = transfor(com.qding.neighbor.rpc.request.GetTopicsByBannerIdRequest.class,request);
            topicsByBannerIdRequest.setMemberId(memberId);
            GetTopicsByIdListResponseData  topicsByIdListResponse = topicRpc.getTopicsByBannerId(topicsByBannerIdRequest);
            checkAndContinue(topicsByIdListResponse);
            List<TopicDetailRpcDTO> topicDetailRpcDTOList = topicsByIdListResponse.getList();
            List<TopicDetail> list = getTopicList(request.getAppDevice().getQdVersion(), topicDetailRpcDTOList);
            data.setTopicList(list);
            data.setBannerTitle(QDStringUtil.isNotNull(topicsByIdListResponse)&QDStringUtil.isNotNull(topicsByIdListResponse.getBanner())?topicsByIdListResponse.getBanner().getTitle():"");
        } catch (ServiceException e) {
            return handleException(GetTopicsByBannerIdResponseData.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }


    @ExplainAnnotation(explain = "通过主题ID获取对应的周边话题列表")
    @HTTP(alias = "getNearbyTopicsByThemeId",isNeadProject = true,isNeadToken = true)
    public Response<GetNearbyTopicsByThemeIdResponseData> getNearbyTopicsByThemeId (GetNearbyTopicsByThemeIdRequest request,UserToken userToken) {

        Response<GetNearbyTopicsByThemeIdResponseData> response = new Response<GetNearbyTopicsByThemeIdResponseData>();
        GetNearbyTopicsByThemeIdResponseData data = new GetNearbyTopicsByThemeIdResponseData();
        String projectId = request.getAppUser().getProjectId();

        try {
            String memberId = userToken.getMemberId();
            VerifyRule verifyRule = verifyRule(request.getAppDevice().getQdVersion(),request.getThemeId(), memberId, projectId, 1);
            if (verifyRule.getCanUse().intValue() <= 0) {
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                data.setMessage(verifyRule.getRemindMsg());
                response.setData(data);
                return response;
            }

            com.qding.neighbor.rpc.request.GetNearbyTopicsByThemeIdRequest nearbyTopicsByThemeIdRequest = transfor(com.qding.neighbor.rpc.request.GetNearbyTopicsByThemeIdRequest.class,request);
            nearbyTopicsByThemeIdRequest.setProjectId(projectId);
            nearbyTopicsByThemeIdRequest.setMemberId(memberId);
            com.qding.neighbor.rpc.response.data.GetNearbyTopicsByThemeIdResponseData nearbyTopicByThemeIdResponse =  topicRpc.getNearbyTopicsByThemeId(nearbyTopicsByThemeIdRequest);
            checkAndContinue(nearbyTopicByThemeIdResponse);
            List<TopicDetailRpcDTO> topicDetailRpcDTOList = nearbyTopicByThemeIdResponse.getList();
            List<TopicDetail> list = getTopicList(request.getAppDevice().getQdVersion(), topicDetailRpcDTOList);

            GetThemeByIdRequest themeByIdRequest = new GetThemeByIdRequest();
            themeByIdRequest.setThemeId(request.getThemeId());
            themeByIdRequest.setSelProjects(false);
            GetThemeDetailByIdResponse themeDetailByIdResponse = themeRpc.selThemeDetailById(themeByIdRequest);
            checkAndContinue(themeDetailByIdResponse);
            ThemeDetailDTO themeDetailDTO = themeDetailByIdResponse.getTheme();
            BriefTheme themeInfo = transfor(BriefTheme.class,themeDetailDTO);
            themeInfo.setThemeId(themeDetailDTO.getId());

            data.setThemeInfo(themeInfo);
            data.setTopicList(list);
            data.setTotalCount(nearbyTopicByThemeIdResponse.getTotalCount());

        } catch (Exception ex) {
            return handleException(GetNearbyTopicsByThemeIdResponseData.class, ex);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }
    
    
    /*@ExplainAnnotation(explain = "通过主题ID获取对应的周边话题列表_mongodb")
    @HTTP(alias = "getNearbyTopicsByThemeId_mongodb",isNeadProject = true,isNeadToken = true)
    public Response<GetNearbyTopicsByThemeIdResponseData> getNearbyTopicsByThemeId_mongodb (GetNearbyTopicsByThemeIdRequest request,UserToken userToken) {

        Response<GetNearbyTopicsByThemeIdResponseData> response = new Response<GetNearbyTopicsByThemeIdResponseData>();
        GetNearbyTopicsByThemeIdResponseData data = new GetNearbyTopicsByThemeIdResponseData();
        String projectId = request.getAppUser().getProjectId();

        try {
            String memberId = userToken.getMemberId();
            VerifyRule verifyRule = verifyRule(request.getAppDevice().getQdVersion(),request.getThemeId(), memberId, projectId, 1);
            if (verifyRule.getCanUse().intValue() <= 0) {
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                data.setMessage(verifyRule.getRemindMsg());
                response.setData(data);
                return response;
            }

            com.qding.neighbor.rpc.request.GetNearbyTopicsByThemeIdRequest nearbyTopicsByThemeIdRequest = transfor(com.qding.neighbor.rpc.request.GetNearbyTopicsByThemeIdRequest.class,request);
            nearbyTopicsByThemeIdRequest.setProjectId(projectId);
            nearbyTopicsByThemeIdRequest.setMemberId(memberId);
            com.qding.neighbor.rpc.response.data.GetNearbyTopicsByThemeIdResponseData nearbyTopicByThemeIdResponse =  topicRpc.getNearbyTopicsByThemeId_mongodb(nearbyTopicsByThemeIdRequest);
            checkAndContinue(nearbyTopicByThemeIdResponse);
            List<TopicDetailRpcDTO> topicDetailRpcDTOList = nearbyTopicByThemeIdResponse.getList();
            List<TopicDetail> list = getTopicList(request.getAppDevice().getQdVersion(), topicDetailRpcDTOList);

            GetThemeByIdRequest themeByIdRequest = new GetThemeByIdRequest();
            themeByIdRequest.setThemeId(request.getThemeId());
            themeByIdRequest.setSelProjects(false);
            GetThemeDetailByIdResponse themeDetailByIdResponse = themeRpc.selThemeDetailById(themeByIdRequest);
            checkAndContinue(themeDetailByIdResponse);
            ThemeDetailDTO themeDetailDTO = themeDetailByIdResponse.getTheme();
            BriefTheme themeInfo = transfor(BriefTheme.class,themeDetailDTO);
            themeInfo.setThemeId(themeDetailDTO.getId());

            data.setThemeInfo(themeInfo);
            data.setTopicList(list);
            data.setTotalCount(nearbyTopicByThemeIdResponse.getTotalCount());

        } catch (Exception ex) {
        	logger.error("getNearbyTopicsByThemeId_mongodb 异常",ex);
            return handleException(GetNearbyTopicsByThemeIdResponseData.class, ex);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }*/

    @ExplainAnnotation(explain = "通过主题ID获取对应的推荐(热门)话题列表")
    @HTTP(alias = "getRecommendTopicsByThemeId",isNeadProject = true,isNeadToken = true)
    public Response<GetRecommendTopicsByThemeIdResponseData> getRecommendTopicsByThemeId (GetRecommendTopicsByThemeIdRequest request,UserToken userToken) {

        Response<GetRecommendTopicsByThemeIdResponseData> response = new Response<GetRecommendTopicsByThemeIdResponseData>();
        GetRecommendTopicsByThemeIdResponseData data = new GetRecommendTopicsByThemeIdResponseData();
        String projectId = request.getAppUser().getProjectId();
        try {
            VerifyRule verifyRule = verifyRule(request.getAppDevice().getQdVersion(),request.getThemeId(), userToken.getMemberId(), projectId, 1);
            if (verifyRule.getCanUse().intValue() <= 0) {
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                data.setMessage(verifyRule.getRemindMsg());
                response.setData(data);
                return response;
            }

            String memberId = userToken.getMemberId();
            GetHotTopicsByThemeIdRequest  hotTopicsByThemeIdRequest= transfor(GetHotTopicsByThemeIdRequest.class,request);
            hotTopicsByThemeIdRequest.setMemberId(memberId);
            GetHotTopicsByThemeIdResponseData hotTopicsByThemeIdResponse =  topicRpc.getHotTopicsByThemeId(hotTopicsByThemeIdRequest);
            checkAndContinue(hotTopicsByThemeIdResponse);
            List<TopicDetailRpcDTO> topicDetailRpcDTOList = hotTopicsByThemeIdResponse.getList();
            List<TopicDetail> list = getTopicList(request.getAppDevice().getQdVersion(), topicDetailRpcDTOList);

            GetThemeByIdRequest themeByIdRequest = new GetThemeByIdRequest();
            themeByIdRequest.setThemeId(request.getThemeId());
            themeByIdRequest.setSelProjects(false);
            GetThemeDetailByIdResponse themeDetailByIdResponse = themeRpc.selThemeDetailById(themeByIdRequest);
            checkAndContinue(themeDetailByIdResponse);
            ThemeDetailDTO themeDetailDTO = themeDetailByIdResponse.getTheme();
            BriefTheme themeInfo = transfor(BriefTheme.class,themeDetailDTO);
            themeInfo.setThemeId(themeDetailDTO.getId());

            data.setThemeInfo(themeInfo);
            data.setTopicList(list);
            data.setTotalCount(hotTopicsByThemeIdResponse.getTotalCount());

        } catch (Exception ex) {
            return handleException(GetRecommendTopicsByThemeIdResponseData.class, ex);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

    @ExplainAnnotation(explain = "通过社区ID获取对应的周边话题列表")
    @HTTP(alias = "getNearbyTopicsByProjectId",isNeadProject = true,isNeadToken = true)
    public Response<GetNearbyTopicsByProjectIdResponseData> getNearbyTopicsByProjectId (GetNearbyTopicsByProjectIdRequest request,UserToken userToken) {

        Response<GetNearbyTopicsByProjectIdResponseData> response = new Response<GetNearbyTopicsByProjectIdResponseData>();
        GetNearbyTopicsByProjectIdResponseData data = new GetNearbyTopicsByProjectIdResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        String version = request.getAppDevice().getQdVersion();
        Integer permissionType = getVistorPermissionType(userToken.getMemberId(), request.getAppUser().getProjectId());

        try {
            String memberId = userToken.getMemberId();
            com.qding.neighbor.rpc.request.GetNearbyTopicsByProjectIdRequest topicsByProjectIdRequest = transfor(com.qding.neighbor.rpc.request.GetNearbyTopicsByProjectIdRequest.class, request);
            topicsByProjectIdRequest.setType(permissionType);
            topicsByProjectIdRequest.setMemberId(memberId);
            topicsByProjectIdRequest.setProjectId(request.getAppUser().getProjectId());
            com.qding.neighbor.rpc.response.data.GetNearbyTopicsByProjectIdResponseData nearbyTopicsByProjectIdResponse = topicRpc.getNearbyTopicsByProjectId(topicsByProjectIdRequest);
            checkAndContinue(nearbyTopicsByProjectIdResponse);
            List<TopicDetailRpcDTO> topicDetailRpcDTOList = nearbyTopicsByProjectIdResponse.getList();
            List<TopicDetail> list = getTopicList(version, topicDetailRpcDTOList);
            data.setTotalCount(nearbyTopicsByProjectIdResponse.getTotalCount());
            data.setTopicList(list);

        } catch (ServiceException e) {
            return handleException(GetNearbyTopicsByProjectIdResponseData.class, e);
        }
        response.setData(data);
        return response;
    }

    @ExplainAnnotation(explain = "通过社区ID获取对应的推荐(热门)话题列表")
    @HTTP(alias = "getRecommendTopicsByProjectId",isNeadProject = true,isNeadToken = true)
    public Response<GetRecommendTopicsByProjectIdResponseData> getRecommendTopicsByProjectId (GetRecommendTopicsByProjectIdRequest request,UserToken userToken) {

        Response<GetRecommendTopicsByProjectIdResponseData> response = new Response<GetRecommendTopicsByProjectIdResponseData>();
        GetRecommendTopicsByProjectIdResponseData data = new GetRecommendTopicsByProjectIdResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        String version = request.getAppDevice().getQdVersion();
        Integer permissionType = getVistorPermissionType(userToken.getMemberId(), request.getAppUser().getProjectId());

        try {
            String memberId = userToken.getMemberId();
            GetHotTopicsByProjectIdRequest hotTopicsByProjectIdRequest = transfor(GetHotTopicsByProjectIdRequest.class, request);
            hotTopicsByProjectIdRequest.setType(permissionType);
            hotTopicsByProjectIdRequest.setMemberId(memberId);
            hotTopicsByProjectIdRequest.setProjectId(request.getAppUser().getProjectId());
            GetHotTopicsByProjectIdResponseData hotTopicsByProjectIdResponse = topicRpc.getHotTopicsByProjectId(hotTopicsByProjectIdRequest);
            checkAndContinue(hotTopicsByProjectIdResponse);
            List<TopicDetailRpcDTO> topicDetailRpcDTOList = hotTopicsByProjectIdResponse.getList();
            List<TopicDetail> list = getTopicList(version, topicDetailRpcDTOList);
            data.setTotalCount(hotTopicsByProjectIdResponse.getTotalCount());
            data.setTopicList(list);

        } catch (ServiceException e) {
            return handleException(GetRecommendTopicsByProjectIdResponseData.class, e);
        }
        response.setData(data);
        return response;
    }


    @ExplainAnnotation(explain = "通过活动标签ID获取对应的话题列表")
    @HTTP(alias = "getRecommendTopicsByTagId",isNeadProject = true,isNeadToken = true)
    public Response<GetRecommendTopicsByTagIdResponseData> getRecommendTopicsByTagId (GetRecommendTopicsByTagIdRequest request,UserToken userToken) {

        Response<GetRecommendTopicsByTagIdResponseData> response = new Response<GetRecommendTopicsByTagIdResponseData>();
        GetRecommendTopicsByTagIdResponseData data = new GetRecommendTopicsByTagIdResponseData();
        String version = request.getAppDevice().getQdVersion();

        try {
            String memberId = userToken.getMemberId();
            VerifyRule verifyRule = verifyRule(request.getAppDevice().getQdVersion(),request.getThemeId(), userToken.getMemberId(), request.getAppUser().getProjectId(), 1);
            if (verifyRule.getCanUse().intValue() <= 0) {
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                data.setMessage(verifyRule.getRemindMsg());
                response.setData(data);
                return response;
            }

            if(request.getPageNo()==1){ //第一页提供标签详情信息给APP
                List<ThemeTagInfo>  tagList = verifyRule.getTagList();
                if(CollectionUtils.isNotEmpty(tagList)){
                    for (ThemeTagInfo themeTagInfo : tagList) {
                        if(themeTagInfo.getTagId().equals(request.getTagId())){
                            if(themeTagInfo.getTagStatus() == 5){
                                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                                data.setMessage("相关内容已被删除");
                                response.setData(data);
                                return response;
                            } else {
                                data.setTag(themeTagInfo);
                            }
                        }
                    }
                }
            }
            GetTopicsByTagIdRequest topicsByTagIdRequest = transfor(GetTopicsByTagIdRequest.class,request);
            topicsByTagIdRequest.setMemberId(memberId);
            GetTopicsByTagIdResponseData topicsByTagIdResponse = topicRpc.getTopicsByTagId(topicsByTagIdRequest);
            checkAndContinue(topicsByTagIdResponse);
            List<TopicDetailRpcDTO> topicDetailRpcDTOList = topicsByTagIdResponse.getList();
            List<TopicDetail> list = getTopicList(version, topicDetailRpcDTOList);
            data.setTotalCount(topicsByTagIdResponse.getTotalCount());
            data.setTopicList(list);

        } catch (Exception e) {
            return handleException(GetRecommendTopicsByTagIdResponseData.class, e);
        }

        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

    @ExplainAnnotation(explain = "获取全局发帖时显示的标题")
    @HTTP(alias = "getGlobalThemeTitle")
    public Response<GetGlobalThemeTitleResponseData> getGlobalThemeTitle(GetGlobalThemeTitleRequest request){
        Response<GetGlobalThemeTitleResponseData> response = new Response<GetGlobalThemeTitleResponseData>();
        GetGlobalThemeTitleResponseData data = new GetGlobalThemeTitleResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        String publishTitle = null;
        try {
            publishTitle = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_NEIGHBOR_PUBLISH_TITLE.getGroupName(),Constant.Dict_K_V_Enum.DICT_NEIGHBOR_PUBLISH_TITLE.getDictKey());
            data.setTitle(publishTitle);
            response.setData(data);
        } catch (TException e) {
            e.printStackTrace();
        }
        return response;
    }

    @HTTP(alias = "checkContainMultiProjectsForTheme")
    @ExplainAnnotation(explain = "检查指定主题站是否有多个社区")
    public Response<GetThemeContainMultiProjectResponse> getThemeContainMultiProject(GetThemeContainMultiProjectRequest request) {

        Response<GetThemeContainMultiProjectResponse> response = new  Response<GetThemeContainMultiProjectResponse>();
        GetThemeContainMultiProjectResponse data = new GetThemeContainMultiProjectResponse();

        try {
            GetThemeByIdRequest getThemeByIdRequest = new GetThemeByIdRequest();
            getThemeByIdRequest.setThemeId(request.getThemeId());
            getThemeByIdRequest.setSelProjects(true);
            GetThemeDetailByIdResponse themeByIdResponse = themeRpc.selThemeDetailById(getThemeByIdRequest);
            checkAndContinue(themeByIdResponse);
            data.setMultiProject( themeByIdResponse.getTheme().getMultiProject());
        } catch (ServiceException e) {
            return handleException(GetThemeContainMultiProjectResponse.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

}
