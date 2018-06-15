package com.qding.api.call.app.qding.v2_5_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.brick.DomainDTO;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/12/12.
 */
public class GetUrlWhiteListResponseData extends ResponseData {

    private static final long serialVersionUID = -1692249248024843662L;

    @ExplainAnnotation(explain = "域名白名单列表")
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetUrlWhiteListResponseData{" +
                "list=" + list +
                '}';
    }
}
