package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.request;

import java.util.Arrays;
import java.util.Set;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 通过商品ID数组获取商品列表
 * @author jiawenzheng 
 *
 */
@Validate
public class GetGoodsByGoodIdArrayRequest  extends BaseRequest {

	private static final long serialVersionUID = 886999065031686265L;

	/**
     * 货品ID数组
     */
	@NotNullValidate
	private Long[] skuIds;


	/**
	 * 社区ID
	 */
	@NotNullValidate
	private Long projectId;
	
	/**
     * 销售平台类型  单词写错了 sell修改为sale-->
     */
	@NotNullValidate
	private String sellPlatform;
	
	/**
     * 是否获取商品状态
     */
	private boolean findAllStatus=true;
	
	/**
     * 是否获取点赞数
     */
	private boolean findClickLike=true;
	
	
	/**
	 * @return the findAllStatus
	 */
	public boolean getFindAllStatus() {
		return findAllStatus;
	}


	@ExplainAnnotation(explain = "查询排序类型",desc = "1:最新上架时间,2:销量最好(总销量（实际销量+虚拟销量）desc, 实际销量 desc, 虚拟销量 desc),3:价格(销售价格 asc),4:价格(销售价格 desc)")
	private Integer sortType =0;



	/**
	 * @param findAllStatus the findAllStatus to set
	 */
	public void setFindAllStatus(boolean findAllStatus) {
		this.findAllStatus = findAllStatus;
	}



	public Long[] getSkuIds() {
		return skuIds;
	}



	/**
	 * @param skuIds the skuIds to set
	 */
	public void setSkuIds(Long[] skuIds) {
		this.skuIds = skuIds;
	}


	public String getSellPlatform() {
		return sellPlatform;
	}



	public void setSellPlatform(String sellPlatform) {
		this.sellPlatform = sellPlatform;
	}



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

	public Integer getSortType() {
		return sortType;
	}

	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}

	public GetGoodsByGoodIdArrayRequest(){
		
	}


	@Override
	public String toString() {
		return "GetGoodsByGoodIdArrayRequest{" +
				"skuIds=" + Arrays.toString(skuIds) +
				", projectId=" + projectId +
				", sellPlatform='" + sellPlatform + '\'' +
				", findAllStatus=" + findAllStatus +
				", findClickLike=" + findClickLike +
				", sortType=" + sortType +
				'}';
	}
}
