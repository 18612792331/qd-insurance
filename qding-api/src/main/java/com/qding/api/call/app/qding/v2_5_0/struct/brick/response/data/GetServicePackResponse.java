package com.qding.api.call.app.qding.v2_5_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request.IncrCartNumRequest;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/10/26.
 */
public class GetServicePackResponse extends ResponseData {

    private static final long serialVersionUID = 8183313711410946895L;

    @ExplainAnnotation(explain = "版本code")
    private String versionCode;

    @ExplainAnnotation (explain = "补丁路径")
    private String patchUrl;

    @ExplainAnnotation (explain = "补丁code")
    private Integer patchCode;

    @ExplainAnnotation (explain = "IOS开关",desc = "1:打开，0：关闭")
    private Integer isHotfixOpenForIos = 0;

    @ExplainAnnotation (explain = "android开关",desc = "1:清除补丁包，0：不清除补丁包")
    private Integer isClearForAndroid = 0;


    public Integer getIsHotfixOpenForIos() {
        return isHotfixOpenForIos;
    }

    public void setIsHotfixOpenForIos(Integer isHotfixOpenForIos) {
        this.isHotfixOpenForIos = isHotfixOpenForIos;
    }

    public Integer getIsClearForAndroid() {
        return isClearForAndroid;
    }

    public void setIsClearForAndroid(Integer isClearForAndroid) {
        this.isClearForAndroid = isClearForAndroid;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }

    public Integer getPatchCode() {
        return patchCode;
    }

    public void setPatchCode(Integer patchCode) {
        this.patchCode = patchCode;
    }

    @Override
    public String toString() {
        return "GetServicePackResponse{" +
                "versionCode='" + versionCode + '\'' +
                ", patchUrl='" + patchUrl + '\'' +
                ", patchCode=" + patchCode +
                ", isHotfixOpenForIos=" + isHotfixOpenForIos +
                ", isClearForAndroid=" + isClearForAndroid +
                '}';
    }
}
