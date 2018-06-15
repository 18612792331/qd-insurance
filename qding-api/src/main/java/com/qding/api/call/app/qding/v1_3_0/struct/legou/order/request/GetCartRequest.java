package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 我的购物车列表					
 * @author lichao
 *
 */
@Validate
public class GetCartRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4941087106932894888L;

	@NotNullValidate
	private String memberId;
	
	@NotNullValidate
	private String projectId;
	
	public GetCartRequest() {

	}

	public GetCartRequest(String memberId, String projectId) {
		super();
		this.memberId = memberId;
		this.projectId = projectId;
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

	@Override
	public String toString() {
		return "GetCartRequest [memberId=" + memberId + ", projectId="
				+ projectId + ", toString()=" + super.toString() + "]";
	}
	
	
}
