package com.qding.api.call.app.qding.v3_3_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2017/3/4.
 */

@Validate
public class GetNewsPageRequest extends BaseRequest {

    private static final long serialVersionUID = -6672965910774753859L;


    @ExplainAnnotation (explain = "每页显示数")
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize;

    @ExplainAnnotation (explain = "新闻分类")
    private Integer newsType;

    @ExplainAnnotation(explain = "排序规则字段")
    private String orderByRule;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getNewsType() {
		return newsType;
	}

	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}

	public String getOrderByRule() {
		return orderByRule;
	}

	public void setOrderByRule(String orderByRule) {
		this.orderByRule = orderByRule;
	}

	@Override
	public String toString() {
		return "GetNewsPageRequest [pageSize=" + pageSize + ", newsType="
				+ newsType + ", orderByRule=" + orderByRule + "]";
	}

    

    
    
    
}
