package com.qding.api.call.service;

import com.google.common.collect.Lists;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.constant.Constant;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.promotion.common.domain.PromotionGrouponPrice;
import com.qding.promotion.common.dto.PromotionDto;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.GetPromotionListByWareIdRequest;
import com.qding.promotion.common.struct.response.GetPromotionListByWareIdResponse;
import com.qding.promotion.common.struct.response.GetPromotionPriceByWareIdResponse;
import com.qding.promotion.common.struct.response.GetPromotionPriceByWareIdsResponse;
import com.qding.promotion.common.struct.response.GetSkuLimitByWareIdResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hsqldb.lib.tar.TarHeaderField.size;


/**
 * Created by qd on 2017/8/3.
 */
public class PromotionService extends Callable {


    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IPromotionRemoteService promotionRemoteService;

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PromotionService.class);


    /**
     * 获取 单个商品所拥有的活动名称
     * @param
     * @return
     * @throws Exception
     */
    public String[] getGoodActivityInfo(Long skuId, Long projectId) {

        String[] activityInfo = null;
        if (QDStringUtil.isNull(skuId) || QDStringUtil.isNull(projectId)) {
            return  activityInfo;
        }
        List<PromotionDto> promotionDtoList = getPromotionListByWareId( skuId,projectId);
        if (promotionDtoList == null) {
            promotionDtoList = new ArrayList<>();
        }
        List<String> promotionNameList = Lists.newArrayList();
        for (int i = 0; i < promotionDtoList.size(); i++) {
            PromotionDto promotionDto = promotionDtoList.get(i);
            if (QDStringUtil.isNotEmpty(promotionDto.getName()) && !"null".equals(promotionDto.getName())){
                promotionNameList.add( promotionDto.getName());
            }
        }
        return (String[])promotionNameList.toArray(new String[promotionNameList.size()]);
    }

    /**
     * 批量获取商品促销活动名称
     *
     * @param skuIds
     * @param projectId
     * @return
     * @throws Exception
     */
    public Map<Long, String[]> getGoodActivityInfoBySkuids(List<Long> skuIds, Long projectId) {

        if (QDStringUtil.isNull(skuIds) || skuIds.size() <= 0) {
            return new HashMap<>();
        }
        Map<Long, String[]> promotionNameMap = new HashMap();
        Map<Long, List<PromotionDto>> promotionDtoMap = batchGetPromotionListByWareIds(skuIds, projectId);
        for (Long key : promotionDtoMap.keySet()) {
            List<PromotionDto> promotionDtoList = promotionDtoMap.get(key);
            List<String> promotionNameList = Lists.newArrayList();
            for (int i = 0; i < promotionDtoList.size(); i++) {
                if (QDStringUtil.isNotEmpty(promotionDtoList.get(i).getName()) && !"null".equals(promotionDtoList.get(i).getName())){
                    promotionNameList.add( promotionDtoList.get(i).getName());
                }
            }
            promotionNameMap.put(key, (String[])promotionNameList.toArray(new String[promotionNameList.size()]));
        }
        return promotionNameMap;
    }



    /**
     * 获取指定货品的促销列表
     * @param skuId
     * @param projectId
     * @return
     */
    public List<GoodsPromotion> getGoodsPromotions (Long skuId, Long projectId) {

        List<GoodsPromotion> goodsPromotionList = Lists.newArrayList();
        if (QDStringUtil.isNull(skuId) || QDStringUtil.isNull(projectId)) {
            return  goodsPromotionList;
        }

        List<PromotionDto> promotionDtoList = getPromotionListByWareId( skuId,projectId);
        if (promotionDtoList == null) {
            promotionDtoList = new ArrayList<>();
        }
        for (PromotionDto promotionDto : promotionDtoList) {
            GoodsPromotion promotion = fittingPromotion(promotionDto, "");
            goodsPromotionList.add(promotion);
        }

        return  goodsPromotionList;
    }

    /**
     * 批量获取商品促销活动列表
     * @param skuIds
     * @param projectId
     * @return
     */
    public Map<Long, List<GoodsPromotion>> batchGetGoodsPromotions(List<Long> skuIds, Long projectId) {

        Map<Long, List<GoodsPromotion>> promotionDtoMap = new HashMap<>();
        Map<Long, List<PromotionDto>> promotionRpcListMap = batchGetPromotionListByWareIds(skuIds, projectId);
        if (promotionRpcListMap == null) {
            return null;
        }
        for (Map.Entry<Long, List<PromotionDto>> entry : promotionRpcListMap.entrySet()) {
            List<GoodsPromotion> goodsPromotionList = Lists.newArrayList();
            List<PromotionDto> promotionDtoList = entry.getValue();
            for (PromotionDto promotionDto : promotionDtoList) {
                GoodsPromotion promotion = fittingPromotion(promotionDto, "");
                goodsPromotionList.add(promotion);
            }
            promotionDtoMap.put(entry.getKey(), goodsPromotionList);
        }
        return promotionDtoMap;
    }



    /**
     * 批量获取多个货品的最优促销活动
     *
     * @param projectId
     * @param skuIds
     * @return
     */
    public Map<Long, GoodsPromotion> batchGetGoodsOptimizePromotion(List<Long> skuIds, Long projectId) {

        Map<Long, GoodsPromotion> optimizePromotionMap = new HashMap<>();
        if (CollectionUtils.isEmpty(skuIds)) {
            return  optimizePromotionMap;
        }
        GetPromotionListByWareIdRequest promotionRequest = new GetPromotionListByWareIdRequest();
        Project project = projectReadService.get(projectId);
        promotionRequest.setWareSkuIds(skuIds);
        promotionRequest.setProjectId(projectId);
        promotionRequest.setCityId(project.getRegionId());
        promotionRequest.setProductId(Constant.PRODUCT_NO_NG); //新乐购业态
        GetPromotionPriceByWareIdsResponse promotionPriceByWareIdResponse = promotionRemoteService.getPromotionPriceByWareIds(promotionRequest);
        if (HttpStatus.OK.getStatusCode() == promotionPriceByWareIdResponse.getReturnInfo().getCode()) {
            Map<Long, GetPromotionPriceByWareIdsResponse.SkuPromotion> promotionMap = promotionPriceByWareIdResponse.getSkuPromotionData();
            for (Long skuId : skuIds) {
                if (promotionMap.containsKey(skuId)) {
                    GetPromotionPriceByWareIdsResponse.SkuPromotion promotion = promotionMap.get(skuId);
                    PromotionDto promotionDto = promotion.getPromotionDto();
                    String discountWarePrice = promotion.getDiscountWarePrice();
                    GoodsPromotion optimizePromotion = fittingPromotion(promotionDto, discountWarePrice);
                    optimizePromotionMap.put(skuId, optimizePromotion);
                }
            }
        } else {
            logger.error("class:PromotionService,method:batchGetGoodsOptimizePromotion is error: return code ： " + promotionPriceByWareIdResponse.getReturnInfo().getCode() + ", error message :" + promotionPriceByWareIdResponse.getReturnInfo().getMessage());
        }

        return optimizePromotionMap;

    }


    /**
     * 获取指定货品最优促销信息（促销名称，促销最终价格）
     */
    public GoodsPromotion getGoodsOptimizePromotion(Long projectId, Long skuId) {

        GoodsPromotion optimizePromotion = new GoodsPromotion();
        if (QDStringUtil.isNull(skuId) || QDStringUtil.isNull(projectId)) {
            return  optimizePromotion;
        }

        GetPromotionListByWareIdRequest promotionRequest = new GetPromotionListByWareIdRequest();
        Project project = projectReadService.get(projectId);
        promotionRequest.setWareSkuId(skuId);
        promotionRequest.setProjectId(projectId);
        promotionRequest.setCityId(project.getRegionId());
        promotionRequest.setProductId(Constant.PRODUCT_NO_NG); //新乐购业态
        GetPromotionPriceByWareIdResponse promotionPriceByWareIdResponse = promotionRemoteService.getPromotionPriceByWareId(promotionRequest);
        if (HttpStatus.OK.getStatusCode() == promotionPriceByWareIdResponse.getReturnInfo().getCode()) {
            PromotionDto promotionDto = promotionPriceByWareIdResponse.getPromotionDto();
            String discountWarePrice = promotionPriceByWareIdResponse.getDiscountWarePrice();
            optimizePromotion = fittingPromotion(promotionDto, discountWarePrice);
        } else {
            logger.error("class:PromotionService,method:getGoodsOptimizePromotion is error: return code ： " + promotionPriceByWareIdResponse.getReturnInfo().getCode() + ", error message :" + promotionPriceByWareIdResponse.getReturnInfo().getMessage());
        }

        return optimizePromotion;
    }

    /**
     * 指定用户在指定社区下乐购多个商品限购信息
     *
     * @param memberId
     * @param skuIds
     * @return
     * @throws Exception
     */
    public Map<Long, Map<String, Object>> batchGetSkuLimitListByWareIds(String memberId, List<Long> skuIds,Long projectId) throws Exception {

        Map<Long, Map<String, Object>> limitMap = new HashMap<>();
        if (CollectionUtils.isEmpty(skuIds)){
            return limitMap;
        }
        Project project = projectReadService.get(projectId);
        GetPromotionListByWareIdRequest promotionListByWareIdReqeust = new GetPromotionListByWareIdRequest();
        promotionListByWareIdReqeust.setCityId(project.getRegionId());
        promotionListByWareIdReqeust.setMid(memberId);
        promotionListByWareIdReqeust.setProductId(Constant.PRODUCT_NO_NG);
        promotionListByWareIdReqeust.setProjectId(project.getId());
        promotionListByWareIdReqeust.setWareSkuIds(skuIds);
        Long startTime4 = System.currentTimeMillis();
        GetSkuLimitByWareIdResponse skuLimitByWareIdResponse = promotionRemoteService.getSkuLimitListByWareIds(promotionListByWareIdReqeust);
        checkAndContinue(skuLimitByWareIdResponse);
        Long endTime4 = System.currentTimeMillis();
        logger.info("获取商品限购信息时间时间：" + (endTime4 - startTime4));
        limitMap = skuLimitByWareIdResponse.getSkuLimitInfo();
        return limitMap;

    }




/***********************************************************************************私有方法区**************************************************************/


   private  List<PromotionDto> getPromotionListByWareId (Long skuId,Long projectId){

       List<PromotionDto> promotionList = Lists.newArrayList();
       if (QDStringUtil.isNull(skuId) || QDStringUtil.isNull(projectId)) {
           return  promotionList;
       }
       GetPromotionListByWareIdRequest promotionRequest = new GetPromotionListByWareIdRequest();
       Project project = projectReadService.get(projectId);
       promotionRequest.setWareSkuId(skuId);
       promotionRequest.setProjectId(projectId);
       promotionRequest.setCityId(project.getRegionId());
       promotionRequest.setProductId(Constant.PRODUCT_NO_NG); //新乐购业态
       GetPromotionListByWareIdResponse promotionResponse = promotionRemoteService.getPromotionListByWareId(promotionRequest);
       if (HttpStatus.OK.getStatusCode() == promotionResponse.getReturnInfo().getCode()) {
           promotionList.addAll(promotionResponse.getPromotionDtoList()) ;
       } else {
           logger.error("class:PromotionService,method:batchGetPromotionListByWareIds is error: return code ： " + promotionResponse.getReturnInfo().getCode() + ", error message :" + promotionResponse.getReturnInfo().getMessage());
       }
       return promotionList;
   }


    private Map<Long, List<PromotionDto>> batchGetPromotionListByWareIds(List<Long> skuIds, Long projectId) {

        Map<Long, List<PromotionDto>> promotionDtoMap = null;
        if (CollectionUtils.isEmpty(skuIds)) {
            return  promotionDtoMap;
        }
        GetPromotionListByWareIdRequest promotionRequest = new GetPromotionListByWareIdRequest();
        Project project = projectReadService.get(projectId);
        promotionRequest.setWareSkuIds(skuIds);
        promotionRequest.setProjectId(projectId);
        promotionRequest.setCityId(project.getRegionId());
        promotionRequest.setProductId(Constant.PRODUCT_NO_NG); //新乐购业态
        GetPromotionListByWareIdResponse promotionResponse = promotionRemoteService.getPromotionListByWareIds(promotionRequest);
        if (HttpStatus.OK.getStatusCode() == promotionResponse.getReturnInfo().getCode()) {
            promotionDtoMap = promotionResponse.getPromotionDtoMap();
        } else {
            logger.error("class:PromotionService,method:batchGetPromotionListByWareIds is error: return code ： " + promotionResponse.getReturnInfo().getCode() + ", error message :" + promotionResponse.getReturnInfo().getMessage());

        }
        return promotionDtoMap;
    }



    private GoodsPromotion fittingPromotion(PromotionDto promotionDto, String discountWarePrice) {

        GoodsPromotion optimizePromotion = new GoodsPromotion();
        optimizePromotion.setPromotionPrice(discountWarePrice);
        if (QDStringUtil.isNotNull(promotionDto.getLabel())) {
            optimizePromotion.setPromotionTag(promotionDto.getLabel());
            if (Constant.PromotionTypeEnum.TYPE_6.getCode() == promotionDto.getType()) {
                getPromotionName4Groupon(promotionDto.getPromotionGrouponPrice());
//                optimizePromotion.setPromotionName("目前该商品正参加阶梯团购，查看活动");
            } else {
                optimizePromotion.setPromotionName(promotionDto.getName());
            }
        } else {
            optimizePromotion.setPromotionTag(promotionDto.getName());
            optimizePromotion.setPromotionName("");
        }
        optimizePromotion.setStatus(promotionDto.getStatus());
        optimizePromotion.setPromotionType(promotionDto.getType());
        optimizePromotion.setPromotionId(promotionDto.getId());
        optimizePromotion.setLimitStrategy(promotionDto.getLimitStrategy());
        return optimizePromotion;
    }

    /**
     * 获取截图团购促销文案
     *
     * @param promotionGrouponPrice
     * @return
     */
    private static String getPromotionName4Groupon(PromotionGrouponPrice promotionGrouponPrice) {
        String yuan = new BigDecimal(promotionGrouponPrice.getPrice()).divide(new BigDecimal(100)).setScale(2).toString();
        StringBuilder stringBuilder = new StringBuilder("当前订单满足团购一阶梯：");
        stringBuilder.append("\n");
        stringBuilder.append("满").append(promotionGrouponPrice.getIntervalLeftCount()).append("件，阶梯价：").append(yuan).append("元");
        return stringBuilder.toString();
    }

}








