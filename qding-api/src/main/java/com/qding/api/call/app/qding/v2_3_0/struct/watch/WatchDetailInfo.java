package com.qding.api.call.app.qding.v2_3_0.struct.watch;


import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * 手表详情
 * Created by qd on 2016/5/18.
 */
public class WatchDetailInfo implements Serializable {

    @ExplainAnnotation(explain = "手表账号信息")
    private WatchAccountInfo accountInfo;

    @ExplainAnnotation(explain = "当前绑定人信息")
    private WatchBindInfo currentWatchBindInfo;

    @ExplainAnnotation(explain = "手表电话信息，包括自身电话和亲情电话列表")
    private WatchPhoneInfo watchPhone;

    @ExplainAnnotation(explain = "此手表其他绑定人信息")
    private List<WatchBindInfo> otherWatchBindList;

    public WatchAccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(WatchAccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public WatchBindInfo getCurrentWatchBindInfo() {
        return currentWatchBindInfo;
    }

    public void setCurrentWatchBindInfo(WatchBindInfo currentWatchBindInfo) {
        this.currentWatchBindInfo = currentWatchBindInfo;
    }

    public WatchPhoneInfo getWatchPhone() {
        return watchPhone;
    }

    public void setWatchPhone(WatchPhoneInfo watchPhone) {
        this.watchPhone = watchPhone;
    }

    public List<WatchBindInfo> getOtherWatchBindList() {
        return otherWatchBindList;
    }

    public void setOtherWatchBindList(List<WatchBindInfo> otherWatchBindList) {
        this.otherWatchBindList = otherWatchBindList;
    }

    @Override
    public String toString() {
        return "WatchDetailInfo{" +
                "accountInfo=" + accountInfo +
                ", currentWatchBindInfo=" + currentWatchBindInfo +
                ", watchPhone=" + watchPhone +
                ", otherWatchBindList=" + otherWatchBindList +
                '}';
    }
}
