package com.qding.api.call.app.qding.v3_0_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.user.GiftInfoDTO;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/3/3.
 */
public class GetGiftListResponseData extends ResponseData {

    private static final long serialVersionUID = -5146843144933808244L;

    @ExplainAnnotation (explain = "可领取礼包列表")
    private List<GiftInfoDTO> unclaimedlist;

    @ExplainAnnotation (explain = "已领取礼包列表")
    private List<GiftInfoDTO> receivedList;

    @ExplainAnnotation (explain = "总数")
    private Integer totalCount;

    public List<GiftInfoDTO> getUnclaimedlist() {
        return unclaimedlist;
    }

    public void setUnclaimedlist(List<GiftInfoDTO> unclaimedlist) {
        this.unclaimedlist = unclaimedlist;
    }

    public List<GiftInfoDTO> getReceivedList() {
        return receivedList;
    }

    public void setReceivedList(List<GiftInfoDTO> receivedList) {
        this.receivedList = receivedList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    @Override
    public String toString() {
        return "GetGiftListResponseData{" +
                "unclaimedlist=" + unclaimedlist +
                ", receivedList=" + receivedList +
                ", totalCount=" + totalCount +
                '}';
    }
}
