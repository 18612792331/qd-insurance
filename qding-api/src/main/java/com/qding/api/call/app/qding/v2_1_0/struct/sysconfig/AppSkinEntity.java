package com.qding.api.call.app.qding.v2_1_0.struct.sysconfig;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;


public class AppSkinEntity implements Serializable {

    @ExplainAnnotation(explain = "皮肤id")
    private String id;

    @ExplainAnnotation(explain = "皮肤名称")
    private String name;

    @ExplainAnnotation(explain = "头部区域背景图")
    private String headBg;

    @ExplainAnnotation(explain = "社区名称色值")
    private String mainColor;

    @ExplainAnnotation(explain = "切换社区箭头图标")
    private String projectSelectArrawIcon;

    @ExplainAnnotation(explain = "消息中心图标")
    private String messageIcon;

    @ExplainAnnotation(explain = "消息数字颜色")
    private String messageColor;

    @ExplainAnnotation(explain = "头部4个推荐位背景图")
    private String recommendBg;

    @ExplainAnnotation(explain = "首页图标：（选中态）")
    private String homeIcon;

    @ExplainAnnotation(explain = "管家图标：（未选中态）")
    private String managerIcon;

    @ExplainAnnotation(explain = "邻聚图标：（未选中态）")
    private String socialIcon;

    @ExplainAnnotation(explain = "我的图标：（未选中态）")
    private String mineIcon;

    @ExplainAnnotation(explain = "扫码/报事/发照片图标")
    private String moreIcon;

    @ExplainAnnotation(explain = "底部导航栏背景色")
    private String footBg;

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

    public String getHeadBg() {
        return headBg;
    }

    public void setHeadBg(String headBg) {
        this.headBg = headBg;
    }

    public String getMainColor() {
        return mainColor;
    }

    public void setMainColor(String mainColor) {
        this.mainColor = mainColor;
    }

    public String getProjectSelectArrawIcon() {
        return projectSelectArrawIcon;
    }

    public void setProjectSelectArrawIcon(String projectSelectArrawIcon) {
        this.projectSelectArrawIcon = projectSelectArrawIcon;
    }

    public String getMessageIcon() {
        return messageIcon;
    }

    public void setMessageIcon(String messageIcon) {
        this.messageIcon = messageIcon;
    }

    public String getMessageColor() {
        return messageColor;
    }

    public void setMessageColor(String messageColor) {
        this.messageColor = messageColor;
    }

    public String getRecommendBg() {
        return recommendBg;
    }

    public void setRecommendBg(String recommendBg) {
        this.recommendBg = recommendBg;
    }

    public String getHomeIcon() {
        return homeIcon;
    }

    public void setHomeIcon(String homeIcon) {
        this.homeIcon = homeIcon;
    }

    public String getManagerIcon() {
        return managerIcon;
    }

    public void setManagerIcon(String managerIcon) {
        this.managerIcon = managerIcon;
    }

    public String getSocialIcon() {
        return socialIcon;
    }

    public void setSocialIcon(String socialIcon) {
        this.socialIcon = socialIcon;
    }

    public String getMineIcon() {
        return mineIcon;
    }

    public void setMineIcon(String mineIcon) {
        this.mineIcon = mineIcon;
    }

    public String getMoreIcon() {
        return moreIcon;
    }

    public void setMoreIcon(String moreIcon) {
        this.moreIcon = moreIcon;
    }

    public String getFootBg() {
        return footBg;
    }

    public void setFootBg(String footBg) {
        this.footBg = footBg;
    }

    @Override
    public String toString() {
        return "AppSkinEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", headBg='" + headBg + '\'' +
                ", mainColor='" + mainColor + '\'' +
                ", projectSelectArrawIcon='" + projectSelectArrawIcon + '\'' +
                ", messageIcon='" + messageIcon + '\'' +
                ", messageColor='" + messageColor + '\'' +
                ", recommendBg='" + recommendBg + '\'' +
                ", homeIcon='" + homeIcon + '\'' +
                ", managerIcon='" + managerIcon + '\'' +
                ", socialIcon='" + socialIcon + '\'' +
                ", mineIcon='" + mineIcon + '\'' +
                ", moreIcon='" + moreIcon + '\'' +
                ", footBg='" + footBg + '\'' +
                '}';
    }
}
