package com.qding.api.call.app.qding.v3_3_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2017/3/4.
 */

@Validate
public class GetTopicPageRequest extends BaseRequest {

    private static final long serialVersionUID = -6672965910774753859L;

    @ExplainAnnotation (explain = "每页显示数")
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize;

    @ExplainAnnotation (explain = "个人空间列表被访问者userId",desc = "只针对个人空间使用")
    private String userId;
    
    @ExplainAnnotation (explain = "常态标签ID",desc = "标签聚合")
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

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

	@Override
	public String toString() {
		return "GetTopicPageRequest [pageSize=" + pageSize + 
				", userId="
				+ userId + ", tagId=" + tagId + ", orderByRule=" + orderByRule
				+ "]";
	}
    
    
    

    
     
}
