package com.qding.api.call.app.qding.v2_3_0.struct.watch;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

public class WatchInfo implements Serializable {

    private static final long serialVersionUID = 2328313377915896877L;

    @ExplainAnnotation(explain = "手表串号")
    private String imei = "";

    @ExplainAnnotation(explain = "手表电话信息")
    private WatchPhoneInfo watchPhone;

    @ExplainAnnotation(explain = "手表账户信息")
    private WatchAccountInfo accountInfo;


    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public WatchPhoneInfo getWatchPhone() {
        return watchPhone;
    }

    public void setWatchPhone(WatchPhoneInfo watchPhone) {
        this.watchPhone = watchPhone;
    }

    public WatchAccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(WatchAccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    @Override
    public String toString() {
        return "WatchInfo{" +
                "imei='" + imei + '\'' +
                ", watchPhone=" + watchPhone +
                ", accountInfo=" + accountInfo +
                '}';
    }
}
