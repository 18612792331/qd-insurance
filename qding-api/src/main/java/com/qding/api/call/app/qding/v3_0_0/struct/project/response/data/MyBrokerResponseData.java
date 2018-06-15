package com.qding.api.call.app.qding.v3_0_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * 社区经纪人.
 */
public class MyBrokerResponseData extends ResponseData {

    private static final long serialVersionUID = 5490879723819004114L;

    @ExplainAnnotation(explain = "姓名")
    private String name;
    
    @ExplainAnnotation(explain = "电话")
    private String mobile;
    
    @ExplainAnnotation(explain = "头像")
    private String headImage;
    
    @ExplainAnnotation(explain = "200 存在经纪人 404 不存在经纪人")
    private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MyBrokerResponseData [name=" + name + ", mobile=" + mobile
				+ ", headImage=" + headImage + "]";
	}

    
    
    
    
    
}
