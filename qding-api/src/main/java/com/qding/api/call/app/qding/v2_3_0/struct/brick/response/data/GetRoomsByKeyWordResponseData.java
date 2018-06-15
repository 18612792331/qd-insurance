package com.qding.api.call.app.qding.v2_3_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Room;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/8/11.
 */


public class GetRoomsByKeyWordResponseData extends ResponseData {

    private static final long serialVersionUID = -7654126062406134557L;

    @ExplainAnnotation(explain = "房间列表")
    private List<Room> list;

    @ExplainAnnotation (explain = "总计路数")
    private int totalCount;

    public List<Room> getList() {
        return list;
    }

    public void setList(List<Room> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "GetRoomsByKeyWordResponseData{" +
                "list=" + list +
                ", totalCount=" + totalCount +
                '}';
    }
}
