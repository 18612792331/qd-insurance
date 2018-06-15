package com.qding.api.call.app.qding.v2_4_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetFeedByCommunityIdResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.hotcycle.FeedsBean;
import com.qding.api.call.app.qding.v2_4_0.struct.hotcycle.request.GetFeedDetailListRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.hotcycle.response.data.GetFeedDetailListResponseData;
import com.qding.api.struct.Response;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hotcycle.service.IHotCycleRemoteService;
import com.qding.hotcycle.struct.Feed;
import com.qding.hotcycle.struct.request.*;
import com.qding.hotcycle.struct.response.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * Created by qd on 2015/10/23.
 */
public class CallHotcycle extends com.qding.api.call.app.qding.v2_3_0.CallHotcycle {


    @Autowired
    private IHotCycleRemoteService hotCycleService;

    @Autowired
    private ProjectReadRemote projectReadService;


    @HTTP(alias="getFeedDetailList")
    @ExplainAnnotation (explain = "获取图文详情信息流")
    @Deprecated
    public Response<GetFeedDetailListResponseData> getFeedDetailList (GetFeedDetailListRequest request) {

        Response<GetFeedDetailListResponseData> response =  checkFeedStatus (request.getFeedId());
        if (QDStringUtil.isNotNull(response) && HttpStatus.OK.getStatusCode() != response.getCode()) {
            return  response;
        }

        GetFeedDetailListResponseData data = new GetFeedDetailListResponseData();
        List<com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed> feedList = Lists.newArrayList();
        try {
            Map<String,FeedsBean>  feedsBeanMap = null;
            switch (request.getType()) {

                case 1://广场图文信息列表
                    feedsBeanMap = getFeedsByProjectId( request);
                    break;
                case 2:  //标签
                    feedsBeanMap = getFeedsByTagId(request);
                    break;
                case 3: //活动
                    feedsBeanMap = getFeedsByActivityId (request);
                    break;
                case 4: //群朋友圈
                    feedsBeanMap = getFeedsByGcRoomId (request);
                    break;
               default:
                        break;

            }

            if (QDStringUtil.isNotNull(feedsBeanMap) && feedsBeanMap.size()>0){

                for (String key : feedsBeanMap.keySet()) {
                    feedsBeanMap.get(key).setShowCommentSize(request.getShowCommentSize());
                    feedsBeanMap.get(key).setShowPraiseSize(request.getShowPraiseSize());
                    feedList = fittingFeeds (feedsBeanMap.get(key));
                    data.setTotalCount(feedsBeanMap.get(key).getTotalCount());
                    if ("l".equals(key)){
                       data.setlList(feedList);
                    } else {
                        data.setrList(feedList);
                    }
                }
            }

            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);

            return response;

        } catch (ServiceException e) {
            return handleException(GetFeedDetailListResponseData.class, e);
        }

    }


    /**
     * 验证当前切入点的feed是否存在，如果不存在直接返回异常
     * @param feedId
     * @return
     */
    private  Response<GetFeedDetailListResponseData>  checkFeedStatus (String feedId){

        Response<GetFeedDetailListResponseData> response = new  Response<GetFeedDetailListResponseData>();
        GetFeedByFeedIdRequest feedByFeedIdRequest = new GetFeedByFeedIdRequest();
        feedByFeedIdRequest.setFeedId(feedId);
        GetFeedByFeedIdResponse feedByFeedIdResponse = hotCycleService.getFeedByFeedId( feedByFeedIdRequest );
        if ( HttpStatus.OK.getStatusCode() != feedByFeedIdResponse.getReturnInfo().getCode()) {
            response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            GetFeedDetailListResponseData data = new GetFeedDetailListResponseData();
            data.setMessage("当前信息可能已被冻结或删除");
            response.setData(data);
        }
        return  response;
    }


    /**
     * 获取广场场景图文详情信息流
     * @param request
     * @return
     * @throws ServiceException
     */
    private Map<String,FeedsBean> getFeedsByProjectId(GetFeedDetailListRequest request) throws  ServiceException{

        GetFeedByCommunityIdRequest getFeedByCommunityIdRequest = new GetFeedByCommunityIdRequest();
        getFeedByCommunityIdRequest.setPageSize(request.getPageSize());
        getFeedByCommunityIdRequest.setFeedId(request.getFeedId());
        getFeedByCommunityIdRequest.setChangeType(request.getChangeType());
        getFeedByCommunityIdRequest.setCommunityId(request.getId());
        GetFeedByCommunityIdResponse getFeedByCommunityIdResponse = hotCycleService.getFeedByCommunityId(getFeedByCommunityIdRequest);
        checkAndContinue(getFeedByCommunityIdResponse);
        List<Feed> lfeeds = getFeedByCommunityIdResponse.getlFeeds();
        List<Feed> rfeeds = getFeedByCommunityIdResponse.getrFeeds();
        FeedsBean lFeedsBean = new FeedsBean();
        FeedsBean rFeedsBean = new FeedsBean();
        lFeedsBean.setFeedList(lfeeds);
        rFeedsBean.setFeedList(rfeeds);
        lFeedsBean.setTotalCount(getFeedByCommunityIdResponse.getTotalCount());
        rFeedsBean.setTotalCount(getFeedByCommunityIdResponse.getTotalCount());
        Map<String,FeedsBean> map = new HashMap<>();
        map.put("l",lFeedsBean);
        map.put("r",rFeedsBean);

        return map;
    }

    /**
     *标签所属信息流
     * @param request
     * @return
     * @throws ServiceException
     */
    private Map<String,FeedsBean> getFeedsByTagId(GetFeedDetailListRequest request) throws  ServiceException {

        GetFeedsByTagIdRequest getFeedsByTagIdRequest = new GetFeedsByTagIdRequest();
        getFeedsByTagIdRequest.setPageSize(request.getPageSize());
        getFeedsByTagIdRequest.setFeedId(request.getFeedId());
        getFeedsByTagIdRequest.setChangeType(request.getChangeType());
        getFeedsByTagIdRequest.setTagId(request.getId());
        GetFeedsByTagIdResponse getFeedsByTagIdResponse = hotCycleService.getFeedsByTagId(getFeedsByTagIdRequest );
        checkAndContinue(getFeedsByTagIdResponse);
        List<Feed> lfeeds = getFeedsByTagIdResponse.getlFeeds();
        List<Feed> rfeeds = getFeedsByTagIdResponse.getrFeeds();
        FeedsBean lFeedsBean = new FeedsBean();
        FeedsBean rFeedsBean = new FeedsBean();
        lFeedsBean.setFeedList(lfeeds);
        rFeedsBean.setFeedList(rfeeds);
        lFeedsBean.setTotalCount(getFeedsByTagIdResponse.getTotalCount());
        rFeedsBean.setTotalCount(getFeedsByTagIdResponse.getTotalCount());
        Map<String,FeedsBean> map = new HashMap<>();
        map.put("l",lFeedsBean);
        map.put("r",rFeedsBean);

        return map;

    }

    /**
     *活动所属信息流
     * @param request
     * @return
     * @throws ServiceException
     */
    private Map<String,FeedsBean> getFeedsByActivityId (GetFeedDetailListRequest request) throws  ServiceException {

        GetFeedsByActivityIdRequest getFeedsByActivityIdRequest = new GetFeedsByActivityIdRequest();
        getFeedsByActivityIdRequest.setFeedId(request.getFeedId());
        getFeedsByActivityIdRequest.setChangeType(request.getChangeType());
        getFeedsByActivityIdRequest.setPageSize(request.getPageSize());
        getFeedsByActivityIdRequest.setActivityId(request.getId());
        GetFeedsByActivityIdResponse feesByActivityIdResponse = hotCycleService.getFeedsByActivityId(getFeedsByActivityIdRequest);
        checkAndContinue(feesByActivityIdResponse);
        List<Feed> lfeeds = feesByActivityIdResponse.getlFeeds();
        List<Feed> rfeeds = feesByActivityIdResponse.getrFeeds();
        FeedsBean lFeedsBean = new FeedsBean();
        FeedsBean rFeedsBean = new FeedsBean();
        lFeedsBean.setFeedList(lfeeds);
        rFeedsBean.setFeedList(rfeeds);
        lFeedsBean.setTotalCount(feesByActivityIdResponse.getTotalCount());
        rFeedsBean.setTotalCount(feesByActivityIdResponse.getTotalCount());
        Map<String,FeedsBean> map = new HashMap<>();
        map.put("l",lFeedsBean);
        map.put("r",rFeedsBean);

        return map;
    }

    /**
     * 群组朋友圈信息流
     * @param request
     * @return
     * @throws ServiceException
     */
    private Map<String,FeedsBean> getFeedsByGcRoomId (GetFeedDetailListRequest request) throws ServiceException {

        GetFeedsByGroupIdRequest getFeedsByGroupIdRequest = new GetFeedsByGroupIdRequest();
        getFeedsByGroupIdRequest.setFeedId(request.getFeedId());
        getFeedsByGroupIdRequest.setChangeType(request.getChangeType());
        getFeedsByGroupIdRequest.setPageSize(request.getPageSize());
        getFeedsByGroupIdRequest.setGcRoomId(request.getId());
        GetFeedsByGroupIdResponse getFeedsByGroupIdResponse = hotCycleService.getFeedsByGroupId(getFeedsByGroupIdRequest);
        checkAndContinue(getFeedsByGroupIdResponse);
        List<Feed> lfeeds = getFeedsByGroupIdResponse.getlFeeds();
        List<Feed> rfeeds = getFeedsByGroupIdResponse.getrFeeds();
        FeedsBean lFeedsBean = new FeedsBean();
        FeedsBean rFeedsBean = new FeedsBean();
        lFeedsBean.setFeedList(lfeeds);
        rFeedsBean.setFeedList(rfeeds);
        lFeedsBean.setTotalCount(getFeedsByGroupIdResponse.getTotalCount());
        rFeedsBean.setTotalCount(getFeedsByGroupIdResponse.getTotalCount());
        Map<String,FeedsBean> map = new HashMap<>();
        map.put("l",lFeedsBean);
        map.put("r",rFeedsBean);

        return map;
    }


    /**
     * 组装对外展示的feed信息流
     * @param
     * @return
     */
    public  List<com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed> fittingFeeds ( FeedsBean  feedsBean){

        List<com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed> feedList = new ArrayList<>();

        if ( CollectionUtils.isNotEmpty(feedsBean.getFeedList()) ) {

            Set<String> projectIds = new HashSet<String>();
            for (com.qding.hotcycle.struct.Feed feed : feedsBean.getFeedList()) {
                projectIds.add(feed.getCommunityId());
            }

            List<Project> projectList = projectReadService.getByIds(projectIds);
            Map<Long,Project> projectMap = new HashMap<>();
            if (CollectionUtils.isNotEmpty(projectIds)) {
                for (Project project : projectList) {
                    projectMap.put(project.getId(),project);
                }
            }

            for(com.qding.hotcycle.struct.Feed feed : feedsBean.getFeedList()) {
                try {
                    Project project = projectMap.get(Long.parseLong(feed.getCommunityId()));
                    feed.setCommunityName((QDStringUtil.isNotNull(project) ? project.getRegionName()+"-" : "" ) +feed.getCommunityName());
                    com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed fd = customerTransfor.transforFeed(feed, feedsBean.getShowPraiseSize(), feedsBean.getShowCommentSize());
                    if(QDStringUtil.isNotNull(fd)) {
                        feedList.add(fd);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }

            }

        }
        return  feedList;
    }

}
