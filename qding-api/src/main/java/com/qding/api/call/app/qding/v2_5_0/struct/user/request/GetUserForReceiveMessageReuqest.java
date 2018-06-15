package com.qding.api.call.app.qding.v2_5_0.struct.user.request;


import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetUserForReceiveMessageReuqest extends BaseRequest  {

	
	private static final long serialVersionUID = 8495137362893495830L;

	public GetUserForReceiveMessageReuqest() {
	}
	
	@NotNullValidate
	@ExplainAnnotation (explain = "会员ID")
	private String memberId;

	@NotNullValidate
	@ExplainAnnotation (explain = "业态类型",desc = "0:通用,2:乐购,3:洗衣,4:洗车,")
	private Integer addressBusinessType;

	@ExplainAnnotation (explain = "社区ID")
	private String projectId;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Integer getAddressBusinessType() {
		return addressBusinessType;
	}
	
	public void setAddressBusinessType(Integer addressBusinessType) {
		this.addressBusinessType = addressBusinessType;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public GetUserForReceiveMessageReuqest(String memberId) {
		super();
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "GetUserForReceiveMessageReuqest{" +
				"memberId='" + memberId + '\'' +
				", addressBusinessType=" + addressBusinessType +
				", projectId='" + projectId + '\'' +
				'}';
	}
}
