package com.qding.api.call.app.qding.v3_3_0.struct.search.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2017/9/6.
 */
public class SearchItemsRequest extends BaseRequest {

    private static final long serialVersionUID = 5053256530990637029L;

    @ExplainAnnotation (explain = "搜索关键词")
    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "SearchItemsRequest [keyWord=" + keyWord +
                " ,super.toString() ]";
    }
}
