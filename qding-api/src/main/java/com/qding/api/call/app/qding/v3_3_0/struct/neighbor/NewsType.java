package com.qding.api.call.app.qding.v3_3_0.struct.neighbor;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

/**
 * Created by qd on 2016/11/22.
 */
public class NewsType  implements Serializable {

    private static final long serialVersionUID = 1911087656742581664L;

    @ExplainAnnotation(explain = "新闻分类ID")
    private String typeId;

    @ExplainAnnotation (explain = "新闻分类名称")
    private String typeName;
    
    public NewsType() {
	}

	public NewsType(String typeId, String typeName) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

    
    
    
    
}
