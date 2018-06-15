package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2016/9/19.
 */
@Validate
public class DianZanRequest  extends BaseRequest {

    private static final long serialVersionUID = 7719067168274759990L;

    @ExplainAnnotation(explain = "话题ID")
    @NotNullValidate
    private String topicId;

    @ExplainAnnotation (explain = "每页显示数")
    @RangeValueValidate(max="20", min="1")
    private Integer size;
    
    @ExplainAnnotation (explain = "分页分割时间")
  	private Long cursorTime;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Long getCursorTime() {
		return cursorTime;
	}

	public void setCursorTime(Long cursorTime) {
		this.cursorTime = cursorTime;
	}
    
    
    

     
}
