package com.qding.api.call.app.qding.v2_5_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetUserForReceiveMessageByIdReuqest extends BaseRequest  {


	private static final long serialVersionUID = 1273770087198256877L;

	public GetUserForReceiveMessageByIdReuqest() {
		
	}
	
	@ExplainAnnotation (explain = "地址id")
	@NotNullValidate
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GetUserForReceiveMessageReuqest{" +
				"id='" + id + '\'' +
				'}';
	}
}
