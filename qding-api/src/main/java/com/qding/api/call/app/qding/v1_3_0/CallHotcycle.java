package com.qding.api.call.app.qding.v1_3_0;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.PublishTag;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.IntegralMessage;
import com.qding.api.imessage.IntegralMessageBeanT;
import com.qding.api.util.QDMemberRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberRoom;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.response.memberroom.GetMemberRoomResponse;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberByAccountIdRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.hotcycle.request.PublishFeedRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.hotcycle.response.data.PublishFeedResponseData;
import com.qding.api.struct.Response;
import com.qding.hotcycle.service.IHotCycleRemoteService;
import com.qding.hotcycle.struct.response.PublishFeedResponse;

import java.util.List;

public class CallHotcycle extends com.qding.api.call.app.qding.v1_2_2.CallHotcycle{

	@Autowired
	private IHotCycleRemoteService hotcycleService;

	@Autowired
	private IntegralMessage integralMessage;

    @Autowired
    private IMemberRoomRPC memberRoomAPI;

    @Autowired
    private IProfileService profileAPI;

	/**
	 * 发布信息
	 * @param request
	 * @return
	 */
	@HTTP(alias="publishFeed")
    @Deprecated
	public Response<PublishFeedResponseData> publishFeed(PublishFeedRequest request) {

		try {

			com.qding.hotcycle.struct.request.PublishFeedAndTagRequest feedRequest = transfor(
					com.qding.hotcycle.struct.request.PublishFeedAndTagRequest.class, 
					request
					);
            String imgUrl = feedRequest.getImageUrl();

            if (imgUrl.contains("http:")){
                imgUrl = imgUrl.replace("http:","https:");
                feedRequest.setImageUrl(imgUrl);
            }
			
			PublishFeedResponse publishFeedResponse = hotcycleService.publishFeedAndTag(feedRequest);
			
			checkAndContinue(publishFeedResponse);
			
			Response<PublishFeedResponseData> response = new Response<>();

			PublishFeedResponseData data = transfor(PublishFeedResponseData.class, publishFeedResponse);

			response.setData(data);

			response.setCode(HttpStatus.OK.getStatusCode());

			List<PublishTag> publishTagList = request.getTags();
            //绑定房间会员，添加积分
            if(isBindRoom(request.getUserId())){
                for (int i=0;i<publishTagList.size();i++){

                    try {

                        PublishTag tag = publishTagList.get(i);

                        if (QDStringUtil.isNotNull(tag.getActivityId()) && QDStringUtil.isNotNull(data.getFeedId())) {
                            //加入积分规则
                            IntegralMessageBeanT integralMessageBeanT = new IntegralMessageBeanT(request.getUserId(), Constant.INTEGRAL_PUBLISH_FEED,data.getFeedId()+","+tag.getActivityId(), Long.parseLong(request.getCommunityId()), System.currentTimeMillis(), Constant.INCOME_OPT_TYPE, null,data.getFeedId());
                            integralMessage.assembleIntegralMessage(integralMessageBeanT);
                            break;
                        }

                    } catch (Exception e) {

                    }

                }
            }

			return response;

		} catch (Exception e) {
			
			return handleException(PublishFeedResponseData.class, e);
		}
	}

    private boolean isBindRoom(String userId){
        if(QDStringUtil.isEmpty(userId)){
            return false;
        }
        try{
            //获取memberId
            GetMemberByAccountIdRequest request = new GetMemberByAccountIdRequest();
            request.setAccountId(userId);
            GetMemberResponse response = profileAPI.getMemberByAccountId(request);
            if(response!=null && response.getReturnInfo().getCode()==HttpStatus.OK.getStatusCode()){
                MemberInfo mi = response.getMemberInfo();
                GetMemberRoomResponse memberRoomResponse = memberRoomAPI.listValidByMember(mi.getId());
                if(memberRoomResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                    List<MemberRoom> list = memberRoomResponse.getList();
                    if(list.size()>0){
                        return true;
                    }
                } else {
                    return  false;
                }

            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
}
