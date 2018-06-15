package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.NeighborBanner;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.*;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/2/23.
 */
public class GetNeighborIndexResponseData  extends ResponseData{

    private static final long serialVersionUID = 484707813977294931L;

    @ExplainAnnotation(explain = "邻聚社区首页banner列表")
    private List<NeighborBanner> bannerList;

    @ExplainAnnotation (explain = "新闻版块")
    private NewsBoard newsBoard;

    @ExplainAnnotation (explain = "邻里互动版块")
    private InteractBoard interactBoard;

    @ExplainAnnotation (explain = "社区活动板块")
    private ActivityBoard activityBoard;

    @ExplainAnnotation (explain = "精选话题版块")
    private ChoicenessBoard choicenessBoard;

    @ExplainAnnotation (explain = "生活百科版块")
    private EncyclopediaBoard encyclopediaBoard;

    public List<NeighborBanner> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<NeighborBanner> bannerList) {
        this.bannerList = bannerList;
    }

    public NewsBoard getNewsBoard() {
        return newsBoard;
    }

    public void setNewsBoard(NewsBoard newsBoard) {
        this.newsBoard = newsBoard;
    }

    public InteractBoard getInteractBoard() {
        return interactBoard;
    }

    public void setInteractBoard(InteractBoard interactBoard) {
        this.interactBoard = interactBoard;
    }

    public ActivityBoard getActivityBoard() {
        return activityBoard;
    }

    public void setActivityBoard(ActivityBoard activityBoard) {
        this.activityBoard = activityBoard;
    }

    public ChoicenessBoard getChoicenessBoard() {
        return choicenessBoard;
    }

    public void setChoicenessBoard(ChoicenessBoard choicenessBoard) {
        this.choicenessBoard = choicenessBoard;
    }

    public EncyclopediaBoard getEncyclopediaBoard() {
        return encyclopediaBoard;
    }

    public void setEncyclopediaBoard(EncyclopediaBoard encyclopediaBoard) {
        this.encyclopediaBoard = encyclopediaBoard;
    }
}
