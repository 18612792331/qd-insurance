package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/9/12.
 */
public class BriefVote implements Serializable {

    private static final long serialVersionUID = -3350778769897354072L;

    @ExplainAnnotation (explain = "投票方式",desc = "1:单选，2：多选")
    private Integer voteType;

    @ExplainAnnotation (explain = "投票开始时间")
    private Long startTime;

    @ExplainAnnotation (explain = "投票结束时间")
    private Long endTime;

    @ExplainAnnotation(explain = "投票选项")
    private List<BriefVoteItemDTO> voteList;

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<BriefVoteItemDTO> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<BriefVoteItemDTO> voteList) {
        this.voteList = voteList;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getVoteType() {
        return voteType;
    }

    public void setVoteType(Integer voteType) {
        this.voteType = voteType;
    }
}
