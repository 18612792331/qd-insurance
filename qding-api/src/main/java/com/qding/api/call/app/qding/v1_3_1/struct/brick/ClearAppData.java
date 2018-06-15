package com.qding.api.call.app.qding.v1_3_1.struct.brick;

import java.io.Serializable;

/**
 * Created by qd on 2015/9/25.
 */
public class ClearAppData implements Serializable {

    private static final long serialVersionUID = 4247126929484098587L;

    private Integer cacheStrategy = 0;

    private Long cacheTimeStamp ;

    public Long getCacheTimeStamp() {
        return cacheTimeStamp;
    }

    public void setCacheTimeStamp(Long cacheTimeStamp) {
        this.cacheTimeStamp = cacheTimeStamp;
    }

    public Integer getCacheStrategy() {
        return cacheStrategy;
    }

    public void setCacheStrategy(Integer cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
    }
}
