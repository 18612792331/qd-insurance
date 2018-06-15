package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2017/3/4.
 */

@Validate
public class GetTopicFlowRequest extends BaseRequest {

    private static final long serialVersionUID = -6672965910774753859L;


    @ExplainAnnotation (explain = "每页显示数")
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize;

    @ExplainAnnotation (explain = "查询类型",desc = "1: 跟帖列表，2:邻里互动，3：邻里互动标签聚合页，4：我(他)个人空间帖子列表")
    @NotNullValidate
    private Integer flowType;

    @ExplainAnnotation (explain = "个人空间列表被访问者userId",desc = "只针对个人空间使用")
    private String userId;

    @ExplainAnnotation (explain = "排序类型",desc = "1:最新，2：最热，3:本社区，4：我发的")
    @NotNullValidate
    private Integer queryType;

    @ExplainAnnotation (explain = "父ID",desc = "只针对话题跟帖，活动跟帖")
    private String parentTopicId;

    @ExplainAnnotation (explain = "常态标签ID",desc = "针对邻里互动，指定标签聚合页")
    private String tagId;

    @ExplainAnnotation(explain = "排序规则字段")
    private String orderByRule;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderByRule() {
        return orderByRule;
    }

    public void setOrderByRule(String orderByRule) {
        this.orderByRule = orderByRule;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getParentTopicId() {
        return parentTopicId;
    }

    public void setParentTopicId(String parentTopicId) {
        this.parentTopicId = parentTopicId;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    
    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    @Override
    public String toString() {
        return "GetTopicFlowRequest{" +
                "pageSize=" + pageSize +
                ", flowType=" + flowType +
                ", queryType=" + queryType +
                ", parentTopicId='" + parentTopicId + '\'' +
                ", tagId='" + tagId + '\'' +
                ", orderByRule='" + orderByRule + '\'' +
                '}';
    }
}
