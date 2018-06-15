package com.qding.api.call.app.qding.v1_3_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedComment;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedPraise;
import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.*;
import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.TimeLineFeed;
import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.TimeLineFeedSet;
import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.request.*;
import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.response.data.*;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.request.AddShareFeedImgRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data.AddShareFeedImgResponseData;
import com.qding.basis.user.api.util.QDStringUtil;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;

import com.qding.dictionary.client.DictionaryClient;
import com.qding.hotcycle.contant.Constants;
import com.qding.hotcycle.struct.*;
import com.qding.hotcycle.struct.request.GetCommentForPagingRequest;
import com.qding.hotcycle.struct.request.GetParaiseForPagingRequest;
import com.qding.hotcycle.struct.response.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedImage;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.User;
import com.qding.api.struct.Response;
import com.qding.api.util.ImageUtil;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.hotcycle.service.IHotCycleRemoteService;

/**
 * Created by Administrator on 2015/7/20.
 */
public class CallHotcycle extends com.qding.api.call.app.qding.v1_3_0.CallHotcycle {


	@Autowired
	private IHotCycleRemoteService hotCycleService;

	@Autowired
	private ImageUtil imageUtil;

	@Autowired
	private ProjectReadRemote projectReadService;

	protected CustomerTransfor customerTransfor = new CustomerTransfor();


	/**
	 *获取feed贴纸列表
	 * @param requet
	 * @return
     */
	@HTTP(alias = "getChartlet")
	@Deprecated
	public Response<GetChartletResponseData> getChartlet(GetChartletRequest requet) {

		try {

			com.qding.hotcycle.struct.request.GetHcChartletRequest hcChartletRequest = transfor(com.qding.hotcycle.struct.request.GetHcChartletRequest.class, requet);
			GetHcChartletResponse hcChartletResponse = hotCycleService.getHcChartlet(hcChartletRequest);

			checkAndContinue(hcChartletResponse);

			GetChartletResponseData data = transfor(GetChartletResponseData.class, hcChartletResponse);
			Response<GetChartletResponseData> response = new Response<GetChartletResponseData>();
			response.setData(data);

			return response;

		} catch (Exception e) {

			return handleException(GetChartletResponseData.class, e);
		}

	}


	/**
	 * 获取指定社区下开展的活动
	 * @param request
	 * @return
     */
	@HTTP(alias = "getActivityByCommunityId")
	@Deprecated
	public Response<GetActivityByCommunityIdResponseData> getActivityByCommunityId(GetActivityByCommunityIdRequest request) {

		Response<GetActivityByCommunityIdResponseData> response = new Response<GetActivityByCommunityIdResponseData>();

		try {

			com.qding.hotcycle.struct.request.GetActivityByCommunityIdRequest request2 = transfor(com.qding.hotcycle.struct.request.GetActivityByCommunityIdRequest.class, request);
			com.qding.hotcycle.struct.response.GetActivityByCommunityIdResponse activityByCommunityIdResponse = hotCycleService.getActivityByCommunityId(request2);
			checkAndContinue(activityByCommunityIdResponse);
			GetActivityByCommunityIdResponseData data = transfor(GetActivityByCommunityIdResponseData.class, activityByCommunityIdResponse);

			List<HcActivityDto> list=data.getList();
			for(HcActivityDto hcActivityDto:list){
				String thumbnails = imageUtil.get(hcActivityDto.getActivityTagThumbnails(),90,90);
				hcActivityDto.setActivityTagThumbnails(thumbnails);
				List<FeedImage> feedImages=hcActivityDto.getList();
				for(FeedImage image : feedImages) {
					String imageUrl = imageUtil.get(image.getImageUrl(),318, 318);
					image.setImageUrl(imageUrl);
				}
			}

			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());
			return response;

		} catch (Exception e) {

			return handleException(GetActivityByCommunityIdResponseData.class, e);
		}

	}

	/**
	 * 获取指定活动下的feed信息
	 * @param request
	 * @return
     */
	@Deprecated
	@HTTP(alias = "getActivityFeeds")
	public Response<GetFeedsByActivityIdResponseData> getFeedsByActivityId(GetFeedsByActivityIdRequest request) {

		Response<GetFeedsByActivityIdResponseData> response = new Response<GetFeedsByActivityIdResponseData>();
		try {

			com.qding.hotcycle.struct.request.GetFeedsByActivityIdRequest request2 = transfor(com.qding.hotcycle.struct.request.GetFeedsByActivityIdRequest.class, request);
			GetFeedsByActivityIdResponse feesByActivityIdResponse = hotCycleService.getFeedsByActivityId(request2);
			checkAndContinue(feesByActivityIdResponse);
			GetFeedsByActivityIdResponseData data = transfor(GetFeedsByActivityIdResponseData.class, feesByActivityIdResponse);
			String tagImage = data.getEntity().getTagImage();
			data.getEntity().setTagImage(imageUtil.get(tagImage,750,400));
			List<com.qding.hotcycle.struct.Feed> feeds =data.getFeeds();
			for(com.qding.hotcycle.struct.Feed feed :feeds){
				String imageUrl = imageUtil.get(feed.getImgUrl(),318,318);
				feed.setImgUrl(imageUrl);
			}
			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());
			return response;

		} catch (Exception e) {

			return handleException(GetFeedsByActivityIdResponseData.class, e);
		}
	}

	/**
	 * 获取指定活动下feed信息图片
	 * @param request
	 * @return
     */
	@HTTP(alias = "getActivityFeedImages")
	@Deprecated
	public Response<GetFeedImagesByActivityIdResponseData> getFeedImagesByActivityId(GetFeedImagesByActivityIdRequest request){


		try {
			 Response<GetFeedImagesByActivityIdResponseData> response = new  Response<GetFeedImagesByActivityIdResponseData>();
			
			GetFeedImagesByActivityIdResponse feedImagesByActivityIdresponse = hotCycleService.getFeedImagesByActivityId(
					transfor(
							com.qding.hotcycle.struct.request.GetFeedImagesByActivityIdRequest.class,
							request
					)
			);

			checkAndContinue(feedImagesByActivityIdresponse);

			GetFeedImagesByActivityIdResponseData data = transfor(GetFeedImagesByActivityIdResponseData.class, feedImagesByActivityIdresponse);

			List<com.qding.hotcycle.struct.FeedImage> images = feedImagesByActivityIdresponse.getFeedImages();

			List<FeedImage> fis = new ArrayList<>();

			for(com.qding.hotcycle.struct.FeedImage image : images) {
				fis.add(
						customerTransfor.resizeFeedImage(image, 318, 318)
				);
			}

			data.setList(fis);

			//如果不是solr查询方式，则需要单独查询参与人数
			if (!checkSolrStatus()) {
				com.qding.hotcycle.struct.response.GetUsersByActivityIdResponse activityUserResponse =
						hotCycleService.getUsersByActivityId(new com.qding.hotcycle.struct.request.GetUsersByActivityIdRequest(request.getActivityId(), 1, 1));

				checkAndContinue(activityUserResponse);

				data.setUserCount(activityUserResponse.getTotalCount());
			}

			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());

			return response;
		} catch (Exception e) {

			return handleException(GetFeedImagesByActivityIdResponseData.class, e);
		}

	}

	/**
	 * 用于检测是否启用solr查询方式
	 * @return
	 */
	private boolean checkSolrStatus(){
		try {
			String solrIsOpen =  DictionaryClient.findDictValueByGroupNameAndDictKey(Constants.DICT_GROUP_SWITCH,Constants.DICT_NAME_SWITCH_FOR_SOLR);
			if ("open".equals(solrIsOpen)) {
				return true;
			}  else {
				return  false;
			}
		} catch (TException e) {
			e.printStackTrace();
			return  true;
		}
	}

	/**
	 * 获取指定活动下参与的用户
 	 * @param request
	 * @return
     */
	@HTTP(alias = "getActivityUsers")
	@Deprecated
	public Response<GetUsersByActivityIdResponseData> getUsersByActivityId(GetUsersByActivityIdRequest request) {

		Response<GetUsersByActivityIdResponseData> response = new Response<GetUsersByActivityIdResponseData>();
		try {

			com.qding.hotcycle.struct.request.GetUsersByActivityIdRequest request2 = transfor(com.qding.hotcycle.struct.request.GetUsersByActivityIdRequest.class, request);
			GetUsersByActivityIdResponse usersByActivityIdResponse = hotCycleService.getUsersByActivityId(request2);
			checkAndContinue(usersByActivityIdResponse);
			GetUsersByActivityIdResponseData data = transfor(GetUsersByActivityIdResponseData.class, usersByActivityIdResponse);
			List<User> userList = transforList(User.class, usersByActivityIdResponse.getUsers());
			for (User userInfo : userList) {
				userInfo.setUserHeadImageUrl(
						imageUtil.get(userInfo.getUserHeadImageUrl(), 150, 150));
			}
			data.setList(userList);

			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());
			return response;

		} catch (Exception e) {

			return handleException(GetUsersByActivityIdResponseData.class, e);
		}
	}

	/**
	 * 获取指定用户参与过的活动
	 * @param request
	 * @return
	 */
	@Deprecated
	@HTTP(alias = "getUserActivitys")
	public Response<GetActivityByUserIdResponseData> getActivityByUserId(GetActivityByUserIdRequest request) {

		Response<GetActivityByUserIdResponseData> response = new Response<GetActivityByUserIdResponseData>();
		try {
			com.qding.hotcycle.struct.request.GetActivityByUserIdRequest activityByUserIdRequest = transfor(com.qding.hotcycle.struct.request.GetActivityByUserIdRequest.class, request);
			GetActivityByUserIdResponse activityByUserIdResponse = hotCycleService.getActivityByUserId(activityByUserIdRequest);
			checkAndContinue(activityByUserIdResponse);
			GetActivityByUserIdResponseData data = transfor(GetActivityByUserIdResponseData.class, activityByUserIdResponse);
			List<UserActivityBean> userActivityBeanList = transforList(UserActivityBean.class, activityByUserIdResponse.getList());
			data.setList(userActivityBeanList);
			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());

			return response;

		} catch (Exception e) {
			return handleException(GetActivityByUserIdResponseData.class, e);
		}
	}

	/**
	 * 获取指定社区下的活动标签
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getActivityTagByCommunityId")
	@Deprecated
	public Response<GetActivityTagByCommunityIdResponseData> getActivityTagBycommunityId(GetActivityTagByCommunityIdRequest request) {

		Response<GetActivityTagByCommunityIdResponseData> response = new Response<GetActivityTagByCommunityIdResponseData>();
		try {
			com.qding.hotcycle.struct.request.GetActivityTagByCommunityIdRequest activityTagByCommunityIdRequest =transfor(com.qding.hotcycle.struct.request.GetActivityTagByCommunityIdRequest.class, request);
			com.qding.hotcycle.struct.response.GetActivityTagByCommunityIdResponse  activityTagByCommunityIdResponse = hotCycleService.getActivityTagBycommunityId(activityTagByCommunityIdRequest);
			checkAndContinue(activityTagByCommunityIdResponse);
			GetActivityTagByCommunityIdResponseData data = transfor(GetActivityTagByCommunityIdResponseData.class, activityTagByCommunityIdResponse);
		
			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());
			return response;
			
		} catch (Exception e) {
			return handleException(GetActivityTagByCommunityIdResponseData.class, e);
		}
		
	}

	/**
	 * 利用时间轴展示个人feed信息列表
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getFeedImagesByTimelineForPerson")
	@Deprecated
	public Response<GetFeedImagesByTimelineForPersonResponseData> getFeedImagesByTimelineForPerson (GetFeedImagesByTimelineForPersonRequest request){

		Response<GetFeedImagesByTimelineForPersonResponseData> response = new Response<GetFeedImagesByTimelineForPersonResponseData>();

		try {
			GetFeedImagesByTimelineForPersonResponse timelineForPersonResponse = hotCycleService.getFeedImagesByTimelineForPerson(
					transfor(com.qding.hotcycle.struct.request.GetFeedImagesByTimelineForPersonRequest.class, request));
			
			checkAndContinue(timelineForPersonResponse);

			GetFeedImagesByTimelineForPersonResponseData data = null;

			if (QDStringUtil.isNotNull(timelineForPersonResponse.getList())||timelineForPersonResponse.getList().size()>0){
				 data = transfor(GetFeedImagesByTimelineForPersonResponseData.class, timelineForPersonResponse);
				 List<TimeLineFeedSet> timeLineFeedSet=data.getList();
				 for (int i=0;i<timeLineFeedSet.size();i++) {
					TimeLineFeedSet timeLineFeed = timeLineFeedSet.get(i);
					List<TimeLineFeed> timeLineFeedList = timeLineFeed.getTimeLineFeedList();
					for(int h=0;h<timeLineFeedList.size();h++){
						TimeLineFeed feed =timeLineFeedList.get(h);
						FeedImage feedImage = feed.getFeedImage();
						FeedImage feedImage1 = customerTransfor.resizeFeedImage(transfor(com.qding.hotcycle.struct.FeedImage.class,feedImage), 318, 318);
						feed.setFeedImage(feedImage1);
					}
				}
			}

			response.setCode(HttpStatus.OK.getStatusCode());
			response.setData(data);
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetFeedImagesByTimelineForPersonResponseData.class, e);
		}
	}

	/**
	 * 获取指定活动的状态
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getActivityStatus")
	@Deprecated
	public Response<GetActivityStatusResponseData> getActivityStatusResponse(GetActivityStatusRequest request){
		try{
			
			Response<GetActivityStatusResponseData> response = new Response<GetActivityStatusResponseData>();
			GetActivityStatusResponse activityStatusResponse = hotCycleService.getActivityStatusResponse(transfor(com.qding.hotcycle.struct.request.GetActivityStatusRequest.class, request));
			checkAndContinue(activityStatusResponse);
			GetActivityStatusResponseData data = transfor(GetActivityStatusResponseData.class, activityStatusResponse);
			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());
			return response;
		
		}catch (Exception e) {

			return handleException(GetActivityStatusResponseData.class, e);
		}


	}

	/**
	 * 获取指定活动下参与的用户
	 * @param request
	 * @return
	 */
	@HTTP(alias = "setFeedShareImg")
	@Deprecated
	public Response<AddShareFeedImgResponseData> setFeedShareImg (AddShareFeedImgRequest request) {

		try {
			Response<AddShareFeedImgResponseData> response = new Response<AddShareFeedImgResponseData>();
			
			com.qding.hotcycle.struct.request.AddShareFeedImgRequest shareFeedImgRequest = transfor(
					com.qding.hotcycle.struct.request.AddShareFeedImgRequest.class, request);
			
			com.qding.hotcycle.struct.response.AddShareFeedImgResponse shareFeedImgResponse =  hotCycleService.setFeedShareImg(shareFeedImgRequest);

			checkAndContinue(shareFeedImgResponse);
			response.setCode(HttpStatus.OK.getStatusCode());
			return response;
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			return handleException(AddShareFeedImgResponseData.class, e);
		}
	}

	/**
	 * 获取活动详情
	 * @param request
	 * @return
	 */
	@HTTP(alias = "getActivityById")
	@Deprecated
	public Response<GetActivityByActivityIdResponseData> getActivityByActivityId(GetActivityByActivityIdRequest request) {

		try {
			Response<GetActivityByActivityIdResponseData> response = new Response<GetActivityByActivityIdResponseData>();
	
			GetActivityByActivityIdResponse activityResponse = hotCycleService.getActivityByActivityId(
					transfor( com.qding.hotcycle.struct.request.GetActivityByActivityIdRequest.class, request));
	
			checkAndContinue(activityResponse);
			
			GetActivityByActivityIdResponseData data = transfor(GetActivityByActivityIdResponseData.class, activityResponse);
			
			response.setData(data);
			
			response.setCode(HttpStatus.OK.getStatusCode());
			
			return response;
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			return handleException(GetActivityByActivityIdResponseData.class, e);
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


		Response<GetFeedByFeedIdResponseData> response = new Response<GetFeedByFeedIdResponseData>();
		GetFeedByFeedIdResponse getFeedByFeedIdResponse;
		GetFeedByFeedIdResponseData data;

		try {
			getFeedByFeedIdResponse = hotCycleService.getFeedByFeedId(
					transfor(
							com.qding.hotcycle.struct.request.GetFeedByFeedIdRequest.class,
							request
					)
			);

			checkAndContinue(getFeedByFeedIdResponse);

			data = transfor(GetFeedByFeedIdResponseData.class, getFeedByFeedIdResponse);

			com.qding.hotcycle.struct.Feed feed = getFeedByFeedIdResponse.getFeeds();

			try {

				Project project = projectReadService.get(Long.parseLong(feed.getCommunityId()));
				feed.setCommunityName(project.getRegionName()+"-"+feed.getCommunityName());

			} catch (Exception e) {

			}

			com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed fd = customerTransfor.transforFeed(feed, request.getShowPraiseSize(), request.getShowCommentSize());
			if(QDStringUtil.isNotNull(fd))
				data.setEntity(fd);

			response.setCode(HttpStatus.OK.getStatusCode());
			response.setData(data);
			return response;

		} catch (Exception e) {
			return handleException(GetFeedByFeedIdResponseData.class, e);
		}

	}



	public class CustomerTransfor {

		public com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed transforFeed(com.qding.hotcycle.struct.Feed feed, int showPraiseSize, int showCommentSize) {

			com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed fd = transfor(com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed.class, feed);

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
			List<com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedTag> fts = new ArrayList<>();
			List<com.qding.hotcycle.struct.FeedTag> feedTagList = feed.getFeedTags();
			if (CollectionUtils.isNotEmpty(feedTagList)){
				for (com.qding.hotcycle.struct.FeedTag feedTag : feedTagList) {
					com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedTag ft = transfor(com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedTag.class,feedTag);
					fts.add(ft);
				}
			}

			User feedUser = transfor(User.class, userMap.get(fd.getUserId()));
			if(QDStringUtil.isNotNull(feedUser)) {
				feedUser.setUserHeadImageUrl(com.qding.framework.common.util.QDStringUtil.isNotNull(feedUser.getUserHeadImageUrl())?imageUtil.get(feedUser.getUserHeadImageUrl(), 150, 150):"");
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
			if(image == null) {
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
