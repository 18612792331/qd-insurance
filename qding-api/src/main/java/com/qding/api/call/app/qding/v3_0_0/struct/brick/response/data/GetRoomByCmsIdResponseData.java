package com.qding.api.call.app.qding.v3_0_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.Room;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetRoomByCmsIdResponseData extends ResponseData {

    private static final long serialVersionUID = -2428487856607774815L;

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
        return "GetRoomByCmsIdResponseData{" +
                "list=" + list +
                ", totalCount=" + totalCount +
                '}';
    }
}
