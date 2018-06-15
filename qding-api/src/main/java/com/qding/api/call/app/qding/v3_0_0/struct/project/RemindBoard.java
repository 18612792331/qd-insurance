package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_1.struct.notify.SysNotify;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/3/1.
 */
public class RemindBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 6007835086294777107L;


    @ExplainAnnotation(explain = "板块Code")
    private  String sectionCode;

    @ExplainAnnotation(explain = "排序索引")
    private Integer sortIndex;

    @ExplainAnnotation(explain = "图像")
    private String img;

    @ExplainAnnotation (explain = "名称")
    private String name;

    @ExplainAnnotation (explain = "消息")
    private String sysNotify;

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

    public String getSysNotify() {
        return sysNotify;
    }

    public void setSysNotify(String sysNotify) {
        this.sysNotify = sysNotify;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }
}
