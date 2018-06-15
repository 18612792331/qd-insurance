package com.qding.api.call.app.qding.v1_4_1.struct.legou.goods.response.data;

import com.qding.api.struct.ResponseData;


/**
 * Created by qd on 2015/11/4.
 */
public class GetLgKeyWordForSysResponseData  extends ResponseData {

    private static final long serialVersionUID = -3556596733212782565L;

    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "GetLgKeyWordForSysResponseData [keyWord=" + keyWord +
                " ,super.toString() ]";
    }
}
