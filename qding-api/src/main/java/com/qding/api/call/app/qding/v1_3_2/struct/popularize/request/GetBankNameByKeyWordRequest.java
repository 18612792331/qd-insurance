package com.qding.api.call.app.qding.v1_3_2.struct.popularize.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by jiawenzheng on 2015/7/28.
 */
public class GetBankNameByKeyWordRequest   extends BaseRequest {


	private static final long serialVersionUID = 5889782313217984371L;
	/**
     * 输入的查询字符
     */
    private String queryKeyWord;

    public String getQueryKeyWord() {
        return queryKeyWord;
    }

    public void setQueryKeyWord(String queryKeyWord) {
        this.queryKeyWord = queryKeyWord;
    }

    public GetBankNameByKeyWordRequest() {
    }
}
