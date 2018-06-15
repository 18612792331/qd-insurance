package com.qding.api.call.app.qding.v3_3_0.struct.realestate.request;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class AddRoomEvaluateRequest extends BaseRequest {

    private static final long serialVersionUID = 1699350228064434666L;
    
    @ExplainAnnotation (explain = "评价主体Id")
    private String bodyId;
    
    @ExplainAnnotation (explain = "评价主体类型  0 签约，1 入住")
	private String bodyType;
    
    @ExplainAnnotation (explain = "评价星级")
    private int starLevel;
    
    @ExplainAnnotation (explain = "评价标签id")
    private List<String> lableIds;
    
    @ExplainAnnotation (explain = "评价标签内容")
    private String content;
    
	public String getBodyId() {
		return bodyId;
	}

	public void setBodyId(String bodyId) {
		this.bodyId = bodyId;
	}

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public List<String> getLableIds() {
		return lableIds;
	}

	public void setLableIds(List<String> lableIds) {
		this.lableIds = lableIds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	@Override
	public String toString() {
		return "AddRoomEvaluateRequest [starLevel=" + starLevel + ", lableId="
				+ lableIds + ", content=" + content + "]";
	}

	
    
    
    
    
     
}
