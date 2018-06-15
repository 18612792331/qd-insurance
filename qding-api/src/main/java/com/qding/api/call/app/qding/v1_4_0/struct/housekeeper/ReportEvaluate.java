package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper;

import java.io.Serializable;

/**
 * Created by qd on 2015/9/10.
 */
public class ReportEvaluate implements Serializable {


    private static final long serialVersionUID = -7662895618680695870L;

    /**
     * 报事报修申请单ID
     */
    private String reportId;

    /**
     * 好评度
     */
    private Integer score;

    /**
     * 评价内容
     */
    private String  evaluateContent;

    /**
     * 评价时间
     */
    private Long evaluateTime;


    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Long getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Long evaluateTime) {
        this.evaluateTime = evaluateTime;
    }
}
