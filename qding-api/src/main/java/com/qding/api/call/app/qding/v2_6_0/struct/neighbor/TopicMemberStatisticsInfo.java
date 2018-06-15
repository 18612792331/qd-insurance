package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;


/**
 * topic_member_statistics
 */
public class TopicMemberStatisticsInfo implements Serializable {

    public TopicMemberStatisticsInfo() {
    }

    @ExplainAnnotation(explain = "群组状态", desc = "是否可能发言 1可以发言  0禁言（不可以在群组发言）")
    private Integer isSay;

    @ExplainAnnotation(explain = "主题站状态", desc = "是否冻结 1是 0否")
    private Integer isFreeze;

    public Integer getIsSay() {
        return isSay;
    }

    public void setIsSay(Integer isSay) {
        this.isSay = isSay;
    }

    public Integer getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(Integer isFreeze) {
        this.isFreeze = isFreeze;
    }
}