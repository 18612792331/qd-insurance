package com.qding.api.call.app.qding.v3_3_0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.ProjectService;
import com.qding.api.call.app.qding.v3_3_0.struct.search.SearchItem;
import com.qding.api.call.app.qding.v3_3_0.struct.search.SearchItemGroup;
import com.qding.api.call.app.qding.v3_3_0.struct.search.SearchItemPage;
import com.qding.api.call.app.qding.v3_3_0.struct.search.request.SearchItemsByTypeRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.search.request.SearchItemsRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.search.request.SearchKeyWordItemsRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.search.response.data.SearchItemsByTypeResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.search.response.data.SearchItemsResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.search.response.data.SearchKeyWordItemsResponseData;
import com.qding.api.call.service.SearchService;
import com.qding.api.call.service.dto.SearchLgDTO;
import com.qding.api.call.service.dto.SearchProjectServiceItemDTO;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.KeywordResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.concurrent.*;



/**
 * Created by qd on 2017/9/6.
 */
@ExplainAnnotation (explain = "千丁搜索")
public class CallSearchItems extends Callable {

    @Autowired
    private SearchService searchService;

    @Autowired
    private SkipModeFitting skipMode;

    private static final Logger logger = Logger.getLogger(CallSearchItems.class);

    // 定义一个缓冲的线程值 线程池的大小根据任务变化
    private ExecutorService threadPool = Executors.newCachedThreadPool();

    @HTTP(alias = "searchItems",isNeadProject = true)
    @ExplainAnnotation (explain = "千丁搜索首页")
    public Response<SearchItemsResponseData> searchItems (SearchItemsRequest request, UserToken userToken){

        Response<SearchItemsResponseData> response = new  Response<SearchItemsResponseData>();
        SearchItemsResponseData data = new SearchItemsResponseData();
        Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());

        Integer salePlatform = null;
        try {
            salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap2, request.getAppDevice());
            String searchTypeConfig = APIPropertiesClient.getValue("search_type_list");
            String[] searchTypeArray = searchTypeConfig.split(",");
            List<String> searchTypeList = Arrays.asList(searchTypeArray);
            String searchCount = APIPropertiesClient.getValue("index_search_count");
            List<Future<SearchItemGroup>> futureList = new ArrayList<Future<SearchItemGroup>>();
            for (String searchType : searchTypeList) {
                SearchThread orderStatusThread = new SearchThread(Integer.parseInt(searchType), Long.parseLong(request.getAppUser().getProjectId()),  QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())?userToken.getMemberId():"",  salePlatform, request.getKeyWord(),brickSourceType,skipModelMap,1,Integer.parseInt(searchCount));
                Future<SearchItemGroup> future = threadPool.submit(orderStatusThread);
                futureList.add(future);
            }

            List<SearchItemGroup> searchList = Lists.newArrayList();
            Integer resultTotalCount = 0;
            //将总搜索结果数作为一个业态类型传给APP
            SearchItemGroup indexSearchItemGroup = new SearchItemGroup();
            indexSearchItemGroup.setSearchType(1);
            indexSearchItemGroup.setTotalCount(resultTotalCount);
            indexSearchItemGroup.setList(Lists.<SearchItem>newArrayList());
            searchList.add(indexSearchItemGroup);
            for (Future<SearchItemGroup> future : futureList) {
                try {
                    SearchItemGroup searchItemGroup = future.get(4, TimeUnit.SECONDS);
                    if (QDStringUtil.isNotNull(searchItemGroup)) {
                        searchList.add(searchItemGroup);
                        resultTotalCount+=QDStringUtil.isNotNull(searchItemGroup.getTotalCount())?searchItemGroup.getTotalCount():0;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if (resultTotalCount>0) {
                searchList.get(0).setTotalCount(resultTotalCount);
            }
            data.setSearchList(searchList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }



    @HTTP(alias = "searchItemsByType",isNeadProject = true)
    @ExplainAnnotation (explain = "千丁按类搜索",desc = "支持分页")
    public Response<SearchItemsByTypeResponseData> searchItemsByType (SearchItemsByTypeRequest request, UserToken userToken) {

        Response<SearchItemsByTypeResponseData> response = new Response<SearchItemsByTypeResponseData>();
        SearchItemsByTypeResponseData data = new SearchItemsByTypeResponseData();
        Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
        Integer salePlatform  = null;
        try {
            String searchCount = APIPropertiesClient.getValue("index_search_count");
            salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap2, request.getAppDevice());
            List<Future<SearchItemGroup>> futureList = new ArrayList<Future<SearchItemGroup>>();
            SearchThread orderStatusThread = new SearchThread(request.getSearchType(), Long.parseLong(request.getAppUser().getProjectId()),  QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())?userToken.getMemberId():"",  salePlatform, request.getKeyWord(),brickSourceType,skipModelMap,request.getPageNo(),request.getPageSize());
            Future<SearchItemGroup> future = threadPool.submit(orderStatusThread);
            futureList.add(future);

            for (Future<SearchItemGroup> resultFuture : futureList) {
                try {
                    SearchItemGroup searchItemGroup = resultFuture.get(10, TimeUnit.SECONDS);
                    transfor(data,searchItemGroup);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            response.setData(data);
        } catch (ServiceException e) {
            return handleException(SearchItemsByTypeResponseData.class, e);
        }
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }


    @HTTP(alias="searchKeyWordItems",isNeadProject = true)
    @ExplainAnnotation(explain = "联想关键词")
    public Response<SearchKeyWordItemsResponseData> searchKeyWordItems (SearchKeyWordItemsRequest request) {

        Response<SearchKeyWordItemsResponseData> response = new  Response<SearchKeyWordItemsResponseData>();
        SearchKeyWordItemsResponseData data = new SearchKeyWordItemsResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        String limit = APIPropertiesClient.getValue("associate_keyWord_limit_v3");
        String productNoStr = APIPropertiesClient.getValue("associate_keyWord_productNo_v3");
        String[] productNoArray = productNoStr.split(",");
        List<String> propCodes = Arrays.asList(productNoArray);
        try {
            Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
            KeywordResponse keywordResponse = searchService.getSearchAssociateKeyWords(Long.parseLong(request.getAppUser().getProjectId()),request.getKeyWord(),QDStringUtil.isNotEmpty(limit)?Integer.parseInt(limit):6,propCodes,salePlatform);
            checkAndContinue(keywordResponse);
            data.setList(keywordResponse.getKeywords());
            response.setData(data);
        } catch (Exception e) {
            return handleException(SearchKeyWordItemsResponseData.class, e);
        }
        return response;
    }


    /**
     * 并发获取搜索首页数据
     */
    class SearchThread implements java.util.concurrent.Callable<SearchItemGroup> {

        private Integer pageNo;

        private Integer pageSize;

        private  Map<String, String> skipModelMap;

        private Integer searchType;

        private Long projectId;

        private String memberId;

        private Integer salePlatform;

        private String keyWord;

        private Integer brickSourceType;

        public SearchThread( Integer searchType,Long projectId, String memberId, Integer salePlatform,String keyWord,Integer brickSourceType, Map<String, String> skipModelMap,Integer pageNo,Integer pageSize) {
            this.projectId = projectId;
            this.memberId = memberId;
            this.salePlatform = salePlatform;
            this.keyWord = keyWord;
            this.skipModelMap = skipModelMap;
            this.searchType = searchType;
            this.pageNo = pageNo;
            this.pageSize = pageSize;
            this.brickSourceType = brickSourceType;
        }


        @Override
        public SearchItemGroup call() throws Exception {

            SearchItemGroup searchItemGroup = new SearchItemGroup();
            searchItemGroup.setSearchType(searchType);
            List<String> propCodes = null;
            switch (searchType) {
                case 2: //乐购查询
                    try {
                        LegouSkuRequest skuRequest = new LegouSkuRequest();
                        skuRequest.setProjectId(projectId);
                        skuRequest.setSellPlatform(salePlatform);
                        skuRequest.setFindSellNum(true);
                        skuRequest.setMain(true);
                        skuRequest.setPageno(pageNo);
                        skuRequest.setPagesize(pageSize);
                        skuRequest.setKeyword(keyWord);
                        if (Constant.SALEPLATFORM_APP.intValue() == salePlatform) { //只有APP才支持
                            skuRequest.setIsAppSearch(1);
                        }
                        skuRequest.setExtQuery("NOT markingCode:("+Constant.MRX_PSF_MAKING_CODE+")");
                        SearchLgDTO searchLg = searchService.searchLgItems( skuRequest);
                        logger.info("QD Search , RPC method:searchLgItems  request :"+JSONObject.toJSONString(skuRequest));
                        logger.info("QD Search ,RPC method:searchLgItems  response :"+JSONObject.toJSONString(searchLg));
                        List<Goods> goodsList = searchLg.getList();
                        List<SearchItem> goodsItems = Lists.newArrayList();
                        for (Goods goods : goodsList) {
                            SearchItem searchItem = new SearchItem();
                            String skipModeStr = skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.SPXQ_5004.toInteger(), goods.getSkuId());
                            searchItem.setSkipModel(skipModeStr);
                            searchItem.setItemDesc(goods.getGoodsDesc());
                            searchItem.setItemImg(QDStringUtil.isNotNull(goods.getSkuImgUrl())&&goods.getSkuImgUrl().length>0?goods.getSkuImgUrl()[0]:"");
                            searchItem.setItemName(goods.getGoodsName());
                            searchItem.setItemPrice(goods.getPrice());
                            if(QDStringUtil.isNull(goods.getCountSkuSellNum())){
                                searchItem.setItemShellCount(0);
                            } else {
                                searchItem.setItemShellCount(Integer.parseInt(String.valueOf(goods.getCountSkuSellNum())));
                            }

                            searchItem.setMarkingName(goods.getMarkingName());
                            goodsItems.add(searchItem);
                        }
                        Long total = searchLg.getTotalCount();
                        searchItemGroup.setSearchType(searchType);
                        searchItemGroup.setList(goodsItems);
                        searchItemGroup.setTotalCount(Integer.parseInt(String.valueOf(total)));
                    } catch (Exception ex) {
                        logger.error("class: CallSearchItems ,innerClasss:SearchThread search legou is error :",ex);
                    }
                    break;

                case 3: //服务查询

                    String productNoStr = APIPropertiesClient.getValue("service_search_type_product_no");
                    String[] productArray = productNoStr.split(",");
                    propCodes = Arrays.asList(productArray);
                    SearchItemPage searchItemPage = searchService.searchWareAppItems( projectId,  keyWord, pageNo, pageSize,propCodes);
                    logger.info("QD Search , RPC method:searchWareAppItems(service)  request :projectId:"+projectId+", keyWord:"+keyWord+",pageNo:"+pageNo+", pageSize:"+pageSize+",propCodes:"+ JSON.toJSONString(propCodes));
                    logger.info("QD Search ,RPC method:searchWareAppItems(service)  response :"+JSONObject.toJSONString(searchItemPage));
                    if (QDStringUtil.isNotNull(searchItemPage)) {
                        searchItemGroup.setSearchType(searchType);
                        searchItemGroup.setTotalCount(searchItemPage.getTotalCount());
                        searchItemGroup.setList( searchItemPage.getList());
                    }
                    break;

                case 4: //旅游线路
                    propCodes = Lists.newArrayList();
                    propCodes.add(Constant.PRODUCT_NO_TL);
                    SearchItemPage lvSearchItemPage = searchService.searchWareAppItems( projectId,  keyWord, pageNo, pageSize,propCodes);
                    logger.info("QD Search , RPC method:searchWareAppItems(lv)  request :projectId:"+projectId+", keyWord:"+keyWord+",pageNo:"+pageNo+", pageSize:"+pageSize+",propCodes:"+ JSON.toJSONString(propCodes));
                    logger.info("QD Search ,RPC method:searchWareAppItems(lv)  response :"+JSONObject.toJSONString(lvSearchItemPage));
                    if (QDStringUtil.isNotNull(lvSearchItemPage)) {
                        searchItemGroup.setTotalCount(lvSearchItemPage.getTotalCount());
                        searchItemGroup.setList( lvSearchItemPage.getList());
                    }
                    break;

                case 5: //其他
                    ServiceItem serviceItem = new ServiceItem();
                    SearchProjectServiceItemDTO projectServiceItems = searchService.searchProjectServiceItems(projectId,keyWord,serviceItem.getRealValueFromPlatform(brickSourceType),skipModelMap,pageNo,pageSize);
                    logger.info("QD Search , RPC method:searchWareAppItems(other)  request :projectId:"+projectId+", superDevice:"+serviceItem.getRealValueFromPlatform(brickSourceType)+", keyWord:"+keyWord+",pageNo:"+pageNo+", pageSize:"+pageSize);
                    logger.info("QD Search ,RPC method:searchWareAppItems(other)  response :"+JSONObject.toJSONString(projectServiceItems));
                    List<ProjectService> projectItems = projectServiceItems.getList();
                    if (CollectionUtils.isNotEmpty(projectItems)) {
                        List<SearchItem> list = Lists.newArrayList();
                        for (ProjectService projectItem : projectItems) {
                            SearchItem searchItem = new SearchItem();
                            searchItem.setItemDesc(projectItem.getServiceDesc());
                            searchItem.setSkipModel(projectItem.getSkipModel());
                            searchItem.setItemImg(projectItem.getImageUrl());
                            searchItem.setItemName(projectItem.getName());
                            list.add(searchItem);
                        }
                        searchItemGroup.setList(list);
                        searchItemGroup.setTotalCount(projectServiceItems.getTotalCount());
                    }
                    break;
            }
            return searchItemGroup;
        }
    }



}
