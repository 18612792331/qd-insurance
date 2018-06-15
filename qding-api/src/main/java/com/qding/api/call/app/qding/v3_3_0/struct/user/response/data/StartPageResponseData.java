package com.qding.api.call.app.qding.v3_3_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/3.
 */
public class StartPageResponseData extends ResponseData {

	private static final long serialVersionUID = -3048088629820103021L;
	
	@ExplainAnnotation(explain = "提示信息，关闭时可以提示")
	private String tipInfo="您可能错过重要信息通知，点击开启";

	public String getTipInfo() {
		return tipInfo;
	}

	public void setTipInfo(String tipInfo) {
		this.tipInfo = tipInfo;
	}
	
	
	
     
}
