package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/21.
 */
public class ReplacementBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 1572821574043252718L;

    public ReplacementBoard() {
    }

    public ReplacementBoard(List<SimplFeedBean> replacementList) {
        this.replacementList = replacementList;
    }

    private List<SimplFeedBean> replacementList;

    @ExplainAnnotation(explain = "板块名称")
    private String name = "二手闲置";

    @ExplainAnnotation(explain = "时间申明")
    private String timeInfo = "每日10点，16点更新";

    public String getTimeInfo() {
        return timeInfo;
    }

    public void setTimeInfo(String timeInfo) {
        this.timeInfo = timeInfo;
    }

    public List<SimplFeedBean> getReplacementList() {
        return replacementList;
    }

    public void setReplacementList(List<SimplFeedBean> replacementList) {
        this.replacementList = replacementList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
