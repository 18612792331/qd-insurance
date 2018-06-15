package com.qding.api.call.app.qding.v3_0_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.ProjectService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/22.
 */
public class ServiceGoodBean implements Serializable {

    @ExplainAnnotation(explain = "总记录数")
    private Long totalCount;

    @ExplainAnnotation(explain = "业态列表")
    private List<ProjectService> list;

    public List<ProjectService> getList() {
        return list;
    }

    public void setList(List<ProjectService> list) {
        this.list = list;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
