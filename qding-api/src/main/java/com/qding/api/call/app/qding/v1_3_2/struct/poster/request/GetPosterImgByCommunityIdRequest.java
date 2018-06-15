package com.qding.api.call.app.qding.v1_3_2.struct.poster.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by Administrator on 2015/8/3.
 */
public class GetPosterImgByCommunityIdRequest extends BaseRequest {

    /**
     * 社区ID
     */
    private String communityId = "-1";

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    @Override
    public String toString() {
        return "GetHomePosterRequest [communityId=" + communityId
                + ", toString()=" + super.toString() + "]";
    }


}
