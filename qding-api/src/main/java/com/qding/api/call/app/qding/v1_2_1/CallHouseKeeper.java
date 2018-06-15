package com.qding.api.call.app.qding.v1_2_1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v1_2_1.struct.housekeeper.HKCycleBanner;
import com.qding.api.call.app.qding.v1_2_1.struct.housekeeper.request.GetHKIndexRequest;
import com.qding.api.call.app.qding.v1_2_1.struct.housekeeper.response.data.GetHKIndexResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.hk.rpc.request.notice.GetAppNoticeRequest;
import com.qding.hk.rpc.response.notice.GetAppNoticeResponse;
import com.qding.hk.rpc.service.INoticeRpcService;

/**
 * 管家
 * @author lichao
 *
 */
public class CallHouseKeeper extends Callable{


	@Autowired
	private INoticeRpcService noticeService;
	
	@Autowired
	private AppConfigRemote appConfigRemote;
	
	/**
	 * 管家首页
	 * @param request
	 * @return
	 */
	@HTTP(alias="index")
	@Deprecated
	public Response<GetHKIndexResponseData> index(GetHKIndexRequest request) {
		try {
			Response<GetHKIndexResponseData> response = new Response<GetHKIndexResponseData>();
			
			Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
			
			GetAppNoticeRequest noticeRequest = new GetAppNoticeRequest();
			noticeRequest.setPageSize(1);
			noticeRequest.setPosition(1);
			Long projectId = Long.parseLong(request.getProjectId());
			noticeRequest.setProjectId(projectId);
			noticeRequest.setAppDevice(request.getAppDevice());
			
			/**
			 * 社区首页轮播
			 */
			GetAppNoticeResponse noticeResponse = noticeService.getAppNoticeList(noticeRequest);
				
			List<HKCycleBanner> banners = transforList(HKCycleBanner.class, noticeResponse.getNoticeList());
			
			/**
			 * 社区首页业态
			 */
			String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());
			
			List<ServiceItem> serviceItems = appConfigRemote.getBindServiceItemListbyProjectId(projectId, 
					Constant.SERVICE_ITEM_MANAGER, 
					version,
					brickSourceType
				);
			
			List<ProjectService> services = transforList(ProjectService.class, serviceItems);
			
			response.setData(new GetHKIndexResponseData(banners, services, System.currentTimeMillis()));
			
			return response;
			
		} catch (Exception e) {
			return handleException(GetHKIndexResponseData.class, e);
		}
	}
}
