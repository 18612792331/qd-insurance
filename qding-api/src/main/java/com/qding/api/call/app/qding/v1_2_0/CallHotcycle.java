package com.qding.api.call.app.qding.v1_2_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.CommunitySale;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.CycleBanner;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedComment;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedCommunityTag;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedImage;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedPraise;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedReplyNotify;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedTag;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.PushTag;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.User;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.UserInfo;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.AddUserTagRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.CommentFeedRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.DeleteFeedByFeedIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.DeleteFeedCommentRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetCommentByFeedIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetCommunityIndexByIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetFeedByCommunityIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetFeedByFeedIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetFeedByGcRoomIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetFeedImagesByUserIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetFeedsByTagIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetHistoryNotifyByUserIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetNotifyByUserIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetPersonalFeedByTagIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetPraiseByFeedIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetTagByCommunityIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetTagByUserIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetUserByIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.GetUsersByTagIdRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.PraiseFeedRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.PublishFeedRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request.SearchTagRequest;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.AddUserTagResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.CommentFeedResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.DeleteFeedByFeedIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.DeleteFeedCommentResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetCommentByFeedIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetCommunityIndexByIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetFeedByCommunityIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetFeedByFeedIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetFeedByGcRoomIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetFeedImagesByUserIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetFeedsByTagIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetHistoryNotifyByUserIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetNotifyByUserIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetPersonalFeedByTagIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetPraiseByFeedIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetTagByCommunityIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetTagByUserIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetUserByIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.GetUsersByTagIdResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.PraiseFeedResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.PublishFeedResponseData;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data.SearchTagResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.IntegralMessage;
import com.qding.api.imessage.IntegralMessageBeanT;
import com.qding.api.struct.Response;
import com.qding.api.util.ImageUtil;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.pojo.contract.Supplier;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.contract.SupplierRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.storage.qiniu.ImgServiceForAPPInPrivate;
import com.qding.framework.common.util.MD5Util;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hk.domain.ResNotice;
import com.qding.hk.rpc.request.notice.GetAppNoticeRequest;
import com.qding.hk.rpc.response.notice.GetAppNoticeResponse;
import com.qding.hk.rpc.service.INoticeRpcService;
import com.qding.hotcycle.service.IHotCycleRemoteService;
import com.qding.hotcycle.struct.cache.FeedBean;
import com.qding.hotcycle.struct.request.GetCommentForPagingRequest;
import com.qding.hotcycle.struct.request.GetCommunityPushTagRequest;
import com.qding.hotcycle.struct.request.GetFeedsByGroupIdRequest;
import com.qding.hotcycle.struct.request.GetParaiseForPagingRequest;
import com.qding.hotcycle.struct.request.GetUserInfoByIdRequest;
import com.qding.hotcycle.struct.request.PublishCommentRequest;
import com.qding.hotcycle.struct.response.AddUserTagResponse;
import com.qding.hotcycle.struct.response.DeleteFeedByFeedIdResponse;
import com.qding.hotcycle.struct.response.DeleteFeedCommentResponse;
import com.qding.hotcycle.struct.response.GetCommentForPagingResponse;
import com.qding.hotcycle.struct.response.GetCommunityPushTagResponse;
import com.qding.hotcycle.struct.response.GetFeedByCommunityIdResponse;
import com.qding.hotcycle.struct.response.GetFeedByFeedIdResponse;
import com.qding.hotcycle.struct.response.GetFeedImagesByUserIdResponse;
import com.qding.hotcycle.struct.response.GetFeedsByGroupIdResponse;
import com.qding.hotcycle.struct.response.GetFeedsByTagIdResponse;
import com.qding.hotcycle.struct.response.GetHistoryNotifyByUserIdResponse;
import com.qding.hotcycle.struct.response.GetNotifyByUserIdResponse;
import com.qding.hotcycle.struct.response.GetParaiseForPagingResponse;
import com.qding.hotcycle.struct.response.GetPersonalFeedByTagIdResponse;
import com.qding.hotcycle.struct.response.GetTagByCommunityIdResponse;
import com.qding.hotcycle.struct.response.GetTagByUserIdResponse;
import com.qding.hotcycle.struct.response.GetUserInfoByIdResponse;
import com.qding.hotcycle.struct.response.GetUsersByTagIdResponse;
import com.qding.hotcycle.struct.response.PraiseFeedResponse;
import com.qding.hotcycle.struct.response.PublishCommentResponse;
import com.qding.hotcycle.struct.response.PublishFeedResponse;
import com.qding.hotcycle.struct.response.SearchTagResponse;
import com.qding.manager.common.ModelResult;
import com.qding.manager.domain.Puser;
import com.qding.manager.service.IPuserRPCService;
import com.qding.passport.service.IPassportService;
import com.qding.passport.struct.AccountInfo;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetAccountRequest;
import com.qding.passport.struct.response.GetAccountResponse;
import com.qding.sysconfig.domain.ActivityConfig;
import com.qding.sysconfig.domain.ActivityContent;
import com.qding.sysconfig.domain.ActivityGoods;
import com.qding.sysconfig.rpc.model.MarketActivityModel;
import com.qding.sysconfig.rpc.response.MarketActivityResponse;
import com.qding.sysconfig.rpc.service.MarketActivityRpcService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 热圈
 * @author lichao
 *
 */
public class CallHotcycle extends Callable{
	
	@Autowired
	private IHotCycleRemoteService hotCycleService;
	
	@Autowired
	private INoticeRpcService noticeService;

	@Autowired
	private AppConfigRemote appConfigRemote;
	
	@Autowired
	private MarketActivityRpcService marketActivityService;
	
	@Autowired
	private ImgServiceForAPPInPrivate imgServiceForAPP;

	@Autowired
	private ProjectReadRemote projectReadService;

	@Autowired
	private IntegralMessage integralMessage;

	@Autowired
	private  IHotCycleRemoteService hotCycleRemoteService;

	@Autowired
	private IPassportService passportAPI;


	@Autowired
	private IPuserRPCService puserRPCService;

	protected CustomerTransfor customerTransfor = new CustomerTransfor();

	/**
	 * 评论信息
	 * @param request
	 * @return
	 */
	@HTTP(alias="commentFeed")
	@Deprecated
	public Response<CommentFeedResponseData> commentFeed(CommentFeedRequest request) {
		
		PublishCommentResponse response = hotCycleService.commentFeed(
				transfor(
						PublishCommentRequest.class, 
						request
						)
				);
		return standardResponse(response, new CommentFeedResponseData());
	}

	/**
	 * 删除评论
	 * @param request
	 * @return
	 */
	@HTTP(alias="deleteFeedComment")
	@Deprecated
	public Response<DeleteFeedCommentResponseData> deleteFeedComment(DeleteFeedCommentRequest request) {
		
		DeleteFeedCommentResponse response = hotCycleService.deleteFeedComment(
				transfor(
						com.qding.hotcycle.struct.request.DeleteFeedCommentRequest.class,
						request
						)
				);
		
		return standardResponse(response, new DeleteFeedCommentResponseData());
	}
	
	/**
	 * 获取信息评论列表
	 * @param request
	 * @return
	 */
	@HTTP(alias="getFeedComment")
	@Deprecated
	public Response<GetCommentByFeedIdResponseData> getFeedComment(GetCommentByFeedIdRequest request) {
		
		GetCommentForPagingResponse response = hotCycleService.getCommentForPaging(
				transfor(
						GetCommentForPagingRequest.class, 
						request
						)
				);
		
		GetCommentByFeedIdResponseData data = transfor(GetCommentByFeedIdResponseData.class, response);
		
		List<com.qding.hotcycle.struct.FeedComment> comments = response.getFeedComments();
		
		List<String> userIds = new ArrayList<>();
		
		for(com.qding.hotcycle.struct.FeedComment f : comments) {
			userIds.add(f.getSendUserId());
			if(f.getParentUserId() != null) {
				userIds.add(f.getParentUserId());
			}
		}

		Map<String, com.qding.hotcycle.struct.User> userMap = getUserMap(userIds);
		
		List<FeedComment> cms = new ArrayList<>();

		for(com.qding.hotcycle.struct.FeedComment f : comments) {
			
			FeedComment cm = transfor(FeedComment.class, f);
			
			User sendUser = transfor(User.class, userMap.get(f.getSendUserId()));
			
			sendUser.setUserHeadImageUrl(
					imageUtil.get(sendUser.getUserHeadImageUrl(), 150, 150));
			
			cm.setSendUser(sendUser);
			
			cms.add(cm);
			
			if(f.getParentUserId() != null) {

				User parentUser = transfor(User.class, userMap.get(f.getParentUserId()));
				
				parentUser.setUserHeadImageUrl(
						imageUtil.get(parentUser.getUserHeadImageUrl(), 150, 150));
				
				cm.setParentUser(parentUser);
			}
			
		}
		
		data.setList(cms);
		
		return standardResponse(response, data);
	}
	
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
			
			List<ServiceItem> serviceItems = appConfigRemote.getBindServiceItemListbyProjectId(Long.parseLong(request.getCommunityId()), 
					Constant.SERVICE_ITEM_NOTIFY, 
					version,
					brickSourceType
				);
			
			List<ProjectService> services = transforList(ProjectService.class, serviceItems);

			/**
			 * 社区首页活动
			 */
			MarketActivityResponse activityResponse = marketActivityService.getIndexActivity(request.getCommunityId(), 1);
		
			List<MarketActivityModel> activities = activityResponse.getMarketActivityModelList();
				
			List<CommunitySale> sales = new ArrayList<CommunitySale>();

			for(MarketActivityModel activity : activities) {
				
				ActivityConfig config = activity.getActivityConfig();
				ActivityContent content = activity.getActivityContent();
				List<ActivityGoods> goods = activity.getActivityGoodsList();
				StringBuffer goodsIds = new StringBuffer();
				for(ActivityGoods g : goods) {
					goodsIds.append(",").append(g.getGoodsId());
				}
				int type = StringUtils.isEmpty(content.getUrl()) ? 1 : 2;
				
				sales.add(
						new CommunitySale(
								config.getActivityName(),
								content.getActivityDesc(),
								config.getNavigation(),
								type,
								goodsIds.toString().replaceFirst(",", ""), 
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
	
	/**
	 * 获取社区的信息列表
	 * @param request
	 * @return
	 */
	@HTTP(alias="getCommunityFeed")
	@Deprecated
	public Response<GetFeedByCommunityIdResponseData> getCommunityFeed(GetFeedByCommunityIdRequest request) {
		
		GetFeedByCommunityIdResponse response = hotCycleService.getFeedByCommunityId(
				transfor(
						com.qding.hotcycle.struct.request.GetFeedByCommunityIdRequest.class, 
						request
						)
				);
		GetFeedByCommunityIdResponseData data;

		try {
			
			checkAndContinue(response);
			
			data = transfor(GetFeedByCommunityIdResponseData.class, response);
			
			List<com.qding.hotcycle.struct.Feed> feeds = response.getFeeds();
			
			List<Feed> fds = new ArrayList<>();

			if (CollectionUtils.isNotEmpty(feeds)) {

				Set<String> projectIds = new HashSet<String>();
				for (com.qding.hotcycle.struct.Feed feed : feeds) {
					projectIds.add(feed.getCommunityId());
				}

				List<Project> projectList = projectReadService.getByIds(projectIds);
				Map<Long,Project> projectMap = new HashMap<>();
				if (CollectionUtils.isNotEmpty(projectIds)) {
					for (Project project : projectList) {
						projectMap.put(project.getId(),project);
					}
				}

				for(com.qding.hotcycle.struct.Feed feed : feeds) {

					try {
						try {
							Project project = projectMap.get(Long.parseLong(feed.getCommunityId()));
							feed.setCommunityName((QDStringUtil.isNotNull(project) ? project.getRegionName()+"-" : "" ) +feed.getCommunityName());
						} catch (Exception e) {

						}
						Feed fd = customerTransfor.transforFeed(feed, request.getShowPraiseSize(), request.getShowCommentSize());
						if(QDStringUtil.isNotNull(fd))
							fds.add(fd);

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}

			data.setList(fds);
			
			return standardResponse(response, data);

		} catch (Exception e) {
			
			return handleException(GetFeedByCommunityIdResponseData.class, e);
			
		}
		
	}
	
	/**
	 * 根据信息ID获取信息详情
	 * @param request
	 * @return
	 */
	@HTTP(alias="getFeed")
	@Deprecated
	public Response<GetFeedByFeedIdResponseData> getFeed(GetFeedByFeedIdRequest request) {
		
		
		GetFeedByFeedIdResponse response;
		GetFeedByFeedIdResponseData data;
		
		try {
			response = hotCycleService.getFeedByFeedId(
					transfor(
							com.qding.hotcycle.struct.request.GetFeedByFeedIdRequest.class, 
							request
							)
					);
			
			checkAndContinue(response);

			data = transfor(GetFeedByFeedIdResponseData.class, response);
			
			com.qding.hotcycle.struct.Feed feed = response.getFeeds();

			try {
				
				Project project = projectReadService.get(Long.parseLong(feed.getCommunityId()));
				feed.setCommunityName(project.getRegionName()+"-"+feed.getCommunityName());
				
			} catch (Exception e) {
				
			}

			Feed fd = customerTransfor.transforFeed(feed, request.getShowPraiseSize(), request.getShowCommentSize());
			if(QDStringUtil.isNotNull(fd))
			data.setEntity(fd);

			return standardResponse(response, data);

		} catch (Exception e) {
			return handleException(GetFeedByFeedIdResponseData.class, e);
		}
		
	}
	
	/**
	 * 根据用户id获取照片列表
	 * @param request
	 * @return
	 */
	@Deprecated
	@HTTP(alias="getUserFeedImages")
	public Response<GetFeedImagesByUserIdResponseData> getUserFeedImages(GetFeedImagesByUserIdRequest request) {
		
		try {
			GetFeedImagesByUserIdResponse response = hotCycleService.getFeedImagesByUserId(
					transfor(
							com.qding.hotcycle.struct.request.GetFeedImagesByUserIdRequest.class, 
							request
							)
					);
			
			checkAndContinue(response);

			GetFeedImagesByUserIdResponseData data = transfor(GetFeedImagesByUserIdResponseData.class, response);
			
			List<com.qding.hotcycle.struct.FeedImage> images = response.getFeedImages();
			
			List<FeedImage> fis = new ArrayList<>();
			
			for(com.qding.hotcycle.struct.FeedImage image : images) {
				fis.add(
						customerTransfor.resizeFeedImage(image, 318, 318)
						);
			}
			
			data.setList(fis);
			
			GetTagByUserIdResponse tagUserResponse = 
					hotCycleService.getTagByUserId(new com.qding.hotcycle.struct.request.GetTagByUserIdRequest(request.getUserId(), null, 1, 1));
			
			checkAndContinue(tagUserResponse);

			data.setTagCount(tagUserResponse.getTotalCount());
			
			return standardResponse(response, data);
		} catch (Exception e) {

			return handleException(GetFeedImagesByUserIdResponseData.class, e);
		}
	}
	
	/**
	 *  根据用户id查询新消息
	 * @param request
	 * @return
	 */
	@Deprecated
	@HTTP(alias="getUserNotifies")
	public Response<GetNotifyByUserIdResponseData> getUserNotifies(GetNotifyByUserIdRequest request) {
		
		try {
			GetNotifyByUserIdResponse response = hotCycleService.getNotifyByUserId(
						transfor(
								com.qding.hotcycle.struct.request.GetNotifyByUserIdRequest.class,
								request
								)
					);
			
			checkAndContinue(response);
			
			GetNotifyByUserIdResponseData data = transfor(GetNotifyByUserIdResponseData.class, response);
			
			List<com.qding.hotcycle.struct.FeedReplyNotify> feedReplyNotifies = response.getFeedReplyNotifies();
			List<FeedReplyNotify> ns = new ArrayList<>();
			
			List<String> userIds = new ArrayList<>();
			
			for(com.qding.hotcycle.struct.FeedReplyNotify notify : feedReplyNotifies) {
				
				userIds.add(notify.getTriggerUserId());
			}
			
			Map<String, com.qding.hotcycle.struct.User> userMap = getUserMap(userIds);
			
			for(com.qding.hotcycle.struct.FeedReplyNotify notify : feedReplyNotifies) {
				
				FeedReplyNotify n = transfor(FeedReplyNotify.class, notify);
				
				User triggerUser = transfor(User.class, userMap.get(notify.getTriggerUserId()));
				
				triggerUser.setUserHeadImageUrl(
						imageUtil.get(triggerUser.getUserHeadImageUrl(), 150, 150)
						);
				
				n.setTriggerUser(triggerUser);
				
				n.setFeedImage(customerTransfor.resizeFeedImage(new com.qding.hotcycle.struct.FeedImage(notify.getFeedId(), notify.getFeedImage()), 200, 200));
				
				ns.add(n);
			}
			
			data.setFeedReplyNotifies(ns);
			
			return standardResponse(response, data);
		} catch (Exception e) {
		
			return handleException(GetNotifyByUserIdResponseData.class, e);
		}
	}
	
	/**
	 *  根据用户id查询新消息
	 * @param request
	 * @return
	 */
	@HTTP(alias="getUserHistoryNotifies")
	@Deprecated
	public Response<GetHistoryNotifyByUserIdResponseData> getUserHistoryNotifies(GetHistoryNotifyByUserIdRequest request) {
		
		try {
			GetHistoryNotifyByUserIdResponse response = hotCycleService.getHistoryNotifyByUserId(
						transfor(
								com.qding.hotcycle.struct.request.GetHistoryNotifyByUserIdRequest.class,
								request
								)
					);
			
			checkAndContinue(response);
			
			GetHistoryNotifyByUserIdResponseData data = transfor(GetHistoryNotifyByUserIdResponseData.class, response);
			
			List<com.qding.hotcycle.struct.FeedReplyNotify> feedReplyNotifies = response.getFeedReplyNotifies();
			List<FeedReplyNotify> ns = new ArrayList<>();
			
			List<String> userIds = new ArrayList<>();
			
			for(com.qding.hotcycle.struct.FeedReplyNotify notify : feedReplyNotifies) {
				
				userIds.add(notify.getTriggerUserId());
			}
			
			Map<String, com.qding.hotcycle.struct.User> userMap = getUserMap(userIds);
			
			for(com.qding.hotcycle.struct.FeedReplyNotify notify : feedReplyNotifies) {
				
				FeedReplyNotify n = transfor(FeedReplyNotify.class, notify);
				
				User triggerUser = transfor(User.class, userMap.get(notify.getTriggerUserId()));

				if(QDStringUtil.isNull(triggerUser)) {
					data.setNoticeNum(data.getNoticeNum()>0?data.getNoticeNum()-1:0);
					continue;
				}

				triggerUser.setUserHeadImageUrl(
						imageUtil.get(triggerUser.getUserHeadImageUrl(), 150, 150));
				
				n.setTriggerUser(triggerUser);
				
				n.setFeedImage(customerTransfor.resizeFeedImage(new com.qding.hotcycle.struct.FeedImage(notify.getFeedId(), notify.getFeedImage()), 200, 200));
				
				ns.add(n);
			}
			
			data.setList(ns);
			
			return standardResponse(response, data);
		} catch (Exception e) {
		
			return handleException(GetHistoryNotifyByUserIdResponseData.class, e);
		}
	}
	
	/**
	 *  个人标签页 图片列表
	 * @param request
	 * @return
	 */
	@HTTP(alias="getPersonalTagFeeds")
	@Deprecated
	public Response<GetPersonalFeedByTagIdResponseData> getPersonalTagFeeds(GetPersonalFeedByTagIdRequest request) {
		
		try {
			GetPersonalFeedByTagIdResponse response = hotCycleService.getPersonalFeedByTagId(
					transfor(
							com.qding.hotcycle.struct.request.GetPersonalFeedByTagIdRequest.class,
							request
							)
					);

			checkAndContinue(response);

			GetPersonalFeedByTagIdResponseData data = transfor(GetPersonalFeedByTagIdResponseData.class, response);
			
			List<com.qding.hotcycle.struct.Feed> feeds = response.getFeeds();
			
			List<Feed> fds = new ArrayList<>();
			
			for(com.qding.hotcycle.struct.Feed feed : feeds) {
				
				Feed fd = customerTransfor.transforFeed(feed, request.getShowPraiseSize(), request.getShowCommentSize());
				if(QDStringUtil.isNotNull(fd))
				fds.add(fd);
			}
			
			data.setFeeds(fds);
			
			return standardResponse(response, data);
		} catch (Exception e) {

			return handleException(GetPersonalFeedByTagIdResponseData.class, e);
		}
	}

	//群朋友圈瀑布流展示
	@HTTP(alias = "getFeedByGcRoomId")
	@Deprecated
	public Response<GetFeedByGcRoomIdResponseData> getFeedByGcRoomId (GetFeedByGcRoomIdRequest request) {

		try {

			Response<GetFeedByGcRoomIdResponseData> response = new Response<GetFeedByGcRoomIdResponseData>();

			response.setCode(HttpStatus.OK.getStatusCode());

			GetFeedsByGroupIdResponse getFeedsByGroupIdResponse = hotCycleService.getFeedsByGroupId(transfor(GetFeedsByGroupIdRequest.class,request));

			checkAndContinue(getFeedsByGroupIdResponse);

			GetFeedByGcRoomIdResponseData data = transfor(GetFeedByGcRoomIdResponseData.class,getFeedsByGroupIdResponse);

			List<com.qding.hotcycle.struct.Feed> feeds = getFeedsByGroupIdResponse.getFeeds();

//			List<Feed> list = transforList(Feed.class,getFeedsByGroupIdResponse.getFeeds());

			List<Feed> fds = new ArrayList<>();

			for(com.qding.hotcycle.struct.Feed feed : feeds) {

				try {

					try {
						Project project = projectReadService.get(Long.parseLong(feed.getCommunityId()));
						feed.setCommunityName(project.getRegionName()+"-"+feed.getCommunityName());

					} catch (Exception e) {

					}
					Feed fd = customerTransfor.transforFeed(feed, 3, 3);
					if(QDStringUtil.isNotNull(fd))
						fds.add(fd);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			data.setList(fds);

			response.setData(data);

			return response;

		}catch (Exception ex){

			return handleException(GetFeedByGcRoomIdResponseData.class, ex);
		}

	}
	
	/**
	 * 获取信息的赞列表
	 * @param request
	 * @return
	 */
	@HTTP(alias="getFeedPraises")
	@Deprecated
	public Response<GetPraiseByFeedIdResponseData> getFeedPraises(GetPraiseByFeedIdRequest request) {
 
		try {
			GetParaiseForPagingResponse response = hotCycleService.getParaiseForPaging(
					transfor(
							GetParaiseForPagingRequest.class,
							request
							)
					);
			
			checkAndContinue(response);
			
			List<com.qding.hotcycle.struct.FeedPraise> praises = response.getFeedPraises();
			List<FeedPraise> fps = new ArrayList<>();
			
			List<String> userIds = new ArrayList<String>();
			
			for(com.qding.hotcycle.struct.FeedPraise praise : praises ) {
				
				userIds.add(praise.getPraiseUserId());
				
			}
			
			Map<String, com.qding.hotcycle.struct.User> userMap = getUserMap(userIds);
			
			for(com.qding.hotcycle.struct.FeedPraise praise : praises ) {
				
				FeedPraise fp = transfor(FeedPraise.class, praise);

				User praiseUser = transfor(User.class, userMap.get(praise.getPraiseUserId()));
				
				praiseUser.setUserHeadImageUrl(
						imageUtil.get(praiseUser.getUserHeadImageUrl(), 150, 150));
			
				fp.setPraiseUser(praiseUser);
				
				fps.add(fp);
				
			}
			
			GetPraiseByFeedIdResponseData data = transfor(GetPraiseByFeedIdResponseData.class, response);
			
			data.setList(fps);
			
			return standardResponse(response, data);
		} catch (Exception e) {
			
			return handleException(GetPraiseByFeedIdResponseData.class, e);
		}
	}
	
	/**
	 * 根据社区id显示此商圈的标签
	 * @param request
	 * @return
	 */
	@HTTP(alias="getCommunityTags")
	@Deprecated
	public Response<GetTagByCommunityIdResponseData> getCommunityTags(GetTagByCommunityIdRequest request) {
		
		GetTagByCommunityIdResponse response = hotCycleService.getTagsByCommunityId(
				transfor(com.qding.hotcycle.struct.request.GetTagByCommunityIdRequest.class, request)
				);
		
		GetTagByCommunityIdResponseData data = transfor(GetTagByCommunityIdResponseData.class, response);
		
		List<com.qding.hotcycle.struct.FeedCommunityTag> tags = response.getTags();
		
		List<FeedCommunityTag> fct = new ArrayList<>();
		
		for(com.qding.hotcycle.struct.FeedCommunityTag f : tags) {
			
			FeedCommunityTag tag = transfor(FeedCommunityTag.class, f);
			
			tag.setLastImage(customerTransfor.resizeFeedImage(f.getLastImage(), 284, 284));
			
			fct.add(tag);
		}
		
		data.setList(fct);
		
		return standardResponse(response, data);
	}
	
	/**
	 * 根据用户id获取该用户的标签列表
	 * @param request
	 * @return
	 */
	@HTTP(alias="getUserTags")
	@Deprecated
	public Response<GetTagByUserIdResponseData> getUserTags(GetTagByUserIdRequest request) {
	
		try {
			GetTagByUserIdResponse response = hotCycleService.getTagByUserId(
					transfor(
							com.qding.hotcycle.struct.request.GetTagByUserIdRequest.class, 
							request
							)
					);
			
			checkAndContinue(response);
			
			GetTagByUserIdResponseData data = transfor(GetTagByUserIdResponseData.class, response);
			
			List<FeedCommunityTag> tags = new ArrayList<>();
			
			List<com.qding.hotcycle.struct.FeedCommunityTag> gs = response.getTags();
			
			for(com.qding.hotcycle.struct.FeedCommunityTag g : gs) {
				
				FeedCommunityTag t = transfor(FeedCommunityTag.class, g);
				
				t.setLastImage(customerTransfor.resizeFeedImage(g.getLastImage(), 284, 284));
				
				tags.add(t);
			}
			
			data.setList(tags);
			
			GetFeedImagesByUserIdResponse feedImagesResponse = hotCycleService.getFeedImagesByUserId(new com.qding.hotcycle.struct.request.GetFeedImagesByUserIdRequest(1, 1, request.getUserId()));
			
			checkAndContinue(feedImagesResponse);
			
			data.setImageCount(feedImagesResponse.getTotalCount());
			
			return standardResponse(response, data);
		} catch (Exception e) {
			
			return handleException(GetTagByUserIdResponseData.class, e);
		}
	
	}
	
	/**
	 * 根据标签id获取此标签的图片列表
	 */
	@HTTP(alias="getTagFeeds")
	@Deprecated
	public Response<GetFeedsByTagIdResponseData> getTagFeeds(GetFeedsByTagIdRequest request) {
		
		try {
			GetFeedsByTagIdResponse response = hotCycleService.getFeedsByTagId(
					transfor(
							com.qding.hotcycle.struct.request.GetFeedsByTagIdRequest.class, 
							request
							)
					);
			
			checkAndContinue(response);
			
			GetFeedsByTagIdResponseData data = transfor(GetFeedsByTagIdResponseData.class, response);
			
			List<com.qding.hotcycle.struct.Feed> feeds = response.getFeeds();

			List<Feed> fds = new ArrayList<>();
			boolean falg =false;
			for(com.qding.hotcycle.struct.Feed feed : feeds) {
				Feed fd = customerTransfor.transforFeed(feed, request.getShowPraiseSize(), request.getShowCommentSize());
				if(QDStringUtil.isNotNull(fd)){
					fds.add(fd);
					List<FeedTag> feedTagList = fd.getFeedTags();
					if (!falg && CollectionUtils.isNotEmpty(feedTagList)){
						for (FeedTag feedTag : feedTagList) {
							if (feedTag.getTagId().equals(request.getTagId())){
								data.setTagName(feedTag.getTagName());
								falg = true;
								break;
							}
						}
					}
				}
			}
			data.setList(fds);

			GetUsersByTagIdResponse userResponse = hotCycleService.getUsersByTagId(
					new com.qding.hotcycle.struct.request.GetUsersByTagIdRequest(
							request.getTagId(), 
							0, 
							1
							)
					);
			
			checkAndContinue(userResponse);
			
			List<User> users = new ArrayList<>();
			
			transfor(users, userResponse.getUsers());
			
			data.setTotalUserCount(userResponse.getTotalCount());
			return standardResponse(response, data);
		} catch (Exception e) {

			return handleException(GetFeedsByTagIdResponseData.class, e);
		}
	}
	
	/**
	 * 根据标签id返回此标签的用户列表
	 */
	@HTTP(alias="getTagUsers")
	@Deprecated
	public Response<GetUsersByTagIdResponseData> getTagUsers(GetUsersByTagIdRequest request) {
		
		GetUsersByTagIdResponse response = hotCycleService.getUsersByTagId(
				transfor(
						com.qding.hotcycle.struct.request.GetUsersByTagIdRequest.class, 
						request
						)
				);
		
		GetUsersByTagIdResponseData data = transfor(GetUsersByTagIdResponseData.class, response);
		
		for(User userInfo : data.getList()) {
			
			userInfo.setUserHeadImageUrl(
					imageUtil.get(userInfo.getUserHeadImageUrl(), 150, 150));
		}
		
		return standardResponse(response, data);
	
	}
	
	/**
	 * 点赞
	 * @param request
	 * @return
	 */
	@HTTP(alias="praiseFeed")
	@Deprecated
	public Response<PraiseFeedResponseData> praiseFeed(PraiseFeedRequest request) {
		
		PraiseFeedResponse response = hotCycleService.praiseFeed(
				transfor(
						com.qding.hotcycle.struct.request.PraiseFeedRequest.class, 
						request
						)
				);
       /* //绑定房屋用户添加积分
        String feedUserId = null;
        try {
            com.qding.hotcycle.struct.request.GetFeedByFeedIdRequest req = new com.qding.hotcycle.struct.request.GetFeedByFeedIdRequest();
            req.setFeedId(request.getFeedId());
            GetFeedByFeedIdResponse resp = hotCycleService.getFeedByFeedId(req);
            feedUserId = resp.getFeeds().getUserId();
        }catch (Exception e){
            feedUserId = null;
        }

		if (isBindRoom(feedUserId) && HttpStatus.OK.getStatusCode() == response.getReturnInfo().getCode() && !"cancelPraise".equals(response.getReturnInfo().getMessage())) {
			//加入积分规则
			IntegralMessageBeanT integralMessageBeanT = new IntegralMessageBeanT(request.getFeedId(), Constant.INTEGRAL_PRAISE, request.getFeedId(), null, System.currentTimeMillis(), Constant.INCOME_OPT_TYPE, null,request.getFeedId());
			integralMessage.assembleIntegralMessage(integralMessageBeanT);
		}*/
		return standardResponse(response, new PraiseFeedResponseData());
		
	}
	
	/**
	 * 发布信息
	 * @param request
	 * @return
	 */
	@HTTP(alias="publishFeed")
	@Deprecated
	public Response<PublishFeedResponseData> publishFeed(PublishFeedRequest request) {

		try {
			com.qding.hotcycle.struct.request.PublishFeedRequest feedRequest = transfor(
					com.qding.hotcycle.struct.request.PublishFeedRequest.class, 
					request
					);
			
			String url = feedRequest.getImageUrl();
			
			String key = String.format("qiniu:qding:hotcycle:%s:%s", url.hashCode(), MD5Util.md5(url).substring(24));
			
			imgServiceForAPP.Fetch(url, key);
			
			feedRequest.setImageUrl(key);
			
			PublishFeedResponse response = hotCycleService.publishFeed(feedRequest);
			
			checkAndContinue(response);
			
			return standardResponse(response, new PublishFeedResponseData());

		} catch (Exception e) {
			
			return handleException(PublishFeedResponseData.class, e);
		}
	}
	
	/**
	 * 添加用户标签
	 * @param request
	 * @return
	 */
	@HTTP(alias="addUserTag")
	@Deprecated
	public Response<AddUserTagResponseData> addUserTag(AddUserTagRequest request) {
		
		AddUserTagResponse response = hotCycleService.addUserTag(
				transfor(
						com.qding.hotcycle.struct.request.AddUserTagRequest.class, 
						request
						)
				);
		
		AddUserTagResponseData data = transfor(AddUserTagResponseData.class, response);
		
		return standardResponse(response, data);
	}
	
	/**
	 * 查询用户信息
	 * @param request
	 * @return
	 */
	@HTTP(alias="getUserInfo")
	public Response<GetUserByIdResponseData> getUserInfo(GetUserByIdRequest request) {

		Response<GetUserByIdResponseData> response = new Response<GetUserByIdResponseData>();
		GetUserByIdResponseData data = new GetUserByIdResponseData();
		try {
			String userId = request.getUserId();
			if (org.apache.commons.lang3.StringUtils.isNotBlank(userId)&& userId.startsWith(Constant.SUPPER_PREFIX)) {
				String puserId= userId.replace(Constant.SUPPER_PREFIX,"");
				ModelResult modelResult = puserRPCService.getPuserInfoByPuserId(puserId);
				UserInfo userInfo = new UserInfo();
				if (HttpStatus.OK.getStatusCode() == modelResult.getCode()) {
					Puser puser = (Puser) modelResult.getEntity();
					userInfo.setMemberId(puserId);
					userInfo.setUserHeadImageUrl(imageUtil.get(Constant.DEFAULT_SUPPLIER_HEAD_IMG,150,150));
					userInfo.setUserId(puserId);
					userInfo.setUserName(puser.getName());
					userInfo.setUserSex(1);
				}
				data.setEntity(userInfo);

			} else {

				GetAccountRequest accountRequest = new GetAccountRequest();
				accountRequest.setAccountId(request.getUserId());
				GetAccountResponse accountResponse = passportAPI.getAccountByAccountId(accountRequest);
				checkAndContinue(accountResponse);
				AccountInfo accountInfo = accountResponse.getAccountInfo();
				MemberInfo memberInfo = accountResponse.getMemberInfo();
				UserInfo userInfo = new UserInfo();
				userInfo.setMemberId(memberInfo.getId());
				userInfo.setUserHeadImageUrl(imageUtil.get(userInfo.getUserHeadImageUrl(), 150, 150));
				userInfo.setUserId(accountInfo.getId());
				userInfo.setUserName(accountInfo.getNickName());
				userInfo.setUserSex(memberInfo.getGender());
				userInfo.setUserSign(memberInfo.getSignature());
				data.setEntity(userInfo);
			}
			response.setCode(HttpStatus.OK.getStatusCode());
			response.setData(data);
		} catch (Exception e) {
			return handleException(GetUserByIdResponseData.class, e);
		}
		return response;
		
	}
	
	/**
	 * 搜索
	 * @param request
	 * @return
	 */
	@HTTP(alias="searchTag")
	@Deprecated
	public Response<SearchTagResponseData> searchTag(SearchTagRequest request) {
		
		SearchTagResponse response = hotCycleService.searchTag(
				transfor(com.qding.hotcycle.struct.request.SearchTagRequest.class, request)
				);
		
		SearchTagResponseData data = transfor(SearchTagResponseData.class, response);
		
		return standardResponse(response,data);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@HTTP(alias="deleteFeed")
	@Deprecated
	public Response<DeleteFeedByFeedIdResponseData> deleteFeed(DeleteFeedByFeedIdRequest request) {
		
		DeleteFeedByFeedIdResponse response = hotCycleService.deleteFeedByFeedId(
				transfor(com.qding.hotcycle.struct.request.DeleteFeedByFeedIdRequest.class, request)
				);
		
		DeleteFeedByFeedIdResponseData data = transfor(DeleteFeedByFeedIdResponseData.class, response);

		if (HttpStatus.OK.getStatusCode() == response.getReturnInfo().getCode()) {

			FeedBean feed =  hotCycleRemoteService.getFeedBean(request.getFeedId());

			//如果是活动帖子
			if (QDStringUtil.isNotNull(feed.getActivityId()) || QDStringUtil.isNotEmpty(feed.getActivityId())) {

				IntegralMessageBeanT integralMessageBeanT = new IntegralMessageBeanT(request.getUserId(), Constant.INTEGRAL_PUBLISH_FEED, request.getFeedId()+","+feed.getActivityId(), QDStringUtil.isNotEmpty(feed.getCommunityId())?Long.parseLong(feed.getCommunityId()):null, System.currentTimeMillis(), Constant.BACK_OPT_TYPE, null, request.getFeedId());
				integralMessage.assembleIntegralMessage(integralMessageBeanT);

			} else { //如果是普通帖子
				IntegralMessageBeanT integralMessageBeanT = new IntegralMessageBeanT(request.getUserId(), Constant.INTEGRAL_PUBLISH_FEED, request.getFeedId(),QDStringUtil.isNotEmpty(feed.getCommunityId())?Long.parseLong(feed.getCommunityId()):null, System.currentTimeMillis(), Constant.BACK_OPT_TYPE, null,request.getFeedId());
				integralMessage.assembleIntegralMessage(integralMessageBeanT);

			}

		}
		return standardResponse(response, data);
	}
	
	public Map<String, com.qding.hotcycle.struct.User> getUserMap(
			List<String> userIds) {
		
		if(userIds.isEmpty()) {
			
			return new HashMap<String, com.qding.hotcycle.struct.User>();
			
		}
		GetUserInfoByIdResponse userResponse = hotCycleService.getUserInfoById(
				new GetUserInfoByIdRequest(
						userIds.toArray(new String[]{})
					)
		);

		Map<String, com.qding.hotcycle.struct.User> userMap = userResponse.getMap();
		
		return userMap;
	}
	
	@Autowired
	private ImageUtil imageUtil;
	
	public class CustomerTransfor {
		
		private Feed transforFeed(com.qding.hotcycle.struct.Feed feed, int showPraiseSize, int showCommentSize) {
			
			Feed fd = transfor(Feed.class, feed);
			
			GetParaiseForPagingResponse paraiseResponse = hotCycleService.getParaiseForPaging(new GetParaiseForPagingRequest(feed.getFeedId(), 1, showPraiseSize));
			GetCommentForPagingResponse commentResponse = hotCycleService.getCommentForPaging(new GetCommentForPagingRequest(feed.getFeedId(), 1, showCommentSize));
			
			List<com.qding.hotcycle.struct.FeedPraise> praises = Lists.newArrayList();
			if(QDStringUtil.isNotNull(paraiseResponse.getFeedPraises())) praises =paraiseResponse.getFeedPraises();
			List<com.qding.hotcycle.struct.FeedComment> comments = Lists.newArrayList();
			if(QDStringUtil.isNotNull(commentResponse.getFeedComments())) comments = commentResponse.getFeedComments();
			
			
			List<String> userIds = new ArrayList<>();
			
			userIds.add(feed.getUserId());
			
			for(com.qding.hotcycle.struct.FeedPraise praise : praises) {

				userIds.add(praise.getPraiseUserId());
			}
			
			for(com.qding.hotcycle.struct.FeedComment comment : comments) {
				
				userIds.add(comment.getSendUserId());
				if(comment.getParentUserId() != null) {
					userIds.add(comment.getParentUserId());
				}
				
			}

			Map<String, com.qding.hotcycle.struct.User> userMap = getUserMap(userIds);
			
			List<FeedPraise> fps = new ArrayList<>();
			List<FeedComment> fcs = new ArrayList<>();
			
			for(com.qding.hotcycle.struct.FeedPraise praise : praises) {

				try {
					FeedPraise fp = transfor(FeedPraise.class, praise);
					
					User praiseUser = transfor(User.class, userMap.get(praise.getPraiseUserId()));
					
					praiseUser.setUserHeadImageUrl(
							imageUtil.get(praiseUser.getUserHeadImageUrl(), 150, 150)
							);
					
					fp.setPraiseUser(praiseUser);
					
					fps.add(fp);
				} catch (Exception e) {
				
					continue;
				}
			}
			
			for(com.qding.hotcycle.struct.FeedComment comment : comments) {
				
				try {
					
					FeedComment fc = transfor(FeedComment.class, comment);
					
					User sendUser = transfor(User.class, userMap.get(comment.getSendUserId()));
					
					sendUser.setUserHeadImageUrl(
							imageUtil.get(sendUser.getUserHeadImageUrl(), 150, 150));
					
					fc.setSendUser(sendUser);
					
					if(comment.getParentUserId() != null) {

						User parentUser = transfor(User.class, userMap.get(comment.getParentUserId()));
						
						parentUser.setUserHeadImageUrl(
								imageUtil.get(parentUser.getUserHeadImageUrl(), 150, 150));
						
						fc.setParentUser(parentUser);
					}
					
					fcs.add(fc);
				} catch (Exception e) {
					
					continue;
				}
			}
			List<FeedTag> fts = new ArrayList<>();
			List<com.qding.hotcycle.struct.FeedTag> feedTagList = feed.getFeedTags();
			if (CollectionUtils.isNotEmpty(feedTagList)){
				for (com.qding.hotcycle.struct.FeedTag feedTag : feedTagList) {
					FeedTag ft = transfor(FeedTag.class,feedTag);
					fts.add(ft);
				}
			}

			User feedUser = transfor(User.class, userMap.get(fd.getUserId()));
			if(QDStringUtil.isNotNull(feedUser)) {
				feedUser.setUserHeadImageUrl(QDStringUtil.isNotNull(feedUser.getUserHeadImageUrl())?imageUtil.get(feedUser.getUserHeadImageUrl(), 150, 150):"");
				fd.setFeedUser(feedUser);
				fd.setCommentCount(commentResponse.getTotalCount());
				fd.setFeedComments(fcs);
				fd.setPraiseCount(paraiseResponse.getTotalCount());
				fd.setFeedPraises(fps);
				fd.setFeedTags(fts);
				fd.setFeedImage(resizeFeedImage(
								new com.qding.hotcycle.struct.FeedImage(
										feed.getFeedId(),
										feed.getImgUrl()
								)
								, 640, 640)
				);

			} else fd=null; //如果获取用户数据失败

			return fd;
			
		}
		
		private FeedImage getSourceFeedImage(com.qding.hotcycle.struct.FeedImage image) {
			
			if(image == null) {
				return new FeedImage();
			}
			FeedImage fi = transfor(FeedImage.class, image);
			
			fi.setImageUrl(imageUtil.get(fi.getImageUrl()));
			
			return fi;
		}
		
		public FeedImage resizeFeedImage(com.qding.hotcycle.struct.FeedImage image, int width, int height) {
			if(image == null || QDStringUtil.isEmpty(image.getImageUrl())) {
				return new FeedImage();
			}
			FeedImage fi = transfor(FeedImage.class, image);

//			if (width!=640 && height!=640) {
				fi.setImageUrl(imageUtil.get(fi.getImageUrl(), width, height));
//			}
			
			return fi;
		}
		
		
	}
}
