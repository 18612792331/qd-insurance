package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/3/16.
 */
public class GetPromotionDetailByIdResponseData extends ResponseData {

    private static final long serialVersionUID = -5011247487848378239L;

    @ExplainAnnotation(explain = "特色商品列表")
    private List<Goods> list;

    @ExplainAnnotation (explain = "总金额")
    private String amounts = "0";

    @ExplainAnnotation (explain = "说明")
    private String detail = "";

    @ExplainAnnotation (explain = "促销展示图片")
    private String displayImg;

    @ExplainAnnotation (explain = "促销名称")
    private String promotionName;

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }

    public String getAmounts() {
        return amounts;
    }

    public void setAmounts(String amounts) {
        this.amounts = amounts;
    }

    public String getDetail() {
        return detail;
    }

    public String getDisplayImg() {
        return displayImg;
    }

    public void setDisplayImg(String displayImg) {
        this.displayImg = displayImg;
    }


    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    @Override
    public String toString() {
        return "GetPromotionDetailByIdResponseData{" +
                "list=" + list +
                ", amounts='" + amounts + '\'' +
                ", detail='" + detail + '\'' +
                ", displayImg='" + displayImg + '\'' +
                ", promotionName='" + promotionName + '\'' +
                '}';
    }
}
