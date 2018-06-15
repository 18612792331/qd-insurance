package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.smart.validate.ValidateBean;

import java.util.List;

/**
 * Description
 * Created by ygd on 2017/9/14.
 */

@ValidateBean
public class QueryWarePromotionInfoRequest extends BaseRequest {

    @NotNullValidate(name = "wareId列表")
    private List<Long> wareIdList;

    @NotNullValidate(name = "业态号")
    private String productNo;

    public QueryWarePromotionInfoRequest() {
    }

    public List<Long> getWareIdList() {
        return wareIdList;
    }

    public void setWareIdList(List<Long> wareIdList) {
        this.wareIdList = wareIdList;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    @Override
    public String toString() {
        return "QueryWarePromotionInfoRequest{" +
                "wareIdList=" + wareIdList +
                ", productNo='" + productNo + '\'' +
                '}';
    }
}
