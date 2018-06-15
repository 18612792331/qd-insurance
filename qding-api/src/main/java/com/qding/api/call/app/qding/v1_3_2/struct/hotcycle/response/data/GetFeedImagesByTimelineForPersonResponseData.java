package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.TimeLineFeedSet;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by jiawenzheng on 2015/7/29.
 */
public class GetFeedImagesByTimelineForPersonResponseData  extends ResponseData {

    /**
	 *
	 */
	private static final long serialVersionUID = 6660995213881614079L;

	/**
     * 当前遍历天数
     */
    private Integer pageNo=1;

    /**
     * 当前遍历的feed集合
     */
    private List<TimeLineFeedSet> list;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public List<TimeLineFeedSet> getList() {
        return list;
    }

    public void setList(List<TimeLineFeedSet> list) {
        this.list = list;
    }

    public GetFeedImagesByTimelineForPersonResponseData() {
    	
    }
    
    @Override
	public String toString() {
		return "GetFeedImagesByTimelineForPersonResponse [list=" + list + ", pageNo="+pageNo+",toString()="
				+ super.toString() + "]";
	}
}
