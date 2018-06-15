package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.UserGroup;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/10/27.
 */
public class HotcycleHomePageResponseData extends ResponseData {

    private static final long serialVersionUID = 8722040248346951635L;

    //申请数量
    private Integer applyCount;
    //社区公共群
    private List<UserGroup> publicGroupList;
    //兴趣群
    private List<UserGroup> interestGroupList;
    //内部群
    private List<UserGroup> innerGroupList;
    //是否有更多兴趣群
    private boolean isContainMoreInterList;

    public List<UserGroup> getPublicGroupList() {
        return publicGroupList;
    }

    public void setPublicGroupList(List<UserGroup> publicGroupList) {
        this.publicGroupList = publicGroupList;
    }

    public List<UserGroup> getInterestGroupList() {
        return interestGroupList;
    }

    public void setInterestGroupList(List<UserGroup> interestGroupList) {
        this.interestGroupList = interestGroupList;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public List<UserGroup> getInnerGroupList() {
        return innerGroupList;
    }

    public void setInnerGroupList(List<UserGroup> innerGroupList) {
        this.innerGroupList = innerGroupList;
    }

    public boolean isContainMoreInterList() {
        return isContainMoreInterList;
    }

    public void setContainMoreInterList(boolean isContainMoreInterList) {
        this.isContainMoreInterList = isContainMoreInterList;
    }

    @Override
    public String toString() {
        return "HotcycleHomePageResponseData{" +
                "applyCount=" + applyCount +
                ", publicGroupList=" + publicGroupList +
                ", interestGroupList=" + interestGroupList +
                ", innerGroupList=" + innerGroupList +
                ", isContainMoreInterList=" + isContainMoreInterList +
                '}';
    }
}
