package com.qding.api.call.app.qding.v3_0_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/2/23.
 */
public class HouseDeliverResponseData extends ResponseData {

    private static final long serialVersionUID = 5490879723819004114L;

    @ExplainAnnotation(explain = "交房时间",desc="时间格式：yyyy-mm-dd")
    private String date;
    
    @ExplainAnnotation(explain = "预约交房链接")
    private String submitUrl;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSubmitUrl() {
		return submitUrl;
	}

	public void setSubmitUrl(String submitUrl) {
		this.submitUrl = submitUrl;
	}

	@Override
	public String toString() {
		return "HouseDeliverResponseData [date=" + date + ", submitUrl="
				+ submitUrl + "]";
	}
    
    
    
    
}
