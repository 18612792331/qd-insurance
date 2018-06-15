package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;

import java.io.Serializable;

/**
 * Created by qd on 2015/12/30.
 */
public class RecommendGood extends SkipUrl  implements Serializable {

    private static final long serialVersionUID = 2411968631210851107L;

    @ExplainAnnotation(explain = "活动名称")
     private String name;

    @ExplainAnnotation(explain = "活动描述")
     private String desc;

    @ExplainAnnotation(explain = "图片展示")
     private String imageUrl;

    @ExplainAnnotation(explain = "商品简要信息")
     private Goods good;

    @ExplainAnnotation(explain = "是否是主题位",desc = "1:普通商品，2:主题位")
    private Integer  recommendType = 1;

    public Integer getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(Integer recommendType) {
        this.recommendType = recommendType;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
