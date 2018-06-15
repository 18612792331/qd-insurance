package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/9.
 */
public class BriefTheme implements Serializable {

    private static final long serialVersionUID = -1383609919159279477L;

    @ExplainAnnotation (explain = "主题下是否有多个社区",desc = "2.8新增")
    private Boolean multiProject = true;

    @ExplainAnnotation (explain = "主题ID")
    private String themeId;

    @ExplainAnnotation (explain = "主题图片")
    private String themeImage;

    @ExplainAnnotation (explain = "主题名称")
    private String themeName;

    @ExplainAnnotation ( explain = "访问权限",desc = "1.注册用户可访问 2.绑定房间用户可访问")
    private Integer accessPermission;

    @ExplainAnnotation (explain = "主题简介",desc = "2.8版本新增")
    private String themeDesc;

    public String getThemeDesc() {
        return themeDesc;
    }

    public void setThemeDesc(String themeDesc) {
        this.themeDesc = themeDesc;
    }

    public Integer getAccessPermission() {
        return accessPermission;
    }

    public void setAccessPermission(Integer accessPermission) {
        this.accessPermission = accessPermission;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getThemeImage() {
        return themeImage;
    }

    public void setThemeImage(String themeImage) {
        this.themeImage = themeImage;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public Boolean getMultiProject() {
        return multiProject;
    }

    public void setMultiProject(Boolean multiProject) {
        this.multiProject = multiProject;
    }
}
