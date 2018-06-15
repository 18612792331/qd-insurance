package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.response.data;

import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/10/19.
 */
public class AlipayServeWindowResponseData  extends ResponseData {


    private static final long serialVersionUID = -4589372463040061973L;

    private String url="";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AlipayServeWindowResponseData [ url="+url+", toString()=" + super.toString() + "]";
    }


}
