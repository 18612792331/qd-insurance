package com.qding.api.call.app.qding.v1_3_0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.*;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.request.*;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.response.data.*;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.call.service.PromotionService;
import com.qding.api.util.ImageUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.api.util.WaterMarkConfigBean;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.basemodel.ResultPage;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.legou.domain.Recommend;
import com.qding.legou.domain.RecommendGoods;
import com.qding.legou.domain.UserCollect;
import com.qding.legou.service.ILegouRemoteService;
import com.qding.legou.struct.request.*;
import com.qding.legou.struct.response.*;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.solr.service.ISolrCategoryService;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.category.ShowCategory;
import com.qding.solr.struct.request.GetShowCategoryRequest;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.request.ShowCategoryRequest;
import com.qding.solr.struct.response.GetShowCategoryResponse;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.response.ShowCategoryResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import com.qding.sysconfig.domain.ActivityConfig;
import com.qding.sysconfig.domain.ActivityContent;
import com.qding.sysconfig.domain.ActivityGoods;
import com.qding.sysconfig.rpc.model.MarketActivityModel;
import com.qding.sysconfig.rpc.response.MarketActivityResponse;
import com.qding.sysconfig.rpc.service.MarketActivityRpcService;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


/**
 * 乐购
 * @author jiawenzheng
 *
 */
public class CallLegouGoods extends Callable{

	@Autowired
	private ISolrSkuService solrSku;

	@Autowired
	private ILegouRemoteService legouRemoteService;


	@Autowired
	private MarketActivityRpcService activityService;

	@Autowired
	private ISolrCategoryService solrCategoryService;

	@Autowired
	private SkipModeFitting skipMode;

	@Autowired
	private PromotionService promotionService;

	private static Logger logger = Logger.getLogger(CallLegouGoods.class);


	/**
	 * 乐购首页
	 * @param request
	 * @return
	 */
	@HTTP(alias = "leGouHomePage")
	public Response<GetHomePageForJoyBuyResponseData> leGouHomePage (GetHomePageForJoyBuyRequest request) {

		Response<GetHomePageForJoyBuyResponseData> response = null;

		try {

			Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());

			GetHomePageForJoyBuyResponseData  data = new GetHomePageForJoyBuyResponseData();

			HomePageEntity  entity = new HomePageEntity();

			entity.setRecommend(this.getHomeRecommend(request.getProjectId(), salePlatform));

			entity.setActivity(this.getActivityForBanner (request.getProjectId(), salePlatform));

			entity.setCategory(this.getCategoryEntityList(request.getProjectId(),salePlatform));

			data.setEntity(entity);

			response = new Response<GetHomePageForJoyBuyResponseData>();

			data.setMessage("查询成功");

			response.setData(data);

			return response;

		} catch (Exception e) {

			return handleException( GetHomePageForJoyBuyResponseData.class, e);
		}

	}


	/**
	 * 根据分类ID获取商品列表
	 * @param request
	 * @return
	 */
	@HTTP(alias = "selGoodsBySort")
	public Response<GetGoodsBySortResponseData> getGoodsBySort (GetGoodsBySortRequest request) {

		try {
			Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
			
			Response<GetGoodsBySortResponseData> response = new Response<GetGoodsBySortResponseData>();

			GetShowCategoryRequest categoryRequest = new GetShowCategoryRequest();

			categoryRequest.setId(request.getRevealCategoryId());

			GetShowCategoryResponse categoryResponse = solrCategoryService.querySingleShowCategory(categoryRequest);

			List<ShowCategory> categories = categoryResponse.getCategories();

			List<Long> subCategoryTypeList = Lists.newArrayList();

			if (QDStringUtil.isNotNull(categories) && categories.size()>0) {

				ShowCategory category = categories.get(0);

				String subCategoryStr = category.getRelCategoryId();

				if (QDStringUtil.isNull(subCategoryStr) || QDStringUtil.isEmpty(subCategoryStr)) {

					throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "没有关联相应品类");
				}

				String[] subCategory =subCategoryStr.split(",");

				for (int i=0;i<subCategory.length;i++){

					subCategoryTypeList.add(Long.parseLong(subCategory[i].toString()));
				}
			}

			if(QDStringUtil.isNull(subCategoryTypeList)) {

				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "分类参数传入错误");

			}

			LegouSkuRequest skuRequest = transfor(LegouSkuRequest.class, request);
			
			skuRequest.setSellPlatform(salePlatform);
			
			skuRequest.setCategoryIds(subCategoryTypeList);

			skuRequest.setFindClickLike(request.getFindClickLike());

			skuRequest.setFindSellNum(true);

			skuRequest.setMain(true);

			skuRequest.setIsAppSearch(1);

			String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
			if (Integer.parseInt(initVersion) <310000) {
				skuRequest.setExtQuery("NOT markingCode:("+ Constant.MRX_MAKING_CODE +" "+Constant.MRX_PSF_MAKING_CODE+")");
			}else {
				skuRequest.setExtQuery("NOT markingCode:("+Constant.MRX_PSF_MAKING_CODE+")");
			}

			LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);

			checkAndContinue(skuResponse);

			List<Goods> goods = transforList(Goods.class, skuResponse.getSkus());

			List<Long> skuIds = Lists.newArrayList();

			for (int h = 0; h < goods.size(); h++) {
				Goods good = goods.get(h);
				skuIds.add(Long.parseLong(good.getSkuId()));
				getGoodsImgForWaterMark(good ,"list");
			}

			//批量获取多个货品促销活动
			Map<Long,String[]> promotionNameMap =  promotionService.getGoodActivityInfoBySkuids(skuIds,request.getProjectId());
			//批量获取多个货品最优促销活动
			Map<Long,GoodsPromotion> optimizePromotionMap = promotionService.batchGetGoodsOptimizePromotion (skuIds,request.getProjectId());

			for (int h = 0; h < goods.size(); h++) {
				Goods good = goods.get(h);
				if (QDStringUtil.isNotNull(promotionNameMap) && promotionNameMap.containsKey(Long.parseLong(good.getSkuId()))) {
					String[] activityInfo = promotionNameMap.get(Long.parseLong(good.getSkuId()));
					good.setActivityInfo(activityInfo);//优惠活动
				}
				if (QDStringUtil.isNotNull(optimizePromotionMap) && optimizePromotionMap.containsKey(Long.parseLong(good.getSkuId()))) {
					GoodsPromotion goodsPromotion  = optimizePromotionMap.get(Long.parseLong(good.getSkuId()));//最优活动
					good.setOptimizePromotion(goodsPromotion);
					good.setPromotionPrice(QDStringUtil.isNotNull(goodsPromotion)&&QDStringUtil.isNotNull(goodsPromotion.getPromotionPrice())?goodsPromotion.getPromotionPrice():"");
					
					//3.3阶梯团购不能加入购物车20171017
					if(goodsPromotion.getPromotionType()==Constant.PromotionTypeEnum.TYPE_6.getCode()){
						good.setSupportCart(0);
					}
					
				}

			}

			GetGoodsBySortResponseData data = transfor(GetGoodsBySortResponseData.class, skuResponse);

			data.setTotalCount(Integer.parseInt(String.valueOf(skuResponse.getTotal())));

			data.setList(goods);

			response.setData(data);

			return response;

		} catch (Exception e) {

			return handleException(GetGoodsBySortResponseData.class, e);
		}
	}

	/**
	 * 收藏商品
	 * @param request
	 * @return
	 */
	@HTTP(alias = "addCollect")
	public Response<AddGoodsCollectResponseData> addCollect(AddGoodsCollectRequest request) {

		AddCollectResponse collectResponse = null;

		try {

			collectResponse = legouRemoteService.addCollect(transfor(AddCollectRequest.class, request));

			checkAndContinue(collectResponse);

			Response<AddGoodsCollectResponseData> response = new  Response<AddGoodsCollectResponseData>();

			response.setData(new AddGoodsCollectResponseData());

			return response;

		} catch (Exception e) {

			return handleException(AddGoodsCollectResponseData.class, e);
		}

	}


	/**
	 * 取消收藏商品
	 * @param request
	 * @return
	 */
	@HTTP(alias = "delCollect")
	public Response<DeleteGoodsCollectResponseData> delCollect (DeleteGoodsCollectRequest request) {

		DeleteCollectResponse  deleteResponse = null;

		try {

			deleteResponse = legouRemoteService.deleteCollect(transfor(DeleteCollectRequest.class, request));

			checkAndContinue(deleteResponse);

			Response<DeleteGoodsCollectResponseData> response = new  Response<DeleteGoodsCollectResponseData>();

			response.setData(new DeleteGoodsCollectResponseData());

			return  response;

		} catch (Exception e) {

			return handleException(DeleteGoodsCollectResponseData.class, e);
		}
	}



	/**
	 * 获取用户收藏商品列表
	 * @param request
	 * @return
	 */
	@HTTP(alias = "selCollectGoodsByUser")
	public Response<GetCollectGoodsByUserResponseData> getCollectGoodsByUser (GetCollectGoodsByUserRequest request) {

		Response<GetCollectGoodsByUserResponseData>  response = null;
		try {

			GetUserCollectResultPageResponse  userCollectResult = legouRemoteService.getUserCollectResultPage(transfor(GetUserCollectResultPageRequest.class, request));

			checkAndContinue(userCollectResult);

			ResultPage<UserCollect> userCollects = userCollectResult.getResultPage();

			List<UserCollect> userCollectList = userCollects.getItems();

			GetCollectGoodsByUserResponseData data = new GetCollectGoodsByUserResponseData();

			if (userCollectList.size()>0) {

				ArrayList<Long> sortedSkuIds = new ArrayList<Long>(userCollectList.size());

				for (UserCollect userCollect : userCollectList) {

					sortedSkuIds.add(userCollect.getWareSkuId());
				}

				LegouSkuRequest skuRequest = new LegouSkuRequest();

				skuRequest.setSortedSkuIds(sortedSkuIds);

				skuRequest.setFindClickLike(request.getFindClickLike());

				skuRequest.setFindSellNum(true);

				String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
				if (Integer.parseInt(initVersion) <310000) {
					skuRequest.setExtQuery("NOT markingCode:("+ Constant.MRX_MAKING_CODE +" "+Constant.MRX_PSF_MAKING_CODE+")");
				}else {
					skuRequest.setExtQuery("NOT markingCode:("+Constant.MRX_PSF_MAKING_CODE+")");
				}

				LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);

				checkAndContinue(skuResponse);

				List<Goods> goods = transforList(Goods.class, skuResponse.getSkus());

				List<Long> skuIds = Lists.newArrayList();

				for (int h = 0; h < goods.size(); h++) {

					Goods good = goods.get(h);

					skuIds.add(Long.parseLong(good.getSkuId()));
				}

				Map<Long,String[]> promotionNameMap = promotionService.getGoodActivityInfoBySkuids(skuIds,request.getProjectId());

				for (int h = 0; h < goods.size(); h++) {

					Goods good = goods.get(h);

					String[] activityInfo = promotionNameMap.get(Long.parseLong(good.getSkuId()));

					good.setActivityInfo(activityInfo);//优惠活动

				}

				data = transfor(GetCollectGoodsByUserResponseData.class, skuResponse);

				data.setTotalCount(userCollects.getTotalCount());

				data.setRecordCount(userCollects.getItems().size());

				data.setList(goods);
			}

			response = new Response<GetCollectGoodsByUserResponseData>();

			response.setData(data);

			return response;

		} catch (Exception e) {

			return handleException(GetCollectGoodsByUserResponseData.class, e);
		}
	}


	/**
	 * 获取商品详情
	 * @param request
	 * @return
	 */
	@HTTP(alias = "selGoodsDetails")
	@Deprecated
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
					wareImgs.add(ImageUtil.LgImg(img,2));
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
			goodsDetailsResponse.setEntity(goodsDetail);

			response.setData(goodsDetailsResponse);

			return response;

		} catch (Exception e) {

			return handleException(GetGoodsDetailsResponseData.class, e);
		}

	}


	/**
	 * 通过商品ID数组获得商品列表
	 * @param request
	 * @return
	 */
	@HTTP(alias = "selGoodsByGoodIdArray")
	public Response<GetGoodsByGoodIdArrayResponseData> getGoodsByGoodIdArray (GetGoodsByGoodIdArrayRequest request) {

		try {
			LegouSkuRequest skuRequest= new LegouSkuRequest();

			List<Long> sortedSkuids = Arrays.asList(request.getSkuIds());

			skuRequest.setProjectId(request.getProjectId());

			skuRequest.setSortedSkuIds(sortedSkuids);

			skuRequest.setFindAllStatus(request.getFindAllStatus());

			skuRequest.setFindClickLike(request.getFindClickLike());

			skuRequest.setFindSellNum(true);

			if (null != request.getSortType()){
				skuRequest.setSortType(request.getSortType());
			}

			LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);

			checkAndContinue(skuResponse);

			List<Goods> goods = transforList(Goods.class, skuResponse.getSkus());

			List<Long> skuIds = Lists.newArrayList();

			for (int h = 0; h < goods.size(); h++) {
				Goods good = goods.get(h);
				getGoodsImgForWaterMark(good,"list");
				skuIds.add(Long.parseLong(good.getSkuId()));
			}

			//批量获取多个货品促销活动
			Map<Long,String[]> promotionNameMap  = promotionService.getGoodActivityInfoBySkuids(skuIds,request.getProjectId());
			//批量获取多个货品最优促销活动
			Map<Long,GoodsPromotion> optimizePromotionMap = promotionService.batchGetGoodsOptimizePromotion (skuIds,request.getProjectId());


			for (int h = 0; h < goods.size(); h++) {
				Goods good = goods.get(h);
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

			GetGoodsByGoodIdArrayResponseData data =  new GetGoodsByGoodIdArrayResponseData();//transfor(GetGoodsByGoodIdArrayResponseData.class, skuResponse);

			data.setList(goods);

			Response<GetGoodsByGoodIdArrayResponseData> response = new Response<GetGoodsByGoodIdArrayResponseData> ();

			response.setData(data);

			return response;

		} catch (Exception e) {

			return handleException(GetGoodsByGoodIdArrayResponseData.class, e);
		}
	}


	/**
	 * 通过推荐id查询商品列表
	 * @param request
	 * @return
	 */
	@HTTP(alias = "selGoodsByRecommendId")
	public Response<GetGoodsByRecommendIdResponseData> getGoodsByRecommendId (GetGoodsByRecommendIdRequest request) {

		try {

			Response<GetGoodsByRecommendIdResponseData> response = null;

			FindRecommendGoodsRequest  request1 = transfor(FindRecommendGoodsRequest.class, request);

			FindRecommendGoodsResponse  recommendResponse = legouRemoteService.findRecommendGoods(request1);

			checkAndContinue(recommendResponse);

			ResultPage<RecommendGoods> page = recommendResponse.getResultPage();

			List<RecommendGoods> items = page.getItems();

			ArrayList<Long> skuIdSet = new ArrayList<Long>();

			for (RecommendGoods goods : items) {

				Long skuId = goods.getWareSkuId();

				skuIdSet.add(skuId);
			}

			LegouSkuRequest  skuRequest = new LegouSkuRequest();

			skuRequest.setSortedSkuIds(skuIdSet);

			skuRequest.setFindClickLike(request.getFindClickLike());

			skuRequest.setFindSellNum(true);

			if (QDStringUtil.isNotNull(request.getPage()) && QDStringUtil.isNotNull(request.getSize())) {

				skuRequest.setPageno(request.getPage());
				skuRequest.setPagesize(request.getSize());
			}

			LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);

			checkAndContinue(skuResponse);

			List<Goods> goods = transforList(Goods.class, skuResponse.getSkus());

			GetGoodsByRecommendIdResponseData recommend = new GetGoodsByRecommendIdResponseData();

			if (goods.size()>0) {

				List<Long> skuIds = Lists.newArrayList();

				for (int h = 0; h < goods.size(); h++) {

					Goods good = goods.get(h);

					getGoodsImgForWaterMark(good,"list");

					skuIds.add(Long.parseLong(good.getSkuId()));
				}

				//批量获取多个货品促销活动
				Map<Long,String[]> promotionNameMap  = promotionService.getGoodActivityInfoBySkuids(skuIds,request.getProjectId());
				//批量获取多个货品最优促销活动
				Map<Long,GoodsPromotion> optimizePromotionMap = promotionService.batchGetGoodsOptimizePromotion (skuIds,request.getProjectId());

				for (int h = 0; h < goods.size(); h++) {

					Goods good = goods.get(h);
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

				int recordCount =items.size();

				int totalCount = page.getTotalCount();

				recommend = new GetGoodsByRecommendIdResponseData(recordCount, totalCount, goods);

			}

			response = new Response<GetGoodsByRecommendIdResponseData>();

			response.setData(recommend);

			return response;

		} catch (Exception e) {

			return handleException(GetGoodsByRecommendIdResponseData.class, e);
		}

	}


	/**
	 * 通过skuId 获取当前货品所属的城市列表和社区列表
	 * @param request
	 * @return
	 */
	@HTTP(alias = "selGoodProjectAndCityBySkuId")
	public Response<GetGoodProjectAndCityBySkuIdResponseData> getGoodProjectAndCityBySkuId (GetGoodProjectAndCityBySkuIdRequest request ) {

		Response<GetGoodProjectAndCityBySkuIdResponseData> response = new Response<GetGoodProjectAndCityBySkuIdResponseData>();

		LegouSkuRequest  skuRequest = new LegouSkuRequest();
		ArrayList<Long> sortedSkuIds = new ArrayList<Long>();

		sortedSkuIds.add(request.getSkuId());

		skuRequest.setSortedSkuIds(sortedSkuIds);

		if (QDStringUtil.isNotNull(request.getProjectId())) {

			skuRequest.setProjectId(request.getProjectId());
		}

		LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);

		try {

			checkAndContinue(skuResponse);

			GoodProjectAndCity pcity = new GoodProjectAndCity();

			if (QDStringUtil.isNotNull(skuResponse.getSkus()) && skuResponse.getSkus().size() > 0) {

				LegouSkuDetailInfo detailInfo = skuResponse.getSkus().get(0);

				if (QDStringUtil.isNotNull(detailInfo.getProjectId())) {

					Long[] cityIds = detailInfo.getCityId();

					String[] cityNames = detailInfo.getCityName();

					Long[] projectIds = detailInfo.getProjectId();

					String[] projectNames = detailInfo.getProjectName();

					for (int i = 0; i < projectIds.length; i++) {

						try {

							//如果不为空,且满足查询要求
							if ( QDStringUtil.isNotNull( request.getProjectId() ) && projectIds[i] == request.getProjectId()) {

								pcity.setId(projectIds[i]);
								pcity.setName(projectNames[i]);
								pcity.setCityId(cityIds[i]);
								pcity.setCityName(cityNames[i]);

								break;
							}

							if ( i+1 >= projectIds.length ) {

								pcity.setId(projectIds[i]);
								pcity.setName(projectNames[i]);
								pcity.setCityId(cityIds[i]);
								pcity.setCityName(cityNames[i]);
								break;
							}

						} catch (Exception e) {

							continue;
						}

					}
				}



			}

			GetGoodProjectAndCityBySkuIdResponseData data = new GetGoodProjectAndCityBySkuIdResponseData();

			data.setEntity(pcity);

			response.setData(data);

			return response;

		} catch (ServiceException e) {

			return handleException(GetGoodProjectAndCityBySkuIdResponseData.class, e);
		}

	}


	/**
	 * 获取首页推荐商品信息
	 * @return
	 */
	private  List<HomeRecommend>  getHomeRecommend (Long projectId, Integer salePlatform) throws Exception{

		FindRecommendsWithGoodsRequest  recommendsRequest = new FindRecommendsWithGoodsRequest();

		recommendsRequest.setProjectId(projectId);

		recommendsRequest.setSalePlatform(salePlatform);

		FindRecommendsWithGoodsResponse  recommendsResponse = legouRemoteService.findRecommendsWithGoods(recommendsRequest);

		checkAndContinue(recommendsResponse);

		List<Recommend> recommendList = recommendsResponse.getRecommendList();

		List<HomeRecommend> list = new ArrayList<HomeRecommend>();

		for (Recommend recommend : recommendList) {

			List<RecommendGoods> recommendGoods = recommend.getGoodsList();

			if (QDStringUtil.isNull(recommendGoods) ||  recommendGoods.size()== 0) continue;

			ArrayList<Long> skuIdSet = new ArrayList<Long>();

			for (RecommendGoods goods : recommendGoods)

				skuIdSet.add(goods.getWareSkuId());

			LegouSkuRequest  skuRequest = new LegouSkuRequest();

			skuRequest.setSortedSkuIds(skuIdSet);

			skuRequest.setFindClickLike(true);

			skuRequest.setFindSellNum(true);

			if (skuIdSet.size() == 0) continue;

			LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);

			checkAndContinue(skuResponse);

			List<Goods> goods = transforList(Goods.class, skuResponse.getSkus());

			List<Long> skuIds = Lists.newArrayList();

			for (int h = 0; h < goods.size(); h++) {

				Goods good = goods.get(h);

				skuIds.add(Long.parseLong(good.getSkuId()));
			}

			Map<Long,String[]> promotionNameMap = promotionService.getGoodActivityInfoBySkuids(skuIds,projectId);

			for (int h = 0; h < goods.size(); h++) {

				Goods good = goods.get(h);

				String[] activityInfo = promotionNameMap.get(Long.parseLong(good.getSkuId()));

				good.setActivityInfo(activityInfo);//优惠活动

			}

			HomeRecommend  homeRecommend = new HomeRecommend(String.valueOf(recommend.getId()), recommend.getName(), goods);

			list.add(homeRecommend);
		}

		return list;

	}



	/**
	 * 通过skuId 获取当前货品所属的城市列表和社区列表
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getCategory")
	public Response<GetCategoryResponseData> getCategory (GetCategoryRequest request) {

		try {
			Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());

			Response<GetCategoryResponseData> response = new  Response<GetCategoryResponseData>();

			GetCategoryResponseData data = new GetCategoryResponseData();

			List<CategoryEntity> list = this.getCategoryEntityList(request.getProjectId(),salePlatform);

			data.setList(list);

			response.setCode(HttpStatus.OK.getStatusCode());

			response.setData(data);

			return response;

		}catch (Exception e) {

			return handleException( GetCategoryResponseData.class, e);
		}

	}


	protected  List<CategoryEntity> getCategoryEntityList (Long projectId,Integer salePlatform) {

		List<CategoryEntity> list = Lists.newArrayList();

		ShowCategoryRequest request = new ShowCategoryRequest(0l);

		request.setProjectId(projectId);

		request.setSellPlatform(salePlatform);

		ShowCategoryResponse categoryResponse = solrCategoryService.queryShowCategory(request);

		List<ShowCategory> categoryList = categoryResponse.getCategories();

		for (ShowCategory category:categoryList){

			CategoryEntity categoryEntity = new CategoryEntity(String.valueOf(category.getId()),category.getName(),category.getLogoUrl());
			list.add(categoryEntity);
		}

		return list;
	}

	/**
	 * 获取某社区某商品类别下的活动信息(Banner)
	 * @param projectId
	 * @param salePlatForm
	 * @return
	 */
	private List<Activity> getActivityForBanner (Long projectId,Integer salePlatForm) throws Exception{

		//活动banner
		MarketActivityResponse activityResponse = activityService.getLeGouIndexActivity(String.valueOf(projectId), salePlatForm);

		checkAndContinue(activityResponse);

		List<MarketActivityModel> activityInfoList = activityResponse.getMarketActivityModelList();

		List<Activity> activityList =new ArrayList<Activity>();

		if (activityInfoList!=null && activityInfoList.size()>0) {

			for (MarketActivityModel marketActivityModel : activityInfoList) {

				ActivityConfig config = marketActivityModel.getActivityConfig();

				ActivityContent content =marketActivityModel.getActivityContent();

				Activity   activity = transfor(Activity.class, config);

				transfor(activity, content);

				List<ActivityGoods> goodsList = marketActivityModel.getActivityGoodsList();

				if (goodsList !=null && goodsList.size()>0){

					ArrayList<String> goodsArray = new ArrayList<String>();

					for (ActivityGoods activityGoods : goodsList)  goodsArray.add(activityGoods.getGoodsId());

					activity.setSkuIds(goodsArray);
				}

				activityList.add(activity);
			}

		}

		return activityList;

	}



	/**
	 * 获取商品的水印图片
	 * @param good
	 */
	public static void getGoodsImgForWaterMark(Goods good,String type) {

	}

	/**
	 * 获取乐购水印图片
	 * @param type
	 * @param pipeLine
	 * @param sourceImgUrl
	 * @param waterMarkImgUrl
     * @return
     */
	public static String getGoodsWaterMarkImg (String type,boolean pipeLine,String sourceImgUrl,String waterMarkImgUrl ){

		try {
			String switchStatus = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_WATER_MARK_CONFIG_1.getGroupName(),Constant.Dict_K_V_Enum.DICT_WATER_MARK_CONFIG_1.getDictKey());

			if (!"open".equals(switchStatus)) {
				return sourceImgUrl;
			}

			WaterMarkConfigBean waterMarkConfigBean = new WaterMarkConfigBean();
			String configJsonStr = "";
			if ( "list".equals(type)){
				configJsonStr = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_WATER_MARK_CONFIG_2.getGroupName(),Constant.Dict_K_V_Enum.DICT_WATER_MARK_CONFIG_2.getDictKey());
			} else {
				configJsonStr = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_WATER_MARK_CONFIG_3.getGroupName(),Constant.Dict_K_V_Enum.DICT_WATER_MARK_CONFIG_3.getDictKey());
			}
			if (QDStringUtil.isEmpty(configJsonStr)) return sourceImgUrl;

			JSONObject waterMarkConfigJson = JSON.parseObject(configJsonStr);
			waterMarkConfigBean = waterMarkConfigJson.getObject("configObj",WaterMarkConfigBean.class);
			waterMarkConfigBean.setPipeline(pipeLine);
			waterMarkConfigBean.setWaterMarkImgUrl(waterMarkImgUrl);

			String targetImgUrl = ImageUtil.getImgForWaterMark ( sourceImgUrl, waterMarkConfigBean);
			return  targetImgUrl;

		} catch (TException e) {
			e.printStackTrace();
			return  sourceImgUrl;
		}

	}


}





