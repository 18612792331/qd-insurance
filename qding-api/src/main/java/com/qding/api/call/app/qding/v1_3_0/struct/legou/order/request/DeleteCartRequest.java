package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request;

import java.util.Arrays;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 删除购物车					
 * @author lichao
 *
 */
@Validate
public class DeleteCartRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3603255876585844128L;

	@NotNullValidate
	private String memberId;
	
	@NotNullValidate
	private String projectId;
	
	@NotNullValidate
	private String[] skuIds;
	
	
	public DeleteCartRequest() {

	}


	public DeleteCartRequest(String memberId, String projectId, String[] skuIds) {
		super();
		this.memberId = memberId;
		this.projectId = projectId;
		this.skuIds = skuIds;
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


	public String[] getSkuIds() {
		return skuIds;
	}


	public void setSkuIds(String[] skuIds) {
		this.skuIds = skuIds;
	}


	@Override
	public String toString() {
		return "DeleteCartRequest [memberId=" + memberId + ", projectId="
				+ projectId + ", skuIds=" + Arrays.toString(skuIds)
				+ ", toString()=" + super.toString() + "]";
	}

}
