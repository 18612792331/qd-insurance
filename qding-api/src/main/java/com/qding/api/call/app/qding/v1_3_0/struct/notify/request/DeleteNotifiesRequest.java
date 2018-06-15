package com.qding.api.call.app.qding.v1_3_0.struct.notify.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class DeleteNotifiesRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4319946111251410057L;
	
	@NotNullValidate
	private String ids[];
	
	public DeleteNotifiesRequest() {

	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
}
