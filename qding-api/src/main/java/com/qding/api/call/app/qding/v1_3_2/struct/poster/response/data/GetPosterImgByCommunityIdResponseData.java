package com.qding.api.call.app.qding.v1_3_2.struct.poster.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.poster.HomePagePoster;
import com.qding.api.struct.ResponseData;

/**
 * Created by Administrator on 2015/8/3.
 */
public class GetPosterImgByCommunityIdResponseData extends ResponseData {


    private HomePagePoster entity;

    public HomePagePoster getEntity() {
        return entity;
    }

    public void setEntity(HomePagePoster entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetPosterImgByCommunityIdResponse [entity=" + entity
                + ", toString()=" + super.toString() + "]";
    }
}
