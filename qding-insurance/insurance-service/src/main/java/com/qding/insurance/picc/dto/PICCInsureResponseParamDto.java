package com.qding.insurance.picc.dto;

import com.qding.insurance.picc.bean.ReturnInfo;


/**
 * webservice调用PICC投保接口返回参数封装类
 * @author zhangxiaojun
 *
 */
public class PICCInsureResponseParamDto extends PICCBaseResponseParamDto{
	/** 返回消息体 ReturnInfo节点 */
	private ReturnInfo returnInfo;

	public ReturnInfo getReturnInfo() {
		return returnInfo;
	}

	public void setReturnInfo(ReturnInfo returnInfo) {
		this.returnInfo = returnInfo;
	}

}
