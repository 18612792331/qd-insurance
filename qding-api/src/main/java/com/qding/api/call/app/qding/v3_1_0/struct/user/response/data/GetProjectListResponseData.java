package com.qding.api.call.app.qding.v3_1_0.struct.user.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_0.struct.user.ProjectDto;
import com.qding.api.struct.ResponseData;


public class GetProjectListResponseData extends ResponseData {

    private static final long serialVersionUID = 2409085836086098452L;
    
    @ExplainAnnotation(explain = "社区列表，城市维度分组")
    private List<ProjectDto> list;
    
    @ExplainAnnotation(explain = "社区列表总数")
    private Integer totalNum;

	public List<ProjectDto> getList() {
		return list;
	}

	public void setList(List<ProjectDto> list) {
		this.list = list;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
    
    

}
