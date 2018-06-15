package com.qding.api.call.app.qding.v2_3_0.struct.legou.goods.request;

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
public class GetGoodsBySortRequest extends BaseRequest {

	
	private static final long serialVersionUID = 5307184162593361447L;
	
	/**
     * 社区ID
     */
	@NotNullValidate
	private Long projectId;


	/**
     * 品类ID
     */
	@NotNullValidate
	private String revealCategoryId;
	
	/**
     * 当前页码
     */
	@MinValueValidate(value="1")
	private Integer pageNo = 1;
	
	/**
     * 每页查询显示数
     */
	@RangeValueValidate(max="20", min="1")
	private Integer pageSize = 10;
	
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


	/**
     * @return Returns the revealCategoryId.
     */
    public String getRevealCategoryId() {
        return revealCategoryId;
    }

    /**
     * @param revealCategoryId The revealCategoryId to set.
     */
    public void setRevealCategoryId(String revealCategoryId) {
        this.revealCategoryId = revealCategoryId;
    }

    /**
	 * @return the pageNo
	 */
	public Integer getPageNo() {
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public GetGoodsBySortRequest(){

	}

	@Override
    public String toString() {
        return "GetGoodsBySortRequest [projectId="+projectId+",revealCategoryId=" + revealCategoryId
                + ", pageNo=" + pageNo + ",pageSize="+pageSize+" ,findClickLike ="+findClickLike+" , toString()=" + super.toString() + "]";
    }
}
