package com.qding.api.call.app.qding.v2_1_0.struct.hotcycle.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_1_0.struct.hotcycle.ProjectGcRoomDTO;
import com.qding.api.struct.ResponseData;

import java.util.List;


public class ShareHotcycleHomePageResponseData extends ResponseData {

    private static final long serialVersionUID = 8722040248346951635L;

    @ExplainAnnotation(explain = "社区列表")
    private List<ProjectGcRoomDTO> list;

    @ExplainAnnotation(explain = "总记录数")
    private Long totalCount;

    public List<ProjectGcRoomDTO> getList() {
        return list;
    }

    public void setList(List<ProjectGcRoomDTO> list) {
        this.list = list;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "ShareHotcycleHomePageResponseData{" +
                "list=" + list +
                ", totalCount=" + totalCount +
                '}';
    }
}
