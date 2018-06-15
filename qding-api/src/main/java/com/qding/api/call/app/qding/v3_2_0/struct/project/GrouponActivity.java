package com.qding.api.call.app.qding.v3_2_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import com.qding.api.call.app.qding.v3_0_1.struct.groupon.GradientPriceDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/11/3.
 */
public class GrouponActivity extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 4537205751463285340L;

    @ExplainAnnotation(explain = "阶梯团购ID")
    private String id;

    @ExplainAnnotation(explain = "团购标题")
    private String name;

    @ExplainAnnotation(explain = "已经购买商品数量")
    private Integer boughtCount;

    @ExplainAnnotation(explain = "图片url")
    private String imgUrl;

    @ExplainAnnotation(explain = "团购阶梯价格", desc = "阶梯价格的顺序为后台配置的顺序")
    private List<GradientPriceDto> gradientPrices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoughtCount() {
        return boughtCount;
    }

    public void setBoughtCount(Integer boughtCount) {
        this.boughtCount = boughtCount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<GradientPriceDto> getGradientPrices() {
        return gradientPrices;
    }

    public void setGradientPrices(List<GradientPriceDto> gradientPrices) {
        this.gradientPrices = gradientPrices;
    }

    @Override
    public String toString() {
        return "GrouponActivity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", boughtCount=" + boughtCount +
                ", imgUrl='" + imgUrl + '\'' +
                ", gradientPrices=" + gradientPrices +
                '}';
    }
}
