package com.qding.api.call.app.qding.v3_3_0.struct.neighbor.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.FavoriteDto;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/4.
 */
public class GetMyFavoriteResponseData extends ResponseData {

    private static final long serialVersionUID = 6843397143075031178L;

    @ExplainAnnotation (explain = "收藏帖子列表")
    private List<FavoriteDto> list;


	@ExplainAnnotation(explain = "是否有下一页")
	private boolean haveNextPage;

	@ExplainAnnotation(explain = "排序规则字段")
	private String orderByRule;


	public List<FavoriteDto> getList() {
		return list;
	}

	public void setList(List<FavoriteDto> list) {
		this.list = list;
	}

	public boolean isHaveNextPage() {
		return haveNextPage;
	}

	public void setHaveNextPage(boolean haveNextPage) {
		this.haveNextPage = haveNextPage;
	}

	public String getOrderByRule() {
		return orderByRule;
	}

	public void setOrderByRule(String orderByRule) {
		this.orderByRule = orderByRule;
	}

	@Override
	public String toString() {
		return "GetMyFavoriteResponseData{" +
				"list=" + list +
				", haveNextPage=" + haveNextPage +
				", orderByRule='" + orderByRule + '\'' +
				'}';
	}
}
