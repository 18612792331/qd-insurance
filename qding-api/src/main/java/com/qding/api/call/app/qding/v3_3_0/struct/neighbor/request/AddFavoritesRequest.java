package com.qding.api.call.app.qding.v3_3_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class AddFavoritesRequest extends BaseRequest {

    private static final long serialVersionUID = 1699350228064434666L;

    @ExplainAnnotation (explain = "话题ID")
    @NotNullValidate
    private String topicId;

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	@Override
	public String toString() {
		return "GetTopicDetailRequest [topicId=" + topicId + "]";
	}

    
    
	
	
    
     
}
