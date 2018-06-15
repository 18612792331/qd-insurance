package com.qding.api.call.app.qding.v3_0_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_1.struct.notify.SysNotify;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/2/23.
 */
public class GetPushNoticRemindResponseData extends ResponseData {

    private static final long serialVersionUID = 747885111601850710L;

    @ExplainAnnotation (explain = "图像")
    private String img;

    @ExplainAnnotation (explain = "名称")
    private String name;

    @ExplainAnnotation (explain = "消息列表")
    private List<SysNotify> list;

    @ExplainAnnotation (explain = "消息总数")
    private int totalCount;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysNotify> getList() {
        return list;
    }

    public void setList(List<SysNotify> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
