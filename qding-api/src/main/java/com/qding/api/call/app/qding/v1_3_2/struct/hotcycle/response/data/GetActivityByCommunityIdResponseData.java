package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.HcActivityDto;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by Administrator on 2015/7/23.
 */
public class GetActivityByCommunityIdResponseData extends ResponseData {


    /**
	 * 
	 */
	private static final long serialVersionUID = -3275509478204523883L;

	/**
	 * 
	 */

	private List<HcActivityDto> list;

    /**
     * 总记录数
     */
    private int totalCount;


    public List<HcActivityDto> getList() {
        return list;
    }

    public void setList(List<HcActivityDto> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public GetActivityByCommunityIdResponseData() {
    }

    public GetActivityByCommunityIdResponseData(List<HcActivityDto> list, int totalCount) {
        this.list = list;
        this.totalCount = totalCount;
    }



    @Override
    public String toString() {
        return "GetActivityByCommunityIdResponseData [totalCount="
                + totalCount + ",list=" + list +", toString()=" + super.toString() + "]";
    }
}
