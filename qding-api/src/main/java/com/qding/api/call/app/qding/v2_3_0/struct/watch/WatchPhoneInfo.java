package com.qding.api.call.app.qding.v2_3_0.struct.watch;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * 手表电话
 * Created by qd on 2016/5/18.
 */
public class WatchPhoneInfo implements Serializable {

    @ExplainAnnotation(explain = "手表串号")
    private String imei = "";

    @ExplainAnnotation(explain = "手表电话")
    private String watchPhone = "";

    @ExplainAnnotation(explain = "亲情号码")
    private List<OtherPhoneInfo> otherPhoneList;

    public String getWatchPhone() {
        return watchPhone;
    }

    public void setWatchPhone(String watchPhone) {
        this.watchPhone = watchPhone;
    }

    public List<OtherPhoneInfo> getOtherPhoneList() {
        return otherPhoneList;
    }

    public void setOtherPhoneList(List<OtherPhoneInfo> otherPhoneList) {
        this.otherPhoneList = otherPhoneList;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        return "WatchPhoneInfo{" +
                "imei='" + imei + '\'' +
                ", watchPhone='" + watchPhone + '\'' +
                ", otherPhoneList=" + otherPhoneList +
                '}';
    }
}
