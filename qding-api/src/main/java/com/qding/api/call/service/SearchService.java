package com.qding.api.call.service;

import com.google.common.collect.Lists;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.ProjectService;
import com.qding.api.call.app.qding.v3_3_0.struct.search.SearchItemPage;
import com.qding.api.call.app.qding.v3_3_0.struct.search.SearchItem;
import com.qding.api.call.service.dto.SearchLgDTO;
import com.qding.api.call.service.dto.SearchProjectServiceItemDTO;
import com.qding.api.imessage.KeywordNotify;
import com.qding.api.imessage.SearchKeyStatistics;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.solr.service.ISolrKeywordService;
import com.qding.solr.service.ISolrServiceItemService;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.service.ISolrWareAppShowService;
import com.qding.solr.struct.request.KeywordRequest;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.request.ServiceItemRequest;
import com.qding.solr.struct.request.wareAppShow.WareAppShowRequest;
import com.qding.solr.struct.response.KeywordResponse;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.response.ServiceItemResponse;
import com.qding.solr.struct.response.wareAppShow.WareAppShowResponse;
import com.qding.solr.struct.wareAppShow.WareAppShow;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by qd on 2017/9/13.
 */
public class SearchService extends Callable {


    @Autowired
    private ISolrKeywordService solrKeywordService;

    @Autowired
    private ISolrSkuService solrSku;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private SearchKeyStatistics searchKeyStatistics;

    @Autowired
    private ISolrWareAppShowService solrWareAppShowService;

    @Autowired
    private ISolrServiceItemService solrServiceItemService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private SkipModelService skipService;

    private static final Logger logger = Logger.getLogger(SearchService.class);


    /**
     * 联想关键词搜索
     * @throws ServiceException
     */
    public KeywordResponse  getSearchAssociateKeyWords( Long projectId,String keyWord ,Integer limit,List<String> propCodes,Integer salePlatform )  {

        List<Integer> status = Lists.newArrayList();
        status.add(1);
        KeywordRequest keywordRequest = new KeywordRequest();
        keywordRequest.setKw(keyWord);
        keywordRequest.setLimit(limit);
        keywordRequest.setPropCodes(propCodes);
        keywordRequest.setStatus(status);
        keywordRequest.setProjectId(projectId);
        keywordRequest.setSellPlatform(salePlatform);
        KeywordResponse keywordResponse = null;
        try {
            keywordResponse = solrKeywordService.query(keywordRequest);
        } catch (Exception ex) {
            logger.error("class : SearchService, method:getSearchAssociateKeyWords is error :",ex);
        }
        return keywordResponse;
    }

    /**
     * 乐购商品搜索
     * @param skuRequest
     * @return
     */
    public SearchLgDTO searchLgItems(LegouSkuRequest skuRequest){

        SearchLgDTO searchLgDTO = new SearchLgDTO();
        List<Goods> goods = Lists.newArrayList();
        Long totalCount = 0L;
        try {
            KeywordNotify keywordNotify = new KeywordNotify();
            keywordNotify.setKeyword(skuRequest.getKeyword());
            keywordNotify.setProjectId(skuRequest.getProjectId());
            int resultCode = searchKeyStatistics.sendKeyStatisticsInfo(keywordNotify);
            LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
            checkAndContinue(skuResponse);
            goods = transforList(Goods.class, skuResponse.getSkus());
            if (CollectionUtils.isNotEmpty(goods)) {
                List<Long> skuIdList = Lists.newArrayList();
                for (Goods good : goods) {
                    skuIdList.add(Long.parseLong(good.getSkuId()));
                }
                Map<Long, GoodsPromotion> promotionMap = promotionService.batchGetGoodsOptimizePromotion(skuIdList,skuRequest.getProjectId());

                for (Goods good : goods) {
                    if(promotionMap.containsKey(Long.parseLong(good.getSkuId()))){
                        GoodsPromotion goodsPromotion = promotionMap.get(Long.parseLong(good.getSkuId()));
                        good.setOptimizePromotion(goodsPromotion);
                        good.setPromotionPrice(QDStringUtil.isNotNull(goodsPromotion)&&QDStringUtil.isNotNull(goodsPromotion.getPromotionPrice())?goodsPromotion.getPromotionPrice():"");
                    }
                }
            }
            totalCount = skuResponse.getTotal();
        } catch (ServiceException ex) {
            logger.error("class : SearchService, method:searchNG is error :",ex);
        }
        searchLgDTO.setList(goods);
        searchLgDTO.setTotalCount(totalCount);

        return searchLgDTO;
    }


    /**
     * 查询服务和旅游业态 数据并组装返回
     * @param projectId
     * @param keyWord
     * @param pageNo
     * @param pageSize
     * @param propCodes
     * @return
     */
    public SearchItemPage searchWareAppItems(Long projectId, String keyWord, Integer pageNo, Integer pageSize, List<String> propCodes){

        SearchItemPage searchItemPage = null;
        WareAppShowResponse wareAppShowResponse =  searchWareAppItemsByRpc (projectId, keyWord, pageNo, pageSize,propCodes);
        if (QDStringUtil.isNotNull(wareAppShowResponse)) {
            List<SearchItem> itemList = Lists.newArrayList();
            if (QDStringUtil.isNotNull(wareAppShowResponse))  {
                List<WareAppShow> list = wareAppShowResponse.getList();
                Long totalCount = wareAppShowResponse.getTotal();
                if (CollectionUtils.isNotEmpty(list)) {
                    for (WareAppShow wareAppShow : list) {
                        StringBuffer priceBf = new StringBuffer();
                        String lowPrice = wareAppShow.getLowPrice();
                        String upPrice = wareAppShow.getUpPrice();
                        if (QDStringUtil.isNotEmpty(lowPrice)) {
                            priceBf.append(lowPrice);
                        }
                        if (QDStringUtil.isNotEmpty(upPrice) && !lowPrice.equals(upPrice)) {
                            priceBf.append("-"+upPrice);
                        }

                        SearchItem searchItem = new SearchItem();
                        searchItem.setItemShellCount(null);
                        searchItem.setSkipModel(wareAppShow.getSkipModel());
                        searchItem.setItemPrice(priceBf.toString());
                        searchItem.setItemName(wareAppShow.getWareName());
                        searchItem.setItemImg(wareAppShow.getShowImg());
                        searchItem.setItemDesc(wareAppShow.getDescription());
                        searchItem.setMarkingName(wareAppShow.getMarkingName());
                        itemList.add(searchItem);
                    }
                    searchItemPage = new SearchItemPage();
                    searchItemPage.setList(itemList);
                    searchItemPage.setTotalCount(Integer.parseInt(String.valueOf(totalCount)));
                }
            }
        }

        return searchItemPage;
    }


    /**
     * 查询服务和旅游业态RPC服务
     * @param projectId
     * @param keyWord
     * @param pageNo
     * @param pageSize
     * @param propCodes
     * @return
     */
    public  WareAppShowResponse  searchWareAppItemsByRpc (Long projectId,String keyWord,Integer pageNo,Integer pageSize,List<String> propCodes){
        WareAppShowResponse wareAppShowResponse = null;
        try {
            WareAppShowRequest wareAppShowRequest = new WareAppShowRequest();
            wareAppShowRequest.setProjectId(projectId);
            wareAppShowRequest.setKeyword(keyWord);
            wareAppShowRequest.setPageNo(pageNo);
            wareAppShowRequest.setPageSize(pageSize);
            wareAppShowRequest.setPropCodes(propCodes);
            wareAppShowResponse = solrWareAppShowService.query(wareAppShowRequest);
            checkAndContinue(wareAppShowResponse);
        } catch (ServiceException ex) {
            logger.error("class : SearchService, method:searchNG is error :",ex);
        }
        return wareAppShowResponse;
    }


    /**
     * 查询社区服务项
     * @param projectId
     * @param keyWord
     * @param supportDevice
     */
    public SearchProjectServiceItemDTO searchProjectServiceItems (Long projectId, String keyWord, Integer supportDevice, Map<String, String> skipModelMap, Integer pageNo, Integer pageSize){

        SearchProjectServiceItemDTO  searchServiceItem = new SearchProjectServiceItemDTO();
        try {
            ServiceItemRequest itemRequest = new ServiceItemRequest(projectId, keyWord,  supportDevice);
            itemRequest.setPageno(pageNo);
            itemRequest.setPagesize(pageSize);
            itemRequest.setSupportAppSearch(1);
            ServiceItemResponse serviceItemResponse = solrServiceItemService.query(itemRequest);
            checkAndContinue(serviceItemResponse);
            Project project = projectReadService.get(projectId);
            List<com.qding.solr.struct.serviceItem.ServiceItem> serviceItems = serviceItemResponse.getList();
            List<ProjectService> list = Lists.newArrayList();
            Long totalCount = serviceItemResponse.getTotal();
            for (com.qding.solr.struct.serviceItem.ServiceItem item : serviceItems) {
                ProjectService projectService= transfor(ProjectService.class,item);
                projectService.setServiceDesc(item.getDesc());
                String skipModel = skipService.fittingProjectServiceItemSkip(project,item,skipModelMap);
                if (QDStringUtil.isEmpty(skipModel)) {
                    totalCount --;
                    logger.error("class : SearchService, method:searchProjectServiceItems is error cant't get skipNo");
                    continue;
                }
                projectService.setSkipModel(skipModel);
                list.add(projectService);
            }
            searchServiceItem.setTotalCount(Integer.parseInt(String.valueOf(serviceItemResponse.getTotal())));
            searchServiceItem.setList(list);
        } catch (Exception ex) {
            logger.error("class : SearchService, method:searchProjectServiceItems is error :",ex);
        }

        return searchServiceItem;
    }


}
