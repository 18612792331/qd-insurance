package com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon.response.ShareCouponDetailDTO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xuxiaoxing on 2018/5/3.
 */
public class ShareCouponDTO implements Serializable {

    @ExplainAnnotation(explain = "活动id")
    private String shareId;

    @ExplainAnnotation(explain = "活动名称")
    private String activityName;

    @ExplainAnnotation(explain = "活动开始时间")
    private Long startTime;

    @ExplainAnnotation(explain = "活动结束时间")
    private Long endTime;

    @ExplainAnnotation(explain = "拼团id")
    private String groupId;

    @ExplainAnnotation(explain = "拼团状态",desc = "0未成团, 1已成团")
    private Integer groupStatus;

    @ExplainAnnotation(explain = "成团人数")
    private Integer maxNum;

    @ExplainAnnotation(explain = "分享标题")
    private String shareTitle;

    @ExplainAnnotation(explain = "分享内容")
    private String shareContent;

    @ExplainAnnotation(explain = "分享图片")
    private String shareImage;

    @ExplainAnnotation(explain = "拼团参与人详情")
    private List<ShareCouponDetailDTO> shareCouponDetailDTOList;

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Integer groupStatus) {
        this.groupStatus = groupStatus;
    }

    public List<ShareCouponDetailDTO> getShareCouponDetailDTOList() {
        return shareCouponDetailDTOList;
    }

    public void setShareCouponDetailDTOList(List<ShareCouponDetailDTO> shareCouponDetailDTOList) {
        this.shareCouponDetailDTOList = shareCouponDetailDTOList;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }


    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }
}
