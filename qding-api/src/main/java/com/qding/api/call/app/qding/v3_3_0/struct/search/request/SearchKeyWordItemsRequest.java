package com.qding.api.call.app.qding.v3_3_0.struct.search.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2017/9/7.
 */
public class SearchKeyWordItemsRequest extends BaseRequest {

    private static final long serialVersionUID = 5300865412280519007L;

    @ExplainAnnotation(explain = "关键词")
    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "SearchKeyWordItemsRequest{" +
                "keyWord='" + keyWord + '\'' +
                '}';
    }
}
