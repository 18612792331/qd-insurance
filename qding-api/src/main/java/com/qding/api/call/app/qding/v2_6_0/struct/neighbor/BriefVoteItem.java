package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/12.
 */
public class BriefVoteItem implements Serializable {

    @ExplainAnnotation(explain = "当前项投票选项标识")
    private String index;

    @ExplainAnnotation(explain = "当前项投票选项内容")
    private String  content;

    @ExplainAnnotation(explain = "当前项投票选项投票数")
    private Integer voteCount;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}
