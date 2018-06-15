package com.qding.api.call.app.qding.v3_2_0.struct.project;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.NoticeBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.project.*;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/28.
 */
public class ProjectIndex implements Serializable {

    private static final long serialVersionUID = -8409175784917268200L;

    @ExplainAnnotation(explain = "日历板块")
    private CalendarBoard calendarBoard = new CalendarBoard();

    @ExplainAnnotation(explain = "常用工具")
    private ProjectServicesBoard projectServices =  new ProjectServicesBoard();

    @ExplainAnnotation(explain = "公告板块")
    private NoticeBoard noticeBoard = new NoticeBoard();

    @ExplainAnnotation(explain = "社区生活板块")
    private ProjectLifeBoard projectLifeBoard = new ProjectLifeBoard();

    @ExplainAnnotation(explain = "居家服务板块")
    private LifeServicesBoard lifeServicesBoard = new LifeServicesBoard();

    @ExplainAnnotation(explain = "福利铺板块")
    private WelfareBoard welfareBoard = new WelfareBoard();

    @ExplainAnnotation(explain = "品质生活板块")
    private QualityLifeBoard qualityLifeBoard = new QualityLifeBoard();

    @ExplainAnnotation(explain = "热卖推荐板块")
    private CityWideRecommendBoard cityWideRecommendBoard = new CityWideRecommendBoard();

    @ExplainAnnotation(explain = "提醒板块")
    private RemindBoard remindBoard = new RemindBoard();

    @ExplainAnnotation(explain = "推荐搜索关键词")
    private String keyWord="";

    @ExplainAnnotation(explain = "管家电话")
    private List<String> phones = Lists.newArrayList();

    @ExplainAnnotation(explain = "4产品入口板块")
    private ProductMarketBoard productMarket = new ProductMarketBoard();

    @ExplainAnnotation(explain = "签到入口")
    private SignInEntry signInEntry;

    @ExplainAnnotation(explain = "出游玩乐板块")
    private PlayBoard playBoard = new PlayBoard();

    @ExplainAnnotation(explain = "乐购商城板块")
    private LegouBoard legouBoard = new  LegouBoard();


    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public CalendarBoard getCalendarBoard() {
        return calendarBoard;
    }

    public void setCalendarBoard(CalendarBoard calendarBoard) {
        this.calendarBoard = calendarBoard;
    }

    public ProjectServicesBoard getProjectServices() {
        return projectServices;
    }

    public void setProjectServices(ProjectServicesBoard projectServices) {
        this.projectServices = projectServices;
    }

    public NoticeBoard getNoticeBoard() {
        return noticeBoard;
    }

    public void setNoticeBoard(NoticeBoard noticeBoard) {
        this.noticeBoard = noticeBoard;
    }

    public ProjectLifeBoard getProjectLifeBoard() {
        return projectLifeBoard;
    }

    public void setProjectLifeBoard(ProjectLifeBoard projectLifeBoard) {
        this.projectLifeBoard = projectLifeBoard;
    }

    public LifeServicesBoard getLifeServicesBoard() {
        return lifeServicesBoard;
    }

    public void setLifeServicesBoard(LifeServicesBoard lifeServicesBoard) {
        this.lifeServicesBoard = lifeServicesBoard;
    }

    public WelfareBoard getWelfareBoard() {
        return welfareBoard;
    }

    public void setWelfareBoard(WelfareBoard welfareBoard) {
        this.welfareBoard = welfareBoard;
    }

    public QualityLifeBoard getQualityLifeBoard() {
        return qualityLifeBoard;
    }

    public void setQualityLifeBoard(QualityLifeBoard qualityLifeBoard) {
        this.qualityLifeBoard = qualityLifeBoard;
    }

    public CityWideRecommendBoard getCityWideRecommendBoard() {
        return cityWideRecommendBoard;
    }

    public void setCityWideRecommendBoard(CityWideRecommendBoard cityWideRecommendBoard) {
        this.cityWideRecommendBoard = cityWideRecommendBoard;
    }

    public RemindBoard getRemindBoard() {
        return remindBoard;
    }

    public void setRemindBoard(RemindBoard remindBoard) {
        this.remindBoard = remindBoard;
    }

    public ProductMarketBoard getProductMarket() {
        return productMarket;
    }

    public void setProductMarket(ProductMarketBoard productMarket) {
        this.productMarket = productMarket;
    }

    public SignInEntry getSignInEntry() {
        return signInEntry;
    }

    public void setSignInEntry(SignInEntry signInEntry) {
        this.signInEntry = signInEntry;
    }

    public PlayBoard getPlayBoard() {
        return playBoard;
    }

    public void setPlayBoard(PlayBoard playBoard) {
        this.playBoard = playBoard;
    }

    public LegouBoard getLegouBoard() {
        return legouBoard;
    }

    public void setLegouBoard(LegouBoard legouBoard) {
        this.legouBoard = legouBoard;
    }
}
