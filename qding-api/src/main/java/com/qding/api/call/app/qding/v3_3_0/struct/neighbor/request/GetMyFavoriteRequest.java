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
public class GetMyFavoriteRequest extends BaseRequest {

    private static final long serialVersionUID = -6672965910774753859L;


    @ExplainAnnotation (explain = "每页显示数")
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize;

    @ExplainAnnotation (explain = "1 百科，2 新闻")
    @NotNullValidate
    private Integer favoriteType;

	@ExplainAnnotation(explain = "排序规则字段")
	private String orderByRule;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	

	public Integer getFavoriteType() {
		return favoriteType;
	}

	public void setFavoriteType(Integer favoriteType) {
		this.favoriteType = favoriteType;
	}

	public String getOrderByRule() {
		return orderByRule;
	}

	public void setOrderByRule(String orderByRule) {
		this.orderByRule = orderByRule;
	}

	@Override
	public String toString() {
		return "GetMyFavoriteRequest{" +
				"pageSize=" + pageSize +
				", favoriteType=" + favoriteType +
				", orderByRule='" + orderByRule + '\'' +
				'}';
	}
}
