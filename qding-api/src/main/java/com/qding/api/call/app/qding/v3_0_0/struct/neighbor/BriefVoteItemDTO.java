package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/12.
 */
public class BriefVoteItemDTO extends SkipUrl implements Serializable {

    @ExplainAnnotation(explain = "当前项投票选项标识")
    private String index;

    @ExplainAnnotation(explain = "当前项投票选项内容")
    private String  content;

    @ExplainAnnotation(explain = "当前项投票选项图片")
    private String  voteImg;

    @ExplainAnnotation(explain = "当前项投票选项投票数")
    private Integer voteCount;
    
    @ExplainAnnotation(explain = "投票关联百科帖子")
    private String topicId;

    @ExplainAnnotation(explain = "投票选项关联说明")
    private String explain;

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

    public String getVoteImg() {
        return voteImg;
    }

    public void setVoteImg(String voteImg) {
        this.voteImg = voteImg;
    }

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
