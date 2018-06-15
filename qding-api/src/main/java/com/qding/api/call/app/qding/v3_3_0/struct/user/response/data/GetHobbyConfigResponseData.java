package com.qding.api.call.app.qding.v3_3_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/3.
 */
public class GetHobbyConfigResponseData extends ResponseData {

	private static final long serialVersionUID = -3048088629820103021L;
	
	@ExplainAnnotation(explain = "通用设置----启动页开启关闭    0开启、1关闭")
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GetHobbyConfigResponseData [status=" + status + "]";
	}
	
	
	
	
     
}
