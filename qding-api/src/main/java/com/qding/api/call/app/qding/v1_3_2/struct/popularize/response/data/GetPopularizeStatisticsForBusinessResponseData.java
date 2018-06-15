package com.qding.api.call.app.qding.v1_3_2.struct.popularize.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.popularize.StatisticsItemsForBusiness;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/11/23.
 */
public class GetPopularizeStatisticsForBusinessResponseData extends ResponseData {

    private static final long serialVersionUID = -3142171948419778057L;

    private List<StatisticsItemsForBusiness> list;

    private Integer totalCount;

    public List<StatisticsItemsForBusiness> getList() {
        return list;
    }

    public void setList(List<StatisticsItemsForBusiness> list) {
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
        return "GetPopularizeStatisticsForBusinessResponseData [list="+list+",totalCount="+totalCount+",toString()=" + super.toString() + "]";
    }
}
