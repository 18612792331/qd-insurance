package com.qding.api.call.app.qding.v2_0_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.BoardTmplBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/1/18.
 */
public class GetRecommendBoardBySectionIdAndProjectIdResponseData extends ResponseData {

    private static final long serialVersionUID = 5655427269438716667L;

    @ExplainAnnotation(explain = "板块数据列表")
    private List<BoardTmplBean> list;

    @ExplainAnnotation(explain = "总记录数")
    private Long totalCount =0L;

    public List<BoardTmplBean> getList() {
        return list;
    }

    public void setList(List<BoardTmplBean> list) {
        this.list = list;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }
}
