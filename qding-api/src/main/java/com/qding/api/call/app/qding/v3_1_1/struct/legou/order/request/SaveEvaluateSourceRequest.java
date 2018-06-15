package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.EvaluateSourcesSaveBean;
import com.qding.framework.common.api.struct.request.BaseRequest;

import java.util.List;

/**
 * Created by qd on 2017/6/22.
 */
public class SaveEvaluateSourceRequest extends BaseRequest {

    private static final long serialVersionUID = -2455136850882343862L;

    @ExplainAnnotation(explain = "订单号")
    private String orderCode;

    @ExplainAnnotation(explain = "商品评价列表")
    private List<EvaluateSourcesSaveBean> list;

    @ExplainAnnotation(explain = "是否匿名评价")
    private int anonymousFlag;


    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<EvaluateSourcesSaveBean> getList() {
        return list;
    }

    public void setList(List<EvaluateSourcesSaveBean> list) {
        this.list = list;
    }

    public int getAnonymousFlag() {
        return anonymousFlag;
    }

    public void setAnonymousFlag(int anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }

    @Override
    public String toString() {
        return "SaveEvaluateSourceRequest{" +
                "orderCode='" + orderCode + '\'' +
                ", list=" + list +
                ", anonymousFlag=" + anonymousFlag +
                '}';
    }
}
