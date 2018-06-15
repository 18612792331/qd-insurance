package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicComment;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/9/9.
 */
public class CommentListResponseData extends ResponseData {

    private static final long serialVersionUID = 2830501132208411261L;

    @ExplainAnnotation(explain = "评论信息列表")
    private List<TopicComment> list;
    
    @ExplainAnnotation (explain = "分页分割时间")
  	private Long cursorTime;
    
    @ExplainAnnotation(explain = "是否有下一页")
    private boolean haveNextPage=false;
    
    
    public List<TopicComment> getList() {
        return list;
    }

    public void setList(List<TopicComment> list) {
        this.list = list;
    }

	public Long getCursorTime() {
		return cursorTime;
	}

	public void setCursorTime(Long cursorTime) {
		this.cursorTime = cursorTime;
	}

	public boolean isHaveNextPage() {
		return haveNextPage;
	}

	public void setHaveNextPage(boolean haveNextPage) {
		this.haveNextPage = haveNextPage;
	}

	@Override
	public String toString() {
		return "CommentListResponseData [list=" + list + ", cursorTime="
				+ cursorTime + ", haveNextPage=" + haveNextPage + "]";
	}

    
    
    
}
