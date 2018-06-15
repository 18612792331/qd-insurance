package com.qding.api.call.app.qding.v3_1_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;


/**
 * Created by qd on 2017/3/2.
 */
@Validate
public class ListBkPageRequest extends BaseRequest  {

    private static final long serialVersionUID = -3535380267779528496L;
    
    @ExplainAnnotation(explain = "标签Id",desc="特殊处理，如果是 1最新、2最热,该字段标示两个含义")
    @NotNullValidate
    private String lableId;
    
    @ExplainAnnotation(explain = "每页显示数")
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize = Integer.valueOf(20);

    @ExplainAnnotation (explain = "当前请求页码")
    @MinValueValidate(value="1")
    private Integer pageNo;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

	public String getLableId() {
		return lableId;
	}

	public void setLableId(String lableId) {
		this.lableId = lableId;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
    
    
    
    
    
    
}
