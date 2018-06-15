package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicNotify;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/9/22.
 */
public class GetNotifyByMIdResponseData  extends ResponseData {

    private static final long serialVersionUID = 1293715903035425039L;

    @ExplainAnnotation(explain = "话题点赞评论消息列表")
    private List<TopicNotify> list;

    @ExplainAnnotation(explain = "未读消息数")
    private Integer totalCount;

    public List<TopicNotify> getList() {
        return list;
    }

    public void setList(List<TopicNotify> list) {
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
        return "GetNotifyByMIdResponseData{" +
                "list=" + list +
                ", totalCount=" + totalCount +
                '}';
    }
}
