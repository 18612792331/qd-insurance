package com.qding.api.call.app.qding.v1_3_0.struct.coupon.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.coupon.CalculateOrderPrice;
import com.qding.api.struct.ResponseData;

/**
 * Created by Administrator on 2015/8/13.
 */
public class CalculateOrderPriceResponseData extends ResponseData {


    private static final long serialVersionUID = -716755609626744506L;

    private CalculateOrderPrice entity;

    public CalculateOrderPrice getEntity() {
        return entity;
    }

    public void setEntity(CalculateOrderPrice entity) {
        this.entity = entity;
    }
}
