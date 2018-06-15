package com.qding.api.call.app.qding.v1_2_1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.CommunitySale;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.PushTag;
import com.qding.api.call.app.qding.v1_2_1.struct.hotcycle.CycleBanner;
import com.qding.api.call.app.qding.v1_2_1.struct.hotcycle.request.GetCommunityIndexByIdRequest;
import com.qding.api.call.app.qding.v1_2_1.struct.hotcycle.response.data.GetCommunityIndexByIdResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.hk.domain.ResNotice;
import com.qding.hk.rpc.request.notice.GetAppNoticeRequest;
import com.qding.hk.rpc.response.notice.GetAppNoticeResponse;
import com.qding.hk.rpc.service.INoticeRpcService;
import com.qding.hotcycle.service.IHotCycleRemoteService;
import com.qding.hotcycle.struct.request.GetCommunityPushTagRequest;
import com.qding.hotcycle.struct.response.GetCommunityPushTagResponse;
import com.qding.sysconfig.domain.ActivityConfig;
import com.qding.sysconfig.domain.ActivityContent;
import com.qding.sysconfig.rpc.model.MarketActivityModel;
import com.qding.sysconfig.rpc.response.MarketActivityResponse;
import com.qding.sysconfig.rpc.service.MarketActivityRpcService;

/**
 * 
 * 热圈 version 1.2.1
 * @author lichao
 *
 */
public class CallHotcycle extends com.qding.api.call.app.qding.v1_2_0.CallHotcycle{
	
	
	@Autowired
	private IHotCycleRemoteService hotCycleService;
	
	@Autowired
	private INoticeRpcService noticeService;
	
	@Autowired
	private MarketActivityRpcService marketActivityService;
	
	@Autowired
	private AppConfigRemote appConfigRemote;
	
	/**
	 * 获取社区首页
	 * @param request
	 * @return
	 */
	@HTTP(alias="getCommunityIndex")
	@Deprecated
	public Response<GetCommunityIndexByIdResponseData> getCommunityIndex(GetCommunityIndexByIdRequest request) {
	
		Response<GetCommunityIndexByIdResponseData> response = new Response<GetCommunityIndexByIdResponseData>();
		response.setCode(200);
		
		try {
			
			Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
			Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());

			GetAppNoticeRequest noticeRequest = new GetAppNoticeRequest();
			noticeRequest.setPageSize(4);
			noticeRequest.setPosition(2);
			Long projectId = Long.parseLong(request.getCommunityId());
			noticeRequest.setProjectId(projectId);
			noticeRequest.setAppDevice(request.getAppDevice());
			
			List<ResNotice> noticeList = new ArrayList<>();
			
			/**
			 * 社区首页轮播
			 */
			try {
				GetAppNoticeResponse noticeResponse = noticeService.getAppNoticeList(noticeRequest);
				
				noticeList = noticeResponse.getNoticeList();
				
				if(noticeList == null) {
					
					noticeList = new ArrayList<ResNotice>();
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			List<CycleBanner> banners = transforList(CycleBanner.class, noticeList);
			
			/**
			 * 社区首页业态
			 */
			String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());
			
			List<ServiceItem> serviceItems = appConfigRemote.getBindServiceItemListbyProjectId(projectId, 
					Constant.SERVICE_ITEM_HOME, 
					version,
					brickSourceType
				);
			
			List<ProjectService> services = transforList(ProjectService.class, serviceItems);
			
			/**
			 * 社区首页活动
			 */
			MarketActivityResponse activityResponse = marketActivityService.getIndexActivity(request.getCommunityId(), salePlatform);
		
			List<MarketActivityModel> activities = activityResponse.getMarketActivityModelList();
				
			List<CommunitySale> sales = new ArrayList<CommunitySale>();

			for(MarketActivityModel activity : activities) {
				
				ActivityConfig config = activity.getActivityConfig();
				ActivityContent content = activity.getActivityContent();

				int type = StringUtils.isEmpty(content.getUrl()) ? 1 : 2;
				
				sales.add(
						new CommunitySale(
								config.getActivityName(),
								content.getActivityDesc(),
								config.getNavigation(),
								type,
								content.getActivityId(), 
								content.getUrl()
						));
			}
			
			/**
			 * 社区首页推送标签
			 */
			GetCommunityPushTagRequest pushTagRequest = new GetCommunityPushTagRequest();
			
			pushTagRequest.setCommunityId(request.getCommunityId());
			
			GetCommunityPushTagResponse pushTagResponse = hotCycleService.getCommunityPushTag(pushTagRequest);
			
			List<PushTag> pushTags = new ArrayList<>();
			
			List<com.qding.hotcycle.struct.PushTag> tags = pushTagResponse.getTags();
			
			if(tags == null) {
				tags = new ArrayList<>();
			}
			
			for(com.qding.hotcycle.struct.PushTag t : tags) {
				pushTags.add(transfor(PushTag.class, t));
			}

			
			response.setData(new GetCommunityIndexByIdResponseData(
					banners,
					services,
					pushTags, 
					sales
			));
			
			return response;
			
		} catch (Exception e) {

			response = handleException(GetCommunityIndexByIdResponseData.class, e);
			
		} finally {
		}
		
		return response;
	}
	
	
}
