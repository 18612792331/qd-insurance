package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2017/2/24.
 */
public class GetActivityPiazzaIndexRequest extends BaseRequest {

    private static final long serialVersionUID = 6623576729803277397L;


    @ExplainAnnotation(explain = "查询类型" ,desc = "1:活动广场,2:我参与的活动")
    @NotNullValidate
    private Integer queryType;

    @ExplainAnnotation (explain = "每页显示数")
    @RangeValueValidate(max="100", min="1")
    private Integer pageSize = Integer.valueOf(100);

    @ExplainAnnotation(explain = "排序规则字段")
    private String orderByRule;

    @ExplainAnnotation(explain = "被访问用户账户ID",desc = "只针对个人中心")
    private String userId;

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

    public String getOrderByRule() {
        return orderByRule;
    }

    public void setOrderByRule(String orderByRule) {
        this.orderByRule = orderByRule;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GetActivityPiazzaIndexRequest{" +
                "queryType=" + queryType +
                ", pageSize=" + pageSize +
                ", orderByRule='" + orderByRule + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
