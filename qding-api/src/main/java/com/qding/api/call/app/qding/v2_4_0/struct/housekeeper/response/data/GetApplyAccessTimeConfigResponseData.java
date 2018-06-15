package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/6/21.
 */
public class GetApplyAccessTimeConfigResponseData extends ResponseData {

    private static final long serialVersionUID = 8117788858607393209L;

    @ExplainAnnotation (explain = "系统时间")
    private Long sysTime;

    @ExplainAnnotation (explain = "边界时间点")
    private String borderTime;

    @ExplainAnnotation (explain = "是否展示明日标签")
    private boolean isShowLable = false;

    public boolean getIsShowLable() {
        return isShowLable;
    }

    public void setIsShowLable(boolean showLable) {
        isShowLable = showLable;
    }

    public Long getSysTime() {
        return sysTime;
    }

    public void setSysTime(Long sysTime) {
        this.sysTime = sysTime;
    }

    public String getBorderTime() {
        return borderTime;
    }

    public void setBorderTime(String borderTime) {
        this.borderTime = borderTime;
    }

    @Override
    public String toString() {
        return "GetApplyAccessTimeConfigResponseData{" +
                "sysTime=" + sysTime +
                ", borderTime='" + borderTime + '\'' +
                ", isShowLable=" + isShowLable +
                '}';
    }
}
