package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle;


import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedImage;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedTag;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/7/30.
 */
public class TimeLineFeed  implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -3237551383158823681L;

	/**
     * 信息标签列表
     */
    private List<FeedTag> feedTags;

    /**
     * 信息图片和feedId
     */
    private FeedImage feedImage;

    public FeedImage getFeedImage() {
        return feedImage;
    }

    public void setFeedImage(FeedImage feedImage) {
        this.feedImage = feedImage;
    }

    public List<FeedTag> getFeedTags() {
        return feedTags;
    }

    public void setFeedTags(List<FeedTag> feedTags) {
        this.feedTags = feedTags;
    }
}
