package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.UserActivityBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 */
public class GetActivityByUserIdResponseData  extends ResponseData {

    private static final long serialVersionUID = -4150456507666038908L;
    /**
     * 活动列表
     */
    private List<UserActivityBean> list;

    /**
     * 总记录数
     */
    private int totalCount;

    /**
     * 当前页返回记录数
     */
    private int recordCount;


    public List<UserActivityBean> getList() {
        return list;
    }

    public void setList(List<UserActivityBean> list) {
        this.list = list;
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

    public GetActivityByUserIdResponseData() {
    }


    @Override
    public String toString() {
        return "GetActivityByUserIdResponseData [totalCount=" + totalCount
                + ", recordCount=" + recordCount + ", totalUserCount="
                +", list=" + list + ", toString()=" + super.toString() + "]";
    }
}
