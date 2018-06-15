package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据社区id显示此商圈的标签，带分页，按最新发布标签排序					
 * @author lichao
 *
 */
public class GetTagByCommunityIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3637990567895035465L;

	private String communityId;
	
	private int pageNo = 1;
	
	private int pageSize = 5;
	
	public GetTagByCommunityIdRequest() {

	}

	public GetTagByCommunityIdRequest(String communityId, int pageNo,
			int pageSize) {
		super();
		this.communityId = communityId;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "GetTagByBussinessIdRequest [communityId=" + communityId
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", toString()=" + super.toString() + "]";
	}
	
}


