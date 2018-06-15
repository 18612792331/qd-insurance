package com.qding.api.call.app.qding.v1_3_2.struct.popularize;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/6.
 */
public class PopularizeQuickMarkDto implements Serializable {


    private static final long serialVersionUID = -1466738619455705786L;
    /**
     * 二维码图片路径
     */
    private String quickMarkUrl;

    /**
     * 跳转URL
     */
    private String skipUrl;

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public String getQuickMarkUrl() {
        return quickMarkUrl;
    }

    public void setQuickMarkUrl(String quickMarkUrl) {
        this.quickMarkUrl = quickMarkUrl;
    }

    public PopularizeQuickMarkDto(String quickMarkUrl,String skipUrl) {
        this.quickMarkUrl = quickMarkUrl;
        this.skipUrl = skipUrl;
    }

    public PopularizeQuickMarkDto(String quickMarkUrl) {
        this.quickMarkUrl = quickMarkUrl;
    }

    public PopularizeQuickMarkDto() {
    }
}
