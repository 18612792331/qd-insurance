package com.qding.api.call.app.qding.v3_3_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

/**
 * Created by qd on 2017/9/7.
 */
public class AutoPayProject extends SkipUrl {

    @ExplainAnnotation (explain = "社区ID")
    private String projectId;

    @ExplainAnnotation (explain = "社区名称")
    private String projectName;

    @ExplainAnnotation (explain = "城市名称")
    private String cityName;

    @ExplainAnnotation (explain = "头图")
    private String imgUrl;

    @ExplainAnnotation (explain = "开通状态",desc = "1:开通，0：未开通")
    private Integer openedStatus;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getOpenedStatus() {
        return openedStatus;
    }

    public void setOpenedStatus(Integer openedStatus) {
        this.openedStatus = openedStatus;
    }
}
