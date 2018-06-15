package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2016/9/9.
 */
@Validate
public class GetTopicsByThemeIdRequest extends BaseRequest {

    private static final long serialVersionUID = -7087555633481015323L;

    @ExplainAnnotation(explain = "主题ID")
    @NotNullValidate
    private String themeId;

    @ExplainAnnotation(explain = "会员ID")
    @NotNullValidate
    private String memberId;

    @ExplainAnnotation(explain = "社区ID")
    @NotNullValidate
    private String projectId;

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

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "GetTopicsByThemeIdRequest{" +
                "themeId='" + themeId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", showCommentSize=" + showCommentSize +
                ", showPraiseSize=" + showPraiseSize +
                '}';
    }
}
