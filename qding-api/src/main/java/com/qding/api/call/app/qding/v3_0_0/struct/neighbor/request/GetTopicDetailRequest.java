package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/9/9.
 */
@Validate
public class GetTopicDetailRequest extends BaseRequest {

    private static final long serialVersionUID = 1699350228064434666L;

//    @ExplainAnnotation (explain = "查询帖子类型",desc = "真实环境下无此参数，只是方便APP开发人员可根据类型查看相应类型数据")
//    @NotNullValidate
//    private Integer subTopicType;

    @ExplainAnnotation (explain = "话题ID")
    @NotNullValidate
    private String topicId;

    @ExplainAnnotation (explain = "默认显示评论列表数")
    @NotNullValidate
    private Integer showCommentSize = Integer.valueOf(0);

    @ExplainAnnotation (explain = "默认显示点赞用户个数")
    @NotNullValidate
    private Integer showPraiseSize= Integer.valueOf(0);

    @ExplainAnnotation (explain = "默认显示报名用户个数")
    @NotNullValidate
    private Integer showEnrollSize= Integer.valueOf(0);


    public Integer getShowEnrollSize() {
        return showEnrollSize;
    }

    public void setShowEnrollSize(Integer showEnrollSize) {
        this.showEnrollSize = showEnrollSize;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
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


//    public Integer getSubTopicType() {
//        return subTopicType;
//    }
//
//    public void setSubTopicType(Integer subTopicType) {
//        this.subTopicType = subTopicType;
//    }

    @Override
    public String toString() {
        return "GetTopicDetailRequest{" +
                ", topicId='" + topicId + '\'' +
                ", showCommentSize=" + showCommentSize +
                ", showPraiseSize=" + showPraiseSize +
                ", showEnrollSize=" + showEnrollSize +
                '}';
    }
}
