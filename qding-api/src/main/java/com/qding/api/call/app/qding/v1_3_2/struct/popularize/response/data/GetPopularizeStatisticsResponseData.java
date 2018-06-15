package com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.popularize.StatisticsItemsBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/11/23.
 */
public class GetPopularizeStatisticsResponseData  extends ResponseData {

    /**
     * 统计数量
     */
    private List<StatisticsItemsBean> list;

    /**
     * 总条数
     */
    private Integer totalCount;

    public List<StatisticsItemsBean> getList() {
        return list;
    }

    public void setList(List<StatisticsItemsBean> list) {
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
        return "GetPopularizeStatisticsResponseData [list="+list+",totalCount="+totalCount+",toString()=" + super.toString() + "]";
    }
}
