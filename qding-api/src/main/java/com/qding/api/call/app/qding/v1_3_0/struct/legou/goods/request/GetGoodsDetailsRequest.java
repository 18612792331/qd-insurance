package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.request;

import java.util.Set;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 获取商品详情
 * @author jiawenzheng 
 *
 */
@Validate
public class GetGoodsDetailsRequest  extends BaseRequest {

	
	private static final long serialVersionUID = 1421553712752987858L;
	
	/**
     *社区ID
     */
//	@NotNullValidate
	private Long projectId;
	
	/**
     * 货品ID
     */
	@NotNullValidate
	private Long skuId;
	
	/**
     * 会员ID
     */
	private String memberId;
	

	
	/**
	 * 购买量
	 */
	private boolean findSellNum = true;
	
	/**
	 * 获取点赞神数
	 */
	private boolean findClickLike = true;
	
	/**
	 * 获取库存数
	 */
	private boolean findSkuStock = true;
	
	
	
	public boolean getFindSkuStock() {
		return findSkuStock;
	}

	public void setFindSkuStock(boolean findSkuStock) {
		this.findSkuStock = findSkuStock;
	}


	public boolean getFindClickLike() {
		return findClickLike;
	}

	public void setFindClickLike(boolean findClickLike) {
		this.findClickLike = findClickLike;
	}

	
	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}




	/**
	 * @return the skuId
	 */
	public Long getSkuId() {
		return skuId;
	}

	/**
	 * @param skuId the skuId to set
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}


	/**
	 * @return the findSellNum
	 */
	public boolean getFindSellNum() {
		return findSellNum;
	}

	/**
	 * @param findSellNum the findSellNum to set
	 */
	public void setFindSellNum(boolean findSellNum) {
		this.findSellNum = findSellNum;
	}

	public GetGoodsDetailsRequest(){
		
	}
	
	@Override
	public String toString() {
		 return "GetGoodsDetailsRequest [projectId=" + projectId
				+ ",skuIds="+skuId+",memberId="+memberId+",findSellNum="+findSellNum+","
				        + "findSkuStock="+findSkuStock+", toString()=" + super.toString() + "]";
	}
	

}
