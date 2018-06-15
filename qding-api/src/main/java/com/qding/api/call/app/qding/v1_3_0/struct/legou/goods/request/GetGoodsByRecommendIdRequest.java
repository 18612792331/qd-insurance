package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * 获取乐购首页推荐
 * @author jiawenzheng 
 *
 */
@Validate
public class GetGoodsByRecommendIdRequest  extends BaseRequest {

	
	private static final long serialVersionUID = -2079775475193000303L;
	
	/**
	 * 推荐类型ID
	 */
	@NotNullValidate
	private String recommendId;

	/**
	 * 社区ID
	 */
	@NotNullValidate
	private Long projectId;
	
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
	 * 临时兼容IOS写错参数的问题
	 */
	private Integer page;

	/**
	 * 临时兼容IOS写错参数的问题
	 */
	private Integer size;
	
	/**
	 * 获取点赞神数
	 */
	private boolean findClickLike = true;


	@ExplainAnnotation(explain = "查询排序类型",desc = "1:最新上架时间,2:销量最好(总销量（实际销量+虚拟销量）desc, 实际销量 desc, 虚拟销量 desc),3:价格(销售价格 asc),4:价格(销售价格 desc)")
	private Integer sortType;

	public Integer getSortType() {
		return sortType;
	}

	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}

	public boolean getFindClickLike() {
		return findClickLike;
	}

	public void setFindClickLike(boolean findClickLike) {
		this.findClickLike = findClickLike;
	}

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public GetGoodsByRecommendIdRequest(){
		
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "GetGoodsByRecommendIdRequest{" +
				"recommendId='" + recommendId + '\'' +
				", projectId=" + projectId +
				", pageNo=" + pageNo +
				", pageSize=" + pageSize +
				", page=" + page +
				", size=" + size +
				", findClickLike=" + findClickLike +
				", sortType=" + sortType +
				'}';
	}
}
