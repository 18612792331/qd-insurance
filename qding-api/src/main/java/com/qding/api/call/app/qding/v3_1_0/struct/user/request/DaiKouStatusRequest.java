package com.qding.api.call.app.qding.v3_1_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

public class DaiKouStatusRequest extends BaseRequest {

    private static final long serialVersionUID = -7119413715411419655L;
    
    @ExplainAnnotation (explain = "物业费代扣状态",desc = "1:开通，0：关闭")
    @NotNullValidate
    private Integer payStatus;
    
    @ExplainAnnotation (explain = "开通验证码")
    private String checkCode;

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
    
    
    

}
