package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class SearchTagRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4036045887289238679L;

	private String keyword;
	
	private Integer pageNo = 1;
	
	private Integer pageSize = 5;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	
	public SearchTagRequest() {

	}

	@Override
	public String toString() {
		return "SearchTagRequest [keyword=" + keyword + ", pageNo=" + pageNo
				+ ", pageSize=" + pageSize + ", toString()=" + super.toString()
				+ "]";
	}

	
}
