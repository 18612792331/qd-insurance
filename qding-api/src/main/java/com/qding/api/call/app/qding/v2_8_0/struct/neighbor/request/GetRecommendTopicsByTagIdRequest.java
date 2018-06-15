package com.qding.api.call.app.qding.v2_8_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2016/11/22.
 */
@Validate
public class GetRecommendTopicsByTagIdRequest extends BaseRequest {

    private static final long serialVersionUID = -5731154482991466512L;

    @NotNullValidate
    @ExplainAnnotation(explain = "主题ID")
    private String themeId;

    @NotNullValidate
    @ExplainAnnotation(explain = "活动标签ID")
    private String tagId;

    @ExplainAnnotation(explain = "当前请求页码")
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

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    @Override
    public String toString() {
        return "GetRecommendTopicsByTagIdRequest{" +
                "themeId='" + themeId + '\'' +
                ", tagId='" + tagId + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", showCommentSize=" + showCommentSize +
                ", showPraiseSize=" + showPraiseSize +
                '}';
    }
}
