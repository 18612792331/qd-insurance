package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2016/9/9.
 */
public class DelCommentRequest extends BaseRequest {

    private static final long serialVersionUID = 7986660878479864687L;


    @ExplainAnnotation(explain = "待删除评论ID")
    private String commentId;

    @ExplainAnnotation (explain = "操作者会员ID")
    private String memberId;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "DelCommentRequest{" +
                "commentId='" + commentId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
