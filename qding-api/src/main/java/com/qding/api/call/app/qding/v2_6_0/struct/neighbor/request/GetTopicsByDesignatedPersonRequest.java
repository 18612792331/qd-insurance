package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;


/**
 * Created by qd on 2016/9/12.
 */
@Validate
public class GetTopicsByDesignatedPersonRequest extends BaseRequest  {

    private static final long serialVersionUID = 4242010127780326521L;

    @ExplainAnnotation (explain = "被访问用户账户ID")
    @NotNullValidate
    private String userId;

    @ExplainAnnotation (explain = "当前登陆者会员ID")
    @NotNullValidate
    private String memberId;

    @ExplainAnnotation (explain = "当前登陆者会员所在社区ID")
    @NotNullValidate
    private String projectId;

    @ExplainAnnotation ( explain = "查询类型",desc = "1:全部，2：参与的报名|投票")
    @NotNullValidate
    private Integer type;

    @ExplainAnnotation (explain = "当前请求页码")
    @MinValueValidate(value="1")
    private Integer pageNo;

    @ExplainAnnotation (explain = "每页显示数")
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize;

    @ExplainAnnotation (explain = "默认显示评论数")
    @NotNullValidate
    private Integer showCommentSize = Integer.valueOf(0);

    @ExplainAnnotation (explain = "默认显示点赞人员列表数")
    @NotNullValidate
    private Integer showPraiseSize= Integer.valueOf(0);

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GetTopicsByDesignatedPersonRequest{" +
                "userId='" + userId + '\'' +
                ", type=" + type +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", showCommentSize=" + showCommentSize +
                ", showPraiseSize=" + showPraiseSize +
                '}';
    }
}
