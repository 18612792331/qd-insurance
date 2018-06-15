package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.FeaturedImg;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/12/21.
 */
public class FeaturedGoodsResponseData extends ResponseData {

    private static final long serialVersionUID = -6594555670863944884L;

    @ExplainAnnotation(explain = "总记录数")
    private int totalCount;

    @ExplainAnnotation(explain = "标题")
    private String name = "";

    @ExplainAnnotation(explain = "描述")
    private String desc = "";

    @ExplainAnnotation(explain = "特色商品图列表")
    private List<FeaturedImg> imgs;

    @ExplainAnnotation(explain = "特色商品列表")
    private List<Goods> list;

    public List<FeaturedImg> getImgs() {
        return imgs;
    }

    public void setImgs(List<FeaturedImg> imgs) {
        this.imgs = imgs;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
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

    @Override
    public String toString() {
        return "FeaturedGoodsResponseData [totalCount=" + totalCount +",imgs="+imgs+",list="+list+",toString="+ super.toString()+"]";
    }
}
