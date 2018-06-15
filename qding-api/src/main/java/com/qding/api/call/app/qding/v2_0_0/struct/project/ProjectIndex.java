package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v2_8_0.struct.project.BannerBoard;
import com.qding.api.call.app.qding.v2_8_0.struct.project.IndexBanner;
import com.qding.api.call.app.qding.v2_8_0.struct.project.LifeServicesBoard;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/16.
 */
public class ProjectIndex implements Serializable {

    private static final long serialVersionUID = -1698320207022994484L;

    @ExplainAnnotation(explain = "banner列表",desc = "2.8版本新增")
    private BannerBoard bannerBoard = new BannerBoard();

    @ExplainAnnotation(explain = "管家电话")
    private List<String> phones = Lists.newArrayList();

    @ExplainAnnotation(explain = "推荐搜索关键词")
    private String keyWord="";

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @ExplainAnnotation(explain = "常用工具")
    private List<ProjectService> projectServices = Lists.newArrayList();

    @ExplainAnnotation(explain = "公告板块")
    private NoticeBoard noticeBoard = new NoticeBoard();

    @ExplainAnnotation(explain = "服务板块")
    private LifeServicesBoard lifeServicesBoard = new LifeServicesBoard();

    @ExplainAnnotation(explain = "其他板块",desc = "亲子,生鲜,旅游等")
    private List<BoardTmplBean> boardList = Lists.newArrayList();

    @ExplainAnnotation(explain = "同城闲置板块板块")
    private ReplacementBoard replacementBoard = new ReplacementBoard(Lists.<SimplFeedBean>newArrayList());

    @ExplainAnnotation(explain = "猜你喜欢板块")
    private RecommendBoard recommendBoard = new RecommendBoard();

    @ExplainAnnotation(explain = "乐购品类推荐板块",desc = "2.2版APP Test")
    private LgRevealCategoryBoard lgRevealCategoryBoard = new LgRevealCategoryBoard();

    public LgRevealCategoryBoard getLgRevealCategoryBoard() {
        return lgRevealCategoryBoard;
    }

    public void setLgRevealCategoryBoard(LgRevealCategoryBoard lgRevealCategoryBoard) {
        this.lgRevealCategoryBoard = lgRevealCategoryBoard;
    }

    public List<ProjectService> getProjectServices() {
        return projectServices;
    }

    public void setProjectServices(List<ProjectService> projectServices) {
        this.projectServices = projectServices;
    }

    public NoticeBoard getNoticeBoard() {
        return noticeBoard;
    }

    public void setNoticeBoard(NoticeBoard noticeBoard) {
        this.noticeBoard = noticeBoard;
    }

    public ReplacementBoard getReplacementBoard() {
        return replacementBoard;
    }

    public void setReplacementBoard(ReplacementBoard replacementBoard) {
        this.replacementBoard = replacementBoard;
    }

    public RecommendBoard getRecommendBoard() {
        return recommendBoard;
    }

    public void setRecommendBoard(RecommendBoard recommendBoard) {
        this.recommendBoard = recommendBoard;
    }

    public List<BoardTmplBean> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<BoardTmplBean> boardList) {
        this.boardList = boardList;
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public BannerBoard getBannerBoard() {
        return bannerBoard;
    }

    public void setBannerBoard(BannerBoard bannerBoard) {
        this.bannerBoard = bannerBoard;
    }

    public LifeServicesBoard getLifeServicesBoard() {
        return lifeServicesBoard;
    }

    public void setLifeServicesBoard(LifeServicesBoard lifeServicesBoard) {
        this.lifeServicesBoard = lifeServicesBoard;
    }
}
