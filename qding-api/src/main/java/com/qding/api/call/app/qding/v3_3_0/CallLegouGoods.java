package com.qding.api.call.app.qding.v3_3_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.qding.api.call.app.qding.v3_0_1.struct.groupon.GradientPriceDto;
import com.qding.api.call.app.qding.v3_2_0.struct.project.GrouponActivity;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.*;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.request.*;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.response.data.*;
import com.qding.api.process.security.UserToken;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.api.struct.ReturnInfo;
import com.qding.framework.common.util.JsonUtil;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.legou.domain.NewsellChannel;
import com.qding.legou.domain.NewsellContentChannelElement;
import com.qding.legou.struct.request.NewsellContentChannelRequest;
import com.qding.legou.struct.response.NewsellContentChannelResponse;
import com.qding.newsell.dto.NewsellContentChannelSubRemoteDto;
import com.qding.newsell.request.ChannelRequest;
import com.qding.newsell.response.ChannelResponse;
import com.qding.newsell.service.ILegouRemoteServiceNew;
import com.qding.promotion.common.domain.PromotionGrouponPrice;
import com.qding.promotion.common.dto.PromotionGrouponConfigDto;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.GetPromotionGrouponDetailRequest;
import com.qding.promotion.common.struct.response.GetPromotionGrouponDetailResponse;
import com.qding.promotion.query.domain.SeckillWareInfo;
import com.qding.promotion.query.enums.PromotionStatusEnum;
import com.qding.promotion.query.request.GetSeckillRequest;
import com.qding.promotion.query.response.GetSeckillResponse;
import com.qding.promotion.query.service.IPromotionQueryService;
import com.qding.solr.service.ISolrCategoryService;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.category.ShowCategory;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.GetShowCategoryResponse;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import com.qding.sysconfig.dto.*;
import com.qding.sysconfig.rpc.request.AppHomeConfigRequest;
import com.qding.sysconfig.rpc.response.AppHomeConfigResponse;
import com.qding.sysconfig.rpc.service.AppHomeConfigRpcService;
import com.qding.sysconfig.rpc.service.MallModuleRpcService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.CategoryEntity;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.request.GetGoodsDetailsRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.response.data.GetGoodsDetailsResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;

import javax.annotation.Resource;

/**
 * Created by qd on 2016/9/6.
 */

public class CallLegouGoods extends com.qding.api.call.app.qding.v3_0_0.CallLegouGoods {



    @Autowired
    private IPromotionQueryService promotionQueryService;

	@Autowired
	private ISolrCategoryService solrCategoryService;
	
    @Autowired
    private MallModuleRpcService mallModuleRpcService;

    @Autowired
    private ILegouRemoteServiceNew legouRemoteServiceNew;


    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private ISolrSkuService solrSku;

    @Resource
    private AppConfigRemote appConfigRemote;


    @Resource
    private AppHomeConfigRpcService appHomeConfigRpcService;


    @Resource
    private IPromotionRemoteService promotionRemoteService;

    @Resource
    private ProjectReadRemote projectReadService;


    private static Logger logger = Logger.getLogger(CallLegouGoods.class);


    @HTTP(alias = "newSellHomePage", isNeadProject = true)
    @ExplainAnnotation(explain = "新零售首页")
    public Response<GetNewSellHomePageResponseData> newSellHomePage(GetNewSellHomePageRequest request,UserToken userToken) {
        Response<GetNewSellHomePageResponseData> response = new Response<GetNewSellHomePageResponseData>();
        GetNewSellHomePageResponseData data = new GetNewSellHomePageResponseData();
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setCode(HttpStatus.OK.getStatusCode());
        try {
            Long projectId = Long.parseLong(request.getAppUser().getProjectId());
            Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());

            //banner
            List<NewSellBanner> newSellBanners =  fittingBannerBoard( projectId,skipModelMap);
            data.setBannerBoard(newSellBanners);

            //栏目
            List<NewSellColumn> newSellColumnList =  fittingColumBoard(projectId, skipModelMap);
            data.setColumnBoard(newSellColumnList);

            //资源位
            List<MarketingBoard> marketingBoards = fittingResourceNicheBoard(projectId,skipModelMap);
            data.setMarketingBoard(marketingBoards);

            //阶梯团购
            GrouponActivity grouponActivity = fittingGrouponActivity(request, userToken, skipModelMap);
            data.setGrouponBoard(grouponActivity);

          //品类推荐
            List<CategoryRecommend>  categoryRecommends = fittingCategoryBoard(projectId);
            data.setCategoryBoard(categoryRecommends);


            //内容栏目
            List<ContentColumn> contentColumnList =fittingContentBoard(projectId, skipModelMap);
            data.setContentBoard(contentColumnList);
            response.setData(data);

        } catch (Exception e) {
            return handleException(GetNewSellHomePageResponseData.class, e);
        }

        return response;
    }


    /**
     * 填充banner资源位板块
     * @param projectId
     * @param skipModelMap
     */
    private   List<NewSellBanner> fittingBannerBoard(Long projectId, Map<String, String> skipModelMap){

        List<NewSellBanner> bannerBoard = Lists.newArrayList();
        List<MallManagementModuleRpcDto> bannerList = mallModuleRpcService.getManagermentModeleList(projectId, 5, 0);
        for (MallManagementModuleRpcDto mallManagementModuleRpcDto : bannerList) {
            NewSellBanner newSellBanner = new NewSellBanner();
            newSellBanner.setBannerImg(mallManagementModuleRpcDto.getBannerIcon());
            Integer contentType = mallManagementModuleRpcDto.getContentType();
            List<MallModuleWareDto> wareList = mallManagementModuleRpcDto.getWareList();
            MallModuleWareDto mallModuleWareDto = wareList.get(0);
            if(contentType == 1) {
                newSellBanner.setSkipModel(skipMode.fittingSkipUrl(skipModelMap, mallModuleWareDto.getLinkedUrl(), 1,0,""));
            } else {
                newSellBanner.setSkuId(mallModuleWareDto.getWareSkuId());
            }
            bannerBoard.add(newSellBanner);
        }
        return bannerBoard;
    }


    /**
     * 填充资源位板块
     * @param projectId
     * @return
     */
    private  List<MarketingBoard> fittingResourceNicheBoard(Long projectId, Map<String, String> skipModelMap) {
        List<MarketingBoard> marketingList = Lists.newArrayList();
        List<MallManagementModuleRpcDto> resourceNicheList = mallModuleRpcService.getManagermentModeleList(projectId, 10, 1);//资源位
        if(CollectionUtils.isNotEmpty(resourceNicheList)){
            int size=resourceNicheList.size();
            if(size%2!=0){
                size--;
            }
            for (int index=0;index<size; index++) {
                MallManagementModuleRpcDto mallManagementModuleRpcDto= resourceNicheList.get(index);
                MarketingBoard marketing = new MarketingBoard();
                marketing.setBackgroundImg(mallManagementModuleRpcDto.getBannerIcon());
                marketing.setDesc(mallManagementModuleRpcDto.getIntroduction());
                marketing.setTitle(mallManagementModuleRpcDto.getTitle());
                Integer contentType = mallManagementModuleRpcDto.getContentType();
                List<MallModuleWareDto> wareList = mallManagementModuleRpcDto.getWareList();
                MallModuleWareDto mallModuleWareDto = wareList.get(0);
                if(contentType == 1) {
                    marketing.setSkipModel(skipMode.fittingSkipUrl(skipModelMap, mallModuleWareDto.getLinkedUrl(), 1,0,""));
                } else {
                    marketing.setSkuId(mallModuleWareDto.getWareSkuId());
                }
                marketingList.add(marketing);
            }
        }
        return marketingList;
    }




    @HTTP(alias = "getSeckillList")
    @ExplainAnnotation(explain = "获取清仓秒杀商品列表")
    public Response<GetSeckillListResponseData> getSeckillList(GetSeckillListRequest request ) {
        Response<GetSeckillListResponseData>  response = new  Response<GetSeckillListResponseData>();
        GetSeckillListResponseData data = new GetSeckillListResponseData();
        ReturnInfo returnInfo = new ReturnInfo();
        returnInfo.setCode(HttpStatus.OK.getStatusCode());
        response.setData(data);
        try {
            GetSeckillRequest getSeckillRequest = new GetSeckillRequest();
            getSeckillRequest.setProjectId(Long.valueOf(request.getAppUser().getProjectId()));
            getSeckillRequest.setProductNo(Constant.PRODUCT_NO_NG);
            getSeckillRequest.setPageNo(request.getPageNo());
            getSeckillRequest.setPageSize(request.getPageSize());
            GetSeckillResponse getSeckillResponse = promotionQueryService.getSeckillWareInfo(getSeckillRequest);
            checkAndContinue(getSeckillResponse);

            List<Seckill> finishSeckill = new ArrayList<>();
            List<Seckill> runningSeckill = new ArrayList<>();
            List<Seckill> commingSeckill = new ArrayList<>();
            for (Map.Entry<PromotionStatusEnum, List<SeckillWareInfo>> entry : getSeckillResponse.getSeckillWareMap().entrySet()) {
                PromotionStatusEnum statusEnum = entry.getKey();
                for (SeckillWareInfo seckillWareInfo : entry.getValue()) {
                    Seckill seckill = new Seckill();
                    transfor(seckill, seckillWareInfo);
                    convertSeckillStatus(seckill, seckillWareInfo, statusEnum, finishSeckill, runningSeckill, commingSeckill);
                }

            }
            //合并结果
            List<Seckill> resultSeckill = new ArrayList<>();
            resultSeckill.addAll(runningSeckill);
            resultSeckill.addAll(commingSeckill);
            resultSeckill.addAll(finishSeckill);
            data.setList(resultSeckill);
        } catch (Exception e) {
            return handleException(GetSeckillListResponseData.class, e);
        }
        logger.info(String.format("getSeckillList request: %s, response: %s", JsonUtil.Java2Json(request), JsonUtil.Java2Json(response)));
        return response;

    }

    @HTTP(alias = "getShowCategory")
    @ExplainAnnotation(explain = "获取展示品类列表")
    public Response<GetShowCategoryResponseData> getShowCategory (GetShowCategoryRequest request) {

        Response<GetShowCategoryResponseData> response = new Response<GetShowCategoryResponseData>();
        GetShowCategoryResponseData data = new GetShowCategoryResponseData();
        try {
        	Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());

			List<CategoryEntity> list = this.getCategoryEntityList(request.getProjectId(),salePlatform);
			data.setCategorList(list);
			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());
        } catch (Exception e) {
            return handleException(GetShowCategoryResponseData.class, e);
        }
        return response;
    }

    @HTTP(alias = "getGoodsByProjectId",isNeadProject = true)
    @ExplainAnnotation(explain = "" )
    public Response<GetGoodsByProjectIdResponse> 通过社区ID获取商品列表getGoodsByProjectId(GetGoodsByProjectIdRequest request ) {

        Response<GetGoodsByProjectIdResponse> response = new  Response<GetGoodsByProjectIdResponse>();
        GetGoodsByProjectIdResponse data = new GetGoodsByProjectIdResponse();
        if(Constant.MAX_PAGE.intValue()<request.getPageNo()) {
            data.setMessage("商品下拉最多就展示到这里啦");
            response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            response.setData(data);
            return  response;
        }

        LegouSkuRequest skuRequest = transfor(LegouSkuRequest.class, request);
        try {
            skuRequest.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
            Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
            skuRequest.setSellPlatform(salePlatform);
            skuRequest.setFindSellNum(true);
            skuRequest.setMain(true);
            skuRequest.setIsAppSearch(1);
            skuRequest.setExtQuery("NOT markingCode:("+Constant.MRX_PSF_MAKING_CODE+"  "+ Constant.NEWSE_PSF_MAKING_CODE+")");
            List<Integer> statusList = Lists.newArrayList();
            statusList.add(1);
            skuRequest.setStatus(statusList);
            LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
            List<Goods> goods = transforList(Goods.class, skuResponse.getSkus());
            data.setList(goods);
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
            return response;

        } catch (Exception e) {
            return handleException(GetGoodsByProjectIdResponse.class, e);
        }
    }



    @HTTP(alias = "getGoodsByCategoryId")
    @ExplainAnnotation(explain = "获取指定供货商指定品类下商品列表")
    public Response<GetGoodsByCategoryIdResponseData> getGoodsByCategoryId(GetGoodsByCategoryIdRequest request ) {
        Response<GetGoodsByCategoryIdResponseData>  response = new  Response<GetGoodsByCategoryIdResponseData>();
        GetGoodsByCategoryIdResponseData data = new GetGoodsByCategoryIdResponseData();
        try {
        	Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
        	LegouSkuRequest skuRequest = transfor(LegouSkuRequest.class, request);
			skuRequest.setSellPlatform(salePlatform);
			skuRequest.setFindSellNum(true);
			skuRequest.setMain(true);
			skuRequest.setIsAppSearch(1);

        	if (StringUtils.isNoneBlank(request.getRevealCategoryId())) {
        		com.qding.solr.struct.request.GetShowCategoryRequest categoryRequest = new com.qding.solr.struct.request.GetShowCategoryRequest();
    			categoryRequest.setId(request.getRevealCategoryId());
    			GetShowCategoryResponse categoryResponse = solrCategoryService.querySingleShowCategory(categoryRequest);
    			List<ShowCategory> categories = categoryResponse.getCategories();
    			List<Long> subCategoryTypeList = Lists.newArrayList();
    			if (QDStringUtil.isNotNull(categories) && categories.size()>0) {
    				ShowCategory category = categories.get(0);
    				String subCategoryStr = category.getRelCategoryId();
    				if (QDStringUtil.isNull(subCategoryStr) || QDStringUtil.isEmpty(subCategoryStr)) {
    					throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "没有关联相应品类");
    				}
    				String[] subCategory =subCategoryStr.split(",");
    				for (int i=0;i<subCategory.length;i++){
    					subCategoryTypeList.add(Long.parseLong(subCategory[i].toString()));
    				}
    			}
    			if(QDStringUtil.isNull(subCategoryTypeList)) {
    				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "分类参数传入错误");
    			}
    			skuRequest.setCategoryIds(subCategoryTypeList);    			
        	}
        	
        	LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
    		List<Goods> goods = transforList(Goods.class, skuResponse.getSkus());
			data.setTotalCount(Integer.parseInt(String.valueOf(skuResponse.getTotal())));
			data.setList(goods);
			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());
			return response;
        	
        } catch (Exception e) {
            return handleException(GetGoodsByCategoryIdResponseData.class, e);
        }
    }


    /**
     * 获取sku详情
     *
     * @param request
     * @return
     */
    @HTTP(alias = "selGoodsDetails")
    @ExplainAnnotation(explain = " 获取商品详情", desc = "仅仅增加团购信息，商品缩略图")
    public Response<GetGoodsDetailsResponseData> getGoodsDetails(GetGoodsDetailsRequest request) {

        Response<GetGoodsDetailsResponseData> response = super.getGoodsDetails(request);
        if (response.getCode() == HttpStatus.OK.getStatusCode()) {
            List<GoodsPromotion> list = response.getData().getEntity().getGoodsPromotionList();
            //3.3新增
            if (list != null && list.size() > 0) {
                String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
                for (GoodsPromotion promotion : list) {
                    if (promotion.getPromotionType() == Constant.PromotionTypeEnum.TYPE_6.getCode()) {
                        SkuGrouponInfo info = new SkuGrouponInfo();
                        info.setStatus(promotion.getStatus());
                        String url = APIPropertiesClient.getUrlContent("h5_groupon_url");
                        String skip = skipMode.fittingSkipUrl(initVersion, url + promotion.getPromotionId(), 0, null);
                        info.setSkipModel(skip);
                        response.getData().getEntity().setGroupon(info);
                        //不能加入购物车
                        response.getData().getEntity().setSupportCart(0);
                        break;
                    }
                }
            }
        }
        return response;

    }

    /**
     * 秒杀商品状态转换
     * @param seckill
     * @param seckillWareInfo
     * @param statusEnum
     */
    private void convertSeckillStatus(Seckill seckill, SeckillWareInfo seckillWareInfo,
                                      PromotionStatusEnum statusEnum, List<Seckill> finishSeckill, List<Seckill> runningSeckill, List<Seckill> commingSeckill){
        if (PromotionStatusEnum.FINISH.getValue().equals(statusEnum.getValue())) {
            seckill.setStatus(1);
            finishSeckill.add(seckill);
        } else if (PromotionStatusEnum.RUNNING.getValue().equals(statusEnum.getValue())) {
            seckill.setStatus(seckillWareInfo.getAvailableStock() != null && seckillWareInfo.getAvailableStock() <= 0 ? 2 : 0);
            runningSeckill.add(seckill);
        } else if (PromotionStatusEnum.COMMING.getValue().equals(statusEnum.getValue())) {
            seckill.setStatus(-1);
            commingSeckill.add(seckill);
        }
    }



    /**
     * 填充栏目板块
     * @param projectId
     * @param skipModelMap
     * @return
     */
    private   List<NewSellColumn> fittingColumBoard(Long projectId, Map<String, String> skipModelMap) {

        List<NewSellColumn> columnBoardList = Lists.newArrayList();
        try {
            ChannelRequest channelRequest = new ChannelRequest();
            channelRequest.setProjectId(projectId);
            ChannelResponse channelResponse = legouRemoteServiceNew.getChannelList(channelRequest);
            checkAndContinue(channelResponse);
            List<NewsellChannel> newsellChannelList = channelResponse.getChannelList();
            for (NewsellChannel newsellChannel : newsellChannelList) {
                NewSellColumn newSellColumn = new NewSellColumn();
                newsellChannel.getChannelType();//栏目内容类型  1:Url ,2:App原生
                newsellChannel.getSkipType();//跳转类型 1:url 2:事件，3:货品
                newSellColumn.setColumnImg(newsellChannel.getImgs());
                newSellColumn.setColumnName(newsellChannel.getTitle());
                newSellColumn.setSkipModel(skipMode.fittingSkipUrl(skipModelMap, newsellChannel.getSkipElement(), 1,0,""));
                columnBoardList.add(newSellColumn);
            }
        } catch (ServiceException ex) {
            outIndexLog("栏目板块",ex,"");
        }

        return columnBoardList;
    }

    /**
     * 填充阶梯团购板块
     * @param request
     * @param userToken
     * @param skipModelMap
     * @return
     * @throws ServiceException
     */
    private GrouponActivity fittingGrouponActivity(GetNewSellHomePageRequest request, UserToken userToken, Map<String, String> skipModelMap) throws ServiceException {
        GrouponActivity grouponActivity  = null;
        String curVersion = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());
        Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
        AppHomeConfigRequest appHomeConfigRequest = new AppHomeConfigRequest();
        appHomeConfigRequest.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
//        appHomeConfigRequest.setType(salePlatform);
        appHomeConfigRequest.setVersion(curVersion);
        if (QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())){
            appHomeConfigRequest.setMemberId(userToken.getMemberId());
        }
        AppHomeConfigResponse appHomeConfigResponse = appHomeConfigRpcService.getAppHomeConfigByRequest(appHomeConfigRequest);
        try {
            checkAndContinue(appHomeConfigResponse);
            List<AppHomeSectionDto> sectionDtoList = appHomeConfigResponse.getAppHomeSectionDtos();
            Project project = projectReadService.get(Long.parseLong(request.getAppUser().getProjectId()));
            for (AppHomeSectionDto appHomeSectionDto : sectionDtoList) {
                String sectionCode = appHomeSectionDto.getCode(); //板块编码值
                if (Constant.AH_GS.equals(sectionCode)) { // 福利铺
                    List<AppHomeConfigDto> appHomeConfigs = appHomeSectionDto.getAppHomeConfigs();//板块具体配置项
                    if (CollectionUtils.isNotEmpty(appHomeConfigs)) {
                        for (AppHomeConfigDto appHomeConfig : appHomeConfigs) {
                            Integer configType = appHomeConfig.getConfigType();
                            // 1.阶梯团购
                            if (configType == 1) {
                                grouponActivity  = getGrouponActivityBoard(project, skipModelMap, appHomeConfig, userToken.getMemberId());
                                grouponActivity.setName(appHomeConfig.getName());
                                grouponActivity.setImgUrl(appHomeConfig.getImgUrl());
                                grouponActivity.setId(appHomeConfig.getProductId());
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            String message = "";
            if(QDStringUtil.isNotNull(appHomeConfigResponse.getReturnInfo())) {
                message = appHomeConfigResponse.getReturnInfo().getMessage();
            }
            outIndexLog("阶梯团购板块",ex,message);
        }
        return grouponActivity;
    }

    /**
     * 填充内容栏板块
     * @param projectId
     * @param skipModelMap
     * @return
     */
    private List<ContentColumn>  fittingContentBoard(Long projectId, Map<String, String> skipModelMap) {
        List<ContentColumn> contentColumnList = Lists.newArrayList();
        try {
            NewsellContentChannelRequest newsellContentChannelRequest = new NewsellContentChannelRequest();
            newsellContentChannelRequest.setProjectId(projectId);
            NewsellContentChannelResponse newsellContentChannelResponse = legouRemoteServiceNew.getContentChannelListByProjectId(newsellContentChannelRequest);
            checkAndContinue(newsellContentChannelResponse);
            List<NewsellContentChannelSubRemoteDto>  newsellContentChannelSubRemoteDtoList = newsellContentChannelResponse.getList();
            for (NewsellContentChannelSubRemoteDto newsellContentChannelSubRemoteDto : newsellContentChannelSubRemoteDtoList) {
                ContentColumn contentColumn = transfor(ContentColumn.class,newsellContentChannelSubRemoteDto);
                List<NewsellContentChannelElement>  newsellContentChannelElementList = newsellContentChannelSubRemoteDto.getElementList();
                List<ContentDetail> contents = Lists.newArrayList();
                for (NewsellContentChannelElement newsellContentChannelElement : newsellContentChannelElementList) {
                    ContentDetail contentDetail = new ContentDetail();
                    contentDetail.setContentImg(newsellContentChannelElement.getImgs());
                    Integer skipType = newsellContentChannelElement.getSkipType();
                    if(skipType == 1) {
                        contentDetail.setSkipModel(skipMode.fittingSkipUrl(skipModelMap, newsellContentChannelElement.getSkipElement(), 1,0,""));
                    } else if(skipType == 2) {
                        if(QDStringUtil.isNotEmpty(newsellContentChannelElement.getSkipElement())) {
                            LegouSkuRequest skuRequest = new LegouSkuRequest();
                            ArrayList<Long> sortedSkuIds = new ArrayList<Long>();
                            sortedSkuIds.add(Long.parseLong(newsellContentChannelElement.getSkipElement()));
                            skuRequest.setSortedSkuIds(sortedSkuIds);
                            skuRequest.setProjectId(null);
                            skuRequest.setFindSellNum(true);
                            LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
                            ReturnInfo skuReturnInfo = skuResponse.getReturnInfo();
                            if(skuReturnInfo.getCode() == HttpStatus.OK.getStatusCode()) {
                                List<LegouSkuDetailInfo> legouSkuDetailInfoList = skuResponse.getSkus();
                                if(CollectionUtils.isNotEmpty(legouSkuDetailInfoList)) {
                                    LegouSkuDetailInfo legouSkuDetailInfo = legouSkuDetailInfoList.get(0);
                                    SkuContent sku = transfor(SkuContent.class,legouSkuDetailInfo);
                                    sku.setSalesCount(legouSkuDetailInfo.getCountWareSellNum());
                                    contentDetail.setSku(sku);
                                }
                            }
                        }
                    }
                    contents.add(contentDetail);
                }
                contentColumn.setContents(contents);
                contentColumnList.add(contentColumn);
            }

        } catch (Exception ex) {
            outIndexLog("内容栏目板块",ex,"");
        }
        return contentColumnList;
    }


    /**
     * 填充品类推荐板块
     * @param projectId
     * @return
     */
    private  List<CategoryRecommend>  fittingCategoryBoard(Long projectId) {
        List<CategoryRecommend> categoryRecommendList = Lists.newArrayList();
        try {
            List<MallProjectCategoryRecommendDto> categoryList = mallModuleRpcService.getCategoryListLastest(projectId);
            categoryRecommendList = transforList(CategoryRecommend.class,categoryList);
        } catch (Exception ex) {
            outIndexLog("品类推荐板块",ex,"");
        }
        return categoryRecommendList;
    }


    /**
     * 阶梯团购板块组装
     *
     * @param skipModelMap
     * @param appHomeConfig
     * @return
     */
    private GrouponActivity getGrouponActivityBoard(Project project, Map<String, String> skipModelMap,  AppHomeConfigDto appHomeConfig, String memberId) {

        GrouponActivity grouponActivity = new GrouponActivity();//阶梯团购
        try {
            GetPromotionGrouponDetailRequest getPromotionGrouponDetailRequest = new GetPromotionGrouponDetailRequest();
            getPromotionGrouponDetailRequest.setProjectId(project.getId());
            getPromotionGrouponDetailRequest.setPromotionGrouponId(Long.valueOf(appHomeConfig.getProductId()));
            if(QDStringUtil.isNotEmpty(memberId)) {
                getPromotionGrouponDetailRequest.setMid(memberId);
            }
            GetPromotionGrouponDetailResponse getPromotionGrouponDetailResponse = promotionRemoteService.getPromotionGrouponDetail(getPromotionGrouponDetailRequest);
            if(getPromotionGrouponDetailResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode())
            {
                PromotionGrouponConfigDto promotionGrouponConfigDto = getPromotionGrouponDetailResponse.getPromotionGrouponConfigDto();
                grouponActivity.setBoughtCount(promotionGrouponConfigDto.getSkuBoughtCount());
                grouponActivity.setGradientPrices(Lists.transform(promotionGrouponConfigDto.getPromotionGrouponPrices(), new Function<PromotionGrouponPrice, GradientPriceDto>() {
                    @Override
                    public GradientPriceDto apply(PromotionGrouponPrice input) {
                        GradientPriceDto gradientPriceDto = new GradientPriceDto();
                        gradientPriceDto.setHighCount(input.getIntervalRightCount());
                        gradientPriceDto.setLowCount(input.getIntervalLeftCount());
                        gradientPriceDto.setPrice(input.getPrice());
                        return gradientPriceDto;
                    }
                }));
            }
            String skipModeStr = skipMode.fittingSkipUrl(skipModelMap, appHomeConfig.getContentUrl(), 1, appHomeConfig.getIsShare(),
                    appHomeConfig.getShareTitle(), appHomeConfig.getShareDescription(), appHomeConfig.getShareImgUrl(), appHomeConfig.getContentUrl(), "", appHomeConfig.getProductId());
            grouponActivity.setSkipModel(skipModeStr);
            return grouponActivity;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return grouponActivity;
    }

    /**
     * 首页板块异常日志输出
     */
    private void outIndexLog(String boardName, Exception ex, String desc) {
        logger.error("newsellHomePage ==> " +
                boardName+"is error , desc :" + desc, ex);
    }




}
