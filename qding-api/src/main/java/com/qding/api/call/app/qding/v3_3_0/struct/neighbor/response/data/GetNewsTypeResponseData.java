package com.qding.api.call.app.qding.v3_3_0.struct.neighbor.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.NewsType;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/4.
 */
public class GetNewsTypeResponseData extends ResponseData{

    private static final long serialVersionUID = -6726551281024575314L;

    @ExplainAnnotation (explain = "新闻分类")
    private List<NewsType> list;

	public List<NewsType> getList() {
		return list;
	}

	public void setList(List<NewsType> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetNewsTypeResponseData [list=" + list + "]";
	}

    
    
    
    
    
}
