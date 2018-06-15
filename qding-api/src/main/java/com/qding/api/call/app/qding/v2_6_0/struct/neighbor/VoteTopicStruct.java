package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/9/20.
 */
public class VoteTopicStruct implements Serializable {

    private static final long serialVersionUID = 8464294691189436820L;

    private String voteTitle;

    private Long startTime;

    private Long endTime;

    private Integer voteType;

    private Integer voteFrequency;

    private List<BriefVoteItem> voteList;

    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getVoteType() {
        return voteType;
    }

    public void setVoteType(Integer voteType) {
        this.voteType = voteType;
    }

    public Integer getVoteFrequency() {
        return voteFrequency;
    }

    public void setVoteFrequency(Integer voteFrequency) {
        this.voteFrequency = voteFrequency;
    }

    public List<BriefVoteItem> getVoteList() {
        return voteList;
    }

    public void setVoteList(List<BriefVoteItem> voteList) {
        this.voteList = voteList;
    }
}
