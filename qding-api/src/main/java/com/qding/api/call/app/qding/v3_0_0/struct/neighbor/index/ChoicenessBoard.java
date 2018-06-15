package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/3/2.
 */
public class ChoicenessBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -361310504696748752L;

    @ExplainAnnotation(explain = "版块标题")
    private String boardTitle;

    @ExplainAnnotation(explain = "精选话题列表")
    private List<ChoicenessTopicInfo> choicenessList;

    public List<ChoicenessTopicInfo> getChoicenessList() {
        return choicenessList;
    }

    public void setChoicenessList(List<ChoicenessTopicInfo> choicenessList) {
        this.choicenessList = choicenessList;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }
}
