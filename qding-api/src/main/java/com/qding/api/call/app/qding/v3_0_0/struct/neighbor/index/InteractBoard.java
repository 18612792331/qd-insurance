package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/24.
 */
public class InteractBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 6540296410214105162L;

    @ExplainAnnotation(explain = "邻里互动板块是否显示",desc = "1:是,0:否")
    private Integer isShow = Integer.valueOf(1);

    @ExplainAnnotation(explain = "版块标题")
    private String boardTitle;

    @ExplainAnnotation(explain = "邻里互动帖子列表")
    private List<BriefTopicInfo> topicList;

    @ExplainAnnotation(explain = "邻里互动标签列表")
    private List<BriefInteractionTagInfo> tagList;

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public List<BriefTopicInfo> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<BriefTopicInfo> topicList) {
        this.topicList = topicList;
    }

    public List<BriefInteractionTagInfo> getTagList() {
        return tagList;
    }

    public void setTagList(List<BriefInteractionTagInfo> tagList) {
        this.tagList = tagList;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
}
