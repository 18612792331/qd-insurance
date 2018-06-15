package com.qding.api.call.app.qding.v1_4_1;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.call.app.qding.v1_4_1.struct.legou.goods.request.GetLgKeyWordForSysRequest;
import com.qding.api.call.app.qding.v1_4_1.struct.legou.goods.request.SearchGoodsRequest;
import com.qding.api.call.app.qding.v1_4_1.struct.legou.goods.response.data.GetLgKeyWordForSysResponseData;
import com.qding.api.call.app.qding.v1_4_1.struct.legou.goods.response.data.SearchGoodsResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.KeywordNotify;
import com.qding.api.imessage.SearchKeyStatistics;
import com.qding.api.struct.Response;
import com.qding.brick.remote.ware.KeywordConfRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by qd on 2015/10/23.
 */
public class CallLegouGoods extends com.qding.api.call.app.qding.v1_3_0.CallLegouGoods {

    @Autowired
    private ISolrSkuService solrSku;

    @Autowired
    private KeywordConfRemote keywordConfRemote;

    @Autowired
    private SearchKeyStatistics searchKeyStatistics;

    private org.apache.log4j.Logger logger =  org.apache.log4j.Logger.getLogger(CallLegouGoods.class);

    /**
     * 搜索乐购商品
     * @param request
     * @return
     */
    @HTTP(alias = "searchLgGoods")
    @Deprecated
    public Response<SearchGoodsResponseData> searchLgGoods (SearchGoodsRequest request){

        Response<SearchGoodsResponseData> response = new Response<SearchGoodsResponseData>();

        try {
            Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());

            LegouSkuRequest skuRequest = transfor(LegouSkuRequest.class, request);

            skuRequest.setSellPlatform(salePlatform);

            skuRequest.setFindSellNum(true);

            skuRequest.setMain(true);

            KeywordNotify keywordNotify = new KeywordNotify();

            keywordNotify.setKeyword(request.getKeyWord());

            keywordNotify.setProjectId(request.getProjectId());

            int resultCode = searchKeyStatistics.sendKeyStatisticsInfo(keywordNotify);

            logger.info("[Imessage keyword statistics ] request[keyword:"+request.getKeyWord()+";projectId:"+request.getProjectId()+"] response[resultCode:"+resultCode+"] ");

            LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);

            checkAndContinue(skuResponse);

            List<Goods> goods = transforList(Goods.class, skuResponse.getSkus());

            SearchGoodsResponseData data = new SearchGoodsResponseData();

            data.setList(goods);

            data.setTotalCount(skuResponse.getTotal());

            response.setData(data);

            return response;

        } catch (ServiceException e) {
            return handleException(SearchGoodsResponseData.class, e);
        }

    }

    /**
     * 获取系统推荐搜索关键字
     * @param request
     * @return
     */
    @HTTP(alias = "getLgKeyWordForSys")
    public Response<GetLgKeyWordForSysResponseData> getLgKeyWordForSys (GetLgKeyWordForSysRequest request){

        Response<GetLgKeyWordForSysResponseData> response = new  Response<GetLgKeyWordForSysResponseData>();

        response.setCode(HttpStatus.OK.getStatusCode());

        GetLgKeyWordForSysResponseData data = new GetLgKeyWordForSysResponseData();

        String keyWord = keywordConfRemote.getByProjectId(Long.parseLong(request.getProjectId()));

        data.setKeyWord(QDStringUtil.isNull(keyWord)?"":keyWord);

        response.setData(data);

        return response;

    }


}
