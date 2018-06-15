package com.qding.api.call.app.qding.v1_4_0.struct.coupon.response.data;

import com.qding.api.call.app.qding.v1_4_0.struct.coupon.Coupon;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/9/22.
 */
public class GetPromotionCouponByCodeResponse   extends ResponseData {


    private static final long serialVersionUID = 4654842391448076826L;

    private Coupon entity;

    public Coupon getEntity() {
        return entity;
    }

    public void setEntity(Coupon entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetPromotionCouponByCodeResponse [entity=" + entity
                + ", toString()=" + super.toString() + "]";
    }


}
