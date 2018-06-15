package com.qding.api.call.app.qding.v1_4_0.struct.coupon.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/9/22.
 */
@Validate
public class GetPromotionCouponByCodeRequest  extends BaseRequest {


    private static final long serialVersionUID = -1575250681934693326L;

    /**
     * 优惠券code
     */
    @NotNullValidate
    private String couponCode;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    @Override
    public String toString() {
        return "GetPromotionCouponByCodeRequest [couponCode=" + couponCode
                + ", toString()=" + super.toString() + "]";
    }
}
