package com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.request;


import com.qding.framework.common.api.struct.request.BaseRequest;

import java.util.List;

/**
 * Created by xuxiaoxing on 2018/5/3.
 */
public class ShareCouponRequest extends BaseRequest {


    private static final long serialVersionUID = 1L;


    private String shareId;

    private String groupId;


    private List<Long> skuIds;

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Long> getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(List<Long> skuIds) {
        this.skuIds = skuIds;
    }
}
