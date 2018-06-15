package com.qding.api.call.app.qding.v3_0_1.struct.groupon.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by jinhaishan on 17/4/14.
 */
@Validate
public class GoodsIdRequest extends BaseRequest{

    private static final long serialVersionUID = -496009501296573833L;

    @NotNullValidate
    @ExplainAnnotation(explain = "平台商品ID")
    private Long skuId;

    @NotNullValidate
    @ExplainAnnotation(explain = "业态编号")
    private String productNo;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
}
