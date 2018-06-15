package com.qding.api.call.app.qding.v3_1_0.struct.neighbor;

import java.io.Serializable;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * Created by qd on 2017/3/2.
 */
public class BkBoard implements Serializable {

    private static final long serialVersionUID = -6993447715733947357L;

    @ExplainAnnotation(explain = "版块标题")
    private String boardTitle;

    @ExplainAnnotation(explain = "百科分类")
    private List<InterestLableDto> listLable;
    

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

	public List<InterestLableDto> getListLable() {
		return listLable;
	}

	public void setListLable(List<InterestLableDto> listLable) {
		this.listLable = listLable;
	}
    
    
    
    
}
