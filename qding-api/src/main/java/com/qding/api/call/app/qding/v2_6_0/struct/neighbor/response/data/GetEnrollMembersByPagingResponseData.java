package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefMember;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/9/19.
 */
public class GetEnrollMembersByPagingResponseData extends ResponseData {

    private static final long serialVersionUID = -3921403925395359121L;

    @ExplainAnnotation(explain = "已报名用户列表")
    private List<BriefMember> list;

    @ExplainAnnotation(explain = "已报名用户总数")
    private  Integer totalCount;

    public List<BriefMember> getList() {
        return list;
    }

    public void setList(List<BriefMember> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GetEnrollMembersByPagingResponseData{" +
                "list=" + list +
                ", totalCount=" + totalCount +
                '}';
    }
}
