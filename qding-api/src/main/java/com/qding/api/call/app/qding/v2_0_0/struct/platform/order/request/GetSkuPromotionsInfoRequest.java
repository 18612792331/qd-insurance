package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.smart.validate.ValidateBean;

/**
 * Description
 * Created by ygd on 2017/9/14.
 */
@ValidateBean
public class GetSkuPromotionsInfoRequest extends BaseRequest {

    @NotNullValidate(name = "skuId")
    private Long skuId;

    @NotNullValidate(name = "业态编码")
    private String productNo;


    public GetSkuPromotionsInfoRequest() {
    }

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



    @Override
    public String toString() {
        return "GetSkuPromotionsInfoRequest{" +
                "skuId=" + skuId +
                ", productNo='" + productNo + '\'' +
                '}';
    }
}
