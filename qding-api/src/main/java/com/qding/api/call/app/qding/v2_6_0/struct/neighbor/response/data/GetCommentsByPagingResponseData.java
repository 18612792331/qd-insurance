package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicComment;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/9/9.
 */
public class GetCommentsByPagingResponseData extends ResponseData {

    private static final long serialVersionUID = 2830501132208411261L;

    @ExplainAnnotation(explain = "评论信息列表")
    private List<TopicComment> list;

    @ExplainAnnotation(explain = "总记录数")
    private Integer totalCount;

    public List<TopicComment> getList() {
        return list;
    }

    public void setList(List<TopicComment> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GetCommentsByPagingResponseData{" +
                "list=" + list +
                ", totalCount=" + totalCount +
                '}';
    }
}
