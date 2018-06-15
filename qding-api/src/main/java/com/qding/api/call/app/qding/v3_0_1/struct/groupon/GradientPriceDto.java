package com.qding.api.call.app.qding.v3_0_1.struct.groupon;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by jinhaishan on 17/4/14.
 */
@ExplainAnnotation(explain = "团购阶梯价格信息")
public class GradientPriceDto implements Serializable {

    private static final long serialVersionUID = -9083459637671423874L;

    @ExplainAnnotation(explain = "价格", desc = "单位（分）")
    private Integer price;

    @ExplainAnnotation(explain = "商品数量下限")
    private Integer lowCount;


    @ExplainAnnotation(explain = "商品数量上限")
    private Integer highCount;


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getLowCount() {
        return lowCount;
    }

    public void setLowCount(Integer lowCount) {
        this.lowCount = lowCount;
    }

    public Integer getHighCount() {
        return highCount;
    }

    public void setHighCount(Integer highCount) {
        this.highCount = highCount;
    }

    public GradientPriceDto() {
    }

    public GradientPriceDto(Integer price, Integer lowCount, Integer highCount) {
        this.price = price;
        this.lowCount = lowCount;
        this.highCount = highCount;
    }
}
