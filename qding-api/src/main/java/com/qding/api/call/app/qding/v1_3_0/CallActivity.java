package com.qding.api.call.app.qding.v1_3_0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.call.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.activity.Activity;
import com.qding.api.call.app.qding.v1_3_0.struct.activity.request.GetActivityByIdRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.activity.request.GetGoodsByActivityIdRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.activity.response.data.GetActivityByIdResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.activity.response.data.GetGoodsByActivityIdResonseData;
import com.qding.api.struct.Response;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.sysconfig.domain.ActivityConfig;
import com.qding.sysconfig.domain.ActivityContent;
import com.qding.sysconfig.rpc.model.MarketActivityModel;
import com.qding.sysconfig.rpc.response.MarketActivityResponse;
import com.qding.sysconfig.rpc.service.MarketActivityRpcService;

/**
 * 活动
 * @author lichao
 *
 */
public class CallActivity extends Callable{
	
	@Autowired
	private MarketActivityRpcService marketActivityService;

	@Autowired
	private ISolrSkuService solrSku;

	@Autowired
	private PromotionService promotionService;
	
	/**
	 * 根据活动ID获取活动信息
	 * @param request
	 * @return
	 */
	@HTTP(alias="getActivityById")
	public Response<GetActivityByIdResponseData> getActivityById(GetActivityByIdRequest request) {
		
		try {
			MarketActivityResponse activityResponse = marketActivityService.getActivityByActivityId(request.getActivityId());
			
			List<MarketActivityModel> modelList = activityResponse.getMarketActivityModelList();
			
			if(modelList.isEmpty()) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "活动不存在");
			}
			
			MarketActivityModel activityModel = modelList.get(0);
			
			ActivityConfig config = activityModel.getActivityConfig();
			ActivityContent content = activityModel.getActivityContent();
			
			int type = StringUtils.isEmpty(content.getUrl()) ? 1 : 2;

			GetActivityByIdResponseData data = new GetActivityByIdResponseData();
			
			data.setEntity(new Activity(
					config.getActivityName(),
					content.getActivityDesc(),
					config.getNavigation(),
					type,
					content.getActivityId(), 
					content.getUrl()
			));
			
			Response<GetActivityByIdResponseData> response = new Response<>();
			
			response.setData(data);
			
			return response;
		} catch (Exception e) {
			return handleException(GetActivityByIdResponseData.class, e);
		}
	}
	
	/**
	 * 根据活动ID获取活动商品
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getGoodsByActivityId")
	public Response<GetGoodsByActivityIdResonseData> getGoodsByActivityId(GetGoodsByActivityIdRequest request) {

		try {
        
            Response<GetGoodsByActivityIdResonseData> response = new Response<GetGoodsByActivityIdResonseData>();

            List<String> wareIdList = marketActivityService.getGoodsIdByActivityId(request.getActivityId());
            
            if (wareIdList.isEmpty()) {
            	
            	GetGoodsByActivityIdResonseData data = new GetGoodsByActivityIdResonseData();
            	
            	response.setData(data);
            	
            	return response;
            }
            
            LegouSkuRequest skuRequest = new LegouSkuRequest();
            
            ArrayList<Long> skuIds = new ArrayList<Long>();
            
            for (String wareId : wareIdList) {
                
            	skuIds.add(Long.parseLong(wareId));
            }
            
            skuRequest.setSortedSkuIds(skuIds);
            
            skuRequest.setFindAllStatus(true);
            
//            skuRequest.setFindClickLike(true);

			skuRequest.setFindSellNum(true);

			skuRequest.setSortType(request.getSortType());
            
            LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
            
            checkAndContinue(skuResponse);

			List<Goods> goods = transforList(Goods.class, skuResponse.getSkus());

			List<Long> skuIdList = Lists.newArrayList();

			for (int h = 0; h < goods.size(); h++) {

				Goods good = goods.get(h);

				skuIdList.add(Long.parseLong(good.getSkuId()));
			}

			Map<Long,String[]> promotionNameMap = promotionService.getGoodActivityInfoBySkuids(skuIdList,request.getProjectId());

			for (int h = 0; h < goods.size(); h++) {

				Goods good = goods.get(h);

				String[] activityInfo = promotionNameMap.get(Long.parseLong(good.getSkuId()));

				good.setActivityInfo(activityInfo);//优惠活动

			}

            GetGoodsByActivityIdResonseData data = transfor(GetGoodsByActivityIdResonseData.class, skuResponse);

			data.setList(goods);
            
            response.setData(data);
            
            return response;
            
        } catch (Exception e) {

            return handleException(GetGoodsByActivityIdResonseData.class, e);
        }
	}

}
