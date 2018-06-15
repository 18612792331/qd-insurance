package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.ActivityPiazzaDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/2/24.
 */
public class ActivityBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -4680378886732071475L;

    @ExplainAnnotation(explain = "版块标题")
    private String boardTitle;

    @ExplainAnnotation(explain = "活动简介")
    private List<ActivityPiazzaDTO> list;

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public List<ActivityPiazzaDTO> getList() {
        return list;
    }

    public void setList(List<ActivityPiazzaDTO> list) {
        this.list = list;
    }
}
