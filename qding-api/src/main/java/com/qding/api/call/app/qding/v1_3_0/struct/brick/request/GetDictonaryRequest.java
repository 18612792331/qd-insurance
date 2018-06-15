package com.qding.api.call.app.qding.v1_3_0.struct.brick.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;

/**
 * 获取app字典配置		
 * @author lichao
 *
 */
@Validate
public class GetDictonaryRequest extends BaseRequest{

	private static final long serialVersionUID = -4226231756305228693L;

	public GetDictonaryRequest() {

	}

	@Override
	public String toString() {
		return "GetDictonaryRequest [toString()=" + super.toString() + "]";
	}
	
	
}
