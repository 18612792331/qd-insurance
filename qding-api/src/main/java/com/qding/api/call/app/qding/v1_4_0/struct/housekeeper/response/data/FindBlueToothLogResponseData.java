package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.BlueToothLog;
import com.qding.api.struct.ResponseData;


import java.util.List;

/**
 * Created by qd on 2015/10/10.
 */
public class FindBlueToothLogResponseData extends ResponseData {


    private static final long serialVersionUID = -5924877474461847304L;

    private List<BlueToothLog> list;

    @ExplainAnnotation(explain = "是否有下一页")
    private boolean haveNextPage;

    public List<BlueToothLog> getList() {
        return list;
    }

    public void setList(List<BlueToothLog> list) {
        this.list = list;
    }

    public boolean isHaveNextPage() {
        return haveNextPage;
    }

    public void setHaveNextPage(boolean haveNextPage) {
        this.haveNextPage = haveNextPage;
    }

    @Override
    public String toString() {
        return "FindBlueToothLogResponseData{" +
                "list=" + list +
                ", haveNextPage=" + haveNextPage +
                '}';
    }
}
