package com.qding.api.call.app.qding.v1_3_2.struct.popularize.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by Administrator on 2015/8/6.
 */
@Validate
public class AddShareFeedImgRequest   extends BaseRequest {

    private static final long serialVersionUID = -7133488563918448910L;

    /**
     * 分享feed后的合成图
     */
    @NotNullValidate
    private String shareImgUrl;

    /**
     * 图文信息ID
     */
    @NotNullValidate
    private String feedId;

    public String getShareImgUrl() {
        return shareImgUrl;
    }

    public void setShareImgUrl(String shareImgUrl) {
        this.shareImgUrl = shareImgUrl;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }
}
