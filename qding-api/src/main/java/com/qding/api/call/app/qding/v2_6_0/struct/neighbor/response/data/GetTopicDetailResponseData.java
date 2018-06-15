package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicDetail;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/9/9.
 */
public class GetTopicDetailResponseData extends ResponseData {

    private static final long serialVersionUID = 3141995139599854053L;

    @ExplainAnnotation (explain = "当前登陆用户冻结状态",desc = "1:冻结，0：不冻结")
    private  Integer isFreeze = Integer.valueOf(1);

    @ExplainAnnotation (explain = "话题详情")
    private TopicDetail entity;

    public Integer getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Integer isFreeze) {
        this.isFreeze = isFreeze;
    }

    public TopicDetail getEntity() {
        return entity;
    }

    public void setEntity(TopicDetail entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetTopicDetailResponseData{" +
                "isFreeze=" + isFreeze +
                ", entity=" + entity +
                '}';
    }
}
