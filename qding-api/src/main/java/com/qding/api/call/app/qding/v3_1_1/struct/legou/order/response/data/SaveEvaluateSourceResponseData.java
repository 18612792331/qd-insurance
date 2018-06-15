package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.EvaluateCouponBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/6/22.
 */
public class SaveEvaluateSourceResponseData extends ResponseData {

    private static final long serialVersionUID = 9032347865768224915L;

    @ExplainAnnotation (explain = "积分数显示")
    private String  integralScore;

    @ExplainAnnotation(explain = "优惠券")
    private List<EvaluateCouponBean> couponList;

    public String getIntegralScore() {
        return integralScore;
    }

    public void setIntegralScore(String integralScore) {
        this.integralScore = integralScore;
    }

    public List<EvaluateCouponBean> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<EvaluateCouponBean> couponList) {
        this.couponList = couponList;
    }

    @Override
    public String toString() {
        return "SaveEvaluateSourceResponseData{" +
                "integralScore='" + integralScore + '\'' +
                ", couponList=" + couponList +
                '}';
    }
}
