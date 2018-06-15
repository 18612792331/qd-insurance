package com.qding.api.call.app.qding.v2_8_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

import java.util.List;

/**
 * Created by qd on 2016/11/22.
 */
@Validate
public class GetTopicsByTopicIdListRequest extends BaseRequest {


    @ExplainAnnotation (explain = "话题ID列表")
    private List<String> topicIdList;

    @ExplainAnnotation (explain = "默认显示评论数")
    @NotNullValidate
    private Integer showCommentSize = Integer.valueOf(0);

    @ExplainAnnotation (explain = "默认显示点赞人员列表数")
    @NotNullValidate
    private Integer showPraiseSize= Integer.valueOf(0);

    public List<String> getTopicIdList() {
        return topicIdList;
    }

    public void setTopicIdList(List<String> topicIdList) {
        this.topicIdList = topicIdList;
    }

    public Integer getShowCommentSize() {
        return showCommentSize;
    }

    public void setShowCommentSize(Integer showCommentSize) {
        this.showCommentSize = showCommentSize;
    }

    public Integer getShowPraiseSize() {
        return showPraiseSize;
    }

    public void setShowPraiseSize(Integer showPraiseSize) {
        this.showPraiseSize = showPraiseSize;
    }

    @Override
    public String toString() {
        return "GetTopicsByTopicIdListRequest{" +
                ", showCommentSize=" + showCommentSize +
                ", showPraiseSize=" + showPraiseSize +
                '}';
    }

}
