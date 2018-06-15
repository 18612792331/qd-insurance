package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/9/12.
 */
public class BriefVote implements Serializable {

    private static final long serialVersionUID = -3350778769897354072L;

    @ExplainAnnotation (explain = "投票开始时间")
    private Long startTime;

    @ExplainAnnotation (explain = "投票结束时间")
    private Long endTime;

    @ExplainAnnotation(explain = "投票选项")
    private List<BriefVoteItem> voteList;

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<BriefVoteItem> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<BriefVoteItem> voteList) {
        this.voteList = voteList;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
}
