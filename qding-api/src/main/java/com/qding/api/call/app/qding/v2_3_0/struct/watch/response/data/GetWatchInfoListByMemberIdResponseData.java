package com.qding.api.call.app.qding.v2_3_0.struct.watch.response.data;

import com.qding.api.call.app.qding.v2_3_0.struct.watch.WatchInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/5/16.
 */
public class GetWatchInfoListByMemberIdResponseData extends ResponseData {

    private static final long serialVersionUID = 4613826836833952546L;

    private List<WatchInfo> list;

    public List<WatchInfo> getList() {
        return list;
    }

    public void setList(List<WatchInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetWatchInfoListByMemberIdResponseData{" +
                "list=" + list +
                '}';
    }
}
