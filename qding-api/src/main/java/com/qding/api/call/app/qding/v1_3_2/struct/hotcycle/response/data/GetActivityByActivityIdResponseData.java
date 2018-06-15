package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.HcActivityDto;
import com.qding.api.struct.ResponseData;

/**
 * Created by Administrator on 2015/8/7.
 */
public class GetActivityByActivityIdResponseData  extends ResponseData {


    private static final long serialVersionUID = 8479357354922593841L;

    private HcActivityDto entity;

    public HcActivityDto getEntity() {
        return entity;
    }

    public void setEntity(HcActivityDto entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetActivityByActivityIdResponse [entity=" + entity +","+ super.toString() + "]";
    }
}
