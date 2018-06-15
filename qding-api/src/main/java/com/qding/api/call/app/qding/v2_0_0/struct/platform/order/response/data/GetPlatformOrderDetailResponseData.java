package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.response.data;

import com.qding.api.call.app.qding.v2_0_0.struct.platform.order.PlatformOrder;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/3/17.
 */
public class GetPlatformOrderDetailResponseData extends ResponseData {

    private static final long serialVersionUID = 1612499240501366129L;


    private PlatformOrder entity;

    public GetPlatformOrderDetailResponseData() {

    }

    public PlatformOrder getEntity() {
        return entity;
    }

    public void setEntity(PlatformOrder entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetPlatformOrderDetailResponseData [entity=" + entity + ", toString()="
                + super.toString() + "]";
    }
}
