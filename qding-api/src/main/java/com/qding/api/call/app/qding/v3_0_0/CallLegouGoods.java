package com.qding.api.call.app.qding.v3_0_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.SearchGoodBean;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.ProjectService;
import com.qding.api.call.app.qding.v3_0_0.struct.legou.goods.ServiceGoodBean;
import com.qding.api.call.app.qding.v3_0_0.struct.legou.goods.request.SearchGoodsRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.legou.goods.response.data.SearchGoodsResponseData;
import com.qding.api.call.service.PromotionService;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.KeywordNotify;
import com.qding.api.imessage.SearchKeyStatistics;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.SkipBean;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.solr.service.ISolrServiceItemService;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.request.ServiceItemRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.response.ServiceItemResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by qd on 2017/2/23.
 */
@ExplainAnnotation(explain = "乐购")
public class CallLegouGoods extends com.qding.api.call.app.qding.v2_7_0.CallLegouGoods {

    @Autowired
    private ISolrSkuService solrSku;

    @Autowired
    private SearchKeyStatistics searchKeyStatistics;

    @Autowired
    private ISolrServiceItemService solrServiceItemService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private PromotionService promotionService;


    @Autowired
    private ProjectReadRemote projectReadService;

    private org.apache.log4j.Logger logger =  org.apache.log4j.Logger.getLogger(CallLegouGoods.class);


    /**
     * 搜索乐购商品
     * @param request
     * @return
     */
    @HTTP(alias = "searchLgGoods")
    @ExplainAnnotation(explain = "搜索乐购商品")
    public Response<SearchGoodsResponseData> searchLgGoods (SearchGoodsRequest request, UserToken userToken){

        Response<SearchGoodsResponseData> response = new Response<SearchGoodsResponseData>();

        try {
            Long projectId = Long.parseLong(request.getAppUser().getProjectId());
            Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap2, request.getAppDevice());

            KeywordNotify keywordNotify = new KeywordNotify();
            keywordNotify.setKeyword(request.getKeyWord());
            keywordNotify.setProjectId(projectId);
            int resultCode = searchKeyStatistics.sendKeyStatisticsInfo(keywordNotify);
            logger.info("[Imessage keyword statistics ] request[keyword:"+request.getKeyWord()+";projectId:"+projectId+"] response[resultCode:"+resultCode+"] ");

            LegouSkuRequest skuRequest = transfor(LegouSkuRequest.class, request);
            skuRequest.setProjectId(projectId);
            skuRequest.setSellPlatform(salePlatform);
            skuRequest.setFindSellNum(true);
            skuRequest.setMain(true);

            String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
            if (Integer.parseInt(initVersion) <310000) {
                skuRequest.setExtQuery("NOT markingCode:("+ Constant.MRX_MAKING_CODE +" "+Constant.MRX_PSF_MAKING_CODE+")");
            } else {
                skuRequest.setExtQuery("NOT markingCode:("+Constant.MRX_PSF_MAKING_CODE+")");
            }

            if (Constant.SALEPLATFORM_APP.intValue() == salePlatform) { //只有APP才支持
                skuRequest.setIsAppSearch(1);
            }

            LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
            checkAndContinue(skuResponse);
            List<Goods> goods = transforList(Goods.class, skuResponse.getSkus());
            List<Long> skuIds = Lists.newArrayList();
            for (Goods good : goods) {
                skuIds.add(Long.parseLong(good.getSkuId()));
            }
            Map<Long,String[]> promotionNameMap = null;

            //批量获取多个货品促销活动
            promotionNameMap = promotionService.getGoodActivityInfoBySkuids(skuIds,projectId);
            //批量获取多个货品最优促销活动
            Map<Long,GoodsPromotion> optimizePromotionMap = promotionService.batchGetGoodsOptimizePromotion (skuIds,projectId);

            if (CollectionUtils.isNotEmpty(goods)) {
                for (Goods good : goods) {
                    getGoodsImgForWaterMark(good ,"list");
                    if (QDStringUtil.isNotNull(optimizePromotionMap) && optimizePromotionMap.containsKey(Long.parseLong(good.getSkuId()))) {
                        GoodsPromotion goodsPromotion  = optimizePromotionMap.get(Long.parseLong(good.getSkuId()));//最优活动
                        good.setOptimizePromotion(goodsPromotion);
                        good.setPromotionPrice(QDStringUtil.isNotNull(goodsPromotion)&&QDStringUtil.isNotNull(goodsPromotion.getPromotionPrice())?goodsPromotion.getPromotionPrice():"");
                    }
                    if (QDStringUtil.isNotNull(promotionNameMap)&& promotionNameMap.containsKey(Long.parseLong(good.getSkuId())) ) {
                        String[] activityInfo = promotionNameMap.get(Long.parseLong(good.getSkuId()));
                        good.setActivityInfo(activityInfo);//优惠活动
                    }
                }
            }

            SearchGoodsResponseData data = new SearchGoodsResponseData();
            SearchGoodBean goodBean = new SearchGoodBean();
            goodBean.setList(goods);
            goodBean.setTotalCount(skuResponse.getTotal());
            data.setGoods(goodBean);

            ServiceItem serviceItem = new ServiceItem();
            ServiceItemRequest itemRequest = new ServiceItemRequest(projectId, request.getKeyWord(),  serviceItem.getRealValueFromPlatform(brickSourceType));
            ServiceItemResponse serviceItemResponse = solrServiceItemService.query(itemRequest);
            List<com.qding.solr.struct.serviceItem.ServiceItem> serviceItems = serviceItemResponse.getList();
            List<ProjectService> list = Lists.newArrayList();
            Project project = projectReadService.get(projectId);
            for (com.qding.solr.struct.serviceItem.ServiceItem item : serviceItems) {
                ProjectService projectService= transfor(ProjectService.class,item);
                
                projectService.setServiceDesc(item.getDesc());
                String skipStr ="";
                if (QDStringUtil.isEmpty(item.getSkipNo())) continue;

                SkipBean skipBean = new SkipBean();
                skipBean.setProjectName(project.getName());
                skipBean.setProjectId(String.valueOf(project.getId()));
                skipBean.setIds(String.valueOf(item.getId()));
                skipBean.setSkipNo(Integer.parseInt(item.getSkipNo()));
                StringBuffer identity = new StringBuffer(Constant.DETAL_IDENTITY);
                if (QDStringUtil.isNotNull(item.getAccessPrivilege())){
                    identity.setLength(0);
                    identity.append(item.getAccessPrivilege());
                }
                skipBean.setIdentity(identity.toString());
                skipBean.setPcode( item.getPrivilegeType()==0?1:3);

                if (Integer.parseInt(item.getSkipNo()) == Constant.SkipNo.URL_7000.toInteger()) {
                    skipBean.setSkipUrl(item.getContant());
                    skipStr = skipMode.fittingSkipUrl(request.getAppDevice().getQdVersion(), skipBean);
                } else {
                    skipStr = skipMode.fittingSkipModelBySkipBean(request.getAppDevice().getQdVersion(),skipBean);
                }

                projectService.setSkipModel(skipStr);
                list.add(projectService);
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

}
