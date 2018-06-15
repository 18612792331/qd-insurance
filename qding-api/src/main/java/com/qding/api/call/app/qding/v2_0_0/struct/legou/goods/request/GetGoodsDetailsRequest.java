package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 获取商品详情
 * @author jiawenzheng 
 *
 */
@Validate
public class GetGoodsDetailsRequest extends BaseRequest {

	
	private static final long serialVersionUID = 1421553712752987858L;
	
	@ExplainAnnotation(explain = "社区ID")
	private Long projectId;
	
	@NotNullValidate
	@ExplainAnnotation(explain = "货品ID")
	private Long skuId;
	
	@ExplainAnnotation(explain = "当前会员ID")
	private String memberId;
	
	@ExplainAnnotation(explain = "是否查询购买量",desc = "需要:true,不需要:false")
	private boolean findSellNum = true;
	
	@ExplainAnnotation(explain = "是否查询收藏数",desc = "需要:true,不需要:false")
	private boolean findClickLike = true;
	
	@ExplainAnnotation(explain = "是否查询库存数",desc = "需要:true,不需要:false")
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
