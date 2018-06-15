package com.qding.api.call.app.qding.v2_6_0;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.ThemeRuleForMember;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.ThemeTagInfo;
import com.qding.neighbor.domain.Theme;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/9/21.
 */
public class VerifyRule implements Serializable {

    private static final long serialVersionUID = 1632817280525093371L;

    @ExplainAnnotation(explain = "主题信息")
    private Theme theme;

    @ExplainAnnotation(explain = "主题所属标签列表")
    private List<ThemeTagInfo> tagList;

    @ExplainAnnotation(explain = "不同类型话题上限控制信息")
    private List<ThemeRuleForMember> verifyRuleList;

    @ExplainAnnotation (explain = "当前主题是否可用",desc = "1:可用 ,0:不可用(下架),-1:不可用(未绑定房屋)，-2:不可用(绑定待审及其他)")
    private Integer canUse = Integer.valueOf(0);

    @ExplainAnnotation (explain = "不可用下的话术")
    private String remindMsg = "";

    @ExplainAnnotation (explain = "主题权限类型",desc = "1:注册用户可访问 ,2:绑定房间用户可访问")
    private Integer accessPermission ;

    public String getRemindMsg() {
        return remindMsg;
    }

    public void setRemindMsg(String remindMsg) {
        this.remindMsg = remindMsg;
    }

    public Integer getCanUse() {
        return canUse;
    }

    public void setCanUse(Integer canUse) {
        this.canUse = canUse;
    }

    public List<ThemeRuleForMember> getVerifyRuleList() {
        return verifyRuleList;
    }

    public void setVerifyRuleList(List<ThemeRuleForMember> verifyRuleList) {
        this.verifyRuleList = verifyRuleList;
    }

    public Integer getAccessPermission() {
        return accessPermission;
    }

    public void setAccessPermission(Integer accessPermission) {
        this.accessPermission = accessPermission;
    }

    public List<ThemeTagInfo> getTagList() {
        return tagList;
    }

    public void setTagList(List<ThemeTagInfo> tagList) {
        this.tagList = tagList;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return "VerifyRule{" +
                "theme=" + theme +
                ", tagList=" + tagList +
                ", verifyRuleList=" + verifyRuleList +
                ", canUse=" + canUse +
                ", remindMsg='" + remindMsg + '\'' +
                ", accessPermission=" + accessPermission +
                '}';
    }
}
