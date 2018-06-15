package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.TopicDetail;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/9/9.
 */
public class GetTopicDetailResponseData extends ResponseData {

    private static final long serialVersionUID = 3260083992772127607L;

    @ExplainAnnotation (explain = "帖子详情")
    private TopicDetail entity;
    
    public TopicDetail getEntity() {
        return entity;
    }

    public void setEntity(TopicDetail entity) {
        this.entity = entity;
    }
    
	@Override
    public String toString() {
        return "GetTopicDetailResponseData{" +
                ", entity=" + entity +
                '}';
    }
}
