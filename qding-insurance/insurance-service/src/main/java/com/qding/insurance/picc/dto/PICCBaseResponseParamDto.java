package com.qding.insurance.picc.dto;

/**
 * PICC接口返回通用参数封装类
 * @author zhangxiaojun
 *
 */
public class PICCBaseResponseParamDto {
	/**接口请求结果状态码*/
	private String respCode;
	/**接口请求结果描述*/
	private String respMsg;
	
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
}
