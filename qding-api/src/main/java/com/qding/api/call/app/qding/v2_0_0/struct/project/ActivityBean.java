package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.ActivitySale;
import com.qding.api.call.app.qding.v2_0_0.struct.project.AuctionActivity;
import com.qding.api.call.app.qding.v2_0_0.struct.project.GrouponActivity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/16.
 */
public class ActivityBean implements Serializable {

    private static final long serialVersionUID = -5285943180393154816L;

    @ExplainAnnotation(explain = "阶梯团购")
    private List<GrouponActivity> grouponActivity;

    //拉人团购
    @ExplainAnnotation(explain = "积分夺宝活动")
    private List<AuctionActivity> auctionActivity;

    @ExplainAnnotation(explain = "秒杀||其他h5")
    private List<ActivitySale> miaoshaActivity;

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
