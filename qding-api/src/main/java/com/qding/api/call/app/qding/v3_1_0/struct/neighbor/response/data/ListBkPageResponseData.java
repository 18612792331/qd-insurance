package com.qding.api.call.app.qding.v3_1_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.BriefEncyclopedia;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/3/2.
 */
public class ListBkPageResponseData extends ResponseData {

    private static final long serialVersionUID = -456833869765190357L;

    @ExplainAnnotation (explain = "百科列表")
    private List<BriefEncyclopedia> encyclopediaList;

    @ExplainAnnotation(explain = "总记录数")
    private Integer totalCount;

    public List<BriefEncyclopedia> getEncyclopediaList() {
        return encyclopediaList;
    }

    public void setEncyclopediaList(List<BriefEncyclopedia> encyclopediaList) {
        this.encyclopediaList = encyclopediaList;
    }

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

    
     
    
}
