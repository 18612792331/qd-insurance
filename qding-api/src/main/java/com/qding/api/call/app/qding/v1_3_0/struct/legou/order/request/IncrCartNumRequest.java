package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request;

import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.smart.validate.rule.ProjectValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 修改购物车数量					
 * @author lichao
 *
 */
@Validate
public class IncrCartNumRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3603255876585844128L;

	@MemberValidate
	private String memberId;
	
	@ProjectValidate
	private String projectId;
	
	@NotNullValidate
	private String skuId;
	
	private Integer incrementCount = 0;
	
	public IncrCartNumRequest() {

	}

	public IncrCartNumRequest(String memberId, String projectId, String skuId,
			Integer incrementCount) {
		super();
		this.memberId = memberId;
		this.projectId = projectId;
		this.skuId = skuId;
		this.incrementCount = incrementCount;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Integer getIncrementCount() {
		return incrementCount;
	}

	public void setIncrementCount(Integer incrementCount) {
		this.incrementCount = incrementCount;
	}

	@Override
	public String toString() {
		return "IncrCartNumRequest [memberId=" + memberId + ", projectId="
				+ projectId + ", skuId=" + skuId + ", incrementCount="
				+ incrementCount + ", toString()=" + super.toString() + "]";
	}
}
