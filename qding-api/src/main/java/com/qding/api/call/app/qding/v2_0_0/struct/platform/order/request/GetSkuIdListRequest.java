package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/8/16.
 */
@Validate
public class GetSkuIdListRequest extends BaseRequest {


    @ExplainAnnotation (explain = "推荐详情ID")
    @NotNullValidate
    private String indexId;

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }
}
