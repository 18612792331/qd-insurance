package com.qding.api.call.service.dto;

import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.ProjectService;

import java.util.List;

/**
 * Created by qd on 2017/9/13.
 */
public class SearchProjectServiceItemDTO {

    /**
     * 搜索出总数
     */
   private Integer totalCount;

    /**
     * 搜索出社区服务项
     */
    private  List<ProjectService> list;


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<ProjectService> getList() {
        return list;
    }

    public void setList(List<ProjectService> list) {
        this.list = list;
    }
}
