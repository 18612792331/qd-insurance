package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.ThemeRuleForMember;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.ThemeTagInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/9/13.
 */
public class GetMemberRuleForVoteAndActivityResponseData extends ResponseData {

    private static final long serialVersionUID = -5510765065566725113L;

    @ExplainAnnotation(explain = "标题内容",desc = "2.8版本新增字段")
    private String title;

    @ExplainAnnotation(explain = "活动标签列表",desc = "2.8版本新增字段")
    private List<ThemeTagInfo> themeTagInfoList;

    @ExplainAnnotation(explain = "不同类型话题上限控制信息")
    private List<ThemeRuleForMember> list;

    @ExplainAnnotation (explain = "当前主题是否可用",desc = "0:可用，1：不可用")
    private Integer canUse = Integer.valueOf(0);

    @ExplainAnnotation (explain = "不可用下的话术")
    private String remindMsg = "";

    @ExplainAnnotation (explain = "当前系统时间")
    private Long  nowTime = System.currentTimeMillis();

    public Long getNowTime() {
        return nowTime;
    }

    public void setNowTime(Long nowTime) {
        this.nowTime = nowTime;
    }

    public List<ThemeRuleForMember> getList() {
        return list;
    }

    public void setList(List<ThemeRuleForMember> list) {
        this.list = list;
    }

    public Integer getCanUse() {
        return canUse;
    }

    public void setCanUse(Integer canUse) {
        this.canUse = canUse;
    }

    public String getRemindMsg() {
        return remindMsg;
    }

    public void setRemindMsg(String remindMsg) {
        this.remindMsg = remindMsg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ThemeTagInfo> getThemeTagInfoList() {
        return themeTagInfoList;
    }

    public void setThemeTagInfoList(List<ThemeTagInfo> themeTagInfoList) {
        this.themeTagInfoList = themeTagInfoList;
    }

    @Override
    public String toString() {
        return "GetMemberRuleForVoteAndActivityResponseData{" +
                "title='" + title + '\'' +
                ", themeTagInfoList=" + themeTagInfoList +
                ", list=" + list +
                ", canUse=" + canUse +
                ", remindMsg='" + remindMsg + '\'' +
                ", nowTime=" + nowTime +
                '}';
    }
}
