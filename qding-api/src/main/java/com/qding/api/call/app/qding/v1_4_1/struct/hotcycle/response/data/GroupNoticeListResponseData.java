package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.Notice;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/10/27.
 */
public class GroupNoticeListResponseData extends ResponseData {

    private static final long serialVersionUID = -45884741864329142L;

    @ExplainAnnotation(explain = "群公告总数")
    private int totalCount;

    @ExplainAnnotation (explain = "群类型",desc = "1兴趣群,2社区群,3内部群,4报名讨论组")
    private Integer gcRoomType;

    @ExplainAnnotation(explain = "群公告列表")
    private List<Notice> list;

    @ExplainAnnotation(explain = " 群聊人员类型(1普通用户2管理员3群主)")
    private Integer gcMemberType ;

    public Integer getGcRoomType() {
        return gcRoomType;
    }

    public void setGcRoomType(Integer gcRoomType) {
        this.gcRoomType = gcRoomType;
    }

    public Integer getGcMemberType() {
        return gcMemberType;
    }

    public void setGcMemberType(Integer gcMemberType) {
        this.gcMemberType = gcMemberType;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<Notice> getList() {
        return list;
    }

    public void setList(List<Notice> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GroupNoticeListResponseData{" +
                "totalCount=" + totalCount +
                ", gcRoomType=" + gcRoomType +
                ", list=" + list +
                ", gcMemberType=" + gcMemberType +
                '}';
    }
}
