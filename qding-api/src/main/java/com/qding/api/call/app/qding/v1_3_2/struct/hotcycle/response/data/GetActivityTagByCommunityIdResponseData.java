package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedTag;
import com.qding.api.struct.ResponseData;

public class GetActivityTagByCommunityIdResponseData  extends ResponseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2441830164228917978L;
	
	
	private List<FeedTag> tags;

    public List<FeedTag> getTags() {
        return tags;
    }

    public void setTags(List<FeedTag> tags) {
        this.tags = tags;
    }

}
