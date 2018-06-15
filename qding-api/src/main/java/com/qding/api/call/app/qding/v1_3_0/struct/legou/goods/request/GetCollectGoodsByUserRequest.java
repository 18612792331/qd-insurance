package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * 根据分类ID查询商品列表
 * @author jiawenzheng 
 *
 */
@Validate
public class GetCollectGoodsByUserRequest  extends BaseRequest {

	
	private static final long serialVersionUID = 5307184162593361447L;
	
	/**
     * 社区ID
     */
	@NotNullValidate
	private Long projectId;
	
	/**
     * 会员ID
     */
	@NotNullValidate
	private String memberId;
	
	/**
     * 当前查询页码
     */
	@MinValueValidate(value="1")
	private Integer pageNo=1;
	
	/**
     * 每页查询显示数
     */
	@RangeValueValidate(max="20", min="1")
	private Integer pageSize=10;
	
	/**
	 * 获取点赞神数
	 */
	private boolean findClickLike = true;
	
	
	public boolean getFindClickLike() {
		return findClickLike;
	}

	public void setFindClickLike(boolean findClickLike) {
		this.findClickLike = findClickLike;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public GetCollectGoodsByUserRequest(Long projectId, String memberId,
			Integer pageNo, Integer pageSize) {
		super();
		this.projectId = projectId;
		this.memberId = memberId;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public GetCollectGoodsByUserRequest(){
		
	}
	
	@Override
    public String toString() {
        return "GetCollectGoodsByUserRequest [memberId=" + memberId
                + ", projectId=" + projectId + ",pageNo="+pageNo+" ,pageSize ="+pageSize+" , toString()=" + super.toString() + "]";
    }

}
