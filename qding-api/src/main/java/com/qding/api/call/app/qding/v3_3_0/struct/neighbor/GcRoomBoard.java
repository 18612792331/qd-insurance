package com.qding.api.call.app.qding.v3_3_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.util.List;

/**
 * Created by qd on 2017/9/11.
 */
public class GcRoomBoard extends SkipUrl {

    @ExplainAnnotation (explain = "群组列表")
    private List<GcRoomDTO> list;

    @ExplainAnnotation (explain = "板块标题")
    private String title;

    public List<GcRoomDTO> getList() {
        return list;
    }

    public void setList(List<GcRoomDTO> list) {
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GcRoomBoard{" +
                "list=" + list +
                ", title='" + title + '\'' +
                '}';
    }
}
