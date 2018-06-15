package com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.popularize.PopularizeUserApplyDto;
import com.qding.api.struct.ResponseData;

/**
 * Created by Administrator on 2015/7/31.
 */
public class GetPopularizeApplyInfoResponseData extends ResponseData {


    private PopularizeUserApplyDto entity;

    public PopularizeUserApplyDto getEntity() {
        return entity;
    }

    public void setEntity(PopularizeUserApplyDto entity) {
        this.entity = entity;
    }

    public GetPopularizeApplyInfoResponseData() {
    }
}
