package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderGood;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.EvaluateFlagBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/6/22.
 */
public class GetOrderEvaluateDataPreparationResponseData  extends ResponseData {

    private static final long serialVersionUID = -5302813214141061462L;

    @ExplainAnnotation (explain = "商品列表")
    private List<OrderGood> orderGoods;

    @ExplainAnnotation (explain = "评价标签")
    private List<EvaluateFlagBean> list;


    @ExplainAnnotation (explain = "评价提示语")
    private String evaluatePrompt;


    @ExplainAnnotation (explain = "活动名称")
    private String promotionName;


    public List<OrderGood> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGood> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public List<EvaluateFlagBean> getList() {
        return list;
    }

    public void setList(List<EvaluateFlagBean> list) {
        this.list = list;
    }

    public String getEvaluatePrompt() {
        return evaluatePrompt;
    }

    public void setEvaluatePrompt(String evaluatePrompt) {
        this.evaluatePrompt = evaluatePrompt;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    @Override
    public String toString() {
        return "GetOrderEvaluateDataPreparationResponseData{" +
                "orderGoods=" + orderGoods +
                ", list=" + list +
                ", evaluatePrompt='" + evaluatePrompt + '\'' +
                ", promotionName='" + promotionName + '\'' +
                '}';
    }
}
