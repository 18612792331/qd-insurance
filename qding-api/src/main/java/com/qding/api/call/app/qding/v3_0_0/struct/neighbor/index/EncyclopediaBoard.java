package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.BriefEncyclopedia;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/3/2.
 */
public class EncyclopediaBoard implements Serializable {

    private static final long serialVersionUID = -6993447715733947357L;

    @ExplainAnnotation(explain = "版块标题")
    private String boardTitle;

    @ExplainAnnotation (explain = "是否有下一页")
    private Boolean haveNextPage;

    @ExplainAnnotation (explain = "百科列表")
    private List<BriefEncyclopedia> list;

    public Boolean getHaveNextPage() {
        return haveNextPage;
    }

    public void setHaveNextPage(Boolean haveNextPage) {
        this.haveNextPage = haveNextPage;
    }

    public List<BriefEncyclopedia> getList() {
        return list;
    }

    public void setList(List<BriefEncyclopedia> list) {
        this.list = list;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }
}
