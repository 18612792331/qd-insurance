package com.qding.api.call.app.qding.v1_3_2;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_1.struct.project.ActivityBanner;
import com.qding.api.call.app.qding.v1_3_1.struct.project.ActivitySale;
import com.qding.api.call.app.qding.v1_3_1.struct.project.ProjectIndex;
import com.qding.api.call.app.qding.v1_3_1.struct.project.RecommendTag;
import com.qding.api.call.app.qding.v1_3_1.struct.project.request.GetProjectIndexRequest;
import com.qding.api.call.app.qding.v1_3_1.struct.project.request.GetServiceIsOpenByProjectIdRequest;
import com.qding.api.call.app.qding.v1_3_1.struct.project.response.data.GetProjectIndexResponseData;
import com.qding.api.call.app.qding.v1_3_1.struct.project.response.data.GetServiceIsOpenByProjectIdResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.ImageUtil;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hk.rpc.request.notice.GetAppNoticeRequest;
import com.qding.hk.rpc.response.notice.GetAppNoticeResponse;
import com.qding.hk.rpc.service.INoticeRpcService;
import com.qding.hotcycle.service.IHotCycleRemoteService;
import com.qding.hotcycle.struct.request.GetHomeRecommendTagRequest;
import com.qding.hotcycle.struct.response.GetHomeRecommendTagResponse;
import com.qding.sysconfig.domain.ActivityConfig;
import com.qding.sysconfig.domain.ActivityContent;
import com.qding.sysconfig.rpc.model.MarketActivityModel;
import com.qding.sysconfig.rpc.response.MarketActivityResponse;
import com.qding.sysconfig.rpc.service.MarketActivityRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.*;


/**
 * app社区
 * @author lichao
 *
 */
public class CallProject extends Callable{

	
	@Autowired
	private INoticeRpcService noticeService;
	
	@Autowired
	private MarketActivityRpcService marketActivityService;

	@Autowired
	private AppConfigRemote appConfigRemote;
	
	@Autowired
	private IHotCycleRemoteService hotcycleService;
	
	@Autowired
	private ImageUtil imageUtil;

	@Autowired
	private ProjectReadRemote projectReadService;
	
	/**
	 * 社区首页
	 * @param request
	 * @return
	 */
	@HTTP(alias="index")
	@Deprecated
	public Response<GetProjectIndexResponseData> index(GetProjectIndexRequest request) {

		try {

			Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
			Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
			String serviceItemHome = Constant.transforServiceByAppDevice(Constant.serviceItemHomeMap, request.getAppDevice());
			Response<GetProjectIndexResponseData> response = new Response<>();
			
			GetAppNoticeRequest noticeRequest = new GetAppNoticeRequest();
			noticeRequest.setPageSize(6);
			noticeRequest.setPosition(2);
			Long projectId = Long.parseLong(request.getProjectId());
			noticeRequest.setProjectId(projectId);
			noticeRequest.setAppDevice(request.getAppDevice());
			
			/**
			 * 社区首页轮播
			 */
			GetAppNoticeResponse noticeResponse = noticeService.getAppNoticeList(noticeRequest);
			
			List<ActivityBanner> activityBannerFloor1 = transforList(ActivityBanner.class, noticeResponse.getNoticeList());
			
			List<ActivityBanner> activityBannerFloor2 = transforList(ActivityBanner.class, noticeResponse.getFooterNoticeList());
			
			/**
			 * 社区首页业态
			 */
			String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());

			List<ServiceItem> serviceItems = appConfigRemote.getBindServiceItemListbyProjectId(projectId,
					serviceItemHome,
					version,
					brickSourceType
				);
			
			List<ProjectService> services = transforList(ProjectService.class, serviceItems);

			if (QDStringUtil.isNotNull(services) && services.size()>0) {
				for (ProjectService service : services) {
					if ("MY_KEY".equals(service.getContent()) && "1.4.0".equals(request.getAppDevice().getQdVersion())){
						service.setDesc("蓝牙仅限1.4.1版用户");
						break;
					}

				}
			}
			/**
			 * 社区首页活动
			 */
			MarketActivityResponse activityResponse = marketActivityService.getIndexActivity(
					request.getProjectId(), 
					salePlatform
			);

			List<ActivitySale> activitySales = new ArrayList<>();

			activitySales.addAll(getActivitySale(activityResponse.getMarketActivityModelList()));
			activitySales.addAll(getActivitySale(activityResponse.getSecondMarketActivityModelList()));
			activitySales.addAll(getActivitySale(activityResponse.getThridMarketActivityModelList()));
			
			Iterator<ActivitySale> activitySaleIterator = activitySales.iterator();
			
			/**
			 * 首页推荐标签
			 */
			GetHomeRecommendTagResponse recommendTagResponse = hotcycleService.getHomeRecommend(
					new GetHomeRecommendTagRequest(
							request.getProjectId(),
							7
					));
			
			List<RecommendTag> recommendTags = transforList(RecommendTag.class, recommendTagResponse.getList());

			Project project = projectReadService.get(Long.parseLong(request.getProjectId()));

			if(project == null) {

				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
			}

			com.qding.api.call.app.qding.v1_3_0.struct.brick.Project p =
					transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Project.class, project);

			Set<String> phoneSet = new HashSet<String>();

			List<ProjectConcat> projectConcats = p.getConcats();
			for (ProjectConcat projectConcat : projectConcats) {

				if("1".equals(projectConcat.getType())){

					String[] phoneStrings = projectConcat.getPhones();
					for (String phone : phoneStrings) {
						phoneSet.add(phone);
					}
				}
			}
			List<String> phoneList = new ArrayList<String> ();
			if(phoneSet.size()>0)
				phoneList.addAll(phoneSet);

			GetProjectIndexResponseData data = new GetProjectIndexResponseData(new ProjectIndex(
					activityBannerFloor1, 
					activityBannerFloor2, 
					services,
					recommendTags,
					buildFloor(activitySaleIterator, 3),
					buildFloor(activitySaleIterator, 3),
					buildFloor(activitySaleIterator, 3),
					phoneList
					)
			);

			response.setData(data);
			return response;
		} catch (Exception e) {
			return handleException(GetProjectIndexResponseData.class, e);
		}
		
	}

	private List<ActivitySale> getActivitySale(
			List<MarketActivityModel> activities) {
		
		List<ActivitySale> activitySalesFloor = new ArrayList<ActivitySale>();

		if(activities == null) {
			return activitySalesFloor;
		}
		
		for(MarketActivityModel activity : activities) {
			
			ActivityConfig config = activity.getActivityConfig();
			ActivityContent content = activity.getActivityContent();

			int type = StringUtils.isEmpty(content.getUrl()) ? 1 : 2;
			
			activitySalesFloor.add(
					new ActivitySale(
							config.getActivityName(),
							content.getActivityDesc(),
							config.getNavigation(),
							type,
							content.getActivityId(), 
							content.getUrl()
					));
		}
		return activitySalesFloor;
	}
	
	private <T> List<T> buildFloor(Iterator<T> iterator, int size) {

		int count = 1;
		
		List<T> result = new ArrayList<>();
		
		while(iterator.hasNext() && count <= size) {
			result.add(iterator.next());
			count ++;
		}
		
		return result;
	}

	/**
	 * 获取指定业态在当前社区下是否开放
	 * @param request
	 * @return
	 */
	@HTTP(alias="serviceStatus")
	public Response<GetServiceIsOpenByProjectIdResponseData>  getServiceIsOpenByProjectId (GetServiceIsOpenByProjectIdRequest request) {

		try {

			Response<GetServiceIsOpenByProjectIdResponseData>  response = new  Response<GetServiceIsOpenByProjectIdResponseData>();
			GetServiceIsOpenByProjectIdResponseData data = new GetServiceIsOpenByProjectIdResponseData();
			data.setStatus(0);
			Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());

			/**
			 * 社区首页业态
			 */
			String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());

			String projectId = request.getProjectId();

			String serviceType = request.getServiceType();

			List<ServiceItem> serviceItems = appConfigRemote.getBindServiceItemListbyProjectId(Long.parseLong(projectId),
					Constant.SERVICE_ITEM_HOME,
					version,
					brickSourceType
			);

			for (ServiceItem serviceObj : serviceItems) {

				if (serviceType.equals(serviceObj.getContant())) {
					data.setStatus(1);
					break;
				}
			}

			response.setCode(HttpStatus.OK.getStatusCode());
			response.setData(data);
			return  response;

		} catch (Exception e) {
			return handleException(GetServiceIsOpenByProjectIdResponseData.class, e);
		}
	}
}
