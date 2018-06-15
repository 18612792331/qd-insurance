package com.qding.api.call.app.qding.v2_4_0.struct.hotcycle.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/6/16.
 */
public class GetFeedDetailListResponseData extends ResponseData{

    private static final long serialVersionUID = 5602802772714815722L;

    @ExplainAnnotation (explain = "总记录数")
    private int totalCount = 0;

    @ExplainAnnotation (explain = "当前记录数")
    private int recordCount;

    @ExplainAnnotation (explain = "左切屏图文信息流")
    private List<Feed> lList;

    @ExplainAnnotation (explain = "右切屏图文信息流")
    private List<Feed> rList;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public List<Feed> getlList() {
        return lList;
    }

    public void setlList(List<Feed> lList) {
        this.lList = lList;
    }

    public List<Feed> getrList() {
        return rList;
    }

    public void setrList(List<Feed> rList) {
        this.rList = rList;
    }



    @Override
    public String toString() {
        return "GetFeedDetailListResponseData{" +
                "totalCount=" + totalCount +
                ", recordCount=" + recordCount +
                ", lList=" + lList +
                ", rList=" + rList +
                '}';
    }
}
