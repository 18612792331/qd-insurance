package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/9.
 */
public class VoteItem  extends BriefVoteItem implements Serializable {

    private static final long serialVersionUID = 3977845403511854664L;

    @ExplainAnnotation (explain = "当前项投票人数")
    private Integer voteCount;

    @ExplainAnnotation (explain = "当前项投票结果比值")
    private Double ratio;


    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }


}
