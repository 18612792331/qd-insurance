package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/1/5.
 */
public class OrderWareComment implements Serializable {

    private static final long serialVersionUID = 2740042060177896498L;

    @ExplainAnnotation(explain = "商品ID")
    private Long wareId;

    @ExplainAnnotation(explain = "评价等级",desc = "好：0，中：1，差：2")
    private Integer rate;

    // TODO: 2016/8/11 IOS传入属性名称错误这里做兼容处理

    @ExplainAnnotation(explain = "评价等级",desc = "临时兼容IOS,好：0，中：1，差：2")
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
