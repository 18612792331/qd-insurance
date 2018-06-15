package com.qding.api.call.app.qding.v3_0_1;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v3_0_0.CallNeighbor;
import com.qding.api.call.app.qding.v3_0_1.struct.groupon.GradientPriceDto;
import com.qding.api.call.app.qding.v3_0_1.struct.groupon.GrouponDetailDto;
import com.qding.api.call.app.qding.v3_0_1.struct.groupon.request.GoodsIdRequest;
import com.qding.api.call.app.qding.v3_0_1.struct.groupon.request.GrouponDetailRequest;
import com.qding.api.call.app.qding.v3_0_1.struct.groupon.response.data.GoodsInfoResponseDta;
import com.qding.api.call.app.qding.v3_0_1.struct.groupon.response.data.GrouponDetailResponseDta;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.service.order.IGetProductOrderService;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.insurance.rpc.IInsuranceOrderListRpcService;
import com.qding.promotion.common.domain.PromotionGrouponImg;
import com.qding.promotion.common.domain.PromotionGrouponPrice;
import com.qding.promotion.common.dto.PromotionGrouponConfigDto;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.GetPromotionGrouponDetailRequest;
import com.qding.promotion.common.struct.response.GetPromotionGrouponDetailResponse;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.SkuRequest;
import com.qding.solr.struct.response.SkuResponse;
import com.qding.solr.struct.sku.SkuDetailInfo;

/**
 * Created by jinhaishan on 17/4/14.
 */
@ExplainAnnotation(explain = "阶梯团购")
public class CallGroupon extends Callable {

    private static Logger logger = Logger.getLogger(CallNeighbor.class);

    @Resource
    private IPromotionRemoteService promotionRemoteService;

    @Resource
    private ISolrSkuService solrSku;


    //通用业态订单状态获取RPC
    @Autowired
    private IGetProductOrderService cbRpcSerivce;


    //新旅游状态获取RPC
    @Autowired
    private IGetProductOrderService newTravelRpcService;


    @Autowired
    private IGetProductOrderService touismServiceRpcService;


    //聚合业态状态获取RPC
    @Autowired
    private IGetProductOrderService csRpcService;

    //保险订单状态获取RPC
    @Autowired
    private IInsuranceOrderListRpcService insuranceSkuService;

    @ExplainAnnotation(explain = "获取团购详情页")
    @HTTP(alias = "getGrouponDetailDto")
    public Response<GrouponDetailResponseDta> getGrouponDetailDto(GrouponDetailRequest request, UserToken userToken) {

        GrouponDetailResponseDta data = null;
        try {
            GetPromotionGrouponDetailRequest getPromotionGrouponDetailRequest = new GetPromotionGrouponDetailRequest();
            getPromotionGrouponDetailRequest.setCurt(request.isCurt());
            getPromotionGrouponDetailRequest.setProjectId(request.getProjectId());
            getPromotionGrouponDetailRequest.setPromotionGrouponId(request.getId());
            getPromotionGrouponDetailRequest.setMid(QDStringUtil.isNotNull(userToken)&& QDStringUtil.isNotNull(userToken.getMemberId())?userToken.getMemberId():null);
            GetPromotionGrouponDetailResponse getPromotionGrouponDetailResponse = promotionRemoteService.getPromotionGrouponDetail(getPromotionGrouponDetailRequest);
            checkAndContinue(getPromotionGrouponDetailResponse);
            PromotionGrouponConfigDto promotionGrouponConfigDto = getPromotionGrouponDetailResponse.getPromotionGrouponConfigDto();
            Long skuId = promotionGrouponConfigDto.getSkuId();
            SkuRequest skuRequest = new SkuRequest();
            skuRequest.setSortedSkuIds(Lists.newArrayList(skuId));
            SkuResponse skuResponse = solrSku.querySku(skuRequest);
             data = conver2GrouponDetailResponseDta(promotionGrouponConfigDto, skuResponse);

        } catch (ServiceException e) {
            return handleException(GrouponDetailResponseDta.class, e);
        }
        Response<GrouponDetailResponseDta> response = new Response<>();
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

    private GrouponDetailResponseDta conver2GrouponDetailResponseDta(PromotionGrouponConfigDto promotionGrouponConfigDto, SkuResponse skuResponse) {

        GrouponDetailDto grouponDetailDto = new GrouponDetailDto();
        grouponDetailDto.setId(promotionGrouponConfigDto.getId());
        grouponDetailDto.setName(promotionGrouponConfigDto.getName());
        grouponDetailDto.setDescription(promotionGrouponConfigDto.getDescription());
        grouponDetailDto.setImgUrl(promotionGrouponConfigDto.getImgUrl());
        grouponDetailDto.setStartTime(promotionGrouponConfigDto.getStartTime());
        grouponDetailDto.setEndTime(promotionGrouponConfigDto.getEndTime());
        grouponDetailDto.setProductNo(promotionGrouponConfigDto.getProductNo());
        grouponDetailDto.setSkuId(promotionGrouponConfigDto.getSkuId());
        grouponDetailDto.setSkuName(promotionGrouponConfigDto.getSkuName());
        grouponDetailDto.setSkuType(promotionGrouponConfigDto.getSkuType());
        grouponDetailDto.setMinimumCount(promotionGrouponConfigDto.getSkuGrouponCount());
        grouponDetailDto.setBoughtCount(promotionGrouponConfigDto.getSkuBoughtCount());
        grouponDetailDto.setLimitCount(promotionGrouponConfigDto.getSkuLimitCount());
        grouponDetailDto.setStockCount(promotionGrouponConfigDto.getSkuStockCount());
        grouponDetailDto.setIsCanJoin(promotionGrouponConfigDto.getCanJoin()? 1:0);
        if(CollectionUtils.isNotEmpty(promotionGrouponConfigDto.getPromotionGrouponImgList()))
        {
            grouponDetailDto.setImgUrls(Lists.transform(promotionGrouponConfigDto.getPromotionGrouponImgList(), new Function<PromotionGrouponImg, String>() {
                @Override
                public String apply(PromotionGrouponImg input) {
                    return input.getImgUrl();
                }
            }));
        }

        grouponDetailDto.setGradientPrices(Lists.transform(promotionGrouponConfigDto.getPromotionGrouponPrices(), new Function<PromotionGrouponPrice, GradientPriceDto>() {
            @Override
            public GradientPriceDto apply(PromotionGrouponPrice input) {
                GradientPriceDto gradientPriceDto = new GradientPriceDto();
                gradientPriceDto.setHighCount(input.getIntervalRightCount());
                gradientPriceDto.setLowCount(input.getIntervalLeftCount());
                gradientPriceDto.setPrice(input.getPrice());
                return gradientPriceDto;
            }
        }));
        grouponDetailDto.setIsShare(promotionGrouponConfigDto.getIsShare());
        grouponDetailDto.setShareImgUrl(promotionGrouponConfigDto.getShareImgUrl());
        grouponDetailDto.setShareTitle(promotionGrouponConfigDto.getShareTitle());
        grouponDetailDto.setShareContent(promotionGrouponConfigDto.getShareContent());
        grouponDetailDto.setStatus(promotionGrouponConfigDto.getGrouponStatus());
        grouponDetailDto.setContent(promotionGrouponConfigDto.getContent());
        if (skuResponse != null && CollectionUtils.isNotEmpty(skuResponse.getSkus())) {
            SkuDetailInfo skuDetailInfo = skuResponse.getSkus().get(0);
                grouponDetailDto.setMarketPrice(skuDetailInfo.getPrice());
        }
        grouponDetailDto.setAlreadyBuyCount(promotionGrouponConfigDto.getAlreadyBuyCount());
        GrouponDetailResponseDta groupBuyingDetailResponseDta = new GrouponDetailResponseDta();
        groupBuyingDetailResponseDta.setGroupBuyingDetailDto(grouponDetailDto);
        return groupBuyingDetailResponseDta;
    }


    @ExplainAnnotation(explain = "根据平台商品ID,获取获取各业态对应的商品信息")
    @HTTP(alias = "getGoodsInfo")
    public Response<GoodsInfoResponseDta> getGoodsInfo(GoodsIdRequest request) {
        IGetProductOrderService getProductOrderService = getProductOrderService(request.getProductNo());
        GoodsInfoResponseDta goodsIdResponseDta = new GoodsInfoResponseDta();
        goodsIdResponseDta.setGoodsJson(getProductOrderService.getGoodsJson(request.getSkuId()));
        Response<GoodsInfoResponseDta> response = new Response<>();
        response.setData(goodsIdResponseDta);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

    private IGetProductOrderService getProductOrderService(String productNo) {
        IGetProductOrderService getProductOrderService = null;
        switch (productNo)
        {

            //旅游服务
            case Constant.PRODUCT_NO_TS:
                getProductOrderService = touismServiceRpcService;
                break;

            //通用业态订单
            case Constant.PRODUCT_NO_CB:
                getProductOrderService = cbRpcSerivce;
                break;
            //旅游线路
            case Constant.PRODUCT_NO_TL:
                getProductOrderService = newTravelRpcService;
                break;
                //聚合业态
            case Constant.PRODUCT_NO_CS:
                getProductOrderService = csRpcService;
                break;
              //保险业态
            case Constant.PRODUCT_NO_BX:
                getProductOrderService = insuranceSkuService;
                break;
            default:
                break;
        }
        if(getProductOrderService == null)
        {
            logger.error("没有获取到对应的业态，productNo=" + productNo);
        }
        return getProductOrderService;
    }

}
