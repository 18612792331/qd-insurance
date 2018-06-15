package com.qding.api.call.app.qding.v3_1_0.struct.user.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_0.struct.user.RoomGroupDto;
import com.qding.api.struct.ResponseData;


public class GetRoomListResponseData extends ResponseData {

    private static final long serialVersionUID = 2409085836086098452L;
    
    @ExplainAnnotation(explain = "按照社区分组的房屋列表，实际有效的数据")
    private List<RoomGroupDto> list;
    
    @ExplainAnnotation(explain = "当前分页数据")
    private Integer pageSize;
    
    @ExplainAnnotation(explain = "房屋列表总数")
    private Integer totalNum;

	public List<RoomGroupDto> getList() {
		return list;
	}

	public void setList(List<RoomGroupDto> list) {
		this.list = list;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	

	
    
    

}
