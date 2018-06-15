package com.qding.insurance.picc.bean;

/**
 * PICC接口调用返回xml子节点GeneralInfoReturn
 * @author zhangxiaojun
 *
 */
public class GeneralInfoReturn {
	/** 公司名称或缩写（拼音或英文）+yyyyMMddhhmmssyyy	（报文唯一性约束）*/
	private String uuid;
	/**平台项目标识*/
	private String plateformCode;
	/**xml通用属性处理结果 "00"表示校验成功*/
	private String errorCode;
	/**处理结果描述*/
	private String errorMessage;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPlateformCode() {
		return plateformCode;
	}

	public void setPlateformCode(String plateformCode) {
		this.plateformCode = plateformCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
