package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/7/30.
 */
public class TimeLineFeedSet implements Serializable {

    

	/**
	 * 
	 */
	private static final long serialVersionUID = -937221577164006165L;

	/**
     * 时间戳
     */
    private Long timestamp;

    /**
     * feed信息集合
     */
    private List<TimeLineFeed> timeLineFeedList;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public List<TimeLineFeed> getTimeLineFeedList() {
        return timeLineFeedList;
    }

    public void setTimeLineFeedList(List<TimeLineFeed> timeLineFeedList) {
        this.timeLineFeedList = timeLineFeedList;
    }
}
