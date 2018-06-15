package com.qding.api.call.app.qding.v2_0_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.ProductOrderBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/1/5.
 */
public class GetOrderStatusResponseData extends ResponseData {

    private static final long serialVersionUID = 6881753991216122101L;

    @ExplainAnnotation(explain = "订单列表")
    private List<ProductOrderBean> productOrderList;

    public List<ProductOrderBean> getProductOrderList() {
        return productOrderList;
    }

    public void setProductOrderList(List<ProductOrderBean> productOrderList) {
        this.productOrderList = productOrderList;
    }
}
