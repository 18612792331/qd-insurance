package com.qding.api.call.app.qding.v2_0_0;

import java.util.*;

import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsCommentBean;
import com.qding.api.call.service.PromotionService;
import com.qding.api.call.service.SearchService;
import com.qding.api.call.service.dto.SearchLgDTO;
import com.qding.api.ip.IPUtil;
import com.qding.api.util.APIPropertiesClient;
import com.qding.evaluation.struct.qdapp.bean.EvaluteSourceBatchSaveBean;
import com.qding.evaluation.struct.qdapp.request.EvaluateSourceBatchSaveRequest;
import com.qding.evaluation.struct.qdapp.request.EvaluateSourcesBatchRequest;
import com.qding.evaluation.struct.qdapp.response.EvaluateSourceBatchSaveResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.call.app.qding.v2_0_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.FeaturedImg;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.GoodsDetail;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.OrderWareComment;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.PurchaseUser;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.PurchaseUsersBean;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.SearchGoodBean;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.ServiceGoodBean;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.WareComment;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request.AssociateKeyWordRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request.FeaturedGoodsRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request.GetGoodsDetailsRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request.GetPromotionDetailByIdRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request.GetWareCommentListRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request.PurchaseUserRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request.RecommendGoodRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request.SaveWareCommentRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request.SearchGoodsRequest;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data.AssociateKeyWordResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data.FeaturedGoodsResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data.GetGoodsDetailsResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data.GetPromotionDetailByIdResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data.GetWareCommentListResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data.PurchaseUserResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data.RecommendGoodResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data.SaveWareCommentResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data.SearchGoodsResponseData;
import com.qding.api.call.app.qding.v2_0_0.struct.project.RecommendBoard;
import com.qding.api.call.app.qding.v2_0_0.struct.project.RecommendGood;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.IntegralMessage;
import com.qding.api.imessage.IntegralMessageBeanT;
import com.qding.api.imessage.KeywordNotify;
import com.qding.api.imessage.SearchKeyStatistics;
import com.qding.api.struct.Response;
import com.qding.api.util.ImageUtil;
import com.qding.api.util.ScoreTransfer;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.evaluate.domain.WareEvaluate;
import com.qding.evaluation.model.dto.EvaluateSourcesDto;
import com.qding.evaluation.remote.qdapp.IQdAppRemote;
import com.qding.evaluation.struct.qdapp.response.EvaluateSourcesPageResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hotcycle.contant.Constants;
import com.qding.imessage.common.struct.MsginfoRequest;
import com.qding.imessage.common.struct.MsginfoResponse;
import com.qding.legou.domain.Cart;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.GetOrderDetailByCodeRequest;
import com.qding.legou.struct.request.GetUserCartRequest;
import com.qding.legou.struct.request.IsInCollectRequest;
import com.qding.legou.struct.response.GetOrderDetailByCodeResponse;
import com.qding.legou.struct.response.GetUserCartResponse;
import com.qding.legou.struct.response.IsInCollectResponse;
import com.qding.oder.dto.MemberDto;
import com.qding.order.domain.OrderBase;
import com.qding.order.service.IRemoteOrderService;
import com.qding.order.struct.request.FindBuyedMembersBySkuIdRequest;
import com.qding.order.struct.response.FindBuyedMembersBySkuIdResponse;
import com.qding.order.struct.response.GetOrderBaseResponse;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.promotion.common.dto.PromotionDto;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.GetPromotionDetailRequest;
import com.qding.promotion.common.struct.response.GetPromotionDetailResponse;
import com.qding.solr.service.ISolrKeywordService;
import com.qding.solr.service.ISolrServiceItemService;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.KeywordRequest;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.request.ServiceItemRequest;
import com.qding.solr.struct.request.SkuRequest;
import com.qding.solr.struct.response.KeywordResponse;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.response.ServiceItemResponse;
import com.qding.solr.struct.response.SkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import com.qding.solr.struct.sku.SkuDetailInfo;
import com.qding.sysconfig.dto.PersonRecommendModel;
import com.qding.sysconfig.dto.SpecialWare;
import com.qding.sysconfig.params.PersonRecommendParams;
import com.qding.sysconfig.rpc.response.PersonRecommendResponse;
import com.qding.sysconfig.rpc.service.IndexRecommendRpcService;
import com.qding.sysconfig.rpc.service.PersonRecommendRpcService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by qd on 2015/10/23.
 */
public class CallLegouGoods extends com.qding.api.call.app.qding.v1_4_1.CallLegouGoods {

    private org.apache.log4j.Logger logger =  org.apache.log4j.Logger.getLogger(CallLegouGoods.class);


    @Autowired
    private SearchKeyStatistics searchKeyStatistics;

    @Autowired
    private ISolrSkuService solrSku;

    @Autowired
    private ILegouRemoteService legouRemoteService;

    @Autowired
    private ISolrKeywordService solrKeywordService;

    @Autowired
    private ISolrServiceItemService solrServiceItemService;

    @Autowired
    private IndexRecommendRpcService indexRecommendRpcService;

    @Autowired
    private PersonRecommendRpcService personRecommendRpcService;

    @Autowired
    private IRemoteOrderService orderService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private IPromotionRemoteService promotionRemoteService;

    @Autowired
    private IntegralMessage integralMessage;
    
    @Autowired
    private IQdAppRemote evaluationRemote;
    
    @Autowired
    private ProjectReadRemote projectReadService;
    
    @Autowired
    private IProfileService profileAPI;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private SearchService searchService;

    /**
     * 获取促销大礼包商品信息
     * @param request
     * @return
     */
    @HTTP(alias="getPromotionDetailById")
    @ExplainAnnotation(explain = "获取促销大礼包商品信息")
    public Response<GetPromotionDetailByIdResponseData> getPromotionDetail(GetPromotionDetailByIdRequest request){

        Response<GetPromotionDetailByIdResponseData> response = new  Response<GetPromotionDetailByIdResponseData>();
        GetPromotionDetailByIdResponseData  data = new  GetPromotionDetailByIdResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            GetPromotionDetailResponse promotionDetailResponse = promotionRemoteService.getPromotionDetail(transfor(GetPromotionDetailRequest.class,request));
            checkAndContinue(promotionDetailResponse);
            PromotionDto promotion = promotionDetailResponse.getPromotionDto();
            String skuIdStrs = promotion.getWareIds();
            if (QDStringUtil.isNotEmpty(skuIdStrs)) {
                String[] skuIdArray = skuIdStrs.split(",");
                ArrayList<Long> sortedSkuIds = new ArrayList<Long>();
                for (String skuId : skuIdArray) {
                    sortedSkuIds.add(Long.parseLong(skuId));
                }
                SkuRequest skuRequest = new SkuRequest();
                skuRequest.setSortedSkuIds(sortedSkuIds);
                skuRequest.setFindClickLike(true);
                skuRequest.setFindSellNum(true);
                SkuResponse skuResponse = solrSku.querySku(skuRequest);
                List<SkuDetailInfo> skuDetails = skuResponse.getSkus();
                List<Goods> list = transforList(Goods.class,skuDetails);
                if (CollectionUtils.isNotEmpty(list)) {
                    for (Goods good : list) {
                        getGoodsImgForWaterMark(good,"list");
                    }
                }
                data.setList(list);
                data.setAmounts(promotion.getCounterParams());
                data.setDetail(QDStringUtil.isNotEmpty(promotion.getDetail()) ? promotion.getDetail() : "");
                data.setDisplayImg(promotion.getDisplayImg());
                data.setPromotionName(promotion.getName());
            } else {
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                data.setMessage("没有找到相关的商品信息");
            }
            response.setData(data);
        } catch (ServiceException e) {
            return handleException(GetPromotionDetailByIdResponseData.class, e);
        }
        return response;
    }

    /**
     * 特色商品列表
     * @param request
     * @return
     */
    @HTTP(alias="featuredGoods",isNeadProject = true)
    @ExplainAnnotation(explain = "特色商品列表")
    public Response<FeaturedGoodsResponseData> featuredGoods (FeaturedGoodsRequest request) {

        Response<FeaturedGoodsResponseData> response = new Response<FeaturedGoodsResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        SpecialWare specialWare = indexRecommendRpcService.findSpecialWareByRecommendId(request.getRecommendId());
        FeaturedGoodsResponseData featuredGoodsResponseData = transfor(FeaturedGoodsResponseData.class,specialWare);

        List<String> pics = specialWare.getPics();
        List<FeaturedImg> imgs = Lists.newArrayList();
        for (int i =0;i<pics.size();i++){
            FeaturedImg featuredImg = new FeaturedImg();
            featuredImg.setImageUrl(ImageUtil.LgImg(pics.get(i),3));
            imgs.add(featuredImg);
        }

        List<String> skuIds = specialWare.getSkuIds();
        ArrayList<Long> sortedSkuIds = new ArrayList<Long>();
        //假分页
        int offset = getOffset(request.getPageNo(),request.getPageSize());
        for (int i=offset;i<offset+request.getPageSize(); i++) {
            if (i>=skuIds.size()) break;
            sortedSkuIds.add(Long.parseLong(skuIds.get(i)));
        }
        SkuRequest skuRequest = new SkuRequest();
        skuRequest.setSortedSkuIds(sortedSkuIds);
        skuRequest.setFindClickLike(true);
        skuRequest.setFindSellNum(true);
        String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
        if (Integer.parseInt(initVersion) <310000) {
            skuRequest.setExtQuery("NOT markingCode:("+ Constant.MRX_MAKING_CODE +" "+Constant.MRX_PSF_MAKING_CODE+")");
        } else {
            skuRequest.setExtQuery("NOT markingCode:("+Constant.MRX_PSF_MAKING_CODE+")");
        }

        SkuResponse skuResponse = solrSku.querySku(skuRequest);
        List<SkuDetailInfo> skuDetails = skuResponse.getSkus();
        List<Goods> list = transforList(Goods.class,skuDetails);
        Map<Long,String[]> activityInfoArray = null;

        //批量获取商品促销活动
        activityInfoArray = promotionService.getGoodActivityInfoBySkuids(sortedSkuIds,Long.parseLong(request.getAppUser().getProjectId()));

        if (CollectionUtils.isNotEmpty(list)) {
            for (Goods good : list) {
                try {
                    good.setActivityInfo(QDStringUtil.isNotNull(activityInfoArray) && activityInfoArray.containsKey(Long.parseLong(good.getSkuId()))?activityInfoArray.get(Long.parseLong(good.getSkuId())):null);
                } catch (Exception ex) {
                    logger.error("get goods  promotion activity error",ex);
                }

            }
        }
        featuredGoodsResponseData.setList(list);
        featuredGoodsResponseData.setImgs(imgs);
        featuredGoodsResponseData.setTotalCount(skuIds.size());
        response.setData(featuredGoodsResponseData);

        return  response;
    }

    private int getOffset(Integer pageNo, Integer pageSize) {
        if(pageNo == null) {
            return 0;
        }
        if(pageSize == null) {
            return 0;
        }
        if(pageNo > 0) {
            return (pageNo - 1) * pageSize;
        }
        return 0;
    }

    /**
     * 联想关键词
     * @param request
     * @return
     */
    @HTTP(alias="associateKeyWord")
    @ExplainAnnotation(explain = " 联想关键词",desc = "针对需要传递的参数status 和propCodes 暂时写为固定值，如后期有需要再作为参数传入")
    public Response<AssociateKeyWordResponseData> associateKeyWord (AssociateKeyWordRequest request) {

        Response<AssociateKeyWordResponseData> response = new  Response<AssociateKeyWordResponseData>();
        AssociateKeyWordResponseData data = new AssociateKeyWordResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        String productNoStr = APIPropertiesClient.getValue("associate_keyWord_productNo_v2");
        String[] productNoArray = productNoStr.split(",");
        List<String> propCodes = Arrays.asList(productNoArray);
        try {
            Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
            KeywordResponse keywordResponse = searchService.getSearchAssociateKeyWords(Long.parseLong(request.getProjectId()),request.getKw(),request.getLimit(),propCodes,salePlatform);
            checkAndContinue(keywordResponse);
            data.setList(keywordResponse.getKeywords());
            response.setData(data);
        } catch (Exception e) {
            return handleException(AssociateKeyWordResponseData.class, e);
        }

        return response;
    }

    /**
     * 搜索乐购商品
     * @param request
     * @return
     */
    @HTTP(alias = "searchLgGoods")
    @ExplainAnnotation(explain = "搜索乐购商品")
    @Deprecated
    public Response<SearchGoodsResponseData> searchLgGoods (SearchGoodsRequest request){

        Response<SearchGoodsResponseData> response = new Response<SearchGoodsResponseData>();

        try {
            Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap2, request.getAppDevice());
            LegouSkuRequest skuRequest = transfor(LegouSkuRequest.class, request);
            skuRequest.setSellPlatform(salePlatform);
            skuRequest.setFindSellNum(true);
            skuRequest.setMain(true);
            if (Constant.SALEPLATFORM_APP.intValue() == salePlatform) { //只有APP才支持
                skuRequest.setIsAppSearch(1);
            }

            SearchLgDTO  searchLg = searchService.searchLgItems( skuRequest);
            SearchGoodsResponseData data = new SearchGoodsResponseData();
            SearchGoodBean  goodBean = new SearchGoodBean();
            goodBean.setList(searchLg.getList());
            goodBean.setTotalCount(searchLg.getTotalCount());
            data.setGoods(goodBean);

            ServiceItem serviceItem = new ServiceItem();
            ServiceItemRequest itemRequest = new ServiceItemRequest( request.getProjectId(),  request.getKeyWord(),  serviceItem.getRealValueFromPlatform(brickSourceType));
            ServiceItemResponse serviceItemResponse = solrServiceItemService.query(itemRequest);
            List<com.qding.solr.struct.serviceItem.ServiceItem> serviceItems = serviceItemResponse.getList();
            List<ProjectService> list = Lists.newArrayList();
            for (com.qding.solr.struct.serviceItem.ServiceItem item : serviceItems) {
                ProjectService serviceItemBean = transfor(ProjectService.class,item);
                serviceItemBean.setImageUrl(item.getImgUrl());
                serviceItemBean.setContent(item.getContant());
                list.add(serviceItemBean);
            }

            ServiceGoodBean services = new ServiceGoodBean();
            services.setList(list);
            services.setTotalCount(serviceItemResponse.getTotal());
            data.setServices(services);
            response.setData(data);

            return response;

        } catch (ServiceException e) {
            return handleException(SearchGoodsResponseData.class, e);
        }
    }

    /**
     * 搜索乐购猜你喜欢
     * @param
     * @return
     */
    @HTTP(alias = "getRecommendInfo")
    @ExplainAnnotation(explain = "搜索乐购猜你喜欢")
    public Response<RecommendGoodResponseData>  getRecommendInfo (RecommendGoodRequest reqeust){

        Response<RecommendGoodResponseData> response =  new Response<RecommendGoodResponseData>();
        try {
            Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, reqeust.getAppDevice());
            RecommendGoodResponseData data = new RecommendGoodResponseData();
            PersonRecommendParams personRecommendParams = new PersonRecommendParams();
            personRecommendParams.setMid(reqeust.getMemberId());
            personRecommendParams.setProjectId(Long.parseLong(reqeust.getProjectId()));
            personRecommendParams.setPage(reqeust.getPageNo());
            personRecommendParams.setSize(reqeust.getPageSize());
            personRecommendParams.setPlatform(salePlatform);
            PersonRecommendResponse personRecommendResponse = personRecommendRpcService.getRecommendInfoForAPI(personRecommendParams);
            checkAndContinue(personRecommendResponse);
            RecommendBoard recommendBoard = new RecommendBoard();
            recommendBoard.setTotalCount(personRecommendResponse.getTotalCount());
            List<PersonRecommendModel> personRecommendModelList = personRecommendResponse.getPersonRecommendModelList();
            List<RecommendGood> recommendGoodList = Lists.newArrayList();

            for (PersonRecommendModel personRecommendModel :personRecommendModelList) {

                Integer locationType = personRecommendModel.getLocationType();
                RecommendGood recommendGood = transfor(RecommendGood.class, personRecommendModel);
                //如果是商品
                if (0 == locationType) {

                    if (QDStringUtil.isNotNull(personRecommendModel.getSukId())) {

                        LegouSkuRequest skuRequest = new LegouSkuRequest();
                        skuRequest.setProjectId(Long.parseLong(reqeust.getProjectId()));
                        skuRequest.setFindSellNum(true);
                        ArrayList<Long> sortedSkuIds = new ArrayList<Long>();
                        sortedSkuIds.add(personRecommendModel.getSukId());
                        skuRequest.setSortedSkuIds(sortedSkuIds);
                        String initVersion = skipMode.initVersion(reqeust.getAppDevice().getQdVersion());
                        if (Integer.parseInt(initVersion) <310000) {
                            skuRequest.setExtQuery("NOT markingCode:("+ Constant.MRX_MAKING_CODE +" "+Constant.MRX_PSF_MAKING_CODE+")");
                        }else {
                            skuRequest.setExtQuery("NOT markingCode:("+Constant.MRX_PSF_MAKING_CODE+")");
                        }
                        LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
                        List<LegouSkuDetailInfo> legouSkuDetailInfos = skuResponse.getSkus();

                        Map<Long,String[]> promotionNameMap = null;
                        try {
                            promotionNameMap = promotionService.getGoodActivityInfoBySkuids(sortedSkuIds, Long.parseLong(reqeust.getProjectId()));
                        } catch (Exception ex) {
                            logger.error("get goods  promotion activity error",ex);
                        }


                        if (legouSkuDetailInfos.size() > 0) {
                            LegouSkuDetailInfo detailInfo = legouSkuDetailInfos.get(0);
                            Goods good = transfor(Goods.class, detailInfo);
                            getGoodsImgForWaterMark(good ,"list");
                            recommendGood.setGood(good);
                            if (QDStringUtil.isNotNull(promotionNameMap)) {
                                String[] activityInfo = promotionNameMap.get(Long.parseLong(good.getSkuId()));
                                good.setActivityInfo(activityInfo);//优惠活动
                            }
                        } else {
                            continue;
                        }
                        String skipModeStr = skipMode.fittingSkipModelByOnlyId(reqeust.getAppDevice().getQdVersion(), Constant.SkipNo.SPXQ_5004.toInteger(),String.valueOf(personRecommendModel.getSukId()));
                        recommendGood.setSkipModel(skipModeStr);
                    }

                }
                recommendGoodList.add(recommendGood);
            }
            int count = personRecommendResponse.getTotalCount();
            data.setRecommendList(recommendGoodList);
            data.setTotalCount(count);
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
            return  response;
        }catch ( Exception e) {

            return handleException(RecommendGoodResponseData.class, e);
        }

    }

    /**
     * 获取商品详情
     * @param request
     * @return
     */
    @HTTP(alias = "selGoodsDetails")
    @Deprecated
    @ExplainAnnotation(explain = " 获取商品详情",desc = "新增购买过此货品的用户列表")
    public Response<GetGoodsDetailsResponseData> getGoodsDetails (GetGoodsDetailsRequest request) {

        try {
            Response<GetGoodsDetailsResponseData> response = null;

            LegouSkuRequest skuRequest=transfor(LegouSkuRequest.class, request);

            skuRequest.setFindClickLike(request.getFindClickLike());

            skuRequest.setFindSellNum(request.getFindSellNum());

            skuRequest.setFindSkuStock(request.getFindSkuStock());

            ArrayList<Long> sortedSkuIds = new ArrayList<Long>();

            sortedSkuIds.add(request.getSkuId());

            skuRequest.setSortedSkuIds(sortedSkuIds);

            LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);

            checkAndContinue(skuResponse);

            List<LegouSkuDetailInfo> skuInfoList= skuResponse.getSkus();

            if (skuInfoList ==null || skuInfoList.size() <=0)
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "亲，该商品可能已经下架，请选择其他商品");

            LegouSkuDetailInfo skuDetailInfo = skuInfoList.get(0);

            GoodsDetail goodsDetail = transfor(GoodsDetail.class, skuDetailInfo);
            String[] skuImg = skuDetailInfo.getSkuImgUrl();
            if (QDStringUtil.isNotNull(skuImg)) {
                List<String> skuImgs =Lists.newArrayList();
                for (String img : skuImg) {
                    skuImgs.add(ImageUtil.LgImg(img,2));
                }
                goodsDetail.setSkuImgUrl((String[])skuImgs.toArray(new String[skuImgs.size()]));
            }

            String[] wareImg =skuDetailInfo.getWareImgUrl();
            if (QDStringUtil.isNotNull(wareImg)) {
                List<String> wareImgs =Lists.newArrayList();
                for (String img : wareImg) {
                    wareImgs.add( getGoodsWaterMarkImg ("detail",true,ImageUtil.LgImg(img,2),skuDetailInfo.getWatermarkUrl()));
                }
                goodsDetail.setGoodsImg((String[])wareImgs.toArray(new String[wareImgs.size()]));
            }
            String memberId = request.getMemberId();
            Long projectId = request.getProjectId();

            if (!QDStringUtil.isNull(memberId)){

                IsInCollectRequest isInCollectRequest = new IsInCollectRequest();

                isInCollectRequest.setMid(memberId);

                isInCollectRequest.setProjectId(projectId);

                isInCollectRequest.setWareSkuId(Long.parseLong(goodsDetail.getSkuId()));

                IsInCollectResponse isInCollect = legouRemoteService.isInCollect(isInCollectRequest);

                Boolean checkGoodsIsCollect =  isInCollect.isInCollect();

                if (checkGoodsIsCollect)  goodsDetail.setIsCollect("1");
            }

            String[] activityInfo = promotionService.getGoodActivityInfo(Long.parseLong(goodsDetail.getSkuId()), projectId);

            goodsDetail.setActivityInfo(activityInfo);//优惠活动

            Date endTime = skuDetailInfo.getEndTime();

            Date nowTime = new Date();

            long deltaT=endTime.getTime()-nowTime.getTime();

            goodsDetail.setDiscountTimeLeft(deltaT);

            GetGoodsDetailsResponseData goodsDetailsResponse = new GetGoodsDetailsResponseData();
            response = new Response<GetGoodsDetailsResponseData>();

            if (QDStringUtil.isNotNull(skuDetailInfo.getProviderName())) {
                goodsDetail.setDesc(goodsDetail.getDesc() + "<br><div style=\"color: #ccc;text-align: center\">本商品(服务)由"+skuDetailInfo.getProviderName()+"提供</div>");
            }

            List<PurchaseUser> list= Lists.newArrayList();
            int totalCount =0 ;
            try {
                FindBuyedMembersBySkuIdRequest findBuyedMembersBySkuIdRequest = new FindBuyedMembersBySkuIdRequest();
                findBuyedMembersBySkuIdRequest.setPage(1);
                findBuyedMembersBySkuIdRequest.setSize(6);
                findBuyedMembersBySkuIdRequest.setSkuId(request.getSkuId());

                FindBuyedMembersBySkuIdResponse findBuyedMembersBySkuIdResponse = orderService.findBuyedMembersBySkuId(findBuyedMembersBySkuIdRequest);
                checkAndContinue(findBuyedMembersBySkuIdResponse);
                List<MemberDto> memberDtoList =  findBuyedMembersBySkuIdResponse.getMemberList();
                list = transforList(PurchaseUser.class,memberDtoList);
                totalCount = findBuyedMembersBySkuIdResponse.getTotalCount();

            }catch (Exception e) {
                e.printStackTrace();
            }

            PurchaseUsersBean purchaseUser = new PurchaseUsersBean();
            purchaseUser.setTotalCount(totalCount);
            purchaseUser.setList(list);

            goodsDetail.setPurchaseUser(purchaseUser);
            goodsDetailsResponse.setEntity(goodsDetail);

            if (QDStringUtil.isNotEmpty(memberId) && QDStringUtil.isNotEmpty(String.valueOf(projectId))){
                GetUserCartRequest getCartRequest = new GetUserCartRequest();
                getCartRequest.setMid(memberId);
                getCartRequest.setProjectId(projectId);
                GetUserCartResponse getUserCartResponse = legouRemoteService.getUserCart(getCartRequest);
                List<Cart> catList = getUserCartResponse.getCatList();
                int count = 0;
                if (CollectionUtils.isNotEmpty(catList)){
                    count = catList.size();
                }
                goodsDetailsResponse.setCartCount(count);
            }
            response.setData(goodsDetailsResponse);

            return response;

        } catch (Exception e) {

            return handleException(GetGoodsDetailsResponseData.class, e);
        }

    }

    /**
     * 购买此商品的人列表
     * @param request
     * @return
     */
    @HTTP(alias = "purchaseUser")
    @ExplainAnnotation(explain = "购买此商品的人列表",desc = "注意获取的是购买此货品的会员列表")
    public Response<PurchaseUserResponseData> purchaseUser (PurchaseUserRequest request) {

        Response<PurchaseUserResponseData>  respnse = new  Response<PurchaseUserResponseData>();
        PurchaseUserResponseData data = new PurchaseUserResponseData();
        List<PurchaseUser> list = Lists.newArrayList();
        Integer totalCount = 0;
        try {
            FindBuyedMembersBySkuIdRequest findBuyedMembersBySkuIdRequest = new FindBuyedMembersBySkuIdRequest();
            findBuyedMembersBySkuIdRequest.setPage(request.getPageNo());
            findBuyedMembersBySkuIdRequest.setSize(request.getPageSize());
            findBuyedMembersBySkuIdRequest.setSkuId(request.getSkuId());

            FindBuyedMembersBySkuIdResponse findBuyedMembersBySkuIdResponse = orderService.findBuyedMembersBySkuId(findBuyedMembersBySkuIdRequest);
            checkAndContinue(findBuyedMembersBySkuIdResponse);
            List<MemberDto> memberDtoList =  findBuyedMembersBySkuIdResponse.getMemberList();
            list = transforList(PurchaseUser.class,memberDtoList);
            totalCount = findBuyedMembersBySkuIdResponse.getTotalCount();

        }catch (Exception e) {
            return handleException(PurchaseUserResponseData.class, e);
        }

        data.setTotalCount(totalCount);
        data.setList(list);
        respnse.setData(data);
        respnse.setCode(HttpStatus.OK.getStatusCode());

        return respnse;

    }

    /**
     * 获取订单评价列表
     * @param request
     * @return
     */
    @HTTP(alias="getWareCommentList")
    @ExplainAnnotation(explain = "获取订单评价列表")
    public Response<GetWareCommentListResponseData>  getWareCommentList (GetWareCommentListRequest request) {
        try {
            Response<GetWareCommentListResponseData> response = new Response<GetWareCommentListResponseData>();
            GetWareCommentListResponseData data = new GetWareCommentListResponseData();
            GoodsCommentBean  goodsCommentBean = getWareCommentForIndex(request.getWareId(), request.getPageNo(),  request.getPageSize());
            data.setList(goodsCommentBean.getList());
            data.setTotalCount(goodsCommentBean.getTotalCount());
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;
        }catch (Exception e) {
            return handleException(GetWareCommentListResponseData.class, e);
        }

    }
    

    /**
     * 保存商品评价信息
     * @param request
     * @return
     */
    @HTTP(alias="addWareComment", isRequireAuth=true)
    @ExplainAnnotation(explain = "保存商品评价信息",desc = "多个商品评级，一个评价内容")
    public Response<SaveWareCommentResponseData> saveWareComment (SaveWareCommentRequest request, HttpServletRequest httpServletRequest) {

        SaveWareCommentResponseData  data = new SaveWareCommentResponseData();

        try {
            Response<SaveWareCommentResponseData> response = new Response<SaveWareCommentResponseData>();
            GetOrderBaseResponse  orderBaseResponse =  orderService.getOrderBaseByCode(request.getOrderCode());
            checkAndContinue(orderBaseResponse);
            OrderBase orderBase = orderBaseResponse.getOrderBase();
            if(orderBase.getOrderStatus().intValue() == 401 && orderBase.getMid().equals(request.getMemberId())) { //只有支付完成才能进行评价操作
                List<EvaluteSourceBatchSaveBean> evaluteList = Lists.newArrayList();
            	GetMemberRequest req=new GetMemberRequest();
                req.setMemberId(request.getMemberId());
                GetMemberResponse res=profileAPI.getMemberById(req);
    			checkAndContinue(res);
    			com.qding.brick.pojo.biz.Project p=projectReadService.
            			get(Long.parseLong(request.getAppUser().getProjectId()));
                EvaluteSourceBatchSaveBean evaluteBean=new EvaluteSourceBatchSaveBean();
                evaluteBean.setProductNo("NG");
                evaluteBean.setProductValue("乐购商城");
                evaluteBean.setAnonymousFlag(request.getIsAnonymity());
                evaluteBean.setUserId(request.getMemberId());//memberId 与金龙确认过
                evaluteBean.setUserAccount(request.getUserId());//accountId
            	if(res.getMemberInfo()!=null){
                    evaluteBean.setUserName(res.getMemberInfo().getName());
                    evaluteBean.setUserPhone(res.getMemberInfo().getMobile());
                    String ip = IPUtil.getIpAddress(httpServletRequest);
                    evaluteBean.setUserIp(ip);
            	}
                evaluteBean.setProjectId(p.getId());
                evaluteBean.setProjectName(p.getName());
                evaluteBean.setRegionId(p.getRegionId());
                evaluteBean.setRegionName(p.getRegionName());
                evaluteBean.setSourceValue(request.getComment());
            	OrderWareComment item=request.getList().get(0);
            	if(item.getRate()!=null){
                    evaluteBean.setScore(ScoreTransfer.getScore(item.getRate()));
            	}
            	if(QDStringUtil.isNotNull(item.getScore())) {
                    evaluteBean.setScore(ScoreTransfer.getScore(item.getScore()));
                }
                evaluteBean.setSourceCode(request.getOrderCode());
                evaluteBean.setOriginSourceCode(request.getOrderCode());
                Map<Long,OrderWareComment> wareCommentMap = new HashMap<>();
                for (OrderWareComment item1 : request.getList()) {
                    if (wareCommentMap.containsKey(item1.getWareId())) {
                        continue;
                    } else {
                        wareCommentMap.put(item1.getWareId(),item1);
                    }

                    Set<Long> wareIds = new HashSet<>();
                    wareIds.add(item1.getWareId());
                    SkuRequest skuRequest = new SkuRequest();
                    skuRequest.setWareIds(wareIds);
                    skuRequest.setFindAllStatus(true);
                    skuRequest.setFindWareAllSku(true);
                    SkuResponse skuResponse = solrSku.querySku(skuRequest);
                    checkAndContinue(skuResponse);
                    List<SkuDetailInfo> skuDetailList = skuResponse.getSkus();
                    for (SkuDetailInfo skuDetailInfo : skuDetailList) {
                        EvaluteSourceBatchSaveBean saveEvaluteBean= transfor(EvaluteSourceBatchSaveBean.class,evaluteBean);
                        saveEvaluteBean.setSourceName(skuDetailInfo.getName());
                        saveEvaluteBean.setSourceCode(skuDetailInfo.getSkuId());
                        saveEvaluteBean.setSourceImg(QDStringUtil.isNotNull(skuDetailInfo) && skuDetailInfo.getSkuImgUrl().length > 0 ? skuDetailInfo.getSkuImgUrl()[0] : Constant.defaultPicForOrderListMap.get(Constant.PRODUCT_NO_NG)[0]);
                        evaluteList.add(saveEvaluteBean);
                    }
                }

                EvaluateSourceBatchSaveRequest evaluateSaveRequest = new EvaluateSourceBatchSaveRequest();
                evaluateSaveRequest.setBeans(evaluteList);
                EvaluateSourceBatchSaveResponse sourceBatchSaveResponse  =evaluationRemote.saveBatchEvaluateSource(evaluateSaveRequest);
                checkAndContinue(sourceBatchSaveResponse);

                //评价通知订单
                try{
                	MsginfoRequest msginfoRequest = new MsginfoRequest();
                    msginfoRequest.setMaxRetrycount(8);
                    msginfoRequest.setType(20);
                    msginfoRequest.setToclass("EvaluateCallbackJob");
                    msginfoRequest.setTourl(Constant.PINJIA_NOTICE_ORDER);
                    msginfoRequest.setMsgBody(request.getOrderCode());
                    msginfoRequest.setName("商品评价完成后修改订单评价状态");
                    MsginfoResponse msg=integralMessage.sendImessage(msginfoRequest);
                    logger.info("订单评价通知 Imessage Response code:"+msg.getReturnInfo().getCode()+"," +
                            "message:"+msg.getReturnInfo().getMessage());
                }catch(Exception e){
                }
                //埋点 订单评价送积分规则
                try {
                    GetOrderDetailByCodeRequest getOrderRequest = new GetOrderDetailByCodeRequest();
                    getOrderRequest.setOrderCode(request.getOrderCode());
                    GetOrderDetailByCodeResponse getOrderResposne = legouRemoteService.getOrderDetailByCode(getOrderRequest);
                    Long projectId = getOrderResposne.getLegouOrderDetailDto().getLegouOrder().getProjectId();
                    IntegralMessageBeanT integralMessageBeanT = new IntegralMessageBeanT(request.getMemberId(), Constant.INTEGRAL_EVALUATE,request.getOrderCode(),projectId,System.currentTimeMillis(),Constant.INCOME_OPT_TYPE,null,request.getOrderCode());
                    integralMessage.assembleIntegralMessage(integralMessageBeanT);
                    data.setToast(Constant.integralToastMap.get(Constant.INTEGRAL_EVALUATE));

                } catch (Exception e) {

                }

            }

            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
           return response;

        }catch (Exception e) {

            return handleException(SaveWareCommentResponseData.class, e);
        }

    }


    /************************************************华丽的私有方法分割线****************************************************************/


    /**
     * 乐购商品首页待显示的评论信息
     * @param wareId
     * @return
     */
    public GoodsCommentBean getWareCommentForIndex(Long wareId,Integer pageNo, Integer pageSize) {

        GoodsCommentBean wareComments = new GoodsCommentBean();
        wareComments.setTotalCount(0);
        wareComments.setList(Lists.<WareComment>newArrayList());

        try {
            LegouSkuRequest legouSkuRequest = new LegouSkuRequest();
            Set<Long> wareIds = new HashSet<>();
            wareIds.add(wareId);
            legouSkuRequest.setFindAllStatus(true);
            legouSkuRequest.setFindWareAllSku(true);
            legouSkuRequest.setWareIds(wareIds);
            LegouSkuResponse legouSkuResponse = solrSku.queryLegouSku(legouSkuRequest);
            checkAndContinue(legouSkuResponse);
            List<LegouSkuDetailInfo> skus = legouSkuResponse.getSkus();
            List<String> skuIds = Lists.newArrayList();
            if (CollectionUtils.isNotEmpty(skus)) {
                for (LegouSkuDetailInfo sku : skus) {
                    skuIds.add(sku.getSkuId());
                }
                //切换到评价平台
                EvaluateSourcesBatchRequest re=new EvaluateSourcesBatchRequest();
                re.setSourceCodeList(skuIds);
                re.setPageNo(pageNo);
                re.setPageSize(pageSize);
                EvaluateSourcesPageResponse res=evaluationRemote.queryEvaluateBatchSourcesPage(re);
                checkAndContinue(res);
                List<EvaluateSourcesDto> list=res.getPage().getList();
                List<WareEvaluate> wareEvaluateList=new ArrayList<WareEvaluate>();
                for(EvaluateSourcesDto bd:list){
                    WareEvaluate e=new WareEvaluate();
                    e.setUserId(bd.getUserAccount());//可能为空
                    e.setMid(bd.getUserId());//memberId
                    e.setWareId(wareId);
                    e.setOrderCode(bd.getOriginSourceCode());
                    e.setContent(bd.getSourceValue());
                    e.setRate(ScoreTransfer.getRate(bd.getScore()));
                    e.setIsAnonymous(bd.getAnonymousFlag());
                    e.setProjectName(bd.getProjectName());
                    e.setRegionName(bd.getRegionName());
                    e.setCreateAt(bd.getCreateAt());
                    e.setMemberName(bd.getUserName());
                    wareEvaluateList.add(e);
                }
                List<WareComment> wareCommentList = getWareEvaluateInfoList(wareEvaluateList);
                wareComments.setList(wareCommentList);
                wareComments.setTotalCount(res.getPage().getTotalRow());
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return wareComments;
    }


    /**
     * 组装商品评论列表
     * @param wareEvaluateList
     * @return
     */
    public List<WareComment> getWareEvaluateInfoList(List<WareEvaluate> wareEvaluateList) {

        List<WareComment> wareCommentList = Lists.newArrayList();

        for (WareEvaluate wareEvaluate : wareEvaluateList) {

            try {
                WareComment wareComment = transfor(WareComment.class,wareEvaluate);

                GetMemberRequest req=new GetMemberRequest();
                req.setMemberId(wareEvaluate.getMid());
                GetMemberResponse res=profileAPI.getMemberById(req);
    			checkAndContinue(res);
    			
                MemberInfo memberInfo = res.getMemberInfo();

                String name = wareEvaluate.getMemberName();

                if(wareEvaluate.getIsAnonymous() ==0){
                    name ="匿名用户";
                }
                wareComment.setMemberId(memberInfo.getId());
                
                wareComment.setUserHeadImageUrl(memberInfo.getHeadImg());

                wareComment.setUserName(QDStringUtil.isNotEmpty(name)?name: Constants.DEFAULT_NICK_NAME);

                wareComment.setPublishTime(wareEvaluate.getCreateAt());

                wareComment.setIsAnonymity(wareEvaluate.getIsAnonymous());

                wareComment.setProjectDesc((QDStringUtil.isNotNull(wareEvaluate.getRegionName())?wareEvaluate.getRegionName():"")+
                        (QDStringUtil.isNotNull(wareEvaluate.getProjectName())?wareEvaluate.getProjectName():""));

                wareCommentList.add(wareComment);

            }catch (Exception ex) {

                continue;
            }

        }
        return  wareCommentList;
    }

}
