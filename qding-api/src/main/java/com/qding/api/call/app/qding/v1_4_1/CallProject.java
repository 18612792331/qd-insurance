package com.qding.api.call.app.qding.v1_4_1;

import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_4_1.struct.project.*;
import com.qding.api.call.app.qding.v1_3_1.struct.project.ActivitySale;
import com.qding.api.call.app.qding.v1_3_1.struct.project.RecommendTag;
import com.qding.api.call.app.qding.v1_4_1.struct.project.ProductSalePolicyBean;
import com.qding.api.call.app.qding.v1_4_1.struct.project.request.GetProjectIndexRequest;
import com.qding.api.call.app.qding.v1_4_1.struct.project.response.data.GetProjectIndexResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.auction.rpc.request.GetRecentAuctionRequest;
import com.qding.auction.rpc.response.GetRecentAuctionResponse;
import com.qding.auction.rpc.service.IAuctionRpcService;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;

import com.qding.groupon.remote.ProductRemoteService;
import com.qding.groupon.struct.bean.ProductBean;
import com.qding.groupon.struct.request.GetProductsRequest;
import com.qding.groupon.struct.response.GetProductsResponse;
import com.qding.hk.rpc.request.notice.GetAppNoticeRequest;
import com.qding.hk.rpc.response.notice.GetAppNoticeResponse;
import com.qding.hk.rpc.service.INoticeRpcService;
import com.qding.hotcycle.service.IHotCycleRemoteService;
import com.qding.hotcycle.struct.request.GetHomeRecommendTagRequest;
import com.qding.hotcycle.struct.response.GetHomeRecommendTagResponse;
import com.qding.sysconfig.domain.ActivityConfig;
import com.qding.sysconfig.domain.ActivityContent;
import com.qding.sysconfig.rpc.model.MarketActivityModel;
import com.qding.sysconfig.rpc.response.MarketActivityResponse;
import com.qding.sysconfig.rpc.service.MarketActivityRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by qd on 2015/10/23.
 */
public class CallProject extends com.qding.api.call.app.qding.v1_3_2.CallProject{


    @Autowired
    private INoticeRpcService noticeService;

    @Autowired
    private AppConfigRemote appConfigRemote;

    @Autowired
    private MarketActivityRpcService marketActivityService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IHotCycleRemoteService hotcycleService;

    @Autowired
    private IAuctionRpcService auctionRpcService;

    @Autowired
    private ProductRemoteService grouponRpcService;

    /**
     * 社区首页
     * @param request
     * @return
     */
    @HTTP(alias="index")
    @Deprecated
    public Response<GetProjectIndexResponseData> index(GetProjectIndexRequest request) {

        try {

            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
            Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
            String serviceItemHome = Constant.transforServiceByAppDevice(Constant.serviceItemHomeMap, request.getAppDevice());
            Response<GetProjectIndexResponseData> response = new Response<>();

            GetAppNoticeRequest noticeRequest = new GetAppNoticeRequest();
            noticeRequest.setPageSize(6);
            noticeRequest.setPosition(2);
            Long projectId = Long.parseLong(request.getProjectId());
            noticeRequest.setProjectId(projectId);
            noticeRequest.setAppDevice(request.getAppDevice());

            /**
             * 社区首页轮播
             */
            GetAppNoticeResponse noticeResponse = noticeService.getAppNoticeList(noticeRequest);

            //头部banner
            List<ActivityBanner> activityBannerFloor1 = transforList(ActivityBanner.class, noticeResponse.getNoticeList());

            //底部banner
            List<ActivityBanner> activityBannerFloor2 = transforList(ActivityBanner.class, noticeResponse.getFooterNoticeList());

            /**
             * 社区首页业态
             */

            String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());

            List<ServiceItem> serviceItems = appConfigRemote.getBindServiceItemListbyProjectId(projectId,
                    serviceItemHome,
                    version,
                    brickSourceType
            );

            List<ProjectService> services = transforList(ProjectService.class, serviceItems);

            /**
             * 社区首页活动
             */
            MarketActivityResponse activityResponse = marketActivityService.getIndexActivity(
                    request.getProjectId(),
                    salePlatform
            );

            List<ActivitySale> activitySales = new ArrayList<>();

            activitySales.addAll(getActivitySale(activityResponse.getMarketActivityModelList()));
            activitySales.addAll(getActivitySale(activityResponse.getSecondMarketActivityModelList()));
            activitySales.addAll(getActivitySale(activityResponse.getThridMarketActivityModelList()));

            AuctionActivity auctionActivity = new AuctionActivity();
            GrouponActivity grouponActivity = new GrouponActivity();
            ActivitySale miaoshaActivity = null;
            ActivitySale travelActivity = null;
            try{
                //夺宝
                GetRecentAuctionResponse recentAuction = auctionRpcService.getRecentAuction(transfor(GetRecentAuctionRequest.class,request));
                if(HttpStatus.OK.getStatusCode() ==(recentAuction.getReturnInfo().getCode()) && QDStringUtil.isNotNull(recentAuction.getAuctionInfo())){

                    auctionActivity = transfor(AuctionActivity.class,recentAuction.getAuctionInfo());
                    auctionActivity.setImageUrl(recentAuction.getAuctionInfo().getImageUrl2());
                    if(QDStringUtil.isNotNull(recentAuction.getAuctionOrder())){
                        auctionActivity.setLastPriceFen(recentAuction.getAuctionOrder().getCurrentPriceFen());
                    }
                    auctionActivity.setUrl(APIPropertiesClient.getUrl("auction")+"/"+auctionActivity.getId());
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }

            try{
                //阶梯团购
                GetProductsRequest getProductRequest = new GetProductsRequest();
                getProductRequest.setProjectId(request.getProjectId());
                getProductRequest.setPageSize(1);
                getProductRequest.setPageNo(1);
                GetProductsResponse getProductResponse = grouponRpcService.getProducts(getProductRequest);
                if(getProductResponse.getProduct().size()>0 && getProductResponse.getCurrentCount()>0){
                    ProductBean productBean = getProductResponse.getProduct().get(0);
                    grouponActivity =transfor(GrouponActivity.class,productBean);
                    grouponActivity.setNum(productBean.getSaleNum());
                    List<com.qding.groupon.struct.bean.ProductSalePolicyBean> lists = productBean.getProductSalePolicyList();
                    grouponActivity = initStage(grouponActivity,productBean.getSaleNum(), lists);
                    grouponActivity.setUrl(APIPropertiesClient.getUrl("groupon")+"/"+productBean.getId());
                    List<String> imagez = grouponActivity.getImagez();
                    String imgUrl =imagez.get(0)+"?imageMogr2/gravity/Center/crop/400x400";
                    imagez.set(0,imgUrl);

                }

            }catch (Exception ex){

            }

            try {
                //秒杀活动
                miaoshaActivity = getActivitySaleObj(activityResponse.getMiaoshaActivity());

            }catch (Exception ex){
                ex.printStackTrace();
            }

            try {
                //旅游
              travelActivity = getActivitySaleObj(activityResponse.getTravelActivity());

            }catch ( Exception ex){

                ex.printStackTrace();
            }

            Iterator<ActivitySale> activitySaleIterator = activitySales.iterator();

            /**
             * 首页推荐标签
             */
            GetHomeRecommendTagResponse recommendTagResponse = hotcycleService.getHomeRecommend(
                    new GetHomeRecommendTagRequest(
                            request.getProjectId(),
                            7
                    ));

            List<RecommendTag> recommendTags = transforList(RecommendTag.class, recommendTagResponse.getList());

            Project project = projectReadService.get(Long.parseLong(request.getProjectId()));

            if(project == null) {

                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
            }

            com.qding.api.call.app.qding.v1_3_0.struct.brick.Project p =
                    transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Project.class, project);

            Set<String> phoneSet = new HashSet<String>();

            List<ProjectConcat> projectConcats = p.getConcats();
            for (ProjectConcat projectConcat : projectConcats) {

                if("1".equals(projectConcat.getType())){

                    String[] phoneStrings = projectConcat.getPhones();
                    for (String phone : phoneStrings) {
                        phoneSet.add(phone);
                    }
                }
            }
            List<String> phoneList = new ArrayList<String>();
            if(phoneSet.size()>0)
                phoneList.addAll(phoneSet);

            GetProjectIndexResponseData data = new GetProjectIndexResponseData(new ProjectIndex(
                    activityBannerFloor1,
                    activityBannerFloor2,
                    services,
                    recommendTags,
                    buildFloor(activitySaleIterator, 3),
                    buildFloor(activitySaleIterator, 3),
                    buildFloor(activitySaleIterator, 3),
                    phoneList,
                    travelActivity,
                    auctionActivity,
                    miaoshaActivity,
                    grouponActivity
            )
            );

            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(GetProjectIndexResponseData.class, e);
        }

    }

    /**
     * 初始化团购阶段
     * @param grouponActivity
     * @param currentNum
     * @param lists
     * @return
     */
    private GrouponActivity initStage(GrouponActivity grouponActivity, int currentNum, List<com.qding.groupon.struct.bean.ProductSalePolicyBean> lists) {

        if (lists.size()>0){

            List<ProductSalePolicyBean> policies =Lists.newArrayList();

            for(int i=0;i<lists.size();i++){

                com.qding.groupon.struct.bean.ProductSalePolicyBean sourceBean = lists.get(i);
                ProductSalePolicyBean productSalePolicyBean1 = transfor(ProductSalePolicyBean.class, sourceBean);
                int fromNum = sourceBean.getFromNum();

                if (currentNum<fromNum){
                    int surplus = fromNum-(currentNum<=0?0:currentNum);
                    productSalePolicyBean1.setDesc("差"+surplus+"件");
                    productSalePolicyBean1.setStatus(0);
                } else {
                    productSalePolicyBean1.setDesc("已达成");
                    productSalePolicyBean1.setStatus(1);
                }
                productSalePolicyBean1.setStage(i+1);
                policies.add(productSalePolicyBean1);
            }
            grouponActivity.setPolicies(policies);
        }

        return grouponActivity;
    }

    private ActivitySale getActivitySaleObj( MarketActivityModel activity) {

        if(activity == null) {
            return new ActivitySale();
        }

        ActivityConfig config = activity.getActivityConfig();
        ActivityContent content = activity.getActivityContent();

        int type = StringUtils.isEmpty(content.getUrl()) ? 1 : 2;


        ActivitySale activitySalesFloor  = new ActivitySale(
                config.getActivityName(),
                content.getActivityDesc(),
                config.getNavigation(),
                type,
                content.getActivityId(),
                content.getUrl()
        );
        return activitySalesFloor;
    }


    private List<ActivitySale> getActivitySale(
            List<MarketActivityModel> activities) {

        List<ActivitySale> activitySalesFloor = new ArrayList<ActivitySale>();

        if(activities == null) {
            return activitySalesFloor;
        }

        for(MarketActivityModel activity : activities) {

            activitySalesFloor.add(getActivitySaleObj(activity));
        }
        return activitySalesFloor;
    }

    private <T> List<T> buildFloor(Iterator<T> iterator, int size) {

        int count = 1;

        List<T> result = new ArrayList<>();

        while(iterator.hasNext() && count <= size) {
            result.add(iterator.next());
            count ++;
        }

        return result;
    }

}
