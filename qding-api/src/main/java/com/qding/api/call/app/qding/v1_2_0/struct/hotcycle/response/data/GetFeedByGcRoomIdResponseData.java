package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/10/29.
 */
public class GetFeedByGcRoomIdResponseData extends ResponseData {

    private static final long serialVersionUID = -7717793368414534149L;

    private int totalCount;

    private int recordCount;

    private int refreshCount;

    private List<Feed> list;

    public GetFeedByGcRoomIdResponseData() {

    }

    public GetFeedByGcRoomIdResponseData(int totalCount, int recordCount,
                                         List<Feed> list) {
        super();
        this.totalCount = totalCount;
        this.recordCount = recordCount;
        this.list = list;
    }

    public int getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(int refreshCount) {
        this.refreshCount = refreshCount;
    }

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

    public List<Feed> getList() {
        return list;
    }

    public void setList(List<Feed> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetFeedByGcRoomIdResponseData [totalCount=" + totalCount
                + ", recordCount=" + recordCount + ", list=" + list
                + ", toString()=" + super.toString() + "]";
    }
}
