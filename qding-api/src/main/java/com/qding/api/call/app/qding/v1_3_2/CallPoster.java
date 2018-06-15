package com.qding.api.call.app.qding.v1_3_2;


import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_2.struct.poster.HomePagePoster;
import com.qding.api.call.app.qding.v1_3_2.struct.poster.request.GetPosterImgByCommunityIdRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.poster.response.data.GetPosterImgByCommunityIdResponseData;
import com.qding.api.struct.Response;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.hotcycle.service.IHotCycleRemoteService;
import com.qding.hotcycle.struct.response.GetPosterImgByCommunityIdResponse;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by jiawenzheng on 2015/7/28.
 */
public class CallPoster extends Callable {

    @Autowired
    private IHotCycleRemoteService hotCycleService;


    /**
     * 获取APP启动闪屏图片
     * @param request
     * @return
     */
    @HTTP(alias="homePoster")
    public Response<GetPosterImgByCommunityIdResponseData> homePoster (GetPosterImgByCommunityIdRequest request) {

    	try {
			
    		Response<GetPosterImgByCommunityIdResponseData> response = new Response<GetPosterImgByCommunityIdResponseData>();
            GetPosterImgByCommunityIdResponse posterImgByCommunityIdResponse = hotCycleService.getPosterImgByCommunityId(
            		transfor(com.qding.hotcycle.struct.request.GetPosterImgByCommunityIdRequest.class,request));
            checkAndContinue(posterImgByCommunityIdResponse);

            HomePagePoster  poster = new HomePagePoster();
            poster.setPosterImgUrl(posterImgByCommunityIdResponse.getPosterImgUrl());

            GetPosterImgByCommunityIdResponseData data = new GetPosterImgByCommunityIdResponseData();
            data.setEntity(poster);
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
            
            return response;
    		
		} catch (Exception e) {
			
			return handleException(GetPosterImgByCommunityIdResponseData.class, e);
		}

		
    }

}
