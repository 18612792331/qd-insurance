package com.qding.api.call.app.qding.v3_3_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_1_0.struct.hotcycle.GcRoomDTO;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.NeighborBanner;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.ActivityBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.InteractBoard;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.BkBoard;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.index.ChoicenessBoard;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.GcRoomBoard;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.NewsAndActivityBarBoard;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.PersonalBoard;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/9/11.
 */
public class GetNeighborIndexResponseData extends ResponseData {

    private static final long serialVersionUID = 4891051114149438128L;

/*    @ExplainAnnotation(explain = "我的相关")
    private PersonalBoard personalBoard;*/

    @ExplainAnnotation(explain = "邻聚社区首页banner列表")
    private List<NeighborBanner> bannerList;

    @ExplainAnnotation(explain = "周边新闻导航条")
    private NewsAndActivityBarBoard  newsAndActivityBarBoard;

    @ExplainAnnotation(explain = "群组列表")
    private GcRoomBoard gcRoomBoard;

    @ExplainAnnotation (explain = "邻里互动版块")
    private InteractBoard interactBoard;

    @ExplainAnnotation (explain = "社区活动板块")
    private ActivityBoard activityBoard;

    @ExplainAnnotation (explain = "精选话题版块")
    private ChoicenessBoard choicenessBoard;

    @ExplainAnnotation (explain = "生活百科版块")
    private BkBoard encyclopediaBoard;



    public List<NeighborBanner> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<NeighborBanner> bannerList) {
        this.bannerList = bannerList;
    }

    public NewsAndActivityBarBoard getNewsAndActivityBarBoard() {
        return newsAndActivityBarBoard;
    }

    public void setNewsAndActivityBarBoard(NewsAndActivityBarBoard newsAndActivityBarBoard) {
        this.newsAndActivityBarBoard = newsAndActivityBarBoard;
    }

    public GcRoomBoard getGcRoomBoard() {
        return gcRoomBoard;
    }

    public void setGcRoomBoard(GcRoomBoard gcRoomBoard) {
        this.gcRoomBoard = gcRoomBoard;
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

    public BkBoard getEncyclopediaBoard() {
        return encyclopediaBoard;
    }

    public void setEncyclopediaBoard(BkBoard encyclopediaBoard) {
        this.encyclopediaBoard = encyclopediaBoard;
    }


    @Override
    public String toString() {
        return "GetNeighborIndexResponseData{" +
                ", bannerList=" + bannerList +
                ", newsAndActivityBarBoard=" + newsAndActivityBarBoard +
                ", gcRoomBoard=" + gcRoomBoard +
                ", interactBoard=" + interactBoard +
                ", activityBoard=" + activityBoard +
                ", choicenessBoard=" + choicenessBoard +
                ", encyclopediaBoard=" + encyclopediaBoard +
                '}';
    }
}
