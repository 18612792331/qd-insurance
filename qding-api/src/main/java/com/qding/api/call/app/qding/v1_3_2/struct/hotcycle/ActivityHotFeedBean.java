package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle;

import java.io.Serializable;

/**
 * Created by jiawenzheng on 2015/7/24.
 */
public class ActivityHotFeedBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5724746029978435134L;

	private String feedId;

    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }
}
