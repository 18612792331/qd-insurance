package com.qding.api.call.app.qding.v2_4_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
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


	private static final long serialVersionUID = -4941087106932894888L;

	@NotNullValidate
	@ExplainAnnotation(explain = "会员ID")
	private String memberId;
	
	@NotNullValidate
	@ExplainAnnotation(explain = "社区ID")
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
