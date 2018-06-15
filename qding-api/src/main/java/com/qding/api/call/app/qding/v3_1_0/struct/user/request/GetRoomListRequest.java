package com.qding.api.call.app.qding.v3_1_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetRoomListRequest extends BaseRequest {

    private static final long serialVersionUID = -7119413715411419655L;
    
    @ExplainAnnotation(explain = "页码")
	private Integer pageNo;
    
    @ExplainAnnotation(explain = "每页数据")
    private Integer pageSize;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
    
    

}
