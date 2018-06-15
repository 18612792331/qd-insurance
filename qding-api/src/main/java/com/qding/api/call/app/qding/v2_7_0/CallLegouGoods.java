package com.qding.api.call.app.qding.v2_7_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.user.response.data.SendVerificationCodeResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsActivity;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsDetail;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.request.GetGoodsDetailsRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.request.GetProviderDetailsRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.response.data.GetGoodsDetailsResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.response.data.GetProviderDetailsResponseData;
import com.qding.api.call.service.PromotionService;
import com.qding.api.constant.Constant;
import com.qding.api.constant.SkuCompanyEnum;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.ImageUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.contract.Supplier;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.contract.SupplierRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.JsonUtil;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.legou.domain.Cart;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.GetUserCartRequest;
import com.qding.legou.struct.request.IsInCollectRequest;
import com.qding.legou.struct.response.GetUserCartResponse;
import com.qding.legou.struct.response.IsInCollectResponse;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import com.qding.sysconfig.domain.ClauseConfig;
import com.qding.sysconfig.rpc.service.ClauseConfigRpcService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by qd on 2016/9/6.
 */
public class CallLegouGoods extends com.qding.api.call.app.qding.v2_5_0.CallLegouGoods {


    @Autowired
    private ISolrSkuService solrSku;

    @Autowired
    private ILegouRemoteService legouRemoteService;

    @Autowired
    private ClauseConfigRpcService clauseConfigRpcService;

    @Autowired
    private SupplierRemote supplierRemote;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private PromotionService promotionService;

    private static Logger logger = Logger.getLogger(CallLegouGoods.class);

    @HTTP(alias = "getProviderDetails")
    @ExplainAnnotation(explain = " 获取供方资质")
    public Response<GetProviderDetailsResponseData> getProviderDetails(GetProviderDetailsRequest request) {
        try {
            GetProviderDetailsResponseData getProviderDetailsResponseData = new GetProviderDetailsResponseData();
            Response<GetProviderDetailsResponseData> response = new Response<GetProviderDetailsResponseData>();
            Supplier supplier = supplierRemote.getSupplierById(request.getProviderId());
            getProviderDetailsResponseData.setEntity(transfor(com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.Supplier.class, supplier));
            response.setData(getProviderDetailsResponseData);
            return response;
        } catch (Exception e) {
            return handleException(GetProviderDetailsResponseData.class, e);
        }
    }

    /**
     * 获取商品详情
     *
     * @param request
     * @return
     */
    @HTTP(alias = "selGoodsDetails")
    @ExplainAnnotation(explain = " 获取商品详情")
    public Response<GetGoodsDetailsResponseData> getGoodsDetails(GetGoodsDetailsRequest request) {

        try {
            GetGoodsDetailsResponseData goodsDetailsResponse = new GetGoodsDetailsResponseData();
            Response<GetGoodsDetailsResponseData> response = new Response<GetGoodsDetailsResponseData>();
            String memberId = request.getMemberId();
            Long projectId = request.getProjectId();

            LegouSkuRequest skuRequest = transfor(LegouSkuRequest.class, request);
            skuRequest.setFindSellNum(request.getFindSellNum());
            skuRequest.setFindSkuStock(request.getFindSkuStock());
            ArrayList<Long> sortedSkuIds = new ArrayList<Long>();
            sortedSkuIds.add(request.getSkuId());
            skuRequest.setSortedSkuIds(sortedSkuIds);

            skuRequest.setProjectId(null);
            LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);

            checkAndContinue(skuResponse);
            List<LegouSkuDetailInfo> skuInfoList = skuResponse.getSkus();
            if(CollectionUtils.isEmpty(skuInfoList)){
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "亲，该商品可能已经下架，请选择其他商品");
            }

            LegouSkuDetailInfo skuDetailInfo = skuInfoList.get(0);

            GoodsDetail   goodsDetail = transfor(GoodsDetail.class, skuDetailInfo);

            if (QDStringUtil.isNull(skuDetailInfo.getIsDel())||skuDetailInfo.getIsDel()!=0) { //如果无效

                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "亲，该商品可能已经下架，请选择其他商品");

            } else {
                //如果有效(则查看当前所在社区下此商品是否有效)
                skuInfoList = Lists.newArrayList();
                if (QDStringUtil.isNotNull(request.getProjectId())) {
                    skuRequest.setProjectId(request.getProjectId());
                    skuResponse = solrSku.queryLegouSku(skuRequest);
                    checkAndContinue(skuResponse);
                    skuInfoList = skuResponse.getSkus();
                }
                if (skuInfoList == null || skuInfoList.size() <= 0) { //如果无效

                    goodsDetail.setBuyBtnName("立即购买");
                    goodsDetail.setBuyBtnStatus(-1);
                    goodsDetail.setSoldOutMsg("当前社区不支持购买此商品");

                } else { //如果有效

                    LegouSkuDetailInfo legouSkuDetailInfo = skuInfoList.get(0);
                    goodsDetail = transfor(GoodsDetail.class, legouSkuDetailInfo);

                    //如果有库存
                    if ("1".equals(goodsDetail.getStatus()) && 1 <= goodsDetail.getCount()) {
                        Integer limitCount = -1;
                        Integer availableCount = -1;
                        //获取限购情况
                        if (QDStringUtil.isNotNull(request.getProjectId())) {
                            List<Long> skuIds = Lists.newArrayList();
                            skuIds.add(request.getSkuId());
                            Map<Long, Map<String, Object>> limitMap =  promotionService.batchGetSkuLimitListByWareIds( QDStringUtil.isNotNull(memberId) ? memberId : "",skuIds,request.getProjectId());
                            if (QDStringUtil.isNotNull(limitMap) && limitMap.containsKey(request.getSkuId())) {
                                //3.0针对限购商品超出限购数量是否可以继续购买的判断
                                String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
                                if (Integer.parseInt(initVersion)>=300000) {
                                    GoodsPromotion goodsPromotion =  promotionService.getGoodsOptimizePromotion(request.getProjectId(),request.getSkuId());
                                    Integer limitStrategy =goodsPromotion.getLimitStrategy();
                                    goodsDetail.setIsSupportSell(limitStrategy);
                                    if (Constant.PromotionTypeEnum.TYPE_6.getCode() == goodsPromotion.getPromotionType() ){
                                        goodsDetail.setSupportCart(0);//如果最优促销活动是阶梯团购，则当前商品不可以加入购物车
                                    }
                                }

                                limitCount = Integer.parseInt(limitMap.get(request.getSkuId()).get("limitCount").toString()); //限购数量
                                availableCount = Integer.parseInt(limitMap.get(request.getSkuId()).get("availableCount").toString());//可购买数量
                            }
                        }

                        goodsDetail.setRestrictionBuyNum(limitCount);
                        goodsDetail.setSurplusBuyNum(availableCount);
                        goodsDetail.setBuyBtnName("立即购买");
                        goodsDetail.setBuyBtnStatus(1);

                        //根据当前设备类型和当前商品销售渠道 过滤当前商品是否可以购买
                        Integer[] sellPlateFormArray =  legouSkuDetailInfo.getSellPlatform();
                        if(QDStringUtil.isNotNull(sellPlateFormArray)) {
                            String platform = request.getAppDevice().getQdPlatform();
                            List<Integer> sellPlateFormList = Arrays.asList(sellPlateFormArray);
                            if (((Constant.QD_PLATFORM_IOS.equals(platform.toLowerCase()) || Constant.QD_PLATFORM_ANDROID.equals(platform.toLowerCase()))
                                    & !sellPlateFormList.contains(Constant.SALEPLATFORM_APP))
                                    || ((Constant.QD_PLATFORM_WEIXIN.equals(platform.toLowerCase())) & !sellPlateFormList.contains(Constant.SALEPLATFORM_WX))) {

                                goodsDetail.setBuyBtnStatus(-2);
                            }
                        }

                    } else { //如果没有库存
                        goodsDetail.setSoldOutMsg("商品已售罄，点击“开售提醒”接收开售通知");
                        goodsDetail.setSurplusBuyNum(0);
                    }
                }
            }

            if (QDStringUtil.isEmpty(skuDetailInfo.getMarkingName())) {
                if (Constant.DeliveryType.Logistics.getValue() == skuDetailInfo.getDeliveryType()) {
                    goodsDetail.setDeliveryRemark(Constant.DeliveryTypeName.Logistics.getValue());
                } else if (Constant.DeliveryType.PropertySelf.getValue() == skuDetailInfo.getDeliveryType()) {
                    goodsDetail.setDeliveryRemark(Constant.DeliveryTypeName.PropertySelf.getValue());
                }
            }

            String[] skuImg = skuDetailInfo.getSkuImgUrl();
            if (QDStringUtil.isNotNull(skuImg)) {
                List<String> skuImgs = Lists.newArrayList();
                //3.3新增
                List<String> minskuImgs = Lists.newArrayList();
                for (String img : skuImg) {
                    skuImgs.add(ImageUtil.LgImg(img, 2));
                    minskuImgs.add(ImageUtil.LgImg(img, 4));
                }
                goodsDetail.setSkuImgUrl((String[]) skuImgs.toArray(new String[skuImgs.size()]));
                //3.3新增
                goodsDetail.setMinSkuImgUrl((String[]) minskuImgs.toArray(new String[minskuImgs.size()]));
            }

            String[] wareImg = skuDetailInfo.getWareImgUrl();
            if (QDStringUtil.isNotNull(wareImg)) {
                List<String> wareImgs = Lists.newArrayList();
                for (String img : wareImg) {
                    wareImgs.add(getGoodsWaterMarkImg("detail", true, ImageUtil.LgImg(img, 2), skuDetailInfo.getWatermarkUrl()));
                }
                goodsDetail.setGoodsImg((String[]) wareImgs.toArray(new String[wareImgs.size()]));
            }

            if (!QDStringUtil.isNull(memberId)) {

                IsInCollectRequest isInCollectRequest = new IsInCollectRequest();
                isInCollectRequest.setMid(memberId);
                isInCollectRequest.setProjectId(projectId);
                isInCollectRequest.setWareSkuId(Long.parseLong(goodsDetail.getSkuId()));
                IsInCollectResponse isInCollect = legouRemoteService.isInCollect(isInCollectRequest);
                Boolean checkGoodsIsCollect = isInCollect.isInCollect();
                if (checkGoodsIsCollect) goodsDetail.setIsCollect("1");
            }

            if (QDStringUtil.isNotNull(request.getProjectId())) {
                //货品最优促销信息（促销名称，促销后最终价格）
                GoodsPromotion goodsPromotion = promotionService.getGoodsOptimizePromotion(projectId,Long.parseLong(goodsDetail.getSkuId()));
                goodsDetail.setOptimizePromotion(goodsPromotion);
                goodsDetail.setPromotionPrice(QDStringUtil.isNotNull(goodsPromotion)&&QDStringUtil.isNotNull(goodsPromotion.getPromotionPrice())?goodsPromotion.getPromotionPrice():"");
                List<GoodsPromotion> goodsPromotionList =  promotionService.getGoodsPromotions(Long.parseLong(goodsDetail.getSkuId()), projectId);
                goodsDetail.setGoodsPromotionList(goodsPromotionList); //促销活动信息（所有）
            }

            goodsDetail.setWareComments(getWareCommentForIndex(skuDetailInfo.getWareId(),1,2)); //评论信息

            List<GoodsActivity> goodsActivityList = Lists.newArrayList();
            goodsDetail.setGoodsActivityList(goodsActivityList);
            Date endTime = skuDetailInfo.getEndTime();
            Date nowTime = new Date();
            long deltaT = endTime.getTime() - nowTime.getTime();
            goodsDetail.setDiscountTimeLeft(deltaT);
            if (QDStringUtil.isNotNull(skuDetailInfo.getProviderName())) {
                goodsDetail.setDesc(goodsDetail.getDesc() + "<br><div style=\"color: #ccc;text-align: center\">本商品(服务)由" + skuDetailInfo.getProviderName() + "提供</div>"
                        + "<div class=\"provider\" style=\"text-aign:center\"><a href=\""+APIPropertiesClient.getUrlContent("skip_url_btn_h5")+"/shopping/provider/" + skuDetailInfo.getProviderId() + "\">查看供方资质</a></div>");
            }

            Long startTime16 = System.currentTimeMillis();
            //2.5版本增加商品条款信息
            List<com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.ClauseConfig> clauseConfigList = Lists.newArrayList();
            try {
                List<ClauseConfig> clauseConfigs = clauseConfigRpcService.selClauseConfigByProductNo("NG");
                if (QDStringUtil.isNotNull(clauseConfigs) && clauseConfigs.size() > 0) {
                    clauseConfigList = transforList(com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.ClauseConfig.class, clauseConfigs);

                }
            } catch (Exception ex) {
                logger.error("获取商城商品条款错误：", ex);
            }
            goodsDetail.setClauseConfigList(clauseConfigList);
            //如果剩余可购买数量小于0  3.0版本新增判断
            if(goodsDetail.getSurplusBuyNum() != null && goodsDetail.getSurplusBuyNum()==0) {
                goodsDetail.setPromotionPrice(""); //则不显示促销价格
            }

            // 2018/5/23 王成亮提供关于点滴|千丁商品市场价说明逻辑  
            String thirdSkuBn = skuDetailInfo.getThirdSkuBn();
            if (skuDetailInfo.getSourceType()==1 && QDStringUtil.isNotNull(thirdSkuBn)) { //如果是点滴
            	goodsDetail.setOriginalPrice(goodsDetail.getOriginalPrice()+SkuCompanyEnum.getPriceDesc(thirdSkuBn));
            }
            goodsDetail.setIsSupportShare(skuDetailInfo.getIsShare());
            
            goodsDetailsResponse.setEntity(goodsDetail);
            Long startTime18 = System.currentTimeMillis();
            if (!request.getShowShare() && QDStringUtil.isNotEmpty(memberId) && QDStringUtil.isNotEmpty(String.valueOf(projectId))) {
                GetUserCartRequest getCartRequest = new GetUserCartRequest();
                getCartRequest.setMid(memberId);
                getCartRequest.setProjectId(projectId);
                GetUserCartResponse getUserCartResponse = legouRemoteService.getUserCart(getCartRequest);
                List<Cart> catList = getUserCartResponse.getCatList();
                int count = 0;
                if (CollectionUtils.isNotEmpty(catList)) {
                    count = catList.size();
                }
                goodsDetailsResponse.setCartCount(count);
            }
            Long startTime19 = System.currentTimeMillis();
            response.setData(goodsDetailsResponse);

            return response;


        } catch (Exception e) {

            return handleException(GetGoodsDetailsResponseData.class, e);
        }
    }
}
