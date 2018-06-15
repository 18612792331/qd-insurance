package com.qding.api.call.app.qding.v3_3_0.struct.neighbor.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefNewsInfo;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/4.
 */
public class GetNewsPageResponseData extends ResponseData {

    private static final long serialVersionUID = 6843397143075031178L;

    @ExplainAnnotation (explain = "新闻帖子列表")
    private List<BriefNewsInfo> list;

    @ExplainAnnotation(explain = "排序规则字段")
    private String orderByRule;

    @ExplainAnnotation(explain = "是否有下一页")
    private boolean haveNextPage;

    public String getOrderByRule() {
        return orderByRule;
    }

    public void setOrderByRule(String orderByRule) {
        this.orderByRule = orderByRule;
    }

    public List<BriefNewsInfo> getList() {
		return list;
	}

	public void setList(List<BriefNewsInfo> list) {
		this.list = list;
	}

	public boolean isHaveNextPage() {
        return haveNextPage;
    }

    public void setHaveNextPage(boolean haveNextPage) {
        this.haveNextPage = haveNextPage;
    }

	@Override
	public String toString() {
		return "GetNewsPageResponseData [list=" + list + ", orderByRule="
				+ orderByRule + ", haveNextPage=" + haveNextPage + "]";
	}

     
    
    
    
    
    
}
