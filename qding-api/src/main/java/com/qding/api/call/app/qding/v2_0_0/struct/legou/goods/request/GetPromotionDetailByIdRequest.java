package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/3/16.
 */
@Validate
public class GetPromotionDetailByIdRequest extends BaseRequest {

    private static final long serialVersionUID = 3088065538006662397L;

    @NotNullValidate
    @ExplainAnnotation (explain = "促销活动ID")
    private String promotionId;

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }
}
