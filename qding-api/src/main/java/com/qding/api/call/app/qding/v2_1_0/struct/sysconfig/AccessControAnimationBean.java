package com.qding.api.call.app.qding.v2_1_0.struct.sysconfig;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/7/22.
 */
public class AccessControAnimationBean  implements Serializable{


    @ExplainAnnotation (explain = "缓存code",desc = "用于前端本地code进行对比，不一致表示有更新")
    private String cachCode ="";

    @ExplainAnnotation (explain = "是否有非默认开门动画",desc = "1:有，0:无")
    private Integer status = Integer.valueOf(0);

    @ExplainAnnotation (explain = "压缩包访问地址")
    private String zipUrl = "";


    public String getCachCode() {
        return cachCode;
    }

    public void setCachCode(String cachCode) {
        this.cachCode = cachCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getZipUrl() {
        return zipUrl;
    }

    public void setZipUrl(String zipUrl) {
        this.zipUrl = zipUrl;
    }

}
