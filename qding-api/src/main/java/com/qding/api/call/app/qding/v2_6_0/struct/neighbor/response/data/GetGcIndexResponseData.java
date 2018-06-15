package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.UserGroup;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/9/14.
 */
public class GetGcIndexResponseData extends ResponseData {

    private static final long serialVersionUID = 2623819290135758277L;

    @ExplainAnnotation(explain = "申请数量")
    private Integer applyCount;

    @ExplainAnnotation(explain = "社区公共群")
    private List<UserGroup> publicGroupList;

    @ExplainAnnotation(explain = "话题群")
    private List<UserGroup> discussionList;

    @ExplainAnnotation(explain = "兴趣群")
    private List<UserGroup> interestGroupList;

    @ExplainAnnotation(explain = "是否有更多兴趣群")
    private boolean isContainMoreInterList;

    public boolean isContainMoreInterList() {
        return isContainMoreInterList;
    }

    public void setContainMoreInterList(boolean containMoreInterList) {
        isContainMoreInterList = containMoreInterList;
    }

    public List<UserGroup> getPublicGroupList() {
        return publicGroupList;
    }

    public void setPublicGroupList(List<UserGroup> publicGroupList) {
        this.publicGroupList = publicGroupList;
    }

    public List<UserGroup> getDiscussionList() {
        return discussionList;
    }

    public void setDiscussionList(List<UserGroup> discussionList) {
        this.discussionList = discussionList;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public List<UserGroup> getInterestGroupList() {
        return interestGroupList;
    }

    public void setInterestGroupList(List<UserGroup> interestGroupList) {
        this.interestGroupList = interestGroupList;
    }

    @Override
    public String toString() {
        return "GetGcIndexResponseData{" +
                "applyCount=" + applyCount +
                ", publicGroupList=" + publicGroupList +
                ", discussionList=" + discussionList +
                ", interestGroupList=" + interestGroupList +
                ", isContainMoreInterList=" + isContainMoreInterList +
                '}';
    }
}
