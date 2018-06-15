package com.qding.api.call.app.qding.v3_0_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/2/23.
 */
public class MyHouseKeeperResponseData extends ResponseData {

    private static final long serialVersionUID = 5490879723819004114L;

    @ExplainAnnotation(explain = "管家姓名")
    private String name;
    
    @ExplainAnnotation(explain = "管家电话")
    private String mobile;
    
    @ExplainAnnotation(explain = "管家头像")
    private String headImage;
    
    @ExplainAnnotation(explain = "200 正常存在管家  201 未绑定，202 异常情况或不存在管家")
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
		return "MyHouseKeeperResponseData [name=" + name + ", mobile=" + mobile
				+ ", headImage=" + headImage + "]";
	}

    
    
    
    
    
}
