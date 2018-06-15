package com.qding.api.call.app.qding.v2_4_0;

import java.util.*;

import com.qding.api.call.service.PromotionService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsActivity;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsDetail;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.request.GetGoodsDetailsRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.request.SetSellRemindRequest;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.response.data.GetGoodsDetailsResponseData;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.response.data.SetSellRemindResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.ImageUtil;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.ware.SubscribeRemote;
import com.qding.core.common.Result;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
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


/**
 * 乐购
 * @author jiawenzheng
 *
 */
public class CallLegouGoods extends com.qding.api.call.app.qding.v2_3_0.CallLegouGoods{


	@Autowired
	private ISolrSkuService solrSku;

	@Autowired
	private ILegouRemoteService legouRemoteService;

	@Autowired
	private IPromotionRemoteService promotionRemoteService;

	@Autowired
	private SubscribeRemote subscribeRemote;

	@Autowired
	private ClauseConfigRpcService clauseConfigRpcService;

	@Autowired
	private PromotionService promotionService;
	

	private static Logger logger = Logger.getLogger(CallLegouGoods.class);

	/**
	 * 获取商品详情
	 * @param request
	 * @return
	 */
	@HTTP(alias = "selGoodsDetails")
	@ExplainAnnotation(explain = " 获取商品详情")
	@Deprecated
	public Response<GetGoodsDetailsResponseData> getGoodsDetails (GetGoodsDetailsRequest request) {

		try {
			GetGoodsDetailsResponseData goodsDetailsResponse = new GetGoodsDetailsResponseData();
			Response<GetGoodsDetailsResponseData>  response = new Response<GetGoodsDetailsResponseData>();
			String memberId = request.getMemberId();
			Long projectId = request.getProjectId();

			LegouSkuRequest skuRequest=transfor(LegouSkuRequest.class, request);
			skuRequest.setFindSellNum(request.getFindSellNum());
			skuRequest.setFindSkuStock(request.getFindSkuStock());
			ArrayList<Long> sortedSkuIds = new ArrayList<Long>();
			sortedSkuIds.add(request.getSkuId());
			skuRequest.setSortedSkuIds(sortedSkuIds);
			//如果是查看分享的商品详情
			if (request.getShowShare()) {
				skuRequest.setProjectId(null);
			}
			LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
			checkAndContinue(skuResponse);
			List<LegouSkuDetailInfo> skuInfoList= skuResponse.getSkus();
			if (skuInfoList ==null || skuInfoList.size() <=0 ){
				 throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "亲，该商品可能已经下架，请选择其他商品");
			}

			//如果是查看分享的商品详情
			if (request.getShowShare()) {
				LegouSkuDetailInfo skuDetailInfo = skuInfoList.get(0);
				Long[] projectIds = skuDetailInfo.getProjectId();
				if (projectIds.length>0) {
					for (Long id : projectIds) {
						if (id == -1L) break;
						if (id == request.getProjectId()){
							skuRequest.setProjectId(request.getProjectId());
							skuResponse = solrSku.queryLegouSku(skuRequest);
							skuInfoList= skuResponse.getSkus();
							break;
						}
					}
				}
			}

			LegouSkuDetailInfo skuDetailInfo = skuInfoList.get(0);
			GoodsDetail goodsDetail = transfor(GoodsDetail.class, skuDetailInfo);

			if (QDStringUtil.isEmpty(skuDetailInfo.getMarkingName())) {
				if (Constant.DeliveryType.Logistics.getValue() == skuDetailInfo.getDeliveryType()){
					goodsDetail.setDeliveryRemark(Constant.DeliveryTypeName.Logistics.getValue());
				} else if (Constant.DeliveryType.PropertySelf.getValue() == skuDetailInfo.getDeliveryType() ) {
					goodsDetail.setDeliveryRemark(Constant.DeliveryTypeName.PropertySelf.getValue());
				}
			}

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

			if (!QDStringUtil.isNull(memberId)){

				IsInCollectRequest isInCollectRequest = new IsInCollectRequest();
				isInCollectRequest.setMid(memberId);
				isInCollectRequest.setProjectId(projectId);
				isInCollectRequest.setWareSkuId(Long.parseLong(goodsDetail.getSkuId()));
				IsInCollectResponse isInCollect = legouRemoteService.isInCollect(isInCollectRequest);
				Boolean checkGoodsIsCollect =  isInCollect.isInCollect();
				if (checkGoodsIsCollect)  goodsDetail.setIsCollect("1");
			}

			if (QDStringUtil.isNotNull(request.getProjectId())) {
				List<GoodsPromotion>  goodsPromotionList = promotionService.getGoodsPromotions(Long.parseLong(goodsDetail.getSkuId()), projectId);
				goodsDetail.setGoodsPromotionList(goodsPromotionList); //促销活动信息
			}
			goodsDetail.setWareComments(getWareCommentForIndex(skuDetailInfo.getWareId(),1,2)); //评论信息

			List<GoodsActivity> goodsActivityList = Lists.newArrayList();
			goodsDetail.setGoodsActivityList(goodsActivityList);

			if ("1".equals(goodsDetail.getStatus()) && 1 <= goodsDetail.getCount()) {
				Integer limitCount = -1;
				Integer availableCount = -1;
				//获取限购情况
				if (QDStringUtil.isNotNull(request.getProjectId())) {
					List<Long> skuIds = Lists.newArrayList();
					skuIds.add(request.getSkuId());
					Map<Long, Map<String, Object>> limitMap = promotionService.batchGetSkuLimitListByWareIds(QDStringUtil.isNotNull(request.getMemberId()) ? request.getMemberId() : "",skuIds,request.getProjectId());
					if (QDStringUtil.isNotNull(limitMap) && limitMap.containsKey(request.getSkuId())) {
						limitCount = Integer.parseInt(limitMap.get(request.getSkuId()).get("limitCount").toString()); //限购数量
						availableCount = Integer.parseInt(limitMap.get(request.getSkuId()).get("availableCount").toString());//可购买数量
					}
				}

				goodsDetail.setRestrictionBuyNum(limitCount);
				goodsDetail.setSurplusBuyNum(availableCount);
				goodsDetail.setBuyBtnName("立即购买");
				goodsDetail.setBuyBtnStatus(1);

			} else {
				goodsDetail.setSoldOutMsg("商品已售罄，点击“开售提醒”接收开售通知");
			}

			Date endTime = skuDetailInfo.getEndTime();
			Date nowTime = new Date();
			long deltaT=endTime.getTime()-nowTime.getTime();
			goodsDetail.setDiscountTimeLeft(deltaT);
			if (QDStringUtil.isNotNull(skuDetailInfo.getProviderName())) {
				goodsDetail.setDesc(goodsDetail.getDesc() + "<br><div style=\"color: #ccc;text-align: center\">本商品(服务)由"+skuDetailInfo.getProviderName()+"提供</div>");
			}

			//2.5版本增加商品条款信息
			List< com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.ClauseConfig> clauseConfigList = Lists.newArrayList();
			try {
				List<ClauseConfig>  clauseConfigs = clauseConfigRpcService.selClauseConfigByProductNo("NG");
				if (QDStringUtil.isNotNull(clauseConfigs) && clauseConfigs.size()>0){
					clauseConfigList = transforList(com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.ClauseConfig.class,clauseConfigs);

				}
			}catch (Exception ex){
				logger.error("获取商城商品条款错误：",ex);
			}
			goodsDetail.setClauseConfigList(clauseConfigList);
			goodsDetailsResponse.setEntity(goodsDetail);

			if (!request.getShowShare() &&  QDStringUtil.isNotEmpty(memberId) && QDStringUtil.isNotEmpty(String.valueOf(projectId))){
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
	 * 开售提醒
	 * @param request
	 * @return
	 */
	@HTTP(alias = "setSellRemind")
	@ExplainAnnotation(explain = " 开售提醒")
	public Response<SetSellRemindResponseData> setSellRemind (SetSellRemindRequest request) {

		Response<SetSellRemindResponseData> response = new  Response<SetSellRemindResponseData>();
		SetSellRemindResponseData data = new SetSellRemindResponseData();
		response.setCode(HttpStatus.OK.getStatusCode());
		Result result = subscribeRemote.skuSubscribe(request.getMemberId(),Long.parseLong(request.getSkuId()),Long.parseLong(request.getProjectId()));
		if (!result.isSuccess()) {
			response.setCode(Integer.parseInt(result.getErrCode()));
			data.setMessage(result.getErrorMsg());
			response.setData(data);
		} else {
			data.setToast("提醒成功，商品开售后会及时通知您");
			response.setData(data);
		}
		return response;
	}

}





