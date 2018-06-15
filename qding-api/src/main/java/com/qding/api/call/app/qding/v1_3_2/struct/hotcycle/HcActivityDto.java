package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle;

import java.io.Serializable;
import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedImage;

/**
 * Created by jiawenzheng on 2015/7/23.
 */
public class HcActivityDto extends ActivityBrief implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -6320070240977223807L;
	private List<FeedImage> list;


    public HcActivityDto() {
        super();
    }

    public HcActivityDto(List<FeedImage> list) {
        super();
        this.list = list;
    }



    public List<FeedImage> getList() {
        return list;
    }

    public void setList(List<FeedImage> list) {
        this.list = list;
    }
}
