package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicNotify;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/9/22.
 */
public class GetHistoryNotifyByMIdResponseData extends ResponseData {

    private static final long serialVersionUID = 5760367426565421370L;

    @ExplainAnnotation (explain = "总数")
    private Integer totalCount;

    @ExplainAnnotation (explain = "话题点赞评论消息列表")
    private List<TopicNotify> list;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<TopicNotify> getList() {
        return list;
    }

    public void setList(List<TopicNotify> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetHistoryNotifyByMIdResponse{" +
                "totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }
}
