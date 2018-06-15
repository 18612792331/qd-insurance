package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;
import com.qding.framework.common.smart.validate.Validate;

import java.util.List;

/**
 * Created by qd on 2016/8/16.
 */
public class GetSkuIdListResponseData  extends ResponseData {

    private static final long serialVersionUID = -1994618798953066101L;

    @ExplainAnnotation (explain = "商品ID列表")
    private List<String> skuIds;

    public List<String> getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(List<String> skuIds) {
        this.skuIds = skuIds;
    }

    @Override
    public String toString() {
        return "GetSkuIdListResponseData{" +
                "skuIds=" + skuIds +
                '}';
    }
}
