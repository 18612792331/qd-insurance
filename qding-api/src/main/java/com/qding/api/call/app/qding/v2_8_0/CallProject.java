package com.qding.api.call.app.qding.v2_8_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v2_0_0.struct.project.ProjectIndex;
import com.qding.api.call.app.qding.v2_8_0.struct.project.BannerBoard;
import com.qding.api.call.app.qding.v2_8_0.struct.project.BannerShareInfo;
import com.qding.api.call.app.qding.v2_8_0.struct.project.IndexBanner;
import com.qding.api.call.app.qding.v2_8_0.struct.project.request.GetProjectIndexRequest;
import com.qding.api.call.app.qding.v2_8_0.struct.project.response.data.GetProjectIndexResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hk.domain.ResNotice;
import com.qding.hk.rpc.request.notice.GetAppNoticeRequest;
import com.qding.hk.rpc.response.notice.GetAppNoticeResponse;
import com.qding.hk.rpc.service.INoticeRpcService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by qd on 2016/11/30.
 */
public class CallProject extends  com.qding.api.call.app.qding.v2_0_0.CallProject{

    @Autowired
    private INoticeRpcService noticeService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private AppConfigRemote appConfigRemote;

    private static final Logger logger = Logger.getLogger("CallProject");

    /**
     * 社区首页
     *
     * @param request
     * @return
     */
    @HTTP(alias = "index",isNeadProject = true)
    @ExplainAnnotation(explain = "社区首页")
    @Deprecated
    public Response<GetProjectIndexResponseData> index(GetProjectIndexRequest request, UserToken userToken) {

        return   getIndexResponse ( request,  userToken);
    }


    /**
     * 为了临时增加灰度功能，将整个response功能独立，便于其他版本使用
     * @param request
     * @param userToken
     * @return
     */
    public  Response<GetProjectIndexResponseData>   getIndexResponse (GetProjectIndexRequest request, UserToken userToken) {

        String projectId = request.getAppUser().getProjectId();
        Response<GetProjectIndexResponseData> response = new Response<GetProjectIndexResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());

        try {
            String version = request.getAppDevice().getQdVersion();
            Map<String, String> skipConfigMap = null;
            if (QDStringUtil.isNotEmpty(version)) {
                version = version.trim();
                skipConfigMap = skipMode.selSkipTemplateMap(version);
            } else {
                logger.info("project index can't get version");
            }

            ProjectIndex entity =  fittingIndex(projectId,QDStringUtil.isNotNull(userToken)?userToken.getMemberId():"",request.getAppDevice(),skipConfigMap);


            //常用工具组装
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
            if (QDStringUtil.isNotNull(brickSourceType)) {
                List<ServiceItem> serviceItems = null;
                try {
                    Long startTime = System.currentTimeMillis();
                    serviceItems = appConfigRemote.getIndexPageServiceItem(Long.parseLong(projectId), brickSourceType,version);
                    List<ProjectService> services = Lists.newArrayList();
                    if(CollectionUtils.isNotEmpty(serviceItems)){
                        for (ServiceItem serviceItem : serviceItems) {
                            ProjectService projectService = transfor(ProjectService.class,serviceItem);
                            projectService.setImageUrl(serviceItem.getImgUrlColor());
                            services.add(projectService);
                        }
                    }
                    entity.setProjectServices(services);
                    Long endTime = System.currentTimeMillis();
                    logger.info("==========> get indexPageService total time:" + (endTime - startTime));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //首页Banner
            try {
                Long startTime = System.currentTimeMillis();
                BannerBoard bannerBoard = fittingBanner(skipConfigMap, Long.parseLong(projectId), version,QDStringUtil.isNotNull(userToken)?userToken.getMemberId():"");
                entity.setBannerBoard(bannerBoard);
                Long endTime = System.currentTimeMillis();
                logger.info("==========> get bannerBoard total time:" + (endTime - startTime));
            } catch (Exception e) {
                logger.error("fittingBannerBoard fail ", e);
            }

            GetProjectIndexResponseData data = new GetProjectIndexResponseData();
            data.setEntity(entity);
            response.setData(data);

        } catch (Exception e) {
            return handleException(GetProjectIndexResponseData.class, e);
        }/* finally {
            boardMap = null;
            indexMap = null;
        }*/
        return response;
    }


    /**
     * banner板块组装
     */
    public BannerBoard fittingBanner(Map<String, String> skipConfigMap, Long projectId, String version,String memberId) throws Exception {

        BannerBoard bannerBoard = new BannerBoard();
        List<IndexBanner> bannerList = Lists.newArrayList();
        GetAppNoticeRequest appNoticeRequest = new GetAppNoticeRequest();
        appNoticeRequest.setProjectId(projectId);
        appNoticeRequest.setPosition(32);//30：社区公告  1：表示管家公告  31物业公告  32 banner
        if(QDStringUtil.isNotEmpty(memberId)){
            appNoticeRequest.setMid(memberId);
        }
        GetAppNoticeResponse noticeResponse = noticeService.getAppIndexNoticeList(appNoticeRequest);

        List<ResNotice> noticeList = noticeResponse.getNoticeList();
        if (CollectionUtils.isNotEmpty(noticeList)) {

            for (ResNotice resNotice : noticeList) {
                Integer showPosition = resNotice.getShowPosition();//排序值
                IndexBanner indexBanner = new IndexBanner();
                indexBanner.setImg(resNotice.getBigUrl());
                indexBanner.setId(String.valueOf(resNotice.getId()));
                //如果没有给排序值，则默认放在第四位
                indexBanner.setShowPosition(QDStringUtil.isNull(showPosition)?4:showPosition);

                if (1 == resNotice.getIsShare()) {
                    BannerShareInfo shareInfo = new BannerShareInfo();
                    shareInfo.setShareDesc(resNotice.getShareContent());
                    shareInfo.setShareImg(resNotice.getShareImageUrl());
                    shareInfo.setShareTitle(resNotice.getShareTitle());
                    indexBanner.setShareInfo(shareInfo);
                }
                //如果是社区活动，则获取url
                //如果有url
                if (QDStringUtil.isNotEmpty(resNotice.getUrl())) {
                    String skipStr = skipMode.fittingSkipUrl(skipConfigMap, resNotice.getUrl(), 1, QDStringUtil.isNotNull(resNotice.getIsShare()) ? resNotice.getIsShare() : 0
                            , resNotice.getShareTitle(), resNotice.getShareContent(), resNotice.getShareImageUrl(), resNotice.getUrl(), version,String.valueOf(resNotice.getId()));
                    indexBanner.setSkipModel(skipStr);
                } else {  //没有url，则拼接公告地址访问
                    String noticeUrl = APIPropertiesClient.getSkipUrl(Constant.SkipType.NOTICE_DETAIL.toString());
                    noticeUrl = noticeUrl + resNotice.getId();
                    String skipStr = skipMode.fittingSkipUrl(skipConfigMap, noticeUrl, 1, QDStringUtil.isNotNull(resNotice.getIsShare()) ? resNotice.getIsShare() : 0
                            , resNotice.getShareTitle(), resNotice.getShareContent(), resNotice.getShareImageUrl(), noticeUrl, version,String.valueOf(resNotice.getId()));
                    indexBanner.setSkipModel(skipStr);
                }
                bannerList.add(indexBanner);
            }

            if(bannerList.size()>1){
                Collections.sort(bannerList, new Comparator<IndexBanner>(){
                    public int compare(IndexBanner o1, IndexBanner o2) {
                        if(o1.getShowPosition() > o2.getShowPosition()){
                            return 1;
                        }
                        if(o1.getShowPosition() == o2.getShowPosition()){
                            return 0;
                        }
                        return -1;
                    }
                });
            }
        }
        bannerBoard.setBannerList(bannerList);
        return bannerBoard ;
    }


}
