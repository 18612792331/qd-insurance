package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/16.
 */
public class BoardTmplBean  extends BtnSkip implements Serializable {

    private static final long serialVersionUID = -4908860035474054351L;

    @ExplainAnnotation(explain = "标题")
    private String title;

    @ExplainAnnotation(explain = "描述")
    private String desc;

    @ExplainAnnotation(explain = "UI模板类型")
    private Integer uiTempType;

    @ExplainAnnotation(explain = "阶梯团购")
    private List<GrouponActivity> grouponActivity = Lists.newArrayList();

    @ExplainAnnotation(explain = "积分夺宝活动")
    private List<AuctionActivity> auctionActivity = Lists.newArrayList();

    @ExplainAnnotation(explain = "秒杀")
    private List<ActivitySale> miaoshaActivity = Lists.newArrayList();

    @ExplainAnnotation(explain = "板块图片信息列表")
    private List<BoardImg> imgList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<BoardImg> getImgList() {
        return imgList;
    }

    public void setImgList(List<BoardImg> imgList) {
        this.imgList = imgList;
    }

    public Integer getUiTempType() {
        return uiTempType;
    }

    public void setUiTempType(Integer uiTempType) {
        this.uiTempType = uiTempType;
    }

    public List<GrouponActivity> getGrouponActivity() {
        return grouponActivity;
    }

    public void setGrouponActivity(List<GrouponActivity> grouponActivity) {
        this.grouponActivity = grouponActivity;
    }

    public List<AuctionActivity> getAuctionActivity() {
        return auctionActivity;
    }

    public void setAuctionActivity(List<AuctionActivity> auctionActivity) {
        this.auctionActivity = auctionActivity;
    }

    public List<ActivitySale> getMiaoshaActivity() {
        return miaoshaActivity;
    }

    public void setMiaoshaActivity(List<ActivitySale> miaoshaActivity) {
        this.miaoshaActivity = miaoshaActivity;
    }
}
